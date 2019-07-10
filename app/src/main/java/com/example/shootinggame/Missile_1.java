package com.example.shootinggame;

import android.graphics.Point;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.R;

public class Missile_1 extends Missile_Enemy { //문어 마시일

    public  Missile_1(int x, int y) {
        super(AppManager.getInstance().getBitmap(R.drawable.missile_2));

        p = new Point();
        p.x = AppManager.getInstance().getBitmap(R.drawable.missile_2).getWidth();
        p.y = AppManager.getInstance().getBitmap(R.drawable.missile_2).getHeight();

        this.setPosition(x+25, y+105);
    }

    public void update() {
        super.update();

        m_y += 18;// 문어 미사일 이동

    }
}
