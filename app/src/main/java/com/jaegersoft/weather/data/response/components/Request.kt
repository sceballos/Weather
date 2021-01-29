package com.jaegersoft.weather.data.response.components

data class Request(var type : String,
                   var query : String,
                   var language : String,
                   var unit : String)