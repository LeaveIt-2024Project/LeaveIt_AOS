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
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.overlay.PathOverlay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigatePlaceView : Fragment(), OnMapReadyCallback {
    lateinit var binding: FragmentNavigatePathBinding
    private val rootViewModel: SelectRegionViewModel by activityViewModels()
    private val viewModel: NavigatePlaceViewModel by viewModels()

    /*
    * 사용자 현위치 : checkPermission() -> viewModel에 LatLng형으로 위경도 저장
    * 좌표기반 주소 출력 : viewModel.getCurrentLocation()
    * 관광지 위치 : rootViewModel.mapx, mapy
    * 관광지, 현위치 간 경로 : viewModel.getPath()
    * 경로 표시 : OnMapReadyCallBack -> onViewCreated()에서 호출하는 getMapSync()호출 시 호출되는 콜백 함수
    * 프로세스
    * cheackPermission() -> sumDataForPiccker() -> getPath() -> onMapReady()
    * */

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
        //checkPermission()

        initData()

        observeCurrentLocationAddress()
        observeNavigateStatus()
        binding.navigateMap.onCreate(savedInstanceState)
        binding.navigateMap.getMapAsync(this) // naverMap 객체 가져오기
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
            LocationProvider.getCurrentLocation(requireContext()) {// 사용자 현위치
                val str = "127.1035862,37.160708"
//                   실제 데이터  "${it?.longitude},${it?.latitude}"
                Log.d(TAG, "실제 데이터 ${str}")

                viewModel.setAllLocationData(
                    currentLat = 37.160708,
                    currentLong = 127.1035862,
                    placeLat = rootViewModel.mapy.value!!.toDouble(),
                    placeLong = rootViewModel.mapx.value!!.toDouble()
                )

                // 실제 데이터:
                val userLocationLat = 37.160708
                val userLocationLong = 127.1035862

                viewModel.setCurrentLocation(
                    lat = "37.160708".toDouble(),
                    lng = "127.1035862".toDouble()
                )
                    viewModel.setCenterLocation(
                        LatLng(userLocationLat,userLocationLong),
                        LatLng(rootViewModel.mapy.value!!.toDouble(),rootViewModel.mapx.value!!.toDouble())
                    )

                //"127.1035862,37.160708"
                viewModel.getCurrentLocationAddress("127.1035862,37.160708") // 현 위치 받아오는 메서드
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

    private fun goToBackFragment() {
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

    private fun observeCurrentLocationAddress() {
        viewModel.currentAddress.observe(viewLifecycleOwner) {
            binding.currentAddress.text = it
        }
        rootViewModel.addressInfo.observe(viewLifecycleOwner) {
            binding.placeAddressText.text = it
        }
    }

    private fun sumDataForPicker() { // 관광지, 사용자 위치 합치기

//        126.9289688,37.3798552
        val latitude = viewModel.currentLocation.value?.latitude
        val longtitude = viewModel.currentLocation.value?.longitude
        val start = "${longtitude.toString()}," +
                latitude.toString()

        val goal = "${rootViewModel.mapx.value.toString()}," +
                rootViewModel.mapy.value.toString()


        viewModel.getPath(start, goal) // 합친 데이터를 기반으로 경로 가져오기

    }

    private fun observeNavigateStatus() { // 가져온 경로 정보들
        viewModel.pathData.observe(viewLifecycleOwner) {
            binding.placeDistanceText.text = "${it.distance}km"
            binding.goalTimeText.text = it.departureTime
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        val path = PathOverlay()
        val startMarker = Marker()
        val goalMarker = Marker()
        setUtillFunctionMap(naverMap)


        viewModel.currentLocation.observe(viewLifecycleOwner) {
            startMarker.position = it
            startMarker.map = naverMap
        }

        rootViewModel.placeLocation.observe(viewLifecycleOwner) {
            goalMarker.position = it
            Log.d(TAG, "goalMarker : ${it.latitude}${it.longitude}")
            goalMarker.map = naverMap
        }

        viewModel.pathData.observe(viewLifecycleOwner) {
            val data = it.path.map {
                LatLng(it.latitude.toDouble(), it.longitutde.toDouble())
            }
            path.coords = data
            path.patternImage = OverlayImage.fromResource(R.drawable.baseline_arrow_drop_up_24)
            path.patternInterval = 10
            path.map = naverMap
        }
    }

    // 지도 설정 함수
    private fun setUtillFunctionMap(map: NaverMap) {

      viewModel.locationDataForMap.observe(viewLifecycleOwner){(center,zoomLevel) ->
          val cameraUpdate = CameraUpdate.scrollAndZoomTo(center, zoomLevel)
          Log.d(TAG,"실제 줌 거리 : $zoomLevel")
          map.moveCamera(cameraUpdate)
          map.uiSettings.isRotateGesturesEnabled = false // 회전 비활성화
          map.uiSettings.isTiltGesturesEnabled = false   // 기울이기 비활성화
      }
    }

    private fun initData() {
        checkPermission()
        viewModel.currentLocation.observe(viewLifecycleOwner) { location ->
            val currentLocation = viewModel.currentLocation.value
            if (location != null && currentLocation != null) {
                sumDataForPicker()
            }
        }
    }


    companion object {
        const val TAG = "NavigatePlaceView"
    }
}