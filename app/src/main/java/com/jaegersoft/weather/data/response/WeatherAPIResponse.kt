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
    var localtimeEpoch: Int,

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
    var cloudcover: Int,

    @SerializedName("feelslike")
    @Expose
    var feelslike: Int,

    @SerializedName("humidity")
    @Expose
    var humidity: Int,

    @SerializedName("is_day")
    @Expose
    var isDay: String,

    @SerializedName("observation_time")
    @Expose
    var observationTime: String,

    @SerializedName("precip")
    @Expose
    var precip: Int,

    @SerializedName("pressure")
    @Expose
    var pressure: Int,

    @SerializedName("temperature")
    @Expose
    var temperature: Int,

    @SerializedName("uv_index")
    @Expose
    var uvIndex: Int,

    @SerializedName("visibility")
    @Expose
    var visibility: Int,

    @SerializedName("weather_code")
    @Expose
    var weatherCode: Int,

    @SerializedName("weather_descriptions")
    @Expose
    var weatherDescriptions: List<String>,

    @SerializedName("weather_icons")
    @Expose
    var weatherIcons: List<String>,

    @SerializedName("wind_degree")
    @Expose
    var windDegree: Int,

    @SerializedName("wind_dir")
    @Expose
    var windDir: String,

    @SerializedName("wind_speed")
    @Expose
    var windSpeed: Int
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
    var mintemp: Int,

    @SerializedName("maxtemp")
    @Expose
    var maxtemp: Int,

    @SerializedName("avgtemp")
    @Expose
    var avgtemp: Int,

    @SerializedName("totalsnow")
    @Expose
    var totalsnow: Int,

    @SerializedName("sunhour")
    @Expose
    var sunhour: Int,

    @SerializedName("uv_index")
    @Expose
    var uvIndex: Int
)