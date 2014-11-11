package com.haopu.JSGame;

import java.util.HashMap;

import cn.cmgame.billing.api.GameInterface;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
//import mm.purchasesdk.OnPurchaseListener;
//import mm.purchasesdk.Purchase;
//import mm.purchasesdk.PurchaseCode;

public class ChinaMM extends BillingResult { 
	/*
	 * 1关于移动MM平台，需要调用本地.so文件，2个该文件位于armeabi文件下，
	 * 该文件夹必须放在libs文件夹下,否则路径会出错
	 * 2移动MM平台计费调用同样是利用Android的对话框机制，那么在非Android线程中同样会出现问题，
	 * 这个时候可以采用内部通信handleMessage来发送调用计费
	 * */
	
	/**XML配置问题
	 * 
	 *  
	 *  <receiver
        android:name="com.google.ads.InstallReceiver"
        android:exported="true">
        <intent-filter>
            <action android:name="com.android.vending.INSTALL_REFERRER"/>
        </intent-filter>
    </receiver>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.SEND_SMS" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
	 * 
	 * 
	 */
//	public static final int ITEM0 = Menu.FIRST;// 系统值
//	private EditText mPaycodeView;
//	Context context;
	
//	public  Purchase purchase;
//	public IAPListener mListener;
//	public IAPHandler iapHandler;
//	
	int iBillingIndex;//计费点索引
	
// 计费信息
	public  String APPID = "300002846933";
	public  String APPKEY = "E08B8A4821A7C52B";//此2个值从MM平台获取，对应每个游戏，必须修改
/**
 * 计费信息代码	
 * 必须修改
 */
	private String[] mPaycode ={
			"30000284693301","30000284693302",
			"30000284693303","30000284693304",
			"30000284693305","30000284693306"
	};  
/**
 * 计费点信息描述	
 */
	public static String[] billingMessage = {
	"末日之战一触即发，现在激活立即体验四大场景32场战斗，只需支付信息费6元，需发送1条短信，6元/条（不含通信费）。",
	"技能冷却时间太久不够用？僵尸太多难以应付？立刻购买即可永久减半所有技能冷却时间，体验核弹横飞的快感！只需支付信息费4元，发送1条短信，4元/条（不含通信费）。",
	"敌人太凶猛？差点就能过关？战地急救帮你立刻原地复活，重新投入战场！只需支付信息费2元，发送1条短信，2元/条（不含通信费）。",
	"手里的武器威力不足？想要更爽快的爆破射击？立刻购买南瓜加农炮，享受秒杀成群僵尸的快感！只需支付信息费2元，发送1条短信，2元/条（不含通信费）。  ",
	"金币不够，无法畅快扫射？立即购买即可立刻获得30000金币。只需支付信息费2元，发送1条短信，2元/条（不含通信费）。", 
	"僵尸太多，豌豆射手力不从心？立刻购买即可获得80000金币，立刻购买高级植物畅快扫射！只需支付信息费4元，发送1条短信，4元/条（不含通信费）。",
	}; 
	
	public  ChinaMM(){
//		iapHandler = new IAPHandler(MyActivity.instance);
//		mListener = new IAPListener(MyActivity.instance,iapHandler);
//		purchase = Purchase.getInstance();
//		purchase.setAppInfo(APPID, APPKEY);
//		purchase.init(MyActivity.instance,mListener);
//		
	}

/**
 * 正式发送计费信息
 * 注意：该调用方法采用Android对话框，若在Android线程中调用没有问题
 * 若是在自己的线程中调用，将会出现问题，那么需要采用内部通信机制
 * @param index
 */	
	
	/*
	public void sendBillingMsg(final int index){
		AlertDialog.Builder build=new AlertDialog.Builder(MyActivity.instance);
		build.setMessage(billingMessage[index]).setIcon(R.drawable.icon).
		setPositiveButton("是",new DialogInterface.OnClickListener(){//点击是进行发送计费
			public void onClick(DialogInterface dialog, int which) {
				iBillingIndex = index;
				purchase.order(MyActivity.instance,mPaycode[index],mListener);
			}
		}).setNegativeButton("否",new DialogInterface.OnClickListener() {//点击取消，从哪里来，回哪里去
			public void onClick(DialogInterface dialog, int which) {
				BillingFailed(index);
			}
		}).setCancelable(false).create().show();
	}
	*/
	
	
	public void sendBillingMsg(final int index){
		AlertDialog.Builder build=new AlertDialog.Builder(MyActivity.instance);
		build.setMessage(billingMessage[index]).setIcon(R.drawable.icon).
		setPositiveButton("是",new DialogInterface.OnClickListener(){//点击是进行发送计费
			public void onClick(DialogInterface dialog, int which) {
				iBillingIndex = index;
				//purchase.order(MyActivity.instance,mPaycode[index],mListener);
				GameInterface.doBilling(MyActivity.instance, true, true, ""+index, null, payCallback);
			}
		}).setNegativeButton("否",new DialogInterface.OnClickListener() {//点击取消，从哪里来，回哪里去
			public void onClick(DialogInterface dialog, int which) {
				BillingFailed(index);
			}
		}).setCancelable(false).create().show();
	}
	
	
	
	

/**
 * 以下为读存数据方法，爱要不要
 * @param paycode
 */
//	private void savePaycode(String paycode) {
//		Editor sharedata = MyActivity.instance.getSharedPreferences("data", 0).edit();
//		sharedata.putString(PAYCODE, paycode);
//		sharedata.commit();
//	}
//	private String readPaycode() {
//		SharedPreferences sharedPreferences = MyActivity.instance.getSharedPreferences("data",0);
//		String paycode = sharedPreferences.getString(PAYCODE, LEASE_PAYCODE);
//		return paycode;
//	}


/*
	public class IAPHandler extends Handler {

		public static final int INIT_FINISH = 10000;
		public static final int BILL_FINISH = 10001;
		public static final int QUERY_FINISH = 10002;
		public static final int UNSUB_FINISH = 10003;
		public IAPHandler(Activity context) {}

	}
	
	public class IAPListener  implements OnPurchaseListener{
		private final String TAG = "IAPListener";
		private IAPHandler iapHandler;
		
		Context context;

		public IAPListener(Context context, IAPHandler iapHandler) {
			this.context = context;
			this.iapHandler = iapHandler;
		}

		public void onAfterApply() {

		}

		@Override
		public void onAfterDownload() {

		}

		@Override
		public void onBeforeApply() {

		}

		@Override
		public void onBeforeDownload() {

		}

		@Override
		public void onInitFinish(int code) {
			Log.d(TAG, "Init finish, status code = " + code);
			Message message = iapHandler.obtainMessage(IAPHandler.INIT_FINISH);
			String result = "初始化结果：" + Purchase.getReason(code);
			message.obj = result;
			message.sendToTarget();
		}
	
		@Override
		public void onQueryFinish(int arg0, HashMap arg1) {
			// TODO Auto-generated method stub
		}
		@Override
		public void onBillingFinish(int code, HashMap arg1) {
			String result = "订购结果：订购成功";
			Message message = iapHandler.obtainMessage(IAPHandler.BILL_FINISH);
			if (code == PurchaseCode.ORDER_OK || (code == PurchaseCode.AUTH_OK)) {
				BillingSuccess(iBillingIndex);
			} else {
				BillingFailed(iBillingIndex);
			}
		}
	}
	*/
	   final  GameInterface.IPayCallback payCallback = new GameInterface.IPayCallback() {
	        @Override
	        public void onResult(int resultCode, String billingIndex, Object obj) {
	          String result = "";
	          switch (resultCode) {
	            case cn.cmgame.billing.api.BillingResult.SUCCESS:
	              //result = "购买道具：[" + billingIndex + "] 成功！";
	            	BillingSuccess(iBillingIndex);
	              break;
	            case cn.cmgame.billing.api.BillingResult.FAILED:
	             // result = "购买道具：[" + billingIndex + "] 失败！";
	            	BillingFailed(iBillingIndex);
	              break;
	            default:
	          
	              break;
	          }
	         
	        }
	     };
	
}
