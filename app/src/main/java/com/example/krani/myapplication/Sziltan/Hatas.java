package com.example.krani.myapplication.Sziltan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.drawable.VectorDrawable;

public abstract class Hatas {
    protected Context context;
    protected VectorDrawable vectorDrawable;
    protected double drawableratio; // width/height
    protected Path path;
    protected Vektor helyVektor;
    public Hatas(){
        this.helyVektor=(new Vektor(0,0,0));
    }
    protected Hatas(Vektor helyVektor){
        this.helyVektor = helyVektor;
    }
    public Vektor getHelyVektor() {
        return helyVektor;
    }

    public void setHelyVektor(Vektor helyVektor) {
        this.helyVektor = helyVektor;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }
    protected abstract float getAngle();

    public void setContext(Context context) {
        this.context = context;
        setVectorDrawable();
    }
    protected abstract void setVectorDrawable();
    public abstract void draw(float lambda, float origoX, float origoY, float visibleDiameterInPixels, float maxSize, Canvas canvas, int strokewidth);
}
