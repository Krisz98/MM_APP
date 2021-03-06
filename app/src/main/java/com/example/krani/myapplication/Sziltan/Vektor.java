package com.example.krani.myapplication.Sziltan;

public class Vektor {
    private double x;
    private double y;
    private double z;
    public Vektor(){

    }
    public Vektor(double x, double y, double z){
       this.x = x;
       this.y = y;
       this.z = z;
    }
    public double getLength(){
        return Math.sqrt(x*x+y*y+z*z);
    }
    public boolean isnull(){
        return getLength()==0;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
    public void setXYZ(double x, double y, double z){
        this.z = z;
        this.y = y;
        this.x = x;
    }
}
