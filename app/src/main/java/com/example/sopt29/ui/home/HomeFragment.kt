package com.example.sopt29.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sopt29.FollowerViewPagerAdapter
import com.example.sopt29.R
import com.example.sopt29.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initTabLayout()
    }

    private fun initAdapter() {
        binding.viewpagerFollow.adapter = FollowerViewPagerAdapter(requireActivity())
    }

    private fun initTabLayout() {
        val tabList = listOf("팔로잉", "팔로워")

        TabLayoutMediator(binding.tabLayoutFollow, binding.viewpagerFollow) { tab, position ->
            tab.text = tabList[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}