package org.qixunyun.qixunyun.recyclerview;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * RecyclerView ViewHolder 的超类
 * Created by zwonb on 2018/1/19.
 */

public abstract class SuperViewHolder<E> extends RecyclerView.ViewHolder {

    protected Context mContext;
    private SparseArray<View> mSparseArray;
    private SuperAdapter mAdapter;

    public SuperViewHolder(ViewGroup parent, @LayoutRes int itemLayout) {
        super(LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false));
        if (mContext == null) {
            mContext = parent.getContext();
        }
        if (mSparseArray == null) {
            mSparseArray = new SparseArray<>();
        }
    }

    /**
     * 查找 itemView 的id
     * {@link #itemView}
     */
    @SuppressWarnings("unchecked cast")
    protected <V extends View> V getView(@IdRes int id) {
        View view = mSparseArray.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mSparseArray.put(id, view);
        }
        return (V) view;
    }

    /**
     * 设置 textView 的数据
     */
    protected void setText(@IdRes int id, CharSequence text) {
        ((TextView) getView(id)).setText(text);
    }

    /**
     * 设置 ImageView 的图片
     */
    protected void setImageResource(@IdRes int id, @DrawableRes int resId) {
        ((ImageView) getView(id)).setImageResource(resId);
    }

    protected abstract void setDate(E bean);

    void setAdapter(SuperAdapter adapter) {
        mAdapter = adapter;
    }

    protected void addOnItemChildClickListener(@IdRes int id) {
        final View view = getView(id);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SuperAdapter.OnItemChildClickListener listener = mAdapter.getOnItemChildClickListener();
                    if (mAdapter != null) {
                        if (listener != null) {
                            listener.onItemChildClick(mAdapter, v, getLayoutPosition());
                        }
                    }
                }
            });
        }
    }

}
