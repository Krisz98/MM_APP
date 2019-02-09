package com.example.krani.myapplication.Sziltan.szerkezet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.VectorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.krani.myapplication.R;
import com.example.krani.myapplication.Sziltan.Ero;
import com.example.krani.myapplication.Sziltan.KoncentraltEropar;
import com.example.krani.myapplication.Sziltan.KoordinataRendszer;
import com.example.krani.myapplication.Sziltan.Vektor;

import static android.graphics.Paint.Style.STROKE;

public class RudView extends View {
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private Rud rud;
    private Paint paint;
    private  int tagoloY;
    private float strokeWidth;
    public RudView(Context context) {
        super(context);
        init(context);
    }

    public RudView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RudView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public RudView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
    private void init(Context context){
        rud = new Rud(context);
        rud.setLength(4);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        startX = getPaddingLeft();
        endX = MeasureSpec.getSize(widthMeasureSpec)-getPaddingRight();
        startY = getPaddingTop();
        endY = MeasureSpec.getSize(heightMeasureSpec)-getPaddingBottom();
        int wx = (int) ((endX-startX)*0.8);
        int wy = (int) ((endY-startY)*0.4);
        int px = (endX-startX-wx)/2;
        int py =(endY-startY-wy)/2;
        rud.getPaint().setStrokeWidth((float) (wx*0.011));
        rud.setBounds(new RectF(startX,startY+py,endX,endY-py));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rud.setCanvas(canvas);
        drawTagolok(canvas);
        rud.draw();

        //canvas.drawRect(startX,startY,endX,endY,paint);
        //canvas.drawRect(0,0,getWidth(),getHeight(),paint);
    }
    private void drawTagolok(Canvas canvas){
        strokeWidth = getWidth()*((float)1/100);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(getResources().getColor(R.color.axisY));
        //canvas.drawLine(startX,startY,startX,endY,paint);
        paint.setColor(getResources().getColor(R.color.tagoloY));
        paint.setStrokeWidth(2);
        float dx = (endX-startX)/tagoloY;
        for(int i=0;i<=tagoloY;++i){
            canvas.drawLine(startX+dx*i,startY,startX+dx*i,(endY+startY)/2,paint);
        }
    }

    public void setRud(Rud rud) {
        this.rud = rud;
    }

    public Rud getRud() {
        return rud;
    }

    public int getStartX() {
        return startX;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public int getStartY() {
        return startY;
    }

    public int getTagoloY() {
        return tagoloY;
    }

    public void setTagoloY(int tagoloY) {
        this.tagoloY = tagoloY;
    }
}
