package com.example.shootinggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.IState;
import com.example.teamprojectshootinggame.R;
import com.example.teamprojectshootinggame.SoundManager;

public class StartState implements IState {
    Point point = new Point(); //디바이스 사이즈를 얻기위한 Point
    Bitmap intro;
    Bitmap exit;
    Bitmap start;
    Bitmap select;
    Bitmap story;

    @Override
    public void init() {
        //기기의 width, height저장
        point.x = AppManager.getInstance().getDeviceSize().x;
        point.y = AppManager.getInstance().getDeviceSize().y;

        //각 이미지 로드 후 사이즈 재설정
        intro = AppManager.getInstance().getBitmap(R.drawable.intro_background);
        intro = Bitmap.createScaledBitmap(intro,point.x,point.y,true);

        story = AppManager.getInstance().getBitmap(R.drawable.story_image);
        story = Bitmap.createScaledBitmap(story,point.x/2,point.y/8,true);

        exit = AppManager.getInstance().getBitmap(R.drawable.exit_image);
        exit = Bitmap.createScaledBitmap(exit,point.x/2,point.y/8,true);

        start = AppManager.getInstance().getBitmap(R.drawable.start_image);
        start = Bitmap.createScaledBitmap(start,point.x/2,point.y/8,true);

        select = AppManager.getInstance().getBitmap(R.drawable.select_button);
        select = Bitmap.createScaledBitmap(select,point.x/2,point.y/8,true);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawBitmap(intro, 0, 0, null);
        canvas.drawBitmap(story, point.x/4, (point.y/8) * 4-175, null);
        canvas.drawBitmap(start, point.x/4, (point.y/8) * 5-150, null);
        canvas.drawBitmap(select,point.x/4,(point.y/8) * 6-125 , null);
        canvas.drawBitmap(exit, point.x/4, (point.y/8) * 7 - 100, null);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //터치한 좌표
        int x = (int)event.getX();
        int y = (int)event.getY();

        //각 메뉴 버튼에 해당하는 범위 설정
        Rect r_story = new Rect(point.x/4,(point.y/8) * 4 - 175,(point.x/4)+point.x/2,(point.y/8)*4 - 175 + (point.y/8));
        Rect r_start = new Rect(point.x/4,(point.y/8) * 5 - 150,(point.x/4)+point.x/2,(point.y/8)*5 - 150 + (point.y/8));
        Rect r_select = new Rect(point.x/4,(point.y/8) * 6 - 125,(point.x/4)+point.x/2,(point.y/8)*6 - 125 + (point.y/8));
        Rect r_exit = new Rect(point.x/4,(point.y/8)*7-100,(point.x/4) + (point.x/2),(point.y/8)*7 - 100 + (point.y/8));

        //터치한 좌표가 어느 버튼에 포함하는지 구분하여 각 state를 변경하고 stack에 저장
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(r_start.contains(x,y)){
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    SoundManager.getInstance().play(1);
                    AppManager.getInstance().getGameView().pushState(new ModeState());
                }
            }
            else if(r_select.contains(x,y)) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    SoundManager.getInstance().play(1);
                    AppManager.getInstance().getGameView().pushState(new PlayerSelect());
                }
            }
            else if (r_exit.contains(x, y))
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    SoundManager.getInstance().play(1);
                    System.exit(0);
                }
            }

            else if(r_story.contains(x,y))
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    SoundManager.getInstance().play(1);
                    AppManager.getInstance().getGameView().pushState(new StoryState());
                }
            }
        }
        return false;
    }

}
