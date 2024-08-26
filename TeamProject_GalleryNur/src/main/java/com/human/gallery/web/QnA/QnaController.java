package com.human.gallery.web.QnA;


import com.human.gallery.domain.QnA.iQna;
import com.human.gallery.domain.QnA.qnaDTO;
import com.human.gallery.domain.paging.pageDTO;
import com.human.gallery.domain.user.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class QnaController {
	private final iQna qna;
	
	// 리스트 불러오기
	@RequestMapping("/qna")
	public String Qnalist(@SessionAttribute(name = "user", required = false) Users user, Model model,
						  @ModelAttribute("paging") pageDTO paging, @ModelAttribute("sort") String sort,
						  @RequestParam(required = false, defaultValue = "tc") String type,
						  @RequestParam(required = false) String keyword) {
		model.addAttribute("user",user);
		model.addAttribute("paging", paging);
		paging.setType(type);
		paging.setKeyword(keyword);
		int cnt=qna.getCount(paging);
		paging.setTotalRowCount(cnt);
		paging.pageSetting();
		List<qnaDTO> qnalist=qna.qnalist(paging);
		model.addAttribute("qnalist",qnalist);
		return "qna/qnalist";
	}

	// 상세보기
	@RequestMapping(value="/detail", produces="application/json;charset=utf-8")
	public String doDetail(@SessionAttribute(name = "user", required = false) Users user, Model model,
					 	   @ModelAttribute("paging") pageDTO paging, @ModelAttribute("sort") String sort,
						   @RequestParam(required = false, defaultValue = "tc") String type,
						   @RequestParam(required = false, defaultValue = "") String keyword,
						   @RequestParam String id) {
		model.addAttribute("user",user);
		qnaDTO qdto=qna.selqna(id);
		model.addAttribute("qdto",qdto);
		qnaDTO nepr=qna.nepr(id, paging.getKeyword(), paging.getType(), paging.getSort());
		model.addAttribute("nepr", nepr);
		qna.viewcount(id);
		model.addAttribute("paging", paging);
		paging.setType(type);
		paging.setKeyword(keyword);
		int cnt=qna.getCount(paging);
		paging.setTotalRowCount(cnt);
		paging.pageSetting();
		List<qnaDTO> qnalist=qna.qnalist(paging);
		model.addAttribute("qnalist",qnalist);
		return "qna/detail";
	}

	// 글 작성 탭으로 정보 받아오기
	@RequestMapping("/new")
	public String doNew(@SessionAttribute(name = "user", required = false) Users user, Model model) {
		log.info("글쓰기에 넘어옴");
		model.addAttribute("user",user);
		return "qna/qnawrite";
	}

	// 새글 추가
	@PostMapping("/addqna")
	public String doInsert(@RequestParam String title, @RequestParam String content, @RequestParam int writer) {
		qna.addqna(title, content, writer);
		return "redirect:/qna";
	}

	// 유저정보, 기존 글정보 받아오기
	@RequestMapping("/up")
	public String doUp(@SessionAttribute(name = "user", required = false) Users user, Model model,
					   @RequestParam String id) {
		log.info("수정으로 넘어옴");
		model.addAttribute("user",user);
		qnaDTO qdto=qna.selqna(id);
		model.addAttribute("qdto",qdto);
		return "qna/qnaup";
	}

	// 글 업데이트
	@RequestMapping("/upqna")
	public String doUpdate(@RequestParam String title, @RequestParam String content, @RequestParam int id) {
		qna.upqna(title, content, id);
		return "redirect:/qna";
	}

	// 추천수
	@ResponseBody
	@PostMapping("/qna/like")
	public int doLike(@RequestParam int postid, @RequestParam int userid) {
		int heartcheck=qna.heart(postid, userid);
		if(heartcheck==0) {
			qna.plusheart(postid);
			qna.addheart(postid, userid);
		}
		return heartcheck;
	}

	// 게시글 삭제
	@RequestMapping("/delqna")
	public String doDelete(@RequestParam int id) {
		qna.commentDelete(id);
		qna.heartDelete(id);
		qna.delqna(id);
		return "redirect:/qna";
	}
}
