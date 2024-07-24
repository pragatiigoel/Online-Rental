package com.example.werent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;;import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection extends SQLiteOpenHelper {
    public static final String dbadder="Werentdatabase";
    public static final String prtableadder="profile";
    public static final String col1="name";
    public static final String col2="username";
    public static final String col3="location";
    public static final String col4="password";
   // public static int idno=123;
    public static final String logdata="logindata";
    public DataBaseConnection(Context context) {
        super(context, dbadder,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDb) {
        sqLiteDb.execSQL("create table "+prtableadder+"(username TEXT,name TEXT,location TEXT)");
        sqLiteDb.execSQL("CREATE TABLE groups (groupid INTEGER PRIMARY KEY AUTOINCREMENT,groupname TEXT,description TEXT,price TEXT,itemgroup TEXT)");
        sqLiteDb.execSQL("CREATE TABLE products(productid INTEGER PRIMARY KEY AUTOINCREMENT,productname TEXT,discription TEXT,price TEXT,catagory TEXT,itemgroup TEXT,imgpic blob)");

        sqLiteDb.execSQL("create table "+logdata+"(username TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDb, int i, int i1) {
        sqLiteDb.execSQL("Drop table if exists "+prtableadder);
        sqLiteDb.execSQL("Drop table if exists "+logdata);
        sqLiteDb.execSQL("DROP TABLE IF EXISTS products");
        sqLiteDb.execSQL("DROP TABLE IF EXISTS groups");

        onCreate(sqLiteDb);

    }
    public Cursor getlogdata(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+logdata,null);
        return res;
    }
    public boolean insertLogin (String uname,String pwd) {
        SQLiteDatabase logintab=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put(col2,uname);
        c.put(col4,pwd);
        long result=logintab.insert(logdata,null,c) ;
        if (result==-1)
            return false;
        else
            return true;
    }
    public  boolean insertprofiledata(String username,String name,String location){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues p=new ContentValues();
        p.put(col2,username);
        p.put(col1,name);
        p.put(col3,location);
        long result=db.insert(prtableadder,null,p) ;
        if (result==-1)
            return false;
        else
            return true;
    }
    public boolean addItemsToTable(String productname,String discrption,String price,String catogary,String group,byte[] imgpic){
        SQLiteDatabase itemdb=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("productname",productname);
        c.put("discription",discrption);
        c.put("price",price);
        c.put("catagory",catogary);
        c.put("itemgroup",group);
        c.put("imgpic",imgpic);
        long result = itemdb.insert("products",null,c);
        if (result!=-1)
            return true;
        else
            return false;
    }
    public boolean createNewGroup(String groupname,String discription,String price,String items){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("goupid",123);
        c.put("groupname",groupname);
        c.put("price",price);
        c.put("itemgroup",items);
        long res= db.insert("groups",null,c);
        return true;
    }/*
    public List<ItemsToGroup> sentItemsList(){
        List<ItemsToGroup> objec= new ArrayList<ItemsToGroup>();
        String str="SELECT productid,productname,price FROM products";
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor c = db.rawQuery(str,null);
        if (c.moveToFirst()){
            do {
                ItemsToGroup glist=new ItemsToGroup();
                glist.setItemid(c.getString(0));
                glist.setItem(c.getString(1));
                glist.setPrice(c.getString(2));
                objec.add(glist);
            }while (c.moveToNext());

        }
        return objec;
    }
    */
    public  Cursor  sentToListView(){
        SQLiteDatabase db= this.getReadableDatabase();
        String str="SELECT productid,productname,price FROM products";
        Cursor c = db.rawQuery(str,null);
        return c;
    }

}