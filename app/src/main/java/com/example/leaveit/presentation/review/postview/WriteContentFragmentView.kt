package com.example.leaveit.presentation.review.postview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.leaveit.R
import com.example.leaveit.databinding.FragmentWritecontentBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WriteContentFragmentView : Fragment() {

    lateinit var binding: FragmentWritecontentBinding
    private val viewModel: PostReviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWritecontentBinding.inflate(layoutInflater)
        changeTopText("게시글을 입력하세요")


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.writeContentConfirmBtnForPostReview.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in_review_splash,R.anim.fade_out_review_splash)
                .add(R.id.selectregion_fragment_container, ReviewSplashView())
                .commit()

            lifecycleScope.launch {
                delay(3000) // 3초 대기
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.selectregion_fragment_container, LastPostReviewFragmentView())
                    .addToBackStack(null)
                    .commit()

                requireActivity().supportFragmentManager.beginTransaction()
                    .remove(ReviewSplashView())
                    .commit()
            }

        }
    }

    fun changeTopText(text: String) {
        viewModel.setTopAppBarTitleText(text)
    }
}