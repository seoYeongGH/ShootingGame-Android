package com.example.teamprojectshootinggame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.DisplayMetrics;

import com.example.shootinggame.GameState;
import com.example.shootinggame.Gaming;

public class AppManager {
    private static AppManager s_instance;
    private GameView m_gameView;
    private Resources m_resources;
    public GameState m_gameState;
    private Gaming m_gaming;

    public Bitmap getBitmap(int r){
        return BitmapFactory.decodeResource(m_resources,r);
    }

    void setGameView(GameView _gameView){
        m_gameView = _gameView;
    }
    void setResources(Resources _resources){
        m_resources = _resources;
    }
    public void setGaming(Gaming _gaming){ m_gaming = _gaming;}
    public GameView getGameView(){
        return m_gameView;
    }
    public Gaming getGaming(){ return m_gaming;}
    public Resources getresources(){
        return m_resources;
    }

    //추가한 메소드. 현재 디바이스 또는 뷰의 해상도 WIdth Height 를 구해 Point로 반환
    public Point getDeviceSize(){
        Point p = new Point();

        DisplayMetrics metrics = AppManager.getInstance().getresources().getDisplayMetrics();
        p.x = metrics.widthPixels;
        p.y = metrics.heightPixels;

        return p;
    }

    public static AppManager getInstance(){
        if(s_instance == null) s_instance = new AppManager();
        return s_instance;
    } //Singleton 패턴
}
