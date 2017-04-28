package com.sak.app.subfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sak.app.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class RecyclerViewFragment extends Fragment {


    private List<String> datas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("this is item " + i);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new MyAdapter());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
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
