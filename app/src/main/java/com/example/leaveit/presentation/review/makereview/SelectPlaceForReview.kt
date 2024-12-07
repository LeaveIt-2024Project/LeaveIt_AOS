package com.example.leaveit.presentation.review.makereview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.leaveit.databinding.FragmentSelectplaceforreviewBinding

class SelectPlaceForReview : Fragment(){

    lateinit var binding : FragmentSelectplaceforreviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectplaceforreviewBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)






    }

    private fun initMap(){

    }
}