package com.example.Lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Ex4 extends AppCompatActivity implements View.OnClickListener {

    Button btnread,btnwrite;
    EditText editdata;
    private String fileName = "Ex4file.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex4);


        btnread =  findViewById(R.id.btnreaddata);
        btnwrite =  findViewById(R.id.btnwritedata);
        editdata = findViewById(R.id.editdata);
        btnread.setOnClickListener(this);
        btnwrite.setOnClickListener(this);


    }
    public void onClick(View v) {
        if (v.getId()==R.id.btnreaddata)
        {
            readData();
        }
        else if(v.getId() ==R.id.btnwritedata)
        {
            writeData();
        }
    }

    private void readData() {
        try (FileInputStream fis = new FileInputStream(new File(getExternalFilesDir(null), fileName))) {
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            String data = new String(buffer, StandardCharsets.UTF_8);
            editdata.setText(data);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading data from file: " + fileName, Toast.LENGTH_SHORT).show();
        }
    }

    private void writeData() {
        try (FileOutputStream fos = new FileOutputStream(new File(getExternalFilesDir(null), fileName))) {
            fos.write(editdata.getText().toString().getBytes(StandardCharsets.UTF_8));
            Toast.makeText(this, "Data saved to file: " + fileName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving data to file: " + fileName, Toast.LENGTH_SHORT).show();
        }
    }
}