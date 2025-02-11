package com.example.leaveit.presentation.placeview.place.showplaceview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaveit.databinding.FragmentShowplaceviewBinding
import com.example.leaveit.presentation.placeview.place.selectregionview.SelectRegionView
import com.example.leaveit.utill.sharedpreferences.sharedPreferencesUtill
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShowPlaceView : Fragment() {
    private lateinit var binding: FragmentShowplaceviewBinding
    private lateinit var tourAdapter: ShowPlaceViewRecyclerViewAdapter
    private lateinit var cultureAdapter: ShowPlaceViewRecyclerViewAdapter
    private lateinit var festivalAdapter: ShowPlaceViewRecyclerViewAdapter
    private lateinit var searchAdapter : ShowPlaceViewSearchPagingRecyclerViewAdapter
    private val viewModel: ShowPlaceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowplaceviewBinding.inflate(getLayoutInflater())
        initAdapter()
        initSearchView()
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
        binding.searchDataView.layoutManager =
            LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        tourAdapter = ShowPlaceViewRecyclerViewAdapter({ contentTypeId,isSearch ->
            // SelectRegionView로 이동
            moveToSelectRegionView(requireContext(), contentTypeId,isSearch)
        },false)
        festivalAdapter = ShowPlaceViewRecyclerViewAdapter ({ contentTypeId,isSearch ->
            // SelectRegionView로 이동
            moveToSelectRegionView(requireContext(), contentTypeId,isSearch)
        },false)
        cultureAdapter = ShowPlaceViewRecyclerViewAdapter ({ contentTypeId,isSearch ->
            // SelectRegionView로 이동
            moveToSelectRegionView(requireContext(), contentTypeId,isSearch)
        },false)
        searchAdapter = ShowPlaceViewSearchPagingRecyclerViewAdapter({ contentTypeId,isSearch ->
            // SelectRegionView로 이동
            moveToSelectRegionView(requireContext(), contentTypeId,isSearch)
        },true)


        binding.tourAttraction.adapter = tourAdapter
        binding.culture.adapter = cultureAdapter
        binding.festival.adapter = festivalAdapter
        binding.searchDataView.adapter = searchAdapter
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

        viewModel.searchData.observe(this){
            lifecycleScope.launch {
                searchAdapter.submitData(it)
            }
        }
    }

    private fun moveToSelectRegionView(context: Context, contentTypeId: String,isSearch : Boolean) {
        if(isSearch != true){
            val downloadIntent = Intent(context, SelectRegionView::class.java)

            // 선택된 카테고리의 contentTypeId 넘기기
            downloadIntent.putExtra("contentTypeId", contentTypeId)

            // SharedPreferences에 선택한 데이터 넣기
            sharedPreferencesUtill.setData(context, "showPlaceViewContentTypeId", contentTypeId)
            startActivity(downloadIntent)
        }else{
            //TODO 검색 아이템 클릭 리스너이므로 바로 Deatil로 넘어가기
        }
    }

    private fun initSearchView() {

        binding.searchView.setOnQueryTextFocusChangeListener { _, hasfocus ->
            if (hasfocus) {
                binding.viewScrollView.visibility = View.GONE

                // viewScrollView가 사라지면서 searchView의 bottom_top의
                // view가 사라지므로 이를 대체하는 뷰 설정
                val searchView = binding.searchView
                val params = searchView.layoutParams as ConstraintLayout.LayoutParams
                params.bottomToTop = binding.searchParentView.id
                searchView.layoutParams = params

                binding.searchParentView.visibility = View.VISIBLE
            }

        }

        binding.searchView.setOnCloseListener {
            // searchView의 X 버튼 누르면 기존 창으로 다시 복귀

            binding.searchParentView.visibility = View.GONE

            val searchView = binding.searchView
            val params = searchView.layoutParams as ConstraintLayout.LayoutParams
            params.bottomToTop = binding.viewScrollView.id
            searchView.layoutParams = params

            binding.viewScrollView.visibility = View.VISIBLE

            lifecycleScope.launch {
                searchAdapter.submitData(PagingData.empty())
            }

            false

        }


        binding.searchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // searchView의 검색버튼 눌렀을 때 리스너
                // 사용안할거라 false
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null){
                    viewModel.getSearchData(newText)
                }

                return true
            }

        })
    }

    private fun setTopAppBarText(text: String) {
        viewModel.setTopAppBarTitleText(text)
    }

    companion object {
        const val TAG = "ShowPlaceView"
    }

}