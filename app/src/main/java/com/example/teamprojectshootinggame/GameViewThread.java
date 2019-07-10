package com.example.teamprojectshootinggame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameViewThread extends Thread {

    private SurfaceHolder m_surfaceHolder;
    private GameView m_gameView;

    private boolean m_run = false;
    public GameViewThread(SurfaceHolder surfaceHolder,GameView gameView){
        m_gameView = gameView;
        m_surfaceHolder = surfaceHolder;
    }
    public void setRunning(boolean run){m_run = run;}

    @Override
    public void run() {
        //super.run();
        Canvas _canvas;
        while(m_run){
            _canvas = null;
            try{
                m_gameView.update();
                _canvas = m_surfaceHolder.lockCanvas(null); // lockCanvas() = Start editing the pixels in the surface.
                synchronized (m_surfaceHolder){
                    m_gameView.draw(_canvas);
                }
            }finally {
                if(_canvas != null)
                    m_surfaceHolder.unlockCanvasAndPost(_canvas); // unlockCanvasAndPost (Canvas) = Finish editing pixels in the surface.
            }
        }
    }
}
