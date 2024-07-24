package com.example.werent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class Profile extends AppCompatActivity {
ImageView profilepic;
EditText name,location,password;
Button prsave;
DataBaseConnection l=new DataBaseConnection(this);
String uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=(EditText)findViewById(R.id.name) ;
        password=(EditText)findViewById(R.id.password);
        location=(EditText)findViewById(R.id.location);
        prsave=(Button) findViewById(R.id.prsave);
        Intent i=getIntent();
        uname=i.getStringExtra("uname");
        prsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addToLogin()) {
                   // Toast.makeText(Profile.this, "Inserted", Toast.LENGTH_SHORT).show();
                    addToProfile();
                }
            }
        });
    }
    public void addToProfile(){
        boolean a=l.insertprofiledata(uname,name.getText().toString(),location.getText().toString());
        if (a){
            Toast.makeText(Profile.this,"Inserted", Toast.LENGTH_SHORT).show();
            Intent i =new Intent(Profile.this,MainPage.class);
            startActivity(i);
            finish();
        }
        else
            Toast.makeText(Profile.this,"Not inserted,unsucessful entery",Toast.LENGTH_LONG).show();
    }
    public boolean addToLogin(){
        boolean a=l.insertLogin(uname,password.getText().toString());
       /* if (a){
            //Toast.makeText(Profile.this,"Inserted", Toast.LENGTH_SHORT).show();
            //Intent i =new Intent(Profile.this,MainPage.class);
        }
        else
            Toast.makeText(Profile.this,"Not inserted,unsucessful entery",Toast.LENGTH_LONG).show();*/
        return a;
    }
}
