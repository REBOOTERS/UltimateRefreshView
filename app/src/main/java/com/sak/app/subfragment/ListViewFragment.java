package com.sak.app.subfragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sak.app.R;
import com.sak.app.adapter.MeiTuanHeaderAdapter;
import com.sak.ultilviewlib.UltimateRefreshView;
import com.sak.ultilviewlib.interfaces.OnFooterRefreshListener;
import com.sak.ultilviewlib.interfaces.OnHeaderRefreshListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class ListViewFragment extends Fragment {
    private UltimateRefreshView mUltimateRefreshView;

    private int page = 0;
    private int PER_PAGE_NUM = 15;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        View headview = LayoutInflater.from(getContext()).inflate(R.layout.list_headview_layout,
                null, false);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        final List<String> datas = new ArrayList<>();
        for (int i = 0; i < PER_PAGE_NUM; i++) {
            datas.add("this is item " + i);
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(adapter);
        listView.addHeaderView(headview);
        mUltimateRefreshView = (UltimateRefreshView) view.findViewById(R.id.refreshView);
        mUltimateRefreshView.setBaseHeaderAdapter(new MeiTuanHeaderAdapter(getContext()));
        mUltimateRefreshView.setBaseFooterAdapter();
        mUltimateRefreshView.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(UltimateRefreshView view) {
                page = 0;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        datas.clear();
                        for (int i = page * PER_PAGE_NUM; i < PER_PAGE_NUM; i++) {
                            datas.add("this is item " + i);
                        }
                        adapter.notifyDataSetChanged();
                        mUltimateRefreshView.onHeaderRefreshComplete();
                    }
                }, 2000);
            }
        });

        mUltimateRefreshView.setOnFooterRefreshListener(new OnFooterRefreshListener() {
            @Override
            public void onFooterRefresh(UltimateRefreshView view) {
                page++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = page * PER_PAGE_NUM; i < (page + 1) * PER_PAGE_NUM; i++) {
                            datas.add("this is item " + i);
                        }
                        adapter.notifyDataSetChanged();
                        mUltimateRefreshView.onFooterRefreshComplete();
                    }
                }, 200);
            }
        });
    }


}
