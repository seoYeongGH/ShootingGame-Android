package com.example.shootinggame;

import android.graphics.Point;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.R;

import java.util.Random;

public class Enemy_2 extends Enemy {//복어

    private int deviceHeight = AppManager.getInstance().getDeviceSize().y;//기기의 화면 높이

    public int moveType = new Random().nextInt(2);//복어는 두가지의 movetype중 랜덤으로 움직임
    private int shootingTime;

    Point p = new Point();

    public Enemy_2(int level) {
        super(AppManager.getInstance().getBitmap(R.drawable. enemy2));
        p.x = AppManager.getInstance().getBitmap(R.drawable.enemy2).getWidth()/6;
        p.y = AppManager.getInstance().getBitmap(R.drawable.enemy2).getHeight();
        this.initSpriteData(p.x,p.y, 6, 6);

        if(level==EASY) {
            hp = 20;
            score = 20;
            speed = 6f;

            shootingTime = 1500;
        }
        else if(level == NORMAL){
            hp = 20;
            score = 20;
            speed = 7f;

            shootingTime = 1300;
        }
        else if(level == HARD){
            hp = 25;
            score = 40;
            speed = 8f;

            shootingTime = 1100;
        }
    }

    public void move(){
        super.move();

        if(moveType == 0){
            if(m_y<deviceHeight/3){
                m_y += speed*2;
            }
            else{
                m_x += speed;// 일정 지점 이후 오른쪽 대각선으로 이동
                m_y += speed;
            }
        }
        else if(moveType == 1){
            if(m_y<deviceHeight/3){
                m_y += speed*2;
            }
            else{
                m_x -= speed;// 일정 지점 이후 왼쪽 대각선으로 이동
                m_y += speed;
            }
        }

    }
    void attack() {
        if (System.currentTimeMillis() - LastShoot >= shootingTime) {
            LastShoot = System.currentTimeMillis();

            AppManager.getInstance().getGaming().m_enemmslist.add(new Missile_2(m_x, m_y, moveType));
        }
    }
    public void update( long GameTime) {
        m_BoundBox.set(m_x,m_y,m_x+p.x,m_y+p.y);
        super.update(GameTime);
    }
}
