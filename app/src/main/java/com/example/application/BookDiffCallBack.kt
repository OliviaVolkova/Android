package com.example.application

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import java.util.*

class BookDiffCallBack: DiffUtil.ItemCallback<Book>() {

    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
        (oldItem.name == newItem.name)


    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
        (oldItem == newItem)

    override fun getChangePayload(oldItem: Book, newItem: Book): Any? {
        val bundle = Bundle().apply {
            if(oldItem.name != newItem.name){
                putString("ARG_NAME",newItem.name)
            }
            if(oldItem.description != newItem.description){
                putString("ARG_DESCRIPTION",newItem.description)
            }
        }
        return if(bundle.isEmpty) null else bundle
    }

}