package com.example.taskreminder;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageButton addtask;
    RecyclerView recyclerView;
    DataBaseHelper myDB;
    ArrayList<String> task_id,task_hour,task_minute,task_title;
    CustomAdapter customAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addtask=findViewById(R.id.imageButton);
        recyclerView=findViewById(R.id.recyclerView);




        //add button
        addtask.setOnClickListener(view -> {
            Intent intent =new Intent(MainActivity.this,AddActivity.class);
            startActivity(intent);

        });

        myDB =new DataBaseHelper(MainActivity.this);
        task_id=new ArrayList<>();
        task_hour=new ArrayList<>();
        task_minute=new ArrayList<>();
        task_title=new ArrayList<>();
        storeinarray();
        customAdapter=new CustomAdapter(MainActivity.this,this,task_id,task_hour,task_minute,task_title);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }
    void storeinarray(){
        Cursor cursor =myDB.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(this,"No active reminders",Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                task_id.add(cursor.getString(0));
                task_hour.add(cursor.getString(1));
                task_minute.add(cursor.getString(2));
                task_title.add(cursor.getString(3));
            }
        }
    }



}
