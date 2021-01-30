package com.jaegersoft.weather.ui.main.fragments.details.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jaegersoft.weather.R
import com.jaegersoft.weather.data.response.Forecast
import kotlinx.android.synthetic.main.forecast_item_row.view.*


class ForecastAdapter(private val data: List<Pair<String, Forecast>>,
                      private val context: Context?,
                      val clickListener: (Pair<String, Forecast>) -> Unit) : RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.forecast_item_row,
            parent, false)
        return ForecastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val forecast = data[position].second

        holder.day.text = forecast.date
        holder.maxTemp.text = forecast.maxtemp.toString()

        if (context != null) {
            Glide.with(context)
                .load("https://assets.weatherstack.com/images/wsymbols01_png_64/wsymbol_0001_sunny.png") //API does not provide icons for forecast days
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.icon)
        }


        holder.itemView.setOnClickListener { clickListener(data[position]) }
    }

    override fun getItemCount() = data.size

    class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val day : TextView = itemView.forecast_item_day_tv
        val maxTemp : TextView = itemView.forecast_item_max_temp_tv
        val icon : ImageView = itemView.forecast_item_weather_icon_iv
    }
}