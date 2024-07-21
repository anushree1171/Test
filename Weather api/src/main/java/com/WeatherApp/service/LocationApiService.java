package com.WeatherApp.service;

import com.WeatherApp.dto.LocationDto;
import com.WeatherApp.exceptions.LocationDataInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Service
@Slf4j
public class LocationApiService {

    private final WebClient webClient;

    private final String apiKey;

    private final Logger logger = LoggerFactory.getLogger(LocationApiService.class);

    @Autowired
    public LocationApiService(){
        this.webClient = WebClient.create();
        this.apiKey = "2c8574ec1fe5bbf0f0645e64eb578597";
    }

    public Mono<LocationDto> getLocationData(String city, String countryCode){
        URI uri = UriComponentsBuilder.fromUriString("http://api.openweathermap.org/geo/1.0/direct")
                .queryParam("q", countryCode.isEmpty() ? city : city + "," + countryCode)
                .queryParam("limit", 1)
                .queryParam("appid", apiKey)
                .build()
                .toUri();

        logger.info("Requesting URL: {}", uri);

        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> Mono.error(new LocationDataInvalidException("Client Error: "+response.statusCode())))
                .onStatus(HttpStatusCode::is5xxServerError,
                        response -> Mono.error(new LocationDataInvalidException("Server Error: "+response.statusCode())))
                .bodyToMono(LocationDto[].class)
                .map(response -> {
                    if(response.length == 0){
                        throw new LocationDataInvalidException("No location data found for this location");
                    }
                    else{
                        return response[0];
                    }
                })
                .onErrorResume(LocationDataInvalidException.class,
                        ex -> { return Mono.error(new LocationDataInvalidException("Error while retrieving data : "+ex.getMessage()));})
                .onErrorResume(Exception.class,
                        ex -> {return Mono.error(new Exception("An unexpected error occurred: "+ex.getMessage()));});

    }


}
