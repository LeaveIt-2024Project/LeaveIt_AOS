package com.example.leaveit.presentation.mainpageview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.leaveit.databinding.FragmentMainviewBinding
import com.example.leaveit.presentation.mainpageview.adapter.MainPageViewPagerAdapter
import com.example.leaveit.presentation.mainpageview.adapter.PreferRegionRecyclerViewAdapter
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPageView : Fragment() {

    private lateinit var binding: FragmentMainviewBinding
    private lateinit var mainAdapter: MainPageViewPagerAdapter
    private lateinit var preferRegionAdapter: PreferRegionRecyclerViewAdapter
    private val viewModel1: MainPageViewModel by viewModels()
    private val viewModel2: MainPreferRegionViewModel by viewModels()
    private val viewModel3: AppBarViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainviewBinding.inflate(inflater, container, false)
        initAdapter()
        syncViewPagerWithIndicator()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel1.loadPlaces()
        viewModel2.loadPreferRegions()
        observeData()
    }

    override fun onResume() {
        super.onResume()
        viewModel3.selectFragment("MainPage")
    } // 선택된 프레그먼트 알리기

    private fun initAdapter() {
        // RecyclerView에 사용할 어댑터 설정
        mainAdapter = MainPageViewPagerAdapter()
        binding.mainViewPager.adapter = mainAdapter

        binding.preferTop10Regions.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
        preferRegionAdapter = PreferRegionRecyclerViewAdapter()
        binding.preferTop10Regions.adapter = preferRegionAdapter
    }

    private fun observeData() {
        viewModel1.mainPageAttractionData.observe(viewLifecycleOwner, Observer {
            mainAdapter.submitList(it)
        })
        viewModel2.mainPreferRegionData.observe(viewLifecycleOwner, Observer {
            preferRegionAdapter.submitList(it)
        })
    }

    private fun syncViewPagerWithIndicator() {
        // SpringDotsIndicator와 ViewPager2 동기화
        val dotsIndicator: SpringDotsIndicator = binding.mainViewIndicator
        dotsIndicator.setViewPager2(binding.mainViewPager)
    }
}
