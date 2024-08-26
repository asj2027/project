package com.human.gallery.domain.QnA;

import lombok.Data;

@Data
public class qnaDTO {
	private String id;
	private String title;
	private String content;
	private int writer;
	private String userid;
	private int categoryList;
	private int heart;
	private String postdate;
	private int views;
	private int next;
	private int prev;
	private int userNum;
	private int postid;
	private int heartid;
}