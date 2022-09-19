package com.example.demo.Task;


import com.example.demo.Task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository TaskRepository;

    @Autowired
    public TaskService(TaskRepository TaskRepository) {
        this.TaskRepository = TaskRepository;
    }

    public List<Task> getTasks(){
        return TaskRepository.findAll();
    }

    public Task addNewTask(Task task) {
        return TaskRepository.save(task);
    }

    public void deleteTask(Long taskid) {
        boolean exists = TaskRepository.existsById(taskid);
        if(!exists){
            throw new IllegalStateException("task with id " +taskid +" does not exists");
        }
        TaskRepository.deleteById(taskid);
    }


    @Transactional
    public void updateTask(Long taskid, Task task_param) {
        Task task = TaskRepository.findById(taskid).orElseThrow(()->new IllegalStateException("task with id " +taskid +" does not exists"));
            task.setText(task_param.getText());
            task.setDay(task_param.getDay());
            task.setDay(task_param.getDay());
    }
}



