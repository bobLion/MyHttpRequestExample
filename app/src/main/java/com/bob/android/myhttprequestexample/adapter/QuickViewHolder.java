package com.bob.android.myhttprequestexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * @package com.bob.android.myhttprequestexample.adapter
 * @fileName QuickViewHolder
 * @Author Bob on 2019/2/21 13:30.
 * @Describe TODO
 */
public class QuickViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;
    private View mContentView;

    public QuickViewHolder(View itemView) {
        super(itemView);
        mContentView = itemView;
        mViews = new SparseArray<>();
    }


    public static QuickViewHolder get(ViewGroup parent,int layoutId){
        View contentView = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);
        return new QuickViewHolder(contentView);
    }

    public <T extends View> T getView(int id){
        View v = mViews.get(id);
        if(v == null){
            v = mContentView.findViewById(id);
            mViews.put(id,v);
        }
        return (T) v;
    }

    public void setText(int id,String value){
        TextView view = getView(id);
        view.setText(value);
    }

    public void setImag(int id,String imgString,Context context){
        ImageView view = getView(id);
        Glide.with(context).load(imgString).into(view);
    }
}
