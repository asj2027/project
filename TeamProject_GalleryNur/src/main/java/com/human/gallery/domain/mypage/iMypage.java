package com.human.gallery.domain.mypage;

import com.human.gallery.domain.user.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface iMypage {
    mypageDTO findById(String id);
    mypageDTO list(String id);
    public List<mypageDTO> findPost(@Param("writer") String writer, @Param("pageNumber") String page);
    void del(Users user);
    void delinfo(String id);
    void adddel(String id, String listVal);
    void updel(String password, String id);
    void nulldel(String id);
    void impoupdate(String id);

    Integer findPostNumById(@Param("userId") String userId);

}
