package com.example.retrofit2_test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit2_test.databinding.AdapterMovieBinding
import com.bumptech.glide.Glide

class MovieAdapter : RecyclerView.Adapter<MainViewHolder>() {

    //创建一个mutableList的数据包
    var movieList = mutableListOf<Movie>()

    //把movies赋值给movieList数据包中，并更新当前Adapter
    fun setMovie(movies: List<Movie>){
        this.movieList = movies.toMutableList()

        //notifyDataSetChanged()是很重要的函数，是Adapter的更新函数
        notifyDataSetChanged()
    }

    //初始化，并绑定
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    //各Adapter应对的前端操作。相当于Fragment的onViewCreated
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.name.text = movie.name

        //用上第三方的库Glide，导入画面
        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)
    }

    //当前Adapter中的显示数量
    override fun getItemCount(): Int {
        return movieList.size
    }

}

//说明class MovieAdapter : RecyclerView.Adapter<MainViewHolder>() 中引用的MainViewHolder是一个什么的类
class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

}