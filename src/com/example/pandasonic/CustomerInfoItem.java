package com.example.pandasonic;

public class CustomerInfoItem extends InfoItem {
	private String id = "";
	private String name = "";
	private String phone = "";
	private String address = "";
	private String apt = "";
	
	public CustomerInfoItem(String id, String name, String phone, String address, String apt){
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.apt = apt;
	}
	
	public String getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getApt(){
		return apt;
	}
	
	@Override
	public String toString(){
		String str = PandaSonicContract.CustomerInfo.COLUMN_NAME_PERSON_NAME + ": " + name + "\n" +
				PandaSonicContract.CustomerInfo.COLUMN_NAME_PHONE + ": " + phone + "\n" + 
				PandaSonicContract.CustomerInfo.COLUMN_NAME_ADDRESS + ": " + address + "\n" +
				PandaSonicContract.CustomerInfo.COLUMN_NAME_APT + ": " + apt;
		return str;		
	}
	
	public String displayInfo(){
		return phone;
	}
}
