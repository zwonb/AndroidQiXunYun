package org.qixunyun.qixunyun.holder;

import android.view.ViewGroup;

import org.qixunyun.qixunyun.R;
import org.qixunyun.qixunyun.bean.MainBean;
import org.qixunyun.qixunyun.recyclerview.SuperViewHolder;

/**
 * Created by zwonb on 2018/3/1.
 */

public class MainHolder extends SuperViewHolder<MainBean> {
    public MainHolder(ViewGroup parent) {
        super(parent, R.layout.item_main);
    }

    @Override
    protected void setDate(MainBean bean) {
        setText(R.id.item_main_text, bean.getText());
        setImageResource(R.id.item_main_img, bean.getImg());
    }
}
