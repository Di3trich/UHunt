package org.diedrik.uhunt.webservice

import org.diedrik.uhunt.model.LiveEvent
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface UHuntService {
    companion object {
        val instance: UHuntService by lazy {
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://uhunt.onlinejudge.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            retrofit.create(UHuntService::class.java)
        }
    }

    @GET("api/uname2uid/{uname}")
    fun usernameToUserId(@Path("uname") username: String): Call<Int>

    @GET("api/poll/{poll-id}")
    fun eventPoll(@Path("poll-id") pollId: Long): Call<List<LiveEvent>>
}