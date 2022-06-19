package com.example.clicker.api

import com.example.clicker.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ClickerApi {

    @POST("/api/token/")
    fun  getToken(
        @Body info: LogInBody
    ): Call<Token>

    @POST("/api/register/")
    fun createAccount(
        @Body info: RegistrationBody
    ): Call<RegistrationResponse>
}