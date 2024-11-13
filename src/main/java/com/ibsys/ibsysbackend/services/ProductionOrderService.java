package com.ibsys.ibsysbackend.services;

import com.ibsys.ibsysbackend.dto.ProductionOrderDto;
import com.ibsys.ibsysbackend.entities.ProductionOrder;
import com.ibsys.ibsysbackend.entities.SaleAndProductionProgram;
import com.ibsys.ibsysbackend.entities.WaitingListWorkPlace;
import com.ibsys.ibsysbackend.repositories.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class ProductionOrderService {

    private final ProductionOrderRepository productionOrderRepository;
    private final WaitinglistWorkplaceRepository waitinglistWorkplaceRepository;
    private final CapacityPlanRepository capacityPlanRepository;
    private final CapacityPlanSumUpRepository capacityPlanSumUpRepository;

    private final SaleAndProductionProgramRepository saleAndProductionProgramRepository;
    private final ForecastRepository forecastRepository;
    private final MaterialPlanRepository materialPlanRepository;

    public List<ProductionOrder> findAll() {
        return productionOrderRepository.findAll();
    }
    public void createMany(final List<ProductionOrderDto> productionOrderDtoList) {
        var productionOrderList = productionOrderDtoList.stream()
                .map(ProductionOrderDto::toProductionOrder)
                .toList();

        productionOrderRepository.saveAll(productionOrderList);
    }

    public void update(final ProductionOrder productionOrder) {
        var productionOrderWithUpdatedAmount = updateAmountToProduceOnProductionOrder(productionOrder);
        productionOrderRepository.save(productionOrderWithUpdatedAmount);
        updateProductionOrderOfAllArticles();
    }

    ProductionOrder updateAmountToProduceOnProductionOrder(final ProductionOrder productionOrder) {
        return sumUpProductionOrder(productionOrder);
    }

    private ProductionOrder sumUpProductionOrder(ProductionOrder productionOrder) {
        var saleOrder = productionOrder.getSaleOrder() == null ? 0 : productionOrder.getSaleOrder();
        var waitingQueue = productionOrder.getWaitingQueue() == null ? 0 : productionOrder.getWaitingQueue();
        var warehousePreviousPeriod = productionOrder.getWarehousePreviousPeriod() == null ? 0 : productionOrder.getWarehousePreviousPeriod();
        var ordersInWaitingQueue = productionOrder.getOrdersInWaitingQueue() == null ? 0 : productionOrder.getOrdersInWaitingQueue();
        var workInProgress = productionOrder.getWorkInProgress() == null ? 0 : productionOrder.getWorkInProgress();

        var sum1 = saleOrder +
                waitingQueue -
                warehousePreviousPeriod -
                ordersInWaitingQueue -
                workInProgress;

        if (Objects.equals(productionOrder.getArticle(), StuecklistenGroup.P1)) {
            var saleAndProductionProgramP1 = saleAndProductionProgramRepository.findByArticle(StuecklistenGroup.P1);
            var saleAndProductionProgramQuantity = saleAndProductionProgramP1.isEmpty() ? 0 : saleAndProductionProgramP1.get().getPN();

            productionOrder.setPlannedSafetyStock((saleAndProductionProgramQuantity - sum1));
        }
        if (Objects.equals(productionOrder.getArticle(), StuecklistenGroup.P2)) {
            var saleAndProductionProgramP2 = saleAndProductionProgramRepository.findByArticle(StuecklistenGroup.P2);
            var saleAndProductionProgramQuantity = saleAndProductionProgramP2.isEmpty() ? 0 : saleAndProductionProgramP2.get().getPN();

            productionOrder.setPlannedSafetyStock((saleAndProductionProgramQuantity - sum1));
        }
        if (Objects.equals(productionOrder.getArticle(), StuecklistenGroup.P3)) {
            var saleAndProductionProgramP3 = saleAndProductionProgramRepository.findByArticle(StuecklistenGroup.P3);
            var saleAndProductionProgramQuantity = saleAndProductionProgramP3.isEmpty() ? 0 : saleAndProductionProgramP3.get().getPN();

            productionOrder.setPlannedSafetyStock((saleAndProductionProgramQuantity - sum1));
        }

        var plannedSafetyStock = productionOrder.getPlannedSafetyStock() == null ? 0 : productionOrder.getPlannedSafetyStock();

        var sum = saleOrder +
                waitingQueue +
                plannedSafetyStock -
                warehousePreviousPeriod -
                ordersInWaitingQueue -
                workInProgress;

        productionOrder.setProductionOrder(sum);
        return productionOrder;
    }

    public int sumUpAllAuftraegeOfItemInWarteschlange(int id) {
        ArrayList<WaitingListWorkPlace> waitinglistWorkplaces = waitinglistWorkplaceRepository.findByItem(id);
        return waitinglistWorkplaces.stream()
                .mapToInt(wl -> {
                    if (wl.getItem() == 16 || wl.getItem() == 17 || wl.getItem() == 26) {
                        var combinedValue = wl.getAmount();
                        return (int) Math.ceil((double) combinedValue / 3);
                    }
                    return wl.getAmount();
                })
                .findFirst()
                .orElse(0);
    }

    SaleAndProductionProgram getSaleAndProductionProgramOfSpecificGood(final String good) {
        return saleAndProductionProgramRepository.findAll().stream()
                .filter(s -> Objects.equals(s.getArticle(), good))
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public void updateProductionOrderOfAllArticles() {
        var allProductionOrders = productionOrderRepository.findAll();

        // Set setSaleOrder only for P1, P2, and P3
        var updatedProductionOrders = allProductionOrders.stream()
                .map(p -> {
                    var forecastFromDb = forecastRepository.findAll().get(0);
                    if (Objects.equals(p.getArticle(), StuecklistenGroup.P1)) {
                        p.setSaleOrder(forecastFromDb.getP1());
                    }
                    if (Objects.equals(p.getArticle(), StuecklistenGroup.P2)) {
                        p.setSaleOrder(forecastFromDb.getP2());
                    }
                    if (Objects.equals(p.getArticle(), StuecklistenGroup.P3)) {
                        p.setSaleOrder(forecastFromDb.getP3());
                    }

                    return sumUpProductionOrder(p);
                })
                .toList();

        productionOrderRepository.saveAll(updatedProductionOrders);

        // Set setSaleOrder for 2nd and 3rd row
        updatedProductionOrders = updatedProductionOrders.stream()
                .map(p -> {
                    if (Objects.equals(p.getUsedFor(), StuecklistenGroup.P1)) {
                        var productionOrderP1 = productionOrderRepository.findByUsedForAndArticleNumber(StuecklistenGroup.P1, 1);
                        var productionOrderE51 = productionOrderRepository.findByUsedForAndArticleNumber(StuecklistenGroup.P1, 51);

                        switch (p.getArticleNumber()) {
                            case 26, 51 -> {
                                p.setSaleOrder(productionOrderP1.getProductionOrder());
                                p.setWaitingQueue(productionOrderP1.getWaitingQueue());
                                sumUpProductionOrder(p);
                            }
                            case 16, 17, 50 -> {
                                p.setSaleOrder(productionOrderE51.getProductionOrder());
                                p.setWaitingQueue(productionOrderE51.getWaitingQueue());
                                sumUpProductionOrder(p);
                            }
                        }
                    }
                    if (Objects.equals(p.getUsedFor(), StuecklistenGroup.P2)) {
                        var productionOrderP2 = productionOrderRepository.findByUsedForAndArticleNumber(StuecklistenGroup.P2, 2);
                        var productionOrderE56 = productionOrderRepository.findByUsedForAndArticleNumber(StuecklistenGroup.P2, 56);

                        switch (p.getArticleNumber()) {
                            case 26, 56 -> {
                                p.setSaleOrder(productionOrderP2.getProductionOrder());
                                p.setWaitingQueue(productionOrderP2.getWaitingQueue());
                                sumUpProductionOrder(p);
                            }
                            case 16, 17, 55 -> {
                                p.setSaleOrder(productionOrderE56.getProductionOrder());
                                p.setWaitingQueue(productionOrderE56.getWaitingQueue());
                                sumUpProductionOrder(p);
                            }
                        }
                    }
                    if (Objects.equals(p.getUsedFor(), StuecklistenGroup.P3)) {
                        var productionOrderP3 = productionOrderRepository.findByUsedForAndArticleNumber(StuecklistenGroup.P3, 3);
                        var productionOrderE31 = productionOrderRepository.findByUsedForAndArticleNumber(StuecklistenGroup.P3, 31);

                        switch (p.getArticleNumber()) {
                            case 26, 31 -> {
                                p.setSaleOrder(productionOrderP3.getProductionOrder());
                                p.setWaitingQueue(productionOrderP3.getWaitingQueue());
                                sumUpProductionOrder(p);
                            }
                            case 16, 17, 30 -> {
                                p.setSaleOrder(productionOrderE31.getProductionOrder());
                                p.setWaitingQueue(productionOrderE31.getWaitingQueue());
                                sumUpProductionOrder(p);
                            }
                        }
                    }
                    return p;
                })
                .toList();

        productionOrderRepository.saveAll(updatedProductionOrders);

        // Set setSaleOrder for 4th to 6th row
        updatedProductionOrders = updatedProductionOrders.stream()
                .map(p -> {
                    if (Objects.equals(p.getUsedFor(), StuecklistenGroup.P1)) {
                        var productionOrderE51 = productionOrderRepository.findByUsedForAndArticleNumber(StuecklistenGroup.P1, 51);

                        switch (p.getArticleNumber()) {
                            case 16, 17, 50 -> {
                                p.setSaleOrder(productionOrderE51.getProductionOrder());
                                p.setWaitingQueue(productionOrderE51.getWaitingQueue());
                                sumUpProductionOrder(p);
                            }
                        }
                    }
                    if (Objects.equals(p.getUsedFor(), StuecklistenGroup.P2)) {
                        var productionOrderE56 = productionOrderRepository.findByUsedForAndArticleNumber(StuecklistenGroup.P2, 56);

                        switch (p.getArticleNumber()) {
                            case 16, 17, 55 -> {
                                p.setSaleOrder(productionOrderE56.getProductionOrder());
                                p.setWaitingQueue(productionOrderE56.getWaitingQueue());
                                sumUpProductionOrder(p);
                            }
                        }
                    }
                    if (Objects.equals(p.getUsedFor(), StuecklistenGroup.P3)) {
                        var productionOrderE31 = productionOrderRepository.findByUsedForAndArticleNumber(StuecklistenGroup.P3, 31);

                        switch (p.getArticleNumber()) {
                            case 16, 17, 30 -> {
                                p.setSaleOrder(productionOrderE31.getProductionOrder());
                                p.setWaitingQueue(productionOrderE31.getWaitingQueue());
                                sumUpProductionOrder(p);
                            }
                        }
                    }
                    return p;
                })
                .toList();

        // Set setSaleOrder for 7th to 9th row
        updatedProductionOrders = updatedProductionOrders.stream()
                .map(p -> {
                    if (Objects.equals(p.getUsedFor(), StuecklistenGroup.P1)) {
                        var productionOrderE50 = productionOrderRepository.findByUsedForAndArticleNumber(StuecklistenGroup.P1, 50);

                        switch (p.getArticleNumber()) {
                            case 4, 10, 49 -> {
                                p.setSaleOrder(productionOrderE50.getProductionOrder());
                                p.setWaitingQueue(productionOrderE50.getWaitingQueue());
                                sumUpProductionOrder(p);
                            }
                        }
                    }
                    if (Objects.equals(p.getUsedFor(), StuecklistenGroup.P2)) {
                        var productionOrderE55 = productionOrderRepository.findByUsedForAndArticleNumber(StuecklistenGroup.P2, 55);

                        switch (p.getArticleNumber()) {
                            case 5, 11, 54 -> {
                                p.setSaleOrder(productionOrderE55.getProductionOrder());
                                p.setWaitingQueue(productionOrderE55.getWaitingQueue());
                                sumUpProductionOrder(p);
                            }
                        }
                    }
                    if (Objects.equals(p.getUsedFor(), StuecklistenGroup.P3)) {
                        var productionOrderE30 = productionOrderRepository.findByUsedForAndArticleNumber(StuecklistenGroup.P3, 30);

                        switch (p.getArticleNumber()) {
                            case 6, 12, 29 -> {
                                p.setSaleOrder(productionOrderE30.getProductionOrder());
                                p.setWaitingQueue(productionOrderE30.getWaitingQueue());
                                sumUpProductionOrder(p);
                            }
                        }
                    }
                    return p;
                })
                .toList();

        productionOrderRepository.saveAll(updatedProductionOrders);

        // Set setSaleOrder for last three rows
        updatedProductionOrders = updatedProductionOrders.stream()
                .map(p -> {
                    if (Objects.equals(p.getUsedFor(), StuecklistenGroup.P1)) {
                        var productionOrderE49 = productionOrderRepository.findByUsedForAndArticleNumber(StuecklistenGroup.P1, 49);

                        switch (p.getArticleNumber()) {
                            case 7, 13, 18 -> {
                                p.setSaleOrder(productionOrderE49.getProductionOrder());
                                p.setWaitingQueue(productionOrderE49.getWaitingQueue());
                                sumUpProductionOrder(p);
                            }
                        }
                    }
                    if (Objects.equals(p.getUsedFor(), StuecklistenGroup.P2)) {
                        var productionOrderE54 = productionOrderRepository.findByUsedForAndArticleNumber(StuecklistenGroup.P2, 54);

                        switch (p.getArticleNumber()) {
                            case 8, 14, 19 -> {
                                p.setSaleOrder(productionOrderE54.getProductionOrder());
                                p.setWaitingQueue(productionOrderE54.getWaitingQueue());
                                sumUpProductionOrder(p);
                            }
                        }
                    }
                    if (Objects.equals(p.getUsedFor(), StuecklistenGroup.P3)) {
                        var productionOrderE29 = productionOrderRepository.findByUsedForAndArticleNumber(StuecklistenGroup.P3, 29);

                        switch (p.getArticleNumber()) {
                            case 9, 15, 20 -> {
                                p.setSaleOrder(productionOrderE29.getProductionOrder());
                                p.setWaitingQueue(productionOrderE29.getWaitingQueue());
                                sumUpProductionOrder(p);
                            }
                        }
                    }
                    return p;
                })
                .toList();

        productionOrderRepository.saveAll(updatedProductionOrders);

        updateQuantitiesOfCapacityPlan();
        updateAmountOfWorkingPlacesInCapacityPlan();
        updateCapacityPlanSumUpTable();
        //updateMaterialPlan();
    }

    @Transactional
    void updateQuantitiesOfCapacityPlan() {
        var productionOrderList = productionOrderRepository.findAll();
        var capacityPlanList = capacityPlanRepository.findAll();

        var capacityPlanListToUpdate = capacityPlanList.stream()
                .peek(c -> {
                    var sumOfProductionOrder = productionOrderList.stream()
                            .filter(p -> Objects.equals(p.getArticleNumber(), c.getArticleNumber()))
                            .mapToInt(ProductionOrder::getProductionOrder)
                            .reduce(0, Integer::sum);

                    c.setQuantity(sumOfProductionOrder);
                }).toList();

        capacityPlanRepository.saveAll(capacityPlanListToUpdate);
    }

    @Transactional
    void updateAmountOfWorkingPlacesInCapacityPlan() {
        var capacityPlanList = capacityPlanRepository.findAll();

        var capacityPlanListToUpdate = capacityPlanList.stream()
                .peek(c -> {
                    switch (c.getArticleNumber()) {
                        case 4, 5, 6, 7, 8, 9 -> {
                            c.setWorkplace10(4 * c.getQuantity());
                            c.setWorkplace11(3 * c.getQuantity());
                        }
                        case 10, 13 -> {
                            c.setWorkplace7(2 * c.getQuantity());
                            c.setWorkplace8(c.getQuantity());
                            c.setWorkplace9(3 * c.getQuantity());
                            c.setWorkplace12(3 * c.getQuantity());
                            c.setWorkplace13(2 * c.getQuantity());
                        }
                        case 11, 12, 14, 15 -> {
                            c.setWorkplace7(2 * c.getQuantity());
                            c.setWorkplace8(2 * c.getQuantity());
                            c.setWorkplace9(3 * c.getQuantity());
                            c.setWorkplace12(3 * c.getQuantity());
                            c.setWorkplace13(2 * c.getQuantity());
                        }
                        case  16 -> {
                            c.setWorkplace6(2 * c.getQuantity());
                            c.setWorkplace14(3 * c.getQuantity());
                        }
                        case 17 -> {
                            c.setWorkplace14(3 * c.getQuantity());
                        }
                        case 18, 19, 20 -> {
                            c.setWorkplace6(3 * c.getQuantity());
                            c.setWorkplace7(2 * c.getQuantity());
                            c.setWorkplace8(3 * c.getQuantity());
                            c.setWorkplace9(2 * c.getQuantity());
                        }
                        case 26 -> {
                            c.setWorkplace7(2 * c.getQuantity());
                            c.setWorkplace15(3 * c.getQuantity());
                        }
                        case 49, 54, 29 -> c.setWorkplace1(6 * c.getQuantity());
                        case 50, 55, 30 -> c.setWorkplace2(5 * c.getQuantity());
                        case 51 -> c.setWorkplace3(5 * c.getQuantity());
                        case 56, 31 -> c.setWorkplace3(6 * c.getQuantity());
                        case 1 -> c.setWorkplace4(6 * c.getQuantity());
                        case 2, 3 -> c.setWorkplace4(7 * c.getQuantity());
                    }
                }).toList();

        capacityPlanRepository.saveAll(capacityPlanListToUpdate);
    }

    @Transactional
    void updateCapacityPlanSumUpTable() {
        var capacityPlanSumUpList = capacityPlanSumUpRepository.findAll();
        var capacityPlanList = capacityPlanRepository.findAll();

        var capacityPlanSumUpListToUpdate = capacityPlanSumUpList.stream()
                .map(c -> {
                    switch (c.getWorkPlaceNumber()) {
                        case 1 -> {
                            var sumCapRequirement = capacityPlanList.stream().mapToInt(a -> {
                                if (a.getWorkplace1() == null) return 0;
                                return a.getWorkplace1();
                            }).reduce(0, Integer::sum);
                            c.setCapacityRequirement(sumCapRequirement);
                            c.setTotalCapacityRequirements(sumCapRequirement + c.getSetupTime());
                            c.setOvertimeWeek((sumCapRequirement + c.getSetupTime()) - 2400);
                            c.setOvertimeDay(((sumCapRequirement + c.getSetupTime()) - 2400) / 5);
                        }
                        case 2 -> {
                            var sumCapRequirement = capacityPlanList.stream().mapToInt(a -> {
                                if (a.getWorkplace2() == null) return 0;
                                return a.getWorkplace2();
                            }).reduce(0, Integer::sum);
                            c.setCapacityRequirement(sumCapRequirement);
                            c.setTotalCapacityRequirements(sumCapRequirement + c.getSetupTime());
                            c.setOvertimeWeek((sumCapRequirement + c.getSetupTime()) - 2400);
                            c.setOvertimeDay(((sumCapRequirement + c.getSetupTime()) - 2400) / 5);
                        }
                        case 3 -> {
                            var sumCapRequirement = capacityPlanList.stream().mapToInt(a -> {
                                if (a.getWorkplace3() == null) return 0;
                                return a.getWorkplace3();
                            }).reduce(0, Integer::sum);
                            c.setCapacityRequirement(sumCapRequirement);
                            c.setTotalCapacityRequirements(sumCapRequirement + c.getSetupTime());
                            c.setOvertimeWeek((sumCapRequirement + c.getSetupTime()) - 2400);
                            c.setOvertimeDay(((sumCapRequirement + c.getSetupTime()) - 2400) / 5);
                        }
                        case 4 -> {
                            var sumCapRequirement = capacityPlanList.stream().mapToInt(a -> {
                                if (a.getWorkplace4() == null) return 0;
                                return a.getWorkplace4();
                            }).reduce(0, Integer::sum);
                            c.setCapacityRequirement(sumCapRequirement);
                            c.setTotalCapacityRequirements(sumCapRequirement + c.getSetupTime());
                            c.setOvertimeWeek((sumCapRequirement + c.getSetupTime()) - 2400);
                            c.setOvertimeDay(((sumCapRequirement + c.getSetupTime()) - 2400) / 5);
                        }
                        case 6 -> {
                            var sumCapRequirement = capacityPlanList.stream().mapToInt(a -> {
                                if (a.getWorkplace6() == null) return 0;
                                return a.getWorkplace6();
                            }).reduce(0, Integer::sum);
                            c.setCapacityRequirement(sumCapRequirement);
                            c.setTotalCapacityRequirements(sumCapRequirement + c.getSetupTime());
                            c.setOvertimeWeek((sumCapRequirement + c.getSetupTime()) - 2400);
                            c.setOvertimeDay(((sumCapRequirement + c.getSetupTime()) - 2400) / 5);
                        }
                        case 7 -> {
                            var sumCapRequirement = capacityPlanList.stream().mapToInt(a -> {
                                if (a.getWorkplace7() == null) return 0;
                                return a.getWorkplace7();
                            }).reduce(0, Integer::sum);
                            c.setCapacityRequirement(sumCapRequirement);
                            c.setTotalCapacityRequirements(sumCapRequirement + c.getSetupTime());
                            c.setOvertimeWeek((sumCapRequirement + c.getSetupTime()) - 2400);
                            c.setOvertimeDay(((sumCapRequirement + c.getSetupTime()) - 2400) / 5);
                        }
                        case 8 -> {
                            var sumCapRequirement = capacityPlanList.stream().mapToInt(a -> {
                                if (a.getWorkplace8() == null) return 0;
                                return a.getWorkplace8();
                            }).reduce(0, Integer::sum);
                            c.setCapacityRequirement(sumCapRequirement);
                            c.setTotalCapacityRequirements(sumCapRequirement + c.getSetupTime());
                            c.setOvertimeWeek((sumCapRequirement + c.getSetupTime()) - 2400);
                            c.setOvertimeDay(((sumCapRequirement + c.getSetupTime()) - 2400) / 5);
                        }
                        case 9 -> {
                            var sumCapRequirement = capacityPlanList.stream().mapToInt(a -> {
                                if (a.getWorkplace9() == null) return 0;
                                return a.getWorkplace9();
                            }).reduce(0, Integer::sum);
                            c.setCapacityRequirement(sumCapRequirement);
                            c.setTotalCapacityRequirements(sumCapRequirement + c.getSetupTime());
                            c.setOvertimeWeek((sumCapRequirement + c.getSetupTime()) - 2400);
                            c.setOvertimeDay(((sumCapRequirement + c.getSetupTime()) - 2400) / 5);
                        }
                        case 10 -> {
                            var sumCapRequirement = capacityPlanList.stream().mapToInt(a -> {
                                if (a.getWorkplace10() == null) return 0;
                                return a.getWorkplace10();
                            }).reduce(0, Integer::sum);
                            c.setCapacityRequirement(sumCapRequirement);
                            c.setTotalCapacityRequirements(sumCapRequirement + c.getSetupTime());
                            c.setOvertimeWeek((sumCapRequirement + c.getSetupTime()) - 2400);
                            c.setOvertimeDay(((sumCapRequirement + c.getSetupTime()) - 2400) / 5);
                        }
                        case 11 -> {
                            var sumCapRequirement = capacityPlanList.stream().mapToInt(a -> {
                                if (a.getWorkplace11() == null) return 0;
                                return a.getWorkplace11();
                            }).reduce(0, Integer::sum);
                            c.setCapacityRequirement(sumCapRequirement);
                            c.setTotalCapacityRequirements(sumCapRequirement + c.getSetupTime());
                            c.setOvertimeWeek((sumCapRequirement + c.getSetupTime()) - 2400);
                            c.setOvertimeDay(((sumCapRequirement + c.getSetupTime()) - 2400) / 5);
                        }
                        case 12 -> {
                            var sumCapRequirement = capacityPlanList.stream().mapToInt(a -> {
                                if (a.getWorkplace12() == null) return 0;
                                return a.getWorkplace12();
                            }).reduce(0, Integer::sum);
                            c.setCapacityRequirement(sumCapRequirement);
                            c.setTotalCapacityRequirements(sumCapRequirement + c.getSetupTime());
                            c.setOvertimeWeek((sumCapRequirement + c.getSetupTime()) - 2400);
                            c.setOvertimeDay(((sumCapRequirement + c.getSetupTime()) - 2400) / 5);
                        }
                        case 13 -> {
                            var sumCapRequirement = capacityPlanList.stream().mapToInt(a -> {
                                if (a.getWorkplace13() == null) return 0;
                                return a.getWorkplace13();
                            }).reduce(0, Integer::sum);
                            c.setCapacityRequirement(sumCapRequirement);
                            c.setTotalCapacityRequirements(sumCapRequirement + c.getSetupTime());
                            c.setOvertimeWeek((sumCapRequirement + c.getSetupTime()) - 2400);
                            c.setOvertimeDay(((sumCapRequirement + c.getSetupTime()) - 2400) / 5);
                        }
                        case 14 -> {
                            var sumCapRequirement = capacityPlanList.stream().mapToInt(a -> {
                                if (a.getWorkplace14() == null) return 0;
                                return a.getWorkplace14();
                            }).reduce(0, Integer::sum);
                            c.setCapacityRequirement(sumCapRequirement);
                            c.setTotalCapacityRequirements(sumCapRequirement + c.getSetupTime());
                            c.setOvertimeWeek((sumCapRequirement + c.getSetupTime()) - 2400);
                            c.setOvertimeDay(((sumCapRequirement + c.getSetupTime()) - 2400) / 5);
                        }
                        case 15 -> {
                            var sumCapRequirement = capacityPlanList.stream().mapToInt(a -> {
                                if (a.getWorkplace15() == null) return 0;
                                return a.getWorkplace15();
                            }).reduce(0, Integer::sum);
                            c.setCapacityRequirement(sumCapRequirement);
                            c.setTotalCapacityRequirements(sumCapRequirement + c.getSetupTime());
                            c.setOvertimeWeek((sumCapRequirement + c.getSetupTime()) - 2400);
                            c.setOvertimeDay(((sumCapRequirement + c.getSetupTime()) - 2400) / 5);
                        }
                    }
                    return c;
                })
                .toList();

        capacityPlanSumUpRepository.saveAll(capacityPlanSumUpListToUpdate);
    }

    void updateMaterialPlan() {
        var materialPlanList = materialPlanRepository.findAll();
        var saleAndProductionProgramP1 = saleAndProductionProgramRepository.findByArticle(StuecklistenGroup.P1);
        var saleAndProductionProgramP2 = saleAndProductionProgramRepository.findByArticle(StuecklistenGroup.P2);
        var saleAndProductionProgramP3 = saleAndProductionProgramRepository.findByArticle(StuecklistenGroup.P3);

        var materialPlanSumUpListToUpdate = materialPlanList.stream()
                .map(c -> {
                    c.setInitialStockInPeriodN(c.getDiscountQuantity());
                    c.setGrossRequirementBasedOnProductionProgramN(
                            (saleAndProductionProgramP1.isEmpty() ? 0 : saleAndProductionProgramP1.get().getPN()) * c.getUsedInP1() +
                                    (saleAndProductionProgramP2.isEmpty() ? 0 : saleAndProductionProgramP2.get().getPN()) * c.getUsedInP2() +
                                    (saleAndProductionProgramP3.isEmpty() ? 0 : saleAndProductionProgramP3.get().getPN()) * c.getUsedInP3()
                    );
                    c.setGrossRequirementBasedOnProductionProgramNPlus1(
                            (saleAndProductionProgramP1.isEmpty() ? 0 : saleAndProductionProgramP1.get().getPNPlusOne()) * c.getUsedInP1() +
                                    (saleAndProductionProgramP2.isEmpty() ? 0 : saleAndProductionProgramP2.get().getPNPlusOne()) * c.getUsedInP2() +
                                    (saleAndProductionProgramP3.isEmpty() ? 0 : saleAndProductionProgramP3.get().getPNPlusOne()) * c.getUsedInP3()
                    );
                    c.setGrossRequirementBasedOnProductionProgramNPlus2(
                            (saleAndProductionProgramP1.isEmpty() ? 0 : saleAndProductionProgramP1.get().getPNPlusTwo()) * c.getUsedInP1() +
                                    (saleAndProductionProgramP2.isEmpty() ? 0 : saleAndProductionProgramP2.get().getPNPlusTwo()) * c.getUsedInP2() +
                                    (saleAndProductionProgramP3.isEmpty() ? 0 : saleAndProductionProgramP3.get().getPNPlusTwo()) * c.getUsedInP3()
                    );
                    c.setGrossRequirementBasedOnProductionProgramNPlus3(
                            (saleAndProductionProgramP1.isEmpty() ? 0 : saleAndProductionProgramP1.get().getPNPlusThree()) * c.getUsedInP1() +
                                    (saleAndProductionProgramP2.isEmpty() ? 0 : saleAndProductionProgramP2.get().getPNPlusThree()) * c.getUsedInP2() +
                                    (saleAndProductionProgramP3.isEmpty() ? 0 : saleAndProductionProgramP3.get().getPNPlusThree()) * c.getUsedInP3()
                    );
                    c.setQuantity(0);
                    c.setOrderType("N");
                    return c;
                })
                .toList();

        materialPlanRepository.saveAll(materialPlanSumUpListToUpdate);
    }
}
