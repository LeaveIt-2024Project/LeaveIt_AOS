package com.example.leaveit.presentation.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.leaveit.databinding.FragmentReviewmainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async

@AndroidEntryPoint
class ReviewMainView : Fragment() {
    lateinit var binding : FragmentReviewmainBinding
    private val viewModel: ReviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // 바인딩 초기화
        binding = FragmentReviewmainBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * flow로 데이터를 받아서 UI에서 쓸거면
         * 코루틴 스코프 안에서 옵저빙, 함수호출을 해야 오류 안생김
         * 여기서는 코루틴 스코핑없이 호출했다가 비동기적으로 함수가 호출되는 바람에
         * usecase 의존성이 늦게 호출되서 초기화가 되지 않은 오류가 발생했었음
         */
        lifecycleScope.async {
            viewModel.data.observe(requireActivity()) {
                binding.testTextView.text = it[0].writeUserNickname
            }
        }
    }


}