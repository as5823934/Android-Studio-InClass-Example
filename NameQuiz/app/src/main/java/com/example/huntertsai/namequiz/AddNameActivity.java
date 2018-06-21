package com.example.huntertsai.namequiz;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class AddNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name);
    }

    public void addNewNme(View view) {
        String firstN = ((EditText)findViewById(R.id.newFirstName)).getText().toString();
        String lastN = ((EditText)findViewById(R.id.newLastName)).getText().toString();

        //create new file
        //write firstN + "\t" +lastN into file
        String put = firstN + "\t" + lastN;
        try{
            PrintStream output = new PrintStream(openFileOutput("new_name.txt", MODE_APPEND | MODE_PRIVATE));
            output.println(put);
            output.close();

        } catch (IOException fnf){
            Log.d("work?", "Exception?");
        }

        Intent data = new Intent();
        data.putExtra("first", firstN);
        setResult(RESULT_OK, data);
        finish();
    }
}
