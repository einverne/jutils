package com.jutils.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mi on 18-1-10.
 */
public class WeatherRetro {

    private static final String BASE_URL = "http://api.apixu.com/v1/";
    private static ApiXu apiXuService;

    public static ApiXu getInstance() {
        if (apiXuService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiXuService = retrofit.create(ApiXu.class);
            return apiXuService;
        } else {
            return apiXuService;
        }
    }
}
