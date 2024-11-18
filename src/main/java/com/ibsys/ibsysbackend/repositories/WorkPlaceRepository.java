package com.ibsys.ibsysbackend.repositories;

import com.ibsys.ibsysbackend.entities.WorkPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WorkPlaceRepository extends JpaRepository<WorkPlace, Integer> {
    List<WorkPlace> findWorkPlacesByPeriod(Integer period);
}
