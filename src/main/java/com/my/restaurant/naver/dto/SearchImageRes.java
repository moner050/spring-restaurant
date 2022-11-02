package com.my.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImageRes {
    // naver api 의 출력결과를 위한 변수선언
    private String lastBuildDate;
    private Integer total;
    private Integer start;
    private Integer display;
    private List<SearchImageItem> items;

    // items 를 보관하기 위한 정적 클래스 생성
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchImageItem {
        private String title;
        private String link;
        private String thumbnail;
        private String sizeheight;
        private String sizewidth;
    }
}
