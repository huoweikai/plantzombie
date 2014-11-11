package com.haopu.JSGame;

import java.util.Vector;

import com.haopu.Enemy.fishData;
import com.haopu.kbz.*;
import com.haopu.pak.PAK_BIN;
import com.haopu.pak.PAK_IMAGES;

import android.util.Log;

public final class GameRole extends GameInterface {
//塔的分类	
	public final static int TA_YULEI = 3;//鱼雷塔
//	public final static int TA_ZENGFU = 7;//增幅塔
	public final static int TA_LIEYU = 1;//猎鱼塔
//	public final static int TA_DIANWANG = 3;//电网塔
	public final static int TA_BINGJING = 2;//冰晶塔
	public final static int TA_XIQIAN = 6;//吸钱塔
	public final static int TA_HUOPAO = 5;//火炮塔
	public final static int TA_HERO =4;//英雄塔
	public final static int HOME = 9;//老家
//塔的子弹	 
	public static final int Bullet_lieyu = 11;
	public static final int Bullet_yulei = 12;
	public static final int Bullet_zengfu = 13;
	public static final int Bullet_dianwang = 14;
	public static final int Bullet_hero = 15;
	public static final int Bullet_bingjing = 16;
	public static final int Bullet_xiqian = 17;
	public static final int Bullet_huopao = 18;
	public static final int Bullet_home = 19;//老家子弹
//怪物分类
	public final static int TYPE_ENEMY_步兵 = 20;  
	public final static int TYPE_ENEMY_牧师 = 21;
	public final static int TYPE_ENEMY_刺客 = 22;
	public final static int TYPE_ENEMY_光头 = 23;
	public final static int TYPE_ENEMY_鹰 = 24;
	public final static int TYPE_ENEMY_石盾 = 25;
	public final static int TYPE_ENEMY_金盾 = 26;
	public final static int TYPE_ENEMY_绿盾 = 27;
	public final static int TYPE_ENEMY_紫盾 = 28;
	public final static int TYPE_ENEMY_暗牧= 29;
	public final static int TYPE_ENEMY_狮鹫 = 30;
	public final static int TYPE_ENEMY_黑刺客 = 31;
	public final static int TYPE_ENEMY_黑狮鹫 = 32;
	public final static int TYPE_ENEMY_步兵BOSS = 33;
	public final static int TYPE_ENEMY_骑兵BOSS = 34;
	public final static int TYPE_ENEMY_大剑BOSS = 35;
	public final static int TYPE_ENEMY_重甲BOSS = 36;
	public final static int TYPE_ENEMY_乌鸦 = 37;
//	public final static int TYPE_ENEMY_重甲BOSS = 36;
	public final static int TYPE_goldFish = 61;//黄金鱼
//3种技能	
	public final static int SKILL_STORM = 37;//技能：电磁风暴
	public final static int SKILL_FROST = 38;//技能：绝对霜冻
	public final static int SKILL_RECOVER = 39;//技能：huoqiu
//违建	
	public static final int UnLawBuildBig = 43;//大违建
	public static final int UnLawBuildSmall = 44;//小违建
	public static final int PROPKUANG = 45;//属性框标示
	double num;//怪物精灵行走的步数，用于箭矢攻击时选择范围内的第一个敌人
//	int towerChoose[] = new int[]{TA_LIEYU,TA_BINGJING,0,0,0,0};
	int  iIsAccel = 1;//游戏加速的倍数
	static int list= 0;
	static int line = 0; // 每关的初始点
	int iEnemyHp  ;//怪物的血量
	int iEnemyHpNum;
	int move_X,move_Y;//怪物的移动速度
	int circle_X,circle_Y,circle_R;//塔的圆点坐标级半径
	int attack;//塔的攻击力
	int iCoolTime;//塔的攻击冷却时间
	int iceTime;//怪物被冰冻减速
	int iFrozenTime;//怪物被冻住，无法移动
	int iSkillCoolTime[] = new int[] {0,0,0,0};//技能冷却时间
	boolean bExtraAttack;//是否存在额外伤害,增幅塔的效果
	int iExtraAttack;//额外伤害值
	int iSkillNum[] = new int[]{10,10,10};//3种技能的购买个数
	int iJewelNum;
//8种塔及每种塔对应的自身每一等级的攻击,速度,范围条幅长度
//	static int liuyuTiao[][] =new int[][] {{20,50,80},{40,100,100},{80,100,100},{120,100,100},{150,100,100}};
//	static int bingjingTiao[][] =new int[][] {{30,60,80},{40,100,100},{80,100,100},{120,100,100},{150,100,100}};
//	static int yuleiTiao[][] =new int[][] {{40,40,80},{40,100,100},{80,100,100},{120,100,100},{150,100,100}};
//	static int heroTiao[][] =new int[][] {{50,30,80},{40,100,100},{80,100,100},{120,100,100},{150,100,100}};
//	static int huopaoTiao[][] =new int[][] {{60,30,80},{40,100,100},{80,100,100},{120,100,100},{150,100,100}};
//	static int xiqianTiao[][] =new int[][] {{10,50,326},{10,50,326},{10,50,326},{10,50,326},{10,50,326}};
////8种塔的初始攻击力及对应升级的攻击力，商城的升级时永久的
//	static int startAttack[][] = new int[][]{{8,10,12,14,16},{12,14,16,18,20},{16,20,24,28,32},
//			{20,30,40,50,60},{12,14,16,18,20},{0,0,0,0,0}};
		
	
	public static GameEngine engine;
	public static GameRole me;

	public GameRole(GameEngine ge) {
		me = this;
		engine = ge;
		if(MyActivity.VMWidth<=320){iIsAccel=4;}
	}
/**
 * 关于敌人的数据
 * @param type敌人分类
 * @param hp血量
 * @param dir方向
 * @param line敌人出兵位置所在行
 * @param list敌人出兵位置所在列
 * @param iwave 计算当前怪物血量
 * @param 镜像
 */
    public GameRole(int type,int line,int list,int dir,byte trans,float rota,int drifx,int drify,int delay,int iwave,int teshuguaiwu){//敌人
    	init(type,line,list,dir,trans,rota,drifx,drify,delay,iwave,teshuguaiwu);
    }
    public GameRole(int type,int x,int y,int dir,int index){
    	this.type = type;
    	this.x = x;
    	this.y = y;
    	getEneHp(type,iWaveHp);//获取敌人血量,
    	getEneSpeed(type);//获取敌人运行速度
//    	this.move_X=0;
//    	this.move_Y=0;
		initData(this.type);
		getBox();
		this.CGuanCai = dir;
		setDir(dir);
		dir_from = dir;
    	setStatus(STATUS_STOP);
		this.go=70;
		if(engine.gameRank==1){
			this.TeShuGuaiWu = GameRandom.result(1, 5);
		}

    }
    /**
     * 关于塔的数据
     * @param type塔分类
     * @param line塔所在地图列
     * @param list塔所在地图行
     * @param attack塔的攻击力
     * @param dir塔的口朝向即塔的转向
     */
    public GameRole(int line,int list,int type,int r,int att,int dir){
    	this.type = type;
    	initData(type);
    	this.dir = dir;
		setStatus(STATUS_STOP);
		x = line * GameEngine.me.map.tileWidth+(this.type==TA_XIQIAN?0:30);
		y = list * GameEngine.me.map.tileWidth+(this.type==TA_XIQIAN?0:30);
		circle_X = x;
		circle_Y = y;
		circle_R=r;
		getAttack(type);
    }
/**
 * 违建    
 * @param line
 * @param list
 * @param type
 * @param hp
 */
    public  GameRole(int line,int list,int type,int hp){
    	switch(type){
    	case UnLawBuildBig:
    	case UnLawBuildSmall:
    		x = line*GameEngine.me.map.tileWidth+30;
    		y = list*GameEngine.me.map.tileWidth+30; 
    		this.iEnemyHp = hp;
    		this.type =type;
    		setStatus(STATUS_STOP);
    		break;
    	}
    }
    
	void initData(int type) {
		switch (type) {
		case TA_YULEI:
//			data = MyGameCanvas.data_ta_yulei;
//			motion = GameData.motion_yutata;
			break;
//		case TA_ZENGFU:
//			data = MyGameCanvas.data_ta_zengfu;
//			motion = GameData.motion_zengfuta;
//			break;
		case TA_LIEYU:
//			data = MyGameCanvas.data_ta_lieyu;
//			motion = GameData.motion_lieyuta;
			break;
//		case TA_DIANWANG:
//			data = MyGameCanvas.data_ta_dianwang;
//			motion = GameData.motion_dianwangta;
//			break;
		case TA_BINGJING:
//			data = MyGameCanvas.data_ta_bingjing;
//			motion = GameData.motion_bingjingta;
			break;
		case TA_XIQIAN:
//			data = MyGameCanvas.data_ta_xiqian;
//			motion = GameData.motion_xiqianta;
			break;
		case TA_HUOPAO:
//			data = MyGameCanvas.data_ta_huopao;
//			motion = GameData.motion_huopaota;
			break;
		case TA_HERO:
//			data = MyGameCanvas.data_ta_hero;
//			motion = GameData.motion_herota;
			break;
		case TYPE_ENEMY_步兵:
			data = MyGameCanvas.data_TYPE_ENEMY_步兵;
//			motion = GameData.motion_TYPE_ENEMY_步兵;
			break;
		case TYPE_ENEMY_牧师:
			data = MyGameCanvas.data_TYPE_ENEMY_牧师;
//			motion = GameData.motion_TYPE_ENEMY_牧师;
			break;
		case TYPE_ENEMY_刺客:
			data = MyGameCanvas.data_role_dog;
//			motion = GameData.motion_TYPE_ENEMY_刺客;
			break;
		case TYPE_ENEMY_光头:
			data = MyGameCanvas.data_role_dog;
//			motion = GameData.motion_TYPE_ENEMY_光头;
			break;
		case TYPE_ENEMY_鹰:
    		data = MyGameCanvas.data_TYPE_ENEMY_鹰;
//    		motion = GameData.motion_TYPE_ENEMY_鹰;
			break;
		case TYPE_ENEMY_石盾:
			data = MyGameCanvas.data_TYPE_ENEMY_石盾;
//			motion = GameData.motion_TYPE_ENEMY_石盾;
			break;
		case TYPE_ENEMY_金盾:
			data = MyGameCanvas.data_TYPE_ENEMY_金盾;
//			motion = GameData.motion_TYPE_ENEMY_金盾;
			break;
		case TYPE_ENEMY_绿盾:
			data = MyGameCanvas.data_TYPE_ENEMY_绿盾;
//			motion = GameData.motion_TYPE_ENEMY_绿盾;
			break;
		case TYPE_ENEMY_紫盾:
			data = MyGameCanvas.data_TYPE_ENEMY_紫盾;
//			motion = GameData.motion_TYPE_ENEMY_紫盾;
			break;
		case TYPE_ENEMY_暗牧:
			data = MyGameCanvas.data_TYPE_ENEMY_暗牧;
//			motion = GameData.motion_TYPE_ENEMY_牧师;
			break;
		case TYPE_ENEMY_狮鹫:
			data = MyGameCanvas.data_TYPE_ENEMY_狮鹫;
//			motion = GameData.motion_TYPE_ENEMY_狮鹫;
			break;
		case TYPE_ENEMY_黑刺客:
			data = MyGameCanvas.data_TYPE_ENEMY_黑刺客;
//			motion = GameData.motion_TYPE_ENEMY_刺客;
			break;
		case TYPE_ENEMY_黑狮鹫:
			data = MyGameCanvas.data_TYPE_ENEMY_黑狮鹫;
//			motion = GameData.motion_TYPE_ENEMY_狮鹫;
			break;
		case TYPE_ENEMY_步兵BOSS:
//			data = MyGameCanvas.data_TYPE_ENEMY_步兵BOSS;
//			motion = GameData.motion_TYPE_ENEMY_步兵BOSS;
			break;
		case TYPE_ENEMY_骑兵BOSS:
//			data = MyGameCanvas.data_TYPE_ENEMY_骑兵BOSS;
//			motion = GameData.motion_TYPE_ENEMY_骑兵BOSS;
			break;
		case TYPE_ENEMY_大剑BOSS:
//			data = MyGameCanvas.data_TYPE_ENEMY_大剑BOSS;
//			motion = GameData.motion_TYPE_ENEMY_大剑BOSS;
			break;
		case TYPE_ENEMY_重甲BOSS:
//			data = MyGameCanvas.data_TYPE_ENEMY_重甲BOSS;
//			motion = GameData.motion_TYPE_ENEMY_重甲BOSS;
			break;
		case TYPE_goldFish:
//			data = MyGameCanvas.data_goldFish;
//			motion = GameData.motion_TYPE_ENEMY_重甲BOSS;
			break;
	  }
	}

	void initSprite() { // 雇佣兵
//		int[] attackArea = { 40, 110 + 50, 68, 45, 50, 70, 60, 100 + 50, 84 };
//		int[] doubleAttack = { 3, 5, 8, 12, 0, 15, 18, 21, 25 };
//
//		x = -10;
//		y_layerRoad = GameRandom.result(3);
//		y = y_pos[y_layerRoad];
//		// y = engine.restlt_3(y_pos);
//		if (type == TYPE_ROLE_圆盾兵) {
//			y = y_pos2;
//			y_layerRoad = 1;
//		}
//		setDir(DIR_RIGHT);
//		isLeft = false;
//		roleAttackArea = attackArea[type] * 2;
//		double_attack = doubleAttack[type];
//		attack = MyGameCanvas.menuItem.getPropAttack(type,
//				GameMenuItem.propLeavelData[type]);
//
//		hp = hp_max = MyGameCanvas.menuItem.getPropHp(type,
//				GameMenuItem.propLeavelData[type]);
//
//		switch (GameMenuItem.zhenfaIndex) {
//		case 0:
//			attack = attack
//					+ attack
//					* MyGameCanvas.menuItem
//							.setZhengfaEff(GameMenuItem.zhenfaIndex) / 100;
//			break;
//
//		case 1:
//			hp = hp
//					+ hp
//					* MyGameCanvas.menuItem
//							.setZhengfaEff(GameMenuItem.zhenfaIndex) / 100;
//			break;
//
//		default:
//			break;
//		}
//
	}

	void init(int type,int line,int list,int dir,byte trans,float rota,int drifx,int drify,int delay,int iwave,int teshuguaiwu){//初始敌人数据
		this.type = type;
		this.attackLevel =1;
		initData(this.type);
		getBox();
		setDir(dir);
		dir_from = dir;
		this.trans =trans; 
		this.rota = rota;
		this.driftX = drifx;
		this.driftY = drify;
		this.enemyDelay =delay;
		this.iWaveHp = iwave;
		this.TeShuGuaiWu = teshuguaiwu;
    	x=list*GameEngine.me.map.tileWidth+driftX;//敌人初始出兵坐标
    	y=line*GameEngine.me.map.tileWidth+driftY;
//    	x = (int) (x/(MyGameCanvas.me.SCREEN_WIDTH/fishGame.VMWidth));
//		y = (int) (y/(MyGameCanvas.me.SCREEN_HEIGHT/fishGame.VMHeight));
    	setStatus(STATUS_MOVE);
    	getEneHp(type,iWaveHp);//获取敌人血量,
    	getEneSpeed(type);//获取敌人运行速度
    	
	}

//	public void initRole(int gameRank) {
//		speedX = 12;
//		speedY = 6;
//		index = 0;
//		injureTime = 0; // 
//		initData(type);
//		setStatus(STATUS_MOVE);
//		initProp(level);
//		startPosY = y;
//		isAuto = false;
//		sx = x;
//		mp = mp_max;
//		mp = 0;
//	}
//	public void init_roleXY(int gameRank){
//		if(gameRank>=0&&gameRank<=6){x=0;y=17*64;}
//		else if(gameRank==7){x=0;y=1728;}
//		else if(gameRank==8){x=0;y=1600;}
//		else if(gameRank==9){x=0;y=960;}
//		else if(gameRank==10){x=0;y=1792;}
//		else if(gameRank==11){x=0;y=2240;}
//		else if(gameRank==12){x=0;y=896;}
//		else if(gameRank==13){x=0;y=832;}
//		else if(gameRank==14){x=0;y=832;}
//		else if(gameRank==15){x=0;y=1280;}
//		else if(gameRank==16){x=0;y=1472;}
//		else if(gameRank==17){x=0;y=768;}
//		
//		else if(gameRank==18){x=0;y=1856;}
//		else if(gameRank ==19){x=0;y=1856;}
//		else if(gameRank ==20){x=0;y=1856;}
//		else if(gameRank ==21){x=0;y=1856;}
//		else if(gameRank ==22){x=0;y=1856;}
//		else if(gameRank==23){x=0;y=1856;}
//		
//		else if(gameRank==24){x=0;y=1408;}
//		else if(gameRank==25){x=0;y=1792;}
//		else if(gameRank==26){x=0;y=1792;}
//		else if(gameRank==27){x=0;y=1024;}
//		else if(gameRank==28){x=0;y=1024;}
//		else if(gameRank==29){x=0;y=1600;}
//	}
//	/**
//	 *画教学
//	 * @param obj
//	 * @param vec
//	 * @return
//	*/
//		  public void getTeach(){
//		      if(bTeach==false)return;	
//		      int imge[] = {PAK_IMAGES.IMG_XIAOSHOUZHI,PAK_IMAGES.IMG_DASHOUZHI};
//		      for(int i=0;i<GameEngine.me.enemys.size();i++){
//		    	  GameRole enemy = GameEngine.me.enemys.elementAt(i);
//		    	  if((GameEngine.me.iWave==0)&&(enemy.y>=GameEngine.me.map.tileWidth*3+20&&
//		    			  enemy.y<=GameEngine.me.map.tileWidth*4)){//第一波到（3,3）位置
//					  enemy.setStatus(enemy.STATUS_STOP);
//					  if(enemy.iEnemyHp>0){
//						  GameDraw.add_Image(imge[(MyGameCanvas.me.gameTime/8)%2],
//								  (GameEngine.me.map.tileWidth*7)/2-60,(GameEngine.me.map.tileWidth*7)/2+60,
//								  Tools.HCENTER_VCENTER,Tools.TRANS_NONE,200);//（3,3）处画闪烁的手指
//						  iTeach[0]=1;
//					  }else{//如果敌人被打死了,继续第二波出兵
//						  enemy.setStatus(enemy.STATUS_DEAD);
//						  GameEngine.me.iWavePlace[0]=0;
//						  GameEngine.me.iWave++;
//					  }
//				  }
//		    	  if((GameEngine.me.iWave==1)&&(enemy.x>=GameEngine.me.map.tileWidth*6+20&&
//		    			  enemy.x<=GameEngine.me.map.tileWidth*7)){
//		    		  enemy.setStatus(enemy.STATUS_STOP);
//		    		  if(enemy.iEnemyHp<=0){
//		    			  GameEngine.me.enemys.removeElementAt(i);
//		    		  }
//					  if(GameEngine.me.enemys.size()>0){
//						  if(iTeach[0]==1){
//							  GameDraw.add_Image(imge[(MyGameCanvas.me.gameTime/8)%2],700,260,
//									  Tools.HCENTER_VCENTER,Tools.TRANS_NONE,200);//技能1处画闪烁的手指
//							  
//						  }else if(iTeach[0]==2){//手按住技能1处,手指从技能1处滑向（4,6）
//							  
//						  }
//						  
//					  }else{
//						  GameEngine.me.iWavePlace[0]=0;GameEngine.me.iWavePlace[1]=0;
//						  GameEngine.me.iWave++;
//					  }
//		    	  }
//		    	  
//		      }
//			 
//			  
//		  }
//	public void initProp(int level) {
//		food_max = 40 + level * 2 - 2;
//		mp_max = 100 + level * 2 - 2;
//
//		foodhuifu_time = 6;
//		mphuifu_time = 6;
////		hp_max = 100 + level * 30;
//		attack = 50 + level * 10;
//		defend = 3 + level;
//		double_attack = 10 + level;
//		lucky = 5 + level;
//		exp_up = 10 + 5 * level * level;
//		hp_max = 4;//血量条，一个灯笼相当于2低血
//	  boolean isDrop = false; //掉落标记
	  int mapDir=0;
	  public void roleMove(int mx, int my) {//注意涉及到向上跑时,my为负值
		    int sw = MyActivity.VMWidth;
			int sh = MyActivity.VMHeight;
			float kx = (float) MyGameCanvas.SCREEN_WIDTH / sw ;
			float ky = (float) MyGameCanvas.SCREEN_HEIGHT / sh ;
//			mx = (int) (mx/kx);
//			my = (int) (my/ky);
//			if(mx/kx){
//				
//			}

		    if(iFrozenTime>0){return;}
		    if(iceTime>0){mx-=2;my-=2;}
//		    GameMap map = engine.map;
		    
//		    			    int  mapDir = GameEngine.me.map.propData[GameEngine.me.map.getMapIndex(x, y)];
	

			int n = GameEngine.map.getMapIndex(x, y); // 当前切片块数
			int line = n / GameEngine.map.mapSize[0];
			int col = n % GameEngine.map.mapSize[0];
			switch(dir){
			case DIR_RIGHT://向右跑
				if (x  < col * GameEngine.me.map.tileWidth + GameEngine.me.map.tileWidth / 2
				&& (x+mx)>= col * GameEngine.me.map.tileWidth + GameEngine.me.map.tileWidth / 2
		        ) {
			    x = col * GameEngine.me.map.tileWidth + GameEngine.me.map.tileWidth/ 2;
			    mapDir = GameEngine.me.map.propData[GameEngine.map.getMapIndex(x,y)];//获取怪物脚下切片属性
			    TransToDir(mapDir);
		        } else {
			        x += mx;
			        num++;
		        }
				
				break;
			case DIR_LEFT://向左跑
				if (x + mx <= col * GameEngine.me.map.tileWidth + GameEngine.me.map.tileWidth / 2
				&& x > col * GameEngine.me.map.tileWidth + GameEngine.me.map.tileWidth / 2) {
			    x = col * GameEngine.me.map.tileWidth + GameEngine.me.map.tileWidth / 2;
			    mapDir = GameEngine.me.map.propData[GameEngine.map.getMapIndex(x, y)];//获取怪物脚下切片属性
			    TransToDir(mapDir);
		        } else {
			       x += mx;
			       num++;
		        }
				break;
			case DIR_UP://向上跑,my为负
				if (y + my <= line * GameEngine.me.map.tileWidth + GameEngine.me.map.tileWidth / 2
				&& y > line * GameEngine.me.map.tileWidth + GameEngine.me.map.tileWidth / 2) {
			    y = line * GameEngine.me.map.tileWidth+ GameEngine.me.map.tileWidth / 2;
			    mapDir = GameEngine.me.map.propData[GameEngine.map.getMapIndex(x, y)];//获取怪物脚下切片属性
			    TransToDir(mapDir);
		        } else {
			       y += my;
			       num++;
		        }
				break;
			case DIR_DOWN://向下跑
				if (y + my >= line * GameEngine.me.map.tileWidth + GameEngine.me.map.tileWidth / 2
				&& y < line * GameEngine.me.map.tileWidth + GameEngine.me.map.tileWidth / 2) {
			     y = line * GameEngine.me.map.tileWidth + GameEngine.me.map.tileWidth / 2;
			     mapDir = GameEngine.me.map.propData[GameEngine.map.getMapIndex(x, y)];
			     TransToDir(mapDir);
		       } else {
		    	
			         y += my;
		    		
			         num++;
		        }
				break;
			}

			if(bZhuanwan==true&&Math.abs(maxRota)>0){//处理怪物90度转弯
	        	 rota+=maxRota/9;maxRotaNum++;
	        	 if(maxRotaNum==9){bZhuanwan=false;maxRota=0;maxRotaNum=0;}
			}
		
		  }
/**
 * 将获取到的地图切片的属性转换为对应的怪物移动方向		  
 */
	public void TransToDir(int type){
//		System.out.println("type : "+type);
		switch(type){
		case 94:
			if(GameRandom.result(0,10)>5){
				setDir(DIR_UP);
				bZhuanwan = true;
				maxRota = -90;
			}
			break;
		case 0://向右
			setDir(DIR_RIGHT);
			bZhuanwan = false;
			maxRota = 0;
			break;
		case 1://向下
			setDir(DIR_DOWN);
			bZhuanwan = false;
			maxRota = 0;
			break; 
		case 2://从左到下
			if(dir_from==DIR_RIGHT){
				setDir(DIR_DOWN);
				bZhuanwan = true;
				maxRota = 90;
			}
			break;
		case 3://从上到右
			if(dir_from==DIR_DOWN){
				setDir(DIR_RIGHT);
				bZhuanwan = true;
				maxRota = -90;
			}
			break;
		case 4://从左到上
			if(dir_from==DIR_RIGHT){
				setDir(DIR_UP);
				bZhuanwan =true;
				maxRota=-90;
			}
			break;
		case 5://向上
			setDir(DIR_UP);
			bZhuanwan = false;
			maxRota = 0;
			break;
		case 6://从下到右
			if(dir_from==DIR_UP){
				setDir(DIR_RIGHT);
				bZhuanwan = true;
				maxRota = 90;
			}
			break;
		case 7://从下到左
			if(dir_from==DIR_UP){
				setDir(DIR_LEFT);
				bZhuanwan = true;
				maxRota = -90;
			}
			break;
		case 8://向左
			setDir(DIR_LEFT);
			bZhuanwan = false;
			maxRota = 0;
			break;
		case 9://从右到下
			if(dir_from==DIR_LEFT){
				setDir(DIR_DOWN);
				bZhuanwan = true;
				maxRota = -90;
			}
			break;
		case 10://从上到左
			setDir(DIR_LEFT);
			bZhuanwan = true;
			maxRota = 90;
			break;
		case 11://从左到右，从上到下
			if(dir_from==DIR_RIGHT){
				setDir(DIR_RIGHT);
			}
			if(dir_from==DIR_DOWN){
				setDir(DIR_DOWN);
			}
			bZhuanwan = false;
			maxRota = 0;
			break;
		case 13:
			if(GameRandom.result(0,10)>5){
				setDir(DIR_LEFT);
//				bZhuanwan = true;
//				maxRota = -90;
			}
		break;
		case 15://12,13,14空着,从左到下，从上到下
			if(dir_from==DIR_RIGHT){
				setDir(DIR_DOWN);
				bZhuanwan = true;
				maxRota = 90;
			}
			if(dir_from==DIR_DOWN){
				setDir(DIR_DOWN);
				bZhuanwan = false;
				maxRota = 0;
			}
			break;
		case 16://从左到右，从上到右
			if(dir_from==DIR_RIGHT){
				setDir(DIR_RIGHT);
				bZhuanwan = false;
				maxRota = 0;
			}
			if(dir_from==DIR_DOWN){   
				setDir(DIR_RIGHT);
				bZhuanwan = true;
				maxRota = -90;
			}
			break;   
		case 17://造塔lh    随机下

//			if(dir_from==DIR_DOWN){
//				if(GameRandom.result(0,10)>5){
//					setDir(DIR_RIGHT);	
//				}else{
//					setDir(DIR_LEFT);		
//				}
//			}else{
				if(GameRandom.result(0,10)>5){
					setDir(DIR_DOWN);
//					bZhuanwan = true;
//					maxRota = -90;
				}	
//			}
			
			break;
		case 18://不能早        随机右
			if(GameRandom.result(0,10)>5){
				setDir(DIR_RIGHT);
//				bZhuanwan = true;
//				maxRota = -90;
			}
			break;
			
		case 19://从右到上
//			if(dir_from==DIR_LEFT){
				setDir(DIR_UP);
				bZhuanwan = true;
				maxRota = 90;
//			}
			break;
		case 20://从右到左，从右到上,  根据波数确定究竟上还是下
			if(GameEngine.me.iWave%2==1){
				setDir(DIR_LEFT);
				bZhuanwan = false;
				maxRota = 0;
			}else{
				setDir(DIR_UP);
				bZhuanwan = true;
				maxRota = 90;
			}
			break;
		case 21://从下到上,从右到上
			if(dir_from==DIR_UP){
				setDir(DIR_UP);
				bZhuanwan = false;
				maxRota = 0;
			}
			if(dir_from==DIR_LEFT){
				setDir(DIR_UP);
				bZhuanwan = true;
				maxRota = 90;
			}
			break;
		case 22://从上到左，从上到下
			if(GameEngine.me.iWave%2==1){
				setDir(DIR_LEFT);
				bZhuanwan = true;
				maxRota = 90;
			}else{
				setDir(DIR_DOWN);
				bZhuanwan = false;
				maxRota = 0;
			}
			break;
		case 23://24：从上到左，从右到左
			if(dir_from==DIR_DOWN){
				setDir(DIR_LEFT);
				bZhuanwan = true;
				maxRota = 90;
			}
			if(dir_from==DIR_LEFT){
				setDir(DIR_LEFT);
				bZhuanwan = false;
				maxRota = 0;
			}
			break;
		case 24://25：从左到右，从左到上
            if(GameEngine.me.iWave%2==1){
            	setDir(DIR_RIGHT);
            	bZhuanwan = false;
				maxRota = 0;
            }else{
            	setDir(DIR_UP);
            	bZhuanwan = true;
				maxRota = -90;
            }
			break;
		case 25://26：从左到下，从右到下
			if(dir_from==DIR_RIGHT){
				setDir(DIR_DOWN);
				bZhuanwan = true;
				maxRota = 90;
			}
			if(dir_from==DIR_LEFT){
				setDir(DIR_DOWN);
				bZhuanwan = true;
				maxRota = -90;
			}
			break;
		case 26://27：从上到右，从下到右
			if(dir_from==DIR_DOWN){
				setDir(DIR_RIGHT);
				bZhuanwan =true;
				maxRota =-90;
			}
			if(dir_from==DIR_UP){
				setDir(DIR_RIGHT);
				bZhuanwan =true;
				maxRota =90;
			}
			
			break;
		case 27://28：从右到下，从上到下
			if(dir_from==DIR_DOWN){
				setDir(DIR_DOWN);
				bZhuanwan =false;
				maxRota =0;
			}
			if(dir_from==DIR_LEFT){
				setDir(DIR_DOWN);
				bZhuanwan =true;
				maxRota =90;
			}
			
			break;
		case 28://29：从上到右，从右到左
            if(dir_from==DIR_DOWN){
            	setDir(DIR_RIGHT);
            	bZhuanwan =true;
				maxRota =-90;
            }
            if(dir_from==DIR_LEFT){
            	setDir(DIR_LEFT);
            	bZhuanwan = false;
    			maxRota = 0;
            }
			break;
		case 29://30：从左到右，从右到左
            if(dir_from==DIR_RIGHT){
            	setDir(DIR_RIGHT);
            }
            if(dir_from==DIR_LEFT){
            	setDir(DIR_LEFT);
            }
            bZhuanwan = false;
			maxRota = 0;
			break;
		case 30://31：从上到左，从左到下
            if(dir_from==DIR_DOWN){
            	setDir(DIR_LEFT);
            	bZhuanwan = true;
				maxRota = 90;
            }
            if(dir_from==DIR_RIGHT){
            	setDir(DIR_DOWN);
            	bZhuanwan = true;
				maxRota = 90;
            }
			break;
		case 31://32：从左到上，从上到下
            if(dir_from==DIR_RIGHT){
            	setDir(DIR_UP);
            	bZhuanwan =true;
				maxRota =-90;
            }
            if(dir_from==DIR_DOWN){
            	setDir(DIR_DOWN);
            	bZhuanwan = false;
    			maxRota = 0;
            }
			break;
		case 32://33：从下到右，从右到下
            if(dir_from==DIR_UP){
            	setDir(DIR_RIGHT);
            	bZhuanwan =true;
				maxRota =90;
            }
            if(dir_from==DIR_LEFT){
            	setDir(DIR_DOWN);
            	bZhuanwan =true;
				maxRota =-90;
            }
			break;
		case 33://34：从左到下，从下到左
            if(dir_from==DIR_RIGHT){
            	setDir(DIR_DOWN);
            	bZhuanwan =true;
				maxRota =90;
            }
            if(dir_from==DIR_UP){
            	setDir(DIR_LEFT);
            	bZhuanwan =true;
				maxRota =-90;
            }
			break;
		case 34://35：从右到上，从上到下
            if(dir_from==DIR_LEFT){
            	setDir(DIR_UP);
            	bZhuanwan =true;
				maxRota =90;
            }
            if(dir_from==DIR_DOWN){
            	setDir(DIR_DOWN);
            	bZhuanwan = false;
    			maxRota = 0;
            }
			break;
		case 35://36：从右到下，从下到上
            if(dir_from==DIR_LEFT){
            	setDir(DIR_DOWN);
            	bZhuanwan =true;
				maxRota =-90;
            }
            if(dir_from==DIR_UP){
            	setDir(DIR_UP);
            	bZhuanwan = false;
    			maxRota = 0;
            }
			break;
		case 36://37：从左到上，从上到左
            if(dir_from==DIR_RIGHT){
            	setDir(DIR_UP);
            	bZhuanwan =true;
				maxRota =-90;
            }
            if(dir_from==DIR_DOWN){
            	setDir(DIR_LEFT);
            	bZhuanwan =true;
				maxRota =90;
            }
			break; 
		case 37://38：从左到下，从下到上
            if(dir_from==DIR_RIGHT){
            	setDir(DIR_DOWN);
            	bZhuanwan =true;
				maxRota =90;
            }
            if(dir_from==DIR_UP){
            	setDir(DIR_UP);
            	bZhuanwan = false;
    			maxRota = 0;
            }
			break;
		case 38://39：从上到右，从右到上
            if(dir_from==DIR_DOWN){
            	setDir(DIR_RIGHT);
            	bZhuanwan =true;
				maxRota =-90;
            }
            if(dir_from==DIR_LEFT){
            	setDir(DIR_UP);
            	bZhuanwan =true;
				maxRota =90;
            }
			break;
		case 39://40：从下到右，从上到右
			if(dir_from==DIR_UP){
            	setDir(DIR_RIGHT);
            	bZhuanwan =true;
				maxRota =90;
            }
            if(dir_from==DIR_DOWN){
            	setDir(DIR_RIGHT);
            	bZhuanwan =true;
				maxRota =-90;
            }
			break;
		case 40://41：从上到左，从下到左
			if(dir_from==DIR_DOWN){
            	setDir(DIR_LEFT);
            	bZhuanwan =true;
				maxRota =90;
            }
            if(dir_from==DIR_UP){
            	setDir(DIR_LEFT);
            	bZhuanwan =true;
				maxRota =-90;
            }
			break;
		}
		if(rota>=360||rota<=-360){rota=0;}
		dir_from=dir;
	}	  
		  
		  
	  
//	int runToX;
//	int runToY;
//
//	byte[] adjustX = null;
//	byte[] adjustY = null;
//
//	// boolean isSky; //是否在空中
//
//	int tempx, tempy;
//
//	int[] lastX = new int[11];
//	int[] lastY = new int[11];
//
//	public void setXY(GameRole other) {
//		lastX[0] = x;
//		lastY[0] = y;
//		if (other.roleStatus == STATUS_MOVE || other.roleStatus == STATUS_DECLINE
//				|| other.roleStatus == STATUS_JUMP_UP
//				|| other.roleStatus == STATUS_JUMP_DOWN) {
//			for (int i = 6; i > 0; i--) {
//				other.lastX[i] = other.lastX[i - 1];
//				other.lastY[i] = other.lastY[i - 1];
//			}
//		}
//		isLeft = other.isLeft;0
//	}

	/**
	 * 人物停止时要把人物状态设置为setStatus(STATUS_STOP);并且要要role.go 复制
	 * 因为程序逻辑是在人物不是move状态下如果go=0的话就把人物状态设置为move
	 * 
	 */
	int tingDun;
	public void move() {
		tingDun++;
		if (data == null) {
			initData(type);
		}
		sx = 0;
		sy = 0;
		if (injureTime > 0) {
			injureTime--;
		}
		if(iFrozenTime>0){iFrozenTime--;}
		if(iceTime>0){iceTime--;}
		switch(type){
		case TA_YULEI:
			moveTa(motion);
			sy += y + by;
			sx = x;
			break;
		case TA_LIEYU:
//			moveTa(GameData.motion_lieyuta);
			sy += y + by;
			sx = x;
			break;
		case TA_BINGJING:
			moveTa(motion);
			sy += y + by;
			sx = x;
			break;
		case TA_XIQIAN:
			moveTa(motion);
			sy += y + by;
			sx = x;
			break;
		case TA_HUOPAO:
			moveTa(motion);
			sy += y + by;
			sx = x;
			break;
		case TA_HERO:
			moveTa(motion);
			sy += y + by;
			sx = x;
			break;
		case TYPE_ENEMY_乌鸦:
			roleMove(move_X,0);
			break;
		case TYPE_ENEMY_步兵:
		case TYPE_ENEMY_牧师:
		case TYPE_ENEMY_刺客:
		case TYPE_ENEMY_光头:
		case TYPE_ENEMY_鹰:
		case TYPE_ENEMY_石盾:
		case TYPE_ENEMY_金盾:
		case TYPE_ENEMY_绿盾:
		case TYPE_ENEMY_紫盾:
		case TYPE_ENEMY_暗牧:
		case TYPE_ENEMY_狮鹫:
		case TYPE_ENEMY_黑刺客:
		case TYPE_ENEMY_黑狮鹫:
		case TYPE_ENEMY_步兵BOSS:
		case TYPE_ENEMY_骑兵BOSS:
		case TYPE_ENEMY_大剑BOSS:
		case TYPE_ENEMY_重甲BOSS:

			switch (roleStatus) {
			case STATUS_MOVE:
				if(this.isJianSu>0){
				if(move_X==1||move_X==1){
					if(tingDun%3!=0){
						return;
					}
				}
				}
				switch(dir){
				case DIR_RIGHT://向右
					curindex =10;
					if(type==TYPE_ENEMY_刺客){
						curindex = 28;
					}
					if(type==TYPE_ENEMY_光头){
						curindex = 0;
					}
					if(type==TYPE_ENEMY_鹰){
						curindex = 8;
					}
					if(type==TYPE_ENEMY_石盾||type==TYPE_ENEMY_紫盾||type==TYPE_ENEMY_金盾){
						curindex = 4;
					}
					if(type==TYPE_ENEMY_牧师){
						curindex = 4;
					}
					if(type==TYPE_ENEMY_绿盾){
						curindex = 4;
					}
					isLeft = false;
//					System.out.println("右-----------："+move_X);
					if(GameEngine.time%2==0){
//					roleMove(move_X,-1);
					if(this.isJianSu>0){
						this.isJianSu--;
						if(move_X/2>0){
							roleMove(move_X/2,0);
						}else{
							roleMove(move_X,0);
						}
					}else{
						roleMove(move_X,0);
					}
					}
					break;
				case DIR_LEFT://向左
					isLeft = true;
					curindex =10;
					if(type==TYPE_ENEMY_刺客){
						curindex = 28;
					}
					if(type==TYPE_ENEMY_光头){
						curindex = 0;
					}
					if(type==TYPE_ENEMY_鹰){
						curindex = 8;
					}
					if(type==TYPE_ENEMY_绿盾){
						curindex = 4;
					}
					if(GameEngine.time%2==0){
//					roleMove(-move_X,move_Y);
					if(this.isJianSu>0){
						this.isJianSu--;
						if(move_X/2>0){
							roleMove(-move_X/2,0);
						}else{
							roleMove(-move_X,0);
						}
					}else{
						roleMove(-move_X,0);
					}
					}
					if(type==TYPE_ENEMY_石盾||
							type==TYPE_ENEMY_紫盾||type==TYPE_ENEMY_金盾||type==TYPE_ENEMY_暗牧){
						curindex = 4;
					}
					if(type==TYPE_ENEMY_牧师){
						curindex = 4;
					}
					break;
				case DIR_UP://向上
					if(GameEngine.time%2==0){
						if(this.isJianSu>0){
							this.isJianSu--;
							if(move_Y/2>0){
								roleMove(0,-move_Y/2);
							}else{
								roleMove(0,-move_Y);
							}
						}else{
							roleMove(0,-move_Y);
						}
					}
					break; 
				case DIR_DOWN://向下 
					curindex=0;
					if(type==TYPE_ENEMY_刺客){
						curindex =32;
					}
					if(type==TYPE_ENEMY_光头){
						curindex = 12;
					}
					if(type==TYPE_ENEMY_鹰){
						curindex = 14;
					}
					if(type==TYPE_ENEMY_绿盾){
						curindex = 10;
					}
					if(type==TYPE_ENEMY_石盾||
							type==TYPE_ENEMY_紫盾||type==TYPE_ENEMY_金盾||type==TYPE_ENEMY_暗牧){
						curindex = 14;
					} 
					if(type==TYPE_ENEMY_牧师){
						curindex = 13;
					}
					
					
					if(GameEngine.time%2==0){
						if(this.isJianSu>0){
							this.isJianSu--;
							if(move_Y/2>0){
								roleMove(0,move_Y/2);
							}else{
								roleMove(0,move_Y);
							}
						}else{
							roleMove(0,move_Y);
						}

					}
		
				
					break;
				}
				break;
			case STATUS_DEAD:
				break;
			case STATUS_STOP:
				roleMove(0,0);
				if(type==TYPE_ENEMY_刺客){
					curindex = 28;
				}
				break;
			case STATUS_GONGJI:
				roleMove(0,0);
				break;

			}
			break;
		
		case TYPE_goldFish://黄金鱼自由曲线运动
			switch (roleStatus) {
			case STATUS_MOVE:
				goldFishMove(move_X);
				break;
			case STATUS_STOP:
				break;
			}
			break;
		}
		getBox();
		moveShot();
	}
  int curindex;
  boolean isLeft;
/**
 * 获取敌人血量
 * 公式:x*x+7*x+2
 */
	public void getEneHp(int type,int iWave){
		switch(type){
		case TYPE_ENEMY_步兵:
			if(engine.gameRank<=1){
				iEnemyHp = 5;
			}else{
				iEnemyHp = 10;
			}
			break;
		case TYPE_ENEMY_牧师:
			if(engine.gameRank<=1){
				iEnemyHp = 8;
			}else{
				iEnemyHp = 22;
			}

			break;
		case TYPE_ENEMY_刺客:
			iEnemyHp = 35;
			break;
		case TYPE_ENEMY_光头:
			iEnemyHp = 200;
			break;
		case TYPE_ENEMY_鹰:
			iEnemyHp = 40;
			break;
		case TYPE_ENEMY_石盾:

			if(engine.gameRank<=1){
				iEnemyHp = 10;
			}else{
				iEnemyHp = 65;
			}
			break;
		case TYPE_ENEMY_金盾:
			iEnemyHp = 90;
			break;
		case TYPE_ENEMY_绿盾:
			iEnemyHp = 110;
			break;
		case TYPE_ENEMY_紫盾:
			iEnemyHp = 120;
			break;
		case TYPE_ENEMY_暗牧:
			iEnemyHp = 160;
			break;
		case TYPE_ENEMY_狮鹫:
			if(engine.gameRank<=1){
				iEnemyHp = 12;
			}else{
				iEnemyHp = 150;
			}
			break;
		case TYPE_ENEMY_黑刺客:
			iEnemyHp = 160;
			break;
		case TYPE_ENEMY_黑狮鹫:
			iEnemyHp = 700;
			break;
		case TYPE_ENEMY_步兵BOSS:
			iEnemyHp = 140;
			break;
		case TYPE_ENEMY_骑兵BOSS:
			iEnemyHp = 600;
			break;
		case TYPE_ENEMY_大剑BOSS:
			iEnemyHp = 1000;
			break;
		case TYPE_ENEMY_重甲BOSS:
			iEnemyHp=1600;
			break;
		case TYPE_goldFish:
			iEnemyHp =1300;
			break;
		}
		iEnemyHpNum = iEnemyHp;
	}
/**
 * 获取敌人的速度	
 */
	public void getEneSpeed(int type){
		switch(type){
		case TYPE_ENEMY_乌鸦:
		case TYPE_ENEMY_刺客:
			if(MyActivity.VMHeight<480){
				move_X=4;
				move_Y=4;
			}else{
				move_X=2;
				move_Y=2;	
			}

			break;
			
		case TYPE_ENEMY_狮鹫:
			move_X=2;
			move_Y=2;	
			break;
		case TYPE_ENEMY_黑刺客:

		case TYPE_ENEMY_黑狮鹫:
		case TYPE_ENEMY_鹰:
			if(MyActivity.VMHeight<480){
				if(MyActivity.VMHeight<=240){
					move_X=3;
					move_Y=3;
				}else{
					move_X=3;
					move_Y=3;	
				}
			}else{
				move_X=3;
				move_Y=3;
			}
			break;
		case TYPE_ENEMY_步兵:
		case TYPE_ENEMY_牧师:
		case TYPE_ENEMY_光头:
		case TYPE_ENEMY_石盾:
		case TYPE_ENEMY_金盾:
		case TYPE_ENEMY_绿盾:
		case TYPE_ENEMY_紫盾:
		case TYPE_ENEMY_暗牧:      
		case TYPE_goldFish:
			if(MyActivity.VMHeight<480){
				if(MyActivity.VMHeight<=240){
					move_X=2;
					move_Y=2;
				}else{
					move_X=2;
					move_Y=2;	
				}
			}else{
				move_X=1;
				move_Y=1;
			}
			break;
		case TYPE_ENEMY_步兵BOSS:
		case TYPE_ENEMY_骑兵BOSS:
		case TYPE_ENEMY_大剑BOSS:
		case TYPE_ENEMY_重甲BOSS:
			if(MyActivity.VMHeight<480){
				if(MyActivity.VMHeight<=240){
					move_X=2;
					move_Y=2;
				}else{
					move_X=2;
					move_Y=2;	
				}
			}else{
				move_X=2;
				move_Y=2;
			}
			break;
		}
	}
/**
 * 获取塔的攻击力与	
 * @param type
 * @param level
 */
	void getAttack(int type){
		switch (type) {
		case TA_YULEI:
//			this.level = MyGameCanvas.me.startLevel[2];
			break;
//		case TA_ZENGFU:
//			this.level = MyGameCanvas.me.startLevel[6];
//			break;
		case TA_LIEYU:
//			this.level = MyGameCanvas.me.startLevel[0];
			break;
//		case TA_DIANWANG:
//			attack = startAttack[2][MyGameCanvas.me.startLevel[0]-1];
//			this.level = MyGameCanvas.me.startLevel[2];
//			break;
		case TA_BINGJING:
//			this.level = MyGameCanvas.me.startLevel[1];
			break;
		case TA_XIQIAN://只有3级，对应1000,2000,3000需要分开考虑
//			attack = startAttack[7][MyGameCanvas.me.startLevel[0]-1];
//			this.level = MyGameCanvas.me.startLevel[5];
			break;
		case TA_HUOPAO:
//			this.level = MyGameCanvas.me.startLevel[4];
			break;
		case TA_HERO:
//			this.level = MyGameCanvas.me.startLevel[3];
			break;
		}
		this.level =1;
	}
/**
 * 获取塔的攻击力	
 */
//	public void getAttack(int type,int level){
//		if(level==1){return ;}
//		switch(type){
//		case TA_YULEI:
//			attack*=2;
//			break;
//		case TA_ZENGFU:
//			attack*=2;
//			break;
//		case TA_LIEYU:
//			if(level==2){
//				attack = 4;
//			}else{
//				attack*=2;
//			}
////			iCoolTime = MyGameCanvas.me.iThreadDelay/2;
//			break;
//		case TA_DIANWANG:
//			attack*=2;
//			break;
//		case TA_BINGJING:
//			attack*=2;
//			break;
//		case TA_XIQIAN:
//			attack*=2;
//			break;
//		case TA_HUOPAO://伤害=基础伤害+额外伤害
//			attack*=2;
//			break;
//		case TA_HERO:
//			attack*=2;
//			break;
//		}
//	}
/**
 * 黄金鱼的运动	
 * @param mx
 */
	void goldFishMove(int mx){
		if(iFrozenTime>0){return;}
	    if(iceTime>0){mx-=2;}
	    x+=3;
	    num++;
	}
/**
  * 塔的升级特效		 
	* @param obj
	* @param vec
	* @return
*/
//		public void upleavel(){
//			getAttack(type,level);
//			MyGameCanvas.me.AddBlastEffectList(x-GameEngine.me.iMapX,y-GameEngine.me.iMapY,MyGameCanvas.me.EFF_UPLEVEL,0, 800,0);
//		} 

/**
  * 设置冷却时间	
  * type 哪种东西的冷却时间
  * time 冷却时间多长
  * rank 塔当前的等级
*/
	public void setCoolTime(int type,int rank){
		   switch(type){
		   case TA_LIEYU://猎鱼塔等级提升，速度加快
			  if(rank==1){iCoolTime = 60;}
			  else{iCoolTime = 30;}
			   break;
		   case TA_BINGJING:
//		   case TA_DIANWANG:
		   case TA_YULEI:
		   case TA_HUOPAO:
			   iCoolTime = 120;
			   break;
		   case TA_HERO:
			   iCoolTime = 60;
			   break;
		   case TA_XIQIAN:
//		   case TA_ZENGFU://此2种塔只是增加效果，只要存在，附加的效果始终存在，没有冷却时间
			   iCoolTime = 0;
			   break;
		   case SKILL_STORM:
	
				   iSkillCoolTime[0] = 80;
			   
			   break;
		   case SKILL_FROST:
			   iSkillCoolTime[1] = 80;
			   break;
		   case SKILL_RECOVER://133秒
			   iSkillCoolTime[2] = 80;
			   break;
		   case HOME:
			   if(engine.gameRank!=99){
				   iSkillCoolTime[3] = MyGameCanvas.me.ZBXingXi[engine.canvas.ZB[engine.usBs.iUsBsRank-1]][4];  //老家射击时间间隔 
			   }else{
				   iSkillCoolTime[3] = 20;  //老家射击时间间隔   
			   }
				
                
			   break;
		   }
	}		
	
/*
 * 处理人物移动过程中，触屏时的处理
 * 	
 */
	public void Move_Touch(){

			if(roleStatus==STATUS_MOVE  )
			{
				setStatus(STATUS_JUMP_UP);
			}
			else if((roleStatus==STATUS_JUMP_UP || roleStatus==STATUS_JUMP_DOWN) )//人物跳或落触发二段跳，变身羊驼则只能单跳
			{
				setStatus(STATUS_JUMP_UP2);
			}
			else if(roleStatus==STATUS_JUMP_DOWN4){
				setStatus(STATUS_JUMP_UP);
			}
			else if(roleStatus==STATUS_INJURE_MOVE  )
			{
				setStatus(STATUS_INJURE_JUMP_UP);
			}
			else if((roleStatus==STATUS_INJURE_JUMP_UP || roleStatus==STATUS_INJURE_JUMP_DOWN))//如果人物在跳，触屏则触发二段跳
			{
				setStatus(STATUS_INJURE_JUMP_UP2);
			}
			else if(roleStatus==STATUS_INJURE_JUMP_DOWN4){
				setStatus(STATUS_INJURE_JUMP_UP);
			}
		
	}

	
//
	/*********************************** 主角ai *****************************************************/
	void AI_role(GameInterface role, GameInterface emy, int AttackRange) {
		switch (role.roleStatus) {
		case STATUS_MOVE:
			if (role.x - emy.x < AttackRange) {
				if (GameRandom.isSuccess(30)) {
					setStatus(STATUS_SKILL1);
				} else {
					setStatus(STATUS_ATTACK);
				}
			}
			break;
		default:
			break;
		}
	}

	
	
	public void getBox() {
		lx = rx = 18;
		w = 30;
		bh = 8;
		by = 0;
		ty = 0;
		h = 40;
//		switch(type){
//		case FISH_1:
//		case FISH_2:
//		case FISH_3:
//		case FISH_4:
//		case FISH_5:
//			attackBox = hitArea(data[1], curIndex, false, !isLeft);
//			coxBox = hitArea(data[1], curIndex, false, !isLeft);
//			break;
//		}
		attackBox = hitArea(data[1], curIndex, false, !isLeft);
		coxBox = hitArea(data[1], curIndex, false, !isLeft);
		
//		try {
//			attackBox = hitArea(data[1], curIndex, false, !isLeft);
//			coxBox = hitArea(data[1], curIndex, false, !isLeft);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			Log.e("getbox","getbox"+curIndex+";"+!isLeft+";"+data[1]);
//		}
		
	}

	int enemyMoney() { // 杀死敌人的金钱
		int leav = GameEngine.me.gameRank;
		return GameRandom.result(5 + leav, 10 + leav + leav / 10);
	}

//	public void upleavel(GameRole role, int exp) {
//
//		exp = exp
//				+ exp
//				* MyGameCanvas.menuItem
//						.setGoodsProp(GameMenuItem.Goods_type_战记勋章)
//				% 100
//				+ (GameMenuItem.zhenfaIndex == 4 ? exp
//						* MyGameCanvas.menuItem
//								.setZhengfaEff(GameMenuItem.zhenfaIndex) / 100
//						: 0);
//
//		role.exp += exp;
//		// System.out.println(" role.exp:" + role.exp + "   " + exp + "   " +
//		// role.exp_up);
//		int money = enemyMoney();
//		money = money
//				+ money
//				* MyGameCanvas.menuItem
//						.setGoodsProp(GameMenuItem.Goods_type_黄金戒指)
//				% 100
//				+ (GameMenuItem.zhenfaIndex == 3 ? money
//						* MyGameCanvas.menuItem
//								.setZhengfaEff(GameMenuItem.zhenfaIndex) / 100
//						: 0);
//
//		// if(GameMenuItem.zhenfaIndex==3){
//		// money
//		// }
//
//		GameMenuItem.addMoney(money);
//
//		if (role.exp >= role.exp_up) {
//			initDing();
//			role.level++;
//			role.exp = 0;
//			MyGameCanvas.menuItem.initRoleEquipProp();
//			role.hp = role.hp_max;
//			role.mp = role.mp_max;
//			// if (role.curStatus != role.STATUS_SKILL1
//			// && role.curStatus != role.STATUS_SKILL2
//			// && role.curStatus != role.STATUS_SKILL4
//			// && role.curStatus != role.STATUS_SKILL5 && isRole) {
//			role.setStatus(GameInterface.STATUS_LEVEL_UP);
//		}
//	}

	public int getStatusNum(int curS, short[][] data) {
		if (data == null) {
		
			
			return -1;
		}
		for (int i = 0; i < data.length; i++) {
			if (data[i][0] == curS) {
				return i;
			}
		}
		return -1;
	}

	//	public void upleavel(GameRole role, int exp) {
	//
	//		exp = exp
	//				+ exp
	//				* MyGameCanvas.menuItem
	//						.setGoodsProp(GameMenuItem.Goods_type_战记勋章)
	//				% 100
	//				+ (GameMenuItem.zhenfaIndex == 4 ? exp
	//						* MyGameCanvas.menuItem
	//								.setZhengfaEff(GameMenuItem.zhenfaIndex) / 100
	//						: 0);
	//
	//		role.exp += exp;
	//		// System.out.println(" role.exp:" + role.exp + "   " + exp + "   " +
	//		// role.exp_up);
	//		int money = enemyMoney();
	//		money = money
	//				+ money
	//				* MyGameCanvas.menuItem
	//						.setGoodsProp(GameMenuItem.Goods_type_黄金戒指)
	//				% 100
	//				+ (GameMenuItem.zhenfaIndex == 3 ? money
	//						* MyGameCanvas.menuItem
	//								.setZhengfaEff(GameMenuItem.zhenfaIndex) / 100
	//						: 0);
	//
	//		// if(GameMenuItem.zhenfaIndex==3){
	//		// money
	//		// }
	//
	//		GameMenuItem.addMoney(money);
	//
	//		if (role.exp >= role.exp_up) {
	//			initDing();
	//			role.level++;
	//			role.exp = 0;
	//			MyGameCanvas.menuItem.initRoleEquipProp();
	//			role.hp = role.hp_max;
	//			role.mp = role.mp_max;
	//			// if (role.curStatus != role.STATUS_SKILL1
	//			// && role.curStatus != role.STATUS_SKILL2
	//			// && role.curStatus != role.STATUS_SKILL4
	//			// && role.curStatus != role.STATUS_SKILL5 && isRole) {
	//			role.setStatus(GameInterface.STATUS_LEVEL_UP);
	//		}
	//	}
	
		public void dead() {
			// if (engine.isSuccess(10)) {
			// engine.addItem(x + w / 2, y + 5, 0, 0);
			// }
			// else {
			//
			// }
		}
	public int getStatusNum_dir(int curS, int dir, short[][] data) {//此处dir是个任意参数，也可换成等级之类
		for (int i = 0; i < data.length; i++) {
			if (data[i][0] == curS && data[i][1] == dir) {
				return i;
			}
		}
		return -1;
	}

	public void initDing() {
		// engine.AddBlastEffectList(x, y, engine.EFFECT_SKILL5,
		// isLeft ? 1 : 0, 0, 0);
		engine.dingTime = 7;
		// GameMIDlet.sleepTime = 400;
	}

//	void initSkill() { // 根据快捷键放技能
//		switch (skillIndex) {
//		case 0: // 火，skill1
//			engine.addToVector(x + 195, y - 20, GameEngine.FIRE_SKILL1, isLeft,
//					20, 0, 0, 0, 0, GameEngine.roleShot, y + 1);
//			attack = MyGameCanvas.menuItem
//					.setGoodsProp(GameMenuItem.Goods_type_火焰球);
//
//			break;
//		case 1: // 冰冻
//			engine.addToVector(x + 180, y + 40, GameEngine.FIRE_SKILL4, isLeft,
//					20, 0, 0, 0, 0, GameEngine.roleShot, y + 1);
//			attack = MyGameCanvas.menuItem
//					.setGoodsProp(GameMenuItem.Goods_type_冰冻球);
//
//			break;
//		case 2: // 电 skill2
//			attack = MyGameCanvas.menuItem
//					.setGoodsProp(GameMenuItem.Goods_type_闪电球);
//			int enemyIndex = engine.checkEle();
//			Log.i("skill2", "" + enemyIndex);
//			if (enemyIndex == -1) {
//				engine.addToVector(x + 200, y_pos3 - 25,
//						GameEngine.FIRE_SKILL2, isLeft, 30, 0, 0, 0, 0,
//						GameEngine.roleShot, y + 1);
//			} else {
//				GameRole enemy = (GameRole) engine.enemys.elementAt(enemyIndex);
//				engine.addToVector(enemy.x, y_pos3 - 25,
//						GameEngine.FIRE_SKILL2, isLeft, 30, 0, 0, 0, 0,
//						GameEngine.roleShot, y + 1);
//
//			}
//			break;
//		case 3: // 治疗
//			engine.addToVector(x, y, GameEngine.FIRE_SKILL6, isLeft, 30, 0, 0,
//					0, 0, GameEngine.roleShot, y + 1);
//
//			break;
//
//		case 4: // 老虎 skill3
//			attack = MyGameCanvas.menuItem
//					.setGoodsProp(GameMenuItem.Goods_type_力量球);
//			engine.addToVector(x + 170, y - 10, GameEngine.FIRE_SKILL3, isLeft,
//					70, 0, 0, 0, 0, GameEngine.roleShot, y + 1);
//
//			break;
//		case 5: // 法力转粮食
//			engine.addToVector(x + 15, y - 50, GameEngine.FIRE_SKILL5, isLeft,
//					30, 0, 0, 0, 0, GameEngine.roleShot, y + 1);
//
//			break;
//		}
//
//	}

	public void moveRole(short[][] motion) {//鱼的移动
		int n = getStatusNum(roleStatus, motion);
		if (n == -1) {
			return;
		}
		curIndex = motion[n][2+ index];
		switch (roleStatus) {
		case STATUS_LEVEL_UP:
			break;
		case STATUS_MOVE:
			switch(dir){
			case DIR_RIGHT://向右
			
				roleMove(move_X,0);
				break;
			case DIR_LEFT://向左
				roleMove(-move_X,move_Y);
				break;
			case DIR_UP://向上
				roleMove(0,-move_Y);
				break;
			case DIR_DOWN://向下
				roleMove(0,move_Y);
				break;
			}
			break;
		case STATUS_DEAD:
			break;
		case STATUS_NULL:
			break;
		case STATUS_ATTACK:
			break;
		case STATUS_STOP:
			roleMove(0,0);
			break;
		case STATUS_GONGJI:
			roleMove(0,0);
			break;
		}
	
		index++;
		if (index == motion[n].length - 2) {
			if (motion[n][0] == motion[n][1]) {
				index = 0;
			} else {
				setStatus(motion[n][1]);
			}
		}
	}
	public void moveTa(short[][]motion){//塔的移动,带等级的
		int n=getStatusNum_dir(roleStatus,level,motion);
		if (n == -1) {
			 return;
		}
        curIndex = motion[n][3+ index];
		switch (roleStatus) {
		case STATUS_STOP:
			break;
		case STATUS_ATTACK:
			break;
				
		}
		index++;
		if (index == motion[n].length - 3) {
			if (motion[n][0] == motion[n][2]) {
					index = 0;
			} else {
					setStatus(motion[n][2]);
			}
		}
	}




	void initShot() { // 子弹
//		switch (type) {
//		case TYPE_ROLE_弓箭手:
//			if (index == 5) {
//				engine.addToVector(x, y + 28,
//						curStatus == STATUS_ATTACK ? engine.FIRE_ROLE_弓箭手
//								: engine.FIRE_ROLE_弓箭手_SKILL, isLeft, 20, 0, 0,
//						0, 0, engine.roleShot, y + 1);
//			}
//			break;
//
//		case TYPE_ROLE_术士:
//			if (index == 5) {
//				engine.addToVector(x + 63, y,
//						curStatus == STATUS_ATTACK ? GameEngine.FIRE_ROLE_术士
//								: GameEngine.FIRE_ROLE_术士_SKILL, isLeft, 10, 0,
//						0, 0, 0, GameEngine.roleShot, y + 1);
//			}
//			break;
//		case TYPE_ENEMY_巫师:
//			if (index == 4) {
//				engine.addToVector(x + (isLeft ? -50 : 20), y + 25,
//						GameEngine.FIRE_ENEMY_巫师, isLeft, -10, 0, 0, 0, 0,
//						GameEngine.enemyShot, y + 1);
//			}
//			break;
//
//		case TYPE_ENEMY_刘备:
//			if (index == 13) {
//				engine.addToVector(x + (isLeft ? -50 : 20), y - 40,
//						GameEngine.FIRE_ENEMY_刘备, isLeft, -30, 0, 0, 0, 0,
//						GameEngine.enemyShot, y + 1);
//			}
//
//			if (index == 18) {
//				engine.addToVector(x + (isLeft ? -50 : 20), y - 40,
//						GameEngine.FIRE_ENEMY_刘备, isLeft, -30, 0, 0, 0, 0,
//						GameEngine.enemyShot, y + 1);
//			}
//			break;
//
//		case TYPE_ENEMY_张飞:
//			if (index == 6 && curStatus == STATUS_SKILL1) {
//				engine.addToVector(x + (isLeft ? -200 : 0), y - 40,
//						GameEngine.FIRE_ENEMY_张飞, isLeft, 0, 0, 0, 0, 0,
//						engine.enemyShot, y + 5 + 1);
//			}
//			break;
//
//		case TYPE_ENEMY_弓箭手:
//			if (index == 4) {
//				engine.addToVector(x + (isLeft ? -50 : 20), y + 12,
//						curStatus == STATUS_ATTACK ? GameEngine.FIRE_ENEMY_弓箭手
//								: GameEngine.FIRE_ENEMY_弓箭手_SKILL, isLeft, -15,
//						0, 0, 0, 0, GameEngine.enemyShot, y + 1);
//			}
//			break;
//
//		case TYPE_ENEMY_连弩兵:
//			if (index == 6 || index == 9) {
//				engine.addToVector(x + (isLeft ? -50 : 20), y + 20,
//						curStatus == STATUS_ATTACK ? GameEngine.FIRE_ENEMY_弓箭手
//								: GameEngine.FIRE_ENEMY_弓箭手_SKILL, isLeft, -15,
//						0, 0, 0, 0, GameEngine.enemyShot, y + 1);
//			}
//			break;
//
//		case TYPE_ENEMY_关羽:
//			if (index == 6 && curStatus == STATUS_ATTACK) {
//				GameEngine.AddBlastEffectList(x - 40, y_pos3,
//						GameEngine.EFFECT_关羽召唤烟雾, 0, y, 0);
//				GameEngine.AddBlastEffectList(x - 40, y_pos1,
//						GameEngine.EFFECT_关羽召唤烟雾, 0, y, 0);
//			}
//
//			if (curStatus == STATUS_SKILL1) {
//				if (index == 6) {
//					GameEngine.AddBlastEffectList(x - 40, y_pos3,
//							GameEngine.EFFECT_关羽召唤烟雾, 0, y, 0);
//				}
//
//				if (index == 10) {
//					GameEngine.AddBlastEffectList(x + 30, y_pos1,
//							GameEngine.EFFECT_关羽召唤烟雾, 0, y, 0);
//
//				}
//				//
//				if (curStatus == STATUS_SKILL1 && index == 15) {
//					GameEngine.AddBlastEffectList(x - 40, y_pos1,
//							GameEngine.EFFECT_关羽召唤烟雾, 0, y, 0);
//
//				}
//				if (curStatus == STATUS_SKILL1 && index == 20) {
//					GameEngine.AddBlastEffectList(x + 30, y_pos3,
//							GameEngine.EFFECT_关羽召唤烟雾, 0, y, 0);
//				}
//
//			}
//
//			break;
//
//		case TYPE_ENEMY_赵云:
//			if (index == 12 || (curStatus == STATUS_SKILL1 && index == 18)
//					|| (curStatus == STATUS_SKILL1 && index == 23)) {
//				int spriteIndex = engine.checkBoom();
//				if (spriteIndex == -1) {
//
//				} else if (spriteIndex == -2) {
//					engine.addToVector(
//							x + (isLeft ? -46 : 0),
//							y - 30,
//							engine.FIRE_ENEMY_赵云,
//							isLeft,
//							0,
//							-25,
//							GameRandom.result(engine.role.x - 5, engine.role.x + 5),
//							engine.role.y + 50, 0, engine.enemyShot, y + 1);
//				} else {
//					GameRole sprite = (GameRole) engine.sprites
//							.elementAt(spriteIndex);
//					engine.addToVector(x + (isLeft ? -46 : 0), y + 10,
//							engine.FIRE_ENEMY_赵云, isLeft, 0, -50,
//							GameRandom.result(sprite.x - 5, sprite.x + 5),
//							sprite.y + 50, 0, engine.enemyShot, y + 1);
//				}
//			}
//			break;
//
//		case TYPE_ENEMY_孙坚:
//			if (index == 2 || (curStatus == STATUS_SKILL1 && index == 5)
//					|| (curStatus == STATUS_SKILL1 && index == 8)) {
//				engine.addToVector(x + (isLeft ? -50 : 20), y,
//						engine.FIRE_ENEMY_孙坚, isLeft, -30, 0, 0, 0, 0,
//						engine.enemyShot, y + 1);
//
//			}
//
//			break;
//
//		}
	}

	public void drawLifeBarBoss(int x, int y) {
		// GameDraw.addObject(PAK_IMAGES.IMG_BOSSB_PNG, x + 8, y + 4,
		// 0, 0, 155 * hp / hp_max, 5,
		// Tools.TOP,
		// Tools.TRANS_NONE,
		// 1001);
		// GameDraw.addObject(PAK_IMAGES.IMG_BOSSB_PNG, x, y,
		// 0, 5, 171, 13,
		// Tools.TOP,
		// Tools.TRANS_NONE,
		// 1000);

	}

//	public void drawLifeBar(int x, int y) {
//		if (type == TYPE_ENEMY_老家) {
//			return;
//		}
//
//		GameDraw.add_Image(PAK_IMAGES.IMG_MENU26, x, y, 0, 6, 39, 9,
//				Tools.TOP_LEFT, Tools.TRANS_NONE, y);
//
//		GameDraw.add_Image(PAK_IMAGES.IMG_MENU26, x + 2, y + 2, 2, 16, 35
//				* hp / hp_max, 5, Tools.TOP_LEFT, Tools.TRANS_NONE, y);
//		int enemyicon = 0;
//		switch (type) {
//		default:
//			return;
//		}
//	}

	int temp;

	// boolean isAttack;
	// int maxWaitTime = 20;
	// int waitTime;
	// int runToX;
	// int runToY;

	public void drawHitArea() {
		// GameDraw.addObject(Tools.TYPE_RECT, sx + attackBox[0], sy
		// + attackBox[1] + RoadLine, attackBox[2], attackBox[3], false,
		// Tools.BOTTOM_LEFT, 0xffff0000, 100);
		//
		// GameDraw.addObject(Tools.TYPE_RECT, sx + coxBox[0], sy + coxBox[1]
		// + RoadLine, coxBox[2], coxBox[3], false, Tools.BOTTOM_LEFT,
		// 0xffffff00, 100);
	}


	

/**
 * 画地图上的塔以及敌人	
 */
	int roleSt = 6;
void chekRoleSt(GameRole role){  //根据人物的状态改变帧数
	
    if(MyGameCanvas.gameStatus!= MyGameCanvas.GmStat_Playing){
    	return;
    }
	if(this.gongJi_count>0){
		this.gongJi_count--;
	}
	if(role.roleStatus==STATUS_MOVE){
		roleSt = 5;
	}
	if(role.roleStatus==STATUS_GONGJI){
		engine.count = 10;
		role.gongJi_Time++;
		if(role.gongJi_Time%40==0){
			if(MyGameCanvas.jiNengKaiQi[4]>GameRandom.result(0, 100)){
				//老家闪避 MyGameCanvas.jiNengKaiQi[4]
				chekFanTan(role); //反弹
			}else{
				
		switch (role.type) {
			case TYPE_ENEMY_步兵:
			case TYPE_ENEMY_刺客:
				countLaoJiaHP(1);
//			    engine.laoJiaHP	--;
				break;
			case TYPE_ENEMY_牧师:
			case TYPE_ENEMY_狮鹫:
				countLaoJiaHP(2);
//				engine.laoJiaHP	-=2;
				break;
			case TYPE_ENEMY_光头:
				countLaoJiaHP(3);
//				engine.laoJiaHP	-=3;
				break;
			}
		chekFanTan(role); //反弹
			}

			laoJiaShouShang=true;
			if(engine.laoJiaHP<=0){
//				MyGameCanvas.setST(MyGameCanvas.GmStat_dengdai_lose);
//				MyGameCanvas.setST(MyGameCanvas.GmStat_Lose);
			}
		}
		
		roleSt = 4;
		curindex = 6;
		if(role.dir==DIR_RIGHT||role.dir==DIR_LEFT){
			curindex = 15;
		}
		
		
		

	}
	
	if(role.type ==TYPE_ENEMY_刺客){
		roleSt = 4;
		if(role.roleStatus==STATUS_GONGJI){
			roleSt = 3;
			curindex = 25;
			if(role.dir==DIR_RIGHT||role.dir==DIR_LEFT){
				curindex = 22;
			}
		}
	}
	if(role.type ==TYPE_ENEMY_光头){
		roleSt = 4;
		if(role.roleStatus==STATUS_GONGJI){

			curindex = 8;
			if(role.dir==DIR_RIGHT||role.dir==DIR_LEFT){
				curindex = 4;
			}
		}
	}
	if(role.type ==TYPE_ENEMY_牧师){
		roleSt = 5;
		if(role.roleStatus==STATUS_GONGJI){
			curindex = 9;
			if(role.dir==DIR_RIGHT||role.dir==DIR_LEFT){
				curindex = 0;
			}
		}
	}

	
	

}
void countLaoJiaHP(int HP){
	if(!engine.usBs.laoJiaWuDi){
	    engine.laoJiaHP-=HP;
	}

}


void chekFanTan(GameRole role){
	if(MyGameCanvas.jiNengKaiQi[5]>GameRandom.result(0, 100)){ //反弹
		role.iEnemyHp-=20;
		if(role.iEnemyHp<0){
			MyActivity.instance._mView.waf.StartWav(13, false);
			GameEngine.me.role.DropGold(GameEngine.me.iWave,role.x,role.y,role.type);
			GameEngine.me.usBs.iUsBsCuJinbi += engine.bullm.checkJinBi(role.type);
			GameEngine.me.usBs.rankMoney += engine.bullm.checkJinBi(role.type);
			role.setStatus(role.STATUS_DEAD);
				engine.bullm.addEffect(100,role.x,role.y,role.type);
				for(int i=0;i<GameEngine.me.enemys.size();i++){
		    		GameRole enemy = (GameRole)GameEngine.me.enemys.elementAt(i);
		    		if(enemy.iEnemyHp<0){
		    			GameEngine.me.enemys.remove(i);
		    		}
				}
		}
	}
}

void chekYing(GameRole role){  //根据人物的状态改变帧数
	
    if(MyGameCanvas.gameStatus!= MyGameCanvas.GmStat_Playing){
    	return;
    }
	if(this.gongJi_count>0){
		this.gongJi_count--;
	}
	if(role.roleStatus==STATUS_MOVE){
		roleSt = 6;
	}
	if(role.roleStatus==STATUS_GONGJI){
		engine.count = 10;
		role.gongJi_Time++;
		if(role.gongJi_Time%40==0){
			if(MyGameCanvas.jiNengKaiQi[4]>GameRandom.result(0, 100)){
				//老家闪避
				
			}else{
				countLaoJiaHP(1);
//			    engine.laoJiaHP	--;
			laoJiaShouShang=true;
			chekFanTan(role);
			}
			
			
			if(engine.laoJiaHP<=0){
//				MyGameCanvas.setST(MyGameCanvas.GmStat_Lose);
			}
		}
		
		roleSt = 4;
		curindex = 4;
		if(role.dir==DIR_RIGHT||role.dir==DIR_LEFT){
			curindex = 0;
		}

	}
}
void chekRoleSt_2(GameRole role){  //根据人物的状态改变帧数
	
    if(MyGameCanvas.gameStatus!= MyGameCanvas.GmStat_Playing){
    	return;
    }
	if(this.gongJi_count>0){
		this.gongJi_count--;
	}
	if(role.roleStatus==STATUS_MOVE){
		roleSt = 6;
	}
	if(role.roleStatus==STATUS_GONGJI){
		engine.count = 10;
		role.gongJi_Time++;
		if(role.gongJi_Time%40==0){
			if(MyGameCanvas.jiNengKaiQi[4]>GameRandom.result(0, 100)){
				//老家闪避
				
			}else{
			switch (role.type) {
			case TYPE_ENEMY_石盾:
			case TYPE_ENEMY_绿盾:
				countLaoJiaHP(4);
//			    engine.laoJiaHP	-=4;
				break;
			case TYPE_ENEMY_紫盾:
				countLaoJiaHP(5);
//				engine.laoJiaHP	-=5;
				break;
			case TYPE_ENEMY_金盾:
				countLaoJiaHP(6);
//				engine.laoJiaHP	-=6;
			case TYPE_ENEMY_暗牧:
				countLaoJiaHP(8);
//				engine.laoJiaHP	-=8;
				break;
			}
			chekFanTan(role); //反弹
			}
			laoJiaShouShang=true;
			if(engine.laoJiaHP<=0){
//				MyGameCanvas.setST(MyGameCanvas.GmStat_Lose);
			}
		}
		
		roleSt = 4;
		curindex = 0;
		if(role.dir==DIR_RIGHT||role.dir==DIR_LEFT){
			curindex = 16;
		}
		if(role.type == TYPE_ENEMY_金盾){
			curindex = 10;
			if(role.dir==DIR_RIGHT||role.dir==DIR_LEFT){
				curindex = 0;
			}
		}
		if(role.type ==TYPE_ENEMY_石盾){
			roleSt = 4;
			if(role.roleStatus==STATUS_GONGJI){
				curindex = 10;
				if(role.dir==DIR_RIGHT||role.dir==DIR_LEFT){
					curindex = 0;
				}
			}
		}
		if(role.type ==TYPE_ENEMY_绿盾){
			roleSt = 4;
			if(role.roleStatus==STATUS_GONGJI){
				curindex = 16;
				if(role.dir==DIR_RIGHT||role.dir==DIR_LEFT){
					curindex = 0;
				}
			}
		}
		if(role.type ==TYPE_ENEMY_紫盾){
			roleSt = 4;
			if(role.roleStatus==STATUS_GONGJI){
				curindex = 21;
				if(fishData.isrank2==25){
					curindex = 26;
				}
//				if(role.dir==DIR_RIGHT||role.dir==DIR_LEFT){
//					curindex = 0;
//				}
			}
		}
		

	}
}

    public void drawTSGuaiWu(GameRole role){

		if(this.TeShuGuaiWu==0){
			return;
		}
    	engine.canvas.checkShan2(role);
		switch (this.TeShuGuaiWu) {
		case 1:
//			GameDraw.add_Image(PAK_IMAGES.IMG_JINENGBUFF,role.x,role.y-70,0,0,49,43,
//			Tools.HCENTER_VCENTER,Tools.TRANS_NONE,50+y);
//			System.out.println("role.shan : "+role.shan);
			GameDraw.add_ImageAlpha(PAK_IMAGES.IMG_JINENGBUFF,role.x,role.y-70,0,0,45,43,Tools.HCENTER_VCENTER,
					Tools.TRANS_NONE, 50+y,255-role.shan);//发光框子
			break;
		case 2:
//			GameDraw.add_Image(PAK_IMAGES.IMG_JINENGBUFF,role.x,role.y-70,49,0,49,43,
//			Tools.HCENTER_VCENTER,Tools.TRANS_NONE,50+y);
			GameDraw.add_ImageAlpha(PAK_IMAGES.IMG_JINENGBUFF,role.x,role.y-70,45,0,45,43,Tools.HCENTER_VCENTER,
					Tools.TRANS_NONE, 50+y,255-role.shan);//发光框子
			break;
		case 3:
//			GameDraw.add_Image(PAK_IMAGES.IMG_JINENGBUFF,role.x,role.y-70,98,0,49,43,
//			Tools.HCENTER_VCENTER,Tools.TRANS_NONE,50+y);
			GameDraw.add_ImageAlpha(PAK_IMAGES.IMG_JINENGBUFF,role.x,role.y-70,90,0,45,43,Tools.HCENTER_VCENTER,
					Tools.TRANS_NONE, 50+y,255-role.shan);//发光框子
			break;
		case 4:
//			GameDraw.add_Image(PAK_IMAGES.IMG_JINENGBUFF,role.x,role.y-70,147,0,49,43,
//			Tools.HCENTER_VCENTER,Tools.TRANS_NONE,50+y);
			GameDraw.add_ImageAlpha(PAK_IMAGES.IMG_JINENGBUFF,role.x,role.y-70,135,0,45,43,Tools.HCENTER_VCENTER,
					Tools.TRANS_NONE, 50+y,255-role.shan);//发光框子
			break;
		case 5:
//			GameDraw.add_Image(PAK_IMAGES.IMG_JINENGBUFF,role.x,role.y-70,196,0,49,43,
//			Tools.HCENTER_VCENTER,Tools.TRANS_NONE,50+y);
			GameDraw.add_ImageAlpha(PAK_IMAGES.IMG_JINENGBUFF,role.x,role.y-70,180,0,45,43,Tools.HCENTER_VCENTER,
					Tools.TRANS_NONE, 50+y,255-role.shan);//发光框子
			break;

		}
		
		
		
    }
static boolean laoJiaShouShang;
	public void paint() {
//		getBox();
		if (curIndex == -1) {return;}
//		drawShot();
		if (roleStatus == STATUS_DEAD) {return;}
		if(iFrozenTime>0){drawFrozen(type,x,y);}
		else{enemyDelay++;}
//		drawHuopao();
//	      if(this.x>=150&&this.y>=230&&this.x<=310){
//          	this.setStatus(this.STATUS_GONGJI);
//          	laoJiaShouShang=true;
//          }
//		chekRoleSt(this);
		drawTSGuaiWu(this);

		
		
		
		switch(type){
		case TYPE_ENEMY_乌鸦:
			
			break;
		case TYPE_ENEMY_步兵://水平镜像,x,y值未变,但图片却右移了一个方框单位
			chekRoleSt(this);
			if(this.gongJi_count>0){
				this.gongJi_count--;
			}
			 GameDraw.renderAnimPic22(PAK_IMAGES.IMG_BUBING1,curindex+enemyDelay/10%roleSt,x,
					y+20,data,(isLeft ? true : false),false,50+y,0,0,0);
			if(MyActivity.VMHeight>240){
				
			}else{
				
			}
		    break;
		case TYPE_ENEMY_牧师: 
			chekRoleSt(this);
				GameDraw.renderAnimPic22(PAK_IMAGES.IMG_MUSHI1,curindex+enemyDelay/10%roleSt,x,
						y+20,MyGameCanvas.data_MAOZI,(isLeft ? true : false),false,50+y,0,0,0);
			break;
		case TYPE_ENEMY_刺客:
				chekRoleSt(this);
				     GameDraw.renderAnimPic22(PAK_IMAGES.IMG_SHENSISHULV,curindex+enemyDelay/12%roleSt,x,
								y+20,data,(isLeft ? true : false),false,50+y,0,0,0);
			break;
		case TYPE_ENEMY_光头:
			chekRoleSt(this);
				GameDraw.renderAnimPic22(PAK_IMAGES.IMG_SHENSISHULV,curindex+enemyDelay/10%roleSt,x,
						y+20,data,(isLeft ? true : false),false,50+y,0,0,0);
			break;
		case TYPE_ENEMY_鹰:
			chekYing(this);
				GameDraw.renderAnimPic22(PAK_IMAGES.IMG_YING1,curindex+enemyDelay/10%roleSt,x,
						y+20,data,(isLeft ? true : false),false,50+y,0,0,0);
			break;
		case TYPE_ENEMY_石盾: 
			chekRoleSt_2(this);
				GameDraw.renderAnimPic22(PAK_IMAGES.IMG_SHIDUN1,curindex+enemyDelay/10%roleSt,x,
						y+20,data,(isLeft ? true : false),false,50+y,0,0,0);
			break;
		case TYPE_ENEMY_金盾:
			chekRoleSt_2(this);
				GameDraw.renderAnimPic22(PAK_IMAGES.IMG_JINDUN1,curindex+enemyDelay/10%roleSt,x,
						y+20,data,(isLeft ? true : false),false,50+y,0,0,0);
			break;
		case TYPE_ENEMY_绿盾:
			chekRoleSt_2(this);
				GameDraw.renderAnimPic22(PAK_IMAGES.IMG_LVDUN1,curindex+enemyDelay/10%roleSt,x,
						y+20,data,(isLeft ? true : false),false,50+y,0,0,0);
			break;
		case TYPE_ENEMY_紫盾:
			chekRoleSt_2(this);
				GameDraw.renderAnimPic22(PAK_IMAGES.IMG_ZIDUN1,curindex+enemyDelay/10%roleSt,x,
						y+20,data,(isLeft ? true : false),false,50+y,0,0,0);
                     engine.PTenemyDelay = enemyDelay;
			break;	
		case TYPE_ENEMY_暗牧:
			chekRoleSt_2(this);
//				GameDraw.renderAnimPic22(PAK_IMAGES.IMG_JINDUN2,curindex+enemyDelay/10%roleSt,x,
//						y+20,data,(isLeft ? true : false),false,50,0,0,0);
			break;
		case TYPE_ENEMY_狮鹫:
			chekRoleSt(this);
			GameDraw.renderAnimPic22(PAK_IMAGES.IMG_GUIXIANREN,curindex+enemyDelay/10%roleSt,x,
					y+20,data,(isLeft ? true : false),false,50,0,0,0);
			break;
		case TYPE_ENEMY_黑刺客:
			break;
		case TYPE_ENEMY_黑狮鹫:
			break;
		case TYPE_ENEMY_步兵BOSS:
			break;
		case TYPE_ENEMY_骑兵BOSS:
			break;
		case TYPE_ENEMY_大剑BOSS:
			break;
		case TYPE_ENEMY_重甲BOSS:
			break;
		case TYPE_goldFish:
			break;
				
		}
		
}

/**
 * 画怪物身上的火焰特效	
 */
	public void drawHuopao(){
		if(bHuopao==false){return;}
		int zidanxiaoguo5[][]={{0,2,30,54},{33,2,27,56},{62,0,28,62},{92,1,27,56}};
//		GameDraw.add_Image(PAK_IMAGES.IMG_HUOPAOXIAOGUO,x-5-GameEngine.me.iMapX,y-15-GameEngine.me.iMapY,
//				zidanxiaoguo5[MyGameCanvas.me.gameTime/2%4],Tools.HCENTER_VCENTER, Tools.TRANS_NONE,119);
				
	}
/**
 * 画增幅塔身上的特效
 * @param x
 * @param x
 */
	void drawAmplification(int x,int y){
//		int zengfu[][]={{16,14,208,209},{254,13,146,147},{249,188,103,103}};
//		int motion[]={0,0,0,1,1,1,2,2,2};
//		GameDraw.add_Image(PAK_IMAGES.IMG_ZENGFUXIAOGUO,x,y,zengfu[motion[MyGameCanvas.me.gameTime/2%8]],
//				Tools.HCENTER_VCENTER,Tools.TRANS_NONE,119);
	}

/**
 * 	 敌兵血条
 *  ( (x+1)*(x+1)+7*(x+1)+2 )*y=60
 *=>y=60/( (x+1)*(x+1)+7*(x+1)+2 )
 * @param x
 * @param y
 * @param length 怪物图片对应的长度
 */
	//1.步兵2.牧师3.刺客4.光头5.鹰6.石盾7.金盾8.绿盾9.紫盾10.暗牧11.狮鹫
	public static int iEneHpLength[];
	
	void drawShow(int x,int y,int length){
		switch(type){
		case TYPE_ENEMY_重甲BOSS:
			length+=30;
			GameDraw.add_ImageRota(PAK_IMAGES.IMG_LAOJIAXUETIAO,x-25-(rota==-90?25:0),y-50,20,0,
					(int)(iEnemyHp*((float)length/1600)),
					10,Tools.TOP_LEFT,Tools.TRANS_NONE,51,0);
			break;
		case TYPE_ENEMY_大剑BOSS:
			length+=25;
			GameDraw.add_ImageRota(PAK_IMAGES.IMG_LAOJIAXUETIAO,x-45-(rota==-90?10:0),y-50,20,0,
					(int)(iEnemyHp*((float)length/1000)),
							10,Tools.TOP_LEFT,Tools.TRANS_NONE,51,0);
			break;
		case TYPE_ENEMY_骑兵BOSS:
			length+=30;
			GameDraw.add_ImageRota(PAK_IMAGES.IMG_LAOJIAXUETIAO,x-45-(rota==-90?20:0),y-50,20,0,
					(int)(iEnemyHp*((float)length/600)),
							10,Tools.TOP_LEFT,Tools.TRANS_NONE,51,0);
			break;
		case TYPE_ENEMY_步兵BOSS:
			if(iEnemyHp<40){
						GameDraw.add_ImageRota(PAK_IMAGES.IMG_LAOJIAXUETIAO,x-42+(rota==90?15:0),y-50,20,0,
					(int)(iEnemyHp*((float)length/
							140)),10,Tools.TOP_LEFT,Tools.TRANS_NONE,51,0);	
			}

			break;
		case TYPE_goldFish:
			break;
//		case TYPE_ENEMY_步兵:
//			GameDraw.add_ImageRota(PAK_IMAGES.IMG_LAOJIAXUETIAO,x-30-(rota==-90?10:0),y-50,20,0,
//					(int)(iEnemyHp*(((float)length)/20)),10,Tools.TOP_LEFT,Tools.TRANS_NONE,51,0);
//			break;
//		case TYPE_ENEMY_牧师:
//			GameDraw.add_ImageRota(PAK_IMAGES.IMG_LAOJIAXUETIAO,x-30-(rota==-90?10:0),y-50,20,0,
//					(int)(iEnemyHp*(((float)length)/50)),10,Tools.TOP_LEFT,Tools.TRANS_NONE,51,0);
//			break;
//		case TYPE_ENEMY_刺客:
//			GameDraw.add_ImageRota(PAK_IMAGES.IMG_LAOJIAXUETIAO,x-30-(rota==-90?10:0),y-50,20,0,
//					(int)(iEnemyHp*(((float)length)/80)),10,Tools.TOP_LEFT,Tools.TRANS_NONE,51,0);
//			break;
//		default:
//			GameDraw.add_ImageRota(PAK_IMAGES.IMG_LAOJIAXUETIAO,x-30-(rota==-90?10:0),y-50,20,0,
//					(int)(iEnemyHp*((float)length/(
//							(10+5*(GameEngine.me.gameRank/8))*(iWaveHp+1)))),10,Tools.TOP_LEFT,Tools.TRANS_NONE,51,0);
			default:
				if(iEnemyHp<iEneHpLength[type-20]){
					if(type==TYPE_ENEMY_光头){
						GameDraw.add_ImageRota(PAK_IMAGES.IMG_LAOJIAXUETIAO,x-20-(rota==-90?10:0),y-100,20,0,
						(int)(iEnemyHp*(((float)length)/iEneHpLength[type-20])),10,Tools.TOP_LEFT,Tools.TRANS_NONE,51+y,0);
					}else{
						
						GameDraw.add_ImageRota(PAK_IMAGES.IMG_LAOJIAXUETIAO,x-20-(rota==-90?10:0),y-50,20,0,
								(int)(iEnemyHp*(((float)length)/iEneHpLength[type-20])),10,Tools.TOP_LEFT,Tools.TRANS_NONE,51+y,0);
					}
					
				}
				
		}
//		if(type==TYPE_ENEMY_重甲BOSS||type==TYPE_ENEMY_大剑BOSS
//				||type==TYPE_ENEMY_步兵BOSS||type==TYPE_ENEMY_骑兵BOSS){
//		}10+5*(x)   10*(wave+1)*(x)
//		if(rota==270||rota==-90){
//			GameDraw.add_ImageRota(PAK_IMAGES.IMG_LAOJIAXUETIAO,x,y-40,20,0,
//					(iEnemyHp*length)/((iWaveHp+1)*(iWaveHp+1)+
//							7*(iWaveHp+1)+2),10,Tools.HCENTER_VCENTER,Tools.TRANS_NONE,50,0);
//		}else{
//			GameDraw.add_ImageRota(PAK_IMAGES.IMG_LAOJIAXUETIAO,x-4,y-40,20,0,
//					(iEnemyHp*length)/((iWaveHp+1)*(iWaveHp+1)+
//							7*(iWaveHp+1)+2),10,Tools.HCENTER_VCENTER,Tools.TRANS_NONE,50,0);
//		}

		
		
//		GameDraw.add_ImageRota(PAK_IMAGES.IMG_LAOJIAXUETIAO,x-30-(rota==-90?10:0),y-50,20,0,
//				(int)(iEnemyHp*((float)length/((iWaveHp+1)*(iWaveHp+1)+
//						7*(iWaveHp+1)+2))),10,Tools.TOP_LEFT,Tools.TRANS_NONE,51,0);
	 }
/**
 * 画怪物被冻住效果
 * @param x
 * @param y
 */
	public void drawFrozen(int type,int x,int y){
		enemyDelay = 0;//让怪物动画听下来
		if(type==TYPE_ENEMY_步兵BOSS || type==TYPE_ENEMY_骑兵BOSS || type==TYPE_ENEMY_大剑BOSS
			|| type==TYPE_ENEMY_重甲BOSS){
//			GameDraw.add_Image(PAK_IMAGES.IMG_BINGDONGDA,x,y-10,Tools.HCENTER_VCENTER,
//					Tools.TRANS_NONE,51);
		}else{
//			GameDraw.add_Image(PAK_IMAGES.IMG_BINGDONGXIAO,x,y-10,Tools.HCENTER_VCENTER,
//					Tools.TRANS_NONE,51);
		}
	}
	public void drawUnLaw(){
		switch(type){
		case UnLawBuildBig:
//			GameDraw.add_Image(PAK_IMAGES.IMG_SHUIJINGDA,x,y,Tools.HCENTER_VCENTER,Tools.TRANS_NONE,49);
			break;
		case UnLawBuildSmall:
//			GameDraw.add_Image(PAK_IMAGES.IMG_SHUIJINGXIAO,x,y,Tools.HCENTER_VCENTER,Tools.TRANS_NONE,49);
			break;
		}
	}
/**
 * 画违建的血条
 * @param x 
 * @param y	违建的坐标位置
 * 200--->50
 * 400--->90
 */
	public void unLawHp(int x,int y){
		switch(type){
		case UnLawBuildBig:
			GameDraw.add_ImageRota(PAK_IMAGES.IMG_LAOJIAXUETIAO,x,y-80,20,0,
					(int)(0.225*iEnemyHp),10,Tools.HCENTER_VCENTER,Tools.TRANS_NONE,50+y,0);
			break;
		case UnLawBuildSmall:
			GameDraw.add_ImageRota(PAK_IMAGES.IMG_LAOJIAXUETIAO,x,y-80,20,0,
					(int)(0.25*iEnemyHp),10,Tools.HCENTER_VCENTER,Tools.TRANS_NONE,50+y,0);
			break;
		}
	}
//	int getAngel(int x1,int y1,int x2,int y2){//获得x1的射击角度，      x1，y1为瞄准物体，x2，y2为被瞄准物体
//	 return	getA((y1-y2),-(x1-x2));	
//	}
//	
//	
//	int getA(int x,int y){
//		int angele=0;
//		if (x == 0) {
//			if (y > 0) {
//				return 90;
//			} else {
//				return 270;
//			}
//		} else {
//			 angele=(int) (Math.atan2(x,y)*180/(Math.PI));
//			if (x > 0 && y >= 0) {
////				GameDraw.addObject(Tools.TYPE_STRING, "aaaaaaa:"+angele, Tools.setOffX+ 400
////						, Tools.setOffY +200, Tools.HCENTER_TOP, 0xffC036C7, 1000);	
//				return angele;
//			} else if (x > 0 && y < 0) {
////				GameDraw.addObject(Tools.TYPE_STRING, "bbbbbbb:"+angele, Tools.setOffX+ 400
////						, Tools.setOffY +200, Tools.HCENTER_TOP, 0xffC036C7, 1000);	
//				return angele;
//				
//			} else if (x < 0 && y >= 0) {
////				GameDraw.addObject(Tools.TYPE_STRING, "ccccccc:"+angele, Tools.setOffX+ 400
////						, Tools.setOffY +200, Tools.HCENTER_TOP, 0xffC036C7, 1000);	
//				return 360+ angele;
//				
//				
//			} else {
////				GameDraw.addObject(Tools.TYPE_STRING, "dddddd:"+angele, Tools.setOffX+ 400
////						, Tools.setOffY +200, Tools.HCENTER_TOP, 0xffC036C7, 1000);	
//				
//				return 360 + angele;
//
//			}
//		}
//	}
//	
//	
//
//	int eleTime;
//	int woodTime;
//	void drawWood(int x, int y, int level) {}
//	int boomTime;
//	int skillIndex = 0;
//	byte attackhit;
//	final byte attackIndex1 = 5;
//	final byte attackIndex2 = 11;
//	final byte attackIndex3 = 17;
//	final byte attackMax = 34;
//	int m_index;
//	
//	public void maxAttack() {
//		if (index <= attackIndex1) {
//			attackhit = attackIndex1;
//		} else if (index <= attackIndex2) {
//			attackhit = attackIndex2;
//		} else if (index <= attackIndex3) {
//			attackhit = attackIndex3;
//		} else {
//			attackhit = attackMax;
//		}
//	}
//
//	boolean isdead = false; // 下落时跌死

	public void setStatus(int status) {
		
		roleStatus = status;
		index = 0;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	public void setFaceDir(byte dir) {
		faceDir = dir;
	}
	public void setType(byte  type) {
		if (this.type != type) {
			index = 0;
			initData(type);
		}
		this.type = type;
	}
/******************************************************************************************/
	/**
	 * 添加塔的箭矢
	 * @param x
	 * @param y
	 * @param type
	 * @param dir
	 * @param speedX
	 * @param speedY
	 * @param w
	 * @param attack
	 * @param index
	 */
	
//		public void addToVector(int x, int y, int type, int dir, int speedX,
//				int speedY, int w, int attack, int index) {
//			int[] temp = new int[11];
//			temp[0] = x;
//			temp[1] = y;
//			temp[2] = type;
//			temp[3] = dir;
//			temp[4] = speedX;
//			temp[5] = speedY;
//			temp[6] = w;
//			temp[7] = attack;
//			temp[8] = index;
//			temp[9] = 0;
//			temp[10] = 0;
//			shot.addElement(temp);
//		}
/**		
 * 重写添加子弹类
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
 * @param j 敌人类型
 */
		Vector<int[]> shot = new Vector<int[]>();
		public void addBullet(int x, int y, int type, int dir, int speed,
				int targetX, int targetY, int attack, int index,int i,int j) {
			int[] temp = new int[13];
			temp[0] = x;
			temp[1] = y;
			temp[2] = type;
			temp[3] = dir;
			temp[4] = speed;
			temp[5] = targetX;
			temp[6] = targetY;
			temp[7] = attack;
			temp[8] = index;
			temp[9] = 0;
			temp[10] = 0;
			temp[11] = i;
			temp[12] = j;
			shot.addElement(temp);
		}	
		public void drawShot() {
			byte[] motion = null;
			int size = shot.size();
			for (int i = size - 1; i >= 0; i--) {
				int[] temp = (int[]) shot.elementAt(i);
				switch (temp[2]) {
				case Bullet_lieyu:
//	    				GameDraw.add_ImageRota(PAK_IMAGES.IMG_TA4ZIDAN, temp[0]-GameEngine.me.iMapX, temp[1]-GameEngine.me.iMapY,
//								Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 118,temp[3]);
					break;
				case Bullet_bingjing:
//					GameDraw.add_ImageRota(PAK_IMAGES.IMG_BINGJINGPAOZIDAN,temp[0]-GameEngine.me.iMapX,temp[1]-GameEngine.me.iMapY,
//							Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 118,temp[3]);
					break;
				case Bullet_dianwang:
//					GameDraw.add_ImageRota(PAK_IMAGES.IMG_TA1ZIDAN,temp[0]-GameEngine.me.iMapX,temp[1]-GameEngine.me.iMapY,
//							Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 118,temp[3]);
					break;
				case Bullet_yulei:
//					GameDraw.add_ImageRota(PAK_IMAGES.IMG_TA8ZIDAN,temp[0]-GameEngine.me.iMapX,temp[1]-GameEngine.me.iMapY,
//							Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 118,temp[3]);
					break;
				case Bullet_hero:
//					GameDraw.add_ImageRota(PAK_IMAGES.IMG_TA7ZIDAN,temp[0]-GameEngine.me.iMapX,temp[1]-GameEngine.me.iMapY,
//							Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 118,temp[3]);
					break;
				case Bullet_huopao:
//					GameDraw.add_ImageRota(PAK_IMAGES.IMG_TA5ZIDAN,temp[0]-GameEngine.me.iMapX,temp[1]-GameEngine.me.iMapY,
//							Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 118,temp[3]);
					break;
				case Bullet_xiqian://吸钱和增幅塔特殊，只有在顶端滑动的动画,没有子弹
//					GameDraw.add_ImageRota(PAK_IMAGES.IMG_TA5ZIDAN,temp[0]-GameEngine.me.iMapX,temp[1]-GameEngine.me.iMapY,
//							Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 200,temp[3]);
					break;
				case Bullet_zengfu:
//					GameDraw.add_ImageRota(PAK_IMAGES.IMG_TA5ZIDAN,temp[0]-GameEngine.me.iMapX,temp[1]-GameEngine.me.iMapY,
//							Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 200,temp[3]);
					break;
//				case GameRole.Bullet_home:
//					GameDraw.add_ImageRota(PAK_IMAGES.IMG_LAOJIA1ZIDAN,temp[0]-GameEngine.me.iMapX,temp[1]-GameEngine.me.iMapY,
//							Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 200,temp[3]);
//					break;
				}
			}
		}
	/**
	 * 1 箭矢未击中怪物且锁定的怪物没死
	 * 2 箭矢未击中怪物，但锁定的怪物已死
	 * 3 箭矢进入怪物被攻击范围且怪物没死
	 * 4 箭矢进入怪物被攻击范围但怪物已死	
	 * @param vec
	 */
		int x1,y1,r;
		public void moveShot() {
			int len = shot.size();
			for (int i=len-1;i>=0;i--) {
				int[] temp = (int[]) shot.elementAt(i);
				switch (temp[2]) {
				case GameRole.Bullet_lieyu://coxBox -25,8,40,16
				case GameRole.Bullet_bingjing:
				case GameRole.Bullet_hero:
				case GameRole.Bullet_huopao:
				case GameRole.Bullet_yulei:
//				case GameRole.Bullet_xiqian:
//				case GameRole.Bullet_zengfu:
					x1 = x-5;y1 = y;
					if(!BulletManager.bInCircle(temp[0],temp[1],x1,y1,39)){
						//此处半径大小是有讲究的,小了会出错
						int distance = BulletManager.GetDistance(x1,y1,temp[0],temp[1]);
						temp[0] += ((x1 - temp[0]) *GameEngine.Speed / distance);
						temp[1] += ((y1 - temp[1]) *GameEngine.Speed / distance);
					}else{//记得子弹爆炸动画效果要覆盖住子弹
						temp[0] =x1;
						temp[1] = y1;
						if(iEnemyHp<0){//死亡不爆效果
							if(temp[2]==Bullet_huopao&&bHuopao==false){bHuopao=true;}
							typeAttack(temp[2],temp[7]);
							drawBulletEff(temp[2],temp[0],temp[1]);
							if(iEnemyHp<=0){
								setStatus(STATUS_DEAD);
								bHuopao=false;
								DropGold(GameEngine.me.iWave,temp[0],temp[1],temp[12]);//小怪根据波数爆金币
								
								
//								if(temp[12]==TYPE_goldFish){
//								MyGameCanvas.me.iGold+=30;
//									MyGameCanvas.AddBlastEffectList(temp[0],temp[1],MyGameCanvas.me.EFF_GOLDFISHDEAD,0,200,0);
//		        				}
							}
						}else{bHuopao=false;}
						shot.removeElementAt(i);
					}
					break;
				}
			}
		}
/**
 * 计算怪物伤害值
 * @param type
 * @param attack
 */
		int iRndom;
		public void typeAttack(int type,int attack){
			if(this.type==TYPE_goldFish)return;
		         iEnemyHp-=attack;
			switch(type){
			case Bullet_bingjing://必减速，几率产生冻住
				iceTime = 30;
				iRndom = GameRandom.result(0,10);
				break;
			case Bullet_huopao://持续伤害
				break;
			case Bullet_lieyu:
				break;
			case Bullet_hero://群攻
				break;
			}
		}
/**
 * 	
 * @param type
 * @param x
 * @param y
 */
	public void drawBulletEff(int type,int x,int y) {//画子弹爆炸效果
		switch(type){
		case GameRole.Bullet_lieyu:
		case GameRole.Bullet_yulei:
			MyGameCanvas.AddBlastEffectList(x-GameEngine.me.iMapX,y-GameEngine.me.iMapY,
					MyGameCanvas.me.EFF_ZIDAN2,0,119,0);
			break;
		case GameRole.Bullet_bingjing:
			MyGameCanvas.AddBlastEffectList(x-GameEngine.me.iMapX,y-GameEngine.me.iMapY,
					MyGameCanvas.me.EFF_ZIDAN3,0,119,0);
			break;
		case GameRole.Bullet_dianwang:
			MyGameCanvas.AddBlastEffectList(x-GameEngine.me.iMapX,y-GameEngine.me.iMapY,
					MyGameCanvas.me.EFF_ZIDAN4,0,119,0);
			break;
		case GameRole.Bullet_hero:
			MyGameCanvas.AddBlastEffectList(x-GameEngine.me.iMapX,y-GameEngine.me.iMapY,
					MyGameCanvas.me.EFF_ZIDAN1,0,119,0);
			break;
		case GameRole.Bullet_huopao://火炮的特效跟着怪物走
			break;
		case GameRole.Bullet_xiqian:
		case GameRole.Bullet_zengfu:
			break;
		}

	}
/**
 * 根据波数计算小怪爆的金币	
 * @param wave
 * @param beilv在吸钱塔和卷折的增幅下，小怪爆金币增多
 * @param dropGold此参数每关结束后需重置为0
 * dropJewel
 */
    public void DropGold(int wave,int x,int y,int type){
//    	leaveGold(wave);
    	dropGold=3;
    	calcuateJewel(wave,MyGameCanvas.me.gmStatus,x,y);
//    	GameEngine.me.usBs.iUsBsCuJinbi+=dropGold;
    	drawDrop(x,y,dropGold,type);
//    	GameEngine.me.ps.run(1,0,x,y,0,0);
    }
/**
 * 小怪被打死后爆金币    
 * @param wave
 */
    void leaveGold(int wave){
    	if(GameEngine.me.iFreakMoney==1){iBeishu=2;}
    	else{iBeishu=1;}
    	switch(wave/5){
    	case 0:
    		dropGold =1*iBeishu;
    		break;
    	case 1:
    		dropGold =2*iBeishu;
    		break;
    	case 2:
    		dropGold =3*iBeishu;
    		break;
    		default:
    			dropGold =4*iBeishu;
    	}
    }
/**
 * 计算怪物掉宝石
 * @param status
 * 	@param beilv
 * 吸钱塔范围内必爆宝石	
 */
    int iSuijiNum=100;
    int iBeishu=1;//双倍倍数
    void calcuateJewel(int iWaveHp,int status,int x,int y){
    	iSuijiNum = GameRandom.result(0,20);
    	switch(status){
    	case MyGameCanvas.gmFight:
    		switch(GameEngine.me.iFightGameRank){
    		case 0:
    			if(iSuijiNum<=(7+(GameEngine.me.iFreakMoney==1?12:0))){//40%掉宝石,10%掉双倍
    				if(iSuijiNum<=1){iBeishu=2;}
    				else{
    					if(iWaveHp<=4){dropJewel=(1*iBeishu);iJewelNum=0;}
        				else if(iWaveHp<=9){dropJewel=(2*iBeishu);iJewelNum=1;}
        				else if(iWaveHp<=14){dropJewel=(3*iBeishu);iJewelNum=2;}
        				else if(iWaveHp<=19){dropJewel=(4*iBeishu);iJewelNum=3;}
        				else if(iWaveHp>=20){dropJewel=(5*iBeishu);iJewelNum=4;}
    				}
    			}
    			break;
    		case 1:
    			if(iSuijiNum<=5+(GameEngine.me.iFreakMoney==1?14:0)){//30%掉宝石,10%掉双倍
    				if(iSuijiNum<=1){iBeishu=2;}
    				else{
    					if(iWaveHp<=4){dropJewel=(2*iBeishu);iJewelNum=1;}
        				else if(iWaveHp<=9){dropJewel=(4*iBeishu);iJewelNum=3;}
        				else if(iWaveHp<=14){dropJewel=(6*iBeishu);iJewelNum=5;}
        				else if(iWaveHp<=19){dropJewel=(8*iBeishu);iJewelNum=6;}
        				else if(iWaveHp>=20){dropJewel=(10*iBeishu);iJewelNum=8;}
    				}
    			}
    			break;
    		case 2:
    			if(iSuijiNum<=3+(GameEngine.me.iFreakMoney==1?16:0)){//20%掉宝石,5%掉双倍
    				if(iSuijiNum==0){iBeishu=2;}
    				if(iWaveHp<=4){dropJewel=(3*iBeishu);iJewelNum=2;}
    				else if(iWaveHp<=9){dropJewel=(6*iBeishu);iJewelNum=5;}
    				else if(iWaveHp<=14){dropJewel=(9*iBeishu);iJewelNum=7;}
    				else if(iWaveHp<=19){dropJewel=(12*iBeishu);iJewelNum=9;}
    				else if(iWaveHp>=20){dropJewel=(15*iBeishu);iJewelNum=11;}
    			}
    			break;
    		}
    		break;
    	case MyGameCanvas.gmScripe:
    		switch(GameEngine.me.gameRank/8){
    		case 0:
    			if(iSuijiNum<=7+(GameEngine.me.iFreakMoney==1?12:0)){//40%掉宝石,10%掉双倍
    				if(iSuijiNum<=1){iBeishu=2;}
    				else{
    					if(iWaveHp<=4){dropJewel=(1*iBeishu);iJewelNum=0;}
        				else if(iWaveHp<=9){dropJewel=(2*iBeishu);iJewelNum=1;}
        				else if(iWaveHp<=14){dropJewel=(3*iBeishu);iJewelNum=2;}
        				else if(iWaveHp<=19){dropJewel=(4*iBeishu);iJewelNum=3;}
        				else if(iWaveHp>=20){dropJewel=(5*iBeishu);iJewelNum=4;}
    				}
    			}
    			break;
    		case 1:
    			if(iSuijiNum<=5+(GameEngine.me.iFreakMoney==1?14:0)){//30%掉宝石,10%掉双倍
    				if(iSuijiNum<=1){iBeishu=2;}
    				if(iWaveHp<=4){dropJewel=(2*iBeishu);iJewelNum=1;}
    				else if(iWaveHp<=9){dropJewel=(4*iBeishu);iJewelNum=3;}
    				else if(iWaveHp<=14){dropJewel=(6*iBeishu);iJewelNum=5;}
    				else if(iWaveHp<=19){dropJewel=(8*iBeishu);iJewelNum=6;}
    				else if(iWaveHp>=20){dropJewel=(10*iBeishu);iJewelNum=8;}
    			}
    			break;
    		case 2:
    			if(iSuijiNum<=3+(GameEngine.me.iFreakMoney==1?16:0)){//20%掉宝石,5%掉双倍
    				if(iSuijiNum==0){iBeishu=2;}
    				if(iWaveHp<=4){dropJewel=(3*iBeishu);iJewelNum=2;}
    				else if(iWaveHp<=9){dropJewel=(6*iBeishu);iJewelNum=5;}
    				else if(iWaveHp<=14){dropJewel=(9*iBeishu);iJewelNum=7;}
    				else if(iWaveHp<=19){dropJewel=(12*iBeishu);iJewelNum=9;}
    				else if(iWaveHp>=20){dropJewel=(15*iBeishu);iJewelNum=11;}
    			}
    			break;
    		}
    		break;
    	}
    	if(dropJewel>0){
    		MyGameCanvas.AddBlastEffectList(x, y,MyGameCanvas.me.EFF_LEAVEGOLD,0,120,0);//
        	MyGameCanvas.me.iGold+=dropJewel;
        	dropJewel = 0;
        	iJewelNum = 0;
        	MyGameCanvas.me.saveGame();
    	}
    	GameEngine.me.iFreakMoney = 0;
    	iBeishu = 1;
    	iSuijiNum = 100;
    	
    }
   
/**
 * 怪物掉落金币的运动轨迹
 * @param x
 * @param y
 * @param num爆的金币数,每3个金币爆一次效果
 */
    int dropDelay;
    public void drawDrop(int x,int y,int num,int type){
    	MyGameCanvas.AddBlastEffectList(x,y,MyGameCanvas.me.EFF_DROPGOLD,0,121,type);
    }

}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

