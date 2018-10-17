package me.huigesi.androidqunyinzhuang.section7;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.RotateAnimation;

public class AlphaAnimationDemo {
    View view;
    public AlphaAnimationDemo() {
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);
        view.startAnimation(aa);

        RotateAnimation ra = new RotateAnimation(0, 360, 100, 100);
        ra.setDuration(1000);
        view.startAnimation(ra);

        ObjectAnimator animator = ObjectAnimator.ofFloat(
                view,
                "translationX",
                300);
        animator.setDuration(300);
        animator.start();

    }


}
