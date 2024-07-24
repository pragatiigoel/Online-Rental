package com.example.werent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity {
Button togroup,toitem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        togroup=(Button)findViewById(R.id.togroup);
        toitem=(Button)findViewById(R.id.toadditems);
        toitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainPage.this,AddItemToMarket.class);
                startActivity(i);
            }
        });
        togroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainPage.this,CreateGroup.class);
                startActivity(i);
            }
        });
    }
}
