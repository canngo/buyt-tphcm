package vn.spidersoft.buyt.db;

import java.util.ArrayList;

import vn.spidersoft.buyt.model.BusLine;
import vn.spidersoft.buyt.model.BusStop;
import vn.spidersoft.buyt.model.Buyt;
import vn.spidersoft.buyt.model.Street;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccesser {
	/**
	 * データベースバージョン
	 */
	private final static int    DB_VERSION = 2;
	/**
	 * DB名
	 */
	private final static String DB_NAME = "buyt.db";
	/**
	 * テーブル名
	 */
    private final static String TABLE_STREET = "street";
    private final static String TABLE_BUYT = "buyt";
    private final static String TABLE_BUSSTOP = "buyt_stop";
    private final static String TABLE_BUSLINE = "buyt_line";
    
    private static DatabaseAccesser instance = null;
    private final static String CREATE_TABLE_STREET = 
    	"CREATE TABLE IF NOT EXISTS " +TABLE_STREET+
    	" (id INTEGER PRIMARY KEY, name TEXT, area TEXT)";
    
    private final static String CREATE_TABLE_BUYT = 
    	"CREATE TABLE IF NOT EXISTS " +TABLE_BUYT+
    	" (id INTEGER PRIMARY KEY, code TEXT, name TEXT)";
    
    private final static String CREATE_TABLE_BUYTSTOP = 
    	"CREATE TABLE IF NOT EXISTS " +TABLE_BUSSTOP+
    	" (id INTEGER PRIMARY KEY, street_name TEXT, "+
    	" buyt_code TEXT, buyt_going TEXT, house_num TEXT, area TEXT)";
    
    private final static String CREATE_TABLE_BUSLINE = 
    	"CREATE TABLE IF NOT EXISTS " +TABLE_BUSLINE+
    	" (id INTEGER PRIMARY KEY, buyt_code TEXT, " +
    	"buyt_going TEXT, buyt_code_ex TEXT)";
    
    private SQLiteDatabase db = null;
    
    private DatabaseAccesser(Context context) {
    	//データベースオブジェクトの生成
        DBHelper dbHelper = new DBHelper(context);
        db  = dbHelper.getWritableDatabase();
    }
    
    public static DatabaseAccesser getInstance(Context context) {
    	if (instance == null) {
    		instance = new DatabaseAccesser(context);
    	}
    	return instance;
    }
    
    public String[] getStreets() {
    	ArrayList<String> list = new ArrayList<String>();
    	Cursor cursor = null;
    	String[] columns = {Street.COL_NAME};
    	String selection = null;
    	String[] selectionArgs = null;
    	String groupBy = null;
    	String having = null;
    	String orderBy = null;
    	cursor = db.query(TABLE_STREET, columns, selection, selectionArgs, groupBy, having, orderBy);
    	String[] streets = new String[cursor.getCount()];
    	if (cursor.moveToFirst()) {
	    	do {
	    		String name = cursor.getString(0);
	    		list.add(name);
	    	} while (cursor.moveToNext());
    	}
    	list.toArray(streets);
    	return streets;
    }
    
    /**
     * レコードの追加
     * @param street
     * @return
     */
    public boolean insertStreet(Street street) {
    	boolean result = false;
    	long colNum = 0;
        ContentValues values=new ContentValues();
        values.put(Street.COL_NAME,street.getName());
      
//        String where = String.format("%s='%s'", Street.COL_NAME,street.getName());
//        colNum = db.update(TABLE_STREET,values,where,null);
        if (colNum == 0) colNum = db.insert(TABLE_STREET,"",values);
        if (colNum != -1) result = true;
        return result;
    }
    
    /**
     * レコードの追加
     * @param buyt
     * @return
     */
    public boolean insertBuyt(Buyt buyt) {
    	boolean result = false;
    	long colNum = 0;
        ContentValues values = new ContentValues();
        values.put(Buyt.COL_NAME, buyt.getName());
        values.put(Buyt.COL_CODE, buyt.getCode());

//        String where = String.format("%s='%s'", Buyt.COL_CODE, buyt.getCode());
//        colNum = db.update(TABLE_BUYT,values,where,null);
        if (colNum == 0) colNum = db.insert(TABLE_BUYT,"",values);
        if (colNum != -1) result = true;
        return result;
    }
    
    /**
     * レコードの追加
     * @param BusStop
     * @return
     */
    public boolean insertBusStop(BusStop busStop) {
    	boolean result = false;
    	long colNum = 0;
        ContentValues values = new ContentValues();
        values.put(BusStop.COL_HOUSE_NUM, busStop.getHouseNum());
        values.put(BusStop.COL_STREET_NAME, busStop.getStreetName());
        values.put(BusStop.COL_AREA, busStop.getArea());
        values.put(BusStop.COL_BUYT_CODE, busStop.getBuytCode());
        values.put(BusStop.COL_BUYT_GOING, busStop.getBuytGoing());
        
//        String where = String.format("%s='%s' AND %s='%s' AND %s='%s'", 
//        		BusStop.COL_HOUSE_NUM, busStop.getHouseNum(),
//        		BusStop.COL_STREET_NAME, busStop.getStreetName(),
//        		BusStop.COL_BUYT_CODE, busStop.getBuytCode());
//        colNum = db.update(TABLE_BUSSTOP,values,where,null);
        if (colNum == 0) colNum = db.insert(TABLE_BUSSTOP,"",values);
        if (colNum != -1) result = true;
        return result;
    }
    
    /**
     * レコードの追加
     * @param street
     * @return
     */
    public boolean insertBusline(BusLine busline) {
    	boolean result = false;
        ContentValues values=new ContentValues();
        values.put(BusLine.COL_BUYT_CODE, busline.getBuytCode());
        values.put(BusLine.COL_BUYT_GOING, busline.getBuytGoing());
        values.put(BusLine.COL_BUYT_CODE_EX, busline.getBuytCodeEx());
        long colNum = db.insert(TABLE_BUSLINE,"",values);
        if (colNum != -1) result = true;
        return result;
    }
    /**
     * 
     */
    public Cursor selectBusStopByStreetName(String streetName) {
    	Cursor cursor = null;
    	String[] columns = {"_id", BusStop.COL_HOUSE_NUM};
    	String selection = BusStop.COL_STREET_NAME +"=?";
    	String[] selectionArgs = {streetName};
    	String groupBy = null;
    	String having = null;
    	String orderBy = null;
    	cursor = db.query(TABLE_BUSSTOP, columns, selection, selectionArgs, groupBy, having, orderBy);
    	return cursor;
    }
    
    /**
     * 
     */
    public ArrayList<BusStop> selectBusStopByStreetName2(String streetName, String houseNum) {
    	ArrayList<BusStop> buytNumList = new ArrayList<BusStop>();
    	Cursor cursor = null;
    	String[] columns = {BusStop.COL_BUYT_CODE, BusStop.COL_BUYT_GOING, BusStop.COL_STREET_NAME, 
    						BusStop.COL_HOUSE_NUM, BusStop.COL_AREA};
    	String selection = BusStop.COL_STREET_NAME +"=? AND "+BusStop.COL_HOUSE_NUM+"=?";
    	String[] selectionArgs = {streetName, houseNum};
    	String groupBy = null;
    	String having = null;
    	String orderBy = null;
    	cursor = db.query(TABLE_BUSSTOP, columns, selection, selectionArgs, groupBy, having, orderBy);
    	if (cursor.moveToFirst()) {
    		do {
	    		String buyt_code = cursor.getString(0);
	    		String buyt_going = cursor.getString(1);
	    		String street_name = cursor.getString(2);
	    		String house_num = cursor.getString(3);
	    		String area = cursor.getString(4);
	    		System.out.println(buyt_code);
	    		BusStop busStop = new BusStop(buyt_code, buyt_going, street_name, house_num, area);
	    		buytNumList.add(busStop);
    		} while (cursor.moveToNext());
    	}
    	return buytNumList;
    }
    
    /**
     * 
     */
    public ArrayList<String> selectHouseNumByStreetName(String streetName) {
    	ArrayList<String> list = new ArrayList<String>();
    	Cursor cursor = null;
    	String[] columns = {BusStop.COL_HOUSE_NUM};
    	String selection = BusStop.COL_STREET_NAME +"=?";
    	String[] selectionArgs = {streetName};
    	String groupBy = BusStop.COL_HOUSE_NUM;
    	String having = null;
    	String orderBy = null;
    	cursor = db.query(TABLE_BUSSTOP, columns, selection, selectionArgs, groupBy, having, orderBy);
    	if (cursor != null && cursor.moveToFirst()) {
    		do {
    			String name = cursor.getString(0);
    			list.add(name);
    		} while(cursor.moveToNext());
    	}
    	return list;
    }
    //データベースヘルパーの定義
    private static class DBHelper extends SQLiteOpenHelper {
        //コンストラクタ
        public DBHelper(Context context) {
            super(context,DB_NAME,null,DB_VERSION);
        }
        
        //データベースの生成
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_STREET);
            db.execSQL(CREATE_TABLE_BUYT);
            db.execSQL(CREATE_TABLE_BUYTSTOP);
            db.execSQL(CREATE_TABLE_BUSLINE);
        }

        //データベースのアップグレード
        @Override
        public void onUpgrade(SQLiteDatabase db,
            int oldVersion,int newVersion) {
            db.execSQL("drop table if exists "+TABLE_STREET);
            db.execSQL("drop table if exists "+TABLE_BUYT);
            db.execSQL("drop table if exists "+TABLE_BUSSTOP);
            db.execSQL("drop table if exists "+TABLE_BUSLINE);
            onCreate(db);
        }
    }
    
    public void clearAllData() {
    	try {
    	db.delete(TABLE_BUYT, null, null);
    	db.delete(TABLE_STREET, null, null);
    	db.delete(TABLE_BUSSTOP, null, null);
    	db.delete(TABLE_BUSLINE, null, null);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
