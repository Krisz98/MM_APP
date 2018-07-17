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
import com.example.krani.myapplication.Sziltan.KoordinataRendszer;
import com.example.krani.myapplication.Sziltan.Vektor;

public class MainActivity extends AppCompatActivity{
    private static final String LOGTAG="MainActivity";
    private GestureDetector mGestureDetector;
    private CircularLayout myLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*mGestureDetector = new GestureDetector(this,new PlusViewGestureDetector());

        myLayout = (CircularLayout) findViewById(R.id.mylayout);
        TriangleVIew tr1 = new TriangleVIew(this);
        TriangleVIew tr2 = new TriangleVIew(this);
        TriangleVIew tr3 = new TriangleVIew(this);
        TriangleVIew tr4 = new TriangleVIew(this);
        TriangleVIew tr5 = new TriangleVIew(this);
        PlusVIew pl1 = new PlusVIew(this);
        tr1.setVectorDrawable((VectorDrawable) getResources().getDrawable(R.drawable.arrow_1));
        tr2.setVectorDrawable((VectorDrawable) getResources().getDrawable(R.drawable.arrow_2));
        tr3.setVectorDrawable((VectorDrawable) getResources().getDrawable(R.drawable.plus));
        myLayout.addView(tr1, new CircularLayout.LayoutParams(400,250));
        myLayout.addView(tr2, new CircularLayout.LayoutParams(400,250));
        myLayout.addView(tr3, new CircularLayout.LayoutParams(400,250));
        myLayout.addView(tr4, new CircularLayout.LayoutParams(400,250));
        myLayout.addView(tr5, new CircularLayout.LayoutParams(400,250));
        myLayout.addView(pl1, new CircularLayout.LayoutParams(100,50));
        //tr5.setVisibility(View.GONE);
        tr1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mGestureDetector.onTouchEvent(event);
                if(v instanceof TriangleVIew) Log.v(LOGTAG,"TRIANGLE");
                if(v instanceof PlusVIew) Log.v(LOGTAG,"PLUS");
                if(v instanceof CircularLayout) Log.v(LOGTAG,"Circular");
                return true;
            }
        });*/
        GraphVIew graphVIew = (GraphVIew) findViewById(R.id.nyiroigenybeveteliabra);
        KoordinataRendszer koordinataRendszer = new KoordinataRendszer();
        koordinataRendszer.addHatas(new Ero(new Vektor(0,0,0),new Vektor(1,3,3)));
        koordinataRendszer.addHatas(new Ero(new Vektor(1,0,0),new Vektor(2, (float) -3.4,2)));
        IgenybevetelSzamito igenybevetelSzamito = new IgenybevetelSzamito(koordinataRendszer);
        igenybevetelSzamito.setMode(IgenybevetelSzamito.NYIROERO);
        graphVIew.setGraphFunctionProvider(igenybevetelSzamito);
        graphVIew.setXmax(10);
        graphVIew.setTagolok_y(5);
        graphVIew.setMode(GraphVIew.HALF);
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
