package com.WeatherApp.service;

import com.WeatherApp.entity.Location;

import java.util.List;

public interface LocationService {

    public void save(Location location);

    public void saveAll(List<Location> locations);

    public List<Location> getAll();
}
