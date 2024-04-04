package com.example.drops_of_blood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Message extends AppCompatActivity {

    EditText etName,etBloodGroup,etPhone, etMessage;
    Button btSend;
    DonorDB donorDB = new DonorDB(this);

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_phone);
        etBloodGroup = findViewById(R.id.et_bloodGroup);
        etMessage= findViewById(R.id.et_message);
        btSend = findViewById(R.id.bt_send);
        Intent i = getIntent();
        if(i.hasExtra("DonorID"))
        {
            String ID = i.getStringExtra("DonorID");
            String query = "SELECT * FROM donors WHERE ID = '" + ID + "'";
            String name,phone,bloodGroup,message;
            Cursor rows = donorDB.selectDonors(query);
            if (rows.getCount() == 0) {
                return;
            }
            rows.moveToNext();
            String id = rows.getString(0);
            if(ID.equals(id))
            {
                name = rows.getString(1);
                phone = rows.getString(2);
                bloodGroup = rows.getString(5);
                etName.setText(name);
                etPhone.setText(phone);
                etBloodGroup.setText(bloodGroup);
            }
            name = etName.getText().toString();
            phone = etPhone.getText().toString();
            bloodGroup = etBloodGroup.getText().toString();
            message = etMessage.getText().toString();
            DonorMessageDB db = new DonorMessageDB(this);
            db.insertDonorMessage(id,name,phone,bloodGroup,message);
            db.close();
            donorDB.close();
        }
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(Message.this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED){
                    sendMessage();
                }
                else{
                    ActivityCompat.requestPermissions(Message.this, new String[]{Manifest.permission.SEND_SMS}, 100);
                }
                Intent i = new Intent(Message.this,home_page.class);
                startActivity(i);
            }
        });
    }
    private void sendMessage() {
        String sPhone = etPhone.getText().toString().trim();
        String sMessage = etMessage.getText().toString().trim();
        System.out.println("HI HERE");
        if(!sPhone.equals("") && !sMessage.equals("")){
            SmsManager smsManager = SmsManager.getDefault();

            smsManager.sendTextMessage(sPhone,
                    null, sMessage, null, null);

            Toast.makeText(getApplicationContext(), "SMS sent Successfully!",
                    Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Enter value first",
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100 && grantResults.length>0 && grantResults[0]
        == PackageManager.PERMISSION_GRANTED){
            sendMessage();
        }
        else{
            Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }

    }
}