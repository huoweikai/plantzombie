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

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import com.haopu.JSGame.MyGameCanvas;

import android.content.res.AssetManager;

public class GameDataInputStream {
	public GameDataInputStream() {
	}

	public final static String loadTxt(String strName, boolean haveSpace) {
	    String strReturn = "";
	    int ic;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    DataOutputStream dos = new DataOutputStream(baos);
	    byte[] myData;
	    byte[] buffer = new byte[2048];
	    try {
			InputStream in = null;
			AssetManager am = MyGameCanvas.context.getResources().getAssets();
			in = am.open("bin/" +strName);
	    	
//	      InputStream in = "".getClass().getResourceAsStream(
//	          "/bin/" + strName);
	      if (in != null) {
	        while ( (ic = in.read(buffer)) > 0) {
	          dos.write(buffer, 0, ic);
	        }
	        myData = baos.toByteArray();
	        strReturn = new String(myData, "UTF-8");
	       // Log.i("loadTxt", strReturn+";");
	        in.close();
	      }
	      else {
	        System.out.println("loadTxt null:" + strName);
	      }
	      dos.close();
	      baos.close();
	    }
	    catch (Exception e) {
	      System.out.println("txt load error:" + strName);
	      e.printStackTrace();
	    }
	    return strReturn.substring(haveSpace ? 1 : 0, strReturn.length());
	  }

	  public final static String[] splitString(String src, String key) {
	    Vector vt = new Vector();
	    int w = 0;
	    boolean end = false;
	    while (!end) {
	      int pos = src.indexOf(key, w);
	      if (pos == -1) {
	        pos = src.length();
	        end = true;
	      }
	      int endIndex = pos;
	      if (pos > 0 && src.charAt(pos - 1) == '\r') {
	        endIndex = pos - 1;
	      }
	      String s = src.substring(w, endIndex).trim();
	      if (!s.equals("")) {
	        vt.addElement(s);
	      }
	      w = pos + 1;
	    }
	    String[] cs = new String[vt.size()];
	    vt.copyInto(cs);
	    vt = null;
	    return cs;
	  }
	
	
	
	
	
//	public static String PATH_IMAGES = "images/";
//	public static String PATH_MAPIMAGES = "mapimages/";
//	public static String PATH_MAPDATA = "mapdata/";
//	public static String PATH_MUSIC = "music/";
//	
//	/**
//	 * 閻犲洩顕цぐ鍥捶閺夋寧绂堥柡鍌氭矗濞嗭拷,
//	 */
//	public	final static DataInputStream  getDataInputStream_mapData(int file){
//		DataInputStream dis = null;
//		InputStream is = null;
//		AssetManager am = MyGameView.context.getResources().getAssets();
//		try {
//			is = am.open("mapdata/" +PAK_MAPDATA.FILESNAME[file]+ ".bin");
//			dis = new DataInputStream(is);
////			am.close();			
////			is.close();
//		} catch (IOException e) {
//			System.out.println("getDataInputStream_mapData:"+file);
//			e.printStackTrace();
//		}
//		return dis;
//	}
//	
//	
//	/**
//	 * 閻犲洩顕цぐ鍢糹n闁哄倸娲ｅ▎锟�
//	 */
//	public final static DataInputStream  getDataInputStream_binData(int file){
//		DataInputStream dis = null;
//		InputStream is = null;
//		AssetManager am = MyGameView.context.getResources().getAssets();
//		try {
//			is = am.open("bin/" +PAK_BIN.FILESNAME[file]+ ".bin");
//			dis = new DataInputStream(is);
//	
////		am.close();			
////			is.close();
//		} catch (IOException e) {
//			System.out.println("getDataInputStream_binData:"+PAK_BIN.FILESNAME[file]);
//			e.printStackTrace();
//		}
//		
//		return dis;
//	}
//	
//	
//
//	public final static short[][] initData_2(int file) { // 0 clipdata闁挎冻鎷�checkdata
//		// 2濞寸姰鍎遍幃姊杛amedata
//		short[][] kbz = null;
//		try {
//			
//			InputStream is = null;
//			AssetManager am = MyGameView.context.getResources().getAssets();
//			is = am.open("bin/" +PAK_BIN.FILESNAME[file]+ ".bin");
//		//	dis = new DataInputStream(is);
//			
//			DataInputStream tempDS = new DataInputStream(is);
//			short[] PIC_CLIP_INFO;
//			short[][] PIC_FRAME_INFO;
//			short[] ANIM_CHECK_RANGE;
//
//			int k1 = 0;
//			int i1 = tempDS.readShort();
//			k1 += 2;
//			PIC_CLIP_INFO = new short[i1 * 4];
//			for (int i = 0; i < PIC_CLIP_INFO.length; i++) {
//				PIC_CLIP_INFO[i] = tempDS.readShort();
//			}
//
//			i1 = tempDS.readShort();
//
//			ANIM_CHECK_RANGE = new short[i1 * 8];
//			PIC_FRAME_INFO = new short[i1][];
//
//			for (int m = 0; m < i1; m++) {
//				try {
//					int i3 = tempDS.readShort();
//					k1 += 2;
//					int i4 = i3 << 2;
//					int i5 = m << 3;
//					PIC_FRAME_INFO[m] = new short[i4];
//					k1 += READ_DETAIL_DATEaa(tempDS, PIC_FRAME_INFO[m], 0, i4); // SpriteList
//				} catch (Exception e) {
//				}
//			}
//
//			for (int m = 0; m < i1 * 8; m++) {
//				ANIM_CHECK_RANGE[m] = tempDS.readShort();
//			}
//
//			kbz = new short[2 + PIC_FRAME_INFO.length][];
//			kbz[0] = new short[PIC_CLIP_INFO.length];
//			for (int i = 0; i < PIC_CLIP_INFO.length; i++) {
//				kbz[0][i] = PIC_CLIP_INFO[i];
//			}
//			kbz[1] = new short[ANIM_CHECK_RANGE.length];
//			for (int i = 0; i < ANIM_CHECK_RANGE.length; i++) {
//				kbz[1][i] = ANIM_CHECK_RANGE[i];
//			}
//			for (int i = 0; i < PIC_FRAME_INFO.length; i++) {
//				kbz[2 + i] = new short[PIC_FRAME_INFO[i].length];
//				for (int j = 0; j < PIC_FRAME_INFO[i].length; j++) {
//					kbz[2 + i][j] = PIC_FRAME_INFO[i][j];
//				}
//			}
//			
////			am.close();
////			is.close();
//		
//		} catch (Exception e) {
//			System.out.println("initData_2:" + PAK_BIN.FILESNAME[file]);
//			e.printStackTrace();
//			
//		}
//		return kbz;
//	}
//
//	static int bToi(byte byte0) {
//		int i = byte0;
//		if (byte0 < 0) {
//			i += 256;
//		}
//		return i;
//	}
//
//	int READ_BASED_DATE(InputStream is) throws Exception {
//		int k1 = (is.read() & 0xff) << 8;
//		int k2 = (is.read() & 0xff);
//		return k1 | k2;
//	}
//
//	static int READ_DETAIL_DATE(InputStream is, short aShort[], int i1, int i2) {
//		try {
//			for (int k = 0; k < i2; k++) {
//				int k1 = (is.read() & 0xff) << 8;
//				int k2 = (is.read() & 0xff);
//
//				aShort[i1 + k] = (short) (k1 | k2);
//				// HLUtil.debugPrintln("abyte[i1 + k]: " + abyte[i1 + k]);
//			}
//
//			return i2;
//		} catch (Exception _ex) {
//			return -1;
//		}
//	}
//
//	static int READ_DETAIL_DATEaa(DataInputStream is, short aShort[], int i1,
//			int i2) {
//		try {
//			for (int k = 0; k < i2; k++) {
//				aShort[i1 + k] = is.readShort();
//			}
//			return i2;
//		} catch (Exception _ex) {
//			return -1;
//		}
//	}
//
//	static int READ_DETAIL_DATEbb(DataInputStream is, byte aByte[], int i1,
//			int i2) {
//		try {
//			for (int k = 0; k < i2; k++) {
//				aByte[i1 + k] = is.readByte();
//			}
//			return i2;
//		} catch (Exception _ex) {
//			return -1;
//		}
//	}
	

}
