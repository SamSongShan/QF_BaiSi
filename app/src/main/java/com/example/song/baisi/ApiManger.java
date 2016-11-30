package com.example.song.baisi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 11355 on 2016/11/28.
 */

public class ApiManger {
    public static IApiService creatApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IApiService.TJ_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IApiService iApiService = retrofit.create(IApiService.class);

        return iApiService;
    }
}
