package com.example.leaveit.presentation.review.makereview

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.leaveit.MainActivity
import com.example.leaveit.R
import com.example.leaveit.databinding.FragmentSelectplaceforreviewBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.NaverMapOptions
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage

class SelectPlaceForReview : Fragment(), OnMapReadyCallback {

    lateinit var binding : FragmentSelectplaceforreviewBinding
    lateinit var parentActivity: MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 상단바 초기화
        initTopBar()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = context as MainActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectplaceforreviewBinding.inflate(layoutInflater)

        // 지도 불러오기
        // 프레그먼트 안에 프레그먼트를 불러오는거라 childFragmentManger 사용
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_view) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_view, it).commit()
            }


        mapFragment.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(map: NaverMap) { // 네이버 맵 설정 메서드

        // 맵 유틸 설정
        setUtillFunctionMap(map)

        // 지역별 위경도
        val locations = mapOf<String, LatLng>(
            "경기" to LatLng(37.27, 127.44),  // 경기
            "서울" to LatLng(37.56, 126.97),  // 서울
            "인천" to LatLng(37.45, 126.70),  // 인천
            "강원" to LatLng(37.82, 128.15),  // 강원
            "충북" to LatLng(36.8, 127.7),    // 충북
            "충남" to LatLng(36.518, 126.8),  // 충남
            "경북" to LatLng(36.416, 128.16), // 경북
            "경남" to LatLng(35.46, 128.21),  // 경남
            "전북" to LatLng(35.71, 127.15),  // 전북
            "전남" to LatLng(34.86, 126.99),  // 전남
            "대전" to LatLng(36.35, 127.38),  // 대전
            "제주" to LatLng(33.49, 126.53),  // 제주
            "대구" to LatLng(35.87, 128.60),  // 대구
            "광주" to LatLng(35.15, 126.85),  // 광주
            "부산" to LatLng(35.17, 129.07),  // 부산
            "울산" to LatLng(35.53, 129.31),  // 울산
            "세종" to LatLng(36.50, 127.24)   // 세종
        )

        // 위경도 리스트와 NaverMap 객체를 마커 표시
            createMarker(locations, map)


    }

    // 지도 설정 함수
    private fun setUtillFunctionMap(map: NaverMap){

        // 초기 카메라 포지션
        // 한국 정중앙
        NaverMapOptions().camera(CameraPosition(LatLng(35.95, 128.25), 6.0))

        // 카메라 줌 6.0으로 설정
        map.minZoom = 6.0
        map.maxZoom = 6.0

        // 카메라에서 보이는 화면 좌표 설정
        val southWest = LatLng(33.0, 124.0) // 한국 남서쪽 (제주도 포함)
        val northEast = LatLng(39.0, 132.0) // 한국 북동쪽 (독도 포함)
        val bounds = LatLngBounds(southWest, northEast)

        val cameraUpdate = CameraUpdate.fitBounds(bounds, 100) // 패딩 100px
        map.moveCamera(cameraUpdate)

        // 카메라 최대 움직일 수 있는 범위 bounds로 설정
        map.extent = bounds


        // 카메라 줌,이동,회전,스크롤 비활성화로 지도 고정
        map.uiSettings.isScrollGesturesEnabled = false // 스크롤 비활성화
        map.uiSettings.isZoomGesturesEnabled = false   // 줌 비활성화
        map.uiSettings.isRotateGesturesEnabled = false // 회전 비활성화
        map.uiSettings.isTiltGesturesEnabled = false   // 기울이기 비활성화

        // 카메라 줌 버튼 비활성화
        map.uiSettings.isZoomControlEnabled = false
    }

    fun createMarker(values: Map<String, LatLng>, map: NaverMap): List<Marker> {
        return values.map { (key, latLng) ->  // Map을 순회하며 key, value(LatLng)를 가져옴
            Marker().apply {
                val info = InfoWindow()
                info.adapter = object : InfoWindow.DefaultTextAdapter(requireContext()){
                    override fun getText(p0: InfoWindow): CharSequence {
                        return "선택"
                    }
                }
                info.position = latLng
                this.position = latLng // 각 Marker에 position을 설정
                this.map = map // Marker가 보이도록 map에 추가
                this.width = 80
                this.height = 80
                this.icon = OverlayImage.fromResource(R.drawable.map_picker)

                // 클릭 리스너 추가
                setOnClickListener {
                    Log.d(MainActivity.TAG, "선택된 지역 $key: $latLng")
                    val modalBottomSheet = IntroducePlaceBottomSheet()
                    modalBottomSheet.show(parentFragmentManager, IntroducePlaceBottomSheet.TAG)

                    true
                }
            }
        }
    }

    private fun initTopBar(){
        // setHasOptionsMenu(true) 이거 deprecated되서 MenuProvider 사용해서
        // TopAppBar 메뉴 변경해야돔
        // 참고 : https://developer.android.com/jetpack/androidx/releases/activity?hl=ko#1.4.0-alpha01

        requireActivity().title = "지역을 선택하세요"

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.selectplaceforreviewmenu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when(menuItem.itemId){
                    R.id.selectPlace_gotoMyPage ->{
                        // 마이페이지 이동하는 인텐트 여기 설정
                        Log.d(TAG,"테스트")
                        true
                    }
                    else -> false
                }
            }
        },viewLifecycleOwner,Lifecycle.State.RESUMED)
    }

    companion object{
        val TAG = "SelectPlaceForReview"
    }
}