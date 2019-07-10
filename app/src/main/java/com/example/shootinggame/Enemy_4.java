package com.example.shootinggame;

import android.graphics.Point;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.R;


public class Enemy_4 extends Enemy {//성게
    Point p = new Point();

    public Enemy_4() {
        super(AppManager.getInstance( ).getBitmap(R.drawable. enemy4));
        p.x = AppManager.getInstance().getBitmap(R.drawable.enemy4).getWidth()/2;
        p.y = AppManager.getInstance().getBitmap(R.drawable.enemy4).getHeight();
        this.initSpriteData(p.x,p.y, 2, 2);

        hp = 9999;
        score = 30;
        speed = 1.7f;//배경이 움직이는 속도와 같은 속도로 움직임(그 위치에 정지한것처럼 보이는 효과)
    }

    public void move(){
        super.move();

        m_y+=speed;
    }


    public void update(long GameTime)
    {
        m_BoundBox.set(m_x,m_y,m_x+p.x,m_y+p.y);
        super.update(GameTime);
    }

}
