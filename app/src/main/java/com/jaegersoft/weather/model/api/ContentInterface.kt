package com.jaegersoft.weather.model.api

import com.jaegersoft.weather.data.response.WeatherAPIResponse
import com.jaegersoft.weather.di.RetrofitModule
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface ContentInterface {
    @GET("/forecast?access_key=ba5c575b0cc8dd1fba40a13a3bf66bc2&query=New York")
    suspend fun getCurrentTest() : WeatherAPIResponse
}