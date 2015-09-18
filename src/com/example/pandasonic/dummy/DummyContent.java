package com.example.pandasonic.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.pandasonic.CustomerInfoItem;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

	/**
	 * An array of sample (dummy) items.
	 */
	//public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();
	public static ArrayList<CustomerInfoItem> ITEMS = new ArrayList<CustomerInfoItem>();
	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, CustomerInfoItem> ITEM_MAP = new HashMap<String, CustomerInfoItem>();

	static {
		// Add 3 sample items.
		addItem(new CustomerInfoItem("1", "isaac", "6825571824", "", ""));
		//addItem(new DummyItem("2", "Item 2"));
		//addItem(new DummyItem("3", "Item 3"));
	}

	private static void addItem(CustomerInfoItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.getId(), item);
	}
	
	private static void removeItem(CustomerInfoItem item){
		ITEMS.remove(item);
		ITEM_MAP.remove(item.getId());
	}
	
	public static void addItems(ArrayList<CustomerInfoItem> customerInfoList){
		for(CustomerInfoItem item : customerInfoList)
			addItem(item);
	}
	
	public static void removeItems(){
		if(ITEMS.size() > 0){
			ITEMS.clear();
			ITEM_MAP.clear();
		}
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	/*public static class DummyItem {
		public String id;
		public String name;
		public String phone;
		public String address;
		public String apt;

		public DummyItem(String id, String name, String phone, String address, String apt) {
			this.id = id;
			this.name = name;
			this.phone = phone;
			this.address = address;
			this.apt = apt;
		}

		@Override
		public String toString() {
			return phone;
		}
	}*/
}
