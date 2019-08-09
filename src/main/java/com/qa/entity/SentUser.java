package com.qa.entity;

public class SentUser {
	
	private String memberNumber;
	
	private String name;
	
	private String time;
	
	public SentUser(SearchInfo searchinfo) {
		this.name=searchinfo.getName();
		this.memberNumber=searchinfo.getMemberNumber();
		this.time=searchinfo.getTime();
	}
	
	public SentUser() {
		
	}

	public String getMemberNumber() {
		return memberNumber;
	}

	public void setMemberNumber(String memberNumber) {
		this.memberNumber = memberNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	

}
