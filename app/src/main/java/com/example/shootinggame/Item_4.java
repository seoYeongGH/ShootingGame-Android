package com.example.shootinggame;

import android.graphics.Point;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.R;


public class Item_4 extends Item {
    Point p = new Point();

    public Item_4() {
        super(AppManager.getInstance().getBitmap(R.drawable.item4));

        p.x = AppManager.getInstance().getBitmap(R.drawable.item4 ).getWidth()/4;
        p.y = AppManager.getInstance().getBitmap(R.drawable.item4).getHeight();

        this.initSpriteData(p.x,p.y, 3, 4);

    }

    @Override
    public void update(long gameTime) {
        super.update(gameTime);
        m_BoundBox.set(m_x,m_y,m_x+p.x,m_y+p.y);
    }
}
