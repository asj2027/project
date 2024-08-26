package com.human.gallery.domain.notice;

import com.human.gallery.domain.paging.pageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface inotice {
    public List<noticeDTO> listnotice(pageDTO paging); // notice 테이블 리스트
    public int getCount(pageDTO paging); // 레코드 개수
    noticeDTO noticecontent(String id); // 선택한 글
    noticeDTO nepr(@Param("id") String id, @Param("keyword") String keyword, @Param("type") String type, @Param("sort2") String sort); // 이전글 다음글
    public void count(String id); // 조회수
    int addnotice(String title, String content); // 공지 add
    int updatenotice(String title, String content, int id); // 공지테이블 update
    public int findLike(int postid, int heartid); // 추천 리스트 카운트 조회
    int insertLike(int postid, int heartid); // 추천테이블에 add
    public void likeNotice(int id); // 추천 +1
    public void heartDelete(String id); // 추천 삭제
    public void delete(String id); // 공지 삭제
}
