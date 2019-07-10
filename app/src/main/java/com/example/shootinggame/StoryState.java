package com.example.shootinggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.IState;
import com.example.teamprojectshootinggame.R;
import com.example.teamprojectshootinggame.SoundManager;

public class StoryState implements IState {
    Bitmap background;
    Point p = new Point(); //디바이스 사이즈 얻기 위한 Point

    @Override
    public void init() {
        p.x = AppManager.getInstance().getDeviceSize().x;
        p.y = AppManager.getInstance().getDeviceSize().y;

        background = AppManager.getInstance().getBitmap(R.drawable.story_background);
        background = Bitmap.createScaledBitmap(background,p.x,p.y,true);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawBitmap(background,0,0,null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        Rect r_story = new Rect(0,0,p.x,p.y);

        //화면을 터치하면 메인 화면으로 돌아감
        if(r_story.contains(x,y))
        {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                AppManager.getInstance().getGameView().popState();
                SoundManager.getInstance().play(1);
            }
        }
        return false;
    }
}
