package com.pengl.demo.multipleitem;

import java.util.List;

import com.pengl.PLRecyclerView.ItemType;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/10
 * Time: 12:08
 * FIXME
 */
public interface MultiItemView {
    void onDataLoadSuccess(List<ItemType> list, boolean isRefresh);

    void onDataLoadFailed(boolean isRefresh);
}
