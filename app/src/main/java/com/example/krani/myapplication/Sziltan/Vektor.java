package com.example.krani.myapplication.Sziltan;

public class Vektor {
    private float x;
    private float y;
    private float z;
    public Vektor(){

    }
    public Vektor(float x, float y, float z){
       this.x = x;
       this.y = y;
       this.z = z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
    public void setXYZ(float x, float y, float z){
        this.z = z;
        this.y = y;
        this.x = x;
    }
}
