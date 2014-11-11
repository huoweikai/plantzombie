package com.haopu.JSGame;



import com.haopu.kbz.*;

//import javax.microedition.lcdui.*;
import java.util.Random;
import java.util.Vector;

import android.util.Log;
public class GmPlay {
	
//	int iNandu;//jiandan ,putong,kun
//	int iGuanqia;//guanqia  4guan
//	int iDirenDelay;//用于每隔多尝试时间产生球
//	int iJingquedu;//用于统计精确度
//	int iJifen;//积分
//	int iBianliang;//用于北京图片每次滚动距离
//	int i;//统计北京滚动次数
//	int j;
//	
//	boolean isDead;
//	
//	int iTimeCount;//统计时间
//	
//	int iLianjishu;//用于统计连击数目
//	
//	int iDelay;//用于帧的播放
//	
//	
//	static MyGameCanvas canvas;
//	
//	
//	public int type ;
//	public int x,y;
//	public int speed;
//	public boolean bUseing;
//	
//   public static final int  qiu1 = 1;
//   public static final int  qiu2 = 2;
//   public static final int  qiu3 = 3;
//   public static final int  qiu4 = 4;
//   public static final int  qiu5 = 5;
//   public static final int  qiu6 = 6;
//
//
// byte imageIndex_xiaoguo1[] = {PAK_IMAGES.IMG_C4,PAK_IMAGES.IMG_C5,PAK_IMAGES.IMG_C6,PAK_IMAGES.IMG_C7,
//		 
//		 PAK_IMAGES.IMG_C4,PAK_IMAGES.IMG_C5,PAK_IMAGES.IMG_C6,PAK_IMAGES.IMG_C7//2CI CUNHUAN
//		 };
// 
// byte imageIndex_xiaoguo2[] = {PAK_IMAGES.IMG_D4,PAK_IMAGES.IMG_D5,PAK_IMAGES.IMG_D6,PAK_IMAGES.IMG_D4,PAK_IMAGES.IMG_D5,PAK_IMAGES.IMG_D6
//		 };
// 
// byte imageIndex_xiaoguo3[] = {PAK_IMAGES.IMG_B0,PAK_IMAGES.IMG_B1,PAK_IMAGES.IMG_B2,PAK_IMAGES.IMG_B3,PAK_IMAGES.IMG_B0,PAK_IMAGES.IMG_B1,PAK_IMAGES.IMG_B2,PAK_IMAGES.IMG_B3
// };
//
//
////	static GameMap map; // 地图
//
////	Vector<GameRole> enemys; // 敌人
////	Vector<GameRole> sprites; // 主角
//	
//	
//	//Vector<Yinyueqiu>yinyueqiu;//音乐球
//
////	static Vector<int[]> enemyShot;
////	static Vector<int[]> Item;
////	static Vector<int[]> roleShot;
//
//	static int time = 0;
//
////	static GameEngine me;
//	
//    static GmPlay gp;
////	static GameRole role; // 人物
//	
//	public GmPlay()
//	{
//		
//		
//		canvas = MyGameCanvas.me;
//		
//		isDead= false;
//		
//		iNandu = 0 ;
//		iGuanqia = 0;
//		iDirenDelay = 25 ;
//		iJingquedu = 0;
//		iJifen = 0;
//		iBianliang = 1;
//		i=0;
//		j=0;
//		
//		iTimeCount =1;
//		iLianjishu = 0;
//		
//		iDelay=0;
//		
//		bUseing = false;
//		
//		
//	
//		}
//		
//	
//	
//	int hitNum;
//	int hitTime;
//	public void Draw()
//	{
//		hitTime++;
//		if(hitTime<30){
//			hitNum++;
//			hitTime=0;
//		}else{
//			hitNum=0;
//			hitTime=0;
//		}
//		
//		
//		canvas.drawCleanScreen(0);
////		iDelay++;
////		iTimeCount++;
//	//	canvas.gameTime++;
//	//	DrawBg();
//	//	DrawZhuangshi();
//	//	DrawNumber();
//		
//		
//		
//		switch(type)
//		{
//		
//		case 1:
//			 GameDraw.add_Image(PAK_IMAGES.IMG_B8, x,y, 0,0,67,65,Tools.TOP_LEFT, Tools.TRANS_NONE, 100);//画qiu  1
//			
//			break;
//		case 2:
//			 GameDraw.add_Image(PAK_IMAGES.IMG_C0, x,y,67,0,67,65, Tools.TOP_LEFT, Tools.TRANS_NONE, 100);//画qiu2
//			break;
//		case 3:
//			 GameDraw.add_Image(PAK_IMAGES.IMG_C3, x,y,133,0,67,65, Tools.TOP_LEFT, Tools.TRANS_NONE, 100);//画轨道
//			break;
//		case 4:
//			 GameDraw.add_Image(PAK_IMAGES.IMG_C8, x,y, 196,0,67,65,Tools.TOP_LEFT, Tools.TRANS_NONE, 100);//画轨道
//			break;
//		case 5:
//			 GameDraw.add_Image(PAK_IMAGES.IMG_D1, x,y,0,64,68,67, Tools.TOP_LEFT, Tools.TRANS_NONE, 100);//画轨道
//			break;
//		case 6:
//			 GameDraw.add_Image(PAK_IMAGES.IMG_D2, x,y, 68,64,68,67,Tools.TOP_LEFT, Tools.TRANS_NONE, 100);//画轨道
//			break;
//		
//		
//		
//		}
//		
//	}
//	
//	public void Run()
//	{
//		iDelay++;
//		//Log.e("zc","iDelay"+iDelay);
//		//Log.e("zczczczc","aaaaaaaaa"+canvas.count);
//		
//		switch(type)
//		{
//		case 1:
//			if(isDead==false)
//			{
//				y+=speed;
//				if(y>720)
//				{
//					isDead = true;
//				}
//				else  if((y>=610&&y<710)  &&  canvas.gameStatus==canvas.GmStat_Playing && canvas.pointMenu==0 )               //&&(canvas.pointMenu==1 && canvas.gameStatus==canvas.GmStat_Playing))
//				{
//					//
//					iJifen+=50;
//					isDead=true;
//					for(int i=0;i<8;i++)
//					{	GameDraw.add_Image( imageIndex_xiaoguo1[i], 35,570,Tools.TOP_LEFT, Tools.TRANS_NONE, 100);}//画xiaoshixiaoguo
//					
//				}
//			}
//			break;
//		case 2:
//			if(isDead==false)
//			{
//				y+=speed;
//				if(y>720)
//				{
//					isDead = true;
//				}
//				else  if((y>=610&&y<710)  &&  canvas.gameStatus==canvas.GmStat_Playing && canvas.pointMenu==1 )               //&&(canvas.pointMenu==1 && canvas.gameStatus==canvas.GmStat_Playing))
//				{
//					//
//					iJifen+=50;
//					isDead=true;
//					for(int i=0;i<6;i++)
//					{	GameDraw.add_Image(imageIndex_xiaoguo2[i], 35+150,570,Tools.TOP_LEFT, Tools.TRANS_NONE, 100);}//画xiaoshixiaoguo
//					
//				}
//			}
//			break;
//		case 3:
//			if(isDead==false)
//			{
//				y+=speed;
//				if(y>720)
//				{
//					isDead = true;
//				}
//				
//				else  if((y>=610&&y<720)  &&  canvas.gameStatus==canvas.GmStat_Playing && canvas.pointMenu==2 )               //&&(canvas.pointMenu==1 && canvas.gameStatus==canvas.GmStat_Playing))
//				{
//					//
//					iJifen+=50;
//					isDead=true;
//					for(int i=0;i<8;i++)
//					{	
//						GameDraw.add_Image( imageIndex_xiaoguo3[i], 185+150,570,Tools.TOP_LEFT, Tools.TRANS_NONE, 100);}//画xiaoshixiaoguo
//					}
//				
//			}
//			break;
//		case 4:
//			if(isDead==false)
//			{
//				y+=speed;
//				if(y>canvas.SCREEN_HEIGHT)
//				{
//					isDead = true;
//				}
//			}
//			break;
//		case 5:
//			if(isDead==false)
//			{
//				y+=speed;
//				if(y>canvas.SCREEN_HEIGHT)
//				{
//					isDead = true;
//				}
//			}
//			break;
//		case 6:
//			if(isDead==false)
//			{
//				y+=speed;
//				if(y>canvas.SCREEN_HEIGHT)
//				{
//					isDead = true;
//				}
//			}
//		break;
//		
//		}
//	}
//	
//	
//
///*
// * 	
// */
///*	
//	public void DrawYinyueqiu()
//	{
//		switch(iNandu)
//		{
//		case 0:   //jiandan
//		
//			
//			if(iDelay %2 ==0)
//			{
//			   GameDraw.add_Image(PAK_IMAGES.IMG_C8,
//					   0, 0               +  iNandu*5*canvas.gameTime       ,7,17,54,63,
//					   Tools.TOP_LEFT, Tools.TRANS_NONE, 510); //画轨道1音乐球
//			}
//			
//			else if(iTimeCount % 3 ==0){
//		    	GameDraw.add_Image(PAK_IMAGES.IMG_D1,
//					    0                      +       30, 0         + iNandu*8*canvas.gameTime  ,
//					    Tools.TOP_LEFT, Tools.TRANS_NONE, 510); //画轨道2音乐球
//			}
//			
//			else if(iTimeCount % 4 ==0){
//			GameDraw.add_Image(PAK_IMAGES.IMG_D2,
//					0               +          60, 0             + iNandu*10*canvas.gameTime ,
//					Tools.TOP_LEFT, Tools.TRANS_NONE, 510); //画轨道3音乐球
//			}
//			break;
//		case 1:  //putong
//		case 2:  //kunnan
//			break;
//		
//		}
//		
//		
//	}
//
//
//	*/
//	
//	public void CreateQiu()
//	{
//		GameDraw.add_Image(PAK_IMAGES.IMG_C8,
//				   0, 0               +  5*canvas.gameTime       ,7,17,54,63,
//				   Tools.TOP_LEFT, Tools.TRANS_NONE, 510); //画轨道1音乐球
//	}

}
