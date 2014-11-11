//package com.haopu.JSGame;
//
//import com.haopu.kbz.*;
//
//import java.util.*;
//
//import android.util.Log;
////import javax.microedition.lcdui.*;
////import javax.microedition.rms.*;
//
///**
// * <p>
// * Title:
// * </p>
// * <p>
// * Description:
// * </p>
// * <p>
// * Copyright: Copyright (c) 2004
// * </p>
// * <p>
// * Company: haopu
// * </p>
// * 
// * @author not attributable
// * @version 1.0
// */
//
//public class GameMenuItem {
//
//	GameEngine engine;
//	GameRole skillRole;
//
//	public GameMenuItem(GameEngine eg) {
//		engine = eg;
//		skillRole = new GameRole(engine);
////		skillRole.setType(GameRole.TYPE_ROLE_步兵);
//		skillRole.setStatus(GameInterface.STATUS_MENU_SHOW);
//	//	skillRole.iHp = 100;
//		menuIndex[menuIndex_save] = -1;
//		isTouch = false;
//	}
//
//	/*********************************** 数据定义及常用函数 *******************************************/
//
//	/*
//	 * 人物属性定义
//	 */
//	public static final byte I_LEN = 15;
////	public static final byte I_id = 0;
////	public static final byte I_type = 1;
////	public static final byte I_food = 2; // 造兵消耗的粮食
////	public static final byte I_attack = 3; // 基础攻击
////	public static final byte I_attack_up = 6; // 增加的攻击
////	public static final byte I_money = 4; // 开通需要的钱
////	public static final byte I_money_up = 5; // 升级需要的钱
////	public static final byte I_hp = 7; // 基础血量
////	public static final byte I_hp_up = 8; // 增加的血量
////	public static final byte I_time = 9; // 建造恢复时间
////	public static final byte I_leavel = 10; // 当前雇佣兵的等级
//
////	static int[][] spriteData; //
//
//	/*
//	 * 物品及技能属性定义
//	 */
//
////	public static final byte G_LEN = 30;
////	public static final byte G_id = 0;
////	public static final byte G_type = 1;
////	public static final byte G_mp = 2;
////	public static final byte G_ice = 3; // 技能打中后的冰冻几率
////	public static final byte G_ice_up = 4; //
////	public static final byte G_attack_skill = 5; //
////	public static final byte G_attack_up = 6; //
////	public static final byte G_money = 7; //
////	public static final byte G_money_up = 8; //
////	public static final byte G_hp = 9; //
////	public static final byte G_hp_up = 10; //
////	public static final byte G_time = 11; //
////	public static final byte G_leavel = 12; //
////	public static final byte G_jinqian = 13; //
////	public static final byte G_jinqian_up = 14; //
////	public static final byte G_huifuliangshi = 15; //
////	public static final byte G_huifuliangshi_up = 16; //
////	public static final byte G_jinyan = 17; //
////	public static final byte G_jinyan_up = 18; //
////	public static final byte G_huifump = 19; //
////	public static final byte G_huifump_up = 20; //
////	public static final byte G_ls_addmax = 21; //
////	public static final byte G_mp_up = 22; //
////	public static final byte G_ls_addmax_up = 23; //
////	public static final byte G_huifuhp = 24; //
////	public static final byte G_yiliao = 25; //
////	public static final byte G_yiliao_up = 26; //
////	public static final byte G_mp_xiaohao = 26; //
//
//	static int[][] goodsData; //
//
//	static int indexStrIntro; // 滚屏介绍
//	static int index;
//	static int money = 999;
//
//	final static void addMoney(int m) {
//		money += m;
//	}
//
//	final static int getMoney() {
//		return money;
//	}
//
//	public static final int MENUBG_LAYER = 10;
//	static int[] menuIndex = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//			0, 0, 0, 0 };
//	static int[] point_total = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//			0, 0, 0, 0, 0 };
//	final static byte menuIndex_total = 0; // 主菜单
//	final static byte menuIndex_prop = 1; // 人物属性菜单
//	final static byte menuIndex_equip = 2; // 装备菜单
//	final static byte menuIndex_goods = 3; // 物品菜单
//	final static byte menuIndex_equipchoose = 4; // 物品菜单
//	final static byte menuIndex_goodschoose = 5; // 物品菜单
//	final static byte menuIndex_save = 6; // 存档
//	final static byte menuIndex_goodschoose_2 = 7; // 物品菜单2
//	final static byte menuIndex_skill = 8; // 强化菜单
//	final static byte menuIndex_task = 9; // 任务
//	final static byte menuIndex_qiang = 10; // 强化
//	final static byte menuIndex_shop = 11; // 商店
//	final static byte menuIndex_zhenfa = 12;
//	final static byte menuIndex_up = 13; //
//	final static byte menuIndex_map = 14; //
//	final static byte menuIndex_mapchoose = 15; //
//	final static byte menuIndex_savechoose = 16; //
//
//	static int menuStatus; // 菜单选项
//	public static final int MENU_兵营 = 0; // 3
//	public static final int MENU_装备 = 1; // 4
//	public static final int MENU_背包_CHOOSE = 2; // 4
//	public static final int MENU_技能 = 3; // 5
//	public static final int MENU_升级转盘 = 4; // 0
//	public static final int MENU_任务 = 5; // 1
//	public static final int MENU_地图 = 6; // 2
//	public static final int MENU_系统 = 7; // 6
//	public static final int MENU_阵法 = 8; // 7
//	public static final int MENU_地图选择 = 9; // 2
//	public static final int MENU_TOTAL = 10; // 2
//	public static final int MENU_存档 = 11; // 2
//	public static final int MENU_存档_CHOOSE = 12; // 2
//
//	// public static final int MENU_VIP商店 = 13; // 2
//
//	public static boolean is_menu = false;
//
//	static int zhenfaIndex;
//
//	public static void setMenuStatus(int curStatus) {
////		MyGameCanvas.setST(MyGameCanvas.ST_MENU_TOTAL);
////		menuStatus = curStatus;
////		if (menuStatus == MENU_阵法) {
////			setMenuIndex(menuStatus, zhenfaIndex);
////		}
////		is_menu = true;
////		// indexStrIntro = 0;
////		index = 0;
//	}
//
//	int moveY = 0;
//
//	public void drawMenuBg(int x, int y, int curStatus) { // 菜单背景
////		MyGameCanvas.drawCleanScreen(0xff000000);
////		switch (curStatus) {
////		case MENU_兵营:
////			drawMenuName(x, y, curStatus, true);
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU8, x, y + 40,
////					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 500);
////			if (point_total[menuIndex_prop] == 1) {
////				GameDraw.add_Image(PAK_IMAGES.IMG_FONT4, x + 245, y + 170,
////						0, 88, 73, 39, Tools.HCENTER_TOP, Tools.TRANS_NONE, 501);
////			} else {
////				GameDraw.add_Image(PAK_IMAGES.IMG_FONT3, x + 250, y + 170,
////						0, 88, 73, 39, Tools.HCENTER_TOP, Tools.TRANS_NONE, 501);
////			}
////
////			if (point_total[menuIndex_prop] == 2) {
////				GameDraw.add_Image(PAK_IMAGES.IMG_FONT8, x + 200, y - 3, 2,
////						44, 71, 37, Tools.HCENTER_TOP, Tools.TRANS_NONE, 501);
////			} else {
////
////				GameDraw.add_Image(PAK_IMAGES.IMG_FONT8, x + 200, y - 3, 2,
////						2, 71, 40, Tools.HCENTER_TOP, Tools.TRANS_NONE, 501);
////			}
////			break;
////
////		case MENU_装备:
////			drawMenuName(x, y, curStatus, true);
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU14, x, y + 40,
////					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 500);
////			if (point_total[menuIndex_equip] == 1) {
////				GameDraw.add_Image(PAK_IMAGES.IMG_FONT4, x + 250, y + 170,
////						0, 88, 73, 39, Tools.HCENTER_TOP, Tools.TRANS_NONE, 501);
////			} else {
////				GameDraw.add_Image(PAK_IMAGES.IMG_FONT3, x + 250, y + 170,
////						0, 88, 73, 39, Tools.HCENTER_TOP, Tools.TRANS_NONE, 501);
////			}
////			if (point_total[menuIndex_equip] == 2) {
////				GameDraw.add_Image(PAK_IMAGES.IMG_FONT8, x - 210, y + 120,
////						2, 44, 71, 37, Tools.HCENTER_TOP, Tools.TRANS_NONE, 501);
////			} else {
////				GameDraw.add_Image(PAK_IMAGES.IMG_FONT8, x - 210, y + 120,
////						2, 2, 71, 40, Tools.HCENTER_TOP, Tools.TRANS_NONE, 501);
////			}
////			break;
////
////		case MENU_阵法:
////			drawMenuName(x, y, curStatus, true);
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU15, x, y + 40,
////					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 500);
////			if (point_total[menuIndex_zhenfa] == 1) {
////				GameDraw.add_Image(PAK_IMAGES.IMG_FONT4, x + 250, y + 170,
////						0, 88, 73, 39, Tools.HCENTER_TOP, Tools.TRANS_NONE, 501);
////			} else {
////				GameDraw.add_Image(PAK_IMAGES.IMG_FONT3, x + 250, y + 170,
////						0, 88, 73, 39, Tools.HCENTER_TOP, Tools.TRANS_NONE, 501);
////			}
////
////			if (point_total[menuIndex_zhenfa] == 2) {
////				GameDraw.add_Image(PAK_IMAGES.IMG_FONT8, x - 200, y + 165,
////						2, 44, 71, 37, Tools.HCENTER_TOP, Tools.TRANS_NONE, 501);
////			} else {
////				GameDraw.add_Image(PAK_IMAGES.IMG_FONT8, x - 200, y + 165,
////						2, 2, 71, 40, Tools.HCENTER_TOP, Tools.TRANS_NONE, 501);
////			}
////
////			if (point_total[menuIndex_zhenfa] == 3) {
////				GameDraw.add_Image(PAK_IMAGES.IMG_BUTTON2, x - 90, y - 80,
////						55, 0, 54, 56, Tools.HCENTER_TOP, Tools.TRANS_H, 501);
////			} else {
////				GameDraw.add_Image(PAK_IMAGES.IMG_BUTTON2, x - 90, y - 80,
////						0, 0, 54, 56, Tools.HCENTER_TOP, Tools.TRANS_H, 501);
////			}
////
////			if (point_total[menuIndex_zhenfa] == 4) {
////				GameDraw.add_Image(PAK_IMAGES.IMG_BUTTON2, x + 240, y - 80,
////						55, 0, 54, 56, Tools.HCENTER_TOP, Tools.TRANS_NONE, 501);
////			} else {
////				GameDraw.add_Image(PAK_IMAGES.IMG_BUTTON2, x + 240, y - 80,
////						0, 0, 54, 56, Tools.HCENTER_TOP, Tools.TRANS_NONE, 501);
////			}
////			break;
////
////		case MENU_存档:
////			// case MENU_存档_CHOOSE:s
////			int[][] Font3 = { { 0, 1, 144, 42 },/* 开始游戏 */
////			{ 0, 43, 144, 45 },/* 删除存档 */
////			{ 0, 88, 73, 39 } };/* 返回 */
////			MyGameCanvas.drawMenu();
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU1, x, y,
////					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 30);
////
////			// Log.i("menuIndex_save", "" + menuIndex_save);
////			// Log.i("menuIndex_save11111111", "" + menuIndex[menuIndex_save]);
////
////			if (point_total[menuIndex_save] == 2) {
////				GameDraw.add_Image(PAK_IMAGES.IMG_FONT4, x + 200, y + 160,
////						Font3[2], Tools.HCENTER_TOP, Tools.TRANS_NONE, 31);
////			} else {
////				GameDraw.add_Image(PAK_IMAGES.IMG_FONT3, x + 200, y + 160,
////						Font3[2], Tools.HCENTER_TOP, Tools.TRANS_NONE, 31);
////			}
////
////			if (point_total[menuIndex_save] == 0) {
////				if (menuIndex[menuIndex_save] >= 0) {
////					GameDraw.add_Image(PAK_IMAGES.IMG_FONT4, x, y + 160,
////							Font3[0], Tools.HCENTER_TOP, Tools.TRANS_NONE, 31);
////				}
////			} else {
////				if (menuIndex[menuIndex_save] >= 0) {
////					GameDraw.add_Image(PAK_IMAGES.IMG_FONT3, x, y + 160,
////							Font3[0], Tools.HCENTER_TOP, Tools.TRANS_NONE, 31);
////				}
////			}
////
////			if (point_total[menuIndex_save] == 1) {
////				if (save[0][menuIndex[menuIndex_save] - 3] > 0) {
////					GameDraw.add_Image(PAK_IMAGES.IMG_FONT4, x - 200,
////							y + 160, Font3[1], Tools.HCENTER_TOP,
////							Tools.TRANS_NONE, 31);
////				}
////			} else {
////				if (save[0][menuIndex[menuIndex_save] - 3] > 0) {
////					GameDraw.add_Image(PAK_IMAGES.IMG_FONT3, x - 200,
////							y + 160, Font3[1], Tools.HCENTER_TOP,
////							Tools.TRANS_NONE, 31);
////				}
////			}
////
////			break;
////		}
//	}
//
//	static int getMenuIndex() {
//		switch (menuStatus) {
//		case MENU_兵营: // 0
//			return menuIndex[menuIndex_prop];
//
//		case MENU_阵法: // 1
//			return menuIndex[menuIndex_zhenfa];
//
//		case MENU_装备: // 2
//			return menuIndex[menuIndex_goods];
//
//		case MENU_背包_CHOOSE:
//			return menuIndex[menuIndex_goodschoose];
//
//		case MENU_升级转盘: // 2
//			return menuIndex[menuIndex_up];
//
//		case MENU_地图:
//			return menuIndex[menuIndex_map];
//		case MENU_地图选择:
//			return menuIndex[menuIndex_mapchoose];
//
//		case MENU_TOTAL:
//			return menuIndex[menuIndex_total];
//
//		case MENU_存档:
//			return menuIndex[menuIndex_save];
//
//		case MENU_存档_CHOOSE:
//			return menuIndex[menuIndex_savechoose];
//
//		}
//		return -1;
//	}
//
//	int getMenuIndex(int menuStatus) {
//		switch (menuStatus) {
//		case MENU_兵营: // 0
//			return menuIndex[menuIndex_prop];
//		case MENU_阵法: // 1
//			return menuIndex[menuIndex_zhenfa];
//		case MENU_装备: // 2
//			return menuIndex[menuIndex_goods];
//		case MENU_背包_CHOOSE:
//			return menuIndex[menuIndex_goodschoose];
//		case MENU_地图:
//			return menuIndex[menuIndex_map];
//		case MENU_地图选择:
//			return menuIndex[menuIndex_mapchoose];
//		case MENU_TOTAL:
//			return menuIndex[menuIndex_total];
//		case MENU_存档:
//			return menuIndex[menuIndex_save];
//		case MENU_存档_CHOOSE:
//			return menuIndex[menuIndex_savechoose];
//		}
//		return -1;
//	}
//
//	static void setMenuIndex(int menuStatus, int index) {
//		switch (menuStatus) {
//		case MENU_兵营: // 0
//			menuIndex[menuIndex_prop] = index;
//			break;
//		case MENU_阵法: // 1
//			menuIndex[menuIndex_zhenfa] = index;
//			break;
//		case MENU_装备: // 2
//			menuIndex[menuIndex_goods] = index;
//			break;
//		case MENU_背包_CHOOSE: // 2
//			menuIndex[menuIndex_goodschoose] = index;
//			break;
//		case MENU_升级转盘: // 2
//			menuIndex[menuIndex_up] = index;
//			break;
//
//		case MENU_地图: // 2
//			menuIndex[menuIndex_map] = index;
//			break;
//		case MENU_地图选择: // 2
//			menuIndex[menuIndex_mapchoose] = index;
//			break;
//
//		case MENU_TOTAL: // 2
//			menuIndex[menuIndex_total] = index;
//			break;
//
//		case MENU_存档: // 2
//			menuIndex[menuIndex_save] = index;
//			break;
//		case MENU_存档_CHOOSE:
//			menuIndex[menuIndex_savechoose] = index;
//			break;
//
//		}
//	}
//
//	int a = 110;
//	int b = 50;
//
//	public void paint() {
//		// drawMenuBg(Tools.setOffX + a, Tools.setOffY + b, menuStatus);
////		switch (menuStatus) {
////		case MENU_兵营:
////			engine.drawGame(true);
////			drawMenuProp(Tools.setOffX + MyGameCanvas.SCREEN_WIDTH / 2,
////					Tools.setOffY + MyGameCanvas.SCREEN_HEIGHT / 2);
////			break;
////		case MENU_阵法:
////			engine.drawGame(true);
////			drawZhenFa(Tools.setOffX + MyGameCanvas.SCREEN_WIDTH / 2,
////					Tools.setOffY + MyGameCanvas.SCREEN_HEIGHT / 2);
////
////			break;
////		case MENU_装备:
////			// case MENU_背包_CHOOSE:
////			engine.drawGame(true);
////			drawGoods(Tools.setOffX + MyGameCanvas.SCREEN_WIDTH / 2,
////					Tools.setOffY + MyGameCanvas.SCREEN_HEIGHT / 2);
////			// drawButton(Tools.setOffX, Tools.setOffY, false);
////
////			break;
////
////		case MENU_升级转盘:
////			engine.drawGame(true);
////			drawUpMenu(Tools.setOffX + 160 + a, Tools.setOffY + 40 + b);
////			break;
////
////		case MENU_TOTAL:
////			engine.drawGame(true);
////			drawTotalMenu(Tools.setOffX + (320) / 2 + a, Tools.setOffY + 30 + b);
////			break;
////
////		case MENU_地图:
////			drawMap(Tools.setOffX, Tools.setOffY);
////			break;
////
////		case MENU_地图选择:
////			drawMap(Tools.setOffX, Tools.setOffY);
////			drawMapChoose(Tools.setOffX, Tools.setOffY);
////			break;
////
////		case MENU_存档:
////			drawSave(Tools.setOffX + MyGameCanvas.SCREEN_WIDTH / 2,
////					Tools.setOffY - 200);
////			break;
////
////		case MENU_存档_CHOOSE:
////			// drawSave(Tools.setOffX + a, Tools.setOffY + b);
////			// drawSaveChoose(Tools.setOffX + a, Tools.setOffY + b);
////			break;
////
////		default:
////			drawGoods(Tools.setOffX + 100 + a, Tools.setOffY + 60 + b);
////			break;
////
////		}
////		drawEffect();
//	}
//
//	boolean isOver;
//
//	public void ctrl(int keyCode) {
//		// if (is_midmenu) {
//		// ctrlMenuTotal(keyCode);
//		// return;
//		// }
////		switch (menuStatus) {
////		case MENU_TOTAL:
////			ctrlMenuTotal(keyCode);
////			break;
////
////		case MENU_兵营:
////			ctrlProp(keyCode);
////			break;
////
////		case MENU_阵法:
////			ctrl阵型(keyCode);
////			break;
////
////		case MENU_装备:
////			ctrlGoods(keyCode);
////			break;
////
////		case MENU_背包_CHOOSE:
////			ctrlGoodsChoose(keyCode);
////			break;
////		case MENU_升级转盘:
////			ctrlUp(keyCode);
////			break;
////		case MENU_地图:
////			ctrlMap(keyCode);
////			break;
////
////		case MENU_地图选择:
////			ctrlMapChoose(keyCode);
////			break;
////
////		case MENU_存档:
////			ctrlSave(keyCode);
////			break;
////		case MENU_存档_CHOOSE:
////			ctrlSaveChoose(keyCode);
////			break;
////
////		}
//	}
//
//	/**********************************************************************************************/
//	public void drawMenuName(int x, int y, int MenuStatus, boolean isChoose) { // 菜单标题
////		int[][] Font7 = { { 0, 0, 96, 50 },/* 兵营 */
////		{ 0, 50, 97, 49 },/* 装备 */
////		{ 0, 99, 97, 49 } };/* 阵法 */
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU2, x, y - 188,
////				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 501);
////		for (int i = 0; i < 3; i++) {
////			GameDraw.add_Image(PAK_IMAGES.IMG_FONT7, x + 200 * i - 200,
////					y - 188, Font7[i], Tools.HCENTER_VCENTER, Tools.TRANS_NONE,
////					502);
////
////		}
////		switch (MenuStatus) {
////		case MENU_兵营:
////			GameDraw.add_Image(PAK_IMAGES.IMG_FONT6, x - 200, y - 188, 0,
////					0, 92, 58, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 503);
////			break;
////		case MENU_装备:
////			GameDraw.add_Image(PAK_IMAGES.IMG_FONT6, x, y - 188, 0, 59,
////					106, 56, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 503);
////			break;
////		case MENU_阵法:
////			GameDraw.add_Image(PAK_IMAGES.IMG_FONT6, x + 200, y - 188, 0,
////					116, 100, 55, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 503);
////			break;
////		}
////
////		// GameDraw.addObject(PAK_IMAGES.IMG_MENU2_PNG, x, y, isChoose ? 0 : 59,
////		// curIndex * 32, 59, 32, Tools.TOP_LEFT, Tools.TRANS_NONE,
////		// menuStatus == MENU_TOTAL ? 500 : MENUBG_LAYER + 1);
//
//	}
//
//	/***************************** 总菜单 ******************************/
//
//	public void drawTotalMenu(int x, int y) {
//
//	}
//
//	public void ctrlMenuTotal(int keyCode) {
////		switch (keyCode) {
////		case MyGameCanvas.KEY_DOWN:
////		case MyGameCanvas.KEY_8:
////			if (getMenuIndex() < 2) {
////				setMenuIndex(menuStatus, getMenuIndex() + 1);
////			} else {
////				setMenuIndex(menuStatus, 0);
////			}
////
////			break;
////
////		case MyGameCanvas.KEY_UP:
////		case MyGameCanvas.KEY_2:
////			if (getMenuIndex() > 0) {
////				setMenuIndex(menuStatus, getMenuIndex() - 1);
////			} else {
////				setMenuIndex(menuStatus, 2);
////				// indexStrIntro = 0;
////			}
////			break;
////
////		case MyGameCanvas.KEY_OK:
////		case MyGameCanvas.KEY_5:
////		case MyGameCanvas.KEY_LS:
////			switch (getMenuIndex()) {
////			case 0:
////				setMenuStatus(MENU_装备);
////				break;
////
////			case 1:
////				setMenuStatus(MENU_兵营);
////				break;
////
////			case 2:
////				setMenuStatus(MENU_阵法);
////				break;
////			}
////
////			break;
////		case MyGameCanvas.KEY_RS:
////			MyGameCanvas.setST(MyGameCanvas.ST_PLAY);
////			break;
////
////		}
//	}
//
//	// ////////////////中途菜单//////////////////////////////
//
//	/**************** 人物属性 *********************/
//
//	boolean isKey;
//
//	public void initRoleEquipProp() { // 穿上装备后增加的属性
//
//	}
//
//	/*************************************** 背包 **********************************************/
//
//	static byte[] goodsLeavelData = { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, };
//	// new int[8 + 6];
//	final static int maxGoodsLeavel = 10;
//	static byte[] goodsSkill = { -1, -1, 8, -1, -1 }; // 2个物品+3个技能
//
//	boolean isGoodsEquipHave(int num) { // 是否设置过
//		// System.out.println("num:"+num);
//		for (int i = 0; i < goodsSkill.length; i++) {
//			if (num == goodsSkill[i]) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	boolean is_装备;
//	boolean is_技能;
//
//	void drawGoods(int x, int y) {
////		drawMenuBg(x, y, MENU_装备);
////		int adx = 255;
////		int ady = 97;
////		for (int i = 0; i < 8 + 6; i++) {
////			if (i < 8) {
////				drawGoodsIcon(x - adx + i % 8 * 72, y + i / 8 * 55 - ady, i,
////						goodsLeavelData[i] == 0 ? 1 : 0,
////						getMenuIndex(MENU_装备) == i, true);
////
////			} else {
////				drawSkillIcon(x - adx + i % 8 * 97 + 9, y + i / 8 * 55 - ady
////						+ 43, i, goodsLeavelData[i] == 0 ? 1 : 0,
////						getMenuIndex(MENU_装备) == i, true);
////			}
////		}
////
////		if (getMenuIndex(MENU_装备) < 8) {
////			for (int i = 0; i < 2; i++) {
////				drawGoodsEff(x + i * 68 - 99, y + 169);
////			}
////		} else {
////			for (int i = 0; i < 3; i++) {
////				drawGoodsEff(x + i * 72 + 40, y + 169);
////			}
////		}
////		drawGoodsIntro(x - 10, y + 70, getMenuIndex(MENU_装备));
////		drawGoodsEquip(x - 100, y + 168);
////		drawMoney(x - MyGameCanvas.SCREEN_WIDTH / 2 + 160, y + 175, money);
//	}
//
//	int Effindex = 0;
//
//	void drawGoodsEff(int x, int y) {
////		GameDraw.renderAnimPic2(PAK_IMAGES.IMG_EFF81, Effindex, x, y,
////				MyGameCanvas.data_GoodsEff, false, false, 2001, 0, 0);
////		if (++Effindex > 11) {
////			Effindex = 0;
////		}
//	}
//
//	void drawGoodsEquip(int x, int y) {
//
//		int[] num = { 3, 9, 1, 0, 7 };
//
//		int w = 70;
//		for (int i = 0; i < goodsSkill.length; i++) {
//			if (goodsSkill[i] == -1) {
//
//			} else {
//				if (goodsSkill[i] < 8) {
//					drawGoodsIcon(x + i % 8 * 70, y, goodsSkill[i], 0, false,
//							false);
//				} else {
//					drawSkillIcon(x + i % 8 * 71, y, goodsSkill[i], 0, false,
//							false);
//				}
//
//			}
//
//			// if (num[i] == 1) {
//			//
//			// GameDraw.addObject(PAK_IMAGES.IMG_MENU15_PNG,
//			// x + i * w + 10, y + 10, 34, 26, 9, 8,
//			// Tools.TOP_LEFT,
//			// Tools.TRANS_NONE,
//			// 20);
//			// }
//			// else if (num[i] == 7) {
//			// GameDraw.addObject(PAK_IMAGES.IMG_MENU15_PNG,
//			// x + i * w + 10, y + 10, 34, 50, 9, 8,
//			// Tools.TOP_LEFT,
//			// Tools.TRANS_NONE,
//			// 20);
//			//
//			// }
//			// else {
//			// GameDraw.drawNumber(PAK_IMAGES.IMG_NUMBER1_PNG,
//			// num[i], x + i * w + 10, y + 10, 7,
//			// 0,
//			// Tools.TOP_LEFT,
//			// 20, 9, 0, true);
//			// }
//		}
//
//	}
//
//	void drawGoodsChoose(int x, int y) {
//		int[] motion;
//		motion = new int[] { 5, 2, 3, 4, 6 };
//		// if (getMenuIndex(MENU_背包) > 7) {
//		// drawRect(x, y, 50, 85, 20);
//		// }
//		// else {
//		// drawRect(x, y, 50, 68, 20);
//		// }
//		for (int i = 0; i < motion.length; i++) {
//			// drawGoodsChooseIcon(x + 25, y + 5 + 16 * i, motion[i], i ==
//			// getMenuIndex());
//		}
//
//		// GameDraw.addObject(PAK_IMAGES.IMG_MENU15_PNG, x, y - 40,
//		// getMenuIndex(MENU_装备) > 7 ? 70 : 0, 0, 70, 127,
//		// Tools.HCENTER_TOP, Tools.TRANS_NONE, 30);
//	}
//
//	// void drawGoodsChooseIcon(int x, int y, int curIndex, boolean isChoose) {
//	// if (isChoose) {
//	// GameDraw.addObject(PAK_IMAGES.IMG_MENU15_PNG,
//	// x, y - 3, 0, 96, 49, 18,
//	// Tools.HCENTER_TOP,
//	// Tools.TRANS_NONE,
//	// 30);
//	//
//	// }
//	// GameDraw.addObject(PAK_IMAGES.IMG_MENU15_PNG,
//	// x, y, isChoose ? 0 : 44, curIndex * 12, 44, 12,
//	// Tools.HCENTER_TOP,
//	// Tools.TRANS_NONE,
//	// 30);
//	//
//	// }
//
//	int getGoodsPropIndex(int type) { // 根据物品类型判断是否装备
//
//		for (int i = 0; i < goodsSkill.length; i++) {
//			if (goodsSkill[i] == type) {
//				return type;
//			}
//		}
//
//		return -1;
//	}
//
//	int setGoodsProp(int type) { // 获得物品属性
//		if (goodsLeavelData[type] == 0) {
//			return 0;
//		}
//
//		if (getGoodsPropIndex(type) == -1) {
//			return 0;
//		}
//
//		int leavel = goodsLeavelData[type];
//		// String[] intro = null;
//		int curLeavel = 0;
//		// int nextLeavel = 0;
//		// int money = getGoodsMoney();
//		// int curIndex;
//
////		switch (type) {
////		case Goods_type_农夫铲:
//////			curLeavel = getGoods_2(type, G_huifuliangshi, leavel - 1);
////			break;
////		case Goods_type_稻谷令牌:
//////			curLeavel = getGoods_2(type, G_ls_addmax, leavel - 1);
////			break;
////		case Goods_type_黄金戒指:
//////			curLeavel = getGoods_2(type, G_jinqian, leavel - 1);
////			break;
////		case Goods_type_精神项链:
//////			curLeavel = getGoods_2(type, G_huifump, leavel - 1);
////			break;
////		case Goods_type_战记勋章:
//////			curLeavel = getGoods_2(type, G_jinyan, leavel - 1);
////			break;
////		case Goods_type_生命项链:
//////			curLeavel = getGoods_2(type, G_hp, leavel - 1);
////			break;
////		case Goods_type_回复戒指:
//////			curLeavel = getGoods_2(type, G_huifuhp, leavel - 1);
////			break;
////		case Goods_type_法力戒指:
//////			curLeavel = getGoods_2(type, G_mp, leavel - 1);
////			break;
////
////		case Goods_type_火焰球:
//////			curLeavel = getGoods_2(type, G_attack_skill, leavel - 1);
////			break;
////
////		case Goods_type_冰冻球:
//////			curLeavel = getGoods_2(type, G_attack_skill, leavel - 1);
////			break;
////		case Goods_type_闪电球:
//////			curLeavel = getGoods_2(type, G_attack_skill, leavel - 1);
////			break;
////		case Goods_type_医疗球:
//////			curLeavel = getGoods_2(type, G_yiliao, leavel - 1);
////			// System.out.println("医疗球:" + curLeavel);
////			break;
////		case Goods_type_力量球:
//////			curLeavel = getGoods_2(type, G_attack_skill, leavel - 1);
////			break;
////		case Goods_type_兵粮球:
//////			curLeavel = getGoods_2(type, G_huifuliangshi_up, leavel - 1);
////			break;
////
////		}
//
//		return curLeavel;
//
//	}
//
//	void drawGoodsIntro(int x, int y, int type) { // 物品介绍
////		// drawRect(x, y, 300, 28, 10);
////		int leavel = goodsLeavelData[type];
////		String[] intro = null;
////		int curLeavel = 0;
////		int nextLeavel = 0;
////		int money = getGoodsMoney();
////		int curIndex;
////		switch (type) {
////		case Goods_type_农夫铲:
////			curLeavel = getGoods(G_huifuliangshi, leavel - 1);
////			nextLeavel = getGoods(G_huifuliangshi, leavel);
////			intro = new String[] {
////					"尚未开通，开启后每2秒额外增加" + curLeavel + "点粮食,升级所需金钱" + money,
////					"已升至最高级，每2秒额外增加" + curLeavel + "点粮食",
////					"每2秒额外增加" + curLeavel + "点粮食, 下一级每2秒额外增加" + nextLeavel
////							+ "点粮食, 升级所需金钱" + money };
////			break;
////		case Goods_type_稻谷令牌:
////			curLeavel = getGoods(G_ls_addmax, leavel - 1);
////			nextLeavel = getGoods(G_ls_addmax, leavel);
////			intro = new String[] {
////					"尚未开通，开启后增加角色" + curLeavel + "点粮食上限,升级所需金钱" + money,
////					"已升至最高级，增加角色" + curLeavel + "点粮食上限",
////					"增加角色" + curLeavel + "点粮食上限, 下一级增加角色" + nextLeavel
////							+ "点粮食上限, 升级所需金钱" + money };
////			break;
////		case Goods_type_黄金戒指:
////			curLeavel = getGoods(G_jinqian, leavel - 1);
////			nextLeavel = getGoods(G_jinqian, leavel);
////
////			intro = new String[] {
////					"尚未开通，开启后角色获得金钱额外增加" + curLeavel + "%,升级所需金钱" + money,
////					"已升至最高级，角色获得金钱额外增加" + curLeavel + "%,",
////					"角色获得金钱额外增加" + curLeavel + "%,下一级角色获得金钱额外增加" + nextLeavel
////							+ "%,升级所需金钱" + money };
////
////			break;
////		case Goods_type_精神项链:
////			curLeavel = getGoods(G_huifump, leavel - 1);
////			nextLeavel = getGoods(G_huifump, leavel);
////			intro = new String[] {
////					"尚未开通，开启后每2秒额外增加" + curLeavel + "点法力值,升级所需金钱" + money,
////					"已升至最高级，每2秒额外增加" + curLeavel + "点法力值",
////					"每2秒额外增加" + curLeavel + "点法力值, 下一级每2秒额外增加" + nextLeavel
////							+ "点法力值, 升级所需金钱" + money };
////
////			break;
////		case Goods_type_战记勋章:
////			curLeavel = getGoods(G_jinyan, leavel - 1);
////			nextLeavel = getGoods(G_jinyan, leavel);
////
////			intro = new String[] {
////					"尚未开通，开启后角色获得的经验值额外增加" + curLeavel + "%,升级所需金钱" + money,
////					"已升至最高级，角色获得的经验值额外增加" + curLeavel + "%",
////					"角色获得的经验值额外增加" + curLeavel + "%,下一级角色获得的经验值额外增加"
////							+ nextLeavel + "%,升级所需金钱" + money };
////
////			break;
////		case Goods_type_生命项链:
////			curLeavel = getGoods(G_hp, leavel - 1);
////			nextLeavel = getGoods(G_hp, leavel);
////
////			intro = new String[] {
////					"尚未开通，开启后增加角色" + curLeavel + "%体力值,升级所需金钱" + money,
////					"已升至最高级，增加角色" + curLeavel + "%体力值",
////					"增加角色" + curLeavel + "%体力值, 下一级增加角色" + nextLeavel
////							+ "%体力值, 升级所需金钱" + money };
////
////			break;
////		case Goods_type_回复戒指:
////			curLeavel = getGoods(G_huifuhp, leavel - 1);
////			nextLeavel = getGoods(G_huifuhp, leavel);
////
////			intro = new String[] {
////					"尚未开通，开启后角色每" + curLeavel + "秒回复2点体力值,升级所需金钱" + money,
////					"已升至最高级，角色每" + curLeavel + "秒回复2点体力值",
////					"角色每" + curLeavel + "秒回复2点体力值, 下一级角色每" + nextLeavel
////							+ "秒回复2点体力值, 升级所需金钱" + money };
////
////			break;
////		case Goods_type_法力戒指:
////			curLeavel = getGoods(G_mp, leavel - 1);
////			nextLeavel = getGoods(G_mp, leavel);
////			intro = new String[] {
////					"尚未开通，开启后增加角色" + curLeavel + "点法力值上限,升级所需金钱" + money,
////					"已升至最高级，增加角色" + curLeavel + "点法力值上限",
////					"增加角色" + curLeavel + "点法力值上限, 下一级增加角色" + nextLeavel
////							+ "点法力值上限, 升级所需金钱" + money };
////			break;
////
////		case Goods_type_火焰球:
////			curLeavel = getGoods(G_attack_skill, leavel - 1);
////			nextLeavel = getGoods(G_attack_skill, leavel);
////			intro = new String[] {
////					"尚未开通，开启后用火焰燃烧敌人,本级攻击" + curLeavel + ",升级所需金钱" + money,
////					"已升至最高级，用火焰燃烧敌人,本级攻击" + curLeavel + "",
////					"用火焰燃烧敌人,本级攻击" + curLeavel + ", 下一级攻击" + nextLeavel
////							+ ", 升级所需金钱" + money };
////			break;
////
////		case Goods_type_冰冻球:
////			curLeavel = getGoods(G_attack_skill, leavel - 1);
////			nextLeavel = getGoods(G_attack_skill, leavel);
////			intro = new String[] {
////					"尚未开通，冰冻敌方一定时间,开启后本级攻击" + curLeavel + ","
////							+ getGoods(G_ice, leavel) + "%几率冰冻敌人，升级所需金钱"
////							+ money,
////
////					"已升至最高级，冰冻敌方一定时间,本级攻击" + curLeavel + ","
////							+ getGoods(G_ice, leavel) + "%几率冰冻敌人",
////
////					"冰冻敌方一定时间,本级攻击" + curLeavel + "," + getGoods(G_ice, leavel)
////							+ "%几率冰冻敌人,下一级攻击" + nextLeavel + ","
////							+ getGoods(G_ice, leavel + 1) + "%几率冰冻敌人, 升级所需金钱"
////							+ money };
////
////			break;
////		case Goods_type_闪电球:
////			curLeavel = getGoods(G_attack_skill, leavel - 1);
////			nextLeavel = getGoods(G_attack_skill, leavel);
////			intro = new String[] {
////					"尚未开通，以雷电重创对手,开启后本级攻击" + curLeavel + ",升级所需金钱" + money,
////					"已升至最高级，以雷电重创对手,本级攻击" + curLeavel + "",
////					"以雷电重创对手,本级攻击" + curLeavel + ", 下一级攻击" + nextLeavel
////							+ ", 升级所需金钱" + money };
////			break;
////		case Goods_type_医疗球:
////			curLeavel = getGoods(G_yiliao, leavel - 1);
////			nextLeavel = getGoods(G_yiliao, leavel);
////			intro = new String[] {
////					"尚未开通，开启后治疗我方区域内的士兵" + curLeavel + "点生命,升级所需金钱" + money,
////					"已升至最高级，治疗我方区域内的士兵" + curLeavel + "点生命",
////					"治疗我方区域内的士兵,本级治疗" + curLeavel + "点生命, 下一级治疗" + nextLeavel
////							+ "点生命, 升级所需金钱" + money };
////			break;
////		case Goods_type_力量球:
////			curLeavel = getGoods(G_attack_skill, leavel - 1);
////			nextLeavel = getGoods(G_attack_skill, leavel);
////			intro = new String[] {
////					"尚未开通，强大的区域杀伤魔法,开启后本级攻击" + curLeavel + ",升级所需金钱" + money,
////					"已升至最高级，强大的区域杀伤魔法,本级攻击" + curLeavel + "",
////					"强大的区域杀伤魔法,本级攻击" + curLeavel + ", 下一级攻击" + nextLeavel
////							+ ", 升级所需金钱" + money };
////
////			break;
////		case Goods_type_兵粮球:
////			curLeavel = getGoods(G_huifuliangshi_up, leavel - 1);
////			nextLeavel = getGoods(G_huifuliangshi_up, leavel);
////			intro = new String[] {
////					"尚未开通，将法力转换成粮食,开启后本级消耗" + curLeavel + "点法力值回复" + curLeavel
////							* 2 + "点兵粮，升级所需金钱" + money,
////
////					"已升至最高级，将法力转换成粮食,本级消耗" + curLeavel + "点法力值回复" + curLeavel
////							* 2 + "点兵粮",
////
////					"将法力转换成粮食,本级消耗" + curLeavel + "点法力值回复" + curLeavel * 2
////							+ "点兵粮,下一级消耗" + nextLeavel + "点法力值回复" + nextLeavel
////							* 2 + "点兵粮, 升级所需金钱" + money };
////			break;
////
////		}
////
////		if (leavel == 0) {
////			curIndex = 0;
////		} else if (leavel == maxGoodsLeavel) {
////			curIndex = 1;
////		} else {
////			curIndex = 2;
////		}
////
////		int word = 30;
////		int w = 0;
////		w = intro[curIndex].length() * word;
////		Vector<?> vector = MyGameCanvas.getSubsection(intro[curIndex], 30);
////		for (int i = 0; i < vector.size(); i++) {
////			GameDraw.add_String2((String) vector.elementAt(i),
////					x, y + i * 20, Tools.HCENTER_TOP, 0xff00fcff, 1000);
////		}
//	}
//
////	final static int Goods_type_农夫铲 = 0;
////	final static int Goods_type_稻谷令牌 = 1;
////	final static int Goods_type_黄金戒指 = 2;
////	final static int Goods_type_精神项链 = 3;
////	final static int Goods_type_战记勋章 = 4;
////	final static int Goods_type_生命项链 = 5;
////	final static int Goods_type_回复戒指 = 6;
////	final static int Goods_type_法力戒指 = 7;
////	final static int Goods_type_火焰球 = 8;
////	final static int Goods_type_冰冻球 = 9;
////	final static int Goods_type_闪电球 = 10;
////	final static int Goods_type_医疗球 = 11;
////	final static int Goods_type_力量球 = 12;
////	final static int Goods_type_兵粮球 = 13;
//
//	int getGoods(int type, int leavel) {
//		if (leavel == -1) {
//			leavel = 0;
//		}
//		int curIndex = getMenuIndex(MENU_装备);
//		int prop = goodsData[curIndex][type];
//
////		switch (type) {
////		case G_huifuliangshi_up:
////			prop = prop + leavel; // 每次1:2法力恢复粮食
////			break;
////
////		case G_ice: // 冰冻几率
////			prop = prop + leavel * goodsData[curIndex][G_ice_up]; //
////			break;
////
////		case G_yiliao: // 医疗血量
////			prop = prop + leavel * goodsData[curIndex][G_yiliao_up];
////			break;
////
////		case G_huifuliangshi:
////			prop = prop + leavel * goodsData[curIndex][G_huifuliangshi_up]; // 粮食每级恢复加1
////			return prop; //
////
////		case G_ls_addmax:
////			prop = prop + leavel * goodsData[curIndex][G_ls_addmax_up]; // 增加最大粮食
////
////			break;
////
////		case G_jinqian:
////			prop = prop + leavel * goodsData[curIndex][G_jinqian_up]; // 增加掉落金钱
////			break; //
////
////		case G_huifump:
////			prop = prop + leavel * goodsData[curIndex][G_huifump_up]; // 增加mp恢复速度
////			break;
////
////		case G_jinyan:
////			prop = prop + leavel * goodsData[curIndex][G_jinyan_up]; // 增加获得的经验
////			break; //
////
////		case G_hp:
////			prop = prop + leavel * goodsData[curIndex][G_hp_up]; // 增加最大血量%
////			break; //
////
////		case G_huifuhp:
////			prop = prop - leavel; // 增加血量恢复速度，每次时间减1，*************************************************************************
////			break; //
////
////		case G_mp: // 增加魔法最大值
////			prop = prop + leavel * goodsData[curIndex][G_mp_up];
////			break;
////
////		case G_attack_skill: // 增加技能攻击
////			prop = prop + leavel * goodsData[curIndex][G_attack_up];
////			break; //
////
////		case G_ice_up:
////			break; //
////
////		case G_attack_up:
////			break; //
////		case G_money:
////			break; //
////		case G_money_up:
////			break; //
////
////		case G_hp_up:
////			break; //
////		case G_time:
////			break; //
////		case G_leavel:
////			break; //
////
////		case G_jinqian_up:
////			break; //
////
////		case G_jinyan_up:
////			break; //
////
////		case G_huifump_up:
////			break;
////
////		case G_mp_up:
////			break;
////		case G_ls_addmax_up:
////			break;
////
////		case G_yiliao_up:
////			break;
////		}
//
//		return prop;
//	}
//
//	int getGoods_2(int curIndex, int type, int leavel) {
//		if (leavel == -1) {
//			leavel = 0;
//		}
//		// int curIndex = getMenuIndex(MENU_背包);
//		int prop = goodsData[curIndex][type];
//
////		switch (type) {
////		case G_huifuliangshi_up:
////			prop = prop + leavel; // 每次1:2法力恢复粮食
////			break;
////
////		case G_ice: // 冰冻几率
////			prop = prop + leavel * goodsData[curIndex][G_ice_up]; //
////			break;
////
////		case G_yiliao: // 医疗血量
////			prop = prop + leavel * goodsData[curIndex][G_yiliao_up];
////			break;
////
////		case G_huifuliangshi:
////			prop = prop + leavel * goodsData[curIndex][G_huifuliangshi_up]; // 粮食每级恢复加1
////			return prop; //
////
////		case G_ls_addmax:
////			prop = prop + leavel * goodsData[curIndex][G_ls_addmax_up]; // 增加最大粮食
////
////			break;
////
////		case G_jinqian:
////			prop = prop + leavel * goodsData[curIndex][G_jinqian_up]; // 增加掉落金钱
////			break; //
////
////		case G_huifump:
////			prop = prop + leavel * goodsData[curIndex][G_huifump_up]; // 增加mp恢复速度
////			break;
////
////		case G_jinyan:
////			prop = prop + leavel * goodsData[curIndex][G_jinyan_up]; // 增加获得的经验
////			break; //
////
////		case G_hp:
////			prop = prop + leavel * goodsData[curIndex][G_hp_up]; // 增加最大血量%
////			break; //
////
////		case G_huifuhp:
////			prop = prop - leavel; // 增加血量恢复速度，每次时间减1，*************************************************************************
////			break; //
////
////		case G_mp: // 增加魔法最大值
////			prop = prop + leavel * goodsData[curIndex][G_mp_up];
////			break;
////
////		case G_attack_skill: // 增加技能攻击
////			prop = prop + leavel * goodsData[curIndex][G_attack_up];
////			break; //
////
////		case G_ice_up:
////			break; //
////
////		case G_attack_up:
////			break; //
////		case G_money:
////			break; //
////		case G_money_up:
////			break; //
////
////		case G_hp_up:
////			break; //
////		case G_time:
////			break; //
////		case G_leavel:
////			break; //
////
////		case G_jinqian_up:
////			break; //
////
////		case G_jinyan_up:
////			break; //
////
////		case G_huifump_up:
////			break;
////
////		case G_mp_up:
////			break;
////		case G_ls_addmax_up:
////			break;
////
////		case G_yiliao_up:
////			break;
////		}
//
//		return prop;
//	}
//
////	int getGoodsMoney() { // 升级物品所需金钱
////		int curIndex = getMenuIndex(MENU_装备);
////		int leavel = goodsLeavelData[curIndex];
////
////		int money;
////		if (leavel != 0) {
////			money = (leavel) * goodsData[curIndex][G_money_up];
////		} else {
////			money = goodsData[curIndex][G_money] * 100;
////		}
////		return money;
////	}
//
//	void drawGoodsIcon(int x, int y, int curIndex, int curS, boolean isChoose,
//			boolean isShowNum) {
////		switch (curS) {
////		case 0: // 开通
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU13, x, y, 49 * curIndex,
////					0, 49, 49, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2000);
////			break;
////		case 1: // 未开通
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU11, x, y, 49 * curIndex,
////					0, 49, 49, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2000);
////			break;
////		}
////
////		if (isChoose) {
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU5, x + 3, y + 3, 0, 69,
////					71, 71, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2001);
////		}
////		if (isShowNum) {
////			if (curIndex == getMenuIndex(MENU_装备)) {
////				GameDraw.add_Image(PAK_IMAGES.IMG_MENU5, x + 2, y + 49, 0,
////						140, 73, 26, Tools.HCENTER_VCENTER, Tools.TRANS_NONE,
////						2001);
////				GameNumber.drawAddNum_mid(PAK_IMAGES.IMG_NUMBER3,
////						PAK_IMAGES.IMG_NUMBER3, goodsLeavelData[curIndex],
////						maxGoodsLeavel, x - 2, y + 48, 14, 18, 2002,
////						Tools.HCENTER_VCENTER, 0);
////			} else {
////				GameNumber.drawAddNum_mid(PAK_IMAGES.IMG_NUMBER1,
////						PAK_IMAGES.IMG_NUMBER1, goodsLeavelData[curIndex],
////						maxGoodsLeavel, x - 3, y + 49, 12, 16, 2001,
////						Tools.HCENTER_VCENTER, 0);
////			}
////		}
//	}
//
//	void drawSkillIcon(int x, int y, int curIndex, int curS, boolean isChoose,
//			boolean isShowNum) {
////		switch (curS) {
////		case 0: // 开通
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU13, x, y,
////					56 * (curIndex - 8), 49, 56, 57, Tools.HCENTER_VCENTER,
////					Tools.TRANS_NONE, 2000);
////			break;
////		case 1: // 未开通
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU11, x, y,
////					56 * (curIndex - 8), 49, 56, 57, Tools.HCENTER_VCENTER,
////					Tools.TRANS_NONE, 2000);
////			break;
////		}
////
////		if (isChoose) {
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU5, x, y, 2, 0, 68, 69,
////					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 2001);
////		}
////
////		if (isShowNum) {
////			if (curIndex == getMenuIndex(MENU_装备)) {
////				GameDraw.add_Image(PAK_IMAGES.IMG_MENU5, x + 2, y + 47, 0,
////						140, 73, 26, Tools.HCENTER_VCENTER, Tools.TRANS_NONE,
////						2001);
////				GameNumber.drawAddNum_mid(PAK_IMAGES.IMG_NUMBER3,
////						PAK_IMAGES.IMG_NUMBER3, goodsLeavelData[curIndex],
////						maxGoodsLeavel, x - 8, y + 46, 14, 18, 2002,
////						Tools.HCENTER_VCENTER, 0);
////			} else {
////				GameNumber.drawAddNum_mid(PAK_IMAGES.IMG_NUMBER1,
////						PAK_IMAGES.IMG_NUMBER1, goodsLeavelData[curIndex],
////						maxGoodsLeavel, x - 6, y + 47, 12, 16, 2001,
////						Tools.HCENTER_VCENTER, 0);
////			}
////		}
//
//	}
//
//	public void ctrlGoods(int keyCode) {
//		if (isOver) {
//			return;
//		}
//
////		switch (keyCode) {
////		case MyGameCanvas.KEY_RIGHT:
////		case MyGameCanvas.KEY_6:
////			if (getMenuIndex() < goodsLeavelData.length - 1) {
////				setMenuIndex(menuStatus, getMenuIndex() + 1);
////				// indexStrIntro = -300;
////			} else {
////				setMenuIndex(menuStatus, 0);
////				// indexStrIntro = -300;
////			}
////
////			break;
////		case MyGameCanvas.KEY_LEFT:
////		case MyGameCanvas.KEY_4:
////			if (getMenuIndex() > 0) {
////				setMenuIndex(menuStatus, getMenuIndex() - 1);
////				// indexStrIntro = -300;
////			} else {
////				setMenuIndex(menuStatus, goodsLeavelData.length - 1);
////				// indexStrIntro = -300;
////			}
////			break;
////
////		case MyGameCanvas.KEY_DOWN:
////		case MyGameCanvas.KEY_8:
////			if (getMenuIndex() < 7) {
////				setMenuIndex(menuStatus, getMenuIndex() + 7);
////				// indexStrIntro = -300;
////			} else {
////				setMenuIndex(menuStatus, getMenuIndex() - 7);
////				// indexStrIntro = -300;
////			}
////			break;
////
////		case MyGameCanvas.KEY_UP:
////		case MyGameCanvas.KEY_2:
////			if (getMenuIndex() > 6) {
////				setMenuIndex(menuStatus, getMenuIndex() - 7);
////				// indexStrIntro = -300;
////			} else {
////				setMenuIndex(menuStatus, getMenuIndex() + 7);
////				// indexStrIntro = -300;
////			}
////			break;
////
////		case MyGameCanvas.KEY_OK:
////		case MyGameCanvas.KEY_5:
////		case MyGameCanvas.KEY_LS:
////			setMenuStatus(MENU_背包_CHOOSE);
////			break;
////
////		case MyGameCanvas.KEY_RS:
////			// indexStrIntro = 0;
////			MyGameCanvas.setST(MyGameCanvas.ST_PLAY);
////			break;
////		}
////
////		// Math.min(menuIndex[menuIndex_prop], 8);
////		// Math.max(menuIndex[menuIndex_prop], 0);
////
////	}
////
////	public void ctrlGoodsChoose(int keyCode) {
////		if (isOver) {
////			return;
////		}
////
////		switch (keyCode) {
////		case MyGameCanvas.KEY_DOWN:
////		case MyGameCanvas.KEY_8:
////			if (getMenuIndex(MENU_装备) > 7) { // 物品
////				if (getMenuIndex() < 4) {
////					setMenuIndex(menuStatus, getMenuIndex() + 1);
////				} else {
////					setMenuIndex(menuStatus, 0);
////				}
////			} else {
////				if (getMenuIndex() < 3) {
////					setMenuIndex(menuStatus, getMenuIndex() + 1);
////				} else {
////					setMenuIndex(menuStatus, 0);
////				}
////			}
////			break;
////
////		case MyGameCanvas.KEY_UP:
////		case MyGameCanvas.KEY_2:
////
////			if (getMenuIndex(MENU_装备) > 7) {
////				if (getMenuIndex() > 0) {
////					setMenuIndex(menuStatus, getMenuIndex() - 1);
////				} else {
////					setMenuIndex(menuStatus, 4);
////				}
////			} else {
////				if (getMenuIndex() > 0) {
////					setMenuIndex(menuStatus, getMenuIndex() - 1);
////				} else {
////					setMenuIndex(menuStatus, 3);
////				}
////			}
////
////			break;
////
////		case MyGameCanvas.KEY_OK:
////		case MyGameCanvas.KEY_5:
////		case MyGameCanvas.KEY_LS:
////			if (getMenuIndex(MENU_装备) > 7) {
////				switch (getMenuIndex()) {
////				case 0:
////					menuGoodsChoosePressOK();
////					break;
////
////				case 1:
////					if (goodsLeavelData[getMenuIndex(MENU_装备)] == 0) {
////						MyGameCanvas.toStr(new String[] { "未开启" },
////								MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////					} else {
////						if (isGoodsEquipHave(getMenuIndex(MENU_装备))) {
////							MyGameCanvas.toStr(new String[] { "该技能不能重复装备" },
////									MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////						} else {
////							MyGameCanvas.toStr(new String[] { "技能装备成功" },
////									MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////							goodsSkill[2] = (byte) getMenuIndex(MENU_装备);
////						}
////					}
////					break;
////				case 2:
////					if (goodsLeavelData[getMenuIndex(MENU_装备)] == 0) {
////						MyGameCanvas.toStr(new String[] { "未开启" },
////								MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////					} else {
////						if (isGoodsEquipHave(getMenuIndex(MENU_装备))) {
////							MyGameCanvas.toStr(new String[] { "该技能不能重复装备" },
////									MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////						} else {
////
////							MyGameCanvas.toStr(new String[] { "技能装备成功" },
////									MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////							goodsSkill[3] = (byte) getMenuIndex(MENU_装备);
////						}
////					}
////					break;
////				case 3:
////					if (goodsLeavelData[getMenuIndex(MENU_装备)] == 0) {
////						MyGameCanvas.toStr(new String[] { "未开启" },
////								MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////					} else {
////						if (isGoodsEquipHave(getMenuIndex(MENU_装备))) {
////							MyGameCanvas.toStr(new String[] { "该技能不能重复装备" },
////									MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////						} else {
////
////							MyGameCanvas.toStr(new String[] { "技能装备成功" },
////									MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////							goodsSkill[4] = (byte) getMenuIndex(MENU_装备);
////						}
////					}
////					break;
////
////				case 4:
////					// indexStrIntro = -300;
////					setMenuIndex(menuStatus, 0);
////					setMenuStatus(MENU_装备);
////
////					break;
////				}
////			} else {
////				switch (getMenuIndex()) {
////				case 0:
////					menuGoodsChoosePressOK();
////					break;
////
////				case 1:
////					if (goodsLeavelData[getMenuIndex(MENU_装备)] == 0) {
////						MyGameCanvas.toStr(new String[] { "未开启" },
////								MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////					} else {
////						if (isGoodsEquipHave(getMenuIndex(MENU_装备))) {
////							MyGameCanvas.toStr(new String[] { "该物品不能重复装备" },
////									MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////						} else {
////
////							MyGameCanvas.toStr(new String[] { "物品装备成功" },
////									MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////							goodsSkill[0] = (byte) getMenuIndex(MENU_装备);
////						}
////					}
////					break;
////				case 2:
////					if (goodsLeavelData[getMenuIndex(MENU_装备)] == 0) {
////						MyGameCanvas.toStr(new String[] { "未开启" },
////								MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////					} else {
////						if (isGoodsEquipHave(getMenuIndex(MENU_装备))) {
////							MyGameCanvas.toStr(new String[] { "该物品不能重复装备" },
////									MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////						} else {
////
////							MyGameCanvas.toStr(new String[] { "技能装备成功" },
////									MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////							goodsSkill[1] = (byte) getMenuIndex(MENU_装备);
////						}
////					}
////					break;
////
////				case 3:
////					// indexStrIntro = -300;
////					setMenuIndex(menuStatus, 0);
////					setMenuStatus(MENU_装备);
////					break;
////				}
////
////			}
////
////			break;
////		case MyGameCanvas.KEY_RS:
////			setMenuIndex(menuStatus, 0);
////			setMenuStatus(MENU_装备);
////
////			break;
////		}
////
////		// Math.min(menuIndex[menuIndex_prop], 8);
////		// Math.max(menuIndex[menuIndex_prop], 0);
//
//	}
//
//	void menuGoodsChoosePressOK() {
////		if (goodsLeavelData[getMenuIndex(MENU_装备)] < maxGoodsLeavel) {
////			if (getMoney() >= getGoodsMoney()) {
////				addMoney(-getGoodsMoney());
////				// indexStrIntro = -300;
////				goodsLeavelData[getMenuIndex(MENU_装备)]++;
////				MyGameCanvas.toStr(new String[] { "升级成功" },
////						MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////			} else {
////				 MyGameCanvas.toStr(new String[] { "您的金钱不足" },
////				 MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////			
////			}
////		} else {
////			MyGameCanvas.toStr(new String[] { "已升级至最高等级" },
////					MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////		}
//	}
//
//	/**************************** VIP商店介绍 **********************/
//	public static void drawVipShop(int x, int y) {
////		GameDraw.add_Image(PAK_IMAGES.IMG_VIPSHOP1, x, y,
////				Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 500);
////		GameDraw.add_Image(PAK_IMAGES.IMG_FONT3, x + 220, y + 140, 0, 88,
////				73, 39, Tools.HCENTER_TOP, Tools.TRANS_NONE, 501);
//	}
//
//	/**************************** 道具介绍 **********************/
//
//	public static String[] getSubsection(String str, int lineNum) {
//		Vector<String> vector = null;
//		vector = new Vector<String>();
//		// int i = 0;
//		while (!str.equals("")) {
//			if (str.length() > lineNum) {
//				vector.addElement(str.substring(0, lineNum));
//				str = str.substring(lineNum);
//			} else {
//				vector.addElement(str);
//				str = "";
//			}
//		}
//		String[] resultStr = new String[vector.size()];
//		for (int j = 0; j < vector.size(); j++) {
//			resultStr[j] = (String) vector.elementAt(j);
//		}
//		return resultStr;
//	}
//
//	public void drawItemDescript(int x, int y, String str, int adx) {
//		int word = 20;
//		int h = 30;
//		int len = 176 - adx * 2;
//		int num = len / word;
//		String[] str2 = getSubsection(str, num);
//		for (int i = 0; i < str2.length; i++) {
//			GameDraw.add_String( str2[i], x + adx, y + (h + 3)
//					* i, Tools.TOP_LEFT, 0, 30, 20);
//			GameDraw.add_String( str2[i], x + adx + 1, y
//					+ (h + 3) * i + 1, Tools.TOP_LEFT, 0xffffff, 30, 20);
//
//		}
//	}
//
//	/**************************** 装备 **********************/
//	static int[][] equipData = new int[5][I_LEN]; // 主角装备数组(武器/衣服/鞋子/饰品/手套)
//	static int equip[] = { 0, 0, 0, 0, 0 }; // 身上是否有装备 0：没有，1：有
//
//	public void drawEquipNameRect(int x, int y, int w, int h, String str,
//			boolean isChoose, int color, boolean isDraw) {
//		if (isChoose) {
//			GameDraw.add_Rect( x, y, w, h, true,
//					Tools.TOP_LEFT, 0xFFD27E, MENUBG_LAYER + 1);
//
//			GameDraw.add_Rect(x + 1, y + 1, w - 3, h - 3,
//					false, Tools.TOP_LEFT, 0x8C4900, MENUBG_LAYER + 1);
//			GameDraw.add_Rect( x + 2, y + 2, w - 4, h - 4,
//					true, Tools.TOP_LEFT, 0xFFD27E, MENUBG_LAYER + 1);
//
//		} else {
//			GameDraw.add_Rect( x, y, w, h, true,
//					Tools.TOP_LEFT, 0x8C4900, MENUBG_LAYER + 10);
//			GameDraw.add_Rect( x + 1, y + 1, w - 2, h - 2,
//					true, Tools.TOP_LEFT, 0x8C4900, MENUBG_LAYER + 10);
//
//		}
//
//		if (isDraw) {
//			GameDraw.add_String( str, x + w / 2, y + 2,
//					Tools.HCENTER_TOP, color, MENUBG_LAYER + 2, 20);
//		}
//
//	}
//
//	public static void drawContent(int x, int y, String str, int color) { // 游戏开始或结束的故事介绍
//		String[] s = getSubsection(str, 7);
//		for (int i = 0; i < s.length; i++) {
//			GameDraw.add_String(s[i], x, y + i * 20,
//					Tools.TOP_LEFT, color, 20, 20);
//
//		}
//	}
//
//	/********************** 技能 *********/
//
//	public void drawSkillRole(int x, int y, int type) {
//	//	skillRole.iHp = 100;
//		skillRole.x = x;
//		skillRole.y = y;
//		skillRole.setType((byte) type);
//		skillRole.move();
////		skillRole.RolePaint();
//
//	}
//
//	/************************* 效果 **********************************/
//	static Vector<int[]> effect = new Vector<int[]>();
//
//	public static final byte EFFECT_PROP_UP = 0; // 物品升级特效
//
//	public static void addToVector(int x, int y, int type, boolean isLeft,
//			int curIndex, int speedY, int w, int h, int index, Vector<int[]> vec) {
//		int[] temp = new int[9];
//		temp[0] = x;
//		temp[1] = y;
//		temp[2] = type;
//		temp[3] = isLeft ? -1 : 1;
//		temp[4] = curIndex;
//		temp[5] = speedY;
//		temp[6] = w;
//		temp[7] = h;
//		temp[8] = index;
//		effect.addElement(temp);
//	}
//
//	public void drawEffect() { // 绘制爆炸效果
////		int size = effect.size() - 1;
////		for (int i = size; i >= 0; i--) {
////			int[] temp = (int[]) effect.elementAt(i);
////			// int imgIndex = -1;
////			byte[] motion = null;
////			// short[][] imgData = null;
////
////			// boolean isLeft = temp[4] == 0;
////			switch (temp[2]) {
////			case EFFECT_PROP_UP:
////
////				// System.out.println("EFFECT_PROP_UP:"+EFFECT_PROP_UP);
////				motion = new byte[] { 0, 2, 1, 3, };
////				GameDraw.add_Image(PAK_IMAGES.IMG_EFF1, temp[0], temp[1],
////						0, motion[temp[8]] * 35, 35, 35, Tools.HCENTER_VCENTER,
////						Tools.TRANS_NONE, 30);
////				if (++temp[8] == motion.length) {
////					// propData[menuIndex[menuIndex_prop]]++;
////					// isOver = false;
////					effect.removeElementAt(i);
////				}
////				break;
////			}
////		}
//	}
//
//	/********************************************************************/
//	public void drawRect(int x, int y, int w, int h, int layer) {
//		// GameDraw.addObject(Tools.TYPE_ARC, x, y, w, bh, 0, 360, true,
//		// Tools.BOTTOM_LEFT, 0x000000, layer);
//
//		GameDraw.addObject(Tools.TYPE_ROUND_RECT, x, y, w, h, 8, 8, true,
//				Tools.TOP_LEFT, 0x785040, layer);
//		GameDraw.addObject(Tools.TYPE_ROUND_RECT, x + 1, y + 1, w - 3, h - 3,
//				8, 8, false, Tools.TOP_LEFT, 0x403028, layer);
//		GameDraw.addObject(Tools.TYPE_ROUND_RECT, x + 2, y + 2, w - 4, h - 4,
//				8, 8, true, Tools.TOP_LEFT, 0x282018, layer);
//	}
//
//	/************************************************************ 存档 ******************************/
//	static int[][] save = new int[3][3];// 0 是否有存档 1 等级 2金钱
//
//	boolean isTouch;
//
//	public void drawSave(int x, int y) {
////		String[] str = { "存档1", "存档2", "存档3" };
////		drawMenuBg(x, y + moveY, MENU_存档);
////		for (int i = 0; i < str.length; i++) {
////			if (save[0][i] > 0) {
////				GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER9, save[1][i], x
////						- 180 + 200 * i, y + 85 + moveY, 17, 1,
////						Tools.BOTTOM_LEFT, 501, 24, 0, true);
////				GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER9, save[2][i], x
////						- 200 + 200 * i, y + 125 + moveY, 17, 1,
////						Tools.BOTTOM_LEFT, 501, 24, 0, true);
////			}
////			if (i == (getMenuIndex(MENU_存档) - 3)) {
////				if (skillRole.curStatus != GameInterface.STATUS_STOP) {
////					skillRole.setStatus(GameInterface.STATUS_STOP);
////				}
////				drawSkillRole(x - 175 + 200 * i, y - 35 + moveY,
////						GameRole.TYPE_ROLE_MENUSKILL);
////				GameDraw.add_Image(PAK_IMAGES.IMG_FONT11,
////						x - 240 + 200 * i, y + 130 + moveY, 0, 0, 74, 77,
////						Tools.HCENTER_BOTTOM, Tools.TRANS_NONE, 30);
////				if (save[0][(getMenuIndex(MENU_存档) - 3)] > 0) {
////					GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER7,
////							save[1][(getMenuIndex(MENU_存档) - 3)], x - 180 + 200
////									* i, y + 85 + moveY, 17, 1,
////							Tools.BOTTOM_LEFT, 501, 24, 0, true);
////					GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER8,
////							save[2][(getMenuIndex(MENU_存档) - 3)], x - 200 + 200
////									* i, y + 125 + moveY, 17, 1,
////							Tools.BOTTOM_LEFT, 501, 24, 0, true);
////				}
////
////			} else {
////				GameDraw.add_Image(PAK_IMAGES.IMG_MENU27,
////						x - 200 + 200 * i, y + 50 + moveY,
////						Tools.HCENTER_BOTTOM, Tools.TRANS_NONE, 30);
////				GameDraw.add_Image(PAK_IMAGES.IMG_FONT11,
////						x - 240 + 200 * i, y + 130 + moveY, 74, 0, 74, 77,
////						Tools.HCENTER_BOTTOM, Tools.TRANS_NONE, 30);
////
////			}
////		}
////
////		if (isBack) {
////			if (moveY > 0) {
////				moveY -= 40;
////			} else {
////				ctrl(MyGameCanvas.KEY_RS);
////			}
////		} else {
////			if (moveY < 440) {
////				moveY += 40;
////			} else {
////				isTouch = true;
////				moveY = 440;
////			}
////		}
//	}
//
//	public void drawDataStr(int x, int y, String str, int leavel) {
//		GameDraw.add_String( str, x, y + 1,
//				str == "空" ? Tools.HCENTER_TOP : Tools.TOP_LEFT, 0, leavel + 1,
//				20);
//		// Tools.addObject(Tools.TYPE_STRING, str, x + 1, y + 1,
//		// Tools.TOP,
//		// 0xffffff, leavel + 1);
//	}
//
//	public void ctrlSave(int keyCode) {
////		switch (keyCode) {
////		case MyGameCanvas.KEY_LS:
////		case MyGameCanvas.KEY_OK:
////		case MyGameCanvas.KEY_5:
////
////			// if (save[getMenuIndex(MENU_存档)][0] == 0) {
////			newGame();
////			MyGameCanvas.setST(MyGameCanvas.ST_LOAD);
////			// } else {
////			// setMenuStatus(MENU_存档_CHOOSE);
////			// }
////			break;
////
////		case MyGameCanvas.KEY_RS:
////			MyGameCanvas.setST(MyGameCanvas.ST_MENU);
////			menuIndex[menuIndex_save] = -1;
////			moveY = 0;
////			isBack = false;
////			break;
////
////		case MyGameCanvas.KEY_LEFT:
////		case MyGameCanvas.KEY_4:
////			if (getMenuIndex() > 0) {
////				setMenuIndex(menuStatus, getMenuIndex() - 1);
////			} else {
////				setMenuIndex(menuStatus, 2);
////				// indexStrIntro = 0;
////			}
////			break;
////		case MyGameCanvas.KEY_RIGHT:
////		case MyGameCanvas.KEY_6:
////			if (getMenuIndex() < 2) {
////				setMenuIndex(menuStatus, getMenuIndex() + 1);
////			} else {
////				setMenuIndex(menuStatus, 0);
////			}
////
////			break;
////		}
//	}
//
//	void drawSaveChoose(int x, int y) {
//
////		// GameDraw.addObject(PAK_IMAGES.IMG_MENU27_PNG,
////		// x, y, saveImageData[getMenuIndex() == 0 ? 4 : 5],
////		// Tools.TOP_LEFT,
////		// Tools.TRANS_NONE,
////		// 200);
////
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU27, x + 60, y + 205, 88, 39,
////				86, 31, Tools.TOP_LEFT, Tools.TRANS_NONE, 200);
////
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU27, x + 176, y + 205, 88, 79,
////				86, 31, Tools.TOP_LEFT, Tools.TRANS_NONE, 200);
//
//	}
//
//	public void ctrlSaveChoose(int keyCode) {
////		switch (keyCode) {
////		case MyGameCanvas.KEY_LS:
////		case MyGameCanvas.KEY_OK:
////		case MyGameCanvas.KEY_5:
////
////			if (getMenuIndex() == 0) {
////				readDB(getMenuIndex(MENU_存档), true);
////				setMenuStatus(MENU_地图);
////			} else {
////				// System.out.println(" getMenuIndex(MENU_存档):"+
////				// getMenuIndex(MENU_存档)+"    save[getMenuIndex(MENU_存档)][0]:"+
////				// save[getMenuIndex(MENU_存档)][0]);
////				save[getMenuIndex(MENU_存档)][0] = 0;
////
////				save[getMenuIndex(MENU_存档)][1] = 0;
////				newGame();
////				GameEngine.role.level = 0;
////				writeDB(getMenuIndex(MENU_存档));
////				setMenuStatus(MENU_存档);
////			}
////
////			break;
////
////		case MyGameCanvas.KEY_RS:
////			setMenuStatus(MENU_存档);
////
////			break;
////
////		case MyGameCanvas.KEY_LEFT:
////		case MyGameCanvas.KEY_4:
////			if (getMenuIndex(MENU_存档) > 0) {
////				setMenuIndex(MENU_存档, getMenuIndex(MENU_存档) - 1);
////			} else {
////				setMenuIndex(MENU_存档, 2);
////				// indexStrIntro = 0;
////			}
////			setMenuIndex(MENU_存档_CHOOSE, 0);
////			break;
////		case MyGameCanvas.KEY_RIGHT:
////		case MyGameCanvas.KEY_6:
////			if (getMenuIndex(MENU_存档) < 2) {
////				setMenuIndex(MENU_存档, getMenuIndex(MENU_存档) + 1);
////			} else {
////				setMenuIndex(MENU_存档, 0);
////			}
////			setMenuIndex(MENU_存档_CHOOSE, 0);
////			break;
////
////		case MyGameCanvas.KEY_UP:
////		case MyGameCanvas.KEY_2:
////			if (getMenuIndex() > 0) {
////				setMenuIndex(menuStatus, getMenuIndex() - 1);
////			} else {
////				setMenuIndex(menuStatus, 1);
////				// indexStrIntro = 0;
////			}
////			break;
////		case MyGameCanvas.KEY_DOWN:
////		case MyGameCanvas.KEY_8:
////			if (getMenuIndex() < 1) {
////				setMenuIndex(menuStatus, getMenuIndex() + 1);
////			} else {
////				setMenuIndex(menuStatus, 0);
////			}
////
////			break;
////		}
//	}
//
//	// ///////////////////////////////存档/////////////////////////
//	// RecordStore db;
//	// static int dbId = 1;
//	private void openDB() {
//		// try {
//		// db = RecordStore.openRecordStore("kbz", true);
//		// // db.addRecord(day, 0, day.length);
//		// }
//		// catch (Exception e) {
//		// e.printStackTrace();
//		// }
//	}
//
//	public void writeDB(int recordId) {
//		this.openDB();
//		// try {
//		//
//		// ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		// DataOutputStream dos = new DataOutputStream(baos);
//		// save[recordId][0] = engine.role.level;
//		// save[recordId][1] = getMoney();
//		//
//		// for (int i = 0; i < save[recordId].length; i++) {
//		// dos.writeInt(save[recordId][i]);
//		// }
//		// dos.writeInt(engine.role.exp);
//		// dos.write(goodsLeavelData);
//		//
//		// dos.write(goodsSkill);
//		//
//		// for (int i = 0; i < mapData.length; i++) {
//		// dos.write(mapData[i]);
//		// }
//		// dos.write(propLeavelData); ni
//		// dos.write(ZhengfaLeavelData);
//		//
//		// byte[] data = baos.toByteArray();
//		// baos.close();
//		// dos.close();
//		// try {
//		// db.setRecord(recordId + 1, data, 0, data.length);
//		// }
//		// catch (Exception e) {
//		//
//		// System.out.println("Exception");
//		// db.addRecord(data, 0, data.length);
//		// db.addRecord(data, 0, data.length);
//		// db.addRecord(data, 0, data.length);
//		// }
//		// db.closeRecordStore();
//		// }
//		// catch (Exception e) {
//		// e.printStackTrace();
//		// return;
//		// }
//
//	}
//
//	public boolean readDB(int recordId, boolean isLoad) {
//
//		return false;
//	}
//
//	// /////////////////////////////////////
//
//	public static void drawSelectBG(int x, int y, int w, int h, int leavel,
//			boolean isChoose, String name, int color) {
//		GameDraw.add_Rect(x, y, w, h, true, Tools.TOP_LEFT,
//				0x690000, leavel);
//		GameDraw.add_Rect( x, y + 1, w, h - 2, true,
//				Tools.TOP_LEFT, 0xFF6C00, leavel);
//		GameDraw.add_Rect( x, y + 2, w, h - 4, true,
//				Tools.TOP_LEFT, 0xFFE89E, leavel);
//
//		GameDraw.add_String( name, x + w / 2 + 1, y + 6,
//				Tools.HCENTER_TOP, color, leavel, 20);
//		// Tools.addObject(Tools.TYPE_STRING, name, x + w / 2, isChoose ? y + 1
//		// : y,
//		// Graphics.HCENTER | Graphics.TOP,
//		// color, leavel);
//	}
//
//	public void drawStr(int x, int y, int id, int type) {
////		String[] str = new String[1]; // 名字, 说明1,说明2,说明3
////		switch (type) {
////		case 0: // 装备
////
////			break;
////		case 1: // 舍弃
////
////			break;
////		case 2: // 分解
////
////			break;
////		case 3:
////
////			break;
////		case 4:
////			str[0] = "任务完成";
////			break;
////		case 5:
////			str[0] = "得到经验" + id;
////			break;
////		case 6:
////			str[0] = "得到金钱" + id;
////			break;
////		case 7:
////			break;
////		}
////
////		MyGameCanvas.drawStrBox(Tools.setOffX, Tools.setOffY + 50,
////				MyGameCanvas.SCREEN_WIDTH, 25 * 1 + 1, str, 20);
////		// drawSelectBG(engine.map.setOffX,
////		// engine.map.setOffY + MyGameCanvas.SCREEN_HEIGHT / 2 - 20,
////		// MyGameCanvas.SCREEN_WIDTH, 20, 200, true, str, 0);
//	}
//
//	/******************************************* 人物属性菜单 *****************************************/
//	public static final int PROP_等级 = 0;
//	public static final int PROP_气血 = 1;
//	public static final int PROP_魔法 = 2;
//	public static final int PROP_经验 = 3;
//	public static final int PROP_敏捷 = 4;
//	public static final int PROP_幸运 = 5;
//	public static final int PROP_攻击 = 6;
//	public static final int PROP_防御 = 7;
//	public static final int PROP_魔攻 = 8;
//	public static final int PROP_魔防 = 9;
//	public static final int PROP_金钱 = 10;
//
//	public void drawMenuProp(int x, int y) {
////		drawMenuBg(x, y, MENU_兵营);
////		for (int i = 0; i < 9; i++) {
////			drawPropIcon(x + i % 3 * 68 - 260, y + i / 3 * 96 - 105, i,
////					propLeavelData[i] == 0 ? 1 : 0, i == getMenuIndex(), true,
////					501);
////		}
////		// if (indexStrIntro == 0 || indexStrIntro % 20 == 0) {
////		// addToVector(x + 27 + menuIndex[menuIndex_prop] % 3 * 39, y + 66
////		// + menuIndex[menuIndex_prop] / 3 * 47, EFFECT_PROP_UP,
////		// false, menuIndex[menuIndex_prop], 0, 0, 0, 0, effect);
////		// isOver = false;
////		// }
////		drawPropliveBar(x - 140, y + 220, menuIndex[menuIndex_prop],
////				propLeavelData[menuIndex[menuIndex_prop]]);// 兵种 攻击血量数值条
////		drawPropPoint(x + 230, y + 50, menuIndex[menuIndex_prop],
////				propLeavelData[menuIndex[menuIndex_prop]]);
////		drawPropIntro(x, y, menuIndex[menuIndex_prop]);
////
////		if (skillRole.curStatus != GameRole.STATUS_MENU_SHOW) {
////			skillRole.setStatus(GameRole.STATUS_MENU_SHOW);
////		}
//	//	drawSkillRole(x + 10, y - 45, menuIndex[menuIndex_prop]);
//
//	}
//
//	int conTextX = 0;
//	int conTextY = 0;
//
//	void drawPropIntro(int x, int y, int curIndex) {
////		String[] propInfo = { "战斗中沉稳挺进是部队的中坚力量，攻击力低，防守力中等，在战争初期时您需要大量的步兵来巩固势力",
////				"拥有长距离杀伤力，在最远距离可以造成敌人伤害的射手，但是近身战却是他们致命的弱点",
////				"依靠协调的行动压制敌人的部队，攻击力和防御力上都超过步兵，是战斗中期近战的佼佼者",
////				"拥有力量与速度的战士，他们的拳头又快又强，在战争中的近身战中无疑是属于一个强者的存在",
////				"他们没有任何战斗力，但是手中却有无比坚硬的盾牌，在战斗中无疑给整个部队带来最强的防御力",
////				"杀伤力巨大的兵种，手中的炸弹是他们对敌人致命的武器，虽然防御力低，速度慢，但是他们也无疑是战场上很重要的战斗力",
////				"全身穿着厚重的铠甲，身材魁梧，手中巨大的武器让敌人闻风丧胆，不管是防御力还是攻击力都非常的可观",
////				"一生都在研究法术的术士拥有强大的法力，可以把敌人冰冻在原地丧失战斗力，在杀伤力和限制能力上都非常的不错",
////				"穿着重型铠甲的骑兵和巨大的恐龙坐骑让他拥有较高的移动速度，强大的杀伤力，是战场上可怕的对手", };
////		Vector<?> vector = MyGameCanvas.getSubsection(propInfo[curIndex], 19);
////		for (int i = 0; i < vector.size(); i++) {
////			GameDraw.add_String2( (String) vector.elementAt(i),
////					x + 100, y + 55 + i * 25, Tools.HCENTER_TOP, 0xff00fcff,
////					1000);
////		}
//	}
//
//	void drawPropliveBar(int x, int y, int curIndex, int leave) {
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU7, x, y, 0, 0, 346, 70,
////				Tools.BOTTOM_LEFT, Tools.TRANS_NONE, 502);
////		int hp_max = 2700;
////		int hp = getPropHp(curIndex, leave);
////		int hp_max_curIndex = getPropHp(curIndex, 20);
////
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU7, x + 48, y - 43, 0, 89,
////				hp_max_curIndex * 282 / hp_max, 15, Tools.BOTTOM_LEFT,
////				Tools.TRANS_NONE, 502);
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU7, x + 48, y - 43, 0, 73, hp
////				* 282 / hp_max, 15, Tools.BOTTOM_LEFT, Tools.TRANS_NONE, 502);
////		int attack_max = 2420;
////		int attack = getPropAttack(curIndex, leave);
////		int attack_up = getPropAttack(curIndex, 20);
////
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU7, x + 48, y - 11, 0, 89,
////				attack_up * 282 / attack_max, 15, Tools.BOTTOM_LEFT,
////				Tools.TRANS_NONE, 502);
////
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU7, x + 48, y - 11, 0, 73,
////				attack * 282 / attack_max, 15, Tools.BOTTOM_LEFT,
////				Tools.TRANS_NONE, 502);
//
//	}
//
//	public void drawPropPoint(int x, int y, int curIndex, int leave) { // 升级所需相
////		GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER2, getPropMoney(), x - 65,
////				y - 95, 14, 0, Tools.TOP_LEFT, 502, 18, 0, true);
////		GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER2, money, x
////				- MyGameCanvas.SCREEN_WIDTH / 2 - 70, y + 125, 14, 0,
////				Tools.TOP_LEFT, 502, 18, 0, true);
////		GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER3,
////				getPropAttack_up(curIndex, leave), x - 35, y - 162, 14, 0,
////				Tools.TOP_LEFT, 502, 18, 0, true);
////		GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER3,
////				getPropHp_up(curIndex, leave), x - 35, y - 110, 14, 0,
////				Tools.BOTTOM_LEFT, 502, 18, 0, true);
//	}
//
//	static byte[] propLeavelData = { 1, 0, 0, 0, 0, 0, 0, 0, 0 }; // 兵种等级
//
//	public static void drawPropIcon(int x, int y, int curIndex, int curS,
//			boolean isChoose, boolean isShowNum, int layer) {// curS 0 开通，1 未开通
//
////		switch (curS) {
////		case 0: // 开通
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU3, x, y, 62 * curIndex,
////					0, 60, 58, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, layer);
////			if (isChoose) {
////				GameDraw.add_Image(PAK_IMAGES.IMG_MENU5, x, y, 0, 69, 71,
////						71, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, layer);
////
////			}
////			break;
////
////		case 1: // 未开通
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU3, x, y, 62 * curIndex,
////					59, 60, 58, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, layer);
////			if (isChoose) {
////				GameDraw.add_Image(PAK_IMAGES.IMG_MENU5, x, y, 0, 69, 71,
////						71, Tools.HCENTER_VCENTER, Tools.TRANS_NONE, layer);
////			}
////
////			break;
////		}
////		if (isShowNum) {
////			if (curIndex == getMenuIndex()) {
////				GameDraw.add_Image(PAK_IMAGES.IMG_MENU5, x - 2, y + 46, 0,
////						140, 73, 26, Tools.HCENTER_VCENTER, Tools.TRANS_NONE,
////						layer);
////				GameNumber.drawAddNum_mid(PAK_IMAGES.IMG_NUMBER3,
////						PAK_IMAGES.IMG_NUMBER3, propLeavelData[curIndex],
////						20, x - 8, y + 45, 14, 18, layer + 1,
////						Tools.HCENTER_VCENTER, 0);
////			} else {
////				GameNumber.drawAddNum_mid(PAK_IMAGES.IMG_NUMBER1,
////						PAK_IMAGES.IMG_NUMBER1, propLeavelData[curIndex],
////						20, x - 8, y + 46, 12, 16, layer,
////						Tools.HCENTER_VCENTER, 0);
////			}
////		}
//	}
//
////	int getPropMoney() { // 升级所需金钱
////		int leavel = propLeavelData[getMenuIndex()];
////		int curIndex = getMenuIndex();
////		// return spriteData[curIndex][leavel == 0 ? I_money :
////		// I_money_up] * (leavel == 0 ? 100 : 1);
////		// int money = spriteData[curIndex][leavel == 0 ? I_money :
////		// I_money_up] * (leavel == 0 ? 100 : 1);
////
////		int money;
////		if (leavel != 0) {
////			money = (leavel + 1) * spriteData[curIndex][I_money_up];
////		} else {
////			money = spriteData[curIndex][I_money] * 100;
////		}
////		return money;
////	}
////
////	int getPropHp_up(int curIndex, int leavel) { // 升级增加的血条
////		return spriteData[curIndex][leavel == 0 ? I_hp : I_hp_up];
////	}
////
////	int getPropAttack_up(int curIndex, int leavel) { // 升级增加的攻击
////		return spriteData[curIndex][leavel == 0 ? I_attack : I_attack_up];
////	}
////
////	int getPropHp(int curIndex, int leavel) { // 当前血量
////
////		return spriteData[curIndex][I_hp] + spriteData[curIndex][I_hp_up]
////				* leavel;
////	}
////
////	int getPropAttack(int role_type, int leavel) { // 当前攻击
////		return spriteData[role_type][I_attack]
////				+ spriteData[role_type][I_attack_up] * leavel;
////	}
////
////	int getPropFood(int curIndex) {
////		return spriteData[curIndex][I_food];
////	}
////
////	int getPropDelayTime(int curIndex) { // 造兵时间
////		int time = spriteData[curIndex][I_time];
////
////		if (zhenfaIndex == 2) {
////			time = time - time * setZhengfaEff(zhenfaIndex) / 100 - time
////					* upLeavel[12] * 2 / 100;
////		} else {
////			time = time - time * upLeavel[13] * 2 / 100;
////		}
////
////		return time;
//	//}
//
//	public void ctrlProp(int keyCode) {
//		if (isOver) {
//			return;
//		}
//
////		switch (keyCode) {
////		case MyGameCanvas.KEY_RIGHT:
////		case MyGameCanvas.KEY_6:
////
////			// setMenuStatus(MENU_装备);
////			menuIndex[menuIndex_prop]++;
////			if (menuIndex[menuIndex_prop] > 8) {
////				menuIndex[menuIndex_prop] = 8;
////			} else {
////				// indexStrIntro = 0;
////			}
////
////			break;
////		case MyGameCanvas.KEY_LEFT:
////		case MyGameCanvas.KEY_4:
////
////			// setMenuStatus(MENU_阵法);
////			menuIndex[menuIndex_prop]--;
////			if (menuIndex[menuIndex_prop] < 0) {
////				menuIndex[menuIndex_prop] = 0;
////			} else {
////				// indexStrIntro = 0;
////			}
////
////			break;
////
////		case MyGameCanvas.KEY_DOWN:
////		case MyGameCanvas.KEY_8:
////			if (menuIndex[menuIndex_prop] <= 8 - 3) {
////				menuIndex[menuIndex_prop] += 3;
////				// indexStrIntro = 0;
////			}
////
////			break;
////
////		case MyGameCanvas.KEY_UP:
////		case MyGameCanvas.KEY_2:
////			if (menuIndex[menuIndex_prop] > 2) {
////				menuIndex[menuIndex_prop] -= 3;
////				// indexStrIntro = 0;
////			}
////
////			break;
////
////		case MyGameCanvas.KEY_OK:
////		case MyGameCanvas.KEY_5:
////		case MyGameCanvas.KEY_LS:
////
////			if (propLeavelData[menuIndex[menuIndex_prop]] < 20) {
////				if (getMoney() >= getPropMoney()) {
////					addMoney(-getPropMoney());
////					propLeavelData[getMenuIndex()]++;
////				} else {
////					
////					 MyGameCanvas.toStr(new String[] { "您的金钱不足" },
////					 MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////				}
////			} else {
////				MyGameCanvas.toStr(new String[] { "已升级至最高等级" },
////						MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////			}
////
////			// if (propLeavelData[menuIndex[menuIndex_prop]] < 20) {
////			// propLeavelData[menuIndex[menuIndex_prop]]++;
////			// isOver=true;
////			// addToVector(GameMap.setOffX + 27 + menuIndex[menuIndex_prop] % 3
////			// * 39,
////			// GameMap.setOffY + 66 + menuIndex[menuIndex_prop] / 3 * 47,
////			// EFFECT_PROP_UP, false,
////			// menuIndex[menuIndex_prop], 0, 0, 0, 0, effect);
////
////			// }
////			break;
////		case MyGameCanvas.KEY_RS:
////			// indexStrIntro = 0;
////			// setMenuStatus();
////			MyGameCanvas.setST(MyGameCanvas.ST_PLAY);
////			break;
////		}
////
////		// Math.min(menuIndex[menuIndex_prop], 8);
////		// Math.max(menuIndex[menuIndex_prop], 0);
//
//	}
//
//	void drawButton(int x, int y, boolean isUp) {
////		if (isUp) {
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU2, Tools.setOffX + 5,
////					Tools.setOffY + MyGameCanvas.SCREEN_HEIGHT - 5, 0, 96, 42,
////					20, Tools.BOTTOM_LEFT, Tools.TRANS_NONE, 10); // 升级
////		}
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU2, Tools.setOffX
////				+ MyGameCanvas.SCREEN_WIDTH - 5, Tools.setOffY
////				+ MyGameCanvas.SCREEN_HEIGHT - 5, 42, 96, 42, 20,
////				Tools.BOTTOM_RIGHT, Tools.TRANS_NONE, 10); // 返回
//	}
//
//	void drawYesNo(int left, int right) {
////		if (left != -1) {
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU2, Tools.setOffX + 5,
////					Tools.setOffY + MyGameCanvas.SCREEN_HEIGHT - 5, 0, 116, 42,
////					20, Tools.BOTTOM_LEFT, Tools.TRANS_NONE, 2000); // 确定
////		}
////
////		if (right != -1) {
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU2, Tools.setOffX
////					+ MyGameCanvas.SCREEN_WIDTH - 5, Tools.setOffY
////					+ MyGameCanvas.SCREEN_HEIGHT - 5, 42, 96, 42, 20,
////					Tools.BOTTOM_RIGHT, Tools.TRANS_NONE, 2000); // 返回
////
////		}
//	}
//
//	/***************************************** 阵法菜单 *********************************************/
//
//	// final int[][] zhenfaData = {
//	// {
//	// 0, 3, 6, 9, 12, 20}
//	// , { //攻击
//	// 0, 5, 10, 15, 20, 30}
//	// , { //血量
//	// 0, 5, 8, 12, 15, 20}
//	// , { //冷却时间
//	// 0, 3, 6, 9, 12, 20}
//	// , { //金钱
//	// 0, 6, 8, 10, 12, 18}
//	// , //经验
//	// };
//	final int[][] zhenfaData = { { 0, 3, 6, 9, 12, 20 }, { // 攻击
//			0, 5, 10, 15, 20, 30 }, { // 血量
//			0, 5, 8, 12, 15, 20 }, { // 冷却时间
//			0, 3, 6, 9, 12, 20 }, { // 金钱
//			0, 6, 8, 10, 12, 20 }, // 经验
//	};
//
//	static byte[] ZhengfaLeavelData = new byte[] { 1, 0, 0, 0, 0 }; // 阵法等级
//
//	final int[] zhenfaMoney = { 20000, 30000, 40000, 50000, 60000 };
//
//	int setZhengfaEff(int index) {
//		int leavel = ZhengfaLeavelData[index];
//		int kkk = zhenfaData[index][leavel];
//		return kkk;
//	}
//
//	int getZhenfaMoney(int type) { // 类型，等级
//		return zhenfaMoney[type];
//	}
//
////	public void drawZhenFa(int x, int y) {
////		drawMenuBg(x, y, MENU_阵法);
////		int[][] imgData = { { 3, 2, 137, 48 },/* 1 */
////		{ 1, 52, 139, 47 },/* 2 */
////		{ 3, 102, 135, 47 },/* 3 */
////		{ 2, 151, 138, 50 },/* 4 */
////		{ 4, 204, 137, 47 } };/* 5 */
////		drawZhenfaPoint(x - 250, y, getMenuIndex(),
////				ZhengfaLeavelData[getMenuIndex()]);
////		//
////		// drawButton(Tools.setOffX, Tools.setOffY, true);
////		//
////		if (skillRole.curStatus != GameInterface.STATUS_STOP) {
////			skillRole.setStatus(GameInterface.STATUS_STOP);
////		}
////		drawSkillRole(x + 100, y + 30, GameRole.TYPE_ROLE_MENUSKILL);
////		//
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU9, x + 30, y - 130,
////				imgData[getMenuIndex()], Tools.TOP_LEFT, Tools.TRANS_NONE, 501); // 阵法名字
////		//
////		drawZhenfaEff(x + 100, y + 80, getMenuIndex(), 501);
////
////		drawMoney(x - 30, y + 178, money);
////		//
////		// drawMoney(x + 110, y + 230, money);
////
////	}
////
////	public void drawMoney(int x, int y, int money) {
////		GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER2, money, x, y, 14, 0,
////				Tools.TOP_LEFT, 501, 18, 0, true);
////	}
//
////	public void drawZhenfaPoint(int x, int y, int curIndex, int leave) { // 升级所需相关信息
////		int[][] imgLeave = { { 145, 2, 131, 40 },/* 图片说明 */
////		{ 145, 49, 109, 45 },/* 图片说明 */
////		{ 145, 99, 111, 45 },/* 图片说明 */
////		{ 145, 149, 112, 45 },/* 图片说明 */
////		{ 145, 198, 112, 45 },/* 图片说明 */
////		{ 150, 247, 101, 41 } };/* 图片说明 */
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU9, x, y + 57,
////				imgLeave[leave], Tools.TOP_LEFT, Tools.TRANS_NONE, 501); // 阵法等级
////
////		GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER2,
////				zhenfaMoney[getMenuIndex()], x + 40, y - 75, 14, 0,
////				Tools.TOP_LEFT, 501, 18, 0, true);// 升级金钱
////
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU10, x + 50, y - 30, 0,
////				29 * getMenuIndex(), 150, 29, Tools.HCENTER_TOP,
////				Tools.TRANS_NONE, 501); // 阵法介绍
////		//
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU10, x + 58, y + 10, 0,
////				29 * (getMenuIndex() == 2 ? 5 : 6), 168, 29, Tools.HCENTER_TOP,
////				Tools.TRANS_NONE, 501); // 阵法介绍
////		//
////		GameNumber.drawNum_Mid(PAK_IMAGES.IMG_NUMBER3,
////				zhenfaData[getMenuIndex()][leave], x + 35, y + 15, 14, 18, 501,
////				Tools.TOP_LEFT, 0); // 升级前效果
////
////		GameNumber.drawNum_Mid(PAK_IMAGES.IMG_NUMBER2,
////				zhenfaData[getMenuIndex()][leave > 4 ? 5 : leave + 1], x + 95,
////				y + 15, 14, 18, 501, Tools.TOP_LEFT, 0); // 升级后效果
////
////		// GameDraw.drawAddNum_2(PAK_IMAGES.IMG_NUMBER3_PNG,
////		// PAK_IMAGES.IMG_NUMBER3_PNG, goodsLeavelData[curIndex],
////		// maxGoodsLeavel, x - 8, y + 46, 14, 18, 2002,
////		// Tools.HCENTER_VCENTER, 0);
////		//
////		// GameDraw.drawNum_Mid(PAK_IMAGES.IMG_NUMBER1_PNG,
////		// zhenfaData[getMenuIndex()][leave > 4 ? 5 : leave + 1], x + 68,
////		// y + 60, 7, 9, 30, Tools.TOP_LEFT, 9); // 升级后效果
////
////		// drawFinger(x + 41, y - 3, 1);
////		// drawFinger(x + 41, y + 135, 2);
////
////	}
////
////	int index_Zhenfa;
////
////	void drawZhenfaEff(int x, int y, int curIndex, int leave) {
////		byte[] motion = { 0, 1, 2, 2, 3, 3, 4, 4, 5, 5 };
////		int[] imgIndex = { PAK_IMAGES.IMG_ZHENFA1,
////				 };
////
////		GameDraw.renderAnimPic2(imgIndex[curIndex], motion[index_Zhenfa
////				% motion.length], x, y, MyGameCanvas.data_zhenfa, false, false,
////				leave, 0, 0);
////
////		if (++index_Zhenfa >= motion.length) {
////			index_Zhenfa = 0;
////		}
////	}
////
////	public void ctrl阵型(int keyCode) {
////		switch (keyCode) {
////		case MyGameCanvas.KEY_DOWN:
////		case MyGameCanvas.KEY_8:
////			if (getMenuIndex() < 4) {
////				setMenuIndex(menuStatus, getMenuIndex() + 1);
////				// indexStrIntro = 0;
////				// zhenfaIndex = getMenuIndex();
////			} else {
////				setMenuIndex(menuStatus, 0);
////				// indexStrIntro = 0;
////				// zhenfaIndex = getMenuIndex();
////			}
////			break;
////
////		case MyGameCanvas.KEY_UP:
////		case MyGameCanvas.KEY_2:
////			if (getMenuIndex() > 0) {
////				setMenuIndex(menuStatus, getMenuIndex() - 1);
////				// zhenfaIndex = getMenuIndex();
////				// indexStrIntro = 0;
////			} else {
////				setMenuIndex(menuStatus, 4);
////				// zhenfaIndex = getMenuIndex();
////				// indexStrIntro = 0;
////			}
////			break;
////
////		case MyGameCanvas.KEY_OK:
////		case MyGameCanvas.KEY_5:
////		case MyGameCanvas.KEY_LS:
////			if (ZhengfaLeavelData[getMenuIndex()] < 5) {
////				if (getMoney() >= getZhenfaMoney(getMenuIndex())) {
////					addMoney(-getZhenfaMoney(getMenuIndex()));
////					ZhengfaLeavelData[getMenuIndex()]++;
////				} else {
////					 MyGameCanvas.toStr(new String[] { "您的金钱不足" },
////					 MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////		
////				}
////
////			} else {
////				MyGameCanvas.toStr(new String[] { "已升级至最高等级" },
////						MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////			}
////
////			break;
////		case MyGameCanvas.KEY_RS:
////			if (ZhengfaLeavelData[getMenuIndex()] > 0) {
////				zhenfaIndex = getMenuIndex();
////			}
////			// indexStrIntro = 0;
////			// MyGameCanvas.setST(MyGameCanvas.ST_PLAY);
////			// setMenuStatus(MENU_TOTAL);
////			MyGameCanvas.setST(MyGameCanvas.ST_PLAY);
////			effect.removeAllElements();
////			break;
////		}
////		return;
////	}
////
////	public void drawArraw(int x, int y, boolean isLeft) {
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU4, x
////				+ (MyGameCanvas.gameTime / 2 % 2 == 0 ? 0 : (isLeft ? -2 : 2)),
////				y, Tools.BOTTOM_LEFT,
////				isLeft ? Tools.TRANS_NONE : Tools.TRANS_H, 10);
////	}
////
////	/************************************** 升级转盘 **************************************************/
////
////	static int[] upLeavel = { 0, -1, 0, -1, 0, -1, -1, 0, -1, -1, -1, 0, -1, 0,
////			-1, 0, 0, 0, 0, };
////
////	void setAllupLeavel() {
////		for (int i = 0; i < upLeavel.length; i++) {
////			if (upLeavel[i] != -1) {
////				setUpleavelProp(i);
////			} else {
////				// System.out.println("i:" + i);
////			}
////		}
////	}
////
////	void setUpleavelProp(int index) { // 根据升级转盘增加主角属性
////		switch (index) {
////		case 0:
////			GameEngine.role.hp_max += upLeavel[index] * 10;
////			break;
////		case 1:
////			addMoney(500);
////			break;
////		case 2:
////			GameEngine.role.mp_max += upLeavel[index] * 10;
////			break;
////		case 3:
////			GameEngine.role.upleavel(GameEngine.role, 50);
////			break;
////		case 4: // 增加粮食上限
////			GameEngine.role.food_max += upLeavel[index] * 5;
////			break;
////		case 5:
////			GameEngine.role.upleavel(GameEngine.role, 100);
////			break;
////
////		case 6:
////			addMoney(1000);
////			break;
////
////		case 7:
////			GameEngine.role.mp_max += upLeavel[index] * 15;
////			break;
////		case 8:
////			addMoney(1500);
////			break;
////		case 9:
////			GameEngine.role.upleavel(GameEngine.role, 150);
////			break;
////		case 10:
////			addMoney(2000);
////			break;
////		case 11:
////			GameEngine.role.hp_max += upLeavel[index] * 20;
////			break;
////		case 12:
////			GameEngine.role.upleavel(GameEngine.role, 200);
////			break;
////		case 13: // 冷却时间-2%
////
////			// upLeavel[index]++;
////			break;
////		case 14:
////			addMoney(2500);
////			break;
////		case 15:
////			GameEngine.role.hp_max += upLeavel[index] * 30;
////			break;
////		case 16: // 增加粮食上限
////			GameEngine.role.food_max += upLeavel[index] * 10;
////			break;
////		case 17:
////			GameEngine.role.mp_max += upLeavel[index] * 20;
////			break;
////		}
////	}
////
////	void drawUpMenu(int x, int y) {
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU24, x, y, Tools.TOP_LEFT,
////				Tools.TRANS_NONE, 500);
////
////		if (getMenuIndex() < 6) {
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU25, x + getMenuIndex()
////					* 41, y, 127, 444, 41, 43, Tools.TOP_LEFT,
////					Tools.TRANS_NONE, 5000);
////		} else if (getMenuIndex() < 10) {
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU25, x + 5 * 41, y
////					+ (getMenuIndex() - 6) * 41, 127, 444, 41, 43,
////					Tools.TOP_LEFT, Tools.TRANS_NONE, 5000);
////
////		} else if (getMenuIndex() < 15) {
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU25, x
////					+ (15 - getMenuIndex()) * 41, y + (10 - 6) * 41, 127, 444,
////					41, 43, Tools.TOP_LEFT, Tools.TRANS_NONE, 5000);
////		} else if (getMenuIndex() < 19) {
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU25, x + 1, y
////					+ (18 - getMenuIndex()) * 41, 127, 444, 41, 43,
////					Tools.TOP_LEFT, Tools.TRANS_NONE, 5000);
////		}
////
////		int[][] imgData = { { 2, 2, 121, 53 },/* 图片说明 */
////		{ 2, 58, 108, 25 },/* 图片说明 */
////		{ 2, 86, 121, 53 },/* 图片说明 */
////		{ 2, 141, 102, 52 },/* 图片说明 */
////		{ 2, 196, 121, 53 },/* 图片说明 */
////		{ 2, 250, 102, 54 },/* 图片说明 */
////		{ 3, 306, 115, 25 },/* 图片说明 */
////		{ 2, 333, 121, 53 },/* 图片说明 */
////		{ 3, 388, 115, 25 },/* 图片说明 */
////		{ 2, 416, 102, 53 },/* 图片说明 */
////		{ 129, 2, 116, 25 },/* 图片说明 */
////		{ 127, 29, 123, 53 },/* 图片说明 */
////		{ 134, 84, 102, 52 },/* 图片说明 */
////		{ 126, 139, 120, 53 },/* 图片说明 */
////		{ 129, 195, 116, 25 },/* 图片说明 */
////		{ 129, 223, 121, 52 },/* 图片说明 */
////		{ 127, 277, 122, 52 },/* 图片说明 */
////		{ 128, 332, 121, 53 },/* 图片说明 */
////		{ 127, 388, 119, 53 } };/* 图片说明 */
////
////		switch (upIndex) {
////		case 0: // 准备
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU25, x + 125, y + 100,
////					imgData[18], Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 5000);
////
////			break;
////		case 1: // 加速
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU25, x + 125, y + 100,
////					imgData[(getMenuIndex() == 18 ? 0 : getMenuIndex())],
////					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 5000);
////			index++;
////			if (index < 20) {
////				setMenuIndex(MENU_升级转盘, getMenuIndex() + 1);
////			} else if (index < 40) {
////				setMenuIndex(MENU_升级转盘, getMenuIndex() + 2);
////			} else {
////				if (index % 2 == 0) {
////					setMenuIndex(MENU_升级转盘, getMenuIndex() + 2);
////				} else {
////					setMenuIndex(MENU_升级转盘, getMenuIndex() + 3);
////				}
////			}
////			if (getMenuIndex() > 18) {
////				if (index > 40) {
////					setMenuIndex(MENU_升级转盘, 0);
////				} else {
////					setMenuIndex(MENU_升级转盘, getMenuIndex() - 19);
////				}
////			}
////
////			if (index > indexStrIntro) {
////				index = 0;
////				upIndex = 2;
////				indexStrIntro = 0;
////			}
////			break;
////		case 2: // 减速
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU25, x + 125, y + 100,
////					imgData[(getMenuIndex() == 18 ? 0 : getMenuIndex())],
////					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 5000);
////			index++;
////			if (index < 10) {
////				setMenuIndex(MENU_升级转盘, getMenuIndex() + 2);
////			} else if (index < 20) {
////				setMenuIndex(MENU_升级转盘, getMenuIndex() + 1);
////			} else if (index < 30) {
////				if (index % 2 == 0) {
////					setMenuIndex(MENU_升级转盘, getMenuIndex() + 1);
////				}
////			} else if (index < 50) {
////				if (index % 3 == 0) {
////					setMenuIndex(MENU_升级转盘, getMenuIndex() + 1);
////				}
////			}
////
////			else {
////				upIndex = 3;
////				index = 0;
////			}
////			if (getMenuIndex() > 18) {
////				setMenuIndex(MENU_升级转盘, getMenuIndex() - 19);
////			}
////			break;
////
////		case 3: // 停止
////			index++;
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU25, x + 125, y + 100,
////					imgData[(getMenuIndex() == 18 ? 0 : getMenuIndex())],
////					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 5000);
////
////			break;
////		}
////
////	}
////
////	int upIndex = 0;
////
////	public void ctrlUp(int keyCode) {
////
////		switch (keyCode) {
////		case MyGameCanvas.KEY_DOWN:
////		case MyGameCanvas.KEY_8:
////
////			break;
////
////		case MyGameCanvas.KEY_UP:
////		case MyGameCanvas.KEY_2:
////
////			break;
////
////		case MyGameCanvas.KEY_OK:
////		case MyGameCanvas.KEY_5:
////		case MyGameCanvas.KEY_LS:
////		case MyGameCanvas.KEY_RS:
////			switch (upIndex) {
////			case 0:
////				upIndex = 1;
////				index = 0;
////				indexStrIntro = GameRandom.result(20, 50);
////				break;
////			case 1:
////				// if (index < 40) {
////				// upIndex = 2;
////				// index = 10;
////				// }
////				// else {
////				// upIndex = 2;
////				// index = 0;
////				// }
////				break;
////			case 2:
////
////				break;
////			case 3:
////				if (index > 20) {
////					if (upLeavel[getMenuIndex()] != -1) {
////						upLeavel[getMenuIndex()]++;
////					}
////					engine.role.initProp(engine.role.level);
////					index = 0;
////					upIndex = 0;
////					MyGameCanvas.setST(MyGameCanvas.ST_PLAY);
////				}
////				break;
////			}
////
////			break;
////		}
////		return;
////	}
////
////	/************************************************ 大地图 ****************************************************/
////
////	// static String[] mapName = {
////	// "教学关", "魔法小径前", "魔法小径后", "小径深处", // 0-1-2-(3)boss1
////	// "魔法海洋前"
////	// };
////	static byte[][] mapData = {
////			{ 1, 0, 0, 0, 0 } // 0 未开通 1 开通
////			,
////			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////					0, 0 } // 0 未开通 1 1星 2 2星 3 3星
////			,
////			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////					0, 0 },
////			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////					0, 0 },
////			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////					0, 0 },
////			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////					0, 0 } }; // 是否开启
////
////	// byte[] mapData = {
////	// 1, 0, 1, 1, 0}; //是否开启
////
////	boolean mapOver;
////
////	void drawMap(int x, int y) {
////
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU19, x, y, Tools.TOP_LEFT,
////				Tools.TRANS_NONE, 2);
////
////		if (menuStatus == MENU_地图) {
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU20, x
////					+ MyGameCanvas.SCREEN_WIDTH / 2, y + 40, 0,
////					51 * getMenuIndex(), 235, 50, Tools.HCENTER_VCENTER,
////					Tools.TRANS_NONE, 2);
////		}
////		int[][] posXY = { { 99, 94 }, { 317, 92 }, { 208, 178 }, { 418, 179 },
////				{ 119, 267 } };
////
////		int[][] imgData = { { 0, 1, 286, 190 },/* 图片说明 */
////		{ 287, 0, 285, 191 },/* 图片说明 */
////		{ 0, 194, 219, 205 },/* 图片说明 */
////		{ 220, 194, 259, 211 },/* 图片说明 */
////		{ 0, 406, 556, 208 } };/* 图片说明 */
////
////		for (int i = 0; i < mapData[0].length; i++) {
////			if (mapData[0][i] == 1) {
////				GameDraw.add_Image(PAK_IMAGES.IMG_MENU17, x + posXY[i][0],
////						y + posXY[i][1], imgData[i], Tools.TOP_LEFT,
////						Tools.TRANS_NONE, 10);
////			}
////		}
////
////		GameDraw.add_Image(PAK_IMAGES.IMG_FONT3, x
////				+ MyGameCanvas.SCREEN_WIDTH - 20, y
////				+ MyGameCanvas.SCREEN_HEIGHT - 15, 0, 88, 73, 39,
////				Tools.BOTTOM_RIGHT, Tools.TRANS_NONE, 21);
////	}
////
////	public void ctrlMap(int keyCode) {
////		if (isOver) {
////			return;
////		}
////		switch (keyCode) {
////		case MyGameCanvas.KEY_LEFT:
////		case MyGameCanvas.KEY_4:
////		case MyGameCanvas.KEY_UP:
////		case MyGameCanvas.KEY_2:
////			if (getMenuIndex() > 0) {
////				setMenuIndex(menuStatus, getMenuIndex() - 1);
////			} else {
////				setMenuIndex(menuStatus, mapData[0].length - 1);
////			}
////			break;
////		case MyGameCanvas.KEY_RIGHT:
////		case MyGameCanvas.KEY_6:
////		case MyGameCanvas.KEY_DOWN:
////		case MyGameCanvas.KEY_8:
////			if (getMenuIndex() < mapData[0].length - 1) {
////				setMenuIndex(menuStatus, getMenuIndex() + 1);
////			} else {
////				setMenuIndex(menuStatus, 0);
////			}
////			break;
////
////		case MyGameCanvas.KEY_LS:
////		case MyGameCanvas.KEY_OK:
////		case MyGameCanvas.KEY_5:
////			if (mapData[0][getMenuIndex()] == 1) {
////				setMenuStatus(MENU_地图选择);
////			} else {
////				MyGameCanvas.toStr(new String[] { "关卡尚未开通" },
////						MyGameCanvas.ST_MENU_TOTAL, MENU_地图);
////			}
////
////			break;
////		}
////	}
////
////	/*************************************** 小地图选择 ************************************************/
////
////	void drawMapChoose(int x, int y) {
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU29, x, y, Tools.TOP_LEFT,
////				Tools.TRANS_NONE, 11);
////
////		GameDraw.add_Image(PAK_IMAGES.IMG_MENU20, x
////				+ MyGameCanvas.SCREEN_WIDTH / 2, y + 40, 0,
////				51 * getMenuIndex(MENU_地图), 235, 50, Tools.HCENTER_VCENTER,
////				Tools.TRANS_NONE, 2);
////		for (int i = 0; i < mapData[1].length; i++) {
////			drawSmallMapIcon(x + 90 + i % 6 * 110, y + 120 + i / 6 * 80,
////					mapData[1 + getMenuIndex(MENU_地图)][i], i == getMenuIndex(),
////					i + 1, i);
////		}
////
////		GameDraw.add_Image(PAK_IMAGES.IMG_FONT3, x
////				+ MyGameCanvas.SCREEN_WIDTH - 20, y
////				+ MyGameCanvas.SCREEN_HEIGHT - 15, 0, 88, 73, 39,
////				Tools.BOTTOM_RIGHT, Tools.TRANS_NONE, 21);
////	}
////
////	void drawSmallMapIcon(int x, int y, int curStatus, boolean isChoose,
////			int index, int rank) {
////		switch (curStatus) {
////		case 0: // 未开通
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU6, x, y, 0, 127, 84, 59,
////					Tools.TOP_LEFT, Tools.TRANS_NONE, 20);
////			break;
////		case 4: // 3星
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU6, x + 58, y + 35, 0,
////					248, 17, 16, Tools.TOP_LEFT, Tools.TRANS_NONE, 30);
////		case 3: // 2星
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU6, x + 58, y + 20, 0,
////					248, 17, 16, Tools.TOP_LEFT, Tools.TRANS_NONE, 30);
////
////		case 2: // 开通 1星
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU6, x + 58, y + 5, 0, 248,
////					17, 16, Tools.TOP_LEFT, Tools.TRANS_NONE, 30);
////		case 1:
////			if (!isBossRank(rank)) {
////				GameDraw.add_Image(PAK_IMAGES.IMG_MENU6, x, y, 0, 0, 84,
////						59, Tools.TOP_LEFT, Tools.TRANS_NONE, 20);
////				//
////			} else {
////				GameDraw.add_Image(PAK_IMAGES.IMG_MENU6, x, y, 0, 63, 84,
////						59, Tools.TOP_LEFT, Tools.TRANS_NONE, 20);
////
////			}
////			GameNumber.drawNumber(PAK_IMAGES.IMG_NUMBER5, index, x
////					+ (index < 10 ? 18 : 13), y + 42, 18, 1, Tools.BOTTOM_LEFT,
////					20, 24, 0, true);
////			break;
////
////		default:
////			break;
////		}
////		if (isChoose) {
////			GameDraw.add_Image(PAK_IMAGES.IMG_MENU6, x, y, 0, 186, 85, 60,
////					Tools.TOP_LEFT, Tools.TRANS_NONE, 20);
////		}
////
////	}
////
////	boolean isBossRank(int rank) {
////		int bigRank = getMenuIndex(MENU_地图);
////		short[][] enemyData = null;
////
////		switch (bigRank) {
////		case 0:
////			enemyData = engine.getRank0(rank);
////			break;
////		case 1:
////			enemyData = engine.getRank1(rank);
////			break;
////		case 2:
////			enemyData = engine.getRank2(rank);
////			break;
////		case 3:
////			enemyData = engine.getRank3(rank);
////			break;
////		case 4:
////			enemyData = engine.getRank4(rank);
////			break;
////		}
////		if (enemyData.length == 3) {
////			return true;
////		}
////		return false;
////	}
////
////	public void ctrlMapChoose(int keyCode) {
////		if (isOver) {
////			return;
////		}
////		switch (keyCode) {
////		case MyGameCanvas.KEY_LEFT:
////		case MyGameCanvas.KEY_4:
////			if (getMenuIndex() > 0) {
////				setMenuIndex(menuStatus, getMenuIndex() - 1);
////			} else {
////				setMenuIndex(menuStatus, mapData[1].length - 1);
////			}
////			break;
////
////		case MyGameCanvas.KEY_UP:
////		case MyGameCanvas.KEY_2:
////			if (getMenuIndex() > 5) {
////				setMenuIndex(menuStatus, getMenuIndex() - 6);
////			} else {
////				setMenuIndex(menuStatus, getMenuIndex() + 18);
////			}
////			break;
////
////		case MyGameCanvas.KEY_RIGHT:
////		case MyGameCanvas.KEY_6:
////			if (getMenuIndex() < mapData[1].length - 1) {
////				setMenuIndex(menuStatus, getMenuIndex() + 1);
////			} else {
////				setMenuIndex(menuStatus, 0);
////			}
////			break;
////
////		case MyGameCanvas.KEY_DOWN:
////		case MyGameCanvas.KEY_8:
////			if (getMenuIndex() < 3 * 6) {
////				setMenuIndex(menuStatus, getMenuIndex() + 6);
////			} else {
////				setMenuIndex(menuStatus, getMenuIndex() - 18);
////			}
////			break;
////
////		case MyGameCanvas.KEY_LS:
////		case MyGameCanvas.KEY_OK:
////		case MyGameCanvas.KEY_5:
////			if (mapData[1 + getMenuIndex(MENU_地图)][getMenuIndex()] > 0) {
////				MyGameCanvas.setST(MyGameCanvas.ST_LOAD);
////				GameEngine.gameRank = (byte) (getMenuIndex(MENU_地图) * 24 + getMenuIndex());
////			} else {
////				MyGameCanvas.toStr(new String[] { "关卡尚未开通" },
////						MyGameCanvas.ST_MENU_TOTAL, MENU_地图选择);
////			}
////			break;
////
////		case MyGameCanvas.KEY_RS:
////			setMenuStatus(MENU_地图);
////			break;
////		}
////	}
////
////	// *****************************************newGame************************************************/
////	void newGame() {
////		money = 0;
////		GameEngine.role.exp = 0;
////		GameEngine.gameRank = 0;
////		GameEngine.role.level = 1;
////		engine.enemys = null;
////		engine.sprites = null;
////		GameEngine.role.skillIndex = 0;
////		goodsLeavelData = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
////				0, };
////
////		// goodsLeavelData = new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
////		// 1, };
////
////		goodsSkill = new byte[] { -1, -1, 8, -1, -1 }; // 2个物品+3个技能 8
////
////		is_技能 = false;
////		is_装备 = false;
////
////		// mapData = new byte[][] {
////		// {
////		// 1, 0, 0, 0, 0} //0 未开通 1 开通
////		// , {
////		// 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////		// 0} //0 未开通 1 1星 2 2星 3 3星
////		// , {
////		// 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////		// 0}
////		// , {
////		// 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////		// 0}
////		// , {
////		// 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////		// 0}
////		// , {
////		// 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////		// 0}
////		// }; //是否开启
////		mapData = new byte[][] {
////				{ 1, 0, 0, 0, 0 } // 0 未开通 1 开通
////				,
////				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////						0, 0, 0, 0 } // 0 未开通 1 1星 2 2星 3 3星
////				,
////				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////						0, 0, 0, 0 },
////				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////						0, 0, 0, 0 },
////				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////						0, 0, 0, 0 },
////				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
////						0, 0, 0, 0 } }; // 是否开启
////
////		propLeavelData = new byte[] { 1, 0, 0, 0, 0, 0, 0, 0, 0 }; // 兵种等级
////
////		ZhengfaLeavelData = new byte[] { 1, 0, 0, 0, 0 };
////
////	}
////
////	boolean isBack;
////
////	/***************************************** 触摸屏按键 *************************************************/
////
////	// int point_存档 = -1;
////
////	void pointerPressed_MENU_存档(int x, int y) {
////		if (!isTouch) {
////			return;
////		}
////		int[][] pointPos = { { 320, 400, 150, 40 }// 开始游戏
////				, { 125, 400, 150, 40 }// 删除存档
////				, { 555, 400, 70, 40 } // 返回
////				, { 110, 100, 185, 265 } // 存档1
////				, { 315, 100, 185, 265 } // 存档2
////				, { 515, 100, 185, 265 } // 存档3
////		};
////		int point = MyGameCanvas.getPoint(pointPos, x, y);
////		point_total[menuIndex_save] = MyGameCanvas.getPoint(pointPos, x, y);
////		if (point == -1) {
////			return;
////		}
////
////		switch (point) {
////		case 3:
////			ReadID = "1";
////			SaveID = 1;
////			break;
////		}
////	}
////
////	String ReadID;
////	static int SaveID;
////
////	void pointerReleased_MENU_存档(int x, int y) {
////		if (!isTouch) {
////			return;
////		}
////		int[][] pointPos = { { 320, 400, 150, 40 }// 开始游戏
////				, { 125, 400, 150, 40 }// 删除存档
////				, { 555, 400, 70, 40 } // 返回
////				, { 110, 100, 185, 265 } // 存档1
////				, { 315, 100, 185, 265 } // 存档2
////				, { 515, 100, 185, 265 } // 存档3
////		};
////		point_total[menuIndex_save] = -1;
////		int point = MyGameCanvas.getPoint(pointPos, x, y);
////		switch (point) {
////		case 0:
////			if (save[0][(getMenuIndex(MENU_存档) - 3)] > 0) {
////				MyGameCanvas.mySql.ReadData(ReadID);
////				isTouch = false;
////				isBack = false;
////				moveY = 0;
////				setMenuStatus(MENU_地图);
////			} else {
////				newGame();
////				isTouch = false;
////				isBack = false;
////				moveY = 0;
////				setMenuIndex(MENU_存档, -1);
////				setMenuStatus(MENU_地图);
////			}
////			break;
////		case 1:
////			if (save[0][(getMenuIndex(MENU_存档) - 3)] > 0) {
////				MyGameCanvas.mySql.DeleteData(SaveID);
////				save[0][(getMenuIndex(MENU_存档) - 3)] = 0;
////			}
////			break;
////		case 2:
////			isTouch = false;
////			isBack = true;
////			break;
////		case 3:
////			ReadID = "1";
////			SaveID = 1;
////			if (getMenuIndex(MENU_地图) != point) {
////
////				setMenuIndex(MENU_存档, point);
////			} else {
////				setMenuIndex(MENU_存档, point);
////			}
////			break;
////		case 4:
////			ReadID = "2";
////			SaveID = 2;
////			if (getMenuIndex(MENU_地图) != point) {
////				setMenuIndex(MENU_存档, point);
////			} else {
////				setMenuIndex(MENU_存档, point);
////			}
////			break;
////		case 5:
////			ReadID = "3";
////			SaveID = 3;
////			if (getMenuIndex(MENU_地图) != point) {
////				setMenuIndex(MENU_存档, point);
////			} else {
////				setMenuIndex(MENU_存档, point);
////			}
////			break;
////
////		}
////	}
////
////	void pointerPressed_MENU_存档_CHOOSE(int x, int y) {
////		int[][] pointPos = { { 60, 205, 90, 30 } // 开始游戏
////				, { 176, 205, 90, 30 }, { // 删除存档
////				0, 50, 106, 150 } // 存档1
////				, { 106, 50, 100, 150 } // 存档2
////				, { 212, 50, 100, 150 } // 存档3
////
////		};
////		int point = MyGameCanvas.getPoint(pointPos, x, y);
////		if (point == -1) {
////			return;
////		}
////		switch (point) {
////		case 0:
////		case 1:
////			setMenuIndex(MENU_存档_CHOOSE, point);
////			ctrlSaveChoose(MyGameCanvas.KEY_5);
////			break;
////		case 2:
////		case 3:
////		case 4:
////
////			// if (getMenuIndex(MENU_地图) != point) {
////			// setMenuIndex(MENU_存档, point);
////			// }
////			// else {
////			setMenuIndex(MENU_存档, point - 2);
////			// }
////			break;
////
////		}
////	}
////
////	void pointerPressed_MENU_MAP(int x, int y) {
////		int[][] pointPos = { { 169, 115, 145, 111 } // map1
////				, { 379, 89, 145, 111 } // map2
////				, { 260, 237, 145, 111 } // map3
////				, { 481, 228, 145, 111 } // map4
////				, { 301, 363, 145, 111 } // map5
////				, { 710, 425, 74, 40 } // 返回
////		};
////		int point = MyGameCanvas.getPoint(pointPos, x, y);
////		if (point == -1) {
////			return;
////		}
////		switch (point) {
////		case 0:
////		case 1:
////		case 2:
////		case 3:
////		case 4:
////			if (mapData[0][point] == 1) {
////				setMenuIndex(MENU_地图, point);
////				setMenuStatus(MENU_地图选择);
////			} else {
////				MyGameCanvas.toStr(new String[] { "关卡尚未开通" },
////						MyGameCanvas.ST_MENU_TOTAL, MENU_地图);
////			}
////			break;
////		// case 5:
////		// MyGameCanvas.setST(MyGameCanvas.ST_MENU);
////		// break;
////		}
////	}
////
////	void pointerReleased_MENU_MAP(int x, int y) {
////		int[][] pointPos = { { 710, 425, 74, 40 } // 返回
////		};
////		int point = MyGameCanvas.getPoint(pointPos, x, y);
////		if (point == -1) {
////			return;
////		}
////		switch (point) {
////		case 0:
////			MyGameCanvas.setST(MyGameCanvas.ST_MENU);
////			break;
////		}
////	}
////
////	void pointerPressed_MENU_MAP_CHOOSE(int x, int y) {
////		int[][] pointPos = {
////				{ 710, 425, 74, 40 } // 返回
////				, { 88, 119, 87, 60 }, { 199, 119, 87, 60 },
////				{ 310, 119, 87, 60 }, { 420, 119, 87, 60 },
////				{ 530, 119, 87, 60 }, { 638, 119, 87, 60 },
////				{ 88, 199, 87, 60 }, { 199, 199, 87, 60 },
////				{ 310, 199, 87, 60 }, { 420, 199, 87, 60 },
////				{ 530, 199, 87, 60 }, { 638, 199, 87, 60 },
////				{ 88, 279, 87, 60 }, { 199, 279, 87, 60 },
////				{ 310, 279, 87, 60 }, { 420, 279, 87, 60 },
////				{ 530, 279, 87, 60 }, { 638, 279, 87, 60 },
////				{ 88, 359, 87, 60 }, { 199, 359, 87, 60 },
////				{ 310, 359, 87, 60 }, { 420, 359, 87, 60 },
////				{ 530, 359, 87, 60 }, { 638, 359, 87, 60 },// 小关的坐标
////
////		};
////		int point = MyGameCanvas.getPoint(pointPos, x, y);
////		if (point == -1) {
////			return;
////		}
////
////		switch (point) {
////		case 0:
////			// ctrl(MyGameCanvas.KEY_RS);
////			break;
////		default:
////			if (getMenuIndex(menuStatus) != point - 1) {
////				setMenuIndex(menuStatus, point - 1);
////			} else {
////				ctrl(MyGameCanvas.KEY_5);
////			}
////			break;
////		}
////	}
////
////	void pointerReleased_MENU_MAP_CHOOSE(int x, int y) {
////		int[][] pointPos = { { 710, 425, 74, 40 } // 返回
////		};
////		int point = MyGameCanvas.getPoint(pointPos, x, y);
////		if (point == -1) {
////			return;
////		}
////		switch (point) {
////		case 0:
////			ctrl(MyGameCanvas.KEY_RS);
////			break;
////		}
////	}
////
////	void pointerPressed_MENU_PROP(int x, int y) {
////		int[][] pointPos = { { 110, 105, 55, 50 }, { 175, 105, 55, 50 },
////				{ 240, 105, 55, 50 }, { 110, 200, 55, 50 },
////				{ 175, 200, 55, 50 }, { 240, 200, 55, 50 },
////				{ 110, 295, 55, 50 }, { 175, 295, 55, 50 },
////				{ 240, 295, 55, 50 }, { 325, 25, 160, 50 }// 装备
////				, { 510, 25, 160, 50 }// 阵法
////				, { 555, 235, 90, 40 }// 升级
////				, { 595, 400, 75, 40 } // 返回
////		};
////		int point = MyGameCanvas.getPoint(pointPos, x, y);
////		if (point == -1) {
////			return;
////		}
////		switch (point) {
////		case 0:
////		case 1:
////		case 2:
////		case 3:
////		case 4:
////		case 5:
////		case 6:
////		case 7:
////		case 8:
////			setMenuIndex(MENU_兵营, point);
////			break;
////		case 9:
////			setMenuStatus(MENU_装备);
////			break;
////		case 10:
////			setMenuStatus(MENU_阵法);
////			break;
////		case 11:
////			point_total[menuIndex_prop] = 2;
////			break;
////		case 12:
////			point_total[menuIndex_prop] = 1;
////			break;
////		}
////	}
////
////	void pointerReleased_MENU_PROP(int x, int y) {
////		int[][] pointPos = { { 595, 400, 75, 40 } // 返回
////				, { 555, 235, 90, 40 } // 升级
////		};
////		int point = MyGameCanvas.getPoint(pointPos, x, y);
////		point_total[menuIndex_prop] = -1;
////		switch (point) {
////		case 0:
////			// isBack = true;
////			// isTouch = false;
////			ctrl(MyGameCanvas.KEY_RS);
////			break;
////		case 1:
////			ctrl(MyGameCanvas.KEY_LS);
////			break;
////		}
////	}
////
////	void pointerPressed_MENU_TATOL(int x, int y) {
////		int[][] pointPos = { { 132 + a, 30 + b, 55, 40 },
////				{ 131 + a, 75 + b, 55, 40 }, { 132 + a, 115 + b, 55, 40 },
////				{ 490, 290, 40, 30 } // 返回 4
////		};
////		int point = MyGameCanvas.getPoint(pointPos, x, y);
////		if (point == -1) {
////			return;
////		}
////		switch (point) {
////		case 0:
////		case 1:
////		case 2:
////			setMenuIndex(MENU_TOTAL, point);
////			ctrl(MyGameCanvas.KEY_5);
////			break;
////
////		case 3:
////			ctrl(MyGameCanvas.KEY_RS);
////			break;
////		}
////	}
////
////	void pointerPressed_MENU_装备(int x, int y) {
////		int[][] pointPos = { { 117, 114, 58, 59 }, { 187, 114, 58, 59 },
////				{ 260, 114, 58, 59 }, { 332, 114, 58, 59 },
////				{ 403, 114, 58, 59 }, { 476, 114, 58, 59 },
////				{ 548, 114, 58, 59 }, { 620, 114, 58, 59 },
////				{ 125, 209, 66, 66 }, { 218, 209, 66, 66 },
////				{ 315, 209, 66, 66 }, { 412, 209, 66, 66 },
////				{ 509, 209, 66, 66 }, { 606, 209, 66, 66 },
////				{ 271, 380, 56, 59 }, { 340, 380, 56, 59 },
////				{ 407, 376, 62, 64 }, { 481, 376, 62, 64 },
////				{ 552, 376, 62, 64 }, { 134, 360, 115, 41 },// 升级
////				{ 120, 25, 160, 50 }// 兵营
////				, { 510, 25, 160, 50 }// 阵法
////				, { 595, 400, 75, 40 } // 返回
////		};
////		int point = MyGameCanvas.getPoint(pointPos, x, y);
////		if (point == -1) {
////			return;
////		}
////		switch (point) {
////		case 0:
////		case 1:
////		case 2:
////		case 3:
////		case 4:
////		case 5:
////		case 6:
////		case 7:
////		case 8:
////		case 9:
////		case 10:
////		case 11:
////		case 12:
////		case 13:
////			setMenuIndex(MENU_装备, point);
////			break;
////		case 14:
////		case 15:
////			if (getMenuIndex(MENU_装备) < 8) {
////				if (goodsLeavelData[getMenuIndex(MENU_装备)] == 0) {
////
////					MyGameCanvas.toStr(new String[] { "未开启" },
////							MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////				} else {
////					if (isGoodsEquipHave(getMenuIndex(MENU_装备))) {
////						MyGameCanvas.toStr(new String[] { "该物品不能重复装备" },
////								MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////					} else {
////
////						MyGameCanvas.toStr(new String[] { "物品装备成功" },
////								MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////						goodsSkill[point - 14] = (byte) getMenuIndex(MENU_装备);
////					}
////				}
////			}
////			break;
////		case 16:
////		case 17:
////		case 18:
////			if (getMenuIndex(MENU_装备) >= 8) {
////				if (goodsLeavelData[getMenuIndex(MENU_装备)] == 0) {
////					MyGameCanvas.toStr(new String[] { "未开启" },
////							MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////				} else {
////					if (isGoodsEquipHave(getMenuIndex(MENU_装备))) {
////						MyGameCanvas.toStr(new String[] { "该技能不能重复装备" },
////								MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////					} else {
////						MyGameCanvas.toStr(new String[] { "技能装备成功" },
////								MyGameCanvas.ST_MENU_TOTAL, menuStatus);
////						goodsSkill[point - 14] = (byte) getMenuIndex(MENU_装备);
////					}
////				}
////			}
////			break;
////		case 19:
////			point_total[menuIndex_equip] = 2;
////			break;
////		case 20:
////			setMenuStatus(MENU_兵营);
////			break;
////		case 21:
////			setMenuStatus(MENU_阵法);
////			break;
////		case 22:
////			point_total[menuIndex_equip] = 1;
////			break;
////		}
////	}
//
////	void pointerReleased_MENU_装备(int x, int y) {
////		int[][] pointPos = { { 595, 400, 75, 40 } // 返回
////				, { 134, 360, 115, 41 } // 升级
////		};
////		int point = MyGameCanvas.getPoint(pointPos, x, y);
////		point_total[menuIndex_equip] = -1;
////		switch (point) {
////		case 0:
////			// isBack = true;
////			// isTouch = false;
////			ctrl(MyGameCanvas.KEY_RS);
////			break;
////		case 1:
////			menuGoodsChoosePressOK();
////			break;
////		}
////	}
//
////	void pointerPressed_背包_CHOOSE(int x, int y) {
////		int[][] pointPos = { { 129 + a, 116 - 40, 66, 28 }, { // 升级
////				126 + a, 148 - 40, 66, 28 }, { // 装备至3
////				127 + a, 186 - 40, 66, 28 }, { // 装备至9
////				126 + a, 220 - 40, 66, 28 }, // 取消
////		};
////		if (getMenuIndex(MENU_装备) > 7) {
////			pointPos = new int[][] { { 126 + a, 116 - 40, 67, 21 },
////					{ 125 + a, 142 - 40, 67, 21 },
////					{ 124 + a, 168 - 40, 67, 21 },
////					{ 124 + a, 197 - 40, 67, 21 },
////					{ 125 + a, 222 - 40, 67, 21 }, };
////		}
////		int point = MyGameCanvas.getPoint(pointPos, x, y);
////		if (point == -1) {
////			return;
////		}
////		switch (point) {
////		default:
////			setMenuIndex(MENU_背包_CHOOSE, point);
////			ctrl(MyGameCanvas.KEY_LS);
////			break;
////		}
////	}
//
//	void pointerPressed_阵法(int x, int y) {
//		int[][] pointPos = { { 326, 155, 74, 65 }, { 602, 155, 74, 65 },
//				{ 120, 25, 160, 50 }// 兵营
//				, { 325, 25, 160, 50 }// 装备
//				, { 155, 400, 91, 48 }// 升级
//				, { 595, 400, 75, 40 } // 返回
//		};
//		// { 19 + a, 165 + b, 90, 26 }, // 切换阵法
//		//
//		// { 0, 290, 40, 40 }// 升级 9
//		// , { 490, 290, 40, 40 } // 返回 10
//		// };
//		int point = MyGameCanvas.getPoint(pointPos, x, y);
//		if (point == -1) {
//			return;
//		}
//		switch (point) {
//		case 0:
//			point_total[menuIndex_zhenfa] = 3;
//			break;
//		case 1:
//			point_total[menuIndex_zhenfa] = 4;
//			break;
//		case 2:
//			setMenuStatus(MENU_兵营);
//			break;
//		case 3:
//			setMenuStatus(MENU_装备);
//			break;
//		case 4:
//			point_total[menuIndex_zhenfa] = 2;
//			break;
//		case 5:
//			point_total[menuIndex_zhenfa] = 1;
//			break;
//		// case 1:
//		// ctrl(MyGameCanvas.KEY_LS);
//		// break;
//		// case 2:
//		// ctrl(MyGameCanvas.KEY_RS);
//		// break;
//		//
//		}
//	}
//
////	void pointerReleased_阵法(int x, int y) {
////		int[][] pointPos = { { 326, 155, 74, 65 }, { 602, 155, 74, 65 },
////				{ 155, 400, 91, 48 }// 升级
////				, { 595, 400, 75, 40 } // 返回
////		};
////		int point = MyGameCanvas.getPoint(pointPos, x, y);
////		point_total[menuIndex_zhenfa] = -1;
////		switch (point) {
////		case 0:
////			if (getMenuIndex() < 4) {
////				setMenuIndex(menuStatus, getMenuIndex() + 1);
////				index_Zhenfa = 0;
////				zhenfaIndex = getMenuIndex();
////			} else {
////				setMenuIndex(menuStatus, 0);
////				index_Zhenfa = 0;
////				zhenfaIndex = getMenuIndex();
////			}
////			break;
////		case 1:
////			if (getMenuIndex() > 0) {
////				setMenuIndex(menuStatus, getMenuIndex() - 1);
////				zhenfaIndex = getMenuIndex();
////				index_Zhenfa = 0;
////			} else {
////				setMenuIndex(menuStatus, 4);
////				zhenfaIndex = getMenuIndex();
////				index_Zhenfa = 0;
////			}
////			break;
////		case 2:
////			ctrl(MyGameCanvas.KEY_LS);
////			break;
////		case 3:
////			ctrl(MyGameCanvas.KEY_RS);
////			break;
////		}
////	}
//
////	void pointerPressed_升级转盘(int x, int y) {
////		int[][] pointPos = { { 0, 0, 480, 400 } // 点击开始
////		};
////		int point = MyGameCanvas.getPoint(pointPos, x, y);
////		if (point == -1) {
////			return;
////		}
////		switch (point) {
////		case 0:
////			ctrl(MyGameCanvas.KEY_5);
////			break;
////		}
////	}
////
////	public void pointerPressed_TotalMenu(int x, int y) {
////		switch (menuStatus) {
////		case MENU_升级转盘:
////			pointerPressed_升级转盘(x, y);
////			break;
////		case MENU_存档:
////			pointerPressed_MENU_存档(x, y);
////			break;
////		case MENU_存档_CHOOSE:
////			pointerPressed_MENU_存档_CHOOSE(x, y);
////			break;
////
////		case MENU_地图:
////			pointerPressed_MENU_MAP(x, y);
////			break;
////		case MENU_地图选择:
////			pointerPressed_MENU_MAP_CHOOSE(x, y);
////			break;
////		case MENU_兵营:
////			pointerPressed_MENU_PROP(x, y);
////			break;
////		case MENU_TOTAL:
////			pointerPressed_MENU_TATOL(x, y);
////			break;
////
////		case MENU_装备:
////			pointerPressed_MENU_装备(x, y);
////			break;
////		case MENU_背包_CHOOSE:
////			pointerPressed_背包_CHOOSE(x, y);
////			break;
////		case MENU_阵法:
////			pointerPressed_阵法(x, y);
////			break;
////
////		}
////	}
//
////	public void pointerReleased_TotalMenu(int x, int y) {
////		switch (menuStatus) {
////		case MENU_存档:
////			pointerReleased_MENU_存档(x, y);
////			break;
////		case MENU_兵营:
////			pointerReleased_MENU_PROP(x, y);
////			break;
////		case MENU_装备:
////			pointerReleased_MENU_装备(x, y);
////			break;
////		case MENU_阵法:
////			pointerReleased_阵法(x, y);
////			break;
////		case MENU_地图:
////			pointerReleased_MENU_MAP(x, y);
////			break;
////		case MENU_地图选择:
////			pointerReleased_MENU_MAP_CHOOSE(x, y);
////			break;
////		}
////	}
//
//}
