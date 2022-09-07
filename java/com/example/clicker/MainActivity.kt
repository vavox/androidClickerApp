package com.example.clicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.clicker.api.ClickerApi
import com.example.clicker.api.RetrofitHelper
import com.example.clicker.models.LogInBody
import com.example.clicker.models.Token
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var clickerApi = RetrofitHelper.getInstance().create(ClickerApi::class.java)
    var job: Job? = null
    val token = MutableLiveData<Token>()

    lateinit var activity_main_loginButton: Button
    lateinit var activity_main_regButton: Button

    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activity_main_loginButton = findViewById(R.id.activity_main_loginButton)
        activity_main_regButton = findViewById(R.id.activity_main_regButton)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        activity_main_loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val loginInfo = LogInBody(email, password)
            clickerApi.getToken(loginInfo).enqueue(object: Callback<Token>{
                override fun onFailure(call: Call<Token>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    token.value = response.body()
                    Log.d("login: ", token.value.toString())
                }
            })
            if (token.value != null) {
                val intent = Intent(this@MainActivity, GameActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext,"Wrong Email or Password",Toast.LENGTH_SHORT).show()
            }
        }

        activity_main_regButton.setOnClickListener{
            val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}
