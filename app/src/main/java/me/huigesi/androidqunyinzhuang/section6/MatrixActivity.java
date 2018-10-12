package me.huigesi.androidqunyinzhuang.section6;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import me.huigesi.androidqunyinzhuang.R;

public class MatrixActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageview;
    private GridLayout group;
    private Button brnChange;
    private Button btnReset;
    private Bitmap mBitmap;
    private int mEtWidth,mEtHeight;
    private EditText[] mEts=new EditText[20];
    private float[] mColorMatrix=new float[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        initView();
    }

    private void initView() {
        imageview = (ImageView) findViewById(R.id.imageview);
        group = (GridLayout) findViewById(R.id.group);
        brnChange = (Button) findViewById(R.id.brnChange);
        brnChange.setOnClickListener(this);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(this);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        imageview.setImageBitmap(mBitmap);
        group.post(new Runnable() {
            @Override
            public void run() {
                mEtHeight=group.getHeight()/4;
                mEtWidth=group.getWidth()/5;
                addEts();
                initMatrix();
            }
        });
    }

    private void addEts() {
        for (int i=0;i<20;i++) {
            EditText editText = new EditText(MatrixActivity.this);
            mEts[i]=editText;
            group.addView(editText, mEtWidth, mEtHeight);
        }
    }

    private void initMatrix() {
        for (int i=0;i<20;i++) {
            if (i % 6 == 0) {
                mEts[i].setText(String.valueOf(1));
            }else {
                mEts[i].setText(String.valueOf(0));
            }
        }
    }

    private void getMatrix() {
        for (int i=0;i<20;i++) {
            mColorMatrix[i] = Float.valueOf(mEts[i].getText().toString());
        }
    }

    private void setImageMatrix() {
        Bitmap bmp = Bitmap.createBitmap(mBitmap.getWidth(),
                mBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        android.graphics.ColorMatrix colorMatrix = new android.graphics.ColorMatrix();
        colorMatrix.set(mColorMatrix);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        imageview.setImageBitmap(bmp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.brnChange:
                getMatrix();
                setImageMatrix();
                break;
            case R.id.btnReset:
                initMatrix();
                getMatrix();
                setImageMatrix();
                break;
        }
    }
}
