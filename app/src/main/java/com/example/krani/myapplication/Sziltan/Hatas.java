package com.example.krani.myapplication.Sziltan;

import com.example.krani.myapplication.Sziltan.Vektor;

public abstract class Hatas {
    private Vektor helyVektor;
    public Hatas(Vektor helyVektor){
        this.helyVektor = helyVektor;
    }
    public Vektor getHelyVektor() {
        return helyVektor;
    }

    public void setHelyVektor(Vektor helyVektor) {
        this.helyVektor = helyVektor;
    }
}
