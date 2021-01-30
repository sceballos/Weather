package com.jaegersoft.weather.data.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherAPIResponse(
    @SerializedName("request")
    @Expose
    var request : Request,

    @SerializedName("location")
    @Expose
    var location : Location,

    @SerializedName("current")
    @Expose
    var current : Current,

    @SerializedName("forecast")
    @Expose
    var forecastMap : Map<String, Forecast>?
)

data class Request(
    @SerializedName("type")
    @Expose
    var type : String,

    @SerializedName("query")
    @Expose
    var query : String,

    @SerializedName("language")
    @Expose
    var language : String,

    @SerializedName("unit")
    @Expose
    var unit : String,
)

data class Location(
    @SerializedName("country")
    @Expose
    var country: String,

    @SerializedName("lat")
    @Expose
    var lat: String,

    @SerializedName("localtime")
    @Expose
    var localtime: String,

    @SerializedName("localtime_epoch")
    @Expose
    var localtimeEpoch: Long,

    @SerializedName("lon")
    @Expose
    var lon: String,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("region")
    @Expose
    var region: String,

    @SerializedName("timezone_id")
    @Expose
    var timezoneId: String,

    @SerializedName("utc_offset")
    @Expose
    var utcOffset: String
)

data class Current(
    @SerializedName("cloudcover")
    @Expose
    var cloudcover: Double,

    @SerializedName("feelslike")
    @Expose
    var feelslike: Double,

    @SerializedName("humidity")
    @Expose
    var humidity: Double,

    @SerializedName("is_day")
    @Expose
    var isDay: String,

    @SerializedName("observation_time")
    @Expose
    var observationTime: String,

    @SerializedName("precip")
    @Expose
    var precip: Double,

    @SerializedName("pressure")
    @Expose
    var pressure: Double,

    @SerializedName("temperature")
    @Expose
    var temperature: Double,

    @SerializedName("uv_index")
    @Expose
    var uvIndex: Double,

    @SerializedName("visibility")
    @Expose
    var visibility: Double,

    @SerializedName("weather_code")
    @Expose
    var weatherCode: Double,

    @SerializedName("weather_descriptions")
    @Expose
    var weatherDescriptions: List<String>,

    @SerializedName("weather_icons")
    @Expose
    var weatherIcons: List<String>,

    @SerializedName("wind_degree")
    @Expose
    var windDegree: Double,

    @SerializedName("wind_dir")
    @Expose
    var windDir: String,

    @SerializedName("wind_speed")
    @Expose
    var windSpeed: Double
)

data class Forecast(

    @SerializedName("date")
    @Expose
    var date: String,

    @SerializedName("date_epoch")
    @Expose
    var dateEpoch: Long,

    @SerializedName("mintemp")
    @Expose
    var mintemp: Double,

    @SerializedName("maxtemp")
    @Expose
    var maxtemp: Double,

    @SerializedName("avgtemp")
    @Expose
    var avgtemp: Double,

    @SerializedName("totalsnow")
    @Expose
    var totalsnow: Double,

    @SerializedName("sunhour")
    @Expose
    var sunhour: Double,

    @SerializedName("uv_index")
    @Expose
    var uvIndex: Double
)