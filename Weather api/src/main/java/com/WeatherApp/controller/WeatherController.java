package com.WeatherApp.controller;

import com.WeatherApp.entity.WeatherData;
import com.WeatherApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @PostMapping("/saveWeather")
    void save(@RequestBody WeatherData weatherData){
        weatherService.save(weatherData);
    }

    @PostMapping("/saveAllWeather")
    void saveAll(@RequestBody List<WeatherData> weatherDataList){
        weatherService.saveAll(weatherDataList);
    }

    @GetMapping("/getAllWeather")
    List<WeatherData> getAll(){
        return weatherService.getAll();
    }
}
