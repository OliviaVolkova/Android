package com.example.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item.*


class CatHolder(
    override val containerView: View,
    private val itemClick : (Int) -> Unit
) : RecyclerView.ViewHolder(containerView),LayoutContainer{

    fun bind(cat: Cat){
        itemPic.setImageResource(cat.pic)
        item.text = cat.name
        itemView.setOnClickListener{
            itemClick(cat.id)
        }
    }


    companion object {

        fun create(parent: ViewGroup, itemClick: (Int) -> Unit) = CatHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false), itemClick
        )
    }
}

