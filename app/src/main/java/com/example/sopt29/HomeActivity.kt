package com.example.sopt29

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sopt29.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(CURRENT_ACTIVITY, "Called onStart")


    }

    companion object {
        const val CURRENT_ACTIVITY = "HomeActivity"
    }

}