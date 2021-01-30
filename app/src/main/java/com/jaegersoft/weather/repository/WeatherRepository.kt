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

    suspend fun getForecast(query : String) : Flow<DataState<WeatherAPIResponse>> = flow{
        emit(DataState.Loading)
        try {
            val networkResponse = retrofitContent.requestForecast(query)
            Log.e("WeatherRepository : success", "getForecast: $networkResponse", )

            if (networkResponse.error != null) {
                emit(DataState.Error("Couldn't process the request. Try again"))
                return@flow
            }
            emit(DataState.Success(networkResponse))
        } catch (e : Exception) {
            Log.e("WeatherRepository", "$e", )
            emit(DataState.Error("The request failed"))
        }
    }
}