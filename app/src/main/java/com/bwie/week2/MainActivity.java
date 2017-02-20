package com.bwie.week2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bwie.week2.Interface.LeftOnItemClickListener;
import com.bwie.week2.adapter.LeftListAdapter;
import com.bwie.week2.app.MyApp;
import com.bwie.week2.bean.Bean;
import com.bwie.week2.fragment.MyFragment;
import com.bwie.week2.utils.HttpUtils;
import com.bwie.week2.utils.UrlUtils;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView left_recycler;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initView() {
        left_recycler = (RecyclerView) findViewById(R.id.left_recycler);
        requestData();
    }

    //请求数据
    private void requestData() {
        HttpUtils httpUtils = HttpUtils.getHttpUtils();
        httpUtils.loadDataFromNet(UrlUtils.PATH, Bean.class, new HttpUtils.CallBackListener<Bean>() {
            @Override
            public void onSuccess(Bean result) {
                List<String> mLeftList = new ArrayList<>();
                List<Bean.RsBean> rs = result.getRs();
                for (int i = 0; i < rs.size(); i++) {
                    String dirName = rs.get(i).getDirName();
                    mLeftList.add(dirName);
                }
                leftRecyclerinit(mLeftList, result);
            }

            @Override
            public void onFail() {
                Toast.makeText(MainActivity.this, "网络请求错误", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void leftRecyclerinit(List<String> mList, final Bean bean) {

        //设置布局管理器
        left_recycler.setLayoutManager(new LinearLayoutManager(this));
        final LeftListAdapter leftListAdapter = new LeftListAdapter(this, mList);
        left_recycler.setAdapter(leftListAdapter);
        MyFragment myFragment = new MyFragment();
        Bundle bundle =  new Bundle();
        bundle.putSerializable("bean",bean);
        bundle.putString("name","宝宝奶粉");
        bundle.putInt("index",0);
        myFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.right_fl,myFragment).commit();
        leftListAdapter.setLeftOnItemClickListener(new LeftOnItemClickListener() {
            @Override
            public void leftOnItemClickListener(int pos) {
                MyApp.bgNum = pos;
                leftListAdapter.notifyDataSetChanged();
                MyFragment myFragment1 = new MyFragment();
                Bundle bundle =  new Bundle();
                bundle.putSerializable("bean",bean);
                bundle.putString("name",bean.getRs().get(pos).getDirName());
                bundle.putInt("index",pos);
                myFragment1.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.right_fl,myFragment1).commit();
            }
        });
    }


}
