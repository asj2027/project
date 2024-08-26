package com.human.gallery.domain.payment;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentRepository {

    void addState(Payment payment);
    void deleteById(String orderId);
}
