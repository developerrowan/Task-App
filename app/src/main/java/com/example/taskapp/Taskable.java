package com.example.taskapp;

import com.example.taskapp.models.Task;

import java.util.ArrayList;

public interface Taskable {

    public ArrayList<Task> getAllTasks();

    public Task getTaskById(long id);

    public Task insertTask(Task t);

    public Task updateTask(Task t);

    public int deleteTask(Task t);
}