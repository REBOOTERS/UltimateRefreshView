package com.sak.ultilviewlib;

import android.content.Context;
import android.view.View;

/**
 * Created by engineer on 2017/4/28.
 * 仿京东下拉刷新效果
 */

public class JDAppAdapter extends BaseHeaderAdapter {

    public JDAppAdapter(Context context) {
        super(context);
    }

    @Override
    public View getHeaderView() {
        return null;
    }

    @Override
    public void pullViewToRefresh(int deltaY) {

    }

    @Override
    public void releaseViewToRefresh(int deltaY) {

    }


    @Override
    public void headerRefreshing() {

    }

    @Override
    public void headerRefreshComplete() {

    }
}
