package com.example.drops_of_blood;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signup_page extends AppCompatActivity {
    EditText name, email, phone, address, password, confirm;
    Button save_btn, back_btn, signin_btn;
    Spinner bloodgrp;
    String[] bloodgroup = {"O+","O-","AB+","AB-","A+","A-","B+","B-"};
    DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);
        Spinner bloodgrp = findViewById(R.id.bloodgrp);
        EditText username = findViewById(R.id.username);
        EditText email = findViewById(R.id.email);
        EditText phone = findViewById(R.id.phone);
        EditText address = findViewById(R.id.address);
        EditText password = findViewById(R.id.password);
        EditText confirm = findViewById(R.id.confirm);
        Button save_btn = findViewById(R.id.save_btn);
        Button back_btn = findViewById(R.id.back_btn);
        signin_btn = findViewById(R.id.signin);
        DB = new DBhelper(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(signup_page.this, android.R.layout.simple_spinner_item, bloodgroup);
        adapter.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item));
        bloodgrp.setAdapter(adapter);

        bloodgrp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(signup_page.this, "Select Something", Toast.LENGTH_SHORT).show();

            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String email1 = email.getText().toString();
                String phone1 = phone.getText().toString();
                String address1 = address.getText().toString();
                String confirmpass = confirm.getText().toString();

                if(user.equals("") || pass.equals("") || email1.equals("") || phone1.equals("") || address1.equals("") || confirmpass.equals("")) {
                    Toast.makeText(signup_page.this, "Please fill up all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(confirmpass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insetData(user, pass);
                            if(insert==true){
                                Toast.makeText(signup_page.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                                Intent i6 = new Intent(signup_page.this,login_page.class);
                                startActivity(i6);
                            }
                            else
                                Toast.makeText(signup_page.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(signup_page.this, "User Already Exists", Toast.LENGTH_SHORT).show();

                    }
                    else
                        Toast.makeText(signup_page.this, "password Not Matching", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i5 = new Intent(getApplicationContext(), login_page.class);
                startActivity(i5);

            }
        });
    }
}