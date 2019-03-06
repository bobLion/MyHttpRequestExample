package com.bob.android.myhttprequestexample.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bob.android.myhttprequestexample.R;
import com.bob.android.myhttprequestexample.adapter.NormalRecycleViewAdapter;
import com.bob.android.myhttprequestexample.adapter.QuickAdapter;
import com.bob.android.myhttprequestexample.adapter.QuickAdapterWrapper;
import com.bob.android.myhttprequestexample.entity.StudentEntity;
import com.bob.android.myhttprequestexample.view.MDGridDividerDecoration;
import com.bob.android.myhttprequestexample.view.SwipeRecycleView;
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
    SwipeRecycleView mReView;

    Context mContext;
    private QuickAdapter mAdapter;
    private NormalRecycleViewAdapter normalRecycleViewAdapter;
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
        mReView.setLayoutManager(new LinearLayoutManager(this));
        initView();
//       mReView.setLayoutManager(new GridLayoutManager(this,4, OrientationHelper.VERTICAL,false));

        QuickAdapterWrapper newAdapter = new QuickAdapterWrapper(normalRecycleViewAdapter);
        View headerView = LayoutInflater.from(this).inflate(R.layout.item_header,mReView,false);
        View footerView = LayoutInflater.from(this).inflate(R.layout.item_footer,mReView,false);
        newAdapter.addFooterView(footerView);
        newAdapter.addHeaderView(headerView);
        mReView.setAdapter(newAdapter);
//        mReView.addItemDecoration(new MDGridDividerDecoration(this));
    }

    private void initView() {
        for(int i = 0;i<50;i++){
            StudentEntity stu = new StudentEntity();
            stu.setStuClass("1991").setStuIn("100"+i).setStuName("Alex"+i);
            studentEntityList.add(stu);
        }
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

       /*mAdapter = new QuickAdapter<StudentEntity>(studentEntityList,mContext) {
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
       };*/

        normalRecycleViewAdapter = new NormalRecycleViewAdapter(mContext,studentEntityList);
        normalRecycleViewAdapter.setOnItemClickListener(new NormalRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext,"点击了" + position,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(mContext,"长按了" +  position,Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.btn_back)
    void btnBack(View view){
        finish();
    }
}
