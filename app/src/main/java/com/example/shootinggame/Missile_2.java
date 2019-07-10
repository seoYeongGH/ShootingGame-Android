package com.example.shootinggame;


import android.graphics.Point;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.R;

public class Missile_2 extends Missile_Enemy {//복어 미사일
    private int type;//복어의 움직임 movetype을 저장

    public  Missile_2(int x, int y, int _type) {
        super(AppManager.getInstance().getBitmap(R.drawable.missile_3));

        p = new Point();
        p.x = AppManager.getInstance().getBitmap(R.drawable.missile_3).getWidth();
        p.y = AppManager.getInstance().getBitmap(R.drawable.missile_3).getHeight();

        this.setPosition(x+25, y+105);

        type = _type;
    }

    public void update() {
        super.update();

        //복어의 대각선 이동 경로와 반대 방향 대각선으로 미사일 발사
        if(type == 0){
            m_x -= 10;
            m_y += 10;
        }
        else if(type == 1){
            m_x += 10;
            m_y += 10;
        }

    }
}