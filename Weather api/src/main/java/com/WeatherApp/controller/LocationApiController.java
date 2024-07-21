package com.WeatherApp.controller;

import com.WeatherApp.service.LocationApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/location")
public class LocationApiController {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    LocationApiService locationApiService;

    @GetMapping("/getLocationData")
    public Mono<String> getLocationData(@RequestParam("city") String city, @RequestParam("countryCode") String countryCode){
        return locationApiService.getLocationData(city, countryCode)
        .map(obj -> {
            try {
                return objectMapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error converting object to JSON", e);
            }
        });
    }
}
