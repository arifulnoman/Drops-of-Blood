package com.example.drops_of_blood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBmain extends SQLiteOpenHelper {
    public static final String DB = "Donor.db";
    public static final int VER = 1;
    public DBmain(@Nullable Context context) {
        super(context, DB, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "Create table donortable(id integer primary key, BloodGroup text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    String query = "Drop table if exists donortable";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }
}
