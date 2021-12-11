package com.example.retrofit2_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.retrofit2_test.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity(), View.OnClickListener {
    val TAG = "MenuActivity"
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding.retrofit2Mvvm.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.retrofit2_mvvm -> {
                val intent =
                    Intent(this@MenuActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}