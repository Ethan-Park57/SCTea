package com.app.sctea.model;

import com.google.cloud.Timestamp;
import com.google.firebase.database.annotations.NotNull;

public class Comment{
	@NotNull
	private String userID;
	@NotNull
	private String content;
	@NotNull
	private String displayedName;
	@NotNull
	private Timestamp timestamp;
	@NotNull
	private Integer downvoteNum;
	@NotNull
	private Integer upvoteNum;
	public Comment() {
	}
	public Comment(String userID, String content, String displayedName, Timestamp timestamp, Integer downvoteNum, Integer upvoteNum) {
		this.userID = userID;
		this.content = content;
		this.displayedName = displayedName;
		this.timestamp = timestamp;
		this.downvoteNum = downvoteNum;
		this.upvoteNum = upvoteNum;
	}
	
	public void setuserID(String userID) {
		this.userID = userID; 
	}
	public String getUserID() {
		return userID;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public void setDisplayedName(String displayedName) {
		this.displayedName = displayedName;
	}
	public String getDisplayedName() {
		return displayedName;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setDownvoteNum(Integer downvoteNum) {
		this.downvoteNum = downvoteNum;
	}
	public Integer getDownvoteNum() {
		return downvoteNum;
	}
	public void setUpvoteNum(Integer upvoteNum) {
		this.upvoteNum = upvoteNum;
	}
	public Integer getUpvoteNum() {
		return upvoteNum;
	}
	public String toString() {
		System.out.println("	User: " + userID + "(" + displayedName + ")");
		System.out.println("	Time: " + timestamp);
		System.out.println("	" + upvoteNum + " likes, " + downvoteNum + " dislikes");
		System.out.println("	" + content);
		return "";
		
	}
}
