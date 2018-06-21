package com.example.huntertsai.todolist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="swipe test" ;
    private ListView mlistView;
    private ArrayAdapter<TodoModel> mArrayAdapter;
    private ArrayList<TodoModel> mtodos;
    private DBHandler mDbHandler;
    private TodoModel temp;
    private int temp_i;

    private SwipeLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mlistView = findViewById(R.id.todo_list);
        listAdapter();
    }

    private void listAdapter() {
        mDbHandler = DBHandler.getInstance(this);
        mtodos = (ArrayList<TodoModel>) mDbHandler.getAllToDos();

        mArrayAdapter = new ArrayAdapter<TodoModel>(this, android.R.layout.simple_list_item_1, mtodos);
        mlistView.setAdapter(mArrayAdapter);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                temp = mtodos.get(i);
                temp_i = i;
                deleteToDo();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToDo();
            }
        });
    }


    private void addToDo() {

        final EditText userInput = new EditText(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        userInput.setLayoutParams(layoutParams);
        userInput.setHint("Please enter");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add To Do!");

        builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TodoModel todo = new TodoModel();
                todo.toDo = userInput.getText().toString();

                if (todo.equals("")) {
                    Toast("Field cant be empty");
                } else {
                    mtodos.add(todo);
                    mArrayAdapter.notifyDataSetChanged();
                    Snackbar.make(findViewById(R.id.fab), todo + " is added", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    mDbHandler.addTodo(todo);
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast("works?");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.setView(userInput);
        dialog.show();
    }

    private void deleteToDo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("What do you want to do?");
        builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mDbHandler.deleteTodo(temp);
                mArrayAdapter.remove(mArrayAdapter.getItem(temp_i));
                mArrayAdapter.notifyDataSetChanged();
                Toast(temp + " is deleted");
            }
        });

        builder.setNegativeButton("EDIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                editToDo();
            }
        });

        builder.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void editToDo(){

        final EditText userInput = new EditText(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        userInput.setLayoutParams(layoutParams);
        userInput.setHint("Please enter");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit your todo");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TodoModel todo = new TodoModel();
                todo.toDo = userInput.getText().toString();

                if (todo.equals("")) {
                    Toast("Field cant be empty");
                } else {
                    mArrayAdapter.remove(mArrayAdapter.getItem(temp_i));
                    mtodos.add(todo);
                    mArrayAdapter.notifyDataSetChanged();
                    Snackbar.make(findViewById(R.id.fab), todo + " is updated", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    mDbHandler.updateTodo(todo);
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setView(userInput);
        dialog.show();

    }

    private void Toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
