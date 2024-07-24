package com.example.werent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.widget.Toast.*;

public class RegisterActivity extends AppCompatActivity {
EditText uname,phone,txt;
Button register;
    String otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register=(Button)findViewById(R.id.reg);
        uname=(EditText) findViewById(R.id.username);
        phone=(EditText)findViewById(R.id.phone);
        Random r= new Random();
        otp =""+r.nextInt(9999);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // otpgenerator();
                SmsManager verify= SmsManager.getDefault();
                verify.sendTextMessage(phone.getText().toString(),null,"your OTP for WeRent registration is "+otp+" with user name "+uname.getText().toString(),null,null);
                alertmessage();

            }
        });
    }/*
    public void otpgenerator(){
        String otp;
        Random r=new Random();
        otp=""+r.nextInt(9999);
        SmsManager sentotp=SmsManager.getDefault();
        sentotp.sendTextMessage(phone.getText().toString(),null,"your OTP for WeRent registration is "+otp+" with user name "+uname.getText().toString(),null,null);
    }*/
    public void alertmessage()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Enter your OTP ");
        txt=new EditText(this);
        builder.setView(txt);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setPositiveButton("Verify", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (txt.getText().toString().equals(otp)){
                    Intent pr =new Intent(RegisterActivity.this,Profile.class);
                    pr.putExtra("uname",uname.getText().toString());
                    startActivity(pr);
                    finish();
                }
                else {
                    //AlertDialog.Builder
                    Toast.makeText(RegisterActivity.this,"OTP not matching", Toast.LENGTH_SHORT).show();
                    alertmessage();
                }
            }
        });
        builder.show();



    }
}
