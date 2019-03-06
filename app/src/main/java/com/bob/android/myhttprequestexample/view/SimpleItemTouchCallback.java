package com.bob.android.myhttprequestexample.view;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.bob.android.myhttprequestexample.adapter.NormalRecycleViewAdapter;
import com.bob.android.myhttprequestexample.entity.StudentEntity;

import java.util.Collections;
import java.util.List;

/**
 * @package com.bob.android.myhttprequestexample.view
 * @fileName SimpleItemTouchCallback
 * @Author Bob on 2019/2/25 15:05.
 * @Describe TODO
 */
public class SimpleItemTouchCallback extends ItemTouchHelper.Callback {

    private NormalRecycleViewAdapter mAdapter;
    private List<StudentEntity>  mData;

    public SimpleItemTouchCallback(NormalRecycleViewAdapter adapter,List<StudentEntity> studentEntities){
        mAdapter = adapter;
        mData = studentEntities;
    }
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags,swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromm = viewHolder.getAdapterPosition();
        int to = target.getAdapterPosition();
        Collections.swap(mData,fromm,to);
        mAdapter.notifyItemMoved(fromm,to);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int pos = viewHolder.getAdapterPosition();
        mData.remove(pos);
        mAdapter.notifyItemRemoved(pos);
    }

    //状态改变时回调
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE){
            NormalRecycleViewAdapter.VH holder = (NormalRecycleViewAdapter.VH) viewHolder;
            holder.itemView.setBackgroundColor(0xffbcbcbc);
        }
    }

    //拖拽或者滑动完成之后调用，用来清除一些状态

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        NormalRecycleViewAdapter.VH holder = (NormalRecycleViewAdapter.VH) viewHolder;
        holder.itemView.setBackgroundColor(0xffeeeeee);
    }
}
