package com.example.neo.nougatstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by neo on 2017-07-27.
 */

public class CustomView extends View {
    Paint paint;    //이미지를 그릴때
    Matrix matrix;  //이미지를 확대 축소
    int w, h;
    ShapeDrawable drawable;

    public CustomView(Context context) {
        super(context);
        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        paint = new Paint();
        paint.setColor(Color.RED);
        matrix = new Matrix();

        WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);    //단만의 화면 크기를 알수 있다.
        Display display = manager.getDefaultDisplay();
        w = display.getWidth();
        h = display.getHeight();


        drawable = new ShapeDrawable();
        RectShape rect = new RectShape();   //사각형
        rect.resize(w, h/2.0f);
        drawable.setShape(rect);    //모양을 설정 할수 있다.
        drawable.setBounds(0, 0, w, h);

        LinearGradient gradient = new LinearGradient(0, 0, 0, h, Color.BLUE, Color.YELLOW, Shader.TileMode.CLAMP);
        Paint curPaint = drawable.getPaint();
        curPaint.setShader(gradient);//색이 점점 변형 되는, 즉 그라데이션 같은 기능.
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //View 가 화면에 표시되기 전에 호출 된다.
        super.onDraw(canvas);
        //canvas.drawRect(100, 100, 200, 200, paint);
        /*
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4.0f);
        paint.setColor(Color.GREEN);
        canvas.drawRect(10, 10, 100, 100, paint); //왼쪽, 오른쪽, 위, 아래

        paint.setStyle(Paint.Style.FILL);
        paint.setARGB(128, 0, 0, 255);
        canvas.drawRect(120, 10, 210, 100, paint);

        DashPathEffect dashEffect = new DashPathEffect(new float[]{5, 5}, 1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5.0f);//선의 굵기
        paint.setPathEffect(dashEffect);//점선
        paint.setColor(Color.GREEN);
        canvas.drawRect(120, 10, 210, 100, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40.f);
        canvas.drawText( "안녕하세요.", 20, 320, paint);
        */

        drawable.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            Toast.makeText(getContext(), "눌림", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
