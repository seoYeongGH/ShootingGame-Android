package com.example.shootinggame;

import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.GameView;
import com.example.teamprojectshootinggame.R;

public class GameStateHard extends GameState {
    public int getItemSpeed(){
        return 8000;
    }

    public int getCondition(){
        return 20;
    }
    public int getEnemyRegenSpeed(){
        return 1000;
    }

    public int getEnemyCount(){
        return  3;
    }

    public int getProbShark(){
        return 10;
    }

    public boolean getCanMake4(){
        return true;
    }
}
