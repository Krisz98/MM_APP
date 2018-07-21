package com.example.krani.myapplication;

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
import com.example.krani.myapplication.Sziltan.KoordinataRendszer;
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
        graphVIew = (GraphVIew) findViewById(R.id.graph);
        rudView = (RudView) findViewById(R.id.rudabra);
        IgenybevetelSzamito igenybevetelSzamito = new IgenybevetelSzamito();
        igenybevetelSzamito.setMode(IgenybevetelSzamito.NORMALERO);
        KoordinataRendszer koordinataRendszer = new KoordinataRendszer();
        igenybevetelSzamito.setKoordinataRendszer(koordinataRendszer);
        rudView.getRud().setKoordinataRendszer(koordinataRendszer);
        rudView.getRud().setLength(5);
        rudView.setTagoloY(5);
        graphVIew.setXmax(5);
        graphVIew.setTagolok_y(5);
        graphVIew.setGraphFunctionProvider(igenybevetelSzamito);
        graphVIew.setLabel("V");
        koordinataRendszer.addHatas(new Ero(new Vektor(1,0,0),new Vektor(3,3,0)));
        koordinataRendszer.addHatas(new Ero(new Vektor(1.5,0,0),new Vektor(2,1,0)));
        koordinataRendszer.addHatas(new Ero(new Vektor(5,0,0),new Vektor(-1,-1,0)));
        koordinataRendszer.addHatas(new Ero(new Vektor(5,0,0),new Vektor(0,1,0)));
        koordinataRendszer.addHatas(new KoncentraltEropar(new Vektor(0,0,0),new Vektor(0,2,3)));
        rudView.invalidate();
        graphVIew.invalidate();
    }
    class PlusViewGestureDetector implements GestureDetector.OnGestureListener{

        @Override
        public boolean onDown(MotionEvent e) {
            Log.v(LOGTAG,"Touched");
            return true;
        }


        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }
}
