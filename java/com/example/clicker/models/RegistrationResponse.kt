package com.example.clicker.models

data class RegistrationResponse(
    val email: String,
    val username: String,
    val full_name: String,
    val birth_date: String,
    val gender: String,
)