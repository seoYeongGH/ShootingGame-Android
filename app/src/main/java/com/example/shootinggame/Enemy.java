package com.example.shootinggame;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.SpriteAnimation;

public class Enemy extends SpriteAnimation {
    //적의 상태를 나타내기위한 상수
    public static final int STATE_NORMAL= 0;
    public static final int STATE_OUT= 1;
    public int state= STATE_NORMAL;

    //mode에서 선택한 난이도
    public static final int EASY = 1;
    public static final int NORMAL = 2;
    public static final int HARD = 3;

    long EnemyMoved;//적이 마지막으로 움직인 시간
    long LastShoot;//적이 마지막으로 미사일을 발사한 시간

    //적의 상태정보
    protected int hp;
    protected int score;
    protected float speed;

    Rect m_BoundBox = new Rect();//collisionCheck를 위한 경계 설정


    public Enemy(Bitmap bitmap) {
        super(bitmap);
    }

    public void setSpeed(float _speed) {
        this.speed = _speed;
    }
    void move( ) {
        if( m_y> AppManager.getInstance().getresources().getDisplayMetrics().heightPixels ) state= STATE_OUT;
        if( m_x>  AppManager.getInstance().getresources().getDisplayMetrics().widthPixels ) state= STATE_OUT;
        else if(m_x<-80) state = STATE_OUT;
    }
    void attack() {
    }

    @Override
    public void update(long gameTime) {
        attack();
        move();
        super.update(gameTime);
    }
}
