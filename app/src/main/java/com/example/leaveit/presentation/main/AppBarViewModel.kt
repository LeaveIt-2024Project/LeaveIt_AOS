package com.example.leaveit.presentation.mainpageview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppBarViewModel : ViewModel() {
    private val _selectedFragment = MutableLiveData<String>()
    val selectedFragment: LiveData<String> = _selectedFragment

    fun selectFragment(fragmentName: String) {
        _selectedFragment.value = fragmentName
    }
}