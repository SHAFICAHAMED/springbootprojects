package com.example.TodoList.Service;


import com.example.TodoList.Model.Task;
import com.example.TodoList.Repositary.TaskRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepositary taskrepo;

    public List<Task> getAllTasks(){
        return taskrepo.findAll();
    }

    public List<Task> getAllTaskByCompleted(boolean completed){
        return taskrepo.findByCompleted(completed);
    }

    public void deleteById(Long id){
        taskrepo.deleteById(id);
    }

    public void saveTask(Task task){
        taskrepo.save(task);
    }

    public void updateTaskById(Long id,boolean completed){
        Task task=taskrepo.findById(id).orElse(null);
        if(task!=null){
            task.setCompleted(completed);
            taskrepo.save(task);
        }
    }







}
