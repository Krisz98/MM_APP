package com.example.krani.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LauncherFragment.OnLauncherFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LauncherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LauncherFragment extends Fragment implements View.OnClickListener {
    private final String mm_url = "http://www.mm.bme.hu/";
    private OnLauncherFragmentInteractionListener mListener;

    public LauncherFragment() {
        // Required empty public constructor
    }
    public static LauncherFragment newInstance() {
        LauncherFragment fragment = new LauncherFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_launcher, container, false);
        v.findViewById(R.id.mmhonlap).setOnClickListener(this);
        v.findViewById(R.id.game1).setOnClickListener(this);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onLauncherFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLauncherFragmentInteractionListener) {
            mListener = (OnLauncherFragmentInteractionListener) context;
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
        switch(v.getId()){
            case R.id.mmhonlap:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(mm_url));
                startActivity(i);
                break;
            case R.id.game1:
                Intent b = new Intent(getActivity(),Game1Activity.class);
                startActivity(b);
                break;
        }
    }

    public interface OnLauncherFragmentInteractionListener {
        // TODO: Update argument type and name
        void onLauncherFragmentInteraction(Uri uri);
    }
}
