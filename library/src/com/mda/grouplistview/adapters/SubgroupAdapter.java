package com.mda.grouplistview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class SubgroupAdapter<C, S> extends BaseAdapter {
    private final Context mContext;
    private final LayoutInflater mInflater;
    private int mItemPosition;
    List<C> mSubItemData;

    public SubgroupAdapter(Context context, int itemPosition, List<C> subItemData) {
        mSubItemData = subItemData;
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItemPosition = itemPosition;
    }

    public int getItemPosition() {
        return mItemPosition;
    }

    public void setItemPosition(int itemPosition) {
        mItemPosition = itemPosition;
    }

    @Override
    public int getCount() {
        return mSubItemData.size();
    }

    @Override
    public C getItem(int position) {
        return mSubItemData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        S viewHolder;

        if (convertView == null) {
            viewHolder = getSubItemViewHolder();
            convertView = getSubItemView(position, mInflater, parent, viewHolder);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (S) convertView.getTag();
        }

        updateSubItemView(position, viewHolder);

        return convertView;
    }

    public void setData(ArrayList<C> subItemData) {
        if (subItemData == null) {
            subItemData = new ArrayList<C>();
        }
        mSubItemData = subItemData;
        notifyDataSetChanged();
    }

    public abstract S getSubItemViewHolder();
    public abstract View getSubItemView(int position, LayoutInflater inflater, ViewGroup parent, S subItemHolder);
    public abstract void updateSubItemView(int position, S subItemHolder);
}