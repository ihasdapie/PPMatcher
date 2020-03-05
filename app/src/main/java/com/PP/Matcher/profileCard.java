package com.PP.Matcher;

public class profileCard {
	private String UserID;
	private String firstName;
	private String lastName;
	private String average;
	private String faculty;
	private String year;
	private String prompt1;
	private String prompt2;
	private String prompt3;
	private String response1;
	private String response2;
	private String response3;
	public profileCard(String UserID, String firstName, String lastName, String average, String faculty, String year, String prompt1, String prompt2, String prompt3, String response1, String response2, String response3){
		this.UserID = UserID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.average = average;
		this.faculty = faculty;
		this.year = year;
		this.prompt1 = prompt1;
		this.prompt2 = prompt2;
		this.prompt3 = prompt3;
		this.response1 = response1;
		this.response2 = response2;
		this.response3 = response3;

	}

	public String getUserID(){
		return UserID;
	}
	public String getFirstName(){
		return firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public  String getFaculty() {
		return faculty;
	}
	public String getPrompt1() {
		return prompt1;
	}
	public String getPrompt2() {
		return prompt2;
	}
	public String getPrompt3() {
		return prompt3;
	}
	public String getResponse1() {
		return response1;
	}
	public String getResponse2() {
		return response2;
	}
	public String getResponse3() {
		return response3;
	}
	public String getAverage() {
		return average;
	}
	public String getYear() {
		return year;
	}



	public void setUserID(String UserID) {
		this.UserID = UserID;
	}
	public void setFirstName(String firstName){
		this.firstName=firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAverage(String average) {
		this.average = average;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public void setPrompt1(String prompt1) {
		this.prompt1 = prompt1;
	}

	public void setPrompt2(String prompt2) {
		this.prompt2 = prompt2;
	}

	public void setPrompt3(String prompt3) {
		this.prompt3 = prompt3;
	}

	public void setResponse1(String response1) {
		this.response1 = response1;
	}

	public void setResponse2(String response2) {
		this.response2 = response2;
	}

	public void setResponse3(String response3) {
		this.response3 = response3;
	}

	public void setYear(String year) {
		this.year = year;
	}

}

