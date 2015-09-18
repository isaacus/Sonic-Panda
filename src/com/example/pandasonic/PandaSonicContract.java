package com.example.pandasonic;

import java.io.IOException;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.text.Selection;

public final class PandaSonicContract {

	private static PandaSonicDbHelper mDbHelper = null;
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_CUSTOMERINFO = 
			"CREATE TABLE " + CustomerInfo.TABLE_NAME + "(" +
			CustomerInfo._ID + " INTEGER PRIMARY KEY," +
			CustomerInfo.COLUMN_NAME_PERSON_NAME + TEXT_TYPE + COMMA_SEP + 
			CustomerInfo.COLUMN_NAME_PHONE + TEXT_TYPE + COMMA_SEP + 
			CustomerInfo.COLUMN_NAME_ADDRESS + TEXT_TYPE + COMMA_SEP +
			CustomerInfo.COLUMN_NAME_APT + TEXT_TYPE + 
			")";
	
	private static final String SQL_DELETE_CUSTOMERINFO = 
			"DROP TABLE IF EXISTS " + CustomerInfo.TABLE_NAME;
	
	public PandaSonicContract(Context context){
		if(mDbHelper == null)
			mDbHelper = new PandaSonicDbHelper(context, PandaSonicDbHelper.DATABASE_NAME, null, PandaSonicDbHelper.DATABASE_VERSION);
	}
	
	public static abstract class CustomerInfo implements BaseColumns{
		public static final String TABLE_NAME = "CustomerInformation";
		public static final String COLUMN_NAME_PERSON_NAME = "Name";
		public static final String COLUMN_NAME_PHONE = "Phone";
		public static final String COLUMN_NAME_ADDRESS = "Address";
		public static final String COLUMN_NAME_APT = "ApartmentName";
	}
	
	public class PandaSonicDbHelper extends SQLiteOpenHelper {
		
		public static final int DATABASE_VERSION = 1;
		public static final String DATABASE_NAME = "PandaSonic.db";
		//public static final String DB_PATH = "/data/data/com.example.PandaSonic/databases/";
		private final Context myContext;

		public PandaSonicDbHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
			this.myContext = context;
		}
		
//		public synchronized void createDatabase() throws IOException {
//			boolean dbExist = checkDataBase();
//		}
//		
//		private boolean checkDataBase(){
//			SQLiteDatabase checkDB = null;
//			
//			try {
//				String myPath = DB_PATH + DATABASE_NAME;
//				checkDB = SQLiteDatabase.openDatabase(myPath, null, DATABASE_VERSION);
//			} catch (SQLiteException e) {
//				
//			}
//			
//			if(checkDB != null)
//				checkDB.close();
//			
//			return checkDB == null? false : true;
//		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(SQL_CREATE_CUSTOMERINFO);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
		public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			onUpgrade(db, oldVersion, newVersion);
		}
		
	}
	
	public long saveCustomerInfo(String[] columnName, String[] columnValue) {		
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		for(int i = 0; i < columnValue.length; i++){
			values.put(columnName[i], columnValue[i]);
		}

		return db.insert(CustomerInfo.TABLE_NAME, null, values);
	}
	
	public Cursor queryCustomerInfo(String[] columnName, String[] columnValue, String sortOrder) {
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		
		if(columnValue == null || columnValue.length == 0)
			return null;
					
		String selection = null;
		ArrayList<String> selectionArgs = new ArrayList<String>();

		for(int i = 0; i < columnName.length; i++){
			if(!(columnValue[i].matches(""))){
				if(selection == null)
					selection = columnName[i] + "=?";
				else
					selection += " AND " + columnName[i] + "=?";
				selectionArgs.add(columnValue[i]);
			}
		}
		
		Cursor result = db.query(CustomerInfo.TABLE_NAME, null, selection, selectionArgs.toArray(new String[selectionArgs.size()]), null, null, sortOrder);
		
		return result;
	}
	
	public int deleteCustomerInfo(String[] columnName, String[] columnValue){
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		
		if(columnValue == null || columnValue.length == 0)
			return 0;
					
		String selection = null;
		ArrayList<String> selectionArgs = new ArrayList<String>();

		for(int i = 0; i < columnName.length; i++){
			if(!(columnValue[i].matches(""))){
				if(selection == null)
					selection = columnName[i] + "=?";
				else
					selection += " AND " + columnName[i] + "=?";
				selectionArgs.add(columnValue[i]);
			}
		}
		
		return db.delete(CustomerInfo.TABLE_NAME, selection, selectionArgs.toArray(new String[selectionArgs.size()]));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
