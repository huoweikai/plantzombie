package com.haopu.JSGame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import cn.cmgame.billing.api.GameInterface;

public class BillingResult {
	public final static int Billing_正版激活 = 0;
	public final static int Billing_感恩回馈 = 1;
	public final static int Billing_道具礼包 = 2;
	public final static int Billing_南瓜大炮 = 3;
	public final static int Billing_购买金币8K = 4;
	public final static int Billing_购买金币8W = 5;
	


	/**
	 * 
	 * 未被激活过
	 */
	protected static final byte SMS_NULL = -1;
	/**
	 * 
	 * 激活失败
	 */
	protected static final byte SMS_FALSE = 0;

	/**
	 * 
	 * 激活成功
	 */

	protected static final byte SMS_SUCCESS = 1;

	/**
	 * 
	 * 存档rms用 是否激活 0未激活 1已经激活
	 * 
	 * 
	 */
	public static byte[] sms_RMS = new byte[] { SMS_NULL, SMS_NULL, SMS_NULL,
			SMS_NULL, SMS_NULL, SMS_NULL, SMS_NULL, SMS_NULL, SMS_NULL,
			SMS_NULL, SMS_NULL,SMS_NULL, SMS_NULL, SMS_NULL,
			SMS_NULL, SMS_NULL, SMS_NULL, SMS_NULL, SMS_NULL, SMS_NULL,
			SMS_NULL, SMS_NULL, };
	
	/**
	 * 
	 * 激活成功后添加的功能 需修改
	 */

	@SuppressWarnings("static-access")
	public static void BillingSuccess(int index) {
		sms_RMS[index]=SMS_SUCCESS;
		switch (index) {
		case Billing_正版激活:
			MyGameCanvas.setST(MyGameCanvas.GmStat_Playing);
			break;
		case Billing_感恩回馈:	
			//GameEngine.me.isJB=1;
			GameEngine.me.addSkillNum(1,4);
			MyGameCanvas.me.saveGame();
			break;
		case Billing_道具礼包:
			GameEngine.me.addSkillNum(0,15);
			GameEngine.me.addSkillNum(1,15);
			GameEngine.me.addSkillNum(2,10);
			MyGameCanvas.me.saveGame();
			break;
		case Billing_南瓜大炮:
			for (int i = 0; i < MyGameCanvas.me.ZB.length; i++) {
			if(MyGameCanvas.me.ZB[i]==-1){
				MyGameCanvas.me.ZB[i]=7;
				MyGameCanvas.me.JLZB[i]=7;
				MyGameCanvas.me.saveGame();
				break;
			}

		}
			break;
		case Billing_购买金币8K://
			GameEngine.usBs.iUsBsCuJinbi+=8000;
			break;
		case Billing_购买金币8W://赠1w
			GameEngine.usBs.iUsBsCuJinbi+=90000;
			break;
		}
        GameEngine.canvas.saveGame();
		saveRMS();
	}
//
	/**
	 * 
	 * 激活成功后返回的 需修改
	 */
	@SuppressWarnings("static-access")
	public static void BillingFailed(int index) {
		switch (index) {
		case Billing_正版激活:
			GameEngine.isSms=true;
        MyGameCanvas.setST(MyGameCanvas.GmStat_RankMap);
			break;
//		case Billing_技能冷却时间永久减半:	
//			
//			break;
//		case Billing_战地急救_10:
//			
//			break;
//		case Billing_南瓜大炮:
//			
//			break;
//		case Billing_购买金币3W:
//			
//			break;
//		case Billing_购买金币8W:
//			
//			break;

		}
	}
	
	public static void saveRMS() {
		DataOutputStream dos;
		FileOutputStream fos = null;
		try {
			fos = MyActivity.instance.openFileOutput("buyu", 0);
		} catch (Exception e) {
			System.out.println("FileOutputStream 出错");
		}
		dos = new DataOutputStream(fos);

		try {
			for (int i = 0; i < sms_RMS.length; i++) {
				dos.writeByte(sms_RMS[i]);
			}
			dos.close();
			fos.close();
		} catch (Exception e) {
			System.out.println("writeInt 出错");
		}

	}

	public static void loadRMS() {
		DataInputStream dis;
		FileInputStream fis = null;
		try {
			fis = MyActivity.instance.openFileInput("buyu");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("FileInputStream 出错");
		}
		dis = new DataInputStream(fis);
		try {
			for (int i = 0; i < sms_RMS.length; i++) {
				sms_RMS[i] = dis.readByte();
			}

			dis.close();
			fis.close();
		} catch (Exception e) {
			System.out.println("readInt 出错");
		}
	}
	

	
}
