package com.ibsys.ibsysbackend.services;

import com.ibsys.ibsysbackend.dto.BatchDto;
import com.ibsys.ibsysbackend.entities.Batch;
import com.ibsys.ibsysbackend.repositories.BatchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BatchService {

    private final BatchRepository batchRepository;

    public List<Batch> findBatchByItemNumber(final Integer itemNumber) {
        return batchRepository.findBatchesByItemNumber(itemNumber);
    }

    public void createBatch(final BatchDto batchDto) {
        var batch = batchDto.toBatch();
        batchRepository.save(batch);
    }

    public void createMultipleBatches(final List<BatchDto> batchDtoList) {
        var batchList = batchDtoList.stream()
                .map(BatchDto::toBatch)
                .toList();
        batchRepository.saveAll(batchList);
    }
}
