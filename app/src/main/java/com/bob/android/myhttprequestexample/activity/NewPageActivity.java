package com.bob.android.myhttprequestexample.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bob.android.myhttprequestexample.R;
import com.bob.android.myhttprequestexample.adapter.QuickAdapter;
import com.bob.android.myhttprequestexample.adapter.QuickViewHolder;
import com.bob.android.myhttprequestexample.entity.StudentEntity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewPageActivity extends Activity {

    @BindView(R.id.img_page)
    ImageView mImgPage;
    @BindView(R.id.re_view)
    RecyclerView mReView;

    Context mContext;
    private QuickAdapter mAdapter;
    private List<StudentEntity> studentEntityList = new ArrayList<>();
    private final int TYPE_1 = 0001;
    private final int TYPE_2 = 0002;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_page);
        ButterKnife.bind(this);
        mContext = this;
//        mImgPage.setImageDrawable(mContext.getDrawable(R.mipmap.img));
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.img,null);
//        mImgPage.setImageBitmap(bitmap);
        Glide.with(this).load(R.mipmap.img).into(mImgPage);
        initView();
    }

    private void initView() {
       /* mAdapter = new QuickAdapter<StudentEntity>(studentEntityList,mContext) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_recyecle_view;
            }

            @Override
            public void convert(QuickViewHolder holder, StudentEntity data, int position) {
                holder.setText(R.id.tv_stu_name,data.getStuName());
                holder.setImag(R.id.img_stu_photo,data.getStuName(),mContext);
            }
        };*/

       mAdapter = new QuickAdapter<StudentEntity>(studentEntityList,mContext) {
           @Override
           public int getLayoutId(int viewType) {
               switch (viewType){
                   case TYPE_1:
                       return R.layout.item_recyecle_view;
                   case TYPE_2:
                       return R.layout.item_recyecle_view1;
                       default:
                           return R.layout.item_recyecle_view;
               }
           }

           @Override
           public int getItemViewType(int position) {
               if(position%2 == 0){
                   return TYPE_1;
               }else{
                   return TYPE_2;
               }
           }

           @Override
           public void convert(QuickViewHolder holder, StudentEntity data, int position) {
                int type = getItemViewType(position);
                switch (type){
                    case TYPE_1:
                        holder.setText(R.id.tv_stu_name,data.getStuName());
                        break;
                    case TYPE_2:
                        holder.setImag(R.id.img_stu_photo,data.getStuClass(),mContext);
                        break;
                }
           }


       };
    }

    @OnClick(R.id.btn_back)
    void btnBack(View view){
        finish();
    }
}
