package com.haopu.JSGame;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

	public final static int VERSION = 1;// �汾��
	public final static String TABLE_NAME = "mc";// ����
	public static final String DATABASE_NAME = "SanGuoLvBu.db";

	public final static String ID = "id";// Ĭ�ϱ����е�
	public final static String Save = "Save";//
	public final static String SQL1 = "sql1";
	public final static String SQL2 = "sql2";
	public final static String SQL3 = "sql3";
	public final static String SQL4 = "sql4";
	public final static String SQL5 = "sql5";
	public final static String SQL6 = "sql6";
	public final static String SQL7 = "sql7";
	public final static String SQL8 = "sql8";
	public final static String SQL9 = "sql9";
	public final static String SQL10 = "sql10";
	public final static String SQL11 = "sql11";
	public final static String SQL12 = "sql12";
	public final static String SQL13 = "sql13";
	public final static String SQL14 = "sql14";
	public final static String SQL15 = "sql15";
	public final static String SQL16 = "sql16";
	public final static String SQL17 = "sql17";
	public final static String SQL18 = "sql18";
	public final static String SQL19 = "sql19";
	public final static String SQL20 = "sql20";

	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSION); 
		Log.i("MySQLite", "�ѽ���");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String str_sql = "CREATE TABLE " + TABLE_NAME + "(" + ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + Save + " INTEGER ,"
				+ SQL1 + " INTEGER ," + SQL2 + " INTEGER ," + SQL3
				+ " INTEGER ," + SQL4 + " INTEGER ," + SQL5 + " INTEGER ,"
				+ SQL6 + " INTEGER ," + SQL7 + " INTEGER ," + SQL8
				+ " INTEGER ," + SQL9 + " INTEGER ," + SQL10 + " INTEGER ,"
				+ SQL11 + " INTEGER ," + SQL12 + " INTEGER ," + SQL13
				+ " INTEGER ," + SQL14 + " INTEGER ," + SQL15 + " INTEGER ,"
				+ SQL16 + " INTEGER ," + SQL17 + " INTEGER ," + SQL18
				+ " INTEGER ," + SQL19 + " INTEGER ," + SQL20 + " INTEGER );";
		db.execSQL(str_sql);
		Log.i("�����ݿ�", "onCreate");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.i("onUpgrade", "�Ѹ���");
	}

}
