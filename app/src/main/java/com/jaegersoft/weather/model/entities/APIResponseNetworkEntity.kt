package com.jaegersoft.weather.model.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jaegersoft.weather.data.response.components.Current
import com.jaegersoft.weather.data.response.components.Forecast
import com.jaegersoft.weather.data.response.components.Location
import com.jaegersoft.weather.data.response.components.Request

data class APIResponseNetworkEntity(
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
    var forecast: List<Forecast>?
)