package com.haopu.Enemy;

import com.haopu.JSGame.GameEngine;

public class FISH {

	public static GameEngine engine;
//敌人的种类	
//	public static final int 
	
	int start_X,start_Y;//怪物的起始坐标
	int speedX,speedY;//怪物的移动速度
	
	int iGroup[]={};//每一关对应怪物的总波数
	int iGroup_left;//当前关卡剩余怪物波数
	int iCount;//每一关怪物产出量
	
	int iDefence[]={};//不同种类怪物的防御力
	int iHp[]={};//不同种类怪物对应的血量
	int iDer;//怪物的方向
	
	
	public void FISH(GameEngine ge){
		engine = ge;
		
	}
}
