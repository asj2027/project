package com.human.gallery.domain.admin;

import com.human.gallery.domain.paging.pageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface iconUser {
    public List<conUserDTO> listuser(pageDTO paging); // 유저 리스트
    public int userCount(pageDTO paging); // 레코드 카운트
}
