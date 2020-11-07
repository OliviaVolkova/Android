package com.example.application

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class BookAdapter(
    private val itemClick: (Int) -> Unit
) : ListAdapter<Book,BookHolder>(BookDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder =  BookHolder.create(parent, itemClick)


    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int, payloads: MutableList<Any>) {
        if(payloads.isEmpty()){
            super.onBindViewHolder(holder, position, payloads)
        }
        else{
            (payloads[0] as Bundle).also{
                holder.updateFromBundle(it)
            }
        }

    }
//    fun updateDataSource(new: List<Book>){
//        val callBack = BookDiffCallBack(list,new)
//        val result = DiffUtil.calculateDiff(callBack, true)
//        result.dispatchUpdatesTo(this)
//        list = mutableListOf<Book>().apply{addAll(new)}
//    }
}
