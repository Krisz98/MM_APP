package com.example.krani.myapplication.Sziltan;

public class KoncentraltEropar extends Hatas {
    private Vektor nyomatekVektor;
    public KoncentraltEropar(Vektor helyVektor, Vektor nyomatekVektor) {
        super(helyVektor);
        this.nyomatekVektor = nyomatekVektor;
    }

    public Vektor getNyomatekVektor() {
        return nyomatekVektor;
    }

    public void setNyomatekVektor(Vektor nyomatekVektor) {
        this.nyomatekVektor = nyomatekVektor;
    }
}
