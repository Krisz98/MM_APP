package com.example.krani.myapplication.Sziltan;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.VectorDrawable;
import android.util.Log;

import com.example.krani.myapplication.R;

public  class Ero extends Hatas {
    private Vektor eroVektor;
    private Paint textPaint;
    public Ero() {
        super();
        this.setEroVektor(new Vektor(0,0,0));
        this.textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.parseColor("#D50000"));
        textPaint.setTextAlign(Paint.Align.RIGHT);
    }

    public Ero(Vektor helyVektor, Vektor eroVektor) {
        super(helyVektor);
        this.eroVektor = eroVektor;
        this.textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.parseColor("#D50000"));
        textPaint.setTextAlign(Paint.Align.RIGHT);
    }

    protected Ero(Vektor helyVektor) {
        super(helyVektor);
    }

    public void setEroVektor(Vektor eroVektor) {
        this.eroVektor = eroVektor;
    }

    public Vektor getEroVektor() {
        return eroVektor;
    }


    @Override
    protected float getAngle() {
        if(eroVektor.getY()==0) return 0;
        return (float) ((float) Math.atan(eroVektor.getY()/eroVektor.getX())*180/Math.PI);
    }

    @Override
    protected void setVectorDrawable() {
        vectorDrawable = (VectorDrawable) context.getResources().getDrawable(R.drawable.arrow_1);
        drawableratio = 45.0/155.0;
    }

    @Override
    public void draw(float lambda, float origoX, float origoY, float visibleDiameterInPixels, float maxSize, Canvas canvas, int strokewidth) {
        if(eroVektor.isnull()) return;
        int w = (int) (maxSize*drawableratio);
        int h = (int) maxSize;
        int x = (int) (origoX+helyVektor.getX()/lambda);
        int y = (int) (origoY);
        float forgatas;
        textPaint.setTextSize(h/3);
        if(eroVektor.getY()>0){
            vectorDrawable.setBounds(x-w/2,  (y+strokewidth/2-h),x+w/2,y+strokewidth/2);
            forgatas = -Math.signum(getAngle())*(90+Math.abs(getAngle()));
            canvas.rotate(forgatas,x,y+strokewidth/2);
            vectorDrawable.draw(canvas);
            if(eroVektor.getX()!=0){
                canvas.rotate(forgatasFelirat(),x,y+strokewidth/2);
                canvas.drawText(Integer.toString((int)eroVektor.getLength()),x-Math.signum((float)eroVektor.getX())*h/2f,y-strokewidth,textPaint);
                canvas.rotate(-forgatasFelirat(),x,y+strokewidth/2);
            }else{
                textPaint.setTextAlign(Paint.Align.LEFT);
                canvas.rotate(180,x,y+strokewidth/2);
                canvas.drawText(Integer.toString((int)eroVektor.getLength()),x+strokewidth,y-h/2f,textPaint);
                canvas.rotate(-180,x,y+strokewidth/2);
            }

            canvas.rotate(-forgatas,x,y+strokewidth/2);
        }
        else{
            vectorDrawable.setBounds(x-w/2,  (y-h-strokewidth/2),x+w/2,y-strokewidth/2);
            forgatas = elojel()*(90-Math.abs(getAngle()));
            canvas.rotate( forgatas,x,y-strokewidth/2);
            vectorDrawable.draw(canvas);
            if(eroVektor.getX()!=0){
                canvas.rotate(forgatasFelirat(),x,y+strokewidth/2);
                canvas.drawText(Integer.toString((int)eroVektor.getLength()),x-Math.signum((float)eroVektor.getX())*h/2f,y-strokewidth,textPaint);
                canvas.rotate(-forgatasFelirat(),x,y+strokewidth/2);
            }else{
                textPaint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(Integer.toString((int)eroVektor.getLength()),x-strokewidth,y+h/2f,textPaint);
            }
            canvas.rotate(-forgatas,x,y-strokewidth/2);
        }
        //Log.v("Ero","angle:"+getAngle());


    }
    private float elojel(){
        if(eroVektor.getY()==0) return (float) -Math.signum(eroVektor.getX());
        return Math.signum(getAngle());
    }
    private float forgatasFelirat(){
        return Math.signum((float)eroVektor.getX())*90;
    }
}

