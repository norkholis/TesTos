package com.example.norkholis.testtos;

/**
 * Created by norkholis on 02/02/18.
 */

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListUser {
    @SerializedName("data")
    @Expose
    private ArrayList<User> user = new ArrayList<>();

    public ArrayList<User> getData() {
        return user;
    }

    public void setData(ArrayList<User> user) {
        this.user = user;
    }
}
