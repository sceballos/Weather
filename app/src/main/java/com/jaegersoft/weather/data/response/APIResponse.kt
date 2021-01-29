package com.jaegersoft.weather.data.response

import com.jaegersoft.weather.data.response.components.Current
import com.jaegersoft.weather.data.response.components.Forecast
import com.jaegersoft.weather.data.response.components.Location
import com.jaegersoft.weather.data.response.components.Request

data class APIResponse(var request : Request,
                       var location : Location,
                       var current : Current,
                       var forecast : Forecast?)