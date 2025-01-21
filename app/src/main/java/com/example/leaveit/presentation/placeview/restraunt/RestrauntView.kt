package com.example.leaveit.presentation.placeview.restraunt

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.leaveit.databinding.FragmentRestrauntviewBinding
import com.example.leaveit.presentation.placeview.place.selectregionview.SelectRegionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestrauntView : Fragment(){

    lateinit var binding : FragmentRestrauntviewBinding
    private val rootViewModel : SelectRegionViewModel by activityViewModels()
    private val viewModel : RestrauntViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  FragmentRestrauntviewBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapx = rootViewModel.mapx.value.toString()
        val mapy = rootViewModel.mapy.value.toString()
        Log.d(TAG,mapx)
        Log.d(TAG,mapy)

        viewModel.getRestrauntList(mapx,mapy)

        viewModel.restrauntList.observe(viewLifecycleOwner){data ->
            data.list.map {
                    Log.d(TAG,it.title)
                }
        }


    }


    companion object{
        const val TAG = "RestrauntView"
    }
}