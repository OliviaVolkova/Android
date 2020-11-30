package com.example.application

import java.util.*

object Musics {

    var list: LinkedList<Music> = LinkedList( listOf(
        Music(
            1,
            "My kind of woman",
            "Mac Demarco",
            R.raw.mykind,
            R.raw.g
        ),
        Music(
            2,
            "Track03",
            "Хаски",
            R.raw.track03,
            R.raw.r
        ),
        Music(
            3,
            "Pretty when you cry",
            "Lana del rey",
            R.raw.pretty,
            R.raw.l
        ),
        Music(4, "Иуда", "Хаски", R.raw.iyda, R.raw.r)
    ))

    fun getAllMusic():List<Music> = list

    fun nextMusic(id: Int) : Music?{
        for(i in 0 until list.size){
            if(list[i].id == id){
                if(i+1 < list.size) return list[i+1]
            }
        }
        return null
    }

    fun previousMusic(id:Int): Music?{
        for(i in 0 until list.size){
            if(list[i].id == id){
                if(i-1 >= 0) return list[i-1]
            }
        }
        return null
    }





}