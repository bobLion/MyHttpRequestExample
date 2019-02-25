package com.bob.android.myhttprequestexample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * @package com.bob.android.myhttprequestexample.adapter
 * @fileName QuickAdapterWrapper
 * @Author Bob on 2019/2/25 9:51.
 * @Describe TODO
 */
public class QuickAdapterWrapper extends RecyclerView.Adapter<NormalRecycleViewAdapter.VH> {

    private NormalRecycleViewAdapter mAdapter;
    private View mHeaderView,mFooterView;

    enum ITEM_TYPE{
        HEADER,
        FOOTER,
        NORMAL
    }

    public QuickAdapterWrapper (NormalRecycleViewAdapter adapter){
        this.mAdapter = adapter;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return ITEM_TYPE.HEADER.ordinal();
        }else if(position == mAdapter.getItemCount()+1){
            return ITEM_TYPE.FOOTER.ordinal();
        }else{
            return  ITEM_TYPE.NORMAL.ordinal();
        }
    }

    @Override
    public NormalRecycleViewAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE.HEADER.ordinal()){
            return new NormalRecycleViewAdapter.VH(mHeaderView) {};
        }else if(viewType == ITEM_TYPE.FOOTER.ordinal()){
            return new NormalRecycleViewAdapter.VH(mFooterView) {};
        }else{
            return mAdapter.onCreateViewHolder(parent,viewType);
        }
    }

    @Override
    public void onBindViewHolder(NormalRecycleViewAdapter.VH holder, int position) {
        if(position == 0){
            return;
        }else if(position == mAdapter.getItemCount() + 1){
            return;
        }else{
            mAdapter.onBindViewHolder((NormalRecycleViewAdapter.VH) holder,position - 1);
        }
    }

    @Override
    public int getItemCount() {
        return mAdapter.getItemCount() + 2;
    }


    public void addHeaderView(View view){
        this.mHeaderView = view;
    }

    public void addFooterView(View view){
        this.mFooterView = view;
    }

}
