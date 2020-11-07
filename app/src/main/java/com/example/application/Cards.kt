package com.example.application

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*

object Cards {
    var list = ArrayList<Card>()

    init {
        var b1 = Card("Martin Eden", getListOfImages(1),"Jack London")
        var b2 = Card("The necklace",getListOfImages(2),"Guy de Maupassant ")
        var b3 = Card("The 13th tale",getListOfImages(3),"Diane Setterfield")
        var b4 = Card("Lie to me",getListOfImages(4),"Paul Ekman")
        var b5 = Card("The live",getListOfImages(5),"Guy de Maupassant")
        var b6 = Card("Good girls go to heaven",getListOfImages(6),"Ute Erhart")
        list.add(b1)
        list.add(b2)
        list.add(b3)
        list.add(b4)
        list.add(b5)
        list.add(b6)
    }

    fun getCards(): List<Card>{
        return list;
    }
    fun getListOfImages(i: Int): List<Int>{
        var a : List<Int>;
        when(i){
            1 -> a = arrayListOf(R.drawable.r3, R.drawable.r4)
            2 -> a = arrayListOf(R.drawable.r1, R.drawable.r2)
            3 -> a = arrayListOf(R.drawable.rr, R.drawable.add)
            4 -> a = arrayListOf(R.drawable.l1, R.drawable.add)
            5 -> a = arrayListOf(R.drawable.y1, R.drawable.add)
            6 -> a = arrayListOf(R.drawable.t1, R.drawable.add)
            else ->  a = listOf(R.drawable.first, R.drawable.add)
        }
        return a;
    }


}