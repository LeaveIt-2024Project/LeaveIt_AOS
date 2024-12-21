package com.example.leaveit.presentation.review

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaveit.R
import com.example.leaveit.databinding.FragmentReviewmainBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReviewMainView : Fragment() {
    private lateinit var binding: FragmentReviewmainBinding
    private lateinit var adapter: ReviewRecyclerViewAdapter
    private val viewModel: ReviewViewModel by viewModels()
    private var regionCode: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val selectedRegion = arguments?.getString("region")
        regionCode = viewModel.convertStrRegionCodeToInt(selectedRegion.toString())

        // 바인딩 초기화
        binding = FragmentReviewmainBinding.inflate(layoutInflater)

        // 어댑터 초기화
        initAdapter(regionCode)
        initTopBar()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * flow로 데이터를 받아서 UI에서 쓸거면
         * 코루틴 스코프 안에서 옵저빙, 함수호출을 해야 오류 안생김
         * 여기서는 코루틴 스코핑없이 호출했다가 비동기적으로 함수가 호출되는 바람에
         * usecase 의존성이 늦게 호출되서 초기화가 되지 않은 오류가 발생했었음
         */
        Log.d(TAG, regionCode.toString())

        // TapBar 초기화
        initCategoryTopBar(regionCode)
    }

    private fun initAdapter(initRegionCode: Int) {
        binding.reviewRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )

        // 좋아요 버튼 클릭 리스너
        adapter = ReviewRecyclerViewAdapter { reviewData, likeButton -> // reviewData : 좋아요 등록 데이터, likeBtn : 좋아요btn view
            lifecycleScope.launch(Dispatchers.IO) {
                if (reviewData.isUserLiked == true) {
                   viewModel.downLike(reviewData)
                } else if (reviewData.isUserLiked == false) {
                    viewModel.upLike(reviewData)
                }
            }
        }
        binding.reviewRecyclerView.adapter = adapter
        observerData(initRegionCode, 0)
    }

    fun observerData(regionCode: Int, selectedScreen: Int) {
        lifecycleScope.launch {
            when (selectedScreen) {
                0 -> {
                    lifecycleScope.launch {
                        viewModel.getReviewSortByRegion(regionCode).collectLatest { pagingData ->
                            adapter.submitData(pagingData)
                        }
                    }
                }

                1 -> {
                    lifecycleScope.launch {
                        viewModel.getReviewSortByRank(regionCode).collectLatest { pagingData ->
                            adapter.submitData(pagingData)
                        }
                    }
                }

                2 -> {
                    lifecycleScope.launch {
                        viewModel.getReviewSortByLike(regionCode).collectLatest { pagingData ->
                            adapter.submitData(pagingData)
                        }
                    }
                }
            }
        }
    }

    private fun initTopBar() {
        // setHasOptionsMenu(true) 이거 deprecated되서 MenuProvider 사용해서
        // TopAppBar 메뉴 변경해야돔
        // 참고 : https://developer.android.com/jetpack/androidx/releases/activity?hl=ko#1.4.0-alpha01

        requireActivity().title = "경기도"

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.reviewmainviewmenu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.reviewMain_gotoMyPage -> {
                        // 마이페이지 이동하는 인텐트 여기 설정
                        Log.d(TAG, "테스트")
                        true
                    }

                    R.id.reviewMain_addReviewBtn -> {
                        Log.d(TAG, "테스트")
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner)
    }

    private fun initCategoryTopBar(regionCode: Int) {

        binding.TabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position // 현재 클릭한 탭의 포지션 가져오기
                when (position) {
                    0 -> {
                        lifecycleScope.launch {
                            observerData(
                                regionCode = regionCode,
                                selectedScreen = 0
                            )
                        }
                    }

                    1 -> {
                        lifecycleScope.launch {
                            observerData(
                                regionCode = regionCode,
                                selectedScreen = 1
                            )
                        }
                    }

                    2 -> {
                        lifecycleScope.launch {
                            observerData(
                                regionCode = regionCode,
                                selectedScreen = 2
                            )
                        }
                    }
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

        })
    }

    companion object {
        val TAG = "ReviewMainView"
    }


}