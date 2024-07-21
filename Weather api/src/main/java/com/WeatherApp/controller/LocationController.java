package com.WeatherApp.controller;

import com.WeatherApp.entity.Location;
import com.WeatherApp.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationService locationService;

    @PostMapping("/saveLocation")
    void save(@RequestBody Location location){
        locationService.save(location);
    }

    @PostMapping("/saveAllLocations")
    void save(@RequestBody List<Location> locationList){
        locationService.saveAll(locationList);
    }

    @GetMapping("/getAllLocations")
    List<Location> getAll(){
        return locationService.getAll();
    }
}
