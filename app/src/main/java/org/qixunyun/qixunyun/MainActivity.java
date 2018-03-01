package org.qixunyun.qixunyun;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.youth.banner.Banner;

import org.qixunyun.qixunyun.bean.MainBean;
import org.qixunyun.qixunyun.holder.MainHolder;
import org.qixunyun.qixunyun.recyclerview.SuperAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Integer[] imgs = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private String[] texts = {"会员管理", "新增会员", "快速消费", "会员充值", "商品管理", "加减积分",
            "商品消费", "会员冲次", "礼品管理", "积分管理", "计次消费", "更多功能"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        mDrawerLayout = findViewById(R.id.main_drawer_layout);

        initToolbar(toolbar);

        initBanner();

        initRV();
    }

    private void initToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //Toolbar与DrawerLayout关联
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        mDrawerLayout.addDrawerListener(drawerToggle);
    }

    private void initBanner() {
        List<Integer> imgList = new ArrayList<>();
        Collections.addAll(imgList, imgs);
        Banner banner = findViewById(R.id.main_banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imgList);
        banner.start();
    }

    private void initRV() {
        RecyclerView rv = findViewById(R.id.main_recycler_view);
        rv.setLayoutManager(new GridLayoutManager(this, 4));
        rv.setAdapter(new SuperAdapter<>(getList(), MainHolder.class));
    }

    private List<MainBean> getList() {
        List<MainBean> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {

            MainBean bean = new MainBean();
            bean.setText(texts[i]);
            list.add(bean);
        }
        return list;
    }
}

