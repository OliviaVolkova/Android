package com.example.application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.addCategory(Intent.CATEGORY_INFO)
            intent.putExtra("myString",text.text.toString())
            if(intent.resolveActivity(packageManager)!=null) {
                startActivityForResult(intent,1);
            }
            else{
                Toast.makeText(this,"Не найдено",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            text.setText(data?.getStringExtra("my_answer") ?: "Не найдено текста")
        }
    }


}
