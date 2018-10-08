# 第3章 Android控件架构与自定义控件详解

## 3.1 Android 控件结构

Android控件大致分为两类：ViewGroup控件与View控件。

通过ViewGroup控件，界面上的控件形成一个树形结构（控件树）。

上层控件负责下层控件的测量与绘制，并传递交互事件。
Activity的findViewById()方法，就是在控件树中以树的深度优先遍历查找对应元素。

ViewParent 是整个控件树的核心。

![view结构](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539007164427&di=45a0ff662c6e8782b8c64b422db220ea&imgtype=0&src=http%3A%2F%2Fupload-images.jianshu.io%2Fupload_images%2F6118149-ffc51dfe9453e9e6.png)

Activity使用setContentView()方法来把整个DecorView添加到PhoneWindow中。

![UI结构](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539007104751&di=fe0e3eff042b28684e9cca268a934f8f&imgtype=0&src=http%3A%2F%2Fstatic.open-open.com%2Flib%2FuploadImg%2F20151218%2F20151218085754_582.png)

## 3.2 View的测量

Android通过MeasureSpec类测量View,有三种测量模式：

·**EXACTLY**

精确模式,比如android:layout_width="100dp",使用的是EXACTLY模式(默认)。

·**AT_MOST**

最大值模式,比如wrap_content,只要不超过父控件允许的最大尺寸，控件大小随内容变化而变化。

·**UNSPECIFIED**

绘制自定义View时使用

View类默认的onMeasure()只支持EXACTLY模式,所以自定义控件的时候最好重写onMeasure()方法：

     @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension(
                    measureWidth(widthMeasureSpec),
                    measureHeight(heightMeasureSpec)
            );
        }

        private int measureHeight(int heightMeasureSpec) {
            int result=0;
            int specMode=MeasureSpec.getMode(heightMeasureSpec);
            int specSize = MeasureSpec.getSize(heightMeasureSpec);
            if (specMode == MeasureSpec.EXACTLY) {
                result=specSize;
            }else {
                result=200;
                if (specMode == MeasureSpec.AT_MOST) {
                    result = Math.min(result, specSize);
                }
            }
            return result;
        }

        private int measureWidth(int widthMeasureSpec) {
            int result=0;
            int specMode=MeasureSpec.getMode(widthMeasureSpec);
            int specSize = MeasureSpec.getSize(widthMeasureSpec);
            if (specMode == MeasureSpec.EXACTLY) {
                result=specSize;
            }else {
                result=200;
                if (specMode == MeasureSpec.AT_MOST) {
                    //取最小（不能超过父控件）
                    result = Math.min(result, specSize);
                }
            }
            return result;
        }


## 3.3 View的绘制

简单介绍了Canvas对象。使用Canvas通常需要通过继承View并重写onDraw()方法。

在onDraw()方法里有一个canvas参数,使用它就能绘图。

在其他地方需要创建一个Canvas对象：


    Canvas canvas = new Canvas(bitmip);

创建时传一个bitmap对象时为了储存使用绘制在Canvas上的像素信息。

## 3.4 ViewGroup的测量

ViewGroup通过遍历子View的大小,从而决定自己的大小。

自定义ViewGroup,通常会取重写onLayout()方法控制子View显示位置的逻辑。
如果需要支持wrap_content属性,它也像View一样得去重写onMeasure()方法。

## 3.5 ViewGroup的绘制

ViewGroup的onDraw()方法只会在指定背景颜色时调用。

ViewGroup使用dispatchDraw()方法来绘制子View,过程：遍历->调用子View的绘制方法。


## 3.6 自定义View

View中比较重要的回调方法:

·**onFinishInflate()**: 从XML加载组件后回调。

·**onSizeChanged()**: 组件大小改变时回调。

·**onMeasure()**: 测量方法。

·**onLayout()**: 确定显示的位置。

·**onTouchEvent()**: 监听触摸事件。

有三种方法实现自定义的控件：

·**对现有控件进行拓展**

书中演示了一个textView例子。

·**创建复合控件**

演示了一个TopBar控件。

·**重写View来实现全新的控件**

演示了弧线展示图和音频条形图。

## 3.7 自定义ViewGroup

自定义ViewGroup需要重写onMeasure()方法来对子View进行测量，重写onLayout()方法来确定子View的位置,
重写onTouchEvent()方法增加响应事件。

演示了一个类似ScrollView的自定义ViewGroup

## 3.8 事件拦截机制分析


如果一个触摸事件，子View和父ViewGroup都有可能想处理,这就需要事件拦截。

在ViewGroup中,事件有三个方法:

·**dispatchTouchEvent**()

·**onInterceptTouchEvent**()

·**onTouchEvent**()

而View只有两个方法：

·**dispatchTouchEvent**()

·**onTouchEvent**()

View比ViewGroup少了个onInterceptTouchEvent()方法,这个onInterceptTouchEvent()方法就是事件拦截的核心代码。

事件传递时,先执行dispatchTouchEvent()方法，再执行onInterceptTouchEvent()方法。

事件处理时,都是执行onTouchEvent()方法。


假设有三层结构,

最外层叫它MyViewGroupA,

第二层叫它MyViewGroupB,

最里层叫它MyView。

事件传递时，dispatchTouchEvent()方法一般不会去改写，所以暂时不管。
则事件的传递过程：
MyViewGroupA -> onInterceptTouchEvent() A  -> MyViewGroupB -> onInterceptTouchEvent() B -> MyView

事件的处理过程：

onTouchEvent() View  -> onTouchEvent() B -> onTouchEvent() A


方法的返回值都是布尔值,初始情况下返回值都是false(都往下传递\往上处理)。

如果在MyViewGroupA的onInterceptTouchEvent()中返回true,则MyViewGroupA的三个方法都跑一遍,事件结束。

如果在MyViewGroup的onInterceptTouchEvent()B中返回true,则在MyViewGroupA的onTouchEvent()响应前,会把MyViewGroupB的三个方法都跑一遍。

