package com.example.user.samplefoc.Interface;

import com.example.user.samplefoc.JSONResponse;
import com.example.user.samplefoc.product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by user on 2/22/2017.
 */

public interface productAPI {
    @GET("services/get_products")
    Call<JSONResponse> getJSON();
}
