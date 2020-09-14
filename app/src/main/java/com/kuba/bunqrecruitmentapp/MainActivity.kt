package com.kuba.bunqrecruitmentapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kuba.bunqrecruitmentapp.utils.KeyController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("Key", KeyController.getPEMFormattedKey())
    }
}