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


public class GameNumber {
	public GameNumber() {
	}

	/**
	 * 画数字 数字图片必须是从 0到9的 顺序，且每个数字 宽度一样 用于一张图片有多套数字，且一行只有一套数字
	 * 
	 * @param img
	 *            数字图片索引
	 * @param num
	 *            数字的值
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param numWidth
	 *            数字的宽度
	 * @param space
	 *            数字间的间隔
	 * @param athor
	 *            锚点
	 * @param layer
	 *            画图层数
	 * @param numHeight
	 *            数字的高度
	 * @param clipY
	 *            数字在图片中的clipY坐标
	 * 
	 */

	public final static void drawNumber(int img, int num, int x, int y,
			int numWidth, int space, int athor, int layer, int numHeight,
			int clipY) {
		int spacing = numWidth;
		int digitCounter = 0;
		int[] digits = new int[10];
		do {
			digits[digitCounter] = num % 10;
			num /= 10;
			digitCounter++;
		} while (num > 0);
		for (int i = 0; i < digitCounter; i++) { // ��λ����λ��������
			GameDraw.add_Image(img, x + i * (spacing + space), y,
					digits[digitCounter - 1 - i] * numWidth, clipY, numWidth,
					numHeight, athor, Tools.TRANS_NONE, layer);
		}

	}

	/**
	 * 画数字 数字图片必须是从 0到9的 顺序，且每个数字 宽度一样 用于一张图片只有一套数字
	 * 
	 * @param img
	 *            数字图片索引
	 * @param num
	 *            数字的值
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param numWidth
	 *            数字的宽度
	 * @param space
	 *            数字间的间隔
	 * @param athor
	 *            锚点
	 * @param layer
	 *            画图层数
	 * @param numHeight
	 *            数字的高度
	 */
	public final static void drawNumber(int imgIndex, int num, int x, int y,
			int numWidth, int space, int athor, int layer, int numHeight) {
		drawNumber(imgIndex, num, x, y, numWidth, space, athor, layer,
				numHeight, 0);
	}

	/**
	 * 画数字 数字图片必须是从 0到9的 顺序，且每个数字 宽度一样 用于一张图片有多套数字，且一行只有一套数字
	 * 
	 * @param img
	 *            数字图片索引
	 * @param num
	 *            数字的值
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param numWidth
	 *            数字的宽度
	 * @param space
	 *            数字间的间隔
	 * @param athor
	 *            锚点
	 * @param layer
	 *            画图层数
	 * @param numHeight
	 *            数字的高度
	 * @param clipY
	 *            数字在图片中的clipY坐标
	 * @param isdraw
	 *            是否画出来
	 * 
	 */

	public final static int drawNumber(int imgIndex, int num, int x, int y,
			int numWidth, int space, int athor, int layer, int numHeight,
			int clipY, boolean isdraw) {

		if (isdraw) {
			drawNumber(imgIndex, num, x, y, numWidth, space, athor, layer,
					numHeight, clipY);
		}
		int numlen = getNumLen(num);
		return numlen;
	}

	/**
	 * 获得数字是几位数
	 * 
	 * @param num
	 *            数字
	 * 
	 * */
	public final static int getNumLen(int num) {
		int digitCounter = 0;
		int[] digits = new int[10];
		do {
			digits[digitCounter] = num % 10;
			num /= 10;
			digitCounter++;
		} while (num > 0);

		return digitCounter;
	}

	/**
	 * 最高位数，是0的补0, 例最高位数为6,则999写成000999
	 * 
	 * @param img
	 *            数字图片索引
	 * @param number
	 *            数字的值
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param numWidth
	 *            数字的宽度
	 * @param space
	 *            数字间的间隔
	 * @param athor
	 *            锚点
	 * @param layer
	 *            画图层数
	 * @param numHeight
	 *            数字的高度
	 * @param clipY
	 *            数字在图片中的clipY坐标
	 * @param isdraw
	 *            是否画出来
	 * @param maxdigit
	 *            最高位数
	 * 
	 * 
	 */
	public final static void drawNumber_max(int img, int number, int x, int y,
			int numWidth, int space, int athor, int layer, int numHeight,
			int clipY, int maxdigit) { //
										//
		int spacing = numWidth;
		int[] digits = new int[maxdigit]; // 存放各位数字。int型最多10位
		for (int i = 0; i < maxdigit; i++) {
			digits[i] = number % 10;
			number /= 10;
		}
		for (int i = 0; i < maxdigit; i++) { // 高位到低位，从左到右
			GameDraw.add_Image(img, x + i * (spacing + space), y,
					digits[maxdigit - 1 - i] * numWidth, clipY, numWidth,
					numHeight, athor, Tools.TRANS_NONE, layer);
		}
	}

	/**
	 * 画数字 格式 XXXX num1为XXXX x,y为中间点坐标
	 * 
	 * @param imgIndex
	 *            数字图片索引
	 * @param num1
	 *            XXXX的值
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param numWidth
	 *            数字的宽度
	 * @param space
	 *            数字间的间隔
	 * @param athor
	 *            锚点
	 * @param layer
	 *            画图层数
	 * @param numHeight
	 *            数字的高度
	 * @param clipY
	 *            数字在图片中的clipY坐标
	 * @param isdraw
	 *            是否画出来
	 * @param maxdigit
	 *            最高位数
	 * 
	 * 
	 */
	public final static void drawNum_Mid(int imgIndex, int num1, int x, int y,
			int numWidth, int h, int layer, int athor, int clipY) { //
		// 格式 XXXX num1为XXXX,w h为数字的宽高
		int len = drawNumber(imgIndex, num1, x, y, numWidth, 1, athor, layer,
				h, 0, false);
		x = x - (numWidth * len) / 2;
		drawNumber(imgIndex, num1, x, y, numWidth, 1, athor, layer, h, clipY,
				true);

	}

	/**
	 * 画数字， 格式 XXXX/YYYY,num1为XXXX,num2为YYYY x,y为右下点坐标
	 * 
	 * @param imgIndex
	 *            数字图片索引
	 * @param num1
	 *            XXXX的值
	 * @param num2
	 *            YYYY的值
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param numWidth
	 *            数字的宽度
	 * @param space
	 *            数字间的间隔
	 * @param athor
	 *            锚点
	 * @param layer
	 *            画图层数
	 * @param numHeight
	 *            数字的高度
	 * @param clipY
	 *            数字在图片中的clipY坐标
	 * @param isdraw
	 *            是否画出来
	 * @param maxdigit
	 *            最高位数
	 * 
	 * 
	 */
	public final static void drawNum_right(int imgIndex, int num1, int num2,
			int x, int y, int numWidth, int numHeight, int layer, int athor) { //
		//
		int len = drawNumber(imgIndex, num2, x, y, numWidth, 1, athor, layer,
				numHeight, 0, true);
		GameDraw.add_Image(imgIndex, x - numWidth * len - numWidth - 1, y,
				numWidth * 10, 0, numWidth, numHeight, athor, Tools.TRANS_NONE,
				layer); // 画/
		drawNumber(imgIndex, num1, x - numWidth * (len + 1), y, numWidth, 1,
				athor, layer, numHeight, 0, true);
	}

	/**
	 * 画数字， 格式 XXXX/YYYY,num1为XXXX,num2为YYYY x,y为/的坐标
	 * 
	 * @param imgIndex
	 *            XXXX数字图片索引
	 * @param imgIndex2
	 *            YYYY数字图片索引
	 * @param num1
	 *            XXXX的值
	 * @param num2
	 *            YYYY的值
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param numWidth
	 *            数字的宽度
	 * @param space
	 *            数字间的间隔
	 * @param athor
	 *            锚点
	 * @param layer
	 *            画图层数
	 * @param numHeight
	 *            数字的高度
	 * @param clipY
	 *            数字在图片中的clipY坐标
	 * @param isdraw
	 *            是否画出来
	 * 
	 * 
	 */

	public final static void drawAddNum_mid(int imgIndex, int imgIndex2,
			int num1, int num2, int x, int y, int w, int h, int layer,
			int athor, int clipY) { // x,y为中间/坐标
		// 格式 XXXX/YYYY X,Y为任意位数,num1为XXXX,num2为YYYY,w h为数字的宽高
		GameDraw.add_Image(imgIndex, x, y, w * 10, clipY, w, h, athor,
				Tools.TRANS_NONE, layer); // 画/

		drawNumber_2(imgIndex, num1, x, y, w, 0, athor, layer, h, clipY, true);

		drawNumber(imgIndex2, num2, x + w, y, w, 0, athor, layer, h, clipY,
				true);

	}

	/**
	 * 画数字， 格式 XXXX/YYYY,num1为XXXX,num2为YYYY x,y为/的坐标
	 * 
	 * @param imgIndex
	 *            XXXX数字图片索引
	 * @param imgIndex2
	 *            YYYY数字图片索引
	 * @param num1
	 *            XXXX的值
	 * @param num2
	 *            YYYY的值
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param numWidth
	 *            数字的宽度
	 * @param space
	 *            数字间的间隔
	 * @param athor
	 *            锚点
	 * @param layer
	 *            画图层数
	 * @param numHeight
	 *            数字的高度
	 * @param clipY
	 *            数字在图片中的clipY坐标
	 * @param isdraw
	 *            是否画出来
	 * 
	 * 
	 */

	public final static void drawAddNum_mid(int imgIndex, int num1, int num2,
			int x, int y, int w, int h, int layer, int athor, int clipY) { // x,y为中间/坐标
		// 格式 XXXX/YYYY X,Y为任意位数,num1为XXXX,num2为YYYY,w h为数字的宽高
		GameDraw.add_Image(imgIndex, x, y, w * 10, clipY, w, h, athor,
				Tools.TRANS_NONE, layer); // 画/

		drawNumber_2(imgIndex, num1, x, y, w, 0, athor, layer, h, clipY, true);

		drawNumber(imgIndex, num2, x + w, y, w, 0, athor, layer, h, clipY, true);

	}

	/**
	 * 画数字， 格式 XXXX/YYYY,num1为XXXX,num2为YYYY x,y为左下点
	 * 
	 * @param imgIndex1
	 *            XXXX数字图片索引
	 * 
	 * @param num1
	 *            XXXX的值
	 * @param num2
	 *            YYYY的值
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 * @param numWidth
	 *            数字的宽度
	 * @param space
	 *            数字间的间隔
	 * @param athor
	 *            锚点
	 * @param layer
	 *            画图层数
	 * @param numHeight
	 *            数字的高度
	 * @param clipY
	 *            数字在图片中的clipY坐标
	 * @param isdraw
	 *            是否画出来
	 * 
	 * 
	 */
	public final static void drawAddNum_left(int imgIndex1, int num1, int num2,
			int x, int y, int w, int h, int layer, int athor) { // x,y为左下点坐标

		// 格式 XXXX/YYYY X,Y为任意位数,num1为XXXX,num2为YYYY,w h为数字的宽高
		int len = drawNumber(imgIndex1, num1, x, y, w, 1, athor, layer, h, 0,
				true);
		GameDraw.add_Image(imgIndex1, x + w * len + 2, y, w * 10, 0, w, h,
				athor, Tools.TRANS_NONE, layer); // 画斜杠/
		drawNumber(imgIndex1, num2, x + w * (len + 1) + 3, y, w, 1, athor,
				layer, h, 0, true);
	}

	public final static int drawNumber_2(int img, int number, int x, int y,
			int numWidth, int space, int athor, int layer, int numHeight,
			int clipY, boolean isDraw) {
		int spacing = numWidth;
		int digitCounter = 0; // digit数字位数
		int[] digits = new int[10]; // 存放各位数字。int型最多10位
		do { // 算出number共有几位
			digits[digitCounter] = number % 10;
			number /= 10;
			digitCounter++;
		} while (number > 0);
		if (isDraw) {
			for (int i = digitCounter - 1; i >= 0; i--) { // 高位到低位，从左到右
				GameDraw.add_Image(img, x - (digitCounter - 1 - i) * (spacing)
						- spacing, y, digits[digitCounter - 1 - i] * numWidth,
						clipY, numWidth, numHeight, athor, Tools.TRANS_NONE,
						layer);
			}
		}
		return digitCounter;
	}

	
}


