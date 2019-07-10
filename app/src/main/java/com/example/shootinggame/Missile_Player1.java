package com.example.shootinggame;


import android.graphics.Point;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.R;


public class Missile_Player1 extends Missile_Player {
    public Missile_Player1(int x, int y) {
        super( AppManager.getInstance().getBitmap(R.drawable.missile_1));

        //미사일 크기를 missileSize Point에 저장
        missileSize = new Point();
        missileSize.x = AppManager.getInstance().getBitmap(R.drawable.missile_1).getWidth();
        missileSize.y = AppManager.getInstance().getBitmap(R.drawable.missile_1).getHeight();

        damage = 10;

        this.setPosition(x+35 ,y);
    }

    public void update()
    {
        super.update();
    }
}
