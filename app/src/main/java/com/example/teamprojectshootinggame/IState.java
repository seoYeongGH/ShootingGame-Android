package com.example.teamprojectshootinggame;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

public interface IState {
    public void init();
    public void destroy();
    public void update();
    public void render(Canvas canvas);
    public boolean onKeyDown(int keyCode, KeyEvent event);
    public boolean onTouchEvent(MotionEvent event);
}
