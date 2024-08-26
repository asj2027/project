package com.human.gallery.domain.reserve;

import lombok.Data;

@Data
public class Reserve {
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
