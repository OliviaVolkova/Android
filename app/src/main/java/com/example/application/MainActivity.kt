package com.example.application
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_2.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        var iv: ImageView? = null
        bnv.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.iv1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, Fragment1())
                        .commit()
                    true
                }
                R.id.iv2 -> {
                    supportFragmentManager.beginTransaction()
                    .replace(R.id.mainFragment, Fragment2())
                    .commit()
                    true
                }
                R.id.iv3 -> {
                    supportFragmentManager.beginTransaction()
                   .replace(R.id.mainFragment, Fragment3())
                   .commit()
                    true
                }
                else -> false
            }
        }
    }

}
