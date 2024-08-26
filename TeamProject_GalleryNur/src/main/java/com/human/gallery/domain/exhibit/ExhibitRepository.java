package com.human.gallery.domain.exhibit;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ExhibitRepository {

    void addExhibit(String name, String images, String artist, String startDate, String endDate, String info, int price, int total);
    ArrayList<Exhibit> findAll();
    Exhibit findById(int id);

    void deleteById(int id);

    void updateById(String name, String images, String artist, String startDate, String endDate, String info, int price, int total, int id);
    void updateByIdWithoutImages(String name, String artist, String startDate, String endDate, String info, int price, int total, int id);
}
