package com.example.shootinggame;

import android.graphics.Point;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.R;

public class Missile_Player4 extends Missile_Player {

    public Missile_Player4(int x, int y) {

        super( AppManager.getInstance().getBitmap(R.drawable.missile6));

        missileSize = new Point(); //미사일 크기
        missileSize.x = AppManager.getInstance().getBitmap(R.drawable.missile6).getWidth();
        missileSize.y = AppManager.getInstance().getBitmap(R.drawable.missile6).getHeight();

        damage = 10;

        this.setPosition(x+35 ,y);
    }

    public void update()
    {
        super.update();
    }
}