package com.example.krani.myapplication;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.krani.myapplication.FeladatSzervezes.Grafikonvalasztas;
import com.example.krani.myapplication.Sziltan.Ero;
import com.example.krani.myapplication.Sziltan.Hatas;
import com.example.krani.myapplication.Sziltan.IgenybevetelSzamito;
import com.example.krani.myapplication.Sziltan.KoordinataRendszer;
import com.example.krani.myapplication.Sziltan.Vektor;
import com.example.krani.myapplication.Sziltan.szerkezet.RudView;

import java.net.URI;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Grafikonvalaszto.OnGrafikonValasztoFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Grafikonvalaszto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Grafikonvalaszto extends Fragment implements FeladatProperties, View.OnClickListener {

    private static final String LOG_TAG ="Grafikonvalaszto";
    private int mNehezseg;
    private int mSzintido;
    private Context mContext;
    private Grafikonvalasztas grafikonvalasztas;

    private OnGrafikonValasztoFragmentInteractionListener mListener;

    public Grafikonvalaszto() {
        // Required empty public constructor
    }

    public static Grafikonvalaszto newInstance(int nehezseg, int szintido, Grafikonvalasztas grafikonvalasztas) {
        Grafikonvalaszto fragment = new Grafikonvalaszto();
        Bundle args = new Bundle();
        args.putInt(NEHEZSEG_KEY, nehezseg);
        args.putInt(SZINTIDO_KEY, szintido);
        fragment.setArguments(args);
        fragment.setGrafikonvalasztas(grafikonvalasztas);
        return fragment;
    }
    public static Grafikonvalaszto newInstance(int nehezseg, int szintido) {
        Grafikonvalaszto fragment = new Grafikonvalaszto();
        Bundle args = new Bundle();
        args.putInt(NEHEZSEG_KEY, nehezseg);
        args.putInt(SZINTIDO_KEY, szintido);
        fragment.setArguments(args);
        fragment.setGrafikonvalasztas(null);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNehezseg = getArguments().getInt(NEHEZSEG_KEY);
            mSzintido = getArguments().getInt(SZINTIDO_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_grafikonvalaszto, container, false);
        v.findViewById(R.id.graph1).setOnClickListener(this);
        v.findViewById(R.id.graph2).setOnClickListener(this);
        v.findViewById(R.id.graph3).setOnClickListener(this);
        v.findViewById(R.id.graph4).setOnClickListener(this);

        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fillContent();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
        if (context instanceof OnGrafikonValasztoFragmentInteractionListener) {
            mListener = (OnGrafikonValasztoFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mContext=getActivity();
    }

    @Override
    public void onClick(View v) {
        if(v instanceof GraphVIew){
            RippleDrawable background = (RippleDrawable) v.getBackground();
           // background.setColor(ColorStateList.valueOf(getResources().getColor(R.color.graphClicked)));
            int id = v.getId();
            int valasz = -1;
            if(id == R.id.graph1) valasz = 0;
            else if(id == R.id.graph2) valasz = 1;
            else if(id == R.id.graph3) valasz = 2;
            else if(id == R.id.graph4) valasz = 3;
            Log.v(LOG_TAG,getResources().getResourceName(id) + "Clicked");
            mListener.OnGrafikonValasztoFragmentInteraction(OnGrafikonValasztoFragmentInteractionListener.NEXT,valasz);
        }
    }

    public Grafikonvalasztas getGrafikonvalasztas() {
        return grafikonvalasztas;
    }

    public void setGrafikonvalasztas(Grafikonvalasztas grafikonvalasztas) {
        this.grafikonvalasztas = grafikonvalasztas;
    }

    public interface OnGrafikonValasztoFragmentInteractionListener {
        int NEXT = 1;
        void OnGrafikonValasztoFragmentInteraction(int query, int valasz);
    }
    private void fillContent(){
        if(grafikonvalasztas==null){
           fillDefault();
        }
        else {
            RudView r = (RudView)  getActivity().findViewById(R.id.rud);
            GraphVIew graphVIew;
            LinearLayout linearLayout= (LinearLayout) getActivity().findViewById(R.id.grafikonvalasztoRoot);
            int resourceId, i=0;
            String name = "graph";
            KoordinataRendszer koordinataRendszer;
            IgenybevetelSzamito igenybevetelSzamito;
            for(ArrayList<Hatas> hatasok: grafikonvalasztas.getValaszok()){
                resourceId = this.getResources().getIdentifier(name+Integer.toString(++i), "id", this.getActivity().getPackageName());
                graphVIew = (GraphVIew) getActivity().findViewById(resourceId);
                koordinataRendszer = new KoordinataRendszer();
                igenybevetelSzamito = new IgenybevetelSzamito();
                igenybevetelSzamito.setKoordinataRendszer(koordinataRendszer);
                igenybevetelSzamito.setMode(grafikonvalasztas.getModeok()[i-1]);
                graphVIew.setGraphFunctionProvider(igenybevetelSzamito);
                graphVIew.setTagolok_y(5);
                graphVIew.setXmax(1);
                koordinataRendszer.setHatasok(hatasok);
                if(grafikonvalasztas.getHelyes()==(i-1)){
                    r.getRud().setKoordinataRendszer(koordinataRendszer);
                    r.getRud().setLength(1);
                    r.setTagoloY(5);
                }
            }
        }

    }
    private void fillDefault(){
        RudView r = (RudView)  getActivity().findViewById(R.id.rud);
        GraphVIew g1 = (GraphVIew) getActivity().findViewById(R.id.graph1);
        GraphVIew g2 = (GraphVIew) getActivity().findViewById(R.id.graph2);
        GraphVIew g3 = (GraphVIew) getActivity().findViewById(R.id.graph3);
        GraphVIew g4 = (GraphVIew) getActivity().findViewById(R.id.graph4);
        KoordinataRendszer koordinataRendszer = new KoordinataRendszer();
        IgenybevetelSzamito igenybevetelSzamito = new IgenybevetelSzamito();
        igenybevetelSzamito.setKoordinataRendszer(koordinataRendszer);
        igenybevetelSzamito.setMode(IgenybevetelSzamito.HAJLITONYOMATEK);
        g1.setGraphFunctionProvider(igenybevetelSzamito);
        g2.setGraphFunctionProvider(igenybevetelSzamito);
        g3.setGraphFunctionProvider(igenybevetelSzamito);
        g4.setGraphFunctionProvider(igenybevetelSzamito);
        r.getRud().setKoordinataRendszer(koordinataRendszer);
        r.getRud().setLength(5);
        g1.setTagolok_y(5);
        g2.setTagolok_y(5);
        g3.setTagolok_y(5);
        g4.setTagolok_y(5);
        r.setTagoloY(5);
        g1.setXmax(5);
        g2.setXmax(5);
        g3.setXmax(5);
        g4.setXmax(5);
        koordinataRendszer.addHatas(new Ero(new Vektor(0,0,0),new Vektor(0,1,0)));
        koordinataRendszer.addHatas(new Ero(new Vektor(2,0,0),new Vektor(1,2,0)));
        /**
        g1.invalidate();
        g2.invalidate();
        g3.invalidate();
        g4.invalidate();
        r.invalidate();*/
    }
}
