package com.example.huntertsai.swipelistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by huntertsai on 2018-02-02.
 */

public class DBHandler extends SQLiteOpenHelper{
    //database version
    public static final int DATABASE_VERSION = 1;
    //database name
    public static final String DATABASE_NAME = "todo_Database";
    private static final String TAG = "list";

    public static class TodoEntry implements BaseColumns{
        //table name
        public static final String TABLE_TODO_NAME = "toDoList";
        //to_do table columns name
        private static final String COLUMN_TODO = "todo";
        private static final String COLUMN_ID = "uuid";
    }

        private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TodoEntry.TABLE_TODO_NAME + " (" +
        TodoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TodoEntry.COLUMN_ID + "," + TodoEntry.COLUMN_TODO + " TEXT)";

        private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TodoEntry.TABLE_TODO_NAME;


    // Singleton
    private static DBHandler sDBHandler;
    public static synchronized DBHandler getInstance(Context context) {
        if (sDBHandler == null) {
            sDBHandler = new DBHandler(context.getApplicationContext());
        }
        return sDBHandler;
    }

    private DBHandler(Context contex){
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int older, int newer) {
        //drop table if exists
        db.execSQL(SQL_DELETE_ENTRIES);
        //create table again
        onCreate(db);
    }

    public void addTodo(TodoModel todo){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(TodoEntry.COLUMN_ID, String.valueOf(todo.getId()));
            values.put(TodoEntry.COLUMN_TODO, todo.toDo);
            db.insertOrThrow(TodoEntry.TABLE_TODO_NAME, null, values);
            db.setTransactionSuccessful();
        }catch (Exception e){

        }finally {
            db.endTransaction();
        }
    }

    public void deleteTodo(TodoModel todo) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM " + TodoEntry.TABLE_TODO_NAME + " WHERE " + TodoEntry.COLUMN_ID + " = '" + todo.getId() + "'";
        db.execSQL(query);
    }

    public void updateTodo(TodoModel todo){
        SQLiteDatabase db = getWritableDatabase();

        Log.d("DBHandler", "id: " + todo.getId());

        String uuidString = todo.getId().toString();
        ContentValues values = new ContentValues();
        values.put(TodoEntry.COLUMN_ID, uuidString);
        values.put(TodoEntry.COLUMN_TODO, todo.toDo);
        db.update(TodoEntry.TABLE_TODO_NAME, values, TodoEntry.COLUMN_ID + " = ?", new String[]{uuidString});

    }

    public List<TodoModel> getAllToDos(){
        List<TodoModel> todos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TodoEntry.TABLE_TODO_NAME, null);
        try{
            if (cursor.moveToFirst()){
                do{
                    TodoModel todo = new TodoModel();
                    todo.toDo = cursor.getString(cursor.getColumnIndex("todo"));
                    todo.setId(UUID.fromString(cursor.getString(cursor.getColumnIndex("uuid"))));

                    Log.d(TAG, "getAllTodos: " + cursor.getInt(0) + " | " + todo.getId() + " | " + todo.toDo);
                    todos.add(todo);

                }while(cursor.moveToNext());
            }
        } catch (Exception e) {

        } finally {
            if (cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }
        return todos;
    }


}
