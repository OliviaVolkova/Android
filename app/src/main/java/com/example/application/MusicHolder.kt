package com.example.application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.music_item.view.*

class MusicHolder(
        override val containerView: View,
        private val callBack: (Music) -> Unit
) : RecyclerView.ViewHolder(containerView),LayoutContainer {

    companion object{
        fun createHolder(parent: ViewGroup,callBack: (Music) -> Unit): MusicHolder =
            MusicHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.music_item, parent, false),
                callBack
            )
    }

    fun bind(audio: Music){
        containerView.name_list.text = audio.name
        containerView.author_list.text = audio.author
        containerView.icon_list.setImageResource(audio.icon)
        containerView.item_music.setOnClickListener {
            callBack.invoke(audio)
        }
    }




}