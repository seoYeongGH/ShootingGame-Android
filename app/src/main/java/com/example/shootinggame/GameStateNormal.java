package com.example.shootinggame;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.GameView;
import com.example.teamprojectshootinggame.R;


public class GameStateNormal extends GameState {

    public int getItemSpeed(){
        return 5000;
    }

    public int getCondition(){
        return 15;
    }

    public int getEnemyRegenSpeed(){
        return 2000;
    }

    public int getEnemyCount(){
        return  2;
    }

    public int getProbShark(){
        return 8;
    }

    public boolean getCanMake4(){
        return true;
    }
}
