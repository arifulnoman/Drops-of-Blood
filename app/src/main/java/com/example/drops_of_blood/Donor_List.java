package com.example.drops_of_blood;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Donor_List extends AppCompatActivity {
    DBmain dBmain;
    ListView list_view;
    ArrayList<Donor> donors;
    CustomDonorAdapter adapter;
    String[] name;
    Button btn,btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_list);
        dBmain = new DBmain(Donor_List.this);
        list_view = findViewById(R.id.list_view);
        btn =findViewById(R.id.btn1);
        btnExit = findViewById(R.id.btn3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5 = new Intent(Donor_List.this, donor_registration.class);
                startActivity(i5);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        displayData();
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent i = new Intent(Donor_List.this,Message.class);
                i.putExtra("DonorID", donors.get(position).id);
                startActivity(i);
            }
        });
    }
    private void displayData() {
        donors = new ArrayList<>();
        DonorDB db = new DonorDB(this);
        Cursor rows = db.selectDonors("SELECT * FROM donors");
        if (rows.getCount() == 0) {
            return;
        }
        while (rows.moveToNext()) {
            String ID = rows.getString(0);
            String name = rows.getString(1);
            String phone = rows.getString(2);
            int age = rows.getInt(3);
            String address = rows.getString(4);
            String bloodGroup = rows.getString(5);

            Donor donor = new Donor(ID, name, phone, age, address, bloodGroup);
            donors.add(donor);
        }
        db.close();
        adapter = new CustomDonorAdapter(this, donors);
        list_view.setAdapter(adapter);
    }
}