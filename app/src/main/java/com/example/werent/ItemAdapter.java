package com.example.werent;
/*
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {
    Context cntx;
    ArrayList<String> arlist;
    DataBaseConnection db;
    public ItemAdapter(Context context, ArrayList<String> resource)
    {

        this.cntx=context;
        this.arlist=resource;

    }
    public  class  Holder{
        CheckBox checked;

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
       // ItemsToGroup itg= getItem(position);
        db=new DataBaseConnection(cntx);
        Holder hldr;
        if (convertView==null){
            hldr=new Holder();
            LayoutInflater lif= (LayoutInflater)cntx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=lif.inflate(R.layout.activity_listing_items,parent,false);
            hldr.checked=(CheckBox)convertView.findViewById(R.id.checkBox);
            convertView.setTag(hldr);
        }else {
            hldr=(Holder)convertView.getTag();
        }
        hldr.checked.setText(arlist.get(position));

        return convertView;

    }
}
 */
