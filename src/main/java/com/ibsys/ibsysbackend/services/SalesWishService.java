package com.ibsys.ibsysbackend.services;

import com.ibsys.ibsysbackend.entities.SalesWish;
import com.ibsys.ibsysbackend.repositories.SalesWishRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SalesWishService {

    private SalesWishRepository salesWishRepository;

    public SalesWish getSalesWish() {
        return salesWishRepository.findAll().getFirst();
    }

    public void updateSalesWish(final SalesWish salesWish) {
        salesWishRepository.save(salesWish);
    }
}