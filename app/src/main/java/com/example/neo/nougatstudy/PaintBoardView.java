package com.example.neo.nougatstudy;

import android.content.Context;
import android.graphics.Bitmap;
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

public class PaintBoardView extends View {
    Paint paint;
    Bitmap mBitmap;
    Canvas mCanvas;

    float oldx;
    float oldy;

    public PaintBoardView(Context context) {
        super(context);
        init(context);
    }

    public PaintBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context){
        paint = new Paint();
        paint.setColor(Color.BLACK);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap);
        mCanvas.drawColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    public void setColor(int color){
        paint.setColor(color);
    }

    public void setLineWidth(float lineWidth){
        paint.setStrokeWidth(lineWidth);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        int curx =  (int)event.getX();
        int cury = (int)event.getY();

        if (action == MotionEvent.ACTION_DOWN) {
            oldx = curx;
            oldy = cury;

        } else if (action == MotionEvent.ACTION_MOVE) {
            if (oldx > 0 || oldy > 0) {
                mCanvas.drawLine(oldx, oldy, curx, cury, paint);
            }

            oldx = curx;
            oldy = cury;
        } else if (action == MotionEvent.ACTION_UP) {

        }

        invalidate(); //화면에 다시 그려라.
        return true;

    }
}
