package com.WeatherApp.controller;

import com.WeatherApp.dto.LocationDto;
import com.WeatherApp.dto.WeatherDto;
import com.WeatherApp.entity.Location;
import com.WeatherApp.entity.WeatherData;
import com.WeatherApp.service.LocationApiService;
import com.WeatherApp.service.LocationService;
import com.WeatherApp.service.WeatherApiService;
import com.WeatherApp.service.WeatherService;
import com.WeatherApp.util.WeatherDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Objects;

@RestController
public class MainController {

    @Autowired
    LocationApiService locationApiService;

    @Autowired
    LocationService locationService;

    @Autowired
    WeatherApiService weatherApiService;

    @Autowired
    WeatherService weatherService;

    @GetMapping("/getAllWeatherData")
    public List<WeatherData> getAllWeatherData(@RequestParam("city") String city, @RequestParam("countryCode") String countryCode){
        Mono<LocationDto> locationDto = locationApiService.getLocationData(city, countryCode);

        Location location = new Location();
        location.setName(Objects.requireNonNull(locationDto.block()).getName());
        location.setLat(Objects.requireNonNull(locationDto.block()).getLat());
        location.setLon(location.getLon());
        location.setCountry(location.getCountry());
        location.setState(location.getState());

        locationService.save(location);

        Mono<WeatherDto> weatherDtoMono = weatherApiService.getWeatherData(location.getLat(), location.getLon());

        List<WeatherData> weatherData = Objects.requireNonNull(weatherDtoMono.block()).getList()
                .stream()
                .map(dtoWeatherData -> WeatherDataMapper.map(dtoWeatherData, location.getName()))
                .toList();

        weatherService.saveAll(weatherData);

        return weatherData;
    }
}
