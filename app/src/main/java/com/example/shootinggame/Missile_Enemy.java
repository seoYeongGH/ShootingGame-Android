package com.example.shootinggame;


import android.graphics.Bitmap;
import android.graphics.Point;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.R;

public class Missile_Enemy extends Missile {
    public static int speed_x=1;
    public static int speed_y=1;

    Point p ;//미사일의 크기를 저장
    public  Missile_Enemy(Bitmap bitmap) {
        super(bitmap);
    }

    public void update() {
        if (m_y > AppManager.getInstance().getresources().getDisplayMetrics().heightPixels) {
            state = STATE_OUT;
        }
        if( m_x>  AppManager.getInstance().getresources().getDisplayMetrics().widthPixels )
            state= STATE_OUT;
        else if(m_x<-p.x) state = STATE_OUT;

        m_BoundBox.set(m_x, m_y, m_x + p.x, m_y + p.y);
    }
}

