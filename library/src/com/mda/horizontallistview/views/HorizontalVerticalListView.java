package com.mda.horizontallistview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.mda.horizontallistview.adapters.AbstractGroupAdapter;

/**
 * Created by Dmitriy Manzhosov on 7/6/13.
 */
public class HorizontalVerticalListView extends ListView {
    public HorizontalVerticalListView(Context context) {
        super(context);
    }

    public HorizontalVerticalListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAdapter(AbstractGroupAdapter adapter) {
        super.setAdapter(adapter);
    }

    @Override
    public void setAdapter(final ListAdapter adapter) {
        throw new UnsupportedOperationException("setAdapter(ListAdapter) is not supported in HorizontalVerticalListView");
    }
}
