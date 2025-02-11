package com.example.leaveit.presentation.placeview.place.showplaceview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaveit.databinding.FragmentShowplaceviewBinding
import com.example.leaveit.presentation.placeview.place.selectregionview.SelectRegionView
import com.example.leaveit.utill.sharedpreferences.sharedPreferencesUtill
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowPlaceView : Fragment() {
    private lateinit var binding: FragmentShowplaceviewBinding
    private lateinit var tourAdapter: ShowPlaceViewRecyclerViewAdapter
    private lateinit var cultureAdapter: ShowPlaceViewRecyclerViewAdapter
    private lateinit var festivalAdapter: ShowPlaceViewRecyclerViewAdapter
    private val viewModel: ShowPlaceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowplaceviewBinding.inflate(getLayoutInflater())
        initAdapter()
        setTopAppBarText("관광지를 선택해주세요")

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeData()

    }

    private fun initAdapter() {
        binding.tourAttraction.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.culture.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.festival.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        tourAdapter = ShowPlaceViewRecyclerViewAdapter { contentTypeId ->
            // SelectRegionView로 이동
            moveToSelectRegionView(requireContext(), contentTypeId)
        }
        festivalAdapter = ShowPlaceViewRecyclerViewAdapter { contentTypeId ->
            //  SelectRegionView로 이동
            moveToSelectRegionView(requireContext(),contentTypeId)
        }
        cultureAdapter = ShowPlaceViewRecyclerViewAdapter { contentTypeId ->
            // SelectRegionView로 이동
            moveToSelectRegionView(requireContext(),contentTypeId)
        }


        binding.tourAttraction.adapter = tourAdapter
        binding.culture.adapter = cultureAdapter
        binding.festival.adapter = festivalAdapter
    }

    private fun observeData() {
        viewModel.categoryData() // 관광지 카테고리 초기화


        // 옵저버 패턴으로 데이터 변경 감지 후 각 어댑터에 데이터 넣기
        viewModel.tourAttractionData.observe(this) {
            tourAdapter.submitList(it)
        }

        viewModel.cultureData.observe(this) {
            cultureAdapter.submitList(it)
        }

        viewModel.festivalData.observe(this) {
            festivalAdapter.submitList(it)
        }
    }

    private fun moveToSelectRegionView(context: Context,contentTypeId : String) {
        val downloadIntent = Intent(context, SelectRegionView::class.java)

        // 선택된 카테고리의 contentTypeId 넘기기
        downloadIntent.putExtra("contentTypeId",contentTypeId)

        // SharedPreferences에 선택한 데이터 넣기
        sharedPreferencesUtill.setData(context,"showPlaceViewContentTypeId",contentTypeId)
        startActivity(downloadIntent)
    }

    private fun setTopAppBarText(text: String) {
        viewModel.setTopAppBarTitleText(text)
    }

}