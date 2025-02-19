package com.example.leaveit

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.leaveit.databinding.ActivityMainBinding
import com.example.leaveit.presentation.mainpageview.AppBarViewModel
import com.example.leaveit.presentation.mainpageview.MainPageView
import com.example.leaveit.presentation.myprofile.MyProfileChangeView
import com.example.leaveit.presentation.placeview.place.showplaceview.ShowPlaceView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: AppBarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)
       // changeTopAppText()

        setBottomNavigationView()
        if(savedInstanceState == null) {
//            binding.bottomnavigationView.selectedItemId = R.id.bottomHome
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, MainPageView())
                .commit()
        }

        viewModel.selectedFragment.observe(this) { fragmentName ->
            when (fragmentName) {
                "ShowPlacePage" -> {
                    println("왼쪽 탭 선택")
                    changeToolbar(null, "떠나자IT")
                }
                "MainPage" -> {
                    println("메인 탭 선택")
                    changeToolbar(R.menu.testtopbar, "테스트명")
                }
                "SelectPlaceForReview" -> {
                    println("오른쪽 탭 선택")
                    changeToolbar(R.menu.selectplaceforreviewmenu, "지역을 선택하세요")
                }
                "MyProfilePage" -> {
                    changeToolbar(R.menu.mypagemenu, "떠나자IT")
                }
                "MyProfileChange" -> {
                    changeToolbar(null, "떠나자IT")
                }
//                유동적으로 바꿈
            }
        }

    }
    fun setBottomNavigationView() {
        binding.bottomnavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomMap -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ShowPlaceView())
                        .commit()
                    viewModel.selectFragment("ShowPlacePage")
                    true
                }
                R.id.bottomHome -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MainPageView())
                        .commit()
                    viewModel.selectFragment("MainPage")
                    true
                }
                R.id.bottomMenu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MyProfileChangeView())
                        .commit()
                    viewModel.selectFragment("MyProfileChange")
                    true
                }

                else -> false
            }
        }
    }

    private fun changeToolbar(menuId: Int?, title: String) {
        binding.topAppBar.menu.clear()
        binding.topAppBar.title = title

        // MyProfilePage에서만 뒤로가기 버튼 활성화
        if (title == "떠나자IT" && menuId == R.menu.mypagemenu) {
            binding.topAppBar.setNavigationIcon(R.drawable.backarrow_icon)
            binding.topAppBar.setNavigationOnClickListener {
                onBackPressed()
            }
        } else {
            binding.topAppBar.navigationIcon = null
        }

        if (menuId != null) {
            binding.topAppBar.inflateMenu(menuId)
        }
    }
//    private fun changeTopAppText() {
//        viewModel.topAppBarText.observe(this) {
//            binding.topAppBar.title = it
//        }
//    }

    companion object {
        val TAG = "MainActivity"
    }
}

