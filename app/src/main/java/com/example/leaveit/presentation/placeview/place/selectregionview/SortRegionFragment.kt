package com.example.leaveit.presentation.placeview.place.selectregionview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaveit.R
import com.example.leaveit.databinding.FragmentSortregionBinding
import com.example.leaveit.presentation.placeview.place.detailview.DetailCultureView
import com.example.leaveit.presentation.placeview.place.detailview.DetailFestivalView
import com.example.leaveit.presentation.placeview.place.detailview.DetailPlaceView
import com.example.leaveit.utill.sharedpreferences.sharedPreferencesUtill
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SortRegionFragment : Fragment() {
    private lateinit var binding: FragmentSortregionBinding
    private lateinit var adapter: SelectRegionRecyclerAdapter
    private val viewModel: SelectRegionViewModel by activityViewModels()
    private lateinit var category: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSortregionBinding.inflate(layoutInflater)
        category = getCategoryData()

        initAdapter()
        handleTabLayout()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        observeAllPlaceData()
        viewModel.getAllPlaceData(category)
        viewModel.setIsMoveDetailView(true)
        viewModel.setTopTapContent("카테고리를 선택하세요")
    }


    private fun initAdapter() {

        //리사이클러뷰에 레이아웃매니저 설정
        binding.sortRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )

        adapter = SelectRegionRecyclerAdapter(moveToPlace = {
            viewModel.setContentId(it.contentId)
            viewModel.setPlaceTitle(it.title)
            viewModel.setMapX(it.mapx)
            viewModel.setMapY(it.mapy)
            viewModel.setPlaceLocation(it.mapy, it.mapx)
            viewModel.setPlaceImage(it.image)
            viewModel.setAddressInfo(it.address)
            viewModel.setContentTypeId(it.contentTypeId)

            val fragment = branchFragment(it.contentTypeId)

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
                    fragment
                )
                .commit()
        })
        binding.sortRecyclerView.adapter = adapter
    }

    private fun handleTabLayout() {
        binding.TabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position // 현재 클릭한 탭의 포지션 가져오기

                when (position) { // 포지션 별 분기
                    0 -> {
                        //TODO 카테고리 선택에 해당하는 API 호출 후
                        // 비동기로 API 호출이 끝나면 adapter.submitList() 호출하게 만들기
                        Log.d(TAG, "${position} 위치")
                        callAllPlaceData(category = category)
                    }

                    1 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData(category = category, "1")
                    }

                    2 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData(category = category, "2")
                    }

                    3 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData(category = category, "31")
                    }

                    4 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData(category = category, "32")
                    }

                    5 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData(category = category, "3")
                    }

                    6 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData(category = category, "4")
                    }

                    7 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData(category = category, "5")
                    }

                    8 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData(category = category, "6")
                    }

                    9 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData(category = category, "7")
                    }

                    10 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData(category = category, "8")
                    }
                    11 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData(category = category, "33")
                    }
                    12 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData(category = category, "34")
                    }
                    13 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData(category = category, "35")
                    }
                    14 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData(category = category, "36")
                    }
                    15 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData(category = category, "37")
                    }
                    16 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData(category = category, "38")
                    }
                    17 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData(category = category, "39")
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


    private fun observeAllPlaceData() {
        viewModel.placeData.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                it.map {
                    Log.d(TAG, it.title)
                }
                adapter.submitData(it)
            }
        }
    }

    private fun callSortByPlaceData(category: String, areaCode: String) {
        viewModel.getSortByRigionData(category, areaCode) // 데이터 호출
    }

    private fun callAllPlaceData(category: String) {
        viewModel.getAllPlaceData(category) // 데이터 호출
    }


    private fun branchFragment(value: String): Fragment {
        var fragment = Fragment()

        if (value == "12") {
            fragment = DetailPlaceView()
        } else if (value == "14") {
            fragment = DetailCultureView()
        } else if (value == "15") {
            fragment = DetailFestivalView()
        }

        return fragment
    }

    private fun getCategoryData(): String {
        val data = sharedPreferencesUtill.getData(requireContext(), "showPlaceViewContentTypeId")
        return data
    }

    companion object {
        val TAG = "SortRegionFragment"
    }
}