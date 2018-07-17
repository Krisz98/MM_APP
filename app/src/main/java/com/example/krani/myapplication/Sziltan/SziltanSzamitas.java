package com.example.krani.myapplication.Sziltan;

public class SziltanSzamitas {
    public static float vectorDotProduct(Vektor a, Vektor b){
        return a.getX()*b.getX()+a.getY()*b.getY()+a.getZ()*b.getZ();
    }
    public static Vektor vectorCrossProduct(Vektor a, Vektor b){
        Vektor p = new Vektor();
        p.setX(a.getY()*b.getZ()-a.getZ()*b.getY());
        p.setY(a.getZ()*b.getX()-a.getX()*b.getZ());
        p.setZ(a.getX()*b.getY()-a.getY()*b.getX());
        return p;
    }
    public static Vektor vectorSubstraction(Vektor a, Vektor b){
        return new Vektor(a.getX()-b.getX(),a.getY()-b.getY(),a.getZ()-b.getZ());
    }
}
