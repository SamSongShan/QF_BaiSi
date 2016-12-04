package com.example.song.baisi.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.song.baisi.EmptyFragment;
import com.example.song.baisi.R;
import com.example.song.baisi.guanzhu.GuanZhuFragment;
import com.example.song.baisi.jinghua.JingHuaFragment;
import com.example.song.baisi.wode.WoDeFragment;
import com.example.song.baisi.xintie.XinTieFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        assignViews();
        assignViews();
    }

    private LinearLayout mActivityMain;
    private FrameLayout mFrame;
    private FragmentTabHost mTabhost;
    private FrameLayout mContent;

    private void assignViews() {
        mActivityMain = (LinearLayout) findViewById(R.id.activity_main);
        mFrame = (FrameLayout) findViewById(R.id.frame);
        mTabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mContent = (FrameLayout) findViewById(android.R.id.content);
        initView();
    }

    private void initView() {
        List<Fragment> fragments = new ArrayList<>();
    fragments.add(new JingHuaFragment());
        fragments.add(new XinTieFragment());
        fragments.add(new EmptyFragment());
        fragments.add(new GuanZhuFragment());
        fragments.add(new WoDeFragment());
mTabhost.setup(this,getSupportFragmentManager(), R.id.frame);
        for (int i = 0; i < fragments.size(); i++) {
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(i + "");
            View view = getView(i);
            tabSpec.setIndicator(view);
            mTabhost.addTab(tabSpec,fragments.get(i).getClass(),null);
            mTabhost.getTabWidget().setDividerDrawable(null);
        }

    }
    public View getView(int i) {
        String[] tabs = {"精华", "新帖", "","关注", "我的"};
        int[] tab_imgs = {
                R.drawable.bt_jinghua,
                R.drawable.bt_xintie,
                R.drawable.bt_xintie,
                R.drawable.bt_guanzhu,
                R.drawable.bt_wode,
        };
        View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
        if (i!=2){
            ImageView img = (ImageView) view.findViewById(R.id.img);
            TextView tv = (TextView) view.findViewById(R.id.tv);
            img.setImageResource(tab_imgs[i]);
            tv.setText(tabs[i]);
        }


        return view;
    }


//    private LinearLayout mActivityMain;
//    private FrameLayout mFrame;
//    private RelativeLayout mRelatJinghua;
//    private ImageView mImgJinghua;
//    private TextView mTvJinghua;
//    private RelativeLayout mRelatXintie;
//    private ImageView mImgXintie;
//    private TextView mTvXintie;
//    private ImageView mImgFabiao;
//    private RelativeLayout mRelatGuanzhu;
//    private ImageView mImgGuanzhu;
//    private TextView mTvGuanzhu;
//    private RelativeLayout mRelatWode;
//    private ImageView mImgWode;
//    private TextView mTvWode;
//
//    private void assignViews() {
//        mActivityMain = (LinearLayout) findViewById(R.id.activity_main);
//        mFrame = (FrameLayout) findViewById(R.id.frame);
//        mRelatJinghua = (RelativeLayout) findViewById(R.id.relat_jinghua);
//        mImgJinghua = (ImageView) findViewById(R.id.img_jinghua);
//        mTvJinghua = (TextView) findViewById(R.id.tv_jinghua);
//        mRelatXintie = (RelativeLayout) findViewById(R.id.relat_xintie);
//        mImgXintie = (ImageView) findViewById(R.id.img_xintie);
//        mTvXintie = (TextView) findViewById(R.id.tv_xintie);
//        mImgFabiao = (ImageView) findViewById(R.id.img_fabiao);
//        mRelatGuanzhu = (RelativeLayout) findViewById(R.id.relat_guanzhu);
//        mImgGuanzhu = (ImageView) findViewById(R.id.img_guanzhu);
//        mTvGuanzhu = (TextView) findViewById(R.id.tv_guanzhu);
//        mRelatWode = (RelativeLayout) findViewById(R.id.relat_wode);
//        mImgWode = (ImageView) findViewById(R.id.img_wode);
//        mTvWode = (TextView) findViewById(R.id.tv_wode);
//
//        initView();
//    }
//
//
//    private void initView() {
//        mImgJinghua.setSelected(true);
//        mTvJinghua.setSelected(true);
//        mRelatJinghua.setOnClickListener(this);
//        mRelatJinghua.setTag(0);
//        mRelatXintie.setOnClickListener(this);
//        mRelatXintie.setTag(1);
//        mRelatGuanzhu.setOnClickListener(this);
//        mRelatGuanzhu.setTag(2);
//        mRelatWode.setOnClickListener(this);
//        mRelatWode.setTag(3);
//        mFragments = new ArrayList<>();
//        mFragments.add(new JingHuaFragment());
//        mFragments.add(new XinTieFragment());
//        mFragments.add(new GuanZhuFragment());
//        mFragments.add(new WoDeFragment());
//        //getSupportFragmentManager().beginTransaction().replace(R.id.frame, new JingHuaFragment()).commit();
//        changeTab(0);
//    }
//
//    private Fragment mCurrentFrgment;
//    private int currentIndex = 0;
//
//    private void changeTab(int i) {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        if (mCurrentFrgment != null) {
//            fragmentTransaction.hide(mCurrentFrgment);
//        }
//        Fragment fragment = getSupportFragmentManager().findFragmentByTag(mFragments.get(i).getClass().getName());
//        if (fragment == null) {
//            fragment = mFragments.get(i);
//        }
//        mCurrentFrgment = fragment;
//        if (!fragment.isAdded()) {
//            fragmentTransaction.add(R.id.frame, fragment, fragment.getClass().getName());
//        } else {
//            fragmentTransaction.show(fragment);
//        }
//        fragmentTransaction.commit();
//    }
//
//    @Override
//    public void onClick(View v) {
//        setUnselect();
//        int tag = (int) v.getTag();
//
//        switch (v.getId()) {
//            case R.id.relat_jinghua:
////                getSupportFragmentManager().beginTransaction().replace(R.id.frame,new JingHuaFragment()).commit();
//                mImgJinghua.setSelected(true);
//                mTvJinghua.setSelected(true);
//                break;
//            case R.id.relat_xintie:
////                getSupportFragmentManager().beginTransaction().replace(R.id.frame,new XinTieFragment()).commit();
//
//                mImgXintie.setSelected(true);
//                mTvXintie.setSelected(true);
//                break;
//            case R.id.relat_guanzhu:
////                getSupportFragmentManager().beginTransaction().replace(R.id.frame,new GuanZhuFragment()).commit();
//
//                mImgGuanzhu.setSelected(true);
//                mTvGuanzhu.setSelected(true);
//                break;
//
//            case R.id.relat_wode:
////                getSupportFragmentManager().beginTransaction().replace(R.id.frame,new WoDeFragment()).commit();
//
//                mImgWode.setSelected(true);
//                mTvWode.setSelected(true);
//                break;
//        }
//        changeTab(tag);
//    }
//
//    private void setUnselect() {
//        mImgJinghua.setSelected(false);
//        mImgXintie.setSelected(false);
//        mImgGuanzhu.setSelected(false);
//        mImgWode.setSelected(false);
//
//        mTvJinghua.setSelected(false);
//        mTvXintie.setSelected(false);
//        mTvGuanzhu.setSelected(false);
//        mTvWode.setSelected(false);
//
//
//    }
}
