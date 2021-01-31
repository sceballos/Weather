package com.jaegersoft.weather.util

import android.graphics.Color
import android.widget.ImageView
import com.jaegersoft.weather.R

object BitmapUtils {

    fun updateBackground(target : ImageView, weatherDescription: String, isDay: Boolean) : Int{

        var resultFontColor = Color.BLACK

        when {
            weatherDescription.contains("sunny") || weatherDescription.contains("clear") -> {
                if (isDay) {
                    target.setImageResource(R.drawable.sunny_day_bg)
                } else {
                    target.setImageResource(R.drawable.clear_night_bg)
                    resultFontColor = Color.WHITE
                }
            }

            weatherDescription.contains("snow") -> {
                if (isDay) {
                    target.setImageResource(R.drawable.snow_bg_day)
                } else {
                    target.setImageResource(R.drawable.snow_bg_night)
                    resultFontColor = Color.WHITE
                }
            }

            weatherDescription.contains("cloud") -> {
                if (isDay) {
                    target.setImageResource(R.drawable.cloud_day_bg)
                } else {
                    target.setImageResource(R.drawable.cloud_night_bg)
                }
            }

            weatherDescription.contains("overcast") -> {
                target.setImageResource(R.drawable.overcast_bg)
            }

            weatherDescription.contains("mist") || weatherDescription.contains("haze") || weatherDescription.contains("rain")-> {
                target.setImageResource(R.drawable.mist_bg)
            }

            else -> {
                resultFontColor = Color.WHITE
            }
        }

        return resultFontColor
    }
}