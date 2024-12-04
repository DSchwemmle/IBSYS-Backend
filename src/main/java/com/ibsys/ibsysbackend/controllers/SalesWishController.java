package com.ibsys.ibsysbackend.controllers;

import com.ibsys.ibsysbackend.entities.SalesWish;
import com.ibsys.ibsysbackend.services.SalesWishService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/vertriebswunsch")
public class SalesWishController {

    private SalesWishService salesWishService;

    @GetMapping
    public SalesWish getSalesWish() {
        return salesWishService.getSalesWish();
    }

    @PutMapping
    public void updateSalesWish(@RequestBody final SalesWish salesWish) {
        salesWishService.updateSalesWish(salesWish);
    }
}
