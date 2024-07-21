package com.WeatherApp.exceptions;

public class LocationDataInvalidException extends RuntimeException{
    public LocationDataInvalidException(String message){
        super(message);
    }
}
