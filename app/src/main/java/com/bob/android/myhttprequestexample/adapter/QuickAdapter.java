package com.bob.android.myhttprequestexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @package com.bob.android.myhttprequestexample.adapter
 * @fileName QuickAdapter
 * @Author Bob on 2019/2/21 13:40.
 * @Describe 万能recycleView适配器，具体实现查看NewPageActivity
 */
public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickViewHolder> {
    private List<T> mDatas;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public QuickAdapter(List<T> datas,Context context){
        this.mDatas = datas;
        this.mContext = context;
    }

    public abstract int getLayoutId(int viewType);

    @Override
    public QuickViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return QuickViewHolder.get(parent,getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(QuickViewHolder holder, int position) {
        convert(holder,mDatas.get(position),position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public abstract void convert(QuickViewHolder holder, T data, int position);


    public interface OnItemClickListener{
        void OnItemClick(View view,int position);
        void OnItemLongClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener  = listener;
    }


    public void updateData(ArrayList<T> data){
        this.mDatas = data;
        notifyDataSetChanged();
    }

    public void adddNewItem(T data){
        if(mDatas == null){
            mDatas = new ArrayList<>();
        }
        mDatas.add(0,data);
        notifyItemChanged(0);
    }

    public void deleteItem(int position){
        if(mDatas == null || mDatas.isEmpty()){
            return;
        }
        mDatas.remove(position);
        notifyItemRemoved(position);
    }


}
