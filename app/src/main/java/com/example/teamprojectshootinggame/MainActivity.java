package com.example.teamprojectshootinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.shootinggame.StartState;

import static com.example.Constant.END_APP;
import static com.example.Constant.START_APP;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GameView(this);

        Intent intent = new Intent(getApplicationContext(), StartState.class);
        startActivityForResult(intent,START_APP);
    }

    //뒤로가기 버튼 누르면
    @Override
    public void onBackPressed() {
        AppManager.getInstance().getGameView().popState();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent intent){
        if(resultCode == END_APP){
            SoundManager.getInstance().stopMediaPlayer();
            this.finish();
            System.exit(0);
        }
    }
}
