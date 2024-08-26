package com.human.gallery.domain.faq;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ifaq {
    ArrayList<faqDTO> list(String id);
    void addfaq(int category, String question, String answer);
    void faqdelete(int id);
    void updatefaq(faqDTO faq);
    faqDTO addlist(int id);

}
