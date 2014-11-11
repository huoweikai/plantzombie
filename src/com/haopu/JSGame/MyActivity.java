package com.haopu.JSGame;

import cn.cmgame.billing.api.GameInterface;

import com.haopu.JSGame.R;

import android.app.Activity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class MyActivity extends Activity {
	/** Called when the activity is first created. */

	public static int VMWidth;
	public static int VMHeight;

	public static MyActivity instance = null;
	public  MyGameView _mView;
//	public static Activity instance;
	public AlertDialog.Builder build;
//震动
	Vibrator mVibrator;//声明振动器
	boolean shakeflag=true;//是否震动
//	private StringBuffer buffer;  
//	public static GameNet net;
//	public static GameBilling billing;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		instance=this;
	   GameInterface.initializeApp(this);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Display dis = getWindowManager().getDefaultDisplay();
		VMWidth = dis.getWidth();
		VMHeight = dis.getHeight();
	
//		GameBilling.initBilling(MyActivity.instance);
		_mView = new MyGameView(this);

		collisionShake();
		setContentView(new MyGameView(this));
		build=new AlertDialog.Builder(this);
		super.onCreate(savedInstanceState); 
		MyGameCanvas.me.mm = new ChinaMM();
//		GameBilling.initBilling(MyActivity.instance);
//		billing = new GameBilling();
//		net = new GameNet(this);
	}
	protected void onStart() {   
		super.onStart(); 
//		initProperty();  
//		getMemoryInfo();  
//		TextView tv = new TextView(this); 
//		tv.setText(buffer.toString());  
//		this.setContentView(tv);
		}
	
	
	//手机震动
    public void collisionShake()
    {
    		mVibrator=(Vibrator)getApplication().getSystemService
            (Service.VIBRATOR_SERVICE);	
    }
    //震动
    public void shake()
    {
    	if(shakeflag)
    	{
    		mVibrator.vibrate( new long[]{0,80},-1);
    	}
    }
  		public void everExit()
  		{  
  				AlertDialog.Builder build=new AlertDialog.Builder(this);
  				build.setMessage(MyGameCanvas.gameStatus==MyGameCanvas.GmStat_Stop?"你确定要返回主菜单吗？":"你确定要退出游戏吗？").setIcon(R.drawable.icon).
  				setPositiveButton("是",new DialogInterface.OnClickListener() {
  					@Override
  					public void onClick(DialogInterface dialog, int which) {
  						MyGameCanvas.me.saveGame();
  						if(MyGameCanvas.gameStatus==MyGameCanvas.GmStat_Stop){
  							MyGameCanvas.setST(MyGameCanvas.GmStat_Menu);
  						}else{
//  							exitGame();
  							System.exit(0);
  						}
  					}
  				}).setNegativeButton("否",new DialogInterface.OnClickListener() {
  					
  					@Override
  					public void onClick(DialogInterface dialog, int which) {
  					}
  				}).show();
  		}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if (this._mView.gameCanvas != null && !this._mView.gameCanvas.onBackKeyPressed()) {
				return false;
			}
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	// static boolean isPause;
	public void onResume() {
		super.onResume();
		this._mView.bRunning = true;
		if(this._mView.waf.isMusicEnabled()){
			this._mView.waf.ResumeBackMusic();
		}
	}

	public void onPause() {
		if(MyGameCanvas.gameStatus==MyGameCanvas.GmStat_Playing){
//	MyGameCanvas.gameStatus=MyGameCanvas.GmStat_MidMenu;
	MyGameCanvas.setST(MyGameCanvas.GmStat_Stop);
}
		super.onPause();
		this._mView.bRunning = false;
		this._mView.waf.PauseBackMusic();
		
		
	}
	public void onDestroy() {
		android.util.Log.d("JT", "engtst onDestroy (isFinishing:"+this.isFinishing()+")");
		super.onDestroy();
		this._mView.waf.Free();
	}

	public final static boolean isScreenLocked() {
		KeyguardManager mKeyguardManager = (KeyguardManager) MyGameView.context
				.getSystemService(KEYGUARD_SERVICE);
		return mKeyguardManager.inKeyguardRestrictedInputMode();

	}
	public static void openUrl(Context context, String url) {
		if (context == null)
			return;
		try {
			context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}