package com.example.shootinggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.GameView;
import com.example.teamprojectshootinggame.IState;
import com.example.teamprojectshootinggame.R;


public class GameNextState implements IState {

    Point p = new Point();
    Bitmap background;
    Bitmap gameClear;
    int level;

    public GameNextState(int level){
        this.level = level;
    }

    @Override
    public void init() {
        p.x = AppManager.getInstance().getDeviceSize().x;
        p.y = AppManager.getInstance().getDeviceSize().y;

        background = AppManager.getInstance().getBitmap(R.drawable.background_aqua);
        background = Bitmap.createScaledBitmap(background, AppManager.getInstance().getDeviceSize().x,
                AppManager.getInstance().getDeviceSize().y,true);
        gameClear = AppManager.getInstance().getBitmap(R.drawable.game_clear);
        gameClear = Bitmap.createScaledBitmap(gameClear, p.x,
                (p.y/2),true);

    }

    @Override
    public void destroy() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawBitmap(gameClear,0,200,null);
        Paint p = new Paint( );
        p.setTextSize(150);
        p.setColor(Color. BLACK);
        p.setFakeBoldText(true);
        canvas.drawText("점수 : "+String.valueOf(GameView.gameScore), 230, 1000, p);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if(event.getAction() == MotionEvent.ACTION_DOWN) {//터치시 다음 스테이지로 이동
            GameView.stage++;
            AppManager.getInstance().getGameView().changeGameState(new Gaming(level,GameView.stage));

        }
        return false;
    }
}
