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

public class PlayerSelect implements IState {
    Bitmap background;

    Bitmap player;
    Bitmap player2;
    Bitmap player3;
    Bitmap enemy1; //적과 같은 이미지로도 플레이 가능
    Bitmap enemy2;
    Bitmap enemy3;

    Bitmap select;

    Point p = new Point();
    @Override
    public void init() {
        //기기의 width,height
        p.x = AppManager.getInstance().getDeviceSize().x;
        p.y = AppManager.getInstance().getDeviceSize().y;

        //각 이미지 로드 후 사이즈 재설정
        background = AppManager.getInstance().getBitmap(R.drawable.select_background);
        background = Bitmap.createScaledBitmap(background, p.x,p.y,true);

        player = AppManager.getInstance().getBitmap(R.drawable.player_select);
        player = Bitmap.createScaledBitmap(player, p.x/4, p.y/4,true);

        player2 = AppManager.getInstance().getBitmap(R.drawable.player2_select);
        player2 = Bitmap.createScaledBitmap(player2, p.x/4, p.y/4,true);

        player3 = AppManager.getInstance().getBitmap(R.drawable.player3_select);
        player3 = Bitmap.createScaledBitmap(player3, p.x/4, p.y/4,true);


        enemy1 = AppManager.getInstance().getBitmap(R.drawable.enemy1_select);
        enemy1 = Bitmap.createScaledBitmap(enemy1, p.x/4, p.y/4,true);

        enemy2 = AppManager.getInstance().getBitmap(R.drawable.enemy2_select);
        enemy2 = Bitmap.createScaledBitmap(enemy2, p.x/4, p.y/4,true);

        enemy3 = AppManager.getInstance().getBitmap(R.drawable.enemy5_select);
        enemy3 = Bitmap.createScaledBitmap(enemy3, p.x/4, p.y/4,true);

        select = player; //플레이어 기본 상태
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
        canvas.drawBitmap(select,p.x/2-p.x/8,p.y/2-p.y/8,null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Gaming gaming = AppManager.getInstance().getGaming();

        int x = (int)event.getX();
        int y = (int)event.getY();

        Rect r_right = new Rect(p.x-p.x/4,0,p.x,p.y);
        Rect r_left = new Rect(0,0,p.x/4,p.y);
        Rect r_mid = new Rect(p.x/4,0,p.x-p.x/4,p.y);

        //기기의 좌,우를 터치하면 캐릭터의 전환이 발생하고, 기기의 가운데를 터치하면 캐릭터를 선택할 수 있음
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if(r_left.contains(x,y)){
                if(gaming.playerSelect>0) {
                    SoundManager.getInstance().play(1);
                    gaming.playerSelect--;
                }
                else if(gaming.playerSelect<=0) {
                    SoundManager.getInstance().play(1);
                    gaming.playerSelect = 5;
                }
            }else if(r_right.contains(x,y)){
                if(gaming.playerSelect<5) {
                    SoundManager.getInstance().play(1);
                    gaming.playerSelect++;
                }
                else if(gaming.playerSelect>=5) {
                    SoundManager.getInstance().play(1);
                    gaming.playerSelect = 0;
                }
            }else if(r_mid.contains(x,y)){
                SoundManager.getInstance().play(1);
                AppManager.getInstance().getGameView().popState();
            }
        }

        //playerSelect 값에 따라 캐릭터 변경
        if(gaming.playerSelect == 0){
            select = player;
        }else if(gaming.playerSelect == 1)
        {
            select = player2;
        }else if(gaming.playerSelect == 2){
            select = player3;
        }else if(gaming.playerSelect == 3){
            select = enemy1;
        }else if(gaming.playerSelect == 4){
            select = enemy2;
        }else if(gaming.playerSelect == 5){
            select = enemy3;
        }

        return false;
    }
}
