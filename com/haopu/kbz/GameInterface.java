//package com.haopu;
package com.haopu.kbz;
public abstract class GameInterface {
//	public final static int level_1 = 1;
//	public final static int level_2 = 2;
//	public final static int level_3 = 3;
//	public final static int level_4 = 4;
//	public final static int level_5 = 5;
	  public final static String[] faceDirData={
		  " FACE_DIR_0_0",
		  " FACE_DIR_1_22",
		  "FACE_DIR_2_45 = 2",
		  " FACE_DIR_3_67",
		  " FACE_DIR_4_90= 4",
		  " FACE_DIR_5_112= 5",
		  " FACE_DIR_6_135= 6",
		  "FACE_DIR_7_157= 7",
		  " FACE_DIR_8_180= 8","FACE_DIR_9_202= 9"
		  ,"FACE_DIR_10_225= 10",
		  "FACE_DIR_11_247= 11",
		  " FACE_DIR_12_270= 12",
		  "FACE_DIR_13_292= 13",
		  " FACE_DIR_14_315= 14",
		  " FACE_DIR_15_337= 15"
		 };
	  
	
//  public final static byte DIR_LEFT = 0;
//  public final static byte DIR_RIGHT = 1;
//  public final static byte DIR_UP = 2;
//  public final static byte DIR_DOWN = 3;
//  public final static byte DIR_STOP = 4;
  
  //public static final byte STATUS_PAO = 100;
//  public final static byte STATUS_SQUAT = 4; //閿熸枻鎷?
//  public final static byte STATUS_SQUAT_ATT = 5; //閿熸枻鎷?
  public final static byte STATUS_JUMP_UP = 6;
  public final static byte STATUS_JUMP_DOWN = 7;
  public final static byte STATUS_DEAD = 8;
  public final static byte STATUS_STOP = 9;
  public final static byte STATUS_ATTACK = 10;
  public final static byte STATUS_ATTACK_END = 11; //閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓杈冣懝鎲嬫嫹閿?
  public final static byte STATUS_SKILL1 = 12;
//  public final static byte STATUS_SKILL2 = 13;
//  public final static byte STATUS_SKILL5 = 14; //閿熸枻鎷烽敓鏂ゆ嫹
//  public final static byte STATUS_DECLINE = 15;
//  public final static byte STATUS_INJURE = 17;
  public final static byte STATUS_LEVEL_UP = 18;
  public final static byte STATUS_NULL = 19;
//  public final static byte STATUS_SKILL4 = 20; //l閿熸枻鎷?
  public final static byte STATUS_MOVE = 21;
//  public final static byte STATUS_UP = 22;
//  public final static byte STATUS_MOVESCREEN = 23; //閿熸枻鎷烽敓鏂ゆ嫹
//  public final static byte STATUS_READY_ATTACK = 24; //鍑嗛敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹
////  public final static byte STATUS_NEXT = 24; //閿熸枻鎷烽敓鏂ゆ嫹
//  public final static byte STATUS_JUMP_ATTACK = 25;

  public final static byte STATUS_JUMP_UP2 = 28; //閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷?
//  public final static byte STATUS_JUMP_DOWN2 = 29;
//  public final static byte STATUS_RUNTOPOINT = 30; //閿熸枻鎷烽敓鏂ゆ嫹鎾為敓鏂ゆ嫹鍓嶉敓鏂ゆ嫹閿熸澃纰夋嫹
//  public final static byte STATUS_RUN = 31;
//  public final static byte STATUS_RUN_ATTACK = 32;
//
//  public final static byte STATUS_DISAPPEAR = 35; //閿熸枻鎷峰け
//  public final static byte STATUS_APPEAR = 36; //閿熸枻鎷烽敓鏂ゆ嫹
//  public final static byte STATUS_DEFEND_END = 37;
//  public final static byte STATUS_DEFEND = 38; //閿熸枻鎷烽泙閿熸枻鎷烽敓鏂ゆ嫹

//  public final static byte STATUS_CUSHION = 39; //閿熸枻鎷烽紬閿熸枻鎷烽敓?
//  public final static byte STATUS_BACK = 40; //閿熸枻鎷烽敓鏂ゆ嫹
//  public final static byte STATUS_WUDI = 45; //閿熺潾纰夋嫹
//  public final static byte STATUS_WAIT = 42; //閿熸枻鎷?
//  public final static byte STATUS_FUKONG = 43;
//  public final static byte STATUS_FUKONG_END = 44;
//  public final static byte STATUS_DAODI = 45;
//  public final static byte STATUS_DAODI_END = 46;

//  public final static byte STATUS_WIN = 51;
//  public final static byte STATUS_FAIL = 52;
//  public final static byte STATUS_FLY = 53;
//  public final static byte STATUS_INJURE_FLY = 70;
//  public final static byte STATUS_TUI = 54;
//  public final static byte STATUS_COOK = 55;
//  public final static byte STATUS_FREEZE = 56;
//  public final static byte STATUS_YUN = 57;

//  public final static byte STATUS_ICE = 58; //閿熸枻鎷?
//  public final static byte STATUS_ICE_END = 59; //閿熻В鍐?
//  public final static byte STATUS_MENU_SHOW = 59; //閿熻В鍐?
  
  public final static byte STATUS_INJURE_MOVE = 60;
  public final static byte STATUS_INJURE_JUMP_UP = 61;
  public final static byte STATUS_INJURE_JUMP_DOWN = 62;
  public final static byte STATUS_INJURE_JUMP_UP2 = 63;
//  public final static byte STATUS_INJURE_JUMP_DOWN2 = 64;
//  public final static byte STATUS_INJURE_DECLINE = 65;
//  public final static byte STATUS_INJURE_DEAD = 93;
//  public final static byte STATUS_POSE = 94;
//  public final static byte STATUS_JUMP_UP4 = 75;
//  public final static byte STATUS_INJURE_JUMP_UP4 = 76;
  public final static byte STATUS_JUMP_DOWN4 = 77;
  public final static byte STATUS_INJURE_JUMP_DOWN4 = 78;

  
  
//  public static final byte XIANJIN_FEIBIAO = 67;
//  public static final byte XIANJIN_SUISHIKUAI = 68;
//  public static final byte XIANJIN_JIASU = 69;
//敌人行走方向  
    public final static int DIR_STOP = 80;//上下左右
	public final static int DIR_UP = 81;
	public final static int DIR_DOWN = 82;
	public final static int DIR_LEFT = 83;
	public final static int DIR_RIGHT = 93;
	public final static int DIR_LEFT_DOWN = 84;//从左向下
	public final static int DIR_LEFT_UP = 85;
	public final static int DIR_RIGHT_DOWN = 86;
	public final static int DIR_RIGHT_UP = 87;
	public final static int DIR_UP_LEFT = 88;//从上往左
	public final static int DIR_UP_RIGHT = 89;
	public final static int DIR_DOWN_LEFT = 90;//
	public final static int DIR_DOWN_RIGHT = 91;
	public final static int DIR_LEFT_RIGHT_UP_DOWN = 92;//从左直往右走，从直往下走
	public final static byte STATUS_GONGJI = 93;
	public final static byte STATUS_SUIJI = 94;
	public boolean BINGDONG=false;
	   public int  shan=0;
	   public int  ting=0;
	   public boolean jia=true;
//	public boolean BAOZHA=false;
 
  
  

  public int roleStatus;
  public int x;
  public int y;
  public int x2;
  public int y2;
  public int w;
  public int h;
  public int dir;
  public int dir_from;//判断怪物是从哪个方向来的
  public int faceDir;
  public int sx;
  public int sy;
  public int lx; 
  public int rx; 
  public int ty; 
  public int by;
  public int bh;
  public int  CGuanCai;
  public int[] attackBox = null; 
  public int[] coxBox = null;
  public short[][] motion;
  public short[][] data;
  public int imgIndex = 0;

  public int roleAttackArea = 40;

  public int level ;//塔的等级
  public int attackLevel=1;//塔的攻击力等级
//  public int hp; 
//  public int hp_max;
//
//  public int mp; 
//  public int mp_max; 

  public int attack;
//  public int defend; 
//  public int lucky; 
  public int double_attack; //
//  public int destroy_attack; //
//  public int food;
  public int food_max;

  public int speedX;
  public int speedY;
  public int isJianSu;
  public int lastStatus;
 // public int nextStatus;
  public int injureTime; 
  public int curIndex; 
  public boolean isAuto = false;
//  public boolean isJump = true;

//  public int magic_attack;
//  public int magic_defend;
//
//  public int exp;
//  public int exp_up; //閿熸枻鎷烽敓鏂ゆ嫹
//
//  public int foodhuifu_time;
//  public int mphuifu_time;
//  public int hphuifu_time;

  public boolean isLeft;

  public int index;
//  public int startPosX;
//  public int startPosY;
//  public short bound;
  public int type;//塔的类型
//  public int moneyNeed;//每种塔升级所需金币
//  public int money;//每种塔初始造价
  public int rank;//塔的的等级
  public float rota=0;//怪物的旋转角度
  public byte trans = Tools.TRANS_H;//怪物的水平镜像
  public boolean bHuopao;//判断怪物是否被火炮集中
  public boolean bZhuanwan;//判断怪物是否转弯
  public int maxRota;//怪物最大转弯角度
  public int maxRotaNum;
  public int dropGold;//怪物掉落的金币
  public int dropJewel;//怪物几率掉落的宝石
  public int driftX,driftY;//怪物起始出兵点偏移量
  public int enemyDelay;//怪物动画帧数
  public int iWaveHp;//用于计算怪物血量的
  public int Stop_time=0;//僵尸停顿计时
  public int go=10;//僵尸停顿时长，过多长时间可以行走（10为停顿，100为眩晕）
  public int gongJi_Time=0;
  public int gongJi_count=0; 
  public int gongJi_count2=0;
  public int TeShuGuaiWu=0;//特殊怪物，0.正常 1-5为5中特殊状态
//  public int iFreakMoney ;//小怪额外爆出的金币的倍率

  public final static int[] hitArea(short[] array, int curIndex,
                                    boolean ishitArea,
                                    boolean isLeft) {
    int a = 0;
    if (ishitArea) {
      a = 4;
    }
    int[] temp = new int[4];
    temp[0] = array[a + curIndex * 8] +
        (isLeft ? 0 :Math.abs(array[a + curIndex * 8 + 0] - array[a + curIndex * 8 + 2]));
    temp[1] = array[a + curIndex * 8 + 3];
    temp[2] = Math.abs(array[a + curIndex * 8 + 0] - array[a + curIndex * 8 +
                       2]);
    temp[3] = Math.abs(array[a + curIndex * 8 + 1] - array[a + curIndex * 8 +
                       3]);
    if (!isLeft) {
      temp[0] = -temp[0];
    }
    return temp;
  }

//  public abstract void move() {}
//
//  public abstract void paint() {}

}
