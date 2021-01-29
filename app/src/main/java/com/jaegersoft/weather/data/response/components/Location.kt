package com.jaegersoft.weather.data.response.components

data class Location(var name : String,
                    var country : String,
                    var region : String,
                    var lat : String,
                    var lon : String,
                    var timezone_id : String,
                    var localtime : String,
                    var localtimeEpoch : Long,
                    var utcOffset : String, )