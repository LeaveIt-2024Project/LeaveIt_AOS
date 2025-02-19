package com.example.leaveit.presentation.myprofile

import android.content.Context
import android.widget.ArrayAdapter

class MyProfileChangeAdapter(
    context: Context,
    items: List<MyProfileChangeModel>
    ) : ArrayAdapter<MyProfileChangeModel>(context, android.R.layout.simple_spinner_item, items) {
        init {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
}