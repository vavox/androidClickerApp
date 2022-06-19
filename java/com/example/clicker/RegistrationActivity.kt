package com.example.clicker

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.clicker.api.ClickerApi
import com.example.clicker.api.RetrofitHelper
import com.example.clicker.models.RegistrationBody
import com.example.clicker.models.RegistrationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {
    var clickerApi = RetrofitHelper.getInstance().create(ClickerApi::class.java)
    val regResponse = MutableLiveData<RegistrationResponse>()
    lateinit var activity_reg_regButton: Button

    lateinit var emailEditText: EditText
    lateinit var usernameEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var fullnameEditText: EditText
    lateinit var genderEditText: EditText
    lateinit var dateEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        var gender = ""

        activity_reg_regButton = findViewById(R.id.activity_reg_regButton)
        emailEditText = findViewById(R.id.emailEditText)
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        fullnameEditText = findViewById(R.id.fullnameEditText)
        dateEditText = findViewById(R.id.dateEditText)
        genderEditText = findViewById(R.id.genderEditText)

        activity_reg_regButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val fullname = fullnameEditText.text.toString().trim()
            if(genderEditText.text.toString().trim() == "Male"){
                gender = "M"
            } else {
                gender = "F"
            }
            Log.d("gender: ", gender)
            val date = dateEditText.text.toString().trim()
            val regInfo = RegistrationBody(email, username, password, fullname, date, gender)
            clickerApi.createAccount(regInfo).enqueue(object: Callback<RegistrationResponse> {
                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<RegistrationResponse>, response: Response<RegistrationResponse>) {
                    regResponse.value = response.body()
                    Log.d("register: ", regResponse.value.toString())
                }
            })
            if (regResponse.value != null) {
                Toast.makeText(applicationContext,"Account created.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext,"Wrong Email or Password", Toast.LENGTH_SHORT).show()
            }
        }
    }


}