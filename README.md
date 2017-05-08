# UltimateRefreshView

### 预览

<img src="https://raw.githubusercontent.com/REBOOTERS/UltimateRefreshView/master/captures/listview_pull_down.gif"/>

### 功能

- 支持ListView，RecycleView，ScrollView，WebView 
- 一行代码指定是否支持上拉加载，下拉刷新
- 自由定制刷新时头部和尾部的动画效果

### 使用方式

**首先，是引入库**

```
compile 'com.reoobter:ultrapullview:1.0.0'
```

**其次，实现各自的动画效果**

这里我们就以美团APP顶部下拉刷新的动画为例来看看如何实现动画效果


*meituan_header_refresh_layout.xml*

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:gravity="center"
              android:background="@color/white"
              android:orientation="vertical">
    <ImageView
        android:id="@+id/loading"
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:layout_margin="10dp"
        android:scaleX="0"
        android:scaleY="0"
        android:src="@drawable/pull_image"/>
</LinearLayout>
```

这个布局文件很简单，整个只有一个ImageView。我们的实现思路，就是在不同的结点修改ImageView的内容，从而呈现出整个下拉刷新时所有的动画效果。那么这些**结点**是哪些呢？

```java
public class MeiTuanHeaderAdapter extends BaseHeaderAdapter {

    private ImageView loading;
    private int viewHeight;
    private float pull_distance=0;

    public MeiTuanHeaderAdapter(Context context) {
        super(context);
    }

    @Override
    public View getHeaderView() {
        View mView = mInflater.inflate(R.layout.meituan_header_refresh_layout, null, false);
        loading = (ImageView) mView.findViewById(R.id.loading);
        MeasureTools.measureView(mView);
        viewHeight = mView.getMeasuredHeight();
        return mView;
    }

    @Override
    public void pullViewToRefresh(int deltaY) {
        //这里乘以0.3 是因为UltimateRefreshView 源码中对于滑动有0.3的阻尼系数，为了保持一致
        pull_distance=pull_distance+deltaY*0.3f;
        float scale = pull_distance / viewHeight;
        loading.setScaleX(scale);
        loading.setScaleY(scale);

    }


    @Override
    public void releaseViewToRefresh(int deltaY) {
        loading.setImageResource(R.drawable.mei_tuan_loading_pre);
        AnimationDrawable mAnimationDrawable= (AnimationDrawable) loading.getDrawable();
        mAnimationDrawable.start();
    }

    @Override
    public void headerRefreshing() {
        loading.setImageResource(R.drawable.mei_tuan_loading);
        AnimationDrawable mAnimationDrawable= (AnimationDrawable) loading.getDrawable();
        mAnimationDrawable.start();
    }

    @Override
    public void headerRefreshComplete() {
        loading.setImageResource(R.drawable.pull_image);
        loading.setScaleX(0);
        loading.setScaleY(0);
        pull_distance=0;
    }
}
```

通过代码我们可以总结出有**4个**重要的结点

- **下拉进行时**，这个时候随着手指滑动，整个顶部的view逐渐显示出来
- **顶部view完全被下拉出来**，这个时候顶部view已经完全显示出来了，手指释放（抬起）后将进入下一个结点。
- **正在刷新进行时**，刷新进行时，这个结点就是刷新动画执行的时候。
- **刷新完成**，在这个结点触发了刷新完成的动作


为了实现美团顶部刷新动画的效果，在**第一个结点**我们便开始执行动画，根据刷新的位移比，使用scale动画逐渐放大初始图片（绿色椭圆）；在**第二个结点**，这个结点一般都很短暂，这个时候顶部已经完全展示，执行了小人偶翻转出现的动画；在**第三个结点**，这个结点一般是比较耗时的，在这里用帧动画播放了一个小人偶左右摇摆的动画；最后，在**第四个结点**，将所有内容初始化到下拉之前的状态，方便下次下拉刷星动画的执行。这样就完成了一次下拉刷新的动画效果。


**最后，将动画效果适配到UltimateRefreshView之上**

这里就以ListView为例。

首先是布局实现：

```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
             xmlns:tools="http://schemas.android.com/tools"
             android:layout_width="match_parent"
             android:layout_height="match_parent"
             android:background="@color/white"
             tools:context=".subfragment.ListViewFragment"
    >

    <com.sak.ultilviewlib.UltimateRefreshView
        android:id="@+id/refreshView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/white"
        android:orientation="vertical">

        <ListView
            android:id="@+id/listView"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:scrollbars="none"/>

    </com.sak.ultilviewlib.UltimateRefreshView>

</FrameLayout>
```

布局文件很简单，将所要实现的下拉刷新的控件放在UltimateRefreshView控件内即可。

```java
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
```

## 效果图



<img src="https://raw.githubusercontent.com/REBOOTERS/UltimateRefreshView/master/captures/GIF.gif"/>

<img src="https://raw.githubusercontent.com/REBOOTERS/UltimateRefreshView/master/captures/webview_pull_downgif.gif"/>

<img src="https://raw.githubusercontent.com/REBOOTERS/UltimateRefreshView/master/captures/ddmsrec_clip.gif"/>

<img src="https://raw.githubusercontent.com/REBOOTERS/UltimateRefreshView/master/captures/normal.gif"/>

<img src="https://raw.githubusercontent.com/REBOOTERS/UltimateRefreshView/master/captures/pull_up.gif"/>


[更多详细说明](http://www.jianshu.com/p/4343492c01f7)
