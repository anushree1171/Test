package com.WeatherApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "LocationData")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    String locationId;
    String name;
    Double lat;
    Double lon;
    String country;
    String state;
    LocalDateTime savedOn;
}
