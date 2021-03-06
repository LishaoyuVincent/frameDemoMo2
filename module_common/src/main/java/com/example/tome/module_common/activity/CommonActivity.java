package com.example.tome.module_common.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tome.component_base.arouter.RouterConfig;
import com.example.tome.component_base.util.GetJsonDataUtil;
import com.example.tome.component_base.util.L;
import com.example.tome.component_base.util.T;
import com.example.tome.component_base.widget.SuperDividerItemDecoration;
import com.example.tome.component_data.d_arouter.RouterURLS;
import com.example.tome.module_common.R;
import com.example.tome.module_common.adapter.CommonAdapter;
import com.example.tome.module_common.bean.CommonBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
@Route(path = RouterURLS.CUSTOM_CONTROL)
public class CommonActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private TextView mTitleBack;
    private TextView mTitleText;
    private List<CommonBean> mJsonList;
    private CommonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        initData();
        initView();

    }

    private void initData() {
        //读取json文件
        String json = new GetJsonDataUtil().getJson(this, "customControl.json");
        mJsonList = new Gson().fromJson(json, new TypeToken<ArrayList<CommonBean>>() {
        }.getType());
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mTitleBack = (TextView) findViewById(R.id.title_back);
        mTitleText = (TextView) findViewById(R.id.title_content_text);
        //设置标题
        mTitleText.setText("常用自定义控件汇总");
        //mTitleBack.setVisibility(View.VISIBLE);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //分割线
        mRecyclerView.addItemDecoration(new SuperDividerItemDecoration.Builder(this)
                .setDividerWidth(1)
                .setDividerColor(getResources().getColor(R.color.colorAccent))
                .build());
        mAdapter = new CommonAdapter(R.layout.item_common, mJsonList);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if ("0".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonActivity.this, TagFlowActivity.class));
        }else if ("1".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonActivity.this, AlertViewDemoActivity.class));
        }else if ("2".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonActivity.this, HoverItemActivity.class));
        }else if ("3".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonActivity.this, DragBallActivity.class));
        }else if ("4".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonActivity.this, RatingBarActivity.class));
        }else if ("5".equals(mJsonList.get(position).getType())){

        }else if ("6".equals(mJsonList.get(position).getType())){

        }else if ("7".equals(mJsonList.get(position).getType())){

        }else if ("8".equals(mJsonList.get(position).getType())){

        }else if ("9".equals(mJsonList.get(position).getType())){

        }
    }



    public void jumpTo() {
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }
}
