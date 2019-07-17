package com.example.shootinggame;


import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.IState;
import com.example.teamprojectshootinggame.R;
import com.example.teamprojectshootinggame.SoundManager;

import static com.example.Constant.END_APP;

public class StartState extends AppCompatActivity implements IState {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
    }

    @Override
    public void init() {
    }
    @Override
    public void destroy() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(Canvas canvas) {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public void onStoryClicked(View view){
        SoundManager.getInstance().play(1);
        AppManager.getInstance().getGameView().pushState(new StoryState());

        Intent intent = new Intent(getApplicationContext(),StoryState.class);
        startActivity(intent);
    }

    public void onStartClicked(View view){
        SoundManager.getInstance().play(1);
        AppManager.getInstance().getGameView().pushState(new ModeState());
    }

    public void onSelectClicked(View view){
        SoundManager.getInstance().play(1);
        AppManager.getInstance().getGameView().pushState(new PlayerSelect());
    }

    public void onExitClicked(View view){
        setResult(END_APP);
        finish();
    }

}
