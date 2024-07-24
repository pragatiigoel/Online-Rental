package com.example.werent;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateGroup extends AppCompatActivity {
    Button additem,creategroup;
    DataBaseConnection adder=new DataBaseConnection(this);
    EditText groupname,groupdescription,setprice;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        additem = (Button) findViewById(R.id.additem);
        creategroup = (Button) findViewById(R.id.creategroup);
        groupname = (EditText) findViewById(R.id.groupname);
        groupdescription = (EditText) findViewById(R.id.groupdescription);
        setprice = (EditText) findViewById(R.id.setprice);
        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(CreateGroup.this,ListingGroupItems.class);
                startActivity(i);
            }
        });
      /*  creategroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    public boolean createNewGroup(String groupname,String discription,String price,String items){
                boolean res= adder.createNewGroup(groupname.getText().toString(),groupdescription.getText().toString(),setprice.getText().toString(),);
            }
        });
*/

    }
}
