package com.example.krani.myapplication.Sziltan;

import android.graphics.Canvas;
import android.graphics.drawable.VectorDrawable;
import android.util.Log;

import com.example.krani.myapplication.R;

public class KoncentraltEropar extends Hatas {
    private Vektor nyomatekVektor;
    public KoncentraltEropar(Vektor helyVektor, Vektor nyomatekVektor) {
        super(helyVektor);
        this.nyomatekVektor = nyomatekVektor;
    }

    public Vektor getNyomatekVektor() {
        return nyomatekVektor;
    }

    public void setNyomatekVektor(Vektor nyomatekVektor) {
        this.nyomatekVektor = nyomatekVektor;
    }


    @Override
    protected float getAngle() {
        return 0;
    }

    @Override
    protected void setVectorDrawable() {
        vectorDrawable = (VectorDrawable) context.getResources().getDrawable(R.drawable.arrow_2);
        drawableratio = (float) (371.45/361.23);
    }

    @Override
    public void draw(float lambda, float origoX, float origoY, float visibleDiameterInPixels, float maxSize, Canvas canvas, int strokewidth) {
        int w = (int) (maxSize*drawableratio);
        int h = (int) maxSize;
        int x = (int) (origoX+helyVektor.getX()/lambda);
        int y = (int) origoY;
        vectorDrawable.setBounds(x-w/2,y-h/2,x+w/2,y+h/2);
        if(nyomatekVektor.getZ()<0){
            canvas.scale(1,-1,x,y);
            vectorDrawable.draw(canvas);
            canvas.scale(1,-1,x,y);
        }
        else vectorDrawable.draw(canvas);
        Log.v("Nyomatek","x: "+x+" y: "+y);

    }
}
