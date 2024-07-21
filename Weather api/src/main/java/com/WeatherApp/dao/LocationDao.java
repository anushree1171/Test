package com.WeatherApp.dao;

import com.WeatherApp.entity.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDao extends MongoRepository<Location, String> {

    boolean existsByName(String name);
}
