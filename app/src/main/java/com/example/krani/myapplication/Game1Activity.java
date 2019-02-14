package com.example.krani.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krani.myapplication.FeladatSzervezes.Feladat;
import com.example.krani.myapplication.FeladatSzervezes.FeladatGenerator;
import com.example.krani.myapplication.FeladatSzervezes.Felelet_valasztas;
import com.example.krani.myapplication.FeladatSzervezes.Grafikonvalasztas;
import com.example.krani.myapplication.FeladatSzervezes.Igaz_Hamis;
import com.example.krani.myapplication.FeladatSzervezes.XMLolvaso;
import com.example.krani.myapplication.adatok.AdatokContract;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Game1Activity extends AppCompatActivity implements Grafikonvalaszto.OnGrafikonValasztoFragmentInteractionListener,
        IgazHamis.OnIgazHamisFragmentInteractionListener, Valaszto.OnValasztoFragmentInteractionListener,EredmenyFragment.OnEredmenyFragmentInteractionListener{
    private static final String LOGTAG="Game1Activity";
    private android.support.v4.app.FragmentManager fragmentManager;
    private android.support.v4.app.FragmentTransaction fragmentTransaction;
    private ArrayList<Feladat> feladatok;
    private Iterator<Feladat> feladatIterator;
    private Feladat currFeladat;
    private Szamlalo sz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        fragmentManager = getSupportFragmentManager();
        FeladatGenerator generator = new FeladatGenerator(this);
        feladatok = generator.generate_order(10,1,1,FeladatProperties.ALL_TYPES);
        feladatIterator = feladatok.iterator();
        sz = null;
        //az 1. feladat megjelenítése:
        nextFeladat();
    }

    private void nextFeladat(){
        if(feladatIterator.hasNext()){
            fragmentTransaction = fragmentManager.beginTransaction();
            currFeladat = feladatIterator.next();
            Log.v(LOGTAG,Integer.toString(currFeladat.get_Type()));
            Log.v(LOGTAG,"tipus: "+Integer.toString(currFeladat.get_Type())+
                    "  nehezseg:"+Integer.toString(currFeladat.getNehezseg())+
                    " szintido:"+Integer.toString(currFeladat.getSzintido())+
                    " id:"+Integer.toString(currFeladat.getId()));
            switch (currFeladat.get_Type()){
                case FeladatProperties.GRAFIKONVALASZTO:
                    Grafikonvalaszto grafikonvalaszto = Grafikonvalaszto.newInstance(currFeladat.getNehezseg(),1,(Grafikonvalasztas)currFeladat);
                    fragmentTransaction.replace(R.id.game1_fragment_container,grafikonvalaszto);
                    break;
                case FeladatProperties.IGAZ_HAMIS:
                    IgazHamis igazHamis = IgazHamis.newInstance((Igaz_Hamis) currFeladat);
                    fragmentTransaction.replace(R.id.game1_fragment_container,igazHamis);
                    break;
                case FeladatProperties.FELELETVALASZTO:
                    Valaszto valaszto = Valaszto.newInstance((Felelet_valasztas)currFeladat);
                    fragmentTransaction.replace(R.id.game1_fragment_container,valaszto);
                    break;
                case FeladatProperties.IGENYBEVETELVALASZTO:
                    //TODO: megírni a fragmentet hozzá és kicserélni rá!!!!
                    Valaszto val = Valaszto.newInstance((Felelet_valasztas)currFeladat);
                    fragmentTransaction.replace(R.id.game1_fragment_container,val);
                    break;
            }
            fragmentTransaction.commit();
            final TextView stopper_integral = (TextView) findViewById(R.id.stopper_integral);
            final TextView stopper_fractional = (TextView) findViewById(R.id.stopper_fractional);
            sz = new Szamlalo(100){
                @Override
                public void onTick(long time) {
                    double t = (double)time/1000;
                    stopper_integral.setText(Integer.toString((int)t));
                    stopper_fractional.setText("."+String.format("%03d",(int) ((t-(int)t)*1000))+" sec");
                }
            };
            sz.start();
        }
        else {
            Log.v(LOGTAG,"Nincs több feladat");
            for(Feladat t : feladatok){
                Log.v(LOGTAG,Long.toString(t.getTime()));
            }
            final TextView stopper_integral = (TextView) findViewById(R.id.stopper_integral);
            final TextView stopper_fractional = (TextView) findViewById(R.id.stopper_fractional);
            stopper_integral.setVisibility(View.GONE);
            stopper_fractional.setVisibility(View.GONE);
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.game1_fragment_container,EredmenyFragment.newInstance(feladatok));
            fragmentTransaction.commit();
        }


    }


    @Override
    public void OnGrafikonValasztoFragmentInteraction(int query, int valasz) {
        if(query==Grafikonvalaszto.OnGrafikonValasztoFragmentInteractionListener.NEXT){
            Log.v(LOGTAG,"Grafikonvalaszto_finished "+ valasz);
            ((Grafikonvalasztas)currFeladat).setValasz(valasz);
            long time=Feladat.NO_TIME;
            if(sz!=null){
                time = sz.cancel();
                Log.v(LOGTAG, Long.toString(time)+" ms");
            }
            currFeladat.setTime(time);
            nextFeladat();
        }
    }

    @Override
    public void OnIgazHamisFragmentInteraction(int query, boolean valasz) {
        if(query==IgazHamis.OnIgazHamisFragmentInteractionListener.NEXT){
            Log.v(LOGTAG,"IgazHamis finished");
            ((Igaz_Hamis)currFeladat).setValasz(valasz);
            long time=Feladat.NO_TIME;
            if(sz!=null){
                time = sz.cancel();
                Log.v(LOGTAG, Long.toString(time)+" ms");
            }
            currFeladat.setTime(time);
            nextFeladat();
        }
    }

    @Override
    public void OnValasztoFragmentInteraction(int query, boolean[] valasz) {
        if(query==Valaszto.OnValasztoFragmentInteractionListener.NEXT){
            Log.v(LOGTAG,"FeleletValaszto finished "+valasz);
            ((Felelet_valasztas)currFeladat).setJelolesek(valasz);
            long time=Feladat.NO_TIME;
            if(sz!=null){
                time = sz.cancel();
                Log.v(LOGTAG, Long.toString(time)+" ms");
            }
            currFeladat.setTime(time);
            nextFeladat();
        }
    }

    @Override
    public void onEredmenyFragmentInteraction(Uri uri) {

    }

    public abstract class Szamlalo{
        private long startTime;
        private long mCountdownInterval;
        private boolean mCancelled;
        public Szamlalo(long interval) {
            startTime = SystemClock.elapsedRealtime();
            mCountdownInterval = interval;
            mCancelled = false;
        }
        public synchronized final long cancel() {
            mCancelled = true;
            mHandler.removeMessages(MSG);
            return SystemClock.elapsedRealtime()-startTime;
        }
        /**
         * Start the countdown.
         */
        public synchronized final void start() {
            mCancelled = false;
            mHandler.sendMessage(mHandler.obtainMessage(MSG));
        }
        public abstract void onTick(long time);
        private static final int MSG = 1;
        @SuppressLint("HandlerLeak")
        private Handler mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                synchronized (Szamlalo.this){
                    if (mCancelled) return;
                    long lastTickStart = SystemClock.elapsedRealtime();
                    onTick(lastTickStart-startTime);
                    // take into account user's onTick taking time to execute
                    long lastTickDuration = SystemClock.elapsedRealtime() - lastTickStart;
                    long delay;
                    delay = mCountdownInterval - lastTickDuration;
                    // special case: user's onTick took more than interval to
                    // complete, skip to next interval
                    while (delay < 0) delay += mCountdownInterval;
                    sendMessageDelayed(obtainMessage(MSG), delay);
                }
            }
        };

    }
}
