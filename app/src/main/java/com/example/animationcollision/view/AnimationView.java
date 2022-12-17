package com.example.animationcollision.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.example.animationcollision.state.Ball;
import com.example.animationcollision.state.Drawable;
import com.example.animationcollision.state.Moveable;
import com.example.animationcollision.state.Wall;
import com.example.animationcollision.util.CollisionDynamics;

import java.util.ArrayList;
import java.util.HashSet;


public class AnimationView extends CustomView {

    private int wallOffset = 50, wallSize = 40;
    private ArrayList<Wall> walls = new ArrayList<>(4);
    private HashSet<Drawable> collided = new HashSet<>(drawables.size());
    private long startTime = System.currentTimeMillis(), threshold = 1000;

    public AnimationView(Context context) {
        super(context, 30);
        this.context = context;

        getHolder().addCallback(this);
        setFocusable(true);
        setAlpha(0);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawRGB(255, 255, 255);
        if (canvas != null) {
            for (Drawable item: drawables) {
                item.draw(canvas);
            }
        }
    }

    @Override
    public void update() {
        for (Drawable item: drawables) {
            if (item instanceof Moveable) ((Moveable)(item)).update();
        }
        if (System.currentTimeMillis() - startTime > 100)
            applyDynamics();
    }

    public void applyDynamics() {
        collided.clear();
        for (int i = 0; i < drawables.size(); i++) {
            Drawable d = drawables.get(i);
            if (d instanceof Wall) continue;
            if (d instanceof Ball) {
                Ball b = (Ball)(d);
                for (Wall w : walls) {
                    if (CollisionDynamics.wallCollisionDynamics(w, b)) {
                        collided.add(d);
                        break;
                    }
                }
                if (collided.contains(d)) continue;
                for (int j = i + 1; j < drawables.size(); j++) {
                    Drawable e = drawables.get(j);
                    if (e instanceof Ball && CollisionDynamics.ballCollisionDynamics(b, (Ball)(e))) {
                        collided.add(d);
                        collided.add((Drawable)e);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        super.surfaceCreated(surfaceHolder);

        walls.add(new Wall(wallOffset,wallOffset,WIDTH_PX - wallOffset,wallSize + wallOffset, Color.BLACK));
        walls.add(new Wall(wallOffset,wallOffset,wallOffset + wallSize,HEIGHT_PX - wallOffset, Color.BLACK));
        walls.add(new Wall(wallOffset,HEIGHT_PX - wallSize - wallOffset,WIDTH_PX - wallOffset,HEIGHT_PX - wallOffset, Color.BLACK));
        walls.add(new Wall(WIDTH_PX - wallSize - wallOffset,wallOffset,WIDTH_PX - wallOffset,HEIGHT_PX - wallOffset, Color.BLACK));

        drawables.addAll(walls);
        drawables.add(new Ball(WIDTH_PX / 2, HEIGHT_PX / 2, 50, Color.RED, 235, 20));
        drawables.add(new Ball(WIDTH_PX / 2, HEIGHT_PX / 2, 50, Color.RED, 342, 20));
        drawables.add(new Ball(WIDTH_PX / 2, HEIGHT_PX / 2, 50, Color.RED, 34, 20));
        drawables.add(new Ball(WIDTH_PX / 2, HEIGHT_PX / 2, 50, Color.RED, 133, 20));
        drawables.add(new Ball(WIDTH_PX / 2, HEIGHT_PX / 2, 50, Color.RED, 120, 20));
        drawables.add(new Ball(WIDTH_PX / 2, HEIGHT_PX / 2, 50, Color.RED, 9, 20));
        drawables.add(new Ball(WIDTH_PX / 2, HEIGHT_PX / 2, 50, Color.RED, 53, 20));
        drawables.add(new Ball(WIDTH_PX / 2, HEIGHT_PX / 2, 50, Color.RED, 253, 20));
        drawables.add(new Ball(WIDTH_PX / 2, HEIGHT_PX / 2, 50, Color.RED, 32, 20));
        drawables.add(new Ball(WIDTH_PX / 2, HEIGHT_PX / 2, 50, Color.RED, 85, 20));
        drawables.add(new Ball(WIDTH_PX / 2, HEIGHT_PX / 2, 50, Color.RED, 220, 20));
        drawables.add(new Ball(WIDTH_PX / 2, HEIGHT_PX / 2, 50, Color.RED, 183, 20));

        renderer.startRender();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {}

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        renderer.stopRender();
    }


}
