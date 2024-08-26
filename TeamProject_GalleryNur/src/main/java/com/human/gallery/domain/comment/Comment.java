package com.human.gallery.domain.comment;

import lombok.Data;

@Data
public class Comment {
    private String id;
    private String postId;
    private String writer;
    private String writeName;
    private String content;
    private String postDate;
    private String reparent;
    private String redepth;
    private String reorder;
    private String isDelete;
}
