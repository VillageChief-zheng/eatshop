package com.stateunion.eatshop.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.stateunion.eatshop.R;

/**
 * Created by zhangguozheng on 2017/11/28.
 */

public class RedPointRadioButton extends android.support.v7.widget.AppCompatRadioButton{


    /**
     * x距离中心点偏差
     */
    private float cx_offset;

    /**
     * y中心点
     */
    private float cy_offset;
    private float density;

    /**
     * 半径 dp
     */
    private float radius = 3;

    /**
     * 圆圈颜色
     */
    private int backgroundColor;

    private boolean isDraw = false;

    public RedPointRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        density = context.getResources().getDisplayMetrics().density;

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.bm);
        backgroundColor = a.getColor(R.styleable.bm_red_bg, Color.RED);
        a.recycle();

        cx_offset = -18 * density;
        cy_offset = radius * density;


    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (isDraw) {
            Paint paint = new Paint();
            paint.setColor(backgroundColor);
            paint.setAntiAlias(true);

            TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
            textPaint.density = density;
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setAntiAlias(true);

            float cx = this.cx_offset + getWidth();
            float cy = this.cy_offset;

            canvas.drawCircle(cx, cy, radius * density, paint);
        }
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void setIsDraw(boolean isDraw) {
        this.isDraw = isDraw;
    }
}
