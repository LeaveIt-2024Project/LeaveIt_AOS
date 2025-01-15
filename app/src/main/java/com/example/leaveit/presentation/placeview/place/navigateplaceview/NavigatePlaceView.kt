package com.example.leaveit.presentation.placeview.place.navigateplaceview

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.leaveit.R
import com.example.leaveit.databinding.FragmentNavigatePathBinding
import com.example.leaveit.presentation.placeview.place.detailplaceview.DetailPlaceView
import com.example.leaveit.presentation.placeview.place.selectregionview.SelectRegionViewModel
import com.example.leaveit.utill.location.LocationProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigatePlaceView : Fragment() {
    lateinit var binding: FragmentNavigatePathBinding
    private val rootViewModel: SelectRegionViewModel by activityViewModels()
    private val viewModel : NavigatePlaceViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavigatePathBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        observeCurrentLocationAddress()
    }

    private fun checkPermission() {
        val fineLocationStatus = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val coarseLocationStatus = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        if ((fineLocationStatus == PackageManager.PERMISSION_GRANTED) || (coarseLocationStatus == PackageManager.PERMISSION_GRANTED)) {
            //TODO 권한이 부여되어 있을 때
            Log.d(TAG, "권한 부여되어있음")
            LocationProvider.getCurrentLocation(requireContext()){
                val str = "${it?.longitude},${it?.latitude}"
                Log.d(TAG,str)

                viewModel.setLatitude(it?.latitude.toString())
                viewModel.setLonggitutde(it?.longitude.toString())
                sumDataForPicker()

                viewModel.getCurrentLocationAddress("127.1035862,37.160708")
            }
        } else {
            //TODO 권한이 부여되어 있지 않을 때
            Log.d(TAG, "권한 부여되어있지 않음")
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }

    }

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.d(TAG, "isGranted")
            //TODO 권한 요청 된 부분
        } else {
            //TODO 권한 요청이 안 된 부분
            Log.d(TAG, "!isGranted")
            // ActivityCompat.shouldShowRequestPermissionRationale
            //  → 사용자가 권한 요청을 명시적으로 거부한 경우 true를 반환한다.
            //	→ 사용자가 다시 묻지 않음 선택한 경우 false를 반환한다.
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                // 권한 요청에 대한 이유를 사용자에게 설명하는 Dialog를 표시
                AlertDialog.Builder(requireContext())
                    .setTitle("권한 요청")
                    .setMessage("위치 권한이 필요합니다.")
                    .setPositiveButton("확인") { dialog, _ ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        intent.data = Uri.fromParts("package", "com.example.leaveit", null)
                        this.startActivity(intent)
                        dialog.dismiss()
                    }.setNegativeButton("취소") { dialog, _ ->
                        // Dialog에서 취소 버튼을 누른 경우에 실행할 코드
                        dialog.dismiss()
                        goToBackFragment()
                    }
                    .show()
            } else {
                // 권한 요청에 대한 이유를 사용자에게 설명하는 Dialog를 표시
                AlertDialog.Builder(requireContext())
                    .setTitle("권한 요청")
                    .setMessage("위치 권한이 필요합니다.")
                    .setPositiveButton("확인") { dialog, _ ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        intent.data = Uri.fromParts("package", "com.example.leaveit", null)
                        this.startActivity(intent)
                        dialog.dismiss()
                    }.setNegativeButton("취소") { dialog, _ ->
                        // Dialog에서 취소 버튼을 누른 경우에 실행할 코드
                        dialog.dismiss()
                        goToBackFragment()
                    }
                    .show()
            }
        }
    }

    private fun goToBackFragment(){
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.fade_in_review_splash,
                R.anim.fade_out_review_splash,
                R.anim.fade_in_review_splash,
                R.anim.fade_out_review_splash
            )
            .replace(
                R.id.selectregion_fragment_container,
                DetailPlaceView()
            )
            .addToBackStack(null) // 백스택에 추가
            .commit()
    }

    private fun observeCurrentLocationAddress(){
        viewModel.currentAddress.observe(viewLifecycleOwner){
            binding.currentAddress.text = it
        }
        rootViewModel.addressInfo.observe(viewLifecycleOwner){
            binding.placeAddressText.text = it
        }
    }

    private fun sumDataForPicker(){

        val test = NavigateDataClass(
            myLocationX = viewModel.currentLongitude.value.toString(),
            myLocationY = viewModel.currentLatitude.value.toString(),
            placeLocationX = rootViewModel.mapx.value.toString(),
            placeLocationY = rootViewModel.mapy.value.toString(),
            arriveTime = "123",
            distance = "123"
        )

        test.let {
            Log.d(TAG,"현위치 : ${it.myLocationX}")
            Log.d(TAG,"관광지 : ${it.placeLocationX}")
        }

    }


    companion object {
        const val TAG = "NavigatePlaceView"
    }
}