package com.example.taskreminder;
//functions required for database Taskclass

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME= "Tasks.db";
    public static final int DATABASE_VERSION =1;

    public static final String TABLE_NAME = "my_library";
    public static final String COLUMN_ID = "ID";
    public static final String TASK_HOUR = "TASK_HOUR";
    public static final String TASK_MINUTE = "TASK_MINUTE";
    public static final String TASK_TITLE = "TASK_TITLE";


    DataBaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);     //constructor requires context database stored as task.db
    }

    //This is the first time a database is accessed. There should be code in here to create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement= "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TASK_HOUR + " INT, " + TASK_MINUTE + " INT," + TASK_TITLE + " TEXT)";
        db.execSQL(createTableStatement);

    }
    //if new updated colums are added in the next update It can create a new database. but i wont be using that
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //no need of upgradability
    }
    public boolean addOne(Taskclass taskClass){                 //inputing values
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv =new ContentValues(); //to insert into the tables
        cv.put(TASK_HOUR ,taskClass.getHour());
        cv.put(TASK_MINUTE ,taskClass.getMinute());
        cv.put(TASK_TITLE ,taskClass.getTitle()); //id is auto increment so dont need that
        long insert = db.insert(TABLE_NAME, null, cv);//inserting into database TASK_TABLE
        //and seeing if it was inserted using the insert variable
        if(insert==-1)
            return false;
        else
            return true;
    }
    //Returns s cursor containing all data
    Cursor readAllData(){
        String query="SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=null;
        if(db!=null)
        {
            cursor=db.rawQuery(query,null);
        }
        return cursor;
    }

    void updateData(String ROW_ID,String HOUR,String MINUTE,String TITLE){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv =new ContentValues(); //to insert into the tables
        cv.put(TASK_HOUR,HOUR);
        cv.put(TASK_MINUTE,MINUTE);
        cv.put(TASK_TITLE,TITLE);

        long result =db.update(TABLE_NAME,cv,"id=?",new String[]{ROW_ID});
        if(result==-1){
            Toast.makeText(context,"Failed to update",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context,"Successfully updated",Toast.LENGTH_SHORT).show();

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db=this.getWritableDatabase();
        long result =db.delete(TABLE_NAME,"id=?",new String[]{row_id});
        if(result==-1){
            Toast.makeText(context,"Some error exists",Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(context,"Successfully deleted",Toast.LENGTH_SHORT).show();

        }

    }




}
