package com.example.retrofit2_test

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    //一个可以被改变数据的LiveData
    val errorMessage = MutableLiveData<String>()

    //一个可以被改变数据的LiveData
    val movieLiveData = MutableLiveData<List<Movie>>()

    //job是CoroutineScope launch 的一个进程变量。
    var job:Job? = null

    //有意外发生时的对应线程
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    val loading = MutableLiveData<Boolean>()

    fun getAllMovies(){
        //因为是需要获取数据，所以这里要用Dispatchers.IO。exceptionHandler，就是有意外发生时的对应线程
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {

            val response = mainRepository.getAllMoviesData()

            //因为这里是把获取数据后，返回到主线程上，所以这里用上Dispatchers.Main
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    movieLiveData.postValue(response.body())
                    loading.value = false
                }else{
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}