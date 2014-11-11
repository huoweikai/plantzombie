//package com.haopu.JSGame;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import cn.game189.sms.SMS;
//import cn.game189.sms.SMSListener;
//
//
///**
// * 
// * android电信sms 使用方法： 1.在项目中引入sms.jar包; 2.在AndroidManifest.xml中添加:
// * <uses-permission android:name="android.permission.SEND_SMS" />
// * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
// * <activity android:name="cn.game189.sms.SMS"
// * android:theme="@android:style/Theme.Dialog"></activity>
// * 
// * 
// * 
// * 
// */
//
//public final class SMS_dianxing {
//	/**
//	 * 
//	 * 
//	 * 定义激活点 要修改
//	 * 
//	 */
//
//	
//	public final static int Billing_正版激活 = 0;
//	public final static int Billing_技能冷却时间永久减半 = 1;
//	public final static int Billing_战地急救_10 = 2;
//	public final static int Billing_南瓜大炮 = 3;
//	public final static int Billing_购买金币3W = 4;
//	public final static int Billing_购买金币8W = 5;
//
//	
//
//	/**
//	 * 
//	 * 
//	 * 激活点介绍 要修改
//	 * 
//	 */
//	
//	public static String[] str_smsIntro = {
//			"末日之战一触即发，现在激活立即体验四大场景32场战斗，只需支付信息费6元，需发送1条短信，6元/条（不含通信费）。",
//			"技能冷却时间太久不够用？僵尸太多难以应付？立刻购买即可永久减半所有技能冷却时间，体验核弹横飞的快感！只需支付信息费4元，发送1条短信，4元/条（不含通信费）。",
//			"敌人太凶猛？差点就能过关？战地急救帮你立刻原地复活，重新投入战场！只需支付信息费2元，发送1条短信，2元/条（不含通信费）。",
//			"手里的武器威力不足？想要更爽快的爆破射击？立刻购买南瓜加农炮，享受秒杀成群僵尸的快感！只需支付信息费2元，发送1条短信，2元/条（不含通信费）。  ",
//			"金币不够，无法畅快扫射？立即购买即可立刻获得30000金币。只需支付信息费2元，发送1条短信，2元/条（不含通信费）。", 
//			"僵尸太多，豌豆射手力不从心？立刻购买即可获得80000金币，立刻购买高级植物畅快扫射！只需支付信息费4元，发送1条短信，4元/条（不含通信费）。",
//			};
//	/**
//	 * 
//	 * 
//	 * 激活点代码，电信分配 要修改
//	 * 
//	 */
//	
//	public static String[] sms_Message = {
//			"0611C3203511022207770311022207701601MC099544000000000000000000000000",
//			"0411C3203511022207770311022207701701MC099544000000000000000000000000",
//			"0211C3203511022207770311022207701801MC099544000000000000000000000000",
//			"0211C3203511022207770311022207701901MC099544000000000000000000000000",
//			"0211C3203511022207770311022207702001MC099544000000000000000000000000",
//			"0411C3203511022207770311022207702101MC099544000000000000000000000000",};
//
//	/**
//	 * 
//	 * 未被激活过
//	 */
//	protected static final byte SMS_NULL = -1;
//	/**
//	 * 
//	 * 激活失败
//	 */
//	protected static final byte SMS_FALSE = 0;
//
//	/**
//	 * 
//	 * 激活成功
//	 */
//
//	protected static final byte SMS_SUCCESS = 1;
//
//	/**
//	 * 
//	 * 存档rms用 是否激活 0未激活 1已经激活
//	 * 
//	 * 
//	 */
//	public static byte[] sms_RMS = new byte[] { SMS_NULL, SMS_NULL, SMS_NULL,
//			SMS_NULL, SMS_NULL, SMS_NULL, SMS_NULL, SMS_NULL, SMS_NULL,
//			SMS_NULL, SMS_NULL,SMS_NULL, SMS_NULL, SMS_NULL,
//			SMS_NULL, SMS_NULL, SMS_NULL, SMS_NULL, SMS_NULL, SMS_NULL,
//			SMS_NULL, SMS_NULL, };
//
//	static int smsIndex;
//
//	/**
//	 * 
//	 * 
//	 * 自己随意定义，可以不用改
//	 * 
//	 */
//	static String[] smsData = { "sms", "sms", "sms", "sms", "sms", "sms",
//			"sms", "sms", "sms", "sms", "sms", "sms", "sms", "sms",
//			"sms", "sms","sms", "sms", "sms", "sms", "sms", "sms",
//			"sms", "sms",};
//
//	static boolean runStop = false;
//
//	public static void sendSMS(int Index) {
//		smsIndex=Index;
//		runStop = true;
//		String strMessage = sms_Message[smsIndex];
//		String strInsro = str_smsIntro[smsIndex];
//		smsData[smsIndex]+=System.currentTimeMillis();
//		
//		if (SMS.checkFee(smsData[smsIndex], MyActivity.instance,
//				new SMSListener() {
//					public void smsOK(String arg0) {
//						
//						sms_收费点激活成功();
//
//						runStop = false;
//					}
//
//					public void smsFail(String arg0, int arg1) {
//						sms_收费点激活失败();
//						runStop = false;
//					}
//
//					public void smsCancel(String arg0, int arg1) {
//						sms_收费点激活失败();
//						runStop = false;
//					}
//
//				}, strMessage, strInsro, "发送成功，游戏继续")) {
//			runStop = false;
//		}
//	}
//
//	/**
//	 * 
//	 * 激活成功后添加的功能 需修改
//	 */
//
//	@SuppressWarnings("static-access")
//	public static void sms_收费点激活成功() {
//		sms_RMS[smsIndex]=SMS_SUCCESS;
//		switch (smsIndex) {
//		case Billing_正版激活:
//		
////			MyGameCanvas.setST(MyGameCanvas.GmStat_RankMap);
//			break;
//		case Billing_技能冷却时间永久减半:	
//			GameEngine.me.isJB=1;
//			MyGameCanvas.me.saveGame();
//			break;
//		case Billing_战地急救_10:
//			GameEngine.fuhuo+=10;
//			MyGameCanvas.me.saveGame();
//			break;
//		case Billing_南瓜大炮:
//			for (int i = 0; i < MyGameCanvas.me.ZB.length; i++) {
//			if(MyGameCanvas.me.ZB[i]==-1){
//				MyGameCanvas.me.ZB[i]=7;
//				MyGameCanvas.me.JLZB[i]=7;
//				MyGameCanvas.me.saveGame();
//				break;
//			}
//
//		}
//			break;
//		case Billing_购买金币3W:
//			GameEngine.usBs.iUsBsCuJinbi+=30000;
//			break;
//		case Billing_购买金币8W:
//			GameEngine.usBs.iUsBsCuJinbi+=80000;
//			break;
//		}
//        GameEngine.canvas.saveGame();
//		saveRMS();
//	}
////
//	/**
//	 * 
//	 * 激活成功后返回的 需修改
//	 */
//	@SuppressWarnings("static-access")
//	public static void sms_收费点激活失败() {
//		switch (smsIndex) {
//		case Billing_正版激活:
//			GameEngine.isSms=true;
//        MyGameCanvas.setST(MyGameCanvas.GmStat_RankMap);
//			break;
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
//
//		}
//	}
//
//	public static void saveRMS() {
//		DataOutputStream dos;
//		FileOutputStream fos = null;
//		try {
//			fos = MyActivity.instance.openFileOutput("buyu", 0);
//		} catch (Exception e) {
//			System.out.println("FileOutputStream 出错");
//		}
//		dos = new DataOutputStream(fos);
//
//		try {
//			for (int i = 0; i < sms_RMS.length; i++) {
//				dos.writeByte(sms_RMS[i]);
//			}
//			dos.close();
//			fos.close();
//		} catch (Exception e) {
//			System.out.println("writeInt 出错");
//		}
//
//	}
//
//	public static void loadRMS() {
//		DataInputStream dis;
//		FileInputStream fis = null;
//		try {
//			fis = MyActivity.instance.openFileInput("buyu");
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("FileInputStream 出错");
//		}
//		dis = new DataInputStream(fis);
//		try {
//			for (int i = 0; i < sms_RMS.length; i++) {
//				sms_RMS[i] = dis.readByte();
//			}
//
//			dis.close();
//			fis.close();
//		} catch (Exception e) {
//			System.out.println("readInt 出错");
//		}
//	}
//
//
//
//}
