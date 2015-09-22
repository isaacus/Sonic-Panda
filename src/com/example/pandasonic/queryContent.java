package com.example.pandasonic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.database.Cursor;
import android.widget.SimpleCursorAdapter;


public class queryContent {

	public static ArrayList<InfoItem> ITEMS = new ArrayList<InfoItem>();
	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, InfoItem> ITEM_MAP = new HashMap<String, InfoItem>();
	
	public static String[] fromColumns = null;
	public static int[] toViews = {android.R.id.text1};
	public static Cursor result = null;
	
	public static String[] columnNames = null;
	public static String[] columnValues = null;
	public static String sortOrder = null;
	
	public static PandaSonicContract pandaSonicContract = null;
	
	public static SimpleCursorAdapter adapter = null;
	
	private static void addItem(InfoItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.getId(), item);
	}
	
	private static void removeItem(InfoItem item){
		ITEMS.remove(item);
		ITEM_MAP.remove(item.getId());
	}
	
	public static void addItems(ArrayList<InfoItem> infoList){
		for(InfoItem item : infoList)
			addItem(item);
	}
	
	public static void removeItems(){
		if(ITEMS.size() > 0){
			ITEMS.clear();
			ITEM_MAP.clear();
		}
	}
	
	public static Cursor query(){
		
		result = pandaSonicContract.queryInfo(columnNames, columnValues, sortOrder);

		if(adapter != null)
			adapter.changeCursor(result);
		
		return result;
	}
}
