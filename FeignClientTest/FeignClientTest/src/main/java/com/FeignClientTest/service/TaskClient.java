package com.FeignClientTest.service;

import com.FeignClientTest.dto.TaskDto;
import com.FeignClientTest.entity.Task;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="task-service", url="http://localhost:8080")
public interface TaskClient {

    @PostMapping("/saveTask")
    void saveTask(@RequestBody TaskDto taskDto);

    @GetMapping("/getAllTasks")
    List<Task> getTasks();

    @DeleteMapping("/deleteTask/{id}")
    void deleteTask(@PathVariable("id") Integer id);
}
