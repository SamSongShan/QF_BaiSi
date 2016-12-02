package com.example.song.baisi.jinghua;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.song.baisi.CommonAdapter;
import com.example.song.baisi.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;
import java.util.List;

/**
 * Created by 11355 on 2016/11/28.
 */

public class QuanbuAdapter extends CommonAdapter<QuanBuEntity.ListEntity> implements View.OnClickListener, MediaPlayer.OnPreparedListener, SurfaceHolder.Callback {


    public static final int TYPE_VIDEO = 0;
    public static final int TYPE_GIF = 1;
    public static final int TYPE_PHOTO = 2;
    public static final int TYPE_TEXT = 3;
    public static final int TYPE_HTML = 4;
    private final Context mContext;
    private final List<QuanBuEntity.ListEntity> mData;
    private final int[] mLayoutId;
    private MediaPlayer mMediaPlayer;
    private SimpleDraweeView mSimplePlay;
    private RelativeLayout mRelatPlay;
    private SimpleDraweeView mSimpleDraweeView;
    private TextView mTvContent;

    public QuanbuAdapter(Context context, List<QuanBuEntity.ListEntity> data, int... layoutId) {
        super(context, data, layoutId);
        mContext = context;
        mData = data;
        mLayoutId = layoutId;
    }

    @Override
    public void bindData(int position, viewHolder mHolder) {
        switch (getItemViewType(position)) {
            case TYPE_VIDEO:
                assignViewsVedio(mHolder);
                initViewVedio(position);
                break;
            case TYPE_GIF:
                break;
            case TYPE_PHOTO:
                break;
            case TYPE_TEXT:
                break;
            case TYPE_HTML:
                break;
        }
    }

    private SimpleDraweeView mSimpleHaed;
    private TextView mTvRoomname;
    private TextView mTvTime;
    private TextView mTvText;
    private SurfaceView mSf;
    private RelativeLayout mLineDainzan;
    private ImageView mImgDianzan;
    private TextView mTvDianzan;
    private RelativeLayout mLineUndianzan;
    private ImageView mImgUndianzan;
    private TextView mTvUndianzan;
    private RelativeLayout mLineFenxiang;
    private ImageView mImgFenxiang;
    private TextView mTvFenxiang;
    private RelativeLayout mLinePinglun;
    private ImageView mImgPinglun;
    private TextView mTvPinglun;
    private ListView mLv;
    private ImageView mImgPlay;

    private void assignViewsVedio(viewHolder view) {

        mRelatPlay = (RelativeLayout) view.mView.findViewById(R.id.relat_play);

        mSimplePlay = (SimpleDraweeView) view.mView.findViewById(R.id.simple_Play);
        mSimpleHaed = (SimpleDraweeView) view.mView.findViewById(R.id.simple_head);
        mTvRoomname = (TextView) view.mView.findViewById(R.id.tv_roomname);
        mTvTime = (TextView) view.mView.findViewById(R.id.tv_time);
        mTvText = (TextView) view.mView.findViewById(R.id.tv_text);
        mSf = (SurfaceView) view.mView.findViewById(R.id.sf);
        mLineDainzan = (RelativeLayout) view.mView.findViewById(R.id.line_dainzan);
        mImgDianzan = (ImageView) view.mView.findViewById(R.id.img_dianzan);
        mTvDianzan = (TextView) view.mView.findViewById(R.id.tv_dianzan);
        mLineUndianzan = (RelativeLayout) view.mView.findViewById(R.id.line_undianzan);
        mImgUndianzan = (ImageView) view.mView.findViewById(R.id.img_undianzan);
        mTvUndianzan = (TextView) view.mView.findViewById(R.id.tv_undianzan);
        mLineFenxiang = (RelativeLayout) view.mView.findViewById(R.id.line_fenxiang);
        mImgFenxiang = (ImageView) view.mView.findViewById(R.id.img_fenxiang);
        mTvFenxiang = (TextView) view.mView.findViewById(R.id.tv_fenxiang);
        mLinePinglun = (RelativeLayout) view.mView.findViewById(R.id.line_pinglun);
        mImgPinglun = (ImageView) view.mView.findViewById(R.id.img_pinglun);
        mTvPinglun = (TextView) view.mView.findViewById(R.id.tv_pinglun);
        mLv = (ListView) view.mView.findViewById(R.id.lv);
        mImgPlay = (ImageView) view.mView.findViewById(R.id.img_play);
        mTvContent = (TextView) view.mView.findViewById(R.id.tv_content);
    }

    public int oldPosition = -1;
    int num = 0;

    private void initViewVedio(int position) {
        RelativeLayout.LayoutParams paramsAll = (RelativeLayout.LayoutParams) mSf.getLayoutParams();
        paramsAll.width = 0;
        paramsAll.height = 0;
        mSf.setLayoutParams(paramsAll);

        //视频设置
        if (mData.get(position).getType().equals("video")) {
            mTvContent.setVisibility(View.GONE);
            mImgPlay.setVisibility(View.VISIBLE);
            mSimplePlay.setVisibility(View.VISIBLE);
            //设置视宽高
            int height = mData.get(position).getVideo().getHeight();
            int width = mData.get(position).getVideo().getWidth();
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mSf.getLayoutParams();
            params.width = mContext.getResources().getDisplayMetrics().widthPixels;
            if (mContext.getResources().getDisplayMetrics().widthPixels * height / width < 1000) {
                params.height = mContext.getResources().getDisplayMetrics().widthPixels * height / width;
            } else {
                params.height = 1000;
            }
            mSf.setLayoutParams(params);
            //设置遮挡图片宽高
            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) mSimplePlay.getLayoutParams();
            params1.width = mContext.getResources().getDisplayMetrics().widthPixels;
            if (mContext.getResources().getDisplayMetrics().widthPixels * height / width < 1000) {
                params1.height = mContext.getResources().getDisplayMetrics().widthPixels * height / width;
            } else {
                params1.height = 1000;
            }
            mSimplePlay.setLayoutParams(params1);
            mSimplePlay.setImageURI(Uri.parse(mData.get(position).getVideo().getThumbnail().get(0)));
            String url = mData.get(position).getVideo().getVideo().get(0);
            if (position == oldPosition) {
                if (isvideo) {
                    if (isfirst) {
                        if (mMediaPlayer != null) {
                            mMediaPlayer.stop();
                            mMediaPlayer.release();
                        }
                        mMediaPlayer = new MediaPlayer();
                        mMediaPlayer.setOnPreparedListener(this);
                        SurfaceHolder holder = mSf.getHolder();
                        holder.addCallback(this);


                        try {
                            mMediaPlayer.setDataSource(mContext, Uri.parse(url));
                            mMediaPlayer.prepareAsync();
                            mSf.setVisibility(View.VISIBLE);
                            mImgPlay.setVisibility(View.GONE);
                            mSimplePlay.setVisibility(View.GONE);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (mMediaPlayer.isPlaying()) {
                            mMediaPlayer.pause();
                        } else {
                            mMediaPlayer.start();
                            mImgPlay.setVisibility(View.GONE);
                        }
                    }
                } else {
                    if (mMediaPlayer != null) {
                        mMediaPlayer.stop();
                        mMediaPlayer.release();
                        isfirst = true;
                        isvideo = false;
                    }
                }


            } else {
                mSf.setVisibility(View.INVISIBLE);
                mImgPlay.setVisibility(View.VISIBLE);
                mSimplePlay.setVisibility(View.VISIBLE);
            }
            mImgPlay.setTag(position);
            mRelatPlay.setOnClickListener(this);
            mRelatPlay.setTag(-2, mImgPlay);
            mRelatPlay.setTag(-1, mData.get(position).getType());
            mImgPlay.setOnClickListener(this);
        }
        //文字部分
        if (mData.get(position).getType().equals("text")) {

            mImgPlay.setVisibility(View.GONE);
            mSimplePlay.setVisibility(View.GONE);
            mTvContent.setVisibility(View.VISIBLE);
            String text = mData.get(position).getText();
            mTvContent.setText(text);
        } else {

        }
        //GIF部分
        if (mData.get(position).getType().equals("gif")) {
            mImgPlay.setVisibility(View.GONE);
            mTvContent.setVisibility(View.GONE);
            mSimplePlay.setVisibility(View.VISIBLE);
            mSimplePlay.setImageURI(Uri.parse(mData.get(position).getGif().getGif_thumbnail().get(0)));
            DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                    .setAutoPlayAnimations(true)
                    //加载drawable里的一张gif图
                    .setUri(Uri.parse(mData.get(position).getGif().getImages().get(0)))//设置uri
                    .build();
            //设置Controller
            mSimplePlay.setController(mDraweeController);

        } else {


        }


    }


    @Override
    public int getItemViewType(int position) {

        switch (mData.get(position).getType()) {
            case "video":
                return TYPE_VIDEO;
            case "gif":
                return TYPE_VIDEO;
            case "image":
                return TYPE_PHOTO;
            case "text":
                return TYPE_VIDEO;
            case "html":
                return TYPE_HTML;
        }

        return -1;
    }

    String oldurl = "_1";
    boolean isfirst = false;
    boolean isvideo = false;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_play: {
                int tag = (int) v.getTag();
                if (mData.get(tag).getType().equals("video")) {
                    isvideo = true;
                    if (oldPosition == tag) {
                        isfirst = false;
                    } else {
                        isfirst = true;
                    }
                } else {
                    isvideo = false;
                    isfirst = false;
                }

                oldPosition = tag;
                notifyDataSetChanged();
            }
            break;
            case R.id.relat_play: {
                String type = (String) v.getTag(-1);
                if ("video".equals(type)) {
                    if (mMediaPlayer != null) {
                        if (mMediaPlayer.isPlaying()) {

                            ImageView imgPlay = (ImageView) v.getTag(-2);
                            imgPlay.setVisibility(View.VISIBLE);
                            mMediaPlayer.pause();

                        }
                    }
                }


            }
            break;
        }

    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mMediaPlayer.setDisplay(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void onScroll() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        oldPosition = -1;
        isfirst = false;
        isvideo = false;
    }
}
