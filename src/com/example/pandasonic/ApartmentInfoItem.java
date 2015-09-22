package com.example.pandasonic;

public class ApartmentInfoItem extends InfoItem {
	private String id = "";
	private String type = "";
	private String name = "";
	private String address = "";
	private String gateCode = "";
	
	public ApartmentInfoItem(String id, String name, String address, String gateCode){
		this.id = id;
		this.name = name;
		this.address = address;
		this.gateCode = gateCode;
	}
	
	public String getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getAddress(){
		return address;
	}
		
	public String getGateCode(){
		return gateCode;
	}
	
	@Override
	public String toString(){
		String str = PandaSonicContract.ApartmentInfo.COLUMN_NAME_APARTMENT_NAME + ": " + name + "\n" +
				PandaSonicContract.ApartmentInfo.COLUMN_NAME_TYPE + ": " + type + "\n" + 
				PandaSonicContract.ApartmentInfo.COLUMN_NAME_ADDRESS + ": " + address + "\n" +
				PandaSonicContract.ApartmentInfo.COLUMN_NAME_GATECODE + ": " + gateCode;
		return str;		
	}
	
	public String displayInfo(){
		return name;
	}
}
