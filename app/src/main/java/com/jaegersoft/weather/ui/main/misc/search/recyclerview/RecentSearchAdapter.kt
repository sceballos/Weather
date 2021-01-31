package com.jaegersoft.weather.ui.main.misc.search.recyclerview

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jaegersoft.weather.R
import com.jaegersoft.weather.data.response.WeatherAPIResponse
import com.jaegersoft.weather.util.BitmapUtils
import kotlinx.android.synthetic.main.recent_search_item_row.view.*


class RecentSearchAdapter(private val data: List<WeatherAPIResponse>,
                      private val context: Context?,
                      val clickListener: (WeatherAPIResponse) -> Unit) : RecyclerView.Adapter<RecentSearchAdapter.RecentSearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recent_search_item_row,
            parent, false)
        return RecentSearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        val search = data[position]

        if (context != null) {
            Glide.with(context)
                .load(search.current.weatherIcons[0]) //API does not provide icons for forecast days
                .fitCenter()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.iconCondition)
        }

        holder.city.text = search.location.name

        holder.currentConditionText.text = search.current.weatherDescriptions[0]

        holder.currentTemperature.text = "${search.current.temperature.toInt()}° C"

        val weatherDescription = search.current.weatherDescriptions[0].toLowerCase()
        val isDay = search.current.isDay == "yes"

        val textColor = BitmapUtils.updateBackground(holder.itemBackground, weatherDescription, isDay)

        holder.city.setTextColor(textColor)
        holder.currentConditionText.setTextColor(textColor)
        holder.currentTemperature.setTextColor(textColor)

        holder.itemView.setOnClickListener { clickListener(data[position]) }
    }

    override fun getItemCount() = data.size

    class RecentSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconCondition : ImageView = itemView.recent_search_current_condition_iv
        val city : TextView = itemView.recent_search_city_tv
        val currentTemperature : TextView = itemView.recent_search_current_temperature_iv
        val currentConditionText : TextView = itemView.recent_search_current_condition_tv
        val itemBackground : ImageView = itemView.recent_search_item_background
    }
}