package com.example.krani.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CircularLayout extends ViewGroup {
    private int plusWidth=0;
    public CircularLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int count = getChildCount();
        int maxWidth =-1;
        int pluswidth=0;
        int childstate =0;
        for(int i =0;i<count;++i){
            final View child = getChildAt(i);
            if(child.getVisibility()!=GONE){
                if(child instanceof PlusVIew){
                    measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,0);
                    pluswidth = child.getMeasuredWidth();
                    plusWidth = pluswidth;
                }
                else{
                    final TriangleVIew triangleVIew = (TriangleVIew) child;
                    triangleVIew.setCount(count-1);
                    child.setLayoutParams(new CircularLayout.LayoutParams(child.getLayoutParams().width, (int) (child.getLayoutParams().width*Math.sin(triangleVIew.getAngle()))));
                    measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,0);
                    if(maxWidth==-1) maxWidth=child.getMeasuredWidth();
                    else maxWidth = Math.max(maxWidth,child.getMeasuredWidth());
                }
                childstate = combineMeasuredStates(childstate,child.getMeasuredState());
            }
        }
        setMeasuredDimension(resolveSizeAndState(maxWidth+pluswidth/2,widthMeasureSpec,childstate),
                resolveSizeAndState(maxWidth+pluswidth/2,widthMeasureSpec,childstate<<MEASURED_HEIGHT_STATE_SHIFT));

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int k =0;

        for(int i=0;i<count;++i){
            final View child = getChildAt(i);
            if (child instanceof PlusVIew){
                child.layout(0,getHeight()-plusWidth,plusWidth,getHeight());
            }
            else if(child instanceof TriangleVIew){
                final TriangleVIew triangleVIew = (TriangleVIew) child;
                triangleVIew.setCount(count-1);
                triangleVIew.layout(plusWidth/2,getHeight()-plusWidth/2-triangleVIew.getMeasuredHeight(),
                        plusWidth/2+triangleVIew.getMeasuredWidth(),getHeight()-plusWidth/2);
                triangleVIew.setmRotation((k*90/(count-1))*(-1));
                k++;
            }
        }
    }
    public static class LayoutParams extends MarginLayoutParams{
        int num=0;
        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(LayoutParams source) {
            super(source);
        }

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }
    }
}
