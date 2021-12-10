package com.example.databindingtest

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    //从"https://howtodoandroid.com/"中，获取的movielist.json数据，并封装成List<Movie>格式
    @GET("movielist.json")
    suspend fun getAllMovies(): Response<List<Movie>>

    companion object {
        //本类RetrofitService的一个静态对象，暂时是空的
        var retrofitService: RetrofitService? = null

        //创建本类RetrofitService的一个静态对象
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://howtodoandroid.com/")      //从哪个地址获取的
                    .addConverterFactory(GsonConverterFactory.create()) //Gson转换器
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)  //创建后，返回给静态对象中
            }
            return retrofitService!!       //返回给函数
        }
    }
}