package com.example.pandasonic;

public class CustomerInfoItem {
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
}
