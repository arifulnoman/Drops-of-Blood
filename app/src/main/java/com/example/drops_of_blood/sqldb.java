package com.example.drops_of_blood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class sqldb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Blood";
    private static final String TABLE_NAME = "Donor_details";
    private static final String PHONE_NO = "phone_no";
    private static final String NAME = "name";
    private static final String BLOOD_GROUP = "blood_group";
    private static final String AGE = "age";
    private static final String ADDRESS = "address";
    private static final int VERSION_NUMBER = 1;
    private static final String CREATE_TABLE ="CREATE TABLE " +TABLE_NAME+"("+PHONE_NO+"INTEGER, "+NAME+" VARCHAR(200)," +
            " "+AGE+" INTEGER, "+ADDRESS+" VARCHAR(200),"+BLOOD_GROUP+" VARCHAR(200));";

    private static final String DROP_TABLE = "Drop Table IF Exists "+TABLE_NAME;
    private static final String SELECT_ALL = " SELECT * FROM " +TABLE_NAME;


    private  Context context;           //Toast er jonno

    public sqldb(@Nullable Context context) {                                       //constractor
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {                           //create table
                                                                                    //table create er somoy exception hote pare tai try catch use.thakle catch e dekhabe
        
        try
        {
            Toast.makeText(context, "oncreate method called ", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(CREATE_TABLE);
            
        }catch (Exception e)
        {
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try
        {
            Toast.makeText(context, "Upgrade called", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        }catch (Exception e)
        {
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();
        }
    }

    public long insertData(String name,String phone,String age,String blood,String address)
    {
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();                         // shob dta ekshate korar jonno
        contentValues.put(NAME,name);
        contentValues.put(PHONE_NO,phone);
        contentValues.put(AGE,age);
        contentValues.put(BLOOD_GROUP,blood);
        contentValues.put(ADDRESS,address);
        long rowid = sQLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return  rowid;
    }

    public Cursor DisplayDoner()
    {
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(SELECT_ALL,null);
        return cursor;
    }
}
