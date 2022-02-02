package com.example.taskreminder;
//To display the adding window

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;



import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    Button save,cancelButton;
    EditText titleTask;
    TimePicker timepicker;
    private int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        timepicker=findViewById(R.id.timeid);
        titleTask=findViewById(R.id.title);
        save=findViewById(R.id.button);
        createNotificationChannel();

        //save button
        save.setOnClickListener(view -> {
            if( titleTask.getText().toString().trim().equals(""))
            {
                titleTask.setError( "Title is required" );

                titleTask.setHint("Enter title of the task here");
            } else
                {   Taskclass taskclass;

                        taskclass=new Taskclass(-1,timepicker.getHour(),timepicker.getMinute(),titleTask.getText().toString());
                        DataBaseHelper dataBaseHelper=new DataBaseHelper(AddActivity.this);
                        boolean success = dataBaseHelper.addOne(taskclass);
                        Toast.makeText(AddActivity.this,"Success ="+success,Toast.LENGTH_SHORT).show();



                    Intent intention = new Intent(AddActivity.this,ReminderBroadcast.class);
                    intention.putExtra("notificationId", notificationId);
                    intention.putExtra("todo", titleTask.getText().toString());

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(AddActivity.this, 0,
                            intention, 0);

                    AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
                    long timeAtButtonClick=System.currentTimeMillis();
                    long additionaltime=5000;//test for 5seconds
                    alarmManager.set(AlarmManager.RTC_WAKEUP,timeAtButtonClick+additionaltime,pendingIntent);


                    //go back to the main activity
                    titleTask.setText("");
                    Intent intent =new Intent(AddActivity.this,MainActivity.class);
                    startActivity(intent);


                 }


        });

        //cancel button
        cancelButton=findViewById(R.id.cancel);
        cancelButton.setOnClickListener(view -> {
            //go back to the main activity
            titleTask.setText("");
            Intent intent =new Intent(AddActivity.this,MainActivity.class);
            startActivity(intent);

        });

    }
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name="Task Reminder channel";
            String description="Well, you read the channel name";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel=new NotificationChannel("channelid",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}