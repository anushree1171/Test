package com.WeatherApp.service;

import com.WeatherApp.dao.WeatherDao;
import com.WeatherApp.entity.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService{

    @Autowired
    WeatherDao weatherDao;


    @Override
    public void save(WeatherData weatherData) {
        if(weatherDao.existsById(weatherData.getWeatherId())){
            return;
        }
        else{
            weatherDao.save(weatherData);
        }

    }

    @Override
    public void saveAll(List<WeatherData> weatherDataList) {
        weatherDataList.removeIf(weatherData -> weatherDao.existsById(weatherData.getWeatherId()));
        weatherDao.saveAll(weatherDataList);
    }

    @Override
    public List<WeatherData> getAll() {
        return weatherDao.findAll();
    }
}
