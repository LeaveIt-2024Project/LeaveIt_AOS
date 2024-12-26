package com.example.leaveit.presentation.review.postview

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.leaveit.R
import com.example.leaveit.databinding.FragmentSelectphotoviewBinding
import com.example.leaveit.presentation.review.postview.adapter.SelectPlacePhotoAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


@AndroidEntryPoint
class SelectPhotoFragmentView : Fragment() {
    private lateinit var binding: FragmentSelectphotoviewBinding
    private lateinit var adapter: SelectPlacePhotoAdapter
    private val selectedFiles = mutableListOf<File>() // 생성된 파일 리스트
    private val tempUriImage = mutableListOf<Uri>()


    // 프레그먼트간 데이터 공유를 위해선 viewModels()가 아닌  activityViewModels()로 해야함.
    // viewModels는 프레그먼트간 독립적으로 데이터를 다룰 때 사용
    private val viewModel: PostReviewViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectphotoviewBinding.inflate(layoutInflater)
        initAdapter()

        changeTopText("사진을 선택하세요")
//https://github.com/williamyyu/SimpleRatingBar  별점

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRatingValue()

        binding.ConfirmBtnForPostReview.setOnClickListener {

            // 별점 선택 안하면 다음 페이지로 이동 불가
            if (viewModel.starCount.value == null) {
                return@setOnClickListener
            } else {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(
                        com.example.leaveit.R.id.selectregion_fragment_container,
                        WriteContentFragmentView()
                    )
                    .commit()
            }
        }

        binding.addPhotoBtn.setOnClickListener {
            checkAndRequestPermission()
        }

        binding.reselectPhotoBtn.setOnClickListener {
            checkAndRequestPermission()
        }

        binding.ConfirmBtnForPostReview.setOnClickListener {

            val nextFragment = WriteContentFragmentView()

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.selectregion_fragment_container, nextFragment)
                .addToBackStack(null) // 뒤로가기 하려면 스택에 쌓아야됌.
                .commit()

        }
    }

    fun changeTopText(text: String) { // 상단 탭바 타이틀 변경 함수
        viewModel.setTopAppBarTitleText(text)
    }

    private fun initAdapter() { // 리사이클러뷰 초기화 함수

        //리사이클러뷰에 레이아웃매니저 설정
        adapter = SelectPlacePhotoAdapter()
        adapter.submitList(emptyList())
        binding.ShowPlaceImageViewPager.adapter = adapter
        binding.showPlaceIndicator.attachTo(binding.ShowPlaceImageViewPager)
    }

    // 뷰모델에 사용자가 지정한 starCount 지정
    fun getRatingValue() {
        binding.rotationRatingBar.setOnRatingChangeListener { ratingBar, rating, fromUser ->
            viewModel.setStarCount(rating.toInt())
        }
    }

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openGallery()
        } else {
            Toast.makeText(requireContext(), "권한이 필요합니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val clipData = result.data?.clipData
            val uriList = mutableListOf<Uri>()

            if (clipData != null) {
                val itemCount = clipData.itemCount
                if (itemCount > 3) {
                    Toast.makeText(requireContext(), "최대 3장까지만 선택할 수 있습니다.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    for (i in 0 until itemCount) {
                        uriList.add(clipData.getItemAt(i).uri)
                    }
                }
            } else {
                result.data?.data?.let { uriList.add(it) }
            }

            if (uriList.isEmpty()) {
                Toast.makeText(requireContext(), "사진을 1장 이상 선택해 주세요.", Toast.LENGTH_SHORT).show()
            } else {
                handleSelectedImages(uriList)
            }
        }
    }

    private fun checkAndRequestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.READ_MEDIA_IMAGES
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
            } else {
                openPhotoPicker()
            }
        } else {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            } else {
                openGallery()
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }
        galleryLauncher.launch(intent)
    }

    private fun handleSelectedImages(uriList: List<Uri>) {
        // 선택된 이미지 리스트를 처리
        uriList.forEach { uri ->
            Log.d("SelectedImage", "URI: $uri")

            createFileFromUri(uri)?.let { selectedFiles.add(it) }
            tempUriImage.add(uri)
        }
        adapter.submitList(uriList)
        viewModel.setImageList(selectedFiles)
        viewModel.setTempImageList(tempUriImage)

        // 이미지가 선택되면 숨겨진 뷰들 올라오기
        toggleView()
    }

    private fun createFileFromUri(uri: Uri): File? {
        return try {
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            val file = File(requireContext().cacheDir, "${System.currentTimeMillis()}.jpg")
            val outputStream = file.outputStream()

            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // PhotoPicker를 호출하는 함수
    private fun openPhotoPicker() {
        // 최대 3개의 이미지를 선택할 수 있도록 설정
        val photoPickerIntent = Intent(MediaStore.ACTION_PICK_IMAGES).apply {
            putExtra(MediaStore.EXTRA_PICK_IMAGES_MAX, 3) // 최대 선택 가능 이미지 수
        }

        photoPickerLauncher.launch(photoPickerIntent)
    }

    // PhotoPicker 결과를 처리하는 ActivityResultLauncher 등록
    private val photoPickerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.clipData?.let { clipData ->
                // 여러 이미지 선택 시 ClipData 처리
                val selectedUris = mutableListOf<Uri>()
                for (i in 0 until clipData.itemCount) {
                    selectedUris.add(clipData.getItemAt(i).uri)
                }

                if (selectedUris.size > 3) {
                    Toast.makeText(context, "최대 3개의 이미지만 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    // 선택된 이미지 URI 리스트 처리
                    handleSelectedImages(selectedUris)
                }
            } ?: result.data?.data?.let { uri ->
                // 단일 이미지 선택 시 처리
                handleSelectedImages(listOf(uri))
            }
        }
    }

    fun toggleView() { // 사진 선택되었을 때 View 처리
        binding.addPhotoLayout.visibility = View.INVISIBLE
        binding.ShowPlaceImageViewPager.visibility = View.VISIBLE
        binding.showPlaceIndicator.visibility = View.VISIBLE
        binding.ConfirmBtnForPostReview.visibility = View.VISIBLE
        binding.ShowYourPlacePhotoTextView.text = ""
        binding.reselectPhotoBtn.visibility = View.VISIBLE
    }

    companion object {
        const val TAG = "SelectPhotoFragmentView"
        const val PERMISSION_CODE = 101
    }

}