package com.example.leaveit.presentation.myprofile

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.leaveit.databinding.FragmentMyprofilechangeviewBinding
import com.example.leaveit.presentation.mainpageview.AppBarViewModel

class MyProfileChangeView : Fragment() {

    private lateinit var binding: FragmentMyprofilechangeviewBinding
    private val viewModelMain: MyProfileChangeViewModel by viewModels()
    private val viewModelSub: AppBarViewModel by activityViewModels()

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?, savedInstantState: Bundle?
    ) : View? {
        binding = FragmentMyprofilechangeviewBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelMain.loadSpinnerData()

        viewModelMain.regionList.observe(viewLifecycleOwner) { regions ->
            val adapter = MyProfileChangeAdapter(requireContext(), regions)
            binding.chooseRegion.adapter = adapter
        }

        binding.chooseRegion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedRegion = parent?.getItemAtPosition(position) as MyProfileChangeModel

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

    }

    override fun onResume() {
        super.onResume()
        viewModelSub.selectFragment("MyProfileChange")
    }
}