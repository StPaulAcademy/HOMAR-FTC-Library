package edu.spa.ftclib;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by Gabriel on 2018-01-03.
 * A simple way to log data to a file.
 */

public class Log {
    private static final String BASE_FOLDER_NAME = "FIRST";
    private Writer fileWriter;
    String line = "";
    Log(String filename) {
        String directoryPath = Environment.getExternalStorageDirectory().getPath()+"/"+BASE_FOLDER_NAME;
        File directory = new File(directoryPath);
        //noinspection ResultOfMethodCallIgnored
        directory.mkdir();
        try {
            fileWriter = new FileWriter(directoryPath+"/"+filename+".csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void close() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        try {
            fileWriter.write(line+"\n");
            line = "";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addData(String data) {
        if (!line.equals("")) line += ",";
        line += data;
    }
    public void addData(Object data) {
        addData(data.toString());
    }
    public void addData(boolean data) {
        addData(String.valueOf(data));
    }
    public void addData(byte data) {
        addData(String.valueOf(data));
    }
    public void addData(char data) {
        addData(String.valueOf(data));
    }
    public void addData(short data) {
        addData(String.valueOf(data));
    }
    public void addData(int data) {
        addData(String.valueOf(data));
    }
    public void addData(long data) {
        addData(String.valueOf(data));
    }
    public void addData(float data) {
        addData(String.valueOf(data));
    }
    public void addData(double data) {
        addData(String.valueOf(data));
    }

}
