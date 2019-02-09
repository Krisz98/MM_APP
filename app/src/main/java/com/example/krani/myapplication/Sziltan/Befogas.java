package com.example.krani.myapplication.Sziltan;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public final class Befogas extends Kenyszer {
    private Paint mPainter;
    private KoncentraltEropar koncentraltEropar;
    public Befogas(Vektor helyVektor) {
        super(helyVektor);
        this.koncentraltEropar = new KoncentraltEropar(helyVektor,new Vektor(0,0,0));
    }

    public Befogas() {
        super();
        this.koncentraltEropar = new KoncentraltEropar(new Vektor(0,0,0),new Vektor(0,0,0));
    }

    @Override
    protected float getAngle() {
        return 0;
    }

    @Override
    protected void setVectorDrawable() {
        mPainter = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    public void draw(float lambda, float origoX, float origoY, float visibleDiameterInPixels, float maxSize, Canvas canvas, int strokewidth) {
        maxSize = maxSize*1.5f;
        int w = (int) (maxSize*drawableratio);
        int h = (int) maxSize;
        int x = (int) (origoX+helyVektor.getX()/lambda);
        int y = (int) (origoY);

        mPainter.setColor(Color.BLUE);
        mPainter.setStrokeWidth(3);
        for(int i=0; i<10;i++){
            canvas.drawLine(x,y-h/2+i*(h/10),x-16*2,y-h/2+16+i*(h/10),mPainter);
        }
        mPainter.setColor(Color.CYAN);
        mPainter.setStrokeWidth(strokewidth*2);
        canvas.drawLine(x,y+h/2,x,y-h/2,mPainter);


    }

    public void setKoncentraltEropar(Vektor nyomatekVektor) {
        this.koncentraltEropar.setNyomatekVektor(nyomatekVektor);
    }

    public KoncentraltEropar getKoncentraltEropar() {
        return koncentraltEropar;
    }

    @Override
    public void setHelyVektor(Vektor helyvektor) {
        super.setHelyVektor(helyvektor);
        this.koncentraltEropar.setHelyVektor(helyvektor);
    }
}
