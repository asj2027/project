package com.human.gallery.domain.reserve;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface ReserveRepository {

    void addReserve(Reserve reserve);
    void deleteById(String orderId);
    Reserve findById(String orderId);
    void updatePaymentById(String orderId);
    ArrayList<Reserve> findByUserId(String userId);
    Reserve findByIdWithUserId(String orderId, String userId);
    void updateStateByOrderId(String orderId);

    ArrayList<Reserve> findByDateWithUserId(String userId, String reserveDate);
    ArrayList<Reserve> findWeekByDateWithUserId(@Param("userId")String userId, @Param("startDate") String startDate,
                                                @Param("endDate") String endDate);
}
