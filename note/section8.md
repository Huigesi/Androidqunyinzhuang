


# 第8章 Activity与Activity调用栈分析

## 8.1 Activity

### 8.1.1 起源

### 8.1.2 Activity 形态

**Active/Running** 
Activity处于Activity栈最顶层，可见，并与用户进行交互。

**Paused**
Activity失去焦点，被一个非全屏的Activity或者一个透明的Activity放置在栈顶的形态，
状态信息、成员变量都还保持着。

**Stopped**
Activity被另一个Activity完全覆盖，它不再可见，但却依然保持了所有状态信息和成员变量。

**Killed**
当Activity被系统回收掉或出来没有创建过的状态。

### 8.1.3 生命周期

有三种生命周期状态是稳定的：

**Resumed**
Activity处于Activity栈顶

**Paused**
当Activity的一部分被挡住时候进入这个状态。

**Stopped**
当Activity完全被覆盖时进入这个状态。

#### 8.1.3.1 Activity 启动与销毁过程

启动：
onCreate()->onStart()->onResume()

销毁：调用onDestroy()结束Activity的生命周期。

onCreate()中：创建基本的UI元素。

onPause()与onStop():清除Activity的资源，避免浪费。

onDestory()中：清除开启的线程。

#### 8.1.3.2 Activity的暂停与恢复过程

当栈顶的Activity部分不可见后，就会导致Activity进入Pause形态，
此时就会调用onPause()方法，当结束阻塞后，就会调用onResume()方法恢复到Resume形态。

onPause():释放系统资源，如Camera,sensor,receivers。

onResume():需要重新初始化在onPause()中释放的资源。

#### 8.1.3.3 Activity的停止过程

当Activity在系统不可见时，会调用onPause()过程。

#### 8.1.3.4 Activity的重新创建过程

系统回收Activity时，Activity状态会通过onSaveInstanceState()方法保存到
Bundle对象中。当需要重新创建Acivity时，保存的Bundle对象就会传递到
Activity的onRestoreInstanceState()方法与onCreate()方法中。


## 8.2 Android 任务栈简介

栈结构：后进先出（Last In First Out）

## 8.3 AndroidMainFest 启动模式
    
### 8.3.1 standard

默认的启动模式，每次都会创建新的实例。

### 8.3.2 singleTop

启动时，系统会判断当前栈顶Activity是不是要启动的Activity，
如果是则不创建新的Activity而直接引用这个Activity;如果不是则创建新的Activity。

### 8.3.3 singleTask

启动时，系统会判断整个Activity栈中是否存在需要启动的Activity，
如果存在，则将该Activity置于栈顶，**并将Activity以上的Activity都销毁**。

### 8.3.4 singleInstance

声明的Activity会出现在一个新的任务栈中，而且该任务栈中只存在这一个Activity。

## 8.4 Intent Flag 启动模式

**Intent.FLAG_ACTIVITY_NO_HISTORY**

使用这种模式启动Activity,当该Activity启动其他Activity后，
该Activity就消失了，不会保留在Activity栈中。
例如A-B，B中以这种模式启动C,C再启动D,则当前Activity栈为ABD。

## 8.5 清空任务栈

**clearTaskOnLaunch**

clearTaskOnLaunch在每次返回该Activity时，
都将该Activity之上的所有Activity清。通过这个属性，
可以让这个Task每次在初始化的时候，都只有这一个Activity。


**finishOnTaskLaunch**

finishOnTaskLaunch 当离开这个Activity所处的Task,那么用户再返回时，
该Activity就会被finish掉。

**alwaysRetainTaskState**

如果设置为true,则Activity所在的Task将不接受任何清理命令，一直保持当前Task状态。

## 8.6 Activity任务栈所用

app中使用Activity任务栈一定要根据实际项目的需要，而不是为了使用任务栈而使用任务栈。



