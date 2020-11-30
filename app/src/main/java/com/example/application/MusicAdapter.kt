package com.example.application

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MusicAdapter(
    private val list: List<Music>,
    private val callBack: (Music) -> Unit
) : RecyclerView.Adapter<MusicHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicHolder =
        MusicHolder.createHolder(
            parent,
            callBack
        )

    override fun onBindViewHolder(holder: MusicHolder, position: Int) = holder.bind(list[position])


    override fun getItemCount(): Int = list.size

}