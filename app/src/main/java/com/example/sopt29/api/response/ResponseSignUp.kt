package com.example.sopt29.api.response

data class ResponseSignUp (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
)  {
    data class Data(
        val id: Int,
        val name: String,
        val email: String,
        val password: String
    )
}