package com.example.krani.myapplication.Sziltan.szerkezet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.krani.myapplication.R;
import com.example.krani.myapplication.Sziltan.Hatas;
import com.example.krani.myapplication.Sziltan.KoncentraltEropar;
import com.example.krani.myapplication.Sziltan.KoordinataRendszer;

public class Rud {
    private Canvas canvas;
    private Paint paint;
    private RectF rectF;
    private boolean show;
    private int color;
    private int rx;
    private int ry;
    private int length;
    private Context context;
    private KoordinataRendszer koordinataRendszer;
    public Rud(Context context){
        init(context);
    }
    public Rud(RectF bounds, Context context){
        setBounds(bounds);
        init(context);
    }
    public Rud(Canvas canvas, Context context){
        this.canvas= canvas;
        init(context);
    }
    public Rud(Canvas canvas, RectF bounds, Context context){
        this.canvas= canvas;
        setBounds(bounds);
        init(context);

    }
    private void init(Context context){
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(6);
        this.show=true;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(context.getResources().getColor(R.color.axisY));
        this.context = context;
    }

    public void showRud(){
        this.show = true;
    }
    public void hideRud(){
        this.show=false;
    }
    public void setBounds(RectF bounds){
        this.rectF = bounds;
        this.rx = (int) (Math.min(bounds.width(),bounds.height())*0.1);
        this.ry = rx;
    }
    public RectF getBounds(){
        return this.rectF;
    }
    public void draw(){
        paint.setStrokeWidth(rectF.width()/200);
       canvas.drawLine(rectF.left,rectF.centerY(),rectF.right,rectF.centerY(),paint);
       canvas.drawLine(rectF.left,rectF.centerY()-(rectF.centerY()-rectF.top)/3f,rectF.left,rectF.centerY()+(rectF.centerY()-rectF.top)/3f,paint);
       canvas.drawLine(rectF.right,rectF.centerY()-(rectF.centerY()-rectF.top)/3f,rectF.right,rectF.centerY()+(rectF.centerY()-rectF.top)/3f,paint);
       for(Hatas hatas: koordinataRendszer.getHatasok()){
            hatas.setContext(context);
            if(hatas instanceof KoncentraltEropar){
                hatas.draw(length/rectF.width(),rectF.left,rectF.centerY(),rectF.height(),rectF.height()*2.5f,canvas, (int) paint.getStrokeWidth());
            }
            else {
                int max = (int) Math.min(canvas.getHeight()-rectF.height(),canvas.getWidth()-rectF.width());
                hatas.draw(length/rectF.width(),rectF.left,rectF.centerY(),rectF.height(), (float) (max/2),canvas, (int) paint.getStrokeWidth());
            }

       }
    }

    public void setColor(int color) {
        this.color = color;
        this.paint.setColor(color);
    }

    public int getColor() {
        return color;
    }

    public boolean isShow() {
        return show;
    }

    public void setKoordinataRendszer(KoordinataRendszer koordinataRendszer) {
        this.koordinataRendszer = koordinataRendszer;
    }

    public KoordinataRendszer getKoordinataRendszer() {
        return koordinataRendszer;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
