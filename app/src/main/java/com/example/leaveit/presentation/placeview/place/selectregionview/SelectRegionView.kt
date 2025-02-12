package com.example.leaveit.presentation.placeview.place.selectregionview

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.leaveit.R
import com.example.leaveit.databinding.ActivitySelectregionviewBinding
import com.example.leaveit.presentation.placeview.place.detailview.DetailCultureView
import com.example.leaveit.presentation.placeview.place.detailview.DetailFestivalView
import com.example.leaveit.presentation.placeview.place.detailview.DetailPlaceView
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectRegionView : AppCompatActivity() {
    private lateinit var binding : ActivitySelectregionviewBinding
    private val viewModel: SelectRegionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isSearch = intent.getBooleanExtra("isSearch",false) // 검색으로 유입되는 화면인지 확인
        binding = ActivitySelectregionviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 처음 보여줄 프레그먼트 설정
        if(savedInstanceState == null){
            if(isSearch){
                //요구되는 데이터들 ShowPlaceViewActivity에서 Intent로 받은 후 RootViewModel에 저장

                val contentTypeId = intent.getStringExtra("searchContentTypeId")
                val contentId = intent.getStringExtra("searchContentId")
                val mapx = intent.getStringExtra("searchMapx")
                val mapy = intent.getStringExtra("searchMapy")
                val image = intent.getStringExtra("searchImage")
                val addr = intent.getStringExtra("searchAddr")
                val title = intent.getStringExtra("searchTitle")
                viewModel.setContentId(contentId.toString())
                viewModel.setMapX(mapx.toString())
                viewModel.setMapY(mapy.toString())
                viewModel.setPlaceImage(image.toString())
                viewModel.setAddressInfo(addr.toString())
                viewModel.setPlaceTitle(title.toString())

                // contentTypeId에 따라 이동
                when(contentTypeId){
                    "12" ->{ // 관광지
                        supportFragmentManager.beginTransaction()
                            .add(R.id.selectregion_fragment_container, DetailPlaceView())
                            .commit()
                    }
                    "14" ->{ // 문화시설
                        supportFragmentManager.beginTransaction()
                            .add(R.id.selectregion_fragment_container, DetailCultureView())
                            .commit()
                    }
                    "15" ->{ // 행사,축제,공연
                        supportFragmentManager.beginTransaction()
                            .add(R.id.selectregion_fragment_container, DetailFestivalView())
                            .commit()
                    }

                }


            }else{ // 검색으로 유입된게 아니라면,
                supportFragmentManager.beginTransaction()
                    .add(R.id.selectregion_fragment_container, SortRegionFragment())
                    .commit()
            }
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