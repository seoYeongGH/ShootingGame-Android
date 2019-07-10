package com.example.shootinggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.GameView;
import com.example.teamprojectshootinggame.IState;
import com.example.teamprojectshootinggame.R;
import com.example.teamprojectshootinggame.SoundManager;


public class GameOverState implements IState {
    Bitmap gameOver;
    Point p = new Point();
    @Override
    public void init() {

        p.x = AppManager.getInstance().getDeviceSize().x;
        p.y = AppManager.getInstance().getDeviceSize().y;
        gameOver = AppManager.getInstance().getBitmap(R.drawable.game_over);
        gameOver = Bitmap.createScaledBitmap(gameOver, p.x, p.y,true);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawBitmap(gameOver,0,0,null);
        Paint paint = new Paint( );
        paint.setTextSize(100);
        paint.setColor(Color. BLACK);
        paint.setFakeBoldText(true);
        canvas.drawText("점수 : " + String.valueOf(GameView.gameScore * GameView.stage),p.x - (p.x/2 +200),p.y - p.y/4,paint);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //터치 이벤트의 x,y좌표
        int x = (int)event.getX();
        int y = (int)event.getY();


        Rect r_retry = new Rect(0,0,p.x/2,p.y);
        Rect r_main = new Rect(p.x/2,0,p.x,p.y);

        //화면의 왼쪽을 터치하면 난이도 선택, 오른쪽을 터치하면 처음시작화면으로 이동
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            SoundManager.getInstance().play(6);
            if(r_main.contains(x,y)){
                AppManager.getInstance().getGameView().popState();
                AppManager.getInstance().getGameView().popState();
            }else if(r_retry.contains(x,y)){
                AppManager.getInstance().getGameView().popState();
            }
        }
        return false;
    }

}
