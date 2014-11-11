package com.haopu.JSGame;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import com.haopu.Enemy.FISH;
import com.haopu.Enemy.UsBoss;
import com.haopu.Enemy.fishData;
import com.haopu.kbz.GameDraw;
import com.haopu.kbz.GameHit;
import com.haopu.kbz.GameInterface;
import com.haopu.kbz.GameMapTile;
import com.haopu.kbz.GameNumber;
import com.haopu.kbz.GameRandom;
import com.haopu.kbz.Tools;
import com.haopu.pak.PAK_IMAGES;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.service.wallpaper.WallpaperService.Engine;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

public class GameEngine {
	public static final int skillR = 40;// 技能攻击范围半径
	public static final float PI = 3.141592654f;
	public static int Speed = 5;// 箭矢的速度
	public static GameEngine me;
	static MyGameCanvas canvas;
	public static GameMap map;// 地图
	public static UsBoss usBs;
	public static GameRole role;// 人物
	static FISH fish;// yu
	public fishData fd;
	public BulletManager bullm;
	// ParticleSet ps;
	public static int time = 0;// 屏幕震荡的时间
    public static int [][]JiJiaXY;
	// 出兵参数
	int iWaveTime = 500;// 波与波的时间间隔
	int iTime;// 相邻敌人时间间隔
	int iWave = 0;// 当前出兵波数
	int iWaveNum;// 某一波中某个敌人的排列号
	int iWavePlace[] = new int[8];// 每一波中某个出兵点对应的敌人序数
	// 动态数组
	public Vector<GameRole> sprites; // 主角
	public Vector<GameRole> enemys;// 敌人
	static Vector<int[]> Item;
	Vector<int[]> PTZD ;
	public Vector<BulletManager> bullets;
	public Vector<GameRole> unLawBuilds;// 违建
	public Vector<JiangLiRank99> jiangLi;// 奖励
	public int iMapX = 0;// 滑屏时候
	public int iMapY = 0;
	// int iLock[]=new int[]{1,1,0,0,0,0};//开始锁住4个塔、
	int iFreakMoney;
	// 剧情模式下的相关参数                                    roleNumber 减到1时则胜利
	public static int roleType,roleNumber,Zrole,roleTime;//特殊关卡需要消灭的敌人，数量 如果roleNumber为0则正常出兵roleType为0则包含所有敌人种类
	int zd;
	int iCatchMove = 0;// 关卡选择界面的滑屏距离
	int iCatchStart = 0;// 关卡起始坐标
	boolean bCatchSlip = false;// 判断滑屏是否有效
	int iDriftX;// 位移差值,即剩下的需要移动的距离
	public static int fuhuo=5;
	int iResult[][] = new int[][] { 
			{  0, -1, -1, -1, -1, -1 }, 
			{ -1, -1, -1, -1, -1, -1 }, 
			{ -1, -1, -1, -1, -1, -1 },
			{ -1, -1, -1, -1, -1, -1 }};// 3大地图24关的评价,0表示开启但为0评价，而-1表示关卡未开
//	int iResult[][] = new int[][] { 
//			{ 0, 0, 0, 0, 0, 0 },
//			{ 0, 0, 0, 0, 0, 0 }, 
//			{ 0, 0, 0, 0, 0, 0 },
//			{ 0, 0, 0, 0, 0, 0 } };// 3大地图24关的评价,0表示开启但为0评价，而-1表示关卡未开
	
	int TSRabk[] = new int[]{-1,-1,-1,-1,-1,-1,-1,-1};//一共八关奖励关卡
//	int TSRabk[] = new int[]{0,0,0,0,0,0,0,0};//一共八关奖励关卡
	int JL;
	public static int teSuGuaiXG;
	public static boolean JiaoXueDian=false;
	public int gameRank = 0;// 玩家当前所选的等级
	public int gameRankOpen = 0;// 当前已开的关卡
	int mapRank = 0;// 大地图的等级gameRankOpen/8时候++
	// 挑战模式
	int iFightResult[] = new int[] { 1, 0, 0 };
	public int iFightGameRank;
	public int iFightGameRankOpen;
	public int iFightMapRank;
	// 地图大小
	int mapWidth;// 地图总长度
	int mapHeight;// 地图总高度
	int iPropNum;// 地图上可造塔的块数目
	int iRanTieshi;// 小贴士的随机数
	boolean bFreakMoneyExtra = false;// 是否是倍率增幅在身
	// 成就相关参数
	int iAchieve[] = new int[12];// 12条成就
	int iAchieveNum;// 1-12,对应12个成就
	int iTowerNum;// 升级到顶的塔的个数
	int iRunNum;// 每关跑掉的敌人数
	int iHit;// 总共出兵数
	int iTaSellNum;// 一关中卖掉的塔的个数
	boolean bBuildBing = false;// 一关胜利后判断是否有过建造冰晶塔
	int iNumJianyu;// 杀死的剑鱼数量
	int iNumShayu;// 杀死的鲨鱼数量
	int iNumBookUse;// 一关中卷折的使用个数

	int skillX, skillY;// 释放技能时候离手坐标
	int skillType;// 标示离手的是哪个技能
	int skillPushX, skillPushY, skillPushType;// 技能初始大圈圈动画的坐标
	public boolean bTeach = false;// 是否进行教学
	int teachStep;// 教学步骤
	boolean bTowerSus;// 教学造塔是否成功
	boolean bTowerClear;// 教学铲子是否成功清除掉塔
	public static int laoJiaHP = 0;
	public static int laoJiaHP_MAX = 100;

	public GameEngine() {
		me = this;
		canvas = MyGameCanvas.me;
		map = new GameMap(MyGameCanvas.SCREEN_WIDTH,
				MyGameCanvas.SCREEN_HEIGHT, this);
		role = new GameRole(this);
		bullm = new BulletManager();
		fish = new FISH();
		fd = new fishData();
		usBs = new UsBoss();
		// ps = new ParticleSet();
		bullets = new Vector<BulletManager>();
		sprites = new Vector<GameRole>();
		enemys = new Vector<GameRole>();
		unLawBuilds = new Vector<GameRole>();
		jiangLi = new Vector<JiangLiRank99>();
		iResult[0][0] = 0;
		PTZD =  new Vector<int[]>();
	}

	// 游戏初始化
	boolean isKey = false;
	int dingTime;
	final static byte FLY = 80;
	int wangX, wangY;

	/**
	 * 每关结束后的数据的重置
	 * 
	 * @param index
	 */
	public void initGame(int index) {
		switch (index) {
		case 0:
			GameEngine.isSms=true;
			for (int i = 0; i < canvas.ZB.length; i++) {
				canvas.ZB[i] = canvas.JLZB[i];
			}
//		     JL=-1;
			rank0=0;
			canvas.diTuSuiPian=0;
			canvas.random = GameRandom.result(0, 8);// 随机数
			if (gameRank == 99) {
				for (int i = 0; i < GameEngine.me.enemys.size(); i++) {
					GameRole enemy = (GameRole) GameEngine.me.enemys
							.elementAt(i);
					if (enemy.x > 1000) {
						enemy.setStatus(enemy.STATUS_DEAD);
					}
				}
				ziDan = 30+canvas.dRank*3;
			}
			if (canvas.TiaoZhan != 0) {
				// gameRank = canvas.TiaoZhan;
				// canvas.TiaoZhan = 0;
			}
			// Tools.removeAllImage();
			break;
		case 1:
			if (gameRank >= 1) {// 即第一关通关,则关闭教学,关闭判断是否第一次进入游戏
			// MyGameCanvas.me.bFirstEnter=false;
			// bTeach = false;
			// teachStep = 0;
			// bTowerSus = false;
			// bTowerClear =false;
			// MyGameCanvas.me.saveGame();
			}
			break;
		case 2:
			bullm.isrank=true;
			countSms = 0;
			random=GameRandom.result(0, 100);
			canvas.countY=-60;

			canvas.scaleX=0.0f;
			canvas.scaleY=0.0f;
			canvas.rota=360;
			canvas.TSpointMenus =-1;
			 MyGameCanvas.isyiDaBo = true;
			isWuYa = false;
			WuYaX=-50;WuYaXIndex=0;//恢复乌鸦数据
			GameEngine.usBs.sheSuJiaBei = false;
			GameEngine.usBs.laoJiaWuDi = false;
			GameEngine.me.contlaojia = 0;
			dingTime = 0;
			starTime = 0;
			countRole = 0;
			isNewGame = false;
			for (int i = 0; i < canvas.ZB.length; i++) {
				canvas.JLZB[i] = canvas.ZB[i];
			}
			break;
		case 4:
			//1.步兵2.牧师3.刺客4.光头5.鹰6.石盾7.金盾8.绿盾9.紫盾10.暗牧11.狮鹫
			if(gameRank<=1){
				GameRole.iEneHpLength=new int []{5,8,35,200,40,10,90,110,120,160,12,160,700};
			}else{
				GameRole.iEneHpLength=new int []{10,22,35,200,40,65,90,110,120,160,150,160,700};
			}

			isKey = false;
			MyGameCanvas.EffectV.removeAllElements();

			unLawBuilds = new Vector<GameRole>();
			enemys = new Vector<GameRole>();
//			sprites = new Vector<GameRole>();
			bullets = new Vector<BulletManager>();
//			role.shot = new Vector<int[]>();
			break;
		case 18:
			// getPropNum();
			int backg[] = { PAK_IMAGES.IMG_CHANGJING1, PAK_IMAGES.IMG_CHANGJING2,
					PAK_IMAGES.IMG_CHANGJING3, PAK_IMAGES.IMG_CHANGJING4 };
			imgIndex = backg[(canvas.gamerank)];
			laoJiaHP_MAX = (100 + canvas.jiNengKaiQi[3] * 15);
			// 初始化奖励关卡的道具种类
			if (gameRank == 99) {
				initRnak99();
			}
            if(gameRank ==99&& iResult[0][2]==-1){
            	iResult[0][2] = 0;
            }
			break;
		case 6:
			initData();
			break;
		case 8:
			if(gameRank==2||gameRank==19){
			  paoTaiGJ = false;	
			}else{
			  paoTaiGJ = true;	
			}

			for (int i = 0; i < canvas.zhang.length; i++) {
				canvas.zhang[i] = 2.0f;
			}
			isSctrpe = false;
			role.isAuto = false;
			switch (gameRank) {
			case 0:
			case 3:
			case 4:
			case 5:
				wangX = 705;
				wangY = 160;
				break;
			case 9:
			case 10:
			case 11:
				wangX = 705;
				wangY = 200;
				break;
			case 15:
			case 16:
			case 17:
				wangX = 705 + 15;
				wangY = 255;
				break;
			default:
				wangX = 330;
				wangY = 400;
				break;
			}
			break;
		case 10:
			GetEnemyData(MyGameCanvas.gmStatus);
			zd=0;
			if(roleNumber!=0){
				zd=1;
			}
			if(roleNumber!=0||fishData.isJiJia){
				Zrole=roleNumber;
				canvas.rankTiShi=false;
			}else{
				canvas.rankTiShi = true;
			}
			
			break;
		case 12:// 算出违建
			try {
				getUnLawBuild();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("getUnLawBuild");
			}

			break;
		case 14:
			//第一关新手体验
			DianJi=240;
			xinShouJiangLi=0;
			shenli = true;
			dengdai=0;
			fangda=0;
			if(gameRank==1){
			xinShouJiangLi=2;	
			}
			if(iResult[0][1]!=-1){
			isRank1	=true;
			}
//			if(iResult[0][2]==-1&&gameRank==1){
//				usBs.iUsBsRank++;
//				Speed = 8;
//				 usBs.iUsBsCurAttack = 30;
//			}
			try {
				// getPropNum();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("getPropNum");
			}

			break;
		case 16:
	    	if(GameEngine.roleNumber!=0){
                switch (gameRank) {
				case 8:
					GameEngine.roleTime=80;
					break;
				case 14:
		    		GameEngine.roleTime=90;
					break;
				case 20:
		    		GameEngine.roleTime=90;
					break;
				default:
		    		GameEngine.roleTime=100;
					break;
				}

				canvas.counI=0;
	    	   }else{
	   			canvas.counI=-1;
	    	    GameEngine.roleTime=0;
	    	   }
			if (gameRank != 99) {
				MyGameCanvas.me.checkRole = MyGameCanvas.me.checkdiRen();
			} else {
				MyGameCanvas.me.checkRole = jiangLi.size();
			}

			int size2 = MyGameCanvas.EffectV2.size();
			if (MyGameCanvas.EffectV2.size() < 1)
				return;
			for (int j = size2-1; j >= 0; j--) {
				MyGameCanvas.EffectV2.removeElementAt(j);
			}
			int size = MyGameCanvas.EffectV.size();
			if (MyGameCanvas.EffectV.size() < 1)
				return;
			for (int j = size; j >= 0; j--) {
				MyGameCanvas.EffectV.removeElementAt(j);
			}
			Tools.removeAllImage();

//			Tools.getImage(PAK_IMAGES.IMG_YING3);
			break;

		}
	}

	/**
	 * 根据状态获取敌人数据,初始化地图等等
	 * 
	 * @param status
	 */
	public void GetEnemyData(int status) {
//		System.out.println("倒萨");
		switch (status) {
		case MyGameCanvas.gmScripe:
			map.init(gameRank);
			getMapSize(gameRank);
			fd.getEnemyData(MyGameCanvas.gmScripe, gameRank);
			fd.getWavePlace(MyGameCanvas.gmScripe, gameRank, iWave);
			break;
		case MyGameCanvas.gmFight:
			map.init(iFightGameRank);
			getMapSize(iFightGameRank);
			fd.getEnemyData(MyGameCanvas.gmFight, iFightGameRank);
			break;
		}
	}

	
	
	void initRnak99() {
		jiangLi.removeAllElements();
		for (int i = 0; i < 90+canvas.dRank*20; i++) {
			jiangLi.addElement(new JiangLiRank99(15000-90*i, // x
					GameRandom.result(0, 10) > 5 ? 280 : 150,// y
					GameRandom.result(0, 10) > 5 ? 2 : 3,// speed
					getType())); // type
		}
	}

	int getType() {
		int type = 0;
		int random = GameRandom.result(0, 100);
		if (random <= 25) {
			type = 0;
		}
		if (random > 25 && random <= 35) {
			type = 1;
		}
		if (random > 35 && random <= 60) {
			type = 2;
		}
		if (random > 60 && random <= 70) {
			type = 3;
		}
		if (random > 70 && random <= 80) {
			type = 4;
		}
		if (random > 80 && random <= 90) {
			type = 5;
		}
		if (random > 90 && random <= 95) {
			type = 6;
		}
		if (random > 95 && random <= 100) {
			type = 7;
		}
		return type;
	}

	/**
	 * 获取每一关地图的宽高
	 * 
	 * @param gameRank
	 * @param mapWidth
	 * @param mapHeight
	 */
	public void getMapSize(int gameRank) {
		// int sw = fishGame.VMWidth;
		// int sh = fishGame.VMHeight;
		// float kx = (float) sw / MyGameCanvas.SCREEN_WIDTH;
		// float ky = (float) sh / MyGameCanvas.SCREEN_HEIGHT;
		//
		switch (MyGameCanvas.gmStatus) {
		case MyGameCanvas.gmScripe:
			switch (gameRank) {
			case 0:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 10 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			case 1:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 10 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			case 2:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 10 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			case 3:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 10 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			case 4:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 9 * 60;
				usBs.iUsBsY = 6 * 60;
				break;
			case 5:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 9 * 60;
				usBs.iUsBsY = 6 * 60;
				break;
			case 6:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 9 * 60;
				usBs.iUsBsY = 6 * 60;
				break;
			case 7:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 9 * 60;
				usBs.iUsBsY = 6 * 60;
				break;
			case 8:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 12 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			case 9:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 12 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			case 10:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 12 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			case 11:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 12 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			case 12:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 7 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			case 13:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 7 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			case 14:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 7 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			case 15:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 7 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			case 16:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 7 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			case 17:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 7 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			case 18:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 7 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			case 19:
				mapWidth = 24 * 20;
				mapHeight = 16 * 20;
				usBs.iUsBsX = 7 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			case 20:
				mapWidth = 13 * 60;
				mapHeight = 8 * 60;
				usBs.iUsBsX = 12 * 60;
				usBs.iUsBsY = 3 * 60;
				break;
			case 21:
				mapWidth = 13 * 60;
				mapHeight = 8 * 60;
				usBs.iUsBsX = 12 * 60;
				usBs.iUsBsY = 3 * 60;
				break;
			case 22:
				mapWidth = 13 * 60;
				mapHeight = 8 * 60;
				usBs.iUsBsX = 12 * 60;
				usBs.iUsBsY = 3 * 60;
				break;
			case 23:
				mapWidth = 13 * 60;
				mapHeight = 8 * 60;
				usBs.iUsBsX = 12 * 60;
				usBs.iUsBsY = 3 * 60;
				break;
			}
			break;
		case MyGameCanvas.gmFight:
			switch (gameRank) {
			case 0:
				mapWidth = 13 * 60;
				mapHeight = 8 * 60;
				usBs.iUsBsX = 11 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			case 1:
				mapWidth = 13 * 60;
				mapHeight = 8 * 60;
				usBs.iUsBsX = 11 * 60;
				usBs.iUsBsY = 2 * 60;
				break;
			case 2:
				mapWidth = 13 * 60;
				mapHeight = 8 * 60;
				usBs.iUsBsX = 6 * 60;
				usBs.iUsBsY = 4 * 60;
				break;
			}
			break;
		}
		mapWidth = 24 * 20;
		mapHeight = 16 * 20;
	}

	/**
	 * 根据起始出兵行列，算出旋转及角度
	 * 
	 * @param type
	 */
	public void getStartDir(int type) {
		switch (type) {
		case GameRole.DIR_DOWN:
			role.trans = Tools.TRANS_H;
			role.rota = 90;
			role.driftX = 30;
			role.driftY = -20;
			break;
		case GameRole.DIR_UP:
			role.trans = Tools.TRANS_H;
			role.rota = -90;
			role.driftX = 30;
			role.driftY = 20;
			break;
		case GameRole.DIR_LEFT:
			role.trans = Tools.TRANS_NONE;
			role.rota = 0;
			role.driftX = 40;
			role.driftY = 30;
			break;
		case GameRole.DIR_RIGHT:
			role.trans = Tools.TRANS_H;
			role.rota = 0;
			role.driftX = 20;
			role.driftY = 30;
			break;
		}
	}

	public void spritesMove() {// 人物角色的移动
		if (isCg) {
			return;
		}
//		System.out.println("dsadsadsa");
		if (sprites != null) {
			for (int i = 0; i < sprites.size(); i++) {
				GameRole sprite = (GameRole) sprites.elementAt(i);

				sprite.move();
				// if(sprite.iCoolTime>0){sprite.dir=0;}//塔若不攻击，将角度置0
				if (sprite.roleStatus == GameInterface.STATUS_NULL
						|| sprite.x > GameMap.mapWidth + 10) {
					sprites.removeElementAt(i);
				}
			}
		}
	}

	public void enemysMove() {// 怪物的移动
	// System.out.println("enemys.size() :"+enemys.size());
	// if(enemys==null&&gameRank==99){
	// canvas.setST(canvas.GmStat_dengdai_win);
	// }
		if (enemys != null) {
			for (int i = 0; i < enemys.size(); i++) {
				GameRole enemy = (GameRole) enemys.elementAt(i);
				enemy.move();
		    	if(enemy.type==GameRole.TYPE_ENEMY_紫盾&&enemy.roleStatus!=GameRole.STATUS_GONGJI){
		    		chekPaoTai(enemy);
		    		}
				
//				if (gameRank == 99 && enemy.x > 1000) {
//					enemy.setStatus(enemy.STATUS_DEAD);
//					enemys.remove(i);
//				}

//				if (enemy.type == GameRole.TYPE_goldFish) {
//					if ((enemy.x < -100 || enemy.x > 1000 || enemy.y < -100 || enemy.y > 680)
//							&& enemy.iEnemyHp > 0) {
//						enemy.iEnemyHp = 0;
//						enemys.removeElementAt(i);
//					}
//				}
//				if (enemy.roleStatus == GameInterface.STATUS_DEAD
//						&& enemy.shot.size() == 0) {// 将某个敌人身上锁定的箭矢全部清空
//					enemy.shot = null;
//					if (enemy.type == GameRole.TYPE_ENEMY_重甲BOSS) {
//						iNumShayu++;
//					} else if (enemy.type == GameRole.TYPE_ENEMY_大剑BOSS) {
//						iNumJianyu++;
//					}
//					enemys.removeElementAt(i);
//				}
			}
		}
	}

	byte pause = 10;// 判断怪物是停顿还是眩晕
	byte countRole;

	public void BulletMove() {// 箭矢的移动
	// enemy.setStatus(enemy.STATUS_MOVE);
	// System.out.println("aaaaaaaaaaaaaaaaa : "+i);

		for (int i = 0; i < GameEngine.me.enemys.size(); i++) {
			GameRole enemy = (GameRole) GameEngine.me.enemys.elementAt(i);
			if (laoJiaHP <= 0) { // 判断游戏失败 当有十个怪物出屏的话则失败
				if (enemy.x >= 800 || enemy.y > 450) {
					enemy.setStatus(enemy.STATUS_DEAD);
					GameEngine.me.enemys.remove(i);
					countRole++;
				}
				if (countRole >= 10) {
					MyGameCanvas.setST(MyGameCanvas.GmStat_dengdai_lose);
				}

				// MyGameCanvas.setST(MyGameCanvas.GmStat_Lose);
			}
			if (enemy.x >= 800 || enemy.y > 460||enemy.x<-20) {
//				iHit++;
				enemy.setStatus(enemy.STATUS_DEAD);
				GameEngine.me.enemys.remove(i);
			}
			// if(enemy.roleStatus !=role.STATUS_GONGJI){
			// if(enemy.x>=150&&enemy.y>=250&&enemy.x<=310){
			// enemy.setStatus(enemy.STATUS_GONGJI);
			// role.laoJiaShouShang=true;
			// }
            if(enemy.y<450){
    			if (enemy.x >= wangX && enemy.y >= wangY && laoJiaHP > 0) {
    				enemy.setStatus(enemy.STATUS_GONGJI);
    				role.laoJiaShouShang = true;
    			}
            }else{
            	enemy.setStatus(enemy.STATUS_MOVE);	
            }

			if (laoJiaHP < 0) {
				for (int k = 0; k < GameEngine.me.enemys.size(); k++) {
					GameRole enemy2 = (GameRole) GameEngine.me.enemys.elementAt(k);
					if(enemy2.type!=GameRole.TYPE_ENEMY_紫盾&&enemy2.roleStatus==GameRole.STATUS_GONGJI){
					   enemy2.setStatus(enemy.STATUS_MOVE);
					}
	
				}
			}

			// if(enemy.x>=450&&enemy.y>=410&&enemy.x<=500){
			// enemy.setStatus(enemy.STATUS_GONGJI);
			// role.laoJiaShouShang=true;
			// }
			// if(enemy.x>=300&&enemy.y>=410&&enemy.x<=350){
			// enemy.setStatus(enemy.STATUS_GONGJI);
			// role.laoJiaShouShang=true;
			// }
			// if(enemy.x>=350&&enemy.y>=390&&enemy.x<=400){
			// enemy.setStatus(enemy.STATUS_GONGJI);
			// role.laoJiaShouShang=true;
			// }

			// }
			if (enemy.roleStatus == enemy.STATUS_STOP) {
				enemy.Stop_time++;
				if (enemy.Stop_time > enemy.go) { // 判断如果停顿或者眩晕时间到了让怪物继续行走lh
					canvas.isSTOP = false;
					enemy.setStatus(enemy.STATUS_MOVE);
					enemy.go = 10;
					enemy.Stop_time = 0;
					enemy.BINGDONG = false;

				}
			}
		}
		if (bullets != null) {
			for (int i = 0; i < bullets.size(); i++) {
				BulletManager bullet = (BulletManager) bullets.elementAt(i);
				bullet.bulletMove(i); // 射击 子弹碰撞
				if (bullet.bActive == false) {
					if (canvas.ZB[usBs.iUsBsRank - 1] + 1 != 4) {
						bullets.removeElementAt(i);
					}

				}
			}
		}
	}

	public void drawSprites() {
		if (sprites != null) {
			for (int i = 0; i < sprites.size(); i++) {
				GameRole sprite = (GameRole) sprites.elementAt(i);
				if (sprite != null
						&& sprite.roleStatus != GameInterface.STATUS_NULL) {
					sprite.paint();
				}
			}
		}
	}

	int[][] index2 = { { 7, 8, 29, 19 },/* 图片说明 */
	{ 6, 27, 30, 19 } };/* 图片说明 */
	int boo;

	public void drawEnemy() { 

		if (enemys != null) {
			for (int i = 0; i < enemys.size(); i++) {
				GameRole enemy = (GameRole) enemys.elementAt(i);
			if (enemy.roleStatus == GameRole.STATUS_DEAD) {
			enemys.remove(i);
		}
				if (enemy.isJianSu > 0) {
					GameDraw.add_Image(PAK_IMAGES.IMG_LAOJIA1ZIDAN, enemy.x,
							enemy.y
									- (enemy.type == role.TYPE_ENEMY_光头 ? 80
											: 50), index2[time / 8 % 2],
							Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 1000);
					// GameDraw.renderAnimPic22(PAK_IMAGES.IMG_FISHDIS,(time/5)%5,enemy.x,
					// enemy.y-(enemy.type==role.TYPE_ENEMY_光头?80:50),MyGameCanvas.data_JIANSU,false,false,1500,0,0,0);
				}
				if (enemy.BINGDONG) {
					GameDraw.add_Image(PAK_IMAGES.IMG_LAOJIA1ZIDAN111, enemy.x,
							enemy.y, Tools.HCENTER_VCENTER, Tools.TRANS_NONE,
							1800);
				}

				if (enemy != null
						&& enemy.roleStatus != GameInterface.STATUS_DEAD) {
					enemy.paint();
					// if(enemy.type==GameRole.TYPE_ENEMY_重甲BOSS||enemy.type==GameRole.TYPE_ENEMY_大剑BOSS
					// ||enemy.type==GameRole.TYPE_ENEMY_步兵BOSS||enemy.type==GameRole.TYPE_ENEMY_骑兵BOSS){
					// enemy.drawShow(enemy.x,enemy.y,55);
					// }else{
					enemy.drawShow(enemy.x, enemy.y, 55);
					// }
				}
			}
		}
	}

	public void drawBullet() {// 箭矢的绘制
		if (bullets != null) {
			for (int i = 0; i < bullets.size(); i++) {
				BulletManager bullet = (BulletManager) bullets.elementAt(i);
				bullet.drawBullet(bullet.type);
			}
		}
	}

	public void drawUnLaw() {
		if (unLawBuilds != null) {
			for (int i = 0; i < unLawBuilds.size(); i++) {
				GameRole unlaw = (GameRole) unLawBuilds.elementAt(i);
				if (unlaw.roleStatus == unlaw.STATUS_DEAD) {
					unLawBuilds.removeElementAt(i);
				}
				unlaw.drawUnLaw();
				// unlaw.unLawHp(unlaw.x,unlaw.y);
			}
		}
	}

	int getSpriteIndex(int col, int line) { // 根据坐标算出第几个雇佣兵,列和行
		if (sprites != null) {
			for (int i = 0; i < sprites.size(); i++) {
				GameRole sprite = (GameRole) sprites.elementAt(i);
				if (sprite.x / map.tileWidth == col
						&& sprite.y / map.tileHight == line) {
					return i;
				}
			}
		}
		return -1;
	}

	int getUnLawIndex(int col, int line) {// 获取违建的索引
		if (unLawBuilds != null) {
			for (int i = 0; i < unLawBuilds.size(); i++) {
				GameRole unlaw = (GameRole) unLawBuilds.elementAt(i);
				if (unlaw.x / map.tileWidth == col
						&& unlaw.y / map.tileHight == line) {
					return i;
				}
			}
		}
		return -1;
	}

	void chekRole() {

		if (usBs.UsBsRota <= 18 || usBs.UsBsRota < 342) {
			in = 0;
		}
		if (usBs.UsBsRota > 18 && usBs.UsBsRota <= 54) {
			in = 3;
		}
		if (usBs.UsBsRota > 54 && usBs.UsBsRota <= 90) {
			in = 4;
		}
		if (usBs.UsBsRota > 306 && usBs.UsBsRota <= 342) {
			in = 2;
		}
		if (usBs.UsBsRota > 250 && usBs.UsBsRota <= 306) {
			in = 1;
		}
	}

	void drawTeShuGuai(int x, int y, int xh) {
		if (teSuGuaiXG == 0) {
			return;
		}
		int[] img = { PAK_IMAGES.IMG_B2, PAK_IMAGES.IMG_B3, PAK_IMAGES.IMG_B1,
				PAK_IMAGES.IMG_B5, PAK_IMAGES.IMG_B4 };
         if(MyActivity.VMHeight<=240){return;}
		GameDraw.add_ImageAlpha(img[teSuGuaiXG - 1], 400, y,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 12, 255 + xh);

	}

	int ziY;
	int xiaoShi;

	void chekTeShuGuai() {
		if (teSuGuaiXG != 0) {
			if (ziY < 240) {
				ziY += 20;
			}
			if (ziY >= 240) {
				// if(canvas.gameTime%2==0){
				xiaoShi -= 5;
				// }

				if (xiaoShi < -150) {
					xiaoShi = 0;
					ziY = -50;
					teSuGuaiXG = 0;
				}
			}
			if (xiaoShi > -94) {
				drawTeShuGuai(0, ziY, xiaoShi);
			}

		}
	}

	/**
	 * 画老家
	 * 
	 * @param x
	 * @param y
	 *            100/200=>70 400/600/800=>90-20
	 */
	int homeDelay;
	float fLingqu = 0.5f;
	int LingquNum;
	// int
	// xuanwo[]={PAK_IMAGES.IMG_XUANWO1,PAK_IMAGES.IMG_XUANWO2,PAK_IMAGES.IMG_XUANWO3,
	// PAK_IMAGES.IMG_XUANWO4};
	// int
	// paotai1[]={PAK_IMAGES.IMG_PAOTAI1,PAK_IMAGES.IMG_PAOTAI12,PAK_IMAGES.IMG_PAOTAI13};
	// int
	// paotai2[]={PAK_IMAGES.IMG_PAOTAI2,PAK_IMAGES.IMG_PAOTAI22,PAK_IMAGES.IMG_PAOTAI23};
	// int
	// paotai3[]={PAK_IMAGES.IMG_PAOTAI3,PAK_IMAGES.IMG_PAOTAI32,PAK_IMAGES.IMG_PAOTAI33};
	// int
	// paotai4[]={PAK_IMAGES.IMG_PAOTAI4,PAK_IMAGES.IMG_PAOTAI42,PAK_IMAGES.IMG_PAOTAI43};
	// int
	// paotai5[]={PAK_IMAGES.IMG_PAOTAI5,PAK_IMAGES.IMG_PAOTAI52,PAK_IMAGES.IMG_PAOTAI53};
	// int paotai6[][]={
	// {3,2,42,50},/*图片说明*/
	// {55,5,46,51},/*图片说明*/
	// {112,2,44,51},/*图片说明*/
	// {56,61,46,50},/*图片说明*/
	// {111,59,45,50}};/*图片说明*/
	byte in;
	byte count;

	int wangImg[] = { PAK_IMAGES.IMG_WANG3, PAK_IMAGES.IMG_WANG2,
			PAK_IMAGES.IMG_WANG1 };// 正常情况下
	int wangImg2[] = { PAK_IMAGES.IMG_WANG6, PAK_IMAGES.IMG_WANG5,
			PAK_IMAGES.IMG_WANG4 };// 受伤情况下
	byte poSun = 2;
	int wangImg3[] = { PAK_IMAGES.IMG_CEWANG3, PAK_IMAGES.IMG_CEWANG2,
			PAK_IMAGES.IMG_CEWANG1 };// 正常情况下
	int wangImg4[] = { PAK_IMAGES.IMG_CEWANG6, PAK_IMAGES.IMG_CEWANG5,
			PAK_IMAGES.IMG_CEWANG4 };// 受伤情况下

	public void drawUsHome(int x, int y) {
		// switch ((int)((float)(laoJiaHP /laoJiaHP_MAX)*10)/3) {
		// case 0:
		//
		// break;
		//
		// default:
		// break;
		// }
		// laoJiaHP = laoJiaHP_MAX;
		if(MyActivity.VMHeight>240){
		if (laoJiaHP > laoJiaHP_MAX * 0.6) {
			poSun = 2;
		}
		if (laoJiaHP < laoJiaHP_MAX * 0.6 && laoJiaHP > laoJiaHP_MAX * 0.3) {
			poSun = 1;
		}
		if (laoJiaHP < laoJiaHP_MAX * 0.3) {
			poSun = 0;
		}
		}
		switch (gameRank) {
		case 0:
		case 3:
		case 4:
		case 5:
			if(MyActivity.VMHeight>240){
				if (!usBs.laoJiaWuDi) {
					if (role.laoJiaShouShang) {
						if (canvas.gameTime / 30 % 2 == 0) {
							GameDraw.add_Image(wangImg4[poSun], 580, wangY,
									Tools.TOP_RIGHT, Tools.TRANS_H,
									laoJiaHP >= 0 ? 2230 : 100);
						} else {
							GameDraw.add_Image(wangImg3[poSun], 580, wangY,
									Tools.TOP_RIGHT, Tools.TRANS_H,
									laoJiaHP >= 0 ? 2230 : 100);
						}
					} else {
						GameDraw.add_Image(wangImg3[poSun], 580, wangY,
								Tools.TOP_RIGHT, Tools.TRANS_H,
								laoJiaHP >= 0 ? 2230 : 100);
					}

				} else {
					GameDraw.add_Image(PAK_IMAGES.IMG_CEWANG7, 580, wangY,
							Tools.TOP_RIGHT, Tools.TRANS_H, 2230);
				}
				
			}else{
				if(laoJiaHP >0){
					GameDraw.add_Image(wangImg3[poSun], 580, wangY,
							Tools.TOP_RIGHT, Tools.TRANS_H,
							laoJiaHP >= 0 ? 2230 : 100);	
				}
			}
			break;
		case 9:
		case 10:
		case 11:
			if(MyActivity.VMHeight>240){
			if (!usBs.laoJiaWuDi) {
				if (role.laoJiaShouShang) {
					if (canvas.gameTime / 30 % 2 == 0) {
						GameDraw.add_Image(wangImg4[poSun], 580, wangY,
								Tools.TOP_RIGHT, Tools.TRANS_H,
								laoJiaHP >= 0 ? 2230 : 100);
					} else {
						GameDraw.add_Image(wangImg3[poSun], 580, wangY,
								Tools.TOP_RIGHT, Tools.TRANS_H,
								laoJiaHP >= 0 ? 2230 : 100);
					}
				} else {
					GameDraw.add_Image(wangImg3[poSun], 580, wangY,
							Tools.TOP_RIGHT, Tools.TRANS_H,
							laoJiaHP >= 0 ? 2230 : 100);
				}

			} else {
				GameDraw.add_Image(PAK_IMAGES.IMG_CEWANG7, 580, wangY,
						Tools.TOP_RIGHT, Tools.TRANS_H, 2230);
			}
		
			}else{
				if(laoJiaHP >0){
					GameDraw.add_Image(wangImg3[poSun], 580, wangY,
							Tools.TOP_RIGHT, Tools.TRANS_H,
							laoJiaHP >= 0 ? 2230 : 100);
				}
			}	
			break;
		case 15:
		case 16:
		case 17:
			if(MyActivity.VMHeight>240){
			if (!usBs.laoJiaWuDi) {
				if (role.laoJiaShouShang) {
					if (canvas.gameTime / 30 % 2 == 0) {
						GameDraw.add_Image(wangImg4[poSun], 600, wangY,
								Tools.TOP_RIGHT, Tools.TRANS_H,
								laoJiaHP >= 0 ? 2230 : 100);
					} 
				} else {
					GameDraw.add_Image(wangImg3[poSun], 600, wangY,
							Tools.TOP_RIGHT, Tools.TRANS_H,
							laoJiaHP >= 0 ? 2230 : 100);
				}

			} else {
				GameDraw.add_Image(PAK_IMAGES.IMG_CEWANG7, 600, wangY,
						Tools.TOP_RIGHT, Tools.TRANS_H, 2230);
			}
			
			}else{
				if(laoJiaHP >0){
					GameDraw.add_Image(wangImg3[poSun], 600, wangY,
							Tools.TOP_RIGHT, Tools.TRANS_H,
							laoJiaHP >= 0 ? 2230 : 100);
				}
			}
			break;
		default:
			if(MyActivity.VMHeight>240){
			if (!usBs.laoJiaWuDi) {
				if (role.laoJiaShouShang) {
					if (canvas.gameTime / 30 % 2 == 0) {
						GameDraw.add_Image(wangImg2[poSun], 400, 465,
								Tools.HCENTER_BOTTOM, Tools.TRANS_NONE, 2230);
					} else {
						GameDraw.add_Image(wangImg[poSun], 400, 465,
								Tools.HCENTER_BOTTOM, Tools.TRANS_NONE, 2230);
					}
				} else {
					if (gameRank != 99) {
						GameDraw.add_Image(wangImg[poSun], 400, 465,
								Tools.HCENTER_BOTTOM, Tools.TRANS_NONE, 2230);
					}
				}

			} else {
				GameDraw.add_Image(PAK_IMAGES.IMG_WANG7, 400, 465,
						Tools.HCENTER_BOTTOM, Tools.TRANS_NONE, 2230);
			}
			
			}else{
				if(laoJiaHP >0){
					GameDraw.add_Image(wangImg[poSun], 400, 465,
							Tools.HCENTER_BOTTOM, Tools.TRANS_NONE, 2230);
				}
			}
			break;
		}

		if (usBs.sheSuJiaBei == true) {

			if (usBs.jiaSuTime / 3 % 9 == 0) {
				usBs.touMing++;

			}
			if (usBs.touMing <= 5) {
				usBs.jiaSuTime++;
				if (usBs.Scale > 1.1f) {
					usBs.Scale -= 0.05;
                    if(MyActivity.VMHeight>240){
    					GameDraw.add_ImageScale(PAK_IMAGES.IMG_XIAOSHOUZHI, 400,
    							480, Tools.HCENTER_BOTTOM, Tools.TRANS_NONE, 2000,
    							usBs.Scale, usBs.Scale);
                    }					

				} else {
                    if(MyActivity.VMHeight>240){
					GameDraw.add_Image(PAK_IMAGES.IMG_FISHDIS, 400, 480,
							Tools.HCENTER_BOTTOM, Tools.TRANS_NONE, 201);
                    }
				}
				int[] touMing = { 0x88000000, 0x78000000, 0x68000000,
						0x58000000, 0x48000000, 0x38000000 };
				if(MyActivity.VMHeight>240){
					GameDraw.add_Rect(0, 0, 800, 480, true, Tools.TOP_LEFT,
							touMing[usBs.touMing], 200);// 灰蒙板	
				}


			} else {
				Tools.removeImage(PAK_IMAGES.IMG_XIAOSHOUZHI);
				Tools.removeImage(PAK_IMAGES.IMG_FISHDIS);
			}
            if(MyActivity.VMHeight>240){
			GameDraw.add_ImageRota(PAK_IMAGES.IMG_JIASU, 400,
					430,// 旋转框
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2231,
					MyGameCanvas.me.gameTime * 5 % 360);
            }
		}

		// GameDraw.add_Image(xuanwo[MyGameCanvas.me.gameTime/5%4],x,y,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,130);
		GameDraw.add_Image(PAK_IMAGES.IMG_MEN1, 400, 500, Tools.HCENTER_BOTTOM,
				Tools.TRANS_NONE, 2230);
		if (role.laoJiaShouShang) {
			// GameDraw.add_Image(PAK_IMAGES.IMG_JINJISHENGLI,240,160,
			// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,220);
			if (count-- < 0) {
				role.laoJiaShouShang = false;
			}
		}

		if (gameRank != 99) {
			GameDraw.add_Image(PAK_IMAGES.IMG_SHOP1, 732, 425, Tools.TOP_LEFT,
					Tools.TRANS_NONE, 131);
			GameDraw.add_Image(PAK_IMAGES.IMG_MUSHI2, 0 - 3, 400,
					Tools.TOP_LEFT, Tools.TRANS_NONE, 130);
		}
		GameDraw.add_Image(PAK_IMAGES.IMG_SHENGCUNZHUANGJIAAN, 400, 445,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2000 + 1000);
		switch (usBs.usBsStatus) {
		case GameInterface.STATUS_ATTACK:
			homeDelay++;
			if (homeDelay >= 17) {
				usBs.usBsStatus = GameInterface.STATUS_STOP;
			}
			break;
		case GameInterface.STATUS_STOP:
			homeDelay = 0;
			break;
		}
		// switch(usBs.iUsBsRank){
		// System.out.println("canvas.ZB[usBs.iUsBsRank] : "+canvas.ZB[usBs.iUsBsRank-1]);
		int index2[][] = { {}, { 372 / 4, 0, 372 / 4, 95 },
				{ 444 / 4, 0, 444 / 4, 113 }, { 282 / 3, 0, 282 / 3, 120 },
				{ 312 / 4, 0, 312 / 4, 134 }, { 380 / 4, 0, 380 / 4, 110 },
				{ 396 / 4, 0, 396 / 4, 122 }, { 436 / 4, 0, 436 / 4, 120 }, }; // 1.西红柿
																				// 2.仙人掌
																				// 3.玉米4.香蒲5.大蒜6.西瓜7.南瓜
		if (GameEngine.canvas.countShiYong != -1
				&& GameEngine.canvas.countShiYong / 10 % 2 == 0) {
			if (SSImg[0] > 0) {
				GameDraw.add_ImageRota(SSImg[1], 400, 455, homeDelay / 6 % 3
						* index2[SSImg[0]][0], index2[SSImg[0]][1],
						index2[SSImg[0]][2], index2[SSImg[0]][3],
						Tools.HCENTER_BOTTOM, Tools.TRANS_NONE, 2231,
						usBs.UsBsRota);
			}

		} else {
			if (canvas.contKP <= 120 && canvas.contKP != 0
					&& canvas.contKP / 10 % 2 == 0) {
				// drawHome();
			} else {
				drawHome();
			}
		}
		if (gameRank != 99) {
			GameDraw.add_Image(
					MyGameCanvas.pointMenu != 100 ? PAK_IMAGES.IMG_DENGJIJIA
							: PAK_IMAGES.IMG_DENGJIJIAN, 450, 420,
					Tools.TOP_LEFT, Tools.TRANS_NONE, 3599 + 1000);
			GameDraw.add_Image(
					MyGameCanvas.pointMenu != 101 ? PAK_IMAGES.IMG_DENGJIJIA
							: PAK_IMAGES.IMG_DENGJIJIAN, 350 - 61, 420,
					Tools.TOP_LEFT, Tools.TRANS_H, 3599 + 1000);
		}

		switch (MyGameCanvas.pointMenu) {
		case 4:
			GameDraw.add_Image(PAK_IMAGES.IMG_DENGJIJIAN, 325, 436, 44, 0, 45,
					44, Tools.TOP_LEFT, Tools.TRANS_NONE, 130);
			break;
		case 3:
			GameDraw.add_Image(PAK_IMAGES.IMG_DENGJIJIA, 432, 436, 44, 0, 45,
					44, Tools.TOP_LEFT, Tools.TRANS_NONE, 130);
			break;
		}
		// if(iRunNum>0){
		// GameDraw.drawNumber(PAK_IMAGES.IMG_LOUWANGSHU,10-iRunNum,x-42,y-55,75,-20,
		// Tools.TOP_LEFT,130,103,0,true);
		// }else{
		// GameDraw.drawNumber(PAK_IMAGES.IMG_LOUWANGSHU,10-iRunNum,x-70,y-55,75,-20,
		// Tools.TOP_LEFT,130,103,0,true);
		// }
		// 教学时候,造塔时候,若老家不能攻击则画个禁止符号
	}

	void drawHome() {
		switch (canvas.ZB[usBs.iUsBsRank - 1]) {
		case -1:// 豌豆
			GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI1, 400, 455, homeDelay
					/ 6 % 3 * (223 / 4), 0, 223 / 4, 109, Tools.HCENTER_BOTTOM,
					Tools.TRANS_NONE, 2231, usBs.UsBsRota);
			break;
		case 0:// 豌豆

			GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI1, 400, 455, homeDelay
					/ 6 % 3 * (223 / 4), 0, 223 / 4, 109, Tools.HCENTER_BOTTOM,
					Tools.TRANS_NONE, 2231, usBs.UsBsRota);
			break;
		case 1:// 西红柿
			chekRole();// 計算人物朝向
			// GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI1,240,290,paotai6[homeDelay/60%5],
			// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,135,usBs.UsBsRota);
			GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI12, 400, 455, homeDelay
					/ 6 % 3 * (372 / 4), 0, 372 / 4, 95, Tools.HCENTER_BOTTOM,
					Tools.TRANS_NONE, 2231, usBs.UsBsRota);
			break;
		case 2:// 仙人掌

			// GameDraw.add_ImageRota(paotai2[homeDelay/6%3],240,290,
			// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,135,usBs.UsBsRota);
			GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI13, 400, 455, homeDelay
					/ 6 % 3 * (444 / 4), 0, 444 / 4, 113, Tools.HCENTER_BOTTOM,
					Tools.TRANS_NONE, 2231, usBs.UsBsRota);
			break;
		case 3: // 玉米
			GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI2, 400, 455, homeDelay
					/ 6 % 3 * (282 / 3), 0, 282 / 3, 120, Tools.HCENTER_BOTTOM,
					Tools.TRANS_NONE, 2231, usBs.UsBsRota);
			// GameDraw.add_ImageRota(paotai3[homeDelay/6%3],240,290,
			// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,135,usBs.UsBsRota);
			break;
		case 4: // 大狙

			// GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI2,240,310,homeDelay/6%3*(177/3),0,177/3,75,
			// Tools.HCENTER_BOTTOM,Tools.TRANS_NONE,135,usBs.UsBsRota);
			// GameDraw.add_ImageRota(paotai4[homeDelay/6%3],240,290,
			// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,135,usBs.UsBsRota);
			GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI23, 400, 440, homeDelay
					/ 6 % 3 * (312 / 4), 0, 312 / 4, 134, Tools.HCENTER_BOTTOM,
					Tools.TRANS_NONE, 2231, usBs.UsBsRota);
			break;
		case 5: // 大蒜
			GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI22, 400, 440, homeDelay
					/ 6 % 3 * (380 / 4), 0, 380 / 4, 110, Tools.HCENTER_BOTTOM,
					Tools.TRANS_NONE, 2231, usBs.UsBsRota);
			break;
		case 6: // 西瓜
			int index[][] = { { 0, 1, 58, 71 },/* 图片说明 */
			{ 60, 3, 58, 70 },/* 图片说明 */
			{ 120, 0, 57, 73 } };/* 图片说明 */
			GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI3, 400, 440, homeDelay
					/ 6 % 3 * (396 / 4), 0, 396 / 4, 122, Tools.HCENTER_BOTTOM,
					Tools.TRANS_NONE, 2231, usBs.UsBsRota);
			break;
		case 7: // 南瓜
			GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI32, 400, 440, homeDelay
					/ 6 % 3 * (436 / 4), 0, 436 / 4, 120, Tools.HCENTER_BOTTOM,
					Tools.TRANS_NONE, 2231, usBs.UsBsRota);
			break;
		}
	}

	int getLaiJiaDJ(int Rank) {
		int money = 0;
		switch (Rank) {
		case 0:
			money = 0;
			break;
		case 1:
			money = 50;
			break;
		case 2:
			money = 28;
			break;
		case 3:
			money = 20;
			break;
		case 4:
			money = 20;
			break;
		case 5:
			money = 20;
		case 6:
			money = 20;
			break;
		case 7:
			money = 20;
			break;
		case 8:
			money = 20;
			break;
		case 9:
			money = 20;
			break;

		default:
			break;
		}
		return money;

	}

	/**
	 * 处理老家的发射子弹
	 * 
	 * @param x
	 * @param y
	 *            老家子弹的目标射向点
	 */
	int index;
	public boolean isDraw = false;
    byte countSms=0;
	public void usHomeAttack(int x, int y) {
		index++;
		if (canvas.ZB[usBs.iUsBsRank - 1] > 0) {
			// MyGameCanvas.me.iGold-=(usBs.iUsBsRank-1);
			if (index % 50 == 0) {// 炮台射击在此处扣钱
				index = 0;

				if (usBs.iUsBsCuJinbi > getLaiJiaDJ(canvas.ZB[usBs.iUsBsRank - 1])) {
					usBs.iUsBsCuJinbi -= getLaiJiaDJ(canvas.ZB[usBs.iUsBsRank - 1]);
					// isDraw=true;
					// canvas.yyy=-10;
					// canvas. xxx=0;
					// canvas.speed=0;
				} else {
					if (usBs.iUsBsCuJinbi < 1) {

						usBs.iUsBsCuJinbi = 1;
					}

					usBs.iUsBsRank = 1;
					usBs.iUsBsCurAttack = canvas.ZBXingXi[canvas.ZB[usBs.iUsBsRank - 1]][3];
				}

			}
			if (MyGameCanvas.me.iGold <= 0) {
				MyGameCanvas.setST(MyGameCanvas.GmStat_GOLDNOT);
			}
		}
		if (usBs.iUsBsCuJinbi < (canvas.ZB[usBs.iUsBsRank - 1] == 0 ? 0
				: canvas.ZB[usBs.iUsBsRank - 1] + 50))
			return;// 钱不够
		int homeX, homeY;
		if (bTeach == true
				&& (!((teachStep == 1 || teachStep == 3) && iWave == 0 && enemys
						.size() != 0)) && (teachStep != 4 && teachStep < 9)) {
			return;
		}
		usBs.usBsStatus = GameInterface.STATUS_ATTACK;
		getUsHomeY(usBs.iUsBsRank);
		x = (int) ((float) (x * MyGameCanvas.SCREEN_WIDTH) / MyActivity.VMWidth);
		y = (int) ((float) (y * MyGameCanvas.SCREEN_HEIGHT) / MyActivity.VMHeight);// 转换为自适屏的
		int dir = setDir(400, usHomeY, x, y);
		usBs.UsBsRota = dir;

		if (usBs.UsBsRota <= 90 || usBs.UsBsRota >= 270) {// 老家的射击只能在180度范围内
			if (role.iSkillCoolTime[3] == 0) {
				if (gameRank == 99) {
//					if(canvas.diTuSuiPian!=0){
						ziDan--;
//					}
					if (ziDan < 0) {
						shenli = true;
						MyGameCanvas.setST(MyGameCanvas.GmStat_dengdai_win);
					}
				}

				MyActivity.instance._mView.waf.StartWav(5, false);
				if (canvas.ZB[usBs.iUsBsRank - 1] != 0 && isDraw == false) {
					isDraw = true;
					canvas.yyy = -10;
					canvas.xxx = 0;
					canvas.speed = 0;
				}
				if (dir <= 90) {
					// for(int i=0;i<3;i++){
					homeX = (int) (20 * (cos((70 - dir) * PI / 180)) + 400);
					homeY = (int) (usHomeY - 20 * (sin((70 - dir) * PI / 180))); // lh
					// if(canvas.ZB[usBs.iUsBsRank-1]+1!=2){
					// System.out.println("canvas.ZB[usBs.iUsBsRank-1] :"+canvas.ZB[usBs.iUsBsRank-1]);
					if (canvas.ZB[usBs.iUsBsRank - 1] == 0
							|| canvas.ZB[usBs.iUsBsRank - 1] == 5) {

						bullets.addElement(new BulletManager(homeX, homeY, x
								+ GameRandom.result(-10, 10), y
								+ GameRandom.result(-10, 10), dir,
								canvas.ZB[usBs.iUsBsRank - 1] + 1,MyGameCanvas.me.ZB[GameEngine.me.usBs.iUsBsRank-1]));

						bullets.addElement(new BulletManager(homeX - 200,
								homeY + 200, x + GameRandom.result(-10, 10), y
										+ GameRandom.result(-10, 10), dir,
								canvas.ZB[usBs.iUsBsRank - 1] + 1,MyGameCanvas.me.ZB[GameEngine.me.usBs.iUsBsRank-1]));
					} else {
						bullets.addElement(new BulletManager(homeX, homeY, x,
								y, dir, canvas.ZB[usBs.iUsBsRank - 1] + 1,MyGameCanvas.me.ZB[GameEngine.me.usBs.iUsBsRank-1]));
					}

					// }
					// }

				} else if (dir >= 270) {
					// for(int i=0;i<3;i++){
					homeX = (int) (400 - 20 * (cos((dir - 270) * PI / 180)));
					homeY = (int) (usHomeY - 20 * (sin((dir - 270) * PI / 180)));
					// if(canvas.ZB[usBs.iUsBsRank-1]+1!=3){
					if (canvas.ZB[usBs.iUsBsRank - 1] == 0
							|| canvas.ZB[usBs.iUsBsRank - 1] == 5) {
						bullets.addElement(new BulletManager(homeX, homeY, x
								+ GameRandom.result(-10, 10), y
								+ GameRandom.result(-10, 10), dir,
								canvas.ZB[usBs.iUsBsRank - 1] + 1,MyGameCanvas.me.ZB[GameEngine.me.usBs.iUsBsRank-1]));
					} else {
						bullets.addElement(new BulletManager(homeX, homeY, x,
								y, dir, canvas.ZB[usBs.iUsBsRank - 1] + 1,MyGameCanvas.me.ZB[GameEngine.me.usBs.iUsBsRank-1]));
					}

					// }

					// }
				}

				// usBs.iUsBsCuJinbi-=(canvas.ZB[usBs.iUsBsRank-1]);
				role.setCoolTime(GameRole.HOME, 0);// 设置老家的攻击冷却时间
			}
		}
	}

	/**
	 * 获取老家炮台的Y坐标
	 * 
	 * @param rank
	 */
	int usHomeY;

	int getUsHomeY(int rank) {
		switch (rank) {
		case 1:
		case 2:
			usHomeY = 450;
			break;
		case 3:
		case 4:
			usHomeY = 450;
			break;
		case 5:
			usHomeY = 450;
			break;
		}
		return usHomeY;
	}

	/**
	 * 卷折的BUFF效果 恢复老家血量的卷折游戏中实时生效,因此放在老家逻辑上去做处理
	 */
	void bookBuff() {
		// if(usBs.iBookEverHas[1]==1){
		// if(bFreakMoneyExtra==false){
		// bFreakMoneyExtra = true;
		// iFreakMoney += 10;
		// }
		// }
		// else if(usBs.iBookEverHas[2]==1){usBs.iUsBsCurAttack+=10;}
		// else if(usBs.iBookEverHas[3]==1){usBs.iUsBsCuJinbi+=300;}
	}

	public void UsHomeLogic() {
		for (int i = 0; i < enemys.size(); i++) {
			List<GameRole> tar = new Vector<GameRole>();
			GameRole enemy = (GameRole) enemys.elementAt(i);
			if (enemy.iEnemyHp <= 0) {
				continue;
			}
			if (enemy.type != GameRole.TYPE_goldFish
					&& BulletManager.bInCircle(enemy.x - 5, enemy.y,
							usBs.iUsBsX, usBs.iUsBsY, 80)) {// 到达老家跟前且怪物还没死且非黄金鱼
				MyActivity.instance.shake();
				MyGameCanvas.AddBlastEffectList(enemy.x, enemy.y,
						MyGameCanvas.EFF_FISHDIS, 0, 119, 0);
				enemy.iEnemyHp = 0;
				enemy.setStatus(GameInterface.STATUS_DEAD);
				iRunNum++;
				if (iRunNum > 9) {
					Tools.removeAllImage();
					MyGameCanvas.EffectV.removeAllElements();// 将所有动画效果清除

					MyGameCanvas.setST(MyGameCanvas.GmStat_Lose);
				}
			}
		}

	}

	/**************************************************************************************/

	void drawOver(int x, int y) {
	}

	int getPassMoney() {
		int money = 0;
		money = 2000 + 100 * gameRank;
		return money;
	}

	void drawXX() {

		
		int[][] index = { { 7, 8, 29, 19 },/* 图片说明 */
		{ 6, 27, 30, 19 } };/* 图片说明 */
		int size2 = MyGameCanvas.EffectV2.size() - 1;
		for (int j = size2; j >= 0; j--) {
			GameRole role = (GameRole) (MyGameCanvas.EffectV2.elementAt(j));
			if (role.iEnemyHp > 0) {
				if (role.gongJi_count2 > 0) {
					role.gongJi_count2--;
					GameDraw.add_Image(PAK_IMAGES.IMG_LAOJIA1ZIDAN, role.x - 5,
							role.y - 55, index[time / 8 % 2],
							Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 1000);
				}

				if (role.gongJi_count2 == 0) {
					MyGameCanvas.EffectV2.removeElementAt(j);
				}
			} else {
				MyGameCanvas.EffectV2.removeElementAt(j);
			}
		}

		
		
		if(yuanZiDanTS){
//			GameDraw.add_ImageRota(PAK_IMAGES.IMG_JIASU, 300,
//					430,// 旋转框
//					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2231,
//					MyGameCanvas.me.gameTime * 5 % 360);

			if(isfd){
				jianTouY+=2;
				if(jianTouY>=30){
					isfd=false;
					jianTouSpeed=0;
				}
			}else{
				jianTouY-=2;
				if(jianTouY<=0){
					isfd=true;
					jianTouSpeed=30;
				}
			}
			if(JN3<62){
				JN3=62;
			}
			if(MyActivity.VMHeight>240){
			GameDraw.add_Image(PAK_IMAGES.IMG_M2, 400,240,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2231);
			GameDraw.add_ImageScale2(PAK_IMAGES.IMG_JIASU, 228,452,// 旋转框
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2231
					,0.6f,0.6f,MyGameCanvas.me.gameTime * 5 % 360);
			}
			GameDraw.add_Image(PAK_IMAGES.IMG_Z9, 228,360-jianTouY,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2232);

		}
		
		
	}
	int jianTouY,jianTouSpeed;
	boolean isfd=true;
	
    public static int paotaix,paotaiy,paotaiZDX,paotaiZDY;
	void drawPaoTai(){
		GameDraw.renderAnimPic22(PAK_IMAGES.IMG_ZIDUN1,fishData.isrank2,paotaix,
				paotaiy,MyGameCanvas.data_TYPE_ENEMY_紫盾,false,false,1500,0,0,0);
		
	}
	
	
	

	int checkYZD;
	int countYZD_y;

	void drawYuanZiDan() {
		if (checkYZD == 0) {
			return;
		}
		JN3=0;
//		if(gameRank==2&&iResult[0][3]==-1){
//			canvas.jiNengKaiQi[2]=-1;	
//		}
		if (checkYZD < MyGameCanvas.SCREEN_HEIGHT / 2 + 50) {
			checkYZD += 10;
			GameDraw.add_Image(PAK_IMAGES.IMG_YING2,
					MyGameCanvas.SCREEN_WIDTH / 2, -50 + checkYZD,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 200);
		} else {
			if (countYZD_y == 0) {
				MyActivity.instance._mView.waf.StartWav(4, false);
				for (int i = 0; i < GameEngine.me.enemys.size(); i++) {
					GameRole enemy = (GameRole) GameEngine.me.enemys
							.elementAt(i);
					enemy.iEnemyHp -= (100 + canvas.jiNengKaiQi[2]);
					if (enemy.iEnemyHp <= 0){
						bullm.chekBOO(enemy);
//						MyActivity.instance._mView.waf.StartWav(13, false);
						GameEngine.me.role.DropGold(GameEngine.me.iWave,
								bullm.iX, bullm.iY, enemy.type);
						GameEngine.me.usBs.iUsBsCuJinbi += bullm
								.checkJinBi(enemy.type);
						GameEngine.me.usBs.rankMoney += bullm
								.checkJinBi(enemy.type);
						enemy.setStatus(enemy.STATUS_DEAD);
						bullm.addEffect(100, enemy.x, enemy.y, enemy.type);
	    				if(GameEngine.roleNumber>1){//roleNumber不等于0则是特殊关卡
	    						if(GameEngine.roleType!=0){//roleType！=0则表示有指定怪物类型
	    							if(GameEngine.roleType==enemy.type){//打死的怪物类型和要求类型一样
	    								GameEngine.roleNumber--;//要求数量减
	    							}
	    						}else{//没有怪物类型限制的话 数量直接--
	    							GameEngine.roleNumber--;
	    						}
	    				}
						
//						 enemys.remove(i);
						// addEffect_2(enemy);
					}
				}
//				for (int i = 0; i < GameEngine.me.enemys.size(); i++) {
//					GameRole enemy = (GameRole) GameEngine.me.enemys
//							.elementAt(i);
////					if (enemy.roleStatus == GameRole.STATUS_DEAD) {
////						enemys.remove(i);
////					}
//				}
				if (countYZD_y < 12) {
					GameMap.screenShake();
				}

			}
			countYZD_y++;
			int[] YZD = { 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5,
					6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9, 9, 9, 10, 10, 10,
					10, 10, 10, };

			if (countYZD_y >= YZD.length - 2) {
				checkYZD = 0;
				countYZD_y = 0;
//				Tools.removeImage(PAK_IMAGES.IMG_YING3);
			} else {
//				if(MyActivity.VMHeight>240){
				GameDraw.renderAnimPic22(PAK_IMAGES.IMG_YING3, YZD[countYZD_y],
						MyGameCanvas.SCREEN_WIDTH / 2,
						MyGameCanvas.SCREEN_HEIGHT / 2,
						MyGameCanvas.data_YUANZIDAN, false, false, 220, 0, 0, 0);
//			}
				}

		}

	}
	
	

	public void drawGame(boolean isDrawRole) {
		// System.out.println("gameRank: "+gameRank);
//			for(int j=0;j<GameEngine.me.enemys.size();j++){
//	    		GameRole enemyS = (GameRole)GameEngine.me.enemys.elementAt(j);
//	    		
//			}
		
//		int x=0;int y=0;
//		for (int i = 0; i < map.propData.length; i++) {
//			x = i % 20 * 40;
//			y = i / 20 * 40;
//			if( map.propData[i]!=-1){
//				GameDraw.add_String( ""+map.propData[i], x, y, Tools.TOP_LEFT, 0xffffffff, 3000, 20);
//			}
//		}
        
		if(roleNumber!=0){
//			System.out.println("gameTime : "+MyGameCanvas.gameTime%100);
//			if(MyGameCanvas.gameTime%50==0&&roleTime>0){
//				roleTime--; 
//			}
			GameDraw.add_ImageScale(PAK_IMAGES.IMG_DJ6, 650-310, 45, Tools.BOTTOM_LEFT, Tools.TRANS_NONE, 1000,0.5f,0.5f);
			GameDraw.add_String(""+roleTime, 650-270,40,Tools.BOTTOM_LEFT, 0xffffffff, 1000, 21);	
//			GameDraw.add_String("指定怪："+roleNumber, 650,110,Tools.BOTTOM_LEFT, 0xffffffff, 1000, 21);	
		}
		DrawBackground(MyGameCanvas.me.gmStatus);// 背景图
		drawSkill();
		// drawTowIcon();
		// drawMark();
		pushSkill(skillPushX, skillPushY, skillPushType);
//		openTeachPlaying();
		drawEnemy();
		drawSprites();
		drawBullet();
		// drawUnLaw();
		drawUsHome(usBs.iUsBsX, usBs.iUsBsY);
		drawXX();
		drawYuanZiDan();
        drawGameUI();

		
	}
	
	void drawGameUI(){
		if(roleType==0&&roleNumber==0){return;}
		int img=0;
			switch (GameEngine.roleType) {
			case fishData.TYPE_ENEMY_步兵:
				img = PAK_IMAGES.IMG_RBUBING;
				break;
			case fishData.TYPE_ENEMY_刺客:
				img = PAK_IMAGES.IMG_RCIKE;
				break;
			case fishData.TYPE_ENEMY_牧师:
				img = PAK_IMAGES.IMG_RMUSHI;
				break;
			case fishData.TYPE_ENEMY_石盾:
				img = PAK_IMAGES.IMG_RSHIDUN;
				break;
			}
			if(img!=0){
				GameDraw.add_ImageScale(img, 518-290-35 , 30 , 50,86,55,84,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2000, 0.6f,
				0.6f);		
			}
			if(roleNumber>=100){
				GameDraw.drawNumber(PAK_IMAGES.IMG_TISHISHUZI, roleNumber, 630-90-300-30, 10,20,
						0, Tools.TOP_RIGHT, 2000, 33, 0, true);//怪物剩余个数
			}else{
				GameDraw.drawNumber(PAK_IMAGES.IMG_TISHISHUZI, roleNumber, 630-90-300-10, 10,20,
						0, Tools.TOP_RIGHT, 2000, 33, 0, true);//怪物剩余个数
			}

		GameDraw.add_ImageScale(PAK_IMAGES.IMG_TISHISHUZI, 670-90+2-300-30 , 10 , 200,0,20,33,
		Tools.TOP_LEFT, Tools.TRANS_NONE, 2000, 1f,//  斜杠
		1f);
		GameDraw.drawNumber(PAK_IMAGES.IMG_TISHISHUZI, Zrole, 690-90-300-25, 10,20,
				0, Tools.TOP_LEFT, 2000, 33, 0, true);//怪物总个数
	}
	
	
	void chekPaoTai(){
//		if (canvas.playIndex == 0 && canvas.playJX == false) {
//			canvas.playJX = true;
//			canvas.setST(canvas.GmStat_RankJX);
//		}
//		if (gameRank == 1 && canvas.playJX2 == false
//				&& canvas.gameStatus == canvas.GmStat_Playing
//				&& canvas.playIndex == 0) {
//			canvas.playJX2 = true;
//			canvas.setST(canvas.GmStat_RankJX);
//		}
//		if (gameRank == 2 && canvas.playJX3 == false
//				&& canvas.gameStatus == canvas.GmStat_Playing
//				&& canvas.playIndex == 0) {
//			canvas.playJX3 = true;
//			canvas.setST(canvas.GmStat_RankJX);
//		}
		// ps.drawParticle();
		if(paoTaiGJ==false){
		  drawPaoTai();	
		}else{
            if(MyGameCanvas.gameStatus!=MyGameCanvas.GmStat_Playing){
            	return;
            }
            if(gameRank!=2&&gameRank!=19){
            	return;
            }
			if(26+PTenemyDelay/10%4==29){
				int [] ZD ={GameEngine.paotaiZDX+30,GameEngine.paotaiZDY-20};
				if(PTZD.size()==0){
					PTZD.add(ZD);	
				}
			}
			for (int i = 0; i < PTZD.size(); i++) {
				int[] ZD =  PTZD.elementAt(i);
		    	int x = ZD[0];
		    	int y =ZD[1] ;
		    	int distance = BulletManager.GetDistance(x,y,450,450);
		    	ZD[0] -= ((x -450) *20 / distance);
		    	ZD[1] -= ((y -450) *20 / distance);
		    	if(ZD[1]>=350){
					bullm.addEffect(104,400,450,1);
		    		PTZD.remove(i);
		    		}
//                GameDraw.add_Image(PAK_IMAGES.IMG_MENUZI, ZD[0], ZD[1], Tools.TOP_LEFT, Tools.TRANS_NONE, 100);
        		GameDraw.add_ImageRota(PAK_IMAGES.IMG_MENUZI, ZD[0], ZD[1],
	    				Tools.HCENTER_VCENTER,Tools.TRANS_NONE,4000,40);
			}
		}
	}
	
  public static int PTenemyDelay;
	// 注意：凡涉及到坐标x,y的，一定要注意是否牵扯到iMapX,iMapY,注意iMapX,iMapY的+与-
	int iGameTime;
	int cont;
	boolean bProduce = true;// 每一波是否能产生黄金鱼
	int jiasu, stop;
    int rank0;
    public static boolean isSms=true;
	public void runGame() {
		time++;
		if(gameRank<=1){
			rank0++;
		}
		if(roleNumber!=0){
			if(MyGameCanvas.gameTime%50==0&&roleTime>1){
				roleTime--; 
			}
		}
		if(MyActivity.VMHeight>240){
			if(canvas.rankTiShi==false){
				MyGameCanvas.setST(MyGameCanvas.GmStat_TSGTiShi);
				
			}
		}

		cont = 0;
		if (gameRank != 99) {
			produceEnemy(MyGameCanvas.gmStatus);

		} else {
			if (JiangLiRank99.isJiaS) {
				jiasu++;
				if (jiasu > 80) {
					JiangLiRank99.isJiaS = false;
					jiasu = 0;
				}
			}
			if (JiangLiRank99.isStop) {
				stop++;
				if (stop > 80) {
					JiangLiRank99.isStop = false;
					stop = 0;
				}
			}
		}
		if(gameRank>1&&gameRank!=99&&MyGameCanvas.gameTime%400==0){
			chekGuanCaiXY();
		}

		// }else{
		if (MyGameCanvas.isSTOP == true&&MyActivity.VMHeight>240) {
			GameDraw.add_Image(PAK_IMAGES.IMG_PAOTAI5, 800, 480,
					Tools.BOTTOM_RIGHT, Tools.TRANS_NONE, 2000); // 右下
			GameDraw.add_Image(PAK_IMAGES.IMG_PAOTAI5, 0, 480,
					Tools.BOTTOM_LEFT, Tools.TRANS_H, 2000);// 左下
			GameDraw.add_Image(PAK_IMAGES.IMG_PAOTAI5, 800, 0, Tools.TOP_RIGHT,
					Tools.TRANS_V, 2000);// 右上
			GameDraw.add_Image(PAK_IMAGES.IMG_PAOTAI5, 0, 0, Tools.TOP_LEFT,
					Tools.TRANS_HV, 2000);// 左上
			if (++cont == 600 + (canvas.jiNengKaiQi[1] * 5)) {// 冰封技能
				MyGameCanvas.isSTOP = false;
				Tools.removeImage(PAK_IMAGES.IMG_PAOTAI5);
			}
		}

		// }
		chekTeShuGuai();
		// System.out.println("gameRank : "+gameRank);
		releaseSkill(skillX, skillY, skillType);
    		BulletMove();
 
		// if(time%2==0){
		enemysMove();
//		spritesMove();
		// }
		// UsHomeLogic();
		skillCold();
		chekRank99();
        chekXSJLi();//新手奖励
        if(MyActivity.VMHeight>240){
            chekPaoTai();
            chekWuYa();
        }


        chekJiJia();
        chekJiaoXue(rank0);
        
	}
	
	public static  boolean isRank1JX=false;
	public  boolean isRank2JX=false;
	void chekJiaoXue(int time){
		if(isRank1JX==false&&time==50&&gameRank==0){
			isRank1JX=true;
			MyGameCanvas.setST(MyGameCanvas.GmStat_RankJX2);
		}
//		System.out.println("time : "+time);
		if(time==450&&iResult[0][2]==-1&&gameRank==1){
			if(MyActivity.VMHeight>240){
				MyGameCanvas.setST(MyGameCanvas.GmStat_RankJX2);	
			}

		}
		if(MyGameCanvas.gameStatus==MyGameCanvas.GmStat_Playing){
			if(iResult[0][1]!=-1&&iResult[0][2]==-1&&gameRank==1&&isRank2JX==false){
				isRank2JX=true;
				MyGameCanvas.setST(MyGameCanvas.GmStat_RankJX);	
			}
		}
	    }
	
	void chekJiJia(){
		if(!fishData.isJiJia){return;}
		for (int i = 0; i < JiJiaXY.length; i++) {
			GameDraw.add_Image(PAK_IMAGES.IMG_LAOJIAXUETIAO, JiJiaXY[i][0],JiJiaXY[i][1]-80
					,0,0,(int)(160*(float)((float)JiJiaXY[i][2]/(float)fishData.jiJiaHPMax)),10, Tools.HCENTER_VCENTER,
					Tools.TRANS_NONE, 2000);// daoju
			if(JiJiaXY[i][2]>0){
				GameDraw.renderAnimPic22(PAK_IMAGES.IMG_JIJIA, (MyGameCanvas.gameTime / 10) % 8,
						JiJiaXY[i][0],JiJiaXY[i][1],
						MyGameCanvas.data_jijia, false, false, 1500, 0, 0, 0);
			}else{
//				GameDraw.renderAnimPic22(PAK_IMAGES.IMG_JIJIA, 0,
//						JiJiaXY[i][0],JiJiaXY[i][1],
//						MyGameCanvas.data_jijia, false, false, 1500, 0, 0, 0);
				
				int data[] ={3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,8,8,8,9,9,9};
				if(++JiJiaXY[i][3]<21){
					GameDraw.renderAnimPic22(PAK_IMAGES.IMG_SHANDIAN1, data[JiJiaXY[i][3]],
							JiJiaXY[i][0],JiJiaXY[i][1],
							MyGameCanvas.data_huoqiuTX, false, false, 1500, 0, 0, 0);
					
					
				}
			}
	
		}
	}
	//乌鸦的一些属性
	 boolean isWuYa=false,isChouJiang; 
	 int WuYaX,WuYaXIndex,random=99,wuyaHP,wuYaSpeed=1,dengdai1=42;
	 
	void chekWuYa(){
		if(gameRank ==99||gameRank==0||gameRank==1){
			return;
		}
		if(MyActivity.VMHeight<=240){return;}
		if(!isWuYa){
		if(random>50){//出乌鸦
			random=0;
			isWuYa=true;
			wuyaHP =200;
			dengdai1=42;
			WuYaXIndex=0;
			WuYaX=-50;
			isChouJiang = true;
		}}
		if(isWuYa){
			if(WuYaX<1000&&wuyaHP>0){//乌鸦移动
				WuYaXIndex++;
				WuYaX+=wuYaSpeed;
			}else{
				if(WuYaXIndex>42){//乌鸦死亡了，帧数从0开始
					WuYaXIndex=0;	
					
				}else{
					if(WuYaXIndex<41){
		                WuYaXIndex++;
					}
				}
			}
			if(wuyaHP>0||WuYaXIndex!=41){
				drawWuYa(WuYaX,WuYaXIndex);	//乌鸦死亡过后画图停止
			}else{
				if(isChouJiang){
					isChouJiang = false;
					MyGameCanvas.setST(MyGameCanvas.me.GmStat_GAMBLE);//跳转到抽奖状态
				}
				
			}
		}
	}
	void drawWuYa(int x,int index){
		GameDraw.renderAnimPic22(PAK_IMAGES.IMG_WUYA,
				(wuyaHP>0?0:6)+index/7%6, x , 240, MyGameCanvas.data_WUYA, false,
				false, 1219, 0, 0, 0);
		GameDraw.add_Image(PAK_IMAGES.IMG_LAOJIAXUETIAO, x, 180,0,0,(int)(160*(float)((float)wuyaHP/(float)200)),10, Tools.HCENTER_VCENTER,
				Tools.TRANS_NONE, 2000);// daoju
	}
	void chekXSJLi(){
		if(gameRank==2&&JN3>60&&isYuanZiDan&&iResult[0][3]==-1){
//			GameDraw.add_Image(PAK_IMAGES.IMG_JIAOXUE1, 520, 465, Tools.BOTTOM_RIGHT,
//					Tools.TRANS_NONE, 2000);// daoju	
		}
		if(xinShouJiangLi==0){return;} 
		switch (xinShouJiangLi) {
		case 1://第一关新手 奖励，结束后掉落一张南瓜卡片
			if(iResult[0][1]==-1){
			   chekXS_1();
			}
			break;
		case 2:
//			usBs.iUsBsRank =1;
//			if(iResult[0][2]==-1&&usBs.iUsBsRank==2){
//				dengdai++;
//			  MyGameCanvas.ZB[1]=7;
//				if(dengdai>=800){
//					usBs.iUsBsRank =1;
//					 MyGameCanvas.ZB[1]=-1;
//					 xinShouJiangLi=0;
//					 Speed = canvas.ZBXingXi[MyGameCanvas.ZB[usBs.iUsBsRank - 1]][8];
//					 usBs.iUsBsCurAttack = canvas.ZBXingXi[MyGameCanvas.ZB[usBs.iUsBsRank - 1]][3];
//				}
//			}
			break;
		case 3://第三关新手奖励，老头掉落原子弹
//			chekXS_3();
			
			break;
		 default:
//          System.out.println("");
			break;
		}
		
	}
	
	int XS_3_x=400,XS_3_y=-50,dengdai3;
	void chekXS_3(){
		if(XS_3_y<DianJi){
			XS_3_y+=15;
			dengdai3=0;
		}else{
			if(++dengdai3>=70&&dengdai3<=100){
				XS_3_x-=13;	
				XS_3_y+=21;	
			}else{
				fangda++;
				if(XS_3_y>480){
					BulletManager.Rank3_diyi=2;
					xinShouJiangLi=0;
				}
			}
		}

		
		
		
		drawXS_3();
	}
	void drawXS_3(){


		
		GameDraw.add_ImageScale(PAK_IMAGES.IMG_BUBING5, XS_3_x, XS_3_y, 132,
				0, 66, 64, Tools.HCENTER_VCENTER,Tools.TRANS_NONE,
				500,((XS_1_ScaleXY==0.3f?(fangda/15%2==0?0.3f:0.4f):XS_1_ScaleXY)+1.0f),
						((XS_1_ScaleXY==0.3f?(fangda/15%2==0?0.3f:0.4f):XS_1_ScaleXY))+1.0f);
	}
	
	
	
	int XS_1_y=-100,DianJi=240,dengdai,fangda;
	void chekXS_1(){
//		System.out.println("aaaaaaaaaaaaaaaaaaaaa");
		if(XS_1_y<DianJi){//dianji变量在点击时值会从新赋值为600，让卡卡片继续往下移动
			if(XS_1_ScaleXY<=0.8f&&DianJi==600){
				XS_1_ScaleXY+=0.05f;//等放大到0.8再往下
			}else{
				if(DianJi==600){
					if(++dengdai>=50){//放大后灯三秒再下落
						XS_1_y+=15;
					}
				}else{
					XS_1_y+=10;	
				}
			}
		}else{
			
			if(DianJi==600){//只有点击过一次后才跳转
			DianJi=240;
			xinShouJiangLi=0;
//			MyGameCanvas.setST(MyGameCanvas.GmStat_dengdai_win);
			shenli = true;
			dengdai=0;
			fangda=0;
			shenli = true;
			Tools.removeAllImage();
            isRank1 = true;
            
			}
			fangda++;
		}
		draweXS_1();
	}
	float XS_1_ScaleXY=0.3f;
	public boolean yuanZiDanTS=false;
	public boolean isGuanBi=false;
	void draweXS_1(){
		GameDraw.add_ImageScale(PAK_IMAGES.IMG_Z15,400, XS_1_y, Tools.HCENTER_VCENTER,
				Tools.TRANS_NONE, 500,XS_1_ScaleXY==0.3f?(fangda/15%2==0?0.3f:0.4f):XS_1_ScaleXY,
						XS_1_ScaleXY==0.3f?(fangda/15%2==0?0.3f:0.4f):XS_1_ScaleXY);
                if(XS_1_y>500){
                	if(MyGameCanvas.jiNengKaiQi[2]==-1){
                    	MyGameCanvas.jiNengKaiQi[2]=1;
                    	isGuanBi =true;
                	}

                	yuanZiDanTS=true;
                }
		
		//				GameDraw.add_Image(PAK_IMAGES.IMG_NANGUAGE, 400, XS_1_y, Tools.HCENTER_VCENTER,
//						Tools.TRANS_NONE, 500);// daoju
	}
	
    boolean paoTaiGJ=true;

    void chekPaoTai(GameRole role){//计算炮台
    	if(GameHit.hit(role.x, role.y, 40, 50, paotaix-15, paotaiy, 80, 80)){
    		role.setStatus(GameRole.STATUS_GONGJI);
    		paoTaiGJ = true;
    		role.x=paotaix+1;
    		role.y=paotaiy-20;
    	}
    	
    }
    
    int ziDan;
	void chekRank99() {
		if (gameRank != 99) {
			return;
		}
		
		GameDraw.add_Image(PAK_IMAGES.IMG_DJ9, 100, 50, Tools.HCENTER_VCENTER,
				Tools.TRANS_NONE, 5000);// daoju
		GameDraw.add_Image(PAK_IMAGES.IMG_Z0, 640, 440,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 5001);// daoju
		GameDraw.drawNumber(PAK_IMAGES.IMG_SHIJIANJIANGLI, ziDan, 630, 425, 28,
				0, Tools.TOP_LEFT, 5001, 38, 0, true);//子弹数量
		
		if(canvas.diTuSuiPian==0){
			if(iResult[0][2]<=0){
				GameDraw.add_Rect(0, 0, 800, 480, true, Tools.TOP_LEFT, 0x88000000, 4999);
				GameDraw.add_Image(PAK_IMAGES.IMG_DJ11, 330, 240, Tools.HCENTER_VCENTER,
						Tools.TRANS_NONE, 5000);// 规则说明	
			}else{
				GameDraw.add_Image(PAK_IMAGES.IMG_Z8, 400, 240, Tools.HCENTER_VCENTER,
						Tools.TRANS_NONE, 5000);// 规则说明
				return;
			}
			
			GameDraw.add_Image(PAK_IMAGES.IMG_Z3, 0, 15, Tools.TOP_LEFT,
					Tools.TRANS_NONE, 5000);// 金钱
			GameDraw.add_Image(PAK_IMAGES.IMG_Z4, 405, 5, Tools.TOP_LEFT,
					Tools.TRANS_NONE, 5000);// 游戏进度	

			GameDraw.add_Image(PAK_IMAGES.IMG_Z7, 535, 315, Tools.TOP_LEFT,
					Tools.TRANS_NONE, 5000);// 子弹
			
			return;
		}

		for (int i = 0; i < jiangLi.size(); i++) {
			JiangLiRank99 DaoJu = (JiangLiRank99) jiangLi.elementAt(i);
			DaoJu.move();
			DaoJu.drawDaoJu();
			chekWinOrLose(DaoJu, i);
		}

	}

	void chekWinOrLose(JiangLiRank99 DaoJu, int i) {
		if (DaoJu.x - 15000 > 1000) {
			iHit++;
			jiangLi.remove(i);
		}
		if (jiangLi.size() == 0
				&& MyGameCanvas.gameStatus == MyGameCanvas.GmStat_Playing) {
			MyGameCanvas.setST(MyGameCanvas.GmStat_dengdai_win);
			if(JL!=-1){
//				iResult[JL / 6][JL % 6] = 0;	
			}
			shenli = true;
		}
	}

	public void LianjiCount() {
		// iLianjiTime++;
		// if(iLianjiTime>=15){
		// / if(iLianjiFood>iMaxHit){iMaxHit = iLianjiFood;}
		// / iLianjiFood = 0;
		// iLianjiTime = 0;
		// }
	}

	/**
	 * 处理黄金鱼产生的逻辑 每一关每一波都有10%的概率
	 */
	int num;
	int waveNum;// 每一关黄金鱼出现的数目
	int waveNumMax = 2;// 每一关可以出现的黄金鱼的最大数目

	void goldFish() {
		if (gameRank == 0 || bProduce == false || waveNum >= waveNumMax
				|| iWaveTime == 0)
			return;
		// if(bTeach==true&&teachStep<14)return;
		if (MyGameCanvas.me.gameStatus == MyGameCanvas.me.gmFight) {
			waveNumMax = 5;
		}
		num = GameRandom.result(1, 11);
		if (num <= 2) {
			enemys.addElement(new GameRole(GameRole.TYPE_goldFish, 4, 0,
					GameInterface.DIR_RIGHT, Tools.TRANS_H, 0, 20, 30, 0,
					iWave, 0));
			waveNum++;
			bProduce = false;
			num = 0;
		} else {
			bProduce = false;
			num = 0;
		}
	}

	/**
	 * 每一关结束后，重复回复相关数据
	 */
	public void initData() {
		usBs.iUsBsRank = 1;
		// usBs.iUsBsCuJinbi = usBs.iJinbi[0];
		usBs.iUsBsCurAttack = 5;
		usBs.bAttackCenter = false;
		bFreakMoneyExtra = false;
		iFreakMoney = 0;
		role.enemyDelay = 0;
		MyGameCanvas.me.gameTime = 0;
		iTime = 0;
		iWaveTime = 0;
		iWave = 0;
		iWaveNum = 0;
		for (int i = 0; i < 8; i++) {
			iWavePlace[i] = 0;
		}
		iRunNum = 0;
		iHit = 0;
		if (MyActivity.VMWidth > 320) {
			role.iIsAccel = 1;
		} else {
			role.iIsAccel = 4;
		}
		iTaSellNum = 0;
		bBuildBing = false;
		iNumBookUse = 0;
		usBs.UsBsRota = 0;
		usBs.iAttackTime = 0;
		bSkill = false;
		MyGameCanvas.me.bPropShow = false;
		role.iceTime = 0;
		role.iCoolTime = 0;
		for (int i = 0; i < 3; i++) {
			role.iSkillCoolTime[i] = 0;
		}
		usBs.bHomeAttack = false;
		role.iBeishu = 1;
		role.dropJewel = 0;
		role.dropGold = 0;
		waveNum = 0;
		MyGameCanvas.me.iPrideWin = 0;
		iPropNum = 0;
		MyGameCanvas.me.tempAttack = 0;
	}

	/**
	 * 开始游戏前将游戏中所有能建塔的位置标示
	 */
	boolean bWeijian;

	public void drawMark() {
		// for(int i=0;i<(mapWidth/60);i++){
		// for(int j=0;j<(mapHeight/60);j++){
		// GameDraw.add_Rect(i*60,j*60,60,60,false,Tools.TOP_LEFT,0xffff00ff,500);
		// }
		// }
		//
		//
		if (MyGameCanvas.me.bPropShow == false)
			return;
		// if(MyGameCanvas.me.iTowerType<4){
		// GameDraw.add_Image(PAK_IMAGES.IMG_HUANGQUANQUAN,36+80*(MyGameCanvas.me.iTowerType-1),450,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,121);
		// }else{
		// GameDraw.add_Image(PAK_IMAGES.IMG_HUANGQUANQUAN,42+80*(MyGameCanvas.me.iTowerType+3),450,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,121);
		// }

		// switch(MyGameCanvas.me.iTowerType){
		// case GameRole.TA_LIEYU:
		// GameDraw.add_Image(PAK_IMAGES.IMG_HUANGQUANQUAN,36,450,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,121);
		// break;
		// case GameRole.TA_BINGJING:
		// GameDraw.add_Image(PAK_IMAGES.IMG_HUANGQUANQUAN,36+80,450,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,121);
		// break;
		// case GameRole.TA_YULEI:
		// GameDraw.add_Image(PAK_IMAGES.IMG_HUANGQUANQUAN,36+80*2,450,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,121);
		// break;
		// case GameRole.TA_HERO:
		// GameDraw.add_Image(PAK_IMAGES.IMG_HUANGQUANQUAN,42+80*7,450,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,121);
		// break;
		// case GameRole.TA_HUOPAO:
		// GameDraw.add_Image(PAK_IMAGES.IMG_HUANGQUANQUAN,42+80*8,450,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,121);
		// break;
		// case GameRole.TA_XIQIAN:
		// GameDraw.add_Image(PAK_IMAGES.IMG_HUANGQUANQUAN,42+80*9,450,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,121);
		// break;
		// }
		for (int i = 0; i < (mapWidth / 20); i++) {
			for (int j = 0; j < (mapHeight / 20); j++) {
				if (map.propData[i + j * map.mapSize[0]] == 17) {// 属性块全部错开1位,将起始能建塔的位置全部标示
				// if(MyGameCanvas.me.bPropShow==true){
				// GameDraw.add_Image(PAK_IMAGES.IMG_TA,i*map.tileWidth+map.tileWidth/2,
				// j*map.tileWidth+map.tileWidth/2,0,0,80,80,Tools.HCENTER_VCENTER,Tools.TRANS_NONE,49);
				// }
				}
			}
		}
	}

	/**
	 * 根据地图属性算出是否存在违建
	 */
	public void getUnLawBuild() {
		for (int i = 0; i < (mapWidth / 40); i++) {
			for (int j = 0; j < (mapHeight / 40); j++) {
				if (map.propData[i + j * map.mapSize[0]] == 12) {
					unLawBuilds.addElement(new GameRole(i, j,
							GameRole.UnLawBuildSmall, 60));
				} else if (map.propData[i + j * map.mapSize[0]] == 13) {
					unLawBuilds.addElement(new GameRole(i, j,
							GameRole.UnLawBuildBig, 110));
				}
			}
		}
	}

	/**
	 * 获取地图上课造塔的区域块的数目
	 * 
	 */
	int getPropNum() {
		for (int i = 0; i < (mapWidth / 20); i++) {
			for (int j = 0; j < (mapHeight / 20); j++) {
				if (map.propData[i + j * map.mapSize[0]] == 17) {
					iPropNum++;
				}
			}
		}
		return iPropNum;
	}

	void initStep1() {

	}

	/**
	 * 游戏中的教学 开启教学
	 */
	float scaleCirX = 0.50f;// 教学阶段放大缩小圆圈
	int scaleDelay;
	int iFingerDelay;// 用于手指在某一地方短暂的停留

	public void openTeachLogic() {
		if (bTeach == false || teachStep >= 10)
			return;
		switch (teachStep) {
		case 0:
			break;
		case 1:
		case 2:
		case 3:
		case 6:
		case 8:
		case 9:
			scaleDelay++;
			if (scaleDelay <= 20) {
				scaleCirX += 0.035;
			} else if (scaleDelay <= 41) {
				scaleCirX -= 0.035;
			} else {
				scaleDelay = 0;
				scaleCirX = 0.50f;
			}
			break;
		case 5:
			scaleDelay++;
			if ((scaleDelay >= 0 && scaleDelay <= 20)
					|| (scaleDelay >= 42 && scaleDelay <= 62)) {
				scaleCirX += 0.05f;
			} else if ((scaleDelay >= 21 && scaleDelay <= 41)
					|| (scaleDelay >= 63 && scaleDelay <= 83)) {
				scaleCirX -= 0.05f;
			} else if (scaleDelay >= 84) {
				scaleDelay = 0;
				scaleCirX = 0.50f;
				tmpX = 700;
				tmpY = 180;
			}
			break;
		}
	}

	public void openTeachPlaying() {
		if (bTeach == false)
			return;
		if (teachStep > 9 || gameRank >= 1
				|| MyGameCanvas.gmStatus != MyGameCanvas.gmScripe)
			return;// 教学完退出
		int imge[] = { PAK_IMAGES.IMG_XIAOSHOUZHI,
		// PAK_IMAGES.IMG_DASHOUZHI
		};
		for (int i = 0; i < enemys.size(); i++) {
			GameRole enemy = enemys.elementAt(i);
			// 第一波怪物的教学
			if ((iWave == 0)
					&& (enemy.x >= map.tileWidth * 3 && enemy.y <= map.tileWidth * 4 + 30)) {// 第一波到（4,5）位置
				if (teachStep == 0) {
					teachStep = 1;
				}
				if (iWavePlace[0] == 2 && teachStep <= 2) {
					teachStep = 2;
				}
				enemy.setStatus(GameInterface.STATUS_STOP);
				if (teachStep == 1 || teachStep == 3) {
					if (enemy.iEnemyHp > 0) {
						GameDraw.add_Image(
								imge[(MyGameCanvas.gameTime / 14) % 2], 150,
								330, Tools.HCENTER_VCENTER, Tools.TRANS_NONE,
								200);// （4,5）处画闪烁的手指
						// GameDraw.add_ImageScale(PAK_IMAGES.IMG_QUANQUAN,205,265,Tools.HCENTER_VCENTER,
						// Tools.TRANS_NONE,49,scaleCirX,scaleCirX);
						// GameDraw.add_Image(PAK_IMAGES.IMG_DAYUSHIHOUZI,90,275,Tools.BOTTOM_LEFT,
						// Tools.TRANS_NONE,50);
					} else {// 如果敌人被打死了,进入第二波但此时不出兵
						enemys.removeElementAt(i);
						scaleCirX = 0.50f;
						scaleDelay = 0;
						iTime = 0;
						if (teachStep == 3) {
							teachStep = 4;
							iWavePlace[0] = 0;
							iWave++;
							usBs.iUsBsCuJinbi += 10;
							iWaveTime = 0;
							scaleDelay = 0;
							scaleCirX = 0.50f;
						}
					}
				}
				// if(enemy.iEnemyHp>0){
				// GameDraw.add_Image(imge[(MyGameCanvas.gameTime/14)%2],150,330,
				// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,200);//（4,5）处画闪烁的手指
				// GameDraw.add_ImageScale(PAK_IMAGES.IMG_QUANQUAN,205,265,Tools.HCENTER_VCENTER,
				// Tools.TRANS_NONE,49,scaleCirX,scaleCirX);
				// GameDraw.add_Image(PAK_IMAGES.IMG_DAYUSHIHOUZI,90,275,Tools.BOTTOM_LEFT,
				// Tools.TRANS_NONE,50);
				// }else{//如果敌人被打死了,进入第二波但此时不出兵
				// enemys.removeElementAt(i);
				// teachStep+=1;
				// scaleCirX=0.50f;
				// scaleDelay = 0;
				// iTime = 0;
				// }
			}
			// 第二波的教学
			// if(iWave==1){
			// if(enemy.iEnemyHp<=0){enemys.removeElementAt(i);}
			// }//第二波
			// 第三波教学
			if (iWave == 1 && teachStep == 4 && enemys.size() > 0) {
				// if(enemy.y<=200){
				// for(i=0;i<enemys.size();i++){enemys.elementAt(i).setStatus(GameInterface.STATUS_STOP);}
				// teachStep=5;
				// if(MyGameCanvas.me.bPropShow==true){MyGameCanvas.me.bPropShow=false;}
				// }
				if (enemy.x >= 400) {
					enemy.setStatus(GameInterface.STATUS_STOP);
					teachStep = 5;
					if (MyGameCanvas.me.bPropShow == true) {
						MyGameCanvas.me.bPropShow = false;
					}
				}
			}
		}// for i<enemys.size()

		if (teachStep == 2 && usBs.iUsBsRank < 3) {// 老家必须升到3级的教学
			GameDraw.add_ImageRota(imge[(MyGameCanvas.gameTime / 10) % 2], 515,
					395, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 301, 180);
			// GameDraw.add_ImageScale(PAK_IMAGES.IMG_QUANQUAN,450,455,Tools.HCENTER_VCENTER,
			// Tools.TRANS_NONE,150,scaleCirX,scaleCirX);
			// GameDraw.add_Image(PAK_IMAGES.IMG_QIANGLIWUQIJIAOXUE,127,465,Tools.BOTTOM_LEFT,
			// Tools.TRANS_NONE,129);
		}

		// if(iWavePlace[0]==6&&enemys.size()==0&&iWave==1&&teachStep<8){teachStep=8;}
		// if(teachStep==4){//水晶教学
		// GameDraw.add_Image(imge[(MyGameCanvas.gameTime/15)%2],270,270,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,301);//小水晶处画闪烁的手指
		// GameDraw.add_ImageScale(PAK_IMAGES.IMG_QUANQUAN,325,205,Tools.HCENTER_VCENTER,
		// Tools.TRANS_NONE,150,scaleCirX,scaleCirX);
		// GameDraw.add_Image(PAK_IMAGES.IMG_LIANXUGONGJIZI,320,215,Tools.BOTTOM_LEFT,
		// Tools.TRANS_NONE,300);
		// }
		if (teachStep == 8) {// 教学造塔图标处画闪烁的手指
		// GameDraw.add_Image(PAK_IMAGES.IMG_ZAOTAJIAOXUE,25,460,
		// Tools.BOTTOM_LEFT,Tools.TRANS_NONE,180);
			GameDraw.add_ImageRota(imge[(MyGameCanvas.gameTime / 10) % 2],
					tmpTowerX, tmpTowerY, Tools.HCENTER_VCENTER,
					Tools.TRANS_NONE, 251, 180);
		}
		if (teachStep == 9) {// 提示在固定位置造塔
			GameDraw.add_Image(imge[(MyGameCanvas.gameTime / 10) % 2], 210,
					270, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 251);
			// GameDraw.add_ImageScale(PAK_IMAGES.IMG_TA1,270,210,Tools.HCENTER_VCENTER,
			// Tools.TRANS_NONE,150,scaleCirX,scaleCirX);
			// GameDraw.add_Image(PAK_IMAGES.IMG_TUODONGPAOTAI,260,220,
			// Tools.BOTTOM_LEFT,Tools.TRANS_NONE,250);
		}
		// if(teachStep==8){//铲子处画提示
		// GameDraw.add_Image(PAK_IMAGES.IMG_WAPAOTAI,770,390,Tools.BOTTOM_RIGHT,
		// Tools.TRANS_NONE,300);
		// GameDraw.add_ImageRota(imge[(MyGameCanvas.gameTime/15)%2],700,290,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,300,90);//铲子处画闪烁的手指
		// }
		// if(teachStep==9){//挖塔处画提示
		// GameDraw.add_Image(PAK_IMAGES.IMG_WAPAOTAI,770,390,Tools.BOTTOM_RIGHT,
		// Tools.TRANS_NONE,300);
		// GameDraw.add_ImageScale(PAK_IMAGES.IMG_QUANQUAN,620,150,Tools.HCENTER_VCENTER,
		// Tools.TRANS_NONE,150,scaleCirX,scaleCirX);
		// GameDraw.add_ImageRota(imge[(MyGameCanvas.gameTime/15)%2],
		// (36%(mapWidth/60))*map.tileWidth+90,
		// ((36-36%(mapWidth/60))/map.mapSize[0])*map.tileWidth+90,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,300,-90);
		// }
		// if(teachStep==11&&enemys.size()==0){
		// scaleCirX=0.50f;
		// scaleDelay = 0;
		// teachStep = 13;//教学结束
		// // iWavePlace[0]=0;iWavePlace[1]=0;iWavePlace[2]=0;
		// // iWave++;
		// // bProduce =true;
		// // iWaveTime = 40;
		// // if(MyGameCanvas.me.bFirstEnter==true){
		// // MyGameCanvas.me.EffectV.removeAllElements();//清除所有动画效果
		// // MyGameCanvas.setST(MyGameCanvas.me.GmStat_TeachReward);
		// // }else{//非第一次弹出下游戏正式开始的提示
		// //
		// MyGameCanvas.AddBlastEffectList(400,240,MyGameCanvas.me.EFF_START,0,200,0);
		// // }
		// }
		if (teachStep == 5 && enemys.size() == 0) {
			scaleCirX = 0.50f;
			scaleDelay = 0;
			teachStep = 7;// 教学结束
			MyGameCanvas.setST(MyGameCanvas.me.GmStat_GAMBLE);
		}
		if (bTeach == true && teachStep == 6
				&& MyGameCanvas.me.bTouchMoving == false) {// 拖动技能后释放完了
			if (enemys.size() == 0) {// 释放技能后怪物全部被杀死
				scaleCirX = 0.50f;
				scaleDelay = 0;
				teachStep = 7;// 教学结束
				MyGameCanvas.setST(MyGameCanvas.me.GmStat_GAMBLE);

				// iWavePlace[0]=0;iWavePlace[1]=0;iWavePlace[2]=0;
				// iWave++;
				// bProduce =true;
				// iWaveTime = 40;
				// if(MyGameCanvas.me.bFirstEnter==true){//如果第一次教学最后给予奖励界面
				// MyGameCanvas.me.EffectV.removeAllElements();//清除所有动画效果
				// MyGameCanvas.setST(MyGameCanvas.me.GmStat_TeachReward);
				// }else{//非第一次弹出下游戏正式开始的提示
				// MyGameCanvas.AddBlastEffectList(400,240,MyGameCanvas.me.EFF_START,0,200,0);
				// }
			} else {// 否则回到第二步骤继续
				teachStep = 5;
				scaleDelay = 0;
				scaleCirX = 0.50f;
				tmpX = 700;
				tmpY = 180;
			}
		}
		// if(teachStep==13){
		// MyGameCanvas.me.iGoldGet=100;
		// GameDraw.add_ImageRota(imge[(MyGameCanvas.gameTime/10)%2],220,405,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,301,90);
		// GameDraw.add_ImageScale(PAK_IMAGES.IMG_QUANQUAN,270,460,Tools.HCENTER_VCENTER,
		// Tools.TRANS_NONE,150,scaleCirX,scaleCirX);
		// GameDraw.add_Image(PAK_IMAGES.IMG_LINGQUBAOSHIJIAOXUE,270,465,Tools.BOTTOM_LEFT,
		// Tools.TRANS_NONE,129);
		// }
		if (teachStep == 5) {// 技能教学
			if (scaleDelay <= 5) {// 技能教学处,小手指暂停500毫秒
				GameDraw.add_Image(imge[1], tmpX, tmpY, Tools.HCENTER_VCENTER,
						Tools.TRANS_NONE, 301);
			} else if (scaleDelay <= 15) {
				GameDraw.add_Image(imge[0], tmpX, tmpY, Tools.HCENTER_VCENTER,
						Tools.TRANS_NONE, 301);
			} else if (scaleDelay < 84) {
				if (scaleDelay <= 60) {
					if (tmpY < 325) {
						int distance = BulletManager.GetDistance(tmpX, tmpY,
								340, 325);
						tmpX -= ((tmpX - 340) * 10 / distance);
						tmpY -= ((tmpY - 325) * 10 / distance);
						GameDraw.add_Image(imge[0], tmpX, tmpY,
								Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 301);
					} else {
						GameDraw.add_Image(imge[0], 335, 325,
								Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 301);
					}
				} else {
					GameDraw.add_Image(imge[0], 335, 325,
							Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 301);
				}

			}
			if (teachStep == 5) {
				// GameDraw.add_Image(PAK_IMAGES.IMG_JINENGJIAOXUE,770,150,Tools.BOTTOM_RIGHT,
				// Tools.TRANS_NONE,300);
				// GameDraw.add_ImageScale(PAK_IMAGES.IMG_QUANQUAN,390,260,Tools.HCENTER_VCENTER,
				// Tools.TRANS_NONE,49,scaleCirX,scaleCirX);
				GameDraw.add_Image(PAK_IMAGES.IMG_JIANTOU1, 575, 220,
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 250);
			}

		}

	}

	/**
	 * 画教学
	 * 
	 * @param obj
	 * @param vec
	 * @return
	 */

	int tmpX = 700;
	int tmpY = 180;
	int tmpTowerX = 100;
	int tmpTowerY = 390;

	public void getTeach() {
		// if(bTeach==false)return;
		// int imge[] = {PAK_IMAGES.IMG_XIAOSHOUZHI,PAK_IMAGES.IMG_DASHOUZHI};
		// for(int i=0;i<enemys.size();i++){
		// GameRole enemy = enemys.elementAt(i);
		// 教学第1波怪
		// if((iWave==0)&&(enemy.y>=map.tileWidth*3+20&&enemy.y<=map.tileWidth*4)){//第一波到（3,3）位置
		// enemy.setStatus(enemy.STATUS_STOP);
		// if(enemy.iEnemyHp>0){
		// iTeach[0]=1;
		// GameDraw.add_Image(imge[(MyGameCanvas.me.gameTime/8)%2],
		// (map.tileWidth*7)/2-60,(map.tileWidth*7)/2+60,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,200);//（3,3）处画闪烁的手指
		// }else{//如果敌人被打死了,继续第二波出兵
		// enemys.removeElementAt(i);
		// iTeach[0]=2;
		// iWavePlace[0]=0;
		// iWave++;
		// iWaveTime =30;iTime = 0;
		// }
		// }
		// //教学第二波怪
		// if((iWave==1)&&(enemy.x>=map.tileWidth*6+20&&enemy.x<=map.tileWidth*7)){
		// enemy.setStatus(enemy.STATUS_STOP);
		// if(enemy.iEnemyHp<=0){
		// enemys.removeElementAt(i);
		// }
		// if(enemys.size()>0){
		// GameDraw.add_Image(PAK_IMAGES.IMG_ANZHUZI,400,200,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,200);//只要第二波怪物未全死，始终画按住字
		//
		// if(iTeach[0]==2){
		// GameDraw.add_Image(imge[(MyGameCanvas.me.gameTime/8)%2],700,260,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,200);//技能1处画闪烁的手指
		// }else if(iTeach[0]==3){//手按住技能1处,手指从技能1处滑向（4,6）
		// iTeachDelay++;
		// tmpX = 700-10*iTeachDelay;
		// tmpY = 260+2*iTeachDelay;
		// if(tmpY<=330){
		// GameDraw.add_Image(imge[0],tmpX,tmpY,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,200);
		// }else{//手指滚到目的地后消失
		// }
		// }
		// }else{
		// iWavePlace[0]=0;iWavePlace[1]=0;
		// iWave++;
		// iWaveTime = 40;
		// }
		// }
		// }
		// //第三波

	}

	/**
	 * 画点击屏幕，弹出建塔或者卖升级界面
	 */
	int tempLevel;// 临时的塔最高等级
	int iNumX;// 生/卖 塔的时候对应价格数字的x坐标
	float scalX = 1;
	float scalY = 1;

	public void drawTA(int status) {// 造塔界面的有效触碰区域

	}

	/**
	 * 游戏中滑屏处理
	 * 
	 * @param obj
	 * @param vec
	 * @return
	 */

	public void move(int moveX, int moveY) {// 960,720
		this.iMapX = (short) Math.max(0,
				Math.min(mapWidth - MyActivity.VMWidth, this.iMapX + moveX));
		this.iMapY = (short) Math.max(0,
				Math.min(mapHeight - MyActivity.VMHeight, this.iMapY + moveY));
	}

	/**
	 * 点击技能，但未松手时的效果 indexDelay 动画帧 type 释放的究竟是哪一种技能
	 */

	public void pushSkill(int x, int y, int type) {
		if ((x <= 0 && y <= 0) || type <= 0)
			return;
		x = (int) ((float) (x * MyGameCanvas.SCREEN_WIDTH) / MyActivity.VMWidth);
		y = (int) ((float) (y * MyGameCanvas.SCREEN_HEIGHT) / MyActivity.VMHeight);// 转换为自适屏的
		// if(role.iSkillCoolTime[type-1]>0){return;}
		switch (type) {
		case 1:// 电磁风暴
			GameDraw.add_ImageRota(PAK_IMAGES.IMG_SHANDIANQUAN1, x, y,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 160,
					MyGameCanvas.me.gameTime % 360);
			break;
		case 2:// 霜冻
			// GameDraw.add_ImageRota(PAK_IMAGES.IMG_SHANDIANQUAN2, x, y,
			// Tools.HCENTER_VCENTER,
			// Tools.TRANS_NONE,160,MyGameCanvas.gameTime%360);
			break;
		case 3:
			// GameDraw.add_ImageRota(PAK_IMAGES.IMG_SHANDIANQUAN3, x, y,
			// Tools.HCENTER_VCENTER,
			// Tools.TRANS_NONE,160,MyGameCanvas.gameTime%360);
			break;
		}
	}

	/**
	 * 获取离手时候技能的信息
	 * 
	 * @param x
	 * @param y
	 * @param type
	 */
	public void getSkill(int x, int y, int type) {
		skillX = x;
		skillY = y;
		skillType = type;
	}

	/***
	 * 
	 * @param type
	 *            释放哪种技能
	 * @param attack
	 *            技能杀伤力
	 * @param x
	 * @return y技能离手屏幕坐标
	 */
	boolean bSkill;
	int bskillDelay;
	int ranNum;// 随机点
	int numNotconi;// 判断两个闪电砸向点是否重合
	int iSkillKind;// 技能特效的标示序列

	public void releaseSkill(int x, int y, int type) {
		if (bSkill == false)
			return;
		x = (int) ((float) (x * MyGameCanvas.SCREEN_WIDTH) / MyActivity.VMWidth);
		y = (int) ((float) (y * MyGameCanvas.SCREEN_HEIGHT) / MyActivity.VMHeight);// 转换为自适屏的

		int skillRan[][] = new int[][] { { x, y }, { x + 90, y },
				{ x + 70, y + 50 }, { x + 50, y + 70 }, { x - 50, y + 70 },
				{ x - 70, y + 50 }, { x - 90, y }, { x - 70, y - 50 },
				{ x - 50, y - 70 }, { x, y - 90 }, { x + 50, y - 70 },
				{ x + 70, y - 50 }, { x + 70, y + 70 }, { x - 70, y + 70 },
				{ x - 70, y - 70 }, { x + 70, y - 50 } };// 技能事先选好固定砸向点

		ranNum = GameRandom.result(1, 17);
		if (ranNum == numNotconi) {// 技能砸向点重合，则人为错开
			ranNum = ranNum + (ranNum >= 12 ? -3 : 3);
		}
		iSkillKind = type - 37;
		switch (type) {
		case GameRole.SKILL_STORM:// 37 闪电，只释放一个
			// fishGame.instance._mView.waf.StartWav(6,false);
			// MyGameCanvas.AddBlastEffectList(x,y,MyGameCanvas.me.EFF_SHANDIANDIS,0,180,0);
			// MyGameCanvas.AddBlastEffectList(x,y,MyGameCanvas.EFF_SKILL_SHANDIAN,0,180,0);
			// if(bTeach==false||(bTeach==true&&teachStep>=11)){
			// MyGameCanvas.me.iGold-=100;
			// }
			// bSkill = false;
			// break;
		case GameRole.SKILL_FROST:// 38霜冻
		case GameRole.SKILL_RECOVER:// 火球
			bskillDelay++;
			if (bskillDelay == 1) {
				MyActivity.instance._mView.waf.StartWav(
						6 - (type != GameRole.SKILL_STORM ? 5 : 0), false);
				MyGameCanvas.AddBlastEffectList(x, y,
						MyGameCanvas.me.EFF_SHANDIANDIS, 0, 180, 0);
				// if(role.iSkillNum[iSkillKind]>=1){
				if (bTeach == false || (bTeach == true && teachStep >= 9)) {
					MyGameCanvas.me.iGold -= 0;
				}
				// }
			}

			if (bskillDelay % 10 == 0
					&& bskillDelay <= 210 + (iSkillKind == 2 ? 9 : 0)) {

				MyGameCanvas.AddBlastEffectList(skillRan[ranNum - 1][0],
						skillRan[ranNum - 1][1],
						MyGameCanvas.EFF_SKILL_SHANDIAN, 0, 180, 0);

				numNotconi = ranNum;
			} else if (bskillDelay > 21 + (iSkillKind == 2 ? 9 : 0)) {
				bSkill = false;
				numNotconi = 0;
				bskillDelay = 0;
			}
			break;
		}
		role.setCoolTime(type, 0);
		// MyGameCanvas.me.saveGame();//技能的使用个数需存档
	}

	/**
	 * 卷折的使用
	 * 
	 */
	public void bookUse(int type) {
		// switch(type){
		// case 1://卷折1的使用:攻击核心,老家攻击力+10
		// if(usBs.iBookNum[0]>0 && usBs.bAttackCenter==false){
		// usBs.iBookNum[0]--;
		// usBs.bAttackCenter = true;
		// usBs.iUsBsAttack[usBs.iUsBsRank-1]+=10;
		// MyGameCanvas.AddBlastEffectList(usBs.iUsBsX-5,usBs.iUsBsY-8,MyGameCanvas.EFF_ATTACKHEXIN,0,120,0);
		// }
		// break;
		// case 2://资源宝箱,一关只能使用1此,小怪爆的金币+10%
		// if(usBs.iBookNum[1]>0&&bFreakMoneyExtra==false){
		// usBs.iUsBsCuJinbi+=1000;
		// usBs.iBookNum[1]--;
		// // if(bFreakMoneyExtra==false){
		// bFreakMoneyExtra = true;
		// iFreakMoney += 10;
		// MyGameCanvas.AddBlastEffectList(usBs.iUsBsX-2,usBs.iUsBsY-1,MyGameCanvas.EFF_ZIYUANBAOXIANG,
		// 0,120,0);
		// // }
		// }
		// break;
		// case 3://生命试剂,每次回复50滴血hp+=50
		// if(usBs.iBookNum[2]>0 &&
		// usBs.iUsBsCuHp<usBs.iUsBsHp[usBs.iUsBsRank-1]){
		// usBs.iBookNum[2]--;
		// usBs.iUsBsCuHp+=50;
		// if(usBs.iUsBsCuHp>=usBs.iUsBsHp[usBs.iUsBsRank-1]){usBs.iUsBsCuHp=usBs.iUsBsHp[usBs.iUsBsRank-1];}
		// MyGameCanvas.AddBlastEffectList(usBs.iUsBsX-5,usBs.iUsBsY-7,MyGameCanvas.EFF_SHENGMINGSHIJI,0,120,0);
		// }
		// break;
		// case 4://资源卡，金币+300
		// if(usBs.iBookNum[3]>0){
		// usBs.iBookNum[3]--;
		// usBs.iUsBsCuJinbi+=300;
		// MyGameCanvas.AddBlastEffectList(usBs.iUsBsX,usBs.iUsBsY,MyGameCanvas.EFF_ZIYUANQIA,0,120,0);
		// }
		// break;
		// }
		// iNumBookUse++;
		// if(iNumBookUse>=10){
		// iAchieveNum = 11;
		// MyGameCanvas.AddBlastEffectList(400,680,MyGameCanvas.EFF_GETACHIEVE,
		// 0,180,180);
		// }
	}

	// ///////////////////////////////主角攻击判断/////////////////////////////////////

	/**
	 * 判断怪物是否在技能的攻击范围内,并计算伤害
	 * 
	 * @param obj
	 * @param vec
	 * @return 技能攻击范围为300的矩形
	 */
	public void checkCrash(int x, int y, int kind) {
		if (enemys == null)
			return;
		for (int i = 0; i < enemys.size(); i++) {
			GameRole enemy = (GameRole) enemys.elementAt(i);
			if (BulletManager.bInCircle(enemy.x, enemy.y, x, y, skillR)) {
				switch (kind) {
				case 0:// 技能1减少90%血量
					if (enemy.iEnemyHp > 0) {
						enemy.iEnemyHp -= (enemy.iEnemyHpNum * 90 / 100); // 技能伤害lh
						// if(enemy.iEnemyHp<=0){
						// role.DropGold(iWave,x,y);
						// if(bTeach==true){//教学技能杀死飞怪直接清除
						// enemys.removeElementAt(i);
						// }else{
						// enemy.setStatus(GameInterface.STATUS_DEAD);
						// }
						// }
					}
					break;
				case 2:// 300伤害
					if (enemy.iEnemyHp > 0) {
						enemy.iEnemyHp -= (300);
						// if(enemy.iEnemyHp<=0){
						// role.DropGold(iWave,x,y);
						// if(bTeach==true){//教学技能杀死飞怪直接清除
						// enemys.removeElementAt(i);
						// }else{
						// enemy.setStatus(GameInterface.STATUS_DEAD);
						// }
						// }
					}
					break;
				case 1:// 50伤害
					enemy.iEnemyHp -= 50;
					enemy.iFrozenTime = 50;
					// if(enemy.iEnemyHp<=0){
					// role.DropGold(iWave,x,y);
					// enemy.setStatus(enemy.STATUS_DEAD);
					// }
					break;
				}
				if (enemy.iEnemyHp <= 0) {
					role.DropGold(iWave, x, y, enemy.type);
					enemy.setStatus(GameInterface.STATUS_DEAD);
					if (enemy.type == GameRole.TYPE_goldFish) {
						MyGameCanvas.me.iGold += 30;
						MyGameCanvas.AddBlastEffectList(enemy.x, enemy.y,
								MyGameCanvas.me.EFF_GOLDFISHDEAD, 0, 200, 0);

					}
				}

			}
		}
	}

	/**
	 * 判断技能是否处于冷却时间
	 * 
	 * @param obj
	 * @param vec
	 * @return
	 */
	int contlaojia;
	int contlaojiaWD;
	int countSpeed;

	public void skillCold() {

		if (usBs.laoJiaWuDi) {
			if (++contlaojiaWD > 500) {
				contlaojiaWD = 0;
				usBs.laoJiaWuDi = false;
			}
		}
		if (usBs.sheSuJiaBei) {
			if (contlaojia == 1) {
				// countSpeed = Speed;
				Speed = Speed * 2;

			}

			if (++contlaojia == 500) {
				Tools.removeImage(PAK_IMAGES.IMG_JIASU);
				contlaojia = 0;
				usBs.sheSuJiaBei = false;
				Speed = canvas.ZBXingXi[canvas.ZB[usBs.iUsBsRank - 1]][8];
			}
			// if(contlaojia==1){

			// }

		}
		for (int i = 0; i < 4; i++) {
			if (role.iSkillCoolTime[i] > 0) {
				role.iSkillCoolTime[i]--;
			}
		}
	}

	/**
	 * 判断是否触发金币达到1W的成就 游戏中实时判断
	 */
	public void everGetJinbi() {
		if (MyGameCanvas.me.iGold >= 10000 && iAchieve[7] == 0) {
			iAchieve[7] = 1;
			iAchieveNum = 8;
			MyGameCanvas.AddBlastEffectList(400, 680,
					MyGameCanvas.EFF_GETACHIEVE, 0, 180, 0);// 触发第8个成就
		}
		if (iNumJianyu >= 10 && iAchieve[5] == 0) {
			iAchieve[5] = 1;
			iAchieveNum = 6;
			MyGameCanvas.AddBlastEffectList(400, 680,
					MyGameCanvas.EFF_GETACHIEVE, 0, 180, 0);// 触发第6个成就
		}
		if (iNumShayu >= 10 && iAchieve[6] == 0) {
			iAchieve[6] = 1;
			iAchieveNum = 7;
			MyGameCanvas.AddBlastEffectList(400, 680,
					MyGameCanvas.EFF_GETACHIEVE, 0, 180, 0);// 触发第7个成就
		}
		if (iNumBookUse >= 10 && iAchieve[10] == 0) {
			iAchieve[10] = 1;
			iAchieveNum = 11;
			MyGameCanvas.AddBlastEffectList(400, 680,
					MyGameCanvas.EFF_GETACHIEVE, 0, 180, 0);// 触发第11个成就
		}
		MyGameCanvas.me.saveGame();
	}

	public boolean checkAttack(GameRole obj, Vector<?> vec) { // 进入攻击范围
		for (int j = 0; j < vec.size(); j++) {
			GameRole enemy = (GameRole) vec.elementAt(j);
			// if(enemy.curStatus==enemy.STATUS_DISAPPEAR||
			// enemy.){
			// continue;
			// }
			if (Math.abs(obj.x - enemy.x) < obj.roleAttackArea) {
				if (GameRandom.isSuccess(obj.double_attack)) {
					obj.setStatus(GameInterface.STATUS_SKILL1);
				} else {
					obj.setStatus(GameInterface.STATUS_ATTACK);
				}
				return true;
			}
		}
		return false;
	}

	public boolean checkRoleAttack(GameRole obj) { // 主角进入敌人攻击范围
	// if (MyGameCanvas.iHp <= 0) {
	// return false;
	// }

		if (Math.abs(obj.x - role.x) < obj.roleAttackArea) {
			if (GameRandom.isSuccess(obj.double_attack)) {
				obj.setStatus(GameInterface.STATUS_SKILL1);
			} else {
				obj.setStatus(GameInterface.STATUS_ATTACK);
			}
			return true;
		}
		return false;
	}

	public boolean isDefend(GameRole obj, Vector<?> vec) {
		for (int j = 0; j < vec.size(); j++) {
			GameRole enemy = (GameRole) vec.elementAt(j);
			if (Math.abs(obj.x - enemy.x) < obj.roleAttackArea) {
				return true;
			}
		}
		return false;
	}

	// private void checkPlayerAttack(Vector<GameRole> sprites,
	// Vector<GameRole> enemys) {
	// if (sprites == null) {
	// return;
	// }
	// if (enemys == null) {
	// return;
	// }
	// for (int j = 0; j < sprites.size(); j++) {
	// GameRole sprite = (GameRole) sprites.elementAt(j);
	// // if(sprite.type==sprite.TYPE_ROLE_步兵){
	// //
	// System.out.println("curStatus"+sprite.curStatus+"     curIndex:"+sprite.curIndex+"   "+sprite.index+"     "+sprite.isAttack
	// // );
	// // }
	//
	// if (sprite.isAttack) {
	//
	// continue;
	// }
	// if (sprite.type == GameRole.TYPE_ENEMY_老家) {
	// continue;
	// }
	//
	// for (int i = enemys.size() - 1; i >= 0; i--) {
	// // for (int i = 0; i < enemys.size(); i++) {
	// GameRole enemy = (GameRole) enemys.elementAt(i);
	// if (enemy.curStatus == GameInterface.STATUS_FUKONG
	// || enemy.hp <= 0 || enemy.injureTime > 0) {
	// continue;
	// }
	//
	// if (GameHit.hit_box(sprite, enemy)) {
	// if (sprite.type < 20) { // 主角攻击
	//
	// if (sprite.type == GameRole.TYPE_ROLE_炸弹兵) {
	// enemy.InjureEnemy(
	// sprite.attack,
	// GameRole.HURT_炸弹兵,
	// sprite.curStatus == GameInterface.STATUS_SKILL1);
	//
	// } else {
	// enemy.InjureEnemy(
	// sprite.attack,
	// (sprite.curStatus == GameInterface.STATUS_SKILL1 ? GameRole.HURT_技能
	// : GameRole.HURT_普通),
	// sprite.curStatus == GameInterface.STATUS_SKILL1);
	//
	// }
	// sprite.isAttack = true;
	// } else { // 主角被攻击
	// if (check盾兵(sprite)) {
	//
	// } else if (sprite.type == GameRole.TYPE_ENEMY_铁人) {
	// enemy.InjureEnemy(
	// sprite.attack,
	// (sprite.curStatus == GameInterface.STATUS_SKILL1 ? GameRole.HURT_电击
	// : GameRole.HURT_普通), false);
	//
	// } else if (sprite.type == GameRole.TYPE_ENEMY_张飞) {
	// enemy.InjureEnemy(
	// sprite.attack,
	// (sprite.curStatus == GameInterface.STATUS_SKILL1 ? GameRole.HURT_技能
	// : GameRole.HURT_普通), true);
	//
	// }
	//
	// else {
	// enemy.InjureEnemy(
	// sprite.attack,
	// (sprite.curStatus == GameInterface.STATUS_SKILL1 ? GameRole.HURT_技能
	// : GameRole.HURT_普通),
	// sprite.curStatus == GameInterface.STATUS_SKILL1);
	// sprite.isAttack = true;
	// }
	// }
	// break;
	// }
	// }
	// }
	// }

	// 碰撞检查

	public void check() {
		checkRoleShot();
		checkEnemyShot();
		checkEnemyShot_Role();
		// checkDefend(enemys, sprites);

		// checkPlayerAttack(sprites, enemys);
		// checkPlayerAttack(enemys, sprites);
		checkRole();
	}

	void checkRole() {
		// if (enemys == null) {
		// return;
		// }
		// for (int i = 0; i < enemys.size(); i++) {
		// GameRole enemy = (GameRole) enemys.elementAt(i);
		// if (enemy.curStatus == GameInterface.STATUS_FUKONG || enemy.hp <= 0
		// || enemy.injureTime > 0) {
		// continue;
		// }
		//
		// if (GameHit.hit_box(enemy, role)) {
		// if (check盾兵(enemy)) {
		//
		// } else {
		// role.InjureRole(enemy.attack,
		// enemy.curStatus == GameInterface.STATUS_SKILL1);
		// enemy.isAttack = true;
		// }
		// }
		// }
	}

	// private void checkDefend(Vector sprites, Vector enemys) {
	// //圆盾兵要单独判断，先打圆盾兵
	// if (sprites == null) {
	// return;
	// }
	// if (enemys == null) {
	// return;
	// }
	// for (int j = 0; j < sprites.size(); j++) {
	// GameRole sprite = (GameRole) sprites.elementAt(j);
	// if (sprite.isAttack) {
	// continue;
	// }
	// for (int i = 0; i < enemys.size(); i++) {
	// GameRole enemy = (GameRole) enemys.elementAt(i);
	// if (enemy.curStatus == GameInterface.STATUS_FUKONG ||
	// enemy.hp <= 0 ||
	// enemy.injureTime > 0
	// ) {
	// continue;
	// }
	// if (enemy.type == enemy.TYPE_ROLE_圆盾兵) {
	// if (Tools.hit_box(sprite, enemy)) {
	// enemy.InjureEnemy(sprite.attack,
	// false);
	// sprite.isAttack = true;
	// break;
	// }
	// }
	// }
	// }
	// }

	// boolean check盾兵(GameRole enemy) {
	// for (int j = 0; j < sprites.size(); j++) {
	// GameRole sprite = (GameRole) sprites.elementAt(j);
	// if (sprite.type == GameRole.TYPE_ROLE_圆盾兵) {
	// if (GameHit.hit_box(enemy, sprite)) {
	// sprite.InjureEnemy(
	// enemy.attack,
	// enemy.type == GameRole.TYPE_ENEMY_巫师 ? GameRole.HURT_巫师
	// : GameRole.HURT_普通, false);
	// enemy.isAttack = true;
	// return true;
	// }
	// }
	// }
	// return false;
	// }

	public final boolean hit_box_shot(int x1, int y1, short[][] data1,
			int curIndex1, boolean isLeft1, GameInterface sprite) { // 子弹与精灵的碰撞
		int[] attackBox = GameInterface.hitArea(data1[1], curIndex1, true,
				!isLeft1);
		int[] coxBox = GameInterface.hitArea(sprite.data[1], sprite.curIndex,
				false, !sprite.isLeft);
		if (attackBox[2] == 0) {
			return false;
		}

		// GameDraw.addObject(Tools.TYPE_RECT,
		// x1 + attackBox[0], y1 + attackBox[1],
		// attackBox[2], attackBox[3], false,
		// Tools.BOTTOM_LEFT, 0xff0000, 100);
		//
		// GameDraw.addObject(Tools.TYPE_RECT,
		// sprite.sx
		// + coxBox[0], sprite.sy + coxBox[1],
		// coxBox[2], coxBox[3]
		// , false,
		// Tools.BOTTOM_LEFT, 0xffff00, 100);

		if (GameHit.hit(x1 + attackBox[0], y1 + attackBox[1], attackBox[2],
				attackBox[3], sprite.sx + coxBox[0], sprite.sy + coxBox[1],
				coxBox[2], coxBox[3])) {
			return true;
		}
		return false;
	}

	// int itemId, itemColor;

//	static int[] propTimeIndex = { // 造兵延时index
//	0, 0, 0, 0, 0, 0, 0, 0, 0 };
//
//	int getBuildTime(int index) {
//		return propTimeIndex[index];
//	}

//	static int[] foodIndex = { // 造兵消耗的食物
//	10, 20, 30, 40, 50, 70, 90, 120, 180 };
//
//	int[] skill_mp = { // 技能消耗的魔法
//	20, 32, 25, 27, 40, 10 };

	// int[] skill_mp = { // 技能消耗的魔法
	// 0, 0, 0, 0, 0, 0 };

	// int getSkillMp(int skillIndex) {
	// int mp = 0;
	// switch (skillIndex) {
	// case GameMenuItem.Goods_type_火焰球:
	// case GameMenuItem.Goods_type_冰冻球:
	// case GameMenuItem.Goods_type_闪电球:
	// case GameMenuItem.Goods_type_医疗球:
	// case GameMenuItem.Goods_type_力量球:
	// return skill_mp[skillIndex - 8];
	//
	// case GameMenuItem.Goods_type_兵粮球:
	// mp = MyGameCanvas.menuItem
	// .setGoodsProp(GameMenuItem.Goods_type_兵粮球);
	// break;
	// }
	//
	// return mp;
	// }

	// int food = 50; //现有粮食
	// int food_Max = 100; //Z最大粮食

	void drawTime(int x, int y, int time, int curIndex, int layer) {
		// switch (curIndex) {
		// case 0: // 秒
		// int sec = time / 10 % 60;
		// if (sec < 10) {
		// GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER1, 0, x, y, 12, 1,
		// Tools.BOTTOM_LEFT, layer, 16, 0, true);
		//
		// GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER1, sec, x + 12, y,
		// 12, 1, Tools.BOTTOM_LEFT, layer, 16, 0, true);
		//
		// } else {
		// GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER1, sec, x, y, 12,
		// 1, Tools.BOTTOM_LEFT, layer, 16, 0, true);
		//
		// }
		// break;
		// case 1: // 分
		//
		// int min = time / 10 / 60;
		// if (min < 10) {
		// GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER1, 0, x, y, 12, 1,
		// Tools.BOTTOM_LEFT, layer, 16, 0, true);
		//
		// GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER1, min, x + 15, y,
		// 12, 1, Tools.BOTTOM_LEFT, layer, 16, 0, true);
		// } else {
		// GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER1, min, x, y, 12,
		// 1, Tools.BOTTOM_LEFT, layer, 16, 0, true);
		//
		// }
		//
		// break;
		//
		// default:
		//
		// break;
		// }

	}

	// public void drawInterFace(int x, int y) {
	//
	// int[][] array = { { 0, 0, 96, 58 },/* 图片说明 */
	// { 97, 0, 95, 58 } };/* 图片说明 */
	//
	// if (role.hp >= role.hp_max) {
	// role.hp = role.hp_max;
	// }
	// if (role.mp >= role.mp_max) {
	// role.mp = role.mp_max;
	// }
	//
	// // Log.i("drawInterFace", "" + GameEngine.role.food_max);
	//
	// GameDraw.add_Image(PAK_IMAGES.IMG_FONT10, x + 480, y
	// - MyGameCanvas.SCREEN_HEIGHT, array[0], Tools.TOP_LEFT,
	// Tools.TRANS_NONE, 20);// 人物头像
	//
	// GameDraw.add_Image(PAK_IMAGES.IMG_FONT10, x + 600, y
	// - MyGameCanvas.SCREEN_HEIGHT, array[1], Tools.TOP_LEFT,
	// Tools.TRANS_NONE, 20);// 人物头像
	//
	// GameDraw.add_Image(PAK_IMAGES.IMG_MENU21, x, y
	// - MyGameCanvas.SCREEN_HEIGHT, 163, 148, 95, 42, Tools.TOP_LEFT,
	// Tools.TRANS_NONE, 20);// 人物头像
	//
	// // GameDraw.addObject(PAK_IMAGES.IMG_MENU21_PNG, x, y + 40
	// // - MyGameCanvas.SCREEN_HEIGHT, 545, 148, 93, 42, Tools.TOP_LEFT,
	// // Tools.TRANS_NONE, 20);// 人物头像
	//
	// GameDraw.add_Image(PAK_IMAGES.IMG_MENU21, x + 150, y + 10
	// - MyGameCanvas.SCREEN_HEIGHT, 263, 148, 276, 43,
	// Tools.TOP_LEFT, Tools.TRANS_NONE, 20);// 人物头像
	//
	// int[][] music = { { 2, 1, 63, 63 },/* 图片说明 */
	// { 66, 1, 62, 63 },/* 图片说明 */};
	//
	// if (MyGameCanvas.isSound) {
	// GameDraw.add_Image(PAK_IMAGES.IMG_MUSIC, x + 720, y + 5
	// + -MyGameCanvas.SCREEN_HEIGHT, music[0], Tools.TOP_LEFT,
	// Tools.TRANS_NONE, 20);// 人物头像
	// } else {
	// GameDraw.add_Image(PAK_IMAGES.IMG_MUSIC, x + 720, y + 5
	// + -MyGameCanvas.SCREEN_HEIGHT, music[1], Tools.TOP_LEFT,
	// Tools.TRANS_NONE, 20);// 人物头像
	// }
	//
	// // role.level = 10;
	// GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER1, role.level, x
	// + (role.level < 10 ? 60 : 53), y - MyGameCanvas.SCREEN_HEIGHT
	// + 38, 12, 1, Tools.BOTTOM_LEFT, 20, 16, 0, true);// 等级
	//
	// // drawTime(x + 548, y - MyGameCanvas.SCREEN_HEIGHT + 18, time, 0, 20);
	// // drawTime(x + 598, y - MyGameCanvas.SCREEN_HEIGHT + 18, time, 1, 20);
	//
	// GameDraw.add_Image(PAK_IMAGES.IMG_MENU21, x + 62, y - 129, 0, 197,
	// (110 * role.food) / role.food_max, 6, Tools.BOTTOM_LEFT,
	// Tools.TRANS_NONE, 200); //
	//
	// GameNumber.drawAddNum_mid(PAK_IMAGES.IMG_NUMBER6,
	// PAK_IMAGES.IMG_NUMBER6, role.food, role.food_max, x + 110,
	// y - 128, 7, 9, 201, Tools.BOTTOM_LEFT, 0);
	//
	// GameDraw.add_Image(PAK_IMAGES.IMG_MENU21, x + 737, y - 129, 110
	// + 110 - (110 * role.mp) / role.mp_max, 197, (110 * role.mp)
	// / role.mp_max, 6, Tools.BOTTOM_RIGHT, Tools.TRANS_NONE, 200); //
	//
	// GameNumber.drawAddNum_mid(PAK_IMAGES.IMG_NUMBER6,
	// PAK_IMAGES.IMG_NUMBER6, role.mp, role.mp_max, x + 689,
	// y - 128, 7, 9, 201, Tools.BOTTOM_LEFT, 0);
	//
	// GameDraw.add_Image(PAK_IMAGES.IMG_MENU21, x, y, 0, 0, 800, 150,
	// Tools.BOTTOM_LEFT, Tools.TRANS_NONE, 50);// interface
	//
	// int col = 80;
	// int a = 50;
	// for (int i = 0; i < GameMenuItem.propLeavelData.length; i++) {
	// GameMenuItem.drawPropIcon(x + 33 + i * col + a, y - 83, i,
	// GameMenuItem.propLeavelData[i] > 0 ? 0 : 1, false, false,
	// 200);
	// if (propTimeIndex[i] > 0) {
	// propTimeIndex[i]--;
	// }
	// GameDraw.add_Image(PAK_IMAGES.IMG_MENU23, x + 3 + i * col + a,
	// y - 83 + 24, 0, 0, 54, (54 * propTimeIndex[i])
	// / MyGameCanvas.menuItem.getPropDelayTime(i),
	// Tools.BOTTOM_LEFT, Tools.TRANS_NONE, 200);
	//
	// }
	//
	// GameDraw.add_Image(PAK_IMAGES.IMG_BUTTON1, x + 40, y - 55, 0, 0,
	// 54, 56, Tools.HCENTER_TOP, Tools.TRANS_H, 310);
	//
	// GameDraw.add_Image(PAK_IMAGES.IMG_BUTTON1, x + 220, y - 55, 0, 0,
	// 54, 56, Tools.HCENTER_TOP, Tools.TRANS_NONE, 310);
	//
	// GameDraw.add_Image(PAK_IMAGES.IMG_MENU21, x + 257, y
	// - MyGameCanvas.SCREEN_HEIGHT + 30, 110 + 110 - (63 * role.hp)
	// / role.hp_max, 197, (63 * role.hp) / role.hp_max, 2,
	// Tools.TOP_RIGHT, Tools.TRANS_NONE, 20);
	//
	// // GameDraw.addObject(PAK_IMAGES.IMG_VIPSHOP_PNG, x
	// // + MyGameCanvas.SCREEN_WIDTH - 50, y
	// // - MyGameCanvas.SCREEN_HEIGHT + 120, Tools.HCENTER_VCENTER,
	// // Tools.TRANS_NONE, 300);
	//
	// if (enemys.size() > 0) {
	// GameRole enemy = (GameRole) enemys.elementAt(0);
	// if (enemy.type == GameRole.TYPE_ENEMY_老家
	// || enemy.type > GameRole.TYPE_ENEMY_老家) {
	//
	// GameDraw.add_Image(PAK_IMAGES.IMG_MENU21, x + 315, y
	// - MyGameCanvas.SCREEN_HEIGHT + 30, 0, 197,
	// (63 * enemy.hp) / enemy.hp_max, 2, Tools.TOP_LEFT,
	// Tools.TRANS_NONE, 301);
	//
	// }
	// }
	// if ((GameMenuItem.goodsSkill[2] - 8) >= 0) {
	// GameDraw.add_Image(PAK_IMAGES.IMG_MENU28, x
	// + MyGameCanvas.SCREEN_WIDTH / 2 + 152, y - 26,
	// 44 * (GameMenuItem.goodsSkill[2] - 8), 0, 44, 44,
	// Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 200);
	// }
	// if ((GameMenuItem.goodsSkill[3] - 8) >= 0) {
	// GameDraw.add_Image(PAK_IMAGES.IMG_MENU28, x
	// + MyGameCanvas.SCREEN_WIDTH / 2 + 232, y - 26,
	// 44 * (GameMenuItem.goodsSkill[3] - 8), 0, 44, 44,
	// Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 200);
	//
	// }
	// if ((GameMenuItem.goodsSkill[4] - 8) >= 0) {
	// GameDraw.add_Image(PAK_IMAGES.IMG_MENU28, x
	// + MyGameCanvas.SCREEN_WIDTH / 2 + 320, y - 26,
	// 44 * (GameMenuItem.goodsSkill[4] - 8), 0, 44, 44,
	// Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 200);
	//
	// }
	//
	// drawSmallMap(x + 290, y);
	// }
	/*
	 * 画背景
	 */

	int imgIndex;

	public void DrawBackground(int status) {
		GameDraw.add_Image(canvas.pointMenu == 5 ? PAK_IMAGES.IMG_MUSHI7
				: PAK_IMAGES.IMG_MUSHI6, 730, 0, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 120);// 暂停图标

		canvas.gamerank = gameRank / 6;
		switch (canvas.gamerank) {
		case 1:
			// GameDraw.add_Image(PAK_IMAGES.IMG_FISHDIS,464,163,Tools.TOP_LEFT,Tools.TRANS_NONE,
			// 3000);
			break;
		// default:
		// GameDraw.add_Image(PAK_IMAGES.IMG_CHANGJING1,0,0,Tools.TOP_LEFT,Tools.TRANS_NONE,
		// 10);
		// break;
		case 2:
			if(MyGameCanvas.gameStatus==MyGameCanvas.GmStat_Playing){
				GameDraw.add_Image(PAK_IMAGES.IMG_DADITUSUO, 71, 51,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 1500);
			}

			break;
		case 3:
			GameDraw.add_Image(PAK_IMAGES.IMG_JIANTOU1, 82, 0, Tools.TOP_LEFT,
					Tools.TRANS_NONE, 200);
			GameDraw.add_Image(PAK_IMAGES.IMG_JIAGEFUHAO, 358, 27,
					Tools.TOP_LEFT, Tools.TRANS_NONE, 200);
			break;
		}
		// 背景图
		if (gameRank != 99) {
			GameDraw.add_Image(imgIndex, 0, 0, Tools.TOP_LEFT,
					Tools.TRANS_NONE, 10);
//			GameDraw.add_Rect(0, 0, 800, 480, true, Tools.TOP_LEFT, 0x88000000,0);
		} else {
			GameDraw.add_Image(PAK_IMAGES.IMG_DJ10, 0, 0, Tools.TOP_LEFT,
					Tools.TRANS_NONE, 10);
		}

		if (gameRank != 99) {
			GameDraw.add_Image(PAK_IMAGES.IMG_MUSHI8, 10, 6, Tools.TOP_LEFT,
					Tools.TRANS_NONE, 120 + 100);
			GameDraw.add_Image(PAK_IMAGES.IMG_SHANDIAN5, 75, 28, 0, 0,
					(int) (107 * (float) (laoJiaHP / (float) laoJiaHP_MAX)),
					24, Tools.TOP_LEFT, Tools.TRANS_NONE, 120 + 100);
			GameDraw.drawNumber55(PAK_IMAGES.IMG_ZISHIQILIAN, laoJiaHP, 130,
					40, 10, 0, 0, 201 + 100, 14, 0, true);
		}

		if (MyGameCanvas.gameStatus == MyGameCanvas.GmStat_Playing) {
			if(gameRank==99){
				GameDraw.drawNumber(PAK_IMAGES.IMG_DENGJISHUZI, usBs.iUsBsCuJinbi,
						80,38, 15, 0, Tools.TRANS_NONE, 5001, 25, 0, true);
			}else{
				GameDraw.drawNumber(PAK_IMAGES.IMG_JINBISHUZI, usBs.iUsBsCuJinbi,
						600, 455, 11, 0, Tools.TRANS_NONE, 220, 25, 0, true);	
			}

		}

		// 画敌人及波数提示
		GameDraw.add_Image(PAK_IMAGES.IMG_SHANDIAN4, 200 + 330, 25,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 200);

		if (gameRank != 99) {
			GameDraw.add_Image(PAK_IMAGES.IMG_SHANDIAN3, 200 + 330, 25,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 200);
			GameDraw.add_Image(
					PAK_IMAGES.IMG_SHANDIAN2,
					200 + 230 + (int) (209 * ((float) iHit / (float) canvas.checkRole)), // 计算敌人出来的百分比
					25, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 201);
		} else {
			// System.out.println("size : "+jiangLi.size());
			GameDraw.add_Image(PAK_IMAGES.IMG_SHANDIAN3, 200 + 330, 25,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 5001);
			GameDraw.add_Image(
					PAK_IMAGES.IMG_SHANDIAN2,
					200 + 230 + (int) (200 - 209 * ((float) jiangLi.size() / (float) 100)), // 计算敌人出来的百分比
					25, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 5001);
		}
	}

	/**
	 * 画技能
	 * 
	 * @param x
	 * @param y
	 */
	// int
	// skillCold[]={PAK_IMAGES.IMG_JINENG11,PAK_IMAGES.IMG_JINENG22,PAK_IMAGES.IMG_JINENG33};
	int JN1;
	int JN2;
	int JN3;
    int isJB=0;//计费点  是否技能减半
	public void drawSkill() {
		if (time == 1600) {
			time = 0;
			// x=0;
		}
		if (time % (isJB==1?12:25) == 0) {
			if ((JN1 < 62)&&MyGameCanvas.gameStatus==MyGameCanvas.GmStat_Playing) {
				JN1++;
			}
		}
		if (canvas.jiNengKaiQi[1] > 0) {
			if (time % (isJB==1?20:40) == 0) {
				if ((JN2 < 62)&&MyGameCanvas.gameStatus==MyGameCanvas.GmStat_Playing) {
					JN2++;
				}
			}

		}
		if (canvas.jiNengKaiQi[2] > 0) {
			if (time % (isJB==1?25:50) == 0) {
				if ((JN3 < 62)&&MyGameCanvas.gameStatus==MyGameCanvas.GmStat_Playing) {
					JN3++;
				}
			}

		}

		// GameDraw.add_ImageScale(PAK_IMAGES.IMG_BUBING6, cgCurIndex, y, clipX,
		// clipY, clipW, clipH, anchor, trans, drawLevel, scaleX, scaleY)
		// System.out.println("xxxxxxxxxxxxxx   : "+x);
		if (gameRank != 99) {
			GameDraw.add_Image(PAK_IMAGES.IMG_BUBING6, 10, 409, 0, 0, JN1, 20,
					Tools.TOP_LEFT, Tools.TRANS_NONE, 150);
			if (JN1 > 61) {// 第一个以开启技能
				GameDraw.add_ImageScale(PAK_IMAGES.IMG_BUBING5, 10 + 30, 452,
						0, 0, 61, 61, Tools.HCENTER_VCENTER, Tools.TRANS_NONE,
						150, 1f, 1f);
				canvas.checkShan2();
				GameDraw.add_ImageAlpha(PAK_IMAGES.IMG_FAGUANG, 10 + 30, 452,
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 149,
						255 - canvas.shan2);// 发光框子
			} else {

				GameDraw.add_ImageScale(PAK_IMAGES.IMG_BUBING5, 10 + 30, 452,
						0, 0, 61, 61, Tools.HCENTER_VCENTER, Tools.TRANS_NONE,
						150, 1f, 1f);

			}
			GameDraw.add_Image(PAK_IMAGES.IMG_BUBING6, 52 + 55, 409, 0, 0, JN2,
					20, Tools.TOP_LEFT, Tools.TRANS_NONE, 150);
			if (JN2 > 61) {
				GameDraw.add_ImageScale(PAK_IMAGES.IMG_BUBING5, 52 + 80, 452,
						67, canvas.jiNengKaiQi[1] < 0 ? 66 : 0, 61, 61,
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 150, 1f, 1f);// 第二个未开启技能
				canvas.checkShan2();
				GameDraw.add_ImageAlpha(PAK_IMAGES.IMG_FAGUANG, 52 + 80, 452,
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 149,
						255 - canvas.shan2);// 发光框子
			} else {
				GameDraw.add_ImageScale(PAK_IMAGES.IMG_BUBING5, 52 + 80, 452,
						67, canvas.jiNengKaiQi[1] < 0 ? 66 : 0, 61, 61,
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 150, 1f, 1f);// 第二个未开启技能
			}
			GameDraw.add_Image(PAK_IMAGES.IMG_BUBING6, 143 + 55, 409, 0, 0,
					JN3, 20, Tools.TOP_LEFT, Tools.TRANS_NONE, 150);
			if (JN3 > 61) {
				GameDraw.add_ImageScale(PAK_IMAGES.IMG_BUBING5, 143 + 82, 452,
						135, canvas.jiNengKaiQi[2] < 0 ? 66 : 0, 61, 61,
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 150, 1f, 1f);// 第三个未开启技能
				canvas.checkShan2();
				GameDraw.add_ImageAlpha(PAK_IMAGES.IMG_FAGUANG, 143 + 82, 452,
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 149,
						255 - canvas.shan2);// 发光框子
			} else {
				GameDraw.add_ImageScale(PAK_IMAGES.IMG_BUBING5, 143 + 82, 452,
						135, canvas.jiNengKaiQi[2] < 0 ? 66 : 0, 61, 61,
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 150, 1f, 1f);// 第三个未开启技能
			}

		}

		// GameDraw.add_Image(MyGameCanvas.me.iGold>=50?PAK_IMAGES.IMG_JINENG2:PAK_IMAGES.IMG_JINENG22,
		// 760,260,Tools.HCENTER_VCENTER,Tools.TRANS_NONE,150);
		// GameDraw.add_Image(MyGameCanvas.me.iGold>=50?PAK_IMAGES.IMG_JINENG3:PAK_IMAGES.IMG_JINENG33,
		// 760,270,Tools.HCENTER_VCENTER,Tools.TRANS_NONE,150);
		for (int i = 0; i < 3; i++) {
			if (role.iSkillCoolTime[i] > 0) {
				// GameDraw.add_Image(skillCold[i],760+4,200+75*i-role.iSkillCoolTime[i]+3,
				// 0,80-role.iSkillCoolTime[i],70,70,Tools.HCENTER_VCENTER,Tools.TRANS_NONE,150);//画冷却的
			}

			// GameDraw.add_Image(PAK_IMAGES.IMG_14,754,147+75*i,0,0,33,25,
			// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,150);
			// GameDraw.drawNumber(PAK_IMAGES.IMG_SHENGMAITASHUZI,50,
			// 761,140+75*i,15,-3,Tools.HCENTER_VCENTER,150,18,0,true);
		}
		// if(MyGameCanvas.me.iGold>=50){
		// GameDraw.add_Image(PAK_IMAGES.IMG_JINENG11,760,200-role.iSkillCoolTime[0],
		// 0,80-role.iSkillCoolTime[0],70,70,Tools.HCENTER_VCENTER,Tools.TRANS_NONE,150);//画冷却的
		// }
		// if(MyGameCanvas.me.iGold>=50){GameDraw.add_Image(PAK_IMAGES.IMG_JINENG22,760,
		// 275-role.iSkillCoolTime[1],0,80-role.iSkillCoolTime[1],70,70,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,150);//画冷却的
		// }
		// if(MyGameCanvas.me.iGold>=50){GameDraw.add_Image(PAK_IMAGES.IMG_JINENG33,760,
		// 350-role.iSkillCoolTime[2],0,80-role.iSkillCoolTime[2],70,70,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,150);//画冷却的

		// }

		drawKaPian();

	}

	void drawKaPian() {// 画掉落的使用武器
	// System.out.println("MyGameCanvas.shiYongKaPian[0] : "+MyGameCanvas.shiYongKaPian[0]);
		if (canvas.countShiYong != -1) {// 画，掉落书包
			canvas.countShiYong++;
			if (canvas.countShiYong >= 200) {
				canvas.countShiYong = -1;
				canvas.diaoLuoY = 0;
				canvas.diaoLuoX = 0;
				canvas.diaoLuoY = 0;
			}
			if (canvas.countShiYong < 20) {// 第一次掉落特效
				canvas.diaoLuoSpeedY += 1;
				if (canvas.countShiYong < 10) {
					canvas.diaoLuoY -= canvas.diaoLuoSpeedY;
				} else {
					if (canvas.countShiYong == 10) {
						canvas.diaoLuoSpeedY = 0;
					}
					canvas.diaoLuoY += canvas.diaoLuoSpeedY;
				}
			} else {
				// canvas.diaoLuoSpeedY=0;
			}
			if (canvas.countShiYong > 20 && canvas.countShiYong < 30) {// 第二次掉落特效

				if (canvas.countShiYong < 25) {
					canvas.diaoLuoSpeedY -= 2;
					canvas.diaoLuoY -= canvas.diaoLuoSpeedY;
				} else {
					if (canvas.countShiYong == 25) {
						canvas.diaoLuoSpeedY = 0;
					}
					canvas.diaoLuoSpeedY += 2;
					canvas.diaoLuoY += canvas.diaoLuoSpeedY;
				}

			}
			if (canvas.countShiYong > 30 && canvas.countShiYong < 35) {// 第三次掉落特效
				if (canvas.countShiYong < 33) {
					canvas.diaoLuoSpeedY -= 2;
					canvas.diaoLuoY -= canvas.diaoLuoSpeedY;
				} else {
					if (canvas.countShiYong == 33) {
						canvas.diaoLuoSpeedY = 0;
					}
					canvas.diaoLuoSpeedY += 2;
					canvas.diaoLuoY += canvas.diaoLuoSpeedY;
				}
			}

			if (canvas.countShiYong > 45) {// 掉落的卡片移动到老家身上
				int x, y;
//				if (canvas.piBaoX - 400 > 0) {
					x = -(canvas.piBaoX - 400) / 10;
//				} else {
//					x = -(canvas.piBaoX - 400) / 10;
//				}
				y = -(canvas.piBaoY - 500) / 10;
				canvas.diaoLuoX += x;
				canvas.diaoLuoY += y;
			}
//			else {
//				// if(canvas.shiYongKaPian[0]!=0){ //换装备
//				// for (int i = 0; i < canvas.ZB.length; i++) {
//				// if(canvas.ZB[i]==-1){
//				// canvas.contKP=1000;
//				// if(usBs.iUsBsRank<index){
//				// usBs.iUsBsRank++;
//				// }
//				// canvas.ZB[i]=canvas.shiYongKaPian[0];
//				// Speed = canvas.ZBXingXi[canvas.ZB[usBs.iUsBsRank-1]][8];
//				// break;
//				// }
//				// }
//				//
//				// }else{
//				// usBs.iUsBsRank=1;
//				// }
//			}
			if (canvas.countShiYong < 60&&canvas.countShiYong>0) {
				GameDraw.add_ImageScale(
						canvas.bubing43[MyGameCanvas.shiYongKaPian[0]],
						canvas.piBaoX + canvas.diaoLuoX, canvas.piBaoY
								+ canvas.diaoLuoY, Tools.HCENTER_VCENTER,
						Tools.TRANS_NONE, 2000, 0.6f, 0.6f);
				
				// GameDraw.add_Image(canvas.bubing43[MyGameCanvas.shiYongKaPian[0]],canvas.piBaoX+canvas.diaoLuoX,canvas.piBaoY+canvas.diaoLuoY,
				// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,1000);

			} else {
				if(canvas.countShiYong >= 60){
				if (canvas.countShiYong == 61) {
					SSImg[0] = canvas.shiYongKaPian[0];
					// SSImg[1] = canvas.bubing42[canvas.shiYongKaPian[0]];
					switch (canvas.shiYongKaPian[0]) {
					case 0:// 1.西红柿 2.仙人掌 3.玉米4.香蒲5.大蒜6.西瓜7.南瓜
						SSImg[1] = PAK_IMAGES.IMG_PAOTAI1;
						break;
					case 1:
						SSImg[1] = PAK_IMAGES.IMG_PAOTAI12;
						break;
					case 2:
						SSImg[1] = PAK_IMAGES.IMG_PAOTAI13;
						break;
					case 3:
						SSImg[1] = PAK_IMAGES.IMG_PAOTAI2;
						break;
					case 4:
						SSImg[1] = PAK_IMAGES.IMG_PAOTAI23;
						break;
					case 5:
						SSImg[1] = PAK_IMAGES.IMG_PAOTAI22;
						break;
					case 6:
						SSImg[1] = PAK_IMAGES.IMG_PAOTAI3;
						break;
					case 7:
						SSImg[1] = PAK_IMAGES.IMG_PAOTAI32;
						break;
					}
				}
				if (canvas.countShiYong == 100) {
					if (canvas.shiYongKaPian[0] != 0) { // 换装备
	    				if(GameEngine.me.gameRank==0&&GameEngine.me.iResult[0][1]==-1){
//	    					GameEngine.JiaoXueDian = true;
	    					MyGameCanvas.setST(MyGameCanvas.GmStat_QieHuan);
	    				}
	    				if(gameRank!=0||iResult[0][1]!=-1){
							for (int i = 0; i < canvas.ZB.length; i++) {
			 					if (canvas.ZB[i] == -1) {
									canvas.contKP = 1000;
									if (usBs.iUsBsRank < index) {
										usBs.iUsBsRank = i + 1;
										pOp=usBs.iUsBsRank;
									}
									canvas.ZB[i] = canvas.shiYongKaPian[0];
									if (usBs.sheSuJiaBei) {
										if(Speed<(canvas.ZBXingXi[canvas.ZB[usBs.iUsBsRank - 1]][8]) * 2){
											Speed = (canvas.ZBXingXi[canvas.ZB[usBs.iUsBsRank - 1]][8]) * 2;
										}
									} else {
										Speed = canvas.ZBXingXi[canvas.ZB[usBs.iUsBsRank - 1]][8];
									}
									usBs.iUsBsCurAttack = canvas.ZBXingXi[canvas.ZB[usBs.iUsBsRank - 1]][3];
									break;
								}
							}	
	    				}


					} else {
//						if(usBs.iUsBsRank==pOp){
							usBs.iUsBsRank = 1;
//						}
						
					}
				}

			}
		}

		}

	}
    public int pOp;
	public static int[] SSImg = { 0, 1 };

	private void add_ImageScale(int imgBubing6, int i, int j, int topLeft,
			byte transNone, int k, float f, float g) {
		// TODO Auto-generated method stub

	}

	/**
	 * 画下方的塔图标
	 * 
	 * @param x
	 * @param y
	 */
	// int
	// imgI[][]={{PAK_IMAGES.IMG_YOUXIZAOTA11},{PAK_IMAGES.IMG_YOUXIZAOTA16},
	// {PAK_IMAGES.IMG_YOUXIZAOTA21},{PAK_IMAGES.IMG_YOUXIZAOTA26},
	// {PAK_IMAGES.IMG_YOUXIZAOTA31},{PAK_IMAGES.IMG_YOUXIZAOTA36}};//shop塔图样
	public void drawTowIcon() {
		// GameDraw.add_Image(PAK_IMAGES.IMG_CHANZIBEIBAN,760,350,Tools.HCENTER_VCENTER,Tools.TRANS_NONE,120);
		// GameDraw.add_Image(MyGameCanvas.me.iGold>=50?PAK_IMAGES.IMG_CHANZI0:
		// PAK_IMAGES.IMG_CHANZI1,760,350,Tools.HCENTER_VCENTER,Tools.TRANS_NONE,120);
		// GameDraw.add_Image(PAK_IMAGES.IMG_14,754,376,0,0,33,25,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,150);
		// GameDraw.drawNumber(PAK_IMAGES.IMG_SHENGMAITASHUZI,50,
		// 761,370,15,-3,Tools.HCENTER_VCENTER,150,18,0,true);

		if (MyGameCanvas.me.bShovel == true) {// 画铲子被选中效果
		// GameDraw.add_Image(PAK_IMAGES.IMG_HUANGQUANQUAN,760,450-100,
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,120);
		}
		// for(int i=0;i<6;i++){
		// if(role.towerChoose[i]>0){//塔开启且排除刚开始第一个教学时候的不画塔
		// if(i<3){
		// // GameDraw.add_Image(PAK_IMAGES.IMG_JINENGBEIBAN,36+80*i,450,
		// // Tools.HCENTER_VCENTER,Tools.TRANS_NONE,120);
		// // GameDraw.add_Image(imgI[i][0],40+80*i-4,450,
		// // Tools.HCENTER_VCENTER,Tools.TRANS_NONE,120);
		// // GameDraw.drawNumber(PAK_IMAGES.IMG_SHENGMAITASHUZI,
		// // MyGameCanvas.me.buildWaste[i][0],
		// // 28+82*i,470,15,-3,Tools.HCENTER_VCENTER,120,18,0,true);
		// }else{
		// // GameDraw.add_Image(PAK_IMAGES.IMG_JINENGBEIBAN,42+80*(i+4),450,
		// // Tools.HCENTER_VCENTER,Tools.TRANS_NONE,120);
		// // GameDraw.add_Image(imgI[i][0],42+80*(i+4),450,
		// // Tools.HCENTER_VCENTER,Tools.TRANS_NONE,120);
		// // GameDraw.drawNumber(PAK_IMAGES.IMG_SHENGMAITASHUZI,
		// // MyGameCanvas.me.buildWaste[i][0],
		// // 595+80*(i-3),470,15,-3,Tools.HCENTER_VCENTER,120,18,0,true);
		// }
		// // if(MyGameCanvas.me.bHuangquanquan==true){
		// //
		// // }
		// // switch(role.towerChoose[i]){
		// // case GameRole.TA_LIEYU:
		// //
		// GameDraw.add_Image(imgI[0][MyGameCanvas.me.startLevel[0]-1],40+80*i-4,450,
		// // Tools.HCENTER_VCENTER,Tools.TRANS_NONE,120);
		// // GameDraw.drawNumber(PAK_IMAGES.IMG_SHENGMAITASHUZI,
		// // MyGameCanvas.me.buildWaste[0][MyGameCanvas.me.startLevel[0]-1],
		// // 28,470,15,-3,Tools.HCENTER_VCENTER,120,18,0,true);
		// // break;
		// // case GameRole.TA_BINGJING:
		// //
		// GameDraw.add_Image(imgI[1][MyGameCanvas.me.startLevel[1]-1],40+80*i-4,450,
		// // Tools.HCENTER_VCENTER,Tools.TRANS_NONE,120);
		// // GameDraw.drawNumber(PAK_IMAGES.IMG_SHENGMAITASHUZI,
		// // MyGameCanvas.me.buildWaste[1][MyGameCanvas.me.startLevel[1]-1],
		// // 110,470,15,-3,Tools.HCENTER_VCENTER,120,18,0,true);
		// // break;
		// // case GameRole.TA_YULEI:
		// //
		// GameDraw.add_Image(imgI[2][MyGameCanvas.me.startLevel[2]-1],40+80*i-4,450,
		// // Tools.HCENTER_VCENTER,Tools.TRANS_NONE,120);
		// // GameDraw.drawNumber(PAK_IMAGES.IMG_SHENGMAITASHUZI,
		// // MyGameCanvas.me.buildWaste[2][MyGameCanvas.me.startLevel[2]-1],
		// // 184,470,15,-3,Tools.HCENTER_VCENTER,120,18,0,true);
		// // break;
		// // case GameRole.TA_HERO:
		// //
		// GameDraw.add_Image(imgI[3][MyGameCanvas.me.startLevel[3]-1],40+80*(i+4),450,
		// // Tools.HCENTER_VCENTER,Tools.TRANS_NONE,120);
		// // GameDraw.drawNumber(PAK_IMAGES.IMG_SHENGMAITASHUZI,
		// // MyGameCanvas.me.buildWaste[3][MyGameCanvas.me.startLevel[3]-1],
		// // 428+160,470,15,-3,Tools.HCENTER_VCENTER,120,18,0,true);
		// // break;
		// // case GameRole.TA_HUOPAO:
		// //
		// GameDraw.add_Image(imgI[4][MyGameCanvas.me.startLevel[4]-1],40+80*(i+4),450,
		// // Tools.HCENTER_VCENTER,Tools.TRANS_NONE,120);
		// // GameDraw.drawNumber(PAK_IMAGES.IMG_SHENGMAITASHUZI,
		// // MyGameCanvas.me.buildWaste[4][MyGameCanvas.me.startLevel[4]-1],
		// // 510+160,470,15,-3,Tools.HCENTER_VCENTER,120,18,0,true);
		// // break;
		// // case GameRole.TA_XIQIAN:
		// //
		// GameDraw.add_Image(imgI[5][MyGameCanvas.me.startLevel[5]-1],40+80*(i+4),450,
		// // Tools.HCENTER_VCENTER,Tools.TRANS_NONE,120);
		// // GameDraw.drawNumber(PAK_IMAGES.IMG_SHENGMAITASHUZI,
		// // MyGameCanvas.me.buildWaste[5][MyGameCanvas.me.startLevel[5]-1],
		// // 589+160,470,15,-3,Tools.HCENTER_VCENTER,120,18,0,true);
		// // break;
		// // }
		//
		// }else{//如果塔未开启则画个问号
		// if(i<3){
		// // GameDraw.add_Image(PAK_IMAGES.IMG_WENHAO,36+80*i,450,
		// // Tools.HCENTER_VCENTER,Tools.TRANS_NONE,120);
		// }else{
		// // GameDraw.add_Image(PAK_IMAGES.IMG_WENHAO,40+80*(i+4),450,
		// // Tools.HCENTER_VCENTER,Tools.TRANS_NONE,120);
		// }
		// }
		// }
	}

	void drawSmallMap(int x, int y) {
		int y_layer;
		int y_Adj = 0;

		//
		// for (int i = 0; i < enemys.size(); i++) {
		// GameRole enemy = (GameRole) enemys.elementAt(i);
		// y_layer = enemy.y_layerRoad;
		// switch (y_layer) {
		// case 0:
		// y_Adj = -21;
		// break;
		// case 1:
		// y_Adj = -15;
		// break;
		// case 2:
		// y_Adj = -9;
		// break;
		// }
		// }

		GameDraw.add_Rect(x + 193 * role.x / GameMap.mapWidth, y - 15, 5, 5,
				true, Tools.BOTTOM_LEFT, 0xFFFFFC1A, 1000);

		for (int i = 0; i < sprites.size(); i++) {
			GameRole sprite = (GameRole) sprites.elementAt(i);

			// y_layer = sprite.y_layerRoad;
			// switch (y_layer) {
			// case 0:
			// y_Adj = -21;
			// break;
			// case 1:
			// y_Adj = -15;
			// break;
			// case 2:
			// y_Adj = -9;
			// break;
			// }
			GameDraw.add_Rect(x + 193 * sprite.x / GameMap.mapWidth, y + y_Adj,
					5, 5, true, Tools.BOTTOM_LEFT, 0xFF00FFEA, 1000 + 1);

		}
		// 主角:FFFC1A
		// 我方:00FFEA
		// 敌方:BF6DDA
		// 敌人老家:FF4A4A

	}

	/**
	 * 初始化道具标志和金币
	 */
	void initDaoju_Jinbi() {
		// int j =0;//记录超过屏幕的即将显示的食物序号
		// for(int i =0;i<Item.size();i++){
		// if((Item.elementAt(i)[0]>Tools.setOffX +MyGameCanvas.SCREEN_WIDTH)){
		// j = i;
		// }
		// }
		// //地图的一半随即产生道具标志
		// if((role.x>(mapWidth/4))&&(daojuMark_count<daojuMark_max)){
		// Item.elementAt(GameRandom.result(j,Item.size()))[2] =
		// GameInterface.DAOJU_MARK;
		// daojuMark_count++;
		// }
		// switch(GameRandom.result(0,10)){//随机产生金币
		// case 5:
		// int k =GameRandom.result(j,Item.size());
		// if((Item.elementAt(k)[2]!=
		// GameInterface.DAOJU_MARK)&&(jinbi_count<jinbi_max)){
		// Item.elementAt(k)[2] = GameInterface.JINBI;
		// jinbi_count++;
		// }
		// break;
		// }
		// for(int i =0;i<Item.size();i++){//地图最后一部分产生的全是金币
		// if((Item.elementAt(i)[0]>GameMap.mapWidth-
		// (2*MyGameCanvas.SCREEN_WIDTH+400))){
		// Item.elementAt(i)[1] = 360;
		// Item.elementAt(i)[2] =GameInterface.JINBI;
		// }
		// }
		//
	}

	// public void addItem(int x, int y, int id, int color) {
	// int[] temp = new int[10];
	// temp[0] = x;
	// temp[1] = y;
	// temp[2] = id;
	// temp[3] = color;
	// if (id == -3) {
	// temp[4] = y; // endY
	// temp[5] = 1; // bool
	// temp[6] = -8; // speed
	// temp[7] = 0; // index
	// }
	// Item.addElement(temp);
	// }

	public static final byte Item_炸弹 = 101;
	public static final byte Item_幽灵 = 102;
	public static final byte Item_爱心 = 107;
	public static final byte Item_菠萝 = 109;
	public static final byte Item_草莓 = 108;
	public static final byte Item_火腿 = 106;
	public static final byte Item_星星 = 104;
	public static final byte Item_月亮 = 105;

	public void addFoood(int x, int y, int n) {
	}

	void initItem_food(int x, int y, int type, int w, int h) {
		addItem(x, y, type, 0, w, h);
	}

	/**
	 * 画不同的食物
	 */
	int w = 0;
	int jinbi_index = 0;// 用于画旋转的金币
	int speed_index = 0;// 用于画闪烁的加速图标
	int feibiao_index = 0;// 用于播放飞镖帧数的
	int gunshi_index = 0;// 用于播放滚石的

	public void jinbi_move() {

	}

	public static final byte EFFECT_ENEMY_DEAD = 8; // 敌人死亡效果

	public static final byte EFFECT_STR = 13; // 使用物品后的提示效果
	public static final byte EFFECT_FULL = 19; // 已满
	public static final byte EFFECT_ITEM_NAME = 20; // 吃到道具的名字
	public static final byte EFFECT_NUMBER = 21; // 打到敌人费血
	public static final byte EFFECT_NUMBER2 = 27; // 暴击打到敌人费血
	public static final byte EFFECT_LEVEL_UP = 22; // 升级效果

	public static final byte EFFECT_STONE = 24; // 石头破裂
	public static final byte EFFECT_ELE = 26; //

	public static final byte EFFECT_TASKCOMPLETE = 31; // 任务奖励的提示
	public static final byte EFFECT_SHADOW = 32; // 影子

	// public static final byte EFFECT_SHADOW_RUN1 = 33; // 影子
	// public static final byte EFFECT_SHADOW_RUN2 = 34; // 影子
//	public static final byte EFFECT_HIT = 35;
//	public static final byte EFFECT_DOUBLE_HIT = 36;
//	public static final byte EFFECT_WIN = 43;
//	public static final byte EFFECT_ATTACK = 45; // 普通攻击效果
//	public static final byte EFFECT_SKILL1 = 46; // 技能攻击效果
//
//	public static final byte EFFECT_ROLE_ATTACK = 64;
//
//	public static final byte EFFECT_炸弹兵_ATTACK = 65;
//	public static final byte EFFECT_巫师_ATTACK = 66;
//
//	public static final byte EFFECT_关羽召唤烟雾 = 67;
//	public static final byte EFFECT_刘备变木头 = 68;
//
//	public static final byte EFFECT_装备 = 47;

	public static Vector<int[]> EffectV = new Vector<int[]>(); // 爆炸效果集合

	public void drawEffect() { // 绘制游戏中的效果
	}

	public static void AddBlastEffectList(int x, int y, byte type,
			int isMirror, int layer, int color) {
		EffectV.addElement(new int[] { x, y, 0, type, isMirror, layer, color }); // 添加碰撞效果集合
	}

	/**
	 * 根据搜索的地图块属性,决定是否添加违建装饰物
	 * 
	 * @param x
	 * @param y
	 * @param type
	 * @param isMirror
	 * @param layer
	 * @param hp
	 */
	public static void AddUnLawBuild(int x, int y, int type, int isMirror,
			int layer, int hp) {
		// unLawBuild.addElement(new int[] { x, y, 0, type, isMirror, layer, hp
		// });
	}

	//
	public void drawUnLawBuild() {
		// int size = unLawBuild.size()-1;
		// if(size<0){return;}
		// for(int i=size;i>=0;i--){
		// int[]temp = (int[])unLawBuild.elementAt(i);
		// switch(temp[3]){
		// case UnLawBuildBig:
		// GameDraw.add_Image(PAK_IMAGES.IMG_SHUIJINGDA,temp[0], temp[1],
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,49);
		// if(temp[6]<=0){
		// unLawBuild.removeElementAt(i);
		// }
		// break;
		// case UnLawBuildSmall:
		// GameDraw.add_Image(PAK_IMAGES.IMG_SHUIJINGXIAO,temp[0], temp[1],
		// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,49);
		// if(temp[6]<=0){
		// unLawBuild.removeElementAt(i);
		// }
		// break;
		// case PROPKUANG:
		// if(MyGameCanvas.me.bPropShow==true){
		// GameDraw.add_Image(PAK_IMAGES.IMG_TA,temp[0],
		// temp[1],Tools.HCENTER_VCENTER,Tools.TRANS_NONE,49);
		// }
		// break;
		// }
		// }
	}

	/**
	 * 
	 * 画转盘抽奖界面
	 */
	int flashLightX = 300;
	int flashLightY = 140;// 闪光灯的初始坐标
	int iAnJian;// 按键序数

	public void drawGamble() {
		GameDraw.add_Rect(0, 0, 800, 480, true, Tools.TOP_LEFT, 0x88000000, 5000);
		 GameDraw.add_Image(PAK_IMAGES.IMG_CHOUJIANGZHUANPAN,403,242,
		 Tools.HCENTER_VCENTER,Tools.TRANS_NONE,5000);
		 GameDraw.add_Image(PAK_IMAGES.IMG_CHOUJIANGANJIAN1,402,244,
		 Tools.HCENTER_VCENTER,Tools.TRANS_NONE,5000);
		switch (MyGameCanvas.me.pointMenu) {
		case 0:
			if (bEnd == false) {
				 GameDraw.add_Image(PAK_IMAGES.IMG_CHOUJIANGANJIAN1,402,244,
				 Tools.HCENTER_VCENTER,Tools.TRANS_NONE,5000);
			}
			break;
		}
		switch (iAnJian) {
		case 0:
			 GameDraw.add_Image(PAK_IMAGES.IMG_CHOUJIANGKAISHI,402,227+18,
			 Tools.HCENTER_VCENTER,Tools.TRANS_NONE,5000);
			break;
		case 1:
			 GameDraw.add_Image(PAK_IMAGES.IMG_CHOUJIANGTINGZHI,402,227+18,
			 Tools.HCENTER_VCENTER,Tools.TRANS_NONE,5000);
			break;
		default:
			 GameDraw.add_Image(PAK_IMAGES.IMG_CHOUJIANGTINGZHI,402,227+18,
			 Tools.HCENTER_VCENTER,Tools.TRANS_NONE,5000);
		}
		if (iAnJian >= 1) {// 只有开始抽奖后才出现闪光灯

			 GameDraw.add_Image(PAK_IMAGES.IMG_CHOUJIANGSHANGUANGDENG,flashLightX,
			 flashLightY,Tools.HCENTER_VCENTER,Tools.TRANS_NONE,4999);
		}

	}

	/**
	 * 
	 * 抽奖逻辑处理
	 */
	int endIndex;
	int moveIndex;// 闪光灯移动中对应的转盘图标
	boolean bStart;
	boolean bEnd;// 抽奖开始与结束
	int iRandom;

	public void gamebleLogic(){
		if (bStart == true) {
			moveIndex++;
			if (moveIndex <= 3) {
				flashLightX += 68;
			} else if (moveIndex <= 6) {
				flashLightY += 68;
			} else if (moveIndex <= 9) {
				flashLightX -= 68;
			} else if (moveIndex <= 12) {
				flashLightY -= 68;
			} else {
				moveIndex = 0;
			}
		} else if (bEnd == true) {
			endIndex++;
			if (iRandom <= 0) {
				iRandom = GameRandom.result(2, 6);
			}
			if (endIndex <= 10) {
				if (endIndex % 5 == 0) {
					getGambleIndex();
				}
			} else if (endIndex <= 20 + 5 * iRandom) {
				if (endIndex % 10 == 0) {
					getGambleIndex();
				}
			} else if (endIndex <= 45 + 5 * iRandom) {
				if (endIndex % 15 == 0) {
					getGambleIndex();
				}
			} else if (endIndex <= 60 + 5 * iRandom) {
				if (endIndex % 20 == 0) {
					getGambleIndex();
				}
			} else {// 转盘结束,弹出奖励
				bEnd = false;
				iRandom = 0;
				getWhichPride();
				MyGameCanvas.AddBlastEffectList(400, 240,
						MyGameCanvas.me.EFF_GONGXIHUODE, 0, 120, 0);
			}
		}
	}

	/**
	 * 转盘结束后将数据重置
	 * 
	 */
	void resetData() {
		endIndex = 0;
		moveIndex = 0;
		iAnJian = 0;
		flashLightX = 300;
		flashLightY = 140;
	}

	/**
	 * 
	 * 获取点击抽奖结束按键瞬间,闪光灯所处位置
	 */
	void getGambleIndex() {
		int x = (flashLightX - 300);
		int y = (flashLightY - 140);
		if (x == 0) {
			if (y > 0) {
				flashLightY -= 68;
			} else {
				flashLightX += 68;
			}
		} else if (x > 0 && x < 68 * 3) {
			if (y == 0) {
				flashLightX += 68;
			} else {
				flashLightX -= 68;
			}
		} else if (x == 68 * 3) {
			if (y < 68 * 3) {
				flashLightY += 68;
			} else {
				flashLightX -= 68;
			}
		}
	}

	/**
	 * 
	 * 获取最终抽奖物品
	 */
	int iGetPride;

	void getWhichPride() { //从左边开始，竖排计算 ，一共四列
		int x = (flashLightX - 300);
		int y = (flashLightY - 140);
		switch (x / 68) {
		case 0: 
			switch (y / 68) { 
			case 0: //南瓜
				iGetPride = 6;
//				usBs.iUsBsCuJinbi += 50;
				MyGameCanvas.shiYongKaPian[0] = 7;
				MyGameCanvas.countShiYong=0;
				break;
			case 1:
				iGetPride = 3;
				usBs.iUsBsCuJinbi += 200;
				break;
			case 2:
				iGetPride = 5;
				usBs.iUsBsCuJinbi += 1000;
				break;
			case 3:
				iGetPride = 1;
				usBs.iUsBsCuJinbi += 50;
				break;
			}
			break;
		case 1:
			if (y == 0) {
				iGetPride = 2;
				usBs.iUsBsCuJinbi += 100;
			} else {
				iGetPride = 4;
				usBs.iUsBsCuJinbi += 500;
			}
			break;
		case 2:
			if (y == 0) {
				iGetPride = 1;
				usBs.iUsBsCuJinbi += 50;
			} else {
				iGetPride = 3;
				usBs.iUsBsCuJinbi += 200;
			}
			break;
		case 3:
			switch (y / 68) {
			case 0: //fuhuo 
				iGetPride = 7;
				fuhuo++;
//				MyGameCanvas.me.iGold += 10;
				break;
			case 1:
				iGetPride = 2;
				usBs.iUsBsCuJinbi += 100;
				break;
			case 2:
				iGetPride = 1;
				usBs.iUsBsCuJinbi += 50;
				break;
			case 3:
				iGetPride = 2;
				usBs.iUsBsCuJinbi += 100;
				break;
			}
			break;
		}
		MyGameCanvas.me.saveGame();
	}

	/**************************** 游戏中一系列逻辑处理 ******************************************************/

	/**
	 * 判断攻击
	 */
	// int hpChoose;
	int jianshiDir;

	private void checkPlayerAttack() {
		if (sprites == null || enemys == null) {
			return;
		}
		for (int i = 0; i < enemys.size(); i++) {
			List<GameRole> tar = new Vector<GameRole>();
			GameRole enemy = (GameRole) enemys.elementAt(i);
			if (enemy.iEnemyHp <= 0) {
				continue;
			}
			for (int j = 0; j < sprites.size(); j++) {
				GameRole sprite = (GameRole) sprites.elementAt(j);
				if (sprite.roleStatus != GameInterface.STATUS_STOP) {
					continue;
				}
				if (BulletManager.bInCircle(enemy.x, enemy.y, sprite.circle_X,
						sprite.circle_Y, sprite.circle_R)) {// 怪物进入塔的攻击范围内,
															// //如果范围内存在多个敌人,箭矢锁定第一个怪物
					tar.add(enemys.get(i));// 将箭塔攻击范围内的敌人添加进临时列表
					if (sprite.iCoolTime > 0) {// 如果塔处于冷却时间，则无法释放技能
						sprite.iCoolTime--;
					} else {
						if (tar.size() != 0) {// 找出临时列表中最前面的怪物
							GameRole gr = getMaxNumtarget(tar);
							jianshiDir = setDir(sprite.x, sprite.y, gr.x, gr.y);
							sprite.setStatus(GameInterface.STATUS_ATTACK);
							if (sprite.type != GameRole.TA_XIQIAN) {
								sprite.dir = jianshiDir;
							}
							switch (sprite.type) {
							case GameRole.TA_LIEYU:
								MyActivity.instance._mView.waf.StartWav(4,
										false);
								gr.addBullet(sprite.x, sprite.y,
										GameRole.Bullet_lieyu, jianshiDir,
										Speed, gr.x, gr.y, sprite.attack
												+ MyGameCanvas.me.tempAttack,
										0, i, enemy.type);// 传入的i没必要
								sprite.setCoolTime(GameRole.TA_LIEYU,
										sprite.level);// 设置塔的攻击冷却时间
								break;
							case GameRole.TA_BINGJING:
								MyActivity.instance._mView.waf.StartWav(2,
										false);
								gr.addBullet(sprite.x, sprite.y,
										GameRole.Bullet_bingjing, jianshiDir,
										Speed, gr.x, gr.y, sprite.attack
												+ MyGameCanvas.me.tempAttack,
										0, i, enemy.type);
								sprite.setCoolTime(GameRole.TA_BINGJING,
										sprite.level);
								break;
							case GameRole.TA_YULEI:
								MyActivity.instance._mView.waf.StartWav(7,
										false);
								gr.addBullet(sprite.x, sprite.y,
										GameRole.Bullet_yulei, jianshiDir,
										Speed, gr.x, gr.y, sprite.attack
												+ MyGameCanvas.me.tempAttack,
										0, i, enemy.type);
								sprite.setCoolTime(GameRole.TA_YULEI,
										sprite.level);
								break;
							// case GameRole.TA_DIANWANG:
							// fishGame.instance._mView.waf.StartWav(3,false);
							// gr.addBullet(sprite.x,sprite.y,GameRole.Bullet_dianwang,jianshiDir,Speed,
							// gr.x,gr.y,sprite.attack,0,i,j);
							// sprite.setCoolTime(GameRole.TA_DIANWANG,sprite.level);
							// break;
							case GameRole.TA_HERO:
								MyActivity.instance._mView.waf.StartWav(3,
										false);
								gr.addBullet(sprite.x, sprite.y,
										GameRole.Bullet_hero, jianshiDir,
										Speed, gr.x, gr.y, sprite.attack
												+ MyGameCanvas.me.tempAttack,
										0, i, enemy.type);
								sprite.setCoolTime(GameRole.TA_HERO,
										sprite.level);
								break;
							case GameRole.TA_HUOPAO:
								MyActivity.instance._mView.waf.StartWav(3,
										false);
								gr.addBullet(sprite.x, sprite.y,
										GameRole.Bullet_huopao, jianshiDir,
										Speed, gr.x, gr.y, sprite.attack
												+ MyGameCanvas.me.tempAttack,
										0, i, enemy.type);
								sprite.setCoolTime(GameRole.TA_HUOPAO,
										sprite.level);
								break;
							// case GameRole.TA_ZENGFU:
							// gr.addBullet(sprite.x,sprite.y,GameRole.Bullet_zengfu,jianshiDir,Speed,
							// gr.x,gr.y,sprite.attack,0,i,j);
							// sprite.setCoolTime(GameRole.TA_ZENGFU,sprite.level);
							// break;
							case GameRole.TA_XIQIAN:// 范围内100%出宝石
								iFreakMoney = 1;
								break;
							}
						}
						tar.clear();// 清空临时列表

					}// cooltime<=0的时候
				} else {// 不再范围内
					continue;
				}
			}

		}
	}

	public GameRole getMaxNumtarget(List<GameRole> target) {
		GameRole result = target.get(0);
		for (int i = 1; i < target.size(); i++) {
			if (result.num < target.get(i).num) {
				result = target.get(i);
			}
		}
		return result;
	}

	/**
	 * 重写添加子弹类
	 * 
	 * @param x
	 * @param y箭矢实时坐标
	 * @param type
	 * @param dir
	 * @param speed箭矢速度
	 * @param targetX
	 * @param targetY目标位置
	 * @param attack释放的箭矢的攻击力
	 * @param index
	 * @param i
	 * @param j
	 */
	// public void addBullet(int x, int y, int type, int dir, int speed,
	// int targetX, int targetY, int attack, int index,int i,int j) {
	// int[] temp = new int[13];
	// temp[0] = x;
	// temp[1] = y;
	// temp[2] = type;
	// temp[3] = dir;
	// temp[4] = speed;
	// temp[5] = targetX;
	// temp[6] = targetY;
	// temp[7] = attack;
	// temp[8] = index;
	// temp[9] = 0;
	// temp[10] = 0;
	// temp[11] = i;
	// temp[12] = j;
	// shot.addElement(temp);
	// }

	/**
	 * 画箭矢打击在怪物身上的效果
	 * 
	 * @param type
	 */
	public void drawAttack(byte type) {
		switch (type) {
		case GameRole.Bullet_lieyu:

			break;
		}

	}

	/**
	 * 数学三角函数
	 */
	public static float sin(float jd) {
		return (float) Math.sin(jd);
	}

	public static float cos(float jd) {
		return (float) Math.cos(jd);
	}

	public static float asin(float dirty) {
		return (float) Math.asin(dirty);
	}

	public static float acos(float dirty) {
		return (float) Math.acos(dirty);
	}

	/**
	 * 根据塔与怪物的坐标，算出子弹的方向 子弹的初始图，方向均是想上的
	 */
	int getDir(int x, int y) {
		int DIR = 0;
		int l = (int) Math.sqrt((x) * (x) + (y) * (y));
		float a = (float) y / (float) l;
		if (a > 0) {// 怪物在塔的下方
			if (x < 0) {// 怪物在塔的左侧
				DIR = (int) (180 * acos(a) / PI) + 180;
			} else {
				DIR = 180 - (int) (180 * acos(a) / PI);
			}
		} else if (a < 0) {// 怪物在塔的上方
			if (x < 0) {
				DIR = (int) (180 * asin(Math.abs(y) / l) / PI + 270);
			} else {
				DIR = (int) (90 - (180 * asin(Math.abs(y / l)) / PI));
			}
		} else if (a == 0) {
			if (x < 0) {
				DIR = 270;
			} else {
				DIR = 90;
			}
		}
		return DIR;
	}

	/**
	 * 根据箭塔与怪物的坐标，算出箭矢旋转角度
	 * 
	 * @param x1
	 * @param y1箭塔的坐标
	 * @param x2
	 *            (基本上是固定的位置)
	 * @param y2怪物的坐标
	 * @param jd
	 *            (基本上为运动,即 即时变化的坐标) 注:全部转换到800X480的坐标系中去运算
	 */
	public int setDir(int x1, int y1, int x2, int y2) {
		int sw = MyActivity.VMWidth;
		int sh = MyActivity.VMHeight;
		float dw = (float) sw / (MyGameCanvas.SCREEN_WIDTH);
		float dh = (float) sh / (MyGameCanvas.SCREEN_HEIGHT);

		// x2=(int) (x2*MyGameCanvas.SCREEN_WIDTH/sw);
		// y2=(int) (y2*MyGameCanvas.SCREEN_HEIGHT/sh);//转换为自适屏的
		int len = (int) Math
				.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));// 两点间的距离
		int jd = 0;// 箭矢旋转角度
		float a = (float) (x2 - x1) / (float) len;
		if (a == 0) {// 怪物位于箭塔的正上下方时存在
			if (y2 - y1 >= 0) {// 下方
				jd = 180;
			} else {
				jd = 0;
			}
		} else {
			if (a < 0) {// 怪物位于箭塔的左侧上下区域
				if (y2 - y1 == 0) {// 怪物与箭塔同处一条直线
					jd = 270;
				} else {
					if (y2 - y1 > 0) {// 左下
						jd = 180 + (int) (180 * asin(Math.abs(a)) / PI);
					} else {// 左上
						jd = 360 - (int) (180 * asin(Math.abs(a)) / PI);
					}
				}
			} else {// 怪物位于箭塔的右侧上下区域
				if (y2 - y1 == 0) {// 怪物与箭塔同处一条直线
					jd = 90;
				} else {
					if (y2 - y1 > 0) {// 右下
						jd = 180 - (int) (180 * asin(Math.abs(a)) / PI);
					} else {// 右上
						jd = (int) (180 * asin(Math.abs(a)) / PI);
					}
				}
			}
		}

		return jd;
	}

	/**
	 * 根据坐标，获取方向
	 */
	int reflushDir(int x2, int y2) { // x1,y1,雇佣兵，x2,y2为敌兵,返回的是雇佣兵的方向 x2为x2-x1
		if (y2 == 0) { // 临界点
			if (x2 < 0) {
				return GameInterface.DIR_LEFT;
			} else {
				return GameInterface.DIR_RIGHT;
			}
		}

		if (x2 == 0) { // 临界点
			if (y2 < 0) {
				return GameInterface.DIR_UP;
			} else {
				return GameInterface.DIR_DOWN;
			}
		}

		if (x2 > 0) {
			if (Math.abs(y2 / x2) < 1) {
				return GameInterface.DIR_RIGHT;
			}
			if (y2 > 0) {
				return GameInterface.DIR_DOWN;
			} else {
				return GameInterface.DIR_UP;
			}
		} else {
			if (Math.abs(y2 / x2) < 1) {
				return GameInterface.DIR_LEFT;
			}
			if (y2 > 0) {
				return GameInterface.DIR_DOWN;
			} else {
				return GameInterface.DIR_UP;
			}
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param id
	 * @param color
	 * @param w
	 * @param h
	 * @param foodGroup
	 *            哪组食物
	 * @param star_end
	 *            食物的开始或结束的标志(1为开始，-1为结束)
	 */
	public void addItemFood(int x, int y, int id, int color, int w, int h,
			int foodGroup, int star_end, int i, int j) {
		int[] temp = new int[13];
		temp[0] = x;
		temp[1] = y;
		temp[2] = id;
		temp[3] = color;
		temp[4] = w;
		temp[5] = h;
		temp[6] = foodGroup;
		temp[7] = star_end;
		temp[8] = i;
		temp[9] = j;
		temp[10] = 0;
		temp[11] = 0;
		temp[12] = 0;
		Item.addElement(temp);
	}

	public void addItem(int x, int y, int id, int color, int w, int h) {
		int[] temp = new int[13];
		temp[0] = x;
		temp[1] = y;
		temp[2] = id;
		temp[4] = w;
		temp[5] = h;
		temp[3] = color;
		temp[6] = 0;
		temp[7] = 0;
		temp[8] = 0;
		temp[9] = 0;
		temp[10] = 0;
		temp[11] = 0;
		temp[12] = 0;
		Item.addElement(temp);
	}

	/**
	 * 得到子弹的运行轨迹
	 * 
	 * @param sx
	 * @param sy
	 * @param ex
	 * @param ey
	 * @param lot
	 * @return
	 */

	/**
	 * 检查是否遇到
	 * 
	 * @return
	 * 
	 */
	public void checkItem() {
		int lev = 100;
		for (int i = 0; i < Item.size(); i++) {
			int[] temp = (int[]) Item.elementAt(i);
			if (GameHit.hit2(role, temp[0], temp[1], temp[4], temp[5])) {
				switch (temp[2]) {
				}
			}
		}
	}

	/**
	 * 画剧情
	 * 
	 */
	public void draw_Scripe() {


	}

	/************************************* 子弹相关 **************************************************/


	int checkEle() { // 判断闪电落点
	
		return -1;
	}

	int checkBoom() { // 判断赵云导弹
		if (sprites.size() < 1) {
			if (Tools.isDraw(role.x, role.y, 40, 40, Tools.BOTTOM_LEFT)) {
				return -2;
			}
			return -1;
		}

		for (int i = 0; i < sprites.size(); i++) {
			GameRole sprite = (GameRole) sprites.elementAt(i);
			if (Tools.isDraw(sprite.x, sprite.y, 10, 10, Tools.BOTTOM_LEFT)) {
				return i;
			}
		}
		if (Tools.isDraw(role.x, role.y, 40, 40, Tools.BOTTOM_LEFT)) {
			return -2;
		}
		return -1;
	}

	public void checkRoleShot() {
		// if (enemys == null) {
		// return;
		// }
		// for (int i = 0; i < enemys.size(); i++) {
		// GameRole enemy = (GameRole) enemys.elementAt(i);
		// if (enemy.hp <= 0
		// || enemy.curStatus == GameInterface.STATUS_DISAPPEAR
		// || enemy.curStatus == GameInterface.STATUS_INJURE
		// || enemy.curStatus == GameInterface.STATUS_NULL
		// || enemy.curStatus == GameInterface.STATUS_DEAD) {
		// continue;
		// }
		// // 普通攻击
		// for (int j = 0; j < roleShot.size(); j++) {
		// int[] temp = (int[]) roleShot.elementAt(j);
		// boolean isLeft = (temp[3] == -1);
		// switch (temp[2]) {
		// case FIRE_SKILL4:
		// if (hit_box_shot(temp[0], temp[1],
		// MyGameCanvas.data_skill4, temp[10], isLeft, enemy)) {
		// enemy.InjureEnemy(role.attack, GameRole.HURT_冰冻, false);
		//
		// }
		// break;
		//
		// case FIRE_SKILL1:
		// if (hit_box_shot(temp[0], temp[1],
		// MyGameCanvas.data_skill1, temp[10], isLeft, enemy)) {
		// // System.out.println("role.attack:"+role.attack);
		// enemy.InjureEnemy(role.attack, GameRole.HURT_技能, false);
		//
		// }
		// break;
		//
		// case FIRE_SKILL2:
		// if (hit_box_shot(temp[0], temp[1],
		// MyGameCanvas.data_skill2, temp[10], isLeft, enemy)) {
		// enemy.InjureEnemy(role.attack, GameRole.HURT_电击, false);
		// }
		//
		// break;
		//
		// case FIRE_SKILL3:
		// if (hit_box_shot(temp[0], temp[1],
		// MyGameCanvas.data_skill3, temp[10], isLeft, enemy)) {
		// enemy.InjureEnemy(role.attack, GameRole.HURT_技能, false);
		// }
		// break;
		//
		// case FIRE_ROLE_术士:
		// case FIRE_ROLE_术士_SKILL:
		// if (hit_box_shot(temp[0], temp[1], MyGameCanvas.data_fire2,
		// 0, isLeft, enemy)) {
		// enemy.InjureEnemy(
		// MyGameCanvas.menuItem
		// .getPropAttack(
		// GameRole.TYPE_ROLE_术士,
		// GameMenuItem.propLeavelData[GameRole.TYPE_ROLE_术士]),
		// temp[2] == FIRE_ROLE_术士 ? GameRole.HURT_普通
		// : GameRole.HURT_冰冻, false);
		// roleShot.removeElementAt(j);
		// }
		// break;
		//
		// case FIRE_ROLE_弓箭手:
		// if (hit_box_shot(temp[0], temp[1],
		// MyGameCanvas.data_role_弓箭兵, 31, isLeft, enemy)) {
		// enemy.InjureEnemy(
		// MyGameCanvas.menuItem
		// .getPropAttack(
		// GameRole.TYPE_ROLE_弓箭手,
		// GameMenuItem.propLeavelData[GameRole.TYPE_ROLE_弓箭手]),
		// GameRole.HURT_普通, false);
		// roleShot.removeElementAt(j);
		// }
		// break;
		//
		// case FIRE_ROLE_弓箭手_SKILL:
		// if (hit_box_shot(temp[0], temp[1],
		// MyGameCanvas.data_role_弓箭兵, 32, isLeft, enemy)) {
		// enemy.InjureEnemy(
		// MyGameCanvas.menuItem
		// .getPropAttack(
		// GameRole.TYPE_ROLE_弓箭手,
		// GameMenuItem.propLeavelData[GameRole.TYPE_ROLE_弓箭手]),
		// GameRole.HURT_炸弹兵, true);
		// roleShot.removeElementAt(j);
		// }
		// break;
		//
		// default:
		//
		// break;
		// }
		// }
		// }
	}

	public void checkEnemyShot_Role() { // 敌人子弹与主角
	// for (int j = 0; j < enemyShot.size(); j++) {
	// int[] temp = (int[]) enemyShot.elementAt(j);
	// boolean isLeft = (temp[3] == -1);
	// switch (temp[2]) {
	// case FIRE_ENEMY_赵云_BOOM:
	// if (hit_box_shot(temp[0], temp[1], MyGameCanvas.data_fire3,
	// temp[10], isLeft, role)) {
	//
	// role.InjureRole(10, false);
	// }
	// break;
	//
	// case FIRE_ENEMY_巫师:
	// if (hit_box_shot(temp[0], temp[1], MyGameCanvas.data_enemy_巫师,
	// 14, isLeft, role)) {
	// role.InjureRole(10, false);
	// enemyShot.removeElementAt(j);
	// continue;
	// }
	// break;
	//
	// case FIRE_ENEMY_弓箭手:
	// case FIRE_ENEMY_弓箭手_SKILL:
	// if (hit_box_shot(temp[0], temp[1], MyGameCanvas.data_enemy_弓箭手,
	// 21, isLeft, role)) {
	// role.InjureRole(10, false);
	// enemyShot.removeElementAt(j);
	// }
	// break;
	// }
	// }

	}

	public void checkEnemyShot() { // 敌人子弹与sprites
	// if (sprites == null) {
	// return;
	// }
	//
	// for (int i = 0; i < sprites.size(); i++) {
	// GameRole sprite = (GameRole) sprites.elementAt(i);
	// if (sprite.hp <= 0
	// || sprite.curStatus == GameInterface.STATUS_DISAPPEAR
	// || sprite.curStatus == GameInterface.STATUS_INJURE
	// || sprite.curStatus == GameInterface.STATUS_NULL
	// || sprite.curStatus == GameInterface.STATUS_DEAD
	// || sprite.woodTime > 0 || sprite.eleTime > 0) {
	//
	// continue;
	// }
	// // 普通攻击
	// for (int j = 0; j < enemyShot.size(); j++) {
	// int[] temp = (int[]) enemyShot.elementAt(j);
	// boolean isLeft = (temp[3] == -1);
	// switch (temp[2]) {
	// case FIRE_ENEMY_张飞:
	// if (hit_box_shot(temp[0], temp[1], MyGameCanvas.data_fire4,
	// temp[10], isLeft, sprite)) {
	// // sprite.InjureEnemy(10, GameRole.HURT_被炸, false);
	// } else if (hit_box_shot(temp[0], temp[1],
	// MyGameCanvas.data_fire4, temp[10], isLeft, role)) {
	// role.InjureRole(10, false);
	// enemyShot.removeElementAt(j);
	// }
	//
	// break;
	//
	//
	//
	// case FIRE_ENEMY_孙坚:
	// if (hit_box_shot(temp[0], temp[1], MyGameCanvas.data_fire3,
	// temp[10], isLeft, sprite)) {
	// sprite.InjureEnemy(10, GameRole.HURT_被炸, false);
	// enemyShot.removeElementAt(j);
	// }
	//
	// else if (hit_box_shot(temp[0], temp[1],
	// MyGameCanvas.data_fire3, temp[10], isLeft, role)) {
	// role.InjureRole(10, false);
	// enemyShot.removeElementAt(j);
	// }
	//
	// break;
	//
	// case FIRE_ENEMY_赵云_BOOM:
	// if (hit_box_shot(temp[0], temp[1], MyGameCanvas.data_fire3,
	// temp[10], isLeft, sprite)) {
	// sprite.InjureEnemy(10, GameRole.HURT_技能, true);
	// } else if (hit_box_shot(temp[0], temp[1],
	// MyGameCanvas.data_fire3, temp[10], isLeft, role)) {
	// role.InjureRole(10, false);
	// enemyShot.removeElementAt(j);
	// }
	//
	// break;
	//
	// case FIRE_ENEMY_巫师:
	// if (hit_box_shot(temp[0], temp[1],
	// MyGameCanvas.data_enemy_巫师, 14, isLeft, sprite)) {
	// sprite.InjureEnemy(10, GameRole.HURT_巫师, false);
	// enemyShot.removeElementAt(j);
	// continue;
	// } else if (hit_box_shot(temp[0], temp[1],
	// MyGameCanvas.data_enemy_巫师, 14, isLeft, role)) {
	// role.InjureRole(10, false);
	// enemyShot.removeElementAt(j);
	// }
	//
	// break;
	//
	// case FIRE_ENEMY_弓箭手:
	// case FIRE_ENEMY_弓箭手_SKILL:
	// if (hit_box_shot(temp[0], temp[1],
	// MyGameCanvas.data_enemy_弓箭手, 21, isLeft, sprite)) {
	// sprite.InjureEnemy(10,
	// temp[2] == FIRE_ENEMY_弓箭手 ? GameRole.HURT_巫师
	// : GameRole.HURT_冰冻, false);
	// enemyShot.removeElementAt(j);
	// continue;
	// } else if (hit_box_shot(temp[0], temp[1],
	// MyGameCanvas.data_enemy_弓箭手, 21, isLeft, role)) {
	// role.InjureRole(10, false);
	// enemyShot.removeElementAt(j);
	// }
	//
	// break;
	// }
	// }
	// }
	}

	/*************************** 剧情相关 ******************************************/
	int scriptcurIndex; // 哪段剧情
	int scriptIndex; // 剧情哪一帧

	byte[] scripeData = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0 }; // 剧情是否开放
	boolean isSctrpe; // 自动

	public void checkScripe() { // 是否触发剧情
		if (isSctrpe) {
			return;
		}
		if (MyGameCanvas.gameStatus != MyGameCanvas.GmStat_Playing) {
			return;
		}

		for (int i = 0; i < scripeData.length; i++) {
			if (scripeData[i] == 0) {
				scriptcurIndex = i;
				break;
			}
		}
		switch (scriptcurIndex) {
		case 0: // 第一关

			break;

		case 1: //

			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;

		}

		if (MyGameCanvas.gameStatus == MyGameCanvas.GmStat_Playing) {
			scriptIndex++;
		}

	}

	public void toScript() {
		isSctrpe = true;
		scriptIndex = 0;
	}

	public void endScript() {
		isSctrpe = false;
		scriptIndex = 0;
		scripeData[scriptcurIndex] = 1;
	}

	boolean isScreen; // 镜头跟谁动
	boolean isVanish;
	boolean isEnemy;

	public void draw() { // 画自动脚本
		if (!isSctrpe) {
			return;
		}
		switch (scriptcurIndex) {
		case 0: // 第一关

			break;

		}
		if (MyGameCanvas.gameStatus == MyGameCanvas.GmStat_Playing) {
			scriptIndex++;
		}
	}

	public void drawBox(int x, int y, int layer) {

	}

	/*********************************** 任务task **********************************/
//	public static final byte TALK_NULL = 0; // 旁白
//	public static final byte TALK_ROLE = 1; // 主角孙
//	public static final byte TALK_村长 = 2; // 唐僧
//	public static final byte TALK_村民男 = 3; // 猪八戒
//	public static final byte TALK_村民女 = 4; // 沙僧
//	public static final byte TALK_小女孩 = 5; // 白骨精变的小姑娘
//	public static final byte TALK_老妇人 = 6;
//	public static final byte TALK_恶鬼 = 7;
//	public static final byte TALK_蚩尤 = 8;
//	public static final byte TALK_二郎神 = 9;

	public static final byte maxTalk = 35;

	public void removeEnemyImage() {

		// enemys = null;
	}

	public boolean isEnemyHp() {
		// for (int i = 0; i < enemys.size(); i++) {
		// GameRole enemy = (GameRole) enemys.elementAt(i);
		// if (enemy.hp > 0) {
		// return false;
		// }
		// }
		return true;
	}

	public static byte STARNUM = 40;
	public static int[][] starPos; // x,y,startx,starty,
	public static int[][] starSpeed;
	public static byte speedOfScroll = 20; // 控制速度的，那个值越大，速度感越强
	int height = MyGameCanvas.SCREEN_HEIGHT;
	int width = MyGameCanvas.SCREEN_WIDTH;
	int starTime = 0;
	byte len[] = new byte[STARNUM]; // 长度

	public void initStar() {
		starPos = new int[STARNUM][2];
		starSpeed = new int[STARNUM][2];
		len = new byte[STARNUM];

		int w = MyGameCanvas.SCREEN_WIDTH;
		if (role.isLeft) {
			speedOfScroll = 25;
		} else {
			speedOfScroll = -25;
			w = -MyGameCanvas.SCREEN_WIDTH;
		}

		for (int i = 0; i < STARNUM; i++) {
			if (i % 2 == 0) {
				starPos[i][0] = (short) (Tools.setOffX + GameRandom
						.result(1, w));
				starPos[i][1] = -(short) (Tools.setOffY + GameRandom.result(1,
						20));
			} else {
				starPos[i][0] = (short) (Tools.setOffX + +GameRandom.result(20,
						w));
				starPos[i][1] = (short) (Tools.setOffY + GameRandom.result(1,
						height));
			}
			starSpeed[i][0] = (byte) (2 + speedOfScroll);
			starSpeed[i][1] = (byte) (2 + Math.abs(speedOfScroll));

			len[i] = (byte) (GameRandom.result(3));
		}
	}

	int cgIndex;
	int cgCurIndex;
	boolean isCg;

	public void initCG() {
		Tools.setOffX = 0;
		role.x = -10;
		role.y = 263;

		scriptcurIndex = 0;
		cgIndex = 0;
		cgCurIndex = 0;
	}

	public void drawCG() { // 画开始自动脚本

	}

	/*************************************** 画教学 ****************************************************/
	byte isNewGameIndex = 0;
	boolean isNewGame = true;
	int teachIndex;
	int teachStatus;
	String s[] = null;

	void drawTeach(int x, int y) {
		if (!isNewGame) {
			return;
		}
		if (MyGameCanvas.gameStatus != MyGameCanvas.GmStat_Playing) {
			return;
		}

		int index = 0;
		teachIndex++;
		// switch (teachStatus) {
		// case 0:
		// index = PAK_IMAGES.IMG_H1_PNG;
		// break;
		// case 1:
		// index = PAK_IMAGES.IMG_H2_PNG;
		// break;
		// case 2:
		// index = PAK_IMAGES.IMG_H3_PNG;
		// break;
		// case 3:
		// index = PAK_IMAGES.IMG_H4_PNG;
		// break;
		// case 4:
		// index = PAK_IMAGES.IMG_H5_PNG;
		// break;
		// case 5:
		// index = PAK_IMAGES.IMG_H6_PNG;
		// break;
		// }
		// GameDraw.renderAnimPic2(PAK_IMAGES.IMG_BG1_PNG, index, x
		// + MyGameCanvas.SCREEN_WIDTH / 2 + 13, y
		// + MyGameCanvas.SCREEN_HEIGHT / 2 + 10, MyGameCanvas.data_help,
		// false, false, 1000, 0, 0);

		//
		GameDraw.add_Image(index, Tools.setOffX, Tools.setOffY, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 1000);
	}

	void ctrlTeach(int keyCode) {
		// System.out.println("isNewGame:" + isNewGame + "   " + teachStatus);
		if (!isNewGame) {
			return;
		}
		// switch (keyCode) {
		// case MyGameCanvas.KEY_OK:
		// case MyGameCanvas.KEY_5:
		if (teachIndex > 10) {
			teachIndex = 0;
			teachStatus++;
		}
		if (teachStatus > 5) {
			isNewGame = false;
		}
		//
		// break;
		//
		// }

	}

	public void drawJaoXue() {

	}

	/****
	 * 开始产生敌人 wave 波数 iTime 相邻敌人间的时间间距 iWaveTime 相邻波敌人间的时间间隔
	 */

	public void proEnemy() {
		// if(iWave>=role.enemyData.length){return;};//怪物已经全出
		// if(iWaveTime>0){iWaveTime--;}
		// if(iTime>0){iTime--;}
		// if(iWaveTime==0){//波间隔0
		// if(iTime==0){//
		// if(iWaveNum<=role.enemyData[iWave].length-1){//如果当前波数有兵可出
		// iWaveNum++;
		// iTime = 60;
		// }else{//当前波数没兵可出
		// if(iWave==role.enemyData.length-1){//最后一波，且没兵可出
		// if(usBs.iUsBsCuHp>0&&enemys.size()==0){
		// MyGameCanvas.me.setST(MyGameCanvas.me.GmStat_Win);//进入胜利界面
		// }
		// }else{//没兵可出，但并非最后一波
		// iWaveNum = 0;
		// iWave++;
		// iWaveTime = 70;
		// }
		// }
		// }
		// }
	}
	
	public int getemsType(){
		int type=0;
		switch (gameRank) { 
		case 0:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_牧师;
				break;
			case 1:
				type=fd.TYPE_ENEMY_步兵;
				break;
			case 2:
				type=fd.TYPE_ENEMY_鹰;
				break;
			}
			break;
		case 1:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_牧师;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_步兵;
				break;
			case 2:
				type=fd.TYPE_ENEMY_鹰;
				break;
			}
			break;
		case 2:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_步兵;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_牧师;
				break;
			case 2:
				type=fd.TYPE_ENEMY_石盾;
				break;
			}
			break;
		case 3:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_步兵;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_牧师;
				break;
			case 2:
				type=fd.TYPE_ENEMY_石盾;
				break;
			}
			break;
		case 4:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_步兵;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_刺客;
				break;
			case 2:
				type=fd.TYPE_ENEMY_绿盾;
				break;
			}
			break;
		case 5:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_刺客;	
				break;
			case 1:
	
				type=fd.TYPE_ENEMY_牧师;
				break;
			case 2:
				type=fd.TYPE_ENEMY_石盾;

				break;
			}
			break;
		case 6:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_鹰;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_牧师;
				break;
			case 2:
				type=fd.TYPE_ENEMY_石盾;
				break;
			}
			break;
		case 7:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_鹰;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_牧师;
				break;
			case 2:
				type=fd.TYPE_ENEMY_石盾;
				break;
			}
			break;
		case 8:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_步兵;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_刺客;
				break;
			case 2:
				type=fd.TYPE_ENEMY_石盾;
				break;
			}
			break;
		case 9:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_步兵;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_鹰;
				break;
			case 2:
				type=fd.TYPE_ENEMY_石盾;
				break;
			}
			break;
		case 10:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_绿盾;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_鹰;
				break;
			case 2:
				type=fd.TYPE_ENEMY_刺客;
				break;
			}
			break;
		case 11:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_石盾;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_石盾;
				break;
			case 2:
				type=fd.TYPE_ENEMY_刺客;
				break;
			}
			break;
		case 12:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_石盾;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_鹰;
				break;
			case 2:
				type=fd.TYPE_ENEMY_绿盾;
				break;
			}
			break;
		case 13:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_石盾;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_鹰;
				break;
			case 2:
				type=fd.TYPE_ENEMY_绿盾;
				break;
			}
			break;
		case 14:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_石盾;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_牧师;
				break;
			case 2:
				type=fd.TYPE_ENEMY_绿盾;
				break;
			}
			break;
		case 15:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_石盾;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_牧师;
				break;
			case 2:
				type=fd.TYPE_ENEMY_绿盾;
				break;
			}
			break;
		case 16:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_石盾;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_牧师;
				break;
			case 2:
				type=fd.TYPE_ENEMY_绿盾;
				break;
			}
			break;
		case 17:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_石盾;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_刺客;
				break;
			case 2:
				type=fd.TYPE_ENEMY_绿盾;
				break;
			}
			break;
		case 18:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_绿盾;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_刺客;
				break;
			case 2:
				type=fd.TYPE_ENEMY_鹰;
				break;
			}
			break;
		case 19:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_鹰;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_绿盾;
				break;
			case 2:
				type=fd.TYPE_ENEMY_石盾;
				break;
			}
			break;
		case 20:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_鹰;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_绿盾;
				break;
			case 2:
				type=fd.TYPE_ENEMY_石盾;
				break;
			}
			break;
		case 21:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_石盾;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_绿盾;
				break;
			case 2:
				type=fd.TYPE_ENEMY_鹰;
				break;
			}
			break;
		case 22:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_石盾;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_绿盾;
				break;
			case 2:
				type=fd.TYPE_ENEMY_刺客;
				break;
			}
			break;
		case 23:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_石盾;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_绿盾;
				break;
			case 2:
				type=fd.TYPE_ENEMY_鹰;
				break;
			}
			break;
		case 24:
			type=GameRandom.result(0,3);
			switch (type) {
			case 0:
				type=fd.TYPE_ENEMY_石盾;	
				break;
			case 1:
				type=fd.TYPE_ENEMY_绿盾;
				break;
			case 2:
				type=fd.TYPE_ENEMY_鹰;
				break;
			}
			break;
		}
		
		
		return type;

		
	}
	void chekGuanCaiXY(){
		if(gameRank<=1||iWaveNum==fd.enemyPlace[iWave].length){return;}
		if(fd.Guancai2[0][0]==-1){
			return;
		}
		fd.Guancai =new int[GameRandom.result(2, 8)][6];
//		while (fd.Guancai.length!=ems) {

			for (int i = 0; i < fd.Guancai.length; i++) {
				
				int luNum = GameRandom.result(0, fd.Guancai2.length);//随机一个路径
				int x = GameRandom.result(fd.Guancai2[luNum][0],fd.Guancai2[luNum][1]); //随机此路劲的x
				int y = GameRandom.result(fd.Guancai2[luNum][2],fd.Guancai2[luNum][3]);//随机此路劲的Y
				if(bullm.bInCircle(x,y,fd.Guancai[i][2],fd.Guancai[i][3],50)){//如果随机的坐标与其他怪物重复则从新随机
					i--;
					continue;
				}
					fd.Guancai[i][0]=0;
					fd.Guancai[i][1]=10;
					fd.Guancai[i][2]=x;
					fd.Guancai[i][3]=y;
					fd.Guancai[i][4]=getemsType();
					fd.Guancai[i][5]=fd.Guancai2[luNum][4];
					bullm.addEffect(105,fd.Guancai[i][2],fd.Guancai[i][3],i);
//					  bullm.addEffect(105,fd.Guancai[i][2],fd.Guancai[i][3],i);	
//					enemys.addElement(new GameRole(fd.TYPE_ENEMY_步兵,x,y,0,0));
			    }
			
//		}
//		fd.Guancai[j][2],fd.Guancai[j][3]
		
	}

	/**
	 * @param status
	 *            生产敌人
	 */
	public void produceEnemy(int status) {

//		if (status == MyGameCanvas.gmScripe) {
//			fd.getWavePlace(MyGameCanvas.gmScripe, gameRank, iWave);
//		} else {
//			fd.getWavePlace(MyGameCanvas.gmFight, iFightGameRank, iWave);
//		}

		if (iWaveTime > 0) {
			iWaveTime--;
		}
		if (iTime > 0) {
			iTime--;
		}
		if (iWaveTime == 0) {
			if (iTime == 0) {
				if (iWave <= fd.enemyData.length - 1) {
					for (int i = 0; i < fd.enemyPlace[iWave].length; i++) {// 遍历一波中所有的出兵点
					      if(i>=2&&MyGameCanvas.isyiDaBo){
					    	  MyGameCanvas.isyiDaBo = false;
					    	  bullm.addEffect(106,0,0,0);
					      }
						if (iWavePlace[i] <= fd.enemyData[iWave][i].length - 1) {// 判断一个出兵点没有全出
							getStartDir(fd.enemyPlace[iWave][i][2]);
							if (fd.enemyData[iWave][i][iWavePlace[i]] == -1) {
								iHit++;
								iTime = 100;
								iWavePlace[i]++;
//								System.out.println("2222222222222222222:"+i);
								continue;
							}
							for (int j = 0; j < fd.enemyTS.length; j++) {
								if (fd.enemyTS[j][0] == iHit) {
//									System.out.println("11111111111111:"+i);
									if(MyActivity.VMHeight>240){
										enemys.addElement(new GameRole(
												fd.enemyData[iWave][i][iWavePlace[i]],
												fd.enemyPlace[iWave][i][0],
												fd.enemyPlace[iWave][i][1],
												fd.enemyPlace[iWave][i][2],
												role.trans, role.rota, role.driftX,
												role.driftY, 0, iWave,
												fd.enemyTS[j][1]));
									}else{
										int index=fishData.TYPE_ENEMY_步兵;
										switch (fd.enemyData[iWave][i][iWavePlace[i]]) {
										case fishData.TYPE_ENEMY_步兵:
										case fishData.TYPE_ENEMY_紫盾:
											index=fishData.TYPE_ENEMY_步兵;
											break;
										case fishData.TYPE_ENEMY_绿盾:
										case fishData.TYPE_ENEMY_鹰:
										case fishData.TYPE_ENEMY_光头:
											index=fishData.TYPE_ENEMY_鹰;
											break;
										case fishData.TYPE_ENEMY_牧师:
										case fishData.TYPE_ENEMY_金盾:
											index=fishData.TYPE_ENEMY_牧师;
											break;
										case fishData.TYPE_ENEMY_刺客:
										case fishData.TYPE_ENEMY_石盾:
											index=fishData.TYPE_ENEMY_石盾;
											break;
										case fishData.TYPE_ENEMY_狮鹫:
											index=fishData.TYPE_ENEMY_狮鹫;
											break;
										}
										enemys.addElement(new GameRole(
												index,
												fd.enemyPlace[iWave][i][0],
												fd.enemyPlace[iWave][i][1],
												fd.enemyPlace[iWave][i][2],
												role.trans, role.rota, role.driftX,
												role.driftY, 0, iWave,
												fd.enemyTS[j][1]));
									}
	
									break;
								} else {
									if (j == fd.enemyTS.length - 1) {
//										System.out.println("iiiiii:"+i);
										if(MyActivity.VMHeight>240){
											enemys.addElement(new GameRole(
													fd.enemyData[iWave][i][iWavePlace[i]],
													fd.enemyPlace[iWave][i][0],
													fd.enemyPlace[iWave][i][1],
													fd.enemyPlace[iWave][i][2],
													role.trans, role.rota,
													role.driftX, role.driftY, 0,
													iWave, 0));
										}else{
											int index=fishData.TYPE_ENEMY_步兵;
											switch (fd.enemyData[iWave][i][iWavePlace[i]]) {
											case fishData.TYPE_ENEMY_步兵:
											case fishData.TYPE_ENEMY_紫盾:
												index=fishData.TYPE_ENEMY_步兵;
												break;
											case fishData.TYPE_ENEMY_绿盾:
											case fishData.TYPE_ENEMY_鹰:
											case fishData.TYPE_ENEMY_光头:
												index=fishData.TYPE_ENEMY_鹰;
												break;
											case fishData.TYPE_ENEMY_牧师:
											case fishData.TYPE_ENEMY_金盾:
												index=fishData.TYPE_ENEMY_牧师;
												break;
											case fishData.TYPE_ENEMY_刺客:
											case fishData.TYPE_ENEMY_石盾:
												index=fishData.TYPE_ENEMY_石盾;
												break;
											case fishData.TYPE_ENEMY_狮鹫:
												index=fishData.TYPE_ENEMY_狮鹫;
												break;
											}
												enemys.addElement(new GameRole(
														index,
														fd.enemyPlace[iWave][i][0],
														fd.enemyPlace[iWave][i][1],
														fd.enemyPlace[iWave][i][2],
														role.trans, role.rota,
														role.driftX, role.driftY, 0,
														iWave, 0));
												
											
		
										}
	
									}
								}
							}
//							System.out.println("生产敌人");
							if(GameEngine.roleNumber==0){
							iHit++;// 统计某一关总共有的敌人数
							}
							iTime = 100;
							iWavePlace[i]++;
							if (iWavePlace[i] == fd.enemyData[iWave][i].length) {// 某个出兵点敌人全部出完
								iWaveNum++;
							}
//							System.out.println("iWave :"+iWave+":iHit:"+iHit);
							if(iHit==32&&iResult[0][1]==-1&&gameRank==0){
								xinShouJiangLi = 1;//第一关的新手奖励
							}

							if(gameRank<=1){
							for (int j = 0; j < fd.Guancai.length; j++) {
								if(fd.Guancai[j][1]==iHit){
										   bullm.addEffect(105,fd.Guancai[j][2],fd.Guancai[j][3],j);	
						   			}
 
//							enemys.addElement(new GameRole(fd.Guancai[j][4],fd.Guancai[j][2],fd.Guancai[j][3],fd.Guancai[j][5],j));
										}
										}
							 if(iWaveNum==fd.enemyPlace[iWave].length){//某一波拥有出兵点全部出完
							 iWaveNum = 0;
							 if(GameEngine.roleNumber==0){
							 iWave++;
								}
							 bProduce =true;
							 iWaveTime =200;
							 for(int w=0;w<8;w++){iWavePlace[w]=0;}
							 }


						}
					}

				}
				//
				if ((iWave <= fd.enemyData.length - 1)
						&& iWaveNum == fd.enemyPlace[iWave].length
						&& enemys.size() == 0) {// 如果某一波所拥有的出兵点全部出完
					if(iWave<fd.enemyData.length - 1){
						MyGameCanvas.AddBlastEffectList(240, 160,
								MyGameCanvas.me.EFF_WAVETISHI, 0, 800, 0);
						isWuYa = false;//一波过后乌鸦可以重新出现
					}

					iWaveNum = 0;
					// everyWaveGet(iWave);
					iWave++;
					bProduce = true;
					iWaveTime = 200;
					for (int w = 0; w < 8; w++) {
						iWavePlace[w] = 0;
					}
				}

				// if(MyGameCanvas.index%10==0){
				// MyGameCanvas.AddBlastEffectList(400,240,MyGameCanvas.me.EFF_WAVETISHI,
				// 0,800,0);
				// iWaveNum = 0;
				// // everyWaveGet(iWave);
				// iWave++;
				// bProduce =true;
				// iWaveTime =0;
				// for(int w=0;w<8;w++){
				// iWavePlace[w]=0;
				// }
				// }
			}

		}
		if(roleNumber==0){
		if (iWave >= fd.enemyData.length) {
			if (enemys.size() == 0 && iRunNum <= 10&&iHit>=fd.enemyData.length) {// 胜利与否的判断根据逃掉的怪物数量来算
				if(!fishData.isJiJia){
						shenli = true;
						Tools.removeAllImage();
					  MyGameCanvas.setST(MyGameCanvas.GmStat_dengdai_win);	
					role.iIsAccel = 1;
					MyGameCanvas.me.GotPride(status);// 结束了,胜利了,给点奖励和评价
					MyGameCanvas.EffectV.removeAllElements();// 在进入胜利前将所有动画效果全部清除
					MyGameCanvas.me.WinLogic();
//					System.out.println("333333333333333");
				}else{
					iWave=0;iHit=0;
				}
			}
		}// 怪物已经全出
		}
		if(roleNumber>=1&&zd!=0){//特殊关卡
			
			if(roleNumber<=1){//达到规定数量跳胜利界面
				MyGameCanvas.setST(MyGameCanvas.GmStat_dengdai_win);
				canvas.WinLogic();
//				System.out.println("44444444444444444444");
			}
			if(roleTime<=1){//三分钟时间到了判断失败
				canvas.setST(MyGameCanvas.GmStat_dengdai_lose);
			}
			
		}
//		System.out.println("iWave ： "+iWave +"  fd："+fd.enemyData.length);
		if(iWave >= fd.enemyData.length-1&&gameRank==2&&!isYuanZiDan&&iResult[0][3]==-1
				){//第三关临时开启原子弹技能， 用完关闭
//			if(++YZD>=80){
//				canvas.jiNengKaiQi[2]=1;
//				JN3=63;
//				isYuanZiDan=true;
//				xinShouJiangLi=3;
//			}

		}
		
	}
	int YZD;
	boolean isYuanZiDan=false;
	boolean isRank1=false;
	boolean shenli = false;
    public static byte xinShouJiangLi;//新手奖励
	/**
	 * 没波结束后奖励金钱
	 * 
	 * @param wave
	 */
	void everyWaveGet(int wave) {
		switch (wave / 5) {
		case 0:
			usBs.iUsBsCuJinbi += 10;
			break;
		case 1:
			usBs.iUsBsCuJinbi += 20;
			break;
		case 2:
			usBs.iUsBsCuJinbi += 30;
			break;
		default:
			usBs.iUsBsCuJinbi += 40;
		}

	}

}
