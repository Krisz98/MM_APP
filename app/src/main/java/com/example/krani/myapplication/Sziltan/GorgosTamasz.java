package com.example.krani.myapplication.Sziltan;

import android.graphics.Canvas;
import android.graphics.drawable.VectorDrawable;

import com.example.krani.myapplication.R;

public final class GorgosTamasz extends Kenyszer {
    protected GorgosTamasz(Vektor helyvektor) {
        super(helyvektor);
    }

    public GorgosTamasz() {
        super();
    }

    @Override
    protected float getAngle() {
        return 0;
    }

    @Override
    protected void setVectorDrawable() {
        vectorDrawable = (VectorDrawable) context.getResources().getDrawable(R.drawable.gorgostamasz);
        drawableratio = 98.2554/116.918;
    }

    @Override
    public void draw(float lambda, float origoX, float origoY, float visibleDiameterInPixels, float maxSize, Canvas canvas, int strokewidth) {
        int w = (int) (maxSize*drawableratio);
        int h = (int) maxSize;
        int x = (int) (origoX+helyVektor.getX()/lambda);
        int y = (int) (origoY);
        vectorDrawable.setBounds(x-w/2,y+strokewidth,x+w/2,y+strokewidth+h);
        vectorDrawable.draw(canvas);
    }
}
