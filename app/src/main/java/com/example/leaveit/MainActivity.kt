package com.example.leaveit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leaveit.databinding.ActivityMainBinding
import com.example.leaveit.presentation.placeview.showplaceview.ShowPlaceView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container,ShowPlaceView())
                .commit()
        }
    }
}