package com.example.leaveit.presentation.placeview.place.detailplaceview

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
import com.example.leaveit.R
import com.example.leaveit.databinding.FragmentDetailplaceviewBinding
import com.example.leaveit.presentation.placeview.place.selectregionview.SelectRegionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPlaceView : Fragment() {
    private lateinit var binding: FragmentDetailplaceviewBinding
    private val rootViewModel: SelectRegionViewModel by activityViewModels()
    private val viewModel: DetailPlaceViewModel by viewModels()
    private lateinit var placeContentId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailplaceviewBinding.inflate(layoutInflater)
        placeContentId = rootViewModel.placeContentId.value.toString()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Log.d(TAG, rootViewModel.placeContentId.value.toString())

        viewModel.getDetailPlaceData(placeContentId)
        infoEventListener()

        observeDetailPlaceData()

    }

    private fun observeDetailPlaceData() {
        viewModel.detailPlaceData.observe(viewLifecycleOwner) {
            binding.restDayInfo.text = "쉬는날 ${it.restdate}"
            binding.playTimeInfo.text = "운영시간 " + translatePlayTimeString(it.usetime)

            if (it.chkpet) {
                binding.item1.setImageResource(R.drawable.pets_cliked)
            } else {
                binding.item1.setImageResource(R.drawable.pets_uncliked)

            }

            if(it.parking){
                binding.item2.setImageResource(R.drawable.parking_cliked)
            } else {
                binding.item2.setImageResource(R.drawable.parking_uncliked)

            }

            Log.d(TAG, it.chkpet.toString())
            Log.d(TAG, it.parking.toString())
            Log.d(TAG, it.infocenter.toString())
        }
    }

    private fun infoEventListener(){
        binding.itemBtn4.setOnClickListener {
            if(viewModel.detailPlaceData.value?.infocenter?.isEmpty() == true){
                Toast.makeText(requireContext(),"등록된 번호가 없습니다",Toast.LENGTH_SHORT).show()
            }else{
                val uri = Uri.parse("tel:${viewModel.detailPlaceData.value!!.infocenter}")
                startActivity(Intent(Intent.ACTION_DIAL,uri))
            }
        }
    }

    private fun translatePlayTimeString(value: List<String>): String {
        var result = ""
        if (value.size == 1) {
            result = value[0]
        } else if (value.size == 2) {
            result += value[0]
            result += value[1]
        } else {
            result = ""
        }
        return result
    }


    companion object {
        const val TAG = "DetailPlaceView"
    }
}