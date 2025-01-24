package com.example.leaveit.presentation.placeview.restraunt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.leaveit.databinding.FragmentDetailrestrauntviewBinding

class DetailRestrauntView : Fragment() {
    lateinit var binding: FragmentDetailrestrauntviewBinding
    private val viewModel: RestrauntViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailrestrauntviewBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        const val TAG = "DetailRestrauntView"
    }
}