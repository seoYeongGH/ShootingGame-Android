package com.example.shootinggame;

import android.graphics.Bitmap;
import android.graphics.Point;

import com.example.teamprojectshootinggame.SpriteAnimation;

public class CollisionAction extends SpriteAnimation {
    boolean test = false;
    public static final int STATE_NORMAL = 0;


    public CollisionAction(Bitmap bitmap) {
        super(bitmap);
        Point p = new Point();
        p.x = bitmap.getWidth()/6;
        p.y = bitmap.getHeight();
        this.initSpriteData(p.x , p.y, 12, 6);
    }

    @Override
    public void update(long gameTime) {
        if(gameTime > m_frameTimer + m_fps){
            m_frameTimer = gameTime;
            m_currentFrame +=1 ;
            if(m_currentFrame >= m_iFrames) {
                test = true;
            }
        }
        m_rect.left = m_currentFrame * m_spriteWidth; //ppt 오타 수정
        m_rect.right = m_rect.left + m_spriteWidth;
    }
}
