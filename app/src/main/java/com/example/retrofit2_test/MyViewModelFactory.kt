package com.example.retrofit2_test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

//ViewModelFactory，就是ViewModel的建造是需要详细说明的工厂
//MyViewModelFactory创建时，就是要引入MainRepository，说明时用上这个仓库。并把这个仓库的数据导入到MainViewModel中。
class MyViewModelFactory (private val mainRepository: MainRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(repositoryClass: Class<T>): T {

        //这里检测一下，当前过来的repositoryClass，repositoryClass是否可转让自MainViewModel。
        //因为MainViewModel需要mainRepository: MainRepository，这个repositoryClass
        return if (repositoryClass.isAssignableFrom(MainViewModel::class.java)) {

            //检测成功后，就把mainRepository传到MainViewModel函数中，创建一个MainViewModel对象
            //因为 T : ViewModel。所以最后要再把MainViewModel 转到ViewModel类中：as T
            MainViewModel(this.mainRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}