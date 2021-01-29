package com.jaegersoft.weather.model.api

import com.jaegersoft.weather.data.response.WeatherAPIResponse
import com.jaegersoft.weather.di.RetrofitModule
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ContentInterface {
    @GET("/current?access_key=${RetrofitModule.acessKey}")
    suspend fun requestCurrent(@Query("query")  query: String?) : WeatherAPIResponse

    @GET("/forecast?access_key=${RetrofitModule.acessKey}")
    suspend fun requestForecast(@Query("query")  query: String?) : WeatherAPIResponse
}