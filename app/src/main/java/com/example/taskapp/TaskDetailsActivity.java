package com.example.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskapp.models.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskDetailsActivity extends AppCompatActivity {

    public static final String TAG = "TaskDetailsActivity";
    public static final String EXTRA_TASK_ID = "taskId";

    TaskDataAccess da;
    Task task;

    EditText txtDescription;
    EditText txtDueDate;
    CheckBox chkDone;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        txtDescription = findViewById(R.id.txtDescription);
        txtDueDate = findViewById(R.id.txtDueDate);
        chkDone = findViewById(R.id.chkDone);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!save()) {
                    Toast.makeText(TaskDetailsActivity.this,
                            "Unable to save task. Please try again.", Toast.LENGTH_LONG).show();
                    return;
                };

                Intent i = new Intent(TaskDetailsActivity.this, TaskListActivity.class);
                startActivity(i);
            }
        });

        da = new TaskDataAccess(this);
        Intent i = getIntent();
        long id = i.getLongExtra(EXTRA_TASK_ID, 0);

        if (id > 0) {
            task = da.getTaskById(id);
            Log.d(TAG, task.toString());
            putDataIntoUI();
        }
    }

    private void putDataIntoUI() {
        if (task != null) {
            txtDescription.setText(task.getDescription());
            chkDone.setChecked(task.isDone());

            String dateString = new SimpleDateFormat("M/d/yyyy").format(task.getDueDate());
            txtDueDate.setText(dateString);
        }
    }

    private boolean validate() {
        boolean isValid = true;

        if (txtDescription.getText().toString().isEmpty()) {
            isValid = false;
            txtDescription.setError("Description cannot be empty");
        }

        if (txtDueDate.getText().toString().isEmpty()) {
            isValid = false;
            txtDueDate.setError("Due date cannot be empty");
        } else {
            try {
                new SimpleDateFormat("M/d/yyyy").parse(txtDueDate.getText().toString());
            } catch (ParseException e) {
                isValid = false;
                txtDueDate.setError("Please enter a valid date");
            }
        }

        return isValid;
    }

    private boolean save() {
        if (!validate()) return false;

        getDataFromUI();

        if (task.getId() > 0) {
            da.updateTask(task);
        } else {
            da.insertTask(task);
        }

        return true;
    }

    private void getDataFromUI() {
        String description = txtDescription.getText().toString();
        Date dueDate = null;
        boolean done = chkDone.isChecked();

        try {
            dueDate = new SimpleDateFormat("M/d/yyyy").parse(txtDueDate.getText().toString());
        } catch (ParseException e) {}

        Task t = new Task(description, dueDate, done);

        if (task != null) {
            t.setId(task.getId());
        }

        task = t;
    }
}