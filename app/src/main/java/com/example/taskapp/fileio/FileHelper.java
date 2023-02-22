package com.example.taskapp.fileio;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileHelper {

    public static final String TAG = "FileHelper";

    public static boolean writeToFile(String filePath, String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filePath, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();

            return true;
        } catch (IOException e) {
            Log.e(TAG, "File write failed: " + e.toString());
            Log.d(TAG, "filePath: " + filePath + "\ndata: " + data);

            return false;
        }
    }

    public static String readFromFile(String filePath, Context context) {
        String data = null;

        try {
            InputStream inputStream = context.openFileInput(filePath);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                final String LINE_BREAK = System.getProperty("line.separator");

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString + LINE_BREAK);
                }

                inputStream.close();
                data = stringBuilder.toString();
                Log.d(TAG, "Here's the data: \n" + data);
            }
        } catch (FileNotFoundException e) {
            Log.d(TAG, "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e(TAG, "Can not read file: " + e.toString());
        }

        return data;
    }
}
