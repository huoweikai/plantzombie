/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Bbmf</p>
 * @author not attributable
 * @version 1.0
 */
//package com.haopu;

//import javax.microedition.lcdui.*;
//import java.util.*;
//import java.io.*;

package com.haopu.kbz;

public class GameHit {
	public GameHit() {
	}

	/**
	 * 矩形碰撞锚点为左底点
	 */
	public static boolean hit(int x1, int y1, int w1, int h1, int x2, int y2,
			int w2, int h2) {
		y1 = y1 - h1;
		y2 = y2 - h2;
		return !(x1 > x2 + w2 || x2 > x1 + w1 || y1 > y2 + h2 || y2 > y1 + h1);
	}

	/**
	 * 人物攻击框与矩形碰撞锚点为左底点
	 */

	public static boolean hit2(GameInterface role, int x, int y, int w, int h) {
		if (role.attackBox == null) {
			return false;
		}
		if (role.attackBox[2] == 0) {
			return false;
		}
		if (hit(role.sx + role.attackBox[0], role.sy + role.attackBox[1],
				role.attackBox[2], role.attackBox[3], x, y, w, h)) {
			return true;
		}

		return false;
	}

	/**
	 * 矩形与人物被攻击框的碰撞锚点为左底点
	 */
	public static boolean hit2(int x, int y, int w, int h, GameInterface role) {
		if (role.coxBox == null) {
			return false;
		}
		if (role.coxBox[2] == 0) {
			return false;
		}
		if (hit(role.sx + role.attackBox[0], role.sy + role.attackBox[1],
				role.attackBox[2], role.attackBox[3], x, y, w, h)) {
			return true;
		}

		return false;
	}

	/**
	 * 人物与敌人的碰撞锚点为左底点
	 */
	public final static boolean hit_box(GameInterface role, GameInterface enemy) {
		if (role.attackBox == null) {
			return false;
		}
		if (role.attackBox[2] == 0) {
			return false;
		}
		if (enemy.coxBox == null) {
			return false;
		}

		if (enemy.coxBox[2] == 0) {
			return false;
		}

		if (hit(role.sx + role.attackBox[0], role.sy + role.attackBox[1],
				role.attackBox[2], role.attackBox[3], enemy.sx
						+ enemy.coxBox[0], enemy.sy + enemy.coxBox[1],
				enemy.coxBox[2], enemy.coxBox[3])) {
			return true;
		}
		return false;
	}
	
	/**
	 * 子弹与精灵的碰撞
	 * 
	 * @param imgIndex1 图片索引
	 * 
	 * @param x1 x坐标
	 * 
	 * @param y1 y坐标
	 * 
	 * @param data1 子弹的data数据
	 * 
	 * @param curIndex1 子弹的帧数
	 * 
	 * @param isLeft1 子弹的方向
	 * 
	 * @param sprite 被攻击的主角
	 */
	public final boolean hit_box_shot(int x1, int y1, short[][] data1,
			int curIndex1, boolean isLeft1, GameInterface sprite) {
		int[] attackBox = GameInterface.hitArea(data1[1], curIndex1, true,
				!isLeft1);
		int[] coxBox = GameInterface.hitArea(sprite.data[1], sprite.curIndex,
				false, !sprite.isLeft);
		if (attackBox[2] == 0) {
			return false;
		}
		if (hit(x1 + attackBox[0], y1 + attackBox[1], attackBox[2],
				attackBox[3], sprite.sx + coxBox[0], sprite.sy + coxBox[1],
				coxBox[2], coxBox[3])) {
			return true;
		}
		return false;
	}
/***
 * 矩形与矩形的碰撞检测
 * x1,y1,w1,h1
 * x2,y2,w2,h2
 * 
 */
	public final static boolean rectTorectCol(int x1,int y1,int w1,int h1,int x2,int y2,int w2,int h2){
		if((x1<x2+w2) && (x2<x1+w1) && (y1<y2+h2) && (y2<y1+h1)){
			return true;
		}
		return false;
	}

}

