package com.example.krani.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.krani.myapplication.FeladatSzervezes.Igaz_Hamis;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IgazHamis.OnIgazHamisFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IgazHamis#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IgazHamis extends Fragment implements FeladatProperties, View.OnClickListener{
    private OnIgazHamisFragmentInteractionListener mListener;
    private final String LOG_TAG = "IgazHamisFragment";
    private int mNehezseg;
    private int mSzintido;
    private String allitas;
    private boolean igaz;
    public IgazHamis() {
        // Required empty public constructor
    }

    public static IgazHamis newInstance(Igaz_Hamis igaz_hamis) {
        IgazHamis fragment = new IgazHamis();
        Bundle args = new Bundle();
        args.putInt(SZINTIDO_KEY, igaz_hamis.getSzintido());
        args.putInt(NEHEZSEG_KEY, igaz_hamis.getNehezseg());
        args.putString("ALLITAS",igaz_hamis.getAllitas());
        args.putBoolean("HELYES",igaz_hamis.isIgaz());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSzintido = getArguments().getInt(SZINTIDO_KEY);
            mNehezseg = getArguments().getInt(NEHEZSEG_KEY);
            allitas = getArguments().getString("ALLITAS");
            igaz = getArguments().getBoolean("HELYES");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_igaz_hamis, container, false);
        ((TextView)v.findViewById(R.id.feladat_igazhamis)).setText(this.allitas);
        v.findViewById(R.id.FalseBtn).setOnClickListener(this);
        v.findViewById(R.id.TrueBtn).setOnClickListener(this);
        Log.v(LOG_TAG,Boolean.toString(igaz));
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnIgazHamisFragmentInteractionListener) {
            mListener = (OnIgazHamisFragmentInteractionListener) context;
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
        //RippleDrawable background = (RippleDrawable) v.getBackground();
        // background.setColor(ColorStateList.valueOf(getResources().getColor(R.color.graphClicked)));
        Log.v(LOG_TAG,getResources().getResourceName(v.getId())+"Clicked");
        if(v.getId() == R.id.TrueBtn){
            mListener.OnIgazHamisFragmentInteraction(OnIgazHamisFragmentInteractionListener.NEXT,true);
        }else if(v.getId()==R.id.FalseBtn){
            mListener.OnIgazHamisFragmentInteraction(OnIgazHamisFragmentInteractionListener.NEXT,false);
        }

    }

    public interface OnIgazHamisFragmentInteractionListener {
        int NEXT=301;
        void OnIgazHamisFragmentInteraction(int query, boolean valasz);
    }
}
