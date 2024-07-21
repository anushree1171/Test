package com.WeatherApp.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "WeatherData")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherData {

    @Id
    String weatherId;
    private long dt;
    private MainData main;
    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private int visibility;
    private double pop;
    private Sys sys;
    private String dtTxt;
    String city;

    @Data
    public static class MainData {
        private double temp;
        private double feelsLike;
        private double tempMin;
        private double tempMax;
        private int pressure;
        private int seaLevel;
        private int groundLevel;
        private int humidity;
        private double tempKf;
    }

    @Data
    public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    public static class Clouds {
        private int all;
    }

    @Data
    public static class Wind {
        private double speed;
        private int deg;
        private double gust;
    }

    @Data
    public static class Sys {
        private String pod;
    }

}
