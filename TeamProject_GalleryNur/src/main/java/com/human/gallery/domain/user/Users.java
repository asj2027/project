package com.human.gallery.domain.user;

import lombok.Data;

@Data
public class Users {
	
	private int userNum;
	private String id;
	private String password;
	private String role;
	private String address;
	private String dtaddress;
	private String email;
	private String username;
	private int postcode;
	private String mobile;

	private String salt;
	private String signPath;
	private String isDelete;
}
