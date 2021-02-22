package com.example.application.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.application.City
import com.example.application.R
import kotlinx.android.extensions.LayoutContainer

class CityAdapter(
    private val itemClick: (Int) -> Unit,
) :ListAdapter<City, CityAdapter.CityViewHolder>
    (object : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean = oldItem == newItem
}) {
    inner class CityViewHolder(override val containerView: View, private val itemClick: (Int) -> Unit) : LayoutContainer, RecyclerView.ViewHolder(containerView) {
        fun bind(city: City) {
            itemView.setOnClickListener {
                itemClick.invoke(city.id)
            }
            tv_city_info.text = city.name
            tv_temp_info.text = city.temperature.toString()
            setColor(city.temperature)
        }

        private fun setColor(temperature: Double) {
            var color: Int = R.color.black
            if (temperature < -25) color = R.color.blue
            if (temperature >= -25 && temperature < -5) color = R.color.light_blue
            if (temperature >= -5 && temperature < 10) color = R.color.green
            if (temperature >= 10 && temperature < 25) color = R.color.yellow
            if (temperature >= 25 && temperature < 40) color = R.color.orange
            if (temperature >= 40) color = R.color.red
            tv_temp_info.setTextColor(ContextCompat.getColor(containerView.context, color))
        }
    }

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder =
    CityViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false), itemClick
    )

override fun onBindViewHolder(holder: CityViewHolder, position: Int) =
    holder.bind(getItem(position))

override fun submitList(list: MutableList<City>?) {
    super.submitList(list?.let { ArrayList(it) })
}
}