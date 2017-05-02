package com.sak.ultilviewlib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by engineer on 2017/4/29.
 */

public abstract class BaseFooterAdapter {
    protected LayoutInflater mInflater;

    public BaseFooterAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    /**
     * 获取 footerView
     *
     * @return
     */
    public abstract View getFooterView();

    /**
     * footerView 被上拉时此事件发生
     *
     * @param deltaY 上拉的距离
     */
    public abstract void pullViewToRefresh(int deltaY);


    /**
     * footerView 上拉后，完全显示时 此事件发生
     *
     * @param deltaY 上拉的距离
     */
    public abstract void releaseViewToRefresh(int deltaY);

    /**
     * 顶部headView 正在刷新
     */
    public abstract void footerRefreshing();

    /**
     * 顶部headView 完成刷新
     */
    public abstract void footerRefreshComplete();
}
