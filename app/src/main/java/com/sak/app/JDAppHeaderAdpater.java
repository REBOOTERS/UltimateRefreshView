package com.sak.app;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.sak.ultilviewlib.adapter.BaseHeaderAdapter;

/**
 * Created by engineer on 2017/4/29.
 */

public class JDAppHeaderAdpater extends BaseHeaderAdapter {
    private TextView headerText;

    public JDAppHeaderAdpater(Context context) {
        super(context);
    }


    @Override
    public View getHeaderView() {
        View headerView = mInflater.inflate(R.layout.jd_header_refresh_layout, null, false);
        headerText = (TextView) headerView.findViewById(R.id.header_text);
        return headerView;
    }

    @Override
    public void pullViewToRefresh(int deltaY) {
        headerText.setText("下拉刷新…");
    }

    @Override
    public void releaseViewToRefresh(int deltaY) {
        headerText.setText("松开刷新…");
    }

    @Override
    public void headerRefreshing() {
        headerText.setText("更新中…");
    }

    @Override
    public void headerRefreshComplete() {
        headerText.setText("下拉刷新…");
    }
}
