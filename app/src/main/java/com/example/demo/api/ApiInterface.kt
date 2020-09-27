package com.example.demo.api

import com.example.demo.models.Country
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("all")
    fun getAllCountries(): Call<ArrayList<Country>>


}