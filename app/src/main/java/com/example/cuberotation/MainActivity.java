package com.example.cuberotation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView glview;
    private MediaPlayer mp;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.start1);
        glview = new GLSurfaceView(this);
        glview.setRenderer(new MyGLRenderer(this));
        mp = MediaPlayer.create(MainActivity.this, R.raw.sound);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(glview);
                try {
                    glview.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this, "What's That Sound!!!", Toast.LENGTH_SHORT).show();
                            getSupportActionBar().setTitle("What's That Sound!!!");
                            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(androidx.cardview.R.color.cardview_dark_background)));
                        }
                    });
                } catch (IllegalStateException e){
                    e.printStackTrace();
                }

//                Intent i = new Intent(MainActivity.this,MyGLActivity.class);
//                startActivity(i);
//                finish();
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