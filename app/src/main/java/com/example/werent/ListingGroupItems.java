
package com.example.werent;

import android.database.Cursor;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class ListingGroupItems extends AppCompatActivity {
    ListView listing;
    Button additems;
    ArrayList strselected = new ArrayList();
    ArrayList<String> arrlist = new ArrayList<String>();
    ArrayList<String> pricrlist = new ArrayList<String>();
    DataBaseConnection db = new DataBaseConnection(this);
    int gtotal=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_items_to_group);
        listing = (ListView) findViewById(R.id.listing);
        additems = (Button) findViewById(R.id.addtigroup);
        showItems();
    }

    public void showItems() {
        Cursor c = db.sentToListView();
        arrlist.clear();
        if (c.getCount() == 0) {
            Toast.makeText(this, "You don't have added any items to the market", Toast.LENGTH_SHORT).show();
        } else {
            while (c.moveToNext()) {
                arrlist.add(c.getString(c.getColumnIndex("productname")));
               // pricrlist.add(c.getString(c.getColumnIndex("price")));
            }
            ArrayAdapter<String> iadpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, arrlist);
            listing.setAdapter(iadpter);
            listing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                SparseBooleanArray checked = listing.getCheckedItemPositions();
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    String stra=listing.getItemAtPosition(i).toString();
//                    strselected.append(stra);
//                    Toast.makeText(ListingGroupItems.this, strselected, Toast.LENGTH_SHORT).show();
                    String selecteditem=((TextView)view).getText().toString();
                    if (strselected.contains(selecteditem)){
                        strselected.remove(selecteditem);


                    }else {
                        strselected.add(selecteditem);

                        Toast.makeText(ListingGroupItems.this, ""+gtotal, Toast.LENGTH_SHORT).show();
                        //int j = strselected.get(selecteditem);
                      //  if (checked.get(i)){
                        //    gtotal+=(int) pricrlist[i];
                        }
                    }
//                    int length = listing.getCount();
//                    for (int j = 0; j < length; j++) {
//                        if (checked.get(j)) {
//
//                           // if (ifchecked(checked)) {
//                               String item = arrlist.get(j);
//                                strselected.add(item);
//                            //}
//                            }else {
//                            String item = arrlist.get(j);
//                            strselected.remove(item);
//
//                        }
//
//                    }
//
            });
        }
    }

}
