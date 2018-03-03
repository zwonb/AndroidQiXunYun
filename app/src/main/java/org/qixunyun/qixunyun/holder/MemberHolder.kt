package org.qixunyun.qixunyun.holder

import android.view.ViewGroup
import org.qixunyun.qixunyun.R
import org.qixunyun.qixunyun.bean.MemberBean
import org.qixunyun.qixunyun.recyclerview.SuperViewHolder

/**
 * Created by zwonb on 2018/3/3.
 */
class MemberHolder(parent: ViewGroup?) : SuperViewHolder<MemberBean>(parent, R.layout.item_member_manage) {

    override fun setDate(bean: MemberBean?) {
        setText(R.id.item_member_name, bean?.name)
        setText(R.id.item_member_sex, bean?.sex)
    }
}