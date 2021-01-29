package com.jaegersoft.weather.data.response.components

data class Current(var observationTime : String,
                   var temperature : Int,
                   var weatherCode : Int,
                   var weatherIcons : List<String>,
                   var weatherDescriptions : List<String>,
                   var windSpeed : String,
                   var windDegree : String,
                   var windDir : String,
                   var pressure : String,
                   var precipitation : String,
                   var humidity : String,
                   var cloudCover : String,
                   var feelsLike : String,
                   var uv_index : String,
                   var visibility : String,
                   var isDay : String)