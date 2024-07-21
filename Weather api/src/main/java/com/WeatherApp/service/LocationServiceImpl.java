package com.WeatherApp.service;

import com.WeatherApp.dao.LocationDao;
import com.WeatherApp.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{
    @Autowired
    LocationDao locationDao;
    @Override
    public void save(Location location) {
        if(locationDao.existsByName(location.getName())){
            return;
        }
        else{
            location.setSavedOn(LocalDateTime.now());
            locationDao.save(location);
        }
    }

    @Override
    public void saveAll(List<Location> locations) {
        locations.removeIf(l -> locationDao.existsByName(l.getName()));
        for(Location l : locations){
            l.setSavedOn(LocalDateTime.now());
        }
        locationDao.saveAll(locations);
    }

    @Override
    public List<Location> getAll() {
        return locationDao.findAll();
    }
}
