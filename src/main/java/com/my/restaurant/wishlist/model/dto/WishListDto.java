package com.my.restaurant.wishlist.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
public class WishListDto {

    // db의 Entity 가 변경되어버리면 프론트까지 변수명을 변경해야 하기 때문에
    // 그런 귀찮은 점을 없에기 위해서 따로 분리를 시킴
    private Integer index;                // 번호
    private String title;                 // 제목
    private String category;              // 카테고리
    private String address;               // 지번 주소
    private String roadAddress;           // 도로명 주소
    private String homepageLink;          // 홈페이지 주소
    private String imageLink;             // 음식, 가게 이미지 주소
    private boolean isVisit;              // 방문여부
    private int visitCount;               // 방문 카운트
    private String lastVisitDate;  // 마지막 방문일자

    @Builder
    public WishListDto(Integer index, String title, String category, String address, String roadAddress, String homepageLink, String imageLink, boolean isVisit, int visitCount, String lastVisitDate) {
        this.index = index;
        this.title = title;
        this.category = category;
        this.address = address;
        this.roadAddress = roadAddress;
        this.homepageLink = homepageLink;
        this.imageLink = imageLink;
        this.isVisit = isVisit;
        this.visitCount = visitCount;
        this.lastVisitDate = lastVisitDate;
    }

    @Builder
    public WishListDto(Integer index, String title, String category, String address, String roadAddress, String homepageLink, String imageLink) {
        this.index = index;
        this.title = title;
        this.category = category;
        this.address = address;
        this.roadAddress = roadAddress;
        this.homepageLink = homepageLink;
        this.imageLink = imageLink;
    }
}
