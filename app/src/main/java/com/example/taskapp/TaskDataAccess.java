package com.example.taskapp;

import android.content.Context;

import com.example.taskapp.models.Task;

import java.util.ArrayList;
import java.util.Date;

public class TaskDataAccess implements Taskable {

    private Context context;
    private static ArrayList<Task> tasks = new ArrayList<Task>(){{
        add(new Task(TaskDataAccess.nextId(), "Wash the dishes", new Date(), false));
        add(new Task(TaskDataAccess.nextId(), "Walk the dog", new Date(), true));
        add(new Task(TaskDataAccess.nextId(), "Do the laundry", new Date(), false));
    }};
    private static long id;

    public TaskDataAccess(Context context) {
        this.context = context;
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(long id) {
        for (Task t : tasks) {
            if(t.getId() == id) {
                return t;
            }
        }

        return null;
    }

    public Task insertTask(Task task) {
        task.setId(nextId());
        tasks.add(task);

        return task;
    }

    public Task updateTask(Task task) {
        Task originalTask = getTaskById(task.getId());

        if(originalTask == null) return null;

        originalTask.setDescription(task.getDescription());
        originalTask.setDueDate(task.getDueDate());
        originalTask.setDone(task.isDone());

        return originalTask;
    }

    public int deleteTask(Task task) {
        if (tasks.contains((task))) {
            tasks.remove((task));
            return 1;
        }

        return 0;
    }

    private static long nextId() {
        return ++TaskDataAccess.id;
    }
}
