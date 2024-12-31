package com.example.leaveit.presentation.review.postview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaveit.R
import com.example.leaveit.databinding.FragmentSearchPlaceForReviewBinding
import com.example.leaveit.local.RecentSearchEntity
import com.example.leaveit.presentation.review.postview.adapter.RecentSearchListAdapter
import com.example.leaveit.presentation.review.postview.adapter.SelectPlaceAdapter
import com.example.leaveit.presentation.review.postview.data.SelectPlaceData
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.util.UUID

@AndroidEntryPoint
class SelectPlaceFragmentView : Fragment() {
    private lateinit var binding: FragmentSearchPlaceForReviewBinding
    private lateinit var adapter: SelectPlaceAdapter
    private lateinit var recentQueryAdapter: RecentSearchListAdapter
    private val viewModel: PostReviewViewModel by activityViewModels()

    /*
    * TODO
    *  1. 검색 기록 Room에  저장
    *  2. 리사이클러뷰 구현
    *  3. 검색 기록 삭제 기능 구현 -> Shared, RecyclerView 다 삭제해야됌
    * */

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchPlaceForReviewBinding.inflate(layoutInflater)
        changeTopText("관광지를 검색하세요")
        initAdapter()
        initSearchQueryListener()
        initRecentQueryAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllData()
        observeRecentQueryData()
        deleteAllQueryDataListener()

    }


    private fun changeTopText(text: String) { // 상단 탭바 타이틀 변경 함수
        viewModel.setTopAppBarTitleText(text)
    }

    private fun initSearchQueryListener() {
        binding.searchPlaceForReviewSearchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // 검색 버튼을 누르면
                adapter.submitList(initTestData())

                val addData = makeQueryData(query)
                viewModel.setRecentQuery(addData)


                recentQueryAdapter.submitList(cachedRecentData(addData).toList())
                recentQueryAdapter.notifyDataSetChanged()

                binding.searchResultRecyclerView.visibility = View.VISIBLE
                binding.hotKeywordLayout.visibility = View.INVISIBLE
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // 텍스트가 변경될때마다
                // 이거 안쓸거임
                return true
            }
        })
    }


    private fun initAdapter() {
        // TODO 검색해서 가져온 데이터 리사이클러뷰 정의해서 띄우는 로직 만들기
        binding.searchResultRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = SelectPlaceAdapter(
            selectPlaceClick = {
                requireActivity().supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .setCustomAnimations(
                        R.anim.fade_in_review_splash,
                        R.anim.fade_out_review_splash,
                        R.anim.fade_in_review_splash,
                        R.anim.fade_out_review_splash
                    )
                    .replace(
                        R.id.review_fragment_container,
                        SelectPhotoFragmentView()
                    )
                    .commit()
            }
        )
        binding.searchResultRecyclerView.adapter = adapter

        adapter.submitList(emptyList())
    }

    private fun initRecentQueryAdapter() {

        binding.recentRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recentQueryAdapter = RecentSearchListAdapter(
            deleteRecentData = {
                viewModel.deleteRecentQuery()


                recentQueryAdapter.notifyDataSetChanged()
            }
        )

        binding.recentRecyclerView.adapter = recentQueryAdapter
        adapter.submitList(emptyList())
    }

    fun initTestData(): MutableList<SelectPlaceData> {

        return mutableListOf(
            SelectPlaceData(
                feedUID = "123",
                nickname = "테스트닉네임",
                content = "테스트리뷰콘텐츠",
                feedImage = "http://tong.visitkorea.or.kr/cms/resource/17/1598617_image2_1.jpg",
                likeCount = 3,
                starCount = 2,
                placeArea = "서울",
                isUserLiked = false,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
        )
    }

    fun makeQueryData(data: String): RecentSearchEntity {
        return RecentSearchEntity(
            uid = UUID.randomUUID().toString(),
            recentData = data,
            searchIndex = 0
        )
    }

    fun cachedRecentData(data: RecentSearchEntity): MutableList<RecentSearchEntity> {
        val temp = viewModel.recentSearchList.value
        temp!!.add(data)
        return temp
    }

    fun observeRecentQueryData() {
        viewModel.recentSearchList.observe(viewLifecycleOwner) {
            recentQueryAdapter.submitList(it)
        }
    }

    fun deleteAllQueryDataListener() {
        binding.recentAllDeleteBtn.setOnClickListener {
            viewModel.deleteAllQuery()
        }
    }


    companion object {
        val TAG = "SortRegionFragment"
    }
}