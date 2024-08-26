package com.human.gallery.domain.review;

import java.util.List;

import com.human.gallery.domain.paging.pageDTO;
import com.human.gallery.domain.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ReviewController {
	
	@Autowired
	private ReviewRepository review;
	
	// 리스트 불러오기
	@RequestMapping("/review")
	public String viewReview(@SessionAttribute(name = "user", required = false) Users user, Model model,
							 @ModelAttribute("paging") pageDTO paging, @ModelAttribute("sort") String sort,
							 @RequestParam(required = false, defaultValue = "tc") String type,
							 @RequestParam(required = false) String keyword) {
		model.addAttribute("user",user);
		model.addAttribute("paging", paging);
		paging.setType(type);
		paging.setKeyword(keyword);
		int cnt=review.getCount(paging);
		paging.setTotalRowCount(cnt);
		paging.pageSetting();
		List<Review> reviewList=review.reviewList(paging);
		model.addAttribute("reviewlist",reviewList);
		return "review/review";
	}

	// 상세보기
	@RequestMapping(value="/reviewDetail", produces="application/json;charset=utf-8")
	public String doDetail(@SessionAttribute(name = "user", required = false) Users user, Model model,
						   @ModelAttribute("paging") pageDTO paging, @ModelAttribute("sort") String sort,
						   @RequestParam(required = false, defaultValue = "tc") String type,
						   @RequestParam(required = false, defaultValue = "") String keyword,
						   @RequestParam String id) {
		model.addAttribute("user",user);
		Review rdto=review.selView(id);
		model.addAttribute("rdto", rdto);
		Review ndto=review.movePage(id, paging.getKeyword(), paging.getType(), paging.getSort());
		model.addAttribute("ndto", ndto);
		review.count(id);
		model.addAttribute("paging", paging);
		paging.setType(type);
		paging.setKeyword(keyword);
		int cnt=review.getCount(paging);
		paging.setTotalRowCount(cnt);
		paging.pageSetting();
		List<Review> reviewlist=review.reviewList(paging);
		model.addAttribute("reviewlist", reviewlist);
		return "review/reviewDetail";
	}

	// 글 작성 탭으로 정보 받아오기
	@RequestMapping("/writeReview")
	public String doWriteReview(@SessionAttribute(name = "user", required = false) Users user, Model model) {
		model.addAttribute("user",user);
		return "review/writeReview";
	}

	// 새글 추가
	@PostMapping("/insertReview")
	public String doInsert(@RequestParam String title, @RequestParam(value = "content" , required = false) String content, @RequestParam int writer) {
		log.info("넘어온 값 = {}, {}, {}", title, content, writer);
		review.insertReview(title, content, writer);
		return "redirect:/review";
	}

	// 유저정보, 기존 글정보 받아오기
	@RequestMapping("/reviewUpdate")
	public String doUpdateReview(@SessionAttribute(name = "user", required = false) Users user,
								 Model model, @RequestParam String id) {
		Review rdto=review.selView(id);
		model.addAttribute("user", user);
		model.addAttribute("rdto", rdto);
		return "review/updateReview";
	}

	// 글 업데이트
	@RequestMapping("/updateReview")
	public String doUpdate(@RequestParam(value="title", required=false) String title,
						   @RequestParam(value="content", required=false) String content,
						   @RequestParam int id) {
		review.updateReview(title, content, id);
		return "redirect:/review";
	}

	// 추천수
	@ResponseBody
	@RequestMapping(value="/review/like", method=RequestMethod.POST)
	public int doLike(@RequestParam int postid, @RequestParam int userid) {
		int findLike=review.findLike(postid, userid);
		if(findLike == 0) {
			review.likeReview(postid);
			review.insertLike(postid, userid);
		}
		return findLike;
	}

	// 게시글 삭제
	@RequestMapping("/deleteReview")
	public String doDelete(@RequestParam int id) {
		review.commentDelete(id);
		review.heartDelete(id);
		review.deleteReview(id);
		return "redirect:/review";
	}
}