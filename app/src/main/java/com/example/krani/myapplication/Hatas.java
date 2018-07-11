package com.example.krani.myapplication;

import android.content.Context;
import android.graphics.drawable.VectorDrawable;

public abstract class Hatas {
    private VectorDrawable mPic;
    private Context mContext;
    private float mPos_X;
    public Hatas(Context context){
        mContext = context;
    }

    public VectorDrawable getPic() {
        return mPic;
    }

    public void setPic(VectorDrawable mPic) {
        this.mPic = mPic;
    }

    public void setPos_X(float mPos_X) {
        this.mPos_X = mPos_X;
    }

    public float getmPos_X() {
        return mPos_X;
    }
}
