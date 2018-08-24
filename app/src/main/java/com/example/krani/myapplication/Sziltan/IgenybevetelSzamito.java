package com.example.krani.myapplication.Sziltan;

import android.content.Context;
import android.graphics.Paint;

import com.example.krani.myapplication.GraphFunctionProvider;

import java.util.ArrayList;

public class IgenybevetelSzamito implements GraphFunctionProvider {
    private KoordinataRendszer koordinataRendszer;
    public static final int NORMALERO =1;
    public static final int NYIROERO = 2;
    public static final int HAJLITONYOMATEK =3;
    public static final int CSAVARONYOMATEK =4;
    private int mode;
    public IgenybevetelSzamito(){}
    public IgenybevetelSzamito(KoordinataRendszer koordinataRendszer){
        this.koordinataRendszer = koordinataRendszer;
    }
    @Override
    public double func(double x, Paint painter, Context context) {
        float p=0;
        for(Hatas h: koordinataRendszer.getHatasokLeftToX(x)){
            switch (mode){
                case NORMALERO:
                    if(h instanceof Ero){
                        p+= ((Ero) h).getEroVektor().getX();
                    }
                    break;
                case NYIROERO:
                    if(h instanceof Ero) p+= ((Ero) h).getEroVektor().getY();
                    break;
                case HAJLITONYOMATEK:
                    if(h instanceof KoncentraltEropar) p+= ((KoncentraltEropar) h).getNyomatekVektor().getZ();
                    if (h instanceof Ero) {
                        p+= SziltanSzamitas.vectorCrossProduct(SziltanSzamitas.vectorSubstraction(h.getHelyVektor(),new Vektor(x,0,0)),((Ero) h).getEroVektor()).getZ();
                    }
                    break;
                case CSAVARONYOMATEK:
                    if(h instanceof KoncentraltEropar) p+= ((KoncentraltEropar) h).getNyomatekVektor().getX();
                    if(h instanceof Ero){
                        p+= SziltanSzamitas.vectorCrossProduct(SziltanSzamitas.vectorSubstraction(h.getHelyVektor(),new Vektor(x,0,0)),((Ero) h).getEroVektor()).getX();

                    }
            }
        }
        return p;
    }

    public KoordinataRendszer getKoordinataRendszer() {
        return koordinataRendszer;
    }

    public void setKoordinataRendszer(KoordinataRendszer koordinataRendszer) {
        this.koordinataRendszer = koordinataRendszer;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }
    public String getModeString(){
        switch (mode){
            case NORMALERO: return "N";
            case NYIROERO: return "V";
            case HAJLITONYOMATEK: return "Mh";
            case CSAVARONYOMATEK: return "Mt";
        }
        return "";
    }
}
