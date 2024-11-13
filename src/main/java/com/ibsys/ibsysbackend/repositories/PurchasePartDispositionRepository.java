package com.ibsys.ibsysbackend.repositories;

import com.ibsys.ibsysbackend.entities.PurchasePartDisposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasePartDispositionRepository extends JpaRepository<PurchasePartDisposition, Long> {
}

