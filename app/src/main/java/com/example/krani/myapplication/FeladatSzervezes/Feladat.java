package com.example.krani.myapplication.FeladatSzervezes;

import com.example.krani.myapplication.FeladatProperties;

public abstract class Feladat {


    private int szintido;
    private int nehezseg;
    private int mType;
    private int id;
    private long time;
    public static long NO_TIME = -101;
    public Feladat(){
        this.szintido=10;
        this.nehezseg=1;
        this.mType= FeladatProperties.IGAZ_HAMIS;
        time =NO_TIME;
    }
    public Feladat(int szintido, int nehezseg, int type){
        this.szintido=szintido;
        this.nehezseg=nehezseg;
        this.mType = type;
        time =NO_TIME;
    }
    public Feladat(int szintido, int type){
        this.szintido=szintido;
        this.nehezseg=-1;
        this.mType = type;
        time =NO_TIME;
    }
    public Feladat(int szintido, int nehezseg, int type, int id){
        this.szintido=szintido;
        this.nehezseg=nehezseg;
        this.mType = type;
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public static class FeladatDraft{
        private int mszintido;
        private int mnehezseg;
        private int mTipus;
        private int mid;
        public FeladatDraft(){
            this.mszintido=10;
            this.mnehezseg=1;
            this.mTipus=FeladatProperties.FELELETVALASZTO;
            this.mid=1;
        }
        public FeladatDraft(int szintido, int nehezseg){
            this.mnehezseg = mnehezseg;
            this.mszintido = szintido;
            this.mid = 1;
        }
        public FeladatDraft(int szintido, int nehezseg, int tipus){
            this.mszintido = szintido;
            this.mnehezseg = nehezseg;
            this.mTipus = tipus;
            this.mid = 1;
        }
        public FeladatDraft(int szintido, int nehezseg, int tipus, int id){
            this.mszintido = szintido;
            this.mnehezseg = nehezseg;
            this.mTipus = tipus;
            this.mid = id;
        }
        public int getMszintido() {
            return mszintido;
        }

        public void setMszintido(int mszintido) {
            this.mszintido = mszintido;
        }

        public int getMnehezseg() {
            return mnehezseg;
        }

        public void setMnehezseg(int mnehezseg) {
            this.mnehezseg = mnehezseg;
        }

        public int getmTipus() {
            return mTipus;
        }

        public void setmTipus(int mTipus) {
            this.mTipus = mTipus;
        }

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }
    }



    public int getSzintido() {
        return szintido;
    }
    public void setSzintido(int szintido) {
        this.szintido = szintido;
    }

    public int getNehezseg() {
        return nehezseg;
    }

    public void setNehezseg(int nehezseg) {
        this.nehezseg = nehezseg;
    }

    public int get_Type() {
        return mType;
    }

    public void set_Type(int type) {
        this.mType = type;
    }
}
