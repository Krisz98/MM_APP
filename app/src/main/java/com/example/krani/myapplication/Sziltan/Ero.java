package com.example.krani.myapplication.Sziltan;

public class Ero extends Hatas {
    private Vektor eroVektor;
    public Ero(Vektor helyVektor, Vektor eroVektor) {
        super(helyVektor);
        this.eroVektor = eroVektor;
    }

    public void setEroVektor(Vektor eroVektor) {
        this.eroVektor = eroVektor;
    }

    public Vektor getEroVektor() {
        return eroVektor;
    }
}

