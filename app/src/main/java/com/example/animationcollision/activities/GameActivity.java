package com.example.animationcollision.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import android.os.Bundle;
import android.view.Window;

import com.example.animationcollision.R;
import com.example.animationcollision.view.AnimationView;

public class GameActivity extends AppCompatActivity {

    private AnimationView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();


        //Hide System UI
        WindowInsetsControllerCompat windowInsetsController = ViewCompat.getWindowInsetsController(getWindow().getDecorView());
        if (windowInsetsController == null) {
            return;
        }

        windowInsetsController.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );

        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsController.hide(WindowInsetsCompat.Type.captionBar());
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars());

        gameView = new AnimationView(this);
        setContentView(gameView);
    }
}