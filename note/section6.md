

# 第6章 Android绘图机制与处理技巧

## 6.1 屏幕的尺寸信息

#### 6.1.1 屏幕参数

**屏幕大小** 指屏幕对角线的长度，通常用“寸”来度量。

**分辨率** 指手机屏幕的像素点的个数。

**PPI** 每英寸像素，又被称为**DPI**。由对角线的像素点除以屏幕的大小得到。

#### 6.1.2 系统屏幕密度

![]()

#### 6.1.3 独立像素密度

Android系统使用mdpi即密度值为160的屏幕作为标准，在这个屏幕上1px=1dp。

在mdpi这1dp=1px,在hdpi中1dp=1.5px,在xhdpi中1dp=2px,在xxhdpi中1dp=3px。

ldpi:mdpi:hdpi:xhdpi:xxhdpi=3:4:6:8:12。

#### 6.1.4 单位转换

    public class DisplayUtil {
        /*将px值转换为dip或dp值，保证尺寸大小不变*/
        public static int px2dip(Context context, float pxValue) {
            final float scale=context.getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5f);
        }
    
        /*将dip或dp值转换为px值，保证尺寸大小不变*/
        public static int dip2px(Context context, float dipValue) {
            final float scale=context.getResources().getDisplayMetrics().density;
            return (int) (dipValue * scale + 0.5f);
        }
    
        /*将px值转换为sp值，保证文字大小不变*/
        public static int px2sp(Context context, float pxValue) {
            final  float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
            return (int) (pxValue / fontScale + 0.5f);
        }
    
        /*将sp值转换为px值，保证文字大小不变*/
        public static int sp2px(Context context, float spValue) {
            final float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
            return (int) (spValue * fontScale + 0.5f);
        }
    
    }
## 6.2 2D绘图基础

系统通过提供的Canvas对象来提供绘图方法。它提供了各种绘制对象的API。

## 6.3 Android XML 绘图

#### 6.3.1 Bitmap
#### 6.3.2 Shape
#### 6.3.3 Layer

Layer可以实现Photoshop中的图层效果。

#### 6.3.4 Selector

Selector可以通过不同的事件设置不同的图像。

## 6.4 Android 绘图技巧

#### 6.4.1 Canvas

Canvas.save()方法是将之前所有已绘制图像保存起来，让后续的操作就好像在一个新的图层上操作一样。

Canvas.restore()方法是将我们在save()之后的所有图像与save()之前的图像合并。

#### 6.4.2 Layer图层

## 6.5 Android 图像处理之色彩特效处理

#### 6.5.1 色彩矩阵分析

Android中，系统使用一个颜色矩阵——ColorMatrix，来处理图像的色彩效果。

##### 6.5.1.1 改变偏移量

##### 6.5.1.2 改变颜色系数

##### 6.5.1.3 改变色光属性

#### 6.5.2 Android颜色矩阵--ColorMatrix



