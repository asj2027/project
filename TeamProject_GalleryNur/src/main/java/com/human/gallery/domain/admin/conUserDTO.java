package com.human.gallery.domain.admin;

import lombok.Data;

@Data
public class conUserDTO {
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
}
