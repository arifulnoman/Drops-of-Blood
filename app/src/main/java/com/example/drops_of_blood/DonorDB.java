package com.example.drops_of_blood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DonorDB extends SQLiteOpenHelper {

    public DonorDB(Context context) {
        super(context, "DonorDB.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("DB@OnCreate");
        String sql = "CREATE TABLE donors  ("
                + "ID TEXT PRIMARY KEY,"
                + "name TEXT NOT NULL,"
                + "phone TEXT,"
                + "age INT,"
                + "address TEXT,"
                + "bloodGroup TEXT"
                + ")";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("Write code to modify database schema here");
        // db.execSQL("ALTER table my_table  ......");
    }
    public void insertDonor(String ID, String name,String phone, int age, String address,String bloodGroup)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cols = new ContentValues();
        cols.put("ID", ID);
        cols.put("name", name);
        cols.put("phone",phone);
        cols.put("age",age);
        cols.put("address",address);
        cols.put("bloodGroup",bloodGroup);

        db.insert("donors", null ,  cols);
        db.close();
    }

    public Cursor selectDonors(String query) {
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