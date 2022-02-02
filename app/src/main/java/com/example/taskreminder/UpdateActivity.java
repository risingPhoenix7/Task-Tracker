package com.example.taskreminder;

import static java.lang.Integer.min;
import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    Button updateButton,cancelButton,deleteButton;
    EditText titleTask;
    TimePicker timepicker;
    String id,hour,minute,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        timepicker=findViewById(R.id.timeid2);
        titleTask=findViewById(R.id.title2);
        updateButton=findViewById(R.id.updatebutton);
        deleteButton=findViewById(R.id.deletebutton);
        //display existing info in the clock, text box
        getAndSetData();


        //update button
        //save button
        updateButton.setOnClickListener(view -> {

                Taskclass taskclass;
                taskclass=new Taskclass(-1,timepicker.getHour(),timepicker.getMinute(),titleTask.getText().toString());
                DataBaseHelper dataBaseHelper=new DataBaseHelper(UpdateActivity.this);
                boolean success = dataBaseHelper.addOne(taskclass);
                Toast.makeText(UpdateActivity.this,"Update ="+success,Toast.LENGTH_SHORT).show();

                DataBaseHelper myDB= new DataBaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);

                //go back to the main activity
                titleTask.setText("");
                Intent intent =new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);


                //call notification thing and pass t1 to it.




        });

        //delete button
        deleteButton.setOnClickListener(view -> {
            DataBaseHelper myDB= new DataBaseHelper(UpdateActivity.this);
            myDB.deleteOneRow(id);
            finish();
        });

        //cancel button
        cancelButton=findViewById(R.id.cancelbutton);
        cancelButton.setOnClickListener(view -> {
            //go back to the main activity
            titleTask.setText("");
            Intent intent =new Intent(UpdateActivity.this,MainActivity.class);
            startActivity(intent);

        });
    }
    void getAndSetData(){
        if(getIntent().hasExtra("id")&&getIntent().hasExtra("hour")&&getIntent().hasExtra("minute")&&getIntent().hasExtra("title"))
        {
            //getting intent data
            id=getIntent().getStringExtra("id");
            hour=getIntent().getStringExtra("hour");
            minute=getIntent().getStringExtra("minute");
            title=getIntent().getStringExtra("title");

            //setting intent data
            titleTask.setText(title);
            timepicker.setHour(parseInt(hour));
            timepicker.setMinute(parseInt(minute));
        }
        else
            Toast.makeText(UpdateActivity.this,"No data received",Toast.LENGTH_SHORT).show();
    }
}