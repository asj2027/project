package com.human.gallery.domain.exhibit;

import lombok.Data;

@Data
public class Exhibit {

    private int id;
    private String name;
    private String images;
    private String artist;
    private int artistId;
    private String startDate;
    private String endDate;
    private String info;
    private int price;
    private int total;
}
