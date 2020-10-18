package com.example.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.list.Cats.getCatById
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        var id = intent.extras?.getInt("id")
        var cat = getCatById(id)
        profile_pic.setImageResource(cat?.pic ?: R.drawable.ic_launcher_foreground)
        profile_description.text = cat?.description
        profile_id.text = id.toString()
        profile_name.text = cat?.name
    }
}