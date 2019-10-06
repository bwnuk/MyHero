package com.github.wnuk.myhero.infrastracture.services

import com.github.wnuk.myhero.infrastracture.ApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
       private const val BASE_URL = "https://rickandmortyapi.com/api/"

        fun getClient(): Retrofit {
            val okHttpClient = OkHttpClient.Builder().build()

            return Retrofit.Builder().client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }
}