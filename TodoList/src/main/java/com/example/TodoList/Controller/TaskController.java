package com.example.TodoList.Controller;

import com.example.TodoList.Model.Task;
import com.example.TodoList.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String getTasks(@RequestParam(required = false) Boolean completed, Model model){
        if(completed==null){
            model.addAttribute("tasks",taskService.getAllTasks());

        }
        else{
            model.addAttribute("tasks",taskService.getAllTaskByCompleted(completed));
        }

        return "index";

    }

    @PostMapping("/add")
    public String SaveTasks(@ModelAttribute Task task){
        taskService.saveTask(task);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTasks(@PathVariable Long id){
        taskService.deleteById(id);
        return "redirect:/";

    }

    @GetMapping("/update/{id}")
    public String updateTasks(@PathVariable Long id,@RequestParam Boolean completed){
        taskService.updateTaskById(id,completed);
        return "redirect:/";

    }
}
