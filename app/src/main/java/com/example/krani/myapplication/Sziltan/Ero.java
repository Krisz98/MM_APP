package com.example.krani.myapplication.Sziltan;

import android.graphics.Canvas;
import android.graphics.drawable.VectorDrawable;
import android.util.Log;

import com.example.krani.myapplication.R;

public class Ero extends Hatas {
    private Vektor eroVektor;
    public Ero(Vektor helyVektor, Vektor eroVektor) {
        super(helyVektor);
        this.eroVektor = eroVektor;

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
        if(eroVektor.getX()==0) return 0;
        return (float) ((float) Math.atan(eroVektor.getY()/eroVektor.getX())*180/Math.PI);
    }

    @Override
    protected void setVectorDrawable() {
        vectorDrawable = (VectorDrawable) context.getResources().getDrawable(R.drawable.arrow_1);
        drawableratio = 45.0/155.0;
    }

    @Override
    public void draw(float lambda, float origoX, float origoY, float visibleDiameterInPixels, float maxSize, Canvas canvas, int strokewidth) {
        int w = (int) (maxSize*drawableratio);
        int h = (int) maxSize;
        int x = (int) (origoX+helyVektor.getX()/lambda);
        int y = (int) (origoY);
        if(eroVektor.getY()>0){
            vectorDrawable.setBounds(x-w/2,  (y+strokewidth/2-h),x+w/2,y+strokewidth/2);
            canvas.rotate(180+getAngle(),x,y+strokewidth/2);
            vectorDrawable.draw(canvas);
            canvas.rotate(-(180+getAngle()),x,y+strokewidth/2);
        }
        else{
            vectorDrawable.setBounds(x-w/2,  (y-h-strokewidth/2),x+w/2,y-strokewidth/2);
            canvas.rotate(getAngle(),x,y-strokewidth/2);
            vectorDrawable.draw(canvas);
            canvas.rotate(-getAngle(),x,y-strokewidth/2);
        }
        float angle= getAngle();
        Log.v("Ero","x: "+x+" y: "+y);


    }
}

