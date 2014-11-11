package com.haopu.JSGame;


import com.haopu.kbz.*;



//import java.io.DataInputStream;
//import java.io.InputStream;
//import java.io.ByteArrayInputStream;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: 南京浩普
 */

// import com.haopu.*;

public class GameData extends GameInterface {

	public GameData() {
	}
	
//猎鱼塔	
//	final static short[][] motion_lieyuta = {
//		{STATUS_STOP,1,STATUS_STOP,0,0,0,0},
//	    {STATUS_ATTACK,1,STATUS_STOP,0,0,0,0,0,1,1,1,1,1,1},
//	    {STATUS_STOP,2, STATUS_STOP,2,2,2,2},
//	    {STATUS_ATTACK,2,STATUS_STOP,2,2,2,2,2,3,3,3,3,3,3},
//	    {STATUS_STOP, 3,STATUS_STOP,4,4,4,4},
//	    {STATUS_ATTACK,3,STATUS_STOP,4,4,4,4,4,5,5,5,5,5,5},
//	    {STATUS_STOP, 4,STATUS_STOP,6,6,6,6},
//	    {STATUS_ATTACK,4,STATUS_STOP,6,6,6,6,6,7,7,7,7,7,7},
//	    {STATUS_STOP, 5,STATUS_STOP,8,8,8,8},
//	    {STATUS_ATTACK,5,STATUS_STOP,8,8,8,8,8,9,9,9,9,9,9}
//	};
//鱼雷塔	
//	final static short[][] motion_yutata = {
//		{STATUS_STOP,1,STATUS_STOP,0,0,0,0},
//		{STATUS_ATTACK,1,STATUS_STOP,0,0,0,0,0,1,1,1,1,1,1},
//		{STATUS_STOP,2, STATUS_STOP,2,2,2,2},
//		{STATUS_ATTACK,2,STATUS_STOP,2,2,2,2,2,3,3,3,3,3,3},
//		{STATUS_STOP, 3,STATUS_STOP,4,4,4,4},
//		{STATUS_ATTACK,3,STATUS_STOP,4,4,4,4,4,5,5,5,5,5,5},
//		{STATUS_STOP, 4,STATUS_STOP,6,6,6,6},
//	    {STATUS_ATTACK,4,STATUS_STOP,6,6,6,6,6,7,7,7,7,7,7},
//		{STATUS_STOP, 5,STATUS_STOP,8,8,8,8},
//		{STATUS_ATTACK,5,STATUS_STOP,8,8,8,8,8,9,9,9,9,9,9}
//	};
//冰晶塔	
//	final static short[][] motion_bingjingta = {
//		{STATUS_STOP,1,STATUS_STOP,0,0,0,0},
//		{STATUS_ATTACK,1,STATUS_STOP,0,0,0,0,0,1,1,1,1,1,1},
//		{STATUS_STOP,2, STATUS_STOP,2,2,2,2},
//		{STATUS_ATTACK,2,STATUS_STOP,2,2,2,2,2,3,3,3,3,3,3},
//		{STATUS_STOP, 3,STATUS_STOP,4,4,4,4},
//		{STATUS_ATTACK,3,STATUS_STOP,4,4,4,4,4,5,5,5,5,5,5},
//		{STATUS_STOP, 4,STATUS_STOP,6,6,6,6},
//	    {STATUS_ATTACK,4,STATUS_STOP,6,6,6,6,6,7,7,7,7,7,7},
//		{STATUS_STOP, 5,STATUS_STOP,8,8,8,8},
//		{STATUS_ATTACK,5,STATUS_STOP,8,8,8,8,8,9,9,9,9,9,9}
//	};
//增幅塔	
//	final static short[][] motion_zengfuta = {
//		{STATUS_STOP,1,STATUS_STOP,0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,9},
//		{STATUS_ATTACK,1,STATUS_STOP,0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,9},
//		{STATUS_STOP,2, STATUS_STOP,10,10,11,11,12,12,13,13,14,14,15,15,16,16,17,17,18,19},
//		{STATUS_ATTACK,2,STATUS_STOP,10,10,11,11,12,12,13,13,14,14,15,15,16,16,17,17,18,19},
//		{STATUS_STOP, 3,STATUS_STOP,20,20,21,21,22,22,23,23,24,24,25,25,26,26,27,27,28,28,29},
//		{STATUS_ATTACK,3,STATUS_STOP,20,20,21,21,22,22,23,23,24,24,25,25,26,26,27,27,28,28,29},
//		{STATUS_STOP, 4,STATUS_STOP,20,20,21,21,22,22,23,23,24,24,25,25,26,26,27,27,28,28,29},
//	    {STATUS_ATTACK,4,STATUS_STOP,20,20,21,21,22,22,23,23,24,24,25,25,26,26,27,27,28,28,29},
//		{STATUS_STOP, 5,STATUS_STOP,20,20,21,21,22,22,23,23,24,24,25,25,26,26,27,27,28,28,29},
//		{STATUS_ATTACK,5,STATUS_STOP,20,20,21,21,22,22,23,23,24,24,25,25,26,26,27,27,28,28,29}
//	};
//电网塔
//	final static short[][] motion_dianwangta = {
//		{STATUS_STOP,1,STATUS_STOP,0,0,0,0},
//		{STATUS_ATTACK,1,STATUS_STOP,0,0,0,0,0,1,1,1,1,1,1},
//		{STATUS_STOP,2, STATUS_STOP,2,2,2,2},
//		{STATUS_ATTACK,2,STATUS_STOP,2,2,2,2,2,3,3,3,3,3,3},
//		{STATUS_STOP, 3,STATUS_STOP,4,4,4,4},
//		{STATUS_ATTACK,3,STATUS_STOP,4,4,4,4,4,5,5,5,5,5,5},
//		{STATUS_STOP, 4,STATUS_STOP,6,6,6,6},
//	    {STATUS_ATTACK,4,STATUS_STOP,6,6,6,6,6,7,7,7,7,7,7},
//		{STATUS_STOP, 5,STATUS_STOP,8,8,8,8},
//		{STATUS_ATTACK,5,STATUS_STOP,8,8,8,8,8,9,9,9,9,9,9}
//	};
//火炮塔	
//	final static short[][] motion_huopaota = {
//		{STATUS_STOP,1,STATUS_STOP,0,0,0,0},
//		{STATUS_ATTACK,1,STATUS_STOP,0,0,0,0,0,1,1,1,1,1,1},
//		{STATUS_STOP,2, STATUS_STOP,2,2,2,2},
//		{STATUS_ATTACK,2,STATUS_STOP,2,2,2,2,2,3,3,3,3,3,3},
//		{STATUS_STOP, 3,STATUS_STOP,4,4,4,4},
//		{STATUS_ATTACK,3,STATUS_STOP,4,4,4,4,4,5,5,5,5,5,5},
//		{STATUS_STOP, 4,STATUS_STOP,6,6,6,6},
//	    {STATUS_ATTACK,4,STATUS_STOP,6,6,6,6,6,7,7,7,7,7,7},
//		{STATUS_STOP, 5,STATUS_STOP,8,8,8,8},
//		{STATUS_ATTACK,5,STATUS_STOP,8,8,8,8,8,9,9,9,9,9,9}
//	};		
//英雄塔	
//	final static short[][] motion_herota = {
//		{STATUS_STOP,1,STATUS_STOP,0,0,0,0},
//		{STATUS_ATTACK,1,STATUS_STOP,0,0,0,0,0,1,1,1,1,1,1},
//		{STATUS_STOP,2, STATUS_STOP,2,2,2,2},
//		{STATUS_ATTACK,2,STATUS_STOP,2,2,2,2,2,3,3,3,3,3,3},
//		{STATUS_STOP, 3,STATUS_STOP,4,4,4,4},
//		{STATUS_ATTACK,3,STATUS_STOP,4,4,4,4,4,5,5,5,5,5,5},
//		{STATUS_STOP, 4,STATUS_STOP,6,6,6,6},
//	    {STATUS_ATTACK,4,STATUS_STOP,6,6,6,6,6,7,7,7,7,7,7},
//		{STATUS_STOP, 5,STATUS_STOP,8,8,8,8},
//		{STATUS_ATTACK,5,STATUS_STOP,8,8,8,8,8,9,9,9,9,9,9}
//	};
//吸钱塔	
//	final static short[][] motion_xiqianta = {
//		{STATUS_STOP,1,STATUS_STOP,0,0,1,1,2,2,3,3},
//		{STATUS_ATTACK,1,STATUS_STOP,0,0,1,1,2,2,3,3},
//		{STATUS_STOP,2, STATUS_STOP,4,4,5,5,6,6,7,7},
//		{STATUS_ATTACK,2,STATUS_STOP,4,4,5,5,6,6,7,7},
//		{STATUS_STOP, 3,STATUS_STOP,8,8,9,9,10,10,11,11},
//		{STATUS_ATTACK,3,STATUS_STOP,8,8,9,9,10,10,11,11},
//		{STATUS_STOP, 4,STATUS_STOP,8,8,9,9,10,10,11,11},
//	    {STATUS_ATTACK,4,STATUS_STOP,8,8,9,9,10,10,11,11},
//		{STATUS_STOP, 5,STATUS_STOP,8,8,9,9,10,10,11,11},
//		{STATUS_ATTACK,5,STATUS_STOP,8,8,9,9,10,10,11,11}
//	};			
//	final static short[][] motion_fish1={
//		{STATUS_MOVE, STATUS_MOVE,0,1,2,3,4,5,6,7,8,9},
//		{STATUS_DEAD,STATUS_NULL,10,10,11,11,12,12,11,11}
//	};
//	final static short[][] motion_zidan = {//子弹
//	    {STATUS_MOVE, STATUS_MOVE,10,10,10},
//	    {STATUS_ATTACK,STATUS_NULL,11,12,13,14,15}
//	};
//	
////怪物步兵	
//	final static short[][] motion_TYPE_ENEMY_步兵={
//		{STATUS_MOVE, STATUS_MOVE,0,1,2,3,4,5,6},
//		{STATUS_STOP,STATUS_STOP,0},
//		{STATUS_DEAD,STATUS_DEAD,0}
//	};
////怪物牧师,暗牧
//	final static short[][] motion_TYPE_ENEMY_牧师={
//		{STATUS_MOVE, STATUS_MOVE,0,1,2,3,4,5,6,7},
//		{STATUS_STOP,STATUS_STOP,0,0},
//		{STATUS_DEAD,STATUS_DEAD,0}
//	};
////怪物刺客,黑刺客
//	final static short[][] motion_TYPE_ENEMY_刺客={
//		{STATUS_MOVE, STATUS_MOVE,0,1,2,3,4,5,6,7},
//		{STATUS_STOP,STATUS_STOP,0,0},
//		{STATUS_DEAD,STATUS_DEAD,0}
//	};
////光头	
//	final static short[][] motion_TYPE_ENEMY_光头={
//		{STATUS_MOVE, STATUS_MOVE,0,1,2,3,4,5,6,7,8,9,10,11},
//		{STATUS_STOP,STATUS_STOP,0,0},
//		{STATUS_DEAD,STATUS_DEAD,0}
//	};
////鹰	
//	final static short[][] motion_TYPE_ENEMY_鹰={
//		{STATUS_MOVE, STATUS_MOVE,0,1,2,3,4,5,6,7,8,9},
//		{STATUS_STOP,STATUS_STOP,0,0},
//		{STATUS_DEAD,STATUS_DEAD,0}
//	};
////石盾	
//	final static short[][] motion_TYPE_ENEMY_石盾={
//		{STATUS_MOVE, STATUS_MOVE,0,1,2,3,4,5,6,7},
//		{STATUS_STOP,STATUS_STOP,0,0},
//		{STATUS_DEAD,STATUS_DEAD,0}
//	};
////金盾	
//	final static short[][] motion_TYPE_ENEMY_金盾={
//		{STATUS_MOVE, STATUS_MOVE,0,1,2,3,4,5,6,7,4},
//		{STATUS_STOP,STATUS_STOP,0,0},
//		{STATUS_DEAD,STATUS_DEAD,0}
//	};
////绿盾	
//	final static short[][] motion_TYPE_ENEMY_绿盾={
//		{STATUS_MOVE, STATUS_MOVE,0,1,2,3,4,5,6,7},
//		{STATUS_STOP,STATUS_STOP,0,0},
//		{STATUS_DEAD,STATUS_DEAD,0}
//	};
////紫盾	
//	final static short[][] motion_TYPE_ENEMY_紫盾={
//		{STATUS_MOVE, STATUS_MOVE,0,1,2,3,4,5,6,7},
//		{STATUS_STOP,STATUS_STOP,1,1},
//		{STATUS_DEAD,STATUS_DEAD,0}
//	};
////狮鹫，黑狮鹫	
//	final static short[][] motion_TYPE_ENEMY_狮鹫={
//		{STATUS_MOVE, STATUS_MOVE,0,1,2,3,4,5,6,7},
//		{STATUS_STOP,STATUS_STOP,0,0},
//		{STATUS_DEAD,STATUS_DEAD,0}
//	};
//步兵boss	
//	final static short[][] motion_TYPE_ENEMY_步兵BOSS={
//		{STATUS_MOVE, STATUS_MOVE,0,1,2,3,4,5,6,7},
//		{STATUS_STOP,STATUS_STOP,0,0},
//		{STATUS_DEAD,STATUS_DEAD,0}
//	};
//骑兵boss	
//	final static short[][] motion_TYPE_ENEMY_骑兵BOSS={
//		{STATUS_MOVE, STATUS_MOVE,0,1,2,3,4,5,6,7},
//		{STATUS_STOP,STATUS_STOP,0,0},
//		{STATUS_DEAD,STATUS_DEAD,0}
//	};
////大剑boss	
//	final static short[][] motion_TYPE_ENEMY_大剑BOSS={
//		{STATUS_MOVE, STATUS_MOVE,0,1,2,3,4,5,6,7,8,9},
//		{STATUS_STOP,STATUS_STOP,0,0},
//		{STATUS_DEAD,STATUS_DEAD,0}
//	};
////重甲boss	
//	final static short[][] motion_TYPE_ENEMY_重甲BOSS={
//		{STATUS_MOVE, STATUS_MOVE,0,1,2,3,4,5,6,7,8,9},
//		{STATUS_STOP,STATUS_STOP,0,0},
//		{STATUS_DEAD,STATUS_DEAD,0}
//	};
////黄金鱼	
//	final static short[][] motion_TYPE_ENEMY_goldFish={
//		{STATUS_MOVE, STATUS_MOVE,0,1,2,3,4,5,6,7,8,9},
//		{STATUS_STOP,STATUS_STOP,0,0},
//		{STATUS_DEAD,STATUS_DEAD,0}
//	};	
//	
//	final static short[][]motion_beike={
//		{STATUS_STOP,STATUS_STOP,3,0,1,2,4,2,1,0}
//	};
//	final static short[][]motion_haixing={
//		{STATUS_STOP,STATUS_STOP,0,1,2,4,5,6,5,4,2,1}
//	};
//	final static short[][]motion_shuicao={
//		{STATUS_STOP,STATUS_STOP,5,0,2,3,4,3,2,0}
//	};
	

}

