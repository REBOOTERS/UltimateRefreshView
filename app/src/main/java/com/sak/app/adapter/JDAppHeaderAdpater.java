package com.sak.app.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sak.app.R;
import com.sak.ultilviewlib.adapter.BaseHeaderAdapter;
import com.sak.ultilviewlib.util.MeasureTools;

/**
 * Created by engineer on 2017/4/29.
 */

public class JDAppHeaderAdpater extends BaseHeaderAdapter {
    private TextView headerText;
    private ImageView loading;
    private LinearLayout loading_pre;
    private float pull_distance=0;
    private int viewHeight;

    public JDAppHeaderAdpater(Context context) {
        super(context);
    }


    @Override
    public View getHeaderView() {
        View headerView = mInflater.inflate(R.layout.jd_header_refresh_layout, null, false);
        headerText = (TextView) headerView.findViewById(R.id.header_text);
        loading = (ImageView) headerView.findViewById(R.id.loading);
        loading_pre = (LinearLayout) headerView.findViewById(R.id.loading_pre);
        MeasureTools.measureView(headerView);
        viewHeight = headerView.getMeasuredHeight();
        return headerView;
    }

    @Override
    public void pullViewToRefresh(int deltaY) {
        headerText.setText("下拉刷新…");
        pull_distance=pull_distance+deltaY*0.3f;
        float scale = pull_distance / viewHeight;
        loading_pre.setScaleY(scale);
        loading_pre.setScaleX(scale);
    }

    @Override
    public void releaseViewToRefresh(int deltaY) {
        headerText.setText("松开刷新…");
        loading_pre.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void headerRefreshing() {
        headerText.setText("更新中…");
        loading.setImageResource(R.drawable.jd_loading);
        AnimationDrawable mAnimationDrawable= (AnimationDrawable) loading.getDrawable();
        mAnimationDrawable.start();
    }

    @Override
    public void headerRefreshComplete() {
        headerText.setText("下拉刷新…");
        loading.setVisibility(View.GONE);
        loading.setImageResource(R.drawable.x_);
        loading.clearAnimation();
        loading_pre.setVisibility(View.VISIBLE);
        loading_pre.setScaleY(0);
        loading_pre.setScaleX(0);
        pull_distance=0;

    }
}
