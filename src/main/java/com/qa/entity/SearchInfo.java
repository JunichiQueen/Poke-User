package com.qa.entity;

import java.text.SimpleDateFormat;

public class SearchInfo {
	
	private String memberNumber;
	
	private String name;
	
	private String time;
	
	public SearchInfo(String memberNumber, String name) {
		this.memberNumber=memberNumber;
		this.name=name;
	}
	
	public SearchInfo() {
		
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
	public void setTime() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd:HH.mm.ss").format(new java.util.Date());
		this.time = timeStamp;
	}

}
