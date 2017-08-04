package com.sak.app.subfragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sak.app.R;
import com.sak.app.adapter.TraditionFooterAdapter;
import com.sak.app.adapter.TraditionHeaderAdapter;
import com.sak.ultilviewlib.UltimateRefreshView;
import com.sak.ultilviewlib.interfaces.OnFooterRefreshListener;
import com.sak.ultilviewlib.interfaces.OnHeaderRefreshListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class RecyclerViewFragment extends Fragment {

    private UltimateRefreshView mUltimateRefreshView;
    private List<String> datas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("this is item " + i);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),RecyclerView.VERTICAL));
        mRecyclerView.setAdapter(new MyAdapter());
        mUltimateRefreshView = (UltimateRefreshView) view.findViewById(R.id.refreshView);
        mUltimateRefreshView.setBaseHeaderAdapter(new TraditionHeaderAdapter(getContext()));
        mUltimateRefreshView.setBaseFooterAdapter(new TraditionFooterAdapter(getContext()));
        //使用默认样式
//        mUltimateRefreshView.setBaseFooterAdapter();
//        mUltimateRefreshView.setBaseHeaderAdapter();
        mUltimateRefreshView.setOnHeaderRefreshListener(new OnHeaderRefreshListener() {
            @Override
            public void onHeaderRefresh(UltimateRefreshView view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                      mUltimateRefreshView.onHeaderRefreshComplete();
                    }
                },2000);
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
                },800);
            }
        });

        mUltimateRefreshView.post(new Runnable() {
            @Override
            public void run() {
                mUltimateRefreshView.headerRefreshing();
            }
        });

        return view;
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(parent.getContext());
            View mView = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new MyHolder(mView);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.mTextView.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            public MyHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }
    }
}
