package com.example.animationcollision.state;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.security.InvalidParameterException;

public class Wall implements Drawable {

    private int startX, startY, endX, endY, color;

    public Wall(int startX, int startY, int endX, int endY, int color) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(color);
        canvas.drawRect(startX, startY, endX, endY, paint);
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

}
