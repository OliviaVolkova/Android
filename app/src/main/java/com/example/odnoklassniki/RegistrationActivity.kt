package com.example.odnoklassniki

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.registration_activity.*

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)
        login_button.setOnClickListener {
            if(name.text.isEmpty()|| surname.text.isEmpty()||age.text.isEmpty()||emailuser.text.isEmpty()){
                Toast.makeText(this, "Пожалуйста заполните все поля", Toast.LENGTH_SHORT).show()
            }
            else {
                var intent = Intent(this, MainActivity::class.java)
                intent.putExtra("name", name.text.toString())
                intent.putExtra("surname", surname.text.toString())
                intent.putExtra("age", age.text.toString())
                intent.putExtra("email", emailuser.text.toString())
                startActivity(intent)
            }
        }
    }
}