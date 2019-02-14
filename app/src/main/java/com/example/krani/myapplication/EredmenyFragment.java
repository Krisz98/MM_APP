package com.example.krani.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.krani.myapplication.FeladatSzervezes.Feladat;
import com.example.krani.myapplication.FeladatSzervezes.Felelet_valasztas;
import com.example.krani.myapplication.FeladatSzervezes.Grafikonvalasztas;
import com.example.krani.myapplication.FeladatSzervezes.Igaz_Hamis;
import com.example.krani.myapplication.adatok.AdatokContract;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EredmenyFragment.OnEredmenyFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EredmenyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EredmenyFragment extends Fragment {

    private OnEredmenyFragmentInteractionListener mListener;
    private ArrayList<Feladat> feladatok;
    public EredmenyFragment() {
        // Required empty public constructor
    }

    public static EredmenyFragment newInstance(ArrayList<Feladat> feladatok) {
        EredmenyFragment fragment = new EredmenyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.feladatok = feladatok;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_eredmeny, container, false);
        int res = calculateResult();
        ((TextView)v.findViewById(R.id.eredmeny)).setText(Integer.toString(res));
        AddDataToDatabase addDataToDatabase = new AddDataToDatabase(getContext(),res,calculateIdo(),"kod");
        addDataToDatabase.execute("2019.02.10");
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEredmenyFragmentInteractionListener) {
            mListener = (OnEredmenyFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnEredmenyFragmentInteractionListener {
        // TODO: Update argument type and name
        void onEredmenyFragmentInteraction(Uri uri);
    }
    private int calculateResult(){
        int points = 0;
        for(Feladat f: feladatok){
            if(f instanceof Igaz_Hamis){
                Igaz_Hamis i = (Igaz_Hamis) f;
                if(i.isIgaz() == ((Igaz_Hamis) f).isValasz()){
                    points += i.getNehezseg()*FeladatProperties.HELYESSEGI_PONTOK_IGAZ_HAMIS/((double)f.getTime()/1000);
                }
            }else if(f instanceof Grafikonvalasztas){
                Grafikonvalasztas g = (Grafikonvalasztas) f;
                if(g.getHelyes() == g.getValasz()){
                    points += g.getNehezseg()*FeladatProperties.HELYESSEGI_PONTOK_GRAFIKON/((double)f.getTime()/1000);
                }
            }else if(f instanceof Felelet_valasztas){
                Felelet_valasztas v = (Felelet_valasztas) f;
                if(v.getHelyesseg().get('A')==v.getJelolesek()[0] && v.getHelyesseg().get('B')==v.getJelolesek()[1] && v.getHelyesseg().get('C')==v.getJelolesek()[2] && v.getHelyesseg().get('D')==v.getJelolesek()[3]){
                    points += v.getNehezseg()*FeladatProperties.HELYESSEGI_PONTOK_VALASZTO/((double)f.getTime()/1000);
                }
            }
        }
        return points;
    }
    private double calculateIdo(){
        double i=0;
        for(Feladat f : feladatok){
            i+=f.getTime();
        }
        return i;
    }
    private class AddDataToDatabase extends AsyncTask<String,Void,Void>{
        private Context mcontext;
        private double pontszam;
        private double ido;
        private String kod;
        public AddDataToDatabase(Context context, double pontszam, double ido, String kod) {
            super();
            mcontext = context;
            this.pontszam = pontszam;
            this.ido = ido;
            this.kod = kod;
        }

        @Override
        protected Void doInBackground(String... dates) {
            AdatokContract.EredmenyekDbHelper eredmenyekDbHelper = new AdatokContract.EredmenyekDbHelper(mcontext);
            SQLiteDatabase db = eredmenyekDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(AdatokContract.Eredmenyek.COLUMN_NAME_PONTSZAM,pontszam);
            values.put(AdatokContract.Eredmenyek.COLUMN_NAME_IDO,ido);
            values.put(AdatokContract.Eredmenyek.COLUMN_NAME_DATUM,dates[0]);
            values.put(AdatokContract.Eredmenyek.COLUMN_NAME_KOD,kod);
            db.insert(AdatokContract.Eredmenyek.TABLE_NAME,null,values);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.v("EredmenyFragment","Beírva Adatbázisba");
        }
    }

}
