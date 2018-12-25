package com.example.yc.zhihudaily.Presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yc.zhihudaily.Model.Datas;
import com.example.yc.zhihudaily.R;
import com.example.yc.zhihudaily.View.StoryBodyActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    List<Datas.Top_storiesEntity> topStoriesEntities = new ArrayList<>();

    public ViewPagerAdapter(Context context, List<Datas.Top_storiesEntity> list) {
        topStoriesEntities.addAll(list);
        this.context = context;
    }

    @Override
    public int getCount() {
        return topStoriesEntities.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final String[] id = {String.valueOf(topStoriesEntities.get(position).getId())};
        final View view = LayoutInflater.from(context).inflate(
                R.layout.topstory, container, false);

        final TextView textView = view.findViewById(R.id.topTitle);
        ImageView imageView = view.findViewById(R.id.topImage);
        textView.setText(topStoriesEntities.get(position).getTitle());
        Glide.with(context)
                .load(topStoriesEntities.get(position).getImage())
                .into(imageView);
        view.setTag(topStoriesEntities.get(position));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoryBodyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", id[0]);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

}
