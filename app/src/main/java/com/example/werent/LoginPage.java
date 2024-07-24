package com.example.werent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPage extends AppCompatActivity {
TextView reg;
EditText password;
EditText logInId;
Button b;
DataBaseConnection sd=new DataBaseConnection(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        reg=(TextView)findViewById(R.id.newregister);
        password=(EditText)findViewById(R.id.editText2);
        logInId=(EditText)findViewById(R.id.loginid);
        b=(Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(LoginPage.this,MainPage.class);
                startActivity(i);
            }
        });

        reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i =new Intent(LoginPage.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
