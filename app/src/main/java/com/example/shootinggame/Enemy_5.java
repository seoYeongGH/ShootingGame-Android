package com.example.shootinggame;

import android.graphics.Point;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.R;

public class Enemy_5 extends Enemy {//꽃게
    private int deviceWidth = AppManager.getInstance().getDeviceSize().x;//화면의 width

    private int moveX  = 15; //좌,우로 이동하는 속도
    private boolean left = true; //true :왼쪽으로 이동, false :오른쪽으로 이동

    Point p = new Point();

    public Enemy_5() {//꽃게
        super(AppManager.getInstance( ).getBitmap(R.drawable. enemy5));
        p.x = AppManager.getInstance().getBitmap(R.drawable.enemy5).getWidth()/6;
        p.y = AppManager.getInstance().getBitmap(R.drawable.enemy5).getHeight();
        this.initSpriteData(p.x,p.y, 6, 6);

        hp = 10;
        score = 30;
        speed = 4.5f;
    }

    public void move(){
        super.move();

        if(left){ //왼쪽으로 이동
            m_x -=moveX;
            m_y += speed;
            if(m_x<=0) //화면의 왼쪽에 닿으면 이동방향을 바꿈
                left = false;
        }
        else{ //오른쪽으로 이동
            m_x += moveX;
            m_y += speed;

            if(m_x>=deviceWidth-p.x) //화면의 오른쪽에 닿으면 이동방향을 바꿈
                left = true;
        }
    }


    public void update( long GameTime) {
        m_BoundBox.set(m_x,m_y,m_x+p.x,m_y+p.y);
        super.update(GameTime);
    }
}