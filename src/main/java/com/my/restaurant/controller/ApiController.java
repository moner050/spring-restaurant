package com.my.restaurant.controller;

import com.my.restaurant.wishlist.model.dto.WishListDto;
import com.my.restaurant.wishlist.model.entity.WishListEntity;
import com.my.restaurant.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class ApiController {

    private final WishListService wishListService;

    // 맛집 검색하기
    @GetMapping("/search")
    public WishListDto search(@RequestParam(name = "query") String query) {
        return wishListService.search(query);
    }

    // 위시리스트에 추가하기
    @PostMapping
    public WishListDto add(@RequestBody WishListDto wishListDto) {
        log.info("{}", wishListDto);

        return wishListService.add(wishListDto);
    }

    // 전체 리스트 가져오기
    @GetMapping("/all")
    public List<WishListDto> findAll() {
        return wishListService.findAll();
    }

    // 방문여부 추가하기
    @PostMapping("/{index}")
    public WishListEntity addVisit(@PathVariable int index) {
        return wishListService.addVisit(index);
    }

    // 위시리스트 삭제하기
    @DeleteMapping("/{index}")
    public WishListEntity deleteWish(@PathVariable int index) {
        return wishListService.deleteWish(index);
    }
}
