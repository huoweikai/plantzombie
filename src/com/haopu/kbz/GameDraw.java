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

import com.haopu.JSGame.MyGameCanvas;

public class GameDraw {
	public GameDraw() {
	}

	/**
	 * 获得攻击框和被攻击框 ,
	 * 
	 * @param array
	 *            人物拼图数据
	 * @param curIndex
	 *            人物帧
	 * @param ishitArea
	 *            是否是攻击框 true 攻击框，false，被攻击框
	 * @param isLeft
	 *            方向
	 */

	public final static int[] hitArea(short[] array, int curIndex,
			boolean ishitArea, boolean isLeft) {
		int a = 0;
		if (ishitArea) {
			a = 4;
		}
		int[] temp = new int[4];
		temp[0] = array[a + curIndex * 8]
				+ (isLeft ? 0 : Math.abs(array[a + curIndex * 8 + 0]
						- array[a + curIndex * 8 + 2]));
		temp[1] = array[a + curIndex * 8 + 3];
		temp[2] = (int) ((Math.abs(array[a + curIndex * 8 + 0]
				- array[a + curIndex * 8 + 2]))*MyGameCanvas.zooming);
		temp[3] = (int) ((Math.abs(array[a + curIndex * 8 + 1]
				- array[a + curIndex * 8 + 3]))*MyGameCanvas.zooming);
		if (!isLeft) {
			temp[0] = -temp[0];
		}
		return temp;
	}





	public final static void renderAnimPic2Apha(int imgIndex, int curIndex, int x,
			int y, short[][] PIC_DETAIL_INFO,
			boolean shuiping, boolean shangxia, int lev, int x2, int y2,int Apha) {

		try {
			short[] byte0 = PIC_DETAIL_INFO[curIndex + 2];
			for (int i = 0; i < byte0.length; i += 4) {
				int moduleId0 = (byte0[i]) << 2;
				int transFlag = byte0[i + 1];
				int moduleX = PIC_DETAIL_INFO[0][moduleId0];
				int moduleY = PIC_DETAIL_INFO[0][moduleId0 + 1];
				int moduleW = PIC_DETAIL_INFO[0][moduleId0 + 2];
				int moduleH = PIC_DETAIL_INFO[0][moduleId0 + 3];
				int spriteX = x;
				int spriteY = y;
				switch (transFlag) {
				case 2: // '\002'
					transFlag = 1;
					break;

				case 1: // '\001'
					transFlag = 2;
					break;

				case 3: // '\003'
					transFlag = 3;
					break;

				case 5: // '\005'
					transFlag = 4;
					break;

				case 4: // '\004'
					transFlag = 6;
					break;

				case 7: // '\007'
					transFlag = 5;
					break;

				case 6: // '\006'
					transFlag = 7;
					break;
				}
				if (shuiping) {
					spriteX -= byte0[i + 2];
					spriteY += byte0[i + 3];
					transFlag ^= 0x2;
				} else {
					spriteX += byte0[i + 2];
					spriteY += byte0[i + 3];
				}
				switch (transFlag) {
				case 1: // '\001'
					spriteY -= moduleH;
					transFlag = Tools.TRANS_V;
					break;

				case 2: // '\002'
					spriteX -= moduleW;
					transFlag = Tools.TRANS_H;
					break;

				case 3: // '\003'
					spriteX -= moduleW;
					spriteY -= moduleH;
					transFlag = Tools.TRANS_HV;
					break;

				case 4:

					// transFlag = Tools.TRANS_YR;
					transFlag = Tools.TRANS_XR;
					break;

				case 6: // '\006'
					spriteX -= moduleH;
					transFlag = Tools.TRANS_R90;
					break;

				case 5: // '\005'
					spriteY -= moduleW;
					transFlag = Tools.TRANS_HV_R90;
					break;

				case 7: // '\007'
					spriteX -= moduleH;
					spriteY -= moduleW;
					// transFlag = Tools.TRANS_XR;
					transFlag = Tools.TRANS_YR;
					break;
				}
				GameDraw.add_ImageAlpha(imgIndex, spriteX, spriteY, moduleX + x2,
						moduleY + y2, moduleW, moduleH, Tools.TOP_LEFT,
						(byte) transFlag, lev,Apha);
			}
		} catch (Exception e) {
			System.out.println("renderAnimPic2 curIndex:" + curIndex
					+ "   imgIndex:" + imgIndex);
			e.printStackTrace();
		}
	}

	public final static void renderAnimPic2Rota(int imgIndex, int curIndex, int x,
			int y, short[][] PIC_DETAIL_INFO,
			boolean shuiping,int lev, int x2, int y2,float rota) {

		try {
			short[] byte0 = PIC_DETAIL_INFO[curIndex + 2];
			for (int i = 0; i < byte0.length; i += 4) {
				int moduleId0 = (byte0[i]) << 2;
				int transFlag = byte0[i + 1];
				int moduleX = PIC_DETAIL_INFO[0][moduleId0];
				int moduleY = PIC_DETAIL_INFO[0][moduleId0 + 1];
				int moduleW = PIC_DETAIL_INFO[0][moduleId0 + 2];
				int moduleH = PIC_DETAIL_INFO[0][moduleId0 + 3];
				int spriteX = x;
				int spriteY = y;
				
				GameDraw.add_ImageRota(imgIndex, spriteX, spriteY, curIndex/4*60,
						curIndex%4*60, 60, 60, Tools.TOP_LEFT,
						Tools.TRANS_NONE, lev,rota);
				
			}
		} catch (Exception e) {
			System.out.println("renderAnimPic2 curIndex:" + curIndex
					+ "   imgIndex:" + imgIndex);
			e.printStackTrace();
		}
	}


	public final static void renderAnimPic2(int imgIndex, int curIndex, int x,
			int y, short[][] PIC_DETAIL_INFO,
			boolean shuiping, boolean shangxia, int lev, int x2, int y2) {

		try {
			short[] byte0 = PIC_DETAIL_INFO[curIndex + 2];
			for (int i = 0; i < byte0.length; i += 4) {
				int moduleId0 = (byte0[i]) << 2;
				int transFlag = byte0[i + 1];
				int moduleX = PIC_DETAIL_INFO[0][moduleId0];
				int moduleY = PIC_DETAIL_INFO[0][moduleId0 + 1];
				int moduleW = PIC_DETAIL_INFO[0][moduleId0 + 2];
				int moduleH = PIC_DETAIL_INFO[0][moduleId0 + 3];
				int spriteX = x;
				int spriteY = y;
				switch (transFlag) {
				case 2: // '\002'
					transFlag = 1;
					break;

				case 1: // '\001'
					transFlag = 2;
					break;

				case 3: // '\003'
					transFlag = 3;
					break;

				case 5: // '\005'
					transFlag = 4;
					break;

				case 4: // '\004'
					transFlag = 6;
					break;

				case 7: // '\007'
					transFlag = 5;
					break;

				case 6: // '\006'
					transFlag = 7;
					break;
				}
				if (shuiping) {
					spriteX -= byte0[i + 2];
					spriteY += byte0[i + 3];
					transFlag ^= 0x2;
				} else {
					spriteX += byte0[i + 2];
					spriteY += byte0[i + 3];
				}
				
				switch (transFlag) {
				case 1: // '\001'
					spriteY -= moduleH;
					transFlag = Tools.TRANS_V;
					break;

				case 2: // '\002'
					spriteX -= moduleW;
					transFlag = Tools.TRANS_H;
					break;

				case 3: // '\003'
					spriteX -= moduleW;
					spriteY -= moduleH;
					transFlag = Tools.TRANS_HV;
					break;

				case 4:

					// transFlag = Tools.TRANS_YR;
					transFlag = Tools.TRANS_XR;
					break;

				case 6: // '\006'
					spriteX -= moduleH;
					transFlag = Tools.TRANS_R90;
					break;

				case 5: // '\005'
					spriteY -= moduleW;
					transFlag = Tools.TRANS_HV_R90;
					break;

				case 7: // '\007'
					spriteX -= moduleH;
					spriteY -= moduleW;
					// transFlag = Tools.TRANS_XR;
					transFlag = Tools.TRANS_YR;
					break;
				}
				GameDraw.add_ImageAlpha(imgIndex, spriteX, spriteY, moduleX + x2,
						moduleY + y2, moduleW, moduleH, Tools.HCENTER_VCENTER,
						(byte) transFlag, lev,255);
				
			}
		} catch (Exception e) {
			System.out.println("renderAnimPic2 curIndex:" + curIndex
					+ "   imgIndex:" + imgIndex);
			e.printStackTrace();
		}
	}
	public final static void renderAnimPic22(int imgIndex, int curIndex, int x,
			int y, short[][] PIC_DETAIL_INFO,
			boolean shuiping, boolean shangxia, int lev, int x2, int y2,float dir) {

		try {
			short[] byte0 = PIC_DETAIL_INFO[curIndex + 2];
			for (int i = 0; i < byte0.length; i += 4) {
				int moduleId0 = (byte0[i]) << 2;
				int transFlag = byte0[i + 1];
				int moduleX = PIC_DETAIL_INFO[0][moduleId0];
				int moduleY = PIC_DETAIL_INFO[0][moduleId0 + 1];
				int moduleW = PIC_DETAIL_INFO[0][moduleId0 + 2];
				int moduleH = PIC_DETAIL_INFO[0][moduleId0 + 3];
				int spriteX = x;
				int spriteY = y;
				switch (transFlag) {
				case 2: // '\002'
					transFlag = 1;
					break;

				case 1: // '\001'
					transFlag = 2;
					break;

				case 3: // '\003'
					transFlag = 3;
					break;

				case 5: // '\005'
					transFlag = 4;
					break;

				case 4: // '\004'
					transFlag = 6;
					break;

				case 7: // '\007'
					transFlag = 5;
					break;

				case 6: // '\006'
					transFlag = 7;
					break;
				}
				if (shuiping) {
					spriteX -= byte0[i + 2];
					spriteY += byte0[i + 3];
					transFlag ^= 0x2;
				} else {
					spriteX += byte0[i + 2];
					spriteY += byte0[i + 3];
				}
				
				switch (transFlag) {
				case 1: // '\001'
					spriteY -= moduleH;
					transFlag = Tools.TRANS_V;
					break;

				case 2: // '\002'
					spriteX -= moduleW;
					transFlag = Tools.TRANS_H;
					break;

				case 3: // '\003'
					spriteX -= moduleW;
					spriteY -= moduleH;
					transFlag = Tools.TRANS_HV;
					break;

				case 4:

					// transFlag = Tools.TRANS_YR;
					transFlag = Tools.TRANS_XR;
					break;

				case 6: // '\006'
					spriteX -= moduleH;
					transFlag = Tools.TRANS_R90;
					break;

				case 5: // '\005'
					spriteY -= moduleW;
					transFlag = Tools.TRANS_HV_R90;
					break;

				case 7: // '\007'
					spriteX -= moduleH;
					spriteY -= moduleW;
					// transFlag = Tools.TRANS_XR;
					transFlag = Tools.TRANS_YR;
					break;
				}
//				GameDraw.add_ImageAlpha(imgIndex, spriteX, spriteY, moduleX + x2,
//						moduleY + y2, moduleW, moduleH, Tools.HCENTER_VCENTER,
//						(byte) transFlag, lev,255);
				
				GameDraw.add_ImageRota(imgIndex, spriteX, spriteY, moduleX + x2,
						moduleY + y2, moduleW, moduleH, Tools.TOP_LEFT,
						 transFlag, lev,dir);
				
			}
		} catch (Exception e) {
			System.out.println("renderAnimPic2 curIndex:" + curIndex
					+ "   imgIndex:" + imgIndex);
			e.printStackTrace();
		}
	}
	
	public final static void renderAnimPic33(int imgIndex, int curIndex, int x,
			int y, short[][] PIC_DETAIL_INFO,
			boolean shuiping, boolean shangxia, int lev, int x2, int y2,float dir) {

		try {
			short[] byte0 = PIC_DETAIL_INFO[curIndex + 2];
			for (int i = 0; i < byte0.length; i += 4) {
				int moduleId0 = (byte0[i]) << 2;
				int transFlag = byte0[i + 1];
				int moduleX = PIC_DETAIL_INFO[0][moduleId0];
				int moduleY = PIC_DETAIL_INFO[0][moduleId0 + 1];
				int moduleW = PIC_DETAIL_INFO[0][moduleId0 + 2];
				int moduleH = PIC_DETAIL_INFO[0][moduleId0 + 3];
				int spriteX = x;
				int spriteY = y;
				switch (transFlag) {
				case 2: // '\002'
					transFlag = 1;
					break;

				case 1: // '\001'
					transFlag = 2;
					break;

				case 3: // '\003'
					transFlag = 3;
					break;

				case 5: // '\005'
					transFlag = 4;
					break;

				case 4: // '\004'
					transFlag = 6;
					break;

				case 7: // '\007'
					transFlag = 5;
					break;

				case 6: // '\006'
					transFlag = 7;
					break;
				}
				if (shuiping) {
					spriteX -= byte0[i + 2];
					spriteY += byte0[i + 3];
					transFlag ^= 0x2;
				} else {
					spriteX += byte0[i + 2];
					spriteY += byte0[i + 3];
				}
				
				switch (transFlag) {
				case 1: // '\001'
					spriteY -= moduleH;
					transFlag = Tools.TRANS_V;
					break;

				case 2: // '\002'
					spriteX -= moduleW;
					transFlag = Tools.TRANS_H;
					break;

				case 3: // '\003'
					spriteX -= moduleW;
					spriteY -= moduleH;
					transFlag = Tools.TRANS_HV;
					break;
				case 4:
					// transFlag = Tools.TRANS_YR;
					transFlag = Tools.TRANS_XR;
					break;

				case 6: // '\006'
					spriteX -= moduleH;
					transFlag = Tools.TRANS_R90;
					break;

				case 5: // '\005'
					spriteY -= moduleW;
					transFlag = Tools.TRANS_HV_R90;
					break;

				case 7: // '\007'
					spriteX -= moduleH;
					spriteY -= moduleW;
					// transFlag = Tools.TRANS_XR;
					transFlag = Tools.TRANS_YR;
					break;
				}
//				GameDraw.add_ImageAlpha(imgIndex, spriteX, spriteY, moduleX + x2,
//						moduleY + y2, moduleW, moduleH, Tools.HCENTER_VCENTER,
//						(byte) transFlag, lev,255);
				
				GameDraw.add_ImageRota(imgIndex, spriteX, spriteY, moduleX + x2,
						moduleY + y2, moduleW, moduleH, Tools.TOP_LEFT,
						 transFlag, lev,dir);
				
			}
		} catch (Exception e) {
			System.out.println("renderAnimPic2 curIndex:" + curIndex
					+ "   imgIndex:" + imgIndex);
			e.printStackTrace();
		}
	}
	
	
	public static int drawNumber(int img, int time, int x, int y, int numWidth,
			int space, int athor, int layer, int numHeight, int clipY,
			boolean isdraw) {
		int spacing = numWidth;
		int digitCounter = 0; // digit閺佹澘鐡ф担宥嗘殶
		int[] digits = new int[10]; //存储各位数字
		do { //计算各位数字 从右到左,依次个十百千万.....eg:1525
			digits[digitCounter] = time % 10;
			time /= 10;
			digitCounter++;
		} while (time > 0);
		if (isdraw) {
//		digits[]={5,2,5,1}	digitCounter=4
			for (int i = 0; i < digitCounter; i++) { //存储并且画各位数字
				GameDraw.addObject(img, x + i * (spacing + space), y,
						digits[digitCounter - 1 - i] * numWidth, clipY,
						numWidth, numHeight, athor, Tools.TRANS_NONE, layer);
			}
		}
		return digitCounter;
	}
/**
 * 画数字，右对齐方式	
 * @param img
 * @param time
 * @param x
 * @param y
 * @param numWidth
 * @param space
 * @param rightX右对齐的x坐标
 * @param layer
 * @param numHeight
 * @param clipY
 * @param isdraw
 * @return
 */
	public static int drawNumber55(int img, int time, int x, int y, int numWidth,
			int space, int rightX, int layer, int numHeight, int clipY,
			boolean isdraw) {
		int spacing = numWidth;
		int digitCounter = 0; // digit閺佹澘鐡ф担宥嗘殶
		int[] digits = new int[10]; //存储各位数字
		do { //计算各位数字 从右到左,依次个十百千万.....eg:1525
			digits[digitCounter] = time % 10;
			time /= 10;
			digitCounter++;
		} while (time > 0);
		if (isdraw) {
//		digits[]={5,2,5,1}	digitCounter=4
			for (int i = 0; i < digitCounter; i++) { //存储并且画各位数字
				GameDraw.addObject(img, x - (digitCounter - 1 - i) * (spacing + space), y,
						digits[digitCounter - 1 - i] * numWidth, clipY,
						numWidth, numHeight,Tools.HCENTER_VCENTER,Tools.TRANS_NONE, layer);
			}
		}
		return digitCounter;
	}
/**
 * 画数字，以中心点去画	
 * @param img
 * @param time
 * @param x
 * @param y
 * @param numWidth
 * @param space
 * @param rightX
 * @param layer
 * @param numHeight
 * @param clipY
 * @param isdraw
 * @return
 */
	public static int drawNumber22(int img, int time, int x, int y, int numWidth,
			int space, int rightX, int layer, int numHeight, int clipY,
			boolean isdraw) {
		int spacing = numWidth;
		int digitCounter = 0; // digit閺佹澘鐡ф担宥嗘殶
		int[] digits = new int[10]; //存储各位数字
		do { //计算各位数字 从右到左,依次个十百千万.....eg:15252
			digits[digitCounter] = time % 10;
			time /= 10;
			digitCounter++;
		} while (time > 0);
		if (isdraw) {
//		digits[]={2,1,2,5,2,5,1}digitCounter=5
//判断数字位数的奇偶
			if(digitCounter/2==0){//数字是偶数个
				for (int i = 0; i < digitCounter/2; i++) { //画中间数字的左边
					GameDraw.addObject(img, x - (digitCounter/2 - i) * (spacing + space)+(spacing + space)/2, y,
							digits[digitCounter - 1 - i] * numWidth,clipY,
							numWidth, numHeight,Tools.HCENTER_VCENTER,Tools.TRANS_NONE, layer);
				}
				for(int i=digitCounter/2;i<digitCounter;i++){//画中间数字的右边
					GameDraw.addObject(img, x +(i+1-digitCounter/2) * (spacing + space)-(spacing + space)/2, y,
							digits[digitCounter - 1 - i] * numWidth, clipY,
							numWidth, numHeight,Tools.HCENTER_VCENTER,Tools.TRANS_NONE, layer);
				}
			}else{
				for (int i = 0; i <= digitCounter/2; i++) { //画中间数字的左边
					GameDraw.addObject(img, x - (digitCounter/2 - i) * (spacing + space), y,
							digits[digitCounter - 1 - i] * numWidth,clipY,
							numWidth, numHeight,Tools.HCENTER_VCENTER,Tools.TRANS_NONE, layer);
				}
				for(int i=digitCounter/2+1;i<digitCounter;i++){//画中间数字的右边
					GameDraw.addObject(img, x +(i-digitCounter/2) * (spacing + space), y,
							digits[digitCounter - 1 - i] * numWidth, clipY,
							numWidth, numHeight,Tools.HCENTER_VCENTER,Tools.TRANS_NONE, layer);
				}
			}
//			for (int i = 0; i < digitCounter; i++) { //存储并且画各位数字
//				GameDraw.addObject(img, x - (digitCounter - 1 - i) * (spacing + space), y,
//						digits[digitCounter - 1 - i] * numWidth, clipY,
//						numWidth, numHeight,Tools.HCENTER_VCENTER,Tools.TRANS_NONE, layer);
//			}
		}
		return digitCounter;
	}
	public static void addObject(int imgIndex, int x, int y, int clipX,
			int clipY, int clipW, int clipH, int anchor, int trans,
			int drawLevel) {

		if (Tools.isDraw(x, y, clipW, clipH, anchor)) {
			//Log.i("Tools.curIndex", Tools.curIndex+";");
			Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.clipX[Tools.curIndex] = (int) ((short) clipX*MyGameCanvas.zoomingX);
			Tools.clipY[Tools.curIndex] = (int) ((short) clipY*MyGameCanvas.zoomingY);
			Tools.clipW[Tools.curIndex] = (int) ((short) clipW*MyGameCanvas.zoomingX);
			Tools.clipH[Tools.curIndex] = (int) ((short) clipH*MyGameCanvas.zoomingY);

			Tools.anchor[Tools.curIndex] = (int) anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
			Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
			
			Tools.rotate[Tools.curIndex]=0;
			Tools.Alpha[Tools.curIndex]=255;
			Tools.scaleX[Tools.curIndex]=1;
			Tools.scaleY[Tools.curIndex]=1;
			// System.out.print("imgIndex:"+imgIndex);
			setIndex();
			// System.out.println("imgIndex:"+imgIndex);
		}
	}
	
	public static int bToi(byte byte0) {
		int i = byte0;
		if (byte0 < 0) {
			i += 256;
		}
		return i;
	}

	public static void drawShadow(int x, int y, int w, int bh, int layer) {
		// Tools.addObject(Tools.TYPE_ARC, x, y + 2 - bw, w, bw, 0, 360, true,
		// Tools.TOP, 0x000000, y);
		GameDraw.addObject(Tools.TYPE_ARC, x, y, w, bh, 0, 360, true,
				Tools.BOTTOM_LEFT, 0x000000, layer);
	}

	public static void setIndex() {

		try {
			Tools.drawObj[Tools.max_obj++] = Tools.curIndex;
			if (++Tools.curIndex >= Tools.MAX - 1) {
				Tools.curIndex = 0;
				Tools.max_obj = 0;
			}
		} catch (Exception e) {
			System.out.println(Tools.curIndex + "    setIndex:" + Tools.max_obj);
			Tools.curIndex = 0;
			Tools.max_obj = 0;
		}

	}

	/**
	 * 画普通字符串 ，自定义字符大小
	 * 
	 * @param TextSize
	 *            字符大小
	 */
	
	

	public static void add_String(String str, int x, int y, int anchor,
			int color, int drawLevel, int TextSize) {
		Tools.str[Tools.curIndex] = str;
		Tools.type[Tools.curIndex] = Tools.TYPE_STRING;
		;
		Tools.x[Tools.curIndex] = (short) (x);
		Tools.y[Tools.curIndex] = (short) (y);
		Tools.anchor[Tools.curIndex] = (int) anchor;
		Tools.color[Tools.curIndex] = (int) color;
		Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
		Tools.rw[Tools.curIndex] = (int) ((short) TextSize*MyGameCanvas.zooming);
		setIndex();
	}

	/**
	 * 画普通字符串 ,字符默认大小为18，可更改Tools.TextSize
	 */
	public static void add_String2(String str, int x, int y, int anchor,
			int color, int drawLevel) {
		Tools.str[Tools.curIndex] = str;
		Tools.type[Tools.curIndex] = Tools.TYPE_STRING;
		Tools.x[Tools.curIndex] = (short) (x);
		Tools.y[Tools.curIndex] = (short) (y);
		Tools.anchor[Tools.curIndex] = anchor;
		Tools.color[Tools.curIndex] = color;
		Tools.drawLevel[Tools.curIndex] = drawLevel;
		Tools.rw[Tools.curIndex] = (int) (Tools.TextSize*MyGameCanvas.zooming);
		setIndex();
	}

	/**
	 * 画clip字符串 , clipX, clipY, clipW, clipH为裁剪区域，取值范围必须减去setoffX和setoffY
	 */
	public static void add_ClipString(String str, int x, int y, int clipX,
			int clipY, int clipW, int clipH, int anchor, int color,
			int drawLevel, int TextSize) {
		Tools.str[Tools.curIndex] = str;
		Tools.type[Tools.curIndex] = Tools.TYPE_STRING;
		Tools.x[Tools.curIndex] = (short) (x);
		Tools.y[Tools.curIndex] = (short) (y);
		Tools.anchor[Tools.curIndex] = anchor;
		Tools.color[Tools.curIndex] = color;
		Tools.drawLevel[Tools.curIndex] = drawLevel;

		Tools.clipX[Tools.curIndex] = (int) (clipX*MyGameCanvas.zoomingX);
		Tools.clipY[Tools.curIndex] = (int) (clipY*MyGameCanvas.zoomingY);
		Tools.clipW[Tools.curIndex] = (int) (clipW*MyGameCanvas.zoomingX);
		Tools.clipH[Tools.curIndex] = (int) (clipH*MyGameCanvas.zoomingY);
		Tools.rw[Tools.curIndex] = (int) (TextSize*MyGameCanvas.zoomingX);
		setIndex();
	}

	/**
	 * 画clip字符串 , clipX, clipY, clipW, clipH为裁剪区域，超出 部分不显示
	 * 取值范围必须减去setoffX和setoffY 字符默认大小为18，可更改Tools.TextSize
	 */
	public static void add_ClipString2(byte type, String str, int x, int y,
			int clipX, int clipY, int clipW, int clipH, int anchor, int color,
			int drawLevel) {
		Tools.str[Tools.curIndex] = str;
		Tools.type[Tools.curIndex] = type;
		Tools.x[Tools.curIndex] = (short) (x);
		Tools.y[Tools.curIndex] = (short) (y);
		Tools.anchor[Tools.curIndex] = anchor;
		Tools.color[Tools.curIndex] = color;
		Tools.drawLevel[Tools.curIndex] = drawLevel;

		Tools.clipX[Tools.curIndex] = (int) (clipX*MyGameCanvas.zoomingX);
		Tools.clipY[Tools.curIndex] = (int) (clipY*MyGameCanvas.zoomingY);
		Tools.clipW[Tools.curIndex] = (int) (clipW*MyGameCanvas.zoomingX);
		Tools.clipH[Tools.curIndex] = (int) (clipH*MyGameCanvas.zoomingY);
		Tools.rw[Tools.curIndex] = (int) (Tools.TextSize*MyGameCanvas.zoomingX);
		setIndex();
	}

	// ��Բ�Ǿ��κ�Բ
	public static void addObject(byte type, int x, int y, int w, int h, int rw,
			int rh, boolean isFill, int anchor, int color, int drawLevel) {
		if (Tools.isDraw(x, y, w, h, anchor)) {
			Tools.type[Tools.curIndex] = (byte) type;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.w[Tools.curIndex] = (short) (w*MyGameCanvas.zoomingX);
			Tools.h[Tools.curIndex] = (short) (h*MyGameCanvas.zoomingY);
			Tools.rw[Tools.curIndex] = (short) (rw*MyGameCanvas.zoomingX);
			Tools.rh[Tools.curIndex] = (short) (rh*MyGameCanvas.zoomingY);
			Tools.isFill[Tools.curIndex] = isFill;
			Tools.anchor[Tools.curIndex] = (int) anchor;
			Tools.color[Tools.curIndex] = (int) color;
			Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
	//		set(Tools.curIndex);
			Tools.rotate[Tools.curIndex]=0;
			Tools.Alpha[Tools.curIndex]=255;
			Tools.scaleX[Tools.curIndex]=1;
			Tools.scaleY[Tools.curIndex]=1;
			setIndex();
		}
	}

	/**
	 * 画矩形 ,
	 * 
	 * @param isFill
	 *            是否填充颜色，ture是实心，false是空心
	 */
	public static void add_Rect(int x, int y, int w, int h, boolean isFill,
			int anchor, int color, int drawLevel) {
		if (Tools.isDraw(x, y, w, h, anchor)) {
			Tools.type[Tools.curIndex] = Tools.TYPE_RECT;
			Tools.x[Tools.curIndex] = (short) (x*MyGameCanvas.zoomingX);
			Tools.y[Tools.curIndex] = (short) (y*MyGameCanvas.zoomingY);
			Tools.w[Tools.curIndex] = (int) (w*MyGameCanvas.zoomingX);
			Tools.h[Tools.curIndex] = (int) (h*MyGameCanvas.zoomingY);
			Tools.anchor[Tools.curIndex] = anchor;
			Tools.color[Tools.curIndex] = color;
			Tools.isFill[Tools.curIndex] = isFill;
			Tools.drawLevel[Tools.curIndex] = drawLevel;
			setIndex();
		}
	}

	/**
	 * 画整张图片 ,
	 */
	public static void add_Image(int imgIndex, int x, int y, int anchor,
			int trans, int drawLevel) {
		if (Tools.isDraw(x, y, Tools.getImage(imgIndex).getWidth(), Tools
				.getImage(imgIndex).getHeight(), anchor)) {
			Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.clipX[Tools.curIndex] = (short) 0;
			Tools.clipY[Tools.curIndex] = (short) 0;
			Tools.clipW[Tools.curIndex] = (short) Tools.getImage(imgIndex)
					.getWidth();
			Tools.clipH[Tools.curIndex] = (short) Tools.getImage(imgIndex)
					.getHeight();

			Tools.anchor[Tools.curIndex] = (int) anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
			Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
			set(Tools.curIndex);
			
			
			setIndex();
		}
	}
	
	/**
	 *不进行图片处理,
	 */
	static void set(int curIndex){
		Tools.rotate[curIndex]=0;
		Tools.Alpha[curIndex]=255;
		Tools.scaleX[curIndex]=1;
		Tools.scaleY[curIndex]=1;
	}
	
	

	/**
	 * 画整张图片 修改透明度
	 */
	public static void add_ImageAlpha(int imgIndex, int x, int y, int anchor,
			int trans, int drawLevel,int Alpha) {
		if (Tools.isDraw(x, y, Tools.getImage(imgIndex).getWidth(), Tools
				.getImage(imgIndex).getHeight(), anchor)) {
			Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.clipX[Tools.curIndex] = (short) 0;
			Tools.clipY[Tools.curIndex] = (short) 0;
			Tools.clipW[Tools.curIndex] = (short) Tools.getImage(imgIndex)
					.getWidth();
			Tools.clipH[Tools.curIndex] = (short) Tools.getImage(imgIndex)
					.getHeight();

			Tools.anchor[Tools.curIndex] = (int) anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
			Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
			Tools.rotate[Tools.curIndex]=0;
			Tools.Alpha[Tools.curIndex]=Alpha;
			Tools.scaleX[Tools.curIndex]=1;
			Tools.scaleY[Tools.curIndex]=1;
			setIndex();
		}
	}
	
	
	/**
	 * 画整张图片旋转,
	 */
	public static void add_ImageRota(int imgIndex, int x, int y, int anchor,
			int trans, int drawLevel,float rota) { 
		if (Tools.isDraw(x, y, Tools.getImage(imgIndex).getWidth(), Tools
				.getImage(imgIndex).getHeight(), anchor)) {
			Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.clipX[Tools.curIndex] = (short) 0; 
			Tools.clipY[Tools.curIndex] = (short) 0;
			Tools.clipW[Tools.curIndex] = (short) Tools.getImage(imgIndex)
					.getWidth();
			Tools.clipH[Tools.curIndex] = (short) Tools.getImage(imgIndex)
					.getHeight();

			Tools.anchor[Tools.curIndex] = (int) anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
			Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
			Tools.rotate[Tools.curIndex]=rota;
			Tools.Alpha[Tools.curIndex]=255;
			Tools.scaleX[Tools.curIndex]=1;
			Tools.scaleY[Tools.curIndex]=1;
			setIndex();
		}
	}
/**
 * 	
 * @param imgIndex
 * @param x
 * @param y
 * @param clipX
 * @param clipY
 * @param clipW
 * @param clipH
 * @param anchor
 * @param trans
 * @param drawLevel
 * @param rota
 */
	public static void add_ImageRotaScale(int imgIndex, int x, int y, int anchor,
			int trans, int drawLevel,float rota,float scale) {
		if (Tools.isDraw(x, y, Tools.getImage(imgIndex).getWidth(), Tools
				.getImage(imgIndex).getHeight(), anchor)) {
			Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.clipX[Tools.curIndex] = (short) 0;
			Tools.clipY[Tools.curIndex] = (short) 0;
			Tools.clipW[Tools.curIndex] = (short) Tools.getImage(imgIndex)
					.getWidth();
			Tools.clipH[Tools.curIndex] = (short) Tools.getImage(imgIndex)
					.getHeight();

			Tools.anchor[Tools.curIndex] = (int) anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
			Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
			Tools.rotate[Tools.curIndex]=rota;
			Tools.Alpha[Tools.curIndex]=255;
			Tools.scaleX[Tools.curIndex]=scale;
			Tools.scaleY[Tools.curIndex]=scale;
			setIndex();
		}
	}
	public static void add_ImageRota(int imgIndex, int x, int y, int clipX,
			int clipY, int clipW, int clipH, int anchor, int trans,
			int drawLevel,float rota ) {

		if (Tools.isDraw(x, y, clipW, clipH, anchor)) {
			Tools.imgIndex[Tools.curIndex] =  imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.clipX[Tools.curIndex] =  (int) (clipX*MyGameCanvas.zoomingX);
			Tools.clipY[Tools.curIndex] = (int) (clipY*MyGameCanvas.zoomingY);
			Tools.clipW[Tools.curIndex] = (int) (clipW*MyGameCanvas.zoomingX);
			Tools.clipH[Tools.curIndex] = (int) (clipH*MyGameCanvas.zoomingY);

			Tools.anchor[Tools.curIndex] =  anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] =  drawLevel;
			Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
			
			Tools.rotate[Tools.curIndex]=rota;
			Tools.Alpha[Tools.curIndex]=255;
			Tools.scaleX[Tools.curIndex]=1;
			Tools.scaleX[Tools.curIndex]=1;
			setIndex();
		}
	}
	
	/**
	 * 画整张图片 缩放,
	 */
	public static void add_ImageScale(int imgIndex, int x, int y, int anchor,
			int trans, int drawLevel,float ScaleX,float ScaleY) {
		if (Tools.isDraw(x, y, Tools.getImage(imgIndex).getWidth(), Tools
				.getImage(imgIndex).getHeight(), anchor)) {
			Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.clipX[Tools.curIndex] = (short) 0;
			Tools.clipY[Tools.curIndex] = (short) 0;
			Tools.clipW[Tools.curIndex] = (short) Tools.getImage(imgIndex)
					.getWidth();
			Tools.clipH[Tools.curIndex] = (short) Tools.getImage(imgIndex)
					.getHeight();
			Tools.anchor[Tools.curIndex] = (int) anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
			Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
			Tools.rotate[Tools.curIndex]=0;
			Tools.Alpha[Tools.curIndex]=255;
			Tools.scaleX[Tools.curIndex]=ScaleX;
			Tools.scaleY[Tools.curIndex]=ScaleY;
			Tools.scaleXpoint[Tools.curIndex]= x;
			Tools.scaleYpoint[Tools.curIndex]= y;

			setIndex();
		}
	}
	
/**
 * 画图,放缩的同时具备Alpha效果	
 * @param imgIndex
 * @param x
 * @param y
 * @param anchor
 * @param trans
 * @param drawLevel
 * @param ScaleX
 * @param ScaleY
 * @param alpha
 */
	public static void add_ImageScaleAlpha(int imgIndex, int x, int y, int anchor,
			int trans, int drawLevel,float ScaleX,float ScaleY,int alpha) {
		if (Tools.isDraw(x, y, Tools.getImage(imgIndex).getWidth(), Tools
				.getImage(imgIndex).getHeight(), anchor)) {
			Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.clipX[Tools.curIndex] = (short) 0;
			Tools.clipY[Tools.curIndex] = (short) 0;
			Tools.clipW[Tools.curIndex] = (short) Tools.getImage(imgIndex)
					.getWidth();
			Tools.clipH[Tools.curIndex] = (short) Tools.getImage(imgIndex)
					.getHeight();
			Tools.anchor[Tools.curIndex] = (int) anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
			Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
			Tools.rotate[Tools.curIndex]=0;
			Tools.Alpha[Tools.curIndex]=alpha;
			Tools.scaleX[Tools.curIndex]=ScaleX;
			Tools.scaleY[Tools.curIndex]=ScaleY;
			setIndex();
		}
	}
	

	/**
	 * 画带裁剪区域的图片 , clipx2,clipy2,clipw2, cliph2为图片显示 区域 ，超出 部分不显示
	 * 取值范围必须减去setoffX和setoffY
	 */
	public static void add_ClipImage(int imgIndex, int x, int y, int clipx,
			int clipy, int clipw, int cliph, int clipx2, int clipy2,
			int clipw2, int cliph2, int anchor, int trans, int drawLevel) {
		if (Tools.isDraw(x, y, Tools.getImage(imgIndex).getWidth(), Tools
				.getImage(imgIndex).getHeight(), anchor)) {

			Tools.type[Tools.curIndex] = Tools.TYPE_CLIP_IMG;
			Tools.imgIndex[Tools.curIndex] = imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);

			Tools.w[Tools.curIndex] = (int) (clipx*MyGameCanvas.zoomingX);
			Tools.h[Tools.curIndex] = (int) (clipy*MyGameCanvas.zoomingY);
			Tools.rw[Tools.curIndex] = (int) (clipw*MyGameCanvas.zoomingX);
			Tools.rh[Tools.curIndex] = (int) (cliph*MyGameCanvas.zoomingY);

			Tools.clipX[Tools.curIndex] = (int) (clipx2*MyGameCanvas.zoomingX);
			Tools.clipY[Tools.curIndex] = (int) (clipy2*MyGameCanvas.zoomingY);
			Tools.clipW[Tools.curIndex] = (int) (clipw2*MyGameCanvas.zoomingX);
			Tools.clipH[Tools.curIndex] = (int) (cliph2*MyGameCanvas.zoomingY);

			Tools.anchor[Tools.curIndex] = (int) anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
            set(Tools.curIndex);
			
			setIndex();
		}
	}

	//

	/**
	 * 画图片 ,
	 */

	public static void add_Image(int imgIndex, int x, int y, int clipX,
			int clipY, int clipW, int clipH, int anchor, int trans,
			int drawLevel ) {

		if (Tools.isDraw(x, y, clipW, clipH, anchor)) {
			Tools.imgIndex[Tools.curIndex] =  imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.clipX[Tools.curIndex] =  (int) (clipX*MyGameCanvas.zoomingX);
			Tools.clipY[Tools.curIndex] = (int) (clipY*MyGameCanvas.zoomingY);
			Tools.clipW[Tools.curIndex] = (int) (clipW*MyGameCanvas.zoomingX);
			Tools.clipH[Tools.curIndex] = (int) (clipH*MyGameCanvas.zoomingY);

			Tools.anchor[Tools.curIndex] =  anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] =  drawLevel;
			Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
			set(Tools.curIndex);
			
			// System.out.print("imgIndex:"+imgIndex);
			setIndex();
			// System.out.println("imgIndex:"+imgIndex);
		}
	}
	
	
	
	/**
	 * 画图片 ,透明度
	 */
	public static void add_ImageAlpha(int imgIndex, int x, int y, int clipX,
			int clipY, int clipW, int clipH, int anchor, int trans,
			int drawLevel,int  Alpha) {

		if (Tools.isDraw(x, y, clipW, clipH, anchor)) {
			Tools.imgIndex[Tools.curIndex] =  imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.clipX[Tools.curIndex] =  (int) (clipX*MyGameCanvas.zoomingX);
			Tools.clipY[Tools.curIndex] = (int) (clipY*MyGameCanvas.zoomingY);
			Tools.clipW[Tools.curIndex] = (int) (clipW*MyGameCanvas.zoomingX);
			Tools.clipH[Tools.curIndex] = (int) (clipH*MyGameCanvas.zoomingY);

			Tools.anchor[Tools.curIndex] =  anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] =  drawLevel;
			Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
			Tools.rotate[Tools.curIndex]=0;
			Tools.Alpha[Tools.curIndex]=Alpha;
			Tools.scaleX[Tools.curIndex]=1;
			Tools.scaleY[Tools.curIndex]=1;
			
			
			
			// System.out.print("imgIndex:"+imgIndex);
			setIndex();
			// System.out.println("imgIndex:"+imgIndex);
		}
	}
	
	
	/**
	 * 画图片 ,缩放
	 */
	public static void add_ImageScale(int imgIndex, int x, int y, int clipX,
			int clipY, int clipW, int clipH, int anchor, int trans,
			int drawLevel,float  scaleX,float scaleY) {
		if (Tools.isDraw(x, y, clipW, clipH, anchor)) {
			Tools.imgIndex[Tools.curIndex] =  imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.clipX[Tools.curIndex] =  (int) (clipX*MyGameCanvas.zoomingX);
			Tools.clipY[Tools.curIndex] = (int) (clipY*MyGameCanvas.zoomingY);
			Tools.clipW[Tools.curIndex] = (int) (clipW*MyGameCanvas.zoomingX);
			Tools.clipH[Tools.curIndex] = (int) (clipH*MyGameCanvas.zoomingY);

			Tools.anchor[Tools.curIndex] =  anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] =  drawLevel;
			Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
			Tools.rotate[Tools.curIndex]=0;
			Tools.Alpha[Tools.curIndex]=255;
			Tools.scaleX[Tools.curIndex]=scaleX;
			Tools.scaleY[Tools.curIndex]=scaleX;
			
			
			
			// System.out.print("imgIndex:"+imgIndex);
			setIndex();
			// System.out.println("imgIndex:"+imgIndex);
		}
	}
	
	/**
	 * 画图片 ,缩放
	 */
	public static void add_ImageScale2(int imgIndex, int x, int y,
			int anchor, int trans,
			int drawLevel,float  scaleX,float scaleY,float rota) {
		if (
			Tools.isDraw(x, y, Tools.getImage(imgIndex).getWidth(), Tools
						.getImage(imgIndex).getHeight(), anchor)) {
			Tools.imgIndex[Tools.curIndex] =  imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.clipX[Tools.curIndex] = (short) 0; 
			Tools.clipY[Tools.curIndex] = (short) 0;
			Tools.clipW[Tools.curIndex] = (short) Tools.getImage(imgIndex)
					.getWidth();
			Tools.clipH[Tools.curIndex] = (short) Tools.getImage(imgIndex)
					.getHeight();
			Tools.anchor[Tools.curIndex] =  anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] =  drawLevel;
			Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
			Tools.rotate[Tools.curIndex]=0;
			Tools.Alpha[Tools.curIndex]=255;
			Tools.scaleX[Tools.curIndex]=scaleX;
			Tools.scaleY[Tools.curIndex]=scaleX;
			Tools.rotate[Tools.curIndex]=rota;
			
			
			// System.out.print("imgIndex:"+imgIndex);
			setIndex();
			// System.out.println("imgIndex:"+imgIndex);
		}
	}
	
	
	
	
	
	

	/**
	 * 画图片 , byte[] clipRect,
	 */
	public static void add_Image(int imgIndex, int x, int y, byte[] clipRect,
			int anchor, int trans, int drawLevel) {
		if (Tools.isDraw(x, y, clipRect[2], clipRect[3], anchor)) {
			Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.clipX[Tools.curIndex] = (int) ((short) clipRect[0]*MyGameCanvas.zoomingX);
			Tools.clipY[Tools.curIndex] = (int) ((short) clipRect[1]*MyGameCanvas.zoomingY);
			Tools.clipW[Tools.curIndex] = (int) ((short) clipRect[2]*MyGameCanvas.zoomingX);
			Tools.clipH[Tools.curIndex] = (int) ((short) clipRect[3]*MyGameCanvas.zoomingY);
			Tools.anchor[Tools.curIndex] = (int) anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
			Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
			set(Tools.curIndex);
			setIndex();
		}
	}

	/**
	 * 画图片 , short[] clipRect,
	 */
	public static void add_Image(int imgIndex, int x, int y, short[] clipRect,
			int anchor, int trans, int drawLevel) {
		if (Tools.isDraw(x, y, clipRect[2], clipRect[3], anchor)) {
			Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.clipX[Tools.curIndex] = (int) ((short) clipRect[0]*MyGameCanvas.zoomingX);
			Tools.clipY[Tools.curIndex] = (int) ((short) clipRect[1]*MyGameCanvas.zoomingY);
			Tools.clipW[Tools.curIndex] = (int) ((short) clipRect[2]*MyGameCanvas.zoomingX);
			Tools.clipH[Tools.curIndex] = (int) ((short) clipRect[3]*MyGameCanvas.zoomingY);
			Tools.anchor[Tools.curIndex] = (int) anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
			Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
			set(Tools.curIndex);
			setIndex();
		}
	}

	/**
	 * 画图片 , int[] clipRect,
	 */
	public static void add_Image(int imgIndex, int x, int y, int[] clipRect,
			int anchor, int trans, int drawLevel) {
		if (Tools.isDraw(x, y, clipRect[2], clipRect[3], anchor)) {
			Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.clipX[Tools.curIndex] = (int) ((short) clipRect[0]*MyGameCanvas.zoomingX);
			Tools.clipY[Tools.curIndex] = (int) ((short) clipRect[1]*MyGameCanvas.zoomingY);
			Tools.clipW[Tools.curIndex] = (int) ((short) clipRect[2]*MyGameCanvas.zoomingX);
			Tools.clipH[Tools.curIndex] = (int) ((short) clipRect[3]*MyGameCanvas.zoomingY);
			Tools.anchor[Tools.curIndex] = (int) anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
			Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
			set(Tools.curIndex);
			setIndex();
		}
	}
	public static void add_ImageRota(int imgIndex, int x, int y, int[] clipRect,
			int anchor, int trans, int drawLevel,float rota) {
		if (Tools.isDraw(x, y, clipRect[2], clipRect[3], anchor)) {
			Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.clipX[Tools.curIndex] = (int) ((short) clipRect[0]*MyGameCanvas.zoomingX);
			Tools.clipY[Tools.curIndex] = (int) ((short) clipRect[1]*MyGameCanvas.zoomingY);
			Tools.clipW[Tools.curIndex] = (int) ((short) clipRect[2]*MyGameCanvas.zoomingX);
			Tools.clipH[Tools.curIndex] = (int) ((short) clipRect[3]*MyGameCanvas.zoomingY);
			Tools.anchor[Tools.curIndex] = (int) anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
			Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
			Tools.rotate[Tools.curIndex]=rota;
			Tools.Alpha[Tools.curIndex]=255;
			Tools.scaleX[Tools.curIndex]=1;
			Tools.scaleY[Tools.curIndex]=1;
//			set(Tools.curIndex);
			setIndex();
		}
	}
	public static void add_ImageScale(int imgIndex, int x, int y, int[] clipRect,
			int anchor, int trans, int drawLevel,float scalx,float scaly) {
		if (Tools.isDraw(x, y, clipRect[2], clipRect[3], anchor)) {
			Tools.imgIndex[Tools.curIndex] = (short) imgIndex;
			Tools.x[Tools.curIndex] = (short) (x);
			Tools.y[Tools.curIndex] = (short) (y);
			Tools.clipX[Tools.curIndex] = (int) ((short) clipRect[0]*MyGameCanvas.zoomingX);
			Tools.clipY[Tools.curIndex] = (int) ((short) clipRect[1]*MyGameCanvas.zoomingY);
			Tools.clipW[Tools.curIndex] = (int) ((short) clipRect[2]*MyGameCanvas.zoomingX);
			Tools.clipH[Tools.curIndex] = (int) ((short) clipRect[3]*MyGameCanvas.zoomingY);
			Tools.anchor[Tools.curIndex] = (int) anchor;
			Tools.trans[Tools.curIndex] = trans;
			Tools.drawLevel[Tools.curIndex] = (short) drawLevel;
			Tools.type[Tools.curIndex] = Tools.TYPE_IMG;
			Tools.rotate[Tools.curIndex]=0;
			Tools.Alpha[Tools.curIndex]=255;
			Tools.scaleX[Tools.curIndex]=scalx;
			Tools.scaleY[Tools.curIndex]=scaly;
//			set(Tools.curIndex);
			setIndex();
		}
		
		
		
		
	}


}