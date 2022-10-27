package com.my.restaurant.wishlist.model.entity;

import com.my.restaurant.db.BaseDbEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class WishListEntity extends BaseDbEntity {
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
    public WishListEntity(String title, String category, String address, String roadAddress, String homepageLink, String imageLink, boolean isVisit, int visitCount, String lastVisitDate) {
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

    public void setVisit(boolean visit) {
        isVisit = visit;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public void setLastVisitDate() {
        this.lastVisitDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
