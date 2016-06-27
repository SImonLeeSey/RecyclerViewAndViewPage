package com.qianfeng.recyclerview;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 *
 */
public class MyPagerAdapter extends PagerAdapter {
    public MyPagerAdapter(List<TextView> list) {
        this.list = list;
    }
    private List<TextView> list;
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView textView = list.get(position);
        container.addView(list.get(position));
        return list.get(position);

    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
