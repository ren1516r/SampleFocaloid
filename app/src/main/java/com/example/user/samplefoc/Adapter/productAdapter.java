package com.example.user.samplefoc.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.test.espresso.core.deps.guava.base.Splitter;
import android.support.test.espresso.core.deps.guava.collect.Lists;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.samplefoc.R;
import com.example.user.samplefoc.product;
import com.example.user.samplefoc.product_detail;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.text.TextUtils.isEmpty;

/**
 * Created by user on 2/22/2017.  For RecycleView
 */

public class productAdapter extends RecyclerView.Adapter<productAdapter.ViewHolder>{
private ArrayList<product> products;
    private Context mContext;
    public productAdapter(ArrayList<product> products){
        this.products=products;

    }
    @Override
    public productAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_content, parent, false);

       //Added the below mcontext to get the correct context in Glide, otherwise it wil destroy before loading full images.
        //We won't get getActivity().getApplicationContext() in that BindHolder method
        mContext=parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(productAdapter.ViewHolder holder, final int position) {

    String img_URL="http://focalloid.com/Distributor_app/images/product_images_L/";
final String name=products.get(position).getPro_name();
        final String price=Double.toString(products.get(position).getPro_price());
        final String desc=products.get(position).getPro_desc();
          String URL="";

Log.d("name",products.get(position).getPro_name());
     holder.prd_name.setText((CharSequence) products.get(position).getPro_name());
       holder.prd_price.setText(Double.toString(products.get(position).getPro_price()));



       if (products.get(position).getProduct_images() != null) {
            String images = products.get(position).getProduct_images();

            List<String> list = Lists.newArrayList(Splitter.on(",").splitToList(images));

            String img = list.get(0);
 URL=img_URL+img;
            Glide.with(mContext).load(URL).into(holder.prd_image);
        }


        final String finalURL = URL;
        holder.card_view.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(mContext,name,Toast.LENGTH_LONG).show();
        Bundle product_details=new Bundle();
        product_details.putString("Name",name);
        product_details.putString("Price",price);
        product_details.putString("Desc",desc);
        product_details.putString("Url", finalURL);
        Intent prd_intent=new Intent(mContext,product_detail.class);
        prd_intent.putExtras(product_details);
        mContext.startActivity(prd_intent);

    }
});

            }

    @Override
    public int getItemCount() {
       return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView prd_name,prd_price;
        private CardView card_view;
        ImageView prd_image;


        public ViewHolder(View view) {
            super(view);

           prd_name = (TextView)view.findViewById(R.id.prd_name);
            prd_price = (TextView)view.findViewById(R.id.prd_price);
          prd_image = (ImageView)view.findViewById(R.id.prd_image);
            card_view = (CardView)view.findViewById(R.id.id_card);


        }

    }

}

