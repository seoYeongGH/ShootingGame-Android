package com.example.shootinggame;

import android.graphics.Point;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.R;

public class Enemy_3 extends Enemy {//상어

    private int idleTime;//돌진하기전에 대기하는 시간

    Point p = new Point();

    public Enemy_3(int level) {
        super(AppManager.getInstance( ).getBitmap(R.drawable. enemy3));
        p.x = AppManager.getInstance().getBitmap(R.drawable.enemy3).getWidth()/3;
        p.y = AppManager.getInstance().getBitmap(R.drawable.enemy3).getHeight();
        this.initSpriteData(p.x,p.y, 6, 3);

        if(level == EASY)
            idleTime = 1500;
        else if(level == NORMAL)
            idleTime = 1200;
        else if(level == HARD)
            idleTime = 1000;

        score = 30;
        speed = 680f;

        EnemyMoved = System.currentTimeMillis();
    }

    public void move(){
        super.move();

        if (System.currentTimeMillis() - EnemyMoved >= idleTime) {//대기시간이 지나면 돌진
            m_y += speed;
        }

    }


    public void update( long GameTime) {
        m_BoundBox.set(m_x,m_y,m_x+p.x,m_y+p.y);
        super.update(GameTime);
    }
}

