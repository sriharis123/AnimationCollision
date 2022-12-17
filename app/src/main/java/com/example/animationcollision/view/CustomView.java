package com.example.animationcollision.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.animationcollision.state.Drawable;

import java.util.ArrayList;

public abstract class CustomView extends SurfaceView implements SurfaceHolder.Callback {

    protected Context context;
    protected Renderer renderer;

    protected int HEIGHT_PX;
    protected int WIDTH_PX;

    protected ArrayList<Drawable> drawables = new ArrayList<>();

    public CustomView(Context context) {
        super(context);
        renderer = new Renderer(getHolder(), this, 30);
    }

    public CustomView(Context context, int fps) {
        super(context);
        renderer = new Renderer(getHolder(), this, fps);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        Rect dimensions = surfaceHolder.getSurfaceFrame();
        WIDTH_PX = dimensions.right;
        HEIGHT_PX = dimensions.bottom;
    }

    public abstract void update();
}
