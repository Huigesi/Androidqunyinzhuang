package me.huigesi.androidqunyinzhuang.section7;

import android.view.View;

public class WrapperView {
    private View mTarget;

    public WrapperView(View target) {
        mTarget = target;
    }

    public int getWidth() {
        return mTarget.getLayoutParams().width;
    }

    public void setWidth(int width) {
        mTarget.getLayoutParams().width=width;
        mTarget.requestLayout();
    }
}
