package com.example.leaveit.presentation.review.postview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.leaveit.R
import com.example.leaveit.databinding.FragmentSelectphotoviewBinding


class SelectPhotoFragmentView : Fragment() {
    private lateinit var binding: FragmentSelectphotoviewBinding
    private lateinit var viewModel: PostReviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectphotoviewBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity())[PostReviewViewModel::class.java]

        changeTopText("사진을 선택하세요")
//https://github.com/williamyyu/SimpleRatingBar  별점

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ConfirmBtnForPostReview.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.selectregion_fragment_container, WriteContentFragmentView())
                .commit()
        }
    }




    fun changeTopText(text: String) {
        viewModel.setTopAppBarTitleText(text)
    }

}