package me.huigesi.androidqunyinzhuang.section6;

import android.os.Bundle;
import android.se.omapi.SEService;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import me.huigesi.androidqunyinzhuang.R;

public class ImageColorMatrixActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private ImageView img;
    private SeekBar seekBarHue;
    private SeekBar seekbarSaturation;
    private SeekBar seekbarLum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_color_matrix);
        initView();
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        seekBarHue = (SeekBar) findViewById(R.id.seekBarHue);
        seekbarSaturation = (SeekBar) findViewById(R.id.seekbarSaturation);
        seekbarLum = (SeekBar) findViewById(R.id.seekbarLum);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
