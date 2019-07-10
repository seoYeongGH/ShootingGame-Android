package com.example.shootinggame;


public class GameStateEasy extends GameState {

    public int getItemSpeed(){
        return 5000;
    }

    public int getCondition(){
        return 10;
    }

    public int getEnemyRegenSpeed(){
        return 3000;
    }

    public int getEnemyCount(){
        return  2;
    }

    public int getProbShark(){
        return 10;
    }

    public boolean getCanMake4(){
        return false;
    }
}
