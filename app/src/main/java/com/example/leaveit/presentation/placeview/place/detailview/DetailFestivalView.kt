package com.example.leaveit.presentation.placeview.place.detailview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.leaveit.R
import com.example.leaveit.databinding.FragmentDetailfestivalviewBinding
import com.example.leaveit.presentation.placeview.place.navigateplaceview.NavigatePlaceView
import com.example.leaveit.presentation.placeview.place.selectregionview.SelectRegionViewModel
import com.example.leaveit.presentation.placeview.restraunt.RestrauntView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFestivalView : Fragment() {
    lateinit var  binding : FragmentDetailfestivalviewBinding
    private val rootViewModel: SelectRegionViewModel by activityViewModels()
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var placeContentId: String
    private lateinit var placeType: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailfestivalviewBinding.inflate(layoutInflater)
        placeContentId = rootViewModel.placeContentId.value.toString()
        placeType = rootViewModel.contentTypeId.value.toString()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetailFestivalData(id = placeContentId, type = placeType)

        rootViewModel.setIsMoveDetailView(false)
        moveToRestrauntViewEventListener()
        moveToNavigatePlaceEventListener()
        infoListener()
        bookingListener()
        observeContent()
        observeFestivalData()

    }

    override fun onStart() {
        super.onStart()
        observeFestivalData()
    }

    private fun observeFestivalData(){
        viewModel.detailFestivalData.observe(viewLifecycleOwner){
            binding.playTimeInfoText2.text = it.playTime
            binding.userTimeText2.text = it.spendtimefestival
            binding.playAgeLayerText2.text = it.agelimit
            binding.FestivalDateText2.text = it.eventenddate
            binding.FestivalFeeText2.text = it.usetimefestival
            Log.d(TAG,"예매처 : ${it.bookingplace}")
            Log.d(TAG,"주관사 번호 : ${it.sponsor2tel}")

        }

    }

    private fun infoListener() {
        binding.itemBtn4.setOnClickListener {
            if (viewModel.detailFestivalData.value?.sponsor2tel.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "전화번호 정보가 없습니다", Toast.LENGTH_SHORT).show()
            } else {
                val uri = Uri.parse("tel:${viewModel.detailFestivalData.value!!.sponsor2tel}")
                startActivity(Intent(Intent.ACTION_DIAL, uri))
            }
        }
    }

    private fun bookingListener(){
        binding.itemBtn3.setOnClickListener{
            if(viewModel.detailFestivalData.value?.bookingplace.isNullOrEmpty()){
                Toast.makeText(requireContext(), "예매처 정보가 없습니다", Toast.LENGTH_SHORT).show()
            }else{
                val urlintent = Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"))
                startActivity(urlintent)
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

    companion object{
        const val TAG = "DetailFestivalView"
    }


}