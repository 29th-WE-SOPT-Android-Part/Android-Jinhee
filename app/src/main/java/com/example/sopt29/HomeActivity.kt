package com.example.sopt29

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sopt29.databinding.ActivityHomeBinding
import com.example.sopt29.ui.profile.FollowerFragment
import com.example.sopt29.ui.profile.RepoFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(CURRENT_ACTIVITY, "Called onStart")

        goGitHub()
        onClickBtn()
    }

    fun goGitHub(){
        binding.btnGoGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/HJinhee"))
            startActivity(intent)
        }
    }

    private fun onClickBtn() {
        val followerFragment = FollowerFragment()
        val repoFragment = RepoFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_home_rcv, followerFragment)
            .commit()

        binding.btnFollowerList.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_home_rcv, followerFragment)
                .commit()
        }
        binding.btnRepoList.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_home_rcv, repoFragment)
                .commit()
        }
    }

    companion object {
        const val CURRENT_ACTIVITY = "HomeActivity"
    }

}