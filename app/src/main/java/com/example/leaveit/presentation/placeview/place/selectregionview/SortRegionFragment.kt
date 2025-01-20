package com.example.leaveit.presentation.placeview.place.selectregionview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaveit.R
import com.example.leaveit.databinding.FragmentSortregionBinding
import com.example.leaveit.presentation.placeview.place.detailplaceview.DetailPlaceView
import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SortRegionFragment : Fragment() {
    private lateinit var binding: FragmentSortregionBinding
    private lateinit var adapter: SelectRegionRecyclerAdapter
    private val viewModel: SelectRegionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSortregionBinding.inflate(layoutInflater)

        initAdapter()
        handleTabLayout()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.setIsMoveDetailView(true)
        viewModel.setTopTapContent("카테고리를 선택하세요")
    }


    private fun initAdapter() {

        //리사이클러뷰에 레이아웃매니저 설정
        binding.sortRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )

        val testData = listOf(
            SelectRegionModel(contentId = "2733967" ,
                title =  "가회동 성당",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/09/3303909_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            )
        )

        adapter = SelectRegionRecyclerAdapter(moveToPlace = {
            viewModel.setContentId(it.contentId)
            viewModel.setTopTapContent(it.title)
            viewModel.setMapX(it.mapx)
            viewModel.setMapY(it.mapy)
            viewModel.setPlaceLocation(it.mapy,it.mapx)
            viewModel.setPlaceImage(it.image)
            viewModel.setAddressInfo(it.address)

            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(
                    R.anim.fade_in_review_splash,
                    R.anim.fade_out_review_splash,
                    R.anim.fade_in_review_splash,
                    R.anim.fade_out_review_splash
                )
                .replace(
                    R.id.selectregion_fragment_container,
                    DetailPlaceView()
                )
                .commit()
        })
        adapter.submitList(testData)
        binding.sortRecyclerView.adapter = adapter
    }

    private fun handleTabLayout() {
        val testData = listOf(
            SelectRegionModel(contentId = "2733967" ,
                title =  "가회동 성당",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/09/3303909_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
                )
        )
        binding.TabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position // 현재 클릭한 탭의 포지션 가져오기

                when (position) { // 포지션 별 분기
                    0 -> {
                        Log.d(TAG, "${position} 위치")
                        adapter.submitList(testData)
                    }

                    1 -> {
                        Log.d(TAG, "${position} 위치")
                        adapter.submitList(testData)
                    }

                    2 -> {
                        Log.d(TAG, "${position} 위치")
                        adapter.submitList(testData)
                    }

                    3 -> {
                        Log.d(TAG, "${position} 위치")
                        adapter.submitList(testData)
                    }

                    4 -> {
                        Log.d(TAG, "${position} 위치")
                        adapter.submitList(testData)
                    }

                    5 -> {
                        Log.d(TAG, "${position} 위치")
                        adapter.submitList(testData)
                    }

                    6 -> {
                        Log.d(TAG, "${position} 위치")
                        adapter.submitList(testData)
                    }

                    7 -> {
                        Log.d(TAG, "${position} 위치")
                        adapter.submitList(testData)
                    }

                    8 -> {
                        Log.d(TAG, "${position} 위치")
                        adapter.submitList(testData)
                    }

                    9 -> {
                        Log.d(TAG, "${position} 위치")
                        adapter.submitList(testData)
                    }

                    10 -> {
                        Log.d(TAG, "${position} 위치")
                        adapter.submitList(testData)
                    }
                }

                Log.d(SelectRegionView.TAG, "${position}이 선택되었습니다")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }

    companion object {
        val TAG = "SortRegionFragment"
    }
}