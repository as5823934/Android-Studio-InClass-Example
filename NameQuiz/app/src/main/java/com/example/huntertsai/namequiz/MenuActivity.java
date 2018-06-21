package com.example.huntertsai.namequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by huntertsai on 2018-01-17.
 */

public class MenuActivity extends AppCompatActivity {

    private EditText username;
    private static final int REQ_CODE_ADD_NAME = 1111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        username = findViewById(R.id.user);
    }

    public void play(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("secret", username.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE_ADD_NAME){
            //if (requestCode == RESULT_OK){
                String newName = data.getStringExtra("first");
                Toast.makeText(this, newName + " is Added", Toast.LENGTH_SHORT).show();
            //}
        }
    }

    public void Add(View view) {
        Intent intent = new Intent(this, AddNameActivity.class);
        startActivityForResult(intent, REQ_CODE_ADD_NAME);
    }
}
