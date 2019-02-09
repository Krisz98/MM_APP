package com.example.krani.myapplication.Sziltan;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Megoszloero extends Ero {
    protected Vektor endVektor;
    protected Paint mPainter;
    public Megoszloero(){
            init();
    }
    public Megoszloero(Vektor startVektor, Vektor endVektor){
        super(startVektor);
        this.endVektor = endVektor;
        init();
    }
    public Vektor getErovektor(double x){ // az x értéknek megfelelő erővektort adja vissza
        return null;
    }
    public Vektor getCenterVektor(double x){ // a tömegközéppont helyét adja vissza helyvektorral
        return null;
    }
    @Override
    public void draw(float lambda, float origoX, float origoY, float visibleDiameterInPixels, float maxSize, Canvas canvas, int strokewidth) {

    }

    public Vektor getEndVektor() {
        return endVektor;
    }

    public void setEndVektor(Vektor endVektor) {
        this.endVektor = endVektor;
    }

    @Override
    protected void setVectorDrawable() {

    }
    protected void init(){
        mPainter = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected float getAngle() {
        return 0;
    }
}
