package com.example.demo.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object{
        val BASE_URL = "https://restcountries.eu/rest/v2/"
        var retrofit: Retrofit? = null
        fun getApiClient(): Retrofit? {
            if (retrofit == null) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
                retrofit = Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build() //.client(client)
            }
            return retrofit
        }
    }
}