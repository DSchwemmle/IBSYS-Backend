package com.ibsys.ibsysbackend.services;

import com.ibsys.ibsysbackend.dto.ArticleDto;
import com.ibsys.ibsysbackend.dto.OrdersInWorkPlaceDto;
import com.ibsys.ibsysbackend.entities.WarehouseStock;
import com.ibsys.ibsysbackend.entities.*;
import com.ibsys.ibsysbackend.repositories.ArticleRepository;
import com.ibsys.ibsysbackend.repositories.WarehouseStockRepository;
import com.ibsys.ibsysbackend.repositories.*;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ImportService {

    private final ForecastRepository forecastRepository;
    private final ArticleRepository articleRepository;
    private final WaitinglistWorkplaceRepository waitinglistWorkplaceRepository;
    private final WorkPlaceRepositoryNew workplaceRepository;
    private final OrdersInWorkWorkplaceRepository ordersInWorkWorkplaceRepository;
    private final PurchasePartDispositionRepository purchasePartDispositionRepository;
    private final WaitingliststockWaitlinglistRepository waitingliststockWaitlinglistRepository;
    private final FutureInwardStockmovementRepository futureInwardStockmovementRepository;
    //private final DispositionEigenfertigungRepository dispositionEigenfertigungRepository;
    private final ProductionOrderRepository productionOrderRepository;
    private final WarehouseStockRepository warehouseStockRepository;
    private final MaterialPlanRepository materialPlanRepository;

    // private final DispositionEigenfertigungService dispositionEigenfertigungService;
    private ProductionOrderService productionOrderService;

    @Transactional
    public void processImport(ImportData importData) {
        List<Article> articles = importData.getWarehousestock().getArticle().stream()

                .map(ArticleDto::toArticle)
                .toList();

        importArticles(articles);

        var forecast = Forecast.builder()
                .p1(importData.getForecastDto().p1())
                .p2(importData.getForecastDto().p2())
                .p3(importData.getForecastDto().p3())
                .period(importData.getPeriod())
                .build();

        importForecast(forecast);

        importWaitingListWorkstation(importData);

        importOrdersInWorkWorkplace(importData);

        importWaitingliststockWaitinglist(importData);

        importFutureInwardStockmovement(importData);

        importProductionOrder();

        importWarehouseStock(importData);

        productionOrderService.updateProductionOrderOfAllArticles();

        updateMaterialPlan();
    }

    @Transactional
    public void importForecast(Forecast forecast) {
        forecast.setId(UUID.randomUUID());
        forecastRepository.save(forecast);
    }

    @Transactional
    public void importWaitingListWorkstation(ImportData importData) {
        importData.getWaitinglistworkstations().forEach(
                workplaceDTO -> {
                    log.debug(workplaceDTO.toString());
                    WorkPlaceIMPORT workplace = workplaceRepository.save(workplaceDTO.toWorkPlace());
                    if(workplaceDTO.getWaitinglist() == null) {
                        return;
                    }
                    List<WaitingListWorkPlace> waitingListWorkplace = workplaceDTO.getWaitinglist().stream()
                            .map(waitingListDTO -> {
                                waitingListDTO.setWorkplace(workplace);
                                return waitingListDTO.toWaitinglistWorkplace();
                            })
                            .toList();
                    waitinglistWorkplaceRepository.saveAll(waitingListWorkplace);
                }
        );
    }

    @Transactional
    public void importWaitingliststockWaitinglist(ImportData importData) {
        List<WaitingListStockWaitingList> waitingLists = new ArrayList<>();
        importData.getWaitingliststock().
                forEach(missingPartDto ->
                        missingPartDto.getWorkplace()
                                .forEach(waitingListStockWorkplaceDto ->
                                        waitingListStockWorkplaceDto.getWaitingList().forEach(
                                                waitingListStockWaitingListDto -> waitingLists.add(waitingListStockWaitingListDto.toWaitingListStockWaitingList())
                                        )));
        waitingliststockWaitlinglistRepository.saveAll(waitingLists);
    }

    @Transactional
    public void importOrdersInWorkWorkplace(ImportData importData) {
        ordersInWorkWorkplaceRepository.saveAll(importData.getOrdersinwork().stream()
                .map(OrdersInWorkPlaceDto::toOrdersInWorkWorkplace)
                .toList());
    }

    @Transactional
    public void importProductionOrder() {
        List<OrdersInWorkWorkplace> allOrdersInWorkWorkplaces = ordersInWorkWorkplaceRepository.findAll();
        List<ProductionOrder> productionOrderList = productionOrderRepository.findAll();
        productionOrderList.forEach(p -> {
            p.setOrdersInWaitingQueue(
                    productionOrderService.sumUpAllAuftraegeOfItemInWarteschlange(p.getArticleNumber())
            );
            OrdersInWorkWorkplace ordersInWorkWorkplace = allOrdersInWorkWorkplaces.stream()
                    .filter(workplace -> workplace.getItem() == p.getArticleNumber())
                    .findFirst()
                    .orElse(null);

            int auftraegeInBearbeitung = 0;

            if (ordersInWorkWorkplace != null) {
                auftraegeInBearbeitung = ordersInWorkWorkplace.getAmount();
            }
            var workInProgress = (int) Math.ceil((double) auftraegeInBearbeitung / 3);
            p.setWorkInProgress(workInProgress);
        });
        productionOrderRepository.saveAll(productionOrderList);
    }

    @Transactional
    public void importWarehouseStock(final ImportData importData) {
        warehouseStockRepository.deleteAll();
        importData.getWarehousestock().getArticle().forEach(a -> {
            WarehouseStock warehouseStock = WarehouseStock.builder()
                    .id(Integer.parseInt(a.id()))
                    .amount(a.amount())
                    .pct(Double.parseDouble(a.pct()))
                    .price(Double.parseDouble(a.price()))
                    .stockValue(Double.parseDouble(a.stockValue()))
                    .articleId(Integer.parseInt(a.id()))
                   .build();
            warehouseStockRepository.save(warehouseStock);
        });
    }

    //@Transactional
    public void updateMaterialPlan() {
        var allMaterialPlans = materialPlanRepository.findAll();
        allMaterialPlans.forEach(m -> {
            var warehouseStock = warehouseStockRepository.findByArticleId(m.getArticleNumber());
            warehouseStock.ifPresent(stock -> {
                m.setInitialStockInPeriodN(stock.getAmount());
                materialPlanRepository.save(m);
            });
        });
    }

    @Transactional
    public void importArticles(List<Article> articles) {
        List<ProductionOrder> productionOrderList = new ArrayList<>();
        articles = articles.stream().map(article -> {
            switch (article.getId()) {
                case 1, 4, 7, 10, 13, 18, 51, 50, 49 -> {
                    article.setUsedFor(StuecklistenGroup.P1);
                    productionOrderList.add(
                            ProductionOrder.builder()
                                    .article(article.getId() == 1 ? "P1" : "E" + article.getId())
                                    .articleNumber(article.getId())
                                    .usedFor(StuecklistenGroup.P1)
                                    .warehousePreviousPeriod((int) Math.floor(article.getAmount()))
                                    .build()
                    );
                }
                case 2, 5, 8, 11, 14, 19, 56, 55, 54 -> {
                    article.setUsedFor(StuecklistenGroup.P2);
                    productionOrderList.add(
                            ProductionOrder.builder()
                                    .article(article.getId() == 2 ? "P2" : "E" + article.getId())
                                    .articleNumber(article.getId())
                                    .usedFor(StuecklistenGroup.P2)
                                    .warehousePreviousPeriod((int) Math.floor(article.getAmount()))
                                    .build()
                    );
                }
                case 3, 20, 6, 9, 12, 15, 31, 30, 29 -> {
                    article.setUsedFor(StuecklistenGroup.P3);
                    productionOrderList.add(
                            ProductionOrder.builder()
                                    .article(article.getId() == 3 ? "P3" : "E" + article.getId())
                                    .articleNumber(article.getId())
                                    .usedFor(StuecklistenGroup.P3)
                                    .warehousePreviousPeriod((int) Math.floor(article.getAmount()))
                                    .build()
                    );
                }
                case 26, 16, 17 -> {
                    article.setUsedFor(StuecklistenGroup.ALLE);
                    var warehousePreviousPeriodForAllGoods = Math.floor(article.getAmount());
                    var warehousePreviousPeriodShared = (int) Math.ceil(warehousePreviousPeriodForAllGoods / 3);
                    productionOrderList.add(
                            ProductionOrder.builder()
                                    .article(article.getId() == 1 ? "P1" : "E" + article.getId())
                                    .articleNumber(article.getId())
                                    .usedFor(StuecklistenGroup.P1)
                                    .warehousePreviousPeriod(warehousePreviousPeriodShared)
                                    .build()
                    );
                    productionOrderList.add(
                            ProductionOrder.builder()
                                    .article(article.getId() == 2 ? "P2" : "E" + article.getId())
                                    .articleNumber(article.getId())
                                    .usedFor(StuecklistenGroup.P2)
                                    .warehousePreviousPeriod(warehousePreviousPeriodShared)
                                    .build()
                    );
                    productionOrderList.add(
                            ProductionOrder.builder()
                                    .article(article.getId() == 3 ? "P3" : "E" + article.getId())
                                    .articleNumber(article.getId())
                                    .usedFor(StuecklistenGroup.P3)
                                    .warehousePreviousPeriod(warehousePreviousPeriodShared)
                                    .build()
                    );
                }
                default -> article.setUsedFor(null);
            }
            return article;
        }).toList();

        // Feed the stock values for the purchase parts into the purchase-part-disposition table
        List<PurchasePartDisposition> purchasePartDispositions = purchasePartDispositionRepository.findAll();

        for (int i = 0; i < purchasePartDispositions.size(); i++) {
            int itemNumber = purchasePartDispositions.get(i).getItemNumber();

            purchasePartDispositions.get(i).setInitialStock(
                    Integer.parseInt(
                            String.valueOf(articles.get(itemNumber-1).getAmount())
                    )
            );
        }

        purchasePartDispositionRepository.saveAll(purchasePartDispositions);
        articleRepository.saveAll(articles);
        productionOrderRepository.saveAll(productionOrderList);
    }

    public void importWaitinglist(List<WaitingListWorkPlace> waitinglistWorkplace) {
        waitinglistWorkplaceRepository.saveAll(waitinglistWorkplace);
    }

    @Transactional
    public void importFutureInwardStockmovement(ImportData importData) {
        futureInwardStockmovementRepository.deleteAll();

        List<FutureInwardStockmovement> futureInwardStockmovements = importData
                .getFutureinwardstockmovement()
                .stream()
                .toList();

        futureInwardStockmovementRepository.saveAll(futureInwardStockmovements);
    }
}
