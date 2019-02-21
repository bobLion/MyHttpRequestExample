package com.bob.android.myhttprequestexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bob.android.myhttprequestexample.R;
import com.bob.android.myhttprequestexample.entity.StudentEntity;

import java.util.List;

/**
 * @package com.bob.android.myhttprequestexample.adapter
 * @fileName NormalRecycleViewAdapter
 * @Author Bob on 2019/2/21 10:36.
 * @Describe TODO
 */
public class NormalRecycleViewAdapter extends RecyclerView.Adapter<NormalRecycleViewAdapter.VH> {

    private Context mContext;
    private List<StudentEntity> studentEntityList;

    public NormalRecycleViewAdapter(Context context,List<StudentEntity> studentEntities){
        this.mContext = context;
        this.studentEntityList = studentEntities;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyecle_view,parent,false);
        VH viewHolder = new VH(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.title.setText(studentEntityList.get(position).getStuName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return studentEntityList.size();
    }

    public static class VH extends RecyclerView.ViewHolder{
        public final TextView title;
        public final ImageView mImgStuImg;

        public VH(View v){
            super(v);
            title = (TextView)v.findViewById(R.id.tv_stu_name);
            mImgStuImg = (ImageView) v.findViewById(R.id.img_stu_photo);
        }
    }

}
