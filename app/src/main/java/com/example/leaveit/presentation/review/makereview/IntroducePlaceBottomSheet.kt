package com.example.leaveit.presentation.review.makereview

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.leaveit.R
import com.example.leaveit.presentation.review.ReviewMainView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class IntroducePlaceBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_introduce_place, container, false)
    }


    // 다이얼로그는 viewBinding 사용이 안됌
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text : TextView
        val imageView : ImageView
        val imageButton : ImageButton

        text = view.findViewById(R.id.introducePlaceRegionTextView)
        imageView = view.findViewById(R.id.introducePlaceRegionImageView)
        imageButton = view.findViewById(R.id.gotoReviewBtn)

        text.text = "북동부의 산악지역에서 남서쪽 해안지역에 이르는 천혜의 자연조건과 한민족의 정체성을 형성해 온 역사와 문화를 배경으로 경기도는 세계에 자랑하는 풍부한 관광자원을 보유하고 있습니다. " +
                "많은 내 외국인들이 경기도의 산, 절, 폭포, 해변, 강, 문화, 역사유적, 박물관, 갤러리, 리조트, 공원, 골프클럽, 온천 등을 즐겨 찾습니다."

        Glide.with(view)
            .load("http://tong.visitkorea.or.kr/cms/resource/76/532876_image2_1.jpg")
            .fitCenter()
            .into(imageView)

        imageButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ReviewMainView())
                .addToBackStack(null) // 뒤로가기 하려면 스택에 쌓아야됌.
                .commitAllowingStateLoss()
            // 프레그먼트 호출 후 바텀 다이얼로그 없애기
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            setOnShowListener { dialog ->
                val bottomSheet =
                    (dialog as BottomSheetDialog).findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                        ?: return@setOnShowListener
                val behavior = BottomSheetBehavior.from(bottomSheet!!)
                behavior.isDraggable = false
                bottomSheet.makeFullSize()
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
    }


    //bottomsheet은 내부에 content가 없을 경우 매우 작게 표기됌
    // 이는 BottomSheetDialog 자체가 뷰로 지정한 xml을 framelayout 내에서 보여주는데 이 framelayout이 wrap_content로
    // 되어있기 때문이다. 따라서 이를 MATCH_PARENT로 변경해주면 요소가 없어도 크게 보여주기가 가능하다.
    private fun View.makeFullSize() {
        this.layoutParams.height = FrameLayout.LayoutParams.MATCH_PARENT
        requestLayout()
    }



    companion object {
        const val TAG = "ModalBottomSheet"
    }
}