package com.jaegersoft.weather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.jaegersoft.weather.R
import com.jaegersoft.weather.ui.main.fragments.details.DetailsFragment
import com.jaegersoft.weather.ui.main.fragments.search.DataUpdateListener
import com.jaegersoft.weather.ui.main.fragments.search.SearchFragment
import com.jaegersoft.weather.ui.main.fragments.search.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), DataUpdateListener {

    val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            loadFragment(SearchFragment.newInstance())
        }
    }

    private fun loadFragment(fragment : Fragment) : Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment)
            .commitNow()
        return true
    }

    override fun onDataUpdate() {
        loadFragment(DetailsFragment.newInstance())
    }
}