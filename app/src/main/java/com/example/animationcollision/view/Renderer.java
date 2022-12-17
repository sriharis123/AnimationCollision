package com.example.animationcollision.view;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import java.util.Timer;
import java.util.TimerTask;

public class Renderer {

    private RenderTask task;
    private Timer t;
    private int fps;

    public Renderer(SurfaceHolder surfaceHolder, CustomView view, int fps) {
        t = new Timer();
        task = new RenderTask(surfaceHolder, view);
        this.fps = fps;
    }

    public void startRender() {
        t.scheduleAtFixedRate(task, 0, (long)(1.0 / fps * 1000));
    }

    public void pauseRender() {

    }

    public void stopRender() {
        t.cancel();
    }

    public class RenderTask extends TimerTask {

        @NonNull private final SurfaceHolder surfaceHolder;
        @NonNull private final CustomView view;

        public Canvas canvas = null;

        public RenderTask(@androidx.annotation.NonNull SurfaceHolder surfaceHolder, @androidx.annotation.NonNull CustomView view) {
            super();
            this.surfaceHolder = surfaceHolder;
            this.view = view;
        }

        @Override
        public void run() {
            try {
                canvas = this.surfaceHolder.lockCanvas(); //We are trying to draw on the canvas, let's lock it so other stuff can't try to draw on it.
                synchronized (surfaceHolder) { //Prevents thread interference, only one thread access surfaceHolder.
                    //Update and draw the frame
                    this.view.update();
                    this.view.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
                //Log.e(TAG, "Error while locking canvas", e);
            }
            finally {
                if (canvas!=null) {
                    try {
                        //Lets display the frame, and unlock the canvas
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        //Log.e(TAG, "Error while unlocking canvas", e);
                    }
                }
            }
        }
    }
}
