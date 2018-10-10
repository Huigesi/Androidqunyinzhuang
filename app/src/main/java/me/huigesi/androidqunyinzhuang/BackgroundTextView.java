package me.huigesi.androidqunyinzhuang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.MotionEvent;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class BackgroundTextView extends TextView {
    Paint mPaint,mPant1, mPant2;
    LinearGradient mLinearGradient;
    Matrix mGradientMatrix;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取当前输入点的X、Y坐标（视图坐标）
        int x=(int)event.getX();
        int y=(int)event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return true;
    }

    public BackgroundTextView(Context context) {
        super(context);
        mPant1 = new Paint();
        mPant1.setColor(getResources().getColor(R.color.colorAccent));
        mPant1.setStyle(Paint.Style.FILL);
        mPant2 = new Paint();
        mPant2.setColor(Color.YELLOW);
        mPant2.setStyle(Paint.Style.FILL);
    }
    int mTranslate;
    @Override
    protected void onDraw(Canvas canvas) {
        /*canvas.drawRect(0,
                0,
                getMeasuredWidth(),
                getMeasuredHeight(),
                mPant1);
        canvas.drawRect(10,
                10,
                getMeasuredWidth()-10,
                getMeasuredHeight()-10,
                mPant2);
        canvas.save();
        canvas.translate(10, 0);
        super.onDraw(canvas);
        canvas.restore();*/
        super.onDraw(canvas);
        if (mGradientMatrix != null) {
            mTranslate+=mViewWidth/5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate=-mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
    }

    int mViewWidth;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth=getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint=getPaint();
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                        new int[]{
                                Color.BLUE, 0xffffff, Color.BLUE},
                        null,
                        Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }
}
