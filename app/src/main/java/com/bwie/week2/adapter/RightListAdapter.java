package com.bwie.week2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.week2.R;
import com.bwie.week2.bean.Bean;

import java.util.List;

/**
 * Created by lenovo on 2017/2/19.
 */

public class RightListAdapter extends RecyclerView.Adapter<RightListAdapter.MyViewHolder> {
    private Bean.RsBean.ChildrenBeanX bean;
    private Context context;
    private int index;
    private final List<Bean.RsBean.ChildrenBeanX.ChildrenBean> children;

    public RightListAdapter(Context context, Bean.RsBean.ChildrenBeanX bean, int index) {
        this.context = context;
        this.bean = bean;
        this.index = index;
        children = bean.getChildren();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.right_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (index == 0) {
            Glide.with(context).load(children.get(position).getImgApp()).placeholder(R.mipmap.ic_launcher).crossFade().into(holder.right_image);
            holder.right_tv.setText(children.get(position).getDirName());
        } else {
            Glide.with(context).load(children.get(position).getImgApp()).placeholder(R.mipmap.ic_launcher).crossFade().into(holder.right_image);
            holder.right_tv.setText(children.get(position).getDirName());
        }
    }

    @Override
    public int getItemCount() {
        if (index == 0) {
            return children == null ? 0 : children.size();
        }
        return children == null ? 0 : children.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView right_image;
        private final TextView right_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            right_image = (ImageView) itemView.findViewById(R.id.right_image);
            right_tv = (TextView) itemView.findViewById(R.id.right_tv);
        }
    }
}
