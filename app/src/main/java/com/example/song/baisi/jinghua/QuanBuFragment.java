package com.example.song.baisi.jinghua;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.song.baisi.ApiManger;
import com.example.song.baisi.IApiService;
import com.example.song.baisi.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuanBuFragment extends Fragment implements AbsListView.OnScrollListener {


    private static final String TAG = "tag";
    private QuanbuAdapter mAdapter;

    public QuanBuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quan_bu, container, false);
        assignViews(view);
        return view;
    }

    private ListView mLv;
    private List<QuanBuEntity.ListEntity> datas = new ArrayList<>();

    private void assignViews(View view) {
        mLv = (ListView) view.findViewById(R.id.lv);

        initView();
    }

    private void initView() {
        mAdapter = new QuanbuAdapter(getContext(), datas, R.layout.item_shipin, R.layout.itme_gif, R.layout.item_photo, R.layout.item_shipin, R.layout.itme_html);

        mLv.setAdapter(mAdapter);
        mLv.setOnScrollListener(this);
        IApiService iApiService = ApiManger.creatApi();
        Call<QuanBuEntity> tuijian = iApiService.getTuijian();
        tuijian.enqueue(new Callback<QuanBuEntity>() {
            @Override
            public void onResponse(Call<QuanBuEntity> call, Response<QuanBuEntity> response) {
                List<QuanBuEntity.ListEntity> list = response.body().getList();
                datas.addAll(list);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<QuanBuEntity> call, Throwable t) {

            }
        });
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mAdapter.oldPosition!=-1){
            if (firstVisibleItem>mAdapter.oldPosition||firstVisibleItem+visibleItemCount<mAdapter.oldPosition){
                mAdapter.onScroll();
            }
        }

    }
}
