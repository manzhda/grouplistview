package com.mda.grouplistview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by mda on 7/7/13.
 */
public class DisallowInterceptHorizontalListView extends HorizontalListView {
    private static final int CORRECTION = 2;

    public DisallowInterceptHorizontalListView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean onFling(final MotionEvent e1, final MotionEvent e2, final float velocityX, final float velocityY) {
        float dX = Math.abs(e2.getX()-e1.getX());
        float dY = Math.abs(e1.getY()-e2.getY());
        if(dX * CORRECTION > dY){
            requestDisallowInterceptTouchEvent(true);
        }

        return super.onFling(e1, e2, velocityX, velocityY);
    }

    @Override
    protected boolean onScroll(final MotionEvent e1, final MotionEvent e2, final float distanceX, final float distanceY) {
        float dX = Math.abs(e2.getX()-e1.getX());
        float dY = Math.abs(e1.getY()-e2.getY());
        if(dX * CORRECTION > dY){
            requestDisallowInterceptTouchEvent(true);
        }

        return super.onScroll(e1, e2, distanceX, distanceY);
    }
}
