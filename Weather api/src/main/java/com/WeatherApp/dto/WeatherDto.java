package com.WeatherApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {

    private String cod;
    private int message;
    private int cnt;
    private List<WeatherData> list;
    private City city;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WeatherData {
        private long dt;
        private MainData main;
        private List<Weather> weather;
        private Clouds clouds;
        private Wind wind;
        private int visibility;
        private double pop;
        private Sys sys;
        @JsonProperty("dt_txt")
        private String dtTxt;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MainData {
        private double temp;
        @JsonProperty("feels_like")
        private double feelsLike;
        @JsonProperty("temp_min")
        private double tempMin;
        @JsonProperty("temp_max")
        private double tempMax;
        private int pressure;
        @JsonProperty("sea_level")
        private int seaLevel;
        @JsonProperty("grnd_level")
        private int groundLevel;
        private int humidity;
        @JsonProperty("temp_kf")
        private double tempKf;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Clouds {
        private int all;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Wind {
        private double speed;
        private int deg;
        private double gust;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sys {
        private String pod;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class City{
        long id;
        String name;
        Coord coord;
        String country;
        int population;
        long timezone;
        long sunrise;
        long sunset;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Coord{
        double lat;
        double lon;
    }
}
