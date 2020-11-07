package com.example.application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card.view.*

class CardAdapter(
    var cards:List<Card>
) : RecyclerView.Adapter<CardHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder =
        CardHolder.create(parent)

    override fun getItemCount(): Int = cards.size

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(cards[position])
    }


}