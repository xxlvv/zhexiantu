package com.bw.day1114_zdyzxt_demo1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Copyright (C)
 * <p>
 * FileName: MyLineView
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/14 13:43
 * <p>
 * Description:
 */
public class MyLineView extends View {

    private int width;//宽
    private int height;//高
    private Paint linePaint;//线条画笔
    private Paint textPaint;//横坐标画笔
    private Paint pointPaint;//点的坐标
    private Context context;

    public MyLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        //画笔X轴
        linePaint = new Paint();
        //设置画笔样式
        linePaint.setStyle(Paint.Style.FILL);
        //设置画笔颜色
        linePaint.setColor(Color.RED);
        //设置抗锯齿
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth((float) 2.0);

        //文字画笔
        textPaint = new Paint();
        //设置画笔样式
        textPaint.setStyle(Paint.Style.FILL);
        //设置画笔颜色
        textPaint.setColor(Color.RED);
        //设置抗锯齿
        textPaint.setAntiAlias(true);
        //设置文字大小
        textPaint.setTextSize(ScreenUtils.getDptoPx(context, 10));

        //文字画笔
        pointPaint = new Paint();
        //设置画笔样式
        textPaint.setStyle(Paint.Style.FILL);
        //设置画笔颜色
        textPaint.setColor(Color.RED);
        //设置抗锯齿
        textPaint.setAntiAlias(true);
        //设置文字大小
        textPaint.setTextSize(ScreenUtils.getDptoPx(context, 20));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量宽高
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        //设置测量尺寸
        setMeasuredDimension(width, height);
    }

    //定义一个二维数组
    protected float[][] points = new float[][]{{1, 10}, {2, 47}, {3, 11}, {4, 38}, {5, 9}, {6, 52}, {7, 14}, {8, 37}, {9, 29}, {10, 31}};

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //平移坐标原点
        canvas.translate(50, height - 50);

        //有多少条数据，就把X轴分成多少份
        drawLineXAxis(canvas);
        drawLineYAxis(canvas);
        drawLinePoints(canvas);
    }

    float pointX = 0;
    float pointY = 0;

    //画线点
    private void drawLinePoints(Canvas canvas) {
        float pointXTemp = 0;
        float pointYTemp = 0;

        for (int i = 0; i < points.length; i++) {
            float temp = points[i][0] % points.length;

            if (temp == 0) {
                pointX = 0 + (points[i][0]) * ((width - 100) / points.length);
            } else {
                pointX = 0 + (points[i][0] % points.length) * ((width - 100) / points.length);
            }

            pointY = 0 - (points[i][1] / 60) * ((height - 100));
            //画圆点
            canvas.drawCircle(pointX, pointY, 5, pointPaint);
            canvas.drawText(i + 1 + "", pointX - 10, pointY - 10, pointPaint);
            canvas.drawText("(" + ((int) points[i][0] + "," + (int) points[i][1]) + ")", pointX - 20, pointY - 20, textPaint);

            if (i != 0) {
                canvas.drawLine(pointXTemp, pointYTemp, pointX, pointY, linePaint);
            }
            pointXTemp = pointX;
            pointYTemp = pointY;
        }
    }

    //画线Y轴
    private void drawLineYAxis(Canvas canvas) {
        int startX = 0;
        int startY = 0;
        int spaceing = (height - 100) / points.length;

        //每次画一小段
        for (int i = 0; (startY + spaceing * i) < height - 50; i++) {
            //画线
            canvas.drawLine(startX, startY, startX, startY - spaceing * i, linePaint);
            //画圆点
            canvas.drawCircle(startX, startY - spaceing * i, 5, linePaint);
            //绘制文本
            canvas.drawText(6 * i + "", startX - 30, startY - spaceing * i, textPaint);
        }
    }

    //画线X轴
    private void drawLineXAxis(Canvas canvas) {
        int startX = 0;
        int startY = 0;
        int spaceing = (width - 100) / points.length;

        //每次画一小段
        for (int i = 0; (startX + spaceing * i) < width - 50; i++) {
            //画线
            canvas.drawLine(startX, startY, startX + spaceing * i, startY, linePaint);
            //画圆点
            canvas.drawCircle(startX + spaceing * i, startY, 5, linePaint);
            //绘制文本
            canvas.drawText(i + 0 + "", startX + spaceing * i, startY + 30, textPaint);
        }
    }
}
