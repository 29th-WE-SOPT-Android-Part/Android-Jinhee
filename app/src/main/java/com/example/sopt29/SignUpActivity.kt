package com.example.sopt29

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sopt29.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(CURRENT_ACTIVITY, "Called onCreate")

        signUpButtonClickEvent()

    }

    private fun signUpButtonClickEvent() {
        binding.btnSignUp.setOnClickListener {

            val userName = binding.etSignupName.text
            val userId = binding.etSignupId.text
            val userPw = binding.etSignupPw.text
            if (userName.isNullOrBlank() || userId.isNullOrBlank() || userPw.isNullOrBlank()) {
                Toast.makeText(this@SignUpActivity, "입력되지 않은 정보가 있습니다.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intent = Intent(this@SignUpActivity, HomeActivity::class.java)
                intent.putExtra("userName", userName.toString())
                    .putExtra("userId", userId.toString())
                    .putExtra("userPw", userPw.toString())

                setResult(
                    RESULT_OK,
                    intent
                )   //setResult() 메소드로 결과를 저장 -> 성공 : RESULT_OK, 실패 : RESULT_CANCEL

                finish()
            }
        }
    }

    companion object {
        const val CURRENT_ACTIVITY = "SignUpActivity"
    }
}