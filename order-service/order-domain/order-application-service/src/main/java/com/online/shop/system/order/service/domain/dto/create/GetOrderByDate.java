package com.online.shop.system.order.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class GetOrderByDate {
    @NotNull
    private final UUID userID;
    @NotNull
    private final Date startDate;
    @NotNull
    private final Date endDate;
}
