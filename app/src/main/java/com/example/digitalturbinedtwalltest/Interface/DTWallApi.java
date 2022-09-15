package com.example.digitalturbinedtwalltest.Interface;

import com.example.digitalturbinedtwalltest.Model.DTWall;
import com.example.digitalturbinedtwalltest.Model.DWTestModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface DTWallApi {

    //end point for base url
    @GET("0d5c2100-92d2-4cf7-ae35-fb746ae120e2")
    Call<List<DWTestModel>> getItems();

    @GET("5efdc774-d800-43da-8cf8-3093759b762e")
    Call<DTWall> getAllItems();

    @GET("offers.json?appid=1246&google_ad_id=a738df47-a418-4b32-9627-10ca1ad1abe1&google_ad_id_limited_tracking_enabled=true&ip=109.235.143.113&locale=de&page=2&pub0=campaign2&timestamp=1663236208&uid=superman&hashkey=a3d38051c06a489bac607aeebc88ba41131dfc7f")
    Call<DTWall> fetchData();



    @GET //i.e http://api.fyber.com/feed/v1/offers.json?
    Call<DTWall> getData(@Url String endpoint);

    //call this way
//    Call<Results> call = service.productList("Whatever", "here", IService.API_KEY);

        //https://api.test.com/Search?one=Whatever&two=here&key=SFSDF24242353434

}
