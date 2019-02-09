package com.example.krani.myapplication.FeladatSzervezes;

import android.content.Context;
import android.util.Log;

import com.example.krani.myapplication.FeladatProperties;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FeladatGenerator {
    private Context mContext;
    public FeladatGenerator(Context context){
        this.mContext = context;
    }
    //Ez a fv. a feladatok sorrendjét állítja fel
    //TODO: megírni a feladatok legenerlásához szükséges kódot, majd ezek alapján a megfelelő xml fileból beimportálni!
    public ArrayList<Feladat> generate_order(int num, int minDifficulty, int maxDifficulty, int type){
        int diff;
        int tipus;
        Random random = new Random();
        ArrayList<Feladat.FeladatDraft> feladatok = new ArrayList<Feladat.FeladatDraft>();
        for(int i=0;i<num;i++){
            diff = random.nextInt(maxDifficulty-minDifficulty+1)+minDifficulty;
            if(type == FeladatProperties.ALL_TYPES){
                tipus = random.nextInt(FeladatProperties.ALL_TYPES-2)+1;
            }
            else{
                tipus = type;
            }

            Feladat.FeladatDraft feladat =new Feladat.FeladatDraft(10,diff,tipus,random.nextInt(3-1)+1);
            Log.v("Generator",Integer.toString(feladat.getMid()));
            feladatok.add(feladat);
        }
        Log.v("Generator",Integer.toString(feladatok.size()));
        return getFeladatokFromDrafts(feladatok);
    }
    public ArrayList<Feladat> getFeladatokFromDrafts(ArrayList<Feladat.FeladatDraft> feladatDrafts){
        XMLolvaso xmLolvaso = new XMLolvaso();
        int id;
        String name;
        ArrayList<Feladat> feladatok = new ArrayList<Feladat>();
        for(Feladat.FeladatDraft feladatDraft : feladatDrafts){
            name = "f"+String.format("%02d",feladatDraft.getmTipus())+
                    String.format("%02d",feladatDraft.getMnehezseg())+
                    "s"+
                    String.format("%04d",feladatDraft.getMid());
            id = mContext.getResources().getIdentifier(name, "raw", mContext.getPackageName());
            //Log.v("FeladatGenerator",name);
            InputStream is = mContext.getResources().openRawResource(id);
            try {
                //Log.v("FeladatGenerator",Integer.toString(feladatDraft.getmTipus()));
                feladatok.add(xmLolvaso.parse(is,feladatDraft.getmTipus(),feladatDraft.getMnehezseg(),feladatDraft.getMid()));
            }catch (Exception ex){
                feladatok.add(new Igaz_Hamis());
            }

        }
        return feladatok;
    }
}
