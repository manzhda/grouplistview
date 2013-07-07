package com.mda.horizontallistview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import com.mda.horizontallistview.R;
import com.mda.horizontallistview.views.HorizontalListView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public abstract class AbstractGroupAdapter<T, C,
        I extends GroupViewHolder,
        S extends ViewHolder> extends BaseAdapter {

    private final Context mContext;
    private final LayoutInflater mInflater;
    List<T> mTList;
    TreeMap<T, ArrayList<C>> mData;

	public AbstractGroupAdapter(Context context, TreeMap<T, ArrayList<C>> data){
		mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mData = data;

        mTList = new ArrayList<T>();
        for (T key : mData.keySet()) {
            mTList.add(key);
        }
	}

	@Override
	public int getCount() {
		return mData.size()-1;
	}

    @Override
    public T getItem(int position) {
       return mTList.get(position);
    }

    public C getSubItem(int itemPosition, int subItemPosition){
        T key = mTList.get(itemPosition);
        ArrayList<C> cs = mData.get(key);
        C subItem = cs.get(subItemPosition);
        if(subItem != null){
            return subItem;
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        I itemHolder;

        if (convertView == null) {
            itemHolder = getItemViewHolder();
            itemHolder.mSubgroupAdapter = new SubgroupAdapter<C, S>(mContext, position, mData.get(getItem(position))){

                @Override
                public S getSubItemViewHolder() {
                    return AbstractGroupAdapter.this.getSubgroupViewHolder();
                }

                @Override
                public View getSubItemView(int subItemPosition, LayoutInflater inflater, ViewGroup parent, S subItemHolder) {
                    return AbstractGroupAdapter.this.getSubgroupView(getItemPosition(), subItemPosition, inflater, parent, subItemHolder);
                }

                @Override
                public void updateSubItemView(int subItemPosition, S subItemHolder) {
                    AbstractGroupAdapter.this.updateSubgroupView(getItemPosition(), subItemPosition, subItemHolder);
                }
            };

            convertView = mInflater.inflate(R.layout.list_horizontal_view, parent,false);

            FrameLayout header = (FrameLayout) convertView.findViewById(R.id.list_horizontal_view_header);
            View itemView = getGroupView(position, mInflater, parent, itemHolder);
            header.addView(itemView);

            itemHolder.mHorizontalListView = (HorizontalListView) convertView.findViewById(R.id.list_horizontal_view_sub_item_list_view);
            itemHolder.mHorizontalListView.setAdapter(itemHolder.mSubgroupAdapter);

            convertView.setTag(itemHolder);
        } else {
            itemHolder = (I) convertView.getTag();
        }

        itemHolder.mSubgroupAdapter.setItemPosition(position);
        itemHolder.mSubgroupAdapter.setData(mData.get(getItem(position)));
        updateGroupView(position, itemHolder);

        return convertView;
    }

    public void setData(TreeMap<T, ArrayList<C>> data){
        if(data == null){
            data = new TreeMap<T, ArrayList<C>>();
        }
        mData = data;
        notifyDataSetChanged();
    }

    public abstract I getItemViewHolder();
    public abstract View getGroupView(int position, LayoutInflater inflater, ViewGroup parent, I itemHolder);
    public abstract void updateGroupView(int position, I itemHolder);

    public abstract S getSubgroupViewHolder();
    public abstract View getSubgroupView(int itemPosition, int subItemPosition, LayoutInflater inflater, ViewGroup parent, S subItemHolder);
    public abstract void updateSubgroupView(int itemPosition, int subItemPosition, S subItemHolder);
}