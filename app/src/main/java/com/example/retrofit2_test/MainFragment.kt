package com.example.retrofit2_test

import android.view.View
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit2_test.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    lateinit var viewModel: MainViewModel
    private val adapter = MovieAdapter()

    companion object{
        private val TAG = MainActivity::class.java.simpleName
    }

    private var binding:FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //数据绑定FragmentMain
        binding = DataBindingUtil.bind(view)

        //初始化一个Retrofit服务对象
        val retrofitService = RetrofitService.getInstance()

        //把服务器导入到MainRepository资源类中
        val mainRepository = MainRepository(retrofitService)

        binding!!.recyclerview.adapter = adapter

        //初始化viewModel。
        //get(MainViewModel::class.java)，是说明对用哪个ViewModel。现在就是对应MainViewModel这个ViewModel
        //MyViewModelFactory(mainRepository)，是说明MainViewModel中，所导入的数据类MainRepository，是对用哪个类的。
        //MyViewModelFactory类中，就说明了是对应MainViewModel(this.repository)类的。
        //因为MainViewModel生成时，有应对的变量MainRepository，所以要在这里说明要插入哪个变量。
        //现在其实就是生成时，把MainViewModel(mainRepository)，传到viewModel中。
        viewModel = ViewModelProvider(this,MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)

        //observe观察。这里意思就是movieLiveData被观察中，一旦数据有变，就会做出相对应的操作
        viewModel.movieLiveData.observe(this,{
            adapter.setMovie(it)
        })

        //观察errorMessage的动态。
        viewModel.errorMessage.observe(this,{
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

        //观察loading的动态。
        viewModel.loading.observe(this,{
            if (it){
                binding!!.progressDialog.visibility = View.VISIBLE
            }else{
                binding!!.progressDialog.visibility = View.GONE
            }
        })

        //因为上面已经封装好了。这里运用函数里面的线程处理
        viewModel.getAllMovies()
    }


}