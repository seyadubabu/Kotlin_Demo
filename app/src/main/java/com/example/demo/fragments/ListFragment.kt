package com.example.demo.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.R
import com.example.demo.adapters.CountryAdapter
import com.example.demo.api.ApiClient
import com.example.demo.api.ApiInterface
import com.example.demo.models.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ListFragment: Fragment() {

    lateinit var et_search: EditText
    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var tv_error: TextView

    var apiInterface: ApiInterface? = null
    var adapter: CountryAdapter? = null
    var countryArrayList: ArrayList<Country> = ArrayList<Country>()

    companion object{
        fun newInstance(): ListFragment{
            return ListFragment();
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View? = inflater.inflate(R.layout.fragment_list, container, false);

        et_search = rootView!!.findViewById(R.id.et_search)
        recyclerView = rootView.findViewById(R.id.recycler)
        progressBar = rootView.findViewById(R.id.progress)
        tv_error = rootView.findViewById(R.id.tv_error)

        return rootView;
        //return inflater.inflate(R.layout.fragment_list, container, false);
    }

    override fun onStart() {
        super.onStart()

        et_search.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())
            }
        })

        apiInterface = ApiClient.getApiClient()?.create(ApiInterface::class.java)
        callApi();
    }

    private fun filter(s: String) {
        val filterCountries = ArrayList<Country>()
        for (country in countryArrayList) {
            //if the existing elements contains the search input
            if (country.getName()!!.toLowerCase().contains(s.toLowerCase())) {
                //adding the element to filtered list
                filterCountries.add(country)
            }
        }

        adapter?.filterList(filterCountries)
    }

    private fun callApi() {
        apiInterface?.getAllCountries()
            ?.enqueue(object : Callback<ArrayList<Country>> {
                /*override fun onResponse(
                    call: Call<ArrayList<Country?>>,
                    response: Response<ArrayList<Country>>
                ) {
                    countryArrayList = response.body()!!
                    progressBar!!.visibility = View.GONE
                    et_search!!.visibility = View.VISIBLE
                    recyclerView!!.visibility = View.VISIBLE
                    setupList()
                }

                override fun onFailure(
                    call: Call<ArrayList<Country?>>,
                    t: Throwable
                ) {
                    progressBar!!.visibility = View.GONE
                    tv_error!!.text = t.message
                    tv_error!!.visibility = View.VISIBLE
                }*/

                override fun onResponse(
                    call: Call<ArrayList<Country>>,
                    response: Response<ArrayList<Country>>
                ) {
                    countryArrayList = response.body()!!
                    progressBar!!.visibility = View.GONE
                    et_search!!.visibility = View.VISIBLE
                    recyclerView!!.visibility = View.VISIBLE
                    setupList()
                }

                override fun onFailure(call: Call<ArrayList<Country>>, t: Throwable) {
                    progressBar!!.visibility = View.GONE
                    tv_error!!.text = t.message
                    tv_error!!.visibility = View.VISIBLE
                }
            })
    }

    private fun setupList() {
        Log.e("list-sice --->", countryArrayList.size.toString())
        recyclerView!!.layoutManager = LinearLayoutManager(activity)
        adapter = CountryAdapter(this.requireContext())
        recyclerView!!.adapter = adapter

        adapter!!.setCountryListItems(countryArrayList);
    }
}
