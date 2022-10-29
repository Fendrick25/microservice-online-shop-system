package com.online.shop.system.product.review.service.domain.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PagingResponse {

    private final Data data;
    private final int size;
    private final int total;
    private final int totalPages;
    private final int currentPage;

}
