package com.example.song.baisi.jinghua;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.song.baisi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class JingHuaFragment extends Fragment {


    public JingHuaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jing_hua, container, false);
        assignViews(view);
        return view;
    }

    private TabLayout mTab;
    private ViewPager mViewpager;

    private void assignViews(View view) {
        mTab = (TabLayout) view.findViewById(R.id.tab);
        mViewpager = (ViewPager) view.findViewById(R.id.viewpager);

        initView();
    }

    private void initView() {
        final String[] titles = new String[]{"推荐", "视频", "图片", "段子", "排行","社会","美女","冷知识","游戏","网红"};
        final List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            QuanBuFragment fragment = new QuanBuFragment();
            fragments.add(fragment);
        }

        mViewpager.setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        mTab.setupWithViewPager(mViewpager);
    }

}
