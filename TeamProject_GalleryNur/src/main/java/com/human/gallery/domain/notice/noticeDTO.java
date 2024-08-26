package com.human.gallery.domain.notice;

import lombok.Data;

@Data
public class noticeDTO {
	private int id;
	private String title;
	private String content;
	private int writer;
	private int categoryList;
	private int heart;
	private String postdate;
	private int views;
	private String userid;
	private int next;
	private int prev;
	private int postid;
	private int heartid;
}
