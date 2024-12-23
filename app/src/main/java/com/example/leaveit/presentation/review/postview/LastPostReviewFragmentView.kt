package com.example.leaveit.presentation.review.postview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.leaveit.databinding.FragmentLastpostreviewviewBinding

class LastPostReviewFragmentView : Fragment() {
    lateinit var binding: FragmentLastpostreviewviewBinding
    private lateinit var viewModel: PostReviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLastpostreviewviewBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity())[PostReviewViewModel::class.java]
        changeTopText("등록완료")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 뒤로가기 막을라고 OnBackPressedCallback 객체 내부 비워둠
            }
        })
    }

    fun changeTopText(text: String) {
        viewModel.setTopAppBarTitleText(text)
    }
}