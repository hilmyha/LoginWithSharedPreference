package com.example.loginwithsharedpreference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.loginwithsharedpreference.DB.DBHelper

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val savedLogin = getSharedPreferences("Login", MODE_PRIVATE)
        val editSavedLogin = savedLogin.edit()
        if (savedLogin.getString("Status", "Off") == "On") {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val editUsername: EditText = findViewById(R.id.EditUsernameLogin)
        val editPassword: EditText = findViewById(R.id.EditPasswordLogin)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val btnRegister: Button = findViewById(R.id.btnregister)
        val userDBHelper = DBHelper(this)

        btnLogin.setOnClickListener() {
            val emailku = editUsername.text.toString()
            val passku = editPassword.text.toString()
            val cekLogin = userDBHelper.cekLogin(emailku, passku)

            if (cekLogin == "1") {
                editSavedLogin.putString("Email", emailku)
                editSavedLogin.putString("Password", passku)
                editSavedLogin.putString("Status", "On")
                editSavedLogin.commit()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val toast: Toast = Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_LONG)
                toast.show()
            }
        }

        btnRegister.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}