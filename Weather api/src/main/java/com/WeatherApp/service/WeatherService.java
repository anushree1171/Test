package com.WeatherApp.service;

import com.WeatherApp.entity.WeatherData;

import java.util.List;

public interface WeatherService {

    public void save(WeatherData weatherData);

    public void saveAll(List<WeatherData> weatherDataList);

    public List<WeatherData> getAll();
}
