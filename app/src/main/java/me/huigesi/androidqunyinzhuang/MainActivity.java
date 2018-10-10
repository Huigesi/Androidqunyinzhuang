package me.huigesi.androidqunyinzhuang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.huigesi.androidqunyinzhuang.section5.DragViewGroup;

public class MainActivity extends AppCompatActivity {

    private DragViewGroup dragViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);/*
        mTopBar.setOnTopbarClickListener(new topbarClickListener() {
            @Override
            public void leftClick() {

            }

            @Override
            public void rightClick() {

            }
        });*/
        initView();
    }

    private void initView() {
        dragViewGroup = (DragViewGroup) findViewById(R.id.dragViewGroup);
    }
}
