package com.example.krani.myapplication.Sziltan;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.krani.myapplication.R;

public final class KonstansMegoszlo extends Megoszloero {
    private double intensity;

    public KonstansMegoszlo() {
        super();
        this.endVektor = new Vektor(0,0,0);
        this.intensity=0;
        this.helyVektor = new Vektor(0,0,0);
    }

    public KonstansMegoszlo(Vektor startVektor, Vektor endVektor, double intensity) {
        super(startVektor, endVektor);
        this.intensity = intensity;
    }

    @Override
    public Vektor getErovektor(double x) {
        if(x> this.helyVektor.getX()){
            if(x<=this.endVektor.getX())return new Vektor(0,intensity*(x-this.helyVektor.getX()),0);
            return new Vektor(0,intensity*(this.endVektor.getX()-this.helyVektor.getX()),0);
        }
        return new Vektor(0,0,0);
    }
    @Override
    public Vektor getCenterVektor(double x) {
        if(x >this.getHelyVektor().getX()){
            if(x<=this.endVektor.getX()){return new Vektor((x+this.helyVektor.getX())/2,helyVektor.getY(),helyVektor.getZ());}
            return new Vektor((endVektor.getX()-helyVektor.getX())/2,helyVektor.getY(),helyVektor.getZ());
        }
        return new Vektor(x,0,0);
    }

    @Override
    protected void init() {
        super.init();
        mPainter.setStrokeWidth(4);

    }

    @Override
    public void draw(float lambda, float origoX, float origoY, float visibleDiameterInPixels, float maxSize, Canvas canvas, int strokewidth) {
        if(intensity==0) return;
        mPainter.setColor(context.getResources().getColor(R.color.hatasok));
        if(Math.signum(intensity)>0){
            canvas.drawRect((float)(origoX+helyVektor.getX()/lambda),origoY+6,(float)(origoX+endVektor.getX()/lambda),origoY+maxSize/2,mPainter);
        }
        else{
            canvas.drawRect((float)(origoX+helyVektor.getX()/lambda),origoY-maxSize/2,(float)(origoX+endVektor.getX()/lambda),origoY-6,mPainter);
        }
    }

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }
}
