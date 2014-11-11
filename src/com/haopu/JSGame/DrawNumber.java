package com.haopu.JSGame;
//package com.haopu.DufuIsBusy;
//
//import android.util.Log;
//
//import com.haopu.kbz.GameDraw;
//import com.haopu.kbz.Tools;
//
//public class DrawNumber {
//	
//	public final static int Food = 100;
//	public final static int Lianji = 101;
//	public final static int Jinbi = 102;
//	public final static int Gudingzhi = 103;
//	
//	static int iFood;
//	static int iLianji;
//	static int iJinbi;
//	static int iGudingzhi ;
//	
//	
//	public DrawNumber()
//	{
////		iFood = 0;
////	    iJinbi = 0;
////		iLianji =0;
//	}
//	
///*
// * ���ڻ���Ϸ�еĸ���й�����
// * 	x:����x�����
// * y:����y���
// * w:���ֵĿ��
// * type:Ҫ��������
// */
//	
//	public final static void DrawNum(int ImageIndex,int x,int y,int w,int type)
//	{
//		int font[][] ={//��ɫ����
//				{0,0,25,37},{25,0,24,37},//0,1
//				{49,0,26,37},{75,0,23,37},//2,3
//				{98,0,26,37},{124,0,25,37},//4,5
//				{149,0,25,37,},{174,0,26,37},//6,7
//				{200,0,24,37},{224,0,26,37}//8,9
//				
//		};//��ӦҪ�����ֵ�����ͼƬ
//		 int font_jinbi[][] = {//�������
//				 {0,0,20,26},{20,0,25,26},//0,1
//				 {45,0,23,26},{68,0,23,26},//2,3
//				 {91,0,25,26},{116,0,23,26},//4,5
//				 {139,0,24,26},{163,0,23,26},//6,7
//				 {186,0,25,26},{211,0,24,26},//8,9
//				 {235,0,20,26}//-
//		 };
//		 int font_food[][] = {
//    			 {0,0,29,42},{31,0,30,42},//0,1
//    			 {61,0,35,42},{96,0,32,42},
//    			 {128,0,32,42},{160,0,31,42},
//    			 {191,0,33,42},{224,0,30,42},
//    			 {254,0,35,42},{289,0,32,42},
//    	 };
//		int font_lianji[][] = {
//				{0,0,24,37},{24,0,25,37},//0,1
//				{49,0,26,37},{75,0,23,37},
//				{98,0,26,37},{124,0,24,37},
//				{148,0,27,37},{175,0,25,37},
//				{200,0,24,37},{224,0,26,37}
//		};
//		switch(type)
//		{
//		case Food:
//			if(MyGameCanvas.iFood>=0 && MyGameCanvas.iFood<10)
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_food[MyGameCanvas.iFood],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			else if(MyGameCanvas.iFood>=10 && MyGameCanvas.iFood<100)
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_food[MyGameCanvas.iFood/10],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+w, y, font_food[MyGameCanvas.iFood-(10*(MyGameCanvas.iFood/10))],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			else if(MyGameCanvas.iFood>=100 && MyGameCanvas.iFood <1000) 
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_food[MyGameCanvas.iFood/100],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+w, y, font_food[(MyGameCanvas.iFood-(100*(MyGameCanvas.iFood/100)))/10],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+2*w, y, font_food[(MyGameCanvas.iFood-(100*(MyGameCanvas.iFood/100)))-(10*((MyGameCanvas.iFood-(100*(MyGameCanvas.iFood/100)))/10))],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			else if(MyGameCanvas.iFood>=1000 && MyGameCanvas.iFood<10000)
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_food[MyGameCanvas.iFood/1000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+w, y, font_food[ (MyGameCanvas.iFood-(1000*(MyGameCanvas.iFood/1000)))/100],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+2*w, y, 
//						font_food[((MyGameCanvas.iFood-(1000*(MyGameCanvas.iFood/1000))) - ((MyGameCanvas.iFood-(1000*(MyGameCanvas.iFood/1000)))/100)*100)/10],
//						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+3*w, y, 
//						font_food[((MyGameCanvas.iFood-(1000*(MyGameCanvas.iFood/1000))) - ((MyGameCanvas.iFood-(1000*(MyGameCanvas.iFood/1000)))/100)*100) - 10*(((MyGameCanvas.iFood-(1000*(MyGameCanvas.iFood/1000))) - ((MyGameCanvas.iFood-(1000*(MyGameCanvas.iFood/1000)))/100)*100)/10)],
//						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			else if(MyGameCanvas.iFood>=10000 && MyGameCanvas.iFood<100000)
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_food[MyGameCanvas.iFood/10000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+w, y, font_food[ (MyGameCanvas.iFood-(10000*(MyGameCanvas.iFood/10000)))/1000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+2*w, y, 
//						font_food[((MyGameCanvas.iFood-(10000*(MyGameCanvas.iFood/10000))) - ((MyGameCanvas.iFood-(10000*(MyGameCanvas.iFood/10000)))/1000)*1000)/100],
//						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+3*w, y, 
//						font_food[(((MyGameCanvas.iFood-(10000*(MyGameCanvas.iFood/10000))) - ((MyGameCanvas.iFood-(10000*(MyGameCanvas.iFood/10000)))/1000)*1000) - 100*(((MyGameCanvas.iFood-(10000*(MyGameCanvas.iFood/10000))) - ((MyGameCanvas.iFood-(10000*(MyGameCanvas.iFood/10000)))/1000)*1000)/100))/10],
//						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+4*w, y, 
//						font_food[(((MyGameCanvas.iFood-(10000*(MyGameCanvas.iFood/10000))) - ((MyGameCanvas.iFood-(10000*(MyGameCanvas.iFood/10000)))/1000)*1000) - 100*(((MyGameCanvas.iFood-(10000*(MyGameCanvas.iFood/10000))) - ((MyGameCanvas.iFood-(10000*(MyGameCanvas.iFood/10000)))/1000)*1000)/100))
//						     -10*((((MyGameCanvas.iFood-(10000*(MyGameCanvas.iFood/10000))) - ((MyGameCanvas.iFood-(10000*(MyGameCanvas.iFood/10000)))/1000)*1000) - 100*(((MyGameCanvas.iFood-(10000*(MyGameCanvas.iFood/10000))) - ((MyGameCanvas.iFood-(10000*(MyGameCanvas.iFood/10000)))/1000)*1000)/100))/10)],
//						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			else if(iFood>=100000)
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_food[MyGameCanvas.iFood/100000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_food[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_food[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_food[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_food[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_food[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			break;
//		case Lianji:
//			if(MyGameCanvas.iLianji>=0 && MyGameCanvas.iLianji<10)
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_lianji[MyGameCanvas.iLianji],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			else if(MyGameCanvas.iLianji>=10 && MyGameCanvas.iLianji<100)
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_lianji[MyGameCanvas.iLianji/10],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+w, y, font_lianji[MyGameCanvas.iLianji-(10*(MyGameCanvas.iLianji/10))],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			else if(MyGameCanvas.iLianji>=100 && MyGameCanvas.iLianji <1000) 
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_lianji[MyGameCanvas.iLianji/100],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+w, y, font_lianji[(MyGameCanvas.iLianji-(100*(MyGameCanvas.iLianji/100)))/10],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+2*w, y, font_lianji[(MyGameCanvas.iLianji-(100*(MyGameCanvas.iLianji/100)))-(10*((MyGameCanvas.iLianji-(100*(MyGameCanvas.iLianji/100)))/10))],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			else if(MyGameCanvas.iLianji>=1000 && MyGameCanvas.iLianji<10000)
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_lianji[MyGameCanvas.iLianji/1000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+w, y, font_lianji[ (MyGameCanvas.iLianji-(1000*(MyGameCanvas.iLianji/1000)))/100],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+2*w, y, 
//						font_lianji[((MyGameCanvas.iLianji-(1000*(MyGameCanvas.iLianji/1000))) - ((MyGameCanvas.iLianji-(1000*(MyGameCanvas.iLianji/1000)))/100)*100)/10],
//						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+3*w, y, 
//						font_lianji[((MyGameCanvas.iLianji-(1000*(MyGameCanvas.iLianji/1000))) - ((MyGameCanvas.iLianji-(1000*(MyGameCanvas.iLianji/1000)))/100)*100) - 10*(((MyGameCanvas.iLianji-(1000*(MyGameCanvas.iLianji/1000))) - ((MyGameCanvas.iLianji-(1000*(MyGameCanvas.iLianji/1000)))/100)*100)/10)],
//						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			else if(MyGameCanvas.iLianji>=10000 && MyGameCanvas.iLianji<100000)
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_lianji[MyGameCanvas.iLianji/10000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+w, y, font_lianji[ (MyGameCanvas.iLianji-(10000*(MyGameCanvas.iLianji/10000)))/1000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+2*w, y, 
//						font_lianji[((MyGameCanvas.iLianji-(10000*(MyGameCanvas.iLianji/10000))) - ((MyGameCanvas.iLianji-(10000*(MyGameCanvas.iLianji/10000)))/1000)*1000)/100],
//						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+3*w, y, 
//						font_lianji[(((MyGameCanvas.iLianji-(10000*(MyGameCanvas.iLianji/10000))) - ((MyGameCanvas.iLianji-(10000*(MyGameCanvas.iLianji/10000)))/1000)*1000) - 100*(((MyGameCanvas.iLianji-(10000*(MyGameCanvas.iLianji/10000))) - ((MyGameCanvas.iLianji-(10000*(MyGameCanvas.iLianji/10000)))/1000)*1000)/100))/10],
//						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+4*w, y, 
//						font_lianji[(((MyGameCanvas.iLianji-(10000*(MyGameCanvas.iLianji/10000))) - ((MyGameCanvas.iLianji-(10000*(MyGameCanvas.iLianji/10000)))/1000)*1000) - 100*(((MyGameCanvas.iLianji-(10000*(MyGameCanvas.iLianji/10000))) - ((MyGameCanvas.iLianji-(10000*(MyGameCanvas.iLianji/10000)))/1000)*1000)/100))
//						     -10*((((MyGameCanvas.iLianji-(10000*(MyGameCanvas.iLianji/10000))) - ((MyGameCanvas.iLianji-(10000*(MyGameCanvas.iLianji/10000)))/1000)*1000) - 100*(((MyGameCanvas.iLianji-(10000*(MyGameCanvas.iLianji/10000))) - ((MyGameCanvas.iLianji-(10000*(MyGameCanvas.iLianji/10000)))/1000)*1000)/100))/10)],
//						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			else if(MyGameCanvas.iLianji>=100000)
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_lianji[MyGameCanvas.iLianji/100000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_lianji[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_lianji[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_lianji[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_lianji[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_lianji[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			break;
//		case Jinbi:
//			if(MyGameCanvas.iJinbi>=0 && MyGameCanvas.iJinbi<10)
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[MyGameCanvas.iJinbi],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			else if(MyGameCanvas.iJinbi>=10 && MyGameCanvas.iJinbi<100)
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[MyGameCanvas.iJinbi/10],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+w, y, font_jinbi[MyGameCanvas.iJinbi-(10*(MyGameCanvas.iJinbi/10))],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			else if(MyGameCanvas.iJinbi>=100 && MyGameCanvas.iJinbi <1000) 
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[MyGameCanvas.iJinbi/100],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+w, y, font_jinbi[(MyGameCanvas.iJinbi-(100*(MyGameCanvas.iJinbi/100)))/10],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+2*w, y, font_jinbi[(MyGameCanvas.iJinbi-(100*(MyGameCanvas.iJinbi/100)))-(10*((MyGameCanvas.iJinbi-(100*(MyGameCanvas.iJinbi/100)))/10))],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			else if(MyGameCanvas.iJinbi>=1000 && MyGameCanvas.iJinbi<10000)
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[MyGameCanvas.iJinbi/1000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+w, y, font_jinbi[ (MyGameCanvas.iJinbi-(1000*(MyGameCanvas.iJinbi/1000)))/100],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+2*w, y, 
//						font_jinbi[((MyGameCanvas.iJinbi-(1000*(MyGameCanvas.iJinbi/1000))) - ((MyGameCanvas.iJinbi-(1000*(MyGameCanvas.iJinbi/1000)))/100)*100)/10],
//						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+3*w, y, 
//						font_jinbi[((MyGameCanvas.iJinbi-(1000*(MyGameCanvas.iJinbi/1000))) - ((MyGameCanvas.iJinbi-(1000*(MyGameCanvas.iJinbi/1000)))/100)*100) - 10*(((MyGameCanvas.iJinbi-(1000*(MyGameCanvas.iJinbi/1000))) - ((MyGameCanvas.iJinbi-(1000*(MyGameCanvas.iJinbi/1000)))/100)*100)/10)],
//						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			else if(MyGameCanvas.iJinbi>=10000 && MyGameCanvas.iJinbi<100000)
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[MyGameCanvas.iJinbi/10000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+w, y, font_jinbi[ (MyGameCanvas.iJinbi-(10000*(MyGameCanvas.iJinbi/10000)))/1000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+2*w, y, 
//						font_jinbi[((MyGameCanvas.iJinbi-(10000*(MyGameCanvas.iJinbi/10000))) - ((MyGameCanvas.iJinbi-(10000*(MyGameCanvas.iJinbi/10000)))/1000)*1000)/100],
//						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+3*w, y, 
//						font_jinbi[(((MyGameCanvas.iJinbi-(10000*(MyGameCanvas.iJinbi/10000))) - ((MyGameCanvas.iJinbi-(10000*(MyGameCanvas.iJinbi/10000)))/1000)*1000) - 100*(((MyGameCanvas.iJinbi-(10000*(MyGameCanvas.iJinbi/10000))) - ((MyGameCanvas.iJinbi-(10000*(MyGameCanvas.iJinbi/10000)))/1000)*1000)/100))/10],
//						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//				GameDraw.add_Image(ImageIndex, x+4*w, y, 
//						font_jinbi[(((MyGameCanvas.iJinbi-(10000*(MyGameCanvas.iJinbi/10000))) - ((MyGameCanvas.iJinbi-(10000*(MyGameCanvas.iJinbi/10000)))/1000)*1000) - 100*(((MyGameCanvas.iJinbi-(10000*(MyGameCanvas.iJinbi/10000))) - ((MyGameCanvas.iJinbi-(10000*(MyGameCanvas.iJinbi/10000)))/1000)*1000)/100))
//						     -10*((((MyGameCanvas.iJinbi-(10000*(MyGameCanvas.iJinbi/10000))) - ((MyGameCanvas.iJinbi-(10000*(MyGameCanvas.iJinbi/10000)))/1000)*1000) - 100*(((MyGameCanvas.iJinbi-(10000*(MyGameCanvas.iJinbi/10000))) - ((MyGameCanvas.iJinbi-(10000*(MyGameCanvas.iJinbi/10000)))/1000)*1000)/100))/10)],
//						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			else if(MyGameCanvas.iJinbi>=100000)
//			{
//				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[MyGameCanvas.iJinbi/100000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//			}
//			
//			break;
//		}
//		
//	}
///*
// * 
// * �����֣�������Щ��Ҫ���̶�ֵ��
// * idirty:�̶�ֵ��С������Ҫ��������
// * 		
//*/
//    public final static void DrawNum_2(int ImageIndex,int x,int y,int w,int type,int idirty)
//    {
//    	 int font_jinbi[][] = {//�������
//				 {0,0,20,26},{20,0,25,26},//0,1
//				 {45,0,23,26},{68,0,23,26},//2,3
//				 {91,0,25,26},{116,0,23,26},//4,5
//				 {139,0,24,26},{163,0,23,26},//6,7
//				 {186,0,25,26},{211,0,24,26},//8,9
//				 {235,0,20,26}//-
//		 };
//    	 int font_food[][] = {
//    			 {0,0,30,42},{31,0,30,42},//0,1
//    			 {64,0,30,42},{97,0,30,42},
//    			 {128,0,30,42},{160,0,30,42},
//    			 {192,0,30,42},{224,0,30,42},
//    			 {256,0,30,42},{290,0,30,42},
//    	 };
//    	 int font_lianji[][] = {
// 				{0,0,24,37},{24,0,25,37},//0,1
// 				{49,0,26,37},{75,0,23,37},
// 				{98,0,26,37},{124,0,24,37},
// 				{148,0,27,37},{175,0,25,37},
// 				{200,0,24,37},{224,0,26,37}
// 		};
//    	 switch(type){
//    	 case Jinbi:
//    		  if(idirty>=0 && idirty<10)
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[idirty],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//   			else if(idirty>=10 && idirty<100)
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[idirty/10],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+w, y, font_jinbi[idirty-(10*(idirty/10))],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//   			else if(idirty>=100 && idirty <1000) 
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[idirty/100],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+w, y, font_jinbi[(idirty-(100*(idirty/100)))/10],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+2*w, y, font_jinbi[(idirty-(100*(idirty/100)))-(10*((idirty-(100*(idirty/100)))/10))],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//   			else if(idirty>=1000 && idirty<10000)
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[idirty/1000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+w, y, font_jinbi[ (idirty-(1000*(idirty/1000)))/100],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+2*w, y, 
//   						font_jinbi[((idirty-(1000*(idirty/1000))) - ((idirty-(1000*(idirty/1000)))/100)*100)/10],
//   						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+3*w, y, 
//   						font_jinbi[((idirty-(1000*(idirty/1000))) - ((idirty-(1000*(idirty/1000)))/100)*100) - 10*(((idirty-(1000*(idirty/1000))) - ((idirty-(1000*(idirty/1000)))/100)*100)/10)],
//   						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//   			else if(idirty>=10000 && idirty<100000)
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[idirty/10000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+w, y, font_jinbi[ (idirty-(10000*(idirty/10000)))/1000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+2*w, y, 
//   						font_jinbi[((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000)/100],
//   						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+3*w, y, 
//   						font_jinbi[(((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000) - 100*(((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000)/100))/10],
//   						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+4*w, y, 
//   						font_jinbi[(((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000) - 100*(((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000)/100))
//   						     -10*((((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000) - 100*(((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000)/100))/10)],
//   						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//   			else if(idirty>=100000)
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[idirty/100000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_jinbi[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//    		 break;
//    	 case Food:
//    		  if(idirty>=0 && idirty<10)
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_food[idirty],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//   			else if(idirty>=10 && idirty<100)
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_food[idirty/10],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+w, y, font_food[idirty-(10*(idirty/10))],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//   			else if(idirty>=100 && idirty <1000) 
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_food[idirty/100],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+w, y, font_food[(idirty-(100*(idirty/100)))/10],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+2*w, y, font_food[(idirty-(100*(idirty/100)))-(10*((idirty-(100*(idirty/100)))/10))],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//   			else if(idirty>=1000 && idirty<10000)
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_food[idirty/1000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+w, y, font_food[ (idirty-(1000*(idirty/1000)))/100],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+2*w, y, 
//   						font_food[((idirty-(1000*(idirty/1000))) - ((idirty-(1000*(idirty/1000)))/100)*100)/10],
//   						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+3*w, y, 
//   						font_food[((idirty-(1000*(idirty/1000))) - ((idirty-(1000*(idirty/1000)))/100)*100) - 10*(((idirty-(1000*(idirty/1000))) - ((idirty-(1000*(idirty/1000)))/100)*100)/10)],
//   						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//   			else if(idirty>=10000 && idirty<100000)
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_food[idirty/10000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+w, y, font_food[ (idirty-(10000*(idirty/10000)))/1000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+2*w, y, 
//   						font_food[((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000)/100],
//   						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+3*w, y, 
//   						font_food[(((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000) - 100*(((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000)/100))/10],
//   						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+4*w, y, 
//   						font_food[(((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000) - 100*(((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000)/100))
//   						     -10*((((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000) - 100*(((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000)/100))/10)],
//   						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//   			else if(idirty>=100000)
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_food[idirty/100000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_food[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_food[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_food[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_food[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_food[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//    		 break;
//    	 case Lianji:
//    		  if(idirty>=0 && idirty<10)
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_lianji[idirty],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//   			else if(idirty>=10 && idirty<100)
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_lianji[idirty/10],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+w, y, font_lianji[idirty-(10*(idirty/10))],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//   			else if(idirty>=100 && idirty <1000) 
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_lianji[idirty/100],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+w, y, font_lianji[(idirty-(100*(idirty/100)))/10],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+2*w, y, font_lianji[(idirty-(100*(idirty/100)))-(10*((idirty-(100*(idirty/100)))/10))],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//   			else if(idirty>=1000 && idirty<10000)
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_lianji[idirty/1000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+w, y, font_lianji[ (idirty-(1000*(idirty/1000)))/100],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+2*w, y, 
//   						font_lianji[((idirty-(1000*(idirty/1000))) - ((idirty-(1000*(idirty/1000)))/100)*100)/10],
//   						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+3*w, y, 
//   						font_lianji[((idirty-(1000*(idirty/1000))) - ((idirty-(1000*(idirty/1000)))/100)*100) - 10*(((idirty-(1000*(idirty/1000))) - ((idirty-(1000*(idirty/1000)))/100)*100)/10)],
//   						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//   			else if(idirty>=10000 && idirty<100000)
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_lianji[idirty/10000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+w, y, font_lianji[ (idirty-(10000*(idirty/10000)))/1000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+2*w, y, 
//   						font_lianji[((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000)/100],
//   						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+3*w, y, 
//   						font_lianji[(((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000) - 100*(((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000)/100))/10],
//   						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x+4*w, y, 
//   						font_lianji[(((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000) - 100*(((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000)/100))
//   						     -10*((((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000) - 100*(((idirty-(10000*(idirty/10000))) - ((idirty-(10000*(idirty/10000)))/1000)*1000)/100))/10)],
//   						Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//   			else if(idirty>=100000)
//   			{
//   				GameDraw.add_Image(ImageIndex, x, y, font_lianji[idirty/100000],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_lianji[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_lianji[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_lianji[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_lianji[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   				GameDraw.add_Image(ImageIndex, x, y, font_lianji[0],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
//   			}
//    		 
//    		 break;
//    	 }
//    	 
// 	
//    	
//    	
//    }
//	
//	
//    public final static void DrawNum_3(int ImageIndex,int x,int y,int w,int idirty){
//    	int font_lose[][] = {
//    			{0,0,20,26},{20,0,24,26},//0,1
//    			{44,0,24,26},{68,0,24,26},
//    			{92,0,24,26},{116,0,24,26},
//    			{140,0,24,26},{164,0,24,26},
//    			{188,0,24,26},{212,0,24,26},
//    			{236,0,19,26}
//    	};
//    	
//    	  if(idirty>=0 && idirty<=10)
// 			{
// 				GameDraw.add_Image(ImageIndex, x, y, font_lose[idirty],Tools.TOP_LEFT, Tools.TRANS_NONE, 700);
// 			}
//    	
//    }
//	
//	
//}
