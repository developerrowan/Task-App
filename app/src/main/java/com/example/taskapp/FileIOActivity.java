package com.example.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.taskapp.fileio.FileHelper;
import com.example.taskapp.models.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileIOActivity extends AppCompatActivity {

    public static final String TAG = "FileIOActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_ioactivity);

        boolean result = FileHelper.writeToFile("test.txt", "Hello, world!", this);
        if (result) {
            Log.d(TAG, "First test passed");
        } else {
            Log.d(TAG, "Failed: writeToFile() did not succeed");
        }

        String data = FileHelper.readFromFile("test.txt", this);
        if (data != null) {
            Log.d(TAG, "Second test passed");
        } else {
            Log.d(TAG, "Failed: readToFile() did not succeed");
        }

        Task t = new Task(1, "Mow the lawn", new Date(), false);
        String csv = convertTaskToCSV(t);
        Log.d(TAG, "Task CSV: " + csv);

        Task newTask = convertCSVToTask(csv);
        Log.d(TAG, "Task object: " + newTask.toString());
    }

    private String convertTaskToCSV(Task task) {
        return String.format(
                "%d,%s,%s,%b",
                task.getId(),
                task.getDescription(),
                new SimpleDateFormat("M/d/yyyy").format(task.getDueDate()),
                task.isDone());
    }

    private Task convertCSVToTask(String csv) {
        String[] entries = csv.split(",");
        Date dueDate;

        try {
            dueDate = new SimpleDateFormat("M/d/yyyy").parse(entries[2]);
        } catch (ParseException e) {
            return null;
        }

        return new Task(
                Integer.parseInt(entries[0]),
                entries[1],
                dueDate,
                Boolean.parseBoolean(entries[3])
        );
    }
}