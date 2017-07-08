package com.sample.opengl;

import android.content.res.Configuration;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GLSurfaceView mGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout data_container = (LinearLayout) findViewById(R.id.data_container);
        Button buttonCircle = (Button) findViewById(R.id.buttonCircle);
        Button buttonSquare = (Button) findViewById(R.id.buttonSquare);
        Button buttonTriangle = (Button) findViewById(R.id.buttonTriangle);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLView = new MyGLSurfaceView(this);
        data_container.addView(mGLView);
        ((MyGLSurfaceView)mGLView).setCurrentSelection(selectedItem);
        buttonTriangle.setOnClickListener(this);
        buttonSquare.setOnClickListener(this);
        buttonCircle.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // The following call pauses the rendering thread.
        // If your OpenGL application is memory intensive,
        // you should consider de-allocating objects that
        // consume significant memory here.
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // The following call resumes a paused rendering thread.
        // If you de-allocated graphic objects for onPause()
        // this is a good place to re-allocate them.
        mGLView.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("selectedItem", ((MyGLSurfaceView)mGLView).selectedItem);
        super.onSaveInstanceState(outState);

    }

    int selectedItem=1;
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        selectedItem = savedInstanceState.getInt("selectedItem");
        ((MyGLSurfaceView) mGLView).setCurrentSelection(selectedItem);
        super.onRestoreInstanceState(savedInstanceState);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSquare:
                ((MyGLSurfaceView) mGLView).setCurrentSelection(MyGLSurfaceView.SELECTIED_SQUARE);
                break;
            case R.id.buttonCircle:
                ((MyGLSurfaceView) mGLView).setCurrentSelection(MyGLSurfaceView.SELECTIED_CIRCLE);
                break;
            case R.id.buttonTriangle:
                ((MyGLSurfaceView) mGLView).setCurrentSelection(MyGLSurfaceView.SELECTIED_TRIANGLE);
                break;
        }
    }
}
