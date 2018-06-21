package com.example.huntertsai.swipelistview;

import java.util.UUID;

/**
 * Created by huntertsai on 2018-02-02.
 */

public class TodoModel {

    UUID id;
    String toDo;

    public TodoModel(){
        this(UUID.randomUUID());
    }

    public TodoModel(UUID uuid){
        id = uuid;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return toDo;
    }
}
