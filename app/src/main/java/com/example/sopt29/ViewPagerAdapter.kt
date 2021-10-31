package com.example.sopt29

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sopt29.ui.camera.CameraFragment
import com.example.sopt29.ui.home.HomeFragment
import com.example.sopt29.ui.profile.ProfileFragment

class ViewPagerAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProfileFragment()
            1 -> HomeFragment()
            2 -> CameraFragment()
            else -> ProfileFragment()
        }
    }
}