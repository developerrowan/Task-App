package com.example.taskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.taskapp.fileio.FileHelper;

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
    }
}