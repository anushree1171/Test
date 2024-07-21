package com.WeatherApp.controller;

import com.WeatherApp.service.WeatherApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
public class WeatherApiController {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    WeatherApiService weatherApiService;

    @GetMapping("/getWeatherData")
    public Mono<String> getWeatherData(@RequestParam("lat") Double latitude, @RequestParam("lon") Double longitude){
        return weatherApiService.getWeatherData(latitude, longitude)
                .map(obj -> {
                    try{
                        return objectMapper.writeValueAsString(obj);
                    }
                    catch (JsonProcessingException e){
                        throw new RuntimeException("Error converting object to JSON", e);
                    }
                });
    }
}
