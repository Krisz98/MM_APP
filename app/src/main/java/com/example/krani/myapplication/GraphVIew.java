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
        textPaint.setTextSize(10*getResources().getDisplayMetrics().density);
        textPaint.setColor(getResources().getColor(R.color.axisY));
        textPaint.setTextAlign(Paint.Align.LEFT);
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
        textPaint.setTextSize(10*getResources().getDisplayMetrics().density);
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
        //Log.v(LOGTAG, "Pixel density: " + getResources().getDisplayMetrics().densityDpi + "PaddingLeft: " + Float.toString(getX()));
        //Log.v(LOGTAG, "DensityDefault: " + DisplayMetrics.DENSITY_DEFAULT);
        //textPaint.setTextSize(Math.min(getPaddingLeft(),getPaddingRight())*0.3f*getResources().getDisplayMetrics().density);
        textPaint.setTextSize(hh/tagolok_x/3*getResources().getDisplayMetrics().density);
    }
    @Override
    protected void onDraw(Canvas canvas) {
       // Log.v(LOGTAG,"DRAWING");
        super.onDraw(canvas);
        axisStrokeWidth = getWidth()*((float)1/200);

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
        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(((IgenybevetelSzamito)graphFunctionProvider).getModeString(),startX,startY-axisStrokeWidth,textPaint);

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
            while(x<=s/2&&terj==-1){
                i=k-origoX;
                x = i*a;
                if(x>s/2)break;
                y=graphFunctionProvider.func(x,mPainter,mContext);
                if(Math.abs(y)>max) max = Math.abs(y);
                ++k;
            }
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
            while(x<=s&&terj==-1){
                i=k-origoX;
                x = i*a;
                if(x>s)break;
                y=graphFunctionProvider.func(x,mPainter,mContext);
                if(Math.abs(y)>max) max = Math.abs(y);
                ++k;
                //if(k%5==0) Log.v(LOGTAG,"X: "+Double.toString(x)+"; y: "+Double.toString(y)+"; MAX: "+Double.toString(max));
            }
            //Log.v(LOGTAG,"MAX: "+Double.toString(max));
            k=origoX;
            float tagolox = (endX-startX)/tagolok_y;
           // Log.v(LOGTAG,"MAX: "+Double.toString(max));
            for(int p=1;p<=tagolok_y;++p){

                canvas.drawLine(startX+p*tagolox,startY,startX+p*tagolox,endY,mPainter);
            }
            //Log.v(LOGTAG,"MAX: "+Double.toString(max));
            mPainter.setColor(getResources().getColor(R.color.axisY));
            mPainter.setStrokeWidth(axisStrokeWidth);
            canvas.drawLine(origoX,origoY,endX,origoY,mPainter);
        }
        //Log.v(LOGTAG,"MAX: "+Double.toString(max));
        if(terj!=-1) max = terj;
        //Log.v(LOGTAG,"MAX: "+Double.toString(max));
        z= 2*Math.abs(max);
        tagolok_x = (int) ((z/2)*0.9999) +1;
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
        //Log.v(LOGTAG,"AxisStrokeWidth: "+Float.toString(axisStrokeWidth));
        canvas.drawLine(origoX,startY,origoX,endY,mPainter);
        //Log.v(LOGTAG,"z= "+Double.toString(z));
        do{
            i=k-origoX;
            x = i*a;
            y=graphFunctionProvider.func(x,mPainter,mContext);
            //Log.v(LOGTAG,"x= "+Double.toString(x)+"   y= "+Double.toString(y));
            y = y/b;

            if(origoY-y>=startY-(getPaddingTop()*0.1) && origoY-y<=endY+(getPaddingBottom()*0.1)) {
                if(px==-1 && py==-1){
                    px=k;
                    py=y;
                    mPainter.setStrokeWidth(axisStrokeWidth);
                }

               // canvas.drawLine((float) px, (float) (origoY-py),k, (float) (origoY-y),mPainter);
                //canvas.drawCircle(k, (float) (origoY-y),axisStrokeWidth/2,mPainter);
                px = k;
                py=y;
                mPainter.setColor(getResources().getColor(R.color.underAxisY));
                mPainter.setAlpha(30);
                canvas.drawLine(k,origoY,k, (float) (origoY-y),mPainter);
               // mPainter.setColor(getResources().getColor(R.color.axisY));
               // mPainter.setAlpha(255);

            }

            ++k;
        }while(k!=endX);
        mPainter.setColor(getResources().getColor(R.color.axisY));
        canvas.drawLine(startX-getPaddingLeft()/2,startY,startX+getPaddingLeft()/2,startY,mPainter);
        canvas.drawLine(startX-getPaddingLeft()/2,endY,startX+getPaddingLeft()/2,endY,mPainter);
        textPaint.setTextAlign(Paint.Align.LEFT);
        if(max!=0) canvas.drawText(Double.toString(Math.round(max/tagolok_x)),origoX+2,origoY-tagoloy-2,textPaint);
        canvas.drawText("0",origoX+2,origoY-2,textPaint);
        //Log.v(LOGTAG,"Drawing finished");
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

