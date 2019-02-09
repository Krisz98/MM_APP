package com.example.krani.myapplication;

import android.content.Context;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.krani.myapplication.FeladatSzervezes.Felelet_valasztas;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Valaszto.OnValasztoFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Valaszto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Valaszto extends Fragment implements FeladatProperties, View.OnClickListener {

    // TODO: Rename and change types of parameters
    private int mNehezseg;
    private int mSzintido;
    private String valaszA;
    private String valaszB;
    private String valaszC;
    private String valaszD;
    private String kerdes;
    private boolean[] jelolesek;
    private final String LOG_TAG ="FeladatValsztoFragment";
    private OnValasztoFragmentInteractionListener mListener;

    public Valaszto() {
        // Required empty public constructor
    }
    public static Valaszto newInstance(Felelet_valasztas felelet_valasztas) {
        Valaszto fragment = new Valaszto();
        Bundle args = new Bundle();
        fragment.setJelolesek(new boolean[4]);
        for(int i = 0;i<4;i++){
            fragment.getJelolesek()[i] = false;
        }
        args.putInt(SZINTIDO_KEY, felelet_valasztas.getSzintido());
        args.putInt(NEHEZSEG_KEY, felelet_valasztas.getNehezseg());
        args.putString("KERDES",felelet_valasztas.getKerdes());
        args.putString("A",felelet_valasztas.getValasz_A());
        args.putString("B",felelet_valasztas.getValasz_B());
        args.putString("C",felelet_valasztas.getValasz_C());
        args.putString("D",felelet_valasztas.getValasz_D());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSzintido = getArguments().getInt(SZINTIDO_KEY);
            mNehezseg = getArguments().getInt(NEHEZSEG_KEY);
            kerdes = getArguments().getString("KERDES");
            valaszA = getArguments().getString("A");
            valaszB = getArguments().getString("B");
            valaszC = getArguments().getString("C");
            valaszD = getArguments().getString("D");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_valaszto, container, false);
        v.findViewById(R.id.valaszA).setOnClickListener(this);
        v.findViewById(R.id.valaszB).setOnClickListener(this);
        v.findViewById(R.id.valaszC).setOnClickListener(this);
        v.findViewById(R.id.valaszD).setOnClickListener(this);
        v.findViewById(R.id.tovabb).setOnClickListener(this);
        ((TextView)v.findViewById(R.id.ansA)).setText(valaszA);
        ((TextView)v.findViewById(R.id.ansB)).setText(valaszB);
        ((TextView)v.findViewById(R.id.ansC)).setText(valaszC);
        ((TextView)v.findViewById(R.id.ansD)).setText(valaszD);
        ((TextView)v.findViewById(R.id.feleletvalasztoKerdes)).setText(kerdes);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnValasztoFragmentInteractionListener) {
            mListener = (OnValasztoFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int valasz = -1;
        if(id==R.id.tovabb){
            mListener.OnValasztoFragmentInteraction(OnValasztoFragmentInteractionListener.NEXT,jelolesek);
            return;
        }
        ViewGroup w =(ViewGroup)v;
        if(id == R.id.valaszA){
            valasz = 0;
        }
        else if(id == R.id.valaszB) valasz = 1;
        else if(id == R.id.valaszC) valasz =2;
        else if(id == R.id.valaszD) valasz = 3;
        jelolesek[valasz] = !jelolesek[valasz];
        ImageView circle = (ImageView) w.getChildAt(0);

        if(jelolesek[valasz]) circle.setBackground(getResources().getDrawable(R.drawable.valaszto_selected));
        else circle.setBackground(getResources().getDrawable(R.drawable.valaszto_not_selected));
        boolean jelenit = false;
        for(int i = 0;i<4;i++){
            if(jelolesek[i]){
                jelenit = true;
            }
        }
        if(jelenit) getActivity().findViewById(R.id.tovabb).setVisibility(View.VISIBLE);
        else getActivity().findViewById(R.id.tovabb).setVisibility(View.GONE);
    }

    public boolean[] getJelolesek() {
        return jelolesek;
    }

    public void setJelolesek(boolean[] jelolesek) {
        this.jelolesek = jelolesek;
    }

    public interface OnValasztoFragmentInteractionListener {
        int NEXT=1;
        void OnValasztoFragmentInteraction(int query, boolean[] valasz);
    }
}
