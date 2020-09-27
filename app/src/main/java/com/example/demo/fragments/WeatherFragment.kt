package com.example.demo.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.demo.R
import com.example.demo.models.Country
import com.google.gson.Gson

class WeatherFragment: Fragment() {

    var info: TextView? = null
    var error: TextView? = null
    var progress: ProgressBar? = null

    companion object{
        fun newInstance(): WeatherFragment{
            return WeatherFragment();
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        Log.v("info lat--> ", arguments?.getString("lat").toString())
        Log.v("info long--> ", arguments?.getString("long").toString())

        info = rootView.findViewById(R.id.weatherInfo)
        error = rootView.findViewById(R.id.weatherError)
        progress = rootView.findViewById(R.id.progress)

        return rootView;
    }

    override fun onStart() {
        super.onStart()

    }

}