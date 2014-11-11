package com.haopu.Enemy;

import com.haopu.JSGame.GameEngine;
import com.haopu.JSGame.GameRole;
import com.haopu.JSGame.MyGameCanvas;
import com.haopu.kbz.GameInterface;

public class fishData extends GameInterface{
	fishData fishData;
	public final static int TYPE_ENEMY_步兵 = 20;
	public final static int TYPE_ENEMY_牧师 = 21;
	public final static int TYPE_ENEMY_刺客 = 22;
	public final static int TYPE_ENEMY_光头 = 23;
	public final static int TYPE_ENEMY_鹰 = 24;
	public final static int TYPE_ENEMY_石盾 = 25;
	public final static int TYPE_ENEMY_金盾 = 26;
	public final static int TYPE_ENEMY_绿盾 = 27;
	public final static int TYPE_ENEMY_紫盾 = 28;
	public final static int TYPE_ENEMY_暗牧= 29;
	
	public final static int TYPE_ENEMY_狮鹫 = 30;
	public final static int TYPE_ENEMY_黑刺客 = 31;
	public final static int TYPE_ENEMY_黑狮鹫 = 32;
	public final static int TYPE_ENEMY_步兵BOSS = 33;
	public final static int TYPE_ENEMY_骑兵BOSS = 34;
	public final static int TYPE_ENEMY_大剑BOSS = 35;
	public final static int TYPE_ENEMY_重甲BOSS = 36;
	public final static int TYPE_goldFish = 61;
	public int[][]enemyTS;//特殊怪物数组
	public int[][][] enemyData;//怪物数组
	public int [][][] enemyPlace;//每一波敌人的出兵点
	/**
	 * //1.第几波出兵
	 * 2.第几个怪物
	 * 3.x 
	 * 4.y 
	 * 5.兵种类型
	 * 6.任务方向
	 */
	public int [][] Guancai;//棺材出兵
	public int [][] Guancai2;//棺材出兵
	public fishData(){
		
	}
	/**
	 * 根据波数得出对应的出兵点
	 * @param rank
	 * @param wave
	 * @param status
	 */
	    public static int isrank2;
	    public static boolean isJiJia=false;
	    public static int  jiJiaHPMax;
		public void getWavePlace(int status,int rank,int wave){
			if(rank<=5){//第一大关
				GameEngine.paotaix = 170;
				GameEngine.paotaiy = 228;
				GameEngine.paotaiZDX = 170;
				GameEngine.paotaiZDY = 228;
				isrank2=25;
			}
			if(rank>5&&rank<=11){//第二大关
				GameEngine.paotaix = 466;
				GameEngine.paotaiy = 114;
				isrank2=25;
			}
		    if(rank>17&&rank<=23){//第四大关
		    	GameEngine.paotaix = 200;
				GameEngine.paotaiy = 200;
				GameEngine.paotaiZDX = 200;
				GameEngine.paotaiZDY = 200;
				isrank2=25;
			}
		    isJiJia = false;
		  
			switch(status){
			case MyGameCanvas.gmScripe:
				switch(rank){
				case 99:
					enemyPlace = new int[][][]
							{
							{{5,0, DIR_RIGHT },{6,0, DIR_RIGHT },{7,0, DIR_RIGHT}}};
					break;
				case 0:
						enemyPlace = new int[][][]
								{{{2,7, DIR_DOWN },{2,12, DIR_DOWN }},
								{{1,7, DIR_DOWN },{2,12, DIR_DOWN },{1,14, DIR_DOWN}},
								};
					break;
				case 1:
					enemyPlace = new int[][][]
							{{{4,0, DIR_RIGHT },{1,6, DIR_DOWN },{1,13, DIR_DOWN },{7,18, DIR_LEFT}    },
							{{4,0, DIR_RIGHT },{1,6, DIR_DOWN },{1,13, DIR_DOWN },{7,18, DIR_LEFT}}};
					break;
				case 2:
					enemyPlace = new int[][][]
							{  {{4,0, DIR_RIGHT},{1,6, DIR_DOWN },{1,13, DIR_DOWN },{8,18, DIR_LEFT}},
							{  {4,0, DIR_RIGHT },{1,6, DIR_DOWN },{1,12, DIR_DOWN },{8,18, DIR_LEFT}}};
					break;
				case 3:
					enemyPlace = new int[][][]
//							{{{5,3, DIR_RIGHT },{0,3, DIR_DOWN },{0,10, DIR_DOWN}}};
					{   {  {4,0, DIR_RIGHT },{0,6, DIR_DOWN },{0,13, DIR_DOWN}  },
						{  {4,0, DIR_RIGHT },{0,6, DIR_DOWN },{0,13, DIR_DOWN}  }    };

					break;
				case 4:
					enemyPlace = new int[][][]
//							{{{4,0, DIR_RIGHT },{1,7, DIR_DOWN },{1,12, DIR_DOWN },{1,14, DIR_DOWN}},{{4,0, DIR_RIGHT },{1,4, DIR_DOWN },{1,12, DIR_DOWN },{1,14, DIR_DOWN}}};
							{    {  {4,0, DIR_RIGHT },{0,6, DIR_DOWN },{0,13, DIR_DOWN}  },
							{  {4,0, DIR_RIGHT },{0,6, DIR_DOWN },{0,13, DIR_DOWN}  }    };

							break;
				case 5:
					isJiJia = true;
					GameEngine.JiJiaXY = new int [][]{{220,300,1000,-1},{700,100,1000,-1}};
					enemyPlace = new int[][][]
							{{{4,0, DIR_RIGHT },{1,7, DIR_DOWN },{1,14, DIR_DOWN}  },
							{{4,0, DIR_RIGHT },{1,7, DIR_DOWN },{1,12, DIR_DOWN },{1,14, DIR_DOWN} },
							{  {4,0, DIR_RIGHT },{1,7, DIR_DOWN },{1,14, DIR_DOWN}  }    };
					break;
				case 6://第二大关 
					enemyPlace = new int[][][]
							{{ {8,0, DIR_RIGHT },{0,0, DIR_RIGHT },{0,16, DIR_DOWN },{3,18, DIR_LEFT}  }    };
					break;
				case 7:
					enemyPlace = new int[][][]
							{    { {8,0, DIR_RIGHT},{0,16, DIR_DOWN },{5,18, DIR_LEFT}  },
							{  {8,0, DIR_RIGHT },{0,0, DIR_RIGHT },{0,16, DIR_DOWN },{5,18, DIR_LEFT}  }    };

					break;
				case 8:
					enemyPlace = new int[][][]
							{    {  {8,0, DIR_RIGHT },{0,2, DIR_DOWN },{0,16, DIR_DOWN}  },
   							{  {8,0, DIR_RIGHT },{0,2, DIR_DOWN },{0,16, DIR_DOWN}  }    };
					break;
				case 9:
					enemyPlace = new int[][][]
							{    {{8,0, DIR_RIGHT },{0,0, DIR_RIGHT },{0,16, DIR_DOWN },{8,13, DIR_DOWN}  },{  {8,0, DIR_RIGHT },{0,0, DIR_RIGHT },{0,16, DIR_DOWN },{5,13, DIR_DOWN}  }    };

					break;
				case 10:
					enemyPlace = new int[][][]
							{    {  {8,0, DIR_RIGHT },{0,2, DIR_DOWN },{0,16, DIR_DOWN}  },
   							{  {8,0, DIR_RIGHT },{0,2, DIR_DOWN },{0,16, DIR_DOWN}}};
					break;
				case 11:
					isJiJia = true;
					GameEngine.JiJiaXY = new int [][]{{400,111,1000,-1},{550,154,1000,-1}};
					enemyPlace = new int[][][]
							{    { {8,0, DIR_RIGHT },{0,0, DIR_RIGHT },{0,16, DIR_DOWN }  },
						{  {8,0, DIR_RIGHT },{0,0, DIR_RIGHT },{0,16, DIR_DOWN },{6,13, DIR_DOWN}  },
						 {  {8,0, DIR_RIGHT },{0,0, DIR_RIGHT },{0,16, DIR_DOWN },{6,13, DIR_DOWN}  }    };


					break; 
				case 12://第三大关 
					enemyPlace = new int[][][]
							{  { {8,0, DIR_RIGHT },{3,2, DIR_LEFT},{1,11, DIR_DOWN },{0,18, DIR_LEFT},{3,18, DIR_LEFT} }  };
					break; 
				case 13: 
					enemyPlace = new int[][][]
							{  { {8,0, DIR_RIGHT },{3,2, DIR_LEFT},{0,11, DIR_DOWN },{0,16, DIR_LEFT} },
							{ {8,0, DIR_RIGHT },{3,2, DIR_LEFT},{0,11, DIR_DOWN },{0,16, DIR_LEFT},{3,18, DIR_LEFT} }  };

					break;
				case 14:
					enemyPlace = new int[][][]
							{  { {0,8, DIR_DOWN},{0,11, DIR_DOWN},{0,18, DIR_LEFT} },
   							{ {0,8, DIR_DOWN},{0,11, DIR_DOWN},{0,18, DIR_LEFT} }  };
					break;
				case 15:
					enemyPlace = new int[][][]
							{ { {3,2, DIR_LEFT},{1,9, DIR_DOWN },{0,16, DIR_DOWN},{0,4, DIR_DOWN} },
							{ {8,0, DIR_RIGHT },{3,2, DIR_LEFT},{1,9, DIR_DOWN },{0,16, DIR_DOWN},{0,4, DIR_DOWN} }  };
					break;
				case 16: 
					enemyPlace = new int[][][]
							{  { {0,8, DIR_DOWN},{0,18, DIR_DOWN} },
   							{ {0,8, DIR_DOWN},{0,18, DIR_DOWN} }  };
					break; 
				case 17: 
					isJiJia = true;
					GameEngine.JiJiaXY = new int [][]{{300,90,3000,-1}
//					,{583,19,1500,-1}
					};
					enemyPlace = new int[][][]   
							{ {{8,0, DIR_RIGHT },{3,2, DIR_LEFT},{1,9, DIR_DOWN },{0,16, DIR_DOWN},{0,4, DIR_DOWN} },
							{ {8,0, DIR_RIGHT },{0,16, DIR_DOWN },{1,9, DIR_DOWN},{0,4, DIR_DOWN} },
							{ {8,0, DIR_RIGHT },{3,2, DIR_LEFT},{1,9, DIR_DOWN },{0,16, DIR_DOWN},{0,4, DIR_DOWN} }  };

					break; 
				case 18://第四大关
					enemyPlace = new int[][][]
							{  {{7,0,DIR_RIGHT },{2,3,DIR_RIGHT},{0,9, DIR_DOWN },{2,12, DIR_RIGHT},{4,18, DIR_LEFT} }  };
					break;
		    	case 19:
		    		enemyPlace = new int[][][]
		    				{  {{2,3, DIR_RIGHT }, {7,0,DIR_RIGHT },{2,3,DIR_RIGHT},{0,9, DIR_DOWN },{2,12, DIR_RIGHT},{4,18, DIR_LEFT} },
		    				{ {7,0,DIR_RIGHT },{2,3,DIR_RIGHT},{0,9, DIR_DOWN },{2,12, DIR_RIGHT},{4,18, DIR_LEFT} }  };
 
		    		break;
		    	case 20:
					enemyPlace = new int[][][]

   							{  { {2,3, DIR_RIGHT },{0,7, DIR_DOWN },{2,13, DIR_RIGHT},{4,18, DIR_LEFT} },
   							{ {2,7, DIR_DOWN },{2,13, DIR_RIGHT},{4,18, DIR_LEFT} }  };
					break;
		    	case 21:
					enemyPlace = new int[][][]
							{  {{0,9, DIR_DOWN },{2,12, DIR_RIGHT},{4,18, DIR_LEFT} },
							{ {7,0,DIR_RIGHT },{5,3,DIR_RIGHT},{0,9, DIR_DOWN },{2,12, DIR_RIGHT},{4,18, DIR_LEFT} }  };


					break;
		    	case 22:
					enemyPlace = new int[][][]

							{  { {0,7, DIR_DOWN },{2,13, DIR_RIGHT},{4,18, DIR_LEFT} },
   							{ {0,7, DIR_DOWN },{2,13, DIR_RIGHT},{4,18, DIR_LEFT} }  };
					break;
		    	case 23:
					isJiJia = true;
					GameEngine.JiJiaXY = new int [][]{{372,180,4500,-1}
//					,{320,100,-1,-1}
					};
					enemyPlace = new int[][][]
							{ {{7,0,DIR_RIGHT },{0,9, DIR_DOWN },{2,12, DIR_RIGHT},{4,18, DIR_LEFT} },
							{ {7,0,DIR_RIGHT },{2,3,DIR_RIGHT},{0,9, DIR_DOWN },{2,12, DIR_RIGHT},{4,18, DIR_LEFT} },
							{ {7,0,DIR_RIGHT },{2,3,DIR_RIGHT},{0,9, DIR_DOWN },{2,12, DIR_RIGHT},{4,18, DIR_LEFT} }  };

					break;
				}
				if(GameEngine.JiJiaXY!=null){
					jiJiaHPMax=GameEngine.JiJiaXY[0][2];	
				}
				
				break;
			}
		}
	/**
	 * 获取敌人的数组	
	 * @param gameRank
	 * @param status
	 * @return
	 */  
	       public int[][][] getEnemyData(int status,int rank){
	    	   GameEngine.roleType=0;
	    	   GameEngine.roleNumber=0;
	    		Guancai = new int [][]{ 
   						{0,1000,600,250,TYPE_ENEMY_步兵,DIR_RIGHT},
   						{0,1000,400,250,TYPE_ENEMY_步兵,DIR_RIGHT},
   						{0,1000,500,250,TYPE_ENEMY_步兵,DIR_RIGHT},
   						{0,1000,300,250,TYPE_ENEMY_步兵,DIR_RIGHT}};
	    	   switch(status){
	    	   case MyGameCanvas.gmScripe:
	    	    switch(rank){
	    	    case 99:
	    	    	enemyTS = new int[][]{{1,2}}; //1.爆炸 2.
	    	    	enemyData  = new int[][][]
   							{{
   								{-1,-1,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
   									TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,-1,TYPE_ENEMY_步兵,
   									-1,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
   									TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,-1,
   									TYPE_ENEMY_步兵,-1,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
   									TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,-1,
   									TYPE_ENEMY_步兵,-1,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
   									TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
   									TYPE_ENEMY_步兵,-1,TYPE_ENEMY_步兵,-1},

   								{-1,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
   										TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
   										TYPE_ENEMY_步兵,TYPE_ENEMY_石盾,TYPE_ENEMY_步兵,
   										TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
   										TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
   										TYPE_ENEMY_步兵,TYPE_ENEMY_石盾,TYPE_ENEMY_步兵,
   										TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
   										TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
   										TYPE_ENEMY_步兵,TYPE_ENEMY_石盾,TYPE_ENEMY_步兵,
   										
   										TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
   										TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_石盾,TYPE_ENEMY_步兵},
   							
   								{-1,-1,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
   											TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,-1,TYPE_ENEMY_步兵,-1,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,-1,TYPE_ENEMY_步兵,-1, -1,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,-1,TYPE_ENEMY_步兵,-1,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,-1,TYPE_ENEMY_步兵,-1},}
   							};
	    	    	break;
	   			case 0:
	   				enemyTS = new int[][]{{119,3},{113,3},{114,3},{110,3},{114,3},{112,5},{110,1}//1
	   				,{119,3},{113,3},{114,3},{110,3},{114,3},{112,5},{110,1}};
//	   				1:
	   				
	   				Guancai = new int [][]    
	   						{
{0,4,199,119+50,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,4,104,152+50,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,4,164,273+50,TYPE_ENEMY_石盾,DIR_RIGHT},
//{0,4,292,253+90,TYPE_ENEMY_步兵,DIR_RIGHT},
{0,4,249,186+50,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,4,354,159+50,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,4,314,262+50,TYPE_ENEMY_狮鹫,DIR_RIGHT },
//{0,4,460,253+20,TYPE_ENEMY_牧师,DIR_RIGHT },
{0,4,460,269+50,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,4,495,155+50,TYPE_ENEMY_步兵,DIR_RIGHT },
//{0,4,542+83,175+45,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,4,636,153+50,TYPE_ENEMY_牧师,DIR_RIGHT },
{0,8,129,186+50,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,8,214,262+50,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,8,169,186-20,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,8,180,262-20,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,8,240,156+10,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,8,120,162+30,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,14,354,160,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,14,280,215,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,14,404,240,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,14,324,180,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,17,189,200,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,17,184,160,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,17,249,100+50,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,17,224,160,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,21,460+50,253-60,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,21,636,153+50,TYPE_ENEMY_步兵,DIR_RIGHT},
{0,21,314,262+50,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,24,124,273+50,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,24,314,262+50,TYPE_ENEMY_步兵,DIR_RIGHT},
{0,24,542,175+30,TYPE_ENEMY_牧师,DIR_RIGHT },
{0,28,292-70,175,TYPE_ENEMY_步兵,DIR_RIGHT},
{0,28,292-50,253+20,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,28,377+20,175-30,TYPE_ENEMY_石盾,DIR_RIGHT },
{0,28,377-30,253,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,30,460+50,253-60,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,30,199,119,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,30,114,273+50,TYPE_ENEMY_牧师,DIR_RIGHT },
{0,30,354,159+50,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,34,129,186+50,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,34,214,262+50,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,34,169,236,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,34,180,262-20,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,34,240,196+10,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,34,120,182+30,TYPE_ENEMY_牧师,DIR_RIGHT},
	   						};

Guancai = new int [][]    
	   						{
{0,4,199,119+50,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,4,104,152+50,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,4,164,273+50,TYPE_ENEMY_石盾,DIR_RIGHT},
//{0,4,292,253+90,TYPE_ENEMY_步兵,DIR_RIGHT},
{0,4,249,186+50,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,4,354,159+50,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,4,314,262+50,TYPE_ENEMY_狮鹫,DIR_RIGHT },
//{0,4,460,253+20,TYPE_ENEMY_牧师,DIR_RIGHT },
{0,4,460,269+50,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,4,495,155+50,TYPE_ENEMY_步兵,DIR_RIGHT },
//{0,4,542+83,175+45,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,4,636,153+50,TYPE_ENEMY_牧师,DIR_RIGHT },
{0,8,129,186+50,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,8,214,262+50,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,8,169,186-20,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,8,180,262-20,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,8,240,156+10,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,8,120,162+30,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,14,354,160,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,14,280,215,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,14,404,240,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,14,324,180,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,17,189,200,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,17,184,160,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,17,249,100+70,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,17,224,160,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,21,460+50,253-60,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,21,636,153+50,TYPE_ENEMY_步兵,DIR_RIGHT},
{0,21,314,262+50,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,24,124,273+50,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,24,314,262+50,TYPE_ENEMY_步兵,DIR_RIGHT},
{0,24,542,175+30,TYPE_ENEMY_牧师,DIR_RIGHT },
{0,28,292-70,235,TYPE_ENEMY_步兵,DIR_RIGHT},
{0,28,292-50,253+20,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,28,377+20,255,TYPE_ENEMY_石盾,DIR_RIGHT },
{0,28,377-30,253,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,30,460+50,253-60,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,30,199,239,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,30,114,273+50,TYPE_ENEMY_牧师,DIR_RIGHT },
{0,30,354,159+50,TYPE_ENEMY_步兵,DIR_RIGHT },
{0,34,129,186+50,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,34,214,262+50,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,34,169,236,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,34,180,262-20,TYPE_ENEMY_牧师,DIR_RIGHT},
{0,34,240,196+10,TYPE_ENEMY_石盾,DIR_RIGHT},
{0,34,120,182+30,TYPE_ENEMY_牧师,DIR_RIGHT},
	   						};
                            //1.第几波出兵2.第几个怪物3.x 4.y 5.兵种类型6.人物方向
	   			
//	   				enemyTS = new int[][]{{20,3},{21,3},{22,3},{23,3},{24,3},{25,3},{26,3},{35,3},{36,3},{37,3},{38,3},{39,3}};//1
	   				enemyData = new int[][][]    
	   						{{
	   							{TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,
	   							TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,
	   							TYPE_ENEMY_牧师,
   							TYPE_ENEMY_步兵,
	   							TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_步兵,
},
	   							
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,
	   							TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,

	   							TYPE_ENEMY_牧师,

	   							TYPE_ENEMY_步兵,TYPE_ENEMY_石盾,TYPE_ENEMY_步兵,
//	   							TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
//	   							TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
	   						TYPE_ENEMY_石盾,},
	   						},
	   						{{TYPE_ENEMY_石盾,
	   						TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
	   						TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,
	   						
   						TYPE_ENEMY_步兵,
	   						TYPE_ENEMY_石盾,TYPE_ENEMY_步兵,},
	   						{TYPE_ENEMY_步兵,
		   					TYPE_ENEMY_步兵,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,
		   					TYPE_ENEMY_石盾,},
		   					{
		   						TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,
		   				TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
			   				TYPE_ENEMY_步兵,
			   				TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,
			   				TYPE_ENEMY_牧师,}},
	   						};
	   				break;
	   			case 1: 
	   				Guancai = new int [][]  
	   						{{0,18,56,180,TYPE_ENEMY_牧师,DIR_RIGHT},
	   						{0,18,130,230,TYPE_ENEMY_石盾,DIR_DOWN },
	   						{0,18,330,140,TYPE_ENEMY_石盾,DIR_DOWN },
	   						{0,18,150,300,TYPE_ENEMY_步兵,DIR_RIGHT },
	   						{0,18,580,176,TYPE_ENEMY_步兵,DIR_DOWN },
	   						{0,18,465,150,TYPE_ENEMY_牧师,DIR_DOWN },

                            {0,63,56,180,TYPE_ENEMY_牧师,DIR_RIGHT},
	   						{0,63,130,230,TYPE_ENEMY_石盾,DIR_DOWN },
	   						{0,63,330,140,TYPE_ENEMY_石盾,DIR_DOWN },
	   						{0,63,150,300,TYPE_ENEMY_步兵,DIR_RIGHT },
	   						{0,63,580,176,TYPE_ENEMY_步兵,DIR_DOWN },
	   						{0,63,465,150,TYPE_ENEMY_牧师,DIR_DOWN },

                            {0,121,56,180,TYPE_ENEMY_牧师,DIR_RIGHT},
	   						{0,111,130,230,TYPE_ENEMY_石盾,DIR_DOWN },
	   						{0,111,330,140,TYPE_ENEMY_石盾,DIR_DOWN },
	   						{0,111,150,300,TYPE_ENEMY_步兵,DIR_RIGHT },
	   						{0,111,580,176,TYPE_ENEMY_步兵,DIR_DOWN },
	   						{0,111,465,150,TYPE_ENEMY_牧师,DIR_DOWN }

};
	   				enemyTS = new int[][]{{85,3},{86,1},{87,4},{88,5},{118,3},{119,1},{120,4},{121,5},{48,3},{49,1},{50,4},{51,5},{131,3},{132,1},{133,4},{134,5}};//2
	   				enemyData  = new int[][][]
	   						{{{TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,
	   							TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,
	   							TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,
	   							TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
	   							TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,
		   						TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
	   							TYPE_ENEMY_牧师,},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,
	   								TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_石盾,TYPE_ENEMY_步兵,
		   							TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,			TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
   		   							TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,
   		   							TYPE_ENEMY_牧师,},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_步兵,
	   		   							TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
	   		   							TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,			TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
	   		   							TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
	   		   							TYPE_ENEMY_牧师,},
	   							{			TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
	   			   							TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
	   			   							TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
	   			   							TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
	   		   							TYPE_ENEMY_步兵,}  },

	   							{  {TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_石盾,TYPE_ENEMY_步兵,
	   								TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_石盾,TYPE_ENEMY_步兵,
//	   								TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
//	   								TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
	   								TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
	   								TYPE_ENEMY_牧师,},
	   				 			{TYPE_ENEMY_石盾,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,
	   									TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,
	   						    TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
	   									TYPE_ENEMY_步兵,},
	   							{TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,
	   							TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
	   										TYPE_ENEMY_石盾,TYPE_ENEMY_步兵,},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,
	   											TYPE_ENEMY_石盾,TYPE_ENEMY_步兵,
//	   								TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
	   								TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,
//	   								TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,
	   								}  } };
	   				break;
	   			case 2:

	   				Guancai2 = new int [][] //1.minX 2.maxX 3.minY 4.maxY 5.朝向
	   						{
	   						{340,341,140,260,DIR_DOWN},
	   						{580,581,100,260,DIR_DOWN},
	   						{800,460,350,351,DIR_LEFT},
	   						};

	   				enemyTS = new int[][]{{93,2},{7,3}};//3
	   				enemyData  = new int[][][]
	   						{{{TYPE_ENEMY_紫盾,-1,-1,-1,-1,-1,-1,-1,-1,-1,TYPE_ENEMY_紫盾},
	   							{TYPE_ENEMY_狮鹫,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
//	   							TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
//	   							TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,
//	   							TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_狮鹫,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,
//	   							TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
	   							TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	   						{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
//	   						TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,
//	   						TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
//	   						TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
//	   						TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
	   						TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	   						{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,
//	   						TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
//	   						TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
//	   						TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
//	   						TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,
	   						TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师}  },

	   						 { {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}}};


	   				break;
	   			case 3:

	   				Guancai2 = new int [][] //1.minX 2.maxX 3.minY 4.maxY 5.朝向
	   						{
	   						{180,705,260,261,DIR_RIGHT},
	   						{270,705,300,301,DIR_RIGHT},
	   						{550,705,220,221,DIR_RIGHT},
	   						};

	 	    	   GameEngine.roleType=TYPE_ENEMY_牧师;
		    	   GameEngine.roleNumber=20;
	   				enemyTS = new int[][]{{45,1},{100,5},{170,4}};//4
	   		    	enemyData  = new int[][][]
	   		    			{    {  {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师},
	   		    				{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	   		    				{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师}  },
	   		    				{  {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	   		    				{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	   		    				{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}  }    };


	   				break;
	   			case 4:
	   				Guancai2 = new int [][] //1.minX 2.maxX 3.minY 4.maxY 5.朝向
	   						{
	   						{180,705,260,261,DIR_RIGHT},
	   						{270,705,300,301,DIR_RIGHT},
	   						{550,705,220,221,DIR_RIGHT},
	   						};

//		 	    	   GameEngine.roleType=TYPE_ENEMY_牧师;
			    	   GameEngine.roleNumber=50;
	   				enemyTS = new int[][]{{75,2},{100,3}};//5
	   				enemyData = new int[][][]  
	   						{{{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵},
	   							{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}  },
	   							{  {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵},
	   							{TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}  }    };



	   				break;
	   			case 5:
	   				Guancai2 = new int [][] //1.minX 2.maxX 3.minY 4.maxY 5.朝向
	   						{
	   						{310,311,0,220,DIR_DOWN},
	   						{0,340,260,261,DIR_RIGHT},
	   						{590,591,0,340,DIR_DOWN},
	   						};
	   				enemyTS = new int[][]{{40,1},{95,5},{145,4}};//6
	   				enemyData = new int[][][]
	   						{    { 
	   							{TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	   							{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_刺客,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师}  },

	   							{  {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}  },

	   							{{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}  }    };


	   				break;
	   			case 6://第二大关
	   				Guancai2 = new int [][] //1.minX 2.maxX 3.minY 4.maxY 5.朝向
	   						{
	   						{0,660,20,21,DIR_RIGHT},
	   						{0,380,350,351,DIR_RIGHT},
	   						{620,621,150,380,DIR_DOWN},
	   						};

	   				enemyTS = new int[][]{{35,5},{70,3}};//7
	   				enemyData = new int[][][]
	   						{    {{TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_光头,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师}  }    };

	   				break;
	   			case 7:
	   				Guancai2 = new int [][] //1.minX 2.maxX 3.minY 4.maxY 5.朝向
	   						{
	   						{0,380,350,351,DIR_RIGHT},
	   						{340,800,230,231,DIR_LEFT},
	   						};

	   				enemyTS = new int[][]{{40,2},{96,4}};//8
	   				enemyData = new int[][][]
	   						{    { {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	   							{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵}  },

	   							{  {TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客}  }    };

	   				break;
	   			case 8:
	   				Guancai2 = new int [][] //1.minX 2.maxX 3.minY 4.maxY 5.朝向
	   						{
	   						{400,480,0,350,DIR_DOWN},
//	   						{340,800,230,231,DIR_LEFT},
	   						};

		 	    	   GameEngine.roleType=TYPE_ENEMY_刺客;
			    	   GameEngine.roleNumber=80;
	   				enemyTS = new int[][]{{5,2},{50,3},{170,4}};//9
	   				enemyData = new int[][][]
	   						{  	{ {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_光头 ,TYPE_ENEMY_步兵,TYPE_ENEMY_光头 , TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_光头, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_步兵, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_牧师,TYPE_ENEMY_鹰, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_步兵 },
	   							{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_步兵, TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_光头, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 , TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_步兵, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_牧师,TYPE_ENEMY_鹰, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	   							{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_步兵, TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_光头, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_牧师,TYPE_ENEMY_鹰, TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_步兵, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_牧师,TYPE_ENEMY_鹰, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}},
	   								{ {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_步兵, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_光头, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_牧师,TYPE_ENEMY_鹰, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_步兵, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_牧师,TYPE_ENEMY_鹰, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_步兵 },
	   							{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_步兵, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_光头, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_牧师,TYPE_ENEMY_鹰, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_步兵, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_牧师,TYPE_ENEMY_鹰, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	   							{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_步兵, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_光头, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_牧师,TYPE_ENEMY_鹰, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_步兵, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_牧师,TYPE_ENEMY_鹰, TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客, TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}} };

	   							

	   				break;
	   			case 9:
	   				Guancai2 = new int [][]
	   						{{0,660,20,21,DIR_RIGHT},
	   						{220,750,380,381,DIR_RIGHT},
	   						};

	   				enemyTS = new int[][]{{40,5},{140,1}};//10
	   				enemyData = new int[][][]
	   						{  { {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_光头 ,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_刺客,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_刺客,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师} },

	   							{ {TYPE_ENEMY_刺客,TYPE_ENEMY_狮鹫,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	   							{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	   							{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	   							{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头} }  };

	   				break;
	   			case 10:
	   				Guancai2 = new int [][]
	   						{{110,420,60,61,DIR_RIGHT},
	   						{0,750,350,351,DIR_RIGHT},
	   						};

//		 	    	   GameEngine.roleType=TYPE_ENEMY_刺客;
				    	   GameEngine.roleNumber=90;
	   				enemyTS = new int[][]{{40,2}};//11
	   				enemyData = new int[][][]
	   						{  { {TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_刺客,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_光头,TYPE_ENEMY_牧师,TYPE_ENEMY_刺客,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_刺客,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_刺客,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_刺客,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_牧师,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_光头,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}},

	   							{ {TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}}  };


	   							

	   				break;
	   			case 11:
	   				Guancai2 = new int [][]
	   						{{620,620,140,260,DIR_DOWN},
	   						{220,705,380,381,DIR_RIGHT},
	   						};

	   				enemyTS = new int[][]{{40,2}};//12
	   				enemyData = new int[][][]
	   						{  {{TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵} },

	   							{ {TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	   							{TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	   							{TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	   							{TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头} },

	   							{ {TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_光头,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师} }  };

	   				break;
	   			case 12://第三大关
	   				Guancai2 = new int [][]
	   						{{0,420,350,351,DIR_RIGHT},
	   						{470,470,0,300,DIR_DOWN},
	   						{660,340,220,221,DIR_LEFT},
	   						};

	   				enemyTS = new int[][]{{35,5}};//13
	   				enemyData = new int[][][]
	   						{  { {TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_狮鹫,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_光头,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师} }  };

	   				break;
	   			case 13:
	   				Guancai2 = new int [][]
	   						{{0,500,350,351,DIR_RIGHT},
	   						{470,470,0,400,DIR_DOWN},
	   						{380,381,20,221,DIR_DOWN},
	   						};

	   				enemyTS = new int[][]{{70,1},{225,3}};//14
	   				enemyData = new int[][][]
	   						{  { {TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_金盾,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_光头,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_金盾,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_金盾,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_步兵,TYPE_ENEMY_光头 ,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_金盾,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_金盾,TYPE_ENEMY_牧师} },

	   							{ {TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,} }  };

	   				break;
	   			case 14:
	   				Guancai2 = new int [][]
	   						{{350,351,0,340,DIR_DOWN},
	   						{420,750,260,261,DIR_LEFT},
	   						{460,800,310,311,DIR_LEFT},
	   						};

                   GameEngine.roleType=TYPE_ENEMY_石盾;
		    	   GameEngine.roleNumber=99;
	   				enemyTS = new int[][]{{80,5}};//15
	   				enemyData = new int[][][]
	   						{ 

   							{ {TYPE_ENEMY_石盾,TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_光头,TYPE_ENEMY_石盾, TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_金盾,TYPE_ENEMY_石盾},
   							{TYPE_ENEMY_石盾,TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_光头,TYPE_ENEMY_石盾, TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_金盾,TYPE_ENEMY_石盾},
   							{TYPE_ENEMY_石盾,TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_光头,TYPE_ENEMY_石盾, TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_金盾,TYPE_ENEMY_石盾}} , 

                            { {TYPE_ENEMY_石盾,TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_光头,TYPE_ENEMY_石盾, TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_金盾,TYPE_ENEMY_石盾},
   							{TYPE_ENEMY_石盾,TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_光头,TYPE_ENEMY_石盾, TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_金盾,TYPE_ENEMY_石盾},
   							{TYPE_ENEMY_石盾,TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_光头,TYPE_ENEMY_石盾, TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾, TYPE_ENEMY_金盾,TYPE_ENEMY_石盾}} ,
                              };

	   							

	   				break;
	   			case 15:
	   				Guancai2 = new int [][]
	   						{{60,580,340,341,DIR_RIGHT},
	   						{340,341,0,300,DIR_DOWN},
	   						{390,391,0,340,DIR_DOWN},
	   						};

	   				enemyTS = new int[][]{{10,2}};//16
	   				enemyData = new int[][][]
	   						{  { {TYPE_ENEMY_光头,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_光头,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_光头,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_光头,TYPE_ENEMY_步兵,TYPE_ENEMY_光头,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_光头,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_光头,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_光头,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_狮鹫,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,} },

	   							{ {TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_金盾,},
	   							{TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,},
	   							{TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,},
	   							{TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,},
	   							{TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,} }  };

	   				break;
	   			case 16:
	   				Guancai2 = new int [][]
	   						{{350,351,0,340,DIR_DOWN},
	   						};

//		 	    	GameEngine.roleType=TYPE_ENEMY_刺客;
				    GameEngine.roleNumber=80;
	   				enemyTS = new int[][]{{140,4},{157,3}};//17
	   				enemyTS = new int[][]{{140,4},{157,3}};//17
		   				enemyData = new int[][][]
		   						{  { {TYPE_ENEMY_牧师,TYPE_ENEMY_光头,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_光头,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_光头,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头,TYPE_ENEMY_鹰,TYPE_ENEMY_光头,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_光头,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头,TYPE_ENEMY_牧师},
		   							{TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_光头,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_光头,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_光头,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰},
		   							 },

		   							{ {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾},
		   							{TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
		   							 }  };
		   				break;
	   			case 17:
	   				Guancai2 = new int [][]
	   						{{0,580,350,351,DIR_RIGHT},
	   						{340,341,0,300,DIR_DOWN},
	   						{390,391,0,340,DIR_DOWN},
	   						{460,461,0,300,DIR_DOWN},
	   						};


	   				enemyTS = new int[][]{{40,5},{280,4}};//18
	   				enemyData = new int[][][]
	   						{  { {TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_石盾,TYPE_ENEMY_金盾,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,} },

	   							{ {TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师} },

	   							{ {TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,},
	   							{TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,},
	   							{TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,},
	   							{TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,},
	   							{TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,} }  };

	   				break;
	   			case 18://第四大关
	   				Guancai2 = new int [][]
	   						{{0,380,310,311,DIR_RIGHT},
	   						{220,221,140,300,DIR_DOWN},
	   						};

	   				enemyTS = new int[][]{{80,3}};//19
	   				enemyData = new int[][][]
	   						{  {{TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_光头,TYPE_ENEMY_牧师,TYPE_ENEMY_步兵,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_狮鹫,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_光头,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰} }  };

	   				break;
	   			case 19:
	   				Guancai2 = new int [][]
	   						{{0,380,310,311,DIR_RIGHT},
	   						{220,221,140,300,DIR_DOWN},
	   						};

	   				enemyTS = new int[][]{{151,5},{280,4}};//20
	   				enemyData  = new int[][][]
	   						{  {{-1,-1,-1,-1,TYPE_ENEMY_紫盾}, {TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_刺客,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_刺客},
	   							{TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_光头,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师},
	   							{TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_光头 ,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰} },

	   							{ {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾} }  };

	   				break;
	   			case 20:
	   				Guancai2 = new int [][]
	   						{{-1,100,0,0,DIR_RIGHT},
	   						{-1,100,0,0,DIR_DOWN},
	   						};

		 	    	GameEngine.roleType=TYPE_ENEMY_金盾;
				    GameEngine.roleNumber=120;
	   				enemyTS = new int[][]{{150,3}};//21
	   				enemyData = new int[][][]
	   						{  { 
	   							{ TYPE_ENEMY_金盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_光头 ,TYPE_ENEMY_光头,TYPE_ENEMY_金盾, TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_光头,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_光头 ,TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾},
	   							{ TYPE_ENEMY_金盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_光头 , TYPE_ENEMY_光头,TYPE_ENEMY_金盾, TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_光头,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_光头 , TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾},
	   							{ TYPE_ENEMY_金盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_光头,TYPE_ENEMY_金盾, TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_光头,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_绿盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾},
	   							{ TYPE_ENEMY_金盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_光头,TYPE_ENEMY_金盾, TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_光头,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_绿盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_金盾, TYPE_ENEMY_金盾}},
	   							{ {TYPE_ENEMY_金盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾, TYPE_ENEMY_鹰,TYPE_ENEMY_金盾, TYPE_ENEMY_光头,TYPE_ENEMY_金盾, TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_牧师,TYPE_ENEMY_金盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾, TYPE_ENEMY_步兵,TYPE_ENEMY_金盾, TYPE_ENEMY_光头,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_刺客,TYPE_ENEMY_金盾, TYPE_ENEMY_绿盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客},
	   							{TYPE_ENEMY_金盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾, TYPE_ENEMY_鹰,TYPE_ENEMY_金盾, TYPE_ENEMY_光头,TYPE_ENEMY_金盾, TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_牧师,TYPE_ENEMY_金盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾, TYPE_ENEMY_步兵,TYPE_ENEMY_金盾, TYPE_ENEMY_光头,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_刺客,TYPE_ENEMY_金盾, TYPE_ENEMY_绿盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客},	 
	   							{TYPE_ENEMY_金盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾, TYPE_ENEMY_鹰,TYPE_ENEMY_金盾, TYPE_ENEMY_光头,TYPE_ENEMY_金盾, TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_牧师,TYPE_ENEMY_金盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_金盾, TYPE_ENEMY_步兵,TYPE_ENEMY_金盾, TYPE_ENEMY_光头,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾, TYPE_ENEMY_刺客,TYPE_ENEMY_金盾, TYPE_ENEMY_绿盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客}}  };



	   				break;
	   			case 21:
	   				Guancai2 = new int [][]
	   						{
//	   						{0,380,310,311,DIR_RIGHT},
	   						{220,221,140,300,DIR_DOWN},
	   						};

	   				enemyTS = new int[][]{{30,3}};//22
	   				enemyData = new int[][][]
	   						{  {{TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_刺客},
	   							{TYPE_ENEMY_绿盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	   							{TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_绿盾,TYPE_ENEMY_狮鹫,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客}},
	   							
	   							{ {TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客},
	   							{TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	   							{TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_狮鹫,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客},
	   							{TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	   							{TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_刺客,TYPE_ENEMY_绿盾} }  };

	   				break;
	   			case 22:
	   				Guancai2 = new int [][]
	   						{{-1,0,0,0,DIR_RIGHT},
	   						{-1,0,0,0,DIR_DOWN},
	   						};

//		 	    	GameEngine.roleType=TYPE_ENEMY_石盾;
				    GameEngine.roleNumber=99;
	   				enemyTS = new int[][]{{40,1},{100,2}};//23
	   				enemyData = new int[][][]
	   						{  {{TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_金盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	   							{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客},
	   							{TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_光头,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客}},
	   							{ {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_金盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	   								
	   							{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_金盾,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客},
	   							{TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客},
	   							{TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	   							{TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_金盾,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_光头,TYPE_ENEMY_刺客,TYPE_ENEMY_金盾,TYPE_ENEMY_刺客} }  };


	   				break;
	   			case 23:
	   				Guancai2 = new int [][]
	   						{
	   						{0,380,310,400,DIR_RIGHT},
//	   						{220,221,140,300,DIR_DOWN},
	   						};

	   				enemyTS = new int[][]{{170,3},{270,4}};//24
	   				enemyData = new int[][][]
	   						{{{TYPE_ENEMY_光头 ,TYPE_ENEMY_光头 ,TYPE_ENEMY_光头 ,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_步兵,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_光头 ,TYPE_ENEMY_刺客,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_光头 ,TYPE_ENEMY_光头 ,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_刺客,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_步兵,},
	   							{TYPE_ENEMY_石盾,TYPE_ENEMY_金盾,TYPE_ENEMY_鹰,TYPE_ENEMY_狮鹫,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_牧师,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_金盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_鹰,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾} },

	   							{ {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_光头 ,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_光头 ,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_光头 ,TYPE_ENEMY_光头 ,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾} },

	   							{ {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头 ,TYPE_ENEMY_光头 ,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_石盾},
	   							{TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_光头} }  };


	   				break;
	   			}
	    		   break;
	    	   case MyGameCanvas.gmFight:
	    		   switch(rank){
	    		   case 0:
	    			   enemyData = new int[][][]
	    					   {{{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}},

	    						   {{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}},

	    						   {{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}},

	    						   {{TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}},

	    						   {{TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰},
	    						   { TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰}},

	    						   {{TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}},

	    						   {{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}},

	    						   {{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}},

	    						   {{TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}},

	    						   {{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵BOSS}  } ,

	    						   {{TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}  },

	    						   {{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}  },

	    						   {{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}  },

	    						   {{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}  },

	    						   {{TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   {TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫}},


	    						   {{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}},

	    						   {{TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   {TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}},

	    						   {{TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}},

	    						   {{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}},

	    						   {{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_步兵BOSS},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵BOSS}},

	    						   {{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}},

	    						   {  {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   {TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}},

	    						   {  { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}},

	    						   {{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}},

	    						   {{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}},

	    						   {  {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}  },

	    						   {{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   { TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫}  },

	    						   {  { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}  },

	    						   {  { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}  },

	    						   {  {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵BOSS},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_骑兵BOSS}  },

	    						   {  { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾}  },

	    						   {  { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾}  },

	    						   {  { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}  },

	    						   {{TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}},

	    						   {{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   {TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫}  },

	    						   {  { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾}},

	    						   {  { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}},

	    						   {{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}},

	    						   {{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   {TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾}},

	    						   {{ TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_骑兵BOSS},
	    						   {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_步兵BOSS}},

	    						   {  { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}  },

	    						   {    { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}},

	    						   {    {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾}  },

	    						   {  { TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   { TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}},

	    						   {  { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}},

	    						   {  { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   { TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰},
	    						   { TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰}},

	    						   {  { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}},

	    						   {{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}},

	    						   {{ TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}}};
	    			   break;
	    		   case 1:
	    			   enemyData = new int[][][]
	    					   {{{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}},

	    						   {  {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}  },

	    						   {  {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}  },

	    						   {  {TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}  },

	    						   {  {TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰},
	    						   { TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰}  },

	    						   {  {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}  },

	    						   {  {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}  },

	    						   {  {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}  },

	    						   {  { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾}  },

	    						   {  {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵BOSS}  } ,

	    						   {  { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}  },

	    						   {  {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}  },

	    						   {  { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}  },

	    						   {  { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}  },

	    						   {  { TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   { TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫}  },


	    						   {{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}},

	    						   {  { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}},

	    						   {{TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}  },

	    						   {  {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}  },

	    						   {  { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_步兵BOSS},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵BOSS}},

	    						   {  { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}  },

	    						   {  {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾}  },

	    						   {  { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}  },

	    						   {  {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}  },

	    						   {  {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾}  },

	    						   {  { TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   { TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}  },

	    						   {  {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   { TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫}  },

	    						   {  { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}  },

	    						   {  { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}  },

	    						   {  {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵BOSS},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_大剑BOSS}  },

	    						   {  { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾}  },

	    						   {  { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   { TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   { TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾}  },

	    						   {  { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}  },

	    						   {{TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}},

	    						   {{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   {TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫}},

	    						   {{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   {TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾}},

	    						   {{TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}},

	    						   {{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}},

	    						   {{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   {TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾}},

	    						   {{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_大剑BOSS},
	    						   {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS }  },

	    						   {  { TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   { TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}  },

	    						   {{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}},

	    						   {{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   {TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾}},

	    						   {{TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   { TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}},

	    						   {  { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}  },

	    						   {  { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   { TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   { TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   { TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾}  },

	    						   {  { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}  },

	    						   {{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}},

	    						   {{TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   {TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾}}};
	    			   break;
	    		   case 2:
	    			   enemyData = new int[][][]
	    					   {{{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}},

	    						   {{TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}},

	    						   {{ TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客}},

	    						   {{ TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}},

	    						   {{TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰},
	    						   { TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰,TYPE_ENEMY_鹰},
	    						   { TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   { TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫}},

	    						   {{TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧}},

	    						   {{TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客}  },

	    						   {{TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵}},

	    						   {{TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   { TYPE_ENEMY_步兵BOSS, TYPE_ENEMY_步兵BOSS},
	    						   { TYPE_ENEMY_步兵BOSS, TYPE_ENEMY_步兵BOSS}},

	    						   {{TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   {TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}  },

	    						   {{TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   {TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   {TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   {TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾} ,
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}},

	    						   {{TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客}},

	    						   {{TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS},{TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS}},

	    						   {{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS},
	    						   {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧}},

	    						   {{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   {TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   {TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫}},

	    						   {{TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   {TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   {TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}},

	    						   {{TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   {TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}},

	    						   {{TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   {TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   {TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}},

	    						   {{TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   {TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   {TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   {TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾} ,
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}},

	    						   {{TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS },
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   {TYPE_ENEMY_骑兵BOSS},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_大剑BOSS},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   { TYPE_ENEMY_重甲BOSS}},

	    						   {{TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   {TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾} ,
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}},

	    						   {{TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客}},

	    						   {{TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS}},

	    						   {{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧}},

	    						   {{ TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   {TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   { TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫}},

	    						   {{ TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}},

	    						   {  {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}  },

	    						   {  {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}},

	    						   {  { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾} ,
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}},

	    						   {  {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS },
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   {TYPE_ENEMY_骑兵BOSS},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_大剑BOSS},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   { TYPE_ENEMY_重甲BOSS}},

	    						   {  { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾} ,
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}},

	    						   {{TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客}},

	    						   {{TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS}},

	    						   {{TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧}},

	    						   {{ TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   {TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   { TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫}},

	    						   {{ TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}},

	    						   {{TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}},

	    						   {  {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}},

	    						   {{TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾} ,
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}},

	    						   {{TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS },
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   {TYPE_ENEMY_骑兵BOSS},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_大剑BOSS},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   { TYPE_ENEMY_重甲BOSS}},

	    						   {  { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾} ,
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}},

	    						   {  {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {  TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客}},

	    						   {  {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS}},

	    						   {  {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧}  },

	    						   {{TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   {TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   { TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫,TYPE_ENEMY_狮鹫},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫}  },

	    						   {  { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   {TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师},
	    						   { TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师,TYPE_ENEMY_牧师}},


	    						   {  {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   { TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客,TYPE_ENEMY_刺客},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}  },

	    						   {  {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   {TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵,TYPE_ENEMY_步兵},
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头},
	    						   { TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头,TYPE_ENEMY_光头}},

	    						   {  { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾,TYPE_ENEMY_绿盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾},
	    						   { TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾,TYPE_ENEMY_金盾} ,
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾},
	    						   {TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾,TYPE_ENEMY_石盾}  },

	    						   {{TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧,TYPE_ENEMY_暗牧},
	    						   {TYPE_ENEMY_步兵BOSS,TYPE_ENEMY_步兵BOSS },
	    						   { TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客,TYPE_ENEMY_黑刺客},
	    						   {TYPE_ENEMY_骑兵BOSS},
	    						   {TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫,TYPE_ENEMY_黑狮鹫},
	    						   {TYPE_ENEMY_大剑BOSS},
	    						   {TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾,TYPE_ENEMY_紫盾},
	    						   { TYPE_ENEMY_重甲BOSS}}};
	    			   break;
	    		   }
	    		   break;

	    	   }

			return enemyData;
		}
}
