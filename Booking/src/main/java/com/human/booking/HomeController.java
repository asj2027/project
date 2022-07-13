package com.human.booking;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.booking.iHotel;
import com.human.booking.hotelDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SqlSession sqlSession;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {	
		return "hotel";
	}
	
	@ResponseBody
	@RequestMapping(value="/hlist", produces="application/json;charset=utf-8")
	public String doHlist() {
		iHotel hotel=sqlSession.getMapper(iHotel.class);
		ArrayList<hotelDTO> arlist=hotel.list();
		JSONArray ja=new JSONArray();
		for(int i=0; i<arlist.size(); i++) {
			hotelDTO hdto=arlist.get(i);
			JSONObject jo=new JSONObject();
			jo.put("seq", hdto.getSeq());
			jo.put("name", hdto.getName());
			jo.put("type", hdto.getType());
			jo.put("num", hdto.getNum());
			jo.put("price", hdto.getPrice());
			ja.add(jo);
		}
		return ja.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value="/hadd", produces="application/json;charset=utf-8")
	public String doHadd(@RequestParam("name") String name, @RequestParam("type") int type,
							@RequestParam("num") int num, @RequestParam("price") int price) {
		iHotel hotel=sqlSession.getMapper(iHotel.class);
		int recount=hotel.addhotel(name, type, num, price);
		return Integer.toString(recount);
	}
	
	@ResponseBody
	@RequestMapping(value="/hupdate", produces="application/json;charset=utf-8")
	public String doHupdate(@RequestParam("name") String name, @RequestParam("type") int type,
							@RequestParam("num") int num, @RequestParam("price") int price, 
							@RequestParam("seq") int seq) {
		iHotel hotel=sqlSession.getMapper(iHotel.class);
		int recount=hotel.updatehotel(name, type, num, price, seq);
		return Integer.toString(recount);
	}
	
	@ResponseBody
	@RequestMapping("/hdelete")
	public String doHdelete(@RequestParam("seq") int seq) {
		iHotel hotel=sqlSession.getMapper(iHotel.class);
		int recount=hotel.deletehotel(seq);
		return Integer.toString(recount);
	}
	
	@ResponseBody
	@RequestMapping(value="/ye", produces="application/json;charset=utf-8")
	public String doYe(@RequestParam("type") int type, @RequestParam("num") int num,
						@RequestParam("checkin") String checkin, @RequestParam("checkout") String checkout) {
		iHotel hotel=sqlSession.getMapper(iHotel.class);
		ArrayList<hotelDTO> arlist=hotel.yeyaklist(type, num, checkin, checkout);
		JSONArray ja=new JSONArray();
		for(int i=0; i<arlist.size(); i++) {
			hotelDTO hdto=arlist.get(i);
			JSONObject jo=new JSONObject();
			jo.put("seq", hdto.getSeq());
			jo.put("name", hdto.getName());
			jo.put("price", hdto.getPrice());
			jo.put("num", hdto.getNum());
			jo.put("type", hdto.getType());
			jo.put("checkin", hdto.getCheckin());
			jo.put("checkout", hdto.getCheckout());
			ja.add(jo);
		}
		return ja.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value="/not", produces="application/json;charset=utf-8")
	public String doNot(@RequestParam("type") int type, @RequestParam("tnum") int tnum,
						@RequestParam("checkin") String checkin, @RequestParam("checkout") String checkout) {
		iHotel hotel=sqlSession.getMapper(iHotel.class);
		ArrayList<hotelDTO> arlist=hotel.notlist(type, tnum, checkin, checkout);
		JSONArray ja=new JSONArray();
		for(int i=0; i<arlist.size(); i++) {
			hotelDTO hdto=arlist.get(i);
			JSONObject jo=new JSONObject();
			jo.put("seq", hdto.getSeq());
			jo.put("name", hdto.getName());
			jo.put("tnum", hdto.getTnum());
			jo.put("checkin", hdto.getCheckin());
			jo.put("checkout", hdto.getCheckout());
			jo.put("tname", hdto.getTname());
			jo.put("order_no", hdto.getOrder_no());
			jo.put("type", hdto.getType());
			ja.add(jo);
		}
		return ja.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value="/type", produces="application/json;charset=utf-8")
	public String doType() {
		iHotel hotel=sqlSession.getMapper(iHotel.class);
		ArrayList<hotelDTO> arlist=hotel.getlist();
		JSONArray ja=new JSONArray();
		for(int i=0; i<arlist.size(); i++) {
			hotelDTO hdto=arlist.get(i);
			JSONObject jo=new JSONObject();
			jo.put("type", hdto.getType());
			jo.put("typename", hdto.getTypename());
			ja.add(jo);
		}
		return ja.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value="/conlist", produces="application/json;charset=utf-8")
	public String doConlist(@RequestParam("order_no") int order_no) {
		iHotel hotel=sqlSession.getMapper(iHotel.class);
		ArrayList<hotelDTO> arlist=hotel.conlist(order_no);
		JSONArray ja=new JSONArray();
		for(int i=0; i<arlist.size(); i++) {
			hotelDTO hdto=arlist.get(i);
			JSONObject jo=new JSONObject();
			jo.put("order_no", hdto.getOrder_no());
			jo.put("name", hdto.getName());
			jo.put("tnum", hdto.getTnum());
			jo.put("tprice", hdto.getTprice());
			jo.put("tname", hdto.getTname());
			jo.put("mobile", hdto.getMobile());
			jo.put("checkin", hdto.getCheckin());
			jo.put("checkout", hdto.getCheckout());
			jo.put("typename", hdto.getTypename());
			jo.put("seq", hdto.getSeq());
			jo.put("type", hdto.getType());
			ja.add(jo);
		}
		return ja.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value="/conadd", produces="application/json;charset=utf-8")
	public String doConadd(@RequestParam("seq") int seq, @RequestParam("tnum") int tnum,
							@RequestParam("checkin") String checkin, @RequestParam("checkout") String checkout,
							@RequestParam("tname") String tname, @RequestParam("mobile") String mobile,
							@RequestParam("tprice") int tprice, @RequestParam("type") int type) {
		iHotel hotel=sqlSession.getMapper(iHotel.class);
		int recount=hotel.addcon(seq, tnum, checkin, checkout, tname, mobile, tprice, type);
		return Integer.toString(recount);
	}
	
	@ResponseBody
	@RequestMapping(value="/conupdate", produces="application/json;charset=utf-8")
	public String doConupdate(@RequestParam("tnum") int tnum, @RequestParam("tname") String tname,
							@RequestParam("mobile") String mobile, @RequestParam("order_no") int order_no) {
		iHotel hotel=sqlSession.getMapper(iHotel.class);
		int recount=hotel.updatecon(tnum, tname, mobile, order_no);
		return Integer.toString(recount);
	}
	
	@ResponseBody
	@RequestMapping("/condelete")
	public String doCondelete(@RequestParam("order_no") int order_no) {
		iHotel hotel=sqlSession.getMapper(iHotel.class);
		int recount=hotel.deletecon(order_no);
		return Integer.toString(recount);
	}
}
