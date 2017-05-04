package com.sak.app.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.sak.app.R;
import com.sak.ultilviewlib.adapter.BaseHeaderAdapter;

/**
 * Created by engineer on 2017/4/30.
 */

public class TraditionHeaderAdapter extends BaseHeaderAdapter {
    private ImageView pull_to_refresh_image;
    private ImageView pull_to_refresh_image1;
    private TextView pull_to_refresh_text;

    //
    private RotateAnimation mFlipAnimation;
//    private RotateAnimation mReverseFlipAnimation;

    public TraditionHeaderAdapter(Context context) {
        super(context);
        mFlipAnimation = new RotateAnimation(0, -180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mFlipAnimation.setInterpolator(new LinearInterpolator());
        mFlipAnimation.setDuration(250);
        mFlipAnimation.setFillAfter(true);
    }

    @Override
    public View getHeaderView() {
        View mView = mInflater.inflate(R.layout.tradition_header_refresh_layout, null, false);
        pull_to_refresh_image = (ImageView) mView.findViewById(R.id.pull_to_refresh_image);
        pull_to_refresh_image1 = (ImageView) mView.findViewById(R.id.pull_to_refresh_image1);
        pull_to_refresh_text = (TextView) mView.findViewById(R.id.pull_to_refresh_text);
        return mView;
    }

    @Override
    public void pullViewToRefresh(int deltaY) {
        pull_to_refresh_text.setText("下拉刷新…");
        pull_to_refresh_image.clearAnimation();
        pull_to_refresh_image.startAnimation(mFlipAnimation);
    }

    @Override
    public void releaseViewToRefresh(int deltaY) {
        pull_to_refresh_text.setText("放开以刷新…");
    }

    @Override
    public void headerRefreshing() {
        pull_to_refresh_image.setImageDrawable(null);
        pull_to_refresh_image.clearAnimation();
        pull_to_refresh_image.setVisibility(View.GONE);
        pull_to_refresh_image1.setVisibility(View.VISIBLE);
        pull_to_refresh_image1.setImageResource(R.drawable.simple_loading);
        AnimationDrawable mAnimationDrawable= (AnimationDrawable) pull_to_refresh_image1.getDrawable();
        mAnimationDrawable.start();
        pull_to_refresh_text.setText("正在加载…");
    }

    @Override
    public void headerRefreshComplete() {
        pull_to_refresh_image.setVisibility(View.VISIBLE);
        pull_to_refresh_image1.setVisibility(View.GONE);
        pull_to_refresh_image1.clearAnimation();
        pull_to_refresh_image.setImageResource(R.drawable.erg);
        pull_to_refresh_text.setVisibility(View.VISIBLE);
        pull_to_refresh_text.setText("");
    }
}
