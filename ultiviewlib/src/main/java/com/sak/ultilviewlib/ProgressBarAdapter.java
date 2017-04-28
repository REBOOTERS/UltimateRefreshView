package com.sak.ultilviewlib;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sak.ultimateviewlib.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by engineer on 2017/4/26.
 */

public class ProgressBarAdapter extends BaseHeaderAdapter {

    private View headerView;
    //
    private TextView headerText;
    private ProgressBar mProgressBar;


    public ProgressBarAdapter(Context context) {
        super(context);
    }


    @Override
    public View getHeaderView() {
        headerView = mInflater.inflate(R.layout.utril_header_layout, null, false);
        headerText = (TextView) headerView.findViewById(R.id.header_text);
        mProgressBar = (ProgressBar) headerView.findViewById(R.id.progressBar);
        return headerView;
    }

    @Override
    public void pullViewToRefresh(int deltaY) {
        headerText.setText("下拉刷新");
    }

    @Override
    public void releaseViewToRefresh(int deltaY) {
        headerText.setText("释放刷新");
    }

    @Override
    public void headerRefreshing() {
        mProgressBar.setVisibility(VISIBLE);
        headerText.setVisibility(GONE);
    }

    @Override
    public void headerRefreshComplete() {
        mProgressBar.setVisibility(GONE);
        headerText.setVisibility(VISIBLE);
    }
}
