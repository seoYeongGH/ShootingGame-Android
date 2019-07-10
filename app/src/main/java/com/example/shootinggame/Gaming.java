package com.example.shootinggame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.GameView;
import com.example.teamprojectshootinggame.IState;
import com.example.teamprojectshootinggame.R;
import com.example.teamprojectshootinggame.SoundManager;

import java.util.ArrayList;
import java.util.Random;

public class Gaming implements IState {
    GameState state;//난이도 설정을 state구조로 만듦

    //터치 이동을 구현하기 위한 변수
    int cx,cy;
    int px, py;

    private int diff;//현재 난이도에 해당하는 값을 저장하고있는 변수

    protected int regenItemSpeed; //아이템 생성 속도
    protected int regenEnemySpeed; //적 생성 속도
    private int enemyCount; //랜덤으로 생성하는 적의 종류(난이도가 어려워 질수록 생성되는 적의 종류가 증가)
    private int probShark; //상어 생성 확률
    private boolean make4; //성게를 생성할지 유무
    private int stageCondition; //스테이지 클리어를 위한 제거해야하는 적의 수

    //이미지, 애니매이션 구현을 위한 변수
    protected BackGround m_background;
    public Player m_player;
    protected CollisionAction m_explosion;

    ArrayList<CollisionAction> m_explosionlist = new ArrayList<CollisionAction>();//플레이어 미사일과 적의 충돌이미지 구현을 위한 변수
    ArrayList<Item> m_item = new ArrayList<Item>(); //생성된 아이템 list
    ArrayList<Missile> m_pmslist= new ArrayList<Missile>( ); //플레이어가 발사한 미사일 list
    ArrayList<Enemy> m_enemlist = new ArrayList<Enemy>( );//생성된 적 list
    ArrayList<Enemy> m_shark = new ArrayList<Enemy>(); //생성된 상어 list
    ArrayList<sharkBackground> m_sharkWarning = new ArrayList<sharkBackground>(); //상어가 돌진하기 전 경고를 하기위해 상어의 이동경로를 표시해주는 sharkBackground를 담는 변수
    ArrayList<Missile> m_enemmslist= new ArrayList<Missile>( ); //적이 발사한 미사일 list

    long LastRegenEnemy= System.currentTimeMillis(); //적이 마지막으로 생성된 시간
    long LastRegenItem = System.currentTimeMillis(); //아이템이 마지막으로 생성된 시간

    //적과 아이템을 무작위로 생성하기 위해 사용하는 random변수
    protected Random randEnem= new Random();
    protected Random randItem = new Random();

    protected  static int playerSelect = 0;//select에서 선택한 플레이어의 종류
    protected int kill = 0; //제거한 적의 수

    public Gaming(int _diff, int level){//Gaming을 생성할 때, 난이도(_diff)와 level(stage)를 파라미터로 받음
        AppManager.getInstance().setGaming(this);

        state = AppManager.getInstance().m_gameState;
        diff = _diff;

        //난이도와 stage에 따라 아이템 생성속도, 적 생성속도, 제거해야하는 적의 수가 달라짐
        regenItemSpeed = state.getItemSpeed();
        regenItemSpeed += (level-1)*250;

        regenEnemySpeed = state.getEnemyRegenSpeed();
        regenEnemySpeed -= (level-1)*200;

        stageCondition = state.getCondition();
        stageCondition += (level-1)*3;

        //난이도에 따라 생성되는 적의 종류 수, 상어의 생성확률, 성게의 생성 유무가 달라짐
        enemyCount = state.getEnemyCount();
        probShark = state.getProbShark();
        make4 = state.getCanMake4();
    }

    public void makeExplosion(int x, int y){//충돌 애니매이션 생성
        CollisionAction c = new CollisionAction(AppManager.getInstance().getBitmap(R.drawable.explosion));
        c.setPosition(x-55,y-150);
        m_explosionlist.add(c);
    }

    public void makeItem() {
        if (System.currentTimeMillis() - LastRegenItem >= regenItemSpeed) {//일정 시간(regenItemSpeed)가 지나면 아이템 생성
            LastRegenItem = System.currentTimeMillis();

            int itemtype = randItem.nextInt(4);//아이템 종류 무작위로 생성
            Item item = null;

            //아이템을 생성한 후, item.check에 각 아이템에 해당하는 값을 저장
            if (itemtype == 0) {
                item = new Item_1();
                item.check =0;
            }
            if (itemtype == 1) {
                item = new Item_2();
                item.check =1;
            }
            if (itemtype == 2) {
                item = new Item_3();
                item.check =2;
            }
            if (itemtype == 3) {
                item = new Item_4();
                item.check =3;
            }

            //아이템의 x좌표를 무작위로 정한 후, m_item(List)에 저장
            item.setPosition(randEnem.nextInt(AppManager.getInstance().getBitmap(R.drawable.background2).getWidth()), -60);
            m_item.add(item);
        }
    }

    @Override
    public void init() {
        m_player = new Player1(AppManager.getInstance().getBitmap(R.drawable.player));//따로 select하지 않았을 경우 player의 기본 설정

        //선택한 플레이어의 종류에 따라 플레이어 생성
        switch(playerSelect){
            case 0:{
                m_player = new Player1(AppManager.getInstance().getBitmap(R.drawable.player));
                break;
            }
            case 1:{
                m_player = new Player2(AppManager.getInstance().getBitmap(R.drawable.player2));
                break;
            }
            case 2:{
                m_player = new Player3(AppManager.getInstance().getBitmap(R.drawable.player3));
                break;
            }
            case 3 :{
                m_player = new Player4(AppManager.getInstance().getBitmap(R.drawable.enemy1));
                break;
            }
            case 4 : {
                m_player = new Player5(AppManager.getInstance().getBitmap(R.drawable.enemy2));
                break;
            }
            case 5 : {
                m_player = new Player6(AppManager.getInstance().getBitmap(R.drawable.enemy5));
                break;
            }
        }

        //폭발 효과와 배경 생성
        m_explosion = new CollisionAction(AppManager.getInstance().getBitmap(R.drawable.explosion));
        m_background = new BackGround();
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        long gameTime = System.currentTimeMillis();

        m_background.update(gameTime);
        m_player.update(gameTime);
        m_explosion.update(gameTime);

        for (int i = m_pmslist.size() - 1; i >= 0; i--) {
            Missile pms = m_pmslist.get(i);
            pms.update();
            if (pms.state == Missile.STATE_OUT) //플레이어의 미사일이 화면에서 사라지면 list에서 제거
                m_pmslist.remove(i);
        }

        for(int i = m_explosionlist.size()-1 ;i>=0;i--){
            CollisionAction c = m_explosionlist.get(i);
            c.update(gameTime);
            if(c.test)m_explosionlist.remove(i); //폭발효과 프레임을 모두 보여주면 list에서 제거
        }

        for (int j = m_enemlist.size() - 1; j >= 0; j--) {
            Enemy enem = m_enemlist.get(j);
            enem.update(gameTime);
            if (enem.state == Enemy.STATE_OUT) {//적이 화면에서 사라지면 list에서 제거
                m_enemlist.remove(j);
            }
        }

        for(int i = m_item.size()-1;i>=0;i--){
            Item item = m_item.get(i);
            item.update(gameTime);
            if(item.state == Item.STATE_OUT) m_item.remove(i); //아이템이 화면에서 사라지면 list에서 제거
        }

        for( int k= m_enemmslist.size( )-1; k>= 0; k--) {
            Missile enemms= m_enemmslist.get(k);
            enemms.update( );
            if( enemms. state== Missile. STATE_OUT) //적 미사일이 화면에서 사라지면 list에서 제거
            { m_enemmslist.remove(k); }
        }

        for(int i= m_shark.size()-1; i>=0; i--){
            Enemy shark = m_shark.get(i);
            shark.update(gameTime);
            m_sharkWarning.get(i).update(gameTime);

            if(shark.state == Enemy.STATE_OUT){ //상어가 화면에서 사라지면 상어와 상어의 이동경로표시 모두 list에서 제거
                m_sharkWarning.remove(i);
                m_shark.remove(i);
            }
        }

        makeEnemy();
        makeItem();
        checkCollision();
    }

    public void makeEnemy(){
       if(System.currentTimeMillis() - LastRegenEnemy >= regenEnemySpeed){ //일정시간(regenEnemySpeed)가 지나면 적 생성
           LastRegenEnemy = System.currentTimeMillis();

           int enemtype = randEnem.nextInt(enemyCount); //enemyCount수 만큼의 적 종류 중 하나의 적 생성
           Enemy enem;

           if (enemtype == 0) {//문어
               enem = new Enemy_1(diff); //난이도에 따라 적의 상태정보(hp, score, speed)가 달라지므로 난이도 정보를 인자로 넘겨줌

               enem.setPosition(randEnem.nextInt(AppManager.getInstance().getBitmap(R.drawable.background2).getWidth()), -60);
               m_enemlist.add(enem);
           }
           else if (enemtype == 1) {//복어
               enem = new Enemy_2(diff);

               enem.setPosition(randEnem.nextInt(AppManager.getInstance().getBitmap(R.drawable.background2).getWidth()), -60);
               m_enemlist.add(enem);
           }
           else if(enemtype == 2) { //꽃게
               enem = new Enemy_5();

               enem.setPosition(randEnem.nextInt(AppManager.getInstance().getBitmap(R.drawable.background2).getWidth()), -60);
               m_enemlist.add(enem);
           }

           int num = new Random().nextInt(100); //0~99까지의 수를 무작위로 생성하여 적 생성 확률 구현

           if(num<probShark&& num>=0){ //probShark의 확률로 상어생성
               Enemy_3 shark = new Enemy_3(diff);

               shark.setPosition(randEnem.nextInt(AppManager.getInstance().getBitmap(R.drawable.background2).getWidth()), -60);
               m_shark.add(shark);
               m_sharkWarning.add(new sharkBackground(shark.getX(),0));
           }
           if(make4){
               if(num>=probShark && num<probShark+10){//10%의 확률로 성게 생성
                   enem = new Enemy_4();

                   enem.setPosition(randEnem.nextInt(AppManager.getInstance().getBitmap(R.drawable.background2).getWidth()), -60);
                   m_enemlist.add(enem);
               }
           }
       }
    }

    public void checkCollision( ) {
        //플레이어 미사일과 적 충돌
        for (int j = m_enemlist.size() - 1; j >= 0; j--) {
            for (int i = m_pmslist.size() - 1; i >= 0; i--) {
                if (CollisionManager.checkBoxToBox(m_pmslist.get(i).m_BoundBox, m_enemlist.get(j).m_BoundBox)) {
                    //적의 체력이 플레이어의 데미지만큼 감소하고, 폭발효과가 발생한다
                    m_enemlist.get(j).hp -= m_pmslist.get(i).damage;
                    makeExplosion(m_pmslist.get(i).getX(),m_pmslist.get(i).getY());
                    m_pmslist.remove(i); //충돌이 일어나면 플레이어의 미사일 제거거

                    //적 체력이 0이하가 되면  게임스코어 증가후 제거
                   if(m_enemlist.get(j).hp<=0) {
                        SoundManager.getInstance().play(5);//효과음 재생

                        kill++;//제거한 적의 수 증가
                        GameView.gameScore += m_enemlist.get(i).score;
                        m_enemlist.remove(j);
                    }
                    if(kill >= stageCondition) //스테이지 클리어시
                        AppManager.getInstance().getGameView().changeGameState(new GameNextState(diff));
                    return;
                }
            }
        }


        //적 미사일과 플레이어 충돌
        for(int i=m_enemmslist.size()-1; i>=0; i--){
            if(CollisionManager.checkBoxToBox(m_enemmslist.get(i).m_BoundBox, m_player.m_BoundBox)){
                m_enemmslist.remove(i);
                SoundManager.getInstance().play(3); //생명력 감소 효과음
                m_player.destroyPlayer();
                if(m_player.getLife() <= 0) //플레이어 체력이 0이하가 되면 게임오버
                    AppManager.getInstance().getGameView().changeGameState(new GameOverState());
                return ;
            }
        }

        //적과 플레이어 충돌 시
        for (int j = m_enemlist.size() - 1; j >= 0; j--) {
            if (CollisionManager.checkBoxToBox(m_player.m_BoundBox, m_enemlist.get(j).m_BoundBox)) {
                //적을 제거하고 플레이어의 생명력을 1감소시킨다.
                m_enemlist.remove(j);
                SoundManager.getInstance().play(3); //생명력 감소 효과음
                m_player.destroyPlayer();
                if (m_player.getLife() <= 0)  //플레이어 체력이 0이하가 되면 게임오버
                    AppManager.getInstance().getGameView().changeGameState(new GameOverState());
                return;
            }
        }

        //아이템과 플레이어 충돌
        for(int i = m_item.size() -1 ; i>=0;i--){
            if (CollisionManager.checkBoxToBox(m_player.m_BoundBox, m_item.get(i).m_BoundBox)) {
                SoundManager.getInstance().play(2);

                //아이템 종류에 따른 아이템 효과
                if (m_item.get(i).check == 0) {
                    m_item.remove(i);
                    m_player.reudeceMissileTime(); //미사일 발사속도 증가
                }
                else if (m_item.get(i).check == 1) {
                    m_item.remove(i);
                    m_player.addLife(); //플레이어 생명력 1증가
                }
                else if (m_item.get(i).check == 2) {
                    m_item.remove(i);
                    for(int k = m_enemmslist.size() - 1; k >=0; k--)//모든 적 미사일 제거
                       m_enemmslist.remove(k);
                }
                else if (m_item.get(i).check == 3) {
                    m_item.remove(i);
                    for(int k= m_enemlist.size()-1;k>=0;k--)//모든 적 제거
                        m_enemlist.remove(k);

                }
            }
        }

        //플레이어와 상어(적) 충돌
        for(int i= m_shark.size()-1; i>=0; i--) {
            if (CollisionManager.checkBoxToBox(m_player.m_BoundBox, m_shark.get(i).m_BoundBox)) {
                m_shark.remove(i);
                m_sharkWarning.remove(i);
                SoundManager.getInstance().play(3);
                m_player.destroyPlayer();
                if (m_player.getLife() <= 0)
                    AppManager.getInstance().getGameView().changeGameState(new GameOverState());
                return;
            }
        }
    }

    @Override
    public void render(Canvas canvas) {
        m_background.draw(canvas);
        m_player.draw(canvas);

        for(Missile pms: m_pmslist)
            pms.draw(canvas);
        for(Enemy enem: m_enemlist)
            enem.draw(canvas);
        for(Item item: m_item)
            item.draw(canvas);
        for(CollisionAction c : m_explosionlist)
            c.draw(canvas);
        for(Missile enemms: m_enemmslist)
            enemms.draw(canvas);
        for(sharkBackground shark_bg : m_sharkWarning)
            shark_bg.draw(canvas);
        for(Enemy shark : m_shark)
            shark.draw(canvas);

        //상단에 생명력과 stage 정보 출력
        Paint p = new Paint( );
        p.setTextSize(100);
        p.setColor(Color. BLACK);
        p.setFakeBoldText(true);
        canvas.drawText("생명력 :"+String.valueOf( m_player.getLife( )), 0, 100, p);
        canvas.drawText("Stage : " + String.valueOf(GameView.stage),AppManager.getInstance().getDeviceSize().x - AppManager.getInstance().getDeviceSize().x/2,100,p);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //cx1과 cy1 에 현재 터치이벤트가 발생한 좌표 저장
        int cx1 = (int) event.getX();
        int cy1 = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // px,py 에 ACTION_DOWN 이벤트가 일어났을 때의 좌표 저장
            px = (int) event.getX();
            py = (int) event.getY();
        } else {
            //ACTION_DOWN 이벤트가 발생한 후 변화한 좌표값을 cx,cy에 저장
            if (cx1 > px) {
                cx = cx1 - px;
            }
            if (cy1 > py) {
                cy = cy1 - py;
            }
            if (px > cx1) {
                cx = cx1 - px;
            }
            if (py > cy1) {
                cy = cy1 - py;
            }
            //변화한 좌표값 cx,cy 에 따라 m_player 위치 설정
            if (m_player.getX() + cx > 0 && m_player.getX() + cx < AppManager.getInstance().getDeviceSize().x - 100 &&
                    m_player.getY() + cy > 300 && m_player.getY() + cy < AppManager.getInstance().getDeviceSize().y - 200)
                m_player.setPosition(m_player.getX() + cx, m_player.getY() + cy);

            //px,py 현재이벤트가 발생한 좌표값을 갖고 있는 cx1,cy1 의 값을 저장
            px = cx1;
            py = cy1;
        }

        return true;
    }
}
