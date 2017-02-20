package com.bwie.week2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.week2.R;
import com.bwie.week2.adapter.RightListAdapter;
import com.bwie.week2.bean.Bean;

/**
 * Created by lenovo on 2017/2/19.
 */

public class MyFragment extends Fragment {

    private View view;
    private RecyclerView top_recycler;
    private RecyclerView bottom_recycler;
    private TextView mile;
    private TextView mile_brand;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_fragment,container,false);
        top_recycler = (RecyclerView) view.findViewById(R.id.top_recycler);
        bottom_recycler = (RecyclerView) view.findViewById(R.id.bottom_recycler);
        mile = (TextView) view.findViewById(R.id.mile);
        mile_brand = (TextView) view.findViewById(R.id.mile_brand);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        top_recycler.setLayoutManager(new GridLayoutManager(getActivity(),3));
        bottom_recycler.setLayoutManager(new GridLayoutManager(getActivity(),3));
        Bundle bundle = getArguments();
        String name = bundle.getString("name");
        Bean bean = (Bean) bundle.getSerializable("bean");
        int index = bundle.getInt("index");
        String title = bean.getRs().get(index).getDirName();
        mile.setText(title.substring(2));
        mile_brand.setText(bean.getRs().get(index).getDirName());
        if(name.equals(bean.getRs().get(index).getDirName())){
            RightListAdapter adapter = new RightListAdapter(getContext(),bean.getRs().get(index).getChildren().get(0),0);
            top_recycler.setAdapter(adapter);
            if(adapter!=null){
                adapter.notifyDataSetChanged();
            }
            if(bean.getRs().get(index).getChildren().size()>1){
            RightListAdapter adapter1 = new RightListAdapter(getContext(),bean.getRs().get(index).getChildren().get(1),1);
            bottom_recycler.setAdapter(adapter1);
            if(adapter1 != null){
                adapter1.notifyDataSetChanged();
            }

            }
        }
    }

}
