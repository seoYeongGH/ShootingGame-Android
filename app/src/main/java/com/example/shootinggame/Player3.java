package com.example.shootinggame;

import android.graphics.Bitmap;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.SoundManager;

public class Player3 extends Player {

    public Player3(Bitmap bitmap) {
        super(bitmap);

        p.x = bitmap.getWidth()/4; //플레이어의 width 값 얻음.
        p.y= bitmap.getHeight(); // 플레이어의 height 값 얻음.
        this.initSpriteData(p.x,p.y,12,4); // p.x = 프레임수 만큼 일정하게 나눈 값 전달.
        this.setPosition(400,1400); //위치 설정

        m_Life = 10;
    }

    public void makeMissile(int missileTime){
        if (System.currentTimeMillis() - missileShoot>= missileTime) {
            SoundManager.getInstance().play(4);

            AppManager.getInstance().getGaming().m_pmslist.add(new Missile_Player3(m_x,m_y));
            missileShoot = System.currentTimeMillis();
        }
    }

    @Override
    public void update(long gameTime) {
        super.update(gameTime);
    }
}
