package com.example.tome.module_shop_mall.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tome.component_base.base.BaseFragmentAdapter;
import com.example.tome.component_base.base.BaseMVPActivity;
import com.example.tome.component_base.base.BaseTabListActivity;
import com.example.tome.component_base.bean.TabListBean;
import com.example.tome.component_base.util.L;
import com.example.tome.component_base.util.TabLayoutUtils;
import com.example.tome.component_data.d_arouter.IntentKV;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.bean.KnowledgeSystemBean;
import com.example.tome.module_shop_mall.fagment.KnowledgeChildFragment;
import com.example.tome.module_shop_mall.fagment.KnowledgeSystemFragment;
import com.example.tome.module_shop_mall.presenter.KnowledgeDetailPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 知识体系详情子页面
 */
public class KnowledgeDetailActivity extends BaseMVPActivity<KnowledgeDetailPresenter> {

    @BindView(R2.id.title_back)
    TextView mTitleBack;
    @BindView(R2.id.title_content_text)
    TextView mTitleContentText;
    @BindView(R2.id.title_right_text)
    TextView mTitleRightText;
    @BindView(R2.id.title_right_icon)
    ImageView mTitleRightIcon;
    @BindView(R2.id.v_title_container)
    LinearLayout mVTitleContainer;
    @BindView(R2.id.rl_title_bar_content)
    RelativeLayout mRlTitleBarContent;
    @BindView(R2.id.tl_tabs)
    TabLayout mTlTabs;
    @BindView(R2.id.knowledge_detail_viewpager)
    ViewPager mVpView;

    KnowledgeSystemBean knowledgeSystemBean;
    private String mTitle;
    private List<String> mTabTitle;
    private List<Fragment> mFragmentList;


    @Override
    protected KnowledgeDetailPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mall_activity_knowledge_detail;
    }

    @Override
    protected void initTitle() {
        knowledgeSystemBean = getIntent().getParcelableExtra(IntentKV.K_KNOWLEDGE_DATA);

        mTitleBack.setVisibility(View.VISIBLE);
        mTabTitle = new ArrayList<>();
        mFragmentList = new ArrayList<>();
        if (knowledgeSystemBean != null) {
            String titleName = knowledgeSystemBean.getName();
            mTitleContentText.setText(titleName);
            initData();
        }

    }

    private void initData() {
        for (int i = 0 ; i < knowledgeSystemBean.getChildren().size(); i++){
            mTabTitle.add(knowledgeSystemBean.getChildren().get(i).getName());
            mFragmentList.add(createListFragment(knowledgeSystemBean.getChildren().get(i)));
        }
    }



    @Override
    protected void initView() {
        BaseFragmentAdapter FragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), mFragmentList, mTabTitle);
        mVpView.setAdapter(FragmentAdapter);
        mTlTabs.setupWithViewPager(mVpView);
        TabLayoutUtils.dynamicSetTabLayoutMode(mTlTabs);
        setPageChangeListener();
    }

    private void setPageChangeListener() {
        mVpView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private Fragment createListFragment(KnowledgeSystemBean.ChildrenBean childrenBean) {

        KnowledgeChildFragment fragment = new KnowledgeChildFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(IntentKV.K_KNOWLEDGE_ID, childrenBean.getId());
        fragment.setArguments(bundle);
        return fragment;

    }
}
