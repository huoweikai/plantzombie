package com.haopu.JSGame;


import cn.cmgame.billing.api.GameInterface;
import cn.cmgame.billing.api.GameInterface.GameExitCallback;

import com.haopu.kbz.*;
import com.haopu.pak.PAK_BIN;
import com.haopu.pak.PAK_IMAGES;

import java.io.*;
import java.net.Socket;
import java.security.spec.MGF1ParameterSpec;
import java.util.*;
import java.util.logging.Level;
import java.util.regex.PatternSyntaxException;

import com.haopu.Enemy.FISH;
import com.haopu.Enemy.UsBoss;
import com.haopu.Enemy.fishData;

import PaowuxianTexiao.Paowuxiantexiao;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.SurfaceHolder.Callback;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class MyGameCanvas {
	// 屏幕尺寸

	public static float zooming = 1.0f;// 放大倍数
	public static float zoomingX = 1.0f;// 放大倍数
	public static float zoomingY = 1.0f;// 放大倍数
	public static Vector<int[]> EffectV = new Vector<int[]>(); // donghua效果集合
	public static Vector<GameRole> EffectV2 = new Vector<GameRole>(); // donghua效果集合
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 480;
	// 定义一系列的动画效果
	public static final byte EFFECT_1 = 0;// 塔的升级,造塔特效
	public static final byte EFFECT_2 = 1;// 人物灰尘效果
	public static final byte EFFECT_3 = 2;// 人物碰到石块会碎效果
	public static final byte EFF_ZIDAN1 = 60;// 子弹1特效
	public static final byte EFF_ZIDAN2 = 61;// 子弹4特效
	public static final byte EFF_ZIDAN3 = 62;// 子弹5特效
	public static final byte EFF_ZIDAN4 = 63;// 子弹6特效
	public static final byte EFF_ZIDAN5 = 64;// 子弹7特效
	public static final byte EFF_ZIDAN6 = 65;// 子弹8特效
	public static final byte EFF_DROPGOLD = 66;// 小怪爆金币效果
	public static final byte EFF_HUANPAO = 67;// 老家换炮效果
	public static final byte EFF_GONGXIHUODE = 68;
	public static final byte EFF_SAHUA = 69;// 撒花效果
	public static final byte EFF_START = 70;// 教学结束开始游戏
	public static final byte EFF_GETACHIEVE = 71;// 获取成就时候的效果
	public static final byte EFF_SKILL_SHANDIAN = 72;// 技能闪电效果
	public static final byte EFF_SKILL_SHUANGDONG = 73;// 技能霜冻效果
	public static final byte EFF_GOLDFISHDEAD = 74;// 黄金鱼死亡效果
	public static final byte EFF_LAOJIA1ZIDAN = 75;// 老家子弹1爆炸效果
	public static final byte EFF_LAOJIA2ZIDAN = 76;
	public static final byte EFF_LAOJIA3ZIDAN = 77;
	public static final byte EFF_LAOJIA4ZIDAN = 98;
	public static final byte EFF_LAOJIA5ZIDAN = 99;
	public static final byte EFF_死亡 = -19;
	public static final byte EFF_眩晕 = -18;
	public static final byte EFF_停顿 = -17;
	public static final byte EFF_爆炸 = -16;
	public static final byte EFF_UNLAWDEADBIG = 79;// 大违建死亡效果
	public static final byte EFF_UNLAWDEADSMALL = 80;// 小违建死亡效果
	public static final byte EFF_FISHDIS = 81;// 与进入老家自动死亡,爆消失效果
	public static final byte EFF_NOTOPEN = 83;// 挑战模式尚未开启
	public static final byte EFF_TOWERBUILD = 84;// 造塔特效
	public static final byte EFF_SHANDIANDIS = 85;// 闪电技能离屏时候逐渐放大模糊的效果
	public static final byte EFFECT_YULAN = 86;
	public static final byte EFF_WAVETISHI = 87;// 波数提示
	public static final byte EFF_ZAOTA_NOT = 88;
	public static final byte EFF_UPLEVEL = 89;// 造塔,升级塔，卖塔时候的闪光
	public static final byte EFF_LEAVEGOLD = 90;

	// 造塔后，赋予地图的属性
	public static final byte PROP_LIEYU = 91;
	public static final byte PROP_BINGJING = 92;
	public static final byte PROP_DIANWANG = 93;
	public static final byte PROP_YULEI = 94;
	public static final byte PROP_HERO = 95;
	public static final byte PROP_HUOPAO = 96;
	public static final byte PROP_ZENGFU = 97;
	public static final byte PROP_XIQIAN = 98;
	public static final byte EFF_Rank99 = 3;
	boolean bTouchMoving = false;// 判断是否触屏移动了
	int iProp = 0;// 获取地图第几块

	// 各种状态
	public final static int GmStat_Menu = 100;// 主菜单
	protected final static int GmStat_Help = 101;// 帮助
	protected final static int GmStat_About = 102;// 关于
	protected final static int GmStat_Option = 103;// 设置音乐,音效状态
	protected final static int GmStat_More = 104;// 更多游戏
	protected final static int GmStat_Quit = 105;// 退出
	protected final static int GmStat_Load = 106;// 加载界面
	protected final static int GmStat_CatchChoose = 107;// 场景选择界面
	protected final static int GmStat_OpenLoading = 108;// 开机加载界面
	protected final static int GmStat_Playing = 109;// 游戏中
	protected final static int GmStat_Stop = 110;// 暂停
	protected final static int GmStat_Shop = 111;// 商店
	protected final static int GmStat_Lose = 112;// 失败
	protected final static int GmStat_Win = 113;// 成功
	protected final static int GmStat_TA_BUILD = 114;// 游戏中点击屏幕，进入造塔状态
	protected final static int GmStat_Register = 130;// 签到
	protected final static int GmStat_TowerChoose = 134;// 选塔界面
	protected final static int GmStat_MidMenu = 135;// zhongtucaidan
	protected final static int GmStat_OpenAnima = 136;// 加载后,主菜单前的动画
	protected final static int GmStat_Nanduxuanze = 117;// nandu xuanze
	protected final static int GmStat_Achieve = 118;// 成就界面
	protected final static int GmStat_GameOver = 119;
	protected final static int GmStat_Manhua = 120;// 播放漫画的状�?
	protected final static int GmStat_Tiaozhan = 121;// 挑战状�?
	protected final static int GmStat_Pingjia = 125;// 画评价界面
	public final static int GmStat_Scripe = 127;// 剧情
	public final static int GmStat_Teaching = 128;// 是否进行教学界面
	public final static int GmStat_KONG = 129;
	public final static int GmStat_DUMIAO = 160;
	public final static int GmStat_GAMBLE = 131;// 抽奖
	public final static int GmStat_TEACHING = 132;// 后面几部的教学
	public final static int GmStat_TEACHSHOP = 133;
	public final static int GmStat_GOLDNOT = 161;// 宝石数量不足的界面
	public final static int GmStat_SELLUP = 162;// 塔的升级或者出售
	public final static int GmStat_ZhuJiao = 163;// 换主角
	public final static int GmStat_ShenJi = 164;// 升级技能
	public final static int GmStat_RankMap = 165;// 升级技能
	public final static int GmStat_RankJX = 166;// JX
	public final static int GmStat_dengdai_win = 167;// JX等待胜利
	public final static int GmStat_dengdai_lose = 168;// JX等待失败
	protected final static int GmStat_Load2 = 169;// 加载界面2
	protected final static int GmStat_jiangLi = 170;// 询问玩家是否进入奖励关卡
	protected final static int GmStat_ChouJiang = 171;// 抽奖
	protected final static int GmStat_qiandao = 172;// 抽奖
	protected final static int GmStat_sms = 173;// 抽奖
	public final static int GmStat_RankJX2 = 174;// JX
	public final static int GmStat_TSGTiShi = 175;// 限制怪物 种类和数量
	public final static int GmStat_QieHuan = 176;// 限制怪物 种类和数量
	public final static int GmStat_YDSms = 177;// 移动计费点让游戏停止
	public final static int GmStat_YDSms2 = 178;// 移动计费点让游戏停止
	public final static int GmStat_YuanZiDan = 179;// 第一关教学掉落的原子弹
	public final static int GmStat_DianXin = 180;
	// 造塔时候需要的一些参数
	public final static int TA_造塔 = 150;// 点击屏幕，弹出造塔界面
	public final static int TA_卖或升级 = 151;// 点击屏幕，弹出卖塔界面
	static int TA_STATUS = TA_造塔; // 初始默认为造塔
	static int TA_LASTSTATUS = TA_造塔;//
	// 判断处于剧情关卡还是自由关卡模式
	public final static int gmScripe = 200;// 剧情模式
	public final static int gmFight = 201;// 挑战模式
	public static int gmStatus = gmScripe;// 默认剧情模式
	static int lastStatus = -2; // 上个状态
	public static Context context;
//	static int gameStatus = GmStat_OpenLoading; // 定义游戏初始状态
	static int gameStatus = GmStat_DianXin;
	// static int gameStatus =GmStat_Shop;
	// 触屏时候，手指落点及离开点
	int iTouchDownX, iTouchDownY;
	static// 触屏时候，手指落点及离开点
	int iTouchUpX;
	static int iTouchUpY;
	int TowerType;// 拖动所需造塔的类型
	// 商店中用到的一系列参数
	int iShopNum;// 用于标示商店左上角的3个选项
	int iGold = 500;// 宝石的数量,商城的购买货币,初始1000
	int iTowerChoose;// 用于判断商城炮塔下选中的哪一个炮塔

	int iAchieveStartY;// 成就界面的滑屏
	boolean bShovel = false;// 是否选中了铲子
	boolean bPropShow;// 拖动造塔的时候，显示属性块
	boolean bHuangquanquan;
	int TowerChooseType;// 选塔界面被选中的塔的类型
	int iTowerType;// 被选中的要造的塔的类型
	// 定義2個變量,用來判斷幫助介面與設置介面返回位置
	int iWhere[] = new int[] { 0, 0 };
	boolean bFirstEnter = false;// 判断是否第一次进入游戏
	// 游戏中用到的一些索引
	static int index = 0;
	static int m_index = 0;
	public static int gameTime = 0;
	float fTaScale;// 卖或升级界面，塔被选中后的包围圈
	int alpha = 255;// 图片的透明度
	// 一些列需要new的对象
	public static GameEngine engine;
	DataFast df;
	PackageTools pps;
	FISH fish;
	public static MyGameCanvas me;
	boolean isComputer;
	static GameRandom gr;
	static GmPlay gp;
	static SurfaceView gameView;

	static short[][] data_TYPE_ENEMY_步兵;// 怪物1
	static short[][] data_TYPE_ENEMY_牧师;// 怪物2
	static short[][] data_TYPE_ENEMY_刺客;// 怪物3
	static short[][] data_TYPE_ENEMY_光头;// 怪物4
	static short[][] data_TYPE_ENEMY_鹰;// 怪物5
	static short[][] data_TYPE_ENEMY_石盾;// 怪物5
	static short[][] data_TYPE_ENEMY_金盾;// 怪物5
	static short[][] data_TYPE_ENEMY_绿盾;// 怪物5
	static short[][] data_TYPE_ENEMY_紫盾;// 怪物5
	static short[][] data_TYPE_ENEMY_暗牧;// 怪物5
	static short[][] data_TYPE_ENEMY_狮鹫;// 怪物5
	static short[][] data_TYPE_ENEMY_黑刺客;// 怪物5
	static short[][] data_TYPE_ENEMY_黑狮鹫;// 怪物5
	static short[][] data_huoqiuTX;// 火球特效
	static short[][] data_siwang;// 死亡
	static short[][] data_xihongshibaozha;// 西红柿爆炸
	static short[][] data_role_dog;// 胖子和狗
	static short[][] data_SANDAN;// 胖子和狗
	static short[][] data_MAOZI;//
	static short[][] data_YUANZIDAN;//
	static short[][] data_JIANSU;//
	static short[][] data_BAOZHA;//
	static short[][] data_WUYA;//
	static short[][] data_jijia;//
	static short[][] data_GUANCAI;//
	int iTolLevel;// 大地图总共关卡
	int iTolLevel_xiao;// 用于标示小关卡

	// 游戏中时间领奖
	public final int totalSecond = 45 * 60;// 总秒数
	public int leftSecond = totalSecond;
	boolean bToTower;// 是否点击地图上存在塔的区域
	PaintFlagsDrawFilter paintFlagsDrawFilter;
	Gift gift;
//    SMS_dianxing sms;
	ChinaMM mm;
	public MyGameCanvas(SurfaceView gameview, Context context) {
		me = this;
		this.context = context;
		zooming = 1;
		zoomingX=(float)SCREEN_WIDTH/(float)480;
		zoomingY=(float)SCREEN_HEIGHT/(float)320;
		
		 //小图（480）
		 if ( MyActivity.VMWidth >= 800 ) {
			 zooming=1;
			 zoomingX=1;//放大倍数
			 zoomingY=1;//放大倍数
		 } else {
		 // zooming=0.5f;
		 if (MyActivity.VMWidth < 480) {
			 zooming=0;
			 }
			 zoomingX = 0.6f;// 放大倍数
			 zoomingY = 320/480f;// 放大倍数
		 }
		 
		 if (MyActivity.VMWidth == 480) {
			 zooming=1;
			 zoomingX = 1f;// 放大倍数
			 zoomingY = 1f;// 放大倍数
			 }
		 
		gift = new Gift(context);  
		gift.init(); 
		// getScale(fishGame.VMWidth);
		pps = new PackageTools(context);
		df = new DataFast(context, pps);
		fish = new FISH();
		gameView = gameview;
		gr = new GameRandom();
		Tools.setWH(SCREEN_WIDTH, SCREEN_HEIGHT, 4000, PAK_IMAGES.FILESNAME,
				gameview, MyActivity.VMWidth, MyActivity.VMHeight);

		Tools.alphaColor = new int[] { 0x00ffffff, 0x00ff0000, 0x00F87D7D,
				0x000000ff };
		engine = new GameEngine();
		paintFlagsDrawFilter = new PaintFlagsDrawFilter(0,
				Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
		beishuX = (float) SCREEN_HEIGHT / (float) MyActivity.VMHeight;
		beishuY = (float) SCREEN_WIDTH / (float) MyActivity.VMWidth;
		//initJPGData();
		loadGame();
		for (int i = 0; i < ZB.length; i++) {
			JLZB[i] = ZB[i];
		}
		
		BillingResult.loadRMS();
	}

	/******************************************************************************/
	static int lastStatus2;

	public static void setST(int st) {

		index = 0;
		m_index = 0;
		if (lastStatus == GmStat_RankJX2) {
			Tools.removeImage(PAK_IMAGES.IMG_Z10);
			Tools.removeImage(PAK_IMAGES.IMG_M1);
		}
		if (lastStatus == GmStat_RankJX) {
			Tools.removeImage(PAK_IMAGES.IMG_M2);
			Tools.removeImage(PAK_IMAGES.IMG_M3);
		}
		lastStatus = gameStatus;
	

		if (st == GmStat_Load) {
			Tools.removeAllImage();
			
			engine.iRanTieshi = GameRandom.result(0, 8);
		}

		gameStatus = st;
		if (st == GmStat_dengdai_lose) {
			Tools.removeAllImage();
		}
		if (st == GmStat_Load) {
			Tools.removeAllImage();
		}
		if (st == GmStat_ZhuJiao) {
			Tools.removeAllImage();
		}
		if (st == GmStat_ShenJi) {
			Tools.removeAllImage();
		}

		for (int i = 0; i < engine.TSRabk.length; i++) {
			if (engine.TSRabk[i] == 0) {
				pointMenus = -1;
				TSpointMenus = i;
				return;
			}
		}
		if (st == GmStat_RankMap) {
			Tools.removeAllImage();
			int index = 0;
			int index2 = -1;
			MyGameView.rankMove = 0;
			for (int i = 0; i < engine.iResult.length; i++) {
				for (int j = 0; j < engine.iResult[i].length; j++) {
					if (engine.iResult[i][j] != -1 && j != 0) {
						index++;
					}
				}
				if (engine.iResult[i][0] != -1) {
					index2++;
				}
				me.leve[i] = 5;
			}
			me.leve[index2] = 6;// 修改此处
			MyGameView.rankMove -= index2 * 67;
			pointMenus = index - index2 * 5;
			TSpointMenus = -1;
			if (pointMenus >= 5) {
				pointMenus = 5;
			}
		}

	}

	/**
	 * 获取比例因子
	 * 
	 */
	void getScale(int scal) {
		if (scal >= 800) {
			zooming = 1.0f;
		} else {
			zooming = 0.5f;
		}
	}

	String[] midStr = { "返回游戏", "游戏帮助", "关于", "返回菜单" };
	int MidMenudisY = 10;

	// 清屏
	public static void drawCleanScreen(int c) {// 白屏0xffffffff
		GameDraw.add_Rect(Tools.setOffX, Tools.setOffY, SCREEN_WIDTH,
				SCREEN_HEIGHT, true, Tools.TOP_LEFT, c, 1);

	}

	private void drawLogo(int type) {

	}

	byte upIndex = 0;

	protected final static byte ST_TELEPHONE = 50;

	public static byte[] shiYongKaPian = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0 };
	public static int countShiYong = -1;
	public static int piBaoX;
	public static int piBaoY;
	public static int diaoLuoY;
	public static int diaoLuoX;
	public static int diaoLuoSpeedY;
	int menuX = Tools.setOffX + (SCREEN_WIDTH - 176) / 2;
	int menuY = Tools.setOffY + 50;

	boolean isDark;
	int contKP;
	public boolean is;
	public static int smscont;

	public void paint() {
		if (Tools.size > 4000000 && is) {
			is = false;
			Tools.removeAllImage();
		}
		
		if (contKP != 0) {
			contKP--;
			if (contKP == 1) {
				for (int i = 0; i < ZB.length; i++) { // 0,5,-1,-1,-1,6,-1,7
					ZB[i] = JLZB[i]; 

				}
//				if(GameEngine.usBs.iUsBsRank==engine.pOp){
					GameEngine.usBs.iUsBsRank = 1;
					if (GameEngine.usBs.sheSuJiaBei) {
						GameEngine.Speed = (ZBXingXi[ZB[GameEngine.usBs.iUsBsRank - 1]][8]) * 2;
					} else {
						GameEngine.Speed = ZBXingXi[ZB[GameEngine.usBs.iUsBsRank - 1]][8];
					}

					GameEngine.usBs.iUsBsCurAttack = ZBXingXi[ZB[engine.usBs.iUsBsRank - 1]][3];
				
//				}
//				engine.usBs.iUsBsRank = 1;
		}
		}
		// System.out.println("roleNumber : "+engine.roleNumber);
		gameTime++;
		switch (gameStatus) {
		case GmStat_DianXin:
			drawCleanScreen(0xff000000);
			drawDXOpen();
			break;
		case GmStat_YuanZiDan:
			engine.drawGame(true);
			//drawYuanZiDan();
			break;
		case GmStat_YDSms2:
			if(lastStatus==GmStat_Playing){
				engine.drawGame(true);	
			}

			drawYDSms();
			break;
		case GmStat_YDSms:
			engine.drawGame(true);
			drawYDSms();
			break;
		case GmStat_QieHuan:
			engine.drawGame(true);
			QieHuan();
			break;
		case GmStat_TSGTiShi:
			engine.drawGame(true);
			TSGTiShi();
			break;
		case GmStat_RankJX2:
			engine.rank0++;
			engine.drawGame(true);
			drawRankJX2();
			break;
		case GmStat_sms:
			drawSms();
			break;
		case GmStat_qiandao:
			gift.drawImg();
			drawCleanScreen(0);
			menuLogic();
			drawMenubg();
			drawEffect();
			break;
		case GmStat_ChouJiang:
			engine.drawGame(true);
			break;
		case GmStat_jiangLi:
			// drawdengdai_Win();
			drawJiangLi();
			break;
		case GmStat_Load2:
			drawLoad2();
			break;
		case GmStat_RankJX:
			drawCleanScreen(0);
			engine.drawGame(true);
			drawJX();
			break;
		case GmStat_RankMap:
			drawCleanScreen(0);
			// System.out.println("rankMove : "+MyGameView.rankMove);
			drawBrank(gmStatus, pointDRank);
			drawSrank(gmStatus, pointMenus);
			drawArank(gmStatus, pointMenuo);
			break;
		case GmStat_OpenLoading:
			drawCleanScreen(0xff000000);
			LoadingLogic();
			drawOpenLoading();
			break;
		case GmStat_ZhuJiao:
			drawCleanScreen(0);
			drawShop_ZB();
			break;
		case GmStat_ShenJi:
			drawCleanScreen(0);
			drawShop_2();
			break;
		case GmStat_OpenAnima:
			drawCleanScreen(0);
			// OpenAniLogic();
			// drawOpenAni();

			break;
		case GmStat_Menu://
			drawCleanScreen(0);
			menuLogic();
			drawMenubg();
			drawEffect();
			// drawTest();
			break;
		case GmStat_Option:
			drawCleanScreen(0);
			drawOption();
			break;
		case GmStat_Achieve:
			drawCleanScreen(0);
			drawAchieve();
			break;
		case GmStat_Register:
			drawCleanScreen(0);
			break;
		case GmStat_Quit:
			drawCleanScreen(0);
			drawQiut();
			break;
		case GmStat_Teaching:
			engine.map.setMap(true, true);
			engine.DrawBackground(gmStatus);
			drawEverTeach();
			break;
		case GmStat_Stop:
			drawCleanScreen(0);
			engine.drawGame(true);
			drawStop();
			break;
		case GmStat_CatchChoose:
			drawCleanScreen(0);
			upDownShake(GmStat_CatchChoose);
			//catchLogic();
			drawCatch(gmStatus);
			openTeachCatch();
			break;
		case GmStat_TowerChoose:
			drawCleanScreen(0);
			drawTowerChoose();
			break;
		case GmStat_Scripe:
			Tools.setOffXY(0, 0);
			engine.draw_Scripe();
			// engine.run_Scripe();
			break;

		case GmStat_Win:
			drawCleanScreen(0);
			// Tools.removeAllImage();
			engine.map.setMap(true, true);
			engine.DrawBackground(gmStatus);
			drawWin();
			break;
		case GmStat_dengdai_win:
			drawCleanScreen(0);
			engine.DrawBackground(gmStatus);
			drawdengdai_Win();
			// System.out.println("drawdengdai_Win");
			break;
		case GmStat_dengdai_lose:
			drawCleanScreen(0);
			engine.DrawBackground(gmStatus);
			drawDengdai_lose();
			break;
		case GmStat_Lose:
			// System.out.println("GmStat_Lose");
			drawCleanScreen(0xffffffff);
			engine.DrawBackground(gmStatus);
			drawLose();
			break;
		case GmStat_TA_BUILD:
			break;
		case GmStat_SELLUP:
			engine.drawGame(false);
			upDownShake(GmStat_SELLUP);
			drawTa();
			break;
		case GmStat_Load:
			drawCleanScreen(0);
			// sound.stopAllMusic();
			loadLogic(80, 400, 240);
			drawLoad();
			break;

		case GmStat_Help:
			drawCleanScreen(0);
			drawHelp();
			break;
		case GmStat_About:
			drawCleanScreen(0);
			drawAbout();
			break;
		case GmStat_Shop:

			break;
		case GmStat_Playing:
			// try{
			drawCleanScreen(0);
			// gameTime++;
			engine.runGame();
			engine.drawGame(true);
			// }catch(Exception e){
			// Log.e("yichang","yichang"+110);
			// }
			drawEffect();

			if (isShanDian) {
					drawTX((int) (iTouchUpX * beishuX),
							(int) (iTouchUpY * beishuY) + 70);

			}
			drawJinBi(engine.isDraw);

			break;
		case GmStat_GAMBLE: // 抽奖

			engine.drawGame(true);
			drawEffect();
			engine.drawGamble();
			engine.gamebleLogic();
			// System.out.println("GmStat_GAMBLE");
			break;
		case GmStat_GOLDNOT:
			engine.drawGame(false);
			drawGoldNot();
			break;
		case GmStat_TEACHING:
			engine.drawGame(false);
			drawTEACHING();
			break;
		case GmStat_TEACHSHOP:
			drawShop();
			drawTEACHSHOP();
			break;
		case GmStat_More:
			MyActivity.openUrl(MyGameView.context, "http://wap.a.the9.com");
			break;
		}
		drawAll();
		if (MyGameView.iskep == true) {
			pointerMoved(MyGameView.iskepX, MyGameView.iskepY);

		}
	}

	/**
	 * 开机loading界面 loadDelay
	 * 
	 */
	int loadDelay;
	int imgDelay;
	int fishX;

	public void drawOpenLoading() {
		int load[][] = { { 0, 0, 108, 40 }, { 0, 0, 108, 40 },
				{ 0, 40, 108, 40 }, { 0, 40, 108, 40 }, { 0, 80, 108, 40 },
				{ 0, 80, 108, 40 }, { 108, 0, 108, 40 }, { 108, 0, 108, 40 },
				{ 108, 40, 108, 40 }, { 108, 40, 108, 40 },
				{ 108, 80, 108, 40 }, { 108, 80, 108, 40 } };
		if (loadDelay < 20) {
			drawCleanScreen(0);// 前20帧黑屏
		} else {

			GameDraw.add_Rect(0, 0, 480, 320, true, Tools.TOP_LEFT, 0x88000000,
					5);
			GameDraw.add_Image(PAK_IMAGES.IMG_LOADING3, 0, 400, 0, 0,
					fishX + 20, 20, Tools.TOP_LEFT, Tools.TRANS_NONE, 5);
		}
	}

	/**
	 * loading界面的 根据加载的图片,数据决定进度条的滚动
	 */
	public void LoadingLogic() {
		loadDelay++;
		// System.out.println("loadDelay : "+loadDelay);
		if (loadDelay >= 20) {
			fishX = 5 * (loadDelay - 20);
			if (fishX >= 800) {
				fishX = 800;
				imgDelay++;
				if (imgDelay == 10) {// 载入bin数据
					loadGame();

				}
				if (imgDelay == 15) {// 载入其他一些数据
					MyActivity.instance._mView.waf.StartBackMusic(4, true);
					initBinData();
				}
				if (imgDelay == 17) {
					// GameBilling.initBilling(MyActivity.instance);
				}
				if (imgDelay == 1) {
					// GameNet net = new GameNet(MyActivity.instance);
				}
				if (imgDelay >= 25) {
					setST(GmStat_Menu);
					loadDelay = 0;
					imgDelay = 0;
					fishX = 0;
				}

			}
		}
	}

	/**
	 * 加载界面后的3张动画 时间顺序，一次播放 同时也可以,每次点击,下一张
	 */
	int iAniAlpha = 255;// 图片逐渐显现的alpha值
	int iAniType = 0;//


	/**
	 * 技能等级满级30
	 */
	public byte jiNengMax = 30;
	static byte[] jiNengKaiQi = { 1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1 };
	/**
	 * 每个技能升级的价格 0.火球1.冰冻2.核弹3.血量4.回避5.反弹6.暴击7.攻速8.伤害9.眩晕10.穿透
	 * 
	 */
	int[] JNJG = { 1240, 2240, 6335, 11639, 17920, 25043, 32921, 41485, 50685,
			60480, 70835, 81721, 93115, 104993, 117338, 130132, 143360, 157007,
			171063, 185514, 200351, 215564, 231143, 247081, 263369, 280000,
			296966, 314263, 331883, 349820, 400000 };

	public void drawShop_2() {
		int[][] JiNeng1 = { // 已开启的图标
		{ 8, 10, 48, 50 },/* 火球 */
		{ 70, 11, 48, 48 },/* 冰冻 */
		{ 132, 10, 49, 50 },/* 核弹 */
		{ 197, 11, 51, 50 },/* 加血 */
		{ 7, 78, 49, 50 },/* 闪避 */
		{ 68, 77, 51, 51 },/* 反弹 */
		{ 132, 78, 49, 50 },/* 暴击 */
		{ 197, 81, 49, 48 },/* 攻速 */
		{ 8, 136, 49, 49 },/* 伤害 */
		{ 70, 137, 48, 47 },/* 眩晕 */
		{ 132, 137, 48, 47 },/* 穿透 */
		};
		int[][] JiNeng2 = { // 未开启的图标
		{ 9, 193, 47, 49 },/* 暴击灰 */
		{ 70, 194, 48, 46 },/* 冰冻灰 */
		{ 133, 193, 49, 49 },/* 核弹灰 */
		{ 198, 194, 49, 49 },/* 加血灰 */
		{ 9, 261, 46, 48 },/* 闪避灰 */
		{ 70, 260, 48, 50 },/* 反弹灰 */
		{ 132, 260, 49, 50 },/* 暴击灰 */
		{ 198, 264, 48, 47 },/* 攻速 */
		{ 9, 318, 48, 49 },/* 伤害灰 */
		{ 70, 319, 47, 47 },/* 眩晕灰 */
		{ 132, 319, 47, 47 } };/* 穿透灰 */

		int[][] JNshuoming = { // 技能名称和效果
		{ 165, 206, 34, 19 },/* 火球0 */
		{ 124, 205, 34, 20 },/* 冰冻1 */
		{ 43, 205, 34, 20 },/* 核弹2 */
		{ 4, 206, 34, 19 },/* 血量3 */
		{ 287, 205, 34, 20 },/* 回避4 */
		{ 82, 205, 34, 20 },/* 反弹5 */
		{ 326, 205, 33, 20 },/* 暴击6 */
		{ 208, 206, 34, 19 },/* 攻速7 */
		{ 248, 205, 33, 20 },/* 伤害8 */
		{ 368, 205, 35, 20 },/* 眩晕9 */
		{ 409, 205, 34, 20 },/* 穿透10 */

		{ 0, 0, 476, 205 },/* 技能大框11 */
		{ 0, 234, 94, 35 },/* 按钮12 */
		{ 0, 272, 94, 35 },/* 按钮灰13 */
		{ 94, 232, 109, 41 },/* 选中按钮14 */
		{ 204, 226, 52, 52 },/* 选中旋转图片15 */
		{ 95, 281, 55, 26 },/* 升级字16 */
		{ 155, 281, 55, 26 },/* 升级灰17 */
		{ 298, 246, 68, 16 },/* LV:18 */
		{ 255, 280, 129, 32 },/* 小蒙板19 */
		{ 395, 238, 79, 31 },/* MAX图标20 */

		{ 0, 322, 105, 42 },/* 框子灰21 */
		{ 105, 321, 105, 42 },/* 框子22 */
		{ 210, 316, 60, 32 },/* 技能字23 */
		{ 343, 316, 61, 32 },/* 技能字灰24 */
		{ 413, 316, 60, 32 },/* 植物字25 */
		{ 274, 316, 59, 32 },/* 植物字灰26 */
		{ 385, 271, 89, 37 },/* 亮框子27 */

		{ 2, 368, 92, 25 },/* 开始游戏字灰28 */
		{ 109, 367, 92, 26 },/* 开始游戏字29 */
		{ 224, 349, 108, 44 },/* 开始游戏框灰30 */
		{ 355, 349, 107, 44 },/* 开始游戏框31 */
		{ 298, 286, 62, 24 } /* 下一级32 */
		};
		int[][] JNshuoming2 = { // 技能說明介绍
		{ 6, 3, 244, 51 },/* 火球_介绍0 */
		{ 5, 57, 204, 47 },/* 冰冻_介绍1 */
		{ 6, 106, 203, 51 },/* 核弹_介绍2 */
		{ 7, 158, 183, 48 },/* 血量_介绍3 */
		{ 6, 206, 205, 49 },/* 闪避_介绍4 */
		{ 8, 255, 184, 52 },/* 反弹_介绍5 */
		{ 276, 0, 186, 52 },/* 暴击_介绍6 */
		{ 278, 108, 164, 49 },/* 射术_介绍8 */
		{ 276, 209, 184, 51 },/* 伤害_介绍10 */
		{ 278, 56, 205, 49 },/* 眩晕_介绍7 */
		{ 277, 156, 181, 54 },/* 穿透_介绍9 */
		};
		
		int[][] shopban1 = { 
		{ 299, 322, 56, 28 },/* 火球_字0 */
		{ 229, 322, 56, 28 },/* 冰霜_字1 */
		{ 89,  322, 56, 28 },/* 核弹_字2 */
		{ 20,  323, 55, 27 },/* 血量_字3 */
		{ 509, 322, 55, 28 },/* 闪避_字4 */
		{ 159, 322, 56, 28 },/* 反弹_字5 */
		{ 578, 322, 56, 28 },/* 暴击_字6 */
		{ 369, 323, 56, 27 },/* 攻速_字7 */
		{ 440, 322, 55, 28 },/* 伤害_字8 */
		{ 647, 322, 57, 28 },/* 眩晕_字9 */
		{ 718, 322, 56, 28 },/* 穿透_字10 */
		{ 4, 4, 791, 312 },/* 竟能大框11 */
		{ 8, 354, 124, 52 },/* 升级按钮12 */
		{ 669, 356, 123, 51 },/* 升级灰13 */
		{ 206, 371, 151, 64 },/* 选中框14 */
		{ 367, 372, 143, 38 },/* 小蒙板15 */
		{ 521, 360, 141, 57 },/* max16 */
		{ 369, 418,  77, 19 } };/* lv17 */

		int[][] array = { { 5, 4, 141, 36 },/* 1yuan */
		{ 151, 3, 142, 36 },/* 金币 */
		{ 8, 50, 125, 52 },/* 升满级 */
		{ 8, 108, 125, 53 },/* 升满级-灰 */
		{ 156, 48, 125, 52 },/* 升级 */
		{ 156, 109, 125, 53 } };/* 升级-灰 */
		GameDraw.add_Image(PAK_IMAGES.IMG_UPALLSKILL, 300, 460,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 1111);//
		checkShan();
		drawUPTX();
		if (tiShi > 0) {
			tiShi--;
			GameDraw.add_Image(PAK_IMAGES.IMG_ZHIDEYIZHAN, 400, 240,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 1111);//
		}

		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN3, 0, 400, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 11);// 背景
		GameDraw.add_Image(PAK_IMAGES.IMG_GUANQIABEIJING, 0, 80,
				Tools.TOP_LEFT, Tools.TRANS_NONE, 0);// 背景
		GameDraw.add_Image(PAK_IMAGES.IMG_PAOTAI33, 0, 0, Tools.TOP_LEFT,
				Tools.TRANS_V, 11);// 背景
 
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN11, 300, 10, shopban11[1],
				Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 商店lh
		GameDraw.add_Image(PAK_IMAGES.IMG_SMS5, 330 + 4, 15 + 6, 16, 89, 87,
				46, Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 商店字
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1, 4, 86, shopban1[11],
				Tools.TOP_LEFT, Tools.TRANS_NONE, 8);// /*技能大框11*/
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPFONT, 455, 400, array[1],
				Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// /*小蒙板19*/

		if (jiNengKaiQi[JNXuangZhong] != -1) {
			GameDraw.drawNumber(PAK_IMAGES.IMG_DENGJISHUZI,
					JNJG[jiNengKaiQi[JNXuangZhong]], 500, 410, 15, 0,
					Tools.TOP_LEFT, 260, 25, 0, true); // 升级所需要的金币
		}
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN11, 150, 10, shopban11[1],
				Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 植物框子灰
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN11, 0, 10, shopban11[0],
				Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 植物框子lh
		// GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,109,2,JNshuoming[27],
		// Tools.TOP_LEFT, Tools.TRANS_NONE, 12);//发光框子
		GameDraw.add_ImageAlpha(PAK_IMAGES.IMG_SHOPBAN11, 13, 18, 316, 7, 130,
				53, Tools.TOP_LEFT, Tools.TRANS_NONE, 12, 255 - shan);// 发光框子

		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN11, 30 + 4, 15 + 6,
				shopban11[5], Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 技能字灰
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN11, 180 + 4, 15 + 6,
				shopban11[4], Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 植物字

		GameDraw.add_Image(pointMenu == 2 ? PAK_IMAGES.IMG_YUYEDAHENG
				: PAK_IMAGES.IMG_SHENSISHULVAN, 730, 2, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 28);// 返回按钮

		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1, 460 + 3, 427 + 5,
				pointMenu == 1 ? shopban1[13] : shopban1[12], Tools.TOP_LEFT,
				Tools.TRANS_NONE, 20);// 升级按钮

		drawJinBi();
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN11, 710, 435,
				pointMenu == 0 ? shopban11[8] : shopban11[7],
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 20);// 开始游戏框


		for (int i = 0; i < 12; i++) { // 12个技能升级

			if (i >= 9) {
				if (JNXuangZhong == i - 1) {
					GameDraw.add_Image(PAK_IMAGES.IMG_HAISHENZHINU, -5, 385,
							JNshuoming2[i - 1], Tools.TOP_LEFT,
							Tools.TRANS_NONE, 20); // 介绍
				}
			} else {
				if (JNXuangZhong == i) {
					GameDraw.add_Image(PAK_IMAGES.IMG_HAISHENZHINU, -5, 385,
							JNshuoming2[i], Tools.TOP_LEFT, Tools.TRANS_NONE,
							20); // 介绍
				}
			}

			if (i < 3) {
				if (JNXuangZhong == i) {
					GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
							127 + 126 + 191 * i, 23 + 73 - 5, shopban1[14],
							Tools.TOP_LEFT, Tools.TRANS_NONE, 20); // 选中框

					GameDraw.add_ImageRota(PAK_IMAGES.IMG_SHOPBAN2,
							107 + 146 + 193 * i,
							35 + 96 - 7,// 旋转框
							Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 160,
							MyGameCanvas.me.gameTime % 360);
				}
				GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
						125 + 175 + 196 * i, 20 + 80, shopban1[i],
						Tools.TOP_LEFT, Tools.TRANS_NONE, 8);

				if (jiNengKaiQi[i] != -1) {// 技能升级是否开启
					GameDraw.add_Image(PAK_IMAGES.IMG_SUZHANSUJUE,
							100 + 129 + 192 * i, 40 + 63 - 5, JiNeng1[i],
							Tools.TOP_LEFT, Tools.TRANS_NONE, 9);
					if (jiNengKaiQi[i] >= 30) {
						GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
								100 + 158 + 193 * i, 20 + 60 + 16,
								shopban1[16], Tools.TOP_LEFT, Tools.TRANS_NONE,
								8);// 满级
					} else {
						GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
								128 + 162 + 190 * i, 40 + 88, shopban1[17],
								Tools.TOP_LEFT, Tools.TRANS_NONE, 8);// LV:
						GameDraw.drawNumber(PAK_IMAGES.IMG_JINBISHUZI,
								jiNengKaiQi[i], 135 + 200 + 190 * i, 91 + 42,
								11, 0, Tools.TOP_LEFT, 260, 16, 0, true); // 金币
					}
				} else {
					GameDraw.add_Image(PAK_IMAGES.IMG_SUZHANSUJUE,
							100 + 130 + 192 * i, 40 + 63 - 4, JiNeng2[i],
							Tools.TOP_LEFT, Tools.TRANS_NONE, 9);
				}

			}
			if (i >= 3 && i < 6) {

				if (JNXuangZhong == i) {
					GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
							120 + 130 + 192 * (i - 3), 60 + 117 - 5,
							shopban1[14], Tools.TOP_LEFT, Tools.TRANS_NONE, 20); // 选中框
					GameDraw.add_ImageRota(PAK_IMAGES.IMG_SHOPBAN2,
							120 + 133 + 192 * (i - 3),
							69 + 140 - 5,// 旋转框
							Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 160,
							MyGameCanvas.me.gameTime % 360);
				}
				GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
						125 + 175 + 196 * (i - 3), 65 + 120 - 5, shopban1[i],
						Tools.TOP_LEFT, Tools.TRANS_NONE, 8);
				if (jiNengKaiQi[i] != -1) {// 技能升级是否开启

					GameDraw.add_Image(PAK_IMAGES.IMG_SUZHANSUJUE,
							100 + 128 + 192 * (i - 3), 80 + 104 - 5,
							JiNeng1[i], Tools.TOP_LEFT, Tools.TRANS_NONE, 9);
					if (jiNengKaiQi[i] >= 30) {
						if (i == 3) {
							GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
									100 + 158 + 190 * (i - 3), 80 + 93,
									shopban1[16], Tools.TOP_LEFT,
									Tools.TRANS_NONE, 20);// 满级
						} else {
							GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
									100 + 159 + 192 * (i - 3), 80 + 93,
									shopban1[16], Tools.TOP_LEFT,
									Tools.TRANS_NONE, 20);// 满级

						}

					} else {
						GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
								128 + 160 + 190 * (i - 3), 77 + 130,
								shopban1[17], Tools.TOP_LEFT, Tools.TRANS_NONE,
								20);// LV:
						GameDraw.drawNumber(PAK_IMAGES.IMG_JINBISHUZI,
								jiNengKaiQi[i], 135 + 200 + 190 * (i - 3),
								80 + 132, 11, 0, Tools.TOP_LEFT, 260, 16, 0,
								true); // 金币
					}
				} else {
					GameDraw.add_Image(PAK_IMAGES.IMG_SUZHANSUJUE,
							100 + 130 + 192 * (i - 3), 80 + 104 - 5,
							JiNeng2[i], Tools.TOP_LEFT, Tools.TRANS_NONE, 9);
				}
			}
			if (i >= 6 && i < 9) {

				if (i == 6) {

					if (JNXuangZhong == i) {
						GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
								125 + 126 + 190 * (i - 6), 120 + 180 - 14,
								shopban1[14], Tools.TOP_LEFT, Tools.TRANS_NONE,
								20); // 选中框
						GameDraw.add_ImageRota(PAK_IMAGES.IMG_SHOPBAN2,
								120 + 136 + 190 * (i - 6),
								120 + 199,// 旋转框
								Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 160,
								MyGameCanvas.me.gameTime % 360);

						// GameDraw.add_ImageRota(PAK_IMAGES.IMG_SHOPBAN1,150+
						// 110*(i-6), 205-5,JNshuoming[15],//旋转框
						// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,160,MyGameCanvas.me.gameTime%360);
					}
					GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
							125 + 175 + 196 * (i - 6), 120 + 187 - 12,
							shopban1[i], Tools.TOP_LEFT, Tools.TRANS_NONE, 8);
					if (jiNengKaiQi[i] != -1) {// 技能升级是否开启
						GameDraw.add_Image(PAK_IMAGES.IMG_SUZHANSUJUE,
								100 + 128 + 190 * (i - 6), 120 + 170,
								JiNeng1[i], Tools.TOP_LEFT, Tools.TRANS_NONE, 9);
						if (jiNengKaiQi[i] >= 30) {
							GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
									100 + 157, 100 + 187, shopban1[16],
									Tools.TOP_LEFT, Tools.TRANS_NONE, 20);// 满级
						} else {
							GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
									128 + 162, 120 + 180 + 20, shopban1[17],
									Tools.TOP_LEFT, Tools.TRANS_NONE, 8);// LV:
							GameDraw.drawNumber(PAK_IMAGES.IMG_JINBISHUZI,
									jiNengKaiQi[i], 135 + 200 + 190 * (i - 6),
									120 + 180 + 20, 11, 0, Tools.TOP_LEFT, 260,
									16, 0, true); // 金币
						}
					} else {
						GameDraw.add_Image(PAK_IMAGES.IMG_SUZHANSUJUE,
								100 + 130 + 190 * (i - 6), 120 + 180,
								JiNeng2[i], Tools.TOP_LEFT, Tools.TRANS_NONE, 9);
					}
				} else {

					if (JNXuangZhong == i) {
						GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
								120 + 131 + 192 * (i - 6), 116 + 130,
								shopban1[14], Tools.TOP_LEFT, Tools.TRANS_NONE,
								20); // 选中框
						GameDraw.add_ImageRota(PAK_IMAGES.IMG_SHOPBAN2,
								110 + 146 + 191 * (i - 6),
								110 + 169,// 旋转框
								Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 160,
								MyGameCanvas.me.gameTime % 360);

					}
					GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
							125 + 175 + 196 * (i - 6), 100 + 160 - 5,
							shopban1[i], Tools.TOP_LEFT, Tools.TRANS_NONE, 8);
					if (jiNengKaiQi[i] != -1) {// 技能升级是否开启

						GameDraw.add_Image(PAK_IMAGES.IMG_SUZHANSUJUE,
								99 + 131 + 192 * (i - 6), 100 + 160 - 6,
								JiNeng1[i], Tools.TOP_LEFT, Tools.TRANS_NONE, 9);
						if (jiNengKaiQi[i] >= 30) {
							GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
									110 + 151 + 191 * (i - 6), 98 + 150,
									shopban1[16], Tools.TOP_LEFT,
									Tools.TRANS_NONE, 20);// 满级
							// System.out.println("iiiii:"+i);
						} else {
							GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
									132 + 162 + 190 * (i - 6), 120 + 142 + 19,
									shopban1[17], Tools.TOP_LEFT,
									Tools.TRANS_NONE, 8);// LV:
							GameDraw.drawNumber(PAK_IMAGES.IMG_JINBISHUZI,
									jiNengKaiQi[i], 140 + 200 + 190 * (i - 6),
									120 + 142 + 20, 11, 0, Tools.TOP_LEFT, 260,
									16, 0, true); // 金币
						}
					} else {
						GameDraw.add_Image(PAK_IMAGES.IMG_SUZHANSUJUE,
								99 + 131 + 192 * (i - 6), 100 + 160 - 7,
								JiNeng2[i], Tools.TOP_LEFT, Tools.TRANS_NONE, 9);
					}
				}

			}
			if (i >= 10 && i < 12) {

				if (JNXuangZhong == i - 1) {
					GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
							120 + 133 + 191 * (i - 9), 130 + 200 - 15,
							shopban1[14], Tools.TOP_LEFT, Tools.TRANS_NONE, 20); // 选中框
					GameDraw.add_ImageRota(PAK_IMAGES.IMG_SHOPBAN2,
							115 + 142 + 191 * (i - 9),
							140 + 220 - 12,// 旋转框
							Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 160,
							MyGameCanvas.me.gameTime % 360);

					// GameDraw.add_ImageRota(PAK_IMAGES.IMG_SHOPBAN1,150+
					// 110*(i-9), 225-5,JNshuoming[15],//旋转框
					// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,160,MyGameCanvas.me.gameTime%360);
				}
				GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
						125 + 175 + 196 * (i - 9), 160 + 200 - 35,
						shopban1[i - 1], Tools.TOP_LEFT, Tools.TRANS_NONE, 8);
				if (jiNengKaiQi[(i - 1)] != -1) { // 技能升级是否开启
					GameDraw.add_Image(PAK_IMAGES.IMG_SUZHANSUJUE,
							99 + 132 + 192 * (i - 9), 130 + 200 - 5,
							JiNeng1[i - 1], Tools.TOP_LEFT, Tools.TRANS_NONE, 9);
					if (jiNengKaiQi[i - 1] >= 30) {
						GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
								100 + 161 + 191 * (i - 9), 115 + 200 + 4,
								shopban1[16], Tools.TOP_LEFT, Tools.TRANS_NONE,
								20);// 满级
					} else {
						GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN1,
								132 + 162 + 190 * (i - 9), 150 + 200,
								shopban1[17], Tools.TOP_LEFT, Tools.TRANS_NONE,
								8);// LV:
						GameDraw.drawNumber(PAK_IMAGES.IMG_JINBISHUZI,
								jiNengKaiQi[i - 1], 140 + 200 + 190 * (i - 9),
								135 + 200 + 20, 11, 0, Tools.TOP_LEFT, 260, 16,
								0, true); // 金币
					}
				} else {
					GameDraw.add_Image(PAK_IMAGES.IMG_SUZHANSUJUE,
							99 + 130 + 192 * (i - 9), 130 + 200 - 5,
							JiNeng2[i - 1], Tools.TOP_LEFT, Tools.TRANS_NONE, 9);
				}

			}

		}
	}

	/**
	 * 0是否激活（-1否0是）， 1是否装备（-1否0是）， 2装备名称， 3攻击力， 4射击时间间隔， 5价格， 6消耗， 7特殊效果 8子弹速度
	 * 
	 * ——0.豌豆1.大蒜2.玉米3.仙人掌4.西红柿5.西瓜6.香蒲7.南瓜
	 * 
	 * 5000 大蒜 10000 玉米 36000 仙人掌 40000 西红柿 190000 西瓜 230000 香蒲 300000 南瓜
	 */
	int ZBXingXi[][] = {
			{ 0, -1, 0, 6, 5, 1000, 1, 1, 15 },
			{ -1, -1, 1, 60, 30, 12000, 2, 2, 10 },// 西红柿
			{ -1, -1, 2, 60, 28, 12000, 3, 0, 28 },// 仙人掌
			{ -1, -1, 3, 24, 20, 24000, 4, 1, 100 },// 玉米
			{ -1, -1, 4, 120, 25, 32000, 5, 2, 32 },// 香蒲
			{ -1, -1, 5, 10, 4, 40000, 6, 0, 15 },// 大蒜
			{ -1, -1, 6, 96, 30, 48000, 7, 1, 10 },// 西瓜
			{ -1, -1, 7, 180, 30, 300000, 8, 2, 10 },// 南瓜
			{ -1, -1, 8, 900, 10, 900, 9, 0, 10 },
			{ -1, -1, 9, 1000, 10, 10000, 10, 1, 10 }, };
	/**
	 * 装备属性 1眩晕 2穿透 3暴击
	 */
	static int[][] ZBShuXing = { { -1 }, { 1, 2, 3 }, { 1 }, { 2 }, { 1 },
			{ 1 }, { 2, 3 }, { 1, 3 } };


	/**
	 * 装备开启后直接加入该数组 1.西红柿2.仙人掌3.玉米4.香蒲5.大蒜6.西瓜7.南瓜 正确顺序0,5,3,2,1,6,4,7
	 */
	public static int[] ZB = { 0, -1, -1, -1, -1, -1, -1, -1 };
	public static int[] JLZB = { 0, -1, -1, -1, -1, -1, -1, -1 };
	int[] zhuJiao = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	/**
	 * 0.豌豆1.大蒜2.玉米3.仙人掌4.西红柿5.西瓜6.香蒲7.南瓜 以装备的小头像
	 */

	int[][] bubing41 = { { 114, 51, 99, 40 },/* 豌豆_字0 */
	{ 340, 51, 99, 41 },/* 大蒜_字1 */
	{ 229, 51, 98, 40 },/* 玉米_字2 */
	{ 115, 2, 99, 41 },/* 仙人掌_字3 */
	{ 1, 3, 97, 40 },/* 西红柿_字4 */
	{ 1, 54, 98, 40 },/* 西瓜_字5 */
	{ 230, 3, 97, 41 },/* 香蒲_字6 */
	{ 341, 3, 97, 40 },/* 南瓜_字7 */
	{ 447, 6, 72, 36 },/* 激活_字8 */
	{ 449, 48, 72, 36 },/* 激活_字灰9 */
	{ 11, 210, 81, 30 },/* 攻击力_字10 */
	{ 313, 103, 57, 31 },/* 射速_字11 */
	{ 219, 210, 57, 30 },/* 消耗_字12 */
	{ 106, 210, 105, 30 },/* 特殊效果_字13 */
	{ 296, 145, 120, 101 },/* 已装备_字14 */

	{ 166, 101, 37, 38 },/* 火箭筒_图标15 */
	{ 2, 101, 39, 38 },/* 机枪_图标16 */
	{ 64, 101, 38, 38 },/* 狙_图标17 */
	{ 116, 101, 38, 38 },/* 喷子_图标18 */

	{ 218, 99, 40, 40 },/* 下拉圆按钮19 */
	{ 272, 104, 27, 28 },/* 小金币图标20 */
	{ 382, 98, 129, 38 },/* 半透明小框21 */
	{ 71, 145, 51, 52 },/* 暴击图标22 */
	{ 138, 148, 50, 51 },/* 眩晕图标23 */
	{ 206, 148, 52, 53 } /* 穿透图标24 */
	};

	int[] bubing42 = { PAK_IMAGES.IMG_WANDOUZHAI, PAK_IMAGES.IMG_SHUANNIHEN,
			PAK_IMAGES.IMG_BAOMIHUA, PAK_IMAGES.IMG_XIANRENQIU,
			PAK_IMAGES.IMG_FANQIEDI, PAK_IMAGES.IMG_XIGUADI,
			PAK_IMAGES.IMG_CITOUGE, PAK_IMAGES.IMG_NANGUAGE, };
	int[] bubing43 = { PAK_IMAGES.IMG_WANDOUZHAI, PAK_IMAGES.IMG_FANQIEDI,
			PAK_IMAGES.IMG_XIANRENQIU, PAK_IMAGES.IMG_BAOMIHUA,
			PAK_IMAGES.IMG_CITOUGE, PAK_IMAGES.IMG_SHUANNIHEN,
			PAK_IMAGES.IMG_XIGUADI, PAK_IMAGES.IMG_NANGUAGE, };

	int[][] shopban11 = { { 0, 2, 157, 63 },/* 切换按钮0 */
	{ 157, 2, 157, 61 },/* 切换按钮灰1 */
	{ 316, 7, 130, 53 },/* 发光框子2 */
	{ 10, 75, 87, 47 },/* 技能3 */
	{ 116, 75, 85, 45 },/* 植物4 */
	{ 220, 75, 87, 47 },/* 技能灰5 */
	{ 334, 75, 86, 46 },/* 植物灰6 */
	{ 12, 133, 177, 67 },/* 开始游戏7 */
	{ 217, 133, 177, 66 } };/* 开始游戏灰8 */

	byte[] wuQi_lx = { 16, 16, 18, 17, 15, 15, 17, 15 };
	public byte ZBIndex;
	public byte shan = 0;
	public boolean jia = true;

	void checkShan2(GameRole role) {
		if (role.jia == false) {

			if (role.shan <= 5 && !role.jia) {
				if (role.ting < 80) {
					role.ting++;
				} else {
					role.ting = 0;
					role.jia = true;
				}

			} else {
				role.shan -= 5;
			}
		}
		if (role.jia == true) {
			role.shan += 5;
			if (role.shan >= 250) {
				role.jia = false;
			}
		}
	}

	void checkShan() {
		if (shan > 0 && jia == false) {
			shan -= 5;
		} else {
			jia = true;
		}
		if (shan < 100 && jia) {
			shan += 5;
		} else {
			jia = false;
		}

	}

	public byte shan2 = 0;

	void checkShan2() {
		if (shan2 > 0 && jia == false) {
			shan2 -= 5;
		} else {
			jia = true;
		}
		if (shan < 150 && jia) {
			shan2 += 5;
		} else {
			jia = false;
		}

	}

	int UPTXxl = 920;
	int UPTXxr = -120;
	int countI;

	void drawUPTX() {
		if (UPTX) {
			if (UPTXxl != 400 && UPTXxr != 400) {
				UPTXxl -= 40;
				UPTXxr += 40;
				if (UPTXxl == 680) {
					MyActivity.instance._mView.waf.StartWav(1, false);
				}
			} else {

				if (++countI >= 45) {
					UPTXxl = 920;
					countI = 0;
					UPTXxr = -120;
					UPTX = false;
				}
			}

			if (UPTX) {
				GameDraw.add_Image(PAK_IMAGES.IMG_TEXIAO, UPTXxl, 240,
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 200);//
				GameDraw.add_Image(PAK_IMAGES.IMG_SHENJI, UPTXxr, 240,
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 200);//
			}

		}

	}

	void drawShop_ZB() {
		int[][] bubing4 = { { 0, 0, 255, 308 },/* 人物信息背景0 */
		{ 255, 0, 210, 216 },/* 人物头像背景1 */
		{ 4, 316, 274, 99 },/* 装备背景2 */
		{ 292, 282, 122, 77 },/* 激活按钮3 */
		{ 292, 361, 121, 75 },/* 激活按钮灰4 */
		{ 260, 223, 205, 18 },/* 数值条黑5 */
		{ 260, 252, 206, 22 },/* 数值条亮6 */
		{ 476, 2, 28, 315 } };/* 长条下拉7 */
		
		GameDraw.add_Image(PAK_IMAGES.IMG_OPENALLPLANT, openAll.left, openAll.top,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 1111);//
		
		checkShan();
		drawJinBi();
		chekZB();
		// if (ZJJX == false) {
		// setST(GmStat_RankJX);
		// }
		drawShopFont();
		if (tiShi > 0) {
			tiShi--;
			GameDraw.add_Image(PAK_IMAGES.IMG_ZHIDEYIZHAN, 400, 240,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 1111);//

		}
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN11, 700, 450,
				pointMenu != 2 ? shopban11[7] : shopban11[8],
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 13);// 开始游戏框
		// GameDraw.add_Image(PAK_IMAGES.IMG_BUBING4, 420,
		// 300,pointMenu!=2?drawZBData[24]:drawZBData[25],
		// Tools.HCENTER_VCENTER,
		// Tools.TRANS_NONE, 13);// 开始游戏字

		GameDraw.add_Image(PAK_IMAGES.IMG_PAOTAI33, 0, 0, Tools.TOP_LEFT,
				Tools.TRANS_HV, 11);// 背景
		GameDraw.add_Image(PAK_IMAGES.IMG_GUANQIABEIJING, 0, 48,
				Tools.TOP_LEFT, Tools.TRANS_NONE, 0);// 背景
		GameDraw.add_Image(PAK_IMAGES.IMG_PAOTAI33, 0, 400, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 11);// 背景

		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN11, 300, 10, shopban11[1],
				Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 商店lh
		GameDraw.add_Image(PAK_IMAGES.IMG_SMS5, 330 + 4, 15 + 6, 16, 89, 87,
				46, Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 商店字
		// GameDraw.add_Image(PAK_IMAGES.IMG_BUBING4,310,100,bubing4[1],
		// Tools.TOP_LEFT, Tools.TRANS_NONE, 10);//人物头像背景板
		GameDraw.add_Image(PAK_IMAGES.IMG_BUBING4, 520, 100, bubing4[0],
				Tools.TOP_LEFT, Tools.TRANS_NONE, 10);// 人物介绍背景板
		GameDraw.add_Image(PAK_IMAGES.IMG_BUBING4, 280, 80, bubing4[7],
				Tools.TOP_LEFT, Tools.TRANS_H, 10);// 人物下拉条

		GameDraw.add_Image(
				PAK_IMAGES.IMG_BUBING41,
				275,
				81 - (int) (200 * (float) ((float) (MyGameView.wuQi_Move + MyGameView.wuQi_Y) / (float) 378)),
				bubing41[19], Tools.TOP_LEFT, Tools.TRANS_H, 10);// 人物下拉条_圆球
		GameDraw.add_Image(bubing42[wuQi_index], 418, 215,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 10);// 人物头像

		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN11, 150, 10, shopban11[0],
				Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 植物框子
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN11, 0, 10, shopban11[1],
				Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 植物框子灰
		GameDraw.add_ImageAlpha(PAK_IMAGES.IMG_SHOPBAN11, 160, 18, 316, 7, 130,
				53, Tools.TOP_LEFT, Tools.TRANS_NONE, 12, 255 - shan);// 发光框子

		// GameDraw.add_ImageAlpha(PAK_IMAGES.IMG_SHOPBAN11,13,18,316,7,130,53,Tools.TOP_LEFT,
		// Tools.TRANS_NONE, 12,255-shan);//发光框子
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN11, 30 + 4, 6 + 15,
				shopban11[3], Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 技能字
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN11, 180 + 4, 6 + 15,
				shopban11[6], Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 植物字灰lh

		for (int i = 0; i < 7; i++) {

			GameDraw.add_Image(PAK_IMAGES.IMG_BUBING4, 5, 30 + 50 + i * 100
					+ MyGameView.wuQi_Move + MyGameView.wuQi_Y, bubing4[2],
					Tools.TOP_LEFT, Tools.TRANS_NONE, 10);// 背景板
			GameDraw.add_Image(PAK_IMAGES.IMG_BUBING41, 15, 35 + 95 + i * 100
					+ MyGameView.wuQi_Move + MyGameView.wuQi_Y,
					bubing41[wuQi_lx[i]], Tools.TOP_LEFT, Tools.TRANS_NONE, 10);// 武器类型图标
			GameDraw.add_Image(PAK_IMAGES.IMG_BUBING41, 15, 35 + 53 + i * 100
					+ MyGameView.wuQi_Move + MyGameView.wuQi_Y, bubing41[i],
					Tools.TOP_LEFT, Tools.TRANS_NONE, 10);// 武器名称

			if (ZBXingXi[i][0] == -1) {
				GameDraw.add_Image(PAK_IMAGES.IMG_BUBING4, 150, 30 + 60 + i
						* 100 + MyGameView.wuQi_Move + MyGameView.wuQi_Y,
						bubing4[3], Tools.TOP_LEFT, Tools.TRANS_NONE, 10);// 激活框

				GameDraw.add_Image(PAK_IMAGES.IMG_BUBING41, 175, 30 + 90 + i
						* 100 + MyGameView.wuQi_Move + MyGameView.wuQi_Y,
						bubing41[8], Tools.TOP_LEFT, Tools.TRANS_NONE, 10);// 激活字

				GameDraw.drawNumber(PAK_IMAGES.IMG_JINBISHUZI,
						ZBXingXi[i][5],// 武器价格
						170, 30 + 70 + i * 100 + MyGameView.wuQi_Move
								+ MyGameView.wuQi_Y, 11, 0, Tools.TOP_LEFT, 10,
						16, 0, true); // 金币 数字
			} else {
				GameDraw.add_Image(PAK_IMAGES.IMG_BUBING41, 150, 30 + 55 + i
						* 100 + MyGameView.wuQi_Move + MyGameView.wuQi_Y,
						bubing41[14], Tools.TOP_LEFT, Tools.TRANS_NONE, 10);// 激活框
			}
			if (i == wuQi_index) {
				GameDraw.add_Image(PAK_IMAGES.IMG_XUANZHONG, 5, 30 + 50 + i
						* 100 + MyGameView.wuQi_Move + MyGameView.wuQi_Y,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 10);// 选中框
			}
		}

		GameDraw.add_Image(pointMenu == 3 ? PAK_IMAGES.IMG_YUYEDAHENG
				: PAK_IMAGES.IMG_SHENSISHULVAN, 730, 2, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 28);// 返回


		/**
		 * 以下是画装备信息
		 * 
		 */

		for (int i = 0; i < ZBShuXing[wuQi_index].length; i++) {
			if (ZBShuXing[wuQi_index][i] == 1) {
				GameDraw.add_Image(PAK_IMAGES.IMG_BUBING41, 550 + 70 * i, 370,
						bubing41[22], Tools.HCENTER_VCENTER, Tools.TRANS_NONE,
						10);// 技能图标
			}
			if (ZBShuXing[wuQi_index][i] == 2) {
				GameDraw.add_Image(PAK_IMAGES.IMG_BUBING41, 550 + 70 * i, 370,
						bubing41[23], Tools.HCENTER_VCENTER, Tools.TRANS_NONE,
						10);// 技能图标
			}
			if (ZBShuXing[wuQi_index][i] == 3) {
				GameDraw.add_Image(PAK_IMAGES.IMG_BUBING41, 550 + 70 * i, 370,
						bubing41[24], Tools.HCENTER_VCENTER, Tools.TRANS_NONE,
						10);// 技能图标
			}

		}

	}

	void chekZB() {

		for (int i = 0; i < ZB.length; i++) {
			if (i != 0) {
				if (i < ZB.length - 1) {
					if (ZB[i - 1] == -1) {
						ZB[i - 1] = ZB[i];
						ZB[i] = -1;
					}
				} else {
					for (int j = 0; j < ZB.length; j++) {
						if (ZB[j] == -1) {
							ZB[j] = ZB[ZB.length - 1];
							ZB[ZB.length - 1] = -1;
						}

					}
				}
			}
		}
	}

	void drawShopFont() {
		/**
		 * 0.伤害 1.射速 2.消耗 ——0.豌豆1.大蒜2.玉米3.仙人掌4.西红柿5.西瓜6.香蒲7.南瓜
		 */
		int[][] xx = { { 10, 50, 0 }, { 10, 80, 10 }, { 15, 20, 20 },
				{ 30, 40, 30 }, { 30, 20, 20 }, { 50, 30, 70 }, { 70, 40, 60 },
				{ 100, 20, 80 } };

		for (int i = 0; i < 4; i++) {

			GameDraw.add_Image(PAK_IMAGES.IMG_BUBING41, 535, 110 + 70 * i,
					bubing41[10 + i], Tools.TOP_LEFT, Tools.TRANS_NONE, 11);// 攻击力
			// 画长条
			if (i > 2) {
				return;
			}
			GameDraw.add_Image(PAK_IMAGES.IMG_BUBING4, 535, 153 + 70 * i, 260,
					223, 205, 18, Tools.TOP_LEFT, Tools.TRANS_NONE, 20);// 空长条

			GameDraw.add_Image(PAK_IMAGES.IMG_BUBING4, 535, 150 + 70 * i, 260,
					252,
					(int) (206 * ((float) xx[wuQi_index][i] / (float) 100)),
					22, Tools.TOP_LEFT, Tools.TRANS_NONE, 20);// 亮长条
		}


	}

	void drawRankJX2() {
		if (engine.gameRank == 0) {
			// if (engine.isRank1JX) {
			if (MyActivity.VMHeight>240){
						GameDraw.add_Image(PAK_IMAGES.IMG_M1, 0, 0, Tools.TOP_LEFT,
					Tools.TRANS_NONE, 7999);// 背景	
			}

			GameDraw.add_Image(PAK_IMAGES.IMG_Z10, 0, 0, Tools.TOP_LEFT,
					Tools.TRANS_NONE, 8000);// 背景
			GameDraw.add_Image(PAK_IMAGES.IMG_Z12, 280, 80, Tools.TOP_LEFT,
					Tools.TRANS_NONE, 8001);// 背景
			// }
		} else {
			if (engine.gameRank == 1) {
				GameDraw.add_Image(PAK_IMAGES.IMG_Z14, 400, 240,
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 8001);// 提示语
																		// 注意****
				int xy[][] = { { 56, 180 - 100 }, { 130, 230 - 100 },
						{ 330, 140 - 100 }, { 149, 300 - 100 },
						{ 580, 176 - 100 }, { 465, 150 - 100 } };
				for (int i = 0; i < xy.length; i++) {
					GameDraw.add_Image(PAK_IMAGES.IMG_YUANZIDAN_, xy[i][0],
							xy[i][1], Tools.HCENTER_VCENTER, Tools.TRANS_NONE,
							8001);// 框子
				}

			}
			if (engine.gameRank == 99) {

			}

		}

	}

	void QieHuan() {
		GameDraw.add_Image(PAK_IMAGES.IMG_Z11, 400, 360, Tools.HCENTER_VCENTER,
				Tools.TRANS_NONE, 5200);
	}

	void drawYDSms() {
		switch (gameStatus) {
		case GmStat_YDSms:
			GameDraw.add_Image(PAK_IMAGES.IMG_SMS8, 400, 240,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 5200);
			break;

		case GmStat_YDSms2:
			
//			GameDraw.add_Image(PAK_IMAGES.IMG_SMS6, 400, 240,
//					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 5200);
			
			GameDraw.add_Image(PAK_IMAGES.IMG_GOUMAIKUANG, 400, 240,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 5200);
			
			GameDraw.add_Image(PAK_IMAGES.IMG_BUY1+m_currentSelect,400, 220,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 5200);
			//GameDraw.add_String("test", 300, 200, Tools.HCENTER_VCENTER,0, 1, 30);
			break;
		}

	}

	public boolean rankTiShi;

	void TSGTiShi() {
		if (rankTiShi) {
			return;
		}
		
		int img;
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
		default:
			img = -1;
			break;
		}

		GameDraw.add_Image(PAK_IMAGES.IMG_BEIBAN, 400, 240,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 6000);// 背景
		if (img != -1) {
			GameDraw.add_Image(img, 400, 240, Tools.HCENTER_VCENTER,
					Tools.TRANS_NONE, 6000);// 人物类型
			GameDraw.drawNumber(PAK_IMAGES.IMG_SHIJIANJIANGLI,
					GameEngine.roleNumber, 500, 225, 28, 0, Tools.TOP_LEFT,
					10000, 38, 0, true);//  
		} else {

			if (GameEngine.roleNumber != 0) {
				GameDraw.add_Image(PAK_IMAGES.IMG_R50, 400, 240,
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 6000);// 需要打的个数
				GameDraw.drawNumber(PAK_IMAGES.IMG_SHIJIANJIANGLI,
						GameEngine.roleNumber, 430, 195, 28, 0, Tools.TOP_LEFT,
						10000, 38, 0, true);// 子弹数量
			}

			if (fishData.isJiJia) {
				GameDraw.add_Image(PAK_IMAGES.IMG_RDIANBO, 400, 240,
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 6000);// 需要打的个数
			}
		}

		// GameEngine.roleType;
		// GameEngine.roleNumber;

	}

	// /////////////////////////////////////////////////
	/**
	 * 画封面背景+按键 封面字需要向下砸的效果 s =(1/2)*vec*t*t
	 */
	int iMenuCharacter = -120;// 方面3D字的坐标
	int iMenuTime;

	void menuLogic() {
		iMenuTime++;
		if (iMenuCharacter < 160) {
			iMenuCharacter = (int) (0.50f * 9.8f * (iMenuTime * iMenuTime));
		} else {
			iMenuCharacter = 160;
			iMenuTime = 0;
		}
	}

	public void drawSms() {
		int[][] sms5 = { { 3, 2, 140, 35 },/* 4yuan */
		{ 4, 41, 140, 35 },/* 2yuan */
		{ 157, 4, 124, 52 },/* goumai */
		{ 157, 66, 125, 52 },/* goumaihui */
		{ 16, 89, 87, 46 },/* shang */
		{ 15, 146, 88, 46 } };/* shanghui */
		// GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN3, 0, 400, Tools.TOP_LEFT,
		// Tools.TRANS_NONE, 11);// 背景
		GameDraw.add_Image(PAK_IMAGES.IMG_PAOTAI33, 0, 405, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 20);// 背景
		GameDraw.add_Image(PAK_IMAGES.IMG_GUANQIABEIJING, 0, 80,
				Tools.TOP_LEFT, Tools.TRANS_NONE, 0);// 背景
		GameDraw.add_Image(PAK_IMAGES.IMG_PAOTAI33, 0, 0, Tools.TOP_LEFT,
				Tools.TRANS_V, 11);// 背景
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN11, 0, 10, shopban11[1],
				Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 商店lh
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN11, 150, 10, shopban11[1],
				Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 植物框子灰
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN11, 30 + 4, 15 + 6,
				shopban11[3], Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 技能字灰
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN11, 180 + 4, 15 + 6,
				shopban11[4], Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 植物字
		GameDraw.add_Image(PAK_IMAGES.IMG_SMS5, 360 + 4, 15 + 6, sms5[5],
				Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 商店字

		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN11, 300, 10, shopban11[0],
				Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 商店lh
		GameDraw.add_Image(PAK_IMAGES.IMG_SMS5, 330 + 4, 15 + 6, 16, 89, 87,
				46, Tools.TOP_LEFT, Tools.TRANS_NONE, 12);// 商店字
		checkShan();
		GameDraw.add_ImageAlpha(PAK_IMAGES.IMG_SHOPBAN11, 312, 18, 316, 7, 130,
				53, Tools.TOP_LEFT, Tools.TRANS_NONE, 12, 255 - shan);// 发光框子

		GameDraw.add_Image(pointMenu2 == 5 ? PAK_IMAGES.IMG_YUYEDAHENG
				: PAK_IMAGES.IMG_SHENSISHULVAN, 730, 2, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 28);// 返回
		drawJinBi();

		int[] img = {// 商品
		PAK_IMAGES.IMG_SMS4, PAK_IMAGES.IMG_SMS1, PAK_IMAGES.IMG_SMS2,
				PAK_IMAGES.IMG_SMS3,PAK_IMAGES.IMG_SMS0 };
		for (int i = 0; i < img.length; i++) {
			GameDraw.add_Image(img[i], 10 + 157 * i, 90, Tools.TOP_LEFT,
					Tools.TRANS_NONE, 13);// 商品
//			GameDraw.add_Image(PAK_IMAGES.IMG_SMS5, 85 + 157 * i, 75 + 245,
//					i < 2 ? sms5[0] : sms5[1], Tools.HCENTER_VCENTER,
//					Tools.TRANS_NONE, 13);// 价格
			if(i!=3)
			{
				int temp=i!=4?i:3;
				GameDraw.add_Image(PAK_IMAGES.IMG_YUAN12+temp,85 + 157 * i, 75 + 245,
						Tools.HCENTER_VCENTER,Tools.TRANS_NONE, 13
						);
			}else
			{
				GameDraw.add_Image(PAK_IMAGES.IMG_SMS5, 85 + 157 * i, 75 + 245,
						i < 2 ? sms5[0] : sms5[1], Tools.HCENTER_VCENTER,
						Tools.TRANS_NONE, 13);// 价格
			}
		
			GameDraw.add_Image(PAK_IMAGES.IMG_SMS5, 85 + 157 * i, 90 + 290,
					(int) pointMenu2 == i ? sms5[3] : sms5[2],
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 13);// 按钮
		}

	}

	public void drawJinBi() {
		GameDraw.add_Image(PAK_IMAGES.IMG_DAMENBAN, 480, 15, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 13);// 封面背景图
		GameDraw.drawNumber(PAK_IMAGES.IMG_DENGJISHUZI,
				engine.usBs.iUsBsCuJinbi, 525, 28, 15, 0, Tools.TOP_LEFT, 260,
				25, 0, true); // 金币 数字
	}

	public void drawMenubg() {
		GameDraw.add_Image(PAK_IMAGES.IMG_BEIJING1, 0, 0, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 7);// 封面背景图
		GameDraw.add_Image(PAK_IMAGES.IMG_BEIJING2, 20, 20, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 7);// 游戏名称
		if (iGoldGet == 50 || iGoldGet == 100 || iGoldGet == 150
				|| iGoldGet == 300) {
			// GameDraw.add_Image(PAK_IMAGES.IMG_MENULINGQUBAOSHI,30,360,Tools.TOP_LEFT,Tools.TRANS_NONE,7);
		} else {
			if (MyGameCanvas.me.leftSecond - 60
					* (MyGameCanvas.me.leftSecond / 60) < 10) {
			}
		}

		/**
		 * menu按钮
		 */
		int[][] menuData = { { 84, 5, 74, 74 },/* X */
		{ 3, 9, 75, 74 },/* X灰 */
		{ 2, 92, 74, 74 },/* 设置 */
		{ 84, 91, 75, 74 },/* 设置灰 */
		{ 2, 181, 74, 74 },/* 图片说明 */
		{ 83, 180, 74, 74 },/* ？灰 */
		{ 2, 271, 74, 73 },/* 图片说明 */
		{ 82, 268, 74, 73 },/* 关于灰 */
		{ 179, 168, 215, 76 },/* 开始游戏 */
		{ 180, 255, 213, 75 } };/* 开始游戏灰 */
		int[][] sms9 = { { 7, 5, 124, 95 },/* 更多游戏 */
		{ 151, 5, 124, 94 },/* 更多游戏-灰 */
		{ 7, 107, 124, 95 },/* 精品推荐 */
		{ 150, 106, 126, 96 },/* 精品推荐-灰 */
		{ 7, 207, 123, 94 },/* 游戏圈圈 */
		{ 150, 206, 124, 95 } };/* 游戏圈圈-灰 */


		GameDraw.add_Image(PAK_IMAGES.IMG_TIAOZHAN1, 400, 420,
				pointMenu2 == 0 ? menuData[9] : menuData[8],
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 7);// 开始游戏
		GameDraw.add_Image(PAK_IMAGES.IMG_TIAOZHAN1, 30, 200 + 50,
				pointMenu2 == 1 ? menuData[3] : menuData[2],
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 7);// 设置
		GameDraw.add_Image(PAK_IMAGES.IMG_TIAOZHAN1, 30, 280 + 50,
				pointMenu2 == 2 ? menuData[5] : menuData[4],
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 7);// 帮助
		GameDraw.add_Image(PAK_IMAGES.IMG_TIAOZHAN1, 30, 360 + 50,
				pointMenu2 == 3 ? menuData[7] : menuData[6],
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 7);// 关于
		GameDraw.add_Image(PAK_IMAGES.IMG_TIAOZHAN1, 760, 40,
				pointMenu2 == 4 ? menuData[1] : menuData[0],
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 7);// 退出游戏
		GameDraw.add_Image(PAK_IMAGES.IMG_QD1, 750, 420, pointMenu2 == 5 ? 107
				: 0, 0, 107, 103, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 7);// 每日签到

		if (gameStatus != GmStat_Menu) {
			return;
		}// 判断下面的是否需要执行

	}

	/**
	 * 画设置选项
	 */
	public void drawOption() {
		if (lastStatus == GmStat_Stop) {
			engine.drawGame(true);

		}
		if (lastStatus == GmStat_Menu) {
			drawMenubg();
		}

		if (iWhere[1] == 1) {
			// GameDraw.add_Image(PAK_IMAGES.IMG_MENUZI,400,160,Tools.HCENTER_VCENTER,Tools.TRANS_NONE,7+500);
		}

		GameDraw.add_Image(PAK_IMAGES.IMG_MENGBAN, 0, 0, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 7 + 5000);// 背景
		GameDraw.add_Image(PAK_IMAGES.IMG_BEIBAN, 400, 240,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 7 + 5000);// 字背板
		// GameDraw.add_Image(PAK_IMAGES.IMG_YINYUEZI,265,178, Tools.TOP_LEFT,
		// Tools.TRANS_NONE, 7);//字
		GameDraw.add_Image(PAK_IMAGES.IMG_YINXIAOZI, 400, 100,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 7 + 5000);

		GameDraw.add_Image(PAK_IMAGES.IMG_SHENSISHULVAN, 730, 5,
				Tools.TOP_LEFT, Tools.TRANS_NONE, 7 + 5000);// 返回
		if(MyActivity.VMHeight<=240){
			return;
		}
		int[][] yinXiaoData = { { 142, 79, 124, 66 },/* 音乐 */
		{ 142, 4, 129, 63 },/* 音效 */
		{ 6, 13, 122, 52 },/* 音乐开图标 */
		{ 8, 85, 121, 52 },/* 关图标 */
		};
		
		GameDraw.add_Image(PAK_IMAGES.IMG_YINYUEGUAN, 260, 180, yinXiaoData[0],
				Tools.TOP_LEFT, Tools.TRANS_NONE, 7 + 5000);
		GameDraw.add_Image(PAK_IMAGES.IMG_YINYUEGUAN, 260, 260, yinXiaoData[1],
				Tools.TOP_LEFT, Tools.TRANS_NONE, 7 + 5000);

		switch (MyActivity.instance._mView.waf.isMusicEnabled() ? 0 : 1) {// 音乐开关
		case 1:// 关
			GameDraw.add_Image(PAK_IMAGES.IMG_YINYUEGUAN, 420, 180 + 5,
					yinXiaoData[3], Tools.TOP_LEFT, Tools.TRANS_NONE, 7 + 5000);
			break;
		case 0:// 开
			GameDraw.add_Image(PAK_IMAGES.IMG_YINYUEGUAN, 420, 180 + 5,
					yinXiaoData[2], Tools.TOP_LEFT, Tools.TRANS_NONE, 7 + 5000);
			break;
		}
		switch (MyActivity.instance._mView.waf.isSoundEnabled() ? 0 : 1) {// 音xiao开关
		case 1:// 关
			GameDraw.add_Image(PAK_IMAGES.IMG_YINYUEGUAN, 420, 260 + 5,
					yinXiaoData[3], Tools.TOP_LEFT, Tools.TRANS_NONE, 7 + 5000);
			break;
		case 0:// 开
			GameDraw.add_Image(PAK_IMAGES.IMG_YINYUEGUAN, 420, 260 + 5,
					yinXiaoData[2], Tools.TOP_LEFT, Tools.TRANS_NONE, 7 + 5000);
			break;
		}
	}

	/**
	 * 画成就界面
	 * 
	 */
	public void drawAchieve() {
		int chengjiu[] = { PAK_IMAGES.IMG_YIJIBISHA,
				PAK_IMAGES.IMG_HAOBULANGFEI, PAK_IMAGES.IMG_HAOBULANGFEI,
				PAK_IMAGES.IMG_SHENSISHULV, PAK_IMAGES.IMG_SUZHANSUJUE,
				PAK_IMAGES.IMG_ZHIDEYIZHAN, PAK_IMAGES.IMG_JIROUWEI,
				PAK_IMAGES.IMG_FUJIAHAIXIA, PAK_IMAGES.IMG_YUYEDAHENG,
				PAK_IMAGES.IMG_HAISHENZHINU,
				PAK_IMAGES.IMG_ZISHIQILI, PAK_IMAGES.IMG_YIJIBISHAAN,
				PAK_IMAGES.IMG_SHENGCUNZHUANGJIAAN,
				PAK_IMAGES.IMG_HAOBULANGFEIAN, PAK_IMAGES.IMG_SHENSISHULVAN,
				PAK_IMAGES.IMG_SUZHANSUJUEAN, PAK_IMAGES.IMG_ZHIDEYIZHANAN,
				PAK_IMAGES.IMG_JIROUWEIAN, PAK_IMAGES.IMG_FUJIAHAIXIAAN,
				PAK_IMAGES.IMG_YUYEDAHENGAN, PAK_IMAGES.IMG_HAISHENZHINUAN,
				PAK_IMAGES.IMG_JINJISHENGLIAN, PAK_IMAGES.IMG_ZISHIQILIAN,
				PAK_IMAGES.IMG_YIJIBISHAAN, PAK_IMAGES.IMG_YIJIBISHAAN };
		// 一张背景板裁成3分以不同图层画
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBEIJINGBAN, Tools.setOffX,
				Tools.setOffY, 0, 0, 800, 90, Tools.TOP_LEFT, Tools.TRANS_NONE,
				120);
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBEIJINGBAN, Tools.setOffX,
				Tools.setOffY + 90, 0, 90, 800, 345, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 100);
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBEIJINGBAN, Tools.setOffX,
				Tools.setOffY + 435, 0, 435, 800, 50, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 120);

		// GameDraw.add_Image(PAK_IMAGES.IMG_CHENGJIUZI,Tools.setOffX+360,Tools.setOffY+27,
		// Tools.TOP_LEFT,Tools.TRANS_NONE,120);
		// 12条成就
		for (int i = 0; i < 12; i++) {
			GameDraw.add_Image(PAK_IMAGES.IMG_CHENGJIUBEIBAN,
					Tools.setOffX + 396, Tools.setOffY + 115 + 58 * i
							- iAchieveStartY, Tools.HCENTER_VCENTER,
					Tools.TRANS_NONE, 100);
			GameDraw.add_Image(
					chengjiu[i + (engine.iAchieve[i] == 0 ? 12 : 0)],
					Tools.setOffX + 396, Tools.setOffY + 115 + 58 * i
							- iAchieveStartY, Tools.HCENTER_VCENTER,
					Tools.TRANS_NONE, 100);
		}
		GameDraw.add_Image(PAK_IMAGES.IMG_FANHUICAIDAN, Tools.setOffX + 730,
				Tools.setOffY + 10, Tools.TOP_LEFT, Tools.TRANS_NONE, 120);

	}



	/**
	 * 画读秒
	 */
	int dumiao_index = 0;

	/**
	 * 
	 * 画教学时候最后几步的点击屏幕提示
	 */
	int iTeaching;

	void drawTEACHING() {
		GameDraw.add_Image(PAK_IMAGES.IMG_MENGBAN, 0, 0, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 200);
	}

	/**
	 * 画商店教学时候
	 * 
	 */
	void drawTEACHSHOP() {
		GameDraw.add_Image(PAK_IMAGES.IMG_MENGBAN, 0, 0, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 300);
	}

	/*
	 * 画抽奖钱的预览效果
	 */
	int iYuan_Choujiang = 0;

	/*
	 * 画封面动画效果
	 */
	static int kaiji_index = 0;
	static int yinzhang_index = 0;


	/**
	 * 画关卡选择界面
	 * 
	 */
	int tempRank;

	public void drawCatch(int type) {
		int max[] = { 3, 1, 2 };
		GameDraw.add_Image(PAK_IMAGES.IMG_GUANQIABEIJING, 0, 0, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 15);// 背景
		// 画大地图排列2312 312 3123共画11个
		switch (type) {
		case gmScripe:
			tempRank = engine.mapRank;
			break;
		case gmFight:
			tempRank = engine.iFightMapRank;
			break;
		}
		if (tempRank >= 1) {// 画最左边的3
			// GameDraw.add_Image(PAK_IMAGES.IMG_DADITU2,-1060+engine.iCatchMove+engine.iCatchStart,
			// 33, Tools.TOP_LEFT, Tools.TRANS_NONE, 15);
		} else {
			// GameDraw.add_Image(PAK_IMAGES.IMG_DADITU2,-1060+engine.iCatchMove+engine.iCatchStart,
			// 33, Tools.TOP_LEFT, Tools.TRANS_NONE, 15);
			GameDraw.add_Image(PAK_IMAGES.IMG_DADITUSUO, -1060
					+ engine.iCatchMove + engine.iCatchStart, 33,
					Tools.TOP_LEFT, Tools.TRANS_NONE, 15);
		}
		if (tempRank >= 2) {// 画最右边的3
		} else {
			GameDraw.add_Image(PAK_IMAGES.IMG_DADITUSUO, 1640
					+ engine.iCatchMove + engine.iCatchStart, 33,
					Tools.TOP_LEFT, Tools.TRANS_NONE, 15);
		}
		// 画中间的9个
		for (int q = 0; q < 9; q++) {
			if (tempRank >= max[q % 3] - 1) {
				// GameDraw.add_Image(font[q%3],-790+270*q+engine.iCatchMove+engine.iCatchStart,
				// 33, Tools.TOP_LEFT, Tools.TRANS_NONE, 15);
			} else {

				GameDraw.add_Image(PAK_IMAGES.IMG_DADITUSUO, -790 + 270 * q
						+ engine.iCatchMove + engine.iCatchStart, 33,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 15);
			}
		}


		switch (type) {
		case gmScripe:
			// GameDraw.add_ImageScale(PAK_IMAGES.IMG_GUANQIABEIBAN,5,235,
			// Tools.TOP_LEFT, Tools.TRANS_NONE, 15,0.96f,0.7f);//过滤镜框
			switch (engine.iCatchStart / 270) {
			case 0:// 对应大地图1
			case -1:// 对应大地图2
			case -2:// 对应大地图3
			case 1:// 对应大地图3
			case 2:// 对应大地图2
				for (int w = 0; w < 2; w++) {
					for (int i = 0; i < 4; i++) {

						// Tools.TOP_LEFT, Tools.TRANS_NONE, 15);//每一个大地图下的8个小地图
						switch (engine.iResult[((engine.iCatchStart / 270 == 1 || engine.iCatchStart / 270 == 2) ? (engine.iCatchStart / 270 == 1 ? 2
								: 1)
								: Math.abs(engine.iCatchStart) / 270)][(4 * w + i)]) {// 画评价
						case 1:// 0评价，3颗暗星
							GameDraw.add_Image(PAK_IMAGES.IMG_ANXINGXING,
									35 + 160 * i, 310 + 110 * w,
									Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
							GameDraw.add_Image(PAK_IMAGES.IMG_ANXINGXING,
									75 + 160 * i, 290 + 110 * w,
									Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
							GameDraw.add_Image(PAK_IMAGES.IMG_ANXINGXING,
									115 + 160 * i, 310 + 110 * w,
									Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
							break;
						case 2:// 1颗亮星
							GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING,
									35 + 160 * i, 310 + 110 * w,
									Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
							GameDraw.add_Image(PAK_IMAGES.IMG_ANXINGXING,
									75 + 160 * i, 290 + 110 * w,
									Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
							GameDraw.add_Image(PAK_IMAGES.IMG_ANXINGXING,
									115 + 160 * i, 310 + 110 * w,
									Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
							break;
						case 3:// 2颗亮星
							GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING,
									35 + 160 * i, 310 + 110 * w,
									Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
							GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING,
									75 + 160 * i, 290 + 110 * w,
									Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
							GameDraw.add_Image(PAK_IMAGES.IMG_ANXINGXING,
									115 + 160 * i, 310 + 110 * w,
									Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
							break;
						case 4:// 3颗亮星
							GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING,
									35 + 160 * i, 310 + 110 * w,
									Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
							GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING,
									75 + 160 * i, 290 + 110 * w,
									Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
							GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING,
									115 + 160 * i, 310 + 110 * w,
									Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
							break;
						default:// 初始没有任何评价，则表示此关是锁住的
							GameDraw.add_Rect(35 + 160 * i, 250 + 110 * w, 134,
									88, true, Tools.TOP_LEFT, 0x88000000, 17);
						}
					}
				}
				break;
			}
			switch (engine.gameRank / 8) {
			case 0:
				if (engine.iCatchStart / 270 == 0) {

				}
				break;
			case 1:
				if (engine.iCatchStart / 270 == -1
						|| engine.iCatchStart / 270 == 2) {
				}
				break;
			case 2:
				if (engine.iCatchStart / 270 == -2
						|| engine.iCatchStart / 270 == 1) {

				}
				break;
			}
			break;
		case gmFight:
			switch (engine.iFightResult[((engine.iCatchStart / 270 == 1 || engine.iCatchStart / 270 == 2) ? (engine.iCatchStart / 270 == 1 ? 2
					: 1)
					: Math.abs(engine.iCatchStart) / 270)]) {
			case 1:// 0评价，3颗暗星
				GameDraw.add_Image(PAK_IMAGES.IMG_ANXINGXING, 320, 243,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
				GameDraw.add_Image(PAK_IMAGES.IMG_ANXINGXING, 375, 243,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
				GameDraw.add_Image(PAK_IMAGES.IMG_ANXINGXING, 430, 243,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
				break;
			case 2:// 1颗亮星
				GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING, 320, 243,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
				GameDraw.add_Image(PAK_IMAGES.IMG_ANXINGXING, 375, 243,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
				GameDraw.add_Image(PAK_IMAGES.IMG_ANXINGXING, 430, 243,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
				break;
			case 3:// 2颗亮星
				GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING, 320, 243,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
				GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING, 375, 243,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
				GameDraw.add_Image(PAK_IMAGES.IMG_ANXINGXING, 430, 243,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
				break;
			case 4:// 3颗亮星
				GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING, 320, 243,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
				GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING, 375, 243,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
				GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING, 430, 243,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 16);
			default:// 初始没有任何评价，则表示此关是锁住的
			}
			break;
		}

		GameDraw.add_Image(PAK_IMAGES.IMG_FANHUICAIDAN, 735, 5, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 15);
	}

	/**
	 * 处理关卡选择界面滑屏时候的平滑处理
	 */
	int catchNum;
	int catchSlip;

	/**
	 * 关卡选择界面的教学 开启教学
	 */
	int imge[] = { PAK_IMAGES.IMG_XIAOSHOUZHI,
	// PAK_IMAGES.IMG_DASHOUZHI
	};

	void openTeachCatch() {
		if (engine.bTeach == false)
			return;
		if (engine.teachStep == 11) {
			GameDraw.add_ImageRota(imge[(gameTime / 8) % 2], 690, 420,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 300, 0);// 商店图标处画闪烁的手指
		}
		if (engine.teachStep == 12) {// 第二关处画闪烁的手指
			GameDraw.add_ImageRota(imge[(gameTime / 8) % 2], 205, 350,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 300, 0);
		}
		if (engine.teachStep == 13) {// 开始游戏处画闪烁的手指
			GameDraw.add_ImageRota(imge[(gameTime / 8) % 2], 685, 390,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 300, 90);
		}
	}

	/**
	 * 商店界面的教学 开启教学
	 */
	void openTeachShop() {// 715,314 -25 -105 {360,250 ,626,360}
		if (engine.bTeach == false)
			return;
		if (engine.teachStep < 15)
			return;
		if (engine.teachStep == 15) {
			GameDraw.add_ImageRota(imge[(gameTime / 8) % 2], 170, 210,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 300, 270);
		}
		if (engine.teachStep == 16) {
			GameDraw.add_ImageRota(imge[(gameTime / 8) % 2], 620, 440,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 300, 0);
		}
		if (engine.teachStep == 17) {
			GameDraw.add_ImageRota(imge[(gameTime / 8) % 2], 710, 80,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 300, 0);
		}

	}

	/**
	 * 画是否进行教学的判断界面
	 * 
	 */
	void drawEverTeach() {
		GameDraw.add_Rect(0, 0, 800, 480, true, Tools.TOP_LEFT, 0x88000000, 300);
	}

	int shenjiYup = 0;
	int shenjiYdown = 480;
	int countShenJiI;

	void drawdengdai_Win() {
		if (engine.shenli) {
			if (shenjiYup != 240 && shenjiYdown != 240) {
				shenjiYup += 20;
				shenjiYdown -= 20;
			} else {
				// if(++countShenJiI>=45){
				// shenjiYup=0;shenjiYdown=800;countShenJiI=0;engine.shenli=false;
				// }
				countShenJiI++;
				if (countShenJiI / 20 % 2 == 0) {
					GameDraw.add_Image(PAK_IMAGES.IMG_DIANJIJIXU, 400, 360,
							Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2000);//
				}
			}
			if (engine.shenli) {
				if(MyActivity.VMHeight>240){
					GameDraw.add_Image(PAK_IMAGES.IMG_TEXIAO, 400, shenjiYdown,
							Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2000);//
					GameDraw.add_Image(PAK_IMAGES.IMG_SHENLI, 400, shenjiYup,
							Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2000);//
				}

			}

		}

	}

	int counI = -1;
	int countY = -60, rota = 360;
	float scaleX = 0.0f, scaleY = 0.0f;

	void drawDengdai_lose() {
		if (scaleX < 1.0f) {
			scaleX += 0.05;
			scaleY += 0.05;
		}
		if (rota != 0) {
			rota -= 18;
		}

		if (counI == 0) {
			if (countY < 240) {
				countY += 20;
			}
		}
		GameDraw.add_Image(PAK_IMAGES.IMG_LOSE, 400, countY,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2000);//
		if (counI == -1) {
			GameDraw.add_ImageScale2(PAK_IMAGES.IMG_FUHUO, 400, 240,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2000, scaleX,
					scaleY, rota);
			if (rota == 0) {
				GameDraw.drawNumber(PAK_IMAGES.IMG_TISHISHUZI,
						GameEngine.fuhuo, 280, 295, 20, 0, Tools.TOP_LEFT,
						2000, 33, 0, true);// 怪物剩余个数
			}

		}

	}

	/*
	 * 画成功界面
	 */

	public void drawWin() {

		GameDraw.add_Rect(-50, 0, 850, 480, true, Tools.TOP_LEFT, 0x70000000,
				2000);
		GameDraw.add_Image(PAK_IMAGES.IMG_BEIBAN, 400, 240,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2000);

		GameDraw.add_Image(PAK_IMAGES.IMG_WIN, 400, 100, Tools.HCENTER_VCENTER,
				Tools.TRANS_NONE, 2000);
		// GameDraw.add_Image(PAK_IMAGES.IMG_JIROUWEIAN,400,240,Tools.HCENTER_VCENTER,Tools.TRANS_NONE,200);
		int[][] imgdata = { { 24, 3, 183, 43 },/* 关卡选择0 */
		{ 281, 3, 182, 42 },/* 关卡选择灰1 */
		{ 23, 55, 182, 41 },/* 重新开始2 */
		{ 278, 54, 184, 41 },/* 重新开始灰3 */
		{ 43, 103, 134, 42 },/* 下一关4 */
		{ 298, 102, 135, 42 },/* 下一关灰5 */
		{ 8, 152, 228, 69 },/* 蓝框6 */
		{ 248, 152, 229, 70 },/* 蓝框灰7 */
		{ 7, 231, 228, 69 },/* 黄框8 */
		{ 249, 232, 227, 69 },/* 黄框灰9 */
		{ 7, 309, 229, 69 },/* 红框10 */
		{ 247, 309, 230, 69 } /* 红框灰11 */
		};
		GameDraw.add_Image(PAK_IMAGES.IMG_LINGQUZI, 185,
				365 + 20,//
				pointMenu == 0 ? imgdata[7] : imgdata[6],
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2000);

		GameDraw.add_Image(PAK_IMAGES.IMG_LINGQUZI, 615,
				365 + 20, // 按钮框
				pointMenu == 1 ? imgdata[11] : imgdata[10],
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2000);
		
		GameDraw.add_Image(PAK_IMAGES.IMG_YIJIBISHAAN, 188, 365 + 20,// 选择关卡
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2000);

		GameDraw.add_Image(PAK_IMAGES.IMG_YIJIBISHA, 620, 365 + 20,// 下一关
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2000);

		GameDraw.add_Image(PAK_IMAGES.IMG_ZHIDEYIZHANAN, 20 + 100, 170,// 僵尸入侵
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2000);

		GameDraw.drawNumber(PAK_IMAGES.IMG_SHENGMAITASHUZI, engine.countRole,
				170, 150, 28, 0, Tools.TOP_LEFT, 2000, 38, 0, true);

		GameDraw.add_Image(PAK_IMAGES.IMG_BUBING3, 20 + 100, 240,// 金币4
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2000);
		//当前关卡金钱
		GameDraw.drawNumber(PAK_IMAGES.IMG_SHIJIANJIANGLI,
				engine.usBs.rankMoney, 170, 220, 28, 0, Tools.TOP_LEFT, 2000,
				38, 0, true);
		// 画评价星星
		drawWinGot(gmStatus);

	}

	/**
	 * 
	 * 画胜利界面的评价星星
	 */

	public float[] zhang = { 2.0f, 2.0f, 2.0f };

	public void drawWinGot(int status) {
		if (zhang[0] > 1.0f) {
			zhang[0] -= 0.15f;
		}

		if (zhang[0] <= 1.1 && zhang[1] >= 1.0f) {
			zhang[1] -= 0.15f;
		}
		if (zhang[1] <= 1.1 && zhang[2] >= 1.0f) {
			zhang[2] -= 0.15f;
		}

		GameDraw.add_Image(PAK_IMAGES.IMG_ZANTINGANJIAN, 360 - 3, 240,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2001);
		GameDraw.add_Image(PAK_IMAGES.IMG_ZANTINGANJIAN, 500 - 3, 240,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2001);
		GameDraw.add_Image(PAK_IMAGES.IMG_ZANTINGANJIAN, 640 - 3, 240,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2001);

		switch (status) {
		case gmScripe:
			switch (engine.laoJiaHP / 30) {
			case 0:// 1颗亮星
				GameDraw.add_ImageScale(PAK_IMAGES.IMG_JIROUWEIAN, 360 - 3,
						240, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2001,
						zhang[0], zhang[0]);
				break;
			case 1:// 2颗亮星
				GameDraw.add_ImageScale(PAK_IMAGES.IMG_JIROUWEIAN, 360 - 3,
						240, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2001,
						zhang[0], zhang[0]);
				if (zhang[0] <= 1.1f) {
					GameDraw.add_ImageScale(PAK_IMAGES.IMG_JIROUWEIAN, 500 - 3,
							240, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2001,
							zhang[1], zhang[1]);
				}
		
				break;
			case 2:// 3颗亮星
			case 3:
			default:
				GameDraw.add_ImageScale(PAK_IMAGES.IMG_JIROUWEIAN, 360 - 3,
						240, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2001,
						zhang[0], zhang[0]);
				if (zhang[0] <= 1.1f) {
					GameDraw.add_ImageScale(PAK_IMAGES.IMG_JIROUWEIAN, 500 - 3,
							240, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2001,
							zhang[1], zhang[1]);
				}
				if (zhang[1] <= 1.1f) {
					GameDraw.add_ImageScale(PAK_IMAGES.IMG_JIROUWEIAN, 640 - 3,
							240, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2001,
							zhang[2], zhang[2]);
				}

				break;
			}
			break;
		case gmFight:
			switch (engine.iFightResult[engine.iFightGameRank]) {
			case 2:
				GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING, 360, 380,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 201);
				GameDraw.add_Image(PAK_IMAGES.IMG_ANXINGXING, 400, 380,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 201);
				GameDraw.add_Image(PAK_IMAGES.IMG_ANXINGXING, 440, 380,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 201);
				break;
			case 3:
				GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING, 360, 380,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 201);
				GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING, 400, 380,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 201);
				GameDraw.add_Image(PAK_IMAGES.IMG_ANXINGXING, 440, 380,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 201);
				break;
			case 4:
				GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING, 360, 350,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 201);
				GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING, 400, 380,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 201);
				GameDraw.add_Image(PAK_IMAGES.IMG_LIANGXINGXING, 440, 380,
						Tools.TOP_LEFT, Tools.TRANS_NONE, 201);
				break;
			}
			break;
		}

	}

	/*
	 * 画胜利与失败界面
	 */
	public void drawLose() {
		// 画一层灰色蒙版
		GameDraw.add_Rect(0, 0, 800, 480, true, Tools.TOP_LEFT, 0x70000000,
				2000);
		GameDraw.add_Image(PAK_IMAGES.IMG_SMS7, 400, 240,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2000);
	}

	/**
	 * 金币不足
	 */
	void drawGoldNot() {
		GameDraw.add_Image(PAK_IMAGES.IMG_MENGBAN, 0, 0, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 200);
	}

	/**
	 * 画塔的选择界面
	 * 
	 */
	public void drawTowerChoose() {
	}

	/**
	 * 商店的逻辑
	 * 
	 */
	int texiaoDelay;

	public void shopLogic() {
		if (++texiaoDelay == 13) {
			texiaoDelay = 0;
		}
	}

	/**
	 * 画商店 texiaoDelay 用于播放老家动画特效的帧数
	 * 
	 */

	public void drawShop() {
		// 一张背景板裁成3分以不同图层画
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBEIJINGBAN, 0, 0, 0, 0, 62, 480,
				Tools.TOP_LEFT, Tools.TRANS_NONE, 270);
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBEIJINGBAN, 62, 0, 62, 0, 668,
				480, Tools.TOP_LEFT, Tools.TRANS_NONE, 250);
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBEIJINGBAN, 730, 0, 730, 0, 70,
				480, Tools.TOP_LEFT, Tools.TRANS_NONE, 270);

		// 2个选项下的背景框及字
		GameDraw.add_Image((iShopNum == 0 ? PAK_IMAGES.IMG_SHOPBAN1
				: PAK_IMAGES.IMG_SHOPBAN2), 57, 23, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 270);

		// 画拥有的宝石数等
		GameDraw.add_Image(PAK_IMAGES.IMG_SHOPBAN3, 470, 35, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 270);
		// GameDraw.add_Image(PAK_IMAGES.IMG_13,475,41,Tools.TOP_LEFT,Tools.TRANS_NONE,270);
		GameDraw.drawNumber(PAK_IMAGES.IMG_16, iGold, 515, 41, 20, 0,
				Tools.TOP_LEFT, 270, 23, 0, true);
		// 画返回键
		GameDraw.add_Image(PAK_IMAGES.IMG_FANHUICAIDAN, 735, 5, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 270);
		// 画每个选项下对应的各自界面
		if(iShopNum==1)
		{

			GameDraw.add_Image(PAK_IMAGES.IMG_SHANGCHENGFENGEXIAN, 68, 215,
					Tools.TOP_LEFT, Tools.TRANS_NONE, 250);
		}
	

	}

	public int n = -100;
	public int m = 0;


	//

	/*
	 * 
	 * 
	 * 处理游戏中时的所有关于数字的绘制
	 */
	public void DrawNumber()// 用于显示积分和左右连击数、精确度的显�?
	{

	}

	public void drawAll() {
		sort();
		for (int i = 0; i < Tools.max_obj; i++) {
			Tools.drawMe(MyGameView.canvas, MyGameView.paint, Tools.drawObj[i]);
		}
		// System.out.println("    drawAll:"+Tools.curIndex+"    "+Tools.max_obj);
		Tools.max_obj = 0;
		Tools.curIndex = 0;

	}

	private static void sort() {
		int cout = 0;
		int temp;
		int j;
		for (int i = 1; i < Tools.max_obj; i++) {
			if (Tools.drawLevel[Tools.drawObj[i]] < Tools.drawLevel[Tools.drawObj[i - 1]]) {
				temp = Tools.drawObj[i];
				j = i - 1;
				do { // 从右向左在有序区R[1．．i-1]中查找R[i]的插入位�?
						// cout++;
					Tools.drawObj[j + 1] = Tools.drawObj[j]; // 将关键字大于R[i].key的记录后�?
					j--;
					if (j < 0) {
						break;
					}
				} while (Tools.drawLevel[temp] < Tools.drawLevel[Tools.drawObj[j]]); // 当R[i].key≥R[j].key时终�?
				Tools.drawObj[j + 1] = temp;
			}
		}
		// System.out.println(cout);
	}

	static boolean isShanDian = false;
	static int curIndex2;
	static int curIndex1;
	static int a[][] = { { 0, -180, 0, 0, 50 }, { 0, -280, 0, 0, 50 },
			{ 0, -380, 0, 0, 50 }, { 0, -480, 0, 0, 50 },
			{ 0, -580, 0, 0, 50 }, { 0, -680, 0, 0, 50 },
			{ 0, -780, 0, 0, 50 }, { 0, -880, 0, 0, 50 },
			{ 0, -980, 0, 0, 50 }, { 0, -1080, 0, 0, 50 },
			{ 0, -1180, 0, 0, 50 }, { 0, -1280, 0, 0, 50 },
			{ 0, -1480, 0, 0, 50 }, { 0, -1580, 0, 0, 50 },
			{ 0, -1680, 0, 0, 50 }, { 0, -1780, 0, 0, 50 },
			{ 0, -1880, 0, 0, 50 }, { 0, -1980, 0, 0, 50 },
			{ 0, -1080, 0, 0, 50 }, { 0, -2180, 0, 0, 50 },
			{ 0, -2280, 0, 0, 50 }, { 0, -2380, 0, 0, 50 },
			{ 0, -2480, 0, 0, 50 }, { 0, -2580, 0, 0, 50 },
			{ 0, -2680, 0, 0, 50 }, { 0, -2780, 0, 0, 50 },
			{ 0, -2880, 0, 0, 50 }, { 0, -2980, 0, 0, 50 },
			{ 0, -3080, 0, 0, 50 }, { 0, -3180, 0, 0, 50 },

	}; // 0为是否爆炸，1火球依次向上，2为下落帧数，3为爆炸帧，4为下楼时平偏差帧
	static int pc[][] = { { 0, 0 }, { -50, -50 }, { -50, 30 } };

	public static float beishuX;
	public static float beishuY;

	static void drawTX(int x, int y) { // 画火球特效
		for (int i = 0; i < jiNengKaiQi[0]; i++) {
			engine.JN1 = 0;
			if (a[i][1] < 0 && a[i][0] != -1) {

				a[i][1] += 10;
				a[i][2]++;
				if (a[i][1] > -180) {
					a[i][4] -= 5;
				}
//				if(MyActivity.VMHeight>240){
				GameDraw.renderAnimPic22(PAK_IMAGES.IMG_SHANDIAN1,
						(a[i][2] / 3 % 3), x + pc[i % 3][0] + a[i][4], y
								+ a[i][1] + pc[i % 3][1], data_huoqiuTX, false,
						false, 500, 0, 0, 0);
//				}
			}
			if (a[i][1] > -10 && a[i][0] != -1) {
				if (a[i][1] == 0 && a[i][3] == 0) {
					MyActivity.instance._mView.waf.StartWav(10, false);
					for (int r = 0; r < GameEngine.me.enemys.size(); r++) {
						GameRole enemy = (GameRole) GameEngine.me.enemys
								.elementAt(r);

						if (BulletManager.bInCircle(enemy.x, enemy.y, x
								+ pc[i % 3][0] + a[i][4], y + a[i][1]
								+ pc[i % 3][1], 200)) {
							enemy.iEnemyHp -= 50;
							if (enemy.iEnemyHp <= 0) {
								engine.bullm.chekBOO(enemy);
								MyActivity.instance._mView.waf.StartWav(13,
										false);
								engine.bullm.addEffect(100, enemy.x, enemy.y,
										enemy.type);
								enemy.setStatus(GameRole.STATUS_DEAD);
								GameEngine.me.enemys.remove(r);
								if (GameEngine.roleNumber >1) {// roleNumber不等于0则是特殊关卡
										if (GameEngine.roleType != 0) {// roleType！=0则表示有指定怪物类型
											if (GameEngine.roleType == enemy.type) {// 打死的怪物类型和要求类型一样
												GameEngine.roleNumber--;// 要求数量减
											}
										} else {// 没有怪物类型限制的话 数量直接--
											GameEngine.roleNumber--;
										}
								}
							}
						}
					}
				}

				if (a[i][3] / 4 % 7 == 1) {
					GameMap.shakeTime = 4;
				}
				GameMap.screenShake();

				a[i][3]++;
//				if(MyActivity.VMHeight>240){
					GameDraw.renderAnimPic22(PAK_IMAGES.IMG_SHANDIAN1,
						(2 + a[i][3] / 4 % 8), x + pc[i % 3][0] + a[i][4], y
								+ a[i][1] + pc[i % 3][1], data_huoqiuTX, false,
						false, 500, 0, 0, 0);	
//				}

				if (a[i][3] / 4 % 7 == 6) {
					a[i][0] = -1;
					a[i][1] = -180 + (i * -100);
					a[i][2] = 0;
					a[i][3] = 0;
					// Tools.removeImage(PAK_IMAGES.IMG_SHANDIAN1);
				}
			}

			if (a[jiNengKaiQi[0] - 1][0] == -1) {
				for (int j = 0; j < a.length; j++) {
					a[j][0] = 0;
					// a[i][0] =-1;
					a[j][1] = -180 + (j * -100);
					a[j][2] = 0;
					a[j][3] = 0;
					a[j][4] = 50;
				}
				if (EffectV.size() < 1)
					return;
				int size = EffectV.size() - 1;
				// System.out.println("EffectV.size   : " + size);
				for (int j = size; j >= 0; j--) {
					EffectV.removeElementAt(j);
					isShanDian = false;
				}

			}
		}

	}

	int speed2 = 3;
	int index2;
	public static int moveX, moveY, moveZ;
	int[][] KL = new int[35][2];

	void drawTest() {
		index2 += speed2;
		if (index2 > 360) {
			index2 = 0;
		}

		for (int i = 0; i < KL.length; i++) {
			if (index2 == 0) {
				KL[i][0] = 400;
				KL[i][1] = 240;
			}

			// System.out.println("index2 : "
			// + ((10 * i + index2) * engine.PI / 180));
			int x = (int) (Math.cos(((10 * i + index2) * engine.PI / 180)) * 80) + 400;
			int y = (int) (Math.sin((10 * i + index2) * engine.PI / 180) * (10 + moveZ / 10)) + 240;

			GameDraw.add_ImageScale(PAK_IMAGES.IMG_SHANDIAN2, x, y,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 800,
					(float) (1.2 - (float) (+i * 0.2 - 0.3)),
					(float) (1.2 - (i * 0.2 - 0.3)));
		}

	}

	/**
	 * 
	 * Draw动画效果
	 */
	static int font_index_suishi[] = new int[] { 0, 0, 1, 1, 2, 2, 3, 3, 4, 4 };
	static int index_suishi = 0;
	public int x, y;
	public static int font_imgIndex = 0;
	int iAlpha = 255;
	int iAlphaGold = 255;
	float iScale = 1.0f;
	int iStayTime = 40;// 成就停留在屏幕正中央的时间

	// 提示语 一大拨僵尸来了 一关提示一次 出兵点大于等于3才提示
	public static boolean isyiDaBo;

	public static void drawEffect() { // 绘制donghua效果
		if (EffectV.size() < 1)
			return;
		int size = EffectV.size() - 1;
		for (int i = size; i >= 0; i--) {
			int[] temp = (int[]) EffectV.elementAt(i);

			switch (temp[3]) {
			case 106:// 提示语 一大拨僵尸来了
	
				break;
			case 105: // 棺材
				if(MyActivity.VMHeight<=240){return;}
				temp[6]++;
				GameDraw.renderAnimPic22(PAK_IMAGES.IMG_GUANCAI,
						(temp[6] / 5) % 12, temp[0], temp[1] + 20,
						MyGameCanvas.data_GUANCAI, false, false, 6000, 0, 0, 0);
				if (temp[6] == 45) {
					if(MyActivity.VMHeight>240){
						engine.enemys.addElement(new GameRole(
								engine.fd.Guancai[temp[4]][4],
								engine.fd.Guancai[temp[4]][2],
								engine.fd.Guancai[temp[4]][3],
								engine.fd.Guancai[temp[4]][5], temp[4]));
					}else{
						int index=fishData.TYPE_ENEMY_步兵;
						switch (engine.fd.Guancai[temp[4]][4]) {
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
						engine.enemys.addElement(new GameRole(
								index,
								engine.fd.Guancai[temp[4]][2],
								engine.fd.Guancai[temp[4]][3],
								engine.fd.Guancai[temp[4]][5], temp[4]));
					}
					
					

//					break;
				}
				if ((temp[6] / 5) % 12 >= 11) {

					EffectV.remove(i);
				}
				break;
			case 104:// 炮台子弹爆炸效果
				if (MyActivity.VMHeight<=240){return;}
				temp[6]++;
				GameDraw.renderAnimPic22(PAK_IMAGES.IMG_XIAODITU32,
						(temp[6] / 5) % 5, temp[0], temp[1] + 20,
						MyGameCanvas.data_BAOZHA, false, false, 6000, 0, 0, 0);
				if ((temp[6] / 5) % 5 >= 4) {
					EffectV.remove(i);
				}
				break;
			case EFF_爆炸:
				if (MyActivity.VMHeight<=240){return;}
				// System.out.println("EFF_爆炸 : "+temp[0]+":-:"+temp[1]);
				temp[6]++;
				GameDraw.renderAnimPic22(PAK_IMAGES.IMG_XIAODITU32,
						(temp[6] / 5) % 5, temp[0], temp[1] + 20,
						MyGameCanvas.data_BAOZHA, false, false, 1500, 0, 0, 0);
				if (temp[6] == 1) {
					for (int j = 0; j < GameEngine.me.enemys.size(); j++) {
						GameRole enemyS = (GameRole) GameEngine.me.enemys
								.elementAt(j);
						// enemyS.iEnemyHp-=50;
						if ((enemyS.y - temp[1]) < 100
								&& (enemyS.y - temp[1]) > -100
								&& (enemyS.x - temp[0]) < 100
								&& (enemyS.x - temp[0]) > -100) {
							enemyS.iEnemyHp -= 50;
							if (enemyS.iEnemyHp <= 0) {// 怪物死亡
								if (enemyS.TeShuGuaiWu == 3) {
									engine.bullm.addEffect(103, enemyS.x,
											enemyS.y, enemyS.type);
									GameEngine.me.enemys.remove(j);
								}
								if (enemyS.roleStatus != enemyS.STATUS_DEAD) {
									// MyActivity.instance._mView.waf.StartWav(13,
									// false);
									GameEngine.me.role.DropGold(
											GameEngine.me.iWave, enemyS.x,
											enemyS.y, enemyS.type);
									GameEngine.me.usBs.iUsBsCuJinbi += engine.bullm
											.checkJinBi(enemyS.type);
									GameEngine.me.usBs.rankMoney += engine.bullm
											.checkJinBi(enemyS.type);
									enemyS.setStatus(enemyS.STATUS_DEAD);
									engine.bullm.addEffect(100, enemyS.x,
											enemyS.y, enemyS.type);

								}
							}
						}
					}

				}
				if ((temp[6] / 5) % 5 >= 4) {
					// temp[5] = 0;
					// Tools.removeImage(PAK_IMAGES.IMG_XIAODITU32);
					EffectV.remove(i);
				}

				break;
			case EFF_Rank99:
				if (MyActivity.VMHeight<=240){return;}
				if (temp[6] < 250) {
					// temp[6]+=2;
					temp[1] -= 5;
				} else {
					EffectV.remove(i);
				}
				// money
				if (temp[4] == 0) {// jia
					GameDraw.add_ImageAlpha(PAK_IMAGES.IMG_16, temp[0] - 15000,
							temp[1], 220, 0, 20, 23, Tools.TOP_LEFT,
							Tools.TRANS_NONE, 121 + 1000, 255 - (temp[6]));
					GameDraw.drawNumber(PAK_IMAGES.IMG_16, temp[7],
							temp[0] - 15000 + 30, temp[1], 20, 0,
							Tools.TOP_LEFT, 200 + 1000, 23, 0, true);
				} else {// jian
					GameDraw.add_ImageAlpha(PAK_IMAGES.IMG_16, temp[0] - 15000,
							temp[1], 200, 0, 20, 23, Tools.TOP_LEFT,
							Tools.TRANS_NONE, 121 + 1000, 255 - (temp[6]));
					GameDraw.drawNumber(PAK_IMAGES.IMG_16, temp[7],
							temp[0] - 15000 + 30, temp[1], 20, 0,
							Tools.TOP_LEFT, 200 + 6000, 23, 0, true);

				}
				if(MyActivity.VMHeight>240){
				GameDraw.add_ImageAlpha(checkJinbi(temp[6]), temp[0], temp[1]
						- (121 - temp[5]), Tools.HCENTER_VCENTER,
						Tools.TRANS_NONE, 121 + 1000, 255 - (150 - temp[5]));
				}
				break;
			case EFF_TOWERBUILD:// 造塔特效
				if (MyActivity.VMWidth <= 320) {
					EffectV.removeElementAt(i);
					if (engine.teachStep == 10) {
						setST(GmStat_TEACHING);
					}
					return;
				}
	
				int mmotion[] = { 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4 };
				if (++temp[2] == mmotion.length) {
					EffectV.removeElementAt(i);
					if (engine.teachStep == 10) {
						setST(GmStat_TEACHING);
					}
				}
				break;
			case EFF_UPLEVEL:// 卖掉塔的时候,冒烟雾特效
				int uplevel[][] = { { 20, 8, 50, 42 }, { 132, 6, 54, 50 },
						{ 244, 3, 64, 57 }, { 355, 1, 70, 63 },
						{ 468, 0, 77, 67 }, { 3, 112, 82, 71 },
						{ 117, 115, 86, 70 }, { 230, 114, 89, 72 },
						{ 345, 110, 90, 77 }, { 460, 112, 90, 74 },
						{ 0, 226, 90, 78 }, { 121, 226, 82, 77 },
						{ 235, 226, 85, 80 }, { 350, 223, 85, 84 } };
				// GameDraw.add_Image(PAK_IMAGES.IMG_SHENGMAITATEXIAO,
				// temp[0],temp[1],uplevel[temp[2]],
				// Tools.HCENTER_VCENTER,Tools.TRANS_NONE, 800);
				if (++temp[2] == uplevel.length) {
					EffectV.removeElementAt(i);
				}
				break;
			case EFF_SHANDIANDIS:
				me.iScale += 0.02f;
				me.iAlpha -= 8;
				if (me.iAlpha <= 0) {
					EffectV.removeElementAt(i);
					me.iScale = 1.0f;
					me.iAlpha = 255;
				}
				break;
			case EFF_LEAVEGOLD:
				int leaveGold[][] = { { 0, 0, 67, 39 }, { 0, 39, 67, 40 },
						{ 0, 79, 67, 40 }, { 0, 119, 67, 40 },
						{ 0, 159, 67, 40 }, { 0, 200, 67, 40 },
						{ 108, 0, 65, 39 }, { 108, 39, 65, 39 },
						{ 108, 80, 102, 40 }, { 108, 120, 102, 40 },
						{ 108, 160, 102, 40 }, { 108, 200, 102, 40 } };
				me.iAlphaGold -= 10;
				if (++temp[2] >= 20) {
					EffectV.removeElementAt(i);
					me.iAlphaGold = 255;
				}
				break;
			case EFF_GONGXIHUODE:
				if (MyActivity.VMHeight<=240){return;}
				int imgpride[] = { PAK_IMAGES.IMG_JIANGPIN1,
						PAK_IMAGES.IMG_JIANGPIN2, PAK_IMAGES.IMG_JIANGPIN3,
						PAK_IMAGES.IMG_JIANGPIN4, PAK_IMAGES.IMG_JIANGPIN5,
						PAK_IMAGES.IMG_JIANGPIN6, PAK_IMAGES.IMG_JIANGPIN7,
						PAK_IMAGES.IMG_JIANGPIN8, };
				//
				GameDraw.add_ImageScaleAlpha(PAK_IMAGES.IMG_GONGXIHUODE,
						temp[0], temp[1] - 60, Tools.HCENTER_VCENTER,
						Tools.TRANS_NONE, 5010, me.iScale - 0.5f,
						me.iScale - 0.5f, me.iAlpha);
				GameDraw.add_ImageScaleAlpha(imgpride[engine.iGetPride - 1],
						temp[0], temp[1], Tools.HCENTER_VCENTER,
						Tools.TRANS_NONE, 5010, me.iScale - 0.5f,
						me.iScale - 0.5f, me.iAlpha);
				me.iScale += 0.02f;
				me.iAlpha -= 4;
				if (me.iScale >= 1.20f) {
					me.iScale = 1.20f;
				}
				if (me.iAlpha == 203) {
					AddBlastEffectList(392, 235, EFF_SAHUA, 0, 120, 0);
				}
				if (me.iAlpha <= 0) {
					EffectV.removeElementAt(i);
					me.iScale = 1.0f;
					me.iAlpha = 255;
					if (engine.bTeach == true && engine.teachStep < 8) {
						engine.teachStep = 8;
					}
					setST(GmStat_Playing);
					engine.resetData();
				}
				break;
			case EFF_SAHUA:
				int sahuaMotion[] = { 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4,
						4, 4, 5, 5, 5, 6, 6, 6, 6 };
				if (++temp[2] == sahuaMotion.length) {
					EffectV.removeElementAt(i);
				}
				break;
			case EFF_NOTOPEN:// 挑战功能尚未开启
				// int shangweikaiqi[]={PAK_IMAGES.IMG_SHANGWEIKAIQI};
				int motton[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
				if (++temp[2] == motton.length) {
					EffectV.removeElementAt(i);
				}
				break;
			case EFF_FISHDIS:
				int fishdis[][] = { { 24, 38, 74, 66 }, { 148, 40, 80, 66 },
						{ 264, 24, 106, 96 }, { 406, 14, 120, 112 },
						{ 2, 152, 134, 116 }, { 196, 162, 139, 118 },
						{ 412, 147, 120, 131 } };
				GameDraw.add_Image(PAK_IMAGES.IMG_FISHDIS, temp[0], temp[1],
						fishdis[temp[2]], Tools.HCENTER_VCENTER,
						Tools.TRANS_NONE, 200 + 1000);
				if (++temp[2] == fishdis.length) {
					EffectV.removeElementAt(i);
				}
				break;
			case EFF_SKILL_SHANDIAN:
				int shandian[] = { PAK_IMAGES.IMG_SHANDIAN1,
						PAK_IMAGES.IMG_SHANDIAN2, PAK_IMAGES.IMG_SHANDIAN3,
						PAK_IMAGES.IMG_SHANDIAN4, PAK_IMAGES.IMG_SHANDIAN5,
				// PAK_IMAGES.IMG_SHANDIAN6
				};

				int shandian_motion[] = { 0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 4,
						5, 5 };
				// System.out.println("iSkillKind  : "+engine.iSkillKind);
				if(engine.iSkillKind==0)
					isShanDian = true;
	
				if (temp[2] == 10) {
					engine.checkCrash(temp[0], temp[1], engine.iSkillKind);
				}
				if (++temp[2] == shandian_motion.length) {
					// EffectV.removeElementAt(i);
				}
				break;
			case EFF_SKILL_SHUANGDONG:
				int skill_bingjing[][] = { { 15, 131, 70, 67 } };
				int motion[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
				temp[0] -= 3;
				temp[1] += 15;
				if (++temp[2] == motion.length) {
					EffectV.removeElementAt(i);
				}
				for (int m = 0; m < engine.enemys.size(); m++) {
					GameRole enemy = (GameRole) engine.enemys.elementAt(m);
					enemy.roleStatus = GameRole.STATUS_STOP;
				}

				break;
			case EFF_GOLDFISHDEAD:
				break;
			case EFF_WAVETISHI:
				if (MyActivity.VMHeight<=240){return;}
				int wavetishi[] = { PAK_IMAGES.IMG_TISHIBOSHU1 };
				int motion_wave[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
				GameDraw.add_Image(wavetishi[motion_wave[temp[2]]], 400, 240,
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 200);
				GameDraw.drawNumber(PAK_IMAGES.IMG_TISHIBOSHU,
						engine.iWave + 1, 395, 220, 40, 0, Tools.TOP_LEFT,
						200 + 1000, 53, 0, true);
				if (++temp[2] == motion_wave.length) {
					EffectV.removeElementAt(i);
					engine.iWaveTime = 90;
				}
				break;
			case EFF_UNLAWDEADBIG:
				if (MyActivity.VMHeight<=240){return;}
				int bigdead[][] = { { 0, 20, 122, 90 }, { 135, 24, 115, 81 },
						{ 275, 23, 135, 91 }, { 424, 15, 150, 101 },
						{ 9, 154, 48, 47 }, { 80, 132, 62, 73 },
						{ 173, 136, 62, 80 }, { 272, 138, 61, 74 },
						{ 372, 136, 58, 78 } };
				// GameDraw.add_Image(PAK_IMAGES.IMG_SHUIJINGDA1,temp[0],temp[1],bigdead[temp[2]],
				// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,119);
				if (++temp[2] == bigdead.length) {
					EffectV.removeElementAt(i);
				}
				break;
			case EFF_ZAOTA_NOT:
				break;
			case EFF_ZIDAN1:// 英雄塔子弹效果
				int zidanxiaoguo[][] = { { 0, 20, 37, 37 }, { 45, 7, 64, 64 },
						{ 118, 0, 82, 82 } };
				// GameDraw.add_Image(PAK_IMAGES.IMG_HEROXIAOGUO,temp[0],temp[1],zidanxiaoguo[temp[2]],
				// Tools.HCENTER_VCENTER, Tools.TRANS_NONE,132);
				if (++temp[2] == zidanxiaoguo.length) {
					EffectV.removeElementAt(i);
				}
				break;
			case EFF_ZIDAN2:// 鱼雷,猎鱼跑效果
				if (MyActivity.VMHeight<=240){return;}
				int zidanxiaoguo2[][] = { { 24, 20, 64, 68 },
						{ 110, 15, 85, 85 }, { 212, 16, 92, 92 },
						{ 314, 7, 106, 103 } };
				GameDraw.add_Image(PAK_IMAGES.IMG_YULEIPAOXIAOGUO, temp[0],
						temp[1], zidanxiaoguo2[temp[2]], Tools.HCENTER_VCENTER,
						Tools.TRANS_NONE, 132);
				if (++temp[2] == zidanxiaoguo2.length) {
					EffectV.removeElementAt(i);
				}
				break;
			case EFF_ZIDAN3:// 冰晶炮特效
				if (MyActivity.VMHeight<=240){return;}
				int zidanxiaoguo3[][] = { { 0, 4, 59, 56 }, { 67, 2, 69, 68 },
						{ 146, 2, 86, 79 }, { 238, 2, 94, 90 } };
				GameDraw.add_Image(PAK_IMAGES.IMG_BINGJINGPAOTEXIAO, temp[0],
						temp[1], zidanxiaoguo3[temp[2]], Tools.HCENTER_VCENTER,
						Tools.TRANS_NONE, 132);
				if (++temp[2] == zidanxiaoguo3.length) {
					EffectV.removeElementAt(i);
				}
				break;
			case EFF_ZIDAN4:// 电网跑效果
				int zidanxiaoguo4[][] = { { 0, 24, 70, 70 }, { 0, 24, 70, 70 },
						{ 0, 24, 70, 70 }, { 78, 4, 106, 104 },
						{ 78, 4, 106, 104 }, { 78, 4, 106, 104 },
						{ 192, 0, 124, 117 }, { 192, 0, 124, 117 },
						{ 192, 0, 124, 117 } };
				// GameDraw.add_Image(PAK_IMAGES.IMG_DIANWANGPAOXIAOGUO,temp[0],temp[1],zidanxiaoguo4[temp[2]],
				// Tools.HCENTER_VCENTER, Tools.TRANS_NONE,132);
				if (++temp[2] == zidanxiaoguo4.length) {
					EffectV.removeElementAt(i);
				}
				break;
			case EFF_ZIDAN5:// 火炮效果
				int zidanxiaoguo5[][] = { { 0, 2, 30, 54 }, { 33, 2, 27, 56 },
						{ 62, 0, 28, 62 }, { 92, 1, 27, 56 } };
	
				if (++temp[2] == zidanxiaoguo5.length) {
					EffectV.removeElementAt(i);
				}
				break;
			case EFF_DROPGOLD:// 怪物掉落的金币往（0,0）的位置跑，达到位置消失
				if (MyActivity.VMHeight<=240){return;}
				int dropgold[][] = { { 1, 12, 17, 23 },/* 图片说明 */
				{ 1, 12, 17, 23 },/* 图片说明 */
				{ 1, 12, 17, 23 },/* 图片说明 */
				{ 4, 44, 13, 24 },/* 图片说明 */
				{ 4, 44, 13, 24 },/* 图片说明 */
				{ 4, 44, 13, 24 },/* 图片说明 */
				{ 2, 76, 17, 23 },/* 图片说明 */
				{ 2, 76, 17, 23 },/* 图片说明 */
				{ 2, 76, 17, 23 },/* 图片说明 */
				{ 0, 107, 22, 24 }, { 0, 107, 22, 24 }, { 0, 107, 22, 24 },

				};/* 图片说明 */

	
				GameDraw.add_ImageAlpha(PAK_IMAGES.IMG_DROPGOLD, temp[0],
						temp[1], dropgold[temp[2]][0], dropgold[temp[2]][1],
						dropgold[temp[2]][2], dropgold[temp[2]][3],
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 121 + 1000,
						255 - (121 - temp[5]));

				GameDraw.add_ImageAlpha(checkJinbi(temp[6]), temp[0], temp[1]
						- (121 - temp[5]), Tools.HCENTER_VCENTER,
						Tools.TRANS_NONE, 121 + 1000, 255 - (150 - temp[5]));

				if (temp[0] >= 15 && temp[1] >= 15) {
					int distance = BulletManager.GetDistance(temp[0], temp[1],
							0, 0);

				}
				if (++temp[2] == dropgold.length) {
					temp[2] = 0;
					// engine.usBs.iUsBsCuJinbi-=engine.getLaiJiaDJ(ZB[engine.usBs.iUsBsRank-1]);
				}
				if ((temp[5] -= 5) <= 5) {

					EffectV.removeElementAt(i);
				}
				if (temp[0] < 10 || temp[1] < 10) {
					EffectV.removeElementAt(i);
				}
				break;

			case EFF_HUANPAO:// 攻击核心效果
	
				break;
			case EFF_START:
				// int start[]={PAK_IMAGES.IMG_CHONGFUJIAOXUE};
				int motionStart[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	
				if (++temp[2] == motionStart.length) {
					EffectV.removeElementAt(i);
				}
				break;
			case EFF_眩晕:
				int[][] index = { { 7, 8, 29, 19 },/* 图片说明 */
				{ 6, 27, 30, 19 } };/* 图片说明 */
		
				if (++temp[2] == 70) {
					EffectV.removeElementAt(i);
				}
				break;
			case EFF_停顿:

				if (++temp[2] == 30) {
					EffectV.removeElementAt(i);
				}

				break;
			case EFF_死亡:
				if (MyActivity.VMHeight<=240){return;}
				int siwang[] = { 16, 17, 17, 17, 18, 18, 19, 19, 19, 20, 20,
						20, 21, 21, 22, 22, 22, 23, 23, 23, 23, 24, 24, 24, 24,
						24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24,
						24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24,
						24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24 };
				int PZsiwang[] = { 16, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17,
						17, 18, 18, 18, 18, 18, 18, 19, 19, 19, 19, 19, 19, 19,
						19, 19, 20, 20, 20, 20, 20, 20, 21, 21, 21, 21, 21, 21,
						21, 21, 21, 21, 21, 21, 21, 21, 21 };
				// 怪物受伤效果
				if (temp[6] == GameRole.TYPE_ENEMY_光头) {
					if (temp[2] == 2) {
						GameMap.shakeTime = 10;
					}
					GameMap.screenShake();

					GameDraw.renderAnimPic22(
							PAK_IMAGES.IMG_SHENSISHULV,
							PZsiwang[temp[2] >= PZsiwang.length - 1 ? PZsiwang.length - 1
									: temp[2]], temp[0] + 10, temp[1] + 50,
							MyGameCanvas.data_role_dog, false, false,
							220 + 1000, 0, 0, 0);
				} else {
					GameDraw.renderAnimPic22(
							PAK_IMAGES.IMG_FUJIAHAIXIAAN,
							siwang[temp[2] >= siwang.length - 1 ? siwang.length - 1
									: temp[2]], temp[0], temp[1] + 50,
							MyGameCanvas.data_siwang, false, false, 220 + 1000,
							0, 0, 0);
				}

				if (++temp[2] == (temp[6] != GameRole.TYPE_ENEMY_光头 ? siwang.length
						: PZsiwang.length)) {
					EffectV.removeElementAt(i);
				}
				break;
			case EFF_LAOJIA1ZIDAN:
				if (MyActivity.VMHeight<=240){return;}
				int zidan1[][] = { { 0, 15, 36, 33 }, { 66, 2, 70, 51 },
						{ 158, 0, 88, 65 }, { 267, 0, 87, 66 } };
				// int
				// zidan1[][]={{5,5,48,48},{72,0,68,62},{144,0,68,60},{218,0,70,60}};
				int zidan1motion[] = { 26, 26, 27, 27, };
				// 怪物受伤效果
				if (temp[6] > 340 || temp[6] < 20) {
					GameDraw.renderAnimPic22(PAK_IMAGES.IMG_FUJIAHAIXIAAN,
							zidan1motion[temp[2] >= 5 ? zidan1motion.length - 1
									: temp[2]], temp[0], temp[1],
							MyGameCanvas.data_siwang, false, false, 220 + 1000,
							0, 0, temp[6]);
				} else {
					GameDraw.renderAnimPic22(
							PAK_IMAGES.IMG_FUJIAHAIXIAAN,
							zidan1motion[temp[2] >= 5 ? zidan1motion.length - 1
									: temp[2]],
							temp[0] > SCREEN_WIDTH / 2 ? temp[0] + 20
									: temp[0] - 30,
							temp[0] > SCREEN_WIDTH / 2 ? temp[1] : temp[1] + 20,
							MyGameCanvas.data_siwang, false, false, 220 + 1000,
							0, 0, temp[6]);
				}


				if (++temp[2] == zidan1motion.length) {
					EffectV.removeElementAt(i);
				}
				break;

			case EFF_LAOJIA2ZIDAN:
				if (MyActivity.VMHeight<=240){return;}
				int zidan2[][] = { { 0, 15, 36, 33 }, { 66, 2, 70, 51 },
						{ 158, 0, 88, 65 }, { 267, 0, 87, 66 } };
				int zidan2motion[] = { 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4,
						4, 4, 5, 5, 5 };
				GameDraw.renderAnimPic22(PAK_IMAGES.IMG_FUJIAHAIXIA,
						zidan2motion[temp[2] >= 20 ? 20 : temp[2]],
						temp[0] + 20, temp[1] + 50,
						MyGameCanvas.data_xihongshibaozha, false, false,
						220 + 1000, 0, 0, 0);
				if (temp[2] == 1) {
					for (int r = 0; r < GameEngine.me.enemys.size(); r++) {
						GameRole enemy = (GameRole) GameEngine.me.enemys
								.elementAt(r);
						if (BulletManager.bInCircle(enemy.x, enemy.y, temp[0],
								temp[1], 80)) {
							for (int j = 0; j < ZBShuXing[ZB[engine.usBs.iUsBsRank - 1]].length; j++) {

								if (ZBShuXing[ZB[engine.usBs.iUsBsRank - 1]][j] == 3) {// 添加暴击
									int random = GameRandom.result(0, 150);// 随机数
									if (random < (10 + jiNengKaiQi[6])) {
										enemy.iEnemyHp -= engine.usBs.iUsBsCurAttack * 2;
									} else {
										enemy.iEnemyHp -= engine.usBs.iUsBsCurAttack;
									}

								} else {
								}
							}

							if (enemy.iEnemyHp <= 0) {

								MyActivity.instance._mView.waf.StartWav(13,
										false);
								GameEngine.me.role.DropGold(
										GameEngine.me.iWave, enemy.x, enemy.y,
										enemy.type);
								GameEngine.me.role.DropGold(
										GameEngine.me.iWave, enemy.x, enemy.y,
										enemy.type);
								engine.bullm.addEffect(100, enemy.x, enemy.y,
										enemy.type);
								GameEngine.me.usBs.iUsBsCuJinbi += engine.bullm
										.checkJinBi(enemy.type);
								GameEngine.me.usBs.rankMoney += engine.bullm
										.checkJinBi(enemy.type);
								GameEngine.me.enemys.remove(r);

								if (GameEngine.roleNumber > 1) {// roleNumber不等于0则是特殊关卡
										if (GameEngine.roleType != 0) {// roleType！=0则表示有指定怪物类型
											if (GameEngine.roleType == enemy.type) {// 打死的怪物类型和要求类型一样
												GameEngine.roleNumber--;// 要求数量减
											}
										} else {// 没有怪物类型限制的话 数量直接--
											GameEngine.roleNumber--;
										}
								}
								// for (int j = 0; j <
								// MyGameCanvas.EffectV.size(); j++) {
								// MyGameCanvas.EffectV.removeElementAt(j);
								// }
							}
						}
					}
				}

				if (++temp[2] == zidan2motion.length) {
					EffectV.removeElementAt(i);
				}
				break;
			case EFF_LAOJIA3ZIDAN:
				if (MyActivity.VMHeight<=240){return;}
				int zidan3[][] = { { 5, 5, 48, 48 }, { 72, 0, 68, 62 },
						{ 144, 0, 68, 60 }, { 218, 0, 70, 60 } };
				int zidan3motion[] = { 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3 };
				GameDraw.add_Image(PAK_IMAGES.IMG_LAOJIA3ZIDANXIAOGUO, temp[0],
						temp[1], zidan3[zidan3motion[temp[2]]],
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 220 + 5000);
				if (++temp[2] == zidan3motion.length) {
					EffectV.removeElementAt(i);
				}
				break;
			case EFF_LAOJIA4ZIDAN:
				if (MyActivity.VMHeight<=240){return;}
				int zidan4[][] = { { 5, 5, 48, 48 }, { 72, 0, 68, 62 },
						{ 144, 0, 68, 60 }, { 218, 0, 70, 60 } };
				int zidan4motion[] = { 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3 };
				GameDraw.add_Image(PAK_IMAGES.IMG_LAOJIA4ZIDANXIAOGUO, temp[0],
						temp[1], zidan4[zidan4motion[temp[2]]],
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 132 + 100);
				if (++temp[2] == zidan4motion.length) {
					EffectV.removeElementAt(i);
				}
				break;
			case EFF_LAOJIA5ZIDAN:
				if (MyActivity.VMHeight<=240){return;}
				int zidan5[][] = { { 0, 12, 44, 40 }, { 66, 2, 66, 60 },
						{ 135, 0, 73, 62 }, { 208, 0, 76, 62 } };
				int zidan5motion[] = { 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3 };
				GameDraw.add_Image(PAK_IMAGES.IMG_LAOJIA5ZIDANXIAOGUO, temp[0],
						temp[1], zidan5[zidan5motion[temp[2]]],
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 132 + 100);
				if (++temp[2] == zidan5motion.length) {
					EffectV.removeElementAt(i);
				}
				break;
			case EFF_GETACHIEVE://
				if (MyActivity.VMHeight<=240){return;}
				// GameDraw.add_Image(PAK_IMAGES.IMG_GETCHENGJIU,temp[0],temp[1],
				// Tools.HCENTER_VCENTER,Tools.TRANS_NONE,380);
				switch (engine.iAchieveNum) {
				case 1:
					GameDraw.add_Image(PAK_IMAGES.IMG_YIJIBISHA, temp[0],
							temp[1] + 10, Tools.HCENTER_VCENTER,
							Tools.TRANS_NONE, 381);
					break;
				case 2:
					// GameDraw.add_Image(PAK_IMAGES.IMG_SHENGCUNZHUANJIA,
					// temp[0], temp[1] + 10, Tools.HCENTER_VCENTER,
					// Tools.TRANS_NONE, 381);
					break;
				case 3:
					GameDraw.add_Image(PAK_IMAGES.IMG_HAOBULANGFEI, temp[0],
							temp[1] + 10, Tools.HCENTER_VCENTER,
							Tools.TRANS_NONE, 381);
					break;
				case 4:
					GameDraw.add_Image(PAK_IMAGES.IMG_SHENSISHULV, temp[0],
							temp[1] + 10, Tools.HCENTER_VCENTER,
							Tools.TRANS_NONE, 381);
					break;
				case 5:
					GameDraw.add_Image(PAK_IMAGES.IMG_SUZHANSUJUE, temp[0],
							temp[1] + 10, Tools.HCENTER_VCENTER,
							Tools.TRANS_NONE, 381);
					break;
				case 6:
					GameDraw.add_Image(PAK_IMAGES.IMG_ZHIDEYIZHAN, temp[0],
							temp[1] + 10, Tools.HCENTER_VCENTER,
							Tools.TRANS_NONE, 1381);
					break;
				case 7:
					GameDraw.add_Image(PAK_IMAGES.IMG_JIROUWEI, temp[0],
							temp[1] + 10, Tools.HCENTER_VCENTER,
							Tools.TRANS_NONE, 381);
					break;
				case 8:
					GameDraw.add_Image(PAK_IMAGES.IMG_FUJIAHAIXIA, temp[0],
							temp[1] + 10, Tools.HCENTER_VCENTER,
							Tools.TRANS_NONE, 181);
					break;
				case 9:
					GameDraw.add_Image(PAK_IMAGES.IMG_YUYEDAHENG, temp[0],
							temp[1] + 10, Tools.HCENTER_VCENTER,
							Tools.TRANS_NONE, 381);
					break;
				case 10:
					GameDraw.add_Image(PAK_IMAGES.IMG_HAISHENZHINU, temp[0],
							temp[1] + 10, Tools.HCENTER_VCENTER,
							Tools.TRANS_NONE, 381);
					break;
				case 11:
//					GameDraw.add_Image(PAK_IMAGES.IMG_JINJISHENGLI, temp[0],
//							temp[1] + 10, Tools.HCENTER_VCENTER,
//							Tools.TRANS_NONE, 381);
					break;
				case 12:
					GameDraw.add_Image(PAK_IMAGES.IMG_ZISHIQILI, temp[0],
							temp[1] + 10, Tools.HCENTER_VCENTER,
							Tools.TRANS_NONE, 381);
					break;
				}
				if (temp[1] > 240) {
					temp[1] -= 5;
				} else if (temp[1] == 240) {
					me.iStayTime--;
					if (me.iStayTime == 0) {
						me.iStayTime = 40;
						EffectV.removeElementAt(i);
					}
				}
				break;

			}
		}
	}

	static int checkJinbi(int type) {
		int index = 0;
		switch (type) {
		case GameRole.TYPE_ENEMY_步兵:
			index = 0;
			break;
		case GameRole.TYPE_ENEMY_牧师:
			index = 1;
			break;
		case GameRole.TYPE_ENEMY_刺客:
			index = 3;
			break;
		case GameRole.TYPE_ENEMY_光头:
			index = 2;
			break;
		case GameRole.TYPE_ENEMY_鹰:
			index = 4;
			break;
		case GameRole.TYPE_ENEMY_石盾:
			index = 5;
			break;
		case GameRole.TYPE_ENEMY_金盾:
			index = 5;
			break;
		case GameRole.TYPE_ENEMY_绿盾:
			index = 6;
			break;
		case GameRole.TYPE_ENEMY_紫盾:
			index = 7;
			break;
		case GameRole.TYPE_ENEMY_暗牧:
			index = 7;
			break;
		}
		int jingbi[] = {// 2,5,45,
		PAK_IMAGES.IMG_JINJISHENGLIAN, PAK_IMAGES.IMG_HAOBULANGFEI,
				PAK_IMAGES.IMG_HAOBULANGFEIAN, PAK_IMAGES.IMG_7,
				PAK_IMAGES.IMG_8, PAK_IMAGES.IMG_15, PAK_IMAGES.IMG_19,
				PAK_IMAGES.IMG_24, };
		return jingbi[index];
	}

	/**
	 * 添加动画效果
	 * 
	 * @param x
	 * @param y
	 * @param type
	 * @param isMirror
	 * @param layer
	 * @param color
	 */
	public static void AddBlastEffectList(int x, int y, byte type,
			int isMirror, int layer, int color) {
		EffectV.addElement(new int[] { x, y, 0, type, isMirror, layer, color }); // 添加碰撞效果集合
	}

	public static void AddBlastEffectList2(int x, int y, byte type,
			int isMirror, int layer, int color, int money) {
		EffectV.addElement(new int[] { x, y, 0, type, isMirror, layer, color,
				money }); // 添加碰撞效果集合
	}

	// 加载画面的时候,随机给予的小说明
	// static int loadIndex = 0;
	public int checkRole;
	int delay;
	int fishx;
	int fishy;
	int zidanX;
	int in;

	public int random;

	public void drawLoad() {
		int[] tiShi = { PAK_IMAGES.IMG_TIESHI1, PAK_IMAGES.IMG_TIESHI2,
				PAK_IMAGES.IMG_TIESHI3, PAK_IMAGES.IMG_TIESHI4,
				PAK_IMAGES.IMG_TIESHI5, PAK_IMAGES.IMG_TIESHI6,
				PAK_IMAGES.IMG_TIESHI7, PAK_IMAGES.IMG_TIESHI8,
				PAK_IMAGES.IMG_TIESHI9 };
		// System.out.println("load:" + index);
		engine.initGame(index);
		GameDraw.add_Rect(0, 0, 800, 480, true, Tools.TOP_LEFT, 0x88000000, 5);
		if (index > 40) {
			GameDraw.add_Image(tiShi[random], SCREEN_WIDTH / 2,
					SCREEN_HEIGHT / 2, Tools.HCENTER_VCENTER, Tools.TRANS_NONE,
					5);

			GameDraw.add_Image(PAK_IMAGES.IMG_LOADING3, 0, 400, 0, 0,
					index * 2 + 20, 20, Tools.TOP_LEFT, Tools.TRANS_NONE, 5);
			// drawLoad_Dog();
		}

		if ((index += 2) >= 420) {// 一圈180
			in = 0;
			index = 0;
			zidanX = 0;
	
			MyActivity.instance._mView.waf.StartBackMusic(0, true);
			setST(GmStat_Playing);
			// }
			Tools.removeAllImage();
			if (jiNengKaiQi[2] != -1) {
				Tools.getImage(PAK_IMAGES.IMG_YING3);
			}

		
		}
		// Tools.removeAllImage();
	}

	static int load2;

	public void drawLoad2() {
		Tools.removeAllImage();
		setST(lastStatus2);

	}

	/**
	 * 询问玩家是否进入奖励关卡
	 */
	void drawJiangLi() {
		GameDraw.add_Image(PAK_IMAGES.IMG_BEIBAN, 400, 240,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 5000);
		if (gameTime / 30 % 3 != 0) {
			GameDraw.add_Image(PAK_IMAGES.IMG_JIANGLI, 400, 240,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 5000);
		}

	}

	void drawLoad_Dog() {
		if (index * 2 < 460 + 300) {
			GameDraw.renderAnimPic22(PAK_IMAGES.IMG_SHENSISHULV,
					28 + index / 10 % 4, 0 + index * 2, 380, data_role_dog,
					false, false, 50, 0, 0, 0);
			if (index * 2 == 400 + 360) {
				MyActivity.instance._mView.waf.StartWav(13, false);
			}
		}
		if (index * 2 >= 420 + 250) {

			zidanX += 40;

			GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI13, 0 + zidanX, 360,
					371, 1, 33, 97, Tools.HCENTER_VCENTER, Tools.TRANS_NONE,
					132, 90);
			if (zidanX == 40) {
				MyActivity.instance._mView.waf.StartWav(2, false);
			}
		}
		if (zidanX >= 450 + 300) {
			if (in < 23) {
				in++;

			}

			// GameDraw.renderAnimPic22(PAK_IMAGES.IMG_FUJIAHAIXIAAN, in / 3 %
			// 8,
			// 450 + 300, 380, MyGameCanvas.data_siwang, false, false,
			// 220, 0, 0, 0);
		}
	}

	/**
	 * 加载界面的转圈圈的鱼
	 * 
	 * @param r
	 * @param a
	 * @param b
	 * @param delay
	 */
	float jd = 0;
	int diRen;

	void loadLogic(int r, int a, int b) {
		if(engine.gameRank<0&&engine.gameRank>=24){
			if(engine.gameRank!=99){
				engine.gameRank=23;
			}
		}
		engine.JN1 = 0;
		engine.JN2 = 0;
		engine.JN3 = 0;
		engine.checkYZD = 0;
		engine.countYZD_y = 0;
		jd -= 2;
		if (jd <= -360) {
			jd = 0;
		}
		fishx = (int) (r * engine.sin(jd * engine.PI / 180)) + a;
		fishy = (int) (r * engine.cos(jd * engine.PI / 180)) + b;

		engine.usBs.rankMoney = 0;
		engine.laoJiaHP = engine.laoJiaHP_MAX;
		engine.isDraw = false;
		yyy = -10;
		xxx = 0;
		speed = 0;
		engine.Speed = 10;
		isShanDian = false;
		for (int j = 0; j < this.a.length; j++) {
			this.a[j][0] = 0;
			// a[i][0] =-1;
			this.a[j][1] = -180 + (j * -100);
			this.a[j][2] = 0;
			this.a[j][3] = 0;
			this.a[j][4] = 50;
		}
	}

	int checkdiRen() {
		int diRen = 0;
		for (int i = 0; i < engine.fd.enemyData.length; i++) {
			for (int j = 0; j < engine.fd.enemyData[i].length; j++) {
				for (int j2 = 0; j2 < engine.fd.enemyData[i][j].length; j2++) {
					diRen++;
				}
			}
		}
		return diRen;
	}



	// 关于信息
	/**
	 * drawAbout 关于菜单
	 * 
	 * @param g
	 */
	private void drawAbout() {

		drawMenubg();
		GameDraw.add_Image(PAK_IMAGES.IMG_MENGBAN, 0, 0, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 100);
		GameDraw.add_Image(PAK_IMAGES.IMG_BEIBAN, 400, 240,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 100);
		GameDraw.add_Image(PAK_IMAGES.IMG_YINYUEZI, 400, 100,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 100);
		GameDraw.add_Image(PAK_IMAGES.IMG_SHENSISHULVAN, 730, 5,
				Tools.TOP_LEFT, Tools.TRANS_NONE, 100);
		String[] str = {"游戏名称：疯狂植物横扫僵尸",
				"游戏类型：射击",
				"公司名称：南京浩普天地信息技术有限公司",
				"客服电话：13851981385",
				"客服邮箱：cmj731202@sina.com",
//				"免责声明：本游戏的版权归南京浩普",
//				"地信息技术有限公司，游戏中的文字，",
//				"图片等内容均为游戏版权所有者的个人", 
//				"态度或立场，中国电信对此不承担任何法责任。",
				 "版本号：v1.0", };
		

		for (int i = 0; i < str.length; i++) {
			String str12 = str[i];
			GameDraw.add_String2(str12, Tools.setOffX + 160, Tools.setOffY
					+ 150 + 24 * i, Tools.VCENTER_LEFT, 0xffffffff, 10000);
		}
	}

	// 帮助信息
	/**
	 * drawHelp 帮助菜单
	 * 
	 * @param g
	 */

	private int pages; // 页数
	int help[] = { PAK_IMAGES.IMG_BANGZHU1, PAK_IMAGES.IMG_BANGZHU2,
			PAK_IMAGES.IMG_YING7, PAK_IMAGES.IMG_YING8, PAK_IMAGES.IMG_BEIBAN };

	private void drawHelp() {
		if (lastStatus == GmStat_Stop) {
			engine.drawGame(true);

		}
		if (lastStatus == GmStat_Menu) {
			drawMenubg();
		}
		// drawMenubg();
		GameDraw.add_Image(PAK_IMAGES.IMG_MENGBAN, 0, 0, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 100 + 5000);
		GameDraw.add_Image(PAK_IMAGES.IMG_BEIBAN, 400, 240,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 100 + 5000);
		GameDraw.add_Image(PAK_IMAGES.IMG_YINYUEKAI, 400, 100,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 100 + 5000);
		GameDraw.add_Image(PAK_IMAGES.IMG_SHENSISHULVAN, 730, 5,
				Tools.TOP_LEFT, Tools.TRANS_NONE, 100 + 5000);
		GameDraw.add_Image(help[pages], MyGameCanvas.SCREEN_WIDTH / 2,
				MyGameCanvas.SCREEN_HEIGHT / 2, Tools.HCENTER_VCENTER,
				Tools.TRANS_NONE, 100 + 5000);
		if (pages == 4) {
			GameDraw.add_String("使用互动功能将会产生流量，", 160, 240, Tools.VCENTER_LEFT,
					0xffffffff, 10000, 25);
			GameDraw.add_String("流量资费按中国移动流量资费标准收取。", 160, 280,
					Tools.VCENTER_LEFT, 0xffffffff, 10000, 25);
		}
	}

	private void drawQiut() {
		String[] strQiut = { "感谢使用" };
		for (int i = 0; i < strQiut.length; i++) {
			GameDraw.add_String2(strQiut[i], SCREEN_WIDTH >>> 1, 40 + i * 20,
					Tools.HCENTER_TOP, 0xffffff, 1);
		}
		if (++index > 30) {

			// GameMIDlet.quitApp();
		}
	}

	int iSwitch;

	void drawTa() {
		iSwitch = engine.getSpriteIndex(iProp % (engine.mapWidth / 60),
				(iProp - iProp % (engine.mapWidth / 60))
						/ engine.map.mapSize[0]);// 判断选取的究竟是哪个塔
		GameRole sprite = (GameRole) engine.sprites.elementAt(iSwitch);
		GameDraw.add_Image(PAK_IMAGES.IMG_SELLUPMENGBAN, 0, 0, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 300);

		if (sprite.level < 5) {

		} else {
			GameDraw.add_Image(PAK_IMAGES.IMG_MAX, 277 + 34, 256,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 301);
		}

	}

	void upDown() {

	}

	int soundIsOpen = 0;
	int ia = 0;

	public int compare(int x, int len) {
		int y = (byte) x;
		if (x < 0) {
			y = len - 1;
		}
		if (x > len - 1) {
			y = 0;
		}
		return y;
	}

	/**
	 * 游戏中升级塔临时添加的攻击力
	 * 
	 * @param type
	 * @param rank
	 */
	int tempAttack;

	int attackLevel(int type, int rank) {
		switch (type) {
		case GameRole.TA_LIEYU:
			tempAttack = 3 * rank;
			break;
		case GameRole.TA_BINGJING:
			tempAttack = 4 * rank;
			break;
		case GameRole.TA_YULEI:
			tempAttack = 8 * rank;
			break;
		case GameRole.TA_HERO:
			tempAttack = 10 * rank;
			break;
		case GameRole.TA_HUOPAO:
			tempAttack = 4 * rank;
			break;
		}
		return tempAttack;
	}


	boolean isKey;
	boolean isSound;
	byte lasttele;
	boolean leftKey;
	boolean rightKey;

	int keyIndex = 0;
	byte flyIndex = -1;
	boolean isHengPing = false;


	// byte
	static byte nextStatus;
	static int mnextMenucurS;
	static String[] tempStr;

	// static byte[][] equipData = new byte[3][24]; //主角装备数组

	public final static String intToStr(int a) {
		return new Integer(a).toString();
	}

	public void drawStrIntro(int leavel, boolean isChoose, int strX, int strY,
			String name, int color) {
		GameDraw.add_String2(name, strX, isChoose ? strY + 1 : strY,
				Tools.TOP_LEFT, color, leavel + 1);

	}

	int introId, introColor;
	byte[] introData;

	// static byte[][] packData = new byte[40][I_LEN];

	public void drawGoodsMenu(int x, int y, int leavel) {

	}

	public static int drawNumber(int img, int number, int x, int y,
			int numWidth, int space, int athor, int layer, int numHeight,
			int clipY) {
		// int numHeight = 50; //
		int spacing = numWidth;
		int digitCounter = 0; // digit数字位数
		int[] digits = new int[10]; // 存放各位数字。int型最�?0�?
		do { // 算出number共有几位
			digits[digitCounter] = number % 10;
			number /= 10;
			digitCounter++;
		} while (number > 0);
		for (int i = 0; i < digitCounter; i++) { // 高位到低位，从左到右
			GameDraw.add_Image(img, x + i * (spacing + space), y,
					digits[digitCounter - 1 - i] * numWidth, clipY, numWidth,
					numHeight, athor, Tools.TRANS_NONE, layer);

		}
		return digitCounter;
	}

	public static int drawNumber_3(int img, int number, int x, int y,
			int numWidth, int space, int athor, int layer, int numHeight,
			int clipY) { // 中心�?
		// int numHeight = 50; //
		int spacing = numWidth;
		int digitCounter = 0; // digit数字位数
		int[] digits = new int[10]; // 存放各位数字。int型最�?0�?
		do { // 算出number共有几位
			digits[digitCounter] = number % 10;
			number /= 10;
			digitCounter++;
		} while (number > 0);
		for (int i = 0; i < digitCounter; i++) { // 高位到低位，从左到右
			GameDraw.add_Image(img, x + i * (spacing + space) - digitCounter
					* (spacing + space) / 2, y, digits[digitCounter - 1 - i]
					* numWidth, clipY, numWidth, numHeight, athor,
					Tools.TRANS_NONE, layer);
		}
		return digitCounter;
	}

	/**************************************** 大地�? **************************************************************/

	int openIndex;
	int openStatus;
	int[] openMotion;

	public void drawOpen() {
		drawCleanScreen(0xff000000);
	}

	public void drawOtherRect(int clipx, int clipy, int clipw, int cliph) {
		GameDraw.add_Rect(Tools.setOffX, Tools.setOffY, SCREEN_WIDTH, clipy
				- SCREEN_HEIGHT, true, Tools.TOP_LEFT, 0, 100);

		GameDraw.add_Rect(Tools.setOffX, cliph + clipy, SCREEN_WIDTH,
				SCREEN_HEIGHT - (clipy - Tools.setOffY + cliph), true,
				Tools.TOP_LEFT, 0, 100);

		GameDraw.add_Rect(Tools.setOffX, Tools.setOffY, clipx - Tools.setOffX,
				SCREEN_HEIGHT, true, Tools.TOP_LEFT, 0, 100);

		GameDraw.add_Rect(clipx + clipw, Tools.setOffY, SCREEN_WIDTH
				- (clipx - Tools.setOffX + clipw), SCREEN_HEIGHT, true,
				Tools.TOP_LEFT, 0, 100);
	}

	int teachIndex;
	static int teachStatus;

	String[] strTeach = { "�?,6键左右移", "键跳", "跳起后按5键攻", "键释放冰箭技", "键释放火箭技",
			"键释放冰块技", "键释放炸弹技" };

	public void drawKey(int x, int y, int curIndex) {
		if (gameTime / 2 % 2 == 0) {
			return;
		}
		int keyindex = 0;
		byte[] motion = new byte[] { 2, 4, 6, 8, 5, 7, 9, 0, 12, 13 };
		for (int i = 0; i < motion.length; i++) {
			if (motion[i] == curIndex) {
				keyindex = i;
				break;
			}
		}
	}

	/**********************************/
	// 清屏
	/**
	 * 黑屏/白屏
	 */
	static void backScreen(final int menuState) {
		new Thread() {
			public void run() {
				try {
					gameStatus = GmStat_KONG;
					drawCleanScreen(0xffffffff);
					sleep(100);
					gameStatus = GmStat_Playing;
					// if(menuState==MenuState.ST_MENU)startShake();//首页的熊猫震动
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}

	/*********************************************************************************************/
	public static int downx, downy;

	final static int getPoint(int[][] pointPos, int x, int y) { // 数组取左上点
		int sw = MyActivity.VMWidth;
		int sh = MyActivity.VMHeight;
		float kx = (float) sw / (MyGameCanvas.SCREEN_WIDTH);
		float ky = (float) sh / (MyGameCanvas.SCREEN_HEIGHT);
		int point = -1;
		for (int i = 0; i < pointPos.length; i++) {
			if (x >= pointPos[i][0] * kx
					&& x < pointPos[i][0] * kx + pointPos[i][2] * kx
					&& y >= pointPos[i][1] * ky
					&& y < pointPos[i][1] * ky + pointPos[i][3] * ky) {
				// System.out.println("gameStatus:" + gameStatus + "   " + index
				// + "     " + i);
				return i;

			}
		}

		return point;
	}

	// boolean
	int dRank = 0;

	void pointerReleased_CatchChooseb(int x, int y) {
		int[][] pointPos = { { 20, 20, 100, 150 }, { 130, 20, 100, 150 },
				{ 240, 20, 100, 150 }, { 350, 20, 100, 150 },
				{ 730, 0, 80, 60 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
		case 1:
		case 2:
		case 3:

			break;
		case 4:
			setST(GmStat_Menu);
			break;
		}
	}

	int TiaoZhan;

	void pointerReleased_JiangLi(int x, int y) {
		int pointPos[][] = { { 100, 330, 200, 60 }, { 540, 330, 200, 60 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		MyActivity.instance._mView.waf.StartBackMusic(4, true);
		TiaoZhan = engine.gameRank;
		engine.gameRank = 99;
		setST(GmStat_Load);
		// break;

		// }
	}

	void pointerReleased_qiandao(int x, int y) {
		int pointPos[][] = { { 590, 350, 100, 60 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:// 不进入挑战关卡
			setST(lastStatus);
			break;
		}
	}

	void pointerReleased_GmStat_YDSms(int x, int y) {
		int pointPos[][] = { 
				{ 320, 300, 160, 80 },
				{ 150, 300, 160, 120 }, 
				{ 550, 300, 160, 120 },
				{ 320, 220, 160, 80 },
				{ 500, 300, 200, 80 },};
		int point = getPoint(pointPos, x, y);
		System.out.println("point : "+point);
		System.out.println("st : "+gameStatus);
		switch (point) {
		case 0:
			if (gameStatus == GmStat_YDSms) {
//				MyActivity.billing.setBilling(GameBilling.Billing_技能冷却时间永久减半,
//						false, true);
				if(BillingResult.sms_RMS[BillingResult.Billing_技能冷却时间永久减半]!=BillingResult.SMS_SUCCESS){
					mm.sendBillingMsg(BillingResult.Billing_技能冷却时间永久减半);
				}else{
					Toast.makeText(MyActivity.instance,"不能重复购买",Toast.LENGTH_LONG).show();
				}
	
			}

			break;
		case 1:	
			
			//setST(lastStatus);
			//购买各种
			if (gameStatus == GmStat_YDSms2) {
				mm.sendBillingMsg(BillingResult.Billing_购买金币3W);
			}
			break;
		case 2:
			//购买各种
//			if (gameStatus == GmStat_YDSms2) {
//				mm.sendBillingMsg(BillingResult.Billing_购买金币8W);
////			SMS_dianxing.sendSMS(SMS_dianxing.Billing_购买金币8W);
//			}else{
//				setST(lastStatus);
//			}
			setST(lastStatus);
			break;
		case 3:
			if (gameStatus == GmStat_YDSms2) {
				setST(lastStatus);
			}
		case 4:
			if (gameStatus == GmStat_YDSms) {
				setST(lastStatus);
			}
			break;
		}
	}

	void pointerReleased_sms(int x, int y) {
		int pointPos[][] = { { 30, 350, 130, 60 },// 0
				{ 30 + 157, 350, 130, 60 },// 1
				{ 30 + 157 * 2, 350, 130, 60 },// 2
				{ 30 + 157 * 3, 350, 130, 60 },// 3 四件商品按钮
				{ 30 + 157 * 4, 350, 130, 60 },// 3 四件商品按钮
				{ 700, 0, 80, 60 },// 4 返回
				{ 150, 0, 150, 60 },// 5 植物界面按钮
				{ 0, 0, 150, 60 },// 6 技能界面按钮
		};
		pointMenu2 = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0: // 金钱10W或者双倍

			mm.sendBillingMsg(BillingResult.Billing_购买金币8W);
			// }
			break;
		case 1: // 技能冷却时间减半

			if(BillingResult.sms_RMS[BillingResult.Billing_技能冷却时间永久减半]!=BillingResult.SMS_SUCCESS){
//			SMS_dianxing.sendSMS(SMS_dianxing.Billing_技能冷却时间永久减半);
			mm.sendBillingMsg(BillingResult.Billing_技能冷却时间永久减半);
			}else{
				Toast.makeText(MyActivity.instance,"不能重复购买",Toast.LENGTH_LONG).show();
			}
			break;
		case 2:// 铁丝网加血

			mm.sendBillingMsg(BillingResult.Billing_战地急救_10);
			break;
		case 3:// 南瓜加农炮

			if(BillingResult.sms_RMS[BillingResult.Billing_南瓜大炮]!=BillingResult.SMS_SUCCESS){
//			SMS_dianxing.sendSMS(SMS_dianxing.Billing_南瓜大炮);
			mm.sendBillingMsg(BillingResult.Billing_南瓜大炮);
			}else{
				Toast.makeText(MyActivity.instance,"不能重复购买",Toast.LENGTH_LONG).show();
			}
			break;
		case 4:
//			SMS_dianxing.sendSMS(SMS_dianxing.Billing_购买金币3W);
			mm.sendBillingMsg(BillingResult.Billing_购买金币3W);
			break;
		case 5:
			setST(lastStatus);
			break;
		case 6:
			if (lastStatus != GmStat_Playing) {
				setST(GmStat_ZhuJiao);
			}

			break;
		case 7:
			if (lastStatus != GmStat_Playing) {
				setST(GmStat_ShenJi);
			}

			break;
		}
	}

	boolean isDian;

	void pointerPressed_RankJX2(int x, int y) {
		int pointPos[][] = { { 50, 350, 130, 60 },// 0
		};
		pointMenu2 = -1;
		int point = getPoint(pointPos, x, y);
		System.out.println("rank0 ：" + engine.rank0);
		if (engine.rank0 > 100) {
			setST(lastStatus);
			saveGame();
			if (isDian) {
				GameEngine.isRank1JX = false;
				isDian = false;
			}
		}

		// Tools.removeImage(PAK_IMAGES.IMG_Z10);
	}

	void pointerPressed_TSGTiShi(int x, int y) {
		setST(lastStatus);
		rankTiShi = true;
	}

	void pointerPressed_QieHuan(int x, int y) {
		if (IsInRect(x, y, 410, 430, 100, 100)
				|| IsInRect(x, y, 250, 450, 100, 100)) {
			setST(lastStatus);
			for (int i = 0; i < ZB.length; i++) {
				if (ZB[i] == -1) {
					contKP = 1000;
					if (engine.usBs.iUsBsRank < index) {
						engine.usBs.iUsBsRank = i + 1;
					}
					ZB[i] = shiYongKaPian[0];
					if (engine.usBs.sheSuJiaBei) {
						engine.Speed = (ZBXingXi[ZB[engine.usBs.iUsBsRank - 1]][8]) * 2;
					} else {
						engine.Speed = ZBXingXi[ZB[engine.usBs.iUsBsRank - 1]][8];
					}
					engine.usBs.iUsBsCurAttack = ZBXingXi[ZB[engine.usBs.iUsBsRank - 1]][3];
					break;
				}
			}
		}

		// rankTiShi = true;
	}


	void pointerReleased_CatchChooses(int x, int y) {
		int[][] pointPos = { { 310, 150-10, 100, 90 },
				{ 310 + 140, 150-10, 100, 90 }, { 310 + 280, 150-10, 100, 90 },
				{ 310, 270-10, 100, 90 }, { 310 + 140, 270-10, 100, 90 },
				{ 310 + 280, 270-10, 100, 90 }, };

		int point = getPoint(pointPos, x, y);
	}

	void pointerReleased_CatchChooseo(int x, int y) {
		int pointPos[][] = { { 600, 400, 200, 80 } };
		pointMenuo = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
			if(MyActivity.VMHeight<=240){
				Tools.removeAllImage();
			}

			if (TSpointMenus == -1) {
				if (engine.iResult[dRank][pointMenus] != -1) {
					engine.gameRank = dRank * 6 + pointMenus;
					// setST(GmStat_ZhuJiao);
					chekZT(GmStat_ZhuJiao);
				} else {
					System.out.println("关卡未开启！");
				}
			} else {
				if (engine.TSRabk[dRank * 2 + TSpointMenus] != -1) {
					if (engine.TSRabk[dRank * 2 + TSpointMenus] == 0) {
						engine.TSRabk[dRank * 2 + TSpointMenus] = 1;
						engine.gameRank = 99;
						chekZT(GmStat_ZhuJiao);

					} else {
						// 此处扣钱， 非第一次进入的情况下进入关卡扣钱
						if (GameEngine.usBs.iUsBsCuJinbi >= 500 * (dRank + 1)) {
							GameEngine.usBs.iUsBsCuJinbi -= 500 * (dRank + 1);
							engine.gameRank = 99;
							chekZT(GmStat_ZhuJiao);
							// chekZT(GmStat_ZhuJiao);
						}

					}

				} else {
					System.out.println("关卡未开启！");
				}

			}

			// }
			break;
		}
	}

	static int pointMenu = -1;
	static int pointMenu2 = -1;

	void pointerPressed_MENU(int x, int y) {
		int[][] pointPos = {
				{ 400, 420, 150, 60 },// 开始游戏
				{ 0, 250, 60, 60 },// 设置
				{ 0, 330, 60, 60 },// 帮助
				{ 0, 410, 60, 60 },// 关于
				{ 750, 0, 60, 60 },// 退出
				{ 700, 400, 80, 80 }, { 700, 170, 100, 80 },
				{ 700, 270, 100, 80 }, { 560, 400, 100, 80 }, };

		pointMenu2 = getPoint(pointPos, x, y);
		switch (pointMenu) {
		case -1:
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
			// setST(GmStat_RankMap);
			MyActivity.instance._mView.waf.StartWav(0, false);
			break;
		}
	}

	int selectChangjin;
	int selectChangjins;
	static int pointDRank = 0;
	static int pointMenus = 0;
	static int pointMenuo = -1;
	static int TSpointMenus = -1;
	public static boolean isUp_Down = false; // 判断手势是上滑还是下滑，true 上，false下
	int ranMove;
	int DingDian = 160;
	float Fd[] = { 0.6f, 0.6f, 0.6f, 0.6f, };
	int leve[] = { 30, 0, 0, 0 };

	int movex[] = { 5, 5, 5, 5 };

	public void drawBrank(int type, int current) {
		int lev = 1;
		GameDraw.add_Image(PAK_IMAGES.IMG_PAOTAI33, 0, 0, Tools.TOP_LEFT,
				Tools.TRANS_HV, 20);// 背景
		GameDraw.add_Image(PAK_IMAGES.IMG_GUANQIABEIJING, 0, 80,
				Tools.TOP_LEFT, Tools.TRANS_NONE, 0);// 背景
		GameDraw.add_Image(PAK_IMAGES.IMG_PAOTAI33, 0, 400, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 20);// 背景
		GameDraw.add_Image(PAK_IMAGES.IMG_SUZHANSUJUEAN, 20, 240,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 0);// 背景

		GameDraw.add_Image(pointMenu == 4 ? PAK_IMAGES.IMG_YUYEDAHENG
				: PAK_IMAGES.IMG_SHENSISHULVAN, 730, 5, Tools.TOP_LEFT,
				Tools.TRANS_NONE, 28);//
		ranMove = MyGameView.rankY + MyGameView.rankMove;
		// System.out.println("rankMove :"+MyGameView.rankMove);
		if (isUp_Down) {
			checkRanMove();
		}

		for (int i = 0; i < 4; i++) {
			if (ranMove < 1 && ranMove > -202) {
				checkFD(i);
			} else {
				Fd[0] = 0.7f;
				Fd[3] = 0.7f;
			}

			GameDraw.add_ImageScale(
					engine.iResult[i][0] == -1 ? PAK_IMAGES.IMG_JUQING1
							: PAK_IMAGES.IMG_BUBING8, 100 + movex[i], 92 + i
							* 105 + 100 + ranMove, rankData[i],
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, leve[i], Fd[i],
					Fd[i]);

		}

	}


	void drawJX() {
		// System.out.println("gamerank : " + gamerank);
		// if (gameStatus == GmStat_Playing) {
		if (MyActivity.VMHeight>240){
					GameDraw.add_Image(PAK_IMAGES.IMG_M3, // 获得的金钱
				0, 0, Tools.TOP_LEFT, Tools.TRANS_NONE, 100 + 5000);
		}

		GameDraw.add_Image(PAK_IMAGES.IMG_Z5, // 获得的金钱
				540, 350, Tools.TOP_LEFT, Tools.TRANS_NONE, 100 + 5000);

		GameDraw.add_Image(PAK_IMAGES.IMG_Z4,// 关卡进度
				410, 5, Tools.TOP_LEFT, Tools.TRANS_NONE, 100 + 5000);

		GameDraw.add_Image(PAK_IMAGES.IMG_Z2,// 防护网
				5, 5, Tools.TOP_LEFT, Tools.TRANS_NONE, 100 + 5000);


	}

	void checkFD(int i) {
		if (DingDian - (92 + i * 67 + 67 + ranMove) > -10
				&& DingDian - (92 + i * 67 + 67 + ranMove) < 10) {

			Fd[i] = getFD(DingDian - (92 + i * 67 + 67 + ranMove), i);
			// if(MyGameView.rankY+MyGameView.rankMove>1&&MyGameView.rankY+MyGameView.rankMove<-202){
			// Fd[i] =0.76f;
			// }

			leve[i] = 6;
			for (int j = 0; j < 6; j++) {
				if (movex[i] < 30) {
					if (ranMove < 1 && ranMove > -202) {
						movex[i] += 1;
					}
				}
			}
		} else {
			if (Fd[i] > 0.6f) {
				Fd[i] -= 0.1;
				leve[i] = 5;
			}
			for (int j = 0; j < 6; j++) {
				if (movex[i] > 0) {
					// if(MyGameView.rankY+MyGameView.rankMove<1&&MyGameView.rankY+MyGameView.rankMove>-202){
					movex[i] -= 1;
					// }
				}
			}

		}
	}

	float getFD(int FD, int i) {
		float jj = 0;
		if (FD < 0) {
			FD = -FD;
		}
		switch (FD) {
		case 0:
			Fd[i] = 0.8f;
			break;
		case 1:
			Fd[i] = 0.78f;
			break;
		case 2:
			Fd[i] = 0.76f;
			break;
		case 3:
			Fd[i] = 0.74f;
			break;
		case 4:
			Fd[i] = 0.72f;
			break;
		case 5:
			Fd[i] = 0.7f;
			break;
		case 6:
			Fd[i] = 0.68f;
			break;
		case 7:
			Fd[i] = 0.66f;
			break;
		case 8:
			Fd[i] = 0.64f;
			break;
		case 9:
			Fd[i] = 0.62f;
			break;
		case 10:
			Fd[i] = 0.6f;
			break;

		}
		jj = Fd[i];
		return jj;
	}

	int yyyy[] = { 0, 67, 67 * 2, 67 * 3, 67 * 4, 67 * 5, 67 * 6, 67 * 8 };
	int yyyy2[] = { 0, -67, -67 * 2, -67 * 3, -67 * 4, -67 * 5, -67 * 6,
			-67 * 7, -67 * 8 };
	int aa = 67;
	int rank1;
	int gamerank;

	void checkRanMove() {
		switch (ranMove / 67) {
		case 0:
			gamerank = 0;
			break;
		case -1:
			gamerank = 1;
			break;
		case -2:
			gamerank = 2;
			break;
		case -3:
			gamerank = 3;
			break;
		}
		dRank = gamerank;

		if (MyGameView.rankMove >= 0) {
			for (int i = 0; i < 8; i++) {
				if (MyGameView.rankMove >= yyyy[rank1] + 67 / 2
						&& MyGameView.rankMove < yyyy[rank1] + 67) {
					MyGameView.rankMove += 1;
				}
				if (MyGameView.rankMove < yyyy[rank1] + 67 / 2
						&& MyGameView.rankMove > yyyy[rank1]) {
					MyGameView.rankMove -= 1;
				}
			}

		} else {
			// System.out.println("rankMove : "+MyGameView.rankMove
			// +":ran"+rank1+":"+yyyy2[-rank1]);
			for (int i = 0; i < 8; i++) {
				if (MyGameView.rankMove > (yyyy2[-rank1] - 67 / 2)
						&& MyGameView.rankMove < yyyy2[-rank1]) {
					MyGameView.rankMove += 1;
				}
				if (MyGameView.rankMove < (yyyy2[-rank1] - 67 / 2)
						&& MyGameView.rankMove > yyyy2[-rank1] - 67) {
					MyGameView.rankMove -= 1;
				}
			}

		}
	}

	int rankData[][] = { { 17, 11, 298, 199 },/* 广场0 */
	{ 341, 10, 301, 200 },/* 少林1 */
	{ 16, 231, 299, 197 },/* 汽车2 */
	{ 342, 231, 297, 197 },/* 飞机3 */
	{ 43, 42, 122, 107 },/* 小关卡未开启4 */
	{ 226, 247, 110, 25 },/* 上下长条5 */
	{ 220, 10, 121, 108 },/* 小关卡开启6 */
	{ 670, 364, 111, 24 },/* 长条7 */
	{ 10, 179, 176, 65 },/* 确定8 */
	{ 11, 253, 176, 65 },/* 确定灰9 */
	{ 220, 123, 100, 108 } /* 被选中底图 */
	};

	public void drawSrank(int type, int current) {
		int rankShuzi[][] = { { 17, 352, 29, 51 },/* 1 */
		{ 73, 353, 38, 49 },/* 2 */
		{ 133, 352, 38, 50 },/* 3 */
		{ 191, 353, 40, 49 },/* 4 */
		{ 253, 353, 37, 50 },/* 5 */
		{ 312, 353, 37, 49 },/* 6 */
		{319,181,35,50},/*7*/
		{319,121,36,49},/*8*/
		{ 225, 284, 39, 58 },/* 勋章7 */
		{ 278, 283, 39, 59 },/* 勋章灰8 */
		
		};
		engine.time++;
		int lev = 2;
		for (int i = 0; i < 6; i++) {
			// System.out.println("engine.iResult[dRank][i] : "+engine.iResult[dRank][i]);
 
			if (engine.iResult[dRank][i] != -1) {
				if (i < 3) {
					if(dRank==0){
		GameDraw.add_Image(PAK_IMAGES.IMG_DIREN, 335 + i * 120
								+ (dRank == 0 ? i == 2 ? 120 : 0 : 0), 140,
								rankShuzi[i==2?3:i], Tools.TOP_LEFT, Tools.TRANS_NONE,
								lev + 200); // 关卡数字	
					}else{
						GameDraw.add_Image(PAK_IMAGES.IMG_DIREN, 335 + i * 120
								+ (dRank == 0 ? i == 2 ? 120 : 0 : 0), 140,
								rankShuzi[i], Tools.TOP_LEFT, Tools.TRANS_NONE,
								lev + 200); // 关卡数字
					}

					
					for (int j = 0; j < 3; j++) {
						GameDraw.add_Image(PAK_IMAGES.IMG_DIREN,
								305 + j * 30 + i * 120
										+ (dRank == 0 ? i == 2 ? 120 : 0 : 0),
								190,
								engine.iResult[dRank][i] > j ? rankShuzi[8]
										: rankShuzi[9], Tools.TOP_LEFT,
								Tools.TRANS_NONE, lev + 200);
					}

				} else {
					GameDraw.add_Image(PAK_IMAGES.IMG_DIREN,
							335 + (i - 3) * 120, 260, rankShuzi[i+1],
							Tools.TOP_LEFT, Tools.TRANS_NONE, lev + 200); // 关卡数字

					// GameDraw.drawNumber(PAK_IMAGES.IMG_CIKE1,i,
					// 215 + (i-3) * 100, 180-5+42,15,0,Tools.TOP_LEFT,10,19,0,
					// true); //关卡数字
					for (int j = 0; j < 3; j++) {
						GameDraw.add_Image(PAK_IMAGES.IMG_DIREN, 305 + j * 30
								+ (i - 3) * 120, 310,
								engine.iResult[dRank][i] > j ? rankShuzi[8]
										: rankShuzi[9], Tools.TOP_LEFT,
								Tools.TRANS_NONE, lev + 200);
					}
				}

				if (pointMenus != i) {
					if (i < 3) {
						GameDraw.add_Image(PAK_IMAGES.IMG_DIREN, 310 + i * 120
								+ (dRank == 0 ? i == 2 ? 120 : 0 : 0), 130,
								rankData[6], Tools.TOP_LEFT, Tools.TRANS_NONE,
								lev);
					} else {
						GameDraw.add_Image(PAK_IMAGES.IMG_DIREN,
								310 + (i - 3) * 120, 250, rankData[6],
								Tools.TOP_LEFT, Tools.TRANS_NONE, lev);
					}

				} else {
					if (pointMenus == i) {
						if (i < 3) {
							if (engine.iResult[gamerank][i] != -1) {
								// GameDraw.add_Image(PAK_IMAGES.IMG_XIAODITU11,
								// 182 + i * 100, 80+25
								// , Tools.TOP_LEFT, Tools.TRANS_NONE,lev+2);
								GameDraw.add_Image(PAK_IMAGES.IMG_DIREN, 310
										+ i * 120
										+ (dRank == 0 ? i == 2 ? 120 : 0 : 0),
										130 + engine.time / 2 % 70,
										rankData[5], Tools.TOP_LEFT,
										Tools.TRANS_NONE, lev);
							}

							GameDraw.add_Image(PAK_IMAGES.IMG_DIREN,
									310
											+ i
											* 120
											+ (dRank == 0 ? i == 2 ? 120 : 0
													: 0), 130, rankData[10],
									Tools.TOP_LEFT, Tools.TRANS_NONE, lev);
						} else {
							if (engine.iResult[gamerank][i] != -1) {
								// GameDraw.add_Image(PAK_IMAGES.IMG_XIAODITU11,
								// 182 + (i-3) * 100, 180+25
								// , Tools.TOP_LEFT, Tools.TRANS_NONE,lev+2);
								GameDraw.add_Image(PAK_IMAGES.IMG_DIREN,
										310 + (i - 3) * 120,
										250 + engine.time / 2 % 70,
										rankData[5], Tools.TOP_LEFT,
										Tools.TRANS_NONE, lev);
							}
							GameDraw.add_Image(PAK_IMAGES.IMG_DIREN,
									310 + (i - 3) * 120, 250, rankData[10],
									Tools.TOP_LEFT, Tools.TRANS_NONE, lev);
						}

					}

				}

			} else {
				if (i < 3) {

					GameDraw.add_Image(PAK_IMAGES.IMG_DIREN, 310 + i * 120
							+ (dRank == 0 ? i == 2 ? 120 : 0 : 0), 130,
							rankData[4], Tools.TOP_LEFT, Tools.TRANS_NONE, lev);
				} else {
					GameDraw.add_Image(PAK_IMAGES.IMG_DIREN,
							310 + (i - 3) * 120, 250, rankData[4],
							Tools.TOP_LEFT, Tools.TRANS_NONE, lev);

				}

			}
		}

		int[][] TSRANK = { { 4, 5, 95, 109 },/* ts_kai */
		{ 113, 5, 93, 108 },/* ts_未开 */
		{ 9, 129, 83, 23 },
		{ 133, 352, 38, 50 },/* 3 */
		{ 191, 353, 40, 49 },/* 4 */
		{319,121,36,49},/*8*/
		};/* 光条 */

		// System.out.println("dRank : "+dRank);
		for (int i = 0; i < 2; i++) {// 奖励关卡
			// System.out.println("TSRabk : "+engine.TSRabk[dRank * 2 + i]);
			if (engine.TSRabk[dRank * 2 + i] == -1) {// 未开通
				GameDraw.add_Image(PAK_IMAGES.IMG_TSRANK,
						595 + 75 + (dRank == 0 ? i == 0 ? -120 : 0 : 0),
						128 + i * 120, TSRANK[1], Tools.TOP_LEFT,
						Tools.TRANS_NONE, lev);
			} else {// 开通
				GameDraw.add_Image(PAK_IMAGES.IMG_TSRANK,
						595 + 75 + (dRank == 0 ? i == 0 ? -120 : 0 : 0),
						125 + i * 120, TSRANK[0], Tools.TOP_LEFT,
						Tools.TRANS_NONE, lev);
				if(dRank==0){
					GameDraw.add_Image(PAK_IMAGES.IMG_DIREN,595 + 107 + (dRank == 0 ? i == 0 ? -120 : 0 : 0),
							128 + i * 120+15, TSRANK[i==0?3:5], Tools.TOP_LEFT,
							Tools.TRANS_NONE, lev); // 关卡数字	
				}else{
					GameDraw.add_Image(PAK_IMAGES.IMG_DIREN,595 + 107 + (dRank == 0 ? i == 0 ? -120 : 0 : 0),
							128 + i * 120+15, TSRANK[4+i], Tools.TOP_LEFT,
							Tools.TRANS_NONE, lev); // 关卡数字		
				}
				if (TSpointMenus == i) {
					GameDraw.add_Image(PAK_IMAGES.IMG_TSRANK,
							595 + 80 + (dRank == 0 ? i == 0 ? -120 : 0 : 0),
							128 + i * 120 + engine.time / 2 % 70, TSRANK[2],
							Tools.TOP_LEFT, Tools.TRANS_NONE, lev - 1);
				}
			}

		}
	}

	public void drawArank(int type, int current) {
		int lev = 1;
		if (current == 0) {
			GameDraw.add_Image(PAK_IMAGES.IMG_DIREN, 710, 440 + 8, rankData[9],
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, lev + 21);
			// GameDraw.add_Image(PAK_IMAGES.IMG_BUBING8,410,
			// 289+8,rankData[10],
			// Tools.HCENTER_VCENTER, Tools.TRANS_NONE, lev+20);
		} else {
			GameDraw.add_Image(PAK_IMAGES.IMG_DIREN, 710, 440 + 8, rankData[8],
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, lev + 20);
			// GameDraw.add_Image(PAK_IMAGES.IMG_BUBING8,410, 289+8,rankData[8],
			// Tools.HCENTER_VCENTER, Tools.TRANS_NONE, lev+21);
		}
	}

	void chekZT(int st) {
		load2 = 0;
		lastStatus2 = st;
		setST(GmStat_Load2);
	}

	void pointerReleased_MENU(int x, int y) {
		int[][] pointPos = {
				{ 330, 390, 150, 60 },// 开始游戏
				{ 0, 230, 60, 60 },// 设置
				{ 0, 310, 60, 60 },// 帮助
				{ 0, 390, 60, 60 },// 关于
				{ 750, 0, 60, 60 },// 退出
				{ 700, 400, 80, 80 }, { 700, 170, 100, 80 },
				{ 700, 270, 100, 80 }, { 560, 400, 100, 80 },
		};
		pointMenu = -1;
		pointMenu2 = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:

			if (bFirstEnter) {// 第一次进入游戏,跳过关卡选择,直接进入游戏
				gmStatus = gmScripe;
				chekZT(GmStat_RankMap);
			} else {

				chekZT(GmStat_RankMap);
			}

			iMenuTime = 0;
			iMenuCharacter = -120;
			break;
		case 1:
	
			setST(GmStat_Option);
			break;
		case 2:
			setST(GmStat_Help);

			break;
		case 3:
			setST(GmStat_About);

			break;
		case 4:
			
			GameInterface.exit(MyActivity.instance, new GameExitCallback() {
				@Override
					public void onConfirmExit() {
						//确认退出逻辑
					
					   System.exit(0);
					}
					@Override
					public void onCancelExit() {
						//取消退出逻辑
					}
			});
			
			// System.exit(0);
			 //退出游戏
			 

			break;
		case 5://
			setST(GmStat_qiandao);
			// MyActivity.instance._mView.waf.StartBackMusic(3,true);
			break;

		default:
			break;
		}
	}

	void pointerPressed_OpenAnima(int x, int y) {
		int pointPos[][] = { { 400, 240, 100, 60 },// point0
				{ -5, -2, 100, 40 },// 升级商店point1
				{ 95, -2, 100, 40 },// 武器商店point2
				{ 134, 67, 100, 40 },// 技能1point3
				{ 134 + 112, 67, 100, 40 },// 技能2point4
				{ 134 + 224, 67, 100, 40 },// 技能3point5
				{ 134, 107, 100, 40 },// 技能4point6
				{ 134 + 112, 107, 100, 40 },// 技能5point7
				{ 134 + 224, 107, 100, 40 },// 技能6point8
				{ 134, 165, 100, 40 },// 技能7point9
				{ 134 + 112, 147, 100, 40 },// 技能8point10
				{ 134 + 224, 147, 100, 40 },// 技能9point11
				{ 134 + 112, 186, 100, 40 },// 技能10point12
				{ 134 + 224, 186, 100, 40 },// 技能11point13
				{ 240, 255, 80, 40 },// 升级按钮point14
				{ 368, 245, 100, 40 },// 开始游戏point15

		};
		pointMenu = getPoint(pointPos, x, y);
	}

	void pointerPressed_RankJX(int x, int y) {

		setST(lastStatus);
		saveGame();
	}

	void pointerPressed_qiandao(int x, int y) {
		int pointPos[][] = {
				{ 590, 350, 100, 60 },// 升级商店point0
				{ 100, 280, 80, 60 }, { 100 + 85, 280, 80, 60 },
				{ 100 + 85 * 2, 280, 80, 60 }, { 100 + 85 * 3, 280, 80, 60 },
				{ 100 + 85 * 4, 280, 80, 60 }, { 100 + 85 * 5, 280, 80, 60 },
				{ 100 + 85 * 6, 280, 80, 60 }, };
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
			pointMenu = 1;
			break;
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
			gift.open(point);
			break;
		}

		// System.out.println("ssssssssssssssssssss");
	}
	void pointerPressed_sms(int x, int y) {
		int pointPos[][] = { { 30, 350, 130, 60 },// 0
				{ 30 + 157, 350, 130, 60 },// 1
				{ 30 + 157 * 2, 350, 130, 60 },// 2
				{ 30 + 157 * 3, 350, 130, 60 },// 3 四件商品按钮
				{ 30 + 157 * 4, 350, 130, 60 },// 3 四件商品按钮
				{ 700, 0, 80, 60 },// 4 返回
				{ 150, 0, 150, 60 },// 5 植物界面按钮
				{ 0, 0, 150, 60 },// 6 技能界面按钮
		};
		int point = getPoint(pointPos, x, y);
		pointMenu2 = point;
		if (point != -1) {
			MyActivity.instance._mView.waf.StartWav(0, false);
		}
	}

   
	void pointerReleased_ZhuangBei(int x, int y) { // 装备界面按键
		int pointPos[][] = { { -5, -2, 150, 60 },// 升级商店point0
				{ 160, -2, 150, 60 },// 武器商店point1
				{ 600, 350, 390, 200 },// 开始游戏point2

				{ 35, 121, 50, 50 },// 开始游戏point2
				{ 35 + 90, 121, 50, 50 },// 开始游戏point2
				{ 35 + 180, 121, 50, 50 },// 开始游戏point2
				{ 35 + 270, 121, 50, 50 },// 开始游戏point2
				{ 35 + 360, 121, 50, 50 },// 开始游戏point2

				{ 35, 177, 50, 50 },// 开始游戏point2
				{ 35 + 90, 177, 50, 50 },// 开始游戏point2
				{ 35 + 180, 177, 50, 50 },// 开始游戏point2
				{ 35 + 270, 177, 50, 50 },// 开始游戏point2
				{ 35 + 360, 177, 50, 50 },// 开始游戏point2
				{ 280, 260, 80, 50 },// 开始游戏point2
				{ 700, 0, 120, 80 }, { 300, -2, 150, 60 } ,// 升级按钮point18
			
				
		};
		int point = getPoint(pointPos, x, y);

		pointMenu = -1;
		if (point >= 8 && point <= 17) {
			ZBIndex = (byte) (point - 8);
		}

		switch (point) {
		case 0:
			// setST(GmStat_ShenJi);
			chekZT(GmStat_ShenJi);
			if(MyActivity.VMHeight<=240){
		        Tools.removeAllImage();
			}
			break;

		case 2:
			engine.mapRank = 1;
	
			setST(GmStat_Load);
	

			break;
		case 14:
			setST(GmStat_RankMap);
			if(MyActivity.VMHeight<=240){
		        Tools.removeAllImage();
			}
			break;
		case 15:
			setST(GmStat_sms);
			if(MyActivity.VMHeight<=240){
		        Tools.removeAllImage();
			}
			break;
		}
	}

	Rect openAll=new Rect(400,450,400+150,450+35);
	void pointerReleased_ZhuangBei2(int x, int y) { // 装备界面按键
		int pointPos[][] = {
				{ 160, 60 + MyGameView.wuQi_Move + MyGameView.wuQi_Y, 50, 65 },
				{ 160, 60 + 1 * 100 + MyGameView.wuQi_Move + MyGameView.wuQi_Y,
						100, 80 },
				{ 160, 60 + 2 * 100 + MyGameView.wuQi_Move + MyGameView.wuQi_Y,
						100, 80 },
				{ 160, 60 + 3 * 100 + MyGameView.wuQi_Move + MyGameView.wuQi_Y,
						100, 80 },
				{ 160, 60 + 4 * 100 + MyGameView.wuQi_Move + MyGameView.wuQi_Y,
						100, 80 },

				{ 160, 60 + 5 * 100 + MyGameView.wuQi_Move + MyGameView.wuQi_Y,
						100, 80 },
				{ 160, 60 + 6 * 100 + MyGameView.wuQi_Move + MyGameView.wuQi_Y,
						100, 80 },
								
						
				{openAll.left, openAll.top,openAll.width(),openAll.height()}
		};
		int point = getPoint(pointPos, x, y);
		pointMenu = -1;
		if (point != -1 && ZBXingXi[point][0] == -1) {
			if (engine.usBs.iUsBsCuJinbi >= ZBXingXi[point][5]) {
				engine.usBs.iUsBsCuJinbi -= ZBXingXi[point][5];
				ZBXingXi[point][0] = 0;
				switch (point) {
				case 0:
					break;
				case 1:
					for (int i = 0; i < JLZB.length; i++) {
						if(ZB[i]==-1){
							ZB[i] = 5;
							JLZB[i] = 5;	
							break;
						}
					}

					break;
				case 2:
					for (int i = 0; i < JLZB.length; i++) {
						if(ZB[i]==-1){
							ZB[i] = 3;
							JLZB[i] = 3;	
							break;
						}
					}

					break;
				case 3:
					for (int i = 0; i < JLZB.length; i++) {
						if(ZB[i]==-1){
							ZB[i] = 2;
							JLZB[i] = 2;	
							break;
						}
					}

					break;
				case 4:
					for (int i = 0; i < JLZB.length; i++) {
						if(ZB[i]==-1){
							ZB[i] = 1;
							JLZB[i] = 1;	
							break;
						}
					}

					break;
				case 5:
					for (int i = 0; i < JLZB.length; i++) {
						if(ZB[i]==-1){
							ZB[i] = 6;
							JLZB[i] = 6;
							break;
						}
					}

					break;
				case 6:
					for (int i = 0; i < JLZB.length; i++) {
						if(ZB[i]==-1){
							ZB[i] = 4;
							JLZB[i] = 4;
							break;
						}
					}

					break;
				case 7:
					// ZB[point] = 7;
					// JLZB[point] = 7;
					break;
				}
			} else {
				if(point==pointPos.length-1)
				{
					m_currentSelect=11;
				}else
				{
					m_currentSelect=point+2;
				}
			
				setST(GmStat_YDSms2);
				tiShi = 30;
				
			}
		}

	}
	int m_currentSelect=0;
	void pointerPressed_CatchChooseb(int x, int y) {
		int[][] pointPos = { { 20, 20, 100, 150 }, { 130, 20, 100, 150 },
				{ 240, 20, 100, 150 }, { 350, 20, 100, 150 },
				{ 730, 0, 80, 60 }, };

		int point = getPoint(pointPos, x, y);
		if (point != -1 && point != 4) {
			// dRank = point;
			pointDRank = getPoint(pointPos, x, y);
		}
		if (point == 4) {
			MyActivity.instance._mView.waf.StartWav(0, false);
			pointMenu = point;
		}

	}

	void pointerPressed_CatchChooses(int x, int y) {
		int[][] pointPos = { { 310, 150, 80, 80 }, { 310 + 140, 150, 80, 80 },
				{ 310 + 280 + (dRank == 0 ? 120 : 0), 150, 80, 80 },
				{ 310, 270, 80, 80 }, { 310 + 140, 270, 80, 80 },
				{ 310 + 280, 270, 80, 80 },

		};

		int point = getPoint(pointPos, x, y);
		if (point != -1) {
			TSpointMenus = -1;
			engine.time = 0;
			pointMenus = getPoint(pointPos, x, y);
		}
	}

	void pointerPressed_TS(int x, int y) {
		int[][] pointPos = {
				{ 310 + 340 + (dRank == 0 ? -120 : 0), 150, 100, 80 },
				{ 310 + 340, 270, 100, 80 } };
		int point = getPoint(pointPos, x, y);
		if (point != -1) {
			engine.time = 0;
			pointMenus = -1;
			pointMenu2 = -1;
			TSpointMenus = point;
		}
	}

	void pointerPressed_CatchChooseo(int x, int y) {
		int[][] pointPos = { { 600, 400, 200, 100 } // 返回
		};

		int point = getPoint(pointPos, x, y);
		if (point != -1) {
			pointMenuo = getPoint(pointPos, x, y);
			// MyActivity.instance._mView.waf.StartBackMusic(4, true);
			MyActivity.instance._mView.waf.StartWav(0, false);
		}
	}

	byte pointMenu1; // 技能和主角选中效果

	void pointerReleased_OpenAnima(int x, int y) { // 技能商店按键
		int pointPos[][] = { { 400, 240, 100, 60 },// point0
				{ -5, -2, 100, 40 },// 升级商店point1
				{ 95, -2, 100, 40 },// 武器商店point2
				{ 134, 67, 100, 40 },// 技能1point3
				{ 134 + 112, 67, 100, 40 },// 技能2point4
				{ 134 + 224, 67, 100, 40 },// 技能3point5
				{ 134, 107, 100, 40 },// 技能4point6
				{ 134 + 112, 107, 100, 40 },// 技能5point7
				{ 134 + 224, 107, 100, 40 },// 技能6point8
				{ 134, 165, 100, 40 },// 技能7point9
				{ 134 + 112, 147, 100, 40 },// 技能8point10
				{ 134 + 224, 147, 100, 40 },// 技能9point11
				{ 134 + 112, 186, 100, 40 },// 技能10point12
				{ 134 + 224, 186, 100, 40 },// 技能11point13
				{ 240, 255, 80, 40 },// 升级按钮point14
				{ 368, 245, 100, 40 },// 开始游戏point15

		};
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:

			if ((iAniType++) >= 2) {
				setST(GmStat_Menu);
				MyActivity.instance._mView.waf.StartBackMusic(4, true);
				iAniType = 0;
			}
			break;
		case 1:

			break;
		case 2:
			setST(GmStat_ZhuJiao);
			break;
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
			pointMenu1 = (byte) point;
			break;
		case 14:

			break;
		case 15:

			break;
		}
	}

	byte wuQi_index;

	void pointerPressed_ZhuangBei(int x, int y) {
		int pointPos[][] = {
				// {368,245,100,40},
				// {290,255,80,40},
				// {400,0,80,60},

				{ 5, 30 + 50 + MyGameView.wuQi_Move + MyGameView.wuQi_Y, 167,
						65 },
				{
						5,
						30 + 50 + 1 * 110 + MyGameView.wuQi_Move
								+ MyGameView.wuQi_Y, 167, 65 },
				{
						5,
						30 + 50 + 2 * 110 + MyGameView.wuQi_Move
								+ MyGameView.wuQi_Y, 167, 65 },
				{
						5,
						30 + 50 + 3 * 110 + MyGameView.wuQi_Move
								+ MyGameView.wuQi_Y, 167, 65 },
				{
						5,
						30 + 50 + 4 * 110 + MyGameView.wuQi_Move
								+ MyGameView.wuQi_Y, 167, 65 },
				{
						5,
						30 + 50 + 5 * 110 + MyGameView.wuQi_Move
								+ MyGameView.wuQi_Y, 167, 65 },
				{
						5,
						30 + 50 + 6 * 110 + MyGameView.wuQi_Move
								+ MyGameView.wuQi_Y, 167, 65 },
				{
						5,
						30 + 50 + 7 * 110 + MyGameView.wuQi_Move
								+ MyGameView.wuQi_Y, 167, 65 },
				
		};

		int point = getPoint(pointPos, x, y);
		pointMenu = -1;
		// pointMenu = point;
		if (point != -1 && y > 70) {

			MyActivity.instance._mView.waf.StartWav(0, false);
			wuQi_index = (byte) point;
		}
	}

	void pointerPressed_ZhuangBei2(int x, int y) {
		int pointPos[][] = { { 0, 0, 150, 60 }, { 160, 0, 150, 60 },
				{ 600, 430, 300, 60 }, { 700, 0, 100, 60 },
				{ 300, 0, 150, 60 }, };

		int point = getPoint(pointPos, x, y);
		pointMenu = point;
		if (point != -1) {
			MyActivity.instance._mView.waf.StartWav(0, false);
		}

	}

	int JNXuangZhong = 0;

	void pointerPressed_ShenJi(int x, int y) {
		int pointPos[][] = { { 700, 420, 100, 100 }, { 600, 420, 100, 100 },
				{ 730, 0, 80, 60 },

				{ 225, 100, 160, 40 }, { 225 + 190, 100, 160, 40 },
				{ 225 + 380, 100, 160, 40 }, { 225, 200, 160, 40 },
				{ 225 + 190, 200, 160, 40 }, { 225 + 380, 200, 160, 40 },
				{ 210, 222 + 100, 160, 40 }, { 225 + 190, 166 + 100, 160, 40 },
				{ 225 + 380, 166 + 100, 160, 40 },
				{ 225 + 190, 238 + 100, 160, 40 },
				{ 225 + 380, 238 + 100, 160, 40 }, { 150, -2, 150, 70 },
				{ 300, 0, 150, 60 }, };

		int point = getPoint(pointPos, x, y);
		pointMenu = point;
		if (point >= 3 && point <= 13) {
			JNXuangZhong = point - 3;
		}
		if (point == 14) {
			MyActivity.instance._mView.waf.StartWav(0, false);
		}
		if (point >= 0 && point <= 2) {
			MyActivity.instance._mView.waf.StartWav(0, false);
		}
		if (point == pointPos.length - 1) {
			MyActivity.instance._mView.waf.StartWav(0, false);
		}
	}

	int tiShi = 0;
	boolean UPTX = false;

	void pointerReleased_ShenJi(int x, int y) { // 技能商店按键
		int pointPos[][] = {
				{ 800, 240, 100, 60 },// point0
				{ -5, -2, 150, 70 },// 升级商店point1
				{ 150, -2, 150, 70 },// 武器商店point2

				{ 225, 100, 160, 40 }, { 225 + 190, 100, 160, 40 },
				{ 225 + 380, 100, 160, 40 }, { 225, 200, 160, 40 },
				{ 225 + 190, 200, 160, 40 }, { 225 + 380, 200, 160, 40 },
				{ 210, 222 + 100, 160, 40 }, { 225 + 190, 166 + 100, 160, 40 },
				{ 225 + 380, 166 + 100, 160, 40 },
				{ 225 + 190, 238 + 100, 160, 40 },
				{ 225 + 380, 238 + 100, 160, 40 },

				{ 480, 430, 100, 60 },// 升级按钮point14
				{ 600, 350, 400, 200 },// 开始游戏point15
				{ 730, 0, 80, 60 }, { 350, 430, 100, 60 },// 升级按钮point17
				{ 300, -2, 150, 60 },// 升级按钮point18
				
				{ 300, 460,150,10}
		};
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
			if(MyActivity.VMHeight<=240){
		        Tools.removeAllImage();
			}
			if ((iAniType++) >= 2) {
				setST(GmStat_Menu);
				MyActivity.instance._mView.waf.StartBackMusic(4, true);
				iAniType = 0;
			}
			break;
		case 1:

			break;
		case 2:
			// setST(GmStat_ZhuJiao);
			chekZT(GmStat_ZhuJiao);
			if(MyActivity.VMHeight<=240){
		        Tools.removeAllImage();
			}
			break;
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
			pointMenu1 = (byte) point;

			break;
		case 14:
			if (jiNengKaiQi[JNXuangZhong] == -1
					|| jiNengKaiQi[JNXuangZhong] == 30) {
				return;
			}
			if (engine.usBs.iUsBsCuJinbi >= JNJG[jiNengKaiQi[JNXuangZhong]]) {
				UPTX = true;
				if (jiNengKaiQi[JNXuangZhong] != -1
						&& jiNengKaiQi[JNXuangZhong] <= 29) {
					engine.usBs.iUsBsCuJinbi -= JNJG[jiNengKaiQi[JNXuangZhong]];
					jiNengKaiQi[JNXuangZhong]++;

				}

				switch (JNXuangZhong) {
				case 0:
					if (jiNengKaiQi[JNXuangZhong] >= 3
							&& jiNengKaiQi[JNXuangZhong + 1] == -1) {
						jiNengKaiQi[JNXuangZhong + 1] = 1;
					}

					break;
				case 1:
					if (jiNengKaiQi[JNXuangZhong] >= 4
							&& jiNengKaiQi[JNXuangZhong + 1] == -1) {
						jiNengKaiQi[JNXuangZhong + 1] = 1;
					}
					break;
				case 2:
					break;
				case 3:
					if (jiNengKaiQi[JNXuangZhong] >= 2
							&& jiNengKaiQi[JNXuangZhong + 1] == -1) {
						jiNengKaiQi[JNXuangZhong + 1] = 1;
					}
					break;
				case 4:
					if (jiNengKaiQi[JNXuangZhong] >= 2
							&& jiNengKaiQi[JNXuangZhong + 1] == -1) {
						jiNengKaiQi[JNXuangZhong + 1] = 1;
					}
					break;
				case 5:
					break;
				case 6:
					if (jiNengKaiQi[JNXuangZhong] >= 2
							&& jiNengKaiQi[JNXuangZhong + 1] == -1) {
						jiNengKaiQi[JNXuangZhong + 1] = 1;
						jiNengKaiQi[JNXuangZhong + 3] = 1;
					}
					break;
				case 7:
					if (jiNengKaiQi[JNXuangZhong] >= 2
							&& jiNengKaiQi[JNXuangZhong + 1] == -1) {
						jiNengKaiQi[JNXuangZhong + 1] = 1;
					}
					break;
				case 8:

					break;
				case 9:
					if (jiNengKaiQi[JNXuangZhong] >= 2
							&& jiNengKaiQi[JNXuangZhong + 1] == -1) {
						jiNengKaiQi[JNXuangZhong + 1] = 1;
					}
					break;

				}
				saveGame();
			} else {
                setST(GmStat_YDSms2);
				tiShi = 30;
			}
			break;
		case 15:

			setST(GmStat_Load);

			break;
		case 16:
			setST(GmStat_RankMap);
			if(MyActivity.VMHeight<=240){
		        Tools.removeAllImage();
			}
			break;
		case 17:

			break;
		case 18:
			setST(GmStat_sms);
			break;
		case 19:
			m_currentSelect=10;
			setST(GmStat_YDSms2);
			tiShi = 30;
			break;
		}
	}

	void pointerPressed_Teaching(int x, int y) {
		int pointPos[][] = { { 170, 240, 130, 90 }, { 560, 240, 130, 90 } };
		pointMenu = getPoint(pointPos, x, y);
	}

	void pointerReleased_Teaching(int x, int y) {
		int pointPos[][] = { { 170, 240, 130, 90 }, { 550, 240, 130, 90 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:// 再次开启教学
			engine.bTeach = true;
			engine.teachStep = 0;
			iShopNum = 0;
			// 将之前未教学时候对商城的任何操作全部还原
			engine.fd.getWavePlace(MyGameCanvas.gmScripe, engine.gameRank,
					engine.iWave);// 若选择教学,则重置出兵
			engine.fd.getEnemyData(gmScripe, engine.gameRank);
			iGold += 50;
			setST(GmStat_Playing);
			break;
		case 1:
			engine.bTeach = false;
			engine.teachStep = 0;
			engine.fd.getWavePlace(MyGameCanvas.gmScripe, engine.gameRank,
					engine.iWave);// 若选择教学,则重置出兵
			engine.fd.getEnemyData(gmScripe, engine.gameRank);
			setST(GmStat_Playing);
			break;
		}
	}

	void pointerPressed_Option(int x, int y) {
		int pointPos[][] = { { 395, 170, 150, 70 }, { 395, 250, 150, 70 },
				{ 720, 0, 80, 80 } };
		pointMenu = getPoint(pointPos, x, y);
	}

	void pointerReleased_Option(int x, int y) {
		int pointPos[][] = { { 400, 180, 150, 50 }, { 400, 260, 150, 50 },
				{ 720, 0, 80, 80 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
			if (MyActivity.instance._mView.waf.isMusicEnabled()) {
				MyActivity.instance._mView.waf.disableMusic();
			} else {
				MyActivity.instance._mView.waf.enableMusic();
				if (lastStatus == GmStat_Stop) {
					MyActivity.instance._mView.waf.StartBackMusic(0, true);
				} else {
					MyActivity.instance._mView.waf.StartBackMusic(4, true);
				}

			}
			break;
		case 1:
			if (MyActivity.instance._mView.waf.isSoundEnabled()) {
				MyActivity.instance._mView.waf.disableSound();
			} else {
				MyActivity.instance._mView.waf.enableSound();
				MyActivity.instance._mView.waf.StartWav(0, false);
			}
			break;
		case 2:
			saveGame();
			setST(lastStatus);
			break;
		}
	}

	void pointerPressed_ABOUT(int x, int y) {
		int[][] pointPos = { { 720, 0, 80, 80 } };// 返回
		pointMenu = getPoint(pointPos, x, y);
		switch (pointMenu) {
		case 0:
			MyActivity.instance._mView.waf.StartWav(0, false);
			break;
		}
	}

	void pointerReleased_ABOUT(int x, int y) {
		int[][] pointPos = { { 730, 0, 80, 80 } };// 返回
		int point = getPoint(pointPos, x, y);
		pointMenu = -1;
		switch (point) {
		case 0:
			setST(GmStat_Menu);
			break;
		}
	}

	void pointerPressed_HELP(int x, int y) {
		int[][] pointPos = { { 720, 0, 80, 80 } };// 返回
		pointMenu = MyGameCanvas.getPoint(pointPos, x, y);
		switch (pointMenu) {
		case 0:
			MyActivity.instance._mView.waf.StartWav(0, false);
			break;
		}
	}

	void pointerReleased_HELP(int x, int y) {
		int[][] pointPos = { { 730, 0, 80, 80 }, { 0, 0, 720, 480 },
				{ 720, 90, 80, 390 } };// 返回
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
			setST(lastStatus);
			break;
		case 1:
		case 2:
			pages++;
			if (pages >= 4) {
				pages = 0;
			}
			break;
		}
	}

	void pointerPressed_Register(int x, int y) {
		int pointPos[][] = { { 680, 25, 90, 80 }, { 330, 355, 140, 80 } };
		pointMenu = getPoint(pointPos, x, y);
	}

	void pointerReleased_Register(int x, int y) {
		int pointPos[][] = { { 680, 25, 90, 80 }, { 330, 355, 140, 80 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
			setST(GmStat_Menu);
			break;
		case 1:
			setST(GmStat_Menu);
			// registerGet();//将签到界面获取的数据存档
			break;
		}
	}

	void pointerPressed_Achieve(int x, int y) {
		int pointPos[][] = { { 720, 0, 80, 80 } };
		pointMenu = getPoint(pointPos, x, y);
		switch (pointMenu) {
		case 0:
			MyActivity.instance._mView.waf.StartWav(0, false);
			break;
		}
	}

	void pointerReleased_Achieve(int x, int y) {
		int pointPos[][] = { { 720, 0, 80, 80 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
			setST(GmStat_CatchChoose);
			MyActivity.instance._mView.waf.StartBackMusic(4, true);
			break;
		}
	}

	void pointerPressed_Playing(int x, int y) {
		// System.out.println("xxxx : " + x + "yyy: " + y);
		if (diTuSuiPian == 0) {
			diTuSuiPian = 1;
		}
		saveGame();
		if (IsInRect(x, y, 400, 200, 100, 200) && engine.xinShouJiangLi == 1) {
			engine.DianJi = 600; // 让卡片继续往下移动
		}

		if (IsInRect(x, y, 730, 420, 70, 70)) {
			if(MyActivity.VMHeight<=240){
				Tools.removeAllImage();
			}
			setST(GmStat_sms);
		}
		int pointPos[][] = { { 530, 390, 90, 90 },
				{ 620, 390, 85, 90 },
				{ 705, 390, 90, 90 },// 3个技能键
				{ 422, 420, 64, 60 }, { 315, 420, 64, 60 },
				{ 400, 0, 100, 80 }, { 730, 420, 70, 70 } };
		pointMenu = getPoint(pointPos, x, y);
		bTouchMoving = false;
		if (IsInRect(x, y, 710, 80, 90, 75)) {// 点击技能1键滑屏
		} else if (IsInRect(x, y, 1710, 155, 90, 75)) {// 点击技能2键滑屏
		} else if (IsInRect(x, y, 1710, 230, 90, 70)) {// 点击技能3键滑屏
		} else if (IsInRect(x, y, 1710, 305, 90, 70)) {// 点击铲子
		} else if (IsInRect(x, y, 0, 430, 800, 70)) {// 屏幕最底下一排触屏无效
			if (IsInRect(x, y, 300, 440, 64, 60)
					|| IsInRect(x, y, 500, 440, 64, 60)) {// 点击老家+-符号
				// MyActivity.instance._mView.waf.StartWav(9,false);
			} else if (IsInRect(x, y, 1216, 440, 110, 40)) {// 领奖时间到的时候点击领奖

			}
		} else if (IsInRect(x, y, 0, 0, 800, 60)) {// 屏幕最上面一排触屏无效
		} else {
			// getDownProp(x,y);
			if (bShovel == false && bPropShow == false && bToTower == false
					&& !(IsInRect(x, y, 310, 385, 180, 95))) {// 处理老家的攻击
				if (iGold >= 4) {
					engine.usHomeAttack(x, y);
				} else if (iGold > 0) {
					if (engine.usBs.iUsBsRank >= iGold) {
						// engine.usBs.iUsBsRank=iGold;
						engine.usHomeAttack(x, y);
					} else {
						engine.usHomeAttack(x, y);
					}
				} else if (iGold == 0) {
					engine.usBs.iUsBsRank = 1;
					engine.usHomeAttack(x, y);
				}
			}
		}
		if (IsInRect(x, y, 440, 450, 64, 60)) {
			MyActivity.instance._mView.waf.StartWav(0, false);
			pointMenu = 100;
		}
		if (IsInRect(x, y, 250, 450, 64, 60)) {
			MyActivity.instance._mView.waf.StartWav(0, false);
			pointMenu = 101;
		}
		if (pointMenu == 5) {
			MyActivity.instance._mView.waf.StartWav(0, false);
		}

	}

	int ii;
	public static boolean isSTOP = false;

	void pointerReleased_Playing(int x, int y) {
		int pointPos[][] = { { 0, 0, 800, 480 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
			// if(IsInRect(x,y,630,0,80,60)){//点击快进
			if (IsInRect(x, y, 100, 420, 60, 75) && engine.JN2 < 55
					&& engine.gameRank != 99
					&& BillingResult.sms_RMS[BillingResult.Billing_技能冷却时间永久减半]!=BillingResult.SMS_SUCCESS) {
//				 MyActivity.billing.setBilling(GameBilling.Billing_技能冷却时间永久减半,
//				 false, true);
				//m_currentSelect=1;
				setST(GmStat_YDSms);
			}
			if (IsInRect(x, y, 200, 420, 60, 75) && engine.JN3 < 55
					&& engine.gameRank != 99
					&& BillingResult.sms_RMS[BillingResult.Billing_技能冷却时间永久减半]!=BillingResult.SMS_SUCCESS) {
				
//				MyActivity.billing.setBilling(GameBilling.Billing_技能冷却时间永久减半,
//				 false, true);
				//m_currentSelect=2;
				setST(GmStat_YDSms);
			}
			if (IsInRect(x, y, 100, 420, 60, 75) && engine.JN2 >= 61
					&& engine.gameRank != 99) {
				engine.cont = 0;
				isSTOP = true;
				engine.JN2 = 0;
				// System.out.println("enemys.size() :"+GameEngine.me.enemys.size());
				for (int i = 0; i < GameEngine.me.enemys.size(); i++) {

					GameRole enemy = (GameRole) GameEngine.me.enemys
							.elementAt(i);
					if (enemy.roleStatus == GameRole.STATUS_MOVE) {
						ii++;
						// System.out.println("iiii : "+ii+" roleStatus :"+enemy.roleStatus);
						enemy.roleStatus = GameRole.STATUS_STOP;
						enemy.go = 300 + (jiNengKaiQi[1] * 5);
					}

				}
			}
			if (IsInRect(x, y, 200, 420, 60, 75) && engine.JN3 >= 61
					&& engine.gameRank != 99) {
				engine.JN3 = 0;
				engine.checkYZD = 1;
				if (engine.yuanZiDanTS) {
					engine.yuanZiDanTS = false;
					if (engine.isGuanBi) {
						jiNengKaiQi[2] = -1;
					}

				}
			} else if (IsInRect(x, y, 730, 0, 85, 60)) {// 点击进入暂停状态
				setST(GmStat_Stop);
				if(MyActivity.VMHeight<=240){
					Tools.removeAllImage();
				}
				bShovel = false;
			} else if (IsInRect(x, y, 1710, 310, 90, 70)) {// 点击铲子,每次铲子卖塔需要宝石50

				if (iGold >= 50) {
					bShovel = !bShovel;
				}

			} else if (IsInRect(x, y, 20, 265, 90, 70) && engine.gameRank != 99) {// 回到第一个技能键处释放

			} else if (IsInRect(x, y, 1170, 260, 90, 70)
					&& engine.gameRank != 99) {// 回到第2个技能键处释放
				bShovel = false;
			} else if (IsInRect(x, y, 1710, 230, 90, 70)
					&& engine.gameRank != 99) {// 使用第三个技能
				bShovel = false;
			} else if (IsInRect(x, y, 410, 430, 100, 100) && ZB[0] != -1
					&& engine.gameRank != 99) {// 点击“+”
				GameEngine.JiaoXueDian = false;
				int index = 0;
				for (int i = 0; i < ZB.length; i++) {
					if (ZB[i] != -1) {
						index++;
					}
				}

				if (engine.usBs.iUsBsRank < index) {
					engine.usBs.iUsBsRank++;
					// if(ZB[engine.usBs.iUsBsRank]==-1){
					// engine.usBs.iUsBsRank = 1;
					// }

				} else {

					engine.usBs.iUsBsRank = 1;
				
				}

				engine.Speed = ZBXingXi[ZB[engine.usBs.iUsBsRank - 1]][8];

				if (engine.usBs.iUsBsRank > 8) {
					engine.usBs.iUsBsRank = 1;
				}

				engine.usBs.iUsBsCurAttack = ZBXingXi[ZB[engine.usBs.iUsBsRank - 1]][3];
				AddBlastEffectList(400, 438, EFF_HUANPAO, 0, 221, 0);

			} else if (IsInRect(x, y, 250, 430, 100, 100) && ZB[0] != -1
					&& engine.gameRank != 99) {// 点击"-"
				GameEngine.JiaoXueDian = false;
				int index = 0;
				for (int i = 0; i < ZB.length; i++) {
					if (ZB[i] != -1) {
						index++;
					}
				}
				if (engine.usBs.iUsBsRank > 1) {
					engine.usBs.iUsBsRank--;
				} else {
					engine.usBs.iUsBsRank = index;
				}
				engine.Speed = ZBXingXi[ZB[engine.usBs.iUsBsRank - 1]][8];
				// engine.usBs.iUsBsRank--;
				if (engine.usBs.iUsBsRank < 1) {
					engine.usBs.iUsBsRank = 7;
				}
				engine.usBs.iUsBsCurAttack = ZBXingXi[ZB[engine.usBs.iUsBsRank - 1]][3];
				AddBlastEffectList(400, 438, EFF_HUANPAO, 0, 221, 0);

			} else if (IsInRect(x, y, 1230, 420, 75, 60)) {

				if (iGoldGet != 0) {
					iGold += iGoldGet;
					iGoldGet = 0;
					engine.fLingqu = 0.50f;
					engine.LingquNum = 0;
				}

				saveGame();
			}

			else {// 点击屏幕中，造塔或者卖塔之类
				if (bTouchMoving == false) {// 没有滑屏,点击屏幕其他任意位置,需判断之前一步是否选中了铲子
					if (bPropShow == true) {
						//TowerOpen(iTowerType, x, y);
					} else {
						iProp = getMapIndex(x, y);
						// setOperate(iProp,0,0);
					}
				} else {
					if (IsInRect(iTouchDownX, iTouchDownY, 5, 420, 70, 75)
							&& engine.JN1 > 61 && engine.gameRank != 99) {// 滑动技能1
						if (engine.role.iSkillCoolTime[0] == 0) {
							engine.getSkill(x, y, engine.role.SKILL_STORM);
							engine.bSkill = true;

						}
						bShovel = false;
					} else if (IsInRect(iTouchDownX, iTouchDownY, 70, 265, 70,
							75)) {// 滑动技能2

					} else if (IsInRect(iTouchDownX, iTouchDownY, 120, 265, 70,
							75)) {// 滑动技能3
					}
				}
			}
			break;
		}
		bTouchMoving = false;
		engine.usBs.bHomeAttack = false;
		engine.skillPushX = 0;
		engine.skillPushY = 0;
		engine.skillPushType = 0;
	}

	void pointerPressed_CatchChoose(int x, int y) {
		int pointPos[][] = { { 695, 242, 100, 76 }, { 695, 327, 100, 76 },
				{ 695, 408, 100, 76 } //
		};
		pointMenu = getPoint(pointPos, x, y);
	}

	void pointerReleased_CatchChoose(int x, int y) {
		int pointPos[][] = { { 0, 0, 800, 480 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
			if (IsInRect(x, y, 0, 0, 800, 480)) {// 在区域松手
				if (Math.abs(engine.iCatchMove) >= 8) {// 滑屏了
					if (Math.abs(x - iTouchDownX) >= 150) {// 移动距离>=150才算有效移动,否则弹回去
						switch (Math.abs(x - iTouchDownX) / 270) {
						case 0:
							engine.bCatchSlip = true;
							engine.iDriftX = ((((x - iTouchDownX) > 0) ? 1 : -1) * 270)
									- engine.iCatchMove;// 位移差即总共位移-已移动的距离
							engine.iCatchMove = ((((x - iTouchDownX) > 0) ? 1
									: -1) * 270);
							engine.iCatchStart += engine.iCatchMove;
							engine.iCatchMove = 0;
							break;
						case 1:
							if (Math.abs(x - iTouchDownX) >= 420) {
								engine.bCatchSlip = true;
								engine.iCatchMove = (((x - iTouchDownX) > 0) ? 1
										: -1) * 540;
								engine.iCatchStart += engine.iCatchMove;
								engine.iCatchMove = 0;
							} else {
								engine.bCatchSlip = true;
								engine.iCatchMove = (((x - iTouchDownX) > 0) ? 1
										: -1) * 270;
								engine.iCatchStart += engine.iCatchMove;
								engine.iCatchMove = 0;
							}
							break;
						case 2:
							if (Math.abs(x - iTouchDownX) >= 690) {
								engine.bCatchSlip = true;
								engine.iCatchMove = (((x - iTouchDownX) > 0) ? 1
										: -1) * 810;
								engine.iCatchStart += engine.iCatchMove;
								engine.iCatchMove = 0;
							} else {
								engine.bCatchSlip = true;
								engine.iCatchMove = (((x - iTouchDownX) > 0) ? 1
										: -1) * 540;
								engine.iCatchStart += engine.iCatchMove;
								engine.iCatchMove = 0;
							}
							break;
						}
						if (Math.abs(engine.iCatchStart) >= 810) {
							engine.iCatchStart = 0;
						}
					} else {
						engine.iCatchMove = 0;
					}
				} else {// 没有滑屏
					if (IsInRect(x, y, 735, 0, 70, 70)) {// 返回主菜单
						MyActivity.instance._mView.waf.StartWav(0, false);
						setST(GmStat_Menu);
					}
					if (IsInRect(x, y, 695, 242, 100, 76)) {// 进入成就系统
						MyActivity.instance._mView.waf.StartWav(0, false);
						setST(GmStat_Achieve);
						MyActivity.instance._mView.waf.StartBackMusic(3, true);
					}
					if (IsInRect(x, y, 695, 327, 100, 76)) {// 进入商城

						MyActivity.instance._mView.waf.StartWav(0, false);
						setST(GmStat_Shop);
						// Tools.removeAllImage();
						MyActivity.instance._mView.waf.StartBackMusic(3, true);

					}
					switch (gmStatus) {
					case gmScripe:
						if (IsInRect(x, y, 35, 250, 645, 200)) {// 8块地图范围内
							if (getPoint_CatchChoose(x, y) != -1) {
								switch (getPoint_CatchChoose(x, y)) {
								case 0:case 1:case 2:case 3:case 4:case 5:case 6:case 7:

									if (engine.iResult[(engine.iCatchStart / 270 == 1 || engine.iCatchStart / 270 == 2) ? (engine.iCatchStart / 270 == 1 ? 2
											: 1)
											: Math.abs(engine.iCatchStart) / 270][getPoint_CatchChoose(
											x, y)] >= 1) {// 判断点击的地图是否已经开启
										engine.gameRank = ((engine.iCatchStart / 270 == 1 || engine.iCatchStart / 270 == 2) ? (engine.iCatchStart / 270 == 1 ? 2
												: 1)
												: Math.abs(engine.iCatchStart) / 270)
												* 8
												+ getPoint_CatchChoose(x, y);

									}
									break;
								}
							}
						}
						break;
					case gmFight:
						if (engine.iFightResult[(engine.iCatchStart / 270 == 1 || engine.iCatchStart / 270 == 2) ? (engine.iCatchStart / 270 == 1 ? 2
								: 1)
								: Math.abs(engine.iCatchStart) / 270] >= 1) {
							engine.iFightGameRank = (engine.iCatchStart / 270 == 1 || engine.iCatchStart / 270 == 2) ? (engine.iCatchStart / 270 == 1 ? 2
									: 1)
									: (Math.abs(engine.iCatchStart) / 270);
						}
						break;
					}
					if (IsInRect(x, y, 400, 260, 100, 76)) {// 进入游戏,前提是选中的关卡必须已经开启
						jd = 0;

						// engine.iRanTieshi = GameRandom.result(0,8);
						MyActivity.instance._mView.waf.StartWav(0, false);
						setST(GmStat_Load);

					}
				}
			}
			break;
		}
	}

	void pointerPressed_zaota(int x, int y) {
		int pointPos[][] = { { 265, 105, 85, 85 } };
		pointMenu = getPoint(pointPos, x, y);
	}

	void pointerReleased_zaota(int x, int y) {
		int pointPos[][] = { { 0, 0, 800, 480 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
			if (TA_STATUS == TA_造塔) {
				setProprety(TA_造塔, x, y);
			} else {
				setProprety(TA_卖或升级, x, y);
			}
			saveGame();
			setST(GmStat_Playing);
			fTaScale = 0;
			break;
		}
	}

	void pointerPressed_TeachSHOP(int x, int y) {
		int[][] pointPos = { { 0, 0, 800, 480 } };
		pointMenu = getPoint(pointPos, x, y);
	}

	void pointerReleased_TeachSHOP(int x, int y) {
		int[][] pointPos = { { 0, 0, 800, 480 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
			iTowerChoose = 0;
			setST(GmStat_Shop);
			break;
		}
	}

	void pointerPressed_GOLDNOT(int x, int y) {
		int[][] pointPos = { { 335, 275, 140, 70 } };
		pointMenu = getPoint(pointPos, x, y);
	}

	void pointerReleased_GOLDNOT(int x, int y) {
		int[][] pointPos = { { 335, 275, 140, 70 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
			setST(GmStat_Playing);
			break;
		}
	}


	void pointerReleased_SELLUP(int x, int y) {
		int[][] pointPos = { { 0, 0, 800, 480 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
			int it = engine.getSpriteIndex(iProp % (engine.mapWidth / 60),
					(iProp - iProp % (engine.mapWidth / 60))
							/ engine.map.mapSize[0]);// 判断选取的究竟是哪个塔
			GameRole sprite = (GameRole) engine.sprites.elementAt(it);
			if (IsInRect(x, y, 245, 180, 120, 140)) {
			} else if (IsInRect(x, y, 450, 180, 120, 140)) {
				engine.iTaSellNum++;
				if (engine.iTaSellNum >= 10 && engine.iAchieve[3] == 0) {
					engine.iAchieve[3] = 1;
					engine.iAchieveNum = 4;
					AddBlastEffectList(400, 680, EFF_GETACHIEVE, 0, 180, 0);// 出发第4个成就
				}
				engine.map.propData[iProp] = 17;
				AddBlastEffectList(sprite.x - engine.iMapX, sprite.y
						- engine.iMapY, EFF_UPLEVEL, 0, 800, 0);
				engine.sprites.removeElementAt(it);
				engine.iPropNum++;
				setST(GmStat_Playing);
			} else {
				setST(GmStat_Playing);
			}
			bToTower = false;
			break;
		}
	}

	void pointerPressed_TeachReward(int x, int y) {
		int[][] pointPos = { { 0, 0, 800, 480 } };
		pointMenu = getPoint(pointPos, x, y);
	}

	void pointerReleased_TeachReward(int x, int y) {
		int[][] pointPos = { { 0, 0, 800, 480 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
			iTeaching++;
			if (iTeaching >= 3) {
				engine.iWavePlace[0] = 0;
				engine.iWave++;
				iGold += 30;
				engine.iWaveTime = 0;
				engine.iTime = 0;
				engine.teachStep = 11;
				setST(GmStat_Playing);
				AddBlastEffectList(400, 240, EFF_WAVETISHI, 0, 800, 0);
				iTeaching = 0;
			}
			break;
		}
	}

	void pointerPressed_Stop(int x, int y) {
		int[][] pointPos = { { 350, 160, 120, 40 }, { 350, 215, 120, 40 },
				{ 350, 270, 200, 40 }, { 350, 325, 200, 40 } };
		pointMenu2 = getPoint(pointPos, x, y);

	}

	void pointerReleased_Stop(int x, int y) {
		int[][] pointPos = { { 350, 160, 120, 40 }, { 350, 215, 120, 40 },
				{ 350, 270, 200, 40 }, { 350, 325, 200, 40 } };
		pointMenu2 = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
			if(MyActivity.VMHeight<=240){
				Tools.removeAllImage();
			}
			EffectV.removeAllElements();// 清除所有效果
			setST(GmStat_Help);
			// MyActivity.instance._mView.waf.StartBackMusic(3,true);
			iWhere[0] = 1;
			break;
		case 1:
			if(MyActivity.VMHeight<=240){
				Tools.removeAllImage();
			}
			EffectV.removeAllElements();// 清除所有效果
			setST(GmStat_Option);
			// MyActivity.instance._mView.waf.StartBackMusic(3,true);
			iWhere[1] = 1;
			break;
		case 2:// 退出遊戲
			if(MyActivity.VMHeight<=240){
				Tools.removeAllImage();
			}
			setST(GmStat_Playing);

			break;
		case 3:// 返回遊戲中
			if(MyActivity.VMHeight<=240){
				Tools.removeAllImage();
			}
			EffectV.removeAllElements();// 清除所有效果
			// Tools.removeAllImage();
			draw2ciQueRen("你确定要离开游戏并进入关卡选择？", GmStat_RankMap);
			// setST(GmStat_RankMap);

			saveGame();
			break;
		}
	}

	public void draw2ciQueRen(String str, final int gameStatus) {
		MyActivity.instance.build
				.setMessage(str)
				.setPositiveButton("YES",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								pointMenu = -1;
								setST(gameStatus);
								if (gameStatus == GmStat_RankMap) {
									MyActivity.instance._mView.waf
											.StartBackMusic(4, true);
								}
							}
						})
				.setNegativeButton("NO", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				}).show();
	}

	void pointerPressed_dubo(int x, int y) {
		int[][] pointPos = { { 335, 180, 130, 120 } };
		pointMenu = getPoint(pointPos, x, y);
		switch (pointMenu) {
		case 0:
			break;
		}
	}

	void pointerReleased_dubo(int x, int y) {
		int[][] pointPos = { { 335, 180, 130, 120 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
			engine.iAnJian++;
			if (engine.iAnJian == 1) {
				engine.bStart = true;
			} else if (engine.iAnJian == 2) {
				engine.bStart = false;
				engine.bEnd = true;
			}
			break;
		}
	}

	void pointerPressed_Lose(int x, int y) {
		int pointPos[][] = { { 100, 330, 200, 60 }, { 540, 330, 200, 60 } };
		pointMenu = getPoint(pointPos, x, y);
		switch (pointMenu) {
		case 0:
		case 1:
			MyActivity.instance._mView.waf.StartWav(0, false);
			break;
		}
	}

	void pointerReleased_Lose(int x, int y) {
		int pointPos[][] = {
				{ 550, 380, 120, 80 },// 离开
				{ 250, 160, 200, 80 },// 进入技能升级界面
				{ 550, 160, 200, 80 },// 加农炮
				{ 250, 240, 200, 80 },// 技能减半
				{ 550, 240, 200, 80 },// 3w
				{ 250, 300, 200, 80 },// 8W

		};
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		System.out.println("point : " + point);
		switch (point) {
		case 0:// 关卡选择
				// Tools.removeAllImage();
//			System.out.println("point : " + point);
			setST(GmStat_RankMap);
			// MyActivity.instance._mView.waf.StartBackMusic(4, true);
			// saveGame();
			break;
		case 1:// 进入技能升级界面
			setST(GmStat_ShenJi);
			break;
		case 2:// 购买急速恢复技能

			if(BillingResult.sms_RMS[BillingResult.Billing_南瓜大炮]!=BillingResult.SMS_SUCCESS){
				mm.sendBillingMsg(BillingResult.Billing_南瓜大炮);
			}else{
				Toast.makeText(MyActivity.instance,"不能重复购买",Toast.LENGTH_LONG).show();
			}

			// }
			break;
		case 3:// 金币翻倍

			if(BillingResult.sms_RMS[BillingResult.Billing_技能冷却时间永久减半]!=BillingResult.SMS_SUCCESS){
				mm.sendBillingMsg(BillingResult.Billing_技能冷却时间永久减半);
			}else{
				Toast.makeText(MyActivity.instance,"不能重复购买",Toast.LENGTH_LONG).show();
			}
			// }
			break;
		case 4:// 南瓜
				mm.sendBillingMsg(BillingResult.Billing_购买金币3W);
			break;
		case 5:
				mm.sendBillingMsg(BillingResult.Billing_购买金币8W);
			break;
		}
		EffectV.removeAllElements();
	}

	void pointerReleased_dengdai_Lose(int x, int y) {
		int pointPos[][] = { { 100, 330, 200, 60 }, { 540, 330, 200, 60 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		if (counI == -1) {
			switch (point) {
			case 0:
				if (GameEngine.fuhuo > 0) {
					GameEngine.fuhuo--;
					countY = -51;
					counI = -1;
					scaleX = 0.0f;
					scaleY = 0.0f;
					rota = 360;
					engine.countRole = 0;
					engine.iRunNum = 0;
					GameEngine.laoJiaHP = GameEngine.laoJiaHP_MAX;
					setST(GmStat_Playing);
				} else {
					mm.sendBillingMsg(BillingResult.Billing_战地急救_10);			
				}
				break;
			case 1:
				counI = 0;
				break;
			}

		} else {	
			setST(GmStat_Lose);
		}

	}

	void pointerPressed_Win(int x, int y) {
		int pointPos[][] = { { 80, 360, 250, 100 }, { 500, 360, 250, 100 } };
		pointMenu = getPoint(pointPos, x, y);
		switch (pointMenu) {
		case 0:
		case 1:
			MyActivity.instance._mView.waf.StartWav(0, false);
			break;
		}
	}

	void pointerReleased_dengdai_Win(int x, int y) {
		int pointPos[][] = {};
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		setST(GmStat_Win);
		MyActivity.instance._mView.waf.StartWav(11, false);
		engine.role.iIsAccel = 1;
		// MyGameCanvas.me.GotPride(status);//结束了,胜利了,给点奖励和评价
		MyGameCanvas.EffectV.removeAllElements();// 在进入胜利前将所有动画效果全部清除
		if(MyActivity.VMHeight<=240){
	        Tools.removeAllImage();
		}

	}

	int diTuSuiPian = 0;

	void pointerReleased_Win(int x, int y) {
		int pointPos[][] = { { 80, 360, 250, 100 }, { 500, 360, 250, 100 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:// guanka xuan ze
			if(MyActivity.VMHeight<=240){
				Tools.removeAllImage();
			}
//			MyActivity.net.upScore("0", engine.usBs.iUsBsCuJinbi);
			setST(GmStat_Menu);
			MyActivity.instance._mView.waf.StartBackMusic(4, true);
			// Tools.removeAllImage();
			if (engine.gameRank >= 23) {
				engine.gameRank = 23;
			}
			break;
		case 1:// 下一关
			if(MyActivity.VMHeight<=240){
				Tools.removeAllImage();
			}

			MyActivity.instance._mView.waf.StartBackMusic(4, true);

			if (engine.gameRank >= 23) {
				engine.gameRank = 23;
			}
			setST(GmStat_RankMap);
			break;
		}
		EffectV.removeAllElements();
	}

	void pointerPressed_scripe(int x, int y) {
		int[][] pointPos = { { 630, 400, 120, 80 } };
		pointMenu = getPoint(pointPos, x, y);
		switch (pointMenu) {
		case 0:

			break;
		}
	}

	void pointerReleased_scripe(int x, int y) {
		int[][] pointPos = { { 630, 400, 120, 80 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
		}
	}

	void pointerPressed_Xiaoguan(int x, int y) {
		int[][] pointPos = { { 0, 0, 800, 480 } };
		pointMenu = getPoint(pointPos, x, y);

	}

	void pointerReleased_Xiaoguan(int x, int y) {
		int[][] pointPos = { { 0, 0, 800, 480 } };
		pointMenu = -1;
	}

	void pointerPressed_Xuanzejiemian(int x, int y) {
	}

	void pointerReleased_Xuanzejiemian(int x, int y) {
	}


	void pointerReleased_Shop(int x, int y) {
		int[][] pointPos = {
				// {57,23,125,60},{182,23,125,60},{307,23,125,60},{730,0,70,70},//3个选项的选择及返回键
				// {0,0,800,480}
				{ 57, 23, 125, 60 }, { 182, 23, 125, 60 }, { 730, 0, 70, 70 },
				{ 63, 90, 110, 150 }, { 174, 90, 110, 150 },
				{ 285, 90, 110, 150 }, { 396, 90, 110, 150 },
				{ 507, 90, 110, 150 }, { 618, 90, 110, 150 },
				{ 625, 335, 95, 90 } };
		pointMenu = -1;
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
			iShopNum = 0;
			break;
		case 1:
			// iShopNum = point;
			break;
		case 2:
	
			setST(GmStat_CatchChoose);
			MyActivity.instance._mView.waf.StartBackMusic(4, true);
			break;
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		
			break;
		case 9:
			switch (iTowerChoose) {
			case 0:case 1:case 2:case 3:case 4:case 5:
				if (engine.iTowerNum == 6 && engine.iAchieve[9] == 0) {// 6个塔全部升到顶级
					engine.iAchieve[9] = 1;
					engine.iAchieveNum = 10;
					AddBlastEffectList(400, 680, EFF_GETACHIEVE, 0, 380, 0);
				}
				break;
			}
			saveGame();
			break;
		}

	}

	void pointerPressed_MIDMENU(int x, int y) {
		int[][] pointPos = { { 283, 65, 240, 50 }, { 278, 205, 240, 50 },
				{ 278, 135, 240, 50 }, { 275, 275, 240, 50 } };
		pointMenu = getPoint(pointPos, x, y);
	}

	void pointerReleased_MIDMENU(int x, int y) {
		int[][] pointPos = { { 283, 65, 240, 50 }, { 278, 205, 240, 50 },
				{ 278, 135, 240, 50 }, { 275, 275, 240, 50 } };
		int point = getPoint(pointPos, x, y);
		switch (point) {
		case 0:
		case 1:
		case 2:
		case 3:
			pointMenu = -1;
			m_index = point;
			// keyPressed(KEY_5);
			break;

		}
	}

	public void pointerPressed(int x, int y) {

		switch (gameStatus) {
		case GmStat_QieHuan:
			pointerPressed_QieHuan(x, y);
			break;
		case GmStat_TSGTiShi:
			pointerPressed_TSGTiShi(x, y);
			break;
		case GmStat_RankJX2:
			pointerPressed_RankJX2(x, y);
			break;
		case GmStat_sms:
			pointerPressed_sms(x, y);
			break;
		case GmStat_qiandao:
			pointerPressed_qiandao(x, y);
			break;
		case GmStat_dengdai_win:
			pointerReleased_dengdai_Win(x, y);
			break;
		case GmStat_RankJX:
			pointerPressed_RankJX(x, y);
			break;
		case GmStat_OpenAnima:
			pointerPressed_OpenAnima(x, y);
			break;
		case GmStat_RankMap:
			pointerPressed_CatchChooseb(x, y);
			pointerPressed_CatchChooses(x, y);
			pointerPressed_CatchChooseo(x, y);
			pointerPressed_TS(x, y);
			break;
		case GmStat_ShenJi:
			pointerPressed_ShenJi(x, y);
			break;
		case GmStat_ZhuJiao:
			pointerPressed_ZhuangBei2(x, y);
			break;
		case GmStat_Menu:
			pointerPressed_MENU(x, y);
			// pointerPressed_dubo(x, y);//choujiang
			break;
		case GmStat_About:
			pointerPressed_ABOUT(x, y);
			break;
		case GmStat_Help:
			pointerPressed_HELP(x, y);
			break;
		case GmStat_Option:
			pointerPressed_Option(x, y);
			break;
		case GmStat_Register:
			pointerPressed_Register(x, y);
			break;
		case GmStat_CatchChoose:
			pointerPressed_CatchChoose(x, y);
			break;
		case GmStat_TowerChoose:
			// pointerPressed_TowerChoose(x,y);
			break;
		case GmStat_Achieve:
			pointerPressed_Achieve(x, y);
			break;
		case GmStat_Teaching:
			pointerPressed_Teaching(x, y);
			break;
		case GmStat_GAMBLE:
			pointerPressed_dubo(x, y);
			break;
		case GmStat_Stop:
			pointerPressed_Stop(x, y);
			break;
		case GmStat_Playing:
			pointerPressed_Playing(x, y); // 游戏中按键
			break;
		case GmStat_Lose:
			pointerPressed_Lose(x, y);
			break;
		case GmStat_Win:
			pointerPressed_Win(x, y);
			break;
		case GmStat_Scripe:
			pointerPressed_scripe(x, y);
			break;
		case GmStat_Shop:
		   // pointerPressed_Shop(x, y);
			break;
		case GmStat_TA_BUILD:
			pointerPressed_zaota(x, y);
			break;
		case GmStat_TEACHING:
			pointerPressed_TeachReward(x, y);
			break;
		case GmStat_TEACHSHOP:
			pointerPressed_TeachSHOP(x, y);
			break;
		case GmStat_GOLDNOT:
			pointerPressed_GOLDNOT(x, y);
			break;
		case GmStat_SELLUP:
			//pointerPressed_SELLUP(x, y);
			break;

		}

	}

	public void pointerReleased(int x, int y) {
		pointMenu = -1;
		
		switch (gameStatus) {
		case GmStat_YDSms2:
			pointerReleased_GmStat_YDSms(x, y);
			break;
		case GmStat_YDSms:
			pointerReleased_GmStat_YDSms(x, y);
			break;
		case GmStat_RankJX2:
			//pointerReleased_RankJX2(x, y);
			break;
		case GmStat_sms:
			pointerReleased_sms(x, y);
			break;
		case GmStat_qiandao:
			pointerReleased_qiandao(x, y);
			break;
		case GmStat_jiangLi:
			pointerReleased_JiangLi(x, y);
			break;
		case GmStat_RankMap:
			pointerReleased_CatchChooseb(x, y);
			pointerReleased_CatchChooses(x, y);
			pointerReleased_CatchChooseo(x, y);
			break;
		case GmStat_OpenAnima:
			pointerReleased_OpenAnima(x, y);
			// pointerReleased_ZhuangBei(x,y);
			break;
		case GmStat_Menu:
			pointerReleased_MENU(x, y);
			// pointerReleased_dubo(x, y);//choujiang
			break;
		case GmStat_ShenJi:
			// pointerReleased_RankJX(x, y);
			pointerReleased_ShenJi(x, y);
			break;
		case GmStat_ZhuJiao:
			// pointerReleased_RankJX(x, y);
			if (MyGameView.wuQi_xuanZe == true) {
				pointerPressed_ZhuangBei(x, y);
				pointerReleased_ZhuangBei(x, y);
				pointerReleased_ZhuangBei2(x, y);

			}

			break;
		case GmStat_About:
			pointerReleased_ABOUT(x, y);
			break;
		case GmStat_Help:
			pointerReleased_HELP(x, y);
			break;
		case GmStat_Option:
			pointerReleased_Option(x, y);
			break;
		case GmStat_Register:
			pointerReleased_Register(x, y);
			break;
		case GmStat_CatchChoose:
			pointerReleased_CatchChoose(x, y);
			break;
		case GmStat_TowerChoose:
			// pointerReleased_TowerChoose(x,y);
			break;
		case GmStat_Achieve:
			pointerReleased_Achieve(x, y);
			break;
		case GmStat_Teaching:
			pointerReleased_Teaching(x, y);
			break;
		case GmStat_GAMBLE:
			pointerReleased_dubo(x, y);
			break;
		case GmStat_Playing:
			pointerReleased_Playing(x, y);
			break;
		case GmStat_Shop:
			pointerReleased_Shop(x, y);
			break;
		case GmStat_Stop:
			pointerReleased_Stop(x, y);
			break;
		case GmStat_Lose:
			pointerReleased_Lose(x, y);
			break;
		case GmStat_dengdai_lose:
			pointerReleased_dengdai_Lose(x, y);
			break;
		case GmStat_Win:
			pointerReleased_Win(x, y);
			break;

		case GmStat_Scripe:
			pointerReleased_scripe(x, y);
			break;
		case GmStat_TA_BUILD:
			pointerReleased_zaota(x, y);
			break;
		case GmStat_TEACHING:
			pointerReleased_TeachReward(x, y);
			break;
		case GmStat_TEACHSHOP:
			pointerReleased_TeachSHOP(x, y);
			break;
		case GmStat_GOLDNOT:
			pointerReleased_GOLDNOT(x, y);
			break;
		case GmStat_SELLUP:
			pointerReleased_SELLUP(x, y);
			break;
		}
	}

	/**
	 * 得到选择界面按钮序数
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	int getPointNum_Xuanzejiemian(int x, int y) {
		int point = -1;
		int[][] pointPos = { { 0, 35, 800, 170 } };
		point = getPoint(pointPos, x, y);
		return point;
	}

	int getPointNum_Achieve(int x, int y) {
		int point = -1;
		int[][] pointPos = { { 90, 90, 620, 345 } };
		point = getPoint(pointPos, x, y);
		return point;
	}

	/**
	 * 得到选择界面的按钮序数
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	int getPointNum_CatchChosse(int x, int y) {
		int point = -1;
		int[][] pointPos = { {} };
		point = getPoint(pointPos, x, y);
		return point;
	}

	public void move(int moveY) {// 960,720
		// this.iAchieveStartY = (short) Math.max(0,
		// Math.min(mapWidth-fishGame.VMWidth, this.iMapX + moveX));
		iAchieveStartY = (short) Math.max(0,
				Math.min(58 * 6, iAchieveStartY + moveY));
	}

	/**
	 * 得到商店界面按钮序数
	 * 
	 * @param x
	 * @param y
	 * @return
	 */

	int getPointNum_Shop(int x, int y) {
		int point = -1;
		int[][] pointPos = { { 60, 90, 660, 165 } };
		point = getPoint(pointPos, x, y);
		return point;
	}

	/**
	 * 得到Menu界面按钮序数
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	int getPointNum_Menu(int x, int y) {
		int point = -1;
		int[][] pointPos = { { 0, 0, 800, 480 } };
		point = getPoint(pointPos, x, y);
		return point;
	}

	/*
	 * 得到游戏中的按钮序数
	 */
	int getPointNum_Playing(int x, int y) {
		int point = -1;
		int[][] pointPos = { { 0, 60, 800, 420 } };
		point = getPoint(pointPos, x, y);
		return point;
	}

	/**
	 * 得到造塔界面的按键
	 */
	int getPointNum_zaota(int x, int y) {
		int point = -1;
		int[][] pointPos = { { 0, 0, 800, 480 } };
		return point;
	}

	/**
	 * 得到About界面按钮序数
	 */
	int getPointNum_Help_About(int x, int y) {
		int point = -1;
		int[][] pointPos = { { 0, 0, 800, 480 } };
		point = getPoint(pointPos, x, y);
		return point;
	}

	/**
	 * 得到Help界面按钮序数
	 */
	int getPointNum_Help(int x, int y) {
		int point = -1;
		int[][] pointPos = { { 0, 0, 800, 480 } };
		point = getPoint(pointPos, x, y);
		return point;
	}

	/**
	 * 得到商店界面磁铁购买下画道具的区域
	 */
	int getPointNum_shop_draw(int x, int y) {
		int point = -1;
		return point;
	}

	/**
	 * 得到暂停界面的区域
	 */
	int getPointNum_Stop(int x, int y) {
		int point = -1;
		int[][] pointPos = { { 255, 130, 280, 64 },// 帮助
				{ 255, 200, 280, 64 },// 商店
				{ 258, 270, 280, 64 },// 主菜单
				{ 160, 310, 80, 80 },// 重新开始
				{ 560, 310, 80, 80 },// 继续游戏
				{ 515, 100, 100, 100 } // 音乐按键
		};
		return point;
	}

	/**
	 * 手指滑动
	 * 
	 * @param x
	 * @param y
	 */
	int everCold;

	public void pointerMoved(int x, int y) {

		switch (gameStatus) {
		case GmStat_CatchChoose:
			switch (getPointNum_Xuanzejiemian(x, y)) {
			case 0:
				if (engine.bTeach == false) {
				}
				if (Math.abs(x - iTouchDownX) >= 8) {// 加入滑屏超过150
					engine.iCatchMove = x - iTouchDownX;
				}
				break;
			}
			break;
		case GmStat_Achieve:
			switch (getPointNum_Achieve(x, y)) {
			case 0:
				if (Math.abs(y - iTouchDownY) > 8) {
					move(-(y - iTouchDownY));
					iTouchDownX = x;
					iTouchDownY = y;
				}
				break;
			}
			break;
		case GmStat_TA_BUILD:
			break;
		case GmStat_Playing:

			if (engine.usBs.iUsBsCuJinbi > engine
					.getLaiJiaDJ(ZB[engine.usBs.iUsBsRank - 1])) {
				// engine.usBs.iUsBsCuJinbi-=engine.getLaiJiaDJ(ZB[engine.usBs.iUsBsRank-1]);

			} else {
				if (engine.usBs.iUsBsCuJinbi < 1) {
					engine.usBs.iUsBsCuJinbi = 1;
				}
				engine.usBs.iUsBsRank = 1;
				engine.usBs.iUsBsCurAttack = ZBXingXi[ZB[engine.usBs.iUsBsRank - 1]][3];
				engine.Speed = ZBXingXi[ZB[engine.usBs.iUsBsRank - 1]][8];
				if (engine.countSms == 0) {
					engine.countSms = 1;
					MyGameCanvas.setST(MyGameCanvas.GmStat_YDSms2);
				}
			}

			switch (getPointNum_Playing(x, y)) {
			case 0:
				if (Math.abs(x - iTouchDownX) > 15
						|| Math.abs(y - iTouchDownY) > 15) {
					bTouchMoving = true;
				}
				if (IsInRect(iTouchDownX, iTouchDownY, 5, 420, 60, 75)
						&& engine.JN1 < 50) {
//					if(!GameBilling.isBilling(GameBilling.Billing_技能冷却时间永久减半)){
//						setST(GmStat_YDSms);
//					}
					if(BillingResult.sms_RMS[BillingResult.Billing_技能冷却时间永久减半]!=BillingResult.SMS_SUCCESS){
						setST(GmStat_YDSms);
					}
				}
				if (IsInRect(iTouchDownX, iTouchDownY, 5, 420, 60, 75)
						&& engine.JN1 > 61) {// 点击技能1键滑屏
					if (engine.role.iSkillCoolTime[0] <= 0) {
						engine.skillPushX = x;
						engine.skillPushY = y;
						engine.skillPushType = 1;
					}
					engine.usBs.bHomeAttack = false;
				} else if (IsInRect(iTouchDownX, iTouchDownY, 0, 430, 800, 50)) {// 屏幕最底下一排触屏无效
					engine.usBs.bHomeAttack = false;
				} else if (IsInRect(iTouchDownX, iTouchDownY, 0, 0, 800, 60)) {// 屏幕最上面一排触屏无效
					engine.usBs.bHomeAttack = false;
				} else {
					if ((y * ((float) SCREEN_HEIGHT / (float) MyActivity.VMHeight)) > 430) {
						return;
					}

					if (bShovel == false && bPropShow == false
							&& bToTower == false
							&& !(IsInRect(x, y, 1700, 230, 180, 95))) {
						if (engine.usBs.iUsBsCuJinbi >= 4) {

							engine.usHomeAttack(x, y); // 老家射击

						} else if (engine.usBs.iUsBsCuJinbi > 0) {
							if (engine.usBs.iUsBsRank >= iGold) {
								// engine.usBs.iUsBsRank=iGold;
								engine.usHomeAttack(x, y);

							} else {
								engine.usHomeAttack(x, y);

							}
						} else if (engine.usBs.iUsBsCuJinbi == 0) {
							engine.usBs.iUsBsRank = 1;
							engine.usHomeAttack(x, y);

						}
					}
				}
				break;
			}
			break;
		case GmStat_Shop:
	
			break;
		case GmStat_TowerChoose:
			break;
		case GmStat_Menu:
			switch (getPointNum_Menu(x, y)) {
			case 0:
				if (IsInRect(x, y, 280, 285, 240, 95)) {
					pointMenu = 0;
				}// 剧情模式
				else if (IsInRect(x, y, 280, 380, 240, 95)) {
					pointMenu = 1;
				}// 挑战模式
				else {
					pointMenu = -1;
				}
				break;
			}
			break;
		case GmStat_Help:
			switch (getPointNum_Help(x, y)) {
			case 0:
				if (IsInRect(x, y, 720, 400, 80, 80)) {
					pointMenu = 0;
				} else if (IsInRect(x, y, 10, 195, 80, 90)) {
					pointMenu = 1;
				} else if (IsInRect(x, y, 720, 195, 80, 90)) {
					pointMenu = 2;
				} else {
					pointMenu = -1;
				}
				break;
			}
			break;
		case GmStat_Stop:
			if (IsInRect(x, y, 255, 130, 280, 64)) {
				pointMenu = 0;
			} else if (IsInRect(x, y, 255, 200, 280, 64)) {
				pointMenu = 1;
			} else if (IsInRect(x, y, 258, 270, 280, 64)) {
				pointMenu = 2;
			} else if (IsInRect(x, y, 160, 310, 80, 80)) {
				pointMenu = 3;
			} else if (IsInRect(x, y, 560, 310, 80, 80)) {
				pointMenu = 4;
			}
			// else if(IsInRect(x, y, 515,100,100,100)){pointMenu=5;}
			else {
				pointMenu = -1;
			}
			break;
		case GmStat_About:
			switch (getPointNum_Help_About(x, y)) {
			case 0:
				if (IsInRect(x, y, 710, 400, 80, 80)) {
					pointMenu = 0;
				} else {
					pointMenu = -1;
				}
				break;
			}
			break;

		}
	}

	int yyy = -10;
	int xxx;
	int speed;

	void drawJinBi(boolean isDraw) {
		if (isDraw) {
			checkSpeed();
			GameDraw.drawNumber55(PAK_IMAGES.IMG_16,
					engine.getLaiJiaDJ(ZB[engine.usBs.iUsBsRank - 1]),
					350 + xxx, 390 + speed, 20, 0, 0, 600, 20, 0, true);

			GameDraw.add_Image(PAK_IMAGES.IMG_16, 320 + xxx, 390 + speed, 200,
					0, 20, 20, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 6000);

			GameDraw.add_Image(PAK_IMAGES.IMG_16, 380 + xxx, 390 + speed, 240,
					0, 20, 20, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 6000);
		}
	}

	void checkSpeed() {
		xxx -= 2;
		yyy += 1;
		speed += yyy;
		if (yyy > 10) {
			engine.isDraw = false;
		}
		if (yyy == -9) {
			if (ZB[engine.usBs.iUsBsRank - 1] == 1
					|| ZB[engine.usBs.iUsBsRank - 1] == 2) {
				engine.usBs.iUsBsCuJinbi -= engine
						.getLaiJiaDJ(ZB[engine.usBs.iUsBsRank - 1]);
			}

		}
	}

	/**
	 * 得到点击8块小地图的序数
	 */
	int getPoint_CatchChoose(int x, int y) {
		int point = -1;
		int[][] pointPos = { { 35, 250, 135, 90 }, { 195, 250, 135, 90 },
				{ 355, 250, 135, 90 }, { 515, 250, 135, 90 },
				{ 35, 360, 135, 90 }, { 195, 360, 135, 90 },
				{ 355, 360, 135, 90 }, { 515, 360, 135, 90 } };
		point = getPoint(pointPos, x, y);
		return point;
	}

	/**
	 * 得到选中哪个场景,用于大地图选择时
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	int getPoint_Xuanzejiemian_count(int x, int y) {
		int point = -1;
		return point;
	}

	/**
	 * 得到关卡界面按钮序数
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	int getPointNum_Guanka(int x, int y) {
		int point = -1;
		int[][] pointPos = { { 0, 400, 80, 80 },// 箭头返回
				{ 50 + 250 * 0, 35, 182, 204 },// 第一关
				{ 50 + 250 * 1, 35, 182, 204 },// 第2关
				{ 50 + 250 * 2, 35, 182, 204 },// 第3关
				{ 50 + 250 * 0, 235, 182, 204 },// 4
				{ 50 + 250 * 1, 235, 182, 204 },// 5
				{ 50 + 250 * 2, 235, 182, 204 },// 6
		};
		point = getPoint(pointPos, x, y);
		return point;
	}

	/**
	 * 得到选中哪个场景,用于商店选择时
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	int getPoint_Shop_count(int x, int y) {
		int point = -1;
		int[][] pointPos = { { 0, 0, 800, 480 } };
		point = getPoint(pointPos, x, y);
		return point;
	}

	/**
	 * 得到选中的哪个按键，用于Menu界面
	 * 
	 * @param file
	 * @return
	 */
	int getPoint_Menu_count(int x, int y) {
		int point = -1;
		int[][] pointPos = { { 55, 410, 108, 70 },/* 开始游戏 */
		{ 205, 410, 108, 70 },/* 帮助 */
		{ 355, 410, 108, 70 },/* 关于 */
		{ 505, 410, 108, 70 },/* 更多 */
		{ 655, 410, 108, 70 },/* 退出 */
		{ 720, 0, 80, 80 } /* 音乐 */
		};
		point = getPoint(pointPos, x, y);
		return point;

	}

	public DataInputStream getDataInputStream(int file) {
		InputStream in = gameView.getResources().openRawResource(file);
		return new DataInputStream(in);
	}




	/**
	 * 处理点击塔图标的时候 是否可以造塔
	 * 
	 * @param type
	 */
	void everCanTower(int type) {
		// if(type<=0||engine.iPropNum<=0||engine.usBs.iUsBsCuJinbi<buildWaste[type-1][0])return;
		if (engine.teachStep != 9) {
			bPropShow = !bPropShow;
		}// 即教学第四部必须造塔
		bHuangquanquan = (bPropShow == true ? true : false);
		bShovel = false;
		iTowerType = type;
	}

	/**
	 * 每关胜利结束后获得的评价
	 * 
	 * @param status根据关卡选择界面时选择的模式判别
	 */
	int iPrideWin;// 每关胜利获得奖励
	int iPrideTime;// 每关通关奖励

	public void GotPride(int status) {
		switch (gmStatus) {
		case gmScripe:
			if (engine.iRunNum == 0) {// 根据跑掉的敌人去算评价星星的等级
				// engine.iResult[engine.gameRank/8][engine.gameRank%8]=4;
			} else if (engine.iRunNum <= 5) {
				// engine.iResult[engine.gameRank/8][engine.gameRank%8]=3;
			} else {
				// engine.iResult[engine.gameRank/8][engine.gameRank%8]=2;
			}// 大关卡数*（100-10*漏网之鱼）
			iPrideWin = 10 * (engine.gameRank / 8 + 1) * (10 - engine.iRunNum);
			break;
		case gmFight:
			engine.iFightResult[engine.iFightGameRank] = 4;
			switch (engine.iFightGameRank) {
			case 0:
				iPrideWin = 2500;
				break;
			case 1:
				iPrideWin = 6000;
				break;
			case 2:
				iPrideWin = 10000;
				break;
			}
			break;
		}
		if (engine.iAchieve[8] == 0 && engine.gameRank >= 1) {// 剧情模式过关
			engine.iAchieve[8] = 1;
			engine.iAchieveNum = 8;
			AddBlastEffectList(400, 680, EFF_GETACHIEVE, 0, 180, 0);// 触发第9个成就
		}
		if (engine.iAchieve[1] == 0 && engine.iFightGameRank >= 1) {// 挑战模式过关
			engine.iAchieve[1] = 1;
			engine.iAchieveNum = 1;
			AddBlastEffectList(400, 680, EFF_GETACHIEVE, 0, 180, 0);// 出发第2个成就
		}
		if (engine.iRunNum == 0 && engine.iAchieve[2] == 0) {// 剧情或者挑战模式,只要触发此条件,达成成就3
			engine.iAchieve[2] = 1;
			engine.iAchieveNum = 2;
			AddBlastEffectList(400, 680, EFF_GETACHIEVE, 0, 180, 0);// 触发第3个成就
		}
		if (engine.bBuildBing == false && engine.iAchieve[4] == 0) {
			engine.iAchieve[4] = 1;
			engine.iAchieveNum = 5;
			AddBlastEffectList(400, 680, EFF_GETACHIEVE, 0, 180, 0);// 触发第5个成就
		}
		iGold += iPrideWin;
		saveGame();
	}

	/**
	 * 每关评价完后的操作结果 继续游戏或者退出游戏
	 * 
	 * @param status根据关卡选择界面时选择的模式判别
	 */
	public void WinLogic() {
		// if(engine.gameRank>=99){
		// return;
		// }
		// switch (gmStatus) {
		// case gmScripe:
		if (engine.gameRank <= 23) {
			// dRank = engine.gameRankOpen/6;
			switch (engine.laoJiaHP / 30) {
			case 0:
				engine.iResult[engine.gameRank / 6][engine.gameRank % 6] = 1;
				break;
			case 1:
				engine.iResult[engine.gameRank / 6][engine.gameRank % 6] = 2;
				break;
			case 2:
			case 3:
			default:
				engine.iResult[engine.gameRank / 6][engine.gameRank % 6] = 3;
				break;
			}
			if (engine.gameRank / 6 != 0) {
				if (engine.gameRank % 6 == 2 || engine.gameRank% 6 == 5) {
					engine.TSRabk[(((engine.gameRank / 6) * 2) + (engine.gameRank % 6 >= 3 ? 1
							: 0))] = 0;
				}

			} else {

			}
			System.out.println("aaaa ："
							+ (((engine.gameRank / 6) * 2) + (engine.gameRank % 6 >= 3 ? 1
									: 0)));
			engine.gameRank++;

			if (engine.gameRank >= engine.gameRankOpen) {// 记录玩家玩到的最大关卡数

				engine.gameRankOpen = engine.gameRank;

				if (engine.gameRank / 6 != 0) {
					 if (engine.gameRank % 6 == 0&&engine.TSRabk[1]==-1) {
					 engine.TSRabk[1] = 0;
					 }
					engine.iResult[engine.gameRank / 6][engine.gameRank % 6] = 0;	


				} else {
					if (engine.gameRank % 6 == 2) {
						engine.TSRabk[engine.gameRank / 6
								+ (engine.gameRank % 6 >= 3 ? 1 : 0)] = 0;
					} else {
						engine.iResult[engine.gameRank / 6][engine.gameRank % 6] = 0;
					}

				}
			}
			engine.mapRank = engine.gameRankOpen / 6;

			Tools.removeAllImage();
			if (pointMenu != -1) {
				setST(GmStat_ShenJi);
			}

		} else {// 通关了
			if (engine.gameRank > 50) {
				engine.gameRank = TiaoZhan;
			} else {
				setST(GmStat_Menu);
			}

		}

		saveGame();
	}

	/**
	 * 得到bin文件数据
	 * 
	 * @param name
	 * @return
	 */
	public short[][] initData_2(String name) { // 0 clipdata，1
		// checkdata
		// 2以后framedata
		short[][] kbz = null;
		InputStream is = null;
		AssetManager am = this.context.getResources().getAssets();
		DataInputStream tempDS = null;
		try {
			is = am.open("bin/" + name + ".bin");
			tempDS = new DataInputStream(is);
			short[] PIC_CLIP_INFO;
			short[][] PIC_FRAME_INFO;
			short[] ANIM_CHECK_RANGE;
			@SuppressWarnings("unused")
			int k1 = 0;
			int i1 = tempDS.readShort();
			k1 += 2;
			PIC_CLIP_INFO = new short[i1 * 4];
			for (int i = 0; i < PIC_CLIP_INFO.length; i++) {
				PIC_CLIP_INFO[i] = tempDS.readShort();
			}
			i1 = tempDS.readShort();
			ANIM_CHECK_RANGE = new short[i1 * 8];
			PIC_FRAME_INFO = new short[i1][];
			for (int m = 0; m < i1; m++) {
				try {
					int i3 = tempDS.readShort();
					k1 += 2;
					int i4 = i3 << 2;
					PIC_FRAME_INFO[m] = new short[i4];
					k1 += READ_DETAIL_DATEaa(tempDS, PIC_FRAME_INFO[m], 0, i4); // SpriteList
				} catch (Exception e) {
				}
			}
			for (int m = 0; m < i1 * 8; m++) {
				ANIM_CHECK_RANGE[m] = tempDS.readShort();
				// System.out.print(ANIM_CHECK_RANGE[m] + ",");
			}

			kbz = new short[2 + PIC_FRAME_INFO.length][];
			kbz[0] = new short[PIC_CLIP_INFO.length];
			for (int i = 0; i < PIC_CLIP_INFO.length; i++) {
				kbz[0][i] = PIC_CLIP_INFO[i];
			}
			kbz[1] = new short[ANIM_CHECK_RANGE.length];
			for (int i = 0; i < ANIM_CHECK_RANGE.length; i++) {
				kbz[1][i] = ANIM_CHECK_RANGE[i];
			}
			for (int i = 0; i < PIC_FRAME_INFO.length; i++) {
				kbz[2 + i] = new short[PIC_FRAME_INFO[i].length];
				for (int j = 0; j < PIC_FRAME_INFO[i].length; j++) {
					kbz[2 + i][j] = PIC_FRAME_INFO[i][j];
				}
			}

		} catch (Exception e) {
			Log.i("人物数据", "name :" + name);
			e.printStackTrace();
		}
		return kbz;
	}

	static int READ_DETAIL_DATEbb(DataInputStream is, byte aByte[], int i1,
			int i2) {
		try {
			for (int k = 0; k < i2; k++) {
				aByte[i1 + k] = is.readByte();
			}
			return i2;
		} catch (Exception _ex) {
			return -1;
		}
	}

	static int READ_DETAIL_DATEaa(DataInputStream is, short aShort[], int i1,
			int i2) {
		try {
			for (int k = 0; k < i2; k++) {
				aShort[i1 + k] = is.readShort();
			}
			return i2;
		} catch (Exception _ex) {
			return -1;
		}
	}

	static int bToi(byte byte0) {
		int i = byte0;
		if (byte0 < 0) {
			i += 256;
		}
		return i;
	}

	static int READ_DETAIL_DATE(InputStream is, short aShort[], int i1, int i2) {
		try {
			for (int k = 0; k < i2; k++) {
				int k1 = (is.read() & 0xff) << 8;
				int k2 = (is.read() & 0xff);

				aShort[i1 + k] = (short) (k1 | k2);
				// HLUtil.debugPrintln("abyte[i1 + k]: " + abyte[i1 + k]);
			}

			return i2;
		} catch (Exception _ex) {
			return -1;
		}
	}

	/***************************** 存档 *****************************************************/

	static String[] smsData = { "sms" };
	static String[] smsMessage = { "0611C3204511022101025511022101011601MC099640000000000000000000000000" };
	static boolean[] sendSuccess = new boolean[] { false };
	protected static final byte R_ACTIVE = 0;

	static boolean runStop = false;
	static boolean isToSMS = false;
	static boolean isSended = false;

	static long rechargeStartTime = 0;


	public boolean onBackKeyPressed() {
		switch (gameStatus) {
		case GmStat_Menu:
		case GmStat_Playing:
			if (gameStatus == GmStat_Playing) {
				setST(GmStat_Stop);
			}
			MyActivity.instance.everExit();
			break;
		case GmStat_Help:
		case GmStat_About:
		case GmStat_Option:
			break;
		case GmStat_CatchChoose:
			Tools.removeAllImage();
			setST(GmStat_Menu);
			break;
		}
		return false;

	}

	public void drawStop() {

		GameDraw.add_Rect(0, 0, 800, 480, true, Tools.TOP_LEFT, 0x88000000,
				3000);
		GameDraw.add_ImageScale(PAK_IMAGES.IMG_BEIBAN, 400, 240,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 3000, 1.0f, 1.0f);
		GameDraw.add_Image(PAK_IMAGES.IMG_MUSHI5, 400, 100,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 3000);

		GameDraw.add_Image(pointMenu2 == 0 ? PAK_IMAGES.IMG_BANGZHUZI2
				: PAK_IMAGES.IMG_BANGZHUZI, 400, 170, Tools.HCENTER_VCENTER,
				Tools.TRANS_NONE, 3000);

		GameDraw.add_Image(pointMenu2 == 1 ? PAK_IMAGES.IMG_SHEZHIZI2
				: PAK_IMAGES.IMG_SHEZHIZI, 400, 225, Tools.HCENTER_VCENTER,
				Tools.TRANS_NONE, 3000);

		GameDraw.add_ImageScale(pointMenu2 == 2 ? PAK_IMAGES.IMG_FANHUICAIDAN2
				: PAK_IMAGES.IMG_FANHUICAIDAN, 400, 280, Tools.HCENTER_VCENTER,
				Tools.TRANS_NONE, 3000, 0.94f, 0.94f);

		GameDraw.add_Image(pointMenu2 == 3 ? PAK_IMAGES.IMG_TUICHUYOUXI33_2
				: PAK_IMAGES.IMG_TUICHUYOUXI33, 400, 345,
				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 3000);

	}

	/**
	 * 根据触摸区域的属性，算出下一步的操作
	 */
	public void setOperate(int ip, int type, int n) {
		switch (engine.map.propData[ip]) {
		case 17:// 当前属性块可以造塔
			if (type != 0) {
				setProprety(type, n, TA_造塔);
			} else {// 标示铲子在先前一步已经被选中
				// if(engine.bTeach==true&&engine.teachStep<8){
				// return;
				// }
				bShovel = false;
			}
			break;
		case PROP_LIEYU:
		case PROP_BINGJING:
			// case PROP_DIANWANG:
		case PROP_YULEI:
		case PROP_HERO:
		case PROP_HUOPAO:
			// case PROP_ZENGFU:
		case PROP_XIQIAN:
			if (bShovel == true) {// 如果是在铲子的前提下,则卖塔
				// int sellGot = sellGet[engine.map.propData[ip]-90][0];
				// setProprety(engine.map.propData[ip],sellGot,TA_卖或升级);
			} else if (bPropShow == true) {
			} else {// 否则对塔进行升级
				bToTower = true;
				// engine.bullets.removeAllElements();
				setST(GmStat_SELLUP);
			}
			break;
		case 12:// 铲子铲小违建
	
			if (bShovel == true) {
				if (iGold >= 50) {
					int iWhich = engine.getUnLawIndex(ip
							% (engine.mapWidth / 60), (ip - ip
							% (engine.mapWidth / 60))
							/ engine.map.mapSize[0]);// 判断选取的究竟是哪个违建
					GameRole unlaw = (GameRole) engine.unLawBuilds
							.elementAt(iWhich);
					unlaw.setStatus(unlaw.STATUS_DEAD);
					// iGold-=50;
					AddBlastEffectList(ip % (engine.mapWidth / 60)
							* engine.map.tileWidth + 30,
							(ip - ip % (engine.mapWidth / 60))
									/ engine.map.mapSize[0]
									* engine.map.tileWidth + 30,
							EFF_UNLAWDEADBIG, 0, 119, 0);
					engine.map.propData[ip] = 17;
					// iGold+=50;
					engine.usBs.iUsBsCuJinbi += 10;
					bShovel = false;
					engine.iPropNum++;
				} else {// 金币不足
				}
			}
			// }
			break;
		case 13:// 铲子铲大违建
			// if(engine.bTeach==true){if(engine.teachStep==9){return;}}
			if (bShovel == true) {
				if (iGold >= 50) {
					int iWhich = engine.getUnLawIndex(ip
							% (engine.mapWidth / 60), (ip - ip
							% (engine.mapWidth / 60))
							/ engine.map.mapSize[0]);// 判断选取的究竟是哪个违建
					GameRole unlaw = (GameRole) engine.unLawBuilds
							.elementAt(iWhich);
					unlaw.setStatus(unlaw.STATUS_DEAD);
					// iGold-=50;
					AddBlastEffectList(ip % (engine.mapWidth / 60)
							* engine.map.tileWidth + 30,
							(ip - ip % (engine.mapWidth / 60))
									/ engine.map.mapSize[0]
									* engine.map.tileWidth + 30,
							EFF_UNLAWDEADBIG, 0, 119, 0);
					engine.map.propData[ip] = 17;
					engine.map.propData[ip + 1] = 17;
					engine.map.propData[ip + 13] = 17;
					engine.map.propData[ip + 14] = 17;
					// iGold+=200;
					engine.usBs.iUsBsCuJinbi += 50;
					bShovel = false;
					engine.iPropNum += 4;
				} else {// 金币不足
				}
			}
			break;
		default:// 不能造也不能卖
	
		}
	}

	/**
	 * 点击选中塔后，对于出现的卖或者升级界面
	 * 
	 */
	public void BuildLogic(int status) {
		TA_STATUS = status;
		if (TA_STATUS != TA_LASTSTATUS) {
			fTaScale = 0;
			TA_LASTSTATUS = TA_STATUS;
		}
		switch (status) {
		case TA_卖或升级:
			if (fTaScale <= 1.2f) {
				fTaScale += 0.2f;
			}
			break;
		case TA_造塔:
			if (fTaScale <= 1.0f) {
				fTaScale += 0.1f;
			}
			break;
		}
	}

	/**
	 * 改变地图属性 造/升级/卖 塔
	 * 
	 * @param status
	 *            判断是造塔还是卖塔
	 * @param type造的什么塔
	 * @param n
	 *            造该种塔这个等级所需的价格
	 */
	public void setProprety(int type, int n, int status) {
		switch (status) {
		case TA_造塔:
			switch (type) {
			case GameRole.TA_LIEYU:// 造塔时候直接考虑到商城塔已有的等级
				AddBlastEffectList(
						(iProp % (engine.mapWidth / 60)) * engine.map.tileWidth
								+ 25,
						engine.map.tileWidth
								* ((iProp - iProp % (engine.mapWidth / 60)) / engine.map.mapSize[0])
								+ 30, EFF_TOWERBUILD, 0, 800, 0);
				engine.sprites.addElement(new GameRole(iProp
						% (engine.mapWidth / 60), (iProp - iProp
						% (engine.mapWidth / 60))
						/ engine.map.mapSize[0], engine.role.TA_LIEYU, 130, 4,
						0));
				engine.map.propData[iProp] = PROP_LIEYU;// 猎鱼塔 1-1
				engine.usBs.iUsBsCuJinbi -= n;
				bPropShow = false;// 造完塔后重置
				engine.iPropNum--;

				break;
			case GameRole.TA_BINGJING:
				AddBlastEffectList(
						(iProp % (engine.mapWidth / 60)) * engine.map.tileWidth
								+ 25,
						engine.map.tileWidth
								* ((iProp - iProp % (engine.mapWidth / 60)) / engine.map.mapSize[0])
								+ 30, EFF_TOWERBUILD, 0, 800, 0);
				engine.sprites.addElement(new GameRole(iProp
						% (engine.mapWidth / 60), (iProp - iProp
						% (engine.mapWidth / 60))
						/ engine.map.mapSize[0], engine.role.TA_BINGJING, 150,
						5, 0));
				engine.map.propData[iProp] = PROP_BINGJING;// 冰晶塔 1-2
				engine.usBs.iUsBsCuJinbi -= n;
				engine.bBuildBing = true;// 此关建造了冰晶塔
				bPropShow = false;
				engine.iPropNum--;
				break;
			case GameRole.TA_YULEI:
				AddBlastEffectList(
						(iProp % (engine.mapWidth / 60)) * engine.map.tileWidth
								+ 25,
						engine.map.tileWidth
								* ((iProp - iProp % (engine.mapWidth / 60)) / engine.map.mapSize[0])
								+ 30, EFF_TOWERBUILD, 0, 800, 0);
				engine.sprites.addElement(new GameRole(iProp
						% (engine.mapWidth / 60), (iProp - iProp
						% (engine.mapWidth / 60))
						/ engine.map.mapSize[0], engine.role.TA_YULEI, 190, 10,
						0));
				engine.map.propData[iProp] = PROP_YULEI;// 鱼雷塔2-1
				engine.usBs.iUsBsCuJinbi -= n;
				bPropShow = false;
				engine.iPropNum--;
				break;
			case GameRole.TA_HERO:
				AddBlastEffectList(
						(iProp % (engine.mapWidth / 60)) * engine.map.tileWidth
								+ 25,
						engine.map.tileWidth
								* ((iProp - iProp % (engine.mapWidth / 60)) / engine.map.mapSize[0])
								+ 30, EFF_TOWERBUILD, 0, 800, 0);
				engine.sprites.addElement(new GameRole(iProp
						% (engine.mapWidth / 60), (iProp - iProp
						% (engine.mapWidth / 60))
						/ engine.map.mapSize[0], engine.role.TA_HERO, 160, 25,
						0));
				engine.map.propData[iProp] = PROP_HERO;// 英雄塔 2-2
				engine.usBs.iUsBsCuJinbi -= n;
				bPropShow = false;
				engine.iPropNum--;
				break;
			case GameRole.TA_HUOPAO:
				AddBlastEffectList(
						(iProp % (engine.mapWidth / 60)) * engine.map.tileWidth
								+ 25,
						engine.map.tileWidth
								* ((iProp - iProp % (engine.mapWidth / 60)) / engine.map.mapSize[0])
								+ 30, EFF_TOWERBUILD, 0, 800, 0);
				engine.sprites.addElement(new GameRole(iProp
						% (engine.mapWidth / 60), (iProp - iProp
						% (engine.mapWidth / 60))
						/ engine.map.mapSize[0], engine.role.TA_HUOPAO, 170,
						10, 0));
				engine.map.propData[iProp] = PROP_HUOPAO;// 火炮塔 3-1
				engine.usBs.iUsBsCuJinbi -= n;
				bPropShow = false;
				engine.iPropNum--;
				break;

			case GameRole.TA_XIQIAN:
				AddBlastEffectList(
						(iProp % (engine.mapWidth / 60)) * engine.map.tileWidth
								+ 25,
						engine.map.tileWidth
								* ((iProp - iProp % (engine.mapWidth / 60)) / engine.map.mapSize[0])
								+ 30, EFF_TOWERBUILD, 0, 800, 0);
				engine.sprites.addElement(new GameRole(iProp
						% (engine.mapWidth / 60), (iProp - iProp
						% (engine.mapWidth / 60))
						/ engine.map.mapSize[0], engine.role.TA_XIQIAN, 1000,
						10, 0));
				engine.map.propData[iProp] = PROP_XIQIAN;// 吸钱塔 3-3
				engine.usBs.iUsBsCuJinbi -= n;
				bPropShow = false;
				engine.iPropNum--;
				break;
			}
			break;
		case TA_卖或升级:// 铲子卖塔
	
			int iWhich = engine.getSpriteIndex(iProp % (engine.mapWidth / 60),
					(iProp - iProp % (engine.mapWidth / 60))
							/ engine.map.mapSize[0]);// 判断选取的究竟是哪个塔
			GameRole sprite = (GameRole) engine.sprites.elementAt(iWhich);
			// iGold+=n;
			// engine.usBs.iUsBsCuJinbi+=n;
			engine.iTaSellNum++;
			if (engine.iTaSellNum >= 10 && engine.iAchieve[3] == 0) {
				engine.iAchieve[3] = 1;
				engine.iAchieveNum = 4;
				AddBlastEffectList(400, 680, EFF_GETACHIEVE, 0, 180, 0);// 出发第4个成就
			}
			engine.map.propData[iProp] = 17;
			// iGold-=50;
			bShovel = false;
			AddBlastEffectList(sprite.x - engine.iMapX,
					sprite.y - engine.iMapY, EFF_UPLEVEL, 0, 800, 0);
			engine.sprites.removeElementAt(iWhich);
			engine.iPropNum++;
			// }
			break;
		}

	}


	/**
	 * 商城炮塔下塔能否升级的逻辑 iShopStartX值决定被选中的塔 第8个塔吸钱塔情况，作为计费点去做 升级的前提是塔是否开启
	 */
	void everLevelUp(int m) {
	}

	/**
	 * 判断商城中是否出发成就
	 * 
	 * @param n
	 */
	public void isGet(int n) {
	}

	/**
	 * 获取down屏幕时候地图属性块
	 * 
	 * @param x
	 * @param y
	 */
	int tempProp;

	void getDownProp(int x, int y) {
		int ip = getMapIndex(x, y);
		tempProp = engine.map.propData[ip];
		if (tempProp == PROP_LIEYU || tempProp == PROP_BINGJING
				|| tempProp == PROP_YULEI || tempProp == PROP_HERO
				|| tempProp == PROP_HUOPAO || tempProp == PROP_XIQIAN) {
			bToTower = true;
		} else {
			bToTower = false;
		}
	}

	/**
	 * 初始游戏中的所有bin数据
	 * 
	 */
	public void initBinData() {

		data_TYPE_ENEMY_步兵 = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_BUBING]);
		data_TYPE_ENEMY_牧师 = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_ANMU]);
		data_TYPE_ENEMY_刺客 = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_CIKE]);
		data_TYPE_ENEMY_光头 = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_GUANGTOU]);
		data_TYPE_ENEMY_鹰 = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_YING]);
		data_TYPE_ENEMY_石盾 = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_SHIDUN]);
		data_TYPE_ENEMY_金盾 = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_JINDUN]);
		data_TYPE_ENEMY_绿盾 = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_LVDUN]);
		data_TYPE_ENEMY_紫盾 = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_ZIDUN]);
		data_TYPE_ENEMY_暗牧 = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_ANMU]);
		data_TYPE_ENEMY_狮鹫 = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_SHIJIU]);
		data_huoqiuTX = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_HUOQIU]);
		data_siwang = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_SIWANG]);
		data_xihongshibaozha = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_XIHONGSHIBAOZHA]);
		data_role_dog = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_ROLE_DOG]);
		data_SANDAN = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_SANDAN]);
		data_MAOZI = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_MAOZI]);
		data_YUANZIDAN = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_YUANZIDAN]);
		data_JIANSU = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_JIANSU]);
		data_BAOZHA = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_BAOZHA]);
		data_GUANCAI = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_GUANCAI]);
		data_WUYA = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_WUYA]);
		data_jijia = initData_2(PAK_BIN.FILESNAME[PAK_BIN.BIN_JIJIA]);
	}


	/**
	 * 商城及关卡选择界面中，new与hot的上下抖动 shakeDelay 抖动帧数 shakeStatus 当前状态下的抖动 shakeLast
	 * 上次状态的抖动 shakeMoveY 抖动的幅度
	 */
	int shakeDelay;
	int shakeMoveY;
	int shakeStatus;
	int shakeLast = GmStat_CatchChoose;

	void upDownShake(int status) {
		setShakeStatus(status);
		shakeDelay++;
		switch (status) {
		case GmStat_CatchChoose:
		case GmStat_Shop:
		case GmStat_SELLUP:
			if (shakeDelay > 34) {
				shakeDelay = 0;
			}
			if (shakeDelay <= 16 && shakeDelay % 2 == 0) {
				shakeMoveY -= 2;
			}
			if (shakeDelay <= 34 && shakeDelay > 16 && shakeDelay % 2 == 0) {
				shakeMoveY += 2;
			}
			break;
		}
	}

	/**
	 * 重置抖动状态发生改变
	 */
	void setShakeStatus(int st) {
		shakeStatus = st;
		if (shakeStatus != shakeLast) {// 状态发生切换，数据重置
			shakeDelay = 0;
			shakeMoveY = 0;
			shakeLast = shakeStatus;
		}
	}

	/**
	 * 判断是否在矩形框内
	 * 
	 * @return
	 */
	public static boolean IsInRect(int x, int y, int sx, int sy, int w, int h) {
		int sw = MyActivity.VMWidth;
		int sh = MyActivity.VMHeight;
		float kx = (float) sw / MyGameCanvas.SCREEN_WIDTH;
		float ky = (float) sh / MyGameCanvas.SCREEN_HEIGHT;
		if (x > sx * kx && x < (sx + w) * kx && y > sy * ky
				&& y < (sy + h) * ky) {
			return true;
		}
		return false;
	}

	/**
	 * 存储数据
	 * 
	 * @param mpt
	 */
	public void saveGame() {
		pps.SetDataAndOffset(null, 0, 0);
		pps.InsertInt(GameEngine.me.usBs.iUsBsCuJinbi);
		pps.InsertInt(GameEngine.fuhuo);
		pps.InsertInt(engine.isJB);
		for (int j = 0; j < 4; j++) {// 每关评价
			for (int g = 0; g < 6; g++) {
				if (engine.iResult[j][g] == -1) {
					pps.InsertInt(999);
				} else {
					pps.InsertInt(engine.iResult[j][g]);
				}
			}
		}
		for (int i = 0; i < jiNengKaiQi.length; i++) {
			if (jiNengKaiQi[i] == -1) {
				pps.InsertInt(999);
			} else {
				pps.InsertInt(jiNengKaiQi[i]);
			}

		}
		for (int t = 0; t < ZBXingXi.length; t++) {
			if (ZBXingXi[t][0] == -1) {
				pps.InsertInt(999);
			} else {
				pps.InsertInt(ZBXingXi[t][0]);
			}

		}
		for (int i = 0; i < ZB.length; i++) {
			if (JLZB[i] == -1) {
				pps.InsertInt(999);
			} else {
				pps.InsertInt(JLZB[i]);
			}
		}
		for (int i = 0; i < shiYongKaPian.length; i++) {
			if (shiYongKaPian[i] == -1) {
				pps.InsertInt(999);
			} else {
				pps.InsertInt(shiYongKaPian[i]);
			}
		}
		pps.InsertInt(diTuSuiPian);
		pps.InsertInt(contKP);
		for (int i = 0; i < engine.TSRabk.length; i++) {
			if (engine.TSRabk[i] == -1) {
				pps.InsertInt(999);
				pps.InsertInt(engine.TSRabk[i]);
			} else {
				pps.InsertInt(engine.TSRabk[i]);
			}
		}

		df.SaveTo("gamefile.dat");
	}

	/**
	 * 读取数据
	 */

	public void loadGame() {
		df.ReadFrom("gamefile.dat");
		if (df.fileLength("gamefile.dat") <= 0) {
			return;
		}
		GameEngine.me.usBs.iUsBsCuJinbi = pps.GetNextInt();
		GameEngine.fuhuo = pps.GetNextInt();
		engine.isJB = pps.GetNextInt();
		for (int h = 0; h < 4; h++) {// 每关开启和评价
			for (int g = 0; g < 6; g++) {
				int tempresult = pps.GetNextInt();
				if (tempresult == 999) {
					engine.iResult[h][g] = -1; // lh
				} else {
					engine.iResult[h][g] = tempresult; // lh
				}
			}
		}
		for (int i = 0; i < jiNengKaiQi.length; i++) { // 技能等级
			int tempskillnum = pps.GetNextInt();
			if (tempskillnum == 999) {
				jiNengKaiQi[i] = 3;
			} else {
				jiNengKaiQi[i] = (byte) tempskillnum;
			}
		}
		for (int t = 0; t < ZBXingXi.length; t++) {// 装备等级
			int tempskillnum = pps.GetNextInt();
			if (tempskillnum == 999) {
				ZBXingXi[t][0] = -1;
			} else {
				ZBXingXi[t][0] = tempskillnum;
			}

			// System.out.println("load : "+ZBXingXi[t][0]);
		}
		for (int i = 0; i < ZB.length; i++) {
			int tempskillnum = pps.GetNextInt();
			if (tempskillnum == 999) {
				ZB[i] = -1;
			} else {
				ZB[i] = tempskillnum;
			}
		}
		for (int i = 0; i < shiYongKaPian.length; i++) {
			int tempskillnum = pps.GetNextInt();
			if (tempskillnum == 999) {
				shiYongKaPian[i] = -1;
			} else {
				shiYongKaPian[i] = (byte) tempskillnum;
			}
		}
		diTuSuiPian = pps.GetNextInt();
		contKP = pps.GetNextInt();
		for (int i = 0; i < engine.TSRabk.length; i++) {
			int tempskillnum = pps.GetNextInt();
			if (tempskillnum == 999) {
				engine.TSRabk[i] = -1;
			} else {
				engine.TSRabk[i] = (byte) tempskillnum;
			}
		}
	}

	/**
	 * 得到bin文件数据
	 * 
	 * @param name
	 * @return
	 */
	public short[][] initItemData(String name) { // 0 clipdata，1
		// checkdata
		// 2以后framedata
		short[][] kbz = null;
		InputStream is = null;
		AssetManager am = this.context.getResources().getAssets();
		DataInputStream tempDS = null;
		try {
			is = am.open("itemdata/" + name + ".bin");
			tempDS = new DataInputStream(is);

			int len = tempDS.readShort();

			kbz = new short[len][3];
			for (int i = 0; i < kbz.length; i++) {
				for (int j = 0; j < kbz[len].length; j++) {
					kbz[i][0] = tempDS.readShort();
					kbz[i][1] = tempDS.readShort();
					kbz[i][2] = tempDS.readShort();
				}

			}
		} catch (Exception e) {
			Log.i("initItemData", "initItemData :" + name);
			e.printStackTrace();
		}
		return kbz;
	}

	/**
	 * 根据触屏位置算出脚下的地图块属性
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int getMapIndex(int x, int y) {
		int sw = MyActivity.VMWidth;
		int sh = MyActivity.VMHeight;
		float dw = (float) sw / (SCREEN_WIDTH);
		float dh = (float) sh / (SCREEN_HEIGHT);
		// Log.e("xxx22222xx","yyy22222yyy"+x+";"+y+";"+dw+";"+dh);
		x = (int) (x / dw);
		y = (int) (y / dh);
		// Log.e("xxx11111xx","yyy11111yyy"+x+";"+y);
		int tx = (int) (x / (engine.map.tileWidth));
		int ty = (int) (y / (engine.map.tileWidth));
		int n = (ty * engine.map.mapSize[0] + tx);
		if (n < 0 || n >= engine.map.mapSize[0] * engine.map.mapSize[1]) {
			return -1;
		}

		return n;
	}

	/**
	 * 计算游戏中的时间
	 * 
	 * @param second
	 *            15分钟 奖励300宝石 30分钟 奖励600宝石 1小时 奖励1200宝石 2小时 奖励2400宝石
	 */
	int iGoldGet;

	public void subtractTime(int second) {
		if (leftSecond > 0) {
			leftSecond -= second;
			if (leftSecond == 42 * 60) {
				iGoldGet = 50;
			} else if (leftSecond == 37 * 60) {
				iGoldGet = 100;
			} else if (leftSecond == 25 * 60) {
				iGoldGet = 150;
			}
		} else {
			iGoldGet = 300;
			leftSecond = totalSecond;
		}

	}
	
	void drawDXOpen(){
		openIndex++;
		 if(openIndex<50){
			GameDraw.add_Image(PAK_IMAGES.IMG_SMSLOGO0, 400,240,
					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 10);	
		}
		if(openIndex==49){
			setST(GmStat_OpenLoading);
		}
		
	}
}
