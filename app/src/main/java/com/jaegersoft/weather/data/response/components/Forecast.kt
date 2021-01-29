package com.jaegersoft.weather.data.response.components

data class Forecast(var id : String, var details: ForecastDetails)

data class ForecastDetails(var date : String, var dateEpoch : Long, var astro : Astro,
                           var minTemp : Int, var maxTemp : Int, var avgTemp : Int,
                           var totalSnow : Int, var sunHour : Int, var uvIndex : Int)

data class Astro(var sunrise : String,
                 var sunset : String,
                 var moonRise: String,
                 var moonSet : String,
                 var moonPhase : String,
                 var moonIllumination : Int)