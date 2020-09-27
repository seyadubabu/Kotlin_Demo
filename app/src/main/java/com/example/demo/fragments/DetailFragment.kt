package com.example.demo.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.adapters.CountryAdapter
import com.example.demo.adapters.LanguageAdapter
import com.example.demo.models.Country
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.google.gson.Gson
import java.lang.String

class DetailFragment: Fragment() {

    var iv_flag: ImageView? = null
    var tv_name: TextView? = null
    var tv_region: TextView? = null
    var tv_population: TextView? = null
    var tv_capital: TextView? = null
    var tv_cioc: TextView? = null
    var recyclerView: RecyclerView? = null
    var country: Country? = null
    var languageAdapter: LanguageAdapter? = null

    companion object{
        fun newInstance(): DetailFragment{
            return DetailFragment();
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        Log.v("info --> ", arguments?.getString("country").toString())
        val gson = Gson()
        country = gson.fromJson(arguments?.getString("country"), Country::class.java)
        iv_flag = rootView.findViewById(R.id.iv_flag)
        tv_name = rootView.findViewById(R.id.tv_name)
        tv_region = rootView.findViewById(R.id.tv_region)
        tv_population = rootView.findViewById(R.id.tv_population)
        tv_capital = rootView.findViewById(R.id.tv_capital)
        tv_cioc = rootView.findViewById(R.id.tv_cioc)
        recyclerView = rootView.findViewById(R.id.rl_language)

        setupViews()

        return rootView;
    }

    private fun setupViews() {
        GlideToVectorYou
            .init()
            .with(this.context)
            //.setPlaceHolder(R.drawable.loading, R.drawable.actual)
            .load(Uri.parse(country?.getFlag()), iv_flag)

        tv_name!!.text = country!!.getName()
        tv_region!!.text = country!!.getRegion()
        tv_population!!.text = String.valueOf(country!!.getPopulation())
        tv_capital!!.text = country!!.getCapital()
        tv_cioc!!.text = country!!.getCioc()

        recyclerView!!.layoutManager = LinearLayoutManager(activity)
        languageAdapter = LanguageAdapter(this.requireContext())
        recyclerView!!.adapter = languageAdapter

        languageAdapter!!.setListItems(country!!.getLanguages())
    }
}