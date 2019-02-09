package com.example.krani.myapplication.FeladatSzervezes;

import android.util.Log;
import android.util.Xml;

import com.example.krani.myapplication.FeladatProperties;
import com.example.krani.myapplication.Sziltan.Befogas;
import com.example.krani.myapplication.Sziltan.Csuklostamasz;
import com.example.krani.myapplication.Sziltan.Ero;
import com.example.krani.myapplication.Sziltan.GorgosTamasz;
import com.example.krani.myapplication.Sziltan.Hatas;
import com.example.krani.myapplication.Sziltan.IgenybevetelSzamito;
import com.example.krani.myapplication.Sziltan.Kenyszer;
import com.example.krani.myapplication.Sziltan.KoncentraltEropar;
import com.example.krani.myapplication.Sziltan.KonstansMegoszlo;
import com.example.krani.myapplication.Sziltan.KoordinataRendszer;
import com.example.krani.myapplication.Sziltan.Megoszloero;
import com.example.krani.myapplication.Sziltan.SziltanSzamitas;
import com.example.krani.myapplication.Sziltan.Vektor;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class XMLolvaso {
    private final String LOGTAG="XMLolvaso";
    public Feladat parse(InputStream in, int type, int nehezseg, int id) throws XmlPullParserException, IOException{
        Feladat f = null;
        try{
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(in,null);
            f = reedFeed(parser,type, nehezseg, id);
            Log.v(LOGTAG,"FINISHED");
        }catch (Exception ex){
            ex.printStackTrace();
            Log.v(LOGTAG,"ERROR");
            Log.v(LOGTAG,ex.getMessage());
        }
        finally {
            in.close();
        }
        return f;
    }
    private Feladat reedFeed(XmlPullParser parser, int type, int nehezseg, int id)throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        Feladat task = null;
        int eventType;
        String name;
        Log.v(LOGTAG,Integer.toString(type));
        switch(type){
            case FeladatProperties.GRAFIKONVALASZTO:
                eventType = parser.getEventType();
                int feladat = 0;
                int hatas = 0;
                Grafikonvalasztas grafikonvalasztas = new Grafikonvalasztas(10,type,nehezseg);
                ArrayList<ArrayList<Hatas>> valaszok = new ArrayList<ArrayList<Hatas>>();
                valaszok.add(new ArrayList<Hatas>());
                valaszok.add(new ArrayList<Hatas>());
                valaszok.add(new ArrayList<Hatas>());
                valaszok.add(new ArrayList<Hatas>());
                int[] modeok = new int[4];
                while(eventType!= XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            name = parser.getName();
                            if(name.equals("T1")){
                                if(parser.getAttributeValue(null,"helyes").equals("true")) grafikonvalasztas.setHelyes(feladat);
                                modeok[feladat++] = IgenybevetelSzamito.HAJLITONYOMATEK;
                                hatas = 0;
                            }else if(name.equals("T2")){
                                if(parser.getAttributeValue(null,"helyes").equals("true")) grafikonvalasztas.setHelyes(feladat);
                                modeok[feladat++] = IgenybevetelSzamito.NORMALERO;
                                hatas = 0;
                            }else if(name.equals("T3")){
                                if(parser.getAttributeValue(null,"helyes").equals("true")) grafikonvalasztas.setHelyes(feladat);
                                modeok[feladat++] = IgenybevetelSzamito.NYIROERO;
                                hatas = 0;
                            }else if(name.equals("F")){
                                hatas++;
                                valaszok.get(feladat-1).add(new Ero());
                            }else if(name.equals("T")){
                                hatas++;
                                valaszok.get(feladat-1).add(new KoncentraltEropar());
                            }else if(name.equals("D")){
                                hatas++;
                                valaszok.get(feladat-1).add(new KonstansMegoszlo());
                            }else if(name.equals("B")){
                                hatas++;
                                valaszok.get(feladat-1).add(new Befogas());
                            }else if(name.equals("P")){
                                hatas++;
                                valaszok.get(feladat-1).add(new Csuklostamasz());
                            }else if(name.equals("G")){
                                hatas++;
                                valaszok.get(feladat-1).add(new GorgosTamasz());
                            }else if(name.equals("x")){
                                String s = parser.nextText();
                                double x=0;
                                if(s!=null){
                                    x = Double.parseDouble(s);
                                }
                                valaszok.get(feladat-1).get(hatas-1).setHelyVektor(new Vektor(x,0,0));
                            }else if(name.equals("Fx")){
                                String s = parser.nextText();
                                double x=0;
                                if(s!=null) {
                                    x = Double.parseDouble(s);
                                }
                                Hatas h =valaszok.get(feladat-1).get(hatas-1);
                                if(h instanceof Ero){
                                    ((Ero)(valaszok.get(feladat-1).get(hatas-1))).getEroVektor().setX(x);
                                }else if(h instanceof Kenyszer){
                                    ((Kenyszer)h).getEro().getEroVektor().setX(x);
                                }

                            }else if(name.equals("Fy")){
                                String s = parser.nextText();
                                double y=0;
                                if(s!=null) {
                                    y = Double.parseDouble(s);
                                }
                                Hatas h =valaszok.get(feladat-1).get(hatas-1);
                                if(h instanceof Ero){
                                    ((Ero)(valaszok.get(feladat-1).get(hatas-1))).getEroVektor().setY(y);
                                }else if(h instanceof Kenyszer){
                                    ((Kenyszer)h).getEro().getEroVektor().setY(y);
                                }
                            }else if(name.equals("Tz")){
                                String s = parser.nextText();
                                double z=0;
                                if(s!=null){
                                    z = Double.parseDouble(s);
                                }
                                Hatas h =valaszok.get(feladat-1).get(hatas-1);
                                if(h instanceof KoncentraltEropar){
                                    ((KoncentraltEropar)valaszok.get(feladat-1).get(hatas-1)).getNyomatekVektor().setZ(z);
                                }else if(h instanceof Befogas){
                                    ((Befogas)h).getKoncentraltEropar().getNyomatekVektor().setZ(z);
                                }

                            }else if(name.equals("xv")){
                                String s = parser.nextText();
                                double x=0;
                                if(s!=null){
                                    x = Double.parseDouble(s);
                                }
                                ((KonstansMegoszlo)valaszok.get(feladat-1).get(hatas-1)).getEndVektor().setX(x);
                            }
                            else if(name.equals("Dy")){
                                String s = parser.nextText();
                                double y=0;
                                if(s!=null){
                                    y = Double.parseDouble(s);
                                }
                                ((KonstansMegoszlo)valaszok.get(feladat-1).get(hatas-1)).setIntensity(y);
                            }


                    }
                    eventType = parser.next();
                }
                grafikonvalasztas.setValaszok(valaszok);
                grafikonvalasztas.setModeok(modeok);
                task = grafikonvalasztas;
                break;
            case FeladatProperties.FELELETVALASZTO:
                eventType = parser.getEventType();
                Felelet_valasztas felelet_valasztas = null;
                while(eventType!= XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            name = parser.getName();
                            if(name.equals("T4")){
                                felelet_valasztas = new Felelet_valasztas(10,type,nehezseg);
                            }else if(name.equals("K")){
                                felelet_valasztas.setKerdes(parser.nextText());
                            }else if(name.equals("V1")){
                                String s = parser.getAttributeValue(null,"helyes");
                                if(s!=null){
                                    if(s.equals("false")) felelet_valasztas.getHelyesseg().put('A',false);
                                    else if(s.equals("true")) felelet_valasztas.getHelyesseg().put('A',true);
                                }
                                felelet_valasztas.setValasz_A(parser.nextText());
                            }else if(name.equals("V2")){
                                String s = parser.getAttributeValue(null,"helyes");
                                if(s!=null){
                                    if(s.equals("false")) felelet_valasztas.getHelyesseg().put('B',false);
                                    else if(s.equals("true")) felelet_valasztas.getHelyesseg().put('B',true);
                                }
                                felelet_valasztas.setValasz_B(parser.nextText());
                            }else if(name.equals("V3")){
                                String s = parser.getAttributeValue(null,"helyes");
                                if(s!=null){
                                    if(s.equals("false")) felelet_valasztas.getHelyesseg().put('C',false);
                                    else if(s.equals("true")) felelet_valasztas.getHelyesseg().put('C',true);
                                }
                                felelet_valasztas.setValasz_C(parser.nextText());
                            }else if(name.equals("V4")){
                                String s = parser.getAttributeValue(null,"helyes");
                                if(s!=null){
                                    if(s.equals("false")) felelet_valasztas.getHelyesseg().put('D',false);
                                    else if(s.equals("true")) felelet_valasztas.getHelyesseg().put('D',true);
                                }
                                felelet_valasztas.setValasz_D(parser.nextText());
                            }
                    }
                    eventType = parser.next();
                }
                task = felelet_valasztas;
                break;
            case FeladatProperties.IGAZ_HAMIS:
                eventType = parser.getEventType();
                Igaz_Hamis igaz_hamis = new Igaz_Hamis();
                while(eventType!= XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            name = parser.getName();
                            if(name.equals("V1")){
                                String s = parser.getAttributeValue(null,"helyes");
                                if(s!=null){
                                    if(s.equals("true")) igaz_hamis.setIgaz(true);
                                    else if(s.equals("false")) igaz_hamis.setIgaz(false);
                                }
                                igaz_hamis.setAllitas(parser.nextText());
                            }
                    }
                    eventType = parser.next();
                }
                task = igaz_hamis;
                break;

        }
        task.setNehezseg(nehezseg);
        task.set_Type(type);
        task.setId(id);
        //Log.v(LOGTAG,Integer.toString(task.get_Type()));
       // Log.v(LOGTAG,task.getClass().getName());
        return task;
    }
}
