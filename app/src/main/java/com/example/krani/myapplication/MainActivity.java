package com.example.krani.myapplication;

import android.content.Intent;
import android.graphics.Outline;
import android.graphics.drawable.VectorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.RelativeLayout;

import com.example.krani.myapplication.Sziltan.Ero;
import com.example.krani.myapplication.Sziltan.IgenybevetelSzamito;
import com.example.krani.myapplication.Sziltan.KoncentraltEropar;
import com.example.krani.myapplication.Sziltan.KonstansMegoszlo;
import com.example.krani.myapplication.Sziltan.KoordinataRendszer;
import com.example.krani.myapplication.Sziltan.SziltanSzamitas;
import com.example.krani.myapplication.Sziltan.Vektor;
import com.example.krani.myapplication.Sziltan.szerkezet.RudView;

public class MainActivity extends AppCompatActivity{
    private static final String LOGTAG="MainActivity";
    private GestureDetector mGestureDetector;
    private RudView rudView;
    private GraphVIew graphVIew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GraphVIew b = (GraphVIew) findViewById(R.id.a);
        GraphVIew c = (GraphVIew) findViewById(R.id.c);
        RudView r = (RudView) findViewById(R.id.b);
        KoordinataRendszer koordinataRendszer =new KoordinataRendszer();
        IgenybevetelSzamito igenybevetelSzamito = new IgenybevetelSzamito();
        IgenybevetelSzamito igenybevetelSzamitoC = new IgenybevetelSzamito();
        igenybevetelSzamito.setKoordinataRendszer(koordinataRendszer);
        igenybevetelSzamitoC.setKoordinataRendszer(koordinataRendszer);
        b.setGraphFunctionProvider(igenybevetelSzamito);
        c.setGraphFunctionProvider(igenybevetelSzamitoC);
        c.setTagolok_y(5);
        b.setTagolok_y(5);
        c.setXmax(5);
        r.setTagoloY(5);
        b.setXmax(5);
        igenybevetelSzamito.setMode(IgenybevetelSzamito.NYIROERO);
        igenybevetelSzamitoC.setMode(IgenybevetelSzamito.HAJLITONYOMATEK);
        r.getRud().setKoordinataRendszer(koordinataRendszer);
        r.getRud().setLength(5);
        koordinataRendszer.addHatas(new Ero(new Vektor(0,0,0),new Vektor(0,1,0)));
        koordinataRendszer.addHatas(new Ero(new Vektor(2,0,0),new Vektor(0,1,0)));
        koordinataRendszer.addHatas(new Ero(new Vektor(5,0,0),new Vektor(-1,-1,0)));
        //koordinataRendszer.addHatas(new Ero(new Vektor(2,0,0),new Vektor(-3,0,0)));
        koordinataRendszer.addHatas(new KonstansMegoszlo(new Vektor(0,0,0),new Vektor(2,0,0),0.5));
        koordinataRendszer.addHatas(new KonstansMegoszlo(new Vektor(0,0,0),new Vektor(2.5,0,0),-0.1));
        koordinataRendszer.addHatas(new KoncentraltEropar(new Vektor(0,0,0),new Vektor(0,0,5)));
        koordinataRendszer.addHatas(new KoncentraltEropar(new Vektor(3,0,0),new Vektor(0,0,1)));
        koordinataRendszer.addHatas(new KoncentraltEropar(new Vektor(5,0,0),new Vektor(0,0,5.9)));
        r.invalidate();
        b.invalidate();
        c.invalidate();

    }


}
