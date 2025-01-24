package com.example.leaveit.presentation.placeview.restraunt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.leaveit.R
import com.example.leaveit.databinding.FragmentRestrauntviewBinding
import com.example.leaveit.domain.model.RestrauntDomainModel
import com.example.leaveit.presentation.placeview.place.selectregionview.SelectRegionViewModel
import com.example.leaveit.utill.location.ClusterForMap
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.clustering.ClusterMarkerInfo
import com.naver.maps.map.clustering.Clusterer
import com.naver.maps.map.clustering.DefaultClusterMarkerUpdater
import com.naver.maps.map.clustering.DefaultLeafMarkerUpdater
import com.naver.maps.map.clustering.LeafMarkerInfo
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RestrauntView : Fragment(), OnMapReadyCallback {

    lateinit var binding: FragmentRestrauntviewBinding
    private val rootViewModel: SelectRegionViewModel by activityViewModels()
    private val viewModel: RestrauntViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRestrauntviewBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 관광지 좌표 호출
        val mapx = rootViewModel.mapx.value.toString()
        val mapy = rootViewModel.mapy.value.toString()

        // 관광지 좌표 기반 근처 음식점 데이터 불러오기
        viewModel.getRestrauntList(mapx, mapy)

        binding.naverMap.onCreate(savedInstanceState)
        binding.naverMap.getMapAsync(this) // naverMap 객체 가져오기

    }


    override fun onMapReady(naverMap: NaverMap) {
        // 카메라 세팅
        setUtillFunctionMap(naverMap)

        // 관광지 근처 음식점  마커 찍기
        viewModel.restrauntList.observe(viewLifecycleOwner) { data ->

            var dataSize = data.list.size - (data.list.size - 1) // 받아온 데이터 순회를 위한 변수 선언

            val cluster: Clusterer<ClusterForMap> = Clusterer.Builder<ClusterForMap>()
                .clusterMarkerUpdater(object : DefaultClusterMarkerUpdater() {
                    override fun updateClusterMarker(info: ClusterMarkerInfo, marker: Marker) {
                        super.updateClusterMarker(info, marker)
                        // ✅ 클러스터 클릭 시 확대 (zoom in)
                        marker.onClickListener = Overlay.OnClickListener {
                            val clusterCenter = marker.position // 현재 클러스터 위치 받아오기
                            val zoomLevel = naverMap.cameraPosition.zoom + 2.0 // 현재 카메라 줌에 + 2.0

                            val cameraUpdate =
                                CameraUpdate.scrollAndZoomTo(clusterCenter, zoomLevel)
                                    .animate(CameraAnimation.Easing)
                            naverMap.moveCamera(cameraUpdate) // 클릭하면 카메라 줌 업데이트

                            true // 이벤트 처리 완료
                        }

                    }
                }).leafMarkerUpdater(object : DefaultLeafMarkerUpdater() {
                    override fun updateLeafMarker(info: LeafMarkerInfo, marker: Marker) {
                        super.updateLeafMarker(info, marker)

                        val selectedRestrauntData =
                            info.tag as RestrauntDomainModel // 마커 데이터 접근을 위한 형변환

                        marker.onClickListener = Overlay.OnClickListener {
                            viewModel.setRestrauntData(selectedRestrauntData) // 클릭한 데이터 viewModel에 저장
                            moveToDetailRestrauntView() // 저장 후 다음 화면으로 이동
                            true
                        }
                    }
                }).minZoom(4).maxZoom(16).animate(true).build()


            data.list.map { item -> // 마커 데이터 클러스터에 추가
                cluster.add(
                    ClusterForMap(
                        dataSize,
                        LatLng(item.langtitude.toDouble(), item.longitutde.toDouble())
                    ), item
                )
                dataSize++
            }
            cluster.map = naverMap
        }
    }

    // 지도 설정 함수
    private fun setUtillFunctionMap(map: NaverMap) {
        val latLng =
            LatLng(rootViewModel.mapy.value!!.toDouble(), rootViewModel.mapx.value!!.toDouble())

        val cameraUpdate = CameraUpdate.scrollAndZoomTo(latLng, 15.0)
        map.moveCamera(cameraUpdate)
        map.uiSettings.isRotateGesturesEnabled = false // 회전 비활성화
        map.uiSettings.isTiltGesturesEnabled = false   // 기울이기 비활성화
    }

    // 다음 화면 이동 함수
    private fun moveToDetailRestrauntView() {
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
                DetailRestrauntView()
            )
            .commit()
    }

    companion object {
        const val TAG = "RestrauntView"
    }
}