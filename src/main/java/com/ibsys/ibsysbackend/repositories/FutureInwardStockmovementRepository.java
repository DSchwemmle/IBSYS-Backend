package com.ibsys.ibsysbackend.repositories;

import com.ibsys.ibsysbackend.entities.FutureInwardStockmovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FutureInwardStockmovementRepository extends JpaRepository<FutureInwardStockmovement, Long> {

    Optional<FutureInwardStockmovement> findFutureInwardStockmovementByArticle(Integer article);
}