package com.human.gallery.domain.review;

import com.human.gallery.domain.paging.pageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewRepository {
	public List<Review> reviewList(pageDTO paging); // 리뷰 테이블 리스트
	public int getCount(pageDTO paging); // 레코드 개수
	Review selView(String id); // 선택한 글
	Review movePage(@Param("id") String id, @Param("keyword") String keyword, @Param("type") String type, @Param("sort2") String sort); // 이전글, 다음글 번호 불러오기
	public void count(String id); // 조회수
	int insertReview(String title, String content, int writer); // 리뷰게시판 add
	int updateReview(String title, String content, int id); // 게시판테이블 update
	public int findLike(int postid, int heartid); // 추천리스트 카운트 조회
	int insertLike(int postid, int heartid); // 추천테이블에 add
	public void likeReview(int id); // 추천 +1
	public void commentDelete(int id); // 댓글 삭제
	public void heartDelete(int id); // 추천 삭제
	int deleteReview(int id); // 게시글 삭제
}