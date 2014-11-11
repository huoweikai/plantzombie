package com.haopu.kbz;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import com.haopu.JSGame.MyActivity;
import com.haopu.JSGame.MyGameCanvas;
import com.haopu.JSGame.MyGameView;
import com.haopu.pak.*;



import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.TextView;

public final class Tools {

	public Tools() {

	}

	public static int setOffX = 0;
	public static int setOffY = 0;
	static int SCREEN_WIDTH = 0;
	static int SCREEN_HEIGHT = 0;
	static int VMWidth;
	static int VMHeight;
	
	//public static Bitmap[] imgs = new Bitmap[10];
	//public static Bitmap[][] imgs_alpha = new Bitmap[10][5]; // ?????????????4???????


	static SurfaceView view;
	static int TextSize;
	
	
	/*
	 * 游戏的 一些设置参数
	 * @param screen_w
	 *            屏幕宽度，自己 定义
	 * @param screen_h
	 *            屏幕高度，自己 定义
	 * @param max
	 *            最大画图数，
	 * @param gameCanvas2
	 *            SurfaceView
	 * @param vmW
	 *         屏幕实际宽度，自适应用
	 * @param vmH
	 *            屏幕实际高度，自适应用
	 * @param sprite
	 *           被攻击的主角
	 */
	public static void setWH(int screen_w, int screen_h, int max,
			String[] name, SurfaceView gameCanvas2,int vmW,int vmH) {
		VMWidth=vmW;//实际屏幕尺寸
		VMHeight=vmH;
		SCREEN_WIDTH = screen_w;//自定义的尺寸
		SCREEN_HEIGHT = screen_h;
		MAX = max;
//		imgs = new Bitmap[name.length];
//		imgs_alpha = new Bitmap[name.length][4];
		// FILESNAME = name;

		drawObj = new int[MAX];
		clipX = new int[MAX];
		clipY = new int[MAX];
		clipW = new int[MAX];
		clipH = new int[MAX];
		x = new int[MAX];
		y = new int[MAX];
		w = new int[MAX];
		h = new int[MAX];
		rw = new int[MAX];
		rh = new int[MAX];
		drawLevel = new int[MAX]; //
		imgIndex = new int[MAX]; //
		anchor = new int[MAX];
		trans = new int[MAX];
		isFill = new boolean[MAX];
		color = new int[MAX];
		str = new String[MAX];
		type = new int[MAX];
		scaleX = new float[MAX];
		scaleY = new float[MAX];
		rotate = new float[MAX];
		view = gameCanvas2;
		TextSize = 18;
	}

	public static void setOffXY(int x, int y) {
		setOffX = x;
		setOffY = y;
	}

	public static void drawShadow(int x, int y, int w, int h, int lev) {
		// GameDraw.addObject(Tools.TYPE_ARC, x, y, w, h, 0, 360, true,
		// Tools.BOTTOM_LEFT, 0x3B3B3B, lev);
	}

	public static final int ROLE_LAYER = 20;
	public static final int MAP_LAYER = 10;
	public static final int SING_LAYER = 15;

	public static final int MAP_START = 200;

	public final static int TOP_LEFT = 0;
	public final static int BOTTOM_LEFT = 1;
	public final static int HCENTER_VCENTER = 2;
	public final static int HCENTER_BOTTOM = 3;
	public final static int HCENTER_TOP = 4;
	public final static int TOP_RIGHT = 5;
	public final static int BOTTOM_RIGHT = 6;
	public final static int VCENTER_LEFT = 7;
	public final static int VCENTER_RIGHT = 8;

	public static final byte TRANS_NONE = 0;
	public static final byte TRANS_H = 1;
	public static final byte TRANS_V = 2;
	public static final byte TRANS_HV = 3;
	
	public final static byte TRANS_R90 = 4;
	public final static byte TRANS_XR = 5;
	public final static byte TRANS_YR = 6;
	public final static byte TRANS_HV_R90 = 7;

	// public static String[] FILESNAME;
	
	private static HashMap<Integer, Bitmap> bitmaps = new HashMap<Integer, Bitmap>();
	
	

	 public static Bitmap getImage_dise(  Bitmap image) { //去除底色图片，底色为0xffC60B98
	      int w = image.getWidth();
	      int h = image.getHeight();
	      int[] srcRgb = new int[w * h];
	      Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
	      image.getPixels(srcRgb, 0, w, 0, 0, w, h);
	      for (int i = 0; i < srcRgb.length; i++) {
	    	  if( srcRgb[i]==0xffC60B98){
	    	   srcRgb[i] = 0x00ffffff; 
	    	  }
	      }
	      bitmap.setPixels(srcRgb, 0, w, 0, 0, w, h);
//	      System.out.println("getImage_dise")
	      return bitmap;
	    }

	
		public static int  size;
		public static Bitmap getImage(int index) {
			if(index>1000){
				Bitmap bitmap = bitmaps.get(index);
		//		ImageUtil.getImage(index);
				return bitmap;
			}
			Bitmap bitmap = bitmaps.get(index);
			if (bitmap == null || bitmap.isRecycled()) {
				try {
					bitmap =loadBmp(PAK_IMAGES.FILESNAME[index]);	
					
					//小图（480）
					if(MyGameCanvas.zooming==1){
						Matrix matrix=new Matrix();
						matrix.postScale(1/0.6f, 1/0.67f);
						Bitmap temp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
						bitmap=temp;
					}
					
//					大图（800）
//					if(MyGameCanvas.zooming !=1){
//						Matrix matrix=new Matrix();
//						matrix.postScale(0.5f, 0.5f);
//						Bitmap temp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
//						bitmap=temp;
//					}
			
//					bitmap=getImage_dise(bitmap);
					bitmaps.put(index, bitmap);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return bitmap;
		}
	
	public static Bitmap oldRemeber(Bitmap bmp,int color) {
		// 速度测试
		long start = System.currentTimeMillis();
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);

		for (int i = 0; i < pixels.length; i++) {
			if ((pixels[i] & 0x00FFFFFF) != 0x00ffffff) { // 底色
				pixels[i] = (pixels[i] & color ); // 修改最高2位的值
			} else {
				pixels[i] = 0x00ffffff;
			}
		}
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		long end = System.currentTimeMillis();
//		Log.e("may", "used time=" + (end - start));
		return bitmap;
	}

	

//	public static Bitmap loadBmp(String index) {
//		Bitmap Bmp = null;
//		AssetManager am = view.getResources().getAssets();
//		InputStream is = null;
//		
//		   BitmapFactory.Options opt = new BitmapFactory.Options();  
//	        opt.inPreferredConfig = Bitmap.Config.RGB_565;   
//	       opt.inPurgeable = true;  
//	       opt.inInputShareable = true;  
//		
//		
//		try {
//			is = am.open("images/" + index + ".png");
//			Bmp = BitmapFactory.decodeStream(is,null,opt);
//			is.close();
//		} catch (IOException ex) {
//			System.out.println("loadBmp error:"+index);
//			ex.printStackTrace();
//		}
//		return Bmp;
//	}


//	public static Bitmap loadBmp(String index) {
//		Bitmap Bmp = null;
//		AssetManager am = view.getResources().getAssets();
//		InputStream is = null;  		
//		try {
//			is = am.open("images/" + index + ".png");
//			Bmp = BitmapFactory.decodeStream(is);
//			is.close();
//		} catch (IOException ex) {
//			System.out.println("loadBmp error:"+index);
//			ex.printStackTrace();
//		}
//		return Bmp;
//	}
	public static Bitmap loadBmp(String index) {
		Bitmap Bmp = null;
		AssetManager am = view.getResources().getAssets();
		InputStream is = null;  		
		try {
			is = am.open("images/" + index );
			Bmp = BitmapFactory.decodeStream(is);

			is.close();
		} catch (IOException ex) {
			System.out.println("loadBmp error:"+index);
			ex.printStackTrace();
		}
		return Bmp;
	}
	
	
	public static Bitmap loadMapBmp(String index) {
		Bitmap Bmp = null;
		AssetManager am = view.getResources().getAssets();
		InputStream is = null;  		
		try {
			is = am.open("map/" + index + ".png");
			Bmp = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException ex) {
			System.out.println("loadBmp error:"+index);
			ex.printStackTrace();
		}
		return Bmp;
	}
	
	
	
	
	
	
	
	
	public static int sToi(short byte0) {
		int i = byte0;
		if (byte0 < 0) {
			i += 65536;
		}
		return i;
	}

	// public static byte[] getDat(int index) {
	// return openPack_2("mapdata.pak", index);
	// }

	/************************************ ????? *********************************************/

	public static int[] alphaColor = { 0x00ffffff, 0x00ff0000, 0x0000ff00,
			0x000000ff };

	// final static int alphaColor_0 = 0x00FFFFFF;

	public final static int alphaImage_0 = 0;
	public final static int alphaImage_1 = 1;
	public final static int alphaImage_2 = 2;
	public final static int alphaImage_3 = 3;

	// public static Image getAlphaImage(int index, int division, int
	// colorIndex) { //????????
	// Image image = (Image) imgs_alpha[index];
	// if (image == null) {
	// image = getImage(index);
	// imgs_alpha[index] = createAlphaImage(image, 0x00FF00FF);
	// }
	// return imgs_alpha[index];
	// }

	static Bitmap[][] imgGroup= new Bitmap[2][];

	public static Bitmap[] createMapImage(int[] dat, int gameRank) {
		imgGroup[1] = new Bitmap[dat.length];
		// for (int i = 0; i < dat.length; i++) {
		// int temp = dat[i];
		// try {
		//
		// imgGroup[1][i] = Bitmap.createImage("/map0/" + temp + ".png");
		//
		// }
		// catch (IOException ex) {
		// // ex.printStackTrace();
		// System.out.println("map Image error:" + temp);
		// }
		// // imgs.put(indexs[i], images[i]);
		// }
		return null;
	}

	public static void removeImage(int index) {
		
//		if (imgs[index] == null) {
//			return;
//		}
//		if (!imgs[index].isRecycled()) {
//			imgs[index].recycle();
//			imgs[index] = null;
//			
//		}
//		System.out.println("shan");
		Bitmap bitmap = bitmaps.remove(index);
				if (bitmap != null && !bitmap.isRecycled()){
//					bitmap.recycle();
					bitmap=null;
//					size -= bitmap.getWidth()*bitmap.getHeight();  
			}
	}
	public static void removeAllImage() {
		size = 0;
		Object object[] = bitmaps.keySet().toArray();
		for (int i = 0; i < object.length; i++) {
			removeImage((Integer) object[i]);
		}
//		bitmaps.clear();
//		System.gc();
	}

	public static void drawClipImage(Canvas canvas, Paint paint, Bitmap bmp,
			int x, int y, int clipX, int clipY, int clipW, int clipH,
			int anchor, int trans) {
		int x1 = 0, y1 = 0;
		switch (trans) {
		case Tools.TRANS_HV:
			canvas.rotate(180, x, y);
//			y1 = clipH;
//			x1 = clipW;
			break;
		case Tools.TRANS_H:
			canvas.scale(-1, 1);
//			x = -x - clipW;
			break;
		case Tools.TRANS_V:
			canvas.scale(1, -1);
//			y = -y - clipH;
			break;
		case Tools.TRANS_HV_R90:
			canvas.rotate(270, x, y);
//			x1 = clipW;
			break;
		case Tools.TRANS_YR:
			canvas.scale(-1, 1, x, y);
			canvas.rotate(270, x, y);
//			x1 = clipW;
//			y1 = clipH;
			break;
		case Tools.TRANS_R90:
			canvas.rotate(90, x, y);
//			y1 = clipH;
			break;
		case Tools.TRANS_XR:
			canvas.scale(-1, 1, x, y);
			canvas.rotate(90, x, y);
			break;
		}
		switch (anchor) {
		case Tools.BOTTOM_LEFT:
			switch (trans) {
			case Tools.TRANS_HV:
				y1 = clipH;
				x1 = clipW;
				break;
			case Tools.TRANS_H:

				x = -x - clipW;
				break;
			case Tools.TRANS_V:

				y = -y - clipH;
				break;
			case Tools.TRANS_HV_R90:
				x1 = clipW;
				break;
			case Tools.TRANS_YR:
				x1 = clipW;
				y1 = clipH;
				break;
			case Tools.TRANS_R90:
				y1 = clipH;
				break;
			case Tools.TRANS_XR:

				break;
			}
			y -= clipH;
//			if(trans==Tools.TRANS_H){
//				x = -x - clipW;	
//			}
			break;
		case Tools.BOTTOM_RIGHT:
			switch (trans) {
			case Tools.TRANS_HV:

				y1 = clipH;
				x1 = clipW;
				break;
			case Tools.TRANS_H:

				x = -x - clipW;
				break;
			case Tools.TRANS_V:

				y = -y - clipH;
				break;
			case Tools.TRANS_HV_R90:
				x1 = clipW;
				break;
			case Tools.TRANS_YR:
				x1 = clipW;
				y1 = clipH;
				break;
			case Tools.TRANS_R90:
				y1 = clipH;
				break;
			case Tools.TRANS_XR:

				break;
			}
			x -= clipW;
			y -= clipH;
//			if(trans==Tools.TRANS_H){
//				x = -x - clipW;	
//			}
			break;
		case Tools.HCENTER_TOP:
			switch (trans) {
			case Tools.TRANS_HV:

				y1 = clipH;
				x1 = clipW;
				break;
			case Tools.TRANS_H:

				x = -x;
//				x += clipW / 2;
				break;
			case Tools.TRANS_V:

				y = -y - clipH;
				break;
			case Tools.TRANS_HV_R90:
				x1 = clipW;
				break;
			case Tools.TRANS_YR:
				x1 = clipW;
				y1 = clipH;
				break;
			case Tools.TRANS_R90:
				y1 = clipH;
				y+=clipH/2;
				x += clipW;
				break;
			case Tools.TRANS_XR:

				break;
			}
//			if(trans!=Tools.TRANS_H)
			x -= clipW / 2;
			
//			if(trans==Tools.TRANS_H){
//				x = -x - clipW;	
//			}
			
			break;
		case Tools.HCENTER_VCENTER:
			switch (trans) {
			case Tools.TRANS_HV:

				y1 = clipH;
				x1 = clipW;
				break;
			case Tools.TRANS_H:

				x = -x;
				break;
			case Tools.TRANS_V:

				y = -y - clipH;
				break;
			case Tools.TRANS_HV_R90:
				x1 = clipW;
				break;
			case Tools.TRANS_YR:
				x1 = clipW;
				y1 = clipH;
				break;
			case Tools.TRANS_R90:
				y1 = clipH;
				break;
			case Tools.TRANS_XR:

				break;
			}
//			if(trans!=Tools.TRANS_H){
			x -= clipW / 2;
			y -= clipH / 2;
//			}
			
//			if(trans==Tools.TRANS_H){
//				x = -x - clipW;	
//			}
			break;
		case Tools.HCENTER_BOTTOM:
			switch (trans) {
			case Tools.TRANS_HV:

				y1 = clipH;
				x1 = clipW;
				break;
			case Tools.TRANS_H:

				x = -x - clipW;
				break;
			case Tools.TRANS_V:

				y = -y - clipH;
				break;
			case Tools.TRANS_HV_R90:
				x1 = clipW;
				break;
			case Tools.TRANS_YR:
				x1 = clipW;
				y1 = clipH;
				break;
			case Tools.TRANS_R90:
				y1 = clipH;
				break;
			case Tools.TRANS_XR:

				break;
			}
			x -= clipW / 2;
			y -= clipH;
//			if(trans==Tools.TRANS_H){
//				x = -x - clipW;	
//			}
			break;
		case Tools.TOP_RIGHT:
			switch (trans) {
			case Tools.TRANS_HV:

				y1 = clipH;
				x1 = clipW;
				break;
			case Tools.TRANS_H:

				x = -x - clipW;
				break;
			case Tools.TRANS_V:

				y = -y - clipH;
				break;
			case Tools.TRANS_HV_R90:
				x1 = clipW;
				break;
			case Tools.TRANS_YR:
				x1 = clipW;
				y1 = clipH;
				break;
			case Tools.TRANS_R90:
				y1 = clipH;
				break;
			case Tools.TRANS_XR:

				break;
			}
			x -= clipW;
//			if(trans==Tools.TRANS_H){
//				x = -x - clipW;	
//			}
			break;
		case Tools.VCENTER_LEFT:
			switch (trans) {
			case Tools.TRANS_HV:

				y1 = clipH;
				x1 = clipW;
				break;
			case Tools.TRANS_H:

				x = -x - clipW;
				break;
			case Tools.TRANS_V:

				y = -y - clipH;
				break;
			case Tools.TRANS_HV_R90:
				x1 = clipW;
				break;
			case Tools.TRANS_YR:
				x1 = clipW;
				y1 = clipH;
				break;
			case Tools.TRANS_R90:
				y1 = clipH;
				break;
			case Tools.TRANS_XR:

				break;
			}
			y -= clipH / 2;
//			if(trans==Tools.TRANS_H){
//				x = -x - clipW;	
//			}
			break;
		case Tools.VCENTER_RIGHT:
			switch (trans) {
			case Tools.TRANS_HV:

				y1 = clipH;
				x1 = clipW;
				break;
			case Tools.TRANS_H:

				x = -x - clipW;
				break;
			case Tools.TRANS_V:

				y = -y - clipH;
				break;
			case Tools.TRANS_HV_R90:
				x1 = clipW;
				break;
			case Tools.TRANS_YR:
				x1 = clipW;
				y1 = clipH;
				break;
			case Tools.TRANS_R90:
				y1 = clipH;
				break;
			case Tools.TRANS_XR:

				break;
			}
			x -= clipW;
			y -= clipH / 2;
//			if(trans==Tools.TRANS_H){
//				x = -x - clipW;	
//			}
			break;
			
		case Tools.TOP_LEFT:
			switch (trans) {
			case Tools.TRANS_HV:

				y1 = clipH;
				x1 = clipW;
				break;
			case Tools.TRANS_H:

				x = -x - clipW;
				break;
			case Tools.TRANS_V:

				y = -y - clipH;
				break;
			case Tools.TRANS_HV_R90:
				x1 = clipW;
				break;
			case Tools.TRANS_YR:
				x1 = clipW;
				y1 = clipH;
				break;
			case Tools.TRANS_R90:
				y1 = clipH;
				break;
			case Tools.TRANS_XR:

				break;
			}
//			if(trans==Tools.TRANS_H){
//				x = -x - clipW;	
//			}
			break;		
		}
		canvas.clipRect(x - x1, y - y1, x - x1 + clipW, y - y1 + clipH);

		
//		Matrix cm = new Matrix(); 
//		cm.setRotate(30);
   
	canvas.drawBitmap(bmp, x - x1 - clipX, y - y1 - clipY, paint);
		

	//	cm.postTranslate( x - x1 - clipX, y - y1 - clipY);
	//	 canvas.drawBitmap(bmp, cm, paint); 
	
		

	//	cm.postTranslate( x - x1 - clipX, y - y1 - clipY);
	//	 canvas.drawBitmap(bmp, cm, paint); 
	
		

	//	cm.postTranslate( x - x1 - clipX, y - y1 - clipY);
	//	 canvas.drawBitmap(bmp, cm, paint); 
	}



	public static void drawColorString(Canvas canavs, Paint paint,
			String string, int x, int y, int TextSize, int anchor, int color) {
		// canavs.save();
		paint.setTextSize(TextSize);
		paint.setColor(color);
		Rect rect = new Rect();
		paint.getTextBounds(string, 0, string.length(), rect);
		int StrW = rect.width();
		int StrH = rect.height();
		switch (anchor) {
		case HCENTER_VCENTER:
			x -= (StrW >> 1);
			y += StrH / 2;
			break;
		case TOP_LEFT:
			y += StrH;
			break;
		case TOP_RIGHT:
			x -= StrW;
			y += StrH;
			break;
		case BOTTOM_RIGHT:
			x -= StrW;
			break;

		case HCENTER_TOP:
			x -= StrW / 2;
			y += StrH;
			break;
		case HCENTER_BOTTOM:
			x -= StrW / 2;
			// y += StrH;
			break;
		}
//        SpannableStringBuilder style=new SpannableStringBuilder(string);      
//        style.setSpan(new BackgroundColorSpan(Color.RED),3,5,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);     
//        TextView textview = new TextView(null) ;
//		textview.setText(style);    
//		paint.setMaskFilter(BlurMaskFilter filter)；
//        BlurMaskFilter filter = new BlurMaskFilter(10.0, Blur style);
		canavs.drawText(string, x, y - 5, paint);
		// canavs.restore();
	}


	
	public static void drawColorStringFPS(Canvas canavs, Paint paint,
			String string, int x, int y, int TextSize, int anchor, int color) {
		 canavs.save();
		paint.setTextSize(TextSize);
		paint.setColor(color);
		Rect rect = new Rect();
		paint.getTextBounds(string, 0, string.length(), rect);
		int StrW = rect.width();
		int StrH = rect.height();
		switch (anchor) {
		case HCENTER_VCENTER:
			x -= (StrW >> 1);
			y += StrH / 2;
			break;
		case TOP_LEFT:
			y += StrH;
			break;
		case TOP_RIGHT:
			x -= StrW;
			y += StrH;
			break;
		case BOTTOM_RIGHT:
			x -= StrW;
			break;

		case HCENTER_TOP:
			x -= StrW / 2;
			y += StrH;
			break;
		case HCENTER_BOTTOM:
			x -= StrW / 2;
			// y += StrH;
			break;
		}
		canavs.drawText(string, x, y - 5, paint);
		 canavs.restore();
	}
	
	
	public static void drawRect(Canvas canvas, Paint paint, int x, int y,
			int w, int h, boolean isFill, int color, int anchor) {
		paint.setColor(color);
		if (isFill) {
			paint.setStyle(Style.FILL);// 实心
			// canavs.drawRect(x, y, h, w, paint);
		} else {
			// paint.setColor(color);
			paint.setStyle(Style.STROKE);// 空心
			// canavs.drawRect(x, y, h, w, paint);
		}
		
//		if(color==0xff613838)
//		paint.setAlpha(111);

		switch (anchor) {
		case TOP_LEFT:
			canvas.drawRect(x, y, w + x, h + y, paint);
			break;
		case BOTTOM_LEFT:
			canvas.drawRect(x, y - h, w + x, y, paint);
			break;
		}
	   paint.setAlpha(255);

		// if (isFill[index]) {
		// paint.setColor(color[index]);
		// paint.setStyle(Style.FILL);// ??????
		// canvas.drawRect(endx, endy - h[index], w[index] + endx, y[index],
		// paint);
		// } else {
		// paint.setColor(color[index]);
		// paint.setStyle(Style.STROKE);// ????
		// canvas.drawRect(endx, endy - h[index], w[index] + endx, y[index],
		// paint); //
		// }
	}

	public static final byte TYPE_ROUND_RECT = 0;
	public static final byte TYPE_RECT = 1;
	public static final byte TYPE_ARC = 2;
	public static final byte TYPE_LINE = 3;
	public static final byte TYPE_STRING = 4;
	public static final byte TYPE_IMG = 5;
	public static final byte TYPE_ZOOMIMG = 6;
	public static final byte TYPE_CLIPSTRING = 7;
	public static final byte TYPE_CLIP_IMG = 8;
	public static final byte TYPE_ALPHA_IMG = 9;

	static int[] TImgData = null;
	static int[][] ImageRGB = new int[10][];
	static int[] ImageRGBindex = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };

	public final static boolean isDraw(int dx, int dy, int dw, int dh,
			int anchor) {
		switch (anchor) {
		case TOP_LEFT:
			if ((dx - setOffX > -dw && dx - setOffX < SCREEN_WIDTH)
					&& (dy - setOffY > -dh && dy - setOffY < SCREEN_HEIGHT)) {
				return true;
			}
			break;
		case BOTTOM_LEFT:
			if ((dx - setOffX > -dw && dx - setOffX < SCREEN_WIDTH)
					&& (dy - setOffY > 0 && dy - setOffY < SCREEN_HEIGHT + dh)) {
				return true;
			}
			break;
		default:
			return true;
		}
		return false;
	}

	
	public static int MAX = 4000;
	public static int curIndex = 0;
	public static short max_obj = 0;
	public static int[] drawObj = new int[MAX];
	static int[] clipX = new int[MAX];
	static int[] clipY = new int[MAX];
	static int[] clipW = new int[MAX];
	static int[] clipH = new int[MAX];
	static int[] x = new int[MAX];
	static int[] y = new int[MAX];
	static int[] w = new int[MAX];
	static int[] h = new int[MAX];
	static int[] rw = new int[MAX];
	static int[] rh = new int[MAX];
	public static int[] drawLevel = new int[MAX]; // ??????
	static int[] imgIndex = new int[MAX]; // ??????
	static int[] anchor = new int[MAX];
	static int[] trans = new int[MAX];
	static boolean[] isFill = new boolean[MAX];
	static int[] color = new int[MAX];
	static String[] str = new String[MAX];
	static int[] type = new int[MAX];
	public static float[] scaleX = new float[MAX];//放大缩小
	public static float[] scaleY = new float[MAX];//放大缩小
	public static float[] rotate = new float[MAX];//旋转
	public static int[] Alpha = new int[MAX];//透明度
	static int[] scaleXpoint = new int[MAX];
	static int[] scaleYpoint = new int[MAX];

	// static int[] TextSize = new int[MAX];

	public static void drawMe(Canvas canvas, Paint paint, int index) {
		int endx = Tools.x[index] - setOffX;
		int endy = Tools.y[index] - setOffY;
		
		endx=(int) (endx*MyGameCanvas.zoomingX);
		endy=(int) (endy*MyGameCanvas.zoomingY);
		canvas.save();
		int sw = MyActivity.VMWidth;
		int sh = MyActivity.VMHeight;
//		float dw=sw/(MyGameCanvas.SCREEN_WIDTH*MyGameCanvas.zooming);
//		float dh = sh /( MyGameCanvas.SCREEN_HEIGHT*MyGameCanvas.zooming);
//		float kx = (float) MyGameCanvas.zooming;
//		float ky = (float) MyGameCanvas.zooming;
//		
//		
//		kx=sw/(MyGameCanvas.SCREEN_WIDTH*MyGameCanvas.zooming)*kx;
//		ky = sh /( MyGameCanvas.SCREEN_HEIGHT*MyGameCanvas.zooming)*ky;
//		
//
//
//		canvas.scale(dw, dh);
		float dw=sw/(MyGameCanvas.SCREEN_WIDTH*MyGameCanvas.zoomingX);
		 float dh = sh /( MyGameCanvas.SCREEN_HEIGHT*MyGameCanvas.zoomingY);

		canvas.scale(dw, dh);
		
		
		
//		canvas.scale((float) sw / SCREEN_WIDTH, (float) sh / SCREEN_HEIGHT);
		switch (Tools.type[index]) {
		case TYPE_IMG:
//			if(Tools.scaleX[index]!=1||Tools.scaleY[index]!=1){
//				canvas.scale(Tools.scaleX[index], Tools.scaleY[index],endx,endy);
//			}
			
			
			
			
			if(Tools.imgIndex[index]>=1000){
//				drawClipImage(canvas, paint,ImageUtil.getImage(Tools.imgIndex[index]), endx,
//						endy, clipX[index], clipY[index], clipW[index],
//						clipH[index], anchor[index], trans[index]);
		
			}else{
				
				if(scaleX[index]!=1||scaleY[index]!=1){
					canvas.scale(scaleX[index],scaleY[index],endx,endy);
				}
				
				
				if(Alpha[index]!=255){
					paint.setAlpha(Alpha[index]);
				}
			//	canvas.skew(1, 0);
				if(rotate[index]!=0){
	//				Bitmap bitmap = bitmaps.get(index);
	//				Matrix matrix=new Matrix();
	//				matrix.setRotate(rotate[index]);
	//				bitmap=Bitmap.createBitmap(bitmap, clipX[index], clipY[index], clipW[index],
	//						clipH[index], matrix, true);
					
					canvas.rotate(rotate[index],endx,endy);
//					paint.setAntiAlias(true);
				}
				drawClipImage(canvas, paint, getImage(Tools.imgIndex[index]), endx,
						endy, clipX[index], clipY[index], clipW[index],
						clipH[index], anchor[index], trans[index]);
				
				if(Alpha[index]!=255){
					Alpha[index]=0;
					paint.setAlpha(255);
				}
				if(rotate[index]!=0){
					rotate[index]=0;
					canvas.rotate(0,endx,endy);
				
				}
				if(scaleX[index]!=1||scaleY[index]!=1){
					scaleX[index] = 1;scaleY[index] = 1;
					canvas.scale(1, 1,endx,endy);
				}
				
			}
			

			
			
			break;

		case TYPE_STRING:
			// System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeseeeeee:"+rw[index]);
			drawColorString(canvas, paint, str[index], endx, endy, rw[index],
					anchor[index], color[index]);

			break;

		case TYPE_CLIPSTRING:
			canvas.clipRect(Tools.clipX[index], Tools.clipY[index],
					Tools.clipW[index] + Tools.clipX[index], Tools.clipH[index]
							+ Tools.clipY[index]);

			//
			drawColorString(canvas, paint, Tools.str[index], endx, endy,
					rw[index], anchor[index], color[index]);

			Tools.str[index] = null;
			canvas.clipRect(0, 0, SCREEN_WIDTH,
					SCREEN_HEIGHT);
			break;

		case TYPE_RECT:
			// canvas.save();
			drawRect(canvas, paint, endx, endy, w[index], h[index],
					isFill[index], color[index], anchor[index]);
			// if (isFill[index]) {
			// paint.setColor(color[index]);
			// paint.setStyle(Style.FILL);// ʵ�����
			// canvas.drawRect(endx, endy - h[index], w[index] + endx,
			// y[index], paint);
			// } else {
			// paint.setColor(color[index]);
			// paint.setStyle(Style.STROKE);// ����
			// canvas.drawRect(endx, endy - h[index], w[index] + endx,
			// y[index], paint); //
			// }
			// canvas.restore();
			break;

		case TYPE_ARC:
			// canvas.save();
			// paint.setStyle(Style.FILL);
			// paint.setColor(color[index]);
			
	        RectF oval2 = new RectF(endx, endy, endx+ w[index], endy+ h[index]);// 设置个新的长方形，扫描测量  
	        
	        canvas.drawArc(oval2, Tools.rw[index], Tools.rh[index], true, paint);  
			
//			 canvas.drawArc(canvas, Tools.rw[index], Tools.rh[index], false,
//			 paint);
			// y = endy;
			// if (Tools.anchor[index] != (Graphics.TOP | Graphics.LEFT)) {
			// y = endy - Tools.h[index];
			// }
			// g.setColor(Tools.color[index]);
			// if (isFill[index]) {
			// g.fillArc(endx, y, Tools.w[index], Tools.h[index],
			// Tools.rw[index], Tools.rh[index]);
			// }
			// else {
			// g.drawArc(endx, y, Tools.w[index], Tools.h[index],
			// Tools.rw[index], Tools.rh[index]);
			// }
			break;

		}
		canvas.restore();
		// canavs.re();
	}

	
	
	public static Bitmap blurImageAmeliorate(Bitmap bmp) {
		long start = System.currentTimeMillis();
		// 高斯矩阵
		int[] gauss = new int[] { 1, 2, 1, 2, 4, 2, 1, 2, 1 };
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

		int pixR = 0;
		int pixG = 0;
		int pixB = 0;

		int pixColor = 0;

		int newR = 0;
		int newG = 0;
		int newB = 0;

		int delta = 16; // 值越小图片会越亮，越大则越暗

		int idx = 0;
		int[] pixels = new int[width * height];
		bmp.getPixels(pixels, 0, width, 0, 0, width, height);
		for (int i = 1, length = height - 1; i < length; i++) {
			for (int k = 1, len = width - 1; k < len; k++) {
				idx = 0;
				for (int m = -1; m <= 1; m++) {
					for (int n = -1; n <= 1; n++) {
						pixColor = pixels[(i + m) * width + k + n];
						pixR = Color.red(pixColor);
						pixG = Color.green(pixColor);
						pixB = Color.blue(pixColor);

						newR = newR + (int) (pixR * gauss[idx]);
						newG = newG + (int) (pixG * gauss[idx]);
						newB = newB + (int) (pixB * gauss[idx]);
						idx++;
					}
				}

				newR /= delta;
				newG /= delta;
				newB /= delta;

				newR = Math.min(255, Math.max(0, newR));
				newG = Math.min(255, Math.max(0, newG));
				newB = Math.min(255, Math.max(0, newB));

				pixels[i * width + k] = Color.argb(255, newR, newG, newB);

				newR = 0;
				newG = 0;
				newB = 0;
			}
		}

		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}
	
	
	
	public static Bitmap setAlpha(Bitmap sourceImg, int number) {   
	     int[] argb = new int[sourceImg.getWidth() * sourceImg.getHeight()];   
	     sourceImg.getPixels(argb, 0, sourceImg.getWidth(), 0, 0,sourceImg.getWidth(), sourceImg.getHeight());// 获得图片的ARGB值   
	//     number = number * 255 / 100;   
	     for (int i = 0; i < argb.length; i++) {   
	      argb[i] = (number << 24) | (argb[i]&0x00ff0000);// 修改最高2位的值   
	     }   
	     sourceImg = Bitmap.createBitmap(argb, sourceImg.getWidth(), sourceImg.getHeight(), Config.ARGB_8888);   
	        
	     return sourceImg;   
	   }   
	
	public static boolean getProperties(short val, byte bit) {
		return (val >> bit & 0x01) == 1;
	}

	private static void sort() {
		int cout = 0;
		int temp;
		int j;
		for (int i = 1; i < Tools.max_obj; i++) {
			if (Tools.drawLevel[Tools.drawObj[i]] < Tools.drawLevel[Tools.drawObj[i - 1]]) {
				temp = Tools.drawObj[i];
				j = i - 1;
				do { // ????????????????R[1????i-1]?в???R[i]?????λ??
					Tools.drawObj[j + 1] = Tools.drawObj[j]; // ??????????R[i].key????????
					j--;
					if (j < 0) {
						break;
					}
				} while (Tools.drawLevel[temp] < Tools.drawLevel[Tools.drawObj[j]]); // ??R[i].key??R[j].key????
				Tools.drawObj[j + 1] = temp;
			}
		}
		// System.out.println(cout);
	}

	/*
	 * ???Ч?? src:?? contrast1,light1:?????????????
	 * contrast2,light2:???????????????? x,y,rad,????????
	 */

	// public static int temp[] = new int[50000];
	// public static final int BLUR_LEFT = 0x1;
	// public static final int BLUR_RIGHT = 0x2;
	// public static final int BLUR_BOTH = BLUR_LEFT | BLUR_RIGHT;
	/*
	 * ?????Ч?? src:?? x,y,width,height:???????? blurAmount:???? desAmount:?????
	 * blurType:?????
	 */
	// public static void paintBlurEffect(Image src, Graphics g, int x, int y,
	// int width, int height, int blurAmount,
	// int desAmount, int blurType) {
	// //#if(Preprocessor.HIGH_EFFECTS)
	// try {
	// int buf[] = temp;
	// //dg.getPixels(tempShort, 0, width, 0, 0, width, height, format);
	// int d = blurAmount % 128;
	// int maxBlock = temp.length / width;
	// int block = 80;
	// int yy = 0;
	// boolean done = false;
	// while (!done) {
	// block = height - yy;
	// if (block > maxBlock) {
	// block = maxBlock - 1;
	// }
	// else {
	// done = true;
	// }
	// int size = width * block;
	// src.getRGB(buf, 0, width, x, y + yy, width, block);
	// blurDesaturateBlock(size, buf, d, desAmount, blurType);
	// g.drawRGB(buf, 0, width, x, y + yy, width, block, false);
	// yy += block;
	// }
	// }
	// catch (Exception e) {}
	// }

	// static void blurDesaturateBlock(int size, int buf[], int blurAmount,
	// int desAmount, int burType) {
	// int r1 = 0;
	// int b1 = 0;
	// int r1b1 = 0;
	// int g1 = 0;
	// //blur in both sides
	// //right to left
	// if ( (burType & BLUR_LEFT) == BLUR_LEFT) {
	// for (int k = size - 1; k >= 0; k--) {
	// int c2 = buf[k];
	// int r2b2 = (c2) & 0xFF00FF;
	// int g2 = (c2) & 0xFF00;
	// r1b1 = (r2b2 + ( (r1b1 - r2b2) * blurAmount >> 7)) & 0xFF00FF;
	// g1 = (g2 + ( (g1 - g2) * blurAmount >> 7)) & 0x00FF00;
	// int color = r1b1 | g1;
	// buf[k] = color;
	// }
	// }
	// if ( (burType & BLUR_RIGHT) == BLUR_RIGHT) {
	// for (int k = 0; k < size; k++) {
	// int c2 = buf[k];
	// int r2 = (c2) & 0xFF0000;
	// int g2 = (c2) & 0xFF00;
	// int b2 = (c2) & 0xFF;
	// r1 = r2 + ( (r1 - r2) * blurAmount >> 7);
	// g1 = g2 + ( (g1 - g2) * blurAmount >> 7);
	// b1 = b2 + ( (b1 - b2) * blurAmount >> 7);
	// //the desaturated components
	// int lum = ( (r1 >> 16) + (g1 >> 8) + b1 + 16) >> 2; //the desaturated col
	// (luminosity)
	// int desR = (lum << 16);
	// int desG = (lum << 8);
	// int desB = (lum);
	// desR = r1 + ( (desR - r1) * desAmount >> 7);
	// desG = g1 + ( (desG - g1) * desAmount >> 7);
	// desB = b1 + ( (desB - b1) * desAmount >> 7);
	// int color = (desR & 0xFF0000) | (desG & 0xFF00) | desB;
	// buf[k] = color;
	// }
	// }
	// }
	//
	// public static int[] getPixels(Image src) {
	// int w = src.getWidth();
	// int h = src.getHeight();
	// int[] pixels = new int[w * h];
	// src.getRGB(pixels, 0, w, 0, 0, w, h);
	// return pixels;
	// }
	//
	// public static Image effect_negative(Image src) {
	// int srcW = src.getWidth();
	// int srcH = src.getHeight();
	// int[] srcPixels = getPixels(src);
	// int r = 0;
	// int g = 0;
	// int b = 0;
	// int a = 0;
	// int argb;
	// for (int i = 0; i < srcH; i++) {
	// for (int ii = 0; ii < srcW; ii++) {
	// argb = srcPixels[i * srcW + ii];
	// a = ( (argb & 0xff000000) >> 24); // alpha channel
	// r = 255 - ( (argb & 0x00ff0000) >> 16); // red channel
	// g = 255 - ( (argb & 0x0000ff00) >> 8); // green channel
	// b = 255 - (argb & 0x000000ff); // blue channel
	// srcPixels[i * srcW + ii] = ( (a << 24) | (r << 16) | (g << 8) | b);
	// }
	// }
	// return drawPixels(srcPixels, srcW, srcH);
	// }

	// public static Image drawPixels(int[] pixels, int w, int h) {
	// Image image = Image.createRGBImage(pixels, w, h, true);
	// pixels = null;
	// return image;
	// }

	// Image temp;

	/**************************** ??????? ****************************************************/
	// static int[] srcRgb = new int[SCREEN_WIDTH * SCREEN_HEIGHT];
	// public static void drawdark(Graphics g, int x, int y, int w, int h,
	// int division, int max, int color) {
	// for (int i = 0; i < srcRgb.length; ++i) {
	// srcRgb[i] = ( (division * 0xFF / max) << 24) | (color & 0x00ffffff);
	// }
	// g.drawRGB(srcRgb, 0, w, x, y, w, h, true);
	// }
	/************************************** ?????? **************************************************/

	public  static void TestLog(String str){
		System.out.println(""+str);
	}
	/*
	 * 判断圆与矩形的碰撞
	 * 
	 */
	public static final boolean isArcRectCollides(int arcX, int arcY, int arcR,
			int rectX, int rectY, int rectW, int rectH) {
		int arcOx = arcX + arcR; // 
		int arcOy = arcY + arcR; // 
		if (((rectX - arcOx) * (rectX - arcOx) + (rectY - arcOy)
				* (rectY - arcOy)) <= arcR * arcR) {
			return true;
		}
		if (((rectX + rectW - arcOx) * (rectX + rectW - arcOx) + (rectY - arcOy)
				* (rectY - arcOy)) <= arcR * arcR) {
			return true;
		}
		if (((rectX - arcOx) * (rectX - arcOx) + (rectY + rectH - arcOy)
				* (rectY + rectH - arcOy)) <= arcR * arcR) {
			return true;
		}
		if (((rectX + rectW - arcOx) * (rectX + rectW - arcOx) + (rectY + rectH - arcOy)
				* (rectY + rectH - arcOy)) <= arcR * arcR) {
			return true;
		}
		//
		int minDisX = 0;
		if (arcOy >= rectY && arcOy <= rectY + rectH) {
			if (arcOx < rectX) {
				minDisX = rectX - arcOx;
			} else if (arcOx > rectX + rectW) {
				minDisX = arcOx - rectX - rectW;
			} else {
				return true;
			}
			if (minDisX <= arcR) {
				return true;
			}
		} // 
		int minDisY = 0;
		if (arcOx >= rectX && arcOx <= rectX + rectW) {
			if (arcOy < rectY) {
				minDisY = rectY - arcOy;
			} else if (arcOy > rectY + rectH) {
				minDisY = arcOy - rectY - rectH;
			} else {
				return true;
			}
			if (minDisY <= arcR) {
				return true;
			}
		}
		return false;
	}
	
/**
 *
 * @param cirX圆圈坐标
 * @param cirY
 * @param cirR圆圈半径
 * @param recX矩形坐标
 * @param recY
 * @param recW
 * @param recH
 * @return
 */
//方法有问题	
	public static final boolean IsRectCirHit(int cirX,int cirY,int cirR,int recX,int recY,int recW,int recH){
		int cenX = recX+recW/2 ;//矩形的中心
		int cenY = recY+recH/2 ;
		if(TwoDistance(cirX,cirY,cenX,cenY)<=(Math.abs(cirR)+Math.abs(recW/2))
				|| 	TwoDistance(cirX,cirY,cenX,cenY)<=(Math.abs(cirR)+Math.abs(recH/2))
				){
			return true;
		}
		return false;
	}
/**
 * 求两点间的距离	
 */
	public static final int TwoDistance(int x1,int y1,int x2,int y2){
		int result = -1;
		result = (int) Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
		return result;
	}
/**
 * 画圆	
 */
	public static float sin(float jd){return (float) Math.sin(jd);}
	public static float cos(float jd){return (float) Math.cos(jd);}
	public static float tan(float jd){return (float) Math.tan(jd);}
	public static float atan(float jd){return (float) Math.atan(jd);}
	public static float sqrt(float r){return (float) Math.sqrt(r);}
	public static final float PI=(float) 3.1415926;
	public void DrawCircle(int ox,int oy,int rw,int rh,int color)
	{
		int sx,sy,ex,ey;
		float angle=0;
		
		sx=(int) (cos(angle)*rw+ox);
		sy=(int) (sin(angle)*rh+oy);
		
		while(angle<PI*2)
		{
			angle+=0.1;
			ex=(int) (cos(angle)*rw+ox);
			ey=(int) (sin(angle)*rh+oy);
//			DrawLine(sx,sy,ex,ey,color);
			sx=ex;
			sy=ey;
		}
	}
	
}
