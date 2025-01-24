package com.example.leaveit.presentation.placeview.restraunt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.leaveit.databinding.FragmentDetailrestrauntviewBinding
import com.example.leaveit.presentation.placeview.place.selectregionview.SelectRegionViewModel

class DetailRestrauntView : Fragment() {
    lateinit var binding: FragmentDetailrestrauntviewBinding
    private val rootViewModel: SelectRegionViewModel by activityViewModels()
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

        initView()
        initTopAppBarText()

    }

    private fun initView(){
        val data = viewModel.selectRestrauntData.value

        // 이미지 설정
        Glide.with(binding.root)
            .load(data!!.image)
            .fitCenter()
            .into(binding.placeImage)



    }

    private fun initTopAppBarText(){
        rootViewModel.setTopTapContent(viewModel.selectRestrauntData.value!!.title)
    }

    companion object {
        const val TAG = "DetailRestrauntView"
    }
}