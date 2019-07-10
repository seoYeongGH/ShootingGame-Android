package com.example.shootinggame;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.SpriteAnimation;

public class Item extends SpriteAnimation {
    public static final int STATE_NORMAL = 0;
    public static final int STATE_OUT =1;
    public int state = STATE_NORMAL;

    protected  int check; //서로 다른 아이템을 list에서 구분할 수 있도록 하는 변수
    protected float speed = 10; //아이템이 떨어지는 속도

    Rect m_BoundBox = new Rect();



    void move(){
        if(m_y> AppManager.getInstance().getresources().getDisplayMetrics().heightPixels) state = STATE_OUT;

            if(m_y <= 200 )
                m_y += speed;
            else
                m_y += speed*2;
    }

    public Item(Bitmap bitmap) {
        super(bitmap);
    }

    @Override
    public void update(long gameTime) {
        move();
        super.update(gameTime);
    }
}
