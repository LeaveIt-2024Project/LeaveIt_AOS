package com.example.leaveit

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.leaveit.databinding.ActivityMainBinding
import com.example.leaveit.presentation.placeview.place.showplaceview.ShowPlaceView
import com.example.leaveit.presentation.placeview.place.showplaceview.ShowPlaceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: ShowPlaceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)
        changeTopAppText()
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, ShowPlaceView())
                .commit()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
    private fun changeTopAppText() {
        viewModel.topAppBarText.observe(this) {
            binding.topAppBar.title = it
        }
    }

    companion object {
        val TAG = "MainActivity"
    }
}