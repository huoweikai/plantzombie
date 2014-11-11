package com.haopu.JSGame;

import com.haopu.kbz.*;

import java.util.Vector;

import android.graphics.Bitmap;

//import com.haopu.*;

public class GameMap extends GameMapTile {
	static final int TILE_PASS = 0;
	static int SCREEN_WIDTH; // 屏幕宽度
	static int SCREEN_HEIGHT; // 屏幕高度

	byte gameRank; // 当前关数

	short[][] zhuangshiData;
	static int mapCentreSpeed = 4;//地图中景的速度
    static int mapCentreSpeed0 = 0;
	// 位置数组

	public void free() {

		System.gc();
	}

	GameEngine engine;

	public GameMap(int sw, int sh, GameEngine ge) {
		SCREEN_WIDTH = sw;
		SCREEN_HEIGHT = sh;
		setOff(0, 0);
		engine = ge;
		// initMapBuff();
	}

	public static void setOff(int x, int y) {
		Tools.setOffX = x;
		Tools.setOffY = y;
	}

	public void init(int GameRank) {
		this.gameRank = (byte) GameRank;
		// System.out.println("engine.gameRank" + GameEngine.gameRank);
		free();

		initDecoration(); // 装饰
//		arrawData = null; // 传送点
		initPassArraw();

		initMapTile();

		Tools.setOffX = GameEngine.role.x - posX;
		Tools.setOffX = (short) Math.max(Tools.setOffX, 0);
//		Tools.setOffX = (short) Math.min(Tools.setOffX, mapSize[0] * tileWidth
//				- SCREEN_WIDTH);
		
		Tools.setOffY = 0;
//		initSrceen(0,0,false);//上来瞬间根据任务坐标，调整镜头，追踪人物
		

		
		
	
//		astar = new AStar();
//		astar_mapData = new int[mapSize[0]][mapSize[1]];
//		for (int i = 0; i < astar_mapData.length; i++) {
//			for (int j = 0; j < astar_mapData[i].length; j++) {
//				astar_mapData[i][j] = mapData[0][i];
//			}
//		}
//		astar.setMap(astar_mapData);
	}

//	AStar astar;
//	int[][] astar_mapData;

	public void initSrceen(int x, int y, boolean isLeft) {

		// int posX = isLeft ? 100 : 75;
		// int posY = 116;

		Tools.setOffX = (short) (x - posX);

		Tools.setOffX = (short) Math.max(Tools.setOffX, 0);
		Tools.setOffX = (short) Math.min(Tools.setOffX, mapSize[0] * tileWidth
				- SCREEN_WIDTH);

		Tools.setOffY = (short) (y - posY);

		Tools.setOffY = (short) Math.max(Tools.setOffY, 0);
		Tools.setOffY = (short) Math.min(Tools.setOffY, mapSize[1] * tileHight
				- SCREEN_HEIGHT);

	}

	public short[] circumgyrate(int startX, int startY, int bian, //
			int x, int y, boolean trans, int speed) {
		// 左定点x,左定点y,直径,物体x,物体y,正反转,速度 敌人初始面向,多少帧一圈
		// Tools.addObject(Tools.TYPE_ARC,x,y,);

		boolean isLeft, isUp;
		short[] a = { -1, -1 };
		int x0 = x - startX - bian / 2, y0 = y - startY - bian / 2;

		int y1 = y0, x1 = x0;
		if (y1 > -bian * 7 / 20 && y1 < bian * 7 / 20) {
			isUp = x1 < 0;
			x1 = sqrt(bian * bian / 4 - y1 * y1);
			x1 = isUp ? -x1 : x1;
			while ((y1 - y0) * (y1 - y0) + (x1 - x0) * (x1 - x0) < speed
					* speed) {
				if (y1 >= bian / 2) {
					isUp = true;
					y1 = bian / 2;
				}
				if (y1 <= -bian / 2) {
					isUp = false;
					x = -bian / 2;
				}
				y1 += isUp == trans ? -1 : 1;
				x1 = sqrt(bian * bian / 4 - y1 * y1);
				x1 = isUp ? -x1 : x1;
			}
		} else {
			isLeft = y1 > 0;
			if (x1 >= bian / 2) {
				isLeft = true;
				x1 = bian / 2;
			}
			if (x1 <= -bian / 2) {
				isLeft = false;
				x = -bian / 2;
			}
			y1 = sqrt(bian * bian / 4 - x1 * x1);
			y1 = isLeft ? y1 : -y1;
			// 怪物坐标X,Y, isleft,isUp,怪物状态curStatus,当前curIndex,当前index
			while ((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0) < speed
					* speed) {
				if (x1 >= bian / 2) {
					isLeft = true;
					x1 = bian / 2;
				}
				if (x1 <= -bian / 2) {
					isLeft = false;
					x = -bian / 2;
				}

				x1 += isLeft == trans ? -1 : 1;
				y1 = sqrt(bian * bian / 4 - x1 * x1);
				y1 = isLeft ? y1 : -y1;
			}
		}
		a[0] = (short) (x1 + startX + bian / 2);
		a[1] = (short) (y1 + startY + bian / 2);
		return a;
	}

	public final static int sqrt(int v) {
		if (v > 0) {
			v *= 10000;
			int r = 10000;
			int c;
			do {
				c = r;
				r = v / r + r >> 1;
			} while (r < c);
			c = c / 100;
			return c;
		} else {
			return 0;
		}
	}

	static int startPos = 0;

	public final static void drawFullScreenBG3(int imgIndex, int y, int speed,
			int level) {
	//	Bitmap img = Tools.getImage(imgIndex);
		int imgW = 800;
		startPos -= speed;

		if (startPos <= -imgW) {
			startPos += imgW;
		}

		// Tools.addObject(imgIndex,
		// startPos + setOffX, y,
		// Graphics.BOTTOM | Graphics.LEFT, Tools.TRANS_NONE, level);
		for (int i = 0; i < 5; i++) {
			GameDraw.add_Image(imgIndex, startPos + i * imgW + Tools.setOffX,
					y, 0,0,800,480,Tools.TOP_LEFT, Tools.TRANS_NONE, level);
		}

		// for (int i = 0; i < 10; i++) {
		// Tools.addObject(imgIndex,
		// startPos + i * imgW + setOffX, height, 0, 0, imgW, imgH,
		// Graphics.BOTTOM | Graphics.LEFT, Tools.TRANS_NONE, level);
		// }
	}

	// 画地图

	public void setMap(boolean isDraw, boolean isFG) {
		setTile(0);//画路面
		if(MyActivity.VMWidth>320){setTile(1);}
	    
	}

	public static void drawFullScreenBG_4(int imgIndex, int x, int speed,
			int level, boolean isTransNone) {
		Bitmap img = Tools.getImage(imgIndex);
		int imgH = img.getHeight();
		int times = (SCREEN_HEIGHT / imgH) * 2 + 4;
		int startPos = (MyGameCanvas.gameTime) % (SCREEN_HEIGHT / speed)
				* speed;
		if (startPos >= SCREEN_HEIGHT) {
			startPos = Tools.setOffY;
		}
		for (int i = 0; i < times; i++) {
			GameDraw.add_Image(imgIndex, x, Tools.setOffY + startPos - i * imgH
					+ SCREEN_HEIGHT, Tools.TOP_LEFT, Tools.TRANS_NONE, level);
		}
	}

	public void setMapFG() {
		// if (!isStop && !engine.isSctrpe
		// && MyGameCanvas.gameStatus == MyGameCanvas.ST_PLAY) {
		//
		// }
//		if (gameRank < 24) {
//			drawLoopImage(PAK_IMAGES.IMG_B1, 0, 10, true);
//		} else if (gameRank < 24 * 2) {
//			drawLoopImage(PAK_IMAGES.IMG_B2, 0, 10, true);
//		}

		switch (gameRank) {
		case 0:
		case 1:
		case 2:
		case 7:
		case 8:
		case 9:

			// GameDraw.addObject(PAK_IMAGES.IMG_S3_PNG,
			// GameMap.setOffX,
			// GameMap.setOffY + 80,
			// Tools.TOP_LEFT,
			// Tools.TRANS_NONE, 10);
			// GameDraw.addObject_alpha(Tools.TYPE_ALPHA_IMG,
			// PAK_IMAGES.IMG_MAP1_PNG,
			// GameMap.setOffX + 80,
			// GameMap.setOffY + 20,0,0,100,100,
			// 255, 2, Tools.TOP_LEFT,
			// Tools.TRANS_NONE, 10);

			// drawFullScreenBG(PAK_IMAGES.IMG_BG1_PNG, 0, 50, 1, false);

			// drawLoopImage(PAK_IMAGES.IMG_BG1_PNG, 0, 10, true);
			break;
		case 3:
		case 4:
		case 5:
		case 6:

			// drawFullScreenBG(PAK_IMAGES.IMG_BG1_PNG, 0, 50, 1, false);
			break;
		}
	}

	/**
	 * 绘制有偏移的切片装饰图片
	 * 
	 * @param type
	 *            int
	 * @param index
	 *            int
	 * @param imgxy
	 *            int[]
	 * @param movex
	 *            int
	 * @param movey
	 *            int
	 */
	void draw_zhuangshi_cityimg(int type, int index, int[] imgxy, int movex,
			int movey, int lev) {
		GameDraw.add_Image(type, zhuangshiData[index][1] + movex,
				zhuangshiData[index][2] + movey, imgxy, Tools.BOTTOM_LEFT,
				(byte) zhuangshiData[index][3], 10 + lev);
	}

public	static int shakeTime = 0;

	public static void screenShake() {
		if (shakeTime > 0) {
			shakeTime--;
			if(MyGameCanvas.gameStatus==MyGameCanvas.GmStat_Playing){
			Tools.setOffY = GameEngine.time % 2 == 0 ? (short) (Tools.setOffY + 2)
					: (short) (Tools.setOffY - 2);
//			Tools.setOffX = GameEngine.time % 2 == 0 ? (short) (Tools.setOffY + 2)
//					: (short) (Tools.setOffY - 2);
			}
		}else{
			if(Tools.setOffX!=0||Tools.setOffY!=0){
			Tools.setOffX =0;
			Tools.setOffY =0;
			}

		}

		// setOffY = (short) Math.min(setOffY, mapSize[1] * tileHight
		// - SCREEN_HEIGHT);
		// setOffY = (short) Math.max(setOffY, 0);

	}

	// ******************
	// 修正屏幕坐标
	// ******************

	int posX = MyGameCanvas.SCREEN_WIDTH / 2;
	int posY = MyGameCanvas.SCREEN_HEIGHT / 3;

	public void initY() {
		// Tools.setOffY = mapSize[1] * 24 - MyGameCanvas.SCREEN_HEIGHT;
		// System.out.println("setOffY=" + Tools.setOffY);
	}

	public static final byte SCEEN_MOVE = 40;

	public void AdjustSrceen(int x, int y, boolean isLeft) {
//		int posX = MyGameCanvas.SCREEN_WIDTH / 3 - 100;
//		if(x - Tools.setOffX > posX){//地图移动
//			Tools.setOffX = x- posX;
//		}
//		if((Tools.setOffX)>=(mapWidth-2* Tools.SCREEN_WIDTH)){//地图不移动
//
//			Tools.setOffX = mapWidth-2* Tools.SCREEN_WIDTH;
//		}
//		if((x+80)>mapWidth - SCREEN_WIDTH){//胜利了换到最后一幅地图
//			Tools.setOffX =mapWidth - Tools.SCREEN_WIDTH;
//		}
		
		
//		int posX = MyGameCanvas.SCREEN_WIDTH / 2-200;
//		int posY = MyGameCanvas.SCREEN_HEIGHT / 2;
		int posX = 0;
		int posY = 0;
		if (engine.isCg) {
			return;
		}
		if (Math.abs(x - (Tools.setOffX + posX)) <= SCEEN_MOVE) {
			Tools.setOffX = x - (posX);
		} else {
			if (x - Tools.setOffX > posX) {
				Tools.setOffX += SCEEN_MOVE;
				
			}
			if (x - Tools.setOffX < posX) {
				Tools.setOffX -= SCEEN_MOVE;
			}
		}

		Tools.setOffX = (short) Math.max(Tools.setOffX, 0);

		Tools.setOffX = (short) Math
				.min(Tools.setOffX, mapWidth - SCREEN_WIDTH);

		switch (GameEngine.me.gameRank) {
		default:
			if (Math.abs(y - Tools.setOffY - posY) > SCEEN_MOVE) {
				if (y - Tools.setOffY - posY >= SCEEN_MOVE) {
					Tools.setOffY += SCEEN_MOVE;
				} else {
					Tools.setOffY -= SCEEN_MOVE;
				}
			} else {
				Tools.setOffY = (short) (y - posY);
			}
			break;
		}

		Tools.setOffY = (short) Math.min(Tools.setOffY, mapHight
				- SCREEN_HEIGHT);
		
		Tools.setOffY = (short) Math.max(Tools.setOffY, 0);

	
	}

	public static void drawFullScreenBG(int imgIndex, int y, int speed,
			int level, boolean isTransNone) {
		int imgW = Tools.getImage(imgIndex).getWidth();
		// int imgH = Tools.getImage(imgIndex).getHeight();
		int times = (SCREEN_WIDTH / imgW) + 2;
		int startPos = (Tools.setOffX * speed * 10) / 800;
		while (Tools.setOffX - startPos >= imgW) {
			startPos += imgW;
		}
		for (int i = 0; i < times; i++) {
			GameDraw.add_Image(imgIndex, startPos + i * imgW, y,
					Tools.TOP_LEFT, isTransNone ? Tools.TRANS_NONE
							: Tools.TRANS_H, level);
		}
	}

	public void drawLoopImage(int imgIndex, int y, int level,
			boolean isTransNone) {
		int imgW = Tools.getImage(imgIndex).getWidth();
		int loop = (mapWidth / imgW) + 2;
		for (int i = 0; i < loop; i++) {
			GameDraw.add_Image(imgIndex, 0 + imgW * i, y, Tools.TOP_LEFT,
					isTransNone ? Tools.TRANS_NONE : Tools.TRANS_H, level);

		}
	}

	public void drawJianTou(int x, int y, boolean isT) {

	}

	// /////////////装饰////////////////////////////////////////////////////////////
//	short[][] singleData = null; // 单独物体 x,y,翻转

	public void initDecoration() {

	}

	public void drawSingThings(short[][] array) {

	}

	// //////////////////传送阵////////////////////////////////////////

//	short[][] arrawData; // 过关 0 x, 1 y,2 nextRank 下一关是哪关,3 nextX 下一关x ,4

	public void drawPassArraw(short[][] array) {

	}

	short nextRank;
//	int[] coxRole = null;

	public void checkArraw(short[][] array) {

	}

	public void initPassArraw() {

	}
	
	
	
	
	
	
	

}
