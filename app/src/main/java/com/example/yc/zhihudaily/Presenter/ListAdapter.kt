package com.example.yc.zhihudaily.Presenter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.example.yc.zhihudaily.Model.Datas
import com.example.yc.zhihudaily.Model.Datas.Top_storiesEntity
import com.example.yc.zhihudaily.R
import com.example.yc.zhihudaily.View.StoryBodyActivity

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Date
import java.util.HashMap

class ListAdapter(private val mContext: Context, private var datas: Datas?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal var size = 1
    private val date = HashMap<Int, String>()
    var stories: MutableList<Datas.StoriesEntity> = ArrayList()
    var topStories: MutableList<Top_storiesEntity> = ArrayList()

    /**
     * 首次打开及下拉刷新的数据
     * @param datas
     */
    fun initData(datas: Datas) {
        this.datas = datas
        this.stories.clear()
        this.topStories.clear()
        this.stories = datas.stories as MutableList<Datas.StoriesEntity>
        this.topStories = datas.top_stories as MutableList<Top_storiesEntity>
        date[size] = datas.date
        size = stories.size + 1
    }

    /**
     * 上拉加载的数据
     * @param datas
     */
    fun addData(datas: Datas) {
        this.stories.addAll(datas.stories)
        date[size] = datas.date
        size = stories.size + 1
        notifyItemChanged(size, size + stories.size)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View?
        var viewHolder: RecyclerView.ViewHolder? = null

        when (getItemViewType(viewType)) {
            0 -> {
                view = LayoutInflater.from(mContext).inflate(R.layout.topstory, viewGroup, false)
                viewHolder = ViewPagerHolder(view!!)
            }
            1 -> {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_rv, viewGroup, false)
                viewHolder = StoryViewHolder(view!!)
            }
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        val realPosition = i - 1
        if (viewHolder is ViewPagerHolder) {
            val pagerAdapter = ViewPagerAdapter(mContext, topStories)
            viewHolder.viewPager.adapter = pagerAdapter
        }

        if (viewHolder is StoryViewHolder) {
            val story = stories[realPosition]
            viewHolder.title.text = story.title
            Glide.with(mContext)
                    .load(story.images!![0])
                    .into(viewHolder.image)
            viewHolder.itemView.tag = story
            viewHolder.itemView.setOnClickListener {
                val intent = Intent(mContext, StoryBodyActivity::class.java)
                val bundle = Bundle()
                bundle.putString("id", story.id.toString())
                intent.putExtras(bundle)
                mContext.startActivity(intent)
            }

            if (date.containsKey(i)) {
                viewHolder.date.visibility = View.VISIBLE
                if (i == 1) {
                    viewHolder.date.text = "今日热闻"
                } else {
                    viewHolder.date.text = formatDate(date[i])
                }
            } else {
                viewHolder.date.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return stories.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            0
        } else {
            1
        }
    }

    class StoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.textView)
        var date: TextView = itemView.findViewById(R.id.dateView)
        var image: ImageView = itemView.findViewById(R.id.imageView)
        private val cardView: View = itemView.findViewById(R.id.cardview)
    }

    class ViewPagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var viewPager: ViewPager = itemView.findViewById(R.id.viewpager)
    }

    fun formatDate(date: String?): String {
        val calendar = Calendar.getInstance()
        val format = SimpleDateFormat("yymmdd")
        var d: Date? = null
        try {
            d = format.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        calendar.time = d
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        var str = ""
        when (day) {
            1 -> str = "星期日"
            2 -> str = "星期一"
            3 -> str = "星期二"
            4 -> str = "星期三"
            5 -> str = "星期四"
            6 -> str = "星期五"
            7 -> str = "星期六"
            else -> {
            }
        }
        return date!!.substring(4, 6) + "月" + date.substring(6) + "日" + "    " + str
    }

}