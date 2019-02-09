package com.example.krani.myapplication.FeladatSzervezes;

import com.example.krani.myapplication.Sziltan.Hatas;

import java.util.ArrayList;

public class Grafikonvalasztas extends Feladat {

    private ArrayList<ArrayList<Hatas>> valaszok;
    private int[] modeok;
    private int helyes;
    private int valasz;

    public Grafikonvalasztas() {
        super();
    }

    public Grafikonvalasztas(int szintido, int nehezseg, int type) {
        super(szintido, nehezseg, type);
    }

    public Grafikonvalasztas(int szintido, int type) {
        super(szintido, type);
    }

    public ArrayList<ArrayList<Hatas>> getValaszok() {
        return valaszok;
    }

    public void setValaszok(ArrayList<ArrayList<Hatas>> valaszok) {
        this.valaszok = valaszok;
    }


    public int getHelyes() {
        return helyes;
    }

    public void setHelyes(int helyes) {
        this.helyes = helyes;
    }

    public int[] getModeok() {
        return modeok;
    }

    public void setModeok(int[] modeok) {
        this.modeok = modeok;
    }

    public int getValasz() {
        return valasz;
    }

    public void setValasz(int valasz) {
        this.valasz = valasz;
    }
}
