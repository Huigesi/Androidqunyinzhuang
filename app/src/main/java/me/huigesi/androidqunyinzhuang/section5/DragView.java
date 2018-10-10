package me.huigesi.androidqunyinzhuang.section5;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class DragView extends View {
    int lastX, lastY;
    Scroller mScroller = new Scroller(getContext());

    public DragView(Context context) {
        super(context);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(
                    mScroller.getCurrX(),
                    mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        int rawX = (int) (event.getRawX());
        int rawY = (int) (event.getRawY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                //lastX=rawX;
                //lastY=rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;

                //int offsetX = rawX - lastX;
                //int offsetY = rawY - lastY;
                layout(getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY);

                /*//同时对left和right进行偏移
                offsetLeftAndRight(offsetX);
                //同时对top和bottom进行偏移
                offsetTopAndBottom(offsetY);*/

                //动态修改布局的位置参数
               /* LinearLayout.LayoutParams layoutParams=
                        (LinearLayout.LayoutParams) getLayoutParams();
                layoutParams.leftMargin=getLeft()+offsetX;
                layoutParams.topMargin=getTop()+offsetY;
                setLayoutParams(layoutParams);
                //ViewGroup.MarginLayoutParams就不用考虑父布局的类型
                ViewGroup.MarginLayoutParams layoutParams1=
                        (ViewGroup.MarginLayoutParams)getLayoutParams();
                layoutParams.leftMargin=getLeft()+offsetX;
                layoutParams.topMargin=getTop()+offsetY;
                setLayoutParams(layoutParams);*/

                //((View)getParent()).scrollBy(-offsetX,-offsetY);
                /*
                 * 使用绝对坐标系要记得重新设置初始坐标
                 * */
                //lastX=rawX;
                //lastY=rawY;
                break;
            case MotionEvent.ACTION_UP:
                View viewGroup = ((View) getParent());
                mScroller.startScroll(
                        viewGroup.getScrollX(),
                        viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),
                        -viewGroup.getScrollY()
                );
                invalidate();
                break;
        }
        return true;
    }
}
