package com.example.leaveit.presentation.review.postview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.leaveit.R
import com.example.leaveit.databinding.FragmentSelectphotoviewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SelectPhotoFragmentView : Fragment() {
    private lateinit var binding: FragmentSelectphotoviewBinding
    // 프레그먼트간 데이터 공유를 위해선 viewModels()가 아닌  activityViewModels()로 해야함.
    // viewModels는 프레그먼트간 독립적으로 데이터를 다룰 때 사용
    private val viewModel: PostReviewViewModel by  activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectphotoviewBinding.inflate(layoutInflater)

        changeTopText("사진을 선택하세요")
//https://github.com/williamyyu/SimpleRatingBar  별점

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRatingValue()

        binding.ConfirmBtnForPostReview.setOnClickListener {

            // 별점 선택 안하면 다음 페이지로 이동 불가
            if(viewModel.starCount.value == null){
                return@setOnClickListener
            }else{
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.selectregion_fragment_container, WriteContentFragmentView())
                    .commit()
            }
        }
    }




    fun changeTopText(text: String) {
        viewModel.setTopAppBarTitleText(text)
    }

    // 뷰모델에 사용자가 지정한 starCount 지정
    fun getRatingValue(){
        binding.rotationRatingBar.setOnRatingChangeListener { ratingBar, rating, fromUser ->
            viewModel.setStarCount(rating.toInt())
        }
    }

    companion object{
        const val TAG = "SelectPhotoFragmentView"
    }

}