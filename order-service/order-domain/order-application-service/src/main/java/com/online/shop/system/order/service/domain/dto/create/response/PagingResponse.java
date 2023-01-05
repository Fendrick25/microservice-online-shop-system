package com.online.shop.system.order.service.domain.dto.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@Builder
public class PagingResponse<T> {
    @NotNull
    private final T data;
    @NotNull
    private final int size;
    @NotNull
    private final int total;
    @NotNull
    private final int totalPages;
    @NotNull
    private final int currentPage;

}
