package com.haopu.JSGame;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.haopu.kbz.GameDraw;
import com.haopu.kbz.GameNumber;
import com.haopu.kbz.Tools;
import com.haopu.pak.PAK_IMAGES;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Gift {
	public int countXS=0;
    String strLoginDays="gift_loginDays";
    String strOpenGift="gift_openGift";
    String strLastTime="gift_lastTime";
    String strLastDate="gift_lastDate";
    String strLastMonth="gift_lastMonth";
    String strLastYear="gift_lastYear";
    int lastDate=20;//上次日期
    int lastMonth=8;//上次哪月
    int lastYear=2012;//上次哪年
    int nowDate=0;//当天日期
    int nowMonth=0;//本次哪月
    int nowYear=0;//本次哪年
    Long lastTime=0L;
    Long nowTime=0L;
	int loginDays=0;//连续登陆的天数
	boolean openGift=false;//今日礼品是否已打开领取
//	boolean gettingGift=false;//正在领取礼品
	SharedPreferences sp;
	long offset;
	int dateNum=0;//领取哪天礼物
	Calendar calendar=Calendar.getInstance();
	public Gift(Context context){
		 sp = context.getSharedPreferences("SP_GIFT",0);
		 loginDays=sp.getInt(strLoginDays,0);
		 openGift=sp.getBoolean(strOpenGift,false);
		 lastTime=sp.getLong(strLastTime, 0);
		 lastDate=sp.getInt(strLastDate, 31);
		 lastMonth=sp.getInt(strLastMonth, 0);
		 lastYear=sp.getInt(strLastYear,1900);
		 nowTime=System.currentTimeMillis();
		 nowDate=calendar.get(Calendar.DAY_OF_MONTH);
		 nowMonth=calendar.get(Calendar.MONTH);
		 nowYear=calendar.get(Calendar.YEAR);	 
	}
	
	/**
	 * 进入此页面调用
	 */
	void init(){
		Calendar baseDate = new GregorianCalendar(lastYear,lastMonth,lastDate);
        if(nowTime>(baseDate.getTimeInMillis()+86400000*2)){
        	loginDays=0;//非连续登陆
        }		
		if(lastDate==nowDate){//当日重复登陆
			
		}else{
			loginDays++;
			openGift=false;
		}
		lastTime=nowTime;
		lastDate=nowDate;
		lastMonth=nowMonth;
		lastYear=nowYear;
		sp.edit().putInt(strLoginDays, loginDays).commit();
		sp.edit().putLong(strLastTime,lastTime).commit();
		sp.edit().putInt(strLastDate, lastDate).commit();
		sp.edit().putInt(strLastMonth, lastMonth).commit();
		sp.edit().putInt(strLastYear, lastYear).commit();
		sp.edit().putBoolean(strOpenGift, openGift).commit();
	} 

	/**
	 * 打开礼物,单击时调用
	 * @param num 天数1-7
	 */
	void open(int num){
		int k=loginDays;
		if(k>7)k=7;
		if(num!=k)return;
		if((openGift)){
			return;//已打开或正在领取
		}else{
//			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa1111:"+num);
			countXS=0;
//			GameRole.gameMoney+=num*1000;
			openBox=true;
			dateNum=num;
			openGift=true;
			sp.edit().putBoolean(strOpenGift, openGift).commit();
		}
		switch(num){
		case 1:
			Log.i("openGift",1+";");
			GameEngine. usBs.iUsBsCuJinbi+=50;
			break;
		case 2:
			Log.i("openGift",2+";");
			GameEngine. usBs.iUsBsCuJinbi+=100;
			break;
		case 3:
			Log.i("openGift",3+";");
			GameEngine. usBs.iUsBsCuJinbi+=100;
			break;
		case 4:
			Log.i("openGift",4+";");
			GameEngine. usBs.iUsBsCuJinbi+=200;
			break;
		case 5:
			Log.i("openGift",5+";");
			GameEngine. usBs.iUsBsCuJinbi+=200;
			break;
		case 6:
			Log.i("openGift",6+";");
			GameEngine. usBs.iUsBsCuJinbi+=500;
			break;
		case 7:
			Log.i("openGift",7+";");
			GameEngine. usBs.iUsBsCuJinbi+=1000;
			break;
		}
	}
	
	int index=0;
	int count=0;//上下晃动
	boolean openBox=false;//是否正在打开盒子
	void drawImg(){
		int m[]={1,2,3,2,1};
		int lev=1000;
		int k=loginDays;

		GameDraw.add_Image(PAK_IMAGES.IMG_QD3,400,240,Tools.HCENTER_VCENTER, Tools.TRANS_NONE,lev);
		GameDraw.add_Image(PAK_IMAGES.IMG_QD2,630,370,0,MyGameCanvas.pointMenu==1?61:0,137,61,Tools.HCENTER_VCENTER, Tools.TRANS_NONE,lev);
		if(index<=6){//还未领取
//			GameDraw.add_Image(PAK_IMAGES.IMG_QIAN2,163+25,177,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
	        if(loginDays>7)k=7;
	        if(++count==m.length)count=0;
			for(int i=0;i<7;i++){
				if((k-1)==i){//当日礼品
	        		if(openGift){//正在领取
	        			if(openBox)index++;
	        			switch(index){
	        			case 1:
	        			case 2:
	        				//未打开盒子
		            		GameDraw.add_ImageScale(PAK_IMAGES.IMG_QD4,(int)((77+i*82)/1.2)+25,(int)((260+m[count])/1.2),0,0,85,70,Tools.TOP_LEFT, Tools.TRANS_NONE,lev,1.2f,1.2f);
	        				break;
	        			case 3:
	        			case 4:
	        			case 5:
	        				//打开盒子
		            		GameDraw.add_ImageScale(PAK_IMAGES.IMG_QD4,(int)((77+i*82)/1.2)+35,(int)(260/1.2),170,0,85,70,Tools.TOP_LEFT, Tools.TRANS_NONE,lev,1.2f,1.2f);
	        				break;
	        			default: 
	        				//打开盒子	        				
//	        				System.out.println("444444444444444444444");
		            		GameDraw.add_Image(PAK_IMAGES.IMG_QD4,77+i*82+25,260,170,0,85,70,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
	        			}
	        		}else{
	            		GameDraw.add_Image(PAK_IMAGES.IMG_QD4,77+i*82+25,260+m[count],0,0,85,70,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
	        		}
	        	}else{//非当日礼品
	        		if(i<loginDays){
	        			//打开盒子
	            		GameDraw.add_Image(PAK_IMAGES.IMG_QD4,77+i*82+25,260,170,0,85,70,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
	        		}else{
		        		GameDraw.add_Image(PAK_IMAGES.IMG_QD4,77+i*82+25,260,85,0,85,70,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
	        		}
	        	}
	        }
		}else{//画已领取结果
			int [] img = {PAK_IMAGES.IMG_JIANGPIN1,PAK_IMAGES.IMG_JIANGPIN2,PAK_IMAGES.IMG_JIANGPIN2,
					      PAK_IMAGES.IMG_JIANGPIN3,PAK_IMAGES.IMG_JIANGPIN3,PAK_IMAGES.IMG_JIANGPIN4,
					      PAK_IMAGES.IMG_JIANGPIN5};
			
			if(countXS<30){
				countXS++;
				GameDraw.add_Image(img[loginDays-1],400,365,
						Tools.HCENTER_VCENTER, Tools.TRANS_NONE,lev+4);
				
//				 GameNumber.drawNumber_max(img[loginDays],1000*dateNum,Tools.setOffX+350,
//				    		Tools.setOffY+320,33,2,Tools.TOP_LEFT, lev+20, 40, 0,4);   //数字  签到获得多少钱
			    
			}
			
			

			switch(dateNum){
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				for (int i = 1; i < 8; i++) {
					if(i<=dateNum){
						GameDraw.add_Image(PAK_IMAGES.IMG_QD4,102+(i-1)*82,260,170,0,85,70,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
					}else{
						GameDraw.add_Image(PAK_IMAGES.IMG_QD4,102+(i-1)*82,260,85,0,85,70,Tools.TOP_LEFT, Tools.TRANS_NONE,lev);
					}
				}
				break;
			}
		}
		
	}
	 
	/**
	 * �˳���ȡ�������
	 */
	void outGift(){
		openBox=false;
		index=0;
	}
	
}
