package com.example.song.baisi.main;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.song.baisi.R;
import com.example.song.baisi.jinghua.JingHuaFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
    }

    private LinearLayout mActivityMain;
    private FrameLayout mFrame;
    private RelativeLayout mRelatJinghua;
    private ImageView mImgJinghua;
    private TextView mTvJinghua;
    private RelativeLayout mRelatXintie;
    private ImageView mImgXintie;
    private TextView mTvXintie;
    private ImageView mImgFabiao;
    private RelativeLayout mRelatGuanzhu;
    private ImageView mImgGuanzhu;
    private TextView mTvGuanzhu;
    private RelativeLayout mRelatWode;
    private ImageView mImgWode;
    private TextView mTvWode;

    private void assignViews() {
        mActivityMain = (LinearLayout) findViewById(R.id.activity_main);
        mFrame = (FrameLayout) findViewById(R.id.frame);
        mRelatJinghua = (RelativeLayout) findViewById(R.id.relat_jinghua);
        mImgJinghua = (ImageView) findViewById(R.id.img_jinghua);
        mTvJinghua = (TextView) findViewById(R.id.tv_jinghua);
        mRelatXintie = (RelativeLayout) findViewById(R.id.relat_xintie);
        mImgXintie = (ImageView) findViewById(R.id.img_xintie);
        mTvXintie = (TextView) findViewById(R.id.tv_xintie);
        mImgFabiao = (ImageView) findViewById(R.id.img_fabiao);
        mRelatGuanzhu = (RelativeLayout) findViewById(R.id.relat_guanzhu);
        mImgGuanzhu = (ImageView) findViewById(R.id.img_guanzhu);
        mTvGuanzhu = (TextView) findViewById(R.id.tv_guanzhu);
        mRelatWode = (RelativeLayout) findViewById(R.id.relat_wode);
        mImgWode = (ImageView) findViewById(R.id.img_wode);
        mTvWode = (TextView) findViewById(R.id.tv_wode);

        initView();
    }


    private void initView() {
        mImgJinghua.setSelected(true);
        mTvJinghua.setSelected(true);
        mRelatJinghua.setOnClickListener(this);
        mRelatXintie.setOnClickListener(this);
        mRelatGuanzhu.setOnClickListener(this);
        mRelatWode.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new JingHuaFragment()).commit();

    }

    @Override
    public void onClick(View v) {
        setUnselect();
        switch (v.getId()) {
            case R.id.relat_jinghua:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new JingHuaFragment()).commit();

                mImgJinghua.setSelected(true);
                mTvJinghua.setSelected(true);
                break;
            case R.id.relat_xintie:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new JingHuaFragment()).commit();

                mImgXintie.setSelected(true);
                mTvXintie.setSelected(true);
                break;
            case R.id.relat_guanzhu:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new JingHuaFragment()).commit();

                mImgGuanzhu.setSelected(true);
                mTvGuanzhu.setSelected(true);
                break;

            case R.id.relat_wode:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame, new JingHuaFragment()).commit();

                mImgWode.setSelected(true);
                mTvWode.setSelected(true);
                break;
        }
    }

    private void setUnselect() {
        mImgJinghua.setSelected(false);
        mImgXintie.setSelected(false);
        mImgGuanzhu.setSelected(false);
        mImgWode.setSelected(false);

        mTvJinghua.setSelected(false);
        mTvXintie.setSelected(false);
        mTvGuanzhu.setSelected(false);
        mTvWode.setSelected(false);


    }
}
