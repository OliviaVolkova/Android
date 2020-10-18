package com.example.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CatAdapter(
    private val list: List<Cat>,
    private val itemClick: (Int) -> Unit
) : RecyclerView.Adapter<CatHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatHolder =  CatHolder.create(parent, itemClick)


    override fun onBindViewHolder(holder: CatHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
