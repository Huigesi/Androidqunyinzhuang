package me.huigesi.androidqunyinzhuang.section6;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.huigesi.androidqunyinzhuang.R;

public class SurfaceViewActivity extends AppCompatActivity{

    private SurfaceViewTemplate mySurfaceView;
    private Canvas mCanvas;
    private SurfaceViewPaint mySurfaceViewPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        initView();
    }

    private void initView() {
        mySurfaceView = (SurfaceViewTemplate) findViewById(R.id.mySurfaceView);
        mySurfaceViewPaint = (SurfaceViewPaint) findViewById(R.id.mySurfaceViewPaint);
    }

}
