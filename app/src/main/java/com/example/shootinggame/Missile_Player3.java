package com.example.shootinggame;

import android.graphics.Point;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.R;


public class Missile_Player3 extends Missile_Player {
    public Missile_Player3(int x, int y) {

        super( AppManager.getInstance().getBitmap(R.drawable.missile5));

        missileSize = new Point(); //미사일 크기;
        missileSize.x = AppManager.getInstance().getBitmap(R.drawable.missile5).getWidth();
        missileSize.y = AppManager.getInstance().getBitmap(R.drawable.missile5).getHeight();

        damage = 5;

        this.setPosition(x+35,y);
    }

    public void update()
    {
        super.update();
    }
}
