package com.example.jobportal.ui.bottomsheet.congrats_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jobportal.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CongratsBottomsheet : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_congrats_bottomsheet, container, false)
    }
}