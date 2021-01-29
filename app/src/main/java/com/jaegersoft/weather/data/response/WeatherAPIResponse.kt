package com.jaegersoft.weather.data.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherAPIResponse(
    @SerializedName("request")
    @Expose
    var request : Request,
/*
    @SerializedName("current")
    @Expose
    var current : Location,

    @SerializedName("current")
    @Expose
    var current : Current,*/
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