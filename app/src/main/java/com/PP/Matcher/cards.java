package com.PP.Matcher;

public class cards {
	private String UserID;
	private String name;

	public void populateCards(String UserID, String name){
		this.UserID = UserID;
		this.name = name;


	}

	public String getUserID(){
		return UserID;
	}

	public void setUserID(String UserID){
		this.UserID = UserID;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}




}
