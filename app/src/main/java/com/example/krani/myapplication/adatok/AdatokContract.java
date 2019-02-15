package com.example.krani.myapplication.adatok;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class AdatokContract {

    private AdatokContract(){}

    public static class Eredmenyek implements BaseColumns{

        public static final String TABLE_NAME = "eredmenyek";
        public static final String COLUMN_NAME_PONTSZAM = "pontszam";
        public static final String COLUMN_NAME_IDO = "ido";
        public static final String COLUMN_NAME_DATUM = "datum";
        public static final String COLUMN_NAME_KOD = "kod";

    }
    public static class EredmenyekDbHelper extends SQLiteOpenHelper{

        public static final String DATABASE_NAME = "Eredmenyek.db";

        public static final int DATABASE_VERSION = 1;

        private final String CREATE_TABLE_EREDMENYEK = "CREATE TABLE " + Eredmenyek.TABLE_NAME + " ("+Eredmenyek._ID + " INTEGER PRIMARY KEY," +
                Eredmenyek.COLUMN_NAME_PONTSZAM+" REAL," +Eredmenyek.COLUMN_NAME_IDO+" REAL,"+Eredmenyek.COLUMN_NAME_DATUM+" TEXT," + Eredmenyek.COLUMN_NAME_KOD +" TEXT)";

        private final String DELETE_TABLE_EREDMENYEK = "DROP TABLE IF EXISTS "+Eredmenyek.TABLE_NAME;

        public EredmenyekDbHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_EREDMENYEK);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DELETE_TABLE_EREDMENYEK);
            onCreate(db);
        }

    }

}
