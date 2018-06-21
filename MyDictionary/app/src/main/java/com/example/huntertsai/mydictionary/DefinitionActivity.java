package com.example.huntertsai.mydictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DefinitionActivity extends AppCompatActivity {

    private TextView wordView;
    private TextView defView;
    private TextView lexView;
    private TextView etymoView;
    private static final int REQ_CODE_BOOKMARK = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);
        wordView = findViewById(R.id.word);
        lexView = findViewById(R.id.lex);
        etymoView = findViewById(R.id.etymo);
        defView = findViewById(R.id.def);


        Intent intent_menu = getIntent();
        wordView.setText(intent_menu.getStringExtra("word"));
        lexView.setText(intent_menu.getStringExtra("lex"));
        etymoView.setText(intent_menu.getStringExtra("etymo"));
        defView.setText(intent_menu.getStringExtra("def"));

    }

    public void buttonClicked(View view) {
        Intent intent = new Intent(this, BookMarkActivity.class);
        startActivityForResult(intent, REQ_CODE_BOOKMARK);
    }
}
