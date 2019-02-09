package com.example.krani.myapplication.FeladatSzervezes;

import java.util.HashMap;

public class Felelet_valasztas extends Feladat {
    private String valasz_A;
    private String valasz_B;
    private String valasz_C;
    private String valasz_D;
    private String kerdes;
    private HashMap<Character,Boolean> helyesseg;
    private boolean[] jelolesek;
    public Felelet_valasztas() {
        super();
        this.helyesseg = new HashMap<Character, Boolean>();
    }

    public Felelet_valasztas(int szintido, int nehezseg, int type) {
        super(szintido, nehezseg, type);
        this.helyesseg = new HashMap<Character, Boolean>();
    }

    public Felelet_valasztas(int szintido, int type) {
        super(szintido, type);
        this.helyesseg = new HashMap<Character, Boolean>();
    }

    public String getValasz_A() {
        return valasz_A;
    }

    public void setValasz_A(String valasz_A) {
        this.valasz_A = valasz_A;
    }

    public String getValasz_B() {
        return valasz_B;
    }

    public void setValasz_B(String valasz_B) {
        this.valasz_B = valasz_B;
    }

    public String getValasz_C() {
        return valasz_C;
    }

    public void setValasz_C(String valasz_C) {
        this.valasz_C = valasz_C;
    }

    public String getValasz_D() {
        return valasz_D;
    }

    public void setValasz_D(String valasz_D) {
        this.valasz_D = valasz_D;
    }

    public String getKerdes() {
        return kerdes;
    }

    public void setKerdes(String kerdes) {
        this.kerdes = kerdes;
    }

    public HashMap<Character, Boolean> getHelyesseg() {
        return helyesseg;
    }

    public void setHelyesseg(HashMap<Character, Boolean> helyesseg) {
        this.helyesseg = helyesseg;
    }

    public boolean[] getJelolesek() {
        return jelolesek;
    }

    public void setJelolesek(boolean[] jelolesek) {
        this.jelolesek = jelolesek;
    }
}
