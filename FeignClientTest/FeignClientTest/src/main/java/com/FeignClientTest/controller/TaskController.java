package com.FeignClientTest.controller;

import com.FeignClientTest.dto.TaskDto;
import com.FeignClientTest.entity.Task;
import com.FeignClientTest.service.TaskClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class TaskController {

    @Autowired
    private TaskClient taskClient;

    @GetMapping("/getAllTasks-client")
    void getAllTasks(){
        log.info(taskClient.getTasks().toString());
    }

    @PostMapping("/saveTask-client")
    void saveTask(@RequestBody TaskDto taskDto){
        taskClient.saveTask(taskDto);
        log.info("Sent request to save task.");
    }

    @DeleteMapping("/deleteTask-client/{id}")
    void deleteTask(@PathVariable("id") Integer id){
        taskClient.deleteTask(id);
        log.info("Sent request to delete task.");
    }

}
