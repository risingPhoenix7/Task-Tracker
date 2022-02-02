package com.example.taskreminder;

import android.text.Editable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;



public class Taskclass {
    private int id;
    private int hour;
    private int minute;
    private String title;


    public Taskclass(@NonNull View itemView) {

    }

    @Override
    public String toString() {
        return "Taskclass{" +
                "hour=" + hour +
                ", minute=" + minute +
                ", title='" + title + '\'' +
                ", id=" + id +
                '}';
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Taskclass(int id,int hour, int minute, String title) {

        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.title = title;

    }
}