package com.sayan.sample.sampleviewanimation;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private RelativeLayout mRoot;
    private float xDelta;
    private float yDelta;
    private ImageView humanFaceImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRoot = (RelativeLayout) findViewById(R.id.root);
        ImageView shirtImage = findViewById(R.id.shirtImage);
        humanFaceImage = findViewById(R.id.humanFaceImage);
        shirtImage.setOnTouchListener(this);
    }

    public boolean onTouch(View view, MotionEvent event) {
//        final int EVENT_X = (int) event.getX();
//        final int EVENT_Y = (int) event.getY();
//        final int VIEW_X = (int) view.getX();
//        final int VIEW_Y = (int) view.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDelta = view.getX() - event.getRawX();
                yDelta = view.getY() - event.getRawY();
                view.performClick();
                return true;
            case MotionEvent.ACTION_MOVE:
                view.animate()
                        .x(event.getRawX() + xDelta)
                        .y(event.getRawY() + yDelta)
                        .setDuration(0)
                        .start();
                return true;
            default:
                return false;
        }
//        Log.i("EVENT", "EVENT_X = " + EVENT_X + ", EVENT_Y = " + EVENT_Y);
//        Log.d("VIEW", "VIEW_X = " + VIEW_X + ", VIEW_Y = " + VIEW_Y);
    }

    private void onClickCameraButton(){
        new CameraPicProvider(
                this,
                false,false,
                new CameraPicProvider.GetBitmapListener() {

                    @Override
                    public void onGetBitmap(Bitmap bitmapImage, String filepath) {
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                        humanFaceImage.setImageBitmap(bitmapImage);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.cameraButton) {
            onClickCameraButton();
        }
        return super.onOptionsItemSelected(item);
    }
}
