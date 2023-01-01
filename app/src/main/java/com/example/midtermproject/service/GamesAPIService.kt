package com.example.midtermproject.service

import com.example.midtermproject.model.Game
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.core.Single

import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class GamesAPIService {


    private val BASE_URL  = "https://api.rawg.io/api/"


    private val api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(GamesAPI:: class.java)


    fun getData(): Single<Responses> {
        return api.getGames()

    }
}