package com.haopu.JSGame;

import com.haopu.kbz.GameDraw;
import com.haopu.kbz.Tools;
import com.haopu.pak.PAK_IMAGES;

public class JiangLiRank99 { //奖励关卡
   public int x;
   public int y;
   public int speed;
   public int type;
   public int xiaoShi;
   public static boolean isStop=false;
   public static boolean isJiaS=false;
	public JiangLiRank99(int x,int y,int speed,int type) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.type = type;
	}
	
	void move(){
		if(isStop==false){//停止效果
			if(isJiaS){ //加速效果
				x+=speed*2;	
			}else{
				x+=speed;	
			}
	
		}
	
	}
	
	void addMoney(int money){
		GameEngine.me.usBs.iUsBsCuJinbi += money;
		MyGameCanvas.AddBlastEffectList2(this.x,this.y,MyGameCanvas.me.EFF_Rank99,0,this.type,0,money);
		MyGameCanvas.engine.usBs.rankMoney+=money;
	}
	void cutMoney(int money){
		GameEngine.me.usBs.iUsBsCuJinbi -= money;
		MyGameCanvas.AddBlastEffectList2(this.x,this.y,MyGameCanvas.me.EFF_Rank99,1,this.type,0,money);
		MyGameCanvas.engine.usBs.rankMoney-=money;
	}
	
	void drawDaoJu(){
		int imgIndex = 0;
		switch (type) {
		case 0:
			imgIndex = PAK_IMAGES.IMG_DJ1;
			break;
		case 1:
			imgIndex = PAK_IMAGES.IMG_DJ2;
			break;
		case 2:
			imgIndex = PAK_IMAGES.IMG_DJ3;
			break;
		case 3:
			imgIndex = PAK_IMAGES.IMG_DJ4;
			break;
		case 4:
			imgIndex = PAK_IMAGES.IMG_DJ5;
			break;
		case 5:
			imgIndex = PAK_IMAGES.IMG_DJ6;
			break;
		case 6:
			imgIndex = PAK_IMAGES.IMG_DJ7;
			break;
		case 7:
			imgIndex = PAK_IMAGES.IMG_DJ8;
			break;
		}
		GameDraw.add_Image(imgIndex,x-15000,y,Tools.HCENTER_VCENTER, Tools.TRANS_NONE, 500);//daoju
	
		
	}
	
}
