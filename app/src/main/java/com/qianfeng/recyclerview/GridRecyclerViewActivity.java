package com.qianfeng.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GridRecyclerViewActivity extends Activity {
    private RecyclerView recyclerView;

    private List<String> list;

    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        list = new ArrayList<String>();
       // adapter = new RecyclerViewAdapter(this, list);
        initEvent();
        //竖起的布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        /**
         * listview样式的滑动方向由setOrientation方法决定
         * LinearLayout.VERTICAL:竖向滑动
         * LinearLayout.HORIZONTAL:横向滑动
         * GridLayoutManager构造方法中的第二个参数:
         * 当setOrientation为LinearLayout.VERTICAL时,参数代表列数
         * 当setOrientation为LinearLayout.HORIZONTAL,参数代表行数
         */
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        loadData();
    }

    private void initEvent() {
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //TODO 处理点击事件
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //TODO 处理长按事件
            }
        });
    }

    private void loadData() {
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }
        adapter.notifyDataSetChanged();
    }
}
