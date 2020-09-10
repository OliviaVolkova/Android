package com.example.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var cat1 = Cat("A", "B")
        print(cat1.age)
        cat1.age = 5
        Log.d("tag", cat1.age.toString())

    }


}
