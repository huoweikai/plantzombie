package com.haopu.JSGame;

import com.haopu.JSGame.R;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class WavFast { 
	public static final int MAX_SOUND_COUNT = 15;
	private Context cnt;
	private SoundPool sp;
	public MediaPlayer mp;
	private int iSoundIds[];
	public  boolean bSoundEnabled = true;
	public  boolean bMusicEnabled = true;
	  
	public static final int[] iSoundResIds = {//音效
		R.raw.anniu,R.raw.bingdongjineng,R.raw.bingdongjineng,
		R.raw.dianwangpao,R.raw.lieyuta,R.raw.zhujidi,
		R.raw.shandianjineng,R.raw.bingdongjineng,R.raw.bingdongjineng,
		R.raw.huanpao,R.raw.baozha,R.raw.win,
		R.raw.bingdongjineng,R.raw.siwang,R.raw.jizhong,
		//14
	};
	
	public static final int[] iMusicResIds = {//音乐
		R.raw.firstbg,R.raw.menu,R.raw.menu,R.raw.menu,R.raw.menu
	  };
	
	 
	public WavFast(Context ct) {
		this.cnt = ct;
		this.sp = new SoundPool(MAX_SOUND_COUNT, AudioManager.STREAM_MUSIC, 100);
		this.iSoundIds = new int[MAX_SOUND_COUNT];
		for (int i = 0; i < MAX_SOUND_COUNT; i++) {
			iSoundIds[i] = sp.load(cnt, iSoundResIds[i], 0);
		}
//		if (!GameInterface.isMusicEnabled()) {
//			bSoundEnabled=false;
//			bMusicEnabled=false;
//		} 
	}
	
	public void StartBackMusic(int idx, boolean bIsLoop) {
		if(MyActivity.VMHeight>240){
			if (this.bMusicEnabled) {
				StopBackMusic();
				mp = MediaPlayer.create(this.cnt, iMusicResIds[idx]);
				mp.setLooping(bIsLoop);
				mp.start();
			}	
		}
	}
	public void StopBackMusic() {
		if (mp != null) {
			mp.stop();
			mp.release();
			mp = null;
		}
	}
	public void PauseBackMusic() {
		if (mp != null) {
			try {
				mp.pause();
			} catch (IllegalStateException e) {}
		}
	}
	public void ResumeBackMusic() {
		if (mp != null) {
			try {
				mp.start();
			} catch (IllegalStateException e) {}
		}
	}
	
	public void StartWav(int idx, boolean bIsLoop) {
		if(MyActivity.VMHeight>240){
				if (this.bSoundEnabled) {
			android.util.Log.d("JT", "StartWav(" + idx + ", " + bIsLoop + ")");
			sp.play(iSoundIds[idx], 1.0f, 1.0f, 1, bIsLoop?-1:0, 1);
		}	
		}

	}
	public void StartWavEx() {
	}
	public void StopWav(int idx) {
		sp.stop(iSoundIds[idx]);
	}
	
	// 音效的启用与禁用 --------------------
	public boolean isSoundEnabled() {
		return this.bSoundEnabled;
	}
	public void enableSound() {
		this.bSoundEnabled = true;
	}
	public void disableSound() {
		this.bSoundEnabled = false;
		for (int i = 0; i < MAX_SOUND_COUNT; i++) {
			this.StopWav(i);
		}
	}
	
	// 背景音乐的启用与禁用 --------------------
	public boolean isMusicEnabled() {
		return this.bMusicEnabled; 
	}
	public void enableMusic() {
		this.bMusicEnabled = true;
		this.ResumeBackMusic();
	}
	public void disableMusic() {
		this.bMusicEnabled = false;
		this.PauseBackMusic();
	}
	
	// 释放资源
	public void Free() {
		for (int i = 0; i < MAX_SOUND_COUNT; i++) {
			sp.unload(this.iSoundIds[i]);
		}
		sp = null;
		
		if (mp != null) {
			mp.release();
			mp = null;
		}
	}
}
