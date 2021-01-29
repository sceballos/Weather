package com.jaegersoft.weather.model.api

import com.jaegersoft.weather.data.response.APIResponse
import com.jaegersoft.weather.di.RetrofitModule
import retrofit2.http.GET
import retrofit2.http.Path

interface ContentInterface {
    @GET("/current?access_key=${RetrofitModule.acessKey}&query={query}")
    suspend fun getCurrent(@Path("query") id: String?) : APIResponse
}