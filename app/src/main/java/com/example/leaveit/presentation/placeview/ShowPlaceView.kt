package com.example.leaveit.presentation.placeview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaveit.databinding.FragmentShowplaceviewBinding

class ShowPlaceView : Fragment(){
    private lateinit var binding : FragmentShowplaceviewBinding
    private lateinit var adapter: ShowPlaceViewRecyclerViewAdapter
    private val viewModel: ShowPlaceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowplaceviewBinding.inflate(getLayoutInflater())
        initAdapter()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.testGetValue()

        viewModel.currentData.observe(this){
            adapter.submitList(it)
        }

    }

    private fun initAdapter(){
        binding.tourAttraction.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        adapter = ShowPlaceViewRecyclerViewAdapter()
        binding.tourAttraction.adapter = adapter
    }
}