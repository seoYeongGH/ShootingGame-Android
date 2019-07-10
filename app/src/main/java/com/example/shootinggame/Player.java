package com.example.shootinggame;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.teamprojectshootinggame.SpriteAnimation;

public class Player extends SpriteAnimation {
    protected  int missileTime = 700;//미사일 발사 간격(0.7초로 초기화)
    protected long missileShoot = System.currentTimeMillis();//마지막으로 미사일을 발사한 시간

    Point p = new Point(); //플레이어의 width,height 값
    Rect m_BoundBox = new Rect();

    protected int m_Life; //플레이어 생명력

    public Player(Bitmap bitmap) {
        super(bitmap);
    }

    public int getLife( ) { return m_Life; }
    public void addLife( ) { m_Life++; }
    public void destroyPlayer( ) { m_Life--; }

    public void reudeceMissileTime(){ //미사일 속도를 감소시키는 아이템을 획득했을 경우
        missileTime -= 70;
    }

    public void makeMissile(int missileTime){}//플레이어마다 다른 미사일 발사

    @Override
    public void update(long gameTime) {
        m_BoundBox.set(m_x,m_y,m_x+p.x,m_y+p.y);
        makeMissile(missileTime);
        super.update(gameTime);
    }
}
