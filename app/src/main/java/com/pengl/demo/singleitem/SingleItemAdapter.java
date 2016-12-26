package com.pengl.demo.singleitem;

import android.view.ViewGroup;

import com.pengl.PLRecyclerView.AbstractAdapter;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/9/22
 * Time: 09:47
 * FIXME
 */
class SingleItemAdapter extends AbstractAdapter<NormalBean, NormalViewHolder> {

    @Override
    protected NormalViewHolder onNewCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(parent);
    }

    @Override
    protected void onNewBindViewHolder(NormalViewHolder holder, int position) {
        holder.setData(get(position));
    }
}
