package com.ibsys.ibsysbackend.controllers;

import com.ibsys.ibsysbackend.dto.WaitingListDto;
import com.ibsys.ibsysbackend.entities.WaitingList;
import com.ibsys.ibsysbackend.services.WaitingListService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(ApiRoutes.WAITING_LISTS)
public class WaitingListController {

    private final WaitingListService waitingListService;

    @GetMapping
    public List<WaitingList> findWaitingListsByPeriod(@RequestParam(name = "period") final Integer period) {
        return waitingListService.findWaitingListsByPeriod(period);
    }

    @PostMapping
    public void createWaitingList(@RequestBody final WaitingListDto waitingListDto) {
        waitingListService.createWaitingList(waitingListDto);
    }

    @PostMapping("/many")
    public void createMultipleWaitingLists(@RequestBody final List<WaitingListDto> waitingListDtoList) {
        waitingListService.createMultipleWaitingLists(waitingListDtoList);
    }
}
