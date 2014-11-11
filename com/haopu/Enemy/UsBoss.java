package com.haopu.Enemy;

import com.haopu.kbz.GameInterface;

public class UsBoss {

	public int iUsBsX,iUsBsY,iUsBsBR,iUsBsSR;//老家圆心及大半斤，小半径
	public int iUsBsStrength = 100 ;//自己老家的能量
	public int iUsBsDelay ;//老家攻击冷却时间
	public int iUsBsRank ;//老家的等级
	public float UsBsRota;//老家旋转家督
	public int usBsStatus=GameInterface.STATUS_STOP;//老家当前状态
	public int iUsBsAttackTiao;//老家攻击条的长度,注:3个长度条最大值为180
	public int iUsBsHpTiao;//老家生命条的长度 
	public int iUsBsStrengthTiao;//老家能量条
	public boolean bAttackCenter = false;//老家身上是否已经开启过攻击核心，才buff不能叠加
	public int iShopHome[] = new int []{3000,5000,10000,30000,30000};//老家升级所对应的iGold价格
	public int iUsBsCurAttack;//boss当前攻击力
	public int iUsBsAttack[] = new int[]{10,15,30,45,60};//老家5个等级对应的攻击力
	public int iJinbi[] = new int[]{600,600,600,600,600};//每关初始后给予的造塔金币
	public int iUsBsCuJinbi=8000;//玩家当前拥有金币 
	public int rankMoney=0;
	public int iAttackTime;//老家攻击冷却时间
	public int iUsTargetX,iUsTargetY;//老家的目标射击点坐标
	public boolean bHomeAttack;//判断老家是否攻击
	public boolean laoJiaWuDi=false;
	public boolean sheSuJiaBei=false;
	public int jiaSuTime;
	public int touMing;
	public float Scale=1.5f;
	public UsBoss(){
		iUsBsRank = 1;
		iUsBsAttackTiao = 20;
		iUsBsHpTiao = 60;
		iUsBsStrengthTiao = 40;
	}
	public int getCir(int rank){
		iUsBsBR = 100+100*(rank);
		return iUsBsBR;
	}
	public int getSCir(int rank){//半径即为老家图的半径
		switch(rank){
		case 1:
			iUsBsSR = 0;
			break;
		case 2:
			iUsBsSR = 0;
			break;
		case 3:
			iUsBsSR = 0;
			break;
		}
		return iUsBsSR;
	}
/**
 * 每次升级主基地，相应数据发生变化
 * 主基地发生变化的主要包括:攻击力，生命，能量以及各自对应的条长度	
 */
//	public void setUsBs(int attack,int hp,int strength,int attTiao,int hpTiao,int strTiao){
//		iUsBsAttack = attack;
//		iUsBsHp = hp;
//		iUsBsStrength = strength;
//		iUsBsStrengthTiao = strTiao;
//		iUsBsAttackTiao = attTiao;
//		iUsBsHpTiao = hpTiao;
//		iUsBsRank+=1;
//	}
}
