package com.example.sopt29.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sopt29.databinding.FragmentFollowerBinding


class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding ?: error("View 를 참조하기위해 binding 이 초기화 되지 않았습니다.")

    private lateinit var followerAdapter: FollowerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFollowingAdapter()
        setFollowingListData()
    }

    private fun setFollowingAdapter() {
        // 1. 우리가 사용할 어뎁터의 초기 값을 넣어준다
        followerAdapter = FollowerAdapter()

        // 2. RecyclerView 에 어뎁터를 우리가 만든 어뎁터로 만들기
        binding.rcvFollowerList.adapter = followerAdapter
    }

    private fun setFollowingListData() {
        followerAdapter.followerList.addAll(
            listOf<FollowerInfo>(
                FollowerInfo(
//                    userProfile = "",
                    userName = "jinhee",
                    userInfo = "jinheejijinheenheejinheejinheejinheejinhee"
                ),
                FollowerInfo(
//                    userProfile = "",
                    userName = "hello",
                    userInfo = "hellohellohellohellohellohellohellohellohello"
                ),
                FollowerInfo(
//                    userProfile = "",
                    userName = "Hi",
                    userInfo = "jinheejijinheenheejinheejinheejinheejinhee"
                ),
                FollowerInfo(
//                    userProfile = "",
                    userName = "Luulu",
                    userInfo = "jinheejijinheenheejinheejinheejinheejinhee"
                ),
                FollowerInfo(
//                    userProfile = "",
                    userName = "kotlin",
                    userInfo = "jinheejijinheenheejinheejinheejinheejinhee"
                ),
                FollowerInfo(
//                    userProfile = "",
                    userName = "java",
                    userInfo = "jinheejijinheenheejinheejinheejinheejinhee"
                ),
            )
        )

        followerAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
