package com.WeatherApp.dao;

import com.WeatherApp.entity.WeatherData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDao extends MongoRepository<WeatherData, String> {
}
