package com.example.sopt29

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sopt29.databinding.FragmentFollowerBinding
import com.example.sopt29.databinding.FragmentRepoBinding

class RepoFragment : Fragment() {
    private var _binding: FragmentRepoBinding? = null
    private val binding get() = _binding ?: error("View 를 참조하기위해 binding 이 초기화 되지 않았습니다.")

    private lateinit var repoListAdapter: RepositoryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoBinding.inflate(
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
        repoListAdapter = RepositoryListAdapter()

        // 2. RecyclerView 에 어뎁터를 우리가 만든 어뎁터로 만들기
        binding.rcvRepoList.adapter = repoListAdapter
    }

    private fun setFollowingListData() {
        repoListAdapter.repoList.addAll(
            listOf<RepositoryInfo>(
                RepositoryInfo(
                    repoName = "진희 레포 ",
                    repoInfo = "jinheejijinheenheejinheejinheejinheejinhee"
                ),
                RepositoryInfo(
                    repoName = "안녕 레포 ",
                    repoInfo = "hellohellohellohellohellohellohellohellohello"
                ),
                RepositoryInfo(
                    repoName = "진희 레포 11",
                    repoInfo = "jinheejijinheenheejinheejinheejinheejinhee"
                ),
                RepositoryInfo(
                    repoName = "안녕 레포 11",
                    repoInfo = "hellohellohellohellohellohellohellohellohello"
                ),
                RepositoryInfo(
                    repoName = "안녕 레포 22",
                    repoInfo = "hellohellohellohellohellohellohellohellohello"
                )
            )
        )

        binding.rcvRepoList.layoutManager = GridLayoutManager(context,2)
        repoListAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
