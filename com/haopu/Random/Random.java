package com.haopu.Random;

import android.service.wallpaper.WallpaperService.Engine;

import com.haopu.JSGame.MyGameCanvas;
import com.haopu.kbz.GameRandom;

public class Random {//此类专为处理游戏中所要的随机数，随机爆出的东西等等,不完善!!!
     int iRandom;//获取随机数
     int iRan_jinbi;//随机爆出的金币
     int iRan_daoju[] = new int [50];//随机爆出的道具种类
     
     
     public Random(){
    	 iRandom = 0;
    	 iRan_jinbi = 0;
    	 for(int i=0;i<50;i++){
    		 iRan_daoju[i]=0;
    	 }
     }
    	 
     public  void GetRandom(int m,int n,int result){//产生m->n-1之间的数,爆率通常根据百分比来算
    	 iRandom = GameRandom.result(m,n);
    	 result = iRandom/10;//默认iRandom在0-99之间
    	  switch(result){
    	  case 0://50%的概率,假设爆金币
    	  case 1: 
    	  case 2:
    	  case 3:
    	  case 4:
    		 iRan_jinbi = GameRandom.result(0,100); 
    		  break;
    	  case 5://10%的概率,爆羊，磁铁,飞的云，护盾
    		  int k=GameRandom.result(0,4);
    		  switch(k){
    		  case 0:
    			  iRan_daoju[1]+=1;
    			  break;
    		  case 1:
    			  iRan_daoju[2]+=1;
    			  break;
    		  case 2:
    			  iRan_daoju[3]+=1;
    			  break;
    		  case 3:
    			  iRan_daoju[4]+=1;
    			  break;
    		  }
    		  break;
    	  case 6://10%的概率
    		  break;
    	  case 7://10%的概率
    		  break;
    	  case 8://10%的概率
    		  break;
    	  case 9://10%的概率
    		  break;
    	  }
    	
     }
	

     
}
