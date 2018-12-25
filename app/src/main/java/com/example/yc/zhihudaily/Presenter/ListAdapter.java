package com.example.yc.zhihudaily.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yc.zhihudaily.Model.Datas;
import com.example.yc.zhihudaily.Model.Datas.Top_storiesEntity;
import com.example.yc.zhihudaily.R;
import com.example.yc.zhihudaily.View.StoryBodyActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListAdapter extends RecyclerView.Adapter {

    int size = 1;
    private Datas datas;
    private Context mContext;
    private Map<Integer,String> date = new HashMap<>();
    public List<Datas.StoriesEntity> stories = new ArrayList<>();
    public List<Top_storiesEntity> topStories = new ArrayList<>();

    public ListAdapter(Context context, Datas datas) {
        this.mContext = context;
        this.datas = datas;
    }

    /**
     * 首次打开及下拉刷新的数据
     * @param datas
     */
    public void initData(Datas datas){
        this.datas = datas;
        this.stories.clear();
        this.topStories.clear();
        this.stories = datas.getStories();
        this.topStories = datas.getTop_stories();
        date.put(size,datas.getDate());
        size = stories.size() + 1;
    }

    /**
     * 上拉加载的数据
     * @param datas
     */
    public void addData(Datas datas){
        this.stories.addAll(datas.getStories());
        date.put(size,datas.getDate());
        size = stories.size() + 1;
        notifyItemChanged(size,size + stories.size());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;

        switch(getItemViewType(viewType)){
            case 0:
                view = LayoutInflater.from(mContext).inflate(R.layout.topstory,viewGroup,false);
                viewHolder = new ViewPagerHolder(view);
                break;
            case 1:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_rv, viewGroup,false);
                viewHolder = new StoryViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final int realPosition = i - 1;
        if(viewHolder instanceof ViewPagerHolder) {
            ViewPagerHolder viewPagerHolder = (ViewPagerHolder) viewHolder;
            ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(mContext,topStories);
            viewPagerHolder.viewPager.setAdapter(pagerAdapter);
        }

        if(viewHolder instanceof StoryViewHolder) {
            StoryViewHolder storyViewHolder = (StoryViewHolder) viewHolder;
            final Datas.StoriesEntity story = stories.get(realPosition);
            storyViewHolder.title.setText(story.getTitle());
            Glide.with(mContext)
                    .load(story.getImages().get(0))
                    .into(storyViewHolder.image);
            viewHolder.itemView.setTag(story);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, StoryBodyActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id", String.valueOf(story.id));
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });

            if(date.containsKey(i)){
                storyViewHolder.date.setVisibility(View.VISIBLE);
                if(i == 1){
                    storyViewHolder.date.setText("今日热闻");
                }else {
                    storyViewHolder.date.setText(formatDate(date.get(i)));
                }
            }else{
                storyViewHolder.date.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return stories.size() + 1;
    }

    @Override
    public int getItemViewType(int position){
        if(position == 0){
            return 0;
        }else{
            return 1;
        }
    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView date;
        public ImageView image;
        private View cardView;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView);
            date = itemView.findViewById(R.id.dateView);
            image = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }

    public static class ViewPagerHolder extends RecyclerView.ViewHolder{
        public ViewPager viewPager;

        public ViewPagerHolder(@NonNull View itemView) {
            super(itemView);
            viewPager = itemView.findViewById(R.id.viewpager);
        }
    }

    public String formatDate(String date) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yymmdd");
        Date d = null;
        try {
            d = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(d);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String str = "";
        switch (day) {
            case 1:
                str = "星期日";
                break;
            case 2:
                str = "星期一";
                break;
            case 3:
                str = "星期二";
                break;
            case 4:
                str = "星期三";
                break;
            case 5:
                str = "星期四";
                break;
            case 6:
                str = "星期五";
                break;
            case 7:
                str = "星期六";
                break;
            default:
                break;
        }
        return date.substring(4,6) + "月" + date.substring(6) + "日" + "    " + str;
    }

}