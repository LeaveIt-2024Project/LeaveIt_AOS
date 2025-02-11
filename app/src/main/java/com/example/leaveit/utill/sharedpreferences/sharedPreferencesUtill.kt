package com.example.leaveit.utill.sharedpreferences

import android.content.Context

object sharedPreferencesUtill {
    private const val PREFS_NAME = "ShowPlaceViewData"
    private const val DEFAULT_VALUE = "defaultValue"

    fun setData(context : Context,key : String, value : String){
       val data = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
        data.edit().apply{
                putString(key,value)
                apply()
        }
    }

    fun getData(context: Context,key : String) : String{
        val data =  context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
        return data.getString(key,DEFAULT_VALUE).toString()

    }
}