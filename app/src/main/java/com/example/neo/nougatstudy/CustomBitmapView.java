package com.example.neo.nougatstudy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by neo on 2017-07-31.
 */

public class CustomBitmapView extends View {
    Bitmap mBitmap; //메모리에 이미지를 로딩용.
    Bitmap bitmap;  //이미지 로드용.

    public CustomBitmapView(Context context) {
        super(context);
        init(context);
    }

    public CustomBitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.facebook);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawBitmap(bitmap, 0, 0, null);  //화면에 보여주면서 이미지를 처리한다.
        canvas.drawBitmap(mBitmap, 0, 0, null); //더블 버퍼링 메모리에 있는 bitmap 을 보여준다.
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //뷰의 사이즈가 결정 되어 질때 호출 된다. 변경 될때도 호출 된다.
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        mCanvas.drawLine(100, 100, 400, 200, paint);

        mCanvas.drawBitmap(bitmap, 0, 0, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
