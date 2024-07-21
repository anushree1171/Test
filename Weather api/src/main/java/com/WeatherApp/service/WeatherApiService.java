package com.WeatherApp.service;

import com.WeatherApp.dto.WeatherDto;
import com.WeatherApp.exceptions.LatAndLongInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import java.net.URI;

@Service
@Slf4j
public class WeatherApiService {

    private final WebClient webClient;

    private final String apiKey;

    private final Logger logger = LoggerFactory.getLogger(WeatherApiService.class);

    public WeatherApiService(){
        this.webClient = WebClient.create();
        this.apiKey = "2c8574ec1fe5bbf0f0645e64eb578597";
    }

    public Mono<WeatherDto> getWeatherData(double latitude, double longitude){
        URI uri = UriComponentsBuilder.fromUriString("https://api.openweathermap.org/data/2.5/forecast")
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("appid", apiKey)
                .build()
                .toUri();

        logger.info("Requesting url: {}", uri);

        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> Mono.error(new LatAndLongInvalidException("Client Error: "+response.statusCode())))
                .onStatus(HttpStatusCode::is5xxServerError,
                        response -> Mono.error(new LatAndLongInvalidException("Server Error: "+response.statusCode())))
                .bodyToMono(WeatherDto.class)
                .map(response -> {
                    System.out.println(response);
                    if(response == null){
                        throw new LatAndLongInvalidException("No weather data found for the given co-ordinates");
                    }
                    else{
                        return response;
                    }
                })
                .onErrorResume(LatAndLongInvalidException.class,
                        ex-> {return Mono.error(new LatAndLongInvalidException("Error while retrieving data:"+ex.getMessage()));})
                .onErrorResume(Exception.class,
                        ex->{return Mono.error(new Exception("An unexpected error occurred: "+ex.getMessage()));});
    }
}
