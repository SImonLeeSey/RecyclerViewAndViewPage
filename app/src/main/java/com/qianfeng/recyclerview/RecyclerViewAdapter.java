package com.qianfeng.recyclerview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 *
 * 1.创建一个继承于RecyclerView.Adapter的子类作为RecyclerView的适配器
 * 2.创建一个继承于RecyclerView.ViewHolder的子类作为当前适配器的viewholder
 * ,并将其作为RecyclerView.Adapter的泛型来使用(统一开发规范) 3.实现当前适配器中的方法
 * 4.加载布局,并初始化MyViewHolder,然后通过onCreateViewHolder返回 5.通过onBindViewHolder方法填充控件
 *
 */
public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
{

    private Context context;

    private int Id;

    private List<String> list;

    private OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter(Context context, List<String> list) {

        this.context = context;
        this.list = list;
    }

    // 创建ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View convertview = LayoutInflater.from(context).inflate(
                R.layout.adapter_recycler_view, parent, false);
        return new MyViewHolder(convertview);
    }

    // 填充控件(同listview中的getView()方法,只作控件的赋值业务)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        Log.i("info", "onBindViewHolder=" + position);
        holder.tv.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (onItemClickListener != null)
                {
                    onItemClickListener.onItemClick(v, position);
                    Id =  holder.getLayoutPosition();
                }
                Event event = new Event();
                event.setId(position);
                EventBus.getDefault().post(event);
                Log.i("info", "itemView被点击了===>" + position);
            }
        });

        Log.i("info", "onCreateViewHolder");
        Event event = new Event();
        event.setId(position);
        EventBus.getDefault().post(event);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                if (onItemClickListener != null)
                {
                    onItemClickListener.onItemLongClick(v, position);
                }
                Log.i("info", "itemView被长按了===>" + position);
                // 长按的返回值:当返回true表示消费掉当前事件,会使后续的操作无法获取到该事件
                // 如果返回false,则可触发onClick监听
                return true;
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    /**
     * 继承于RecyclerView.ViewHolder的作用 1.统一开发规范 2.增强封装性
     */
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tv;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    public interface OnItemClickListener
    {
        // 点击回调方法
        void onItemClick(View view, int position);

        // 长按回调方法
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }
}
