package com.ibsys.ibsysbackend.controllers;

import com.ibsys.ibsysbackend.entities.SalesWish;
import com.ibsys.ibsysbackend.services.SalesWishService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/vertriebswunsch")
public class VertriebswunschController {

    private  salesWishtService;

    @GetMapping
    public SalesWish getVertriebswunsch() {
        return salesWishService.getVertriebswunsch();
    }

    @PutMapping
    public void updateVertriebswunsch(@RequestBody final SalesWish salesWish) {
        salesWishService.updateVertriebswunsch(salesWish);
    }
}
