package com.jutils.retrofit;

import com.jutils.retrofit.entity.Weather;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mi on 18-1-10.
 */
public interface ApiXu {

    @GET("current.json?key=40b10def99f54043af2131345180801&q=Yerevan")
    Call<Weather> getWeather();
}
