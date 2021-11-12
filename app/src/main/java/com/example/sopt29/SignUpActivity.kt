package com.example.sopt29

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sopt29.api.ApiService
import com.example.sopt29.api.request.RequestSignUp
import com.example.sopt29.api.response.ResponseSignUp
import com.example.sopt29.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                val requestSignUp = RequestSignUp(
                    userPw.toString(),
                    userName.toString(),
                    userPw.toString()
                )

                val call: Call<ResponseSignUp> = ApiService.soptService.postSignUp(requestSignUp)

                call.enqueue(object: Callback<ResponseSignUp> {
                    override fun onResponse(
                        call: Call<ResponseSignUp>,
                        response: Response<ResponseSignUp>
                    ) {
                        if(response.isSuccessful) {
                            val data = response.body()
                            Toast.makeText(this@SignUpActivity, data?.message, Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@SignUpActivity, MainActivity::class.java)
                            intent.putExtra("userName", userName.toString())
                                .putExtra("userId", userId.toString())
                                .putExtra("userPw", userPw.toString())

                            setResult(
                                RESULT_OK,
                                intent
                            )   //setResult() 메소드로 결과를 저장 -> 성공 : RESULT_OK, 실패 : RESULT_CANCEL


                        } else {
                            Log.d("server connect : SignUp", "error")
                            Log.d("server connect : SignUp", "$response.errorBody()")
                            Log.d("server connect : SignUp", response.message())
                            Log.d("server connect : SignUp", "${response.code()}")
                            Toast.makeText(this@SignUpActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
                        Toast.makeText(this@SignUpActivity, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    }
                })

                finish()
            }
        }
    }

    companion object {
        const val CURRENT_ACTIVITY = "SignUpActivity"
    }
}