package com.example.teamprojectshootinggame;

import android.content.Context;
import android.graphics.Canvas;
import android.media.SoundPool;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.example.shootinggame.StartState;

import java.util.Stack;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameViewThread m_thread;
    private Stack<IState> stack = new Stack<>(); // pushState() 와 popState()를 위한 스택.
    private IState m_state; //State 변경을 위한 변수
    public static int gameScore = 0;
    public static int stage=1;

    public GameView(Context context){
        super(context);
        setFocusable(true); //키 이벤트의 포커스를 받을 수 있는 컴포넌트가 여러개있을 때 우선적으로 입력받기 위해 설정
        AppManager.getInstance().setGameView(this);
        AppManager.getInstance().setResources(getResources());

        SoundManager.getInstance().init(context); //음악 초기화
        //효과음 HashMap에 저장
        SoundManager.getInstance().addSound(1,R.raw.menu_select);
        SoundManager.getInstance().addSound(2,R.raw.getitem);
        SoundManager.getInstance().addSound(3,R.raw.life);
        SoundManager.getInstance().addSound(4,R.raw.shot);
        SoundManager.getInstance().addSound(5,R.raw.enemykill);
        SoundManager.getInstance().addSound(6,R.raw.gameover);
        SoundManager.getInstance().addMusicLoop(R.raw.background);

        pushState(new StartState());//어플리케이션 시작
       getHolder().addCallback(this);// getHolder() Return the SurfaceHolder providing access and control over this SurfaceView's underlying surface.
        m_thread = new GameViewThread(getHolder(),this);
        m_state.init();
    }

    public void update(){
        //long gameTime = System.currentTimeMillis();
        m_state.update();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        m_state.onKeyDown(keyCode,event);
        return true;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        m_state.onTouchEvent(event);
        return true;
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        m_state.render(canvas);
    }

    public void changeGameState(IState _state){
        if ( m_state != null) {
            m_state.destroy();
            popState();
            pushState(_state);
            _state.init();
            m_state = _state;
        }
    }

    public void pushState(IState _state){
        _state.init();
        stack.push(_state); //스택하나 추가.
        m_state = _state;
    }

    public void popState(){
        if(stack.size() <= 1){
            System.exit(0);
        }
        m_state.destroy();
        stack.pop(); //스택 뻄.
        m_state = stack.peek(); //현재 제일 위에있는 스택을 현재상태로 초기화
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        m_thread.setRunning(true);
        m_thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        m_thread.setRunning(false);
        while(retry){
            try{
                m_thread.join();
                retry = false;
            }catch (InterruptedException e){}
        }
    }
}
