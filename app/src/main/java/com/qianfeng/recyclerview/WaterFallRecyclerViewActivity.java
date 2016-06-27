package com.qianfeng.recyclerview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class WaterFallRecyclerViewActivity extends Activity {
    private RecyclerView recyclerView;

    private List<String> list;

    private MyPagerAdapter myPagerAdapter;

    private List<TextView> textViewList;

    private RecyclerViewAdapter adapter;

    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_water_fall);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        vp = (ViewPager) findViewById(R.id.vp);
        list = new ArrayList<String>();
        textViewList = new ArrayList<>();
        adapter = new RecyclerViewAdapter(this, list);
        //竖起的布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayout.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        myPagerAdapter = new MyPagerAdapter(textViewList);
        vp.setAdapter(myPagerAdapter);
        recyclerView.setAdapter(adapter);
        loadData();
    }

    private void loadData() {

        for (int i = 0; i < 20; i++) {
            TextView textView = new TextView(this);
            textView.setTextColor(Color.RED);
            textView.setText("TextView" + i);
            textViewList.add(textView);
            list.add(i + "data");
        }
        myPagerAdapter.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
    }

    @Subscribe
    public void onEvent(Event event) {
        Log.e("evebt",event.getId()+"");
        vp.setCurrentItem(event.getId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
