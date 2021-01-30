package com.jaegersoft.weather.ui.main.fragments.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jaegersoft.weather.R
import com.jaegersoft.weather.data.response.WeatherAPIResponse
import com.jaegersoft.weather.databinding.SearchFragmentBinding
import com.jaegersoft.weather.ui.MainActivity
import com.jaegersoft.weather.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

interface DataUpdateListener {
    fun onDataUpdate()
}

@AndroidEntryPoint
class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var mainActivity: MainActivity
    private lateinit var binding : SearchFragmentBinding
    private lateinit var  dataUpdateListener: DataUpdateListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            dataUpdateListener = context as DataUpdateListener
        } catch (castException: ClassCastException) {
            /** The activity does not implement the listener.  */
        }

        mainActivity = activity as MainActivity
    }

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)

        binding.searchBtn.setOnClickListener {
            Log.e("TAG", "onCreateView: ${binding.cityEt.text.toString()} ")
            mainActivity.viewModel.setStateEvent(
                WeatherSearchStateEvent.GetForecast,
                binding.cityEt.text.toString()
            )
        }

        mainActivity.viewModel.dataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<WeatherAPIResponse> -> {
                    Log.e("TAG", "onCreateView: fragment transition to details")
                    mainActivity.viewModel.fillForecastDummyData(dataState.data)
                    dataUpdateListener.onDataUpdate()
                }
                is DataState.Error -> {
                    Log.e("TAG", "onCreateView: ERROR getting data")
                }
                DataState.Loading -> {
                    Log.e("TAG", "onCreateView: update ui")
                }
            }
        })

        return binding.root
    }
}