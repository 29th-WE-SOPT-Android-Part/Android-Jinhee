package com.example.sopt29.ui.profile

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sopt29.R
import com.example.sopt29.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        onClickBtn()

        return root
    }

    private fun onClickBtn() {
        val followerFragment = FollowerFragment()
        val repoFragment = RepoFragment()

        binding.btnFollowerList.setOnClickListener {
            binding.btnFollowerList.isSelected = true
            binding.btnRepoList.isSelected = false

            binding.btnFollowerList.setTextColor(Color.WHITE)
            binding.btnRepoList.setTextColor(Color.BLACK)

            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_home_rcv, followerFragment)
                .commit()
        }
        binding.btnRepoList.setOnClickListener {
            binding.btnRepoList.isSelected = true
            binding.btnFollowerList.isSelected = false

            binding.btnRepoList.setTextColor(Color.WHITE)
            binding.btnFollowerList.setTextColor(Color.BLACK)

            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_home_rcv, repoFragment)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}