package com.example.ckprojectstructure_android.presentation.main

import android.os.Bundle
import com.example.ckprojectstructure_android.R
import com.example.ckprojectstructure_android.presentation.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpView()
        setUpViewModel()
    }

    override fun setUpView() {
    }

    private fun setUpViewModel() {
    }

}