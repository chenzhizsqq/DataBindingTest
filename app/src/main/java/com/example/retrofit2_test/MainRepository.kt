package com.example.retrofit2_test

class MainRepository constructor(private val retrofitService: RetrofitService) {

    //从retrofit服务中，获取数据。因为这里是获取数据，所以用上suspend表示，说明整个函数可以延迟去进行。
    suspend fun getAllMoviesData() = retrofitService.getAllMovies()

}