package com.example.shootinggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.GameView;
import com.example.teamprojectshootinggame.IState;
import com.example.teamprojectshootinggame.R;
import com.example.teamprojectshootinggame.SoundManager;

public class ModeState implements IState {
    //게임의 난이도별 상수 값
    public static int EASY = 1;
    public static int NORMAL = 2;
    public static int HARD = 3;

    Bitmap mode; //배경이미지
    Bitmap easy; //easy 버튼
    Bitmap normal; //normal 버튼
    Bitmap hard; //hard 버튼
    Bitmap back; //back 버튼

    Point p = new Point();

    @Override
    public void init() {
        //기기의 width, height 저장
        p.x = AppManager.getInstance().getDeviceSize().x;
        p.y = AppManager.getInstance().getDeviceSize().y;

        //각 이미지의 크기를 재설정
        mode = AppManager.getInstance().getBitmap(R.drawable.mode_background);
        mode = Bitmap.createScaledBitmap(mode, p.x, p.y,true);

        easy = AppManager.getInstance().getBitmap(R.drawable.easy);
        easy = Bitmap.createScaledBitmap(easy,p.x/2,p.y/8,true);

        back = AppManager.getInstance().getBitmap(R.drawable.back);
        back = Bitmap.createScaledBitmap(back,p.x/2,p.y/8,true);

        normal = AppManager.getInstance().getBitmap(R.drawable.normal);
        normal = Bitmap.createScaledBitmap(normal,p.x/2,p.y/8,true);

        hard = AppManager.getInstance().getBitmap(R.drawable.hard);
        hard = Bitmap.createScaledBitmap(hard,p.x/2,p.y/8,true);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawBitmap(mode, 0, 0, null);
        canvas.drawBitmap(easy, p.x/4, (p.y/8) * 4-175, null);
        canvas.drawBitmap(normal, p.x/4, (p.y/8) * 5-150, null);
        canvas.drawBitmap(hard,p.x/4,(p.y/8) * 6-125 , null);
        canvas.drawBitmap(back, p.x/4, (p.y/8) * 7 - 100, null);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //터치가 발생한 x,y좌표
        int x = (int)event.getX();
        int y = (int)event.getY();

        //각 메뉴 버튼에 해당하는 범위 설정
        Rect r_easy = new Rect(p.x/4,(p.y/8) * 4 - 175,(p.x/4)+p.x/2,(p.y/8)*4 - 175 + (p.y/8));
        Rect r_normal = new Rect(p.x/4,(p.y/8) * 5 - 150,(p.x/4)+p.x/2,(p.y/8)*5 - 150 + (p.y/8));
        Rect r_hard = new Rect(p.x/4,(p.y/8) * 6 - 125,(p.x/4)+p.x/2,(p.y/8)*6 - 125 + (p.y/8));
        Rect r_back = new Rect(p.x/4,(p.y/8)*7-100,(p.x/4) + (p.x/2),(p.y/8)*7 - 100 + (p.y/8));

        if(event.getAction() == MotionEvent.ACTION_DOWN){
           //게임을 새로 시작하므로 스테이지와 점수 초기화
            GameView.stage = 1;
            GameView.gameScore = 0;

            if(r_easy.contains(x,y)){
                SoundManager.getInstance().play(1);
                AppManager.getInstance().m_gameState = new GameStateEasy();
                AppManager.getInstance().getGameView().pushState(new Gaming(EASY,1));
            }
            else if(r_normal.contains(x,y)){
                SoundManager.getInstance().play(1);
                AppManager.getInstance().m_gameState = new GameStateNormal();
                AppManager.getInstance().getGameView().pushState(new Gaming(NORMAL,1));
            }
            else if (r_hard.contains(x, y))
            {
                SoundManager.getInstance().play(1);
                AppManager.getInstance().m_gameState = new GameStateHard();
                AppManager.getInstance().getGameView().pushState(new Gaming(HARD,1));
            }
            else if(r_back.contains(x,y)) //Back을 선택한 경우
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    SoundManager.getInstance().play(1);
                    AppManager.getInstance().getGameView().popState(); //뒤로가기
                }
            }
        }
        return false;
    }
}
