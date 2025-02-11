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
import kotlinx.coroutines.runBlocking

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
        observeAllPlaceData()
        handleTabLayout()
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        // 선택한 데이터 받아오기
        val data = sharedPreferencesUtill.getData(requireContext(),"showPlaceViewContentTypeId")

        viewModel.getAllPlaceData(data)
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
                        observeAllPlaceData()
                    }

                    1 -> {
                        Log.d(TAG, "${position} 위치")
                        callSortByPlaceData()

                    }

                    2 -> {
                        Log.d(TAG, "${position} 위치")
                    }

                    3 -> {
                        Log.d(TAG, "${position} 위치")
                    }

                    4 -> {
                        Log.d(TAG, "${position} 위치")
                    }

                    5 -> {
                        Log.d(TAG, "${position} 위치")
                    }

                    6 -> {
                        Log.d(TAG, "${position} 위치")
                    }

                    7 -> {
                        Log.d(TAG, "${position} 위치")
                    }

                    8 -> {
                        Log.d(TAG, "${position} 위치")
                    }

                    9 -> {
                        Log.d(TAG, "${position} 위치")
                    }

                    10 -> {
                        Log.d(TAG, "${position} 위치")
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


    private fun observeAllPlaceData(){
        viewModel.placeData.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                it.map {
                    Log.d(TAG,it.title)
                }
                adapter.submitData(it)
            }
        }
    }

    private fun callSortByPlaceData(){
        runBlocking {
            val firstJob = launch {
                viewModel.getSortByRigionData("산","1")
            }
            firstJob.join()
            observeAllPlaceData()
        }
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

    companion object {
        val TAG = "SortRegionFragment"
    }
}