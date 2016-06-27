package com.qianfeng.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LinearLayoutRecyclerViewActivity extends Activity
{
    private RecyclerView recyclerView;

    private List<String> list;

    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        list = new ArrayList<String>();
       // adapter = new RecyclerViewAdapter(this, list);
        // 竖起的布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        /**
         * listview样式的滑动方向由setOrientation方法决定 LinearLayout.VERTICAL:竖向滑动
         * LinearLayout.HORIZONTAL:横向滑动
         */
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        loadData();
    }

    private void loadData()
    {
        for (int i = 0; i < 100; i++)
        {
            list.add(i + "");
        }
        adapter.notifyDataSetChanged();
    }
}
