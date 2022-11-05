package com.online.shop.system.product.service.domain.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Builder
public class PagingResponse {
    @NotNull
    private final Data data;
    @NotNull
    private final int size;
    @NotNull
    private final int total;
    @NotNull
    private final int totalPages;
    @NotNull
    private final int currentPage;

}
