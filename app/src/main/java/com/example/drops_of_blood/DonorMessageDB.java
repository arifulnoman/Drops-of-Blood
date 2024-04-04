package com.example.drops_of_blood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DonorMessageDB extends SQLiteOpenHelper {

    public DonorMessageDB(Context context) {
        super(context, "DonorMessageDB.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("DB@OnCreate");
        String sql = "CREATE TABLE messages  ("
                + "ID TEXT PRIMARY KEY,"
                + "name TEXT NOT NULL,"
                + "phone TEXT,"
                + "bloodGroup TEXT,"
                + "message TEXT"
                + ")";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("Write code to modify database schema here");
        // db.execSQL("ALTER table my_table  ......");
    }
    public void insertDonorMessage(String ID, String name,String phone,String bloodGroup,String message)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cols = new ContentValues();
        cols.put("ID", ID);
        cols.put("name", name);
        cols.put("phone",phone);
        cols.put("bloodGroup",bloodGroup);
        cols.put("message",message);

        db.insert("messages", null ,  cols);
        db.close();
    }

    public Cursor selectDonorMessages(String query) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = null;
        try {
            res = db.rawQuery(query, null);
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
}