package com.example.user.samplefoc;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.example.user.samplefoc.R.id.url;

public class product_detail extends AppCompatActivity {
String name,price,desc,Url;
    TextView txt_name,txt_price,txt_desc;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        txt_name=(TextView)findViewById(R.id.id_name);
        txt_price=(TextView)findViewById(R.id.id_price);
        txt_desc=(TextView)findViewById(R.id.id_desc);
        imageView=(ImageView)findViewById(R.id.id_image);

        Intent i=getIntent();
        Bundle bnd_prd_details=i.getExtras();
        name=bnd_prd_details.getString("Name");
        price=bnd_prd_details.getString("Price");
        desc=bnd_prd_details.getString("Desc");
        Url=bnd_prd_details.getString("Url");
        Log.d("Url",Url);
        txt_name.setText(name);
        txt_price.setText(price);
        txt_desc.setText(Html.fromHtml(desc));

        Glide.with(getApplicationContext()).load(Url).into(imageView);



    }
}
