package com.example.application

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaDataSource
import android.media.MediaMetadataRetriever
import java.io.Serializable


data class Music(
    val id : Int,
    val name : String,
    val author: String,
    val icon : Int,
    val recourse : Int
)  : Serializable