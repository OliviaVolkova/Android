package com.example.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.adapter = CatAdapter(Cats.getCats().toList()){
            val intent =Intent(this, SecondActivity::class.java)
            intent.putExtra("id",it)
            startActivity(intent)
        }
        rv.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
    }

}
