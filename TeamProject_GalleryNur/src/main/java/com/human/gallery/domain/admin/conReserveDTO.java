package com.human.gallery.domain.admin;

import lombok.Data;

@Data
public class conReserveDTO {
    private String orderId;
    private int exhibitId;
    private String reserveDate;
    private String person;
    private int userId;
    private int amount;
    private String address;
    private String postcode;
    private String detailAddress;
    private String isPayment;
    private String exhibitName;
    private String userName;
}
