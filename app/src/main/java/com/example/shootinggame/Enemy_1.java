package com.example.shootinggame;

import android.graphics.Point;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.R;

import java.util.Random;

public class Enemy_1 extends Enemy { //문어
    private int i_move = 0;//한 번에 좌표를 이동하는것이아닌 자연스럽게 좌표이동이 가능하도록 하기 위해 사용되는 변수

    Point p = new Point();//적의 width와 height를 저장하는 Point

    private int shootingTime; //적의 미사일 발사속도

    public Enemy_1(int level) {
        //SpriteAnimation 설정
        super(AppManager.getInstance().getBitmap(R.drawable.enemy1));
        p.x = AppManager.getInstance().getBitmap(R.drawable.enemy1).getWidth()/6;
        p.y = AppManager.getInstance().getBitmap(R.drawable.enemy1).getHeight();
        this.initSpriteData(p.x,p.y, 6, 6);

        //난이도에 따라 적의 상태정보가 달라짐
        if(level==EASY) {
            hp = 10;
            score = 10;
            speed = 5f;

            shootingTime = 1500;
        }
        else if(level == NORMAL){
            hp = 10;
            score = 10;
            speed = 7f;

            shootingTime = 1300;
        }
        else if(level ==HARD){
            hp = 20;
            score = 20;
            speed = 9f;

            shootingTime= 1150;
        }
    }

    @Override
    void attack() {
        if (System.currentTimeMillis() - LastShoot >= shootingTime) {//shootingTime만큼의 시간이 지나면 미사일 발사
            LastShoot = System.currentTimeMillis();

            AppManager.getInstance().getGaming().m_enemmslist.add(new Missile_1(m_x , m_y));
        }
    }

    public void move(){
        super.move();

        if (System.currentTimeMillis() - EnemyMoved >= 800) {//8초가 지나면 이동
            i_move ++;
            m_y+=speed;
            if(i_move>15) {
                EnemyMoved = System.currentTimeMillis();
                i_move = 0;
            }
        }
    }
    public void update( long GameTime) {
        m_BoundBox.set(m_x,m_y,m_x+p.x,m_y+p.y);
        super.update(GameTime);
    }
}
