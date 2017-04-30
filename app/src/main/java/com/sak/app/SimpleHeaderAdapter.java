package com.sak.app;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.sak.ultilviewlib.adapter.BaseHeaderAdapter;

/**
 * Created by engineer on 2017/4/30.
 */

public class SimpleHeaderAdapter extends BaseHeaderAdapter {
    private ImageView loading;
    private ObjectAnimator rotateAnim;

    public SimpleHeaderAdapter(Context context) {
        super(context);
    }

    @Override
    public View getHeaderView() {
        View mView = mInflater.inflate(R.layout.simple_header_refresh_layout, null, false);
        loading = (ImageView) mView.findViewById(R.id.loading);
        return mView;
    }

    @Override
    public void pullViewToRefresh(int deltaY) {
        rotateAnim = ObjectAnimator.ofFloat(loading, "rotation", 0, 360f);
        rotateAnim.setDuration(1000);
        rotateAnim.setRepeatCount(-1);
        rotateAnim.start();
    }

    @Override
    public void releaseViewToRefresh(int deltaY) {

    }

    @Override
    public void headerRefreshing() {

    }

    @Override
    public void headerRefreshComplete() {
        rotateAnim.cancel();
        rotateAnim = null;
    }
}
