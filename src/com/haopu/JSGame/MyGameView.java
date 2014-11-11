package com.haopu.JSGame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class MyGameView extends SurfaceView implements Callback, Runnable,
		OnTouchListener, OnGestureListener {

	private SurfaceHolder Sfh;
	public static Canvas canvas;
	public  static Paint paint;
	private GestureDetector gd;
	private Thread thread;
	Thread threadTime;
	boolean flag ;
	public static Context context;
	WavFast  waf;

	public static int ScreenWidth = 0;
	public static int ScreenHeight = 0;
	boolean bRunning = true;


	private boolean isThread;
	public static float dw;
	public static float dh;
	
	
	private int sleepSpan=1000;	

	MyGameCanvas gameCanvas;
	public MyGameView(Context context) {

		super(context);
		MyGameView.context = context;
		// TODO Auto-generated constructor stub
		this.setKeepScreenOn(true);
		thread = new Thread(this);
		threadTime = new Thread(this);
		Sfh = this.getHolder();
		Sfh.addCallback(this); //
		paint = new Paint();


		setFocusable(true);// 按键的焦点（onKeyDown）
		// initGame();// 初始化

//		MyGameCanvas.mySql = new MySQL(context);
//		MyGameCanvas.mySql.initData();
//		MyGameCanvas.mySql.initData();
//		MyGameCanvas.mySql.initData();
		
		
		
		// 我们当前的SurfaceView(view)才能够处理不同于触屏形式;
		// 例如：ACTION_MOVE，或者多个ACTION_DOWN
		gd = new GestureDetector(this);
		// gd = new GestureDetector(new MySimpleGesture());
		gd.setIsLongpressEnabled(true);
		setOnTouchListener(this);// 将本类绑定触屏监听器
		setLongClickable(true); // setLongClickable( true )是必须的，因为 只有这样，
		paint.setAntiAlias(true);// 设置画笔无锯齿(如果不设置可以看到效果很差)
		this.setKeepScreenOn(true);// 保持屏幕常亮
		// this.setFullScreenMode(true);
	
		gameCanvas=new MyGameCanvas(this,context);
		waf = new WavFast(context);

//		canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
		//gameView=this;

	}

	/**
	 * 系统函数
	 */
	public void initSetting() {
		Sfh = this.getHolder();
		Sfh.addCallback(this); //
		paint = new Paint();
		ScreenWidth = 800;
		ScreenHeight = 480;
		/**
		 * setLongClickable( true )是必须的，因为
		 * 只有这样，我们当前的SurfaceView(view)才能够处理不同于触屏形式
		 * ;例如：ACTION_MOVE，或者多个ACTION_DOWN
		 */
		setFocusable(true);// 按键的焦点（onKeyDown）
		this.setOnTouchListener(this);// 将本类绑定触屏监听器
		this.setLongClickable(true);
		// setFocusableInTouchMode（true）;//触屏模式获取焦点.
		gd = new GestureDetector(this);
		gd.setIsLongpressEnabled(true);
		paint.setAntiAlias(true);// 设置画笔无锯齿(如果不设置可以看到效果很差)
		this.setKeepScreenOn(true);// 保持屏幕常亮
	}

	/***
	 * 游戏中画图 加载图片方法
	 */
	

	private void Ondraw() {
		try {
			gameCanvas.paint();
		} catch (Exception e) {
//			System.out.println("gameStatus:" + gameCanvas.gameStatus + "   " + gameCanvas.index);
			e.printStackTrace();
		}
	}

	/***
	 * 游戏中按键
	 */
	@Override
	// 触摸屏

	/**
	 * Touch了滑动一点距离后，up时触发
	 * 
	 * @e1：ACTION_DOWN MotionEvent 按下的动作
	 * @e2：ACTION_UP MotionEvent 抬起的动作
	 * @velocityX：X轴上的移动速度，像素/秒
	 * @velocityY：Y轴上的移动速度，像素/秒
	 */

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		// switch (Conmmon.GameStatus) {
		// case Conmmon.ST_GAMEPLAY:
		if (e1.getX() - e2.getX() > 100) {
		}
		// break;
		return false;
	}

	/**
	 * Touch了滑动时触发
	 * 
	 * @e1：ACTION_DOWN MotionEvent 按下的动作
	 * @e2：ACTION_MOVE MotionEvent 移动的动作
	 * @distanceX：X轴上的移动距离，是本次与上次移动的间隔距离
	 * @distanceY：Y轴上的移动距离，是本次与上次移动的间隔距离
	 */

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		// int firstPos = getFirstVisiblePosition();
		// GameMenu.ctrlMoveGame(e2.getX(), e2.getY(), distanceX, distanceY);
		return false;
	}

	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	
		switch(keyCode)
		{
		case KeyEvent.KEYCODE_BACK:
	//		if()
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub

		return gd.onTouchEvent(event);//
	}
	public static boolean iskep=false;
	public static int iskepX;
	public static int iskepY;
	public static int rankY=0;
	public static int rankMove=0;
	public static int rankdown=0;
	public static int wuQi_Y=0;
	public static int wuQi_Move=0;
	public static boolean wuQi_xuanZe = true;
	public boolean onTouchEvent(MotionEvent event) {// 手机屏幕触摸事件
		int x = (int) event.getX();
		int y = (int) event.getY();
		if (event.getAction() == MotionEvent.ACTION_DOWN) {// 按下
//			MyGameCanvas.downx=x;
//			MyGameCanvas.downy = y;
			wuQi_xuanZe = true;
			iskep = true;
			iskepX=x;
			iskepY=y;
			rankdown=y;
	       	MyGameCanvas.isUp_Down =false;
			gameCanvas.pointerPressed(x, y);
			 
			gameCanvas.iTouchDownX = x;
			gameCanvas.iTouchDownY = y;
	        if(GameEngine.me.gameRank==5&&GameEngine.isSms==true&&
	        		MyGameCanvas.gameStatus==MyGameCanvas.GmStat_Playing){
	        	GameEngine.isSms= false;
	        	if(BillingResult.sms_RMS[BillingResult.Billing_正版激活]!=BillingResult.SMS_SUCCESS){
	        		MyGameCanvas.setST(MyGameCanvas.GmStat_MidMenu);
	        		MyGameCanvas.me.mm.sendBillingMsg(BillingResult.Billing_正版激活);
	        	}  
	        } 
		} else if (event.getAction() == MotionEvent.ACTION_UP) {// 松开

			        rankMove += rankY; 
			        wuQi_Move += wuQi_Y; 
			        MyGameCanvas.me.rank1 = rankMove/67;
			        MyGameCanvas.isUp_Down =true;
			        wuQi_Y = 0;
			        rankY = 0;
			        iskep = false; 
					gameCanvas.pointerReleased(x, y);
					if(GameEngine.me.JN1>60){
					gameCanvas.iTouchUpX = x;
					gameCanvas.iTouchUpY = y;	
					}
					if(rankMove>0){
						rankMove=0;
					}//关卡滑动复位
					if(rankMove<-201){
						rankMove=-201;
					}//关卡滑动复位
//					System.out.println("wuQi_Move : "+wuQi_Move);
					if(wuQi_Move<=-480){
						wuQi_Move=-480;
					}//装备滑动复位
					if(wuQi_Move>=10){
						wuQi_Move = 0;
					}//装备滑动复位
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if(y-rankdown>5||y-rankdown<-5){
			wuQi_xuanZe = false;	
			}
			
			 MyGameCanvas.isUp_Down =true;
			if(gameCanvas.gameStatus==gameCanvas.GmStat_RankMap&&x*MyGameCanvas.beishuY<200){
				if(rankMove+rankY<10&&rankMove+rankY>-211){
					rankY = (int) ((y-rankdown)*MyGameCanvas.beishuY);
				}//关卡选择滑动	
			}
			if(gameCanvas.gameStatus==gameCanvas.GmStat_ZhuJiao&&x*MyGameCanvas.beishuY<300){
				if((wuQi_Y+wuQi_Move)>(-500)&&(wuQi_Y+wuQi_Move)<20&&y>70){
					wuQi_Y =  (int) ((y-rankdown)*MyGameCanvas.beishuY);	
				}
			}//装备滑动
		    MyGameCanvas.moveX = x;
		    MyGameCanvas.moveY = y;
		    
//		    MyGameCanvas.moveZ +=rankY = (int) ((y-rankdown)*MyGameCanvas.beishuY);
		    if(MyGameCanvas.moveZ>1000){
		    	MyGameCanvas.moveZ = 180;
		    }
		    if(MyGameCanvas.moveZ<-1000){
		    	MyGameCanvas.moveZ = -180;
		    }
			iskepX=x;
			iskepY=y;
			GameEngine.me.skillPushX=x;
			GameEngine.me.skillPushY=y;
//			        gameCanvas.pointerMoved(x, y);
		}
		return super.onTouchEvent(event);
	}
	

	
	
	
	int gameTime;
	public void run() {
		
		// TODO Auto-generated method stub
		while (isThread) {
			canvas = null;
			long t = System.currentTimeMillis();
			try {
				   canvas = Sfh.lockCanvas();
				   synchronized (Sfh) {
					     Ondraw();// 调用绘制方法
//					     long	 delay2 = System.currentTimeMillis() - t;
//					     long FPS=1000/(delay2);
//					     Tools.drawColorStringFPS(canvas, paint,"fps:"+FPS, 50,100,30, Tools.TOP_LEFT, 0xffff00ff);
				   }
				   long delay = System.currentTimeMillis() - t;
				   if (delay <=20) { 
						  Thread.sleep(20 - delay);
				   }
			} catch (Exception e) {
			} finally {// 用finally保证下面代码一定被执行
				if (canvas != null) {
					// 更新屏幕显示内容
					Sfh.unlockCanvasAndPost(canvas);
				}
			}
	}
		

	}
	public void runThread()
	{
		threadTime = new Thread(){
			public void run(){
					while(flag){
						 try{
				            	Thread.sleep(1000);
				            }
				            catch(Exception e){
				            	e.printStackTrace();//打印堆栈信息
				            }
				            gameCanvas.subtractTime(1);
					}
			}
		};
		threadTime.start();
	}	 	
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	public void surfaceCreated(SurfaceHolder holder) {
		//if(gameCanvas.imusic==0){gameCanvas.sound.playmusic(SoundPlayerUtil.music_kaiji);}//播放开机封面音乐
		// 在创建时激发，一般在这里调用画图的线程
		// TODO Auto-generated method stub
		isThread = true;
		flag = true;
		thread = new Thread(this, "kbz");
		thread.start();
		runThread();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// 销毁时激发，一般在这里将画图的线程停止、释放
		// TODO Auto-generated method stub
		isThread = false;
		flag = false;
	}

	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
}
