package com.jaegersoft.weather.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jaegersoft.weather.data.response.WeatherAPIResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class APICachePreferences @Inject constructor(@ApplicationContext context: Context){
    private val PREF_TAG = "api_responses"
    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun setCachedResponses(value : List<WeatherAPIResponse>) {
        val editor: SharedPreferences.Editor = prefs.edit()
        val gson = Gson()
        val json = gson.toJson(value)
        editor.putString(PREF_TAG, json)
        editor.apply()
    }

    fun getCachedResponses() : List<WeatherAPIResponse> {
        val serialized = prefs.getString(PREF_TAG, "")!!
        var result = listOf<WeatherAPIResponse>()

        if (serialized.isNotEmpty()) {
            val gson = Gson()
            result = gson.fromJson(serialized, Array<WeatherAPIResponse>::class.java).asList()
        }
        return result
    }
}