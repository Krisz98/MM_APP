package com.example.krani.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.krani.myapplication.adatok.AdatokContract;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;

public class AchievementsFragment extends Fragment {
    public static final String LOGTAG= "AchievementsFragment";
    private OnAchievementFragmentInteractionListener mListener;
    protected RecyclerView recyclerView;
    protected Cursor adats;
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
        recyclerView=(RecyclerView)v.findViewById(R.id.recyclerview);
        adats = null;
        recyclerView.setAdapter(new MyAdapter(adats));
        recyclerView.invalidate();
        return v;
    }
    protected void ertesit(){
        recyclerView.getAdapter().notifyDataSetChanged();
    }
    @Override
    public void onStart() {
        super.onStart();
        ReadFromDatabase readFromDatabase = new ReadFromDatabase();
        readFromDatabase.execute();
        recyclerView.getAdapter().notifyDataSetChanged();
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
        public Cursor adatok;
        public MyAdapter(Cursor adatok) {
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
            if(adatok!=null){
                adatok.moveToPosition(position);
                ((TextView)holder.cardView.findViewById(R.id.pontszam)).setText(Double.toString(adatok.getDouble(adatok.getColumnIndexOrThrow(AdatokContract.Eredmenyek.COLUMN_NAME_PONTSZAM))));
                ((TextView)holder.cardView.findViewById(R.id.kod_hely)).setText("kos");
                ((TextView)holder.cardView.findViewById(R.id.datum)).setText(adatok.getString(adatok.getColumnIndexOrThrow(AdatokContract.Eredmenyek.COLUMN_NAME_DATUM)));
            }

        }

        @Override
        public int getItemCount() {
            if(adatok!=null) return adatok.getCount();
            return 0;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public CardView cardView;
            public MyViewHolder(CardView view){
                super(view);
                this.cardView = view;
            }
        }

        public void setAdatok(Cursor adatok) {
            this.adatok = adatok;
            this.notifyDataSetChanged();
        }
    }
    private class ReadFromDatabase extends AsyncTask<Void,Void,Cursor>{


        @Override
        protected Cursor doInBackground(Void... voids) {
            AdatokContract.EredmenyekDbHelper helper = new AdatokContract.EredmenyekDbHelper(getContext());
            SQLiteDatabase db = helper.getReadableDatabase();
            String[] projection = {AdatokContract.Eredmenyek.COLUMN_NAME_DATUM, AdatokContract.Eredmenyek.COLUMN_NAME_PONTSZAM};
            Cursor cursor = db.query(AdatokContract.Eredmenyek.TABLE_NAME,projection,null,null,null,null,null);
            while(cursor.moveToNext()){
                Log.v(LOGTAG,cursor.getString(cursor.getColumnIndexOrThrow(AdatokContract.Eredmenyek.COLUMN_NAME_DATUM)));
            }
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            MyAdapter myAdapter = (MyAdapter) recyclerView.getAdapter();
            adats = cursor;
            myAdapter.setAdatok(adats);
            Log.v(LOGTAG,"Beolvasva");
        }
    }

}
