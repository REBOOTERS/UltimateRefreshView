package com.sak.app;

import android.content.Context;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sak.ultilviewlib.adapter.BaseHeaderAdapter;

/**
 * Created by engineer on 2017/4/30.
 */

public class TraditionHeaderAdapter extends BaseHeaderAdapter {
    private ProgressBar pull_to_refresh_progress;
    private ImageView pull_to_refresh_image;
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
//        mReverseFlipAnimation = new RotateAnimation(-180, 0,
//                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
//                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//        mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
//        mReverseFlipAnimation.setDuration(250);
//        mReverseFlipAnimation.setFillAfter(true);
    }

    @Override
    public View getHeaderView() {
        View mView = mInflater.inflate(R.layout.tradition_header_refresh_layout, null, false);
        pull_to_refresh_progress = (ProgressBar) mView.findViewById(R.id.pull_to_refresh_progress);
        pull_to_refresh_image = (ImageView) mView.findViewById(R.id.pull_to_refresh_image);
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
        pull_to_refresh_progress.setVisibility(View.VISIBLE);
        pull_to_refresh_text.setText("正在加载…");
    }

    @Override
    public void headerRefreshComplete() {
        pull_to_refresh_image.setVisibility(View.VISIBLE);
        pull_to_refresh_image.setImageResource(R.drawable.ic_pulltorefresh_arrow);
        pull_to_refresh_progress.setVisibility(View.GONE);
        pull_to_refresh_text.setText("");
    }
}
