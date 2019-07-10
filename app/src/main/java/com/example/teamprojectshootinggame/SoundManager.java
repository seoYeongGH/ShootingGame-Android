package com.example.teamprojectshootinggame;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.HashMap;

public class SoundManager {
    private static SoundManager s_instance;//singleton
    private SoundPool m_SoundPool;
    private HashMap m_SoundPoolMap;
    private AudioManager m_AudioManager;
    private Context m_Activity;
    private MediaPlayer m_Sound_BackGround;

    public void init(Context _context){
        m_SoundPool = new SoundPool(4,AudioManager.STREAM_MUSIC,0);
        m_SoundPoolMap = new HashMap();
        m_AudioManager = (AudioManager)_context.getSystemService(Context.AUDIO_SERVICE);
        m_Activity = _context;
        m_Sound_BackGround = new MediaPlayer();
    }

    public void addMusicLoop(int _MusicID)
    {
        m_Sound_BackGround = MediaPlayer.create(m_Activity,_MusicID);
        m_Sound_BackGround.start();
        m_Sound_BackGround.setLooping(true);
    } //배경음 반복 재생


    public void addMusic(int _musicID)
    {
        m_Sound_BackGround = MediaPlayer.create(m_Activity,_musicID);
        m_Sound_BackGround.start();
        m_Sound_BackGround.setLooping(true);
    }

    public void pauseMusic(int _MusicID)
    {
        m_Sound_BackGround.pause();
    }

    public void addSound(int _index, int _soundID) {
        int id = m_SoundPool.load( m_Activity, _soundID, 1); // 효과음을 로드
        m_SoundPoolMap.put(_index, id); // 해시맵에 아이디 값을 받아온 인덱스저장
    }//효과음 재생한다.

    public void play(int _index) {
        float streamVolume = m_AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolume = streamVolume / m_AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        m_SoundPool.play((Integer) m_SoundPoolMap .get(_index),
                0.25f,0.25f, 1, 0, 1f);
    } //음악을 한번만 실행

    public void playLooped( int _index) {
        float streamVolume = m_AudioManager.getStreamVolume(AudioManager. STREAM_MUSIC);
        streamVolume = streamVolume / m_AudioManager.getStreamMaxVolume(AudioManager. STREAM_MUSIC);
        m_SoundPool.play((Integer)m_SoundPoolMap .get(_index),
                0.25f, 0.25f, 1, -1, 1f);
    } //음악을 반복적으로 실행

    public static SoundManager getInstance(){
        if (s_instance == null) s_instance = new SoundManager();
        return s_instance;
    } //SoundManager 싱글턴 패턴
}