package com.human.gallery.domain.admin;

import com.human.gallery.domain.paging.pageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface iconReserve {
    public List<conReserveDTO> listReserve(pageDTO paging); // 예약 리스트
    public int reserveCount(pageDTO paging); // 레코드 카운트
    public void payDelete(String orderId); // 결제정보 삭제
    public void reserveDelete(String orderId); // 예약 삭제
}
