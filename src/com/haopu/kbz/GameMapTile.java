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

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import com.haopu.JSGame.*;
import com.haopu.pak.PAK_IMAGES;

import android.content.res.AssetManager;
import android.util.Log;

public class GameMapTile {

	


	public GameMapTile() {
	}

	void initImage() {
		switch(MyGameCanvas.gmStatus){
		case MyGameCanvas.gmScripe:
			switch (GameEngine.me.gameRank) {
			case 99:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "m99.bin";
				break;
			case 0:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "ce1.bin";
				break;
			case 1:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "m0.bin";
				break;
			case 2:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "m0.bin";
				break;
			case 3:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "d1.bin";
				break;
				
			case 4:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "d1.bin";
				break;
			case 5:
			    imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "ce1.bin";
				break;
			case 6:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "m1.bin"; 
				break; 
			case 7: 
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "m1.bin"; 
				break;
			case 8: 
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "d2.bin";
				break;
			case 9:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "ce2.bin";
				break; 
			case 10:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "d2.bin";
				break;
			case 11:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "ce2.bin";
				break;
			case 12:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
		 		mapName = "m2.bin";
				break;
			case 13:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "m2.bin";
				break;
			case 14:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "d3.bin";
				break;
			case 15:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "ce3.bin";
				break;
			case 16:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "d3.bin";
				break;
			case 17:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "ce3.bin";
				break;
			case 18:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "m3.bin";
				break;
			case 19:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "m3.bin";
				break;
			case 20:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "d4.bin";
				break;
			case 21:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "m3.bin";
				break;
			case 22:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "d4.bin";
				break;
			case 23:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "m3.bin";
				break;
				
			}
			break;
		case MyGameCanvas.gmFight:
			switch(GameEngine.me.iFightGameRank){
			case 0:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "tiaozhan1.bin";
				break;
			case 1:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "tiaozhan2.bin";
				break;
			case 2:
				imgIndex_layer0 = PAK_IMAGES.IMG_ROAD;
				imgIndex_layer1 = PAK_IMAGES.IMG_ROAD;
				mapName = "tiaozhan3.bin";
				break;
			}
			break;
		}
		Tools.getImage(imgIndex_layer0);
		Tools.getImage(imgIndex_layer1);

	}

	public void initMapTile(){
		initImage();
		loadMap(mapName,true);
		line_imgLayer0 = (int) (Tools.getImage(imgIndex_layer0).getWidth()/MyGameCanvas.zooming / tileWidth);
		line_imgLayer1 = (int) (Tools.getImage(imgIndex_layer1).getWidth()/MyGameCanvas.zooming / tileWidth);

	}

	public final int LAYER_MAX = 2;
	public final int LAYER_0 = 0;
	public final int LAYER_1 = 1;
	int imgIndex_layer0;// 第一层的地图图片index
	int imgIndex_layer1;// 第二层的地图图片index
	int line_imgLayer0;
	int line_imgLayer1;
	int mapLayer;
	String mapName;

	public short[] propData;

	public int[] mapGroupIndex;

	public static int mapHight;// 地图的高度
	public static int mapWidth; // 地图的宽度
	public short tileWidth; // 切片的宽度
	public short tileHight; // 切片的高度
	public int[] mapSize = new int[2]; // 地图大小 :[gameRank][0] ->地图宽的切片数
	// [gameRank][1] ->地图高的切片数
	public short[][] mapData; // 地图数据

	// 画切片
	int getMapIndex(int index) {
		for (int j = 0; j < mapGroupIndex.length; j++) {
			if (mapGroupIndex[j] == index) {
				return j;
			}
		}
		return -1;
	}

	 int x;
	int y;
	int clipx;
	int clipy;
	public void setTile(int layer) {
		int line;
		if (layer == LAYER_0) {
			line = line_imgLayer0;
		} else {
			line = line_imgLayer1;
		}
		int imgIndex=layer == LAYER_0 ? imgIndex_layer0
				: imgIndex_layer1;
		int len = mapData[layer].length;

		for (int i = 1; i < len; i++) {
			int index = getIndex(layer, i);
				x = i % mapSize[0] * tileWidth;
				y = i / mapSize[0] * tileHight;
				clipx=index % line * tileWidth;
				clipy=index / line * tileWidth;
				
//				if(propData[i]!=-1){
//					GameDraw.add_String( ""+propData[i], x, y, Tools.TOP_LEFT, 0xffffffff, 3000, 20);
//				}
				
				
			if (mapData[layer][i] == -1) {
				continue;
			}
			
			
			
			
			
			if (layer == LAYER_1) {
	             drawZhuangshiTile(x,y, mapData[LAYER_1][i]);          
			} else {//
				
				
				
//						GameDraw.add_String(""+propData[i],x, y,Tools.BOTTOM_LEFT, 0xffffffff, 500, 20);
//					GameDraw.add_Image(imgIndex,x,    //画地图切片
//						y,clipx ,clipy
//							, tileWidth, tileHight,
//							Tools.TOP_LEFT, Tools.TRANS_NONE, 20);
			}
	      }
		}

/**
 * 画装饰物    
 * @param x
 * @param y
 * @param imgIndex
 */
		int beike[][]={{0,0,147,103},{164,0,146,103},{0,121,147,105},
				{0,243,147,104},{0,121,147,105},{164,0,146,103},{0,0,147,103},{164,121,146,103}};
		int haixing[][]={{0,3,58,58},{58,1,56,56},{114,3,56,54},{171,3,56,55},{0,62,58,56},{62,62,59,55},{121,62,61,54}};
		int shuicao[][]={{358,0,67,62},{0,0,69,62},{141,0,70,62},{214,0,70,62},{286,0,66,62},{214,0,70,62},{141,0,70,62},{0,0,69,62}};
	void drawZhuangshiTile(int x,int y,int imgIndex){
        x=x;
        y=y + tileHight ;
		
		int offsetY=0;
		int offsetX=0;
		int layer=0;
		switch (imgIndex) {
//		case PAK_IMAGES.IMG_S1:
//		case PAK_IMAGES.IMG_S2:
//		case PAK_IMAGES.IMG_S3:
//		case PAK_IMAGES.IMG_S4:
//		case PAK_IMAGES.IMG_S5:
//		case PAK_IMAGES.IMG_S6:
//		case PAK_IMAGES.IMG_S7:
//		case PAK_IMAGES.IMG_S8:
//		case PAK_IMAGES.IMG_S9:
//		case PAK_IMAGES.IMG_S10:
//		case PAK_IMAGES.IMG_S11:
//		case PAK_IMAGES.IMG_S12:
//		case PAK_IMAGES.IMG_S13:
//		case PAK_IMAGES.IMG_S14:
//		case PAK_IMAGES.IMG_S15:
//		case PAK_IMAGES.IMG_S16:
//		case PAK_IMAGES.IMG_S17:
//		case PAK_IMAGES.IMG_S18:
//		case PAK_IMAGES.IMG_S19:
//		case PAK_IMAGES.IMG_S20:
//		case PAK_IMAGES.IMG_S21:
//		case PAK_IMAGES.IMG_S22:
//		case PAK_IMAGES.IMG_S23:
//		case PAK_IMAGES.IMG_S24:
//		case PAK_IMAGES.IMG_S25:
//		case PAK_IMAGES.IMG_S26:
//		case PAK_IMAGES.IMG_S27:
//		case PAK_IMAGES.IMG_S28:
//		case PAK_IMAGES.IMG_S29:
//		case PAK_IMAGES.IMG_S30:
//		case PAK_IMAGES.IMG_S31:
//		case PAK_IMAGES.IMG_S32:
//		case PAK_IMAGES.IMG_S33:
//		case PAK_IMAGES.IMG_S34:
//		case PAK_IMAGES.IMG_S35:
//		case PAK_IMAGES.IMG_S36:
//		case PAK_IMAGES.IMG_S37:
//		case PAK_IMAGES.IMG_S38:
//		case PAK_IMAGES.IMG_S39:
//		case PAK_IMAGES.IMG_S40:
//		case PAK_IMAGES.IMG_S41:
//		case PAK_IMAGES.IMG_S42:
//		case PAK_IMAGES.IMG_S43:
//		case PAK_IMAGES.IMG_S44:
//		case PAK_IMAGES.IMG_S45:
//		case PAK_IMAGES.IMG_S46:
//		case PAK_IMAGES.IMG_S47:
//		case PAK_IMAGES.IMG_S48:
//		case PAK_IMAGES.IMG_S49:
//		case PAK_IMAGES.IMG_S50:
//		case PAK_IMAGES.IMG_S51:
//		case PAK_IMAGES.IMG_S52:
//		case PAK_IMAGES.IMG_S53:
//		case PAK_IMAGES.IMG_S54:
//		case PAK_IMAGES.IMG_S55:
//		case PAK_IMAGES.IMG_S56:
//		case PAK_IMAGES.IMG_S57:
//		case PAK_IMAGES.IMG_S58:
//		case PAK_IMAGES.IMG_S59:
//		case PAK_IMAGES.IMG_S60:
//		case PAK_IMAGES.IMG_S61:
//		case PAK_IMAGES.IMG_S62:
//		case PAK_IMAGES.IMG_S63:
//		case PAK_IMAGES.IMG_S64:
//		case PAK_IMAGES.IMG_S65:
//		case PAK_IMAGES.IMG_S66:
//		case PAK_IMAGES.IMG_S67:
//		case PAK_IMAGES.IMG_S68:
//		case PAK_IMAGES.IMG_S69:
//		case PAK_IMAGES.IMG_S70:
//		case PAK_IMAGES.IMG_S71:
//		case PAK_IMAGES.IMG_S72:
//		case PAK_IMAGES.IMG_S73:
//		case PAK_IMAGES.IMG_S74:
		default:
			layer = 1;
			offsetY = 0;
			GameDraw.add_Image(imgIndex, x, y ,
					Tools.BOTTOM_LEFT, Tools.TRANS_NONE, 20+layer);
			break;
//		case PAK_IMAGES.IMG_S75://动画贝壳
//			int motion[]={0,0,0,1,1,1,2,2,2,3,3,3,3,3,3,3,3,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,7,7,7,7,7,7,7,7};
//			GameDraw.add_Image(PAK_IMAGES.IMG_BEIKE,x,
//					y,beike[motion[MyGameCanvas.me.gameTime/4%40]],Tools.BOTTOM_LEFT, Tools.TRANS_NONE, 20+2);
//			GameDraw.add_Image(PAK_IMAGES.IMG_BEIKE,x-20, 
//				  y+20,156,245,164,87,Tools.BOTTOM_LEFT, Tools.TRANS_NONE, 20+1);
//			break;
//		case PAK_IMAGES.IMG_S76:
//			GameDraw.add_Image(PAK_IMAGES.IMG_HAIXING,x,y ,haixing[MyGameCanvas.gameTime/10%7],
//					Tools.BOTTOM_LEFT, Tools.TRANS_NONE, 20+1);
//			break;
//		case PAK_IMAGES.IMG_S77:
//			GameDraw.add_Image(PAK_IMAGES.IMG_SHUICAO,x,y,shuicao[MyGameCanvas.gameTime/12%8],
//					Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 20+1);
//			break;
		}
//		GameDraw.add_Image(imgIndex, x+offsetX-GameEngine.me.iMapX, y + tileHight + offsetY-GameEngine.me.iMapY,
//				Tools.BOTTOM_LEFT, Tools.TRANS_NONE, 20+layer);
	}
	
	
	

	int getThingImageIndex(int mapIndex) {// 装饰
		int index = 0;
		if (mapIndex == 0) {
			return -1;
		}
		for (int i = 0; i < mapThings.length; i++) {
			if (mapThings[i][0] == mapIndex) {
				return getImageName(mapThings[i][1]);
			}
		}

		return -1;

	}

	int getImageName(int mapIndex) {// 装饰
		String name = "s" + mapIndex;
		// System.out.println("   mapIndex:"+mapIndex+"   "+name);
		for (int i = 0; i < PAK_IMAGES.FILESNAME.length; i++) {
			if (name.equals(PAK_IMAGES.FILESNAME[i])) {
				return i;
			}
		}
		return -1;
	}

	 public int getMapIndex(int x, int y) {

			int tx = (x / tileWidth);
			int ty = ((y) / tileHight);
			int n = (ty * mapSize[0] + tx);
			if (n < 0 || n >= mapSize[0] * mapSize[1]) {
				return -1;
			}
//System.out.println("nnnnnnnnnnnnnnnnnn : "+n);
			return n;
		}

	// ******************
	// 计算传入的x,y在地图数据中的位置
	// ******************
	public short inMapData(int layer, int x, int y) {

		int tx = (x / tileWidth);
		int ty = (y / tileHight);

		if (tx < 0 || tx >= mapSize[0] || ty < 0 || ty >= mapSize[1]) {
			return -1;
		}
		int n = (ty * mapSize[0] + tx);

		short index = (short) 0;
		// try {
		index = mapData[layer][n];
		// if (index > 255) {
		// index = (short) (index & 0xff);
		// }
		// } catch (Exception ex) {
		// System.out.println("inMapData");
		// }
		return index;
	}

	static int bToi(byte byte0) {
		int i = byte0;
		if (byte0 < 0) {
			i += 256;
		}
		return i;
	}

	final static DataInputStream getDataInputStream_mapData(String str) {
		DataInputStream dis = null;
		InputStream is = null;
		AssetManager am = MyGameView.context.getResources().getAssets();

		try {
			is = am.open("mapdata/" + str);
		} catch (Exception e) {
			System.out.println("getDataInputStream map:");
			e.printStackTrace();
		}

		dis = new DataInputStream(is);

		return dis;
	}

	short[][] mapThings;

	public void loadMap(String file,boolean ishaveZhuangshi) {
		try {
			mapData = null;
			DataInputStream dis = getDataInputStream_mapData(file);

			mapHight = dis.readShort();
			mapWidth = dis.readShort();
			tileHight = dis.readByte();
			tileWidth = dis.readByte();
			mapSize[1] = mapHight;
			mapSize[0] = mapWidth;
			mapHight = mapHight * tileHight;
			mapWidth = mapWidth * tileWidth;

			int size = (int) (mapSize[0] * mapSize[1]);
			mapData = new short[2][size];
			propData = new short[size];

			System.out.println("size:" + size);
			int kbz = 0;

			for (int i = 0; i < propData.length; i++) {

				if (i == 0) {
					propData[i] = dis.readShort();
					kbz = propData[0];
					// propData[i]--;
				} else {

					propData[i] = dis.readShort();
					if (propData[i] > 0) {
						propData[i] -= kbz;
					} else {
						propData[i] = -1;
					}
					// propData[i]--;
				}

				// System.out.println(","+propData[i]);
			}

//			System.out.println("kbz:" + kbz);
			for (int i = 0; i < mapData[0].length; i++) {
		if (i == 0) {
					mapData[0][i] = dis.readShort();
					kbz = mapData[0][0] ;
					// propData[i]--;
				}else{

					mapData[0][i] = dis.readShort();
					if (mapData[0][i] > 0) {
						mapData[0][i] -= kbz;
					} else {
						mapData[0][i] = -1;
					}

}

			//	mapData[0][i] = dis.readShort();
			//	mapData[0][i]--;
				// System.out.print(mapData[0][i] + ",");
			}

			
			if(ishaveZhuangshi){
			for (int i = 0; i < mapData[1].length; i++) {
				mapData[1][i] = dis.readShort();
			}

			int len = dis.readShort();
			mapThings = new short[len][4];
			for (int i = 0; i < mapThings.length; i++) {
				mapThings[i][0] = dis.readShort();
				mapThings[i][1] = dis.readShort();
				mapThings[i][2] = dis.readShort();
				mapThings[i][3] = dis.readShort();
		//		System.out.println(mapThings[i][0] + "," + mapThings[i][1] + ","
		//				+ mapThings[i][2] + "," + mapThings[i][3]);
			}
			
			
			for (int i = 0; i < mapData[1].length; i++) {
				 mapData[1][i]= (short) getThingImageIndex(mapData[1][i]);
			}
			
			}

			
//			int zhuangshiIndex = getThingImageIndex(mapData[LAYER_1][i]);
			
			
			System.out.println("end");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("load map error");
		}
	}

	static int isHave(Vector vec, int t) {
		for (int j = 0; j < vec.size(); j++) {
			int temp = ((Integer) vec.elementAt(j)).intValue();
			if (temp == t) {
				return -1;
			}
		}
		return t;
	}

	/************************* 测试 ***********************************/

	// 如果mapData[index]>255，说明有翻转，如果没有翻转那块数就是mapData[index],如果有翻转mapData[index]
	// & 0xff就是块数
	short getIndex(int layer, int i) { // 哪块切片
		short index = mapData[layer][i];
		// if (index > 255) {
		// index = (short) (index & 0xff);
		// }
		return index;
	}

	// &0x200是上下，&0x100是水平
	byte getTrans(int layer, int index) {
		byte trans = -1;
		if ((mapData[layer][index] & 0x300) == 0x300) {
			trans = Tools.TRANS_HV;
		} else if ((mapData[layer][index] & 0x200) == 0x200) {
			trans = Tools.TRANS_H;
		} else if ((mapData[layer][index] & 0x100) == 0x100) {
			trans = Tools.TRANS_V;
		} else {
			trans = Tools.TRANS_NONE;
		}
		return trans;
	}

	// ////////////////////////地图属性判断////////////////////////////////////////

	public final static byte TILE_全部通过 = -1;

	public final static byte TILE_0全部阻挡 = 0;

	public final static byte TILE_1左右通过和下方通过 = 1;
	public final static byte TILE_2上斜坡 = 2;
	public final static byte TILE_3下斜坡 = 3;
	public final static byte TILE_4斜坡下一块 = 4;

	boolean getProp(int index, int tileprop) {
		short prop = propData[index];
		switch (prop) {
		case -1:
			prop = TILE_全部通过;
			break;
		case 0:
			prop = TILE_0全部阻挡;
			break;
		case 1:
			prop = TILE_1左右通过和下方通过;
			break;
		case 2:
			prop = TILE_2上斜坡;
			break;
		case 3:
			prop = TILE_3下斜坡;
			break;
		case 4:
			prop = TILE_4斜坡下一块;
			break;
		default:
			prop = TILE_全部通过;
			break;
		}
		return prop == tileprop;
	}

	// 是否可以向前走
	public boolean canRun(int x, int y) {
		if (mapData == null) {
			return true;
		}
		int mapIndex = getMapIndex(x, y);

		if (mapIndex == -1) {
			return true;
		}
		if (propData[mapIndex] == -1) {
			return true;
		}

		if (getProp(mapIndex, TILE_2上斜坡) || getProp(mapIndex, TILE_4斜坡下一块)
				|| getProp(mapIndex, TILE_3下斜坡)
				|| getProp(mapIndex, TILE_1左右通过和下方通过)

				|| getProp(mapIndex, TILE_全部通过)

		) {
			return true;
		}

		return false;
	}

	public boolean canSlant_RIGHT(int x, int y) { // 斜走
		if (mapData == null) {
			return false;
		}

		int mapIndex = getMapIndex(x, y);
		if (mapIndex == -1) {
			return false;
		}
		if (getProp(mapIndex, TILE_3下斜坡)) {
			return true;
		}
		return false;
	}

	public boolean canSlant_LEFT(int x, int y) { // 斜走
		if (mapData == null) {
			return false;
		}

		int mapIndex = getMapIndex(x, y);
		if (mapIndex == -1) {
			return false;
		}
		if (getProp(mapIndex, TILE_2上斜坡)) {

			return true;
		}
		return false;
	}

	public final boolean isLadder(int x, int y) {
		if (mapData == null) {
			return false;
		}

		int mapIndex = getMapIndex(x, y);
		if (mapIndex == -1) {
			return false;
		}
		if (getProp(mapIndex, TILE_4斜坡下一块)) {
			return true;
		}
		return false;
	}

	public boolean canUp(int x, int y) {
		if (mapData == null) {
			return true;
		}
		int mapIndex = getMapIndex(x, y);
		if (mapIndex == -1) {
			return false;
		}
		if (propData[mapIndex] == -1) {
			return true;
		}
		if (getProp(mapIndex, TILE_2上斜坡) || getProp(mapIndex, TILE_4斜坡下一块)
				|| getProp(mapIndex, TILE_3下斜坡)
				|| getProp(mapIndex, TILE_1左右通过和下方通过)
				|| getProp(mapIndex, TILE_全部通过)) {
			return true;
		}

		return false;
	}

	public final boolean canFall(int x, int y) {
		if (mapData == null) {
			return true;
		}
		int mapIndex = getMapIndex(x, y);

		if (mapIndex == -1) {
			return true;
		}

		if (propData[mapIndex] == -1) {
			// Log.i("canFall", ":222222222222222222");
			return true;
		}

		if (getProp(mapIndex, TILE_2上斜坡)

		|| getProp(mapIndex, TILE_3下斜坡) || getProp(mapIndex, TILE_全部通过)) {
			// Log.i("canFall", ":23333333333333333333333");
			return true;
		}

		return false;
	}

	
}
