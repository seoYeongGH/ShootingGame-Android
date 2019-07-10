package com.example.teamprojectshootinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }

    //뒤로가기 버튼 누르면
    @Override
    public void onBackPressed() {
        AppManager.getInstance().getGameView().popState();
    }
}
