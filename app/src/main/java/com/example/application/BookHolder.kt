package com.example.application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item.*

class BookHolder(
    override val containerView: View,
    private val itemClick : (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(book: Book){
        itemName.text = book.name
        itemDesc.text = book.description
        clean.setOnClickListener {
            itemClick.invoke(adapterPosition)
        }
    }

    fun updateFromBundle(bundle: Bundle?){
        if(bundle?.containsKey("ARG_NAME") == true){
            bundle.getString("ARG_NAME").also {
                itemName.text = it
            }
        }
        if(bundle?.containsKey("ARG_DESCRIPTION") == true){
            bundle.getString("ARG_DESCRIPTION").also {
                itemDesc.text = it
            }
        }
    }


    companion object {
        fun create(parent: ViewGroup, itemClick: (Int) -> Unit) = BookHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false), itemClick
        )
    }
}