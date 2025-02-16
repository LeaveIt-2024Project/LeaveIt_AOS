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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaveit.databinding.FragmentShowplaceviewBinding
import com.example.leaveit.local.PlaceSearch.RecentSearchPlaceEntity
import com.example.leaveit.presentation.placeview.place.selectregionview.SelectRegionView
import com.example.leaveit.presentation.placeview.place.showplaceview.recyclerview.HotKeyWorldViewAdapter
import com.example.leaveit.presentation.placeview.place.showplaceview.recyclerview.RecentPlaceLogViewAdapter
import com.example.leaveit.presentation.placeview.place.showplaceview.recyclerview.ShowPlaceViewRecyclerViewAdapter
import com.example.leaveit.presentation.placeview.place.showplaceview.recyclerview.ShowPlaceViewSearchPagingRecyclerViewAdapter
import com.example.leaveit.utill.sharedpreferences.sharedPreferencesUtill
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShowPlaceView : Fragment() {
    private lateinit var binding: FragmentShowplaceviewBinding
    private lateinit var tourAdapter: ShowPlaceViewRecyclerViewAdapter
    private lateinit var cultureAdapter: ShowPlaceViewRecyclerViewAdapter
    private lateinit var festivalAdapter: ShowPlaceViewRecyclerViewAdapter
    private lateinit var searchAdapter: ShowPlaceViewSearchPagingRecyclerViewAdapter
    private lateinit var recentSearchAdapter: RecentPlaceLogViewAdapter
    private lateinit var hotKeyWordAdapter : HotKeyWorldViewAdapter

    private val viewModel: ShowPlaceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowplaceviewBinding.inflate(getLayoutInflater())
        initAdapter()
        initSearchView()
        initClickListener()
        setTopAppBarText("관광지를 선택해주세요")

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        observeData()
        viewModel.getRecentSearchData()
        viewModel.getHotKeyWord()
    }


    private fun initAdapter() {
        binding.tourAttraction.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.culture.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.festival.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.searchDataView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recentSearchRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.hotSearchKeyWordRecyclerView.layoutManager =
            GridLayoutManager(context,2)


        tourAdapter = ShowPlaceViewRecyclerViewAdapter({ contentTypeId ->
            // SelectRegionView로 이동
            moveToSelectRegionView(requireContext(), contentTypeId)
        }, false)
        festivalAdapter = ShowPlaceViewRecyclerViewAdapter({ contentTypeId ->
            // SelectRegionView로 이동
            moveToSelectRegionView(requireContext(), contentTypeId)
        }, false)
        cultureAdapter = ShowPlaceViewRecyclerViewAdapter({ contentTypeId ->
            // SelectRegionView로 이동
            moveToSelectRegionView(requireContext(), contentTypeId)
        }, false)
        searchAdapter =
            ShowPlaceViewSearchPagingRecyclerViewAdapter { contentTypeId, contentId, mapx, mapy, image, addr, title ->
                // SelectRegionView로 이동
                moveToDetailview(
                    requireContext(),
                    contentTypeId,
                    contentId,
                    mapx,
                    mapy,
                    image,
                    addr,
                    title
                )
            }
        recentSearchAdapter =
            RecentPlaceLogViewAdapter( { selectUid ->
                viewModel.deleteRecentSearchData(selectUid) // 선택한 검색 기록 삭제 이벤트 설정
                recentSearchAdapter.submitList(viewModel.recentSearchData.value)
            },{selectTitle ->
                /*
                * 검색 기록 누르면 해당 이름의 관광지 검색
                * */
                binding.searchView.setQuery(selectTitle,true)
            })
        hotKeyWordAdapter = HotKeyWorldViewAdapter { selectedItem ->
            binding.searchView.setQuery(selectedItem, true)
        }


        binding.recentSearchRecyclerView.adapter = recentSearchAdapter
        binding.tourAttraction.adapter = tourAdapter
        binding.culture.adapter = cultureAdapter
        binding.festival.adapter = festivalAdapter
        binding.searchDataView.adapter = searchAdapter
        binding.hotSearchKeyWordRecyclerView.adapter = hotKeyWordAdapter
    }

    private fun observeData() {
        viewModel.categoryData() // 관광지 카테고리 초기화
        viewModel.getRecentSearchData()


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

        viewModel.searchData.observe(this) {
            lifecycleScope.launch {
                searchAdapter.submitData(it)
            }
        }

        viewModel.recentSearchData.observe(this) {
            recentSearchAdapter.submitList(it)
        }

        viewModel.hotkeyword.observe(this){
            hotKeyWordAdapter.submitList(it.data)
        }

    }

    private fun moveToSelectRegionView(context: Context, contentTypeId: String) {

        val downloadIntent = Intent(context, SelectRegionView::class.java)

        // 선택된 카테고리의 contentTypeId 넘기기
        downloadIntent.putExtra("contentTypeId", contentTypeId)

        // SharedPreferences에 선택한 데이터 넣기
        sharedPreferencesUtill.setData(context, "showPlaceViewContentTypeId", contentTypeId)
        startActivity(downloadIntent)
    }

    private fun moveToDetailview(
        context: Context,
        contentTypeId: String,
        contentId: String,
        mapx: String,
        mapy: String,
        imageUrl: String,
        addr: String,
        title: String
    ) {

        // 선택한 아이템의 contentTypeId에 따라 DetailPage로 넘어감
        when (contentTypeId) {
            "12" -> {// 관광지
                val moveToPlaceView = Intent(context, SelectRegionView::class.java)
                moveToPlaceView.putExtra("isSearch", true)
                moveToPlaceView.putExtra("searchContentId", contentId)
                moveToPlaceView.putExtra("searchMapx", mapx)
                moveToPlaceView.putExtra("searchMapy", mapy)
                moveToPlaceView.putExtra("searchImage", imageUrl)
                moveToPlaceView.putExtra("searchAddr", addr)
                moveToPlaceView.putExtra("searchTitle", title)
                moveToPlaceView.putExtra("searchContentTypeId", contentTypeId)

                //최근 검색 기록 저장
                viewModel.setRecentSearchData(
                    initRecentEntity(
                        title = title,
                        contentTypeId = contentTypeId,
                        contentId = contentId,
                        image = imageUrl,
                        addr = addr,
                        mapx = mapx,
                        mapy = mapy
                    )
                )

                viewModel.storeKeyWord(title)
                startActivity(moveToPlaceView)
            }

            "14" -> {// 문화시설
                val moveToPlaceView = Intent(context, SelectRegionView::class.java)
                moveToPlaceView.putExtra("isSearch", true)
                moveToPlaceView.putExtra("searchContentId", contentId)
                moveToPlaceView.putExtra("searchMapx", mapx)
                moveToPlaceView.putExtra("searchMapy", mapy)
                moveToPlaceView.putExtra("searchImage", imageUrl)
                moveToPlaceView.putExtra("searchAddr", addr)
                moveToPlaceView.putExtra("searchTitle", title)
                moveToPlaceView.putExtra("searchContentTypeId", contentTypeId)
                viewModel.storeKeyWord(title)
                startActivity(moveToPlaceView)
            }

            "15" -> { //행사,공연,축제
                val moveToPlaceView = Intent(context, SelectRegionView::class.java)
                moveToPlaceView.putExtra("isSearch", true)
                moveToPlaceView.putExtra("searchContentId", contentId)
                moveToPlaceView.putExtra("searchMapx", mapx)
                moveToPlaceView.putExtra("searchMapy", mapy)
                moveToPlaceView.putExtra("searchImage", imageUrl)
                moveToPlaceView.putExtra("searchAddr", addr)
                moveToPlaceView.putExtra("searchTitle", title)
                moveToPlaceView.putExtra("searchContentTypeId", contentTypeId)
                viewModel.storeKeyWord(title)
                startActivity(moveToPlaceView)
            }

        }

    }
    /*
    * 1. viewScrollView 보임
    * 2. 검색 버튼 클릭 시 처음엔 recentSearchLayout가 보임
    * 3. 검색을 하면 searchParentView가 보임
    * 4. 검색을 마무리하면 viewScrollView가 보임
    * */

    private fun initSearchView() {

        binding.searchView.setOnQueryTextFocusChangeListener { _, hasfocus ->
            if (hasfocus) { // 처음 검색 클릭 시 인기검색어, 최근검색어 보이기
                binding.viewScrollView.visibility = View.GONE


                // viewScrollView가 사라지면서 searchView의 bottom_top의
                // view가 사라지므로 이를 대체하는 뷰 설정
                val searchView = binding.searchView
                val params = searchView.layoutParams as ConstraintLayout.LayoutParams
                params.bottomToTop = binding.recentSearchLayout.id
                searchView.layoutParams = params

                binding.recentSearchLayout.visibility = View.VISIBLE
                binding.hotSearchKeywordLayout.visibility = View.VISIBLE
            }

        }

        binding.searchView.setOnCloseListener {
            // searchView의 X 버튼 누르면 기존 창으로 다시 복귀

            if (binding.searchParentView.visibility == View.VISIBLE) { // 검색 view가 활성화 되어있다면 숨김
                binding.searchParentView.visibility = View.GONE
            } else if (binding.recentSearchLayout.visibility == View.VISIBLE) { // 최근검색어 view가 활성화 되어있다면 숨김
                binding.recentSearchLayout.visibility = View.GONE
                binding.hotSearchKeywordLayout.visibility = View.GONE
            }

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
                if (!newText.isNullOrEmpty()) { // 텍스트가 입력된다면,
                    viewModel.getSearchData(newText)
                    binding.recentSearchLayout.visibility = View.INVISIBLE
                    binding.hotSearchKeywordLayout.visibility = View.INVISIBLE

                    val searchView = binding.searchView
                    val params = searchView.layoutParams as ConstraintLayout.LayoutParams
                    params.bottomToTop = binding.recentSearchLayout.id
                    searchView.layoutParams = params

                    // 검색 view 보이기
                    binding.searchParentView.visibility = View.VISIBLE

                } else { // 텍스트가 입력되지 않았다면,
                    binding.searchParentView.visibility = View.INVISIBLE

                    val searchView = binding.searchView
                    val params = searchView.layoutParams as ConstraintLayout.LayoutParams
                    params.bottomToTop = binding.recentSearchLayout.id
                    searchView.layoutParams = params

                    // 최근 검색어, 인기 검색어 보이기
                    binding.recentSearchLayout.visibility = View.VISIBLE
                    binding.hotSearchKeywordLayout.visibility = View.VISIBLE
                }

                return true
            }

        })
    }

    private fun setTopAppBarText(text: String) {
        viewModel.setTopAppBarTitleText(text)
    }

    private fun initClickListener() {

        binding.recentSearchAllDeleteBtn.setOnClickListener {
            viewModel.deleteAllRecentSearchData()
            recentSearchAdapter.submitList(viewModel.recentSearchData.value)
        }
    }

    private fun initRecentEntity(
        contentId: String,
        mapx: String,
        mapy: String,
        image: String,
        addr: String,
        title: String,
        contentTypeId: String

    ): RecentSearchPlaceEntity {
        return RecentSearchPlaceEntity(
            title = title,
            addr = addr,
            contentTypeId = contentTypeId,
            imageUrl = image,
            mapx = mapx,
            mapy = mapy,
            searchContentId = contentId
        )

    }

    companion object {
        const val TAG = "ShowPlaceView"
    }

}