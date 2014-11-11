//package com.haopu.JSGame;
//
//import cn.emagsoftware.gamecommunity.api.GameCommunity;
//import cn.emagsoftware.gamecommunity.callback.IChallenge;
//import cn.emagsoftware.gamecommunity.resource.Achievement;
//import cn.emagsoftware.gamecommunity.resource.Challenge;
//import cn.emagsoftware.gamecommunity.resource.Score;
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.text.TextUtils;
//import java.util.List;
//
///**
// * 1.游戏圈圈Android版本SDK的jar包名称是“gcsdk.jar” 2.将SDK的res文件目录直接Copy到游戏的res目录下；
// * 3.在SDK的AndroidManifest.xml加： <activity android:screenOrientation="sensor"
// * android:name="cn.emagsoftware.gamecommunity.activity.CommunityChildActivity"
// * android:label="CommunityChildActivity"
// * android:configChanges="orientation|keyboardHidden"
// * android:windowSoftInputMode="stateHidden"
// * android:theme="@android:style/Theme.NoTitleBar"/> <activity
// * android:screenOrientation="sensor"
// * android:name="cn.emagsoftware.gamecommunity.activity.CommunityActivity"
// * android:label="CommunityActivity"
// * android:configChanges="orientation|keyboardHidden"
// * android:theme="@android:style/Theme.NoTitleBar"
// * android:windowSoftInputMode="adjustPan" android:launchMode="singleTask" />
// * 4.在游戏的AndroidManifest.xml文件中添加指定的android.permision权限： <uses-permission
// * android:name="android.permission.INTERNET" /> <uses-permission
// * android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
// * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
// * <uses-permission
// * android:name="android.permission.CHANGE_WIFI_STATE"></uses-permission>
// * <uses-permission
// * android:name="android.permission.CHANGE_NETWORK_STATE"></uses-permission>
// * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
// * <uses-permission android:name="android.permission.GET_ACCOUNTS" />
// * <uses-permission android:name="android.permission.SEND_SMS" />
// * <uses-permission
// * android:name="android.permission.READ_CONTACTS"></uses-permission>
// * <uses-permission android:name="android.permission.VIBRATE"/> <uses-permission
// * android:name="android.permission.READ_PHONE_STATE"/>
// * 
// * @author Administrator
// */
//public class GameNet {
//	/**
//	 * 游戏名
//	 */
//	final static String AppName = "疯狂植物横扫僵尸";
//	/**
//	 * 产品密钥，弱联网后台分配，必须改
//	 */
//	final static String Key = "000070605000";
//	/**
//	 * 产品秘密，弱联网后台分配，必须改
//	 */
//	final static String EncodeKey = "o19J1kzyuSxOoJOpueyaSEc5Ffo=";
//	/**
//	 * 游戏Id，弱联网后台分配，必须改
//	 */
//	final static String ApplicationId = "12170";
//	/**
//	 * 游戏包名
//	 */
//	final static String PackageName = "com.yidong";
//	// GameMenuItem gameMenuItem;
//	Context con;
//
//	public GameNet(Context con) {
//		this.con = con;
//		init();
//		// gameMenuItem = new GameMenuItem();
//	}
//
//	/**
//	 * Initialize SDK's run environment which include Game community and
//	 * billing.
//	 */
//	public void init() {
//		// GameInterface.initializeApp((Activity)con, "Agile Buddy",
//		// "Emag Software Beijing", "025-53232322");
//		// GameInterface.getInstance().setExitCallback(new GameExitCallback() {
//		// @Override
//		// public void onConfirmExit() {
//		// GameInterface.exitApp();
//		// ((Activity)con).finish();
//		// }
//		//
//		// @Override
//		// public void onCancelExit() {
//		// }
//		// });
//		// 初始化游戏圈圈游戏信息
//		GameCommunity.initializeSDK((Activity) con, AppName, Key, EncodeKey,
//				ApplicationId, PackageName);
//		// 实现发起挑战或应战时的回调函数
//		GameCommunity.setChallengeDelegate(new IChallenge() {
//			public void challenge() {
//				// 这是挑战ID，用来判断挑战是否存在的
//				String challengeId = GameCommunity.getCurrentChallengeId();
//				// 这是挑战关卡ID，标识该挑战是针对哪一关卡进行的，如果游戏没有关卡的概念
//				// 该值为null
//				String challengeCrossId = GameCommunity
//						.getCurrentChallengeCrossId();
//				if (!TextUtils.isEmpty(challengeId)) {
//					Intent intent = new Intent((Activity) con, ((Activity) con)
//							.getClass());
//					if (!TextUtils.isEmpty(challengeCrossId)) {
//						intent.putExtra("CrossId", challengeCrossId);
//					}
//					((Activity) con).startActivity(intent);
//				}
//			}
//		});
//	}
//
//	/**
//	 * 
//	 * 精品推荐
//	 */
//	public void toMoreGame() {
//		GameCommunity.launchGameRecommend(con);
//	}
//
//	/**
//	 * 游戏圈圈
//	 */
//	public void toQuanQuan() {
//		GameCommunity.launchGameCommunity((Activity) con);
//	}
//
//	/**
//	 * 退出游戏圈圈，退出游戏时调用
//	 */
//	public void exitQuanQuan() {
//		GameCommunity.exit();
//	}
//
//	/**
//	 * 
//	 * @param d
//	 *            为游戏分配的总排行ID
//	 * @param score
//	 *            上传的分数
//	 */
//	public void upScore(final String d, final long score) {
//		
//		AlertDialog.Builder build = new AlertDialog.Builder(this.con);
//		build.setMessage("是否提交分数")
//				.setPositiveButton("YES",
//						new DialogInterface.OnClickListener() {
//
//							@Override
//							public void onClick(DialogInterface dialog,
//									int which) {
//								GameCommunity.commitScoreWithRank(d, score,
//										new Score.SubmitScoreCallback() {
//											@Override
//											public void onFailure(
//													String exceptionMessage) {
//												System.out
//														.println(exceptionMessage);
//
//											}
//
//											@Override
//											public void onSuccess(
//													String resultMessage,
//													List<Score> scores,
//													Score myScore) {
//
//											}
//										});
//							}
//						})
//				.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//						// Tools.setST(GameConmmon.ST_WIN);
//					}
//				}).show();
//	}
//
//	/**
//	 * 
//	 * @param id
//	 *            成就id
//	 */
//	public void upAchievement(final String id) {
//
//		AlertDialog.Builder build = new AlertDialog.Builder(this.con);
//		build.setMessage("是否提交成就")
//				.setPositiveButton("YES",
//						new DialogInterface.OnClickListener() {
//
//							@Override
//							public void onClick(DialogInterface dialog,
//									int which) {
//								GameCommunity.openAchievement(id,
//										new Achievement.UnlockCallback() {
//											@Override
//											public void onSuccess(
//													String resultMessage) {
//												System.out
//														.println(resultMessage);
//											}
//
//											@Override
//											public void onFailure(
//													String exceptionMessage) {
//												System.out
//														.println(exceptionMessage);
//											}
//										});
//							}
//						})
//				.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						// TODO Auto-generated method stub
//
//					}
//				}).show();
//	}
//
//	/**
//	 * 
//	 * @param num
//	 *            挑战的成绩
//	 */
//	public void upChallengeScore(long score) {
//		GameCommunity.commitChallengeScore((Activity) con, score,
//				new Challenge.SubmitScoreCallback() {
//					@Override
//					public void onSuccess(String message) {
//						System.out.println(message);
//					}
//
//					@Override
//					public void onFailure(String exceptionMessage) {
//						System.out.println(exceptionMessage);
//					}
//				});
//	}
//}
