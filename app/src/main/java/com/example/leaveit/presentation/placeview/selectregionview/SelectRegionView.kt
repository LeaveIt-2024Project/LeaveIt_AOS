package com.example.leaveit.presentation.placeview.selectregionview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.leaveit.databinding.ActivitySelectregionviewBinding

class SelectRegionView : AppCompatActivity() {
    private lateinit var binding : ActivitySelectregionviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectregionviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val test = intent.getIntExtra("data",0)
        binding.testTextView.text = test.toString()
    }

    companion object{
       val TAG = "SelectRegionView"
    }
}