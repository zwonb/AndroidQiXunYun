package org.qixunyun.qixunyun.home.member

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.recycler_view.*
import org.qixunyun.qixunyun.R
import org.qixunyun.qixunyun.bean.MemberBean
import org.qixunyun.qixunyun.holder.MemberHolder
import org.qixunyun.qixunyun.recyclerview.SuperAdapter
import java.util.*

class MemberUIA : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view)

        init()
    }

    private fun init() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = SuperAdapter(list, MemberHolder::class.java)
    }

    private val list: List<MemberBean>
        get() {
            val list = ArrayList<MemberBean>()
            for (i in 0..30) {
                val bean = MemberBean("", "张三", "男")
                list.add(bean)
            }
            return list
        }

}
