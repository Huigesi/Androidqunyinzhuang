# ListView 使用技巧

### 4.1 ListView常用优化技巧

#### 4.1.1 使用ViewHolder模式提高效率

使用ViewHolder可以避免每次在调用getView()的时候都去通过findViewById()实例化控件。

#### 4.1.2 设置项目间分隔线

ListView提供了divider和dividerHeight这两个属性实现分割线功能。

            android:divider="@color/colorAccent"
            android:dividerHeight="10dp"

#### 4.1.3 隐藏ListView的滚动条

    android:scrollbars="none"

#### 4.1.4 取消ListView的Item点击效果

    android:listSelector="#00000000"

#### 4.1.5