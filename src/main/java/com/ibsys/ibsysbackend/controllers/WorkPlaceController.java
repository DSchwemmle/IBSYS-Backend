package com.ibsys.ibsysbackend.controllers;

import com.ibsys.ibsysbackend.dto.WorkPlaceDto;
import com.ibsys.ibsysbackend.entities.WorkPlace;
import com.ibsys.ibsysbackend.services.WorkPlaceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(ApiRoutes.WORK_PLACES)
public class WorkPlaceController {

    private final WorkPlaceService workPlaceService;

    @GetMapping
    public List<WorkPlace> findWorkPlaces(
            @RequestParam(name = "period") final Integer period,
            @RequestParam(name = "isIdleTimeCosts", required = false) final Boolean isIdleTimeCosts,
            @RequestParam(name = "isOrdersInWork", required = false) final Boolean isOrdersInWork
    ) {
        if (isIdleTimeCosts != null) {
            return workPlaceService.findWorkPlacesByPeriodAndIsIdleTimeCosts(period, isIdleTimeCosts);
        }
        if (isOrdersInWork != null) {
            return workPlaceService.findWorkPlacesByPeriodAndIsOrdersInWork(period, isOrdersInWork);
        }
        return workPlaceService.findWorkPlacesByPeriod(period);
    }

    @PostMapping
    public void createWorkPlace(@RequestBody final WorkPlaceDto workPlaceDto) {
        workPlaceService.createWorkPlace(workPlaceDto);
    }

    @PostMapping("/many")
    public void createMultipleWorkPlaces(@RequestBody final List<WorkPlaceDto> workPlaceDtoList) {
        workPlaceService.createMultipleWorkPlaces(workPlaceDtoList);
    }
}
