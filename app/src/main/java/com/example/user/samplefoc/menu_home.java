package com.example.user.samplefoc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.user.samplefoc.Adapter.productAdapter;
import com.example.user.samplefoc.Interface.productAPI;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2/21/2017.
 */

public class menu_home extends Fragment {
    private RecyclerView recyclerView;
    ArrayList<product> products;
    private productAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
        initViews();
    }

   /* @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }*/

    private void initViews() {
        recyclerView = (RecyclerView)getView().findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
               // .baseUrl("http://shreehome.org")
                .baseUrl("http://focalloid.com/Distributor_app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        productAPI request = retrofit.create(productAPI.class);
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                JSONResponse jsonResponse = response.body();

                products = new ArrayList<>(Arrays.asList(jsonResponse.getProducts()));
                Log.i("jsonResponse",Arrays.toString(jsonResponse.getProducts()));
               adapter = new productAdapter(products);
              recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("JASONERROR",t.getMessage());
            }

        });
    }
}

