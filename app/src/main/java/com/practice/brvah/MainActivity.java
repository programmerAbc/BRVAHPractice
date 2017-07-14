package com.practice.brvah;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final int MAX_NUM = 50;
    @BindView(R.id.mainRv)
    RecyclerView mainRv;
    MainRvAdapter mainRvAdapter;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private int clickCount = 0;
    Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainHandler = new Handler(Looper.getMainLooper());
        mainRvAdapter = new MainRvAdapter();
        mainRv.setLayoutManager(new LinearLayoutManager(this));
        mainRv.setAdapter(mainRvAdapter);
        mainRvAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e(TAG, "onItemClick:" + ((ItemInfo) adapter.getData().get(position)).getText());
            }
        });
        mainRvAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e(TAG, "onItemLongClick:" + ((ItemInfo) adapter.getData().get(position)).getText());
                return true;
            }
        });
        mainRvAdapter.setHeaderAndEmpty(false);
        mainRvAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_view, (ViewGroup) mainRv.getParent(), false));
        mainRvAdapter.addHeaderView(LayoutInflater.from(this).inflate(R.layout.header_layout, (ViewGroup) mainRv.getParent(), false));
        mainRvAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                swipeRefreshLayout.setEnabled(false);
                mainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setEnabled(true);
                        if (mainRvAdapter.getData().size() < MAX_NUM) {
                            for (int i = 0; i < 5; ++i) {
                                mainRvAdapter.addData(new ItemInfo(R.mipmap.ic_launcher_round, "测试:" + clickCount++));
                            }
                            mainRvAdapter.loadMoreComplete();
                        } else {
                            mainRvAdapter.loadMoreEnd(false);
                        }
                    }
                }, 1000L);
            }
        }, mainRv);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainRvAdapter.setEnableLoadMore(false);
                mainHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mainRvAdapter.clear();
                        for (int i = 0; i < 5; ++i) {
                            mainRvAdapter.addData(new ItemInfo(R.mipmap.ic_launcher_round, "测试:" + clickCount++));
                        }
                        mainRvAdapter.setEnableLoadMore(true);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000L);
            }
        });
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        mainRvAdapter.addData(new ItemInfo(R.mipmap.ic_launcher_round, "测试:" + clickCount++));
    }
}
