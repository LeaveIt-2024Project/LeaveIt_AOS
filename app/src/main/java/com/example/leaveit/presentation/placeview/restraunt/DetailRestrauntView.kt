package com.example.leaveit.presentation.placeview.restraunt

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.leaveit.R
import com.example.leaveit.databinding.FragmentDetailrestrauntviewBinding
import com.example.leaveit.presentation.placeview.place.selectregionview.SelectRegionViewModel

class DetailRestrauntView : Fragment() {
    lateinit var binding: FragmentDetailrestrauntviewBinding
    private val rootViewModel: SelectRegionViewModel by activityViewModels()
    private val viewModel: RestrauntViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailrestrauntviewBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initTopAppBarText()
        infoClickListener()

        viewModel.selectRestrauntData.observe(viewLifecycleOwner) {
            viewModel.getDetailRestrauntData(it.contentid)
        }

        viewModel.data.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.playTimeInfo2.text = it.opentimefood
                binding.representativeMenuText2.text = it.firstmenu
                binding.menuText2.text = it.treatmenu
                binding.restDayInfo2.text = it.restdatefood
            }
        }
    }

    private fun initView() {
        val data = viewModel.selectRestrauntData.value
        if (data != null) {

            binding.placeTitleText.text = data.title
            binding.addressInfo.text = data.addr

            // 이미지 설정
            Glide.with(binding.root)
                .load(data.image)
                .fitCenter()
                .fallback(R.drawable.null_image)
                .into(binding.placeImage)

        }

    }

    private fun infoClickListener() {
        binding.infoImage.setOnClickListener {
            if (viewModel.data.value?.infocenterfood?.isEmpty() == true) {
                Toast.makeText(requireContext(), "등록된 번호가 없습니다", Toast.LENGTH_SHORT).show()
            } else {
                val uri = Uri.parse("tel:${viewModel.data.value?.infocenterfood}")
                startActivity(Intent(Intent.ACTION_DIAL, uri))
            }
        }
    }

    private fun initTopAppBarText() {
        rootViewModel.setTopTapContent(viewModel.selectRestrauntData.value!!.title)
    }

    companion object {
        const val TAG = "DetailRestrauntView"
    }
}