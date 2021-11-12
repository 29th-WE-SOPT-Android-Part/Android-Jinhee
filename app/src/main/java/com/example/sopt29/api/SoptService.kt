package com.example.sopt29.api

import com.example.sopt29.api.request.RequestSignIn
import com.example.sopt29.api.request.RequestSignUp
import com.example.sopt29.api.response.ResponseSignIn
import com.example.sopt29.api.response.ResponseSignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SoptService {
    @Headers("Content-Type:application/json")
    @POST("/user/signup")
    fun postSignUp(
        @Body body: RequestSignUp
    ) : Call<ResponseSignUp>

    @Headers("Content-Type:application/json")
    @POST("/user/login")
    fun postSingIn(
        @Body body: RequestSignIn
    ) : Call<ResponseSignIn>
}