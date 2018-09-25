package com.example.test.customviewtrial;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyCustomView extends View {
    private int labelColor,circleColor,textSize=50;
    private String labelText;
    private Paint circlePaint;

    public MyCustomView(Context context) {
        super(context);
        circlePaint=new Paint();
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        circlePaint=new Paint();
        TypedArray a=context.getTheme().obtainStyledAttributes(attrs,R.styleable.MyCustomView,0,0);
        try{
            labelText=a.getString(R.styleable.MyCustomView_label);
            labelColor=a.getInteger(R.styleable.MyCustomView_labelColor,0);
            circleColor=a.getInteger(R.styleable.MyCustomView_circleColor,0);
        }finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int hSpecMode=MeasureSpec.getMode(heightMeasureSpec);
        int wSpecMode=MeasureSpec.getMode(widthMeasureSpec);

        int wSpecSize=MeasureSpec.getSize(widthMeasureSpec);
        int hSpecSize=MeasureSpec.getSize(heightMeasureSpec);

        circlePaint.setTextSize(textSize);
        int myWidth=(int)circlePaint.measureText(getLabelText());
        int myHeight=myWidth+10;

        if(wSpecMode==MeasureSpec.EXACTLY){
            myWidth=wSpecSize;
        }
        else{
            myWidth=resolveSize(myWidth,widthMeasureSpec);
        }


        if(hSpecMode==MeasureSpec.EXACTLY){
            myHeight=hSpecSize;
        }
        else{
            myHeight=resolveSize(myHeight,heightMeasureSpec);
        }
        setMeasuredDimension(myWidth+20,myHeight+20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int viewWidthHalf=this.getMeasuredWidth()/2;
        int viewHeightHalf=this.getMeasuredHeight()/2;
        int radius=0;

        if(viewWidthHalf<viewHeightHalf){
            radius=viewWidthHalf-10;
        }
        else{
            radius=viewHeightHalf-10;
        }

        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(getCircleColor());
        canvas.drawCircle(viewWidthHalf,viewHeightHalf,radius,circlePaint);

        circlePaint.setColor(getLabelColor());
        circlePaint.setTextAlign(Paint.Align.CENTER);
        circlePaint.setTextSize(textSize);
        canvas.drawText(getLabelText(),viewWidthHalf,viewHeightHalf,circlePaint);
    }

    public int getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(int labelColor) {
        this.labelColor = labelColor;
        invalidate();
        requestLayout();
    }

    public int getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
        invalidate();
        requestLayout();
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
        invalidate();
        requestLayout();
    }
}
