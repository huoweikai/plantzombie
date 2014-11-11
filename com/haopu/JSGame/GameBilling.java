//package com.haopu.JSGame;
//
//import android.app.Activity;
//import android.widget.Toast;
//
////import com.haopu.yinyueyouxi.MyGameCanvas;
////import com.haopu.yinyueyouxi.MyGameView;
//
//import cn.emagsoftware.gamebilling.api.GameInterface;
//import cn.emagsoftware.gamebilling.api.GameInterface.BillingCallback;
////import cn.emagsoftware.gamebilling.view.BillingView;
//
//public class GameBilling {
//
//	public final static String Billing[] = { "000", "001", "002", "003", "004", "005", "006"
//		, "007", "008", "009", "010", "011", "012", "013", "014", "015", "016", "017"};
//
//	static int BillingIndex;
//
//	public final static int Billing_正版激活 = 0;
//	public final static int Billing_技能冷却时间永久减半 = 1;
//	public final static int Billing_永久金币双倍 = 2;
//	public final static int Billing_战地急救_10 = 3; 
//	public final static int Billing_南瓜大炮 = 4;
//	public final static int Billing_火球技能升满级 = 5;
//	public final static int Billing_冰霜技能升满级 = 6;
//	public final static int Billing_核弹技能升满级 = 7;
//	public final static int Billing_血量升满级 = 8;
//	public final static int Billing_闪避升满级 = 9;
//	public final static int Billing_反弹升满级 = 10;
//	public final static int Billing_暴击升满级 = 11;
//	public final static int Billing_攻速升满级 = 12;
//	public final static int Billing_伤害升满级 = 13;
//	public final static int Billing_眩晕升满级 = 14;
//	public final static int Billing_穿透升满级 = 15;
//
//	public static void initBilling(Activity activity) {
////		GameInterface.initializeApp(activity, "新水果连连看HD版", "南京浩普天地信息技术有限公司",
////				"13851981385");
//		 GameInterface.initializeApp(activity);
////		 istingdun=false;
//	}
//
//	/**
//	 * 付费条件下调用
//	 * 
//	 * @param index
//	 *            计费点的索引
//	 * @param isRepeated
//	 *            是否是重复计费
//	 * @param useSms
//	 *            是否是短信计费
//	 */
//	static boolean istingdun=false;
//	public void setBilling(int index, boolean isRepeated, boolean useSms) {
////		MyGameCanvas.smscont=0;
////		if(istingdun){
////			return;
////		}
////		BillingIndex = index;
////		GameInterface.doBilling(MyActivity.instance,useSms, isRepeated, Billing[index],
////				billingCallback);
////		istingdun=true;
//		
//	}
//
//	/**
//	 * 是否已经付费（只需要在单次计费下判断）
//	 * 
//	 * @param index
//	 *            计费点的索引
//	 * @return
//	 */
//	public static boolean isBilling(int index) {
////		if (!GameInterface.getActivateFlag(Billing[index])) {
////			return false;
////		}
//		return true;
//	}
//	
//
//	BillingCallback billingCallback = new BillingCallback() {
//		
//		@Override
//		public void onBillingSuccess(String billingIndex) {// 扣费成功后执行
//			istingdun=false;
//			switch (BillingIndex) {
//			case Billing_正版激活:
//            GameEngine.me.iResult[0][2]=0;
//				break;
//			case Billing_技能冷却时间永久减半:
//				GameEngine.me.isJB=1;
//				MyGameCanvas.me.saveGame();
//				break;
//				
//			case Billing_永久金币双倍:
//				MyGameCanvas.me.saveGame();
//				break;
//			case Billing_战地急救_10:
//				MyGameCanvas.me.saveGame();
//				GameEngine.fuhuo+=3;
//				break;
//			case Billing_南瓜大炮:
//				for (int i = 0; i < MyGameCanvas.me.ZB.length; i++) {
//					if(MyGameCanvas.me.ZB[i]==-1){
//						MyGameCanvas.me.ZB[i]=7;
//						MyGameCanvas.me.JLZB[i]=7;
//						MyGameCanvas.me.saveGame();
//						break;
//					}
//
//				}
//
//				
//				break;
//			case Billing_火球技能升满级:
//			case Billing_冰霜技能升满级:
//			case Billing_核弹技能升满级:
//			case Billing_血量升满级:
//			case Billing_闪避升满级:
//			case Billing_反弹升满级:
//			case Billing_暴击升满级:
//			case Billing_攻速升满级: 
//			case Billing_伤害升满级:
//			case Billing_眩晕升满级:
//			case Billing_穿透升满级:
//				MyGameCanvas.jiNengKaiQi[BillingIndex-5] = MyGameCanvas.me.jiNengMax;
//				if(BillingIndex-5!=10){
//				if(MyGameCanvas.jiNengKaiQi[BillingIndex-5+1]==-1){
//					MyGameCanvas.jiNengKaiQi[BillingIndex-5+1]=1;
//				}
//				if(BillingIndex-5==6&&MyGameCanvas.jiNengKaiQi[BillingIndex-5+2]==-1){
//						MyGameCanvas.jiNengKaiQi[BillingIndex-5+3]=1;	
//				}
//				}
//				MyGameCanvas.me.saveGame();
//				break;
//					
//				
//			}
//		} 
//
//		@Override
//		public void onBillingFail(String billingIndex) {// 扣费失败后执行
//			istingdun=false;
//			switch (BillingIndex) {
//			case Billing_正版激活:
//	            GameEngine.me.iResult[0][2]=0;
//	            MyGameCanvas.setST(MyGameCanvas.GmStat_RankMap);
//				break;
//
//			default:
//			    if(MyGameCanvas.gameStatus==MyGameCanvas.GmStat_YDSms){
//			    	MyGameCanvas.setST(MyGameCanvas.lastStatus);
//			    	
//			    }
//				break;
//			}
//			}
//
//		
//	
//
//		@Override
//		public void onUserOperCancel(String billingIndex) {// 不选择付费执行
//			istingdun=false;
////			 MyGameCanvas.me.gameStatus=MenuState.ST_MENU;
//			switch (BillingIndex) {
//			case Billing_正版激活:
//	            GameEngine.me.iResult[0][2]=0;
//	            MyGameCanvas.setST(MyGameCanvas.GmStat_RankMap);
//				break;
//
//			default:
//			    if(MyGameCanvas.gameStatus==MyGameCanvas.GmStat_YDSms){
//			    	MyGameCanvas.setST(MyGameCanvas.lastStatus);
//			    }
//				break;
//			}
//
//		}
//
//		public void onUserOperError(int errCode) {
////			switch(errCode){
////				case BillingView.ERROR_SMS_SEND_FAILURE:
////					
////	Toast.makeText(BillingDemo.this, "短信激活失败", Toast.LENGTH_LONG).show();
////					break;
////	case BillingView.ERROR_WEB_NETWORK_ERROR:
////		
//////	Toast.makeText(BillingDemo.this, "联网错误", Toast.LENGTH_LONG.show();
////					break;
////	case BillingView.ERROR_BILLING_FAILURE:
////		
//////	Toast.makeText(BillingDemo.this, "计费失败了", Toast.LENGTH_LONG.show();
////					break; 
////			}
//		};
//	};
//
// 
//
//	public static void MoreGame() {// 更多游戏
//			GameInterface.viewMoreGames(MyGameView.context);// 更多游戏			
//	}
//}
