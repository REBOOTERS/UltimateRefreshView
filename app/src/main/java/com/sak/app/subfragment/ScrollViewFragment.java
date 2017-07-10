package com.sak.app.subfragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sak.app.adapter.JDAppFooterAdapter;
import com.sak.app.adapter.JDAppHeaderAdpater;
import com.sak.app.R;
import com.sak.ultilviewlib.UltimateRefreshView;
import com.sak.ultilviewlib.interfaces.OnFooterRefreshListener;
import com.sak.ultilviewlib.interfaces.OnHeaderRefreshListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class ScrollViewFragment extends Fragment {

    private UltimateRefreshView mUltimateRefreshView;
    private Context mContext;

    private ListView mListView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getContext();
        View view = inflater.inflate(R.layout.fragment_scroller_view, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.list);
        String[] datas = getResources().getStringArray(R.array.flows);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, datas);
        mListView.setAdapter(mAdapter);


        mUltimateRefreshView = (UltimateRefreshView) view.findViewById(R.id.refreshView);
        mUltimateRefreshView.setBaseHeaderAdapter(new JDAppHeaderAdpater(mContext));
        mUltimateRefreshView.setBaseFooterAdapter(new JDAppFooterAdapter(mContext));
        mUltimateRefreshView.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(UltimateRefreshView view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mUltimateRefreshView.onHeaderRefreshComplete();
                    }
                }, 2000);
            }
        });
        mUltimateRefreshView.setOnFooterRefreshListener(new OnFooterRefreshListener() {
            @Override
            public void onFooterRefresh(UltimateRefreshView view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mUltimateRefreshView.onFooterRefreshComplete();
                    }
                }, 800);
            }
        });
    }

}
