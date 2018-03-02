package org.qixunyun.qixunyun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.banner.Banner;

import org.qixunyun.qixunyun.bean.MainBean;
import org.qixunyun.qixunyun.holder.MainHolder;
import org.qixunyun.qixunyun.recyclerview.SuperAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Integer[] imgs = {R.mipmap.pic_main_banner, R.mipmap.pic_main_banner, R.mipmap.pic_main_banner};

    private Integer[] mainImg = {R.mipmap.pic_main_pay, R.mipmap.pic_main_score, R.mipmap.pic_main_member,
            R.mipmap.pic_main_notice, R.mipmap.pic_main_goods, R.mipmap.pic_main_order, R.mipmap.pic_main_present, R.mipmap.pic_convert,
            R.mipmap.pic_main_collect, R.mipmap.pic_main_coupon, R.mipmap.pic_main_activity};
    private String[] mainText = {"充值管理 ", "积分管理", "会员管理", "会员通知 ", "商品管理", "商品订单",
            "礼品管理", "兑换订单", "扫码收账", "卡券管理", "活动营销"};
    private Integer[] leftImg = {R.mipmap.ic_main_left_website, R.mipmap.ic_main_left_shore, R.mipmap.ic_main_left_score,
            R.mipmap.ic_main_left_share, R.mipmap.ic_main_left_set, R.mipmap.ic_main_left_about, R.mipmap.ic_main_left_exit};
    private String[] leftStr = {"微官网", "自营商城", "积分兑换", "好友分享", "系统设置", "关于我们", "退出登录"};

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

        initLeftList();
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

    private void initLeftList() {
        LinearLayout list = findViewById(R.id.main_left_list);
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < leftStr.length; i++) {
            if (i == 3) {
                View divider = new View(this);
                divider.setBackgroundColor(0xfff0f0f0);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, getDimension(2));
                lp.leftMargin = getDimension(8);
                lp.topMargin = getDimension(8);
                lp.rightMargin = getDimension(8);
                lp.bottomMargin = getDimension(8);
                divider.setLayoutParams(lp);
                list.addView(divider);
            }
            View view = inflater.inflate(R.layout.item_main_left, list, false);
            ((ImageView) view.findViewById(R.id.item_main_img)).setImageResource(leftImg[i]);
            ((TextView) view.findViewById(R.id.item_main_text)).setText(leftStr[i]);
            list.addView(view);
        }
    }

    private List<MainBean> getList() {
        List<MainBean> list = new ArrayList<>();
        for (int i = 0; i < mainText.length; i++) {
            MainBean bean = new MainBean(mainImg[i], mainText[i]);
            list.add(bean);
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_scan_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.main_scan) {
            Intent scan = new Intent(this, CaptureActivity.class);
            startActivityForResult(scan, RequestCode.CODE1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCode.CODE1 && data != null) {
            //处理扫描结果（在界面上显示）
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }
            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String result = bundle.getString(CodeUtils.RESULT_STRING);
                Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
            } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * 转换获取dp值
     */
    private int getDimension(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}

