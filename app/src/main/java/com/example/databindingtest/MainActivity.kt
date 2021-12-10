package com.example.databindingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databindingtest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //因为用上了DataBindingUtil，所以这里就是可以直接binding
        var binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main )

    }
}


