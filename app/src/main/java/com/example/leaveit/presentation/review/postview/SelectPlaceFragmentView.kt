package com.example.leaveit.presentation.review.postview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaveit.databinding.FragmentSortregionBinding
import com.example.leaveit.presentation.placeview.place.selectregionview.SelectRegionView
import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModel
import com.example.leaveit.presentation.review.postview.adapter.SelectPlaceAdapter
import com.example.leaveit.presentation.review.postview.adapter.SelectPlaceBottomSheet
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectPlaceFragmentView  : Fragment() {
    private lateinit var binding : FragmentSortregionBinding
    private lateinit var adapter: SelectPlaceAdapter
    private val viewModel: PostReviewViewModel by activityViewModels()

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

    private fun initAdapter(){

        //리사이클러뷰에 레이아웃매니저 설정
        binding.sortRecyclerView.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.VERTICAL, false)

        val testData = listOf(
            SelectRegionModel(contentId = "1" , title =  "서울 타워", contentTypeId = "1", areaCode = 2,image = "http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg"),
            SelectRegionModel(contentId = "1" , title =  "서울 타워", contentTypeId = "1",areaCode = 2,image = "http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg"),
        )

        adapter = SelectPlaceAdapter{
            Log.d(TAG,"선택된 지역 이름 : $it")

            //뷰모델에 선택된 지역 넣기
            setRegion(value = it)

            val modalBottomSheet = SelectPlaceBottomSheet()
            modalBottomSheet.show(parentFragmentManager, SelectPlaceBottomSheet.TAG)
        }
        adapter.submitList(testData)
        binding.sortRecyclerView.adapter = adapter
    }

    private fun handleTabLayout(){
        val testData = listOf(
            SelectRegionModel(contentId = "1" , title =  "서울 타워", contentTypeId = "1", areaCode = 2,image = "http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg"),
            SelectRegionModel(contentId = "1" , title =  "서울 타워", contentTypeId = "1",areaCode = 2,image = "http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg"),
        )
        binding.TabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position // 현재 클릭한 탭의 포지션 가져오기

                when(position){ // 포지션 별 분기
                    0 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(testData)}
                    1 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(testData)}
                    2 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(testData)}
                    3 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(testData)}
                    4 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(testData)}
                    5 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(testData)}
                    6 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(testData)}
                    7 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(testData)}
                    8 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(testData)}
                    9 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(testData)}
                    10 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(testData)}
                }

                Log.d(SelectRegionView.TAG,"${position}이 선택되었습니다")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }

    fun setRegion(value : String){
        viewModel.setRegion(value)
    }
    companion object{
        val TAG = "SortRegionFragment"
    }
}