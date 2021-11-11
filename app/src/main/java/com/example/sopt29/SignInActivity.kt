package com.example.sopt29

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sopt29.api.ApiService
import com.example.sopt29.api.request.RequestSignIn
import com.example.sopt29.api.response.ResponseSignIn
import com.example.sopt29.databinding.ActivitySignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    private val signUpActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            //데이터를 받아서 할 일이 들어가는 칸
            Log.e("eee",it.data?.extras?.getString("userId").toString())

            if (it.resultCode == Activity.RESULT_OK) {
                if (intent != null) {
                    Log.e("eee",it.data?.extras?.getString("userId").toString())
                    binding.etLoginId.setText(it.data?.extras?.getString("userId"))
                    binding.etLoginPw.setText(it.data?.extras?.getString("userPw"))
                }
            }

        }

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
                val requestSignIn = RequestSignIn(
                    userId.toString(),
                    userPw.toString()
                )

                val call: Call<ResponseSignIn> = ApiService.soptService.postSingIn(requestSignIn)

                call.enqueue(object: Callback<ResponseSignIn> {
                    override fun onResponse(
                        call: Call<ResponseSignIn>,
                        response: Response<ResponseSignIn>
                    ) {
                        if(response.isSuccessful) {
                            val data = response.body()?.data
                            Toast.makeText(this@SignInActivity, "${data?.name} 님 환영합니다.", Toast.LENGTH_SHORT).show()
                            startMainActivity()
                        } else {
                            Log.d("server connect : SignIn", "error")
                            Log.d("server connect : SignIn", "$response.errorBody()")
                            Log.d("server connect : SignIn", response.message())
                            Log.d("server connect : SignIn", "${response.code()}")
                            Toast.makeText(this@SignInActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                        Toast.makeText(this@SignInActivity, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

    private fun startMainActivity(){
        val intent = Intent(this@SignInActivity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun signUpClickEvent() {
        binding.tvSignUp.setOnClickListener {

            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)

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