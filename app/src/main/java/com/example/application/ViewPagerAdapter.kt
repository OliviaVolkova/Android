package com.example.application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.card.view.*

class ViewPagerAdapter(
    val imgs: List<Int>
): PagerAdapter(){

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
    override fun getCount(): Int = imgs.size;

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`;

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.card,container,false)
        view.imageView.setImageResource(imgs.get(position))
        container.addView(view)
        return view

    }
}
