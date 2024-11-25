package com.example.leaveit.presentation.placeview.showplaceview

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaveit.databinding.FragmentShowplaceviewBinding
import com.example.leaveit.domain.testHiltClass
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShowPlaceView : Fragment(){
    private lateinit var binding : FragmentShowplaceviewBinding
    private lateinit var tourAdapter: ShowPlaceViewRecyclerViewAdapter
    private lateinit var cultureAdapter : ShowPlaceViewRecyclerViewAdapter
    private lateinit var festivalAdapter : ShowPlaceViewRecyclerViewAdapter
    private val viewModel: ShowPlaceViewModel by viewModels()

    @Inject
    lateinit var testText : testHiltClass


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowplaceviewBinding.inflate(getLayoutInflater())
        binding.firstcatTextView.text = testText.toString()
        initAdapter()


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeData()


    }

    private fun initAdapter(){
        binding.tourAttraction.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.culture.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.festival.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        tourAdapter = ShowPlaceViewRecyclerViewAdapter{contentId ->
            // 클릭 이벤트 처리

        }
        festivalAdapter = ShowPlaceViewRecyclerViewAdapter{contentId ->

        }
        cultureAdapter = ShowPlaceViewRecyclerViewAdapter{contentId ->

        }


        binding.tourAttraction.adapter = tourAdapter
        binding.culture.adapter = cultureAdapter
        binding.festival.adapter = festivalAdapter
    }

    private fun observeData(){
        viewModel.testGetValue() // 테스트 API 호출


        // 옵저버 패턴으로 데이터 변경 감지 후 각 어댑터에 데이터 넣기
        viewModel.tourAttractionData.observe(this){
            tourAdapter.submitList(it)
        }

        viewModel.cultureData.observe(this){
            cultureAdapter.submitList(it)
        }

        viewModel.festivalData.observe(this){
            festivalAdapter.submitList(it)
        }
    }
}