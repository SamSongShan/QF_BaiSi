package com.example.song.baisi;

import com.example.song.baisi.jinghua.QuanBuEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 11355 on 2016/11/28.
 */

public interface IApiService {
    //0-20.json
    public static final String COM_PATH = "budejie-android-6.5.7/0-{json}.json?market=tencentyingyongbao&udid=864394010748616&appname=baisibudejie&os=4.4.2&client=android&visiting=&mac=08%3A00%3A27%3A00%3A48%3A0D&ver=6.5.7";
    /**
     * 推荐页面URL
     */
    public static final String TJ_PATH = "http://s.budejie.com/topic/list/jingxuan/1/";

    @GET(IApiService.COM_PATH)
    Call<QuanBuEntity> getTuijian(@Path("json") String json);

}
