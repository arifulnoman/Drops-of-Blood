package com.example.drops_of_blood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class donor_registration extends AppCompatActivity {

    sqldb sql_db;
    EditText nameED, phoneED, ageED, addressED, bloodgrpED;
    Button save_btn, back_btn;
    DonorDB db = new DonorDB(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor_registration);

        sql_db = new sqldb(this);
        SQLiteDatabase sQLiteDatabase = sql_db.getWritableDatabase();       //obj return korbe + call korar jonno

        nameED = findViewById(R.id.d_name);
        phoneED = findViewById(R.id.d_phone);
        ageED = findViewById(R.id.d_age);
        addressED = findViewById(R.id.d_address);
        bloodgrpED = findViewById(R.id.bloodgrpED);
        save_btn = findViewById(R.id.save_btn);
        back_btn = findViewById(R.id.back_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameED.getText().toString();
                String id = name+System.currentTimeMillis();
                String phone = phoneED.getText().toString();
                String ageText = ageED.getText().toString();
                int age = Integer.parseInt(ageText);
                String address = addressED.getText().toString();
                String bloodGroup = bloodgrpED.getText().toString();
                if(name.equals("") || phone.equals("") || ageText.equals("") || address.equals("") || bloodGroup.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Fill all the required data", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Donor Successfully Added", Toast.LENGTH_SHORT).show();
                    db.insertDonor(id,name,phone,age,address,bloodGroup);
                    db.close();
                    Intent i = new Intent(donor_registration.this,Donor_List.class);
                    startActivity(i);
                }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i7= new Intent(donor_registration.this,home_page.class);
                startActivity(i7);
            }
        });
    }
}