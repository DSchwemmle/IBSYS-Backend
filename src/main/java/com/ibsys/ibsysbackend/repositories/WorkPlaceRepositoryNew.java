package com.ibsys.ibsysbackend.repositories;

import com.ibsys.ibsysbackend.entities.WorkPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkPlaceRepositoryNew extends JpaRepository<WorkPlace, Integer> {
}
