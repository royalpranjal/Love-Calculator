package com.pranjal.randomfamousquotes.Service;

import com.pranjal.randomfamousquotes.Response.GetPercentageResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

/**
 * Created by royalpranjal on 3/31/2017.
 */

public interface GetPercentage {
    @Headers({"X-Mashape-Key: NO0KrgEZGomsh890TEOIQWDGc7PMp1Cs026jsn51y1EbWWm8Xt",
            "Accept: application/json"})
    @GET("/getPercentage")
    void getPercentage(
            @Query("fname") String fname,
            @Query("sname") String sname,
            Callback<GetPercentageResponse> callback
    );
}
