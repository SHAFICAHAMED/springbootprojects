package com.example.TodoList.Repositary;

import com.example.TodoList.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepositary extends JpaRepository<Task,Long> {
    public List<Task> findByCompleted(boolean completed);
            //it generates query like select * from todolist where completed=?
}
