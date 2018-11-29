package com.pengl.demo.expand;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/10/17
 * Time: 16:02
 * FIXME
 */
class MockResponse {

    /**
     * text : 0
     * child : [{"text":0},{"text":1},{"text":2},{"text":3}]
     */

    @SerializedName("data")
    public List<ParentBean> mData;
}
