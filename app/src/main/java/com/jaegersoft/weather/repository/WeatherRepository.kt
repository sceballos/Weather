package com.jaegersoft.weather.repository

import android.util.Log
import com.jaegersoft.weather.data.response.WeatherAPIResponse
import com.jaegersoft.weather.model.api.ContentInterface
import com.jaegersoft.weather.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class WeatherRepository
constructor(
    private val retrofitContent : ContentInterface) {

    suspend fun getCurrent(query : String) : Flow<DataState<WeatherAPIResponse>> = flow{
        emit(DataState.Loading)
        try {
            val networkResponse = retrofitContent.requestCurrent(query)
            Log.e("TAG", "getCurrent: $networkResponse", )
            emit(DataState.Success(networkResponse))
        } catch (e : Exception) {
            emit(DataState.Error(e))
        }
    }

    suspend fun getForecast(query : String) : Flow<DataState<WeatherAPIResponse>> = flow{
        emit(DataState.Loading)
        try {
            val networkResponse = retrofitContent.requestForecast(query)
            Log.e("TAG", "getForecast: $networkResponse", )
            emit(DataState.Success(networkResponse))
        } catch (e : Exception) {
            emit(DataState.Error(e))
        }
    }
}