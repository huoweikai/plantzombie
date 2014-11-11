package com.haopu.kbz;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import android.bluetooth.BluetoothAdapter;

//
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//
//import com.haopu.FishGame.GameEngine;
//import com.haopu.FishGame.MyGameCanvas;
//import com.haopu.pak.PAK_IMAGES;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.util.Log;
//
public class Gift {
//	String strLoginDays="gift_loginDays";
//    String strOpenGift="gift_openGift";
//    String strLastTime="gift_lastTime";
//    String strLastDate="gift_lastDate";
//    String strLastMonth="gift_lastMonth";
//    String strLastYear="gift_lastYear";
//    int lastDate=20;//�ϴ�����
//    int lastMonth=8;//�ϴ�����
//    int lastYear=2012;//�ϴ�����
//    int nowDate=0;//��������
//    int nowMonth=0;//��������
//    int nowYear=0;//��������
//    Long lastTime=0L;
//    Long nowTime=0L;
//	int loginDays=0;//l���½������
//	boolean openGift=false;//������Ʒ�Ƿ��Ѵ���ȡ
////	boolean gettingGift=false
////������ȡ��Ʒ
//	SharedPreferences sp;
//	long offset;
//	int dateNum=0;//��ȡ��������
//	Calendar calendar=Calendar.getInstance();
//	public static Gift me;
//	public Gift(Context context){
//		me =this;
//		 sp = context.getSharedPreferences("SP_GIFT",0);
//		 loginDays=sp.getInt(strLoginDays,0);
//		 openGift=sp.getBoolean(strOpenGift,false);
//		 lastTime=sp.getLong(strLastTime, 0);
//		 lastDate=sp.getInt(strLastDate, 31);
//		 lastMonth=sp.getInt(strLastMonth, 0);
//		 lastYear=sp.getInt(strLastYear,1900);
//		 nowTime=System.currentTimeMillis();
//		 nowDate=calendar.get(Calendar.DAY_OF_MONTH);
//		 nowMonth=calendar.get(Calendar.MONTH);
//		 nowYear=calendar.get(Calendar.YEAR);	 
//	}
//	
//	/**
//	 * �����ҳ�����
//	 */
//	public void init(){
//		Calendar baseDate = new GregorianCalendar(lastYear,lastMonth,lastDate);
//        if(nowTime>(baseDate.getTimeInMillis()+86400000*2)){
//        	loginDays=0;//��l���½
//        }		
//		if(lastDate==nowDate){//�����ظ���½
//			
//		}else{
//			loginDays++;
//			openGift=false;
//		}
//		lastTime=nowTime;
//		lastDate=nowDate;
//		lastMonth=nowMonth;
//		lastYear=nowYear;
//		sp.edit().putInt(strLoginDays, loginDays).commit();
//		sp.edit().putLong(strLastTime,lastTime).commit();
//		sp.edit().putInt(strLastDate, lastDate).commit();
//		sp.edit().putInt(strLastMonth, lastMonth).commit();
//		sp.edit().putInt(strLastYear, lastYear).commit();
//		sp.edit().putBoolean(strOpenGift, openGift).commit();
//	}
//
//	
//	/**
//	 * ������
//	 * @param num ����1-7
//	 */
//	public void open(int num){
//		int k=loginDays;
//		if(k>7)k=7;
//		if(num!=k)return;
//		if((openGift)){
//			return;//�Ѵ򿪻�������ȡ
//		}else{
//			openBox=true;
//			dateNum=num;
//			openGift=true;
//			sp.edit().putBoolean(strOpenGift, openGift).commit();
//		}
//		switch(num){
//		case 1:
//			Log.i("openGift",1+";");
//			GameEngine.me.usBs.iUsBsCuJinbi+=200;
//			break;
//		case 2:
//			Log.i("openGift",2+";");
//			GameEngine.me.usBs.iUsBsCuJinbi+=400;
//			break;
//		case 3:
//			Log.i("openGift",3+";");
//			GameEngine.me.usBs.iUsBsCuJinbi+=600;
//			break;
//		case 4:
//			Log.i("openGift",4+";");
//			GameEngine.me.usBs.iUsBsCuJinbi+=800;
//			break;
//		case 5:
//			Log.i("openGift",5+";");
//			GameEngine.me.usBs.iUsBsCuJinbi+=1000;
//			break;
//		case 6:
//			Log.i("openGift",6+";");
//			GameEngine.me.usBs.iUsBsCuJinbi+=2;
//			break;
//		case 7:
//		    Log.i("openGift",7+";");
//		    GameEngine.me.usBs.iUsBsCuJinbi+=2;
//			break;
//		}
//		MyGameCanvas.me.saveGame();
//	}
//	
//	int index=0;
//	int count=0;//���»ζ�
//	boolean openBox=false;//�Ƿ����ڴ򿪺���
//	public  void drawImg(){
//		int m[]={1,2,3,2,1};
//		int lev=1000;
//		int k=loginDays;
//		GameDraw.add_Image(PAK_IMAGES.IMG_QIANDAOBAN,140,40,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//		GameDraw.add_Image(PAK_IMAGES.IMG_QUEDING1,183,177,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//		
//		if(index<=6){//��δ��ȡ
//	        if(loginDays>7)k=7;
//	        if(++count==m.length)count=0;
//			for(int i=0;i<7;i++){				
//	        	if((k-1)==i){//������Ʒ
//	        		if(openGift){//������ȡ
//	        			if(openBox)index++;
//	        			switch(index){
//	        			case 1:
//	        			case 2:
//		            		GameDraw.add_ImageScale(PAK_IMAGES.IMG_GIFT1,(int)((188+i*60)/1.2),(int)((260+m[count])/1.2),53,0,53,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev,1.2f,1.2f);
//	        				break;
//	        			case 3:
//	        			case 4:
//	        			case 5:
//		            		GameDraw.add_ImageScale(PAK_IMAGES.IMG_GIFT1,(int)((188+i*60)/1.2),(int)(260/1.2),106,0,59,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev,1.2f,1.2f);
//	        				break;
//	        			default:
//		            		GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260,106,0,59,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//
//	        			}
//	        		}else{
//	            		GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260+m[count],53,0,53,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//	        		}
//	        	}else{
//	        		if(i<loginDays){
//	            		GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260,106,0,59,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//        			}else{
//		        		GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260,0,0,53,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//	        		}
//	        	}
//	        }
//		}else{
//			switch(dateNum){
//			case 1:
//				for(int i=0;i<7;i++){	
//					if((i<dateNum)){
//		        		GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260,106,0,59,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//					} else{
//						GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260,0,0,53,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//					}
//				}	
//				GameDraw.add_String("���", 300, 330, Tools.TOP_LEFT, 0xff00ffff, lev,45);
//				GameDraw.add_String("+200", 400, 340, Tools.TOP_LEFT, 0xff00ffff, lev,45);
//				break;
//			case 2:
//				for(int i=0;i<7;i++){	
//					if((i<dateNum)){
//		        		GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260,106,0,59,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//					} else{
//						GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260,0,0,53,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//					}
//				}	
//				GameDraw.add_String("���",300,330, Tools.TOP_LEFT, 0xff00ffff, lev,45);
//				GameDraw.add_String("+400", 400, 340, Tools.TOP_LEFT, 0xff00ffff, lev,45);
//				break;
//			case 3:
//				for(int i=0;i<7;i++){	
//					if((i<dateNum)){
//		        		GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260,106,0,59,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//					} else{
//						GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260,0,0,53,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//					}
//				}	
//				GameDraw.add_String("���",300,330, Tools.TOP_LEFT, 0xff00ffff, lev,45);
//				GameDraw.add_String("+600", 400, 340, Tools.TOP_LEFT, 0xff00ffff, lev,45);
//				break;
//			case 4:
//				for(int i=0;i<7;i++){	
//					if((i<dateNum)){
//		        		GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260,106,0,59,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//					} else{
//						GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260,0,0,53,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//					}
//				}	
//				GameDraw.add_String("���",300,330, Tools.TOP_LEFT, 0xff00ffff, lev,45);
//				GameDraw.add_String("+800", 400, 340, Tools.TOP_LEFT, 0xff00ffff, lev,45);
//                break;
//			case 5:
////				GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+0*60,260,0,0,53,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
////				GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+1*60,260,0,0,53,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
////				GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+2*60,260,0,0,53,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
////				GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+3*60,260,0,0,53,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
////				GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+4*60,260,106,0,59,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//				for(int i=0;i<7;i++){	
//					if((i<dateNum)){
//		        		GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260,106,0,59,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//					} else{
//						GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260,0,0,53,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//					}
//				}
//				GameDraw.add_String("���",300,330, Tools.TOP_LEFT, 0xff00ffff, lev,45);
//				GameDraw.add_String("+1000", 400, 340, Tools.TOP_LEFT, 0xff00ffff, lev,45);
//				break;
//			case 6:
//				for(int i=0;i<7;i++){	
//					if((i<dateNum)){
//		        		GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260,106,0,59,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//					} else{
//						GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260,0,0,53,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//					}
//				}	
//				GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+6*60,260,0,0,53,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//				GameDraw.add_String("���յ>�",260,330, Tools.TOP_LEFT, 0xff00ffff, lev,45);
//				GameDraw.add_String("+2", 450, 340, Tools.TOP_LEFT, 0xff00ffff, lev,45);
//				break;
//			case 7:
//				for(int i=0;i<7;i++){	
//					if((i<dateNum)){
//		        		GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260,106,0,59,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//					} else{
//						GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+i*60,260,0,0,53,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//					}
//				}
//				GameDraw.add_Image(PAK_IMAGES.IMG_GIFT1,188+6*60,260,106,0,59,53,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
//				GameDraw.add_String("���е>�",260,330, Tools.TOP_LEFT, 0xff00ffff, lev,45);
//				GameDraw.add_String("+2", 450, 340, Tools.TOP_LEFT, 0xff00ffff, lev,45);
//				break;
//			}
//		}
//		
//	}
//	 
//	/**
//	 * �˳���ȡ�������
//	 */
//	public void outGift(){
//		openBox=false;
//		index=0;
//	}
//	
//}
	
//	Socket socket = new Socket("test1",8888);
//	OutputStream outputStream  = socket.getOutputStream();
//	DataInputStream inputStream = new DataInputStream(socket.getInputStream());
//	file;
	void a(){
		BluetoothAdapter ba =BluetoothAdapter.getDefaultAdapter();
		ba.enable();
		try {
		Socket socket = new Socket("127.0.0.1", 4700);
		// 向本机的4700端口发出客户请求 send client request to port 4700

		BufferedReader sin = new BufferedReader(new InputStreamReader(
				System.in));
		// 由系统标准输入设备构造BufferedReader对象

		PrintWriter os = new PrintWriter(socket.getOutputStream());
		// 由Socket对象得到输出流，并构造PrintWriter对象

		BufferedReader is = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		// 由Socket对象得到输入流，并构造相应的BufferedReader对象

		String readline;

		readline = sin.readLine(); // 从系统标准输入读入一字符串
		while (!readline.equals("bye")) {
			// 若从标准输入读入的字符串为 "bye"则停止循环
			os.println(readline);
			// 将从系统标准输入读入的字符串输出到Server
			os.flush();
			// 刷新输出流，使Server马上收到该字符串

			System.out.println("in Talke Client,Client:" + readline);
			// 在系统标准输出上打印读入的字符串
			System.out.println("in Talke Client,Server:" + is.readLine());
			// 从Server读入一字符串，并打印到标准输出上
			readline = sin.readLine(); // 从系统标准输入读入一字符串
		} // 继续循环
		os.close(); // 关闭Socket输出流
		is.close(); // 关闭Socket输入流
		socket.close(); // 关闭Socket

	} catch (Exception e) {
		// TODO: handle exception
	}
}
	
	}


