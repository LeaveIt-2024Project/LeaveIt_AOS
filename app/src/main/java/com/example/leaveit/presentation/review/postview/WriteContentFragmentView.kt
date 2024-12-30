package com.example.leaveit.presentation.review.postview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.leaveit.R
import com.example.leaveit.databinding.FragmentWritecontentBinding
import com.example.leaveit.presentation.review.postview.adapter.ReivewPhotoAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WriteContentFragmentView : Fragment() {

    private lateinit var binding: FragmentWritecontentBinding
    private lateinit var adapter: ReivewPhotoAdapter
    private lateinit var tempContent: String
    private var isValidContentSize: Boolean = false
    private val viewModel: PostReviewViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWritecontentBinding.inflate(layoutInflater)
        changeTopText("게시글을 입력하세요")
        initAdapter()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.writeContentConfirmBtnForPostReview.setOnClickListener {
            getContent()
            if (isValidContentSize) {

                changeTopText("등록중")
                requireActivity().supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.fade_in_review_splash,
                        R.anim.fade_out_review_splash,
                        R.anim.fade_in_review_splash,
                        R.anim.fade_out_review_splash
                    )
                    .replace(R.id.review_fragment_container, ReviewSplashView())
                    .commit()

                lifecycleScope.launch {
                    delay(3000) // 3초 대기
                    requireActivity().supportFragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.fade_in_review_splash,
                            R.anim.fade_out_review_splash,
                            R.anim.fade_in_review_splash,
                            R.anim.fade_out_review_splash
                        )
                        .replace(R.id.review_fragment_container, LastPostReviewFragmentView())
                        .addToBackStack(null)
                        .commit()

                    requireActivity().supportFragmentManager.beginTransaction()
                        .remove(ReviewSplashView())
                        .commit()
                }
            } else {
                Log.d(TAG, "글자가 너무 짧")
            }
        }
    }

    fun changeTopText(text: String) {
        viewModel.setTopAppBarTitleText(text)
    }

    private fun initAdapter() {
        adapter = ReivewPhotoAdapter()
        binding.ShowPlaceImageViewPager.adapter = adapter
        binding.showPlaceIndicator.attachTo(binding.ShowPlaceImageViewPager)

        viewModel.tempImageList.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.submitList(viewModel.tempImageList.value)
            }
        }
    }

    private fun getContent() {
        tempContent = binding.writeContentForReviewEditText.text.toString()
        if (tempContent.isNullOrEmpty()) {
            isValidContentSize = false
        } else {
            isValidContentSize = true
        }
    }


    companion object {
        const val TAG = "WriteContentFragmentView"
    }
}