package com.pengl.demo.grid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.pengl.PLRecyclerView.PLRecyclerView;
import com.pengl.demo.Header;
import com.pengl.demo.R;
import com.pengl.demo.R2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridActivity extends AppCompatActivity {

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;
    @BindView(R2.id.recycler)
    PLRecyclerView mRecycler;
    @BindView(R2.id.content_grid)
    RelativeLayout mContentGrid;
    @BindView(R2.id.fab)
    FloatingActionButton mFab;

    private GridAdapter mAdapter;

    private GridPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mAdapter = new GridAdapter();
        mRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        mRecycler.setAdapterWithLoading(mAdapter);

        mRecycler.setRefreshListener(new PLRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadData(true);
            }
        });

        mRecycler.setLoadMoreListener(new PLRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadData(false);
            }
        });

        mPresenter = new GridPresenter(this);
        mPresenter.setDataLoadCallBack(new GridView() {
            @Override
            public void onDataLoadSuccess(List<GridBean> list, boolean isRefresh) {
                if (isRefresh) {
                    mAdapter.clear();
                    mAdapter.addHeader(new Header());
                    //                    mAdapter.addFooter(new Header());
                    mAdapter.addAll(list);
                } else {
                    mAdapter.addAll(list);
                }
            }

            @Override
            public void onDataLoadFailed(boolean isRefresh) {
                if (isRefresh) {
                    mAdapter.showError();
                } else {
                    mAdapter.loadMoreFailed();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribeAll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadData(true);
    }

}
