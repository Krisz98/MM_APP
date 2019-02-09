package com.example.krani.myapplication.Sziltan;

public abstract class Kenyszer extends Hatas {
    protected Ero eroVektor;
    protected Kenyszer(Vektor helyvektor) {
        super(helyvektor);
        this.eroVektor = new Ero(helyvektor,new Vektor(0,0,0));
    }

    public Kenyszer() {
        super();
        this.eroVektor = new Ero(helyVektor,new Vektor(0,0,0));
    }


    public Ero getEro() {
        return eroVektor;
    }

    public void setEro(Vektor eroVektor) {
        this.eroVektor.setEroVektor(eroVektor);
    }

    @Override
    public void setHelyVektor(Vektor helyvektor) {
        super.setHelyVektor(helyvektor);
        eroVektor.setHelyVektor(helyvektor);
    }
}
