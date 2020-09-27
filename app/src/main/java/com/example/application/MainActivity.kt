package com.example.application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.application.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var clientName : String = intent.getStringExtra("name") ?: ""
        var clientSurname : String = intent.getStringExtra("surname") ?: ""
        name.text = "$clientName $clientSurname"
        age.text = intent.getStringExtra("age") ?: ""
        email.text = intent.getStringExtra("email") ?: ""
        var fl = true

        editName.setText(name.text)
        editAge.setText(age.text)
        editEmail.setText(email.text)

        editButton.setOnClickListener {
            if(fl) {

                editAge.visibility = View.VISIBLE
                age.visibility = View.INVISIBLE

                editName.visibility = View.VISIBLE
                name.visibility = View.INVISIBLE

                editEmail.visibility = View.VISIBLE
                email.visibility = View.INVISIBLE

                age.text = editAge.text.toString()
                name.text = editName.text.toString()
                email.text = editEmail.text.toString()

                fl = false
            }
            else{
                editAge.visibility = View.INVISIBLE
                age.visibility = View.VISIBLE

                editName.visibility = View.INVISIBLE
                name.visibility = View.VISIBLE

                editEmail.visibility = View.INVISIBLE
                email.visibility = View.VISIBLE


                age.text = editAge.text.toString()
                name.text = editName.text.toString()
                email.text = editEmail.text.toString()
                fl = true
            }
        }
        exit.setOnClickListener {
            var intent = Intent(this, RegistrationActivity:: class.java)
            startActivity(intent)
        }
    }

}
