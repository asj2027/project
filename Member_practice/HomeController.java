package com.human.myapp;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.human.myapp.iMember;
import com.human.myapp.memberDTO;
import com.human.myapp.boardDTO;
import com.human.myapp.iBoard;
import com.human.myapp.HomeController;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req, Model model) {
		iBoard bo=sqlSession.getMapper(iBoard.class);
		ArrayList<boardDTO> bolist=bo.listBoard();
		model.addAttribute("list",bolist);
		
		HttpSession session=req.getSession();
		if(session.getAttribute("id")==null) {
			model.addAttribute("userinfo","");
		} else {
			model.addAttribute("userinfo",session.getAttribute("id"));
		}
		return "home";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String doLogin() {
		return "login";
	}
	@RequestMapping(value="/check")
	public String doUserCheck(HttpServletRequest req, Model model) {
		String id=req.getParameter("id");
		String pw=req.getParameter("pw");
		iMember mem=sqlSession.getMapper(iMember.class);
		memberDTO mdto=mem.signMember(id,pw);
		if(mdto.cnt==0) {
			model.addAttribute("alert","<script>alert('아이디 또는 비밀번호가 일치하지 않습니다.')</script>");
			return "login";
		} else {
			HttpSession session=req.getSession();
			session.setAttribute("id", id);
			session.setAttribute("pw", pw);
			return "redirect:/";
		}
	}
	
	@RequestMapping("/signin")
	public String doSignin() {
		return "signin";
	}
	
	@RequestMapping(value="/addMember",method=RequestMethod.POST)
	public String doAddMember(HttpServletRequest req, Model model) {
		HttpSession session=req.getSession();
		String id=req.getParameter("id");
		String pw=req.getParameter("pw");
		iMember mem=sqlSession.getMapper(iMember.class);
		memberDTO mdto=mem.viewMember(id);
		if(mdto.cnt==0) {
			mem.insert(id,pw);
			return "login";
		} else {
			model.addAttribute("alert","<script>alert('이미 사용중인 아이디입니다.')</script>");
			return "signin";
		}
	}
	
	@RequestMapping("/logout")
	public String doLogout(HttpServletRequest req) {
		HttpSession session=req.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/newpost")
	public String doNewpost() {
		return "new";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String doAddTm(@RequestParam String title, @RequestParam String content, HttpServletRequest req, Model model) {
		iBoard bo=sqlSession.getMapper(iBoard.class);
		HttpSession session=req.getSession();
		String writer=(String) session.getAttribute("id");
		bo.inBoard(title,content,writer);
		return "redirect:/";
	}
	
	@RequestMapping("/delete/{seqbbs}")
	public String doDelBoard(@PathVariable int seqbbs, HttpServletRequest req) {
		iBoard bo=sqlSession.getMapper(iBoard.class);
		boardDTO bdto=bo.viewBoard(seqbbs);
		HttpSession session=req.getSession();
		if(bdto.writer.equals(session.getAttribute("id"))) {
			bo.delBoard(seqbbs);
			return "redirect:/";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/up")
	public String doView(@RequestParam int seqbbs, HttpServletRequest req, Model model) {
		iBoard bo=sqlSession.getMapper(iBoard.class);
		boardDTO bdto=bo.viewBoard(seqbbs);
		model.addAttribute("bdto",bdto);
		HttpSession session=req.getSession();
		if(bdto.writer.equals(session.getAttribute("id"))) {
			return "up";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/view")
	public String doViews(@RequestParam int seqbbs, HttpServletRequest req, Model model) {
		iBoard bo=sqlSession.getMapper(iBoard.class);
		boardDTO bdto=bo.viewBoard(seqbbs);
		model.addAttribute("bdto",bdto);
		return "view";
	}
	
	@RequestMapping("/update")
	public String doUpdate(HttpServletRequest req, Model model) {
		String title=req.getParameter("title");
		String content=req.getParameter("content");
		int seqbbs=Integer.parseInt(req.getParameter("seqbbs"));
		iBoard bo=sqlSession.getMapper(iBoard.class);
		bo.upBoard(title, content, seqbbs);
		return "redirect:/";
	}
}
