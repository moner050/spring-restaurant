package com.my.restaurant.naver;

import com.my.restaurant.naver.dto.SearchImageReq;
import com.my.restaurant.naver.dto.SearchImageRes;
import com.my.restaurant.naver.dto.SearchLocalReq;
import com.my.restaurant.naver.dto.SearchLocalRes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaverClientTest {
    @Autowired
    private NaverClient naverClient;

    @DisplayName("네이버 지역검색 API 테스트")
    @Test
    public void searchLocalTest() {
        SearchLocalReq searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery("갈비집");
        searchLocalReq.setDisplay(5);

        SearchLocalRes searchLocalRes = naverClient.searchLocal(searchLocalReq);
        System.out.println(searchLocalRes);
    }

    @DisplayName("네이버 이미지 API 테스트")
    @Test
    public void searchImageTest() {
        SearchImageReq searchImageReq = new SearchImageReq();
        searchImageReq.setQuery("장수갈비");
        searchImageReq.setDisplay(5);

        SearchImageRes searchImageRes = naverClient.searchImage(searchImageReq);
        System.out.println(searchImageRes);
    }
}
