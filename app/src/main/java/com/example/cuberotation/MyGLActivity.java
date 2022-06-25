package com.example.cuberotation;

import android.app.Activity;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

public class MyGLActivity extends Activity {

    private GLSurfaceView glview;
    private MediaPlayer mp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glview = new GLSurfaceView(this);
        glview.setRenderer(new MyGLRenderer(this));
        this.setContentView(glview);

        glview.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(MyGLActivity.this, R.raw.sound);
                mp.start();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        glview.onPause();
        mp.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glview.onResume();
    }
}
