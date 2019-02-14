package com.example.krani.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AchievementsFragment extends Fragment {

    private OnAchievementFragmentInteractionListener mListener;

    public AchievementsFragment() {
        // Required empty public constructor
    }

    public static AchievementsFragment newInstance() {
        AchievementsFragment fragment = new AchievementsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        View v = inflater.inflate(R.layout.fragment_achievements, container, false);
        RecyclerView recyclerView=(RecyclerView)v.findViewById(R.id.recyclerview);
        ArrayList<Datas> adatok = new ArrayList<>();
        adatok.add(new Datas("22dS43F","2019-02-14",34));
        adatok.add(new Datas("943578dfs","2019-02-12",21));
        recyclerView.setAdapter(new MyAdapter(adatok));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onAchievementFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAchievementFragmentInteractionListener) {
            mListener = (OnAchievementFragmentInteractionListener) context;
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

    public interface OnAchievementFragmentInteractionListener {
        // TODO: Update argument type and name
        void onAchievementFragmentInteraction(Uri uri);
    }
    private class Datas{
        private String kod;
        private String date;
        private double pontszam;

        public Datas(String kod, String date, double pontszam) {
            this.kod = kod;
            this.date = date;
            this.pontszam = pontszam;
        }

        public double getPontszam() {
            return pontszam;
        }

        public String getDate() {
            return date;
        }

        public String getKod() {
            return kod;
        }
    }
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        private ArrayList<Datas> adatok;
        public MyAdapter(ArrayList<Datas> adatok) {
            super();
            this.adatok = adatok;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CardView c = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.history_view_holder,parent,false);
            MyViewHolder myViewHolder = new MyViewHolder(c);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            ((TextView)holder.cardView.findViewById(R.id.pontszam)).setText(Double.toString(adatok.get(position).pontszam));
            ((TextView)holder.cardView.findViewById(R.id.kod_hely)).setText(adatok.get(position).kod);
            ((TextView)holder.cardView.findViewById(R.id.datum)).setText(adatok.get(position).date);
        }

        @Override
        public int getItemCount() {
            return adatok.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public CardView cardView;
            public MyViewHolder(CardView view){
                super(view);
                this.cardView = view;
            }
        }
    }

}
