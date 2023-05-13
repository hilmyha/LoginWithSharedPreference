package com.example.loginwithsharedpreference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.loginwithsharedpreference.DB.DBHelper

class RegisterActivity : AppCompatActivity() {

    lateinit var eemail: EditText
    lateinit var epassword: EditText
    lateinit var efullname: EditText
    lateinit var btnregister: Button
    lateinit var btncancel: Button
    lateinit var userDBHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        eemail = findViewById(R.id.EditEmailRegister)
        epassword = findViewById(R.id.EditPasswordRegister)
        efullname= findViewById(R.id.EditFullnameRegister)
        btnregister = findViewById(R.id.btnsubmitregister)
        btncancel = findViewById(R.id.btncancelregister)
        userDBHelper = DBHelper(this)
    }

    fun registerme(view: View) {
        var iemail = eemail.text.toString()
        var ipassword = epassword.text.toString()
        var ifullname = efullname.text.toString()

        var cekuser = userDBHelper.cekUser(iemail)
        var status = "Gagal"

        if (cekuser == "0") {
            userDBHelper.RegisterUser(iemail, ipassword, ifullname)
            status = "Sukses"
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val toast: Toast = Toast.makeText(this, status, Toast.LENGTH_LONG)
        toast.show()
    }

    fun cancelme(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}