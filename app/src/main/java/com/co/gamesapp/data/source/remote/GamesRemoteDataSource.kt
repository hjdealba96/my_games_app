package com.co.gamesapp.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.co.gamesapp.data.Game
import com.co.gamesapp.data.source.GamesDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GamesRemoteDataSource : GamesDataSource {

    private val retrofitClient =
        RetrofitClient.getInstance("https://parseapi.back4app.com/", 30L)?.create(GamesServices::class.java)

    override fun getAllGames(): LiveData<List<Game>> {
        val data = MutableLiveData<List<Game>>()
        retrofitClient?.getAllGames()?.enqueue(object : Callback<GameResponseBody> {
            override fun onFailure(call: Call<GameResponseBody>, error: Throwable) {
            }

            override fun onResponse(call: Call<GameResponseBody>, response: Response<GameResponseBody>) {
                when (response.code()) {
                    200 -> {
                        val body = response.body()
                        if (body?.results != null) {
                            data.value = body.results
                        } else {
                            
                        }
                    }
                    else -> {
                    }
                }
            }
        })
        return data
    }

}