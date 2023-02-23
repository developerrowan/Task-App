package com.example.taskapp.fileio;


import android.content.Context;

import com.example.taskapp.Taskable;
import com.example.taskapp.models.Task;

import java.util.ArrayList;

public class CSVTaskDataAccess implements Taskable {

    public static final String TAG = "CSVTaskDataAccess";
    private static final String DATA_FILE = "tasks.csv";

    ArrayList<Task> allTasks;
    Context context;

    public CSVTaskDataAccess(Context c){
        this.context = c;
        this.allTasks = new ArrayList<Task>();
        // TODO: load all the tasks from the csv file
    }

    private void loadTasks(){
        // Load the data from the .csv file and use it populate allTasks
    }

    private void saveTasks(){
        // Save all the data in allTasks to the .csv file
    }


    @Override
    public ArrayList<Task> getAllTasks() {
        return null;
    }

    @Override
    public Task getTaskById(long id) {
        return null;
    }

    @Override
    public Task insertTask(Task t) {
        return null;
    }

    @Override
    public Task updateTask(Task t) {
        return null;
    }

    @Override
    public int deleteTask(Task t) {
        return 0;
    }
}