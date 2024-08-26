package com.human.gallery.domain.artist;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ArtistRepository {

    void addArtist(String profile, String name, String career, String direction);
    ArrayList<Artist> findAll();
    Artist findById(int id);
    void deleteById(int id);

    void modifyById(String profile, String name, String career, String direction, int id);
    void modifyByIdWithOutImage(String name, String career, String direction, int id);
}
