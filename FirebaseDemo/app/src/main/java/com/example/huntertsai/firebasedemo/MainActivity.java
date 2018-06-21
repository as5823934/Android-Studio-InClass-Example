package com.example.huntertsai.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private EditText input_name, input_email;
    private ListView listdata;
    private ProgressBar circular_progress;

    private FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mdatabaseReference;
    private List<User> list_users = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Firebase Demo");
        setSupportActionBar(toolbar);

        circular_progress = findViewById(R.id.circular_progress);
        input_name = findViewById(R.id.input_name);
        input_email = findViewById(R.id.input_email);
        listdata = findViewById(R.id.list_data);

        initFirebase();
        addEventFirebaseListener();

    }

    private void addEventFirebaseListener() {
        circular_progress.setVisibility(View.VISIBLE);
        listdata.setVisibility(View.INVISIBLE);

        mdatabaseReference.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (list_users.size() > 0){
                    list_users.clear();
                }
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    User user = postSnapshot.getValue(User.class);
                    list_users.add(user);
                }
                listviewAdapter adapter = new listviewAdapter(MainActivity.class, list_users);
                listdata.setAdapter(adapter);

                circular_progress.setVisibility(View.INVISIBLE);
                listdata.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mdatabaseReference = mfirebaseDatabase.getReference();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
