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
    private val retrofitContent : ContentInterface,
){
    suspend fun getCurrent(query : String) : Flow<DataState<WeatherAPIResponse>> = flow{
        emit(DataState.Loading)
        try {
            //val networkResponse = retrofitContent.getCurrent(query)
            val networkResponse = retrofitContent.getCurrentTest()
            Log.e("TAG", "getCurrent: $networkResponse", )
            //val response = entityMapper.mapFromEntity(networkResponse)
            emit(DataState.Success(networkResponse))
        } catch (e : Exception) {
            emit(DataState.Error(e))
        }
    }
}