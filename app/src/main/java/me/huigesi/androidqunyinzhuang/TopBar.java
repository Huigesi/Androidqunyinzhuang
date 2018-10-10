package me.huigesi.androidqunyinzhuang;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TopBar extends RelativeLayout{
    int mLeftTextColor;
    Drawable mLeftBackground;
    String mLeftText;
    int mRightTextColor;
    Drawable mRightBackground;
    String mRightText;
    float mTitleTextSize;
    int mTitleTextColor;
    String mTitle;
    LayoutParams mLeftParams,mRightParams,mTitlepParams;

    Button mLeftButton,mRightButton;
    TextView mTitleView;
    Context context;
    topbarClickListener mListener;

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mLeftTextColor=ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = ta.getDrawable(
                R.styleable.TopBar_leftBackground
        );
        mLeftText = ta.getString(R.styleable.TopBar_leftTest);
        mRightTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);
        mTitleTextSize = ta.getDimension(
                R.styleable.TopBar_titleTextSize, 10
        );
        mTitleTextColor = ta.getColor(
                R.styleable.TopBar_titleTextColor, 0
        );
        mTitle = ta.getString(R.styleable.TopBar_title);
        ta.recycle();
        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setText(mLeftText);
        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        mLeftParams=new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT
        );
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        addView(mLeftButton, mLeftParams);
        mRightParams=new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT
        );
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton,mRightParams);

        mTitlepParams=new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT
        );
        mTitlepParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        addView(mTitleView,mTitlepParams);

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });
        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });
    }

    public void setOnTopbarClickListener(topbarClickListener mListener) {
        this.mListener=mListener;
    }
}
