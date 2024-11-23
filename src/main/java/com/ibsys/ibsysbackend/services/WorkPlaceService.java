package com.ibsys.ibsysbackend.services;

import com.ibsys.ibsysbackend.dto.WorkPlaceDto;
import com.ibsys.ibsysbackend.entities.WorkPlace;
import com.ibsys.ibsysbackend.repositories.WorkPlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkPlaceService {

    private final WorkPlaceRepository workPlaceRepository;

    public List<WorkPlace> findWorkPlacesByPeriod(final Integer period) {
        return workPlaceRepository.findWorkPlacesByPeriod(period);
    }

    public List<WorkPlace> findWorkPlacesByPeriodAndIsIdleTimeCosts(final Integer period, final Boolean isIdleTimeCosts) {
        return workPlaceRepository.findWorkPlacesByPeriodAndIsIdleTimeCosts(period, isIdleTimeCosts);
    }

    public List<WorkPlace> findWorkPlacesByPeriodAndIsOrdersInWork(final int period, final boolean isOrdersInWork) {
        return workPlaceRepository.findWorkPlacesByPeriodAndIsOrdersInWork(period, isOrdersInWork);
    }

    public void createWorkPlace(final WorkPlaceDto workPlaceDto) {
        var workPlace = workPlaceDto.toWorkPlace();
        workPlaceRepository.save(workPlace);
    }

    public void createMultipleWorkPlaces(final List<WorkPlaceDto> workPlaceDtoList) {
        var workPlaceList = workPlaceDtoList.stream()
                .map(WorkPlaceDto::toWorkPlace)
                .toList();
        workPlaceRepository.saveAll(workPlaceList);
    }
}
