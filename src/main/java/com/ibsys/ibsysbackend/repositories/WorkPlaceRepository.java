package com.ibsys.ibsysbackend.repositories;

import com.ibsys.ibsysbackend.entities.WorkPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkPlaceRepository extends JpaRepository<WorkPlace, Integer> {
}