package com.ibsys.ibsysbackend.services;

import com.ibsys.ibsysbackend.dto.ForecastDto;
import com.ibsys.ibsysbackend.entities.Forecast;
import com.ibsys.ibsysbackend.repositories.ForecastRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ForecastService {

    private final ForecastRepository forecastRepository;

    public void createForecast(final ForecastDto forecastDto) {
        var forcast = forecastDto.toForecast();
        forecastRepository.save(forcast);
    }

    public List<Forecast> findAllForecast() {
        return forecastRepository.findAll();
    }

    public Forecast getForecast() {
        var item = forecastRepository.findAll();

        if (item.isEmpty()) return null;

        return item.get(0);
    }
}
