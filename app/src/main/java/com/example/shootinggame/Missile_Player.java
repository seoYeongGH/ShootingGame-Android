package com.example.shootinggame;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Missile_Player extends Missile {
    Point missileSize;

    public Missile_Player(Bitmap bitmap) {
        super(bitmap);
    }

    public void update()
    {
        m_y-=80; //플레이어 미사일의 이동

        if(m_y < -missileSize.y)
            state = STATE_OUT;

        m_BoundBox.set(m_x,m_y,m_x+missileSize.x,m_y+missileSize.y);
    }
}
