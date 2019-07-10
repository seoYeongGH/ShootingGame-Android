package com.example.shootinggame;

//각 난이도별로 다른 값들을 갖는 변수 설정을 위한 클래스를 만들기위한 상위 클래스 (State구조 사용)
public class GameState{
    public int getItemSpeed(){ //item이 생성되는 시간 반환
        return 0;
    }
    public int getCondition(){ //스테이지 클리어를 위해 제거해야하는 적의 수
        return 0;
    }
    public int getEnemyRegenSpeed(){ //적이 생성되는 속도
        return 0;
    }
    public int getEnemyCount(){ //랜덤으로 생성되어야하는 적의 수를 반환한다.(상어, 성게 제외)
        return  0;
    }
    public int getProbShark(){//상어의 생성확률
        return 0;
    }
    public boolean getCanMake4(){//성게를 생성할 것인지 여부를 반환
        return false;
    }
}

