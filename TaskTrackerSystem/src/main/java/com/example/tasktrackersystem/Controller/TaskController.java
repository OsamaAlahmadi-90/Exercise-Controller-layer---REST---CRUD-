package com.example.tasktrackersystem.Controller;

import com.example.tasktrackersystem.Api.ApiResponse;
import com.example.tasktrackersystem.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
    ArrayList <Task> tasks = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Task> getTasks(){
        return tasks;
    }

    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody Task task){
        tasks.add(task);
        return new ApiResponse("Task has been added");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody Task task){
        tasks.set(index,task);
        return new ApiResponse("Task has been updated");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index){
        tasks.remove(index);
        return new ApiResponse("Task has been deleted");
    }

    @PutMapping("/update/status/{title}/{status}")
    public ApiResponse changeTaskStatus(@PathVariable String title,@PathVariable String status){
        for (Task t : tasks){
            if(t.getTitle().strip().equalsIgnoreCase(title)){
                t.setStatus(status);
            }
        }
        return new ApiResponse("Status has been changed");
    }


    @GetMapping("/get/{title}")
    public Task searchTaskByTitle(@PathVariable String title){
        for (Task t : tasks){
            if(t.getTitle().equalsIgnoreCase(title)) {
                return t;
            }
        }
        return null;
    }
}
