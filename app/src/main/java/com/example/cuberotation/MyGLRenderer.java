package com.example.cuberotation;

import static javax.microedition.khronos.opengles.GL10.GL_DEPTH_TEST;
import static javax.microedition.khronos.opengles.GL10.GL_LEQUAL;
import static javax.microedition.khronos.opengles.GL10.GL_PERSPECTIVE_CORRECTION_HINT;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {

    private PhotoCube cube;
    private static float anglecube = 0;
    private static float speedcube = 0.3f;

    public MyGLRenderer(Context context){
        cube = new PhotoCube(context);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        gl10.glClearColor(0.0f,0.0f,0.0f,1.0f);
        gl10.glClearDepthf(1.0f);
        gl10.glEnable(GL10.GL_DEPTH_TEST);
        gl10.glDepthFunc(GL10.GL_LEQUAL);
        gl10.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_NICEST);
        gl10.glShadeModel(GL10.GL_SMOOTH);
        gl10.glDisable(GL10.GL_DITHER);

        cube.loadTexture(gl10);
        gl10.glEnable(GL10.GL_TEXTURE_2D);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int i, int i1) {
        if (i1 == 0) i1 = 1;   // To prevent divide by zero
        float aspect = (float)i / i1;

        // Set the viewport (display area) to cover the entire window
        gl.glViewport(0, 0, i, i1);

        // Setup perspective projection, with aspect ratio matches viewport
        gl.glMatrixMode(GL10.GL_PROJECTION); // Select projection matrix
        gl.glLoadIdentity();                 // Reset projection matrix
        // Use perspective projection
        GLU.gluPerspective(gl, 65, aspect, 0.1f, 100.f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);  // Select model-view matrix
        gl.glLoadIdentity();                 // Reset

        // You OpenGL|ES display re-sizing code here
        // ......
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // Clear color and depth buffers
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // ----- Render the Cube -----
        gl.glLoadIdentity();                  // Reset the model-view matrix
        gl.glTranslatef(0.0f, 0.0f, -6.0f);   // Translate into the screen
        gl.glRotatef(anglecube, 0.15f, 1.0f, 0.3f); // Rotate
        cube.draw(gl);

        // Update the rotational angle after each refresh.
        anglecube += speedcube;
    }
}
