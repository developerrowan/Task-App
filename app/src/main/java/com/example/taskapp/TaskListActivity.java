package com.example.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.taskapp.models.Task;

import java.util.ArrayList;

public class TaskListActivity extends AppCompatActivity {

    public static final String TAG = "TaskListActivity";

    private ListView lsTasks;
    private TaskDataAccess da;
    private ArrayList<Task> allTasks;
    private Button btnAddTask;
    private Button btnFileActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        btnAddTask = findViewById(R.id.btnAddTask);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TaskListActivity.this, TaskDetailsActivity.class);
                startActivity(i);
            }
        });

        btnFileActivity = findViewById(R.id.btnFileActivity);
        btnFileActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TaskListActivity.this, FileIOActivity.class);
                startActivity(i);
            }
        });

        lsTasks = findViewById(R.id.lsTasks);
        da = new TaskDataAccess(this);
        allTasks = da.getAllTasks();

        ArrayAdapter<Task> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, allTasks);
        lsTasks.setAdapter(adapter);

        lsTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Task selectedTask = allTasks.get(i);

                Intent intent = new Intent(TaskListActivity.this, TaskDetailsActivity.class);
                intent.putExtra(TaskDetailsActivity.EXTRA_TASK_ID, selectedTask.getId());
                startActivity(intent);
            }
        });
    }
}