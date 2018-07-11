package com.example.krani.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.VectorDrawable;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;

public class RandomView extends View {
    private VectorDrawable mNyil;
    private Paint mPainter;
    private float m=500;
    public RandomView(Context context) {
        super(context);
        mNyil = (VectorDrawable) getResources().getDrawable(R.drawable.arrow_1);


    }

    public RandomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mNyil = (VectorDrawable) getResources().getDrawable(R.drawable.arrow_1);
        mPainter = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wh = getSuggestedMinimumWidth() + getPaddingLeft() + getPaddingRight();
        int hh = getSuggestedMinimumHeight() + getPaddingBottom() + getPaddingTop();
        int w = resolveSizeAndState(wh, widthMeasureSpec, 1);
        int h = resolveSizeAndState(hh, heightMeasureSpec, 1);
        setMeasuredDimension(w, h);
        mNyil.setBounds(300,300,(int)m+300,300+(int)m);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mNyil.draw(canvas);
        canvas.drawCircle(50,50,30,mPainter);
        canvas.drawLine(0,m,getWidth(),m,mPainter);
        canvas.drawLine(m,0,m,getHeight(),mPainter);
        invalidate();


    }
}
