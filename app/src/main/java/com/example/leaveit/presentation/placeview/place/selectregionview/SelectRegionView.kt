package com.example.leaveit.presentation.placeview.place.selectregionview

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.leaveit.R
import com.example.leaveit.databinding.ActivitySelectregionviewBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectRegionView : AppCompatActivity() {
    private lateinit var binding : ActivitySelectregionviewBinding
    private val viewModel: SelectRegionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectregionviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 처음 보여줄 프레그먼트 설정
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.selectregion_fragment_container, SortRegionFragment())
                .commit()
        }

        binding.topTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab?.position // 현재 클릭한 탭의 포지션 가져오기
                var select: Fragment? = null

                when(position){ // 포지션 별 분기
                    0 -> {
                        select = SortRegionFragment()
                    }
                    1 -> {select = SortPopularityFragment()
                    }
                }


                select?.let { // 선택한 프레그먼트로 액티비티의 프래그먼트 컨테이너 변경
                    supportFragmentManager.beginTransaction().replace(R.id.selectregion_fragment_container,
                        it
                    ).commit()
                }

                Log.d(TAG,"${position}이 선택되었습니다")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }

    override fun onStart() {
        super.onStart()
        observeIsMoveDetailFragment()
        observeTopTapContent()
    }


    private fun observeIsMoveDetailFragment(){
        viewModel.isMoveDetailView.observe(this){
            if(it != true){
                binding.topTabLayout.visibility = View.GONE
            }else{
                binding.topTabLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun observeTopTapContent(){
        viewModel.topTapContent.observe(this){
            binding.topAppBar.title = it
        }
    }

    companion object{
       val TAG = "SelectRegionView"
    }
}