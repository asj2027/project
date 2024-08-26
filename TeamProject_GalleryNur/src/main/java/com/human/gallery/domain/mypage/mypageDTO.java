package com.human.gallery.domain.mypage;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Data
public class mypageDTO {

 private String id;
 private String username;
 private String mobile;
 private String address;
 private String dtaddress;
 private String email;
 private int postcode;
 private int userNum;
 private String password;
 private String role;
 private String salt;
 private String title;
 private int writer;
 private String postDate;
}
