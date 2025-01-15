package com.example.leaveit.presentation.review.postview

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.leaveit.R
import com.example.leaveit.databinding.ActivitySelectplaceviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectPlaceView : AppCompatActivity() {
    private lateinit var binding: ActivitySelectplaceviewBinding
    private val viewModel: PostReviewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectplaceviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        changeTopAppText()

        // 처음 보여줄 프레그먼트 설정
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.fade_in_review_splash,
                    R.anim.fade_out_review_splash,
                    R.anim.fade_in_review_splash,
                    R.anim.fade_out_review_splash
                )
                .add(R.id.review_fragment_container, SelectPlaceFragmentView())
                .commit()
        }

    }

    private fun changeTopAppText() {
        viewModel.topAppBarText.observe(this) {
            binding.topAppBar.title = it
            Log.d(TAG, it)
        }
    }


    companion object {
        val TAG = "SelectRegionView"
    }

}