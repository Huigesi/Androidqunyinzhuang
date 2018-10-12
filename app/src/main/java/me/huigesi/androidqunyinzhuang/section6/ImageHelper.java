package me.huigesi.androidqunyinzhuang.section6;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.SeekBar;

import me.huigesi.androidqunyinzhuang.R;

public class ImageHelper implements SeekBar.OnSeekBarChangeListener {
    private ImageView img;
    private Bitmap bitmap;
    public static final int MID_VALUE=1;
    float mHue,mStaturation,mLum;

    public ImageHelper(ImageView img, Bitmap bitmap) {
        this.img = img;
        this.bitmap = bitmap;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekBarHue:
                mHue = (progress - MID_VALUE) * 1.0f / MID_VALUE * 180;
                break;
            case R.id.seekbarSaturation:
                mStaturation = progress * 1.0f / MID_VALUE;
                break;
            case R.id.seekbarLum:
                mLum=progress*1.0f/MID_VALUE;
                break;
        }
        img.setImageBitmap(ImageHelper.handleImageEffect(bitmap,mHue,mStaturation,mLum));
    }

    private static Bitmap handleImageEffect(Bitmap bitmap, float hue, float staturation, float lum) {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0,hue);
        hueMatrix.setRotate(1,hue);
        hueMatrix.setRotate(2,hue);

        ColorMatrix staturationMatrix = new ColorMatrix();
        staturationMatrix.setSaturation(staturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imaMatrix = new ColorMatrix();
        imaMatrix.postConcat(hueMatrix);
        imaMatrix.postConcat(staturationMatrix);
        imaMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imaMatrix));
        canvas.drawBitmap(bmp, 0, 0, paint);
        return bmp;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
