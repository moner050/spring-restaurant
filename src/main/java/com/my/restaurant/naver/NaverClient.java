package com.my.restaurant.naver;

import com.my.restaurant.naver.dto.SearchImageReq;
import com.my.restaurant.naver.dto.SearchImageRes;
import com.my.restaurant.naver.dto.SearchLocalReq;
import com.my.restaurant.naver.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class NaverClient {

    // application.yml 에 설정된 값 불러오기
    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverLocalSearchImage;

    public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq) {
        URI uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                                    .queryParams(searchLocalReq.toMultiValueMap())
                                    .build().encode().toUri();

        // headers 에 id 와 secret 을 JSON 형태로 담아준다.
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // responseType 을 SearchLocalRes 로 담아줌
        ParameterizedTypeReference<SearchLocalRes> responseType = new ParameterizedTypeReference<>(){};

        // httpEntity 에 headers 의 내용을 담아준다.
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        // RestTemplate 를 통해서 받아주는 절차
        ResponseEntity<SearchLocalRes> searchLocalRestTemplate = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return searchLocalRestTemplate.getBody();
    }

    public SearchImageRes searchImage(SearchImageReq searchImageReq) {
        URI uri = UriComponentsBuilder.fromUriString(naverLocalSearchImage)
                .queryParams(searchImageReq.toMultiValueMap())
                .build().encode().toUri();

        // headers 에 id 와 secret 을 JSON 형태로 담아준다.
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // responseType 을 SearchLocalRes 로 담아줌
        ParameterizedTypeReference<SearchImageRes> responseType = new ParameterizedTypeReference<>(){};

        // httpEntity 에 headers 의 내용을 담아준다.
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        // RestTemplate 를 통해서 받아주는 절차
        ResponseEntity<SearchImageRes> searchLocalRestTemplate = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return searchLocalRestTemplate.getBody();
    }
}
