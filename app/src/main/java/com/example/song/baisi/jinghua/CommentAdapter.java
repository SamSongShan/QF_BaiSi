package com.example.song.baisi.jinghua;

import android.content.Context;

import com.example.song.baisi.CommonAdapter;

import java.util.List;

/**
 * Created by 11355 on 2016/12/4.
 */

public class CommentAdapter extends CommonAdapter<QuanBuEntity.ListEntity.TopCommentsEntity> {

    public CommentAdapter(Context context, List<QuanBuEntity.ListEntity.TopCommentsEntity> data, int... layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void bindData(int position, viewHolder mHolder) {

    }
}
