package com.ibsys.ibsysbackend.repositories;

import com.ibsys.ibsysbackend.entities.SalesWish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SalesWishRepository extends JpaRepository<SalesWish, UUID> {
}
