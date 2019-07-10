package com.example.shootinggame;

import android.graphics.Rect;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.R;
import com.example.teamprojectshootinggame.SpriteAnimation;

public class sharkBackground extends SpriteAnimation {
    Rect m_BoundBox = new Rect();

    public sharkBackground(int x, int y) {
        super(AppManager.getInstance( ).getBitmap(R.drawable. shark_warning));
        this.initSpriteData( AppManager.getInstance().getBitmap(R.drawable.shark_warning).getWidth()/6, AppManager.getInstance().getBitmap(R.drawable.shark_warning).getHeight(), 15, 6);

        setPosition(x,y);
    }

    public void update(long gameTime){
        super.update(gameTime);
    }
}
