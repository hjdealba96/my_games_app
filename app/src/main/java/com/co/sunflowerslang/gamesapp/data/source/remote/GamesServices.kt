package com.co.sunflowerslang.gamesapp.data.source.remote

import retrofit2.Call
import retrofit2.http.GET

interface GamesServices {
    @GET("classes/Product")
    fun getAllGames(): Call<GameResponseBody>
}