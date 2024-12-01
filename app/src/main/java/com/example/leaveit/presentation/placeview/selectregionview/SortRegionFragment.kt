package com.example.leaveit.presentation.placeview.selectregionview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaveit.databinding.FragmentSortregionBinding
import com.example.leaveit.presentation.placeview.selectregionview.data.SelectRegionModel
import com.google.android.material.tabs.TabLayout

class SortRegionFragment : Fragment() {
    private lateinit var binding : FragmentSortregionBinding
    private lateinit var adapter: SelectRegionRecyclerAdapter

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

        val initdata = listOf(
            SelectRegionModel(contentId = "1" , title =  "서울 타워","http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg"),
            SelectRegionModel(contentId = "1" , title =  "서울 타워","http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg"),
            SelectRegionModel(contentId = "1" , title =  "서울 타워","http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg")
        )
        adapter = SelectRegionRecyclerAdapter()
        adapter.submitList(initdata)
        binding.sortRecyclerView.adapter = adapter
    }

    private fun handleTabLayout(){
        binding.TabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            val initdata1 = listOf(
                SelectRegionModel(contentId = "1" , title =  "서울 타워","http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg"),
                SelectRegionModel(contentId = "1" , title =  "서울 타워","http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg"),
                SelectRegionModel(contentId = "1" , title =  "서울 타워","http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg")
            )

            val initdata2 = listOf(
                SelectRegionModel(contentId = "1" , title =  "가나아트파크","http://tong.visitkorea.or.kr/cms/resource/46/2010746_image2_1.jpg"),
                SelectRegionModel(contentId = "1" , title =  "가나아트파크","http://tong.visitkorea.or.kr/cms/resource/46/2010746_image2_1.jpg"),
                SelectRegionModel(contentId = "1" , title =  "가나아트파크","http://tong.visitkorea.or.kr/cms/resource/46/2010746_image2_1.jpg")
            )

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position // 현재 클릭한 탭의 포지션 가져오기

                when(position){ // 포지션 별 분기
                    0 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(initdata1)}
                    1 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(initdata2)}
                    2 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(initdata1)}
                    3 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(initdata2)}
                    4 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(initdata1)}
                    5 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(initdata2)}
                    6 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(initdata1)}
                    7 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(initdata2)}
                    8 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(initdata1)}
                    9 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(initdata2)}
                    10 -> {
                        Log.d(TAG,"${position} 위치")
                        adapter.submitList(initdata1)}
                }

                Log.d(SelectRegionView.TAG,"${position}이 선택되었습니다")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }
    companion object{
        val TAG = "SortRegionFragment"
    }
}