package com.example.krani.myapplication.Sziltan;

import java.util.ArrayList;

public class KoordinataRendszer {
    private ArrayList<Hatas> hatasok;
    public  KoordinataRendszer(){
        hatasok = new ArrayList<Hatas>();
    }
    public void addHatas(Hatas hatas){
        hatasok.add(hatas);
    }
    public ArrayList<Hatas> getHatasokLeftToX(float x){
        ArrayList<Hatas> h = new ArrayList<Hatas>();
        for(Hatas hatas: hatasok){
            if(hatas.getHelyVektor().getX()<x) h.add(hatas);
        }
        return h;
    }
    public ArrayList<Hatas> getHatasokRightToX(float x){
        ArrayList<Hatas> h = new ArrayList<Hatas>();
        for(Hatas hatas: hatasok){
            if(hatas.getHelyVektor().getX()<x) h.add(hatas);
        }
        return h;
    }
}
