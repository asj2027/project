package com.human.gallery.domain.QnA;

import com.human.gallery.domain.paging.pageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface iQna {
    public List<qnaDTO> qnalist(pageDTO paging); // qna 테이블 리스트
	public int getCount(pageDTO paging); // 레코드 개수
	qnaDTO selqna(String id); // 선택한 글
	qnaDTO nepr(@Param("id") String id, @Param("keyword") String keyword, @Param("type") String type, @Param("sort2") String sort); // 이전글, 다음글 번호 불러오기
	public void viewcount(String id); // 조회수
	int addqna(String title, String content, int writer); // qna게시판 add
	int upqna(String title, String content, int id); // 게시판테이블 update
	public int heart(int postid, int heartid); // 추천리스트 카운트 조회
	int addheart(int postid, int heartid); // 추천테이블에 add
	public void plusheart(int id); // 추천+
	public void commentDelete(int id); // 댓글 삭제
	public void heartDelete(int id); // 추천 삭제
	int delqna(int id); // 게시글 삭제
	}