# 第5章 Android Scroll分析

### 5.1 滑动效果是如何产生的

#### 5.1.1 Android 坐标系

系统提供了
**getLocationOnScreen(intlocation[])**
这样的方法获取Android坐标系中点的位置。

在触控事件中使用
**getRaw()、getRawY()**
方法获得Android 坐标系的坐标。

#### 5.1.2 视图坐标系

试图坐标系描述了子试图在父试图中的位置关系。

在视图坐标系中，原点不再是Android坐标系中的屏幕最左上角，而是以父试图坐上角为坐标系原点。

在触控事件中使用
**getX()、getY()**
获取视图坐标系中的坐标。

#### 5.1.3 触控事件--MotionEvent

在onTouchEvent(MotionEvent event)方法中通过event.getAction()来获取触控事件的类型。

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

![获取坐标值的各种方法]()

### 5.2 实现滑动的七种方法

#### 5.2.1 layout方法

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
                    /*
                    * 使用绝对坐标系要记得重新设置初始坐标
                    * */
                    //lastX=rawX;
                    //lastY=rawY;
                    break;

            }
            return true;
        }

#### 5.2.2 offsetLeftAndRight()与offsetTopAndBottom()

就算出偏移量后，使用如下代码。

    //同时对left和right进行偏移
    offsetLeftAndRight(offsetX);
    //同时对top和bottom进行偏移
    offsetTopAndBottom(offsetY);

#### 5.2.3 LayoutParams

    //动态修改布局的位置参数
    LinearLayout.LayoutParams layoutParams=
     (LinearLayout.LayoutParams) getLayoutParams();
    layoutParams.leftMargin=getLeft()+offsetX;
    layoutParams.topMargin=getTop()+offsetY;
    setLayoutParams(layoutParams);

 也可以使用ViewGroup.MarginLayoutParams

    //ViewGroup.MarginLayoutParams就不用考虑父布局的类型
    ViewGroup.MarginLayoutParams layoutParams1=
     (ViewGroup.MarginLayoutParams)getLayoutParams();
    layoutParams.leftMargin=getLeft()+offsetX;
    layoutParams.topMargin=getTop()+offsetY;
    setLayoutParams(layoutParams);

#### 5.2.4 scrollTo与scrollBy

因为scrollBy方法移动的是View的content，所以应该就View所在的ViewGroup中来使用scrollBy方法。

而设置scrollBy时存在参考系选择的问题，所以需要给偏移量加上负号。

    ((View)getParent()).scrollBy(-offsetX,-offsetY);


#### 5.2.5 Scroller

通过Scroller类可以实现平滑移动的效果，scrollBy是瞬间移动。

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

#### 5.2.6 属性动画

第7章中讲。

#### 5.2.7 ViewDragHelper

ViewDragHelper基本可以实现各种不同的滑动、拖放需求。

演示了一个QQ滑动侧边栏的布局案例。