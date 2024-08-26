package com.human.gallery.web.comment;


import com.human.gallery.domain.comment.Comment;
import com.human.gallery.domain.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;


    @PostMapping("/commentWrite")
    public void doInsertComment(@ModelAttribute("comment") Comment comment) {
        log.info("넘어온 값만 확인해봅시다 = {}", comment);
        if (comment.getId() == null || comment.getId().equals("")) {
            if (comment.getReparent() != null) {
                Comment parentComment = commentRepository.findById(comment.getReparent());
                log.info("부모 댓글 = {}", parentComment);
                int parentDepth = Integer.parseInt(parentComment.getRedepth())+1;
                comment.setRedepth(String.valueOf(parentDepth));
                int setOrder;
                int tempReOrder = commentRepository.getMaxReorder(comment.getPostId(), comment.getRedepth(), comment.getReparent());
                if (tempReOrder == 0) {
                    setOrder = Integer.parseInt(parentComment.getReorder())+1;
                } else {
                    setOrder = tempReOrder + 1;
                }
                comment.setReorder(String.valueOf(setOrder));
                log.info("부모 댓글과 비교해서 수정 후 = {}", comment);
                log.info("max 값은 무엇인가 = {}", tempReOrder);
                commentRepository.updateParentById(parentComment.getPostId(), String.valueOf(setOrder-1));
            } else {
                Integer reorder = commentRepository.getOrder(comment.getPostId());
                comment.setReorder(String.valueOf(reorder));
                comment.setRedepth(String.valueOf(0));
                comment.setReparent(String.valueOf(0));
                log.info("설정 후 댓글 값 = {}", comment);
            }
            commentRepository.addComment(comment.getPostId(), comment.getWriter(), comment.getContent(), comment.getReparent(), comment.getRedepth(), comment.getReorder());
        } else {
            log.info("이제 너가 나와야 하는거 맞지?");
            commentRepository.updateById(comment.getContent(), comment.getId());
        }
    }
    @GetMapping("/getComment")
    public String returnComment(@RequestParam("postId") String postId,
                                @RequestParam("pageNumber") String pageNumber) {

//        List<Comment> commentList = commentRepository.findByPostId(postId);
        List<Comment> commentList = commentRepository.findByIdWithPaging(postId, pageNumber);
        log.info("댓글 = {}", commentList);
        JSONArray jA = new JSONArray();
        for (int i = 0; i < commentList.size(); i++) {
            Comment comment = commentList.get(i);
            JSONObject jO = new JSONObject();
            jO.put("id", comment.getId());
            jO.put("redepth", comment.getRedepth());
            jO.put("writeName", comment.getWriteName());
            jO.put("writer",comment.getWriter());
            jO.put("content", comment.getContent());
            jO.put("postDate", comment.getPostDate());
            jO.put("isDelete", comment.getIsDelete());
            jA.add(jO);
        }
        return jA.toJSONString();
    }

    @GetMapping("/getCommentNum")
    public Integer returnCommentNum(@RequestParam("postId") String postId) {
        Integer num = commentRepository.findNumByPostId(postId);
        return num;
    }
    @PostMapping("/getCommentById")
    @ResponseBody
    public String returnComment(@RequestParam("commentId") String id) {
        Comment comment = commentRepository.findOneById(id);
        return comment.getContent();
    }

    @PostMapping("/deleteComment")
    public void doDeleteComment(@RequestParam("id") String id) {
        
        // 댓글을 삭제할 때 삭제할 댓글에 답글이 있는지 없는지 체크
        Integer childrenCnt = commentRepository.findChildren(id);
        log.info("댓글에 달린 자식 개수 = {}", childrenCnt);
        // 답글이 안달린 댓글 삭제
        if (childrenCnt == 0 ) {
            log.info("여기서 답글이 안달린 댓글이 삭제된다.");
            Comment comment = commentRepository.findById(id);
            commentRepository.deleteById(comment.getId());
            commentRepository.adjustReorder(comment.getPostId(), comment.getReorder());
        } else {
            log.info("여기는 답글이 달려있는 댓글이기때문에, update로 content만 수정할 예정이다.");
            LocalDate now = LocalDate.now();
            LocalTime nowTime = LocalTime.now();
            int hour = nowTime.getHour();
            int minutes = nowTime.getMinute();
            String temp = "[삭제된 댓글입니다 " + String.valueOf(now) + " " + hour + ":" + minutes + "]";
            commentRepository.updateByIdWithDelete(temp, id);
        }
    }
    @PostMapping("/findAllMyComment")
    @ResponseBody
    public Object returnAllComment(@RequestParam("userId") String id,
                                   @RequestParam("pageNumber") String page) {

        List<Comment> userCommentById = commentRepository.findUserCommentById(id, page);
        return  userCommentById;
    }
    @PostMapping("/getCategory")
    @ResponseBody
    public String returnCategory(@RequestParam("postId") String id) {
        Integer category = commentRepository.returnCategory(id);
        log.info("게시글 카테고리 = {}", category);
        if (category == 3) {
            return "detail";
        } else {
            return "reviewDetail";
        }
    }
    @PostMapping("/getMyCommentNum")
    @ResponseBody
    public Integer returnMyCommentNum(@RequestParam("userId") String id) {

        return commentRepository.findCommentNumById(id);

    }
}
