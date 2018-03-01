package org.qixunyun.qixunyun.recyclerview;

/**
 * RecyclerView 多种布局的bean需要实现这个接口
 * Created by zwonb on 2018/1/19.
 */

public interface ManyItemType {

    /**
     * @return 默认从0开始 {@link ManyItemAdapter}
     * 0 对应第一个 ManyHolder1.class
     * 1 对应第二个 ManyHolder2.class
     * ...
     */
    int getItemViewType();
}
