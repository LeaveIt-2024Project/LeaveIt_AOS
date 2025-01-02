package com.example.leaveit.presentation.placeview.place.detailplaceview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.leaveit.databinding.FragmentDetailplaceviewBinding
import com.example.leaveit.presentation.placeview.place.selectregionview.SelectRegionViewModel

class DetailPlaceView : Fragment() {
    private lateinit var binding : FragmentDetailplaceviewBinding
    private val viewModel : SelectRegionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailplaceviewBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,viewModel.placeContentId.value.toString())
    }


    companion object{
        const val TAG = "DetailPlaceView"
    }
}