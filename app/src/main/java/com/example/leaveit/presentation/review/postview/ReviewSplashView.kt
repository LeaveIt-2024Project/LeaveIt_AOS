package com.example.leaveit.presentation.review.postview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.leaveit.databinding.FragmentReviewsplashviewBinding

class ReviewSplashView : Fragment() {
    lateinit var binding: FragmentReviewsplashviewBinding
    private val viewModel: PostReviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewsplashviewBinding.inflate(layoutInflater)
        changeTopText("")
        return binding.root
    }

    fun changeTopText(text: String) {
        viewModel.setTopAppBarTitleText(text)
    }
}