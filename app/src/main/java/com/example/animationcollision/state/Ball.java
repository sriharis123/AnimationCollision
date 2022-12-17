package com.example.animationcollision.state;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.security.InvalidParameterException;

public class Ball implements Drawable, Moveable {

    private double centerX, centerY;
    private int radius, color;
    private long collisionTime;

    private Drawable prevCollision = null;

    private double angle, speed;

    public Ball(double centerX, double centerY, int radius, int color, double startAngle, double startSpeed) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.color = color;

        if (startAngle >= 360 || startAngle < 0) throw new InvalidParameterException("Angle must be [0, 360) degrees");
        if (startSpeed < 2 || startSpeed > 40) throw new InvalidParameterException("Speed must be [2, 10]");

        angle = startAngle;
        speed = startSpeed;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawCircle(Math.round(centerX), Math.round(centerY), radius, paint);
    }

    @Override
    public void update() {
        centerX += getVX();
        centerY -= getVY();
    }

    public double getVX() {
        return speed * Math.cos(Math.toRadians((double)angle));
    }

    public double getVY() {
        return speed * Math.sin(Math.toRadians((double)angle));
    }

    @Override
    public void setVelocity(double newAngle, double newSpeed) {
        while (newAngle >= 360) newAngle -= 360;
        while (newAngle < 0) newAngle += 360;
        angle = newAngle;
        speed = newSpeed;
    }

    public double getCenterX() {
        return centerX;
    }
    public double getCenterY() {
        return centerY;
    }
    public int getRadius() {
        return radius;
    }
    public double getAngle() {
        return angle;
    }
    public double getSpeed() {
        return speed;
    }

    public void setAngle(double a) {
        angle = a;
    }
    public void setSpeed(double s) {
        speed = s;
    }

    public Drawable getPrevCollision() {
        return prevCollision;
    }
    public void setPrevCollision(Drawable prevCollision) {
        collisionTime = System.currentTimeMillis();
        this.prevCollision = prevCollision;
    }
    public long getCollisionTime() {
        return collisionTime;
    }

}
