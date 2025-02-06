package com.example.leaveit.presentation.placeview.place.detailview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.leaveit.R
import com.example.leaveit.databinding.FragmentDetailcutureviewBinding
import com.example.leaveit.presentation.placeview.place.navigateplaceview.NavigatePlaceView
import com.example.leaveit.presentation.placeview.place.selectregionview.SelectRegionViewModel
import com.example.leaveit.presentation.placeview.restraunt.RestrauntView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCultureView : Fragment() {
    lateinit var  binding : FragmentDetailcutureviewBinding
    private val rootViewModel: SelectRegionViewModel by activityViewModels()
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var placeContentId: String
    private lateinit var placeType: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailcutureviewBinding.inflate(layoutInflater)
        placeContentId = rootViewModel.placeContentId.value.toString()
        placeType = rootViewModel.contentTypeId.value.toString()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rootViewModel.setIsMoveDetailView(false)
        viewModel.getDetailCultureData(id = placeContentId, type = placeType)
        moveToRestrauntViewEventListener()
        moveToNavigatePlaceEventListener()
        infoEventListener()
        observeContent()
        observeCultureData()
    }

    private fun observeCultureData(){
        viewModel.detailCultureData.observe(viewLifecycleOwner){
            binding.userTimeText2.text = it.usetimeculture
            binding.playTimeInfoText2.text = it.usetimeculture
            binding.useFeeText2.text = it.usefee


            if(it.chkbabycarriageculture == true){
                binding.item3.setImageResource(R.drawable.child_friendly_cliked)
            } else {
                binding.item3.setImageResource(R.drawable.child_friendly_uncliked)
            }

            if (it.chkpetculture == true) {
                binding.item1.setImageResource(R.drawable.pets_cliked)
            } else {
                binding.item1.setImageResource(R.drawable.pets_uncliked)

            }

            if (it.parkingculture == true) {
                binding.item3.setImageResource(R.drawable.parking_cliked)
            } else {
                binding.item3.setImageResource(R.drawable.parking_uncliked)

            }

        }
    }


    private fun infoEventListener() {
        binding.itemBtn4.setOnClickListener {
            if (viewModel.detailCultureData.value?.infocenterculture.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "등록된 번호가 없습니다", Toast.LENGTH_SHORT).show()
            } else {
                val uri = Uri.parse("tel:${viewModel.detailCultureData.value!!.infocenterculture}")
                startActivity(Intent(Intent.ACTION_DIAL, uri))
            }
        }
    }

    private fun moveToNavigatePlaceEventListener() {
        binding.itemBtn1.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(
                    R.anim.fade_in_review_splash,
                    R.anim.fade_out_review_splash,
                    R.anim.fade_in_review_splash,
                    R.anim.fade_out_review_splash
                )
                .replace(
                    R.id.selectregion_fragment_container,
                    NavigatePlaceView()
                )
                .commit()
        }
    }

    private fun moveToRestrauntViewEventListener() {
        binding.itemBtn2.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(
                    R.anim.fade_in_review_splash,
                    R.anim.fade_out_review_splash,
                    R.anim.fade_in_review_splash,
                    R.anim.fade_out_review_splash
                )
                .replace(
                    R.id.selectregion_fragment_container,
                    RestrauntView()
                )
                .commit()
        }
    }

    private fun observeContent() {
        rootViewModel.imageUrl.observe(viewLifecycleOwner) {
            Glide.with(this)
                .load(it)
                .fallback(R.drawable.null_image)
                .override(binding.imageLayer.width, binding.imageLayer.height)
                .into(binding.placeImage)
        }
        rootViewModel.placeTitle.observe(viewLifecycleOwner) {
            binding.placeTitleText.text = it
            rootViewModel.setTopTapContent(it)
        }
       binding.addressInfo.text = rootViewModel.addressInfo.value
    }
}