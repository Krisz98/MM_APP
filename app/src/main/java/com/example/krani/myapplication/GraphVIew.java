package com.example.krani.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

/**
 * Created by krani on 2018. 07. 05..
 */

public class GraphVIew extends View {
    private Paint mPainter;
    private VectorDrawable mArrow;
    private static final String LOGTAG;
    private Context mContext;

    static {
        LOGTAG = "GraphView";
    }

    public GraphVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    private void init(Context context, AttributeSet attrs) {
        mPainter = new Paint(Paint.ANTI_ALIAS_FLAG);
        mContext=context;
    }
    @Override
    protected void onMeasure ( int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wh = getSuggestedMinimumWidth() + getPaddingLeft() + getPaddingRight();
        int hh = getSuggestedMinimumHeight() + getPaddingBottom() + getPaddingTop();
        int w = resolveSizeAndState(wh, widthMeasureSpec, 1);
        int h = resolveSizeAndState(hh, heightMeasureSpec, 1);
        setMeasuredDimension(w, h);
        Log.v(LOGTAG, "Pixel density: " + getResources().getDisplayMetrics().densityDpi + "PaddingLeft: " + Float.toString(getX()));
        Log.v(LOGTAG, "DensityDefault: " + DisplayMetrics.DENSITY_DEFAULT);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int tagolok_y =4; //egy x oldalon levő tagolók száma (y-nal ||-os)
        int tagolok_x = 4; //egy y oldalon levő tagoloók száma (x-szel ||-os)
        boolean full = true; //true: összes siknegyed; false: 1. és 4. siknegyedek
        float y=0;
        float x=0;
        float origoY = getPaddingTop()+getY()+(getHeight()-getPaddingTop()-getPaddingBottom())/2;
        float startX = getX()+getPaddingLeft();
        float startY = getY()+getPaddingTop();
        float endY=getY()+getHeight()-getPaddingTop();
        float endX =getX()+getWidth()-getPaddingLeft();
        float origoX = getPaddingLeft()+getX()+(getWidth()-getPaddingLeft()-getPaddingRight())/2;
        float s = 2; // hány beosztás van az x tengelyen(1 oldalon)
        float z =50;   //hány beosztás van az y tengelyen
        float i; // hányadik pixel az origóhoz képest, lehet negativ is
        float px=-1;
        float py=-1;
        float w = getWidth();
        float ww = endX-startX;
        mPainter.setColor(getResources().getColor(R.color.axisY));
        mPainter.setStrokeWidth(10);
        float k =0; //x koordináta a viewban, ahol járunk
        float a;
        float max=0; //a maxiumhely kereséséhez
        float terj=-1; // ha nem fixálok: -1; különben lefixálhatom hogy mekkora legyen az y tengely
        tagolok_y = (int)s;
        if(full){
            origoY = getPaddingTop()+getY()+(getHeight()-getPaddingTop()-getPaddingBottom())/2;
            k= startX;
            origoX = getPaddingLeft()+getX()+(getWidth()-getPaddingLeft()-getPaddingRight())/2;
            canvas.drawLine(startX,origoY,endX,origoY,mPainter);
            s*=2;
            a = s/(endX-startX);
            max=0;
            do{
                i=k-origoX;
                x = i*a;
                y=Func.GETY(x,mPainter,mContext);
                if(Math.abs(y)>max) max = Math.abs(y);
                ++k;
            }while(x<=s/2&&terj==-1);
            k=startX;
            tagolok_y*=2;
            float tagolox = (endX-startX)/tagolok_y;
            for(int p=0;p<=tagolok_y;++p){
                if(p==tagolok_y/2) ++p;
                mPainter.setColor(getResources().getColor(R.color.tagoloY));
                mPainter.setStrokeWidth(3);
                canvas.drawLine(startX+p*tagolox,startY,startX+p*tagolox,endY,mPainter);
            }
        }
        else{
            origoY = getPaddingTop()+getY()+(getHeight()-getPaddingTop()-getPaddingBottom())/2;
            origoX = startX;
            k= origoX;
            a = s/(endX-startX);
            canvas.drawLine(origoX,origoY,endX,origoY,mPainter);
            max=0;
            do{
                i=k-origoX;
                x = i*a;
                y=Func.GETY(x,mPainter,mContext);
                if(Math.abs(y)>max) max = Math.abs(y);
                ++k;
            }while(x<=s&&terj==-1);
            k=origoX;
            float tagolox = (endX-startX)/tagolok_y;
            for(int p=1;p<=tagolok_y;++p){
                mPainter.setColor(getResources().getColor(R.color.tagoloY));
                mPainter.setStrokeWidth(3);
                canvas.drawLine(startX+p*tagolox,startY,startX+p*tagolox,endY,mPainter);
            }
        }
        if(terj!=-1) max = terj;
        z= 2*Math.abs(max);
        tagolok_x = (int) z/2;
        float tagoloy = (endY-startY)/tagolok_x/2;
        for(int q=0;q<=(tagolok_x*2);q++){
            if(q==tagolok_x) q++;
            canvas.drawLine(startX,endY-q*tagoloy,endX,endY-q*tagoloy,mPainter);
        }
        float b = z/(endY-startY);   //egy pixel hány beosztásnak felel meg az y tengelyen
        mPainter.setColor(getResources().getColor(R.color.axisY));
        mPainter.setStrokeWidth(10);
        canvas.drawLine(origoX,startY,origoX,endY,mPainter);
        do{
            i=k-origoX;
            x = i*a;
            y=Func.GETY(x,mPainter,mContext);
            y = y/b;
            if(origoY-y>=startY && origoY-y<=endY) {
                if(px==-1 && py==-1){
                    px=k;
                    py=y;
                }

                canvas.drawLine(px,origoY-py,k,origoY-y,mPainter);
                canvas.drawCircle(k,origoY-y,5,mPainter);
                px = k;
                py=y;
                mPainter.setColor(getResources().getColor(R.color.underAxisY));
                mPainter.setAlpha(10);
                canvas.drawLine(k,origoY,k,origoY-y,mPainter);
                mPainter.setColor(getResources().getColor(R.color.axisY));
                mPainter.setAlpha(255);

            }

            ++k;
        }while(k!=endX);
        Log.v(LOGTAG,"s: "+Float.toString(s)+" z: "+Float.toString(z));
        invalidate();
    }



    static class Func{
        static float GETY(float x, Paint painter,Context context){
            if(x<=1)return -x;
            return (float) Math.pow(x-1,2)*10-1;
        }
    }
}

