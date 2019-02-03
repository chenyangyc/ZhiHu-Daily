package com.example.yc.zhihudaily.Presenter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.example.yc.zhihudaily.Model.Datas
import com.example.yc.zhihudaily.R
import com.example.yc.zhihudaily.View.StoryBodyActivity

import java.util.ArrayList

class ViewPagerAdapter(private val context: Context, list: List<Datas.Top_storiesEntity>) : PagerAdapter() {
    internal var topStoriesEntities: MutableList<Datas.Top_storiesEntity> = ArrayList()

    init {
        topStoriesEntities.addAll(list)
    }

    override fun getCount(): Int {
        return topStoriesEntities.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val id = arrayOf(topStoriesEntities[position].id.toString())
        val view = LayoutInflater.from(context).inflate(
                R.layout.topstory, container, false)

        val textView = view.findViewById<TextView>(R.id.topTitle)
        val imageView = view.findViewById<ImageView>(R.id.topImage)
        textView.text = topStoriesEntities[position].title
        Glide.with(context)
                .load(topStoriesEntities[position].image)
                .into(imageView)
        view.tag = topStoriesEntities[position]
        view.setOnClickListener {
            val intent = Intent(context, StoryBodyActivity::class.java)
            val bundle = Bundle()
            bundle.putString("id", id[0])
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: View, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }

}
