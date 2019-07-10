package com.example.shootinggame;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.teamprojectshootinggame.GraphicObject;


public class Missile extends GraphicObject {
    public static final int STATE_NORMAL= 0;
    public static final int STATE_OUT= 1;
    public int state;

    Rect m_BoundBox = new Rect();

    protected int damage;

    public Missile(Bitmap bitmap) {
        super(bitmap);
        state= STATE_NORMAL;
    }

    public void update( ) {
    }

}
