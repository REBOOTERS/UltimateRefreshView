package com.sak.ultilviewlib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by engineer on 2017/4/26.
 */

public abstract class BaseHeaderAdapter {
    protected LayoutInflater mInflater;


    public BaseHeaderAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    /**
     * 获取headerView
     *
     * @return
     */
    public abstract View getHeaderView();

    /**
     * 顶部headView 被下拉时此事件发生
     *
     * @param deltaY 下拉的距离
     */
    public abstract void pullViewToRefresh(int deltaY);

    /**
     * 顶部headView 下拉后，完全显示时 此事件发生
     *
     * @param deltaY 下拉的距离
     */
    public abstract void releaseViewToRefresh(int deltaY);

    /**
     * 顶部headView 正在刷新
     */
    public abstract void headerRefreshing();

    /**
     * 顶部headView 完成刷新
     */
    public abstract void headerRefreshComplete();


}
