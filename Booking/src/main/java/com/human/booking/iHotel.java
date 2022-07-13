package com.human.booking;

import java.util.ArrayList;

import com.human.booking.hotelDTO;

public interface iHotel {
	ArrayList<hotelDTO> list(); //호텔 테이블 리스트
	ArrayList<hotelDTO> getlist(); //룸 테이블 리스트
	int addhotel(String name, int type, int num, int price); //호텔 테이블에 insert
	hotelDTO viewlist(int seq);
	int updatehotel(String name, int type, int num, int price, int seq); //호텔 테이블 업데이트
	int deletehotel(int seq); //호텔 테이블 delete
	
	ArrayList<hotelDTO> yeyaklist(int type, int num, String checkin, String checkout); //예약가능한 객실 select
	ArrayList<hotelDTO> notlist(int type, int tnum, String checkin, String checkout); //예약불가능 객실
	
	ArrayList<hotelDTO> conlist(int order_no); //선택한 조건의 예약내역
	int addcon(int seq, int tnum, String checkin, String checkout, String tname, String mobile, int tprice, int type); //예약 추가
	int updatecon(int tnum, String tname, String mobile, int order_no); //예약 수정
	int deletecon(int order_no); //예약 삭제
}
