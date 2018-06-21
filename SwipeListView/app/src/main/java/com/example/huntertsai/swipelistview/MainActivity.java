package com.example.huntertsai.swipelistview;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuAdapter;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SwipeMenuListView mlistView;
    private ArrayAdapter<TodoModel> mArrayAdapter;
    private ArrayList<TodoModel> mtodos;
    private DBHandler mDbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listAdapter();

    }

    private void listAdapter() {
        mDbHandler = DBHandler.getInstance(this);
        mtodos = (ArrayList<TodoModel>) mDbHandler.getAllToDos();
        mlistView = findViewById(R.id.listView);
        mArrayAdapter = new ArrayAdapter<TodoModel>(this, android.R.layout.simple_list_item_1, mtodos);
        mlistView.setAdapter(mArrayAdapter);
        createSwipeView(mlistView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToDo();
            }
        });
    }

    private void createSwipeView(final SwipeMenuListView listView) {
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "edit" item
                SwipeMenuItem EditItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                EditItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                EditItem.setWidth(170);
                // set a icon
                EditItem.setIcon(R.drawable.ic_edit);
                // add to menu
                menu.addMenuItem(EditItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        final EditText userInput = new EditText(MainActivity.this);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.MATCH_PARENT
                        );
                        userInput.setLayoutParams(layoutParams);
                        userInput.setHint("Please enter");

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Edit your todo");

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                TodoModel todo = mtodos.get(position);
                                todo.toDo = userInput.getText().toString();

                                if (todo.equals("")) {
                                    Toast("Field cant be empty");
                                } else {
                                    Snackbar.make(findViewById(R.id.fab), todo + " is updated", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                    Log.d("MainActivity", " edit id :" + todo.getId() + " | " + todo);

                                    mArrayAdapter.notifyDataSetChanged();
                                    mDbHandler.updateTodo(todo);
                                }
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.setView(userInput);
                        dialog.show();
                        break;
                    case 1:
                        TodoModel todo = (TodoModel) listView.getItemAtPosition(position);
                        mDbHandler.deleteTodo(todo);
                        mArrayAdapter.remove(mArrayAdapter.getItem(position));
                        mArrayAdapter.notifyDataSetChanged();

                        Snackbar.make(findViewById(R.id.fab),  "Todo is deleted", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
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

                    Snackbar.make(findViewById(R.id.fab), todo + " is added", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d("MainActivity", " add id :" + todo.getId() + " | " + todo);
                    mtodos.add(todo);
                    mArrayAdapter.notifyDataSetChanged();
                    mDbHandler.addTodo(todo);
                }
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast("canceled");
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
