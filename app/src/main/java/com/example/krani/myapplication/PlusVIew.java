package com.example.krani.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.VectorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class PlusVIew extends View {
    private static final String LOGTAG="PlusView";
    private Paint mPainter;
    private VectorDrawable mVectPlus;
    private boolean mExpanded =false;
    public PlusVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPainter = new Paint(Paint.ANTI_ALIAS_FLAG);
        mVectPlus = (VectorDrawable) getResources().getDrawable(R.drawable.plus);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wh = getSuggestedMinimumWidth() + getPaddingLeft() + getPaddingRight();
        int hh = getSuggestedMinimumHeight() + getPaddingBottom() + getPaddingTop();
        int w = resolveSizeAndState(wh, widthMeasureSpec, 1);
        int h = resolveSizeAndState(hh, heightMeasureSpec, 1);
        setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!mExpanded){

            mVectPlus.setBounds(0,0,getWidth(),getHeight());
            mVectPlus.draw(canvas);
        }
    }
}
