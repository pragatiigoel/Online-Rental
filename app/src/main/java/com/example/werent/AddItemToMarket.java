package com.example.werent;

//import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class AddItemToMarket extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText productname, description, price;
    ImageView productpics;
    Button addproductpic, addtoitemsdb;
    DataBaseConnection itemadder = new DataBaseConnection(this);
    Spinner catagories;
    Spinner groups;
    String selectedcatagory, selectedgroup;
    Bitmap bp;
    byte[] img;
    String catagory[] = {"Home", "Electronics", "Clothing", "Books", "Cunstruction", "Appliences", "Sports", "Kitchen utensils"};
    String group[] = {"g1", "g2", "g3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_to_market);
        productname = (EditText) findViewById(R.id.productname);
        description = (EditText) findViewById(R.id.description);
        price = (EditText) findViewById(R.id.priceofitem);
        productpics = (ImageView) findViewById(R.id.productpics);
        addproductpic = (Button) findViewById(R.id.addproductpic);
        addtoitemsdb = (Button) findViewById(R.id.addtoitemdb);
        catagories = (Spinner) findViewById(R.id.catogary);
        groups = (Spinner) findViewById(R.id.prgroup);
        catagories.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, catagory);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catagories.setAdapter(aa);
        selectedcatagory = catagories.getSelectedItem().toString();
        groups.setOnItemSelectedListener(this);
        aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, group);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groups.setAdapter(aa);
        addproductpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        selectedgroup = groups.getSelectedItem().toString();
        addtoitemsdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItmesToDB();
            }

        });
    }

    public void addItmesToDB() {
        img = profileImage(bp);



        //e(String productname,String discrption,String price,String features[],int catogary,String group){
        boolean c = itemadder.addItemsToTable(productname.getText().toString(), description.getText().toString(), price.getText().toString(), selectedcatagory, selectedgroup,img);
        if (c) {
            Toast.makeText(this, "Item added successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "There was an error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void selectImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 2:
                if (resultCode == RESULT_OK) {
                    Uri choosenImage = data.getData();

                    if (choosenImage != null) {

                        bp = decodeUri(choosenImage, 400);
                        productpics.setImageBitmap(bp);
                    }
                }
        }
    }


    //COnvert and resize our image to 400dp for faster uploading our images to DB
    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {

        try {

            // Decode image size


            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

            // The new size we want to scale to
            // final int REQUIRED_SIZE =  size;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //Convert bitmap to bytes
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    private byte[] profileImage(Bitmap b){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 0, bos);
        return bos.toByteArray();

    }


}

