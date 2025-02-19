package com.example.leaveit.presentation.myprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.leaveit.databinding.FragmentMyprofilepageviewBinding
import androidx.recyclerview.widget.GridLayoutManager
import com.example.leaveit.presentation.mainpageview.AppBarViewModel
import com.example.leaveit.presentation.mainpageview.adapter.MainPageViewPagerAdapter
import com.example.leaveit.presentation.myprofile.MyProfileReviewsRecyclerViewAdapter
import com.example.leaveit.presentation.myprofile.MyProfilePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyProfilePageView : Fragment() {
    private lateinit var binding: FragmentMyprofilepageviewBinding
    private lateinit var profileAdapter: MyProfileReviewsRecyclerViewAdapter
    private val viewModelMain: MyProfilePageViewModel by viewModels()
    private val viewModelSub: AppBarViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyprofilepageviewBinding.inflate(inflater, container, false)
        initAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelMain.loadMyReviews()
        observeData()
    }

    override fun onResume() {
        super.onResume()
        viewModelSub.selectFragment("MyProfilePage")
    }

    private fun initAdapter() {
        binding.myProfileReviews.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        profileAdapter = MyProfileReviewsRecyclerViewAdapter()
        binding.myProfileReviews.adapter = profileAdapter
    }

    private fun observeData() {
        viewModelMain.myProfileReviewsData.observe(viewLifecycleOwner, Observer {
            profileAdapter.submitList(it)
        })
    }
}