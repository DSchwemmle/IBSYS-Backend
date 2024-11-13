package com.ibsys.ibsysbackend.services;

import com.ibsys.ibsysbackend.dto.WarehouseStockDto;
import com.ibsys.ibsysbackend.entities.WarehouseStock;
import com.ibsys.ibsysbackend.repositories.WarehouseStockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WarehouseStockService {

    private WarehouseStockRepository warehouseStockRepository;

    public List<WarehouseStock> findWarehouseStocksByPeriod(final Integer period) {
        return warehouseStockRepository.findWarehouseStocksByPeriod(period);
    }

    public void createWarehouseStock(final WarehouseStockDto warehouseStockDto) {
        var warehouseStock = warehouseStockDto.toWarehouseStock();
        warehouseStockRepository.save(warehouseStock);
    }

    public void createMultipleWarehouseStocks(final List<WarehouseStockDto> warehouseStockDtoList) {
        var warehouseStockList = warehouseStockDtoList.stream()
                .map(WarehouseStockDto::toWarehouseStock)
                .toList();
        warehouseStockRepository.saveAll(warehouseStockList);
    }
}
