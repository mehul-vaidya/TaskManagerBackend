package com.example.demo.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path="api/v1/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService =  taskService;
    }

    @GetMapping
    public List<Task> getTask(){
        return taskService.getTasks();
    }

    @PostMapping
    public Task registerNewTask(@RequestBody Task task){return taskService.addNewTask(task);
    }

    @DeleteMapping(path="{taskid}")
    public void deleteTask(@PathVariable("taskid") Long taskid){
        taskService.deleteTask(taskid);
    }

    @PutMapping(path="{taskid}")
    public void updateTask(@PathVariable("taskid") Long taskid,@RequestBody Task task){
        taskService.updateTask(taskid, task);
    }

}
