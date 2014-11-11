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

import java.util.Random;




public class GameRandom {
	public GameRandom() {
	}
	
	static Random rnd = new Random();
	
	
	/**
	 * 是否满足几率条件

	 * 
	 */
	public final static  boolean isSuccess(int rate) { // 是否满足几率条件
		if (rate > 99) {
			rate = 99;
		}
		if (rate < 1) {
			rate = 1;
		}
		return (rnd.nextInt() >>> 1) % 100 < rate;
	}

	/**
	 *随机数，最大为r-1，最小为0

	 * 
	 */
	public final static int result(int r) {
		int a = (rnd.nextInt() >>> 1) % r;
		return a;
	}

	
	/**
	 *求min和max之间的随机数
	 * 
	 */
	public final static int result(int min, int max) { // 求min和max直接的随机数
		if (min == max) {
			return min;
		}
		if (min > max) {
			int temp = max;
			max = min;
			min = temp;
		}
		return ((rnd.nextInt() >>> 1) % (max - min)) + min;
	}

	/**
	 *从数组array中随机抽取len个元素，然后生产一个新的数组
	 * renturn int[]
	 */
	
	public final static int[] restlt_2(int len, int[] array) {
		int[] r = new int[len];
		for (int i = 0; i < len; i++) {
			int index = result(i, array.length);
			r[i] = array[i];
			array[i] = array[index];
			array[index] = r[i];
			r[i] = array[i];
		}
		return r;
	}

	
	/**
	 *从数组array中随机抽取一个元素
	 *
	 */
	public final static int restlt_3(int[] array) {
		int r = (rnd.nextInt() >>> 1) % (array.length);
		return array[r];
	}

	public final static int restlt_3(short[] array) {
		int r = (rnd.nextInt() >>> 1) % (array.length);
		return array[r];
	}

	public final static int restlt_3(byte[] array) {
		int r = (rnd.nextInt() >>> 1) % (array.length);
		return array[r];
	}
//抛物线运动	
	/**
	 * 抛物线的轨迹
	 * @param x
	 * @param y
	 * @param speedX 初始速度
	 * @param speedY 上负下正
	 * @param velocityX x方向的加速度
	 * @param velocityY y方向的加速度
	 * @param count 画图的次数
	 * @return 返回第1个数为x坐标，第2个数为y坐标
	 */
	public static int[] parabola(int x,int y,int speedX,int speedY,int velocityX,int velocityY,int count){
		int xy[]=new int[2];
//		float time=(MyGameCanvas.time*1.0f/1000)*count;
//		speedX =(int)(speedX+velocityX*time);
//		speedY =(int)(speedY+velocityY*time);
//		xy[0]=x+(int)(speedX*time+(0.5f)*velocityX*time*time);
//		xy[1]=y+(int)(speedY*time+(0.5f)*velocityY*time*time);
		return xy;
	}

	
	
}



