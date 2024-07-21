package com.WeatherApp.util;

import com.WeatherApp.dto.WeatherDto;
import com.WeatherApp.entity.WeatherData;

import java.util.stream.Collectors;

public class WeatherDataMapper {

    public static WeatherData map(WeatherDto.WeatherData dtoWeatherData, String city) {
        WeatherData weatherData = new WeatherData();

        weatherData.setDt(dtoWeatherData.getDt());
        weatherData.setMain(map(dtoWeatherData.getMain()));
        weatherData.setWeather(dtoWeatherData.getWeather().stream()
                .map(WeatherDataMapper::map)
                .collect(Collectors.toList()));
        weatherData.setClouds(map(dtoWeatherData.getClouds()));
        weatherData.setWind(map(dtoWeatherData.getWind()));
        weatherData.setVisibility(dtoWeatherData.getVisibility());
        weatherData.setPop(dtoWeatherData.getPop());
        weatherData.setSys(map(dtoWeatherData.getSys()));
        weatherData.setDtTxt(dtoWeatherData.getDtTxt());
        weatherData.setCity(city);

        return weatherData;
    }

    private static WeatherData.MainData map(WeatherDto.MainData dtoMain) {
        WeatherData.MainData mainData = new WeatherData.MainData();
        mainData.setTemp(dtoMain.getTemp());
        mainData.setFeelsLike(dtoMain.getFeelsLike());
        mainData.setTempMin(dtoMain.getTempMin());
        mainData.setTempMax(dtoMain.getTempMax());
        mainData.setPressure(dtoMain.getPressure());
        mainData.setSeaLevel(dtoMain.getSeaLevel());
        mainData.setGroundLevel(dtoMain.getGroundLevel());
        mainData.setHumidity(dtoMain.getHumidity());
        mainData.setTempKf(dtoMain.getTempKf());
        return mainData;
    }

    private static WeatherData.Weather map(WeatherDto.Weather dtoWeather) {
        WeatherData.Weather weather = new WeatherData.Weather();
        weather.setId(dtoWeather.getId());
        weather.setMain(dtoWeather.getMain());
        weather.setDescription(dtoWeather.getDescription());
        weather.setIcon(dtoWeather.getIcon());
        return weather;
    }

    private static WeatherData.Clouds map(WeatherDto.Clouds dtoClouds) {
        WeatherData.Clouds clouds = new WeatherData.Clouds();
        clouds.setAll(dtoClouds.getAll());
        return clouds;
    }

    private static WeatherData.Wind map(WeatherDto.Wind dtoWind) {
        WeatherData.Wind wind = new WeatherData.Wind();
        wind.setSpeed(dtoWind.getSpeed());
        wind.setDeg(dtoWind.getDeg());
        wind.setGust(dtoWind.getGust());
        return wind;
    }

    private static WeatherData.Sys map(WeatherDto.Sys dtoSys) {
        WeatherData.Sys sys = new WeatherData.Sys();
        sys.setPod(dtoSys.getPod());
        return sys;
    }
}
