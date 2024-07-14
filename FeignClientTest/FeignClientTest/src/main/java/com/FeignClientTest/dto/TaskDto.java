package com.FeignClientTest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TaskDto {
    String name;

    String description;

    String category;

    String status;
}
