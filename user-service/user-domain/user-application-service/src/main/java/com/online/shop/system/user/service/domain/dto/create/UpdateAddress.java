package com.online.shop.system.user.service.domain.dto.create;


import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class UpdateAddress extends CreateAddress{

    @NotNull
    private final Integer addressID;

    public UpdateAddress(@NotNull UUID userID, @NotNull String street, @NotNull String postalCode, @NotNull String city, Integer addressID) {
        super(userID, street, postalCode, city);
        this.addressID = addressID;
    }
}
