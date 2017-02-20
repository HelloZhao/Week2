package com.bwie.week2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.week2.Interface.LeftOnItemClickListener;
import com.bwie.week2.R;
import com.bwie.week2.app.MyApp;

import java.util.List;

/**
 * Created by lenovo on 2017/2/19.
 */

public class LeftListAdapter extends RecyclerView.Adapter<LeftListAdapter.MyViewHolder>{

    public List<String> mList;
    public Context context;
    public LeftOnItemClickListener leftOnItemClickListener;
    public LeftListAdapter(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
    }
    public void setLeftOnItemClickListener(LeftOnItemClickListener leftOnItemClickListener) {
        this.leftOnItemClickListener = leftOnItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.left_layout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if(MyApp.bgNum == position){
            holder.left_ll.setBackgroundColor(Color.WHITE);
        }else{
            holder.left_ll.setBackgroundColor(context.getResources().getColor(R.color.color1));
        }
        holder.left_tv.setText(mList.get(position).toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int layoutPosition = holder.getLayoutPosition();
                if(leftOnItemClickListener != null){
                    leftOnItemClickListener.leftOnItemClickListener(layoutPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView left_tv;
        private final LinearLayout left_ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            left_tv = (TextView) itemView.findViewById(R.id.left_tv);
            left_ll = (LinearLayout) itemView.findViewById(R.id.left_ll);
        }
    }
}
