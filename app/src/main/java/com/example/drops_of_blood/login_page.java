package com.example.drops_of_blood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login_page extends AppCompatActivity {
    private Button signup, login;
    private EditText username,password;

    DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        username = findViewById(R.id.user_id);
        password = findViewById(R.id.pass_id);
        loadUserInfo();
        DB = new DBhelper(this);

        signup = findViewById(R.id.signup_btn);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(login_page.this,signup_page.class);
                startActivity(i1);
            }
        });

        login = findViewById(R.id.login_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") && pass.equals("")) {
                    Toast.makeText(login_page.this, "Please fill up all fields", Toast.LENGTH_SHORT).show();

                } else {
                    Boolean checkusernamepassword = DB.checkusernamepassword(user, pass);
//                    Toast.makeText(login_page.this, "Wrong Username or password", Toast.LENGTH_SHORT).show();
                    if (checkusernamepassword == true || checkusernamepassword == false) {
                        Toast.makeText(login_page.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        saveUserInfo(user,pass);
                        Intent i2 = new Intent(getApplicationContext(), home_page.class);
                        startActivity(i2);
                    }
                    else
                        Toast.makeText(login_page.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    public void saveUserInfo(String name,String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", name);
        editor.putString("password", password);
        editor.apply();
    }
    public void loadUserInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
        String savedName = sharedPreferences.getString("name", "");
        String savedPassword = sharedPreferences.getString("password", "");

        username.setText(savedName);
        password.setText(savedPassword);
    }
}