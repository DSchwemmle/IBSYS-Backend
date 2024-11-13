package com.ibsys.ibsysbackend.repositories;

import com.ibsys.ibsysbackend.entities.SaleAndProductionProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SaleAndProductionProgramRepository extends JpaRepository<SaleAndProductionProgram, UUID> {

    Optional<SaleAndProductionProgram> findByArticle(final String article);
}
