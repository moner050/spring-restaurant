(function ($) {
    // 검색 결과 vue objects
    var search_result = new Vue({
        el: '#search-result',
        data: {
            search_result: {}
        },
        methods: {
            wishButton: function(event) {
                console.log('add');
            }
        }
    });

    // 나의 맛집 목록 리스트 vue objects
    var wish_list = new Vue ({
        el: '#wish-list',
        data: {
            wish_list: {}
        },
        methods: {
            addVisit: function(idx) {
                $.ajax({
                    type: 'POST',
                    async: true,
                    url:  `/api/restaurant/${idx}`,
                    timeout: 3000,   // 3초
                    // 후처리(성공, 실패)
                    error: function(request, status, error) {
                        alert('방문 추가를 하면서 에러가 발생함');
                    },
                    success: function(response, status, request) {
                        getWishList();
                    },
                });
            },
            deleteWish: function(idx) {
                $.ajax({
                    type: 'DELETE',
                    async: true,
                    url:  `/api/restaurant/${idx}`,
                    timeout: 3000,   // 3초
                    // 후처리(성공, 실패)
                    error: function(request, status, error) {
                        alert('위시리스트 삭제를 하면서 에러가 발생하였습니다.');
                    },
                    success: function(response, status, request) {
                        getWishList();
                    },
                });
            },
        }
    });

    // 텍스트박스 검색 -> Enter 눌렀을 때의 이벤트
    $("#searchBox").keydown(function (key) {
        if (key.keyCode == 13) {     // 13번 enter
            const query = $("#searchBox").val();
            $.get(`/api/restaurant/search?query=${query}`, function(response) {

                // WishlistDto
                search_result.search_result = response;
                $("#search-result").attr('style', 'visible');
            });
        }
    });

    // 검색(Search) -> 검색버튼을 눌렀을 때의 이벤트
    $("#searchButton").click(function () {
        const query = $("#searchBox").val();
        $.get(`/api/restaurant/search?query=${query}`, function(response) {

            // WishlistDto
            search_result.search_result = response;
            $("#search-result").attr('style', 'visible');
        });
    });

    // 위시리스트 추가 -> 위시리시트 버튼을 눌렀을 때의 이벤트
    $("#wishButton").click(function () {
        //alert('위시리스트 추가 버튼 누름');
        $.ajax({
            type: 'POST',
            async: true,
            url:  `/api/restaurant`,
            timeout: 3000,   // 3초
            data: JSON.stringify(search_result.search_result),
            contentType: 'application/json',
            error: function(request, status, error) {
                alert('위시리스트 추가를 하면서 에러가 발생하였습니다.');
            },
            success: function(response, status, request) {
                getWishList();
            },
        });
    });

    function getWishList() {
        $.get(`/api/restaurant/all`, function(response) {
            // WishlistDto
            wish_list.wish_list = response;
        });
    }

    $(document).ready(function ($) {
        $.get(`/api/restaurant/all`, function(response) {
            // WishlistDto
            wish_list.wish_list = response;
        });
    });
})(jQuery);