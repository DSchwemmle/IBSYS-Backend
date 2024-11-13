package com.ibsys.ibsysbackend.controllers;

import com.ibsys.ibsysbackend.dto.BatchDto;
import com.ibsys.ibsysbackend.entities.Batch;
import com.ibsys.ibsysbackend.services.BatchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(ApiRoutes.BATCHES)
public class BatchController {

    private final BatchService batchService;

    @GetMapping
    public List<Batch> findBatchByItemNumber(@RequestParam(name = "itemNumber") final Integer orderId) {
        return batchService.findBatchByItemNumber(orderId);
    }

    @PostMapping
    public void createBatch(@RequestBody final BatchDto batchDto) {
        batchService.createBatch(batchDto);
    }

    @PostMapping("/many")
    public void createMultipleBatches(@RequestBody final List<BatchDto>  batchDtoList) {
        batchService.createMultipleBatches(batchDtoList);
    }
}
