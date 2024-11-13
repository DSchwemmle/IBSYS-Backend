package com.ibsys.ibsysbackend.controllers;

import com.ibsys.ibsysbackend.controllers.ApiRoutes;
import com.ibsys.ibsysbackend.dto.ForecastDto;
import com.ibsys.ibsysbackend.entities.Forecast;
import com.ibsys.ibsysbackend.services.ForecastService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(ApiRoutes.FORECAST)
public class ForecastController {

    private final ForecastService forecastService;

    @PostMapping
    public void createForecast(@RequestBody final ForecastDto forecastDto) {
        forecastService.createForecast(forecastDto);
    }

    @GetMapping
    public List<Forecast> findAllForecast() {
        return forecastService.findAllForecast();
    }

    @GetMapping("/findone")
    public Forecast getForecast() {
        return forecastService.getForecast();
    }
}
