package com.example.leaveit.presentation.review.postview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.leaveit.databinding.FragmentReviewsplashviewBinding

class ReviewSplashView : Fragment() {
    lateinit var binding: FragmentReviewsplashviewBinding
    private lateinit var viewModel: PostReviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewsplashviewBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity())[PostReviewViewModel::class.java]
        changeTopText("")
        return binding.root
    }

    fun changeTopText(text: String) {
        viewModel.setTopAppBarTitleText(text)
    }
}