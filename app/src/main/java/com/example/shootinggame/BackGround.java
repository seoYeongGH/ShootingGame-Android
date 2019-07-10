package com.example.shootinggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.example.teamprojectshootinggame.AppManager;
import com.example.teamprojectshootinggame.GraphicObject;
import com.example.teamprojectshootinggame.R;

public class BackGround extends GraphicObject {
    //배경의 스크롤 속도(배경과 layer는 같은 속도로 이동)
    private static final float SCROLL_SPEED = 1.7f;
    private static final float SCROLL_SPEED_2 =1.7f;

    private Bitmap m_layer2;//물방울 layer

    //배경 이미지지의 y치값
    private float m_scroll ;
    private float m_scroll_2;

    private int _height;//기기의 화면 높이

    public BackGround() {
        super(null);

        int width = AppManager.getInstance().getDeviceSize().x;
        int height = AppManager.getInstance().getDeviceSize().y;

        m_bitmap = AppManager.getInstance().getBitmap(R.drawable.background_new);
        m_bitmap = Bitmap.createScaledBitmap(m_bitmap,width,m_bitmap.getHeight(),true); //배경의 크기를 기기의 화면 크기에 맞춤

        m_layer2 = AppManager.getInstance().getBitmap(R.drawable.background_layer_new);
        m_layer2 = Bitmap.createScaledBitmap(m_layer2,width,m_layer2.getHeight(),true);

        //배경 이미지의 시작 위치(y좌표)
        m_scroll = -m_bitmap.getHeight() + height;
        m_scroll_2 = -m_layer2.getHeight() + height;

        _height = height;

        setPosition(0,(int)m_scroll);
    }

    public void update(long gameTime){
        m_scroll = m_scroll + SCROLL_SPEED; //SCROLL_SPEED만큼씩 배경이 아래로 내려옴

        if( m_scroll >=0) //배경 이미지의 끝에 도달하면 다시 이미지를 처음부터 시작함
            m_scroll = -m_bitmap.getHeight() + _height;

        m_scroll_2 = m_scroll_2 + SCROLL_SPEED_2;
        if ( m_scroll_2 >=0)
            m_scroll_2 = -m_layer2.getHeight() + _height;

        setPosition(0, (int)m_scroll); //BackGround의 위치 설정
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(m_bitmap, 0, m_scroll,null );
        canvas.drawBitmap(m_layer2, 0, m_scroll_2,null );
    }
}
