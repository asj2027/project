package com.human.ajax;

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
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/list")
	public String doList() {
		return "menu";
	}
	@ResponseBody
	@RequestMapping("/menulist")
	public String doMenulist() {
		iMenu menu=sqlSession.getMapper(iMenu.class);
		ArrayList<menuDTO> arMenu=menu.listMenu();
		JSONArray ja=new JSONArray();
		for(int i=0; i<arMenu.size(); i++) {
			menuDTO mdto=arMenu.get(i);
			JSONObject jo=new JSONObject();
			jo.put("seqno", mdto.getSeqno());
			jo.put("name", mdto.getName());
			jo.put("price", mdto.getPrice());
			ja.add(jo);
		}
		return ja.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("/addnew")
	public String doAddnew(@RequestParam("name") String name, @RequestParam("price") int price) {
		iMenu menu=sqlSession.getMapper(iMenu.class);
		int recount=menu.addMenu(name,price);
		return Integer.toString(recount);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String doDelete(@RequestParam("seqno") int seqno) {
		iMenu menu=sqlSession.getMapper(iMenu.class);
		int recount=menu.delMenu(seqno);
		return Integer.toString(recount);
	}
	
	@ResponseBody
	@RequestMapping("/change")
	public String doUpdate(@RequestParam("name") String name, @RequestParam("price") int price, @RequestParam("seqno") int seqno) {
		iMenu menu=sqlSession.getMapper(iMenu.class);
		int recount=menu.upMenu(name, price, seqno);
		return Integer.toString(recount);
	}

}
