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

import com.example.krani.myapplication.Sziltan.IgenybevetelSzamito;

/**
 * Created by krani on 2018. 07. 05..
 */

public class GraphVIew extends View {
    public static boolean FULL = true;
    public static boolean HALF = false;
    private Paint mPainter;
    private static final String LOGTAG;
    private Context mContext;
    private GraphFunctionProvider graphFunctionProvider;
    private int tagolok_y;//egy x oldalon levő tagolók száma (y-nal ||-os)
    private int tagolok_x;//egy y oldalon levő tagoloók száma (x-szel ||-os)
    private float terj; // ha nem fixálok: -1; különben lefixálhatom hogy mekkora legyen az y tengely max beosztása
    private double s;// hány beosztás van az x tengelyen(1 oldalon)
    private double z; // hány beosztás van az y tengelyen(1 oldalon) ezt nem piszkálni
    private boolean full; //true: összes siknegyed; false: 1. és 4. siknegyedek
    private Paint textPaint;
    private float axisStrokeWidth;
    private String label;
    static {
        LOGTAG = "GraphView";
    }

    public GraphVIew(Context context) {
        super(context);
        init(context);
    }

    public GraphVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public GraphVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public GraphVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    private void init(Context context, AttributeSet attrs) {
        label = "";
        mPainter = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new Paint();
        textPaint.setTextSize(12*getResources().getDisplayMetrics().density);
        textPaint.setColor(getResources().getColor(R.color.axisY));
        textPaint.setTextAlign(Paint.Align.RIGHT);
        textPaint.setStrokeWidth(5);
        mContext=context;
        tagolok_y =4; //egy x oldalon levő tagolók száma (y-nal ||-os)
        tagolok_x=6;//egy y oldalon levő tagoloók száma (x-szel ||-os)
        full = false;
        terj = -1;
        z =3.5f;
        s = 3;
        this.graphFunctionProvider = new GraphFunctionProvider() {
            @Override
            public double func(double x, Paint painter, Context context) {
                return  Math.sin(x);
            }
        };
    }
    private void init(Context context){
        label = "";
        mPainter = new Paint(Paint.ANTI_ALIAS_FLAG);
        mContext=context;
        textPaint = new Paint();
        textPaint.setTextSize(12*getResources().getDisplayMetrics().density);
        textPaint.setColor(getResources().getColor(R.color.axisY));
        textPaint.setTextAlign(Paint.Align.RIGHT);
        tagolok_y =4;//egy x oldalon levő tagolók száma (y-nal ||-os)
        tagolok_x=6;//egy y oldalon levő tagoloók száma (x-szel ||-os)
        terj = -1;
        full = false;
        z =3.5f;
        s = 3;
        this.graphFunctionProvider = new GraphFunctionProvider() {
            @Override
            public double func(double x, Paint painter, Context context) {
                return Math.sin(x);
            }
        };
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
        axisStrokeWidth = getWidth()*((float)1/100);
        double y=0;
        double x=0;
        float origoY = getPaddingTop()+(getHeight()-getPaddingTop()-getPaddingBottom())/2;
        float startX = getPaddingLeft();
        float startY = getPaddingTop();
        float endY=getHeight()-getPaddingBottom();
        float endX =getWidth()-getPaddingRight();
        float origoX = getPaddingLeft()+(getWidth()-getPaddingLeft()-getPaddingRight())/2;
           //hány beosztás van az y tengelyen
        float i; // hányadik pixel az origóhoz képest, lehet negativ is
        double px=-1;
        double py=-1;
        float w = getWidth();
        float ww = endX-startX;
        canvas.drawText(((IgenybevetelSzamito)graphFunctionProvider).getModeString(),startX-10,startY+getResources().getDisplayMetrics().density*16,textPaint);

        float k =0; //x koordináta a viewban, ahol járunk
        double a;
        double max=0; //a maxiumhely kereséséhez
        //terj = z;
        //tagolok_y = (int)s;
        if(full){
            origoY = getPaddingTop()+(getHeight()-getPaddingTop()-getPaddingBottom())/2;
            k= startX;
            origoX = getPaddingLeft()+(getWidth()-getPaddingLeft()-getPaddingRight())/2;

            s*=2;
            a = s/(endX-startX);
            max=0;
            mPainter.setColor(getResources().getColor(R.color.tagoloY));
            mPainter.setStrokeWidth(2);
            do{
                i=k-origoX;
                x = i*a;
                y=graphFunctionProvider.func(x,mPainter,mContext);
                if(Math.abs(y)>max) max = Math.abs(y);
                ++k;
            }while(x<=s/2&&terj==-1);
            k=startX;
            tagolok_y*=2;
            float tagolox = (endX-startX)/tagolok_y;

            for(int p=0;p<=tagolok_y;++p){
                if(p==tagolok_y/2) ++p;

                canvas.drawLine(startX+p*tagolox,startY,startX+p*tagolox,endY,mPainter);
            }
            mPainter.setColor(getResources().getColor(R.color.axisY));
            mPainter.setStrokeWidth(axisStrokeWidth);
            canvas.drawLine(startX,origoY,endX,origoY,mPainter);
        }
        else{
            origoY = getPaddingTop()+(getHeight()-getPaddingTop()-getPaddingBottom())/2;
            origoX = startX;
            k= origoX;
            a = s/(endX-startX);
            mPainter.setColor(getResources().getColor(R.color.tagoloY));
            mPainter.setStrokeWidth(2);
            max=0;
            do{
                i=k-origoX;
                x = i*a;
                y=graphFunctionProvider.func(x,mPainter,mContext);
                if(Math.abs(y)>max) max = Math.abs(y);
                ++k;
            }while(x<=s&&terj==-1);
            k=origoX;
            float tagolox = (endX-startX)/tagolok_y;

            for(int p=1;p<=tagolok_y;++p){

                canvas.drawLine(startX+p*tagolox,startY,startX+p*tagolox,endY,mPainter);
            }
            mPainter.setColor(getResources().getColor(R.color.axisY));
            mPainter.setStrokeWidth(axisStrokeWidth);
            canvas.drawLine(origoX,origoY,endX,origoY,mPainter);
        }
        if(terj!=-1) max = terj;
        z= 2*Math.abs(max);
        tagolok_x = (int) z/2;
        float tagoloy = (endY-startY)/tagolok_x/2;
        mPainter.setColor(getResources().getColor(R.color.tagoloY));
        mPainter.setStrokeWidth(2);
        for(int q=0;q<=(tagolok_x*2);q++){
            if(q==tagolok_x) q++;
            canvas.drawLine(startX,endY-q*tagoloy,endX,endY-q*tagoloy,mPainter);
        }
        double b = z/(endY-startY);   //egy pixel hány beosztásnak felel meg az y tengelyen
        mPainter.setColor(getResources().getColor(R.color.axisY));
        mPainter.setStrokeWidth(axisStrokeWidth);
        Log.v(LOGTAG,Float.toString(axisStrokeWidth));
        canvas.drawLine(origoX,startY,origoX,endY,mPainter);
        do{
            i=k-origoX;
            x = i*a;
            y=graphFunctionProvider.func(x,mPainter,mContext);
            y = y/b;

            if(origoY-y>=startY-(getPaddingTop()*0.1) && origoY-y<=endY+(getPaddingBottom()*0.1)) {
                if(px==-1 && py==-1){
                    px=k;
                    py=y;
                    mPainter.setStrokeWidth(axisStrokeWidth);
                }

                canvas.drawLine((float) px, (float) (origoY-py),k, (float) (origoY-y),mPainter);
                canvas.drawCircle(k, (float) (origoY-y),axisStrokeWidth/2,mPainter);
                px = k;
                py=y;
                mPainter.setColor(getResources().getColor(R.color.underAxisY));
                mPainter.setAlpha(10);
                canvas.drawLine(k,origoY,k, (float) (origoY-y),mPainter);
                mPainter.setColor(getResources().getColor(R.color.axisY));
                mPainter.setAlpha(255);

            }

            ++k;
        }while(k!=endX);
        textPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(Double.toString(s),endX+3,origoY-3,textPaint);
        canvas.drawText(Double.toString(graphFunctionProvider.func(x,mPainter,mContext)),endX+3, (float) (origoY-py-3),textPaint);
        Log.v(LOGTAG,"s: "+Double.toString(s)+" z: "+Double.toString(z));
        //invalidate();
    }

    public void setGraphFunctionProvider(GraphFunctionProvider graphFunctionProvider) {
        this.graphFunctionProvider = graphFunctionProvider;
    }

    public GraphFunctionProvider getGraphFunctionProvider() {
        return graphFunctionProvider;
    }
    public void setXmax(float x){
        this.s = x;
    }
    public double getXmax(){
        return this.s;
    }
    public void setYmax(float yMax){
        this.terj = yMax;
    }
    public double getYmax(){
        return this.z;
    }
    public void setTagolok_x(int n){
        this.tagolok_x = n;
    }
    public void setTagolok_y(int n){
        this.tagolok_y = n;
    }

    public int getTagolok_x() {
        return tagolok_x;
    }

    public int getTagolok_y() {
        return tagolok_y;
    }
    public void setMode(boolean mode){
        this.full = mode;
    }
    public boolean getMode(){
        return full;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

