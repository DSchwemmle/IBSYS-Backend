package com.ibsys.ibsysbackend.repositories;

import com.ibsys.ibsysbackend.entities.Forecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, UUID> {}
