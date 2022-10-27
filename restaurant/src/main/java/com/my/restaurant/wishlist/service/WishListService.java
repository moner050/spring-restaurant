package com.my.restaurant.wishlist.service;

import com.my.restaurant.naver.NaverClient;
import com.my.restaurant.naver.dto.SearchImageReq;
import com.my.restaurant.naver.dto.SearchImageRes;
import com.my.restaurant.naver.dto.SearchLocalReq;
import com.my.restaurant.naver.dto.SearchLocalRes;
import com.my.restaurant.utils.StringUtil;
import com.my.restaurant.wishlist.model.dto.WishListDto;
import com.my.restaurant.wishlist.model.entity.WishListEntity;
import com.my.restaurant.wishlist.respository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

    public WishListDto search(String query) {
        WishListDto wishListDto = null;

        // 네이버 지역검색 API호출
        SearchLocalReq searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);
        SearchLocalRes searchLocalRes = naverClient.searchLocal(searchLocalReq);

        // 만약 검색결과가 있다면
        if(searchLocalRes.getTotal() > 0) {
            // 첫번째 아이템을 담아준다.
            SearchLocalRes.SearchLocalItem searchLocalItem = searchLocalRes.getItems().stream().findFirst().get();
            // 이상한 문자열(특수문자)을 전부 제거하기 위해 정규식 사용
            String imageQuery = searchLocalItem.getTitle().replaceAll("<[^>]*>", "");
            SearchImageReq searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            // 네이버 이미지검색API 호출
            SearchImageRes searchImageRes = naverClient.searchImage(searchImageReq);
            // 이미지 검색 결과가 있을때
            if(searchImageRes.getTotal() > 0) {
                // 첫번째 아이템을 담아준다.
                SearchImageRes.SearchImageItem searchImageItem = searchImageRes.getItems().stream().findFirst().get();

                // 결과를 리턴
                wishListDto = WishListDto.builder()
                                .title(StringUtil.removeTags(searchLocalItem.getTitle()))
                                .category(searchLocalItem.getCategory())
                                .address(searchLocalItem.getAddress())
                                .roadAddress(searchLocalItem.getRoadAddress())
                                .homepageLink(searchLocalItem.getLink())
                                .imageLink(searchImageItem.getLink())
                                .build();
            }
        }
        return wishListDto;
    }

    // Entity 를 dto 로 변화시키기
    private WishListDto entityToDto(WishListEntity wishListEntity) {
        return WishListDto.builder()
                .index(wishListEntity.getIndex())
                .title(wishListEntity.getTitle())
                .category(wishListEntity.getCategory())
                .address(wishListEntity.getAddress())
                .roadAddress(wishListEntity.getRoadAddress())
                .homepageLink(wishListEntity.getHomepageLink())
                .imageLink(wishListEntity.getImageLink())
                .isVisit(wishListEntity.isVisit())
                .visitCount(wishListEntity.getVisitCount())
                .lastVisitDate(wishListEntity.getLastVisitDate())
                .build();
    }

    // 위시 리스트 추가
    public WishListDto add(WishListDto wishListDto) {
        WishListEntity wishListEntity = WishListEntity.builder()
                .title(wishListDto.getTitle())
                .category(wishListDto.getCategory())
                .address(wishListDto.getAddress())
                .roadAddress(wishListDto.getRoadAddress())
                .homepageLink(wishListDto.getHomepageLink())
                .imageLink(wishListDto.getImageLink())
                .isVisit(wishListDto.isVisit())
                .visitCount(wishListDto.getVisitCount())
                .lastVisitDate(wishListDto.getLastVisitDate())
                .build()
                ;

        return entityToDto(wishListRepository.save(wishListEntity));
    }
    // 위시리스트 목록 전체 불러오기
    public List<WishListDto> findAll() {
        return wishListRepository.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }

    // 방문여부와 방문자수 증가시키기
    public WishListEntity addVisit(int index) {
        WishListEntity wishListEntity = null;

        Optional<WishListEntity> wishListEntityOpt = wishListRepository.findById(index);
        if(wishListEntityOpt.isPresent()) {
            wishListEntity = wishListEntityOpt.get();
            wishListEntity.setVisit(true);
            wishListEntity.setVisitCount(wishListEntity.getVisitCount() + 1);
            wishListEntity.setLastVisitDate();
        }

        return wishListEntity;
    }

    // 위시리스트에서 삭제하기
    public WishListEntity deleteWish(int index) {
        return wishListRepository.deleteById(index);
    }
}
