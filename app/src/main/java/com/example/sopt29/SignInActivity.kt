package com.example.sopt29

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sopt29.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(CURRENT_ACTIVITY, "Called onCreate")

        loginButtonClickEvent()
        signUpClickEvent()


    }

    private fun loginButtonClickEvent() {
        binding.btnLogin.setOnClickListener {

            val userId = binding.etLoginId.text
            val userPw = binding.etLoginPw.text
            if (userId.isNullOrBlank() || userPw.isNullOrBlank()) {
                Toast.makeText(this@SignInActivity, "로그인 실패. 아이디/비밀번호를 확인해주세요!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(this@SignInActivity, "${userId.toString()} 님 환영합니다.", Toast.LENGTH_SHORT).show()
                startHomeActivity()
            }
        }
    }

    private fun startHomeActivity(){
        //HomeActivity로 이동
        val intent = Intent(this@SignInActivity, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun signUpClickEvent() {
        binding.tvSignUp.setOnClickListener {

            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)

        }
    }


    companion object {
        const val CURRENT_ACTIVITY = "SignInActivity"
    }

    override fun onStart() {
        super.onStart()
        Log.d(CURRENT_ACTIVITY, "Called onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(CURRENT_ACTIVITY, "Called onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(CURRENT_ACTIVITY, "Called onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(CURRENT_ACTIVITY, "Called onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(CURRENT_ACTIVITY, "Called onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(CURRENT_ACTIVITY, "Called onDestroy")
    }

}