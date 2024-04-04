package com.example.drops_of_blood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DBDONOR = "login.db";

    public DBhelper(Context context) {
        super(context, "login.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase  MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }
    public Boolean insetData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues coV = new ContentValues();
        coV.put("Username", username);
        coV.put("Password", password);
        long result = MyDB.insert("users", null, coV);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor c = MyDB.rawQuery("select * from users where username = ?", new String[] {username});
        if(c.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor c = MyDB.rawQuery("select * from users where username = ? and password = ?", new String[] {username,password});
        if(c.getCount()>0)
            return true;
        else
            return false;
    }
}
