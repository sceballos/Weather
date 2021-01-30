package com.jaegersoft.weather.ui.main.fragments.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.jaegersoft.weather.R
import com.jaegersoft.weather.databinding.SearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding : SearchFragmentBinding

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)

        binding.searchBtn.setOnClickListener {
            Log.e("TAG", "onCreateView: ${binding.cityEt.text.toString()} ", )
            viewModel.setStateEvent(WeatherSearchStateEvent.GetForecast, binding.cityEt.text.toString())
        }



        return binding.root
    }
}