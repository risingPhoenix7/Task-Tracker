package com.example.taskreminder;
//adapter to show contents in recycler view

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList task_id,task_hour,task_minute,task_title;
    int position;
    CustomAdapter(Activity activity,Context context,ArrayList task_id,ArrayList task_hour,ArrayList task_minute,ArrayList task_title)
    {
        //to refresh activity after update button
        this.activity=activity;

        //created array here and received the global variables into it
        this.context =context;
        this.task_id=task_id;
        this.task_hour=task_hour;
        this.task_minute=task_minute;
        this.task_title=task_title;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inflating layout
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.singlerow,parent,false );
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        this.position=position;
        //setting the texts to the required ones.
        holder.title_txt.setText(String.valueOf(task_title.get(position)));

        holder.minute_txt.setText(String.valueOf(task_minute.get(position)));
        holder.hour_txt.setText(String.valueOf(task_hour.get(position)));
        //when a singlerow is clicked, click listener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent =new Intent(context,UpdateActivity.class);//to go to the update activity
                //but also carry some extras along
            intent.putExtra("id",String.valueOf(task_id.get(position)));
            intent.putExtra("hour",String.valueOf(task_hour.get(position)));
            intent.putExtra("minute",String.valueOf(task_minute.get(position)));
            intent.putExtra("title",String.valueOf(task_title.get(position)));
            //to update the database on updating activity
            activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        //no. of such singlerows
        return task_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView hour_txt,minute_txt,title_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            hour_txt =itemView.findViewById(R.id.hourId);
            minute_txt =itemView.findViewById(R.id.minuteId);
            title_txt =itemView.findViewById(R.id.titleId);
            mainLayout=itemView.findViewById(R.id.mainLayout);

        }
    }
}
