package com.example.application

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        view.text  = intent.getStringExtra("myString") ?: "Не найдено текста"
        button2.setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra("my_answer", text2.text.toString())
            })
            finish()
        }
    }


}
