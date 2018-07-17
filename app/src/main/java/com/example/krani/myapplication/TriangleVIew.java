package com.example.krani.myapplication;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.VectorDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class TriangleVIew extends View {
    private Paint mPaint;
    private Path mPath;
    private int count=1;
    private float angle= (float) (Math.PI/2/count);
    public float maxAngle = (float) (Math.PI/2);
    private int endX;
    private int endY;
    private int startX;
    private int startY;
    private int mRadius;
    private float mRotation=0;
    private int mColor = R.color.underAxisY;
    private VectorDrawable vectorDrawable;
    private float a= angle/5;
    private float vectposx=0.7f;
    private int wi;
    private int he;

    public TriangleVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public TriangleVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public TriangleVIew(Context context) {
        super(context);
        init(context);

    }

    public TriangleVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);


    }
    private void init(Context context){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath = new Path();
        mPaint.setStrokeWidth(1);
        mPaint.setColor(getResources().getColor(mColor));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPath.setFillType(Path.FillType.EVEN_ODD);
        vectorDrawable = (VectorDrawable) context.getResources().getDrawable(R.drawable.arrow_1);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wh = MeasureSpec.getSize(widthMeasureSpec)-getPaddingLeft()-getPaddingRight();
        int hh = (int) (Math.sin(angle)*wh)+getPaddingBottom()+getPaddingTop();
        int w = resolveSizeAndState(wh+getPaddingLeft()+getPaddingRight(), widthMeasureSpec, MeasureSpec.EXACTLY);
        int h = resolveSizeAndState(hh, heightMeasureSpec, MeasureSpec.EXACTLY);
        this.wi=wh;
        this.he=hh;
        setMeasuredDimension(w, h);
        startX = getPaddingLeft();
        endX = startX+wh;
        startY = getPaddingTop();
        endY = hh-getPaddingBottom();
        mRadius = endX-getPaddingLeft();
        Point p1 = new Point(startX,endY);
        Point p2 = new Point(endX,endY);
        mPath.moveTo(p1.x,p1.y);
        mPath.lineTo(p2.x,p2.y);
        mPath.arcTo(new RectF(startX-(mRadius),endY-mRadius,endX, endY+mRadius), 0,(float) (-angle*(180/Math.PI)));
        mPath.close();
        this.setPivotX(startX);
        this.setPivotY(endY);
        int l = (int) (mRadius*vectposx);
        int vectY = (int) (l*Math.tan(4*a)-l*Math.tan(a));
        float lambda = vectY/ TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,155,getResources().getDisplayMetrics());
        vectorDrawable.setBounds(startX+l,(int)(endY-vectY-l*Math.tan(a)), (int)(startX + l+(lambda *  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,45,getResources().getDisplayMetrics()))),(int)(endY-l*Math.tan(a)));
        int hasd= 0;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAlpha(50);
        canvas.drawPath(mPath,mPaint);
        //mPaint.setColor(Color.BLUE);
        //mPaint.setStrokeWidth(10);
        //canvas.drawLine(startX,endY,endX,endY,mPaint);
        vectorDrawable.draw(canvas);
        //canvas.drawLine(startX,endY,endX,startY,mPaint);


    }

    public void setCount(int count) {
        this.count = count;
        this.angle = maxAngle/count;
        this.a = this.angle/5;
    }

    public int getCount() {
        return count;
    }


    public float getAngle() {
        return angle;
    }

    public void setmRotation(float mRotation) {
        this.mRotation = mRotation;
        this.setRotation(mRotation);
    }

    public float getmRotation() {
        return mRotation;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }

    public int getmColor() {
        return mColor;
    }

    public int getHe() {
        return he;
    }

    public int getWi() {
        return wi;
    }

    public void setVectorDrawable(VectorDrawable vectorDrawable) {
        this.vectorDrawable = vectorDrawable;
    }

    public VectorDrawable getVectorDrawable() {
        return vectorDrawable;
    }
}
