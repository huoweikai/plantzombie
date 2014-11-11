package com.haopu.JSGame;

import java.util.Vector;

import android.service.wallpaper.WallpaperService.Engine;
import android.text.format.Time;
import android.util.Log;

import com.haopu.Enemy.UsBoss;
import com.haopu.Enemy.fishData;
import com.haopu.kbz.GameDraw;
import com.haopu.kbz.GameHit;
import com.haopu.kbz.GameRandom;
import com.haopu.kbz.Tools;
import com.haopu.pak.PAK_IMAGES;

public class BulletManager {
	public static final float PI = 3.141592654f;
	public static final int iSpeed = 10;
	boolean bActive=true;//箭矢是否可用
	int distance;//箭矢与怪物间的距离
	boolean bDead=true;//判断被锁定的怪物是否死亡,若死亡,目标才能依次转移下一个
	boolean bAttack;//判断箭矢是否集中目标
	byte iBulletType;//箭矢的类型
	int iBulletX,iBulletY;//箭矢的初始坐标
	int iX, iY;//箭矢的实时坐标
	int type;
	int iTargetX,iTargetY;//箭矢射向的目标的坐标
	int iBulletDelay;//箭矢运行的帧数，一般每个箭矢大约在3,4帧的样子
	int iSpriteIndex,iEnemyIndex;//某个箭矢对应的塔及怪物索引
	int iAccSpeedX,iAccSpeedY;//箭矢x,y轴上的加速度
	int iDir;//箭矢的旋转角度，即方向
	int iDeadX,iDeadY;//箭矢最终消亡时候的坐标
	int iRank;//箭矢的等级
	
	int iprop;//ditukuai
	int iWeijianHp ;
//	GameEngine engine=new GameEngine();
	public int circle[] = new int[2];

	public BulletManager(){
//		engine = new GameEngine();
	}
	
	public BulletManager(int x,int y,int x1,int y1,int dir,int rank,int type){
		this.iX = x;
		this.iY = y;
		this.iTargetX = x1;
		this.iTargetY = y1;
		this.iDir = dir;
		this.iRank = rank;
		this.type = type;
	}
/**
 * 箭矢的实时运动轨迹	
 * @param x1
 * @param y1
 * @param x2
 * @param y2
 */
	
    static boolean isrank=true;
	int i;
    public void bulletMove(int k){ 
    	if(iX<0||iX>800||iY<0||iY>480){bActive=false;}

    	int x = iX;
    	int y = iY;
    	int distance = BulletManager.GetDistance(iX,iY,iTargetX,iTargetY);
		 if(MyGameCanvas.me.ZB[GameEngine.usBs.iUsBsRank-1]+1==4&& iY<=260){
			 i++;
			 if( i/2%6==5){
					for(int i=0;i<GameEngine.me.bullets.size();i++){
							GameEngine.me.bullets.removeElementAt(i);
					}
			 }
			 
   	}else{
   		
   		if(MyGameCanvas.me.ZB[GameEngine.usBs.iUsBsRank-1]+1==4){
   			  if(iX<=450&&iX>=350&&iY>=400){
   				  GameEngine.time=0;
   			  i=0;
   			  iX -= ((iX -iTargetX) *(GameEngine.Speed+20) / distance);
		      iY -= ((iY -iTargetY) *(GameEngine.Speed+20) / distance);
   			  }else{
   	   			i++;
   	   		 if( i/2%6==5){
   					for(int i=0;i<GameEngine.me.bullets.size();i++){
   						GameEngine.me.bullets.removeElementAt(i);
   					}
   	   		 }  
   			  }
   		}else{

		  iX -= ((iX -iTargetX) *GameEngine.Speed / distance);
	      iY -= ((iY -iTargetY) *GameEngine.Speed / distance);
   		}
 
   	}
			if(GameHit.hit(iX, iY, 5, 5, MyGameCanvas.engine.WuYaX+15, 240, 50,50)&&
					 MyGameCanvas.engine.wuyaHP>0){
//				addEffect(100, MyGameCanvas.engine.WuYaX+15,240, 0);
    			addEffect(3, MyGameCanvas.engine.WuYaX+15,240, 1);
				MyActivity.instance._mView.waf.StartWav(14, false);
				MyGameCanvas.engine.wuyaHP-=GameEngine.me.usBs.iUsBsCurAttack;
				MyGameCanvas.engine.bullets.removeElementAt(k);
			}
			if(fishData.isJiJia){
				for (int j = 0; j < GameEngine.JiJiaXY.length; j++) {
					if(bInCircle(iX,iY,GameEngine.JiJiaXY[j][0],GameEngine.JiJiaXY[j][1],50)&&GameEngine.JiJiaXY[j][2]>0){
						MyActivity.instance._mView.waf.StartWav(14, false);
		    			addEffect(3, iX,iY, 1);
						GameEngine.JiJiaXY[j][2] -= GameEngine.me.usBs.iUsBsCurAttack;
						bActive = false;
						break;
					}
					if(GameEngine.JiJiaXY.length>1){
						System.out.println("123 :"+isrank);
//						System.out.println(GameEngine.JiJiaXY[0][2]+":"+GameEngine.JiJiaXY[1][2]);
						if(GameEngine.JiJiaXY[0][2]<=0&&GameEngine.JiJiaXY[1][2]<=0&&isrank==true){
							isrank=false;
							MyGameCanvas.me.WinLogic();
//							System.out.println("xxxxxxxxxxxxxxxxxx");
							MyGameCanvas.setST(MyGameCanvas.GmStat_dengdai_win);
							break;
						}
						
					}else{
						if(GameEngine.JiJiaXY[0][2]<=0&&isrank){
							MyGameCanvas.me.WinLogic();
//							System.out.println("2222222222222");
							MyGameCanvas.setST(MyGameCanvas.GmStat_dengdai_win);
							break;
						}
					}
				}
			}
			if(MyGameCanvas.engine.gameRank==99){
				for(int i=0;i<MyGameCanvas.engine.jiangLi.size();i++){
		    		JiangLiRank99 DaoJu = (JiangLiRank99)MyGameCanvas.engine.jiangLi.elementAt(i);
					if(bInCircle(iX,iY,DaoJu.x-15000,DaoJu.y,30)){
						MyGameCanvas.engine.jiangLi.remove(i);
						MyGameCanvas.engine.iHit++;
						chekType(DaoJu);
					}
				}
				}
		iTargetX -= ((x -iTargetX)*GameEngine.Speed /distance);
		iTargetY -= ((y -iTargetY)*GameEngine.Speed /distance);
			for(int i=0;i<GameEngine.me.enemys.size();i++){
	    		GameRole enemy = (GameRole)GameEngine.me.enemys.elementAt(i);
	    			if(MyGameCanvas.me.ZB[GameEngine.usBs.iUsBsRank-1]+1==4){ //喷子的子弹碰撞
	    				if(bInCircle(iX,300,enemy.x,enemy.y-50,70)&&enemy!=null){
	     					if(enemy.iEnemyHp>=0){
	    						MyActivity.instance._mView.waf.StartWav(14, false);
	    						enemy.iEnemyHp-=GameEngine.me.usBs.iUsBsCurAttack;
	    						enemy.gongJi_count=10;
	    					}else{
	    						GameEngine.me.role.DropGold(GameEngine.me.iWave,enemy.x,enemy.y,enemy.type);
	    						MyActivity.instance._mView.waf.StartWav(13, false);
	        					for (int j = 0; j < MyGameCanvas.EffectV.size(); j++) {
	        						MyGameCanvas.EffectV.removeElementAt(j);
								}
	    						enemy.setStatus(enemy.STATUS_DEAD);
	    					}
	    				}
	    				
	    			}
	    			
	    			if(bInCircle(iX,iY,enemy.x,enemy.y-10,25+(enemy.type==enemy.TYPE_goldFish?100:0))&&enemy!=null){
		    			if(enemy.iEnemyHp>0){
		    				MyActivity.instance._mView.waf.StartWav(14, false);
		    				if(bActive){
		    					if(GameEngine.me.gameRank!=1){
			    					enemy.iEnemyHp-=GameEngine.me.usBs.iUsBsCurAttack;		
		    					}else{
//		    						System.out.println("isRank1JX : "+GameEngine.me.isRank1JX);
		    						if(GameEngine.me.isRank2JX==true&&GameEngine.me.iHit>=14){
		    							enemy.iEnemyHp-=GameEngine.me.usBs.iUsBsCurAttack;	
		    						}else{
		    							if(GameEngine.me.iHit>=14){
		    								enemy.iEnemyHp-=GameEngine.me.usBs.iUsBsCurAttack;		
		    							}
		    						}
		    						
		    					}

		    				}
		    				if(enemy.roleStatus == enemy.STATUS_MOVE){
		    				if(GameRandom.result(0, 10)>4){
		    					enemy.setStatus(enemy.STATUS_STOP);  //让怪物停顿  在此处添加武器属性（眩晕，停顿）
		    				    enemy.gongJi_count=20;
		    						}
		    						}
		        		
		    			}
		    			if(enemy.iEnemyHp<=0){//怪物死亡
//		    				if(GameEngine.me.gameRank==0&&enemy.type==fishData.TYPE_ENEMY_狮鹫&&GameEngine.me.iResult[0][1]==0){
//		    					GameEngine.JiaoXueDian = true;
//		    				}
		    				
		    				
		    				if(GameEngine.roleNumber>1){//roleNumber不等于0则是特殊关卡
		    						if(GameEngine.roleType!=0){//roleType！=0则表示有指定怪物类型
		    							if(GameEngine.roleType==enemy.type){//打死的怪物类型和要求类型一样
		    								GameEngine.roleNumber--;//要求数量减
		    							}
		    						}else{//没有怪物类型限制的话 数量直接--
		    							GameEngine.roleNumber--;
		    						}
		    						
		    					
		    					
		    				}

		    				GameEngine.teSuGuaiXG = enemy.TeShuGuaiWu;
		    				switch (enemy.TeShuGuaiWu){
							case 1://减速,范围
								for(int j=0;j<GameEngine.me.enemys.size();j++){
			    		    		GameRole enemyS = (GameRole)GameEngine.me.enemys.elementAt(j);
			    		    		if(enemyS.type!=GameRole.TYPE_ENEMY_紫盾){
						    	   		enemyS.isJianSu=300;    		    			
			    		    		}
//			    		    		  }
								}
								break;
							case 2://冰冻,范围
								for(int j=0;j<GameEngine.me.enemys.size();j++){
			    		    		GameRole enemyS = (GameRole)GameEngine.me.enemys.elementAt(j);
			    		            if((enemyS.y-enemy.y)<150&&(enemyS.y-enemy.y)>-150
			    		            		&&(enemyS.x-enemy.x)<150&&(enemyS.x-enemy.x)>-150){
			    		    		enemyS.roleStatus = GameRole.STATUS_STOP;
			    		    		enemyS.go=300;	
			    		    		enemyS.BINGDONG = true;
			    		            }
								}
								break;
							case 3://爆炸,范围攻击
								addEffect(103,enemy.x,enemy.y,enemy.type);
								break;
							case 4://基地无敌
								GameEngine.usBs.laoJiaWuDi=true;
								GameEngine.me.contlaojiaWD = 0;
								break;
							case 5://射速加倍
								if(MyActivity.VMHeight>240){}								GameEngine.usBs.sheSuJiaBei=true;
								GameEngine.me.contlaojia = 0;
								GameEngine.usBs.Scale = 1.5f;
								GameEngine.usBs.touMing = 0;
								GameEngine.usBs.jiaSuTime = 0;
								break;
								
							}
		    				MyActivity.instance._mView.waf.StartWav(13, false);
	    					GameEngine.me.role.DropGold(GameEngine.me.iWave,iX,iY,enemy.type);
	    					GameEngine.me.usBs.iUsBsCuJinbi += checkJinBi(enemy.type);
	    					GameEngine.me.usBs.rankMoney += checkJinBi(enemy.type);
	        					enemy.setStatus(enemy.STATUS_DEAD);
	        					addEffect(100,enemy.x,enemy.y,enemy.type);
	        					addEffect_2(enemy);
	        					chekBOO(enemy);
                                if(enemy.type == enemy.TYPE_ENEMY_狮鹫){
                                	Tools.removeImage(PAK_IMAGES.IMG_GUIXIANREN);
                                }
	        					if(enemy.type==GameRole.TYPE_ENEMY_紫盾){
	        						Tools.removeImage(PAK_IMAGES.IMG_ZIDUN1);
 	        					   MyGameCanvas.engine.paoTaiGJ = false;
 	        					}
	        					MyGameCanvas.engine.enemys.remove(i);
		    			}
		    			addEffect(iRank,enemy.x,enemy.y,enemy.type);
		    			GameEngine.me.role.iceTime=2;
		    			enemy.Stop_time=0;  
		    		if(MyGameCanvas.me.ZBShuXing[GameEngine.me.usBs.iUsBsRank-1][0]==1){//判断眩晕(如果是二号炮则随机一个数)
		    			int random = GameRandom.result(0,100);//随机数
		    			if(random>70&&MyGameCanvas.me.ZB[GameEngine.me.usBs.iUsBsRank-1]!=2&&
		    					MyGameCanvas.me.ZB[GameEngine.me.usBs.iUsBsRank-1]!=4){
		    					enemy.go=100;
		    					if(enemy.gongJi_count2<1){
		    					enemy.gongJi_count2 =80;
		    					addEffect_2(enemy);	
		    					addEffect(101,enemy.x,enemy.y,enemy.type);
		    					}
		    					addEffect(102,enemy.x,enemy.y,enemy.type);
		    			}
		    		}

		    		if(MyGameCanvas.me.ZB[GameEngine.me.usBs.iUsBsRank-1]!=2&&MyGameCanvas.me.ZB[GameEngine.me.usBs.iUsBsRank-1]!=4
		    				&&MyGameCanvas.me.ZB[GameEngine.me.usBs.iUsBsRank-1]!=5&&MyGameCanvas.me.ZB[GameEngine.me.usBs.iUsBsRank-1]!=7){
		    			
		    	  		if(index==2){
			    			int random = GameRandom.result(0,100);//随机数
			    			if(random>10+MyGameCanvas.jiNengKaiQi[10]){
			    				bActive = false;
			    			}
			    		}else{
			    			    bActive = false;
			    		}
		    		}else{
		    			int random = GameRandom.result(0,100);//随机数
		    			if(MyGameCanvas.me.ZB[GameEngine.me.usBs.iUsBsRank-1]==4){
			    			if(random>90+MyGameCanvas.jiNengKaiQi[10]){
			    				bActive = false;
			    			}	
		    			}else{
		    				if(MyGameCanvas.me.ZB[GameEngine.me.usBs.iUsBsRank-1]==7){
		    					bActive = false;
		    				}
		    				if(MyGameCanvas.me.ZB[GameEngine.me.usBs.iUsBsRank-1]==2){
			    			    if(random>70+MyGameCanvas.jiNengKaiQi[10]){
				    				bActive = false;
				    			}	
		    				}else{
			    			    if(random>5+MyGameCanvas.jiNengKaiQi[10]){
				    				bActive = false;
				    			}		
		    				}

		    			}

		    		}
		    		}
	    	}
			for(int i=0;i<GameEngine.me.unLawBuilds.size();i++){
				if(bActive==false)return;
	    		GameRole unlaw = (GameRole)GameEngine.me.unLawBuilds.elementAt(i);
	    		if(bInCircle(iX,iY,unlaw.x,unlaw.y,28)&&unlaw!=null){//此处半径不能超出地图切片快的半径
	    			addEffect(iRank==1?3:iRank,unlaw.x,unlaw.y,unlaw.type);
	    			bActive = false;
	    		}
	    	}
    		if(iX>800||iX<0||iY>480||iY<0||bActive==false){
				MyGameCanvas.engine.bullets.removeElementAt(k);
    		}
    }
    int index; //1.西红柿 2.仙人掌 3.玉米4.香蒲5.大蒜6.西瓜7.南瓜
    public void chekBOO(GameRole role){
		if(role.type == GameRole.TYPE_ENEMY_狮鹫){
			switch (MyGameCanvas.engine.gameRank) {
			case 0:
				index=7;//2
//				MyGameCanvas.shiYongKaPian[0] = 5;  //1.西红柿 2.仙人掌 3.玉米4.香蒲5.大蒜6.西瓜7.南瓜
				break;
			case 1:
				index=2;
//				MyGameCanvas.shiYongKaPian[0] = 1;
				break;
			case 2:
				if(GameEngine.me.iResult[0][3]==-1){
//					GameEngine.xinShouJiangLi=3;	
					
				}
				if(Rank3_diyi!=0){
					index=3;	
				}else{
					index=2;	
				}


//				MyGameCanvas.shiYongKaPian[0] = 5;
				break;
			case 3:
				index=5;
//				MyGameCanvas.shiYongKaPian[0] = 3;
				break;
			case 9:
				index=4;
//				MyGameCanvas.shiYongKaPian[0] = 4;
				break;
			case 12:
				index=6;
//				MyGameCanvas.shiYongKaPian[0] = 6;
				break;
			case 15:
				index=7;
//				MyGameCanvas.shiYongKaPian[0] = 7;
				break;
			case 18:
				index=4;
//				MyGameCanvas.shiYongKaPian[0] = 4;
				break;
			case 21:
				index=6;
//				MyGameCanvas.shiYongKaPian[0] = 6;
				break;
			case 23:
				index=7;
//				MyGameCanvas.shiYongKaPian[0] = 7;
				break;
			}
            if(Rank3_diyi!=0&&MyGameCanvas.engine.gameRank==2){
            	MyGameCanvas.ZB[1]=3;MyGameCanvas.JLZB[1]=3;
            	MyGameCanvas.me.ZBXingXi[2][0]=0;
            }
			if(Rank3_diyi!=0){
				for (int i = 0; i < MyGameCanvas.ZB.length; i++) {
					if(MyGameCanvas.ZB[i]!=-1){
						icont++;
						if(icont>=MyGameCanvas.ZB.length){
							return;
						}
					}
				}
				icont=0;
				MyGameCanvas.shiYongKaPian[0] = (byte) index;
				MyGameCanvas.countShiYong=0;
				MyGameCanvas.piBaoX=role.x;
				MyGameCanvas.piBaoY=role.y;	
			}
//			MyGameCanvas.JLZB[1]=3;
//			GameEngine.me.usBs.iUsBsRank = index;
//			iRank =index;
			icont=0;
			MyGameCanvas.shiYongKaPian[0] = (byte) index;
			MyGameCanvas.countShiYong=0;
			MyGameCanvas.piBaoX=role.x;
			MyGameCanvas.piBaoY=role.y;	
		}
    }
   public static byte Rank3_diyi;
    int icont;
    int laojia1[][]={{0,0,48,124},{48,0,48,124}};
	int laojia2[][]={{0,0,42,112},{42,0,42,112}};
	int laojia3[][]={
			{4,2,38,29},/*图片说明*/
			{44,70,37,35},/*图片说明*/
			{42,34,38,34},/*图片说明*/
			{1,35,41,35},/*图片说明*/
			{4,71,40,34},/*图片说明*/
			{40,1,37,30}};/*图片说明*/
  
	int laojia4[][]={{0,0,42,112},{42,0,42,112}};
	int laojia5[][]={{0,0,33,122},{33,0,35,122}}; 
	int sandan [] = {0,0,0,0,1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,5,5,5,5,5};
	int [][] sanDanData ={
			{0,0,98,35},/*图片说明*/
			{0,34,100,40},/*图片说明*/
			{0,75,100,40},/*图片说明*/
			{0,114,99,38},/*图片说明*/
			{0,151,99,38},/*图片说明*/
			{0,189,97,34},
			{0,189,97,34},
			{0,189,97,34},
			{0,189,97,34},
			};/*图片说明*/
	
    public void drawBullet(int type){
       
        //    	switch(iRank){
    	switch(type){ 
    	case -1://豌豆
    		GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI1,iX-1,iY,182,33,28,49,
    		    				Tools.HCENTER_VCENTER,Tools.TRANS_NONE,2231,iDir);
    		break;
    	case 0://豌豆
    		GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI1,iX-1,iY,182,33,28,49,
    				Tools.HCENTER_VCENTER,Tools.TRANS_NONE,2231,iDir);
    		break;
    	case 1://西红柿
    		GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI12,iX-1,iY,307,11,36,72,
    				Tools.HCENTER_VCENTER,Tools.TRANS_NONE,2231,iDir);
    		break;
    	case 2://仙人掌
//    		GameDraw.add_ImageRota(PAK_IMAGES.IMG_LAOJIA2ZIDAN,iX-1,iY,laojia2[MyGameCanvas.me.gameTime/5%2],
//        				Tools.HCENTER_VCENTER,Tools.TRANS_NONE,132,iDir);
    		GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI13,iX-1,iY,371,1,33,97,
    				Tools.HCENTER_VCENTER,Tools.TRANS_NONE,2231,iDir);
    		break;
    	case 3://玉米
//    		GameDraw.add_ImageRota(PAK_IMAGES.IMG_XIAODITU32,iX-1-30,iY-60,laojia3[MyGameCanvas.me.gameTime/5%6],
//    				Tools.HCENTER_VCENTER,Tools.TRANS_NONE,132,iDir);
//    		GameDraw.add_ImageRota(PAK_IMAGES.IMG_XIAODITU32,iX-1,iY,laojia3[MyGameCanvas.me.gameTime/5%6],
//    				Tools.HCENTER_VCENTER,Tools.TRANS_NONE,132,iDir);
//			GameDraw.renderAnimPic22(PAK_IMAGES.IMG_XIAODITU32,0,iX-1,iY,
//					MyGameCanvas.data_SANDAN,false,false,132,0,0,GameEngine.time%360);
    		//三蛋
    		GameDraw.add_ImageRota(PAK_IMAGES.IMG_JIDIXUELIANGTISHI,iX-1,iY,sanDanData[GameEngine.time/2%6],
    				Tools.HCENTER_VCENTER,Tools.TRANS_NONE,2231,iDir);
    		
    			
    		
    		break;
    	case 4://大狙
    		GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI23,iX-1+10,iY,260,4,28,125,
    				Tools.HCENTER_VCENTER,Tools.TRANS_NONE,2231,iDir);
    		break;
    	case 5://大蒜
    		GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI22,iX-1,iY,320,35,28,48,
    				Tools.HCENTER_VCENTER,Tools.TRANS_NONE,2231,iDir);
    		break;
		case 6: //西瓜
	  		GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI3,iX-1,iY,326,39,36,59,
    				Tools.HCENTER_VCENTER,Tools.TRANS_NONE,2231,iDir);
			break;
		case 7: //南瓜
	  		GameDraw.add_ImageRota(PAK_IMAGES.IMG_PAOTAI32,iX-1,iY,360,41,44,46,
    				Tools.HCENTER_VCENTER,Tools.TRANS_NONE,2231,iDir);
			
			break;
    	}
    	
    }
    int checkJinBi(int type){
    	int money=0;
    	switch (type) {
		case GameRole.TYPE_ENEMY_步兵:
			money=2;
			break;
		case  GameRole.TYPE_ENEMY_金盾:
			money=15;
			break;
		case GameRole.TYPE_ENEMY_暗牧:
			money=24;
			break;
		case GameRole.TYPE_ENEMY_牧师:
			money=5;
			break;
		case GameRole.TYPE_ENEMY_刺客:
			money=7;
			break;
		case GameRole.TYPE_ENEMY_光头:
			money=45;
			break;
		case GameRole.TYPE_ENEMY_石盾:
			money=15;
			break;
		case GameRole.TYPE_ENEMY_鹰:
			money=8;
			break;
		case GameRole.TYPE_ENEMY_紫盾:
			money=24;
			break;
		case GameRole.TYPE_ENEMY_绿盾:
			money=19;
			break;
		}
//		return money*(GameBilling.isBilling(GameBilling.Billing_永久金币双倍)?2:1);
		return money;
    }
    void chekType(JiangLiRank99 DaoJu){
    	switch (DaoJu.type) {
		case 0:
			DaoJu.addMoney(20);
			break;
		case 1:
			DaoJu.addMoney(50);
			break;
		case 2:
			DaoJu.cutMoney(20);
			break;
		case 3:
			DaoJu.cutMoney(50);
			break;
		case 4:
			MyGameCanvas.engine.ziDan+=3;
			break;
		case 5:
			DaoJu.isStop = true;
			break;
		case 6:
			DaoJu.isJiaS = true;
			break;
		case 7:
			switch (GameRandom.result(0, 6)) {
			case 0:
				DaoJu.addMoney(20);
				break;
			case 1:
				DaoJu.addMoney(50);
				break;
			case 2:
				DaoJu.cutMoney(20);
				break;
			case 3:
				DaoJu.cutMoney(50);
				break;
			case 4:
				MyGameCanvas.engine.ziDan+=3;
				break;
			case 5:
				DaoJu.isStop = true;
				break;
			case 6:
				DaoJu.isJiaS = true;
				break;
			}
			break;

		}
    	
    }
    
    
/**
 * 添加箭矢打击特效    
 * @param type
 * @param x
 * @param y
 */
    int dataX,dataY;
    public void addEffect(int rank,int x,int y,int type){
    	if(type==GameRole.TYPE_goldFish){dataX = 10;dataY=20;}
    	else{dataX=0;}
    	switch(rank){
    	case 1:
    		MyGameCanvas.AddBlastEffectList(x+dataX,y-dataY,MyGameCanvas.me.EFF_LAOJIA1ZIDAN,0,121,(int)MyGameCanvas.engine.usBs.UsBsRota);
    		break;
    	case 2:
    		MyGameCanvas.AddBlastEffectList(x+dataX,y-dataY,MyGameCanvas.me.EFF_LAOJIA2ZIDAN,0,121,0);
    		break;
    	case 3:
    		MyGameCanvas.AddBlastEffectList(x+dataX,y-dataY,MyGameCanvas.me.EFF_LAOJIA3ZIDAN,0,121,0);
    		break;
    	case 4:
    		MyGameCanvas.AddBlastEffectList(x+dataX,y-dataY,MyGameCanvas.me.EFF_LAOJIA4ZIDAN,0,119,0);
    		break;
    	case 5:
    		MyGameCanvas.AddBlastEffectList(x+dataX,y-dataY,MyGameCanvas.me.EFF_LAOJIA5ZIDAN,0,119,0);
    		break;
    	case 6:
    		MyGameCanvas.AddBlastEffectList(x+dataX,y-dataY,MyGameCanvas.me.EFF_LAOJIA3ZIDAN,0,121,0);
    		break;
    	case 7:
    		MyGameCanvas.AddBlastEffectList(x+dataX,y-dataY,MyGameCanvas.me.EFF_LAOJIA2ZIDAN,0,121,0);
    		break;
    	case 8:
    		MyGameCanvas.AddBlastEffectList(x+dataX,y-dataY,MyGameCanvas.me.EFF_LAOJIA2ZIDAN,0,121,0);
    		break;
    	case 100:
    		
    		if(type==GameRole.TYPE_ENEMY_光头){
    		
    		MyGameCanvas.AddBlastEffectList(x+dataX,y-dataY,MyGameCanvas.me.EFF_死亡,0,GameRole.TYPE_ENEMY_光头,GameRole.TYPE_ENEMY_光头);	
    		}else{
    		MyGameCanvas.AddBlastEffectList(x+dataX,y-dataY,MyGameCanvas.me.EFF_死亡,0,119,0);	
    		}
    		break;
    	case 101: 
    		MyGameCanvas.AddBlastEffectList(x+dataX,y-dataY,MyGameCanvas.me.EFF_眩晕,0,121,0);	
    		break;
    	case 102:
    		MyGameCanvas.AddBlastEffectList(x+dataX,y-dataY,MyGameCanvas.me.EFF_停顿,0,121,0);	
    		break;
    	case 103:
    		MyGameCanvas.AddBlastEffectList(x+dataX,y-dataY,MyGameCanvas.me.EFF_爆炸,0,121,0);	
    		break;
    	case 104://炮台子弹爆炸效果
    		MyGameCanvas.AddBlastEffectList(x+dataX,y-dataY,(byte) 104,0,121,0);	
    		break;
    	case 105://棺材
    		MyGameCanvas.AddBlastEffectList(x+dataX,y-dataY,(byte) 105,type,121,0);
    		break;
    	case 106://提示语 一大拨僵尸来了
    		MyGameCanvas.AddBlastEffectList(0,0,(byte) 106,0,0,0);
    		break;
    	}
    	
    }	
    public void addEffect_2(GameRole en){
    	MyGameCanvas.EffectV2.addElement(en);
    }
/**
 * 根据箭塔与怪物的坐标，算出箭矢旋转角度
 * @param x1
 * @param y1箭塔的坐标
 * @param x2
 * @param y2怪物的坐标    
 * @param jd
 */
    public int setDir(int x1,int y1,int x2,int y2){
    	
    	int len = (int) Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));//两点间的距离
    	int jd = 0;//箭矢旋转角度
    	float a = (float)(x2-x1)/(float)len ;
    	if(a==0){//怪物位于箭塔的正上下方时存在
    		if(y2-y1>=0){//下方
    			jd = 180;
    		}else{
    			jd = 0;
    		}
    	}else{
    		if(a<0){//怪物位于箭塔的左侧上下区域
    			if(y2-y1==0){//怪物与箭塔同处一条直线
    				jd = 270;
    			}else{
    				if(y2-y1>0){//左下
    					jd =  180+(int)(180*asin(Math.abs(a))/PI);
    				}else{//左上
    					jd = 360-(int)(180*asin(Math.abs(a))/PI);
    				}
    			}
    		}else{//怪物位于箭塔的右侧上下区域
    			if(y2-y1==0){//怪物与箭塔同处一条直线
    				jd = 90;
    			}else{
    				if(y2-y1>0){//右下
    					jd =  180-(int)(180*asin(Math.abs(a))/PI);
    				}else{//右上
    					jd = (int)(180*asin(Math.abs(a))/PI);
    				}
    			}
    		}
    	}
    	iDir = jd;
    	return iDir;
    }
    
/**    
 * 求两点间的距离
 * @param x1
 * @param y1
 * @param x2
 * @param y2
 * @return
 */
    public static int GetDistance(int x1, int y1, int x2, int y2) {
		return (int) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
/**
 * 判断是否位于园内 
 * @param x
 * @param y
 * @param cx
 * @param cy
 * @param cr
 * @return
 */
	public static boolean bInCircle(int x, int y, int cx, int cy, int cr) {
		int sw = MyActivity.VMWidth;
		int sh = MyActivity.VMHeight;
		float kx = (float) sw / MyGameCanvas.SCREEN_WIDTH;
		float ky = (float) sh / MyGameCanvas.SCREEN_HEIGHT;
		return (x - cx) * (x - cx) + (y - cy) * (y - cy) <= cr * cr;
	}
/**
 * 判断是否位于矩形范围内	
 * @param x
 * @param y
 * @param sx
 * @param sy
 * @param w
 * @param h
 * @return
 */
	public boolean IsInRect(int x, int y, int sx, int sy, int w, int h) {
		int sw = MyActivity.VMWidth;
		int sh = MyActivity.VMHeight;
		float kx = (float) sw / MyGameCanvas.SCREEN_WIDTH;
		float ky = (float) sh / MyGameCanvas.SCREEN_HEIGHT;
		if (x > sx&& x < (sx + w) && y > sy && y < (sy + h)) {
			return true;
		}
		return false;
	}
/**
 * 获取圆的轨迹	
 * @param r圆心
 * @param a圆心x坐标
 * @param b圆心y坐标
 * @param delay 角度
 * @return
 */
	public  int[] getCircle(int r,int a,int b,float delay){
		circle[0] = (int) (r*sin(delay)) + a;
		circle[1] = (int) (r*sin(delay)) + b;
		return circle;
		
	}
/**
 * 数学三角函数 
 * @param jd
 * @return
 */
    public static float sin(float jd){return (float) Math.sin(jd);}
    public static float c(float jd){return (float) Math.cos(jd);}
    public static float asin(float dirty){return (float) Math.asin(dirty);}
    public static float acos(float dirty){return (float) Math.acos(dirty);}
}
