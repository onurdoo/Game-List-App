package com.example.midtermproject.service

import com.example.midtermproject.model.Game
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GamesAPI {
    //GET,  POST

    //BASE-URL https://api.rawg.io/api/
    @GET("games?key=3be8af6ebf124ffe81d90f514e59856c")
     fun getGames(): Single<Responses>
}

