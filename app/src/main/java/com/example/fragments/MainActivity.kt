package com.example.fragments

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var iv: ImageView? = null

        iv1.setOnClickListener {
            supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_in_right)
                .replace(R.id.mainFragment, Fragment1())
                .commit()
            it.isSelected = true
            iv?.isSelected = false
            iv = iv1
        }

        iv2.setOnClickListener {
            supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_in_right)
                .replace(R.id.mainFragment, Fragment2())
                .commit()
            it.isSelected = true
            iv?.isSelected = false
            iv = iv2
        }

        iv3.setOnClickListener {
            supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_in_right)
                .replace(R.id.mainFragment, Fragment3())
                .commit()
            it.isSelected = true
            iv?.isSelected = false
            iv = iv3
        }

        iv4.setOnClickListener {
            supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_in_right)
                .replace(R.id.mainFragment, Fragment4())
                .commit()
            it.isSelected = true
            iv?.isSelected = false
            iv = iv4
        }

        iv5.setOnClickListener {
            supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_left,R.anim.slide_in_right)
                .replace(R.id.mainFragment, Fragment5())
                .commit()
            it.isSelected = true
            iv?.isSelected = false
            iv = iv5
        }
    }
}
