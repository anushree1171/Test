package com.WeatherApp.exceptions;

public class LatAndLongInvalidException extends RuntimeException{

    public LatAndLongInvalidException(String message){
        super(message);
    }
}
