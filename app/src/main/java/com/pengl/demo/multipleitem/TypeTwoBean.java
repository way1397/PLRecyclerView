package com.pengl.demo.multipleitem;

import com.pengl.demo.RecyclerItemType;
import com.pengl.PLRecyclerView.ItemType;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/10
 * Time: 11:56
 * FIXME
 */
public class TypeTwoBean implements ItemType {
    String text;

    public TypeTwoBean(String text) {
        this.text = text;
    }

    @Override
    public int itemType() {
        return RecyclerItemType.TYPE2.getValue();
    }
}
