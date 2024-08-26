package com.human.gallery.domain.comment;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface CommentRepository {

    Integer getOrder(String boardId);
    void addComment(String postId, String writer, String content, String reparent, String redepth, String reorder);

    ArrayList<Comment> findByPostId(String postId);

    Comment findById(String parentid);
    void updateParentById(String postId, String reorder);

    Integer findNumByPostId(String postId);
    void updateById(String content, String id);

    Integer findChildren(String id);

    void updateByIdWithDelete(String content, String id);

    void deleteById(String id);
    void adjustReorder(String postId, String reorder);

    Integer getMaxReorder(String postId, String redepth, String reparent);
    ArrayList<Comment> findByIdWithPaging(@Param("postId") String postId,
                                          @Param("pageNumber") String pageNumber);

    Comment findOneById(@Param("commentId") String id);
    ArrayList<Comment> findUserCommentById(@Param("userId") String userId, @Param("pageNumber") String page);
    Integer returnCategory(@Param("postId") String postId);

    Integer findCommentNumById(@Param("userId") String userId);
}
