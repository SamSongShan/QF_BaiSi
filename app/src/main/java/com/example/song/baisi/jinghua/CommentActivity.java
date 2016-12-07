package com.example.song.baisi.jinghua;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.song.baisi.ApiManger;
import com.example.song.baisi.IApiService;
import com.example.song.baisi.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.id.list;

public class CommentActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private String mId;
    private CommentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        Intent intent = getIntent();
        mId = intent.getStringExtra("id");
        downLoadData();
        assignViews();
    }

    private LinearLayout mActivityComment;
    private SwipeRefreshLayout mSwRefresh;
    private ListView mLvComment;

    private void assignViews() {
        mActivityComment = (LinearLayout) findViewById(R.id.activity_comment);
        mSwRefresh = (SwipeRefreshLayout) findViewById(R.id.swRefresh);
        mLvComment = (ListView) findViewById(R.id.lv_comment);
        initView();

    }


    private void initView() {
        int[] lays=new int[]{R.layout.item_comment};
        mAdapter = new CommentAdapter(this, datas, prectsDatas,lays);
        mLvComment.setAdapter(mAdapter);
        mSwRefresh.setOnRefreshListener(this);
    }

    List<CommentListEntity.NormalEntity.ListEntityX> datas = new ArrayList<>();
List<Precmts1Entity.NormalEntity.ListEntity> prectsDatas=new ArrayList<>();
    private void downLoadData() {
        IApiService api = ApiManger.creatStringApi(IApiService.COMMENT_BASE_URL);
        Call<String> commentTuiJian = api.getCommentTuiJian(mId);

        commentTuiJian.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body().toString();
                Gson gson = new Gson();
                CommentListEntity commentListEntity = gson.fromJson(s, CommentListEntity.class);
                List<CommentListEntity.NormalEntity.ListEntityX> list = commentListEntity.getNormal().getList();
                Precmts1Entity precmts1Entity = gson.fromJson(s, Precmts1Entity.class);
                List<Precmts1Entity.NormalEntity.ListEntity> list1 = precmts1Entity.getNormal().getList();

                if (list != null) {
                    if (isRefresh){
                        prectsDatas.clear();
                        datas.clear();
                    }
                    datas.addAll(list);
                    prectsDatas.addAll(list1);
                    mAdapter.notifyDataSetChanged();
                    mSwRefresh.setRefreshing(false);
                    isRefresh=false;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                isRefresh=false;
                Toast.makeText(getBaseContext(),"网络错误,重新加载",Toast.LENGTH_SHORT).show();
                mSwRefresh.setRefreshing(false);

            }
        });
    }

    boolean isRefresh=false;
    @Override
    public void onRefresh() {
        isRefresh=true;
        downLoadData();
    }
}
