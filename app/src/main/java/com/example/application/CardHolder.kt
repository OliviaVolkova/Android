package com.example.application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card.view.*

class CardHolder (
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(card : Card){
        itemView.card_title.text = card.title
        itemView.card_description.text = card.desc
        itemView.card_imgs.adapter = ViewPagerAdapter(card.imgs)
    }
    companion object {

        fun create(parent: ViewGroup) = CardHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        )
    }
}