package com.vc19003337.betterweatherapp.Retrofit;

import com.vc19003337.betterweatherapp.Models.WeatherResults;

import retrofit2.http.GET;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Query;

public interface IOpenWeather
{
    @GET("weather")
    Observable<WeatherResults> getWeatherLatAndLon (@Query("lat") String lat ,
                                                  @Query("lon") String lon,
                                                  @Query("appid") String appid,
                                                  @Query("units") String units);

}
