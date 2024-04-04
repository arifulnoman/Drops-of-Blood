package com.example.drops_of_blood;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class home_page extends Activity {
    DBmain dBmain;
    SQLiteDatabase db;
    Button post_btn, message_btn,select_btn,donorlist_btn;
    RadioButton Oplus_btn, Ominus_btn, Aplus_btn, Aminus_btn, Bplus_btn, Bminus_btn, ABminus_btn, ABplus_btn, bloodgrp;

    RadioGroup RadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        post_btn = findViewById(R.id.post_btn);
        message_btn = findViewById(R.id.message_btn);
        RadioGroup = findViewById(R.id.RadioGroup);
        donorlist_btn = findViewById(R.id.donorlist_btn);
        dBmain = new DBmain(home_page.this);
        findid();
        insertData();
        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home_page.this,PostActivity.class);
                startActivity(i);
            }
        });
        message_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(home_page.this,Message.class);
                startActivity(i);
            }
        });
    }
    private void insertData() {
        select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                if(Oplus_btn.isChecked()){
                    contentValues.put("BloodGroup",Oplus_btn.getText().toString());
                }
                if (Ominus_btn.isChecked()){
                    contentValues.put("BloodGroup",Ominus_btn.getText().toString());
                }
                if (ABplus_btn.isChecked()){
                    contentValues.put("BloodGroup", ABplus_btn.getText().toString());
                }
                if (Aminus_btn.isChecked()){
                    contentValues.put("BloodGroup",Aminus_btn.getText().toString());
                }
                if (ABplus_btn.isChecked()){
                    contentValues.put("BloodGroup",ABplus_btn.getText().toString());
                }
                if (ABminus_btn.isChecked()){
                    contentValues.put("BloodGroup",ABminus_btn.getText().toString());
                }
                if(Bplus_btn.isChecked()){
                    contentValues.put("BloodGroup",Bplus_btn.getText().toString());
                }
                if (Bminus_btn.isChecked()){
                    contentValues.put("BloodGroup",Bminus_btn.getText().toString());
                }
                db = dBmain.getWritableDatabase();
                Long rec = db.insert("studenttable", null,contentValues);
                if(rec!=null){
                    Toast.makeText(home_page.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(home_page.this);
                    builder.setTitle("Error");
                    builder.setMessage("Data not inserted");
                    builder.setPositiveButton("Ok", null);
                    builder.setCancelable(true);
                    builder.show();
                }
            }
        });
        donorlist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(home_page.this,Donor_List.class);
                startActivity(i3);
            }
        });

    }

    private void findid() {
        Oplus_btn = (RadioButton) findViewById(R.id.Oplus_btn);
        Ominus_btn = (RadioButton) findViewById(R.id.Ominus_btn);
        Aplus_btn = (RadioButton) findViewById(R.id.Aplus_btn);
        Aminus_btn = (RadioButton) findViewById(R.id.Aminus_btn);
        ABplus_btn = (RadioButton) findViewById(R.id.ABplus_btn);
        ABminus_btn = (RadioButton) findViewById(R.id.ABminus_btn);
        Bplus_btn = (RadioButton) findViewById(R.id.Bplus_btn);
        Bminus_btn = (RadioButton) findViewById(R.id.Bminus_btn);
        donorlist_btn = (Button) findViewById(R.id.donorlist_btn);
        select_btn = (Button) findViewById(R.id.select_btn);
    }
}