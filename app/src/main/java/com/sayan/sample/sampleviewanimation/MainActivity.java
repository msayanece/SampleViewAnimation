package com.sayan.sample.sampleviewanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private RelativeLayout mRoot;
    private float xDelta;
    private float yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRoot = (RelativeLayout) findViewById(R.id.root);
        TextView text = findViewById(R.id.text);
        text.setOnTouchListener(this);
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
}
