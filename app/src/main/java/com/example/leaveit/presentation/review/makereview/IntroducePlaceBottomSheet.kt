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

        //선택된 지역 이름 불러오기
        val selectRegionText = arguments?.getString("region")

        val title: TextView = view.findViewById(R.id.introducePlaceRegionReginNameTextView)
        val content: TextView = view.findViewById(R.id.introducePlaceRegionTextView)
        val imageView: ImageView = view.findViewById(R.id.introducePlaceRegionImageView)
        val imageButton: ImageButton = view.findViewById(R.id.gotoReviewBtn)

        //선택된 지역의 소개말 설정
        content.text = sortIntroContent(selectRegionText.toString())

        //선택된 지역 이름  타이틀로 지정
        title.text = selectRegionText

        Glide.with(view)
            .load(sortIntroImage(selectRegionText.toString()))
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

    private fun sortIntroContent(str: String): String {
        var result = ""
        when (str) {
            "경기" -> result = getString(R.string.intro_Gyeonggi_do)
            "서울" -> result = getString(R.string.intro_Seoul)
            "인천" -> result = getString(R.string.intro_Incheon)
            "강원" -> result = getString(R.string.intro_Gangwon_do)
            "충북" -> result = getString(R.string.intro_Chungbuk)
            "충남" -> result = getString(R.string.intro_Chungnam)
            "경북" -> result = getString(R.string.intro_Gyeongbuk)
            "경남" -> result = getString(R.string.intro_Gyeongnam)
            "전북" -> result = getString(R.string.intro_Jeonbuk)
            "전남" -> result = getString(R.string.intro_Jeonnam)
            "대전" -> result = getString(R.string.intro_Daejeon)
            "제주" -> result = getString(R.string.intro_Jeju)
            "대구" -> result = getString(R.string.intro_Daegu)
            "광주" -> result = getString(R.string.intro_Gwangju)
            "부산" -> result = getString(R.string.intro_Busan)
            "울산" -> result = getString(R.string.intro_Ulsan)
            "세종" -> result = getString(R.string.intro_Sejong)
        }

        return result
    }

    private fun sortIntroImage(str: String): Int {
        var result = 0
        when (str) {
            "경기" -> result = R.drawable.gyeonggyi
            "서울" -> result = R.drawable.seou
            "인천" -> result = R.drawable.incheon
            "강원" -> result = R.drawable.gangwon
            "충북" -> result = R.drawable.chungbook
            "충남" -> result = R.drawable.chungnam
            "경북" -> result = R.drawable.gyeongbook
            "경남" -> result = R.drawable.gyongnam
            "전북" -> result = R.drawable.junbook
            "전남" -> result = R.drawable.junnam
            "대전" -> result = R.drawable.daejun
            "제주" -> result = R.drawable.jeju
            "대구" -> result = R.drawable.daegu
            "광주" -> result = R.drawable.gwangju
            "부산" -> result = R.drawable.busan
            "울산" -> result = R.drawable.ulsan
            "세종" -> result = R.drawable.sejong
        }

        return result
    }


    companion object {
        const val TAG = "ModalBottomSheet"
    }
}