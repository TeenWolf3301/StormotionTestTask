package com.teenwolf3301.stormotiontesttask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.teenwolf3301.stormotiontesttask.databinding.ActivityMainBinding
import com.teenwolf3301.stormotiontesttask.utility.APP_ACTIVITY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
    }
}