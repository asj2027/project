package com.human.gallery.domain.payment;

import lombok.Data;

@Data
public class Payment {
    private String orderId;
    private String paymentKey;
    private String paymentDate;
    private int price;
    private String paymentMethod;
}
