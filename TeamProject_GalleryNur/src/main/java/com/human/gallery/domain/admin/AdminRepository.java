package com.human.gallery.domain.admin;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface AdminRepository {
    ArrayList<WithDrawalReason> withDrawalReason();
    ArrayList<ManageGallery> findSalesForNow();
    ArrayList<ManageGallery> findPeopleForNow();
    ArrayList<ManageGallery> findSalesForYear();
}
