package com.bob.android.myhttprequestexample.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.text.AttributedCharacterIterator;

/**
 * @package com.bob.android.myhttprequestexample.view
 * @fileName EmptyRecyclerView
 * @Author Bob on 2019/2/25 11:01.
 * @Describe TODO
 */
public class EmptyRecyclerView extends RecyclerView {
    private View mEmptyView;

    private AdapterDataObserver mObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            Adapter adapter = getAdapter();
            if(adapter.getItemCount() == 0){
                mEmptyView.setVisibility(VISIBLE);
                EmptyRecyclerView.this.setVisibility(GONE);
            }else{
                mEmptyView.setVisibility(GONE);
                EmptyRecyclerView.this.setVisibility(VISIBLE);
            }
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {onChanged();}
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {onChanged();}
        public void onItemRangeRemoved(int positionStart, int itemCount) {onChanged();}
        public void onItemRangeInserted(int positionStart, int itemCount) {onChanged();}
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {onChanged();}
    };


    public EmptyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
    }

    public void setEmptyView(View view){
        this.mEmptyView = view;
        ((ViewGroup)this.getRootView()).addView(mEmptyView);
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        super.setAdapter(adapter);
        adapter.registerAdapterDataObserver(mObserver);
        mObserver.onChanged();
    }
}
