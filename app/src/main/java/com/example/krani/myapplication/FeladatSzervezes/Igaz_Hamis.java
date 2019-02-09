package com.example.krani.myapplication.FeladatSzervezes;

public class Igaz_Hamis extends Feladat {
    private String allitas;
    private boolean igaz;
    private boolean valasz;
    public Igaz_Hamis() {
        super();
        allitas = "";
        igaz = true;
    }

    public Igaz_Hamis(int szintido, int nehezseg, int type) {
        super(szintido, nehezseg, type);
        allitas = "";
        igaz = true;
    }

    public Igaz_Hamis(int szintido, int type) {
        super(szintido, type);
        allitas = "";
        igaz = true;
    }

    public String getAllitas() {
        return allitas;
    }

    public void setAllitas(String allitas) {
        this.allitas = allitas;
    }

    public boolean isIgaz() {
        return igaz;
    }

    public void setIgaz(boolean igaz) {
        this.igaz = igaz;
    }

    public boolean isValasz() {
        return valasz;
    }

    public void setValasz(boolean valasz) {
        this.valasz = valasz;
    }
}
