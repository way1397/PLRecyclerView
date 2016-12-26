package com.pengl.demo.multipleitem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.pengl.demo.Header;
import com.pengl.demo.R;
import com.pengl.PLRecyclerView.ItemType;
import com.pengl.PLRecyclerView.RecyclerView;

public class MultiItemActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.content_multi_item)
    RelativeLayout mContentMultiItem;
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    private MultiItemAdapter mAdapter;
    private MultiItemPresenter mPresenter;

    private boolean flag = true;

    @OnClick(R.id.fab)
    public void onClick() {
        flag = !flag;
        Toast.makeText(this, "flag:" + flag, Toast.LENGTH_SHORT).show();
//        mRecycler.setNoMoreViewEnabled(flag);
        mRecycler.setLoadMoreViewEnabled(flag);
//        mRecycler.setLoadMoreFailedViewEnabled(flag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_item);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mAdapter = new MultiItemAdapter();
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapterWithLoading(mAdapter);

        mRecycler.setRefreshListener(new RecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadData(true);
            }
        });

        mRecycler.setLoadMoreListener(new RecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadData(false);
            }
        });

        mPresenter = new MultiItemPresenter(this);
        mPresenter.setDataLoadCallBack(new MultiItemView() {
            @Override
            public void onDataLoadSuccess(List<ItemType> list, boolean isRefresh) {
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
