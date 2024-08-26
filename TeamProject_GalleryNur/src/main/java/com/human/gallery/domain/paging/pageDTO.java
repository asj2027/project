package com.human.gallery.domain.paging;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Slf4j
public class pageDTO implements Serializable {
    private String sort; // 정렬 기준
    private String type; // 검색 타입
    private String keyword; // 검색 키워드

    //입력받는 데이터
    private int curPage=1; // 현재 페이지 번호 (시작=1)
    private int rowSizePerPage=10;   // 한 페이지당 레코드 수(기본10)
    private int pageSize=5;         // 페이지 리스트에서 보여줄 페이지 갯수
    private int totalRowCount;      // 총 레코드 건수

    //입력받는 데이터를 통해 계산되는 값
    private int firstRow;           // 시작 레코드 번호
    private int lastRow;             // 마지막 레코드 번호
    private int totalPageCount;      // 총 페이지 건수
    private int firstPage; 	         // 페이지 리스트에서 시작 페이지 번호
    private int lastPage;            // 페이지 리스트에서 마지막 페이지 번호

    public void pageSetting() {
        totalPageCount=(totalRowCount-1)/rowSizePerPage+1; //총페이지 지정
        firstRow=(curPage-1) * rowSizePerPage+1; //시작 레코드 지정
        lastRow=firstRow + rowSizePerPage-1;
        if(lastRow >= totalRowCount) { //마지막 레코드 지정
            lastRow = totalRowCount;
        }

        firstPage = ((curPage-1)/pageSize)*pageSize+1; //첫페이지

        lastPage = firstPage+pageSize-1; //마지막 페이지
        if(lastPage > totalPageCount) {
            lastPage = totalPageCount;
        }
    }
}