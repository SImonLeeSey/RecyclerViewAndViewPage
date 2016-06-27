package com.qianfeng.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SImon on 2016/6/7.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyHolder> {

    private Context context;

    private List<String> list;

    private OnItemClickListener onItemClickListener;


    public MyRecyclerViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public  class MyHolder extends  RecyclerView.ViewHolder{
        TextView tvName;
        public MyHolder(View itemView) {
            super(itemView);
        }
    }
    //将getView解耦
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //复用、错位、封装


        View convertview = LayoutInflater.from(context).inflate(R.layout.layout_recycler_view,parent,false);

        return new MyHolder(convertview) ;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final  int position) {
        //只填充控件
        holder.tvName.setText(list.get(position));


        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (onItemClickListener != null)
                {

                    int pos = holder.getLayoutPosition();//获取动态position
                    onItemClickListener.onItemClick(v, pos);

                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                if (onItemClickListener != null)
                {
                    int pos = holder.getLayoutPosition();//获取动态position
                    onItemClickListener.onItemLongClick(v, pos);
                }
                return true;
            }
        });

    }

    public interface OnItemClickListener
    {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }



}
