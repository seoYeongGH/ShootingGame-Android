package com.example.shootinggame;


import android.graphics.Point;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.R;

public class Missile_Player2 extends Missile_Player {

    public Missile_Player2(int x, int y) {

        super( AppManager.getInstance().getBitmap(R.drawable.missile4));

        missileSize = new Point();
        missileSize.x = AppManager.getInstance().getBitmap(R.drawable.missile4).getWidth();
        missileSize.y = AppManager.getInstance().getBitmap(R.drawable.missile4).getHeight();

        damage = 20;

        this.setPosition(x+35 ,y);
    }

    public void update()
    {
        super.update();
    }
}
